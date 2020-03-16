/*
 * This file is part of emuStudio.
 *
 * Copyright (C) 2006-2020  Peter Jakubčo
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package net.emustudio.application.virtualcomputer;

import net.emustudio.application.configuration.ApplicationConfig;
import net.emustudio.application.configuration.ComputerConfig;
import net.emustudio.application.configuration.PluginConfig;
import net.emustudio.application.configuration.PluginSettingsImpl;
import net.emustudio.application.internal.Unchecked;
import net.emustudio.emulib.plugins.Plugin;
import net.emustudio.emulib.plugins.PluginInitializationException;
import net.emustudio.emulib.plugins.annotations.PLUGIN_TYPE;
import net.emustudio.emulib.plugins.compiler.Compiler;
import net.emustudio.emulib.plugins.cpu.CPU;
import net.emustudio.emulib.plugins.device.Device;
import net.emustudio.emulib.plugins.memory.Memory;
import net.emustudio.emulib.runtime.ApplicationApi;
import net.emustudio.emulib.runtime.PluginSettings;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static net.emustudio.application.internal.Reflection.doesImplement;

public class VirtualComputer implements PluginConnections {
    private static final Map<PLUGIN_TYPE, Class<? extends Plugin>> pluginInterfaces = Map.of(
        PLUGIN_TYPE.COMPILER, Compiler.class,
        PLUGIN_TYPE.CPU, CPU.class,
        PLUGIN_TYPE.MEMORY, Memory.class,
        PLUGIN_TYPE.DEVICE, Device.class
    );

    private final ComputerConfig computerConfig;

    private final Map<Long, PluginMeta> pluginsById = new HashMap<>();
    private final Map<PLUGIN_TYPE, List<PluginMeta>> pluginsByType = new HashMap<>();

    public VirtualComputer(ComputerConfig computerConfig, ApplicationApi applicationApi, ApplicationConfig applicationConfig)
        throws IOException, InvalidPluginException {

        this.computerConfig = Objects.requireNonNull(computerConfig);

        Map<Long, PluginMeta> plugins = loadPlugins(applicationApi, applicationConfig);

        plugins.forEach((pluginId, pluginMeta) -> {
            pluginsById.put(pluginId, pluginMeta);

            List<PluginMeta> metas = pluginsByType.putIfAbsent(pluginMeta.pluginConfig.getPluginType(), new ArrayList<>());
            if (metas != null) {
                metas.add(pluginMeta);
            }
        });
    }

    public  ComputerConfig getComputerConfig() {
        return computerConfig;
    }

    public void initialize(ContextPoolImpl contextPool) throws PluginInitializationException {
        contextPool.setComputer(this);
        List<PluginMeta> pluginsToInitialize = List.of(
            pluginsByType.getOrDefault(PLUGIN_TYPE.COMPILER, Collections.emptyList()),
            pluginsByType.getOrDefault(PLUGIN_TYPE.MEMORY, Collections.emptyList()),
            pluginsByType.getOrDefault(PLUGIN_TYPE.CPU, Collections.emptyList()),
            pluginsByType.getOrDefault(PLUGIN_TYPE.DEVICE, Collections.emptyList())
        ).stream().flatMap(Collection::stream).collect(Collectors.toList());

        pluginsToInitialize.forEach(meta -> Unchecked.run(meta.pluginInstance::initialize));
    }

    public boolean isConnected(long pluginA, long pluginB) {
        String fst = pluginsById.get(pluginA).pluginConfig.getPluginId();
        String snd = pluginsById.get(pluginB).pluginConfig.getPluginId();

        return computerConfig.getConnections().stream().anyMatch(connection -> {
            boolean oneWay = connection.getFromPluginId().equals(fst) && connection.getToPluginId().equals(snd);
            boolean otherWay = connection.getFromPluginId().equals(snd) && connection.getToPluginId().equals(fst);

            return oneWay || (connection.isBidirectional() && otherWay);
        });
    }

    public Optional<Compiler> getCompiler() {
        List<PluginMeta> meta = Optional.ofNullable(pluginsByType.get(PLUGIN_TYPE.COMPILER)).orElse(Collections.emptyList());
        if (meta.isEmpty()) return Optional.empty();
        else return Optional.of((Compiler) meta.get(0).pluginInstance);
    }

    public Optional<CPU> getCPU() {
        List<PluginMeta> meta = Optional.ofNullable(pluginsByType.get(PLUGIN_TYPE.CPU)).orElse(Collections.emptyList());
        if (meta.isEmpty()) return Optional.empty();
        else return Optional.of((CPU) meta.get(0).pluginInstance);
    }

    public Optional<Memory> getMemory() {
        List<PluginMeta> meta = Optional.ofNullable(pluginsByType.get(PLUGIN_TYPE.MEMORY)).orElse(Collections.emptyList());
        if (meta.isEmpty()) return Optional.empty();
        else return Optional.of((Memory) meta.get(0).pluginInstance);
    }

    public List<Device> getDevices() {
        List<PluginMeta> meta = Optional.ofNullable(pluginsByType.get(PLUGIN_TYPE.CPU)).orElse(Collections.emptyList());
        return meta.stream().map(m -> (Device) m.pluginInstance).collect(Collectors.toList());
    }

    public void close() {
        computerConfig.close();
    }

    private Map<Long, PluginMeta> loadPlugins(ApplicationApi applicationApi, ApplicationConfig applicationConfig) throws IOException, InvalidPluginException {
        List<PluginConfig> pluginConfigs = List.of(
            computerConfig.getCompiler(),
            computerConfig.getCPU(),
            computerConfig.getMemory()
        ).stream()
            .map(opt -> opt.map(List::of).orElse(Collections.emptyList()))
            .flatMap(List::stream)
            .collect(Collectors.toList());
        pluginConfigs.addAll(computerConfig.getDevices());

        List<File> filesToLoad = pluginConfigs.stream()
            .map(c -> c.getPluginFile().toFile())
            .collect(Collectors.toList());

        PluginLoader pluginLoader = new PluginLoader();
        List<Class<Plugin>> pluginClasses = pluginLoader.loadPlugins(filesToLoad);

        return constructPlugins(pluginClasses, pluginConfigs, applicationApi, applicationConfig);
    }

    private Map<Long, PluginMeta> constructPlugins(List<Class<Plugin>> pluginClasses, List<PluginConfig> pluginConfigs,
                                                   ApplicationApi applicationApi, ApplicationConfig applicationConfig)
        throws InvalidPluginException {

        Map<Long, PluginMeta> plugins = new HashMap<>();
        AtomicLong pluginIdCounter = new AtomicLong();

        IntStream
            .range(0, Math.min(pluginClasses.size(), pluginConfigs.size()))
            .forEach(i -> {
                Class<Plugin> pluginClass = pluginClasses.get(i);
                PluginConfig pluginConfig = pluginConfigs.get(i);
                PluginSettings pluginSettings = new PluginSettingsImpl(
                    pluginConfig.getPluginSettings(), computerConfig, applicationConfig
                );

                if (!doesImplement(pluginClass, pluginInterfaces.get(pluginConfig.getPluginType()))) {
                    Unchecked.sneakyThrow(new InvalidPluginException(
                        "Plugin" + pluginConfig.getPluginName() + " does not implement interface " + pluginClass.getName()
                    ));
                }

                long pluginId = pluginIdCounter.getAndIncrement();
                Plugin pluginInstance = Unchecked.call(
                    () -> createPluginInstance(pluginId, pluginClass, applicationApi, pluginSettings)
                );

                PluginMeta pluginMeta = new PluginMeta(pluginSettings, pluginInstance, pluginConfig);
                plugins.put(pluginId, pluginMeta);
            });

        return plugins;
    }

    private Plugin createPluginInstance(long pluginID, Class<? extends Plugin> mainClass, ApplicationApi applicationApi,
                                        PluginSettings pluginSettings) throws InvalidPluginException {
        Objects.requireNonNull(mainClass);
        Objects.requireNonNull(applicationApi);

        // First parameter of constructor is plug-in ID
        Class<?>[] constructorParams = { long.class, ApplicationApi.class, PluginSettings.class };

        try {
            Constructor<?> constructor = mainClass.getDeclaredConstructor(constructorParams);
            if (constructor != null) {
                return (Plugin) constructor.newInstance(pluginID, applicationApi, pluginSettings);
            } else {
                throw new InvalidPluginException("Could not find constructor with parameters: " + Arrays.deepToString(constructorParams));
            }
        } catch (InvalidPluginException e) {
            throw e;
        } catch (Exception | NoClassDefFoundError e) {
            throw new InvalidPluginException("Plug-in main class does not have proper constructor", e);
        }
    }

    static class PluginMeta {
        final PluginSettings pluginSettings;
        final Plugin pluginInstance;
        final PluginConfig pluginConfig;

        public PluginMeta(PluginSettings pluginSettings, Plugin pluginInstance, PluginConfig pluginConfig) {
            this.pluginSettings = Objects.requireNonNull(pluginSettings);
            this.pluginInstance = Objects.requireNonNull(pluginInstance);
            this.pluginConfig = Objects.requireNonNull(pluginConfig);
        }
    }
}
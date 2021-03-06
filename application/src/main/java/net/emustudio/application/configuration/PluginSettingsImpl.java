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
package net.emustudio.application.configuration;

import com.electronwill.nightconfig.core.Config;
import net.emustudio.emulib.runtime.CannotUpdateSettingException;
import net.emustudio.emulib.runtime.PluginSettings;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class PluginSettingsImpl implements PluginSettings {
    private final Config config;
    private final ConfigSaver saver;
    private final ApplicationConfig applicationConfig;

    public PluginSettingsImpl(Config config, ConfigSaver saver, ApplicationConfig applicationConfig) {
        this.config = Objects.requireNonNull(config);
        this.saver = Objects.requireNonNull(saver);
        this.applicationConfig = Objects.requireNonNull(applicationConfig);
    }

    @Override
    public boolean contains(String key) {
        if (key.startsWith(EMUSTUDIO_PREFIX)) {
            return applicationConfig.contains(key.substring(EMUSTUDIO_PREFIX.length()));
        }
        return config.contains(key);
    }

    @Override
    public void remove(String key) throws CannotUpdateSettingException {
        checkDoesNotStartWithEmuStudioPrefix(key);
        config.remove(key);
        saver.save();
    }

    @Override
    public Optional<String> getString(String key) {
        if (key.startsWith(EMUSTUDIO_PREFIX)) {
            return applicationConfig.getString(key.substring(EMUSTUDIO_PREFIX.length()));
        }
        return config.getOptional(key);
    }

    @Override
    public String getString(String key, String defaultValue) {
        if (key.startsWith(EMUSTUDIO_PREFIX)) {
            return applicationConfig.getString(key.substring(EMUSTUDIO_PREFIX.length())).orElse(defaultValue);
        }
        return config.<String>getOptional(key).orElse(defaultValue);
    }

    @Override
    public Optional<Boolean> getBoolean(String key) {
        if (key.startsWith(EMUSTUDIO_PREFIX)) {
            return applicationConfig.getBoolean(key.substring(EMUSTUDIO_PREFIX.length()));
        }
        return config.getOptional(key);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        if (key.startsWith(EMUSTUDIO_PREFIX)) {
            return applicationConfig.getBoolean(key.substring(EMUSTUDIO_PREFIX.length())).orElse(defaultValue);
        }
        return config.<Boolean>getOptional(key).orElse(defaultValue);
    }

    @Override
    public Optional<Integer> getInt(String key) {
        if (key.startsWith(EMUSTUDIO_PREFIX)) {
            return applicationConfig.getInt(key.substring(EMUSTUDIO_PREFIX.length()));
        }
        return config.getOptional(key);
    }

    @Override
    public int getInt(String key, int defaultValue) {
        if (key.startsWith(EMUSTUDIO_PREFIX)) {
            return applicationConfig.getInt(key.substring(EMUSTUDIO_PREFIX.length())).orElse(defaultValue);
        }
        return config.getOptionalInt(key).orElse(defaultValue);
    }

    @Override
    public Optional<Long> getLong(String key) {
        if (key.startsWith(EMUSTUDIO_PREFIX)) {
            return applicationConfig.getLong(key.substring(EMUSTUDIO_PREFIX.length()));
        }
        return config.getOptional(key);
    }

    @Override
    public long getLong(String key, long defaultValue) {
        if (key.startsWith(EMUSTUDIO_PREFIX)) {
            return applicationConfig.getLong(key.substring(EMUSTUDIO_PREFIX.length())).orElse(defaultValue);
        }
        return config.getOptionalLong(key).orElse(defaultValue);
    }

    @Override
    public Optional<Double> getDouble(String key) {
        if (key.startsWith(EMUSTUDIO_PREFIX)) {
            return applicationConfig.getDouble(key.substring(EMUSTUDIO_PREFIX.length()));
        }
        return config.getOptional(key);
    }

    @Override
    public double getDouble(String key, double defaultValue) {
        if (key.startsWith(EMUSTUDIO_PREFIX)) {
            return applicationConfig.getDouble(key.substring(EMUSTUDIO_PREFIX.length())).orElse(defaultValue);
        }
        return config.<Double>getOptional(key).orElse(defaultValue);
    }

    @Override
    public List<String> getArray(String key) {
        if (key.startsWith(EMUSTUDIO_PREFIX)) {
            return applicationConfig.getArray(key.substring(EMUSTUDIO_PREFIX.length()));
        }
        return config.<List<String>>getOptional(key).orElse(Collections.emptyList());
    }

    @Override
    public List<String> getArray(String key, List<String> defaultValue) {
        if (key.startsWith(EMUSTUDIO_PREFIX)) {
            return applicationConfig.getArray(key.substring(EMUSTUDIO_PREFIX.length()));
        }
        return config.<List<String>>getOptional(key).orElse(defaultValue);
    }

    @Override
    public void setString(String key, String value) throws CannotUpdateSettingException {
        checkDoesNotStartWithEmuStudioPrefix(key);
        config.set(key, value);
        saver.save();
    }

    @Override
    public void setBoolean(String key, boolean value) throws CannotUpdateSettingException {
        checkDoesNotStartWithEmuStudioPrefix(key);
        config.set(key, value);
        saver.save();
    }

    @Override
    public void setInt(String key, int value) throws CannotUpdateSettingException {
        checkDoesNotStartWithEmuStudioPrefix(key);
        config.set(key, value);
        saver.save();
    }

    @Override
    public void setLong(String key, long value) throws CannotUpdateSettingException {
        checkDoesNotStartWithEmuStudioPrefix(key);
        config.set(key, value);
        saver.save();
    }

    @Override
    public void setDouble(String key, double value) throws CannotUpdateSettingException {
        checkDoesNotStartWithEmuStudioPrefix(key);
        config.set(key, value);
        saver.save();
    }

    @Override
    public void setArray(String key, List<String> value) throws CannotUpdateSettingException {
        checkDoesNotStartWithEmuStudioPrefix(key);
        config.set(key, value);
        saver.save();
    }

    private void checkDoesNotStartWithEmuStudioPrefix(String key) {
        if (key.startsWith(EMUSTUDIO_PREFIX)) {
            throw new IllegalArgumentException("Key cannot start with " + EMUSTUDIO_PREFIX);
        }
    }
}

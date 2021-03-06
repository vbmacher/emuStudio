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
package net.emustudio.plugins.compiler.asZ80;


import net.emustudio.cpu.testsuite.memory.MemoryStub;
import net.emustudio.cpu.testsuite.memory.ShortMemoryStub;
import net.emustudio.emulib.plugins.memory.MemoryContext;
import net.emustudio.emulib.runtime.ApplicationApi;
import net.emustudio.emulib.runtime.ContextPool;
import net.emustudio.emulib.runtime.PluginSettings;
import net.emustudio.emulib.runtime.helpers.NumberUtils;
import net.emustudio.plugins.compiler.asZ80.CompilerImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

public abstract class AbstractCompilerTest {
    protected CompilerImpl compiler;
    protected MemoryStub<Short> memoryStub;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() throws Exception {
        memoryStub = new ShortMemoryStub(NumberUtils.Strategy.LITTLE_ENDIAN);

        ContextPool pool = createNiceMock(ContextPool.class);
        expect(pool.getMemoryContext(0, MemoryContext.class)).andReturn(memoryStub).anyTimes();
        replay(pool);

        ApplicationApi applicationApi = createNiceMock(ApplicationApi.class);
        expect(applicationApi.getContextPool()).andReturn(pool).anyTimes();
        replay(applicationApi);

        compiler = new CompilerImpl(0L, applicationApi, PluginSettings.UNAVAILABLE);
        compiler.initialize();
    }

    protected void compile(String content) throws Exception {
        File sourceFile = folder.newFile();
        Files.write(sourceFile.toPath(), content.getBytes(), StandardOpenOption.WRITE);

        File outputFile = folder.newFile();
        if (!compiler.compile(sourceFile.getAbsolutePath(), outputFile.getAbsolutePath())) {
            throw new Exception("Compilation error");
        }
    }

    protected void assertProgram(int... bytes) {
        for (int i = 0; i < bytes.length; i++) {
            assertEquals(
                String.format("%d. expected=%x, but was=%x", i, bytes[i], memoryStub.read(i)),
                bytes[i], (int) memoryStub.read(i)
            );
        }
        for (int i = bytes.length; i < memoryStub.getSize(); i++) {
            assertEquals(
                String.format("%d. expected=%x, but was=%x", i, 0, memoryStub.read(i)),
                0, (int) memoryStub.read(i)
            );
        }
    }
}

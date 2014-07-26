/*
 * CompilerImpl.java
 *
 * Copyright (C) 2009-2012 Peter Jakubčo
 * KISS, YAGNI, DRY
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package net.sf.emustudio.brainduck.brainc.impl;

import emulib.annotations.PLUGIN_TYPE;
import emulib.annotations.PluginType;
import emulib.plugins.compiler.AbstractCompiler;
import emulib.plugins.compiler.LexicalAnalyzer;
import emulib.plugins.compiler.Message;
import emulib.plugins.compiler.SourceFileExtension;
import emulib.plugins.memory.MemoryContext;
import emulib.runtime.ContextPool;
import emulib.runtime.HEXFileManager;
import emulib.runtime.LoggerFactory;
import emulib.runtime.interfaces.Logger;
import java.io.FileReader;
import java.io.Reader;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import net.sf.emustudio.brainduck.brainc.tree.Program;

/**
 * Main class implementing the compiler.
 *
 * @author Peter Jakubčo
 */
@PluginType(type=PLUGIN_TYPE.COMPILER,
        title="BrainDuck Compiler",
        copyright="\u00A9 Copyright 2009-2012, Peter Jakubčo",
        description="Compiler for esoteric architecture called BrainDuck.")
public class CompilerImpl extends AbstractCompiler {
    private final static Logger LOGGER = LoggerFactory.getLogger(CompilerImpl.class);
    private LexerBD lex;
    private ParserBD par;
    private SourceFileExtension[] suffixes;

    /**
     * Public constructor.
     *
     * @param pluginID plug-in identification number
     */
    public CompilerImpl(Long pluginID) {
        super(pluginID);
        // lex has to be reset WITH a reader object before compile
        lex = new LexerBD((Reader) null);
        par = new ParserBD(lex, this);
        suffixes = new SourceFileExtension[1];
        suffixes[0] = new SourceFileExtension("asm", "Brainduck assembler source");
    }

    @Override
    public String getVersion() {
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("net.sf.emustudio.brainduck.brainc.version");
            return bundle.getString("version");
        } catch (MissingResourceException e) {
            return "(unknown)";
        }
    }

    @Override
    public void destroy() {
        this.par = null;
        this.lex = null;
    }

    /**
     * Compile the source code into HEXFileHadler
     * 
     * @param in  Reader object of the source code
     * @return HEXFileManager object
     */
    private HEXFileManager compile(Reader in) throws Exception {
        if (in == null) {
            throw new Exception("Reader is null");
        }

        Object parsedProgram;
        HEXFileManager hex = new HEXFileManager();

        notifyInfo(CompilerImpl.class.getAnnotation(PluginType.class).title() + ", version " + getVersion());
        lex.reset(in, 0, 0, 0);
        parsedProgram = par.parse().value;

        if (parsedProgram == null) {
            notifyError("Unexpected end of file");
            throw new Exception("Unexpected end of file");
        }
        if (par.errorCount != 0) {
            throw new Exception("Program has errors");
        }

        // do several passes for compiling
        Program program = (Program) parsedProgram;
        program.firstPass(0);
        program.secondPass(hex);
        return hex;
    }

    @Override
    public boolean compile(String fileName, Reader in) {
        try {
            HEXFileManager hex = compile(in);
            
            // Remove ".*" suffix and add ".hex" suffix to the filename
            int i = fileName.lastIndexOf(".");
            if (i >= 0) {
                fileName = fileName.substring(0, i);
            }
            fileName += ".hex"; // the output suffix

            hex.generateFile(fileName);
            notifyInfo("Compile was sucessfull. Output: " + fileName);
            programStart = hex.getProgramStart();
            
            // try to access the memory
            MemoryContext memory = ContextPool.getInstance().getMemoryContext(pluginID, MemoryContext.class);
            if (memory != null) {
                if (hex.loadIntoMemory(memory)) {
                    notifyInfo("Compiled file was loaded into operating memory.");
                } else {
                    notifyError("Compiled file couldn't be loaded into operating"
                            + "memory due to an error.");
                }
            }
            return true;
        } catch (Exception e) {
            LOGGER.error("Unexpected error", e);
            notifyError("Unexpected error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public LexicalAnalyzer getLexer(Reader in) {
        return new LexerBD(in);
    }

    @Override
    public void showSettings() {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean isShowSettingsSupported() {
        return false;
    }
    
    @Override
    public SourceFileExtension[] getSourceSuffixList() {
        return suffixes;
    }

    private static void printHelp() {
        System.out.println("Syntax: brainc-brainduck [-o outputFile] inputFile\nOptions:");
        System.out.println("\t--output, -o\tfile: name of the output file");
        System.out.println("\t--version, -v\t: print version");
        System.out.println("\t--help, -h\t: this help");
    }
    
    public static void main(String[] args) {
        System.out.println(CompilerImpl.class.getAnnotation(PluginType.class).title());
      
        String inputFile;
        String outputFile = null;
      
        int i;
        for (i = 0; i < args.length; i++) {
            String arg = args[i].toLowerCase();
            if ((arg.equals("--output") || arg.equals("-o")) && ((i+1) < args.length)) {
                outputFile = args[++i];
            } else if (arg.equals("--help") || arg.equals("-h")) {
                printHelp();
                return;
            } else if (arg.equals("--version") || arg.equals("-v")) {
                System.out.println(new CompilerImpl(0L).getVersion());
                return;
            } else {
              break;
            }
        }
        if (i >= args.length) {
            System.out.println("Error: expected input file name");
            return;
        }
        inputFile = args[i];
        if (outputFile == null) {
          outputFile = inputFile.substring(0, inputFile.lastIndexOf('.')) + ".hex";
        }
        
        CompilerImpl compiler = new CompilerImpl(0L);
        compiler.addCompilerListener(new CompilerListener() {

            @Override
            public void onStart() {
            }

            @Override
            public void onMessage(Message message) {
                System.out.println(message.getFormattedMessage());
            }

            @Override
            public void onFinish(int errorCode) {
            }
        });
        
        
        try {
          HEXFileManager hex = compiler.compile(new FileReader(inputFile));
          System.out.println("Saving output to: " + outputFile);
          hex.generateFile(outputFile);
        } catch (Exception e) {
          LOGGER.error("Unexpected error", e);
          System.out.println(e.getMessage());
        }
    }
}
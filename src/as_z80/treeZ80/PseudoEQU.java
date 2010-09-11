/*
 * PseudoEQU.java
 *
 * Created on Sobota, 2007, september 29, 10:37
 *
 * KEEP IT SIMPLE, STUPID
 * some things just: YOU AREN'T GONNA NEED IT
 *
 * Copyright (C) 2007-2010 Peter Jakubčo <pjakubco at gmail.com>
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
package as_z80.treeZ80;

import as_z80.impl.Namespace;
import as_z80.treeZ80Abstract.Expression;
import as_z80.treeZ80Abstract.Pseudo;
import emuLib8.plugins.compiler.HEXFileHandler;

/**
 *
 * @author vbmacher
 */
public class PseudoEQU extends Pseudo {

    private Expression expr;
    private String mnemo;

    /** Creates a new instance of PseudoEQU */
    public PseudoEQU(String id, Expression expr, int line, int column) {
        super(line, column);
        this.mnemo = id;
        this.expr = expr;
    }

    public String getName() {
        return mnemo;
    }

    public int getValue() {
        return expr.getValue();
    }

    /// compile time ///
    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void pass1() {
    }

    @Override
    public int pass2(Namespace env, int addr_start) throws Exception {
        if (env.addEquDef(this) == false) {
            throw new Exception("[" + line + "," + column
                    + "] Error: constant already defined: " + mnemo);
        }
        expr.eval(env, addr_start);
        return addr_start;
    }

    @Override
    public void pass4(HEXFileHandler hex) {
    }
}

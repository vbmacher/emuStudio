/*
 * Row.java
 *
 * Created on Streda, 2008, august 13, 11:25
 *
 * Copyright (C) 2008-2012 Peter Jakubčo
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
package net.sf.emustudio.zilogZ80.assembler.tree;

import emulib.runtime.HEXFileManager;
import java.util.ArrayList;
import net.sf.emustudio.zilogZ80.assembler.impl.Namespace;
import net.sf.emustudio.zilogZ80.assembler.impl.NeedMorePassException;
import net.sf.emustudio.zilogZ80.assembler.treeAbstract.Statement;

public class Row {

    protected Label label;
    protected Statement statement;
    private int current_address; // its computed in pass2

    public Row(Label label, Statement statement) {
        this.label = label;
        this.statement = statement;
    }

    public Row(Statement statement) {
        this.label = null;
        this.statement = statement;
    }

    public boolean getIncludeLoops(String filename) {
        if (statement == null) {
            return false;
        }
        if (statement instanceof PseudoINCLUDE) {
            PseudoINCLUDE i = (PseudoINCLUDE) statement;
            if (i.isEqualName(filename)) {
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        if (statement != null) {
            return statement.getSize();
        } else {
            return 0;
        }
    }

    // do pass1 for all elements
    public void pass1()
            throws Exception {
        if (statement != null) {
            statement.pass1();
        }
    }

    public void pass1(ArrayList<String> inclfiles,
            Namespace parent) throws Exception {
        ((PseudoINCLUDE) statement).pass1(inclfiles, parent);
    }

    public int pass2(Namespace prev_env, int addr_start) throws Exception {
        this.current_address = addr_start;
        if (label != null) {
            label.setAddress(new Integer(addr_start));
        }
        // pass2 pre definiciu makra nemozem volat. ide totiz o samotnu expanziu
        // makra. preto pass2 mozem volat az pri samotnom volani makra (pass2 triedy
        // MacroCallPseudo)
        if (statement != null) {
            if ((statement instanceof PseudoMACRO) == false) {
                addr_start = statement.pass2(prev_env, addr_start);
            }
        }
        return addr_start;
    }

    public int getCurrentAddress() {
        return this.current_address;
    }

    public boolean pass3(Namespace env) throws Exception {
        try {
            if (statement != null) {
                statement.pass2(env, this.current_address);
            }
        } catch (NeedMorePassException e) {
            return false;
        }
        return true;
    }

    // code generation
    public void pass4(HEXFileManager hex) throws Exception {
        if (statement != null) {
            if ((statement instanceof PseudoMACRO) == false) {
                statement.pass4(hex);
            }
        }
    }
}
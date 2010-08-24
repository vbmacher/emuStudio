/*
 * DWDataNode.java
 *
 * Created on Sobota, 2007, september 29, 9:30
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
package as_8080.tree8080;

import as_8080.impl.CompileEnv;
import as_8080.tree8080Abstract.DataValueNode;
import as_8080.tree8080Abstract.ExprNode;
import plugins.compiler.HEXFileHandler;

/**
 *
 * @author vbmacher
 */
public class DWDataNode extends DataValueNode {

    private ExprNode expression = null;

    /** Creates a new instance of DWDataNode */
    public DWDataNode(ExprNode expr, int line, int column) {
        super(line, column);
        this.expression = expr;
    }

    /// compile time ///
    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public int pass2(CompileEnv env, int addr_start) throws Exception {
        expression.eval(env, addr_start);
        return addr_start + 2;
    }

    @Override
    public void pass4(HEXFileHandler hex) throws Exception {
        hex.putCode(expression.getEncValue(false));
    }
}

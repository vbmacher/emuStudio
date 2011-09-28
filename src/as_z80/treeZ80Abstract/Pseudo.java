/*
 * Pseudo.java
 *
 * Created on Štvrtok, 2008, august 14, 9:03
 *
 * KEEP IT SIMPLE, STUPID
 * some things just: YOU AREN'T GONNA NEED IT
 *
 * Copyright (C) 2008-2011 Peter Jakubčo <pjakubco at gmail.com>
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

package as_z80.treeZ80Abstract;


/**
 *
 * @author vbmacher
 */
/*
 * pseudocodes var,equ
 * are treated as local if theyre set in a macro. (see Label class)
 */
public abstract class Pseudo extends Statement {

    public Pseudo(int line, int column) {
        super(line,column);
    }
    
    @Override
    public boolean isPseudo() { return true; }
}

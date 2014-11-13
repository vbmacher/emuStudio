/*
 * BrainStatusPanel.java
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
package net.sf.emustudio.brainduck.cpu.gui;

import emulib.plugins.cpu.CPU.CPUListener;
import emulib.plugins.cpu.CPU.RunState;
import emulib.plugins.memory.MemoryContext;
import net.sf.emustudio.brainduck.cpu.impl.EmulatorImpl;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class BrainStatusPanel extends JPanel {
    private final JTextField txtP = new JTextField("0000");
    private final JTextField txtIP = new JTextField("0000");
    private final JLabel lblStatus = new JLabel("breakpoint");
    private final JTextField txtMemP = new JTextField("00");
    private final JLabel lblTime = new JLabel("0.00 s");
    private volatile long nanoStartTime;
    private volatile long nanoTime;

    public BrainStatusPanel(final EmulatorImpl cpu, final MemoryContext mem) {
        initComponents();

        cpu.addCPUListener(new CPUListener() {

            @Override
            public void runStateChanged(RunState state) {
                switch (state) {
                    case STATE_RUNNING:
                        lblStatus.setText("running");
                        lblTime.setText("counting...");
                        nanoStartTime = System.nanoTime();
                        break;
                    case STATE_STOPPED_NORMAL:
                        lblStatus.setText("stopped (normal)");
                        break;
                    case STATE_STOPPED_BREAK:
                        lblStatus.setText("breakpoint");
                        break;
                    case STATE_STOPPED_ADDR_FALLOUT:
                        lblStatus.setText("stopped (address fallout)");
                        break;
                    case STATE_STOPPED_BAD_INSTR:
                        lblStatus.setText("stopped (instruction fallout)");
                        break;
                }
                long tmpNanoTime = 0;
                if (state != RunState.STATE_RUNNING && nanoStartTime != 0) {
                    tmpNanoTime = System.nanoTime() - nanoStartTime;
                    nanoTime = tmpNanoTime;
                    nanoStartTime = 0;
                }
                lblTime.setText(String.format("%.2f s", (double) tmpNanoTime / 1000000000.0));
            }

            @Override
            public void internalStateChanged() {
                txtP.setText(String.format("%04X", cpu.getP()));
                txtIP.setText(String.format("%04X", cpu.getIP()));
                try {
                    short value = (Short) mem.read(cpu.getP());
                    txtMemP.setText(String.format("%02X", value));
                } catch (ArrayIndexOutOfBoundsException e) {
                    txtMemP.setText("[unreachable]");
                }
            }
        });
    }

    private void initComponents() {
        JLabel lblP = new JLabel("P");
        JLabel lblIP = new JLabel("IP");
        JLabel lblMemP = new JLabel("*P");
        JLabel lblTimeT = new JLabel("Execution time: ");

        lblStatus.setFont(lblStatus.getFont().deriveFont(Font.BOLD));
        lblTime.setFont(lblStatus.getFont().deriveFont(Font.BOLD));
        txtP.setEditable(false);
        txtP.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.lightGray));
        txtIP.setEditable(false);
        txtIP.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.lightGray));
        txtMemP.setEditable(false);
        txtMemP.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.lightGray));

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                layout
                                        .createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(
                                                layout.createSequentialGroup()
                                                        .addGroup(
                                                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(lblP)
                                                                        .addComponent(lblIP)
                                                                        .addComponent(lblMemP)
                                                                        .addComponent(lblTimeT))
                                                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                        .addGroup(
                                                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                        .addComponent(
                                                                                txtP,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE)
                                                                        .addComponent(
                                                                                txtIP,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE)
                                                                        .addComponent(
                                                                                txtMemP,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE)
                                                                        .addComponent(
                                                                                lblTime,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE)))
                                        .addComponent(
                                                lblStatus,
                                                GroupLayout.DEFAULT_SIZE,
                                                150,
                                                Short.MAX_VALUE))
                        .addContainerGap(20, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblP)
                                        .addComponent(txtP))
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblIP)
                                        .addComponent(txtIP))
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblMemP)
                                        .addComponent(txtMemP))
                        .addGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lblTimeT)
                                        .addComponent(lblTime))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblStatus).addContainerGap()
        );
    }

}

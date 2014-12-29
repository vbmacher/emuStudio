/*
 * StatusPanel.java
 *
 * Created on Pondelok, 2007, december 31, 10:59
 *
 * Copyright (C) 2007-2012 Peter Jakubčo
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
package net.sf.emustudio.intel8080.gui;

import emulib.plugins.cpu.CPU;
import emulib.plugins.cpu.CPU.RunState;
import net.sf.emustudio.intel8080.ExtendedContext;
import net.sf.emustudio.intel8080.FrequencyChangedListener;
import net.sf.emustudio.intel8080.impl.CpuImpl;
import net.sf.emustudio.intel8080.impl.EmulatorEngine;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.AbstractTableModel;
import java.awt.*;

import static emulib.runtime.RadixUtils.getByteHexString;
import static emulib.runtime.RadixUtils.getWordHexString;

@SuppressWarnings("serial")
public class StatusPanel extends JPanel {
    private final CpuImpl cpu;
    private final EmulatorEngine engine;
    private final ExtendedContext context;
    private final AbstractTableModel flagModel;
    private volatile RunState runState = RunState.STATE_STOPPED_NORMAL;

    public StatusPanel(CpuImpl cpu, ExtendedContext context) {
        this.cpu = cpu;
        this.context = context;
        this.engine = cpu.getEngine();
        flagModel = new FlagsModel(engine);

        initComponents();
        tblFlags.setModel(flagModel);

        setupListeners();
    }

    private void setupListeners() {
        cpu.addCPUListener(new CPU.CPUListener() {

            @Override
            public void runStateChanged(RunState state) {
                runState = state;
            }

            @Override
            public void internalStateChanged() {
                updateGUI();
            }

        });
        cpu.addFrequencyChangedListener(new FrequencyChangedListener() {
            @Override
            public void frequencyChanged(float newFrequency) {
                lblFrequency.setText(String.format("%.2f kHz", newFrequency));
            }
        });
        spnFrequency.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                int i = (Integer)spnFrequency.getModel().getValue();
                try {
                    StatusPanel.this.context.setCPUFrequency(i);
                } catch (IndexOutOfBoundsException ex) {
                    spnFrequency.getModel().setValue(context.getCPUFrequency());
                }
            }
        });
        spnTestPeriode.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                int i = (Integer) spnTestPeriode.getModel().getValue();
                try {
                    StatusPanel.this.cpu.setSliceTime(i);
                } catch (IndexOutOfBoundsException ex) {
                    spnTestPeriode.getModel().setValue(cpu.getSliceTime());
                }
            }
        });
    }

    public void updateGUI() {
        txtRegA.setText(getByteHexString(engine.regs[EmulatorEngine.REG_A]));
        txtRegB.setText(getByteHexString(engine.regs[EmulatorEngine.REG_B]));
        txtRegC.setText(getByteHexString(engine.regs[EmulatorEngine.REG_C]));
        txtRegBC.setText(getWordHexString(engine.regs[EmulatorEngine.REG_B], engine.regs[EmulatorEngine.REG_C]));
        txtRegD.setText(getByteHexString(engine.regs[EmulatorEngine.REG_D]));
        txtRegE.setText(getByteHexString(engine.regs[EmulatorEngine.REG_E]));
        txtRegDE.setText(getWordHexString(engine.regs[EmulatorEngine.REG_D], engine.regs[EmulatorEngine.REG_E]));
        txtRegH.setText(getByteHexString(engine.regs[EmulatorEngine.REG_H]));
        txtRegL.setText(getByteHexString(engine.regs[EmulatorEngine.REG_L]));
        txtRegHL.setText(getWordHexString(engine.regs[EmulatorEngine.REG_H], engine.regs[EmulatorEngine.REG_L]));
        txtRegSP.setText(getWordHexString(engine.SP));
        txtRegPC.setText(getWordHexString(engine.PC));

        txtFlags.setText(getByteHexString(engine.Flags));
        flagModel.fireTableDataChanged();

        lblRun.setText(runState.toString());
        if (runState == RunState.STATE_RUNNING) {
            spnFrequency.setEnabled(false);
            spnTestPeriode.setEnabled(false);
        } else {
            spnFrequency.setEnabled(true);
            spnTestPeriode.setEnabled(true);
        }
    }

    private void initComponents() {
        JPanel paneRegisters = new JPanel();
        JLabel lblRegB = new JLabel("B");
        txtRegB = new JTextField("00");
        JLabel lblRegC = new JLabel("C");
        txtRegC = new JTextField("00");
        JLabel lblRegBC = new JLabel("BC");
        txtRegBC = new JTextField("0000");
        JLabel lblRegD = new JLabel("D");
        txtRegD = new JTextField("00");
        JLabel lblRegE = new JLabel("E");
        txtRegE = new JTextField("00");
        JLabel lblRegDE = new JLabel("DE");
        txtRegDE = new JTextField("0000");
        JLabel lblRegH = new JLabel("H");
        txtRegH = new JTextField("00");
        JLabel lblRegL = new JLabel("L");
        txtRegL = new JTextField("00");
        JLabel lblRegHL = new JLabel("HL");
        txtRegHL = new JTextField("0000");
        JLabel lblRegA = new JLabel("A");
        txtRegA = new JTextField("00");
        JLabel lblRegF = new JLabel("F");
        txtFlags = new JTextField("00");
        JLabel lblRegPC = new JLabel("PC");
        txtRegPC = new JTextField("0000");
        JLabel lblRegSP = new JLabel("SP");
        txtRegSP = new JTextField("0000");
        JPanel panelRun = new JPanel();
        lblRun = new JLabel("Stopped");
        JLabel lblCPUFreq = new JLabel("CPU frequency:");
        spnFrequency = new JSpinner();
        JLabel lblKHZ = new JLabel("kHz");
        JLabel lblRuntimeFreq = new JLabel("Runtime frequency:");
        lblFrequency = new JLabel("0,0 kHz");
        JLabel lblTestPeriode = new JLabel("Test periode:");
        spnTestPeriode = new JSpinner();
        JLabel lblMS = new JLabel("ms");
        JLabel lblFlags = new JLabel("Flags (F): ");
        tblFlags = new JTable();

        setBorder(null);
        paneRegisters.setBorder(null); // NOI18N


        lblRegB.setFont(lblRegB.getFont().deriveFont(lblRegB.getFont().getStyle() | java.awt.Font.BOLD));
        txtRegB.setEditable(false);
        txtRegB.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.lightGray));

        lblRegC.setFont(lblRegC.getFont().deriveFont(lblRegC.getFont().getStyle() | java.awt.Font.BOLD));
        txtRegC.setEditable(false);
        txtRegC.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.lightGray));

        lblRegBC.setFont(lblRegBC.getFont().deriveFont(lblRegBC.getFont().getStyle() | java.awt.Font.BOLD));
        txtRegBC.setEditable(false);
        txtRegBC.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.lightGray));

        lblRegD.setFont(lblRegD.getFont().deriveFont(lblRegD.getFont().getStyle() | java.awt.Font.BOLD));
        txtRegD.setEditable(false);
        txtRegD.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.lightGray));

        lblRegE.setFont(lblRegE.getFont().deriveFont(lblRegE.getFont().getStyle() | java.awt.Font.BOLD));
        txtRegE.setEditable(false);
        txtRegE.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.lightGray));

        lblRegDE.setFont(lblRegDE.getFont().deriveFont(lblRegDE.getFont().getStyle() | java.awt.Font.BOLD));
        txtRegDE.setEditable(false);
        txtRegDE.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.lightGray));

        lblRegH.setFont(lblRegH.getFont().deriveFont(lblRegH.getFont().getStyle() | java.awt.Font.BOLD));
        txtRegH.setEditable(false);
        txtRegH.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.lightGray));

        lblRegL.setFont(lblRegL.getFont().deriveFont(lblRegL.getFont().getStyle() | java.awt.Font.BOLD));
        txtRegL.setEditable(false);
        txtRegL.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.lightGray));

        lblRegHL.setFont(lblRegHL.getFont().deriveFont(lblRegHL.getFont().getStyle() | java.awt.Font.BOLD));
        txtRegHL.setEditable(false);
        txtRegHL.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.lightGray));

        lblRegA.setFont(lblRegA.getFont().deriveFont(lblRegA.getFont().getStyle() | java.awt.Font.BOLD));
        txtRegA.setEditable(false);
        txtRegA.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.lightGray));

        lblRegF.setFont(lblRegF.getFont().deriveFont(lblRegF.getFont().getStyle() | java.awt.Font.BOLD));
        txtFlags.setEditable(false);
        txtFlags.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.lightGray));

        lblRegPC.setFont(lblRegPC.getFont().deriveFont(lblRegPC.getFont().getStyle() | java.awt.Font.BOLD));
        txtRegPC.setEditable(false);
        txtRegPC.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.lightGray));

        lblRegSP.setFont(lblRegSP.getFont().deriveFont(lblRegSP.getFont().getStyle() | java.awt.Font.BOLD));
        txtRegSP.setEditable(false);
        txtRegSP.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.lightGray));

        tblFlags.setAutoCreateRowSorter(true);
        tblFlags.setBackground(Color.white);
        tblFlags.setBorder(null);
        tblFlags.setRowSelectionAllowed(false);

        GroupLayout paneRegistersLayout = new GroupLayout(paneRegisters);
        paneRegisters.setLayout(paneRegistersLayout);

        paneRegistersLayout.setHorizontalGroup(
                paneRegistersLayout.createSequentialGroup().addContainerGap().addGroup(paneRegistersLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(paneRegistersLayout.createSequentialGroup().addGroup(paneRegistersLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(lblRegB).addComponent(lblRegD).addComponent(lblRegH).addComponent(lblRegA)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(paneRegistersLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(txtRegB).addComponent(txtRegD).addComponent(txtRegH).addComponent(txtRegA)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(paneRegistersLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(lblRegC).addComponent(lblRegE).addComponent(lblRegL).addComponent(lblRegF)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(paneRegistersLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(txtRegC).addComponent(txtRegE).addComponent(txtRegL).addComponent(txtFlags)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(paneRegistersLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(lblRegBC).addComponent(lblRegDE).addComponent(lblRegHL).addComponent(lblRegPC).addComponent(lblRegSP)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(paneRegistersLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(txtRegBC).addComponent(txtRegDE).addComponent(txtRegHL).addComponent(txtRegPC).addComponent(txtRegSP))).addComponent(lblFlags).addComponent(tblFlags)).addContainerGap());
        paneRegistersLayout.setVerticalGroup(
                paneRegistersLayout.createSequentialGroup().addContainerGap().addGroup(paneRegistersLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblRegB).addComponent(txtRegB).addComponent(lblRegC).addComponent(txtRegC).addComponent(lblRegBC).addComponent(txtRegBC)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(paneRegistersLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblRegD).addComponent(txtRegD).addComponent(lblRegE).addComponent(txtRegE).addComponent(lblRegDE).addComponent(txtRegDE)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(paneRegistersLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblRegH).addComponent(txtRegH).addComponent(lblRegL).addComponent(txtRegL).addComponent(lblRegHL).addComponent(txtRegHL)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(paneRegistersLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblRegA).addComponent(txtRegA).addComponent(lblRegF).addComponent(txtFlags).addComponent(lblRegPC).addComponent(txtRegPC)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(paneRegistersLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(lblRegSP).addComponent(txtRegSP)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(lblFlags).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(tblFlags));

        panelRun.setBorder(BorderFactory.createTitledBorder(
                new javax.swing.border.LineBorder(new java.awt.Color(153, 153, 153), 1, true),
                "Run control", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("DejaVu Sans", Font.BOLD, 14),
                new java.awt.Color(102, 102, 102))); // NOI18N

        lblRun.setFont(lblRun.getFont().deriveFont(lblRun.getFont().getStyle() | java.awt.Font.BOLD));
        lblRun.setForeground(new java.awt.Color(0, 102, 0));

        SpinnerNumberModel spFrequencyModel = new SpinnerNumberModel();
        spFrequencyModel.setValue(2000);
        spFrequencyModel.setStepSize(100);
        spnFrequency.setModel(spFrequencyModel);
        lblKHZ.setFont(lblKHZ.getFont().deriveFont(lblKHZ.getFont().getStyle() | java.awt.Font.BOLD));
        lblFrequency.setFont(lblFrequency.getFont().deriveFont(lblFrequency.getFont().getStyle() | java.awt.Font.BOLD));

        SpinnerNumberModel spTestPeriodeModel = new SpinnerNumberModel();
        spTestPeriodeModel.setValue(50);
        spTestPeriodeModel.setStepSize(50);
        spnTestPeriode.setModel(spTestPeriodeModel);
        lblMS.setFont(lblMS.getFont().deriveFont(lblMS.getFont().getStyle() | java.awt.Font.BOLD));

        GroupLayout panelRunLayout = new GroupLayout(panelRun);
        panelRun.setLayout(panelRunLayout);
        panelRunLayout.setHorizontalGroup(
                panelRunLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(panelRunLayout.createSequentialGroup().addContainerGap().addComponent(lblRun).addContainerGap()).addGroup(panelRunLayout.createSequentialGroup().addContainerGap().addGroup(panelRunLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(lblCPUFreq).addComponent(lblTestPeriode)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(panelRunLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(spnFrequency).addComponent(spnTestPeriode)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(panelRunLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(lblKHZ).addComponent(lblMS)).addContainerGap()).addGroup(panelRunLayout.createSequentialGroup().addContainerGap().addComponent(lblRuntimeFreq).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(lblFrequency).addContainerGap()));
        panelRunLayout.setVerticalGroup(
                panelRunLayout.createSequentialGroup().addContainerGap().addComponent(lblRun).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(panelRunLayout.createParallelGroup(GroupLayout.Alignment.BASELINE, false).addComponent(lblCPUFreq).addComponent(spnFrequency, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE).addComponent(lblKHZ)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(panelRunLayout.createParallelGroup(GroupLayout.Alignment.BASELINE, false).addComponent(lblTestPeriode).addComponent(spnTestPeriode, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE).addComponent(lblMS)).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addGroup(panelRunLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(lblRuntimeFreq).addComponent(lblFrequency)).addContainerGap());

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(paneRegisters, 10, 290, Short.MAX_VALUE).addComponent(panelRun, 10, 290, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createSequentialGroup().addComponent(paneRegisters, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(panelRun, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE).addContainerGap());
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    JLabel lblFrequency;
    JLabel lblRun;
    JSpinner spnFrequency;
    JSpinner spnTestPeriode;
    JTable tblFlags;
    JTextField txtFlags;
    JTextField txtRegA;
    JTextField txtRegB;
    JTextField txtRegBC;
    JTextField txtRegC;
    JTextField txtRegD;
    JTextField txtRegDE;
    JTextField txtRegE;
    JTextField txtRegH;
    JTextField txtRegHL;
    JTextField txtRegL;
    JTextField txtRegPC;
    JTextField txtRegSP;
    // End of variables declaration//GEN-END:variables
}

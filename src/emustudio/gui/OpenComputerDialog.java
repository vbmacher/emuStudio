/*
 * OpenComputerDialog.java
 *
 * Created on Streda, 2007, august 8, 8:45
 *
 *  Copyright (C) 2007-2010 vbmacher
 * 
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package emustudio.gui;

import emustudio.architecture.ArchLoader;
import emustudio.architecture.drawing.PreviewPanel;
import emustudio.architecture.drawing.Schema;
import javax.swing.AbstractListModel;
import runtime.StaticDialogs;

/**
 *
 * @author vbmacher
 */
public class OpenComputerDialog extends javax.swing.JDialog {
    private String archName;
    private boolean OOK = false;
    private ArchListModel amodel;
    private PreviewPanel preview;

    public OpenComputerDialog() {
        super();
        initComponents();
        amodel = new ArchListModel();
        lstConfig.setModel(amodel);
        this.setModal(true);
        this.setLocationRelativeTo(null);

        preview = new PreviewPanel();
        scrollPreview.setViewportView(preview);
    }

    /** Creates new form OpenComputerDialog */
    public OpenComputerDialog(java.awt.Dialog parent, boolean modal) {
        super(parent, modal);
        initComponents();
        amodel = new ArchListModel();
        lstConfig.setModel(amodel);
        this.setTitle("Browse for arhitecture names");
        btnEdit.setEnabled(false);
        btnEdit.setVisible(false);
        btnDelete.setEnabled(false);
        btnDelete.setVisible(false);
        btnAdd.setEnabled(false);
        btnAdd.setVisible(false);
        this.setLocationRelativeTo(parent);

        preview = new PreviewPanel();
        scrollPreview.setViewportView(preview);
    }

 // existing configurations list model
    private class ArchListModel extends AbstractListModel {

        private String[] allModels;

        public ArchListModel() {
            allModels = ArchLoader.getAllNames(ArchLoader.configsDir, ".conf");
        }

        @Override
        public Object getElementAt(int index) {
            return allModels[index];
        }

        @Override
        public int getSize() {
            if (allModels != null) {
                return allModels.length;
            } else {
                return 0;
            }
        }

        public void update() {
            allModels = ArchLoader.getAllNames(ArchLoader.configsDir,
                    ".conf");
            this.fireContentsChanged(this, -1, -1);
        }
    }

    public boolean getOK() {
        return OOK;
    }

    public String getArchName() {
        return archName;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        splitConfig = new javax.swing.JSplitPane();
        scrollPreview = new javax.swing.JScrollPane();
        panelConfig = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstConfig = new javax.swing.JList();
        jToolBar2 = new javax.swing.JToolBar();
        btnAdd = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnOpen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Open virtual computer");

        splitConfig.setDividerLocation(300);
        splitConfig.setMinimumSize(new java.awt.Dimension(50, 102));
        splitConfig.setPreferredSize(new java.awt.Dimension(300, 299));

        scrollPreview.setPreferredSize(new java.awt.Dimension(20, 100));
        splitConfig.setRightComponent(scrollPreview);

        panelConfig.setPreferredSize(new java.awt.Dimension(200, 297));

        lstConfig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstConfigMouseClicked(evt);
            }
        });
        lstConfig.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lstConfigValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lstConfig);

        jToolBar2.setFloatable(false);
        jToolBar2.setRollover(true);

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emustudio/resources/list-add.png"))); // NOI18N
        btnAdd.setToolTipText("Create new computer...");
        btnAdd.setFocusable(false);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });
        jToolBar2.add(btnAdd);

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emustudio/resources/list-remove.png"))); // NOI18N
        btnDelete.setToolTipText("Remove computer");
        btnDelete.setFocusable(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });
        jToolBar2.add(btnDelete);

        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/emustudio/resources/computer.png"))); // NOI18N
        btnEdit.setToolTipText("Edit existing computer...");
        btnEdit.setFocusable(false);
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });
        jToolBar2.add(btnEdit);

        javax.swing.GroupLayout panelConfigLayout = new javax.swing.GroupLayout(panelConfig);
        panelConfig.setLayout(panelConfigLayout);
        panelConfigLayout.setHorizontalGroup(
            panelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
        );
        panelConfigLayout.setVerticalGroup(
            panelConfigLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConfigLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        splitConfig.setLeftComponent(panelConfig);

        jLabel1.setText("Please select a virtual configuration that will be emulated:");

        btnOpen.setText("Open");
        btnOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOpenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(splitConfig, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addComponent(btnOpen, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(splitConfig, javax.swing.GroupLayout.DEFAULT_SIZE, 244, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnOpen)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lstConfigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstConfigMouseClicked
        if (evt.getClickCount() == 2) {
            btnOpenActionPerformed(null);
        }
    }//GEN-LAST:event_lstConfigMouseClicked

    private void btnOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenActionPerformed
        if (lstConfig.getSelectedIndex() == -1) {
            StaticDialogs.showErrorMessage("Virtual architecture has to be selected!");
            return;
        }
        archName = (String) lstConfig.getSelectedValue();
        OOK = true;
        dispose();
    }//GEN-LAST:event_btnOpenActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        if (lstConfig.getSelectedIndex() == -1) {
            StaticDialogs.showErrorMessage("Virtual architecture has to be selected!");
            return;
        }
        archName = (String) lstConfig.getSelectedValue();
        Schema s = ArchLoader.loadSchema(archName);
        if (s == null) {
            return;
        }
        AddEditArchDialog d = new AddEditArchDialog(this, true, s);
        d.setVisible(true);
        if (d.getOK()) {
            ArchLoader.saveSchema(d.getSchema());
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (lstConfig.getSelectedIndex() == -1) {
            StaticDialogs.showErrorMessage("Virtual architecture has to be selected!");
            return;
        }
        int r = StaticDialogs.confirmMessage("Do you really want to delete"
                + " selected architecture?", "Delete architecture");
        archName = (String) lstConfig.getSelectedValue();
        if ((r == StaticDialogs.YES_OPTION) && ArchLoader.deleteConfig(archName)) {
            amodel.update();
            archName = "";
            lstConfig.setSelectedIndex(-1);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        AddEditArchDialog di = new AddEditArchDialog(null, true);
        di.setVisible(true);
        if (di.getOK()) {
            ArchLoader.saveSchema(di.getSchema());
            amodel.update();
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void lstConfigValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lstConfigValueChanged
        if (lstConfig.getSelectedIndex() == -1)
            return;

        archName = (String) lstConfig.getSelectedValue();
        Schema s = ArchLoader.loadSchema(archName);
        if (s == null)
            return;

        preview.setSchema(s);
        preview.repaint();
    }//GEN-LAST:event_lstConfigValueChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnOpen;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JList lstConfig;
    private javax.swing.JPanel panelConfig;
    private javax.swing.JScrollPane scrollPreview;
    private javax.swing.JSplitPane splitConfig;
    // End of variables declaration//GEN-END:variables

}

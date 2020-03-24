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

/*
 * KISS, YAGNI, DRY
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

package net.emustudio.application.gui.dialogs;

import javax.swing.*;
import java.awt.*;
import java.util.MissingResourceException;
import java.util.Optional;
import java.util.ResourceBundle;

public class AboutDialog extends JDialog {

    public AboutDialog(JFrame parent) {
        super(parent, true);
        initComponents();
        setLocationRelativeTo(parent);
    }

    private void initComponents() {
        JPanel panelLogo = new JPanel();
        JLabel lblLogo = new JLabel();
        JPanel panelInfo = new JPanel();
        JLabel lblName = new JLabel();
        JLabel lblCopyright = new JLabel();
        JLabel lblVersion = new JLabel();
        JLabel jLabel4 = new JLabel();
        JLabel jLabel1 = new JLabel();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("About emuStudio");

        lblLogo.setBackground(java.awt.Color.white);
        lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
        lblLogo.setIcon(new ImageIcon(getClass().getResource("/net/emustudio/application/gui/dialogs/logo.png"))); // NOI18N
        lblLogo.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblLogo.setDoubleBuffered(true);
        lblLogo.setFocusable(false);
        lblLogo.setHorizontalTextPosition(SwingConstants.CENTER);
        lblLogo.setIconTextGap(0);

        GroupLayout panelLogoLayout = new GroupLayout(panelLogo);
        panelLogo.setLayout(panelLogoLayout);
        panelLogoLayout.setHorizontalGroup(
            panelLogoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelLogoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblLogo, GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
        );
        panelLogoLayout.setVerticalGroup(
            panelLogoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelLogoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblLogo, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addContainerGap())
        );

        lblName.setFont(new java.awt.Font("Tahoma", Font.BOLD, 18)); // NOI18N
        lblName.setText("emuStudio");

        lblCopyright.setText(getCopyright());

        lblVersion.setFont(lblVersion.getFont().deriveFont(lblVersion.getFont().getStyle() | java.awt.Font.BOLD));
        lblVersion.setText(getVersion());

        jLabel4.setText("Version");
        jLabel1.setText(
            "<html>This program comes with ABSOLUTELY NO WARRANTY. This is free software, and you are welcome to redistribute it " +
                "under certain conditions; for details see https://www.gnu.org/licenses/gpl-3.0.html. " +
                "For more information about emuStudio, see https://www.emustudio.net/.");

        GroupLayout panelInfoLayout = new GroupLayout(panelInfo);
        panelInfo.setLayout(panelInfoLayout);
        panelInfoLayout.setHorizontalGroup(
            panelInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelInfoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(panelInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(lblName)
                        .addGroup(panelInfoLayout.createSequentialGroup()
                            .addComponent(jLabel4)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(lblVersion))
                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 340, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblCopyright))
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelInfoLayout.setVerticalGroup(
            panelInfoLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(panelInfoLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lblName)
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(lblCopyright)
                    .addGap(18, 18, 18)
                    .addGroup(panelInfoLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                        .addComponent(lblVersion)
                        .addComponent(jLabel4))
                    .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel1, GroupLayout.DEFAULT_SIZE, 81, Short.MAX_VALUE)
                    .addContainerGap())
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(panelLogo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(panelInfo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panelInfo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelLogo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap())
        );

        pack();
    }

    private String getVersion() {
        return getResourceBundle()
            .map(b -> b.getString("version"))
            .orElse(getClass().getPackage().getImplementationVersion());
    }

    private String getCopyright() {
        return getResourceBundle().map(b -> b.getString("copyright")).orElse("(unknown)");
    }


    private Optional<ResourceBundle> getResourceBundle() {
        try {
            return Optional.of(ResourceBundle.getBundle("net.emustudio.application.version"));
        } catch (MissingResourceException e) {
            return Optional.empty();
        }
    }
}

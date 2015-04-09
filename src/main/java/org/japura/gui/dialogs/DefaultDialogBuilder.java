package org.japura.gui.dialogs;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.LinkedHashMap;

/**
 * <P>
 * Copyright (C) 2015 Carlos Eduardo Leite de Andrade
 * <P>
 * This library is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * <P>
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * <P>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <A
 * HREF="www.gnu.org/licenses/">www.gnu.org/licenses/</A>
 * <P>
 * For more information, contact: <A HREF="www.japura.org">www.japura.org</A>
 * <P>
 *
 * @author Carlos Eduardo Leite de Andrade
 */
public class DefaultDialogBuilder extends DialogBuilder {

  @Override
  public JDialog buildDialog(final CustomDialog customDialog) {
    final JDialog dialog = new JDialog();

    ActionListener escListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        dialog.dispose();
      }
    };

    dialog.getRootPane().registerKeyboardAction(escListener,
      KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
      JComponent.WHEN_IN_FOCUSED_WINDOW);

    dialog.setTitle(customDialog.getTitle());

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout(0, 0));
    panel.setOpaque(false);

    if (customDialog.getDialogIcon() != null) {
      BufferedImage image =
        new BufferedImage(customDialog.getDialogIcon().getIconWidth(),
          customDialog.getDialogIcon().getIconHeight(),
          BufferedImage.TYPE_INT_ARGB);
      customDialog.getDialogIcon().paintIcon(null, image.getGraphics(), 0, 0);
      dialog.setIconImage(image);
    }

    if (customDialog.getMessageIcon() != null) {
      JPanel iconPanel = new JPanel();
      iconPanel.setOpaque(false);
      iconPanel.setLayout(new MigLayout("ins 5 5 5 0"));
      iconPanel.add(new JLabel(customDialog.getMessageIcon()));
      panel.add(iconPanel, BorderLayout.WEST);
    }

    LinkedHashMap<Component, Integer> contents = customDialog.getContents();

    JScrollPane sp = new JScrollPane(new ContentPanel(contents));
    sp.getViewport().setOpaque(false);
    sp.setOpaque(false);
    sp.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    panel.add(sp);

    ButtonsPanel buttonsPanel = new ButtonsPanel();
    for (int i = 0; i < customDialog.getButtonsCount(); i++) {
      JButton button = customDialog.getButton(i);
      buttonsPanel.add(button);
    }

    panel.add(buttonsPanel, BorderLayout.SOUTH);

    dialog.add(panel);
    dialog.setModal(true);
    dialog.setResizable(false);

    dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    dialog.addWindowListener(new WindowAdapter() {
      @Override
      public void windowOpened(WindowEvent e) {
        if (customDialog.getFocusedButton() != null) {
          JButton button =
            customDialog.getButton(customDialog.getFocusedButton().intValue());
          if (button != null) {
            button.requestFocus();
          }
        }
      }

      @Override
      public void windowClosing(WindowEvent e) {
        Integer index = customDialog.getDefaultButtonForDialogClose();
        if (index != null && customDialog.getButton(index.intValue()) != null) {
          customDialog.setResult(index);
        }
        else {
          customDialog.setResult(null);
        }
        dialog.dispose();
      }
    });
    return dialog;
  }
}

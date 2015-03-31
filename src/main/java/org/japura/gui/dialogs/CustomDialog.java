package org.japura.gui.dialogs;

import net.miginfocom.swing.MigLayout;
import org.japura.gui.WrapLabel;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;

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
public class CustomDialog {

  private static Icon defaultDialogIcon;

  public static void setDefaultDialogIcon(Icon icon) {
    if (icon != null) {
      CustomDialog.defaultDialogIcon = icon;
    }
  }

  public static Icon getDefaultDialogIcon() {
    return CustomDialog.defaultDialogIcon;
  }

  private Integer result;
  private Integer focusedButton;
  private JDialog dialog;

  private Map<Integer, JButton> buttons;
  private Map<Integer, List<ActionListener>> buttonActions;

  private LinkedHashMap<Component, Integer> contents;
  private int defaultGap = 7;

  private String title;
  private Icon dialogIcon;
  private Icon messageIcon;

  public CustomDialog(String title) {
    this(title, null);
  }

  public CustomDialog(String title, String message) {
    if (title == null) {
      title = "";
    }
    this.buttons = new HashMap<>();
    this.buttonActions = new HashMap<>();
    this.title = title;
    this.contents = new LinkedHashMap<>();

    if (message != null) {
      addMessageBlock(message);
    }

    if (CustomDialog.getDefaultDialogIcon() != null) {
      setDialogIcon(CustomDialog.getDefaultDialogIcon());
    }
  }

  private JDialog getDialog() {
    return dialog;
  }

  public void setDialogIcon(Icon dialogIcon) {
    this.dialogIcon = dialogIcon;
  }

  public void setMessageIcon(Icon messageIcon) {
    this.messageIcon = messageIcon;
  }

  private void fireButtonActions(int button, ActionEvent e) {
    setResult(new Integer(button));
    // first dispose
    getDialog().dispose();
    // now run others listeners
    for (ActionListener listener : this.buttonActions.get(button)) {
      listener.actionPerformed(e);
    }
  }

  public int addButton(String text) {
    JButton button = new JButton();
    button.setText(text);
    final Integer pos = new Integer(this.buttons.size());
    button.setName(pos.toString());
    this.buttons.put(pos, button);
    this.buttonActions.put(pos, new ArrayList<ActionListener>());
    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        fireButtonActions(pos.intValue(), e);
      }
    });
    return pos.intValue();
  }

  public Integer show() {
    return show(null);
  }

  public Integer show(Component owner) {
    Window window = null;
    if (owner != null) {
      window = SwingUtilities.getWindowAncestor(owner);
    }
    return show(window);
  }

  public Integer show(Window owner) {
    buildDialog();

    getDialog().pack();
    getDialog().setLocationRelativeTo(owner);
    getDialog().setVisible(true);

    return this.result;
  }

  protected void setResult(Integer result) {
    this.result = result;
  }

  private JButton getButton(int buttonIndex) {
    Integer index = new Integer(buttonIndex);
    return this.buttons.get(index);
  }

  public void setFocusedButton(int button) {
    JButton b = getButton(button);
    if (b != null) {
      this.focusedButton = new Integer(button);
    }
  }

  public Integer getFocusedButton() {
    return focusedButton;
  }

  public String getButtonText(int button) {
    JButton b = getButton(button);
    if (b == null) {
      return null;
    }
    return b.getText();
  }

  public void setButtonText(int button, String text) {
    JButton b = getButton(button);
    if (b != null && text != null) {
      b.setText(text);
    }
  }

  public void setButtonEnabled(int button, boolean enabled) {
    JButton b = getButton(button);
    if (b != null) {
      b.setEnabled(enabled);
    }
  }

  public boolean isButtonEnabled(int button) {
    JButton b = getButton(button);
    if (b != null) {
      return b.isEnabled();
    }
    return false;
  }

  public void addButtonActionKeyCode(int button, final int keyCode) {
    final JButton b = getButton(button);
    if (b != null) {
      b.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
          if (e.getKeyCode() == keyCode) {
            b.doClick();
          }
        }
      });
    }
  }

  public void addButtonAction(int button, ActionListener actionListener) {
    Integer index = new Integer(button);
    List<ActionListener> list = this.buttonActions.get(index);
    if (list != null) {
      list.add(actionListener);
    }
  }

  protected void buildDialog() {
    dialog = new JDialog();

    dialog.setTitle(title);

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout(0, 0));
    panel.setOpaque(false);

    if (dialogIcon != null) {
      BufferedImage image =
        new BufferedImage(dialogIcon.getIconWidth(),
          dialogIcon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);
      dialogIcon.paintIcon(null, image.getGraphics(), 0, 0);
      dialog.setIconImage(image);
    }

    if (messageIcon != null) {
      JPanel iconPanel = new JPanel();
      iconPanel.setOpaque(false);
      iconPanel.setLayout(new MigLayout("ins 5 5 5 0"));
      iconPanel.add(new JLabel(messageIcon));
      panel.add(iconPanel, BorderLayout.WEST);
    }

    JScrollPane sp = new JScrollPane(new ContentPanel(this.contents));
    sp.getViewport().setOpaque(false);
    sp.setOpaque(false);
    sp.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    panel.add(sp);

    ButtonsPanel buttonsPanel = new ButtonsPanel();
    for (int i = 0; i < this.buttons.size(); i++) {
      JButton button = this.buttons.get(new Integer(i));
      buttonsPanel.add(button);
    }

    panel.add(buttonsPanel, BorderLayout.SOUTH);

    dialog.add(panel);
    dialog.setModal(true);
    dialog.setResizable(false);

    this.dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    this.dialog.addWindowListener(new WindowAdapter() {
      @Override
      public void windowOpened(WindowEvent e) {
        if (getFocusedButton() != null) {
          JButton button = getButton(getFocusedButton().intValue());
          if (button != null) {
            button.requestFocus();
          }
        }
      }

      @Override
      public void windowClosing(WindowEvent e) {
        setResult(null);
        getDialog().dispose();
      }
    });
  }

  public void addContent(Component content) {
    addContent(content, defaultGap);
  }

  public void addContent(Component content, int topMargin) {
    contents.put(content, Math.max(topMargin, 0));
  }

  public void addMessageBlock(String message) {
    addMessageBlock(message, defaultGap);
  }

  public void addMessageBlock(String message, int topMargin) {
    WrapLabel wrapLabel = new WrapLabel(message);
    wrapLabel.setWrapWidth(450);
    addContent(wrapLabel, topMargin);
  }
}

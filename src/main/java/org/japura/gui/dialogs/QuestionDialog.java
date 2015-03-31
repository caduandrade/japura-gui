package org.japura.gui.dialogs;

import org.japura.gui.GUIImages;
import org.japura.gui.i18n.GUIStringKeys;
import org.japura.util.i18n.I18nAdapter;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.ActionListener;

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
public class QuestionDialog extends AbstractBasicDialog {

  private static Icon defaultDialogIcon = new ImageIcon(GUIImages.QUESTION);
  private static Icon defaultMessageIcon = new ImageIcon(GUIImages.QUESTION);

  public static void setDefaultMessageIcon(Icon icon) {
    if (icon != null) {
      QuestionDialog.defaultMessageIcon = icon;
    }
  }

  public static void setDefaultDialogIcon(Icon icon) {
    if (icon != null) {
      QuestionDialog.defaultDialogIcon = icon;
    }
  }

  public static Icon getDefaultDialogIcon() {
    return QuestionDialog.defaultDialogIcon;
  }

  public static Icon getDefaultMessageIcon() {
    return QuestionDialog.defaultMessageIcon;
  }

  public static boolean show(Component owner, String title, String message) {
    QuestionDialog dialog = new QuestionDialog(title, message);
    return dialog.show(owner);
  }

  public static boolean show(Window owner, String title, String message) {
    QuestionDialog dialog = new QuestionDialog(title, message);
    return dialog.show(owner);
  }

  public static boolean show(String title, String message) {
    QuestionDialog dialog = new QuestionDialog(title, message);
    return dialog.show();
  }

  private CustomDialog dialog;
  private QuestionButton defaultButtonForDispose;

  public QuestionDialog(String title, String message) {
    this.defaultButtonForDispose = QuestionButton.NO;
    this.dialog = new CustomDialog(title, message);

    this.dialog.addButton(I18nAdapter.getAdapter().getString(
      GUIStringKeys.YES.getKey()));

    this.dialog.addButton(I18nAdapter.getAdapter().getString(
      GUIStringKeys.NO.getKey()));

    setFocusedButton(QuestionButton.NO);

    setMessageIcon(QuestionDialog.getDefaultMessageIcon());
    if (QuestionDialog.getDefaultDialogIcon() != null) {
      setDialogIcon(QuestionDialog.getDefaultDialogIcon());
    }
  }

  public void setDefaultButtonForDispose(QuestionButton button) {
    this.defaultButtonForDispose = button;
  }

  public QuestionButton getDefaultButtonForDispose() {
    return defaultButtonForDispose;
  }

  @Override
  protected CustomDialog getDialog() {
    return this.dialog;
  }

  public boolean show() {
    return show(null);
  }

  public boolean show(Component owner) {
    Window window = null;
    if (owner != null) {
      window = SwingUtilities.getWindowAncestor(owner);
    }
    return show(window);
  }

  public boolean show(Window owner) {
    Integer result = getDialog().show(owner);
    if (result == null) {
      result = new Integer(getDefaultButtonForDispose().getIndex());
    }
    if (QuestionButton.YES.getIndex() == result.intValue()) {
      return true;
    }
    if (QuestionButton.NO.getIndex() == result.intValue()) {
      return false;
    }
    throw new RuntimeException("Unknow result: " + result);
  }

  public void setFocusedButton(QuestionButton button) {
    if (button != null) {
      getDialog().setFocusedButton(button.getIndex());
    }
  }

  public QuestionButton getFocusedButton() {
    Integer index = getDialog().getFocusedButton();
    if (index != null) {
      for (QuestionButton button : QuestionButton.values()) {
        if (button.getIndex() == index.intValue()) {
          return button;
        }
      }
    }
    return null;
  }

  public String getButtonText(QuestionButton button) {
    if (button == null) {
      return null;
    }
    return getDialog().getButtonText(button.getIndex());
  }

  public void setButtonText(QuestionButton button, String text) {
    if (button != null && text != null) {
      getDialog().setButtonText(button.getIndex(), text);
    }
  }

  public void setButtonEnabled(QuestionButton button, boolean enabled) {
    if (button != null) {
      getDialog().setButtonEnabled(button.getIndex(), enabled);
    }
  }

  public boolean isButtonEnabled(QuestionButton button) {
    if (button != null) {
      return getDialog().isButtonEnabled(button.getIndex());
    }
    return false;
  }

  public void addButtonActionKeyCode(QuestionButton button, int keyCode) {
    if (button != null) {
      getDialog().addButtonActionKeyCode(button.getIndex(), keyCode);
    }
  }

  public void addButtonAction(QuestionButton button,
    ActionListener actionListener) {
    if (button != null) {
      getDialog().addButtonAction(button.getIndex(), actionListener);
    }
  }

}

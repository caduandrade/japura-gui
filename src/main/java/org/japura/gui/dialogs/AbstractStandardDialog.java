package org.japura.gui.dialogs;

import org.japura.gui.i18n.GUIStringKeys;
import org.japura.util.i18n.I18nAdapter;

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
public abstract class AbstractStandardDialog extends AbstractBasicDialog {

  private CustomDialog dialog;

  public AbstractStandardDialog(String title, String message) {
    this(title, message, I18nAdapter.getAdapter().getString(
      GUIStringKeys.CLOSE.getKey()));
  }

  public AbstractStandardDialog(String title, String message, String textButton) {
    this.dialog = new CustomDialog(title, message);
    this.dialog.addButton(textButton);
  }

  @Override
  protected CustomDialog getDialog() {
    return dialog;
  }

  public void addButtonAction(ActionListener actionListener) {
    getDialog().addButtonAction(0, actionListener);
  }

  public void show() {
    getDialog().show();
  }

  public void show(Window owner) {
    getDialog().show(owner);
  }

  public String getButtonText() {
    return getDialog().getButtonText(0);
  }

  public void setButtonText(String text) {
    getDialog().setButtonText(0, text);
  }

  public void setButtonEnabled(boolean enabled) {
    getDialog().setButtonEnabled(0, enabled);
  }

  public boolean isButtonEnabled() {
    return getDialog().isButtonEnabled(0);
  }

  public void addButtonActionKeyCode(int keyCode) {
    getDialog().addButtonActionKeyCode(0, keyCode);
  }

}

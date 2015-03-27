package org.japura.gui.dialogs;

import javax.swing.Icon;
import java.awt.Component;

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
public abstract class AbstractBasicDialog {

  protected abstract CustomDialog getDialog();

  public void setDialogIcon(Icon icon) {
    getDialog().setDialogIcon(icon);
  }

  public void setMessageIcon(Icon icon) {
    getDialog().setMessageIcon(icon);
  }

  public void addMessageBlock(String message) {
    getDialog().addMessageBlock(message);
  }

  public void addMessageBlock(String message, int topMargin) {
    getDialog().addMessageBlock(message, topMargin);
  }

  public void addContent(Component content) {
    getDialog().addContent(content);
  }

  public void addContent(Component content, int topMargin) {
    getDialog().addContent(content, topMargin);
  }
}

package org.japura.gui.i18n;

import org.japura.util.i18n.HandlerString;

/**
 * <P>
 * Copyright (C) 2013-2015 Carlos Eduardo Leite de Andrade
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
public class DefaultGUIHandlerString implements HandlerString {

  @Override
  public String getString(String key) {
    if (key == null) {
      return null;
    }
    if (GUIStringKeys.CALENDAR_WINDOW_TITLE.getKey().equals(key)) {
      return "Choose a date";
    }
    if (GUIStringKeys.SELECT_ALL.getKey().equals(key)) {
      return "Select all";
    }
    if (GUIStringKeys.DESELECT_ALL.getKey().equals(key)) {
      return "Deselect all";
    }
    if (GUIStringKeys.SELECT_DESELECT_ALL.getKey().equals(key)) {
      return "Select/Deselect all";
    }
    if (GUIStringKeys.YES.getKey().equals(key)) {
      return "Yes";
    }
    if (GUIStringKeys.NO.getKey().equals(key)) {
      return "No";
    }
    if (GUIStringKeys.CLOSE.getKey().equals(key)) {
      return "Close";
    }

    return null;
  }

}

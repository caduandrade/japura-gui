package org.japura.gui.util;

import java.awt.Component;
import java.awt.Window;

import javax.swing.SwingUtilities;

/**
 * Copyright (C) 2014 Carlos Eduardo Leite de Andrade
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
 * 
 */
public final class GUIUtils{

  public static Window getWindowAncestor(Component component) {
	if (component != null) {
	  if (component instanceof Window) {
		return (Window) component;
	  }
	  Window window = SwingUtilities.getWindowAncestor(component);
	  if (window != null) {
		return window;
	  }
	}
	return null;
  }

  public static void requestWindowFocus(Component component) {
	if (component != null) {
	  Window window = GUIUtils.getWindowAncestor(component);
	  if (window != null) {
		window.requestFocus();
	  }
	}
  }

  public static boolean isWindowFocused(Component component) {
	if (component != null) {
	  Window window = GUIUtils.getWindowAncestor(component);
	  if (window != null) {
		return window.isFocused();
	  }
	}
	return false;
  }

}

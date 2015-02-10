package org.japura.gui;

import javax.swing.JComponent;

/**
 * 
 * <P>
 * Copyright (C) 2011 Carlos Eduardo Leite de Andrade
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
public class EmbeddedComponent{

  private Anchor anchor;
  private JComponent component;

  public EmbeddedComponent(JComponent component) {
	this(component, Anchor.SOUTH);
  }

  public EmbeddedComponent(JComponent component, Anchor anchor) {
	setAnchor(anchor);
	this.component = component;
  }

  public Anchor getAnchor() {
	return anchor;
  }

  public void setAnchor(Anchor anchor) {
	if (anchor == null) {
	  anchor = Anchor.SOUTH;
	}
	this.anchor = anchor;
  }

  public JComponent getComponent() {
	return component;
  }

}

package org.japura.gui.dialogs;

import net.miginfocom.swing.MigLayout;

import javax.swing.JPanel;
import javax.swing.Scrollable;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.LinkedHashMap;
import java.util.Map;

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
class ContentPanel extends JPanel implements Scrollable {

  private static final long serialVersionUID = 1L;

  public ContentPanel(LinkedHashMap<Component, Integer> contents) {
    setOpaque(false);

    setLayout(new MigLayout("ins 0 0 0 5, gapy 0, wrap 1", "grow"));
    boolean first = true;
    for (Map.Entry<Component, Integer> entry : contents.entrySet()) {
      Component comp = entry.getKey();
      int topGap = entry.getValue().intValue();
      if (first) {
        topGap = 0;
      }
      add(comp, "grow x, gaptop " + topGap);
      first = false;
    }
  }

  @Override
  public Dimension getPreferredScrollableViewportSize() {
    Dimension dim = getPreferredSize();
    if (isPreferredSizeSet() == false) {
      dim.height = Math.min(dim.height, 150);
    }
    return dim;
  }

  @Override
  public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation,
    int direction) {
    return 10;
  }

  @Override
  public int getScrollableBlockIncrement(Rectangle visibleRect,
    int orientation, int direction) {
    return 100;
  }

  @Override
  public boolean getScrollableTracksViewportWidth() {
    return true;
  }

  @Override
  public boolean getScrollableTracksViewportHeight() {
    return false;
  }

}

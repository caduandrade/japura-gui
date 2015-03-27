package org.japura.gui.dialogs;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.Point2D;

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
class ButtonsPanel extends JPanel {
  private static final long serialVersionUID = 1L;

  private Color firstColor = Color.gray;
  private Color secondColor = new Color(215, 215, 215);

  public ButtonsPanel() {
    setLayout(new FlowLayout(FlowLayout.RIGHT));
  }

  @Override
  protected void paintComponent(Graphics g) {
    if (isOpaque()) {
      Graphics2D g2d = (Graphics2D) g;
      g2d.setPaint(new GradientPaint(new Point2D.Double(0, 0), getBackground(),
        new Point2D.Double(getWidth(), 0), secondColor));
      g2d.fillRect(0, 0, getWidth(), getHeight());

      g2d.setPaint(new GradientPaint(new Point2D.Double(getWidth(), 0),
        firstColor, new Point2D.Double(0, 0), secondColor));
      g2d.fillRect(0, 0, getWidth(), 1);
    }
  }
}

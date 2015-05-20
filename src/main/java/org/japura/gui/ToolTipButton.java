package org.japura.gui;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.View;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

/**
 * Button with a tooltip function.
 * <P>
 * The tooltip text is wrapped with a defined width.
 * <P>
 * Copyright (C) 2009-2015 Carlos Eduardo Leite de Andrade
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
public class ToolTipButton extends JPanel {

  private static final long serialVersionUID = 6L;
  public final static Color DEFAULT_BACKGROUND = new Color(255, 255, 220);
  private Color borderColor = Color.BLACK;
  private Color toolTipBackground;
  private Icon image;
  private Icon imageMouseOver;
  private int toolTipWrapWidth = 300;
  private JLabel imageComponent;
  private Insets margin;
  private int borderThickness = 2;
  private String text;
  private ToolTipButtonTimer timer;
  private JPopupMenu popup;
  private Rectangle2D popupBounds;
  private JComponent extraComponent;
  private ToolTipButtonAnchor extraComponentAnchor;

  /**
   * Constructor
   * 
   * @param image {@link Icon} - image for tooltip button
   */
  public ToolTipButton(Icon image) {
    this(image, null, null);
  }

  /**
   * Constructor
   * 
   * @param image {@link Icon} - image for tooltip button
   * @param tooltip {@link String} - tooltip text
   */
  public ToolTipButton(Icon image, String tooltip) {
    this(image, null, tooltip);
  }

  /**
   * Constructor
   *
   * @param image {@link Icon} - image for tooltip button
   * @param imageMouseOver {@link Icon} - image for mouse over tooltip button
   * @param tooltip {@link String} - tooltip text
   */
  public ToolTipButton(Icon image, Icon imageMouseOver, String tooltip) {
    this(image, imageMouseOver, tooltip, null, null);
  }

  /**
   * Constructor
   * 
   * @param image {@link Icon} - image for tooltip button
   * @param imageMouseOver {@link Icon} - image for mouse over tooltip button
   * @param tooltip {@link String} - tooltip text
   * @param extraComponent an extra component for the hint
   * @param extraComponentAnchor the BordeLayout constraint for the extra
   *        component
   */
  public ToolTipButton(Icon image, Icon imageMouseOver, String tooltip,
    JComponent extraComponent, ToolTipButtonAnchor extraComponentAnchor) {
    this.extraComponent = extraComponent;
    this.extraComponentAnchor = extraComponentAnchor;

    setTooltipMargin(null);
    setLayout(new GridBagLayout());

    imageComponent = new JLabel(image);
    add(imageComponent);

    this.image = image;
    this.imageMouseOver = imageMouseOver;
    this.toolTipBackground = ToolTipButton.DEFAULT_BACKGROUND;
    setText(tooltip);
    setOpaque(false);

    this.timer = new ToolTipButtonTimer();

    addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        timer.startNextAction(NextAction.SHOW);
      }

      @Override
      public void mouseClicked(MouseEvent e) {
        timer.stop();
        showTooltip();
      }

      @Override
      public void mouseExited(MouseEvent e) {
        timer.startNextAction(NextAction.DISPOSE);
      }
    });

    if (imageMouseOver != null) {
      addMouseListener(new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
          imageComponent.setIcon(ToolTipButton.this.imageMouseOver);
        }

        @Override
        public void mouseExited(MouseEvent e) {
          imageComponent.setIcon(ToolTipButton.this.image);
        }
      });
    }
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    if (text == null) {
      text = "";
    }
    this.text = text;
  }

  @Override
  public void setToolTipText(String text) {
    setText(text);
  }

  public int getDisposeTime() {
    return timer.getDisposeTime();
  }

  public void setDisposeTime(int time) {
    timer.setDisposeTime(time);
  }

  public int getShowTime() {
    return timer.getShowTime();
  }

  public void setShowTime(int time) {
    timer.setShowTime(time);
  }

  public Insets getTooltipMargin() {
    return margin;
  }

  public void setTooltipMargin(Insets margin) {
    if (margin == null) {
      margin = new Insets(10, 10, 10, 10);
    }
    margin.bottom = (Math.max(margin.bottom, 0));
    margin.top = (Math.max(margin.top, 0));
    margin.right = (Math.max(margin.right, 0));
    margin.left = (Math.max(margin.left, 0));
    this.margin = margin;
  }

  /**
   * Set the border width.
   * 
   * @param thickness an integer specifying the width in pixels
   */
  public void setTooltipBorderThickness(int thickness) {
    borderThickness = Math.max(0, thickness);
  }

  /**
   * Get the border width.
   * 
   * @return an integer specifying the width in pixels
   */
  public int getTooltipBorderThickness() {
    return borderThickness;
  }

  public Color getTooltipBorderColor() {
    return borderColor;
  }

  public void setTooltipBorderColor(Color borderColor) {
    this.borderColor = borderColor;
  }

  public Color getToolTipBackground() {
    return toolTipBackground;
  }

  public void setToolTipBackground(Color toolTipBackground) {
    this.toolTipBackground = toolTipBackground;
  }

  /**
   * Get the tooltip text wrap width.
   * 
   * @return int
   */
  public int getToolTipWrapWidth() {
    return toolTipWrapWidth;
  }

  /**
   * Set the tooltip text wrap width.
   * <P>
   * The minimal value is 200
   * 
   * @param toolTipWrapWidth int
   */
  public void setToolTipWrapWidth(int toolTipWrapWidth) {
    this.toolTipWrapWidth = Math.max(200, toolTipWrapWidth);
  }

  private void tryDisposeTooltip() {
    if (popup != null) {
      if (popup.isShowing()) {
        Point cursorLocation = MouseInfo.getPointerInfo().getLocation();
        if (popupBounds.contains(cursorLocation) == false) {
          disposeTooltip();
        }
      }
      else {
        disposeTooltip();
      }
    }
  }

  /**
   * Dispose the tooltip
   */
  public void disposeTooltip() {
    if (popup != null) {
      timer.stop();
      popup.setVisible(false);
      popup = null;
    }
  }

  /**
   * Show the tooltip.
   */
  private void showTooltip() {
    Wrap tooltipLabel = new Wrap(toolTipWrapWidth);
    tooltipLabel.setForeground(getForeground());
    tooltipLabel.setFont(ToolTipButton.this.getFont());
    tooltipLabel.setText(text);

    JPanel panel = new JPanel();
    panel.setBackground(toolTipBackground);
    panel.setLayout(new BorderLayout());
    panel.add(tooltipLabel, BorderLayout.CENTER);


    if (extraComponent != null && extraComponentAnchor != null) {
      if (extraComponentAnchor.equals(ToolTipButtonAnchor.SOUTH)) {
        panel.add(extraComponent, BorderLayout.SOUTH);
      }
      else if (extraComponentAnchor.equals(ToolTipButtonAnchor.NORTH)) {
        panel.add(extraComponent, BorderLayout.NORTH);
      }
      else if (extraComponentAnchor.equals(ToolTipButtonAnchor.EAST)) {
        panel.add(extraComponent, BorderLayout.EAST);
      }
      else if (extraComponentAnchor.equals(ToolTipButtonAnchor.WEST)) {
        panel.add(extraComponent, BorderLayout.WEST);
      }
    }

    Border out = BorderFactory.createLineBorder(borderColor, borderThickness);
    Border in =
      BorderFactory.createEmptyBorder(margin.top, margin.left, margin.bottom,
        margin.right);
    Border border = BorderFactory.createCompoundBorder(out, in);
    panel.setBorder(border);

    popup = new JPopupMenu();
    popup.setBorder(BorderFactory.createEmptyBorder());
    popup.add(panel);

    popup.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseExited(MouseEvent e) {
        tryDisposeTooltip();
      }
    });

    popup.pack();
    popup.show(ToolTipButton.this, 0, getHeight());

    Point point = popup.getLocationOnScreen();
    Dimension dim = popup.getSize();
    popupBounds =
      new Rectangle2D.Double(point.getX(), point.getY(), dim.getWidth(),
        dim.getHeight());
  }

  private static class Wrap extends JTextPane {

    private static final long serialVersionUID = 2849635511260534304L;
    private View view;
    private int width;

    public Wrap(int width) {
      this.width = width;
      setText("");
      setContentType("text/html");
      setOpaque(false);
      setEditable(false);
      setHighlighter(null);
      setBorder(null);
      setBackground(new Color(0, 0, 0, 0));
    }

    private void setHTMLFont(Font font) {
      MutableAttributeSet attrs = getInputAttributes();
      StyleConstants.setFontFamily(attrs, font.getFamily());
      StyleConstants.setFontSize(attrs, font.getSize());
      // StyleConstants.setItalic(attrs, (font.getStyle() & Font.ITALIC) != 0);
      // StyleConstants.setBold(attrs, (font.getStyle() & Font.BOLD) != 0);
      StyleConstants.setForeground(attrs, getForeground());
      StyledDocument doc = getStyledDocument();
      doc.setCharacterAttributes(0, doc.getLength() + 1, attrs, false);
    }

    @Override
    public void setText(String t) {
      super.setText(t);
      view = javax.swing.plaf.basic.BasicHTML.createHTMLView(Wrap.this, t);
      setHTMLFont(getFont());
    }

    @Override
    public Dimension getPreferredSize() {
      if (isPreferredSizeSet()) {
        return super.getPreferredSize();
      }
      view.setSize(width, 0);

      Insets insets = getInsets();
      Insets margin = getMargin();

      float w =
        view.getPreferredSpan(View.X_AXIS) + insets.left + insets.right
          + margin.left + margin.right;
      float h =
        view.getPreferredSpan(View.Y_AXIS) + insets.bottom + insets.top
          + margin.bottom + margin.top;

      return new Dimension((int) Math.ceil(w), (int) Math.ceil(h));
    }
  }

  private enum NextAction {
    SHOW,
    DISPOSE;
  }

  private class ToolTipButtonTimer extends Timer implements ActionListener {

    private int showTime = 800;
    private int disposeTime = 200;

    private NextAction nextAction = NextAction.SHOW;

    public ToolTipButtonTimer() {
      super(0, null);
      addActionListener(this);
      setRepeats(false);
    }

    public void setShowTime(int showTime) {
      this.showTime = showTime;
    }

    public int getShowTime() {
      return showTime;
    }

    public void setDisposeTime(int disposeTime) {
      this.disposeTime = disposeTime;
    }

    public int getDisposeTime() {
      return disposeTime;
    }

    public void startNextAction(NextAction nextAction) {
      stop();
      this.nextAction = nextAction;
      if (nextAction.equals(NextAction.SHOW)) {
        setInitialDelay(getShowTime());
      }
      else if (nextAction.equals(NextAction.DISPOSE)) {
        setInitialDelay(getDisposeTime());
      }
      start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      if (nextAction.equals(NextAction.SHOW)) {
        showTooltip();
      }
      else if (nextAction.equals(NextAction.DISPOSE)) {
        tryDisposeTooltip();
      }
    }
  }

  public static enum ToolTipButtonAnchor {
    SOUTH,
    NORTH,
    EAST,
    WEST;
  }

}

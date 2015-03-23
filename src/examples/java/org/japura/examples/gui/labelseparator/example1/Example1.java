package org.japura.examples.gui.labelseparator.example1;

import net.miginfocom.swing.MigLayout;
import org.japura.examples.gui.AbstractExample;
import org.japura.gui.LabelSeparator;
import org.japura.gui.renderer.ColoredHorizontalLine;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;

public class Example1 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    JPanel panel = new JPanel();
    panel.setLayout(new MigLayout("wrap 1", "grow"));
    panel.setBackground(Color.WHITE);

    LabelSeparator separator1 = new LabelSeparator("separator 1");

    LabelSeparator separator2 = new LabelSeparator("separator 2");
    separator2.setForeground(Color.BLUE);

    LabelSeparator separator3 = new LabelSeparator("separator 3");
    separator3.setLineRenderer(new ColoredHorizontalLine(Color.BLUE, 1));

    LabelSeparator separator4 = new LabelSeparator("separator 4");
    separator4.setLineRenderer(new ColoredHorizontalLine(Color.BLUE, 3));

    LabelSeparator separator5 = new LabelSeparator("separator 5");
    separator5.setSeparatorGap(20);

    LabelSeparator separator6 = new LabelSeparator("separator 6");
    separator6.setLeftSeparatorWidth(0);

    LabelSeparator separator7 = new LabelSeparator("separator 7");
    separator7.setLeftSeparatorWidth(0);
    separator7.setSeparatorGap(20);

    LabelSeparator separator8 = new LabelSeparator("separator 8");
    separator8.setAlign(LabelSeparator.Align.RIGHT);
    separator8.setRightSeparatorWidth(50);

    panel.add(separator1, "grow x");
    panel.add(separator2, "grow x");
    panel.add(separator3, "grow x");
    panel.add(separator4, "grow x");
    panel.add(separator5, "grow x");
    panel.add(separator6, "grow x");
    panel.add(separator7, "grow x");
    panel.add(separator8, "grow x");

    return panel;
  }

  @Override
  protected LayoutManager buildRootLayout() {
    return new BorderLayout();
  }

  public static void main(String[] args) {
    Example1 example = new Example1();
    example.runExample();
  }

}

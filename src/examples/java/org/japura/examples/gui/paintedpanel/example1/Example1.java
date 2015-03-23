package org.japura.examples.gui.paintedpanel.example1;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.ExampleImages;
import org.japura.gui.Anchor;
import org.japura.gui.Gradient;
import org.japura.gui.PaintedPanel;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.LayoutManager;
import java.net.URL;

public class Example1 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    URL globeUrl = ExampleImages.GLOBE_IMAGE;

    PaintedPanel panel = new PaintedPanel();
    panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    panel.addBackground(new Gradient(Gradient.Direction.LEFT_TO_RIGHT,
      Color.white, Color.orange));
    panel.addBackground(new ImageIcon(globeUrl), Anchor.NORTH_EAST);

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

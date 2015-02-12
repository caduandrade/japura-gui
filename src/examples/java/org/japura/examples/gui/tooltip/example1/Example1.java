package org.japura.examples.gui.tooltip.example1;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.ExampleImages;
import org.japura.gui.ToolTipButton;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.net.URL;

public class Example1 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    URL url = ExampleImages.QUESTION_IMAGE;

    Icon icon = new ImageIcon(url);
    String text = "Visit Japura project at http://www.japura.org";
    ToolTipButton toolTipButton = new ToolTipButton(icon, text);

    return toolTipButton;
  }

  public static void main(String args[]) {
    Example1 example = new Example1();
    example.runExample();
  }
}

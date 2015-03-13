package org.japura.examples.gui.decorator.example4;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.ExampleImages;
import org.japura.gui.Anchor;
import org.japura.gui.Decorator;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Component;
import java.net.URL;

public class Example4 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    URL urlChecked = ExampleImages.CHECK_IMAGE;

    JTextField field = new JTextField(11);
    Decorator decorator = new Decorator(field, Anchor.EAST);
    decorator.addDecoration("name", new ImageIcon(urlChecked));
    decorator.removeDecoration("name");

    return decorator;
  }

  public static void main(String args[]) {
    Example4 example = new Example4();
    example.runExample();
  }

}

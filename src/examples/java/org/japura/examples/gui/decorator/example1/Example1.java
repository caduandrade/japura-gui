package org.japura.examples.gui.decorator.example1;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.ExampleImages;
import org.japura.gui.Anchor;
import org.japura.gui.Decorator;

import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.Component;
import java.awt.Insets;
import java.net.URL;

public class Example1 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    URL urlKey = ExampleImages.KEY_IMAGE;

    JPasswordField passField = new JPasswordField(10);
    Decorator decorator = new Decorator(passField, Anchor.EAST);
    decorator.setMargin(new Insets(0, 0, 0, 20));
    decorator.addDecoration(new ImageIcon(urlKey));

    return decorator;
  }

  public static void main(String args[]) {
    Example1 example = new Example1();
    example.runExample();
  }

}

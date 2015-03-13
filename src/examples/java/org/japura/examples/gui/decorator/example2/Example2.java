package org.japura.examples.gui.decorator.example2;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.ExampleImages;
import org.japura.gui.Anchor;
import org.japura.gui.Decorator;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Insets;
import java.net.URL;

public class Example2 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    URL urlChecked = ExampleImages.CHECK_IMAGE;

    JTextField field = new JTextField(11);
    Decorator decorator = new Decorator(field, Anchor.EAST);
    decorator.setMargin(new Insets(0, 0, 0, 7));
    decorator.addDecoration(new ImageIcon(urlChecked));

    return decorator;
  }

  public static void main(String args[]) {
    Example2 example = new Example2();
    example.runExample();
  }

}

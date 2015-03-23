package org.japura.examples.gui.wraplabel.example1;

import org.japura.examples.gui.AbstractExample;
import org.japura.gui.WrapLabel;

import java.awt.Component;

public class Example1 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    WrapLabel label = new WrapLabel();
    label.setWrapWidth(200);
    label.setText("This is a WrapLabel. The auto wrap width is 200 pixels.");

    return label;
  }

  public static void main(String[] args) {
    Example1 example = new Example1();
    example.runExample();
  }

}

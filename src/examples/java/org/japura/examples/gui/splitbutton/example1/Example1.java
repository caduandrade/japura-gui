package org.japura.examples.gui.splitbutton.example1;

import org.japura.examples.gui.AbstractExample;
import org.japura.gui.SplitButton;

import java.awt.Component;

public class Example1 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    SplitButton button = new SplitButton(SplitButton.BUTTON);
    button.addButton("Action");
    button.addButton("Other action");
    return button;
  }

  public static void main(String[] a) {
    Example1 example = new Example1();
    example.runExample();
  }

}

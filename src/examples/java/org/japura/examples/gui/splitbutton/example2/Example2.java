package org.japura.examples.gui.splitbutton.example2;

import org.japura.examples.gui.AbstractExample;
import org.japura.gui.SplitButton;

import java.awt.Component;

public class Example2 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    SplitButton button = new SplitButton(SplitButton.MENU);
    button.setText("Choose...");
    button.addButton("Action");
    button.addButton("Other action");
    return button;
  }

  public static void main(String[] a) {
    Example2 example = new Example2();
    example.runExample();
  }

}

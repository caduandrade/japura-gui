package org.japura.examples.gui.splitbutton.example5;

import org.japura.examples.gui.AbstractExample;
import org.japura.gui.SplitButton;

import java.awt.Component;

public class Example5 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    SplitButton button = new SplitButton();
    button.addButton("Undo");
    button.addButton("Redo");
    button.addSeparator();
    button.addButton("Cut");
    button.addButton("Copy");
    button.addSeparator();
    button.addButton("Select all");
    return button;
  }

  public static void main(String[] a) {
    Example5 example = new Example5();
    example.runExample();
  }

}

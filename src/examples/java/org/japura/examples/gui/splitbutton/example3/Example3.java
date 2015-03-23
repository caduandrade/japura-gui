package org.japura.examples.gui.splitbutton.example3;

import org.japura.examples.gui.AbstractExample;
import org.japura.gui.SplitButton;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Example3 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    SplitButton button = new SplitButton();
    button.addButton("Search");
    button.addButton("Search adding");
    button.addButton("Search removing");

    button.setButtonEnabled("Search removing", false);

    button.addActionListener("Search", new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // action
      }
    });

    return button;
  }

  public static void main(String[] a) {
    Example3 example = new Example3();
    example.runExample();
  }

}

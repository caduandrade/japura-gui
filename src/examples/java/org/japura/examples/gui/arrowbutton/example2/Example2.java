package org.japura.examples.gui.arrowbutton.example2;

import org.japura.examples.gui.AbstractExample;
import org.japura.gui.ArrowButton;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;

public class Example2 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    JPanel panel = new JPanel();

    panel.add(new ArrowButton(8));
    panel.add(new ArrowButton(14));
    panel.add(new ArrowButton(22));
    panel.add(new ArrowButton(32));

    panel.setBackground(Color.WHITE);
    return panel;
  }

  public static void main(String args[]) {
    Example2 example = new Example2();
    example.runExample();
  }
}

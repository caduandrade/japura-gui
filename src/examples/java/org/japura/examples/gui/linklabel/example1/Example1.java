package org.japura.examples.gui.linklabel.example1;

import org.japura.examples.gui.AbstractExample;
import org.japura.gui.LinkLabel;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Example1 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    LinkLabel linkLabel = new LinkLabel();
    linkLabel.setText("Click on me");

    linkLabel.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Thank you!");
      }
    });

    return linkLabel;
  }

  public static void main(String[] args) {
    Example1 example = new Example1();
    example.runExample();
  }

}

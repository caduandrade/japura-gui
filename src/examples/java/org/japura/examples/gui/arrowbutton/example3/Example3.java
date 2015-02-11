package org.japura.examples.gui.arrowbutton.example3;

import org.japura.gui.ArrowButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Example3 {
  public static void main(String args[]) {
    ArrowButton arrowButton = new ArrowButton(ArrowButton.UP);
    arrowButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("action");
      }
    });
  }
}

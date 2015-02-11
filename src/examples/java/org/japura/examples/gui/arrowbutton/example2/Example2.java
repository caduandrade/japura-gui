package org.japura.examples.gui.arrowbutton.example2;

import org.japura.examples.gui.ExampleUtil;
import org.japura.gui.ArrowButton;

import javax.swing.JPanel;
import java.awt.Color;

public class Example2 {
  public static void main(String args[]) {
    JPanel panel = new JPanel();

    panel.add(new ArrowButton(8));
    panel.add(new ArrowButton(14));
    panel.add(new ArrowButton(22));
    panel.add(new ArrowButton(32));

    panel.setBackground(Color.WHITE);
    ExampleUtil.showFrame(panel, 600, 200);
  }
}

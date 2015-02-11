package org.japura.examples.gui.arrowbutton.example1;

import org.japura.examples.gui.ExampleUtil;
import org.japura.gui.ArrowButton;

import javax.swing.JPanel;
import java.awt.Color;

public class Example1 {
  public static void main(String args[]) {
    JPanel panel = new JPanel();

    panel.add(new ArrowButton(ArrowButton.FIRST));
    panel.add(new ArrowButton(ArrowButton.DOUBLE_LEFT));
    panel.add(new ArrowButton(ArrowButton.LEFT));
    panel.add(new ArrowButton(ArrowButton.RIGHT));
    panel.add(new ArrowButton(ArrowButton.DOUBLE_RIGHT));
    panel.add(new ArrowButton(ArrowButton.DOUBLE_UP));
    panel.add(new ArrowButton(ArrowButton.UP));
    panel.add(new ArrowButton(ArrowButton.DOWN));
    panel.add(new ArrowButton(ArrowButton.DOUBLE_DOWN));
    panel.add(new ArrowButton(ArrowButton.LAST));

    panel.setBackground(Color.WHITE);
    ExampleUtil.showFrame(panel, 600, 200);
  }
}

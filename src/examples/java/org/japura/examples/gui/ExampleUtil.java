package org.japura.examples.gui;

import javax.swing.JComponent;
import javax.swing.JFrame;
import java.net.URL;

public final class ExampleUtil {

  public static final URL QUESTION_IMAGE = ExampleUtil.class
    .getResource("/images/question.png");

  public static void showFrame(JComponent component, int width, int height) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(component);
    frame.setSize(width, height);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}

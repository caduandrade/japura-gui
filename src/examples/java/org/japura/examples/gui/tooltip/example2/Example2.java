package org.japura.examples.gui.tooltip.example2;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.ExampleImages;
import org.japura.gui.ToolTipButton;

import javax.swing.*;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Example2 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    URL url = ExampleImages.QUESTION_IMAGE;

    Icon icon = new ImageIcon(url);
    String text = "Visit Japura project at http://www.japura.org";

    JButton button = new JButton("Click me!");

    JPanel panel = new JPanel();
    panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    panel.setOpaque(false);
    panel.add(new JLabel("my button:"));
    panel.add(button);

    final ToolTipButton toolTipButton =
      new ToolTipButton(icon, null, text, panel,
        ToolTipButton.ToolTipButtonAnchor.SOUTH);

    button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        toolTipButton.disposeTooltip();
      }
    });

    return toolTipButton;
  }

  public static void main(String args[]) {
    Example2 example = new Example2();
    example.runExample();
  }
}

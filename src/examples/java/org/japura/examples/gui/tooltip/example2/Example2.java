package org.japura.examples.gui.tooltip.example2;

import net.miginfocom.swing.MigLayout;
import org.japura.examples.gui.ExampleUtil;
import org.japura.gui.ToolTipButton;

import javax.swing.*;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Example2 {
  public static void main(String args[]) {
    URL url = ExampleUtil.QUESTION_IMAGE;

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

    JPanel panel2 = new JPanel();
    panel2.setBackground(Color.WHITE);
    panel2.setLayout(new MigLayout());
    panel2.add(toolTipButton);

    ExampleUtil.showFrame(panel2, 600, 200);
  }
}

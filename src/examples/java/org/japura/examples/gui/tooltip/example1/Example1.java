package org.japura.examples.gui.tooltip.example1;

import net.miginfocom.swing.MigLayout;
import org.japura.examples.gui.ExampleUtil;
import org.japura.gui.ToolTipButton;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Color;
import java.net.URL;

public class Example1 {
  public static void main(String args[]) {
    URL url = ExampleUtil.QUESTION_IMAGE;

    Icon icon = new ImageIcon(url);
    String text = "Visit Japura project at http://www.japura.org";
    ToolTipButton toolTipButton = new ToolTipButton(icon, text);

    JPanel panel = new JPanel();
    panel.setBackground(Color.WHITE);
    panel.setLayout(new MigLayout());
    panel.add(toolTipButton);

    ExampleUtil.showFrame(panel, 600, 200);
  }
}

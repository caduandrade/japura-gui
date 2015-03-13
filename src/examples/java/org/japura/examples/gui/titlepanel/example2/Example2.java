package org.japura.examples.gui.titlepanel.example2;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.ExampleImages;
import org.japura.gui.TitlePanel;

import javax.swing.*;
import java.awt.Color;
import java.awt.Component;
import java.net.URL;

public class Example2 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    URL urlRefresh = ExampleImages.REFRESH_IMAGE;
    URL urlHelp = ExampleImages.HELP_IMAGE;
    URL urlBuy = ExampleImages.BUY_IMAGE;

    ImageIcon iconRefresh = new ImageIcon(urlRefresh);
    ImageIcon iconHelp = new ImageIcon(urlHelp);
    ImageIcon iconBuy = new ImageIcon(urlBuy);

    JLabel lRefresh = new JLabel(iconRefresh);
    JLabel lHelp = new JLabel(iconHelp);
    JComponent comps[] = new JComponent[] { lRefresh, lHelp };

    TitlePanel titlePanel = new TitlePanel(iconBuy, "List", comps);

    titlePanel.setSeparatorColor(Color.ORANGE);
    titlePanel.setSeparatorThickness(6);
    titlePanel.setBorder(BorderFactory.createLineBorder(Color.RED, 2));

    DefaultListModel model = new DefaultListModel();

    model.addElement("Apple");
    model.addElement("Banana");
    model.addElement("Lemon");
    model.addElement("Orange");
    model.addElement("Pear");
    model.addElement("Tomato");

    JList list = new JList(model);
    titlePanel.add(list);

    return titlePanel;
  }

  public static void main(String[] args) {
    Example2 example = new Example2();
    example.runExample();
  }

}

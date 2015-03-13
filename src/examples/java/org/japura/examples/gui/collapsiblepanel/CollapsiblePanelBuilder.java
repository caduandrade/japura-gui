package org.japura.examples.gui.collapsiblepanel;

import org.japura.examples.gui.ExampleImages;
import org.japura.gui.CollapsiblePanel;
import org.japura.gui.PopupMenuBuilder;

import javax.swing.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class CollapsiblePanelBuilder {

  public static CollapsiblePanel buildFindCollapsiblePanel() {
    URL uFind = ExampleImages.FIND_IMAGE;
    CollapsiblePanel cp = new CollapsiblePanel(new ImageIcon(uFind), "Search");
    cp.add(new JTextField(15));
    return cp;
  }

  public static CollapsiblePanel buildSettingsCollapsiblePanel() {
    URL uTools = ExampleImages.TOOLS_IMAGE;
    CollapsiblePanel cp =
      new CollapsiblePanel(new ImageIcon(uTools), "Settings");
    cp.add(new JScrollPane(new JTextArea(15, 5)));
    return cp;
  }

  public static CollapsiblePanel buildMarketCollapsiblePanel() {
    URL uRefresh = ExampleImages.REFRESH_IMAGE;
    URL uHelp = ExampleImages.HELP_IMAGE;
    URL uBuy = ExampleImages.BUY_IMAGE;

    JLabel lRefresh = new JLabel(new ImageIcon(uRefresh));
    JLabel lHelp = new JLabel(new ImageIcon(uHelp));

    JComponent comps[] = new JComponent[] { lRefresh, lHelp };
    CollapsiblePanel cp =
      new CollapsiblePanel(new ImageIcon(uBuy), "List", comps);

    ArrayList<String> strs = new ArrayList<String>();

    strs.add("Orange");
    strs.add("Lemon");
    strs.add("Tomato");
    strs.add("Apple");
    strs.add("Pear");

    Collections.sort(strs);

    DefaultListModel model = new DefaultListModel();
    for (String str : strs) {
      model.addElement(str);
    }
    JList list = new JList(model);
    list.setVisibleRowCount(5);
    cp.add(new JScrollPane(list));

    cp.setPopupBuilder(new PopupMenuBuilder<CollapsiblePanel>() {
      @Override
      public JPopupMenu buildPopupMenu(CollapsiblePanel source) {
        JPopupMenu menu = new JPopupMenu();
        menu.add(new JMenuItem("teste"));
        return menu;
      }
    });

    return cp;
  }
}

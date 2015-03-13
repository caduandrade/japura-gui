package org.japura.examples.gui.collapsiblepanel;

import org.japura.examples.gui.AbstractExample;
import org.japura.gui.CollapsibleRootPanel;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Component;

public class ScrollCollapsibleRootPanel extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    CollapsibleRootPanel crp = new CollapsibleRootPanel();
    crp.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    crp.add(CollapsiblePanelBuilder.buildFindCollapsiblePanel());
    crp.add(CollapsiblePanelBuilder.buildMarketCollapsiblePanel());
    crp.add(CollapsiblePanelBuilder.buildSettingsCollapsiblePanel());
    return new JScrollPane(crp);
  }

  public static void main(String[] args) {
    ScrollCollapsibleRootPanel mp = new ScrollCollapsibleRootPanel();
    mp.runExample();
    // mp.setSize(230, 525);
    // mp.setSize(230, 325);
  }
}

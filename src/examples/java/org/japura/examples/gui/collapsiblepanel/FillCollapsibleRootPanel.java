package org.japura.examples.gui.collapsiblepanel;

import org.japura.examples.gui.AbstractExample;
import org.japura.gui.CollapsibleRootPanel;

import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Component;

public class FillCollapsibleRootPanel extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    CollapsibleRootPanel crp =
      new CollapsibleRootPanel(CollapsibleRootPanel.FILL);
    crp.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    crp.add(CollapsiblePanelBuilder.buildFindCollapsiblePanel(), 0);
    crp.add(CollapsiblePanelBuilder.buildMarketCollapsiblePanel(), 1);
    crp.add(CollapsiblePanelBuilder.buildSettingsCollapsiblePanel(), 1);
    return crp;
  }

  public static void main(String[] args) {
    FillCollapsibleRootPanel mp = new FillCollapsibleRootPanel();
    // mp.setSize(230, 525);
    // mp.setSize(230, 325);
    mp.runExample();
  }
}

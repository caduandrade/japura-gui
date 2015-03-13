package org.japura.examples.gui.collapsiblepanel;

import org.japura.examples.gui.AbstractExample;
import org.japura.gui.CollapsibleRootPanel;

import java.awt.Component;

public class SimpleCollapsiblePanel extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    CollapsibleRootPanel crp = new CollapsibleRootPanel();

    crp.add(CollapsiblePanelBuilder.buildMarketCollapsiblePanel());
    return crp;
  }

  public static void main(String[] args) {
    SimpleCollapsiblePanel example = new SimpleCollapsiblePanel();
    example.runExample();
  }
}

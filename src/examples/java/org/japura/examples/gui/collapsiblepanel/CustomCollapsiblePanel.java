package org.japura.examples.gui.collapsiblepanel;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.ExampleImages;
import org.japura.gui.CollapsiblePanel;
import org.japura.gui.CollapsibleRootPanel;
import org.japura.gui.Gradient;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;
import java.net.URL;

public class CustomCollapsiblePanel extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    CollapsibleRootPanel crp =
      new CollapsibleRootPanel(CollapsibleRootPanel.FILL);
    crp.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    CollapsiblePanel cp1 = CollapsiblePanelBuilder.buildFindCollapsiblePanel();
    Gradient gradient = (Gradient) CollapsiblePanel.getDefaultTitleBackground();
    gradient =
      new Gradient(Gradient.LEFT_TO_RIGHT, gradient.getFirstColor(),
        gradient.getSecondColor());
    cp1.setTitleBackground(gradient);
    crp.add(cp1, 0);

    CollapsiblePanel cp2 =
      CollapsiblePanelBuilder.buildMarketCollapsiblePanel();
    cp2.setSeparatorColor(Color.ORANGE);
    cp2.setSeparatorThickness(6);
    cp2.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
    crp.add(cp2, 1);

    CollapsiblePanel cp3 =
      CollapsiblePanelBuilder.buildSettingsCollapsiblePanel();
    URL url = ExampleImages.UP_IMAGE;
    cp3.setIcons(new ImageIcon(url), new ImageIcon(url));
    cp3.setTitleBackground(Color.GRAY);
    cp3.setTitleForeground(Color.WHITE);
    crp.add(cp3, 1);
    return crp;
  }

  public static void main(String[] args) {
    CustomCollapsiblePanel c = new CustomCollapsiblePanel();
    c.runExample();
  }
}

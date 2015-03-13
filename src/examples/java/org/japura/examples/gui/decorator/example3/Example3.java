package org.japura.examples.gui.decorator.example3;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.ExampleImages;
import org.japura.gui.Anchor;
import org.japura.gui.Decorator;
import org.japura.gui.Decorator.Direction;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.Component;
import java.awt.Dimension;
import java.net.URL;

public class Example3 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    URL urlRefresh = ExampleImages.REFRESH_IMAGE;
    JButton refreshButton = new JButton(new ImageIcon(urlRefresh));
    refreshButton.setPreferredSize(new Dimension(22, 22));

    URL urlHelp = ExampleImages.HELP_IMAGE;
    JButton helpButton = new JButton(new ImageIcon(urlHelp));
    helpButton.setPreferredSize(new Dimension(22, 22));

    JTextArea field = new JTextArea(7, 20);
    JScrollPane sp = new JScrollPane(field);
    Decorator decorator =
      new Decorator(sp, Anchor.NORTH_EAST, Direction.VERTICAL);

    decorator.addDecoration(helpButton);
    decorator.addDecoration(refreshButton);

    return decorator;
  }

  public static void main(String args[]) {
    Example3 example = new Example3();
    example.runExample();
  }

}

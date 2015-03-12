package org.japura.examples.gui.titlepanel.example3;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.ExampleImages;
import org.japura.gui.TitlePanel;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import java.net.URL;

public class Example3 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
		URL urlFind = ExampleImages.FIND_IMAGE;

      ImageIcon iconFind = new ImageIcon(urlFind);

	TitlePanel titlePanel = new TitlePanel(iconFind, "Search");
	titlePanel.setTitleBackground(Color.LIGHT_GRAY);
	titlePanel.setSeparatorColor(Color.BLACK);
	titlePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

	JTextField field = new JTextField(15);
	field.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
	titlePanel.add(field);

	return titlePanel;
  }

  public static void main(String[] args) {
	Example3 example = new Example3();
	example.runExample();
  }

}

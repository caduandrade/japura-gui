package org.japura.examples.gui.splitbutton.example4;

import java.awt.Component;

import org.japura.examples.gui.AbstractExample;
import org.japura.gui.SplitButton;

public class Example4 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
	SplitButton button = new SplitButton();
	button.addButton("Search");
	button.addButton("Search adding");
	button.addButton("Search removing");

	button.setButtonVisible("Search removing", false);

	return button;
  }

  public static void main(String[] a) {
	Example4 example = new Example4();
	example.runExample();
  }

}

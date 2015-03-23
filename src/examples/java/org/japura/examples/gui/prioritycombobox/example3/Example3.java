package org.japura.examples.gui.prioritycombobox.example3;

import org.japura.examples.gui.AbstractExample;
import org.japura.gui.PriorityComboBox;
import org.japura.gui.model.PriorityComboBoxModel;

import java.awt.Component;

public class Example3 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
	PriorityComboBox combo = new PriorityComboBox();

	PriorityComboBoxModel model = combo.getModel();

	model.addElement("BLUE", true);
	model.addElement("WHITE");
	model.addElement("BLACK");
	model.addElement("YELLOW");
	model.addElement("RED");

	// default value is 10
	model.setMinimalSizeForPriority(5);
	return combo;
  }

  public static void main(String a[]) {
	Example3 example = new Example3();
	example.runExample();
  }

}

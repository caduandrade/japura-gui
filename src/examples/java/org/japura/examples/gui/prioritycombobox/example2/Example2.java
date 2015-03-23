package org.japura.examples.gui.prioritycombobox.example2;

import java.awt.Component;
import java.util.List;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.Country;
import org.japura.gui.PriorityComboBox;
import org.japura.gui.model.PriorityComboBoxModel;

public class Example2 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
	PriorityComboBox combo = new PriorityComboBox();

	PriorityComboBoxModel model = combo.getModel();

	List<String> countries = Country.getCountries();
	for (String country : countries) {
	  model.addElement(country);
	}

	model.setIncreasePriorityOnSelection(false);
	model.addToPriority("Brazil");
	model.addToPriority("Italy");

	return combo;
  }

  public static void main(String a[]) {
	Example2 example = new Example2();
	example.runExample();
  }

}

package org.japura.examples.gui.checkcombobox.example5;

import org.japura.examples.Country;
import org.japura.examples.gui.AbstractExample;
import org.japura.gui.CheckComboBox;
import org.japura.gui.model.ListCheckModel;

import java.awt.Component;
import java.util.List;

public class Example5 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
	List<String> countries = Country.getCountries();

	CheckComboBox ccb = new CheckComboBox();

	ListCheckModel model = ccb.getModel();
	for (String country : countries) {
	  model.addElement(country);
	}

	model.addCheck("Canada", "Cape Verde");
	model.addLock("Canada", "Chile");

	System.out.println("Checkeds: " + model.getChecksCount());
	for (Object checked : model.getCheckeds()) {
	  System.out.println(checked);
	}

	System.out.println("Lockeds: " + model.getLocksCount());
	for (Object locked : model.getLockeds()) {
	  System.out.println(locked);
	}

	return ccb;
  }

  public static void main(String[] args) {
	Example5 example = new Example5();
	example.runExample();
  }

}

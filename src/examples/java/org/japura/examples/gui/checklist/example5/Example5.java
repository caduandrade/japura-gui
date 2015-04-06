package org.japura.examples.gui.checklist.example5;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.CountryNames;
import org.japura.gui.CheckList;
import org.japura.gui.model.DefaultListCheckModel;
import org.japura.gui.model.ListCheckModel;

import javax.swing.JScrollPane;
import java.awt.Component;
import java.util.List;

public class Example5 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
	List<String> countries = CountryNames.getCountries();

	CheckList checkList = new CheckList();
	checkList.setDragEnabled(true);

	ListCheckModel model = new DefaultListCheckModel();
	for (String country : countries) {
	  model.addElement(country);
	}

	checkList.setModel(model);

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

	return new JScrollPane(checkList);
  }

  public static void main(String[] args) {
	Example5 example = new Example5();
	example.runExample();
  }

}

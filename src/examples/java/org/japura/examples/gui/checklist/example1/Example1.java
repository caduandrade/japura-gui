package org.japura.examples.gui.checklist.example1;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.CountryNames;
import org.japura.gui.CheckList;
import org.japura.gui.model.DefaultListCheckModel;
import org.japura.gui.model.ListCheckModel;

import javax.swing.JScrollPane;
import java.awt.Component;
import java.util.List;

public class Example1 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
	List<String> countries = CountryNames.getCountries();

	CheckList checkList = new CheckList();

	ListCheckModel model = new DefaultListCheckModel();
	for (String country : countries) {
	  model.addElement(country);
	}

	model.addCheck("Anguilla");
	model.addCheck("Algeria");

	model.addLock("Algeria");
	model.addLock("Albania");

	checkList.setModel(model);

	return new JScrollPane(checkList);
  }

  public static void main(String[] args) {
	Example1 example = new Example1();
	example.runExample();
  }

}

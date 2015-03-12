package org.japura.examples.gui.checklist.example2;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.Country;
import org.japura.gui.CheckList;
import org.japura.gui.model.DefaultListCheckModel;
import org.japura.gui.model.ListCheckModel;
import org.japura.gui.renderer.CheckListRenderer;

import javax.swing.JScrollPane;
import java.awt.Component;
import java.util.List;

public class Example2 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
	List<String> countries = Country.getCountries();

	CheckList checkList = new CheckList();

	ListCheckModel model = new DefaultListCheckModel();
	for (String country : countries) {
	  model.addElement(country);
	}

	checkList.setModel(model);

	checkList.setCellRenderer(new CheckListRenderer() {
	  @Override
	  public String getText(Object value) {
		String country = (String) value;
		return country.toUpperCase();
	  }
	});

	return new JScrollPane(checkList);
  }

  public static void main(String[] args) {
	Example2 example = new Example2();
	example.runExample();
  }

}

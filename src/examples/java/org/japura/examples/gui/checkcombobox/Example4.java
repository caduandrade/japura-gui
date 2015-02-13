package org.japura.examples.gui.checkcombobox;

import org.japura.examples.Country;
import org.japura.examples.gui.AbstractExample;
import org.japura.gui.CheckComboBox;
import org.japura.gui.event.ListCheckListener;
import org.japura.gui.event.ListEvent;
import org.japura.gui.event.ListLockListener;
import org.japura.gui.model.ListCheckModel;

import java.awt.Component;
import java.util.List;

public class Example4 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
	List<String> countries = Country.getCountries();

	CheckComboBox ccb = new CheckComboBox();
	
	ListCheckModel model = ccb.getModel();
	for (String country : countries) {
	  model.addElement(country);
	}

	model.addListLockListener(new ListLockListener() {
	  @Override
	  public void removeLock(ListEvent event) {
		System.out.println("Lock removed");
	  }

	  @Override
	  public void addLock(ListEvent event) {
		System.out.println("Lock added");
	  }
	});

	model.addListCheckListener(new ListCheckListener() {
	  @Override
	  public void removeCheck(ListEvent event) {
		System.out.println("Check removed");
	  }

	  @Override
	  public void addCheck(ListEvent event) {
		System.out.println("Check added");
	  }
	});

	return ccb;
  }

  public static void main(String[] args) {
	Example4 example = new Example4();
	example.runExample();
  }

}

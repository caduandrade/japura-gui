package org.japura.examples.gui.checklist.example4;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.Country;
import org.japura.gui.CheckList;
import org.japura.gui.event.ListCheckListener;
import org.japura.gui.event.ListEvent;
import org.japura.gui.event.ListLockListener;
import org.japura.gui.model.DefaultListCheckModel;
import org.japura.gui.model.ListCheckModel;

import javax.swing.JScrollPane;
import java.awt.Component;
import java.util.List;

public class Example4 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
	List<String> countries = Country.getCountries();

	CheckList checkList = new CheckList();

	ListCheckModel model = new DefaultListCheckModel();
	for (String country : countries) {
	  model.addElement(country);
	}

	checkList.setModel(model);

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

	return new JScrollPane(checkList);
  }

  public static void main(String[] args) {
	Example4 example = new Example4();
	example.runExample();
  }

}

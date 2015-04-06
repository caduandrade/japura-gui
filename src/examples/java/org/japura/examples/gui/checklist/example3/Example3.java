package org.japura.examples.gui.checklist.example3;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.CountryNames;
import org.japura.gui.CheckList;
import org.japura.gui.PopupMenuBuilder;
import org.japura.gui.model.DefaultListCheckModel;
import org.japura.gui.model.ListCheckModel;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Example3 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
	List<String> countries = CountryNames.getCountries();

	CheckList checkList = new CheckList();

	ListCheckModel model = new DefaultListCheckModel();
	for (String country : countries) {
	  model.addElement(country);
	}

	checkList.setModel(model);

	checkList.setPopupMenuBuilder(new PopupMenuBuilder<CheckList>() {
	  @Override
	  public JPopupMenu buildPopupMenu(final CheckList source) {
		JPopupMenu menu = new JPopupMenu();

		JMenuItem selectAll = new JMenuItem("select all");
		selectAll.addActionListener(new ActionListener() {
		  @Override
		  public void actionPerformed(ActionEvent e) {
			source.getModel().checkAll();
		  }
		});

		menu.add(selectAll);
		return menu;
	  }
	});

	return new JScrollPane(checkList);
  }

  public static void main(String[] args) {
	Example3 example = new Example3();
	example.runExample();
  }

}

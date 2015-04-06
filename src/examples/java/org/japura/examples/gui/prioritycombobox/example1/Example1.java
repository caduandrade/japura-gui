package org.japura.examples.gui.prioritycombobox.example1;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.CountryNames;
import org.japura.gui.PriorityComboBox;
import org.japura.gui.model.PriorityComboBoxModel;

import java.awt.Component;
import java.util.List;

public class Example1 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    PriorityComboBox combo = new PriorityComboBox();

    PriorityComboBoxModel model = combo.getModel();

    List<String> countries = CountryNames.getCountries();
    for (String country : countries) {
      model.addElement(country);
    }

    return combo;
  }

  public static void main(String a[]) {
    Example1 example = new Example1();
    example.runExample();
  }

}

package org.japura.examples.gui.checkcombobox;

import org.japura.examples.Country;
import org.japura.examples.gui.AbstractExample;
import org.japura.gui.CheckComboBox;
import org.japura.gui.model.ListCheckModel;

import java.awt.Component;
import java.util.List;

public class Example2 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    List<String> countries = Country.getCountries();

    CheckComboBox ccb = new CheckComboBox();

    ListCheckModel model = ccb.getModel();
    for (String country : countries) {
      model.addElement(country);
    }

    model.addLock("Australia");

    return ccb;
  }

  public static void main(String[] args) {
    Example2 example = new Example2();
    example.runExample();
  }

}

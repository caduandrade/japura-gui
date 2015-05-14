package org.japura.examples.gui.dropdownfilteredlist.example4;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.CountryNames;
import org.japura.gui.DropDownFilteredList;

import javax.swing.JTextField;
import java.awt.Component;
import java.util.List;

public class Example4 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    List<String> list = CountryNames.getCountries();

    JTextField field = new JTextField(20);
    DropDownFilteredList ddfl = new DropDownFilteredList<String>(field, list);

    ddfl.setFilter(new DropDownFilteredList.Filter() {
      @Override
      public boolean matches(String itemText, String typedText) {
        return itemText.equals("brazil");
      }
    });

    return field;
  }

  public static void main(String args[]) {
    Example4 example = new Example4();
    example.runExample();
  }

}

package org.japura.examples.gui.dropdownfilteredlist.example3;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.CountryNames;
import org.japura.gui.DropDownFilteredList;
import org.japura.gui.ValueToString;

import javax.swing.JTextField;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

public class Example3 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    List<Country> list = new ArrayList<>();
    for (String name : CountryNames.getCountries()) {
      list.add(new Country(name));
    }

    JTextField field = new JTextField(20);

    DropDownFilteredList<Country> dd = new DropDownFilteredList(field, list);

    dd.setValueToString(new ValueToString<Country>() {
      @Override
      public String valueToString(Country value) {
        return value.getName();
      }
    });

    return field;
  }

  public static class Country {
    private final String name;

    public Country(String name) {
      this.name = name;
    }

    public String getName() {
      return name;
    }
  }

  public static void main(String args[]) {
    Example3 example = new Example3();
    example.runExample();
  }

}

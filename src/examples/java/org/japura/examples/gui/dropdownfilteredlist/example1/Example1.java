package org.japura.examples.gui.dropdownfilteredlist.example1;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.CountryNames;
import org.japura.gui.DropDownFilteredList;

import javax.swing.JTextField;
import java.awt.Component;
import java.util.List;

public class Example1 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    List<String> list = CountryNames.getCountries();

    JTextField field = new JTextField(20);
    new DropDownFilteredList(field, list);

    return field;
  }

  public static void main(String args[]) {
    Example1 example = new Example1();
    example.runExample();
  }

}

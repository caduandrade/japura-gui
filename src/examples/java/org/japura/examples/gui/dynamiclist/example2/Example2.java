package org.japura.examples.gui.dynamiclist.example2;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.Country;
import org.japura.examples.gui.ExampleImages;
import org.japura.gui.ButtonTextField;
import org.japura.gui.DynamicList;

import java.awt.Component;
import java.net.URL;
import java.util.List;

public class Example2 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    URL uFind = ExampleImages.FIND_IMAGE;
    ButtonTextField field = new ButtonTextField(10);
    field.addButton("Find", uFind, null);
    field.setDropDownVisible(false);

    List<String> list = Country.getCountries();
    new DynamicList<String>(field, list);

    return field;
  }

  public static void main(String args[]) {
    Example2 example = new Example2();
    example.runExample();
  }

}

package org.japura.examples.gui.checkcombobox.example1;

import org.japura.examples.Colors;
import org.japura.examples.gui.AbstractExample;
import org.japura.gui.CheckComboBox;
import org.japura.gui.model.ListCheckModel;

import java.awt.Component;
import java.util.List;

public class Example1 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    List<String> colors = Colors.getColors();

    CheckComboBox ccb = new CheckComboBox();
    ccb.setTextFor(CheckComboBox.NONE, "* any item selected *");
    ccb.setTextFor(CheckComboBox.MULTIPLE, "* multiple items *");
    ccb.setTextFor(CheckComboBox.ALL, "* all selected *");

    ListCheckModel model = ccb.getModel();
    for (String color : colors) {
      model.addElement(color);
    }

    return ccb;
  }

  public static void main(String[] args) {
    Example1 example = new Example1();
    example.runExample();
  }

}

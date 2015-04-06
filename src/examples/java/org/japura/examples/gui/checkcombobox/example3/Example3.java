package org.japura.examples.gui.checkcombobox.example3;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.CountryNames;
import org.japura.gui.CheckComboBox;
import org.japura.gui.model.ListCheckModel;
import org.japura.gui.renderer.CheckListRenderer;

import javax.swing.JList;
import java.awt.Color;
import java.awt.Component;
import java.util.List;

public class Example3 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    List<String> countries = CountryNames.getCountries();

    CheckComboBox ccb = new CheckComboBox();

    ListCheckModel model = ccb.getModel();
    for (String country : countries) {
      model.addElement(country);
    }

    ccb.setRenderer(new CheckListRenderer() {
      @Override
      public String getText(Object value) {
        String str = (String) value;
        if (str.equals("Australia")) {
          return str + "*";
        }
        return str;
      }

      @Override
      public Component getListCellRendererComponent(JList list, Object value,
        int index, boolean isSelected, boolean cellHasFocus) {
        Component c =
          super.getListCellRendererComponent(list, value, index, isSelected,
            cellHasFocus);

        String str = (String) value;
        if (str.equals("Australia")) {
          c.setForeground(Color.RED);
        }
        else {
          c.setForeground(Color.BLACK);
        }

        // changing default mouse over colors
        if (isSelected) {
          setBackground(Color.BLACK);
          setForeground(Color.WHITE);
        }
        return c;
      }
    });

    return ccb;
  }

  public static void main(String[] args) {
    Example3 example = new Example3();
    example.runExample();
  }

}

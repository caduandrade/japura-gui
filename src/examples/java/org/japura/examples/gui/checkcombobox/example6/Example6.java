package org.japura.examples.gui.checkcombobox.example6;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.CountryNames;
import org.japura.gui.BatchSelection;
import org.japura.gui.CheckComboBox;
import org.japura.gui.EmbeddedComponent;
import org.japura.gui.i18n.DefaultGUIHandlerString;
import org.japura.gui.model.ListCheckModel;
import org.japura.util.i18n.I18nAdapter;

import javax.swing.JPanel;
import java.awt.Component;
import java.util.List;

public class Example6 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {

    List<String> countries = CountryNames.getCountries();

    CheckComboBox ccb = new CheckComboBox();

    ListCheckModel model = ccb.getModel();
    for (String color : countries) {
      model.addElement(color);
    }

    BatchSelection bs = new BatchSelection.Link();
    EmbeddedComponent comp = new EmbeddedComponent(bs);
    ccb.setEmbeddedComponent(comp);

    JPanel panel = new JPanel();
    panel.setOpaque(false);
    panel.add(ccb);
    return panel;
  }

  public static void main(String[] args) {
    I18nAdapter.getAdapter().registerHandler(new DefaultGUIHandlerString());
    Example6 example = new Example6();
    example.runExample();
  }

}

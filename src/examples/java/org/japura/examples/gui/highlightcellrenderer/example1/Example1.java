package org.japura.examples.gui.highlightcellrenderer.example1;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.CountryNames;
import org.japura.gui.renderer.HighlightCellRenderer;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.util.List;

public class Example1 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    JList list = new JList();
    DefaultListModel model = new DefaultListModel();
    List<String> countries = CountryNames.getCountries();
    for (String country : countries) {
      model.addElement(country);
    }
    list.setModel(model);
    HighlightCellRenderer renderer = new HighlightCellRenderer(false);
    renderer.setHighlightText("a");
    list.setCellRenderer(renderer);
    return new JScrollPane(list);
  }

  public static void main(String args[]) {
    Example1 example = new Example1();
    example.runExample();
  }

}

package org.japura.examples.gui.buttontextfield.example2;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.ExampleUtil;
import org.japura.gui.ButtonTextField;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Example2 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    URL urlSortIncrease = ExampleUtil.SORT_INCREASE_IMAGE;
    URL urlSortDecrease = ExampleUtil.SORT_DECREASE_IMAGE;

    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // action
      }
    };

    ButtonTextField field = new ButtonTextField(10);
    field.addButton("Increase", urlSortIncrease, actionListener);
    field.addButton("Decrease", urlSortDecrease, actionListener);
    field.setSelectedDropDownIconVisible(false);

    return field;
  }

  public static void main(String args[]) {
    Example2 example = new Example2();
    example.runExample();
  }

}

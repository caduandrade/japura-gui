package org.japura.examples.gui.buttontextfield.example1;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.ExampleUtil;
import org.japura.gui.ButtonTextField;
import org.japura.gui.event.ButtonTextFieldEvent;
import org.japura.gui.event.ButtonTextFieldListener;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Example1 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    URL urlSortIncrease = ExampleUtil.SORT_INCREASE_IMAGE;
    URL urlSortDecrease = ExampleUtil.SORT_DECREASE_IMAGE;
    URL urlFind = ExampleUtil.FIND_IMAGE;

    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // action
      }
    };

    ButtonTextField field = new ButtonTextField(10);
    field.addButton("Increase", urlSortIncrease, actionListener);
    field.addButton("Decrease", urlSortDecrease, actionListener);
    field.setFixedIcon(urlFind);

    field.addButtonTextFieldListener(new ButtonTextFieldListener() {
      @Override
      public void buttonChanged(ButtonTextFieldEvent event) {
        System.out.println(event.getOldButtonName() + " "
          + event.getNewButtonName());
      }
    });

    return field;
  }

  public static void main(String args[]) {
    Example1 example = new Example1();
    example.runExample();
  }

}

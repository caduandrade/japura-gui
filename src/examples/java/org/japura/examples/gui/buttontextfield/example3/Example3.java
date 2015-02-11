package org.japura.examples.gui.buttontextfield.example3;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.ExampleUtil;
import org.japura.gui.ButtonTextField;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Example3 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    URL urlFind = ExampleUtil.FIND_IMAGE;

    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // action
      }
    };

    ButtonTextField field = new ButtonTextField(10);
    field.addButton("Find", urlFind, actionListener);
    field.setDropDownVisible(false);

    return field;
  }

  public static void main(String args[]) {
    Example3 example = new Example3();
    example.runExample();
  }

}

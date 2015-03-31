package org.japura.examples.gui.buttontextfield.example1;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.ExampleImages;
import org.japura.gui.ButtonTextField;
import org.japura.gui.event.ButtonTextFieldEvent;
import org.japura.gui.event.ButtonTextFieldListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Example1 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    Icon sortIncreaseIcon = new ImageIcon(ExampleImages.SORT_INCREASE_IMAGE);
    Icon sortDecreaseIcon = new ImageIcon(ExampleImages.SORT_DECREASE_IMAGE);
    Icon findIcon = new ImageIcon(ExampleImages.FIND_IMAGE);

    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // action
      }
    };

    ButtonTextField field = new ButtonTextField(10);
    field.addButton("Increase", sortIncreaseIcon, actionListener);
    field.addButton("Decrease", sortDecreaseIcon, actionListener);
    field.setFixedIcon(findIcon);

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

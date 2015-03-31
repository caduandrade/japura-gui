package org.japura.examples.gui.buttontextfield.example2;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.ExampleImages;
import org.japura.gui.ButtonTextField;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Example2 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    Icon sortIncreaseIcon = new ImageIcon(ExampleImages.SORT_INCREASE_IMAGE);
    Icon sortDecreaseIcon = new ImageIcon(ExampleImages.SORT_DECREASE_IMAGE);

    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // action
      }
    };

    ButtonTextField field = new ButtonTextField(10);
    field.addButton("Increase", sortIncreaseIcon, actionListener);
    field.addButton("Decrease", sortDecreaseIcon, actionListener);
    field.setSelectedDropDownIconVisible(false);

    return field;
  }

  public static void main(String args[]) {
    Example2 example = new Example2();
    example.runExample();
  }

}

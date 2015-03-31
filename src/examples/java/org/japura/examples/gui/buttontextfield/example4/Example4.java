package org.japura.examples.gui.buttontextfield.example4;

import org.japura.examples.gui.AbstractExample;
import org.japura.examples.gui.ExampleImages;
import org.japura.gui.ButtonTextField;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Example4 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    Icon keyIcon = new ImageIcon(ExampleImages.KEY_IMAGE);

    ActionListener actionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        // action
      }
    };

    JPasswordField pf = new JPasswordField(10);
    ButtonTextField field = new ButtonTextField(pf);
    field.addButton("Login", keyIcon, actionListener);
    field.setDropDownVisible(false);

    return field;
  }

  public static void main(String args[]) {
    Example4 example = new Example4();
    example.runExample();
  }

}

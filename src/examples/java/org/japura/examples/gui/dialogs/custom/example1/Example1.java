package org.japura.examples.gui.dialogs.custom.example1;

import org.japura.examples.gui.ExampleImages;
import org.japura.examples.gui.dialogs.DialogTexts;
import org.japura.gui.LabelSeparator;
import org.japura.gui.dialogs.CustomDialog;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Example1 {

  public static void main(String args[]) {
    URL url = ExampleImages.GLOBE_IMAGE;
    String title = DialogTexts.TITLE;
    String message = DialogTexts.MESSAGE;

    CustomDialog dialog = new CustomDialog(title, message);

    int button1Index = dialog.addButton("button 1");
    int button2Index = dialog.addButton("button 2");

    dialog.addContent(new LabelSeparator("separator"));
    dialog.addContent(new JCheckBox("checkbox"));
    dialog.addMessageBlock("second message block");

    dialog.addButtonAction(button1Index, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("action 1");
      }
    });
    dialog.addButtonAction(button2Index, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("action 2");
      }
    });

    dialog.setDialogIcon(new ImageIcon(url));
    dialog.setMessageIcon(new ImageIcon(url));

    dialog.show();
  }

}

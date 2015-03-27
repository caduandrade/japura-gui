package org.japura.examples.gui.dialogs.custom.example1;

import org.japura.examples.gui.ExampleImages;
import org.japura.examples.gui.dialogs.DialogTexts;
import org.japura.gui.LabelSeparator;
import org.japura.gui.dialogs.CustomDialog;

import javax.swing.ImageIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Example1 {

  public static void main(String args[]) {
    URL url = ExampleImages.GLOBE_IMAGE;
    String title = DialogTexts.TITLE;
    String message = DialogTexts.MESSAGE;

    CustomDialog dialog = new CustomDialog(title, message);

    int buttonIndex = dialog.addButton("button");

    dialog.addContent(new LabelSeparator("separator"));
    dialog.addMessageBlock("second message block");

    dialog.addButtonAction(buttonIndex, new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("action");
      }
    });

    dialog.setDialogIcon(new ImageIcon(url));
    dialog.setMessageIcon(new ImageIcon(url));

    dialog.show();
  }

}

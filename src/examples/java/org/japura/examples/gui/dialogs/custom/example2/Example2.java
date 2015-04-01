package org.japura.examples.gui.dialogs.custom.example2;

import org.japura.examples.gui.dialogs.DialogTexts;
import org.japura.gui.dialogs.CustomDialog;

public class Example2 {

  public static void main(String args[]) {
    String title = DialogTexts.TITLE;
    String message = DialogTexts.MESSAGE;

    CustomDialog dialog = new CustomDialog(title, message);

    int button1Index = dialog.addButton("button 1");
    dialog.addButton("button 2");

    dialog.setDefaultButtonForDispose(button1Index);

    Integer result = dialog.show();
    System.out.println(result);
  }

}

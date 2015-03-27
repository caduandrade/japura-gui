package org.japura.examples.gui.dialogs.information.example4;

import org.japura.examples.gui.ExampleImages;
import org.japura.gui.dialogs.InformationDialog;

import javax.swing.ImageIcon;
import java.net.URL;

public class Example4 {

  public static void main(String args[]) {
    URL dialogIconURL = ExampleImages.CHECK_IMAGE;
    URL messageIconURL = ExampleImages.BUY_IMAGE;

    String title = "Buy";
    String message = "Thank you for purchasing";

    InformationDialog dialog = new InformationDialog(title, message);

    dialog.setButtonText("OK");
    dialog.setDialogIcon(new ImageIcon(dialogIconURL));
    dialog.setMessageIcon(new ImageIcon(messageIconURL));

    dialog.show();
  }

}

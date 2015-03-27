package org.japura.examples.gui.dialogs.information.example5;

import org.japura.examples.gui.ExampleImages;
import org.japura.gui.dialogs.InformationDialog;
import org.japura.gui.i18n.DefaultGUIHandlerString;
import org.japura.util.i18n.I18nAdapter;

import javax.swing.ImageIcon;
import java.net.URL;

public class Example5 {

  public static void main(String args[]) {
    URL dialogIconURL = ExampleImages.CHECK_IMAGE;
    URL messageIconURL = ExampleImages.BUY_IMAGE;

    String title = "Buy";
    String message = "Thank you for purchasing";

    // default text of the buttons
    I18nAdapter.getAdapter().registerHandler(new DefaultGUIHandlerString());

    // changing the default icons for all dialogs
    InformationDialog.setDefaultDialogIcon(new ImageIcon(dialogIconURL));
    InformationDialog.setDefaultMessageIcon(new ImageIcon(messageIconURL));

    InformationDialog.show(title, message);
  }

}

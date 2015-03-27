package org.japura.examples.gui.dialogs.information.example6;

import org.japura.examples.gui.dialogs.DialogTexts;
import org.japura.gui.dialogs.InformationDialog;
import org.japura.gui.i18n.DefaultGUIHandlerString;
import org.japura.util.i18n.I18nAdapter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Example6 {

  public static void main(String args[]) {
    String title = DialogTexts.TITLE;
    String message = DialogTexts.MESSAGE;

    // default text of the buttons
    I18nAdapter.getAdapter().registerHandler(new DefaultGUIHandlerString());

    InformationDialog dialog = new InformationDialog(title, message);
    dialog.addButtonAction(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("action!");
      }
    });
    dialog.show();
  }

}

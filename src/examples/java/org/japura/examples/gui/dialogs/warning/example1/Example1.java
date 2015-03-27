package org.japura.examples.gui.dialogs.warning.example1;

import org.japura.examples.gui.dialogs.DialogTexts;
import org.japura.gui.dialogs.WarningDialog;
import org.japura.gui.i18n.DefaultGUIHandlerString;
import org.japura.util.i18n.I18nAdapter;

public class Example1 {

  public static void main(String args[]) {
    String title = DialogTexts.TITLE;
    String message = DialogTexts.MESSAGE;

    // default text buttons
    I18nAdapter.getAdapter().registerHandler(new DefaultGUIHandlerString());

    WarningDialog.show(title, message);
  }

}
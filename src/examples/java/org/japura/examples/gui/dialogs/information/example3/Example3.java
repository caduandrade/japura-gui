package org.japura.examples.gui.dialogs.information.example3;

import org.japura.examples.gui.dialogs.DialogTexts;
import org.japura.gui.dialogs.InformationDialog;
import org.japura.gui.i18n.DefaultGUIHandlerString;
import org.japura.gui.i18n.GUIStringKeys;
import org.japura.util.i18n.HandlerString;
import org.japura.util.i18n.I18nAdapter;

public class Example3 {

  public static void main(String args[]) {
    String title = DialogTexts.TITLE;
    String message = DialogTexts.MESSAGE;

    // changing the default text buttons
    I18nAdapter.getAdapter().registerHandler(new HandlerString() {

      private HandlerString handlerString = new DefaultGUIHandlerString();

      @Override
      public String getString(String key) {
        if (key == null) {
          return null;
        }
        if (GUIStringKeys.YES.getKey().equals(key)) {
          return "Confirm";
        }
        if (GUIStringKeys.NO.getKey().equals(key)) {
          return "Cancel";
        }
        if (GUIStringKeys.CLOSE.getKey().equals(key)) {
          return "OK";
        }

        return handlerString.getString(key);
      }
    });

    InformationDialog.show(title, message);
  }
}

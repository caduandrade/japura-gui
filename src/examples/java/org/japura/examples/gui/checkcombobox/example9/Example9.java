package org.japura.examples.gui.checkcombobox.example9;

import org.japura.gui.i18n.DefaultGUIHandlerString;
import org.japura.gui.i18n.GUIStringKeys;
import org.japura.util.i18n.I18nAdapter;

public class Example9 {

  public static void main(String[] args) {

    I18nAdapter.getAdapter().registerHandler(new DefaultGUIHandlerString() {
      @Override
      public String getString(String key) {
        if (key.equals(GUIStringKeys.SELECT_ALL.getKey())) {
          return "[ SELECT ALL ]";
        }
        else if (key.equals(GUIStringKeys.DESELECT_ALL.getKey())) {
          return "[ DESELECT ALL ]";
        }
        else if (key.equals(GUIStringKeys.SELECT_DESELECT_ALL.getKey())) {
          return "[ SELECT / DESELECT ALL ]";
        }
        return super.getString(key);
      }
    });

  }

}

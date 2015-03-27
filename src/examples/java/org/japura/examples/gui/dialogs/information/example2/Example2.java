package org.japura.examples.gui.dialogs.information.example2;

import org.japura.examples.gui.dialogs.DialogTexts;
import org.japura.gui.LabelSeparator;
import org.japura.gui.dialogs.InformationDialog;
import org.japura.gui.i18n.DefaultGUIHandlerString;
import org.japura.util.i18n.I18nAdapter;

public class Example2 {

  public static void main(String args[]) {
    String title = DialogTexts.TITLE;
    String message = DialogTexts.TITLE;
    String detailedMessage = DialogTexts.MESSAGE;

    // default text of the buttons
    I18nAdapter.getAdapter().registerHandler(new DefaultGUIHandlerString());

    InformationDialog dialog = new InformationDialog(title, message);
    dialog.addContent(new LabelSeparator("Details"));
    dialog.addMessageBlock(detailedMessage);
    dialog.show();
  }

}

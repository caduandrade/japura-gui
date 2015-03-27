package org.japura.examples.gui.dialogs.question.example2;

import org.japura.gui.dialogs.QuestionButton;
import org.japura.gui.dialogs.QuestionDialog;
import org.japura.gui.dialogs.QuestionDialogResult;

import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Example2 {

  public static void main(String args[]) {
    String title = "Rename";
    String message = "Enter the name";
    final QuestionDialog dialog = new QuestionDialog(title, message);

    final JTextField field = new JTextField();
    field.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        boolean empty = (field.getText().length() == 0);
        dialog.setButtonEnabled(QuestionButton.YES, !empty);
      }
    });
    dialog.addContent(field);

    dialog.setButtonText(QuestionButton.NO, "Cancel");

    dialog.setButtonText(QuestionButton.YES, "Confirm");
    dialog.setButtonEnabled(QuestionButton.YES, false);

    QuestionDialogResult result = dialog.show();
    String text = field.getText();
    System.out.println(result);
    System.out.println(text);
  }

}

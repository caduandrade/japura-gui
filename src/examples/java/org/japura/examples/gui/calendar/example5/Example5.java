package org.japura.examples.gui.calendar.example5;

import org.japura.examples.gui.AbstractExample;
import org.japura.gui.PopupMenuBuilder;
import org.japura.gui.calendar.Calendar;
import org.japura.gui.calendar.CalendarComponent;
import org.japura.gui.calendar.CalendarComponentType;
import org.japura.gui.calendar.DayOfWeek;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Example5 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    Calendar calendar = new Calendar(Locale.ENGLISH);

    GregorianCalendar gc = new GregorianCalendar();
    gc.set(GregorianCalendar.YEAR, 2011);
    gc.set(GregorianCalendar.MONTH, 0);
    gc.set(GregorianCalendar.DAY_OF_MONTH, 20);

    calendar.setDate(gc.getTimeInMillis());

    calendar.setPopupMenuBuilder(new PopupMenuBuilder<CalendarComponent>() {
      @Override
      public JPopupMenu buildPopupMenu(CalendarComponent source) {
        Calendar calendar = source.getCalendar();
        DayOfWeek dow = calendar.getDayOfWeek(source);
        System.out.println(dow);

        CalendarComponentType type = source.getType();

        if (type.equals(CalendarComponentType.DAY_MONTH)) {
          JPopupMenu menu = new JPopupMenu();

          final Date date = calendar.getDate(source);

          JMenuItem mi = new JMenuItem();
          mi.setText("Events");
          mi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              showEvents(date);
            }
          });

          menu.add(mi);
          return menu;
        }

        return null;
      }
    });
    return calendar;
  }

  public void showEvents(Date date) {
    System.out.println(date);
  }

  public static void main(String[] args) {
    Example5 example = new Example5();
    example.runExample();
  }

}

package org.japura.examples.gui.calendar.example3;

import org.japura.examples.gui.AbstractExample;
import org.japura.gui.calendar.*;

import java.awt.Color;
import java.awt.Component;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Example3 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    Calendar calendar = new Calendar(Locale.ENGLISH);

    GregorianCalendar gc = new GregorianCalendar();
    gc.set(GregorianCalendar.YEAR, 2011);
    gc.set(GregorianCalendar.MONTH, 0);
    gc.set(GregorianCalendar.DAY_OF_MONTH, 20);

    PropertiesProvider pp = calendar.getPropertiesProvider();
    DefaultPropertiesProvider dpp = (DefaultPropertiesProvider) pp;
    CalendarProperties cp = dpp.getProperties();
    cp.setTopBarBackground(Color.BLACK);
    cp.setButtonColor(Color.WHITE);
    cp.setMonthForeground(Color.WHITE);
    cp.setYearForeground(Color.WHITE);

    for (DayOfWeek dow : DayOfWeek.values()) {
      cp.setDayOfWeekBackground(dow, new Color(70, 70, 70));
    }
    cp.setDayOfWeekForeground(DayOfWeek.SATURDAY, Color.LIGHT_GRAY);
    cp.setDayOfWeekForeground(DayOfWeek.SUNDAY, Color.LIGHT_GRAY);

    cp.setTopDayOfWeekSeparatorColor(Color.GRAY);
    cp.setBottomDayOfWeekSeparatorColor(Color.LIGHT_GRAY);

    cp.setDayOfMonthBackground(new Color(100, 100, 100));
    cp.setDayOfMonthForeground(Color.WHITE);
    cp.setDayOfNonCurrentMonthBackground(new Color(100, 100, 100));

    cp.setSelectedDayOfMonthBackground(Color.LIGHT_GRAY);

    calendar.update();

    calendar.setDate(gc.getTimeInMillis());
    return calendar;
  }

  public static void main(String[] args) {
    Example3 example = new Example3();
    example.runExample();
  }

}

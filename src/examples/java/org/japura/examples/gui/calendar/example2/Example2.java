package org.japura.examples.gui.calendar.example2;

import org.japura.examples.gui.AbstractExample;
import org.japura.gui.calendar.Calendar;
import org.japura.gui.calendar.DayOfWeek;
import org.japura.gui.calendar.DefaultPropertiesProvider;
import org.japura.gui.calendar.PropertiesProvider;

import java.awt.Component;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Example2 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    Calendar calendar = new Calendar(Locale.ENGLISH);

    PropertiesProvider pp = calendar.getPropertiesProvider();
    DefaultPropertiesProvider dpp = (DefaultPropertiesProvider) pp;
    dpp.getProperties().setStartDayOfWeek(DayOfWeek.SUNDAY);
    calendar.update();

    GregorianCalendar gc = new GregorianCalendar();
    gc.set(GregorianCalendar.YEAR, 2011);
    gc.set(GregorianCalendar.MONTH, 0);
    gc.set(GregorianCalendar.DAY_OF_MONTH, 20);

    calendar.setDate(gc.getTimeInMillis());
    return calendar;
  }

  public static void main(String[] args) {
    Example2 example = new Example2();
    example.runExample();
  }

}

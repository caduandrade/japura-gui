package org.japura.examples.gui.calendar.example1;

import org.japura.examples.gui.AbstractExample;
import org.japura.gui.calendar.Calendar;

import java.awt.Component;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Example1 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    Calendar calendar = new Calendar(Locale.ENGLISH);

    GregorianCalendar gc = new GregorianCalendar();
    gc.set(GregorianCalendar.YEAR, 2011);
    gc.set(GregorianCalendar.MONTH, 0);
    gc.set(GregorianCalendar.DAY_OF_MONTH, 20);

    calendar.setDate(gc.getTimeInMillis());
    return calendar;
  }

  public static void main(String[] args) {
    Example1 example = new Example1();
    example.runExample();
  }

}

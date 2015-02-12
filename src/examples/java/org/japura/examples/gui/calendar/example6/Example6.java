package org.japura.examples.gui.calendar.example6;

import org.japura.examples.gui.AbstractExample;
import org.japura.gui.calendar.Calendar;
import org.japura.gui.calendar.CalendarComponent;
import org.japura.gui.calendar.CalendarComponentType;
import org.japura.gui.calendar.DefaultPropertiesProvider;

import java.awt.Color;
import java.awt.Component;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Example6 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    Calendar calendar = new Calendar(Locale.ENGLISH);

    calendar.setPropertiesProvider(new ExamplePropertiesProvider());

    GregorianCalendar gc = new GregorianCalendar();
    gc.set(GregorianCalendar.YEAR, 2011);
    gc.set(GregorianCalendar.MONTH, 0);
    gc.set(GregorianCalendar.DAY_OF_MONTH, 20);

    calendar.setDate(gc.getTimeInMillis());
    return calendar;
  }

  private static class ExamplePropertiesProvider extends
    DefaultPropertiesProvider {

    @Override
    public Color getForeground(CalendarComponent component) {
      CalendarComponentType type = component.getType();
      if (type.equals(CalendarComponentType.DAY_MONTH)) {
        Calendar calendar = component.getCalendar();
        int day = calendar.getDay(component);
        if (day >= 10 && day <= 12) {
          return Color.RED;
        }
      }
      return super.getForeground(component);
    }
  }

  public static void main(String[] args) {
    Example6 example = new Example6();
    example.runExample();
  }

}

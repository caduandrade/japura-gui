package org.japura.examples.gui.calendarfield;

import org.japura.examples.gui.AbstractExample;
import org.japura.gui.calendar.CalendarField;
import org.japura.gui.calendar.CalendarProperties;
import org.japura.gui.calendar.DefaultPropertiesProvider;
import org.japura.gui.model.DateDocument;
import org.japura.util.date.DateSeparator;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Component;
import java.util.GregorianCalendar;
import java.util.Locale;

public class Example4 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    JPanel panel = new JPanel();
    panel.setOpaque(false);
    panel.setBorder(BorderFactory.createEmptyBorder(80, 0, 0, 0));

    DateDocument dd = new DateDocument(Locale.ENGLISH, DateSeparator.SLASH);
    CalendarField cf = new CalendarField(dd);

    DefaultPropertiesProvider dpp = new DefaultPropertiesProvider();
    CalendarProperties cp = dpp.getProperties();
    cp.setDayOfMonthForeground(Color.RED);
    cf.setPropertiesProvider(dpp);

    GregorianCalendar gc = new GregorianCalendar();
    gc.set(GregorianCalendar.YEAR, 2011);
    gc.set(GregorianCalendar.MONTH, 0);
    gc.set(GregorianCalendar.DAY_OF_MONTH, 20);
    cf.getDateDocument().setDate(gc.getTimeInMillis());

    panel.add(cf);
    return panel;
  }

  public static void main(String[] args) {
    Example4 example = new Example4();
    example.runExample();
  }

}

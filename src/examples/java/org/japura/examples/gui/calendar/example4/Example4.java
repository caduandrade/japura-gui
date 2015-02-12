package org.japura.examples.gui.calendar.example4;

import org.japura.examples.gui.AbstractExample;
import org.japura.gui.calendar.Calendar;
import org.japura.gui.event.DateEvent;
import org.japura.gui.event.DateListener;

import java.awt.Component;
import java.util.Date;

public class Example4 extends AbstractExample {

  @Override
  protected Component buildExampleComponent() {
    Calendar calendar = new Calendar();

    calendar.addDateListener(new DateListener() {
      @Override
      public void dateChanged(DateEvent dateEvent) {
        Date oldDate = dateEvent.getOldDate();
        Date newDate = dateEvent.getNewDate();
        System.out.println(oldDate + " - " + newDate);
      }
    });
    return calendar;
  }

  public static void main(String[] args) {
    Example4 example = new Example4();
    example.runExample();
  }

}

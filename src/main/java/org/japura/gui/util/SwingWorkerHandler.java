package org.japura.gui.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.SwingWorker;
import javax.swing.SwingWorker.StateValue;
import javax.swing.Timer;

/**
 * <P>
 * Copyright (C) 2012 Carlos Eduardo Leite de Andrade
 * <P>
 * This library is free software: you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * <P>
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * <P>
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <A
 * HREF="www.gnu.org/licenses/">www.gnu.org/licenses/</A>
 * <P>
 * For more information, contact: <A HREF="www.japura.org">www.japura.org</A>
 * <P>
 * 
 * @author Carlos Eduardo Leite de Andrade
 */
public class SwingWorkerHandler{

  private int delay = 600;
  private int workersCount;

  public void setDelay(int delay) {
	this.delay = delay;
  }

  public int getDelay() {
	return delay;
  }

  public int getWorkersCount() {
	return workersCount;
  }

  void decWorkersCount() {
	workersCount--;
  }

  void incWorkersCount() {
	workersCount++;
  }

  public void register(SwingWorker<?, ?> worker, String name) {
	worker.addPropertyChangeListener(new Starter(this, name));
  }

  protected void progress(int value) {}

  protected void before(SwingWorkerHandlerEvent event) {}

  protected void after(SwingWorkerHandlerEvent event) {}

  private static class Starter extends Timer implements ActionListener,
	  PropertyChangeListener{

	private static final long serialVersionUID = 1L;
	private SwingWorkerHandler handler;
	private String name;
	private InputEventBlocker eventHook = new InputEventBlocker();
	private boolean stop;
	private boolean called;

	public Starter(SwingWorkerHandler handler, String name) {
	  super(handler.getDelay(), null);
	  this.name = name;
	  this.handler = handler;
	  addActionListener(this);
	  setRepeats(false);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	  if ("state".equals(evt.getPropertyName())) {
		if (evt.getNewValue().equals(StateValue.STARTED)) {
		  handler.incWorkersCount();
		  eventHook.apply();
		  start();
		} else if (evt.getNewValue().equals(StateValue.DONE)) {
		  handler.decWorkersCount();
		  stop();
		  eventHook.remove();
		}
	  } else if ("progress".equals(evt.getPropertyName())) {
		handler.progress((Integer) evt.getNewValue());
	  }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	  if (stop == false) {
		called = true;
		eventHook.remove();
		handler.before(new SwingWorkerHandlerEvent(handler.getWorkersCount(),
			name));
	  }
	}

	@Override
	public void stop() {
	  stop = true;
	  super.stop();
	  if (called) {
		handler.after(new SwingWorkerHandlerEvent(handler.getWorkersCount(),
			name));
	  }
	}
  }

}

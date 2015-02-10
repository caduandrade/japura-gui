package org.japura.gui;

import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.DefaultListSelectionModel;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import org.japura.gui.dnd.CheckListTransferHandler;
import org.japura.gui.dnd.CheckListTransferable;
import org.japura.gui.event.ListCheckListener;
import org.japura.gui.event.ListEvent;
import org.japura.gui.event.ListLockListener;
import org.japura.gui.event.ListModelListener;
import org.japura.gui.model.DefaultListCheckModel;
import org.japura.gui.model.ListCheckModel;
import org.japura.gui.renderer.CheckListRenderer;

/**
 * List with CheckBoxes
 * <P>
 * Copyright (C) 2010-2014 Carlos Eduardo Leite de Andrade
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
public class CheckList extends JList{

  private static final long serialVersionUID = 7562297704191604289L;
  private ListCheckModel model;
  private PopupMenuBuilder<CheckList> popupMenuBuilder;

  public CheckList() {
	InputMap im = getInputMap(JComponent.WHEN_FOCUSED);
	Action copyAction = new AbstractAction() {
	  private static final long serialVersionUID = -7299012786614721676L;

	  public void actionPerformed(ActionEvent e) {
		String str = CheckListTransferable.toString(getModel(), ", ");
		Clipboard cb = Toolkit.getDefaultToolkit().getSystemClipboard();
		cb.setContents(new StringSelection(str), null);
	  }
	};
	im.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK), "Copy");

	ActionMap am = getActionMap();
	am.put("Copy", copyAction);

	setModel(new DefaultListCheckModel());
	setCellRenderer(new CheckListRenderer());
	super.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	MouseMotionListener[] mmls = getMouseMotionListeners();
	for (MouseMotionListener mml : mmls) {
	  // removeMouseMotionListener(mml);
	}

	MouseListener[] listeners = getMouseListeners();
	for (MouseListener ml : listeners) {
	  // removeMouseListener(ml);
	}

	setTransferHandler(new CheckListTransferHandler());

	// ToolTipManager.sharedInstance().registerComponent(this);

	addMouseMotionListener(new MouseAdapter() {
	  @Override
	  public void mouseMoved(MouseEvent e) {
		updateSelection(e.getPoint());
	  }
	});

	addMouseListener(new MouseAdapter() {
	  @Override
	  public void mouseExited(MouseEvent e) {
		updateSelection(null);
	  }

	  @Override
	  public void mouseClicked(MouseEvent e) {
		if (SwingUtilities.isLeftMouseButton(e)) {
		  int index = locationToIndex(e.getPoint());
		  if (index > -1) {
			Object obj = getModel().getElementAt(index);
			if (getModel().isLocked(obj) == false) {
			  boolean checked = getModel().isChecked(obj);
			  if (checked) {
				getModel().removeCheck(obj);
			  } else {
				getModel().addCheck(obj);
			  }
			  fireActionListener();
			}
		  }
		} else if (SwingUtilities.isRightMouseButton(e)
			&& getPopupMenuBuilder() != null) {
		  JPopupMenu pm = getPopupMenuBuilder().buildPopupMenu(CheckList.this);
		  if (pm != null) {
			pm.show(CheckList.this, e.getX(), e.getY());
		  }
		}
	  }
	});
  }

  public void addActionListener(ActionListener l) {
	listenerList.add(ActionListener.class, l);
  }

  public void removeActionListener(ActionListener l) {
	listenerList.remove(ActionListener.class, l);
  }

  public ActionListener[] getActionListeners() {
	return (ActionListener[]) (listenerList.getListeners(ActionListener.class));
  }

  private void fireActionListener() {
	ActionEvent event =
		new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null,
			System.currentTimeMillis(), 0);
	for (ActionListener listener : getActionListeners()) {
	  listener.actionPerformed(event);
	}
  }

  @Override
  public final void setSelectionModel(ListSelectionModel selectionModel) {
	super.setSelectionModel(new DefaultListSelectionModel());
  }

  @Override
  public final void setSelectionMode(int selectionMode) {
	super.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
  }

  private void updateSelection(Point point) {
	if (point != null) {
	  int index = locationToIndex(point);
	  setSelectedIndex(index);
	} else {
	  clearSelection();
	}
	repaint();
  }

  @Override
  public ListCheckModel getModel() {
	if (model == null) {
	  setModel(new DefaultListCheckModel());
	}
	return model;
  }

  @Override
  public void setCellRenderer(ListCellRenderer cellRenderer) {
	if (cellRenderer instanceof CheckListRenderer) {
	  super.setCellRenderer(cellRenderer);
	}
  }

  public void setCellRenderer(CheckListRenderer cellRenderer) {
	super.setCellRenderer(cellRenderer);
  }

  @Override
  public CheckListRenderer getCellRenderer() {
	return (CheckListRenderer) super.getCellRenderer();
  }

  public void setPopupMenuBuilder(PopupMenuBuilder<CheckList> popupMenuBuilder) {
	this.popupMenuBuilder = popupMenuBuilder;
  }

  public PopupMenuBuilder<CheckList> getPopupMenuBuilder() {
	return popupMenuBuilder;
  }

  @Override
  public void setModel(ListModel model) {
	if (model instanceof ListCheckModel) {
	  setModel((ListCheckModel) model);
	} else {
	  throw new IllegalArgumentException("model must be ListCheckModel");
	}
  }

  public void setModel(ListCheckModel model) {
	if (model == null) {
	  throw new IllegalArgumentException("model must be non null");
	}
	ListCheckModel oldValue = this.model;
	this.model = model;
	if (oldValue != null) {
	  firePropertyChange("model", oldValue, model);
	}

	model.addListModelListener(new ListModelListener() {
	  @Override
	  public void valueAdded(ListEvent e) {
		repaint();
	  }

	  @Override
	  public void valueRemoved(ListEvent e) {
		repaint();
	  }
	});

	model.addListCheckListener(new ListCheckListener() {
	  @Override
	  public void addCheck(ListEvent event) {
		repaint();
	  }

	  @Override
	  public void removeCheck(ListEvent event) {
		repaint();
	  }
	});

	model.addListLockListener(new ListLockListener() {
	  @Override
	  public void addLock(ListEvent event) {
		repaint();
	  }

	  @Override
	  public void removeLock(ListEvent event) {
		repaint();
	  }
	});
	repaint();
  }
}

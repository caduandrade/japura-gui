package org.japura.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JScrollPane;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import org.japura.gui.event.ListCheckListener;
import org.japura.gui.event.ListEvent;
import org.japura.gui.model.DefaultListCheckModel;
import org.japura.gui.model.ListCheckModel;
import org.japura.gui.renderer.CheckListRenderer;

/**
 * ComboBox with Check items.
 * <P>
 * Copyright (C) 2010-2011 Carlos Eduardo Leite de Andrade
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
public class CheckComboBox extends AbstractComboBox{

  private static final long serialVersionUID = 8289247356412690742L;
  public static final CheckState NONE = CheckState.NONE;
  public static final CheckState MULTIPLE = CheckState.MULTIPLE;
  public static final CheckState ALL = CheckState.ALL;

  private JScrollPane scrollPane;
  private CheckList checkList;
  private ListDataListener listDataListener;
  private ListCheckListener listCheckListener;

  private int visibleRowCount = 8;

  private HashMap<CheckState, String> texts;

  public CheckComboBox() {
	texts = new HashMap<CheckState, String>();
	setRenderer(new CheckListRenderer());
	setModel(new DefaultListCheckModel());
  }

  public void setRenderer(CheckListRenderer renderer) {
	getCheckList().setCellRenderer(renderer);
	updateCellPanelWidth();
  }

  public int getVisibleRowCount() {
	return visibleRowCount;
  }

  public void setVisibleRowCount(int visibleRowCount) {
	this.visibleRowCount = Math.max(3, visibleRowCount);
	getCheckList().setVisibleRowCount(getVisibleRowCount());
  }

  @Override
  public void setPopupVisible(boolean visible) {
	if (visible) {
	  if (getModel().getSize() > 0) {
		int rows = Math.min(getVisibleRowCount(), getModel().getSize());
		getCheckList().setVisibleRowCount(rows);
		getCheckList().setPreferredSize(null);
		Dimension pSize = getCheckList().getPreferredSize();
		Dimension dim = getSize();
		if (pSize.width < dim.width) {
		  getCheckList().setPreferredSize(
			  new Dimension(dim.width, pSize.height));
		}
		getPopup().show(this, 0, dim.height);
	  }
	} else {
	  getPopup().setVisible(false);
	}
  }

  @Override
  protected void unregisterEmbeddedComponent(JComponent oldEmbeddedComponent) {
	if (oldEmbeddedComponent instanceof BatchSelection) {
	  BatchSelection bs = (BatchSelection) oldEmbeddedComponent;
	  bs.unregisterModel();
	}
  }

  @Override
  protected void registerEmbeddedComponent(JComponent newEmbeddedComponent) {
	if (newEmbeddedComponent instanceof BatchSelection) {
	  BatchSelection bs = (BatchSelection) newEmbeddedComponent;
	  bs.registerModel(getModel());
	}
  }

  public void setModel(ListCheckModel model) {
	getCheckList().setModel(model);
	model.removeListDataListener(getListDataListener());
	model.removeListCheckListener(getListCheckListener());
	model.addListDataListener(getListDataListener());
	model.addListCheckListener(getListCheckListener());
	if (getEmbeddedComponent() != null) {
	  JComponent comp = getEmbeddedComponent().getComponent();
	  unregisterEmbeddedComponent(comp);
	  registerEmbeddedComponent(comp);
	}
	updateCellPanelWidth();
  }

  public ListCheckModel getModel() {
	return getCheckList().getModel();
  }

  protected void updateCellPanelWidth() {
	if (getPrototypeDisplayValue() != null) {
	  getComboBox().setPrototypeDisplayValue(getPrototypeDisplayValue());
	  return;
	}

	String value = null;

	for (CheckState checkState : CheckState.values()) {
	  String text = getTextFor(checkState);
	  if (text != null) {
		if (value == null) {
		  value = text;
		} else if (text.length() > value.length()) {
		  value = text;
		}
	  }
	}

	ListCheckModel m = getModel();

	for (int i = 0; i < m.getSize(); i++) {
	  String str = m.getElementAt(i).toString();
	  if (value == null) {
		value = str;
	  } else if (str.length() > value.length()) {
		value = str;
	  }
	}

	getComboBox().setPrototypeDisplayValue(value);
  }

  private CheckList getCheckList() {
	if (checkList == null) {
	  checkList = new CheckList();
	}
	return checkList;
  }

  private ListCheckListener getListCheckListener() {
	if (listCheckListener == null) {
	  listCheckListener = new ListCheckListener() {
		@Override
		public void addCheck(ListEvent event) {
		  updateComboBox();
		}

		@Override
		public void removeCheck(ListEvent event) {
		  updateComboBox();
		}
	  };
	}
	return listCheckListener;
  }

  @Override
  protected void updateComboBox() {
	getComboBox().removeAllItems();

	CheckListRenderer renderer = getCheckList().getCellRenderer();

	List<Object> checkeds = getModel().getCheckeds();
	int total = getModel().getSize();
	if (total > 0) {
	  if (checkeds.size() == 0 && texts.containsKey(CheckState.NONE)) {
		getComboBox().addItem(getTextFor(CheckState.NONE));
	  } else if (checkeds.size() == 1) {
		getComboBox().addItem(renderer.getText(checkeds.get(0)));
	  } else if (checkeds.size() == total && texts.containsKey(CheckState.ALL)) {
		getComboBox().addItem(getTextFor(CheckState.ALL));
	  } else if (checkeds.size() > 1 && texts.containsKey(CheckState.MULTIPLE)) {
		getComboBox().addItem(getTextFor(CheckState.MULTIPLE));
	  }
	}
  }

  private ListDataListener getListDataListener() {
	if (listDataListener == null) {
	  listDataListener = new ListDataListener() {
		@Override
		public void contentsChanged(ListDataEvent e) {}

		@Override
		public void intervalAdded(ListDataEvent e) {
		  updateComboBox();
		  updateCellPanelWidth();
		  repaint();
		}

		@Override
		public void intervalRemoved(ListDataEvent e) {
		  updateComboBox();
		  updateCellPanelWidth();
		  repaint();
		}
	  };
	}
	return listDataListener;
  }

  public String getTextFor(CheckState checkState) {
	if (checkState != null) {
	  return texts.get(checkState);
	}
	return null;
  }

  public void removeTextFor(CheckState checkState) {
	if (checkState != null) {
	  texts.remove(checkState);
	  if (checkState.equals(NONE)) {
		updateComboBox();
	  }
	}
  }

  public void setTextFor(CheckState checkState, String text) {
	if (checkState == null) {
	  return;
	}

	if (text == null) {
	  text = "";
	}

	texts.put(checkState, text);
	if (checkState.equals(NONE)) {
	  updateComboBox();
	}
	updateCellPanelWidth();
	repaint();
  }

  private JScrollPane getScrollPane() {
	if (scrollPane == null) {
	  scrollPane = new JScrollPane();
	  scrollPane.setBorder(BorderFactory.createLineBorder(Color.black));
	  scrollPane.setViewportView(getCheckList());
	}
	return scrollPane;
  }

  @Override
  protected JComponent getPopupComponent() {
	return getScrollPane();
  }

  public static enum CheckState {
	NONE,
	ALL,
	MULTIPLE;
  }

}

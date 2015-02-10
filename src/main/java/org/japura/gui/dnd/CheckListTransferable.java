package org.japura.gui.dnd;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.japura.gui.CheckList;
import org.japura.gui.model.ListCheckModel;

/**
 * 
 * <P>
 * Copyright (C) 2011 Carlos Eduardo Leite de Andrade
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
public class CheckListTransferable implements Transferable{

  public static final DataFlavor CHECKEDS_LIST_FLAVOR = new DataFlavor(
	  ArrayList.class, "CHECKEDS_LIST_FLAVOR");

  public static boolean isAvailableToTransfer(CheckList list) {
	ListCheckModel model = list.getModel();
	if (model.getChecksCount() == 0) {
	  return false;
	}

	int index = list.getSelectedIndex();
	if (index == -1) {
	  return false;
	}

	if (index >= model.getSize()) {
	  return false;
	}

	Object selectedElement = model.getElementAt(index);
	return model.isChecked(selectedElement);
  }

  private ListCheckModel model;
  private Comparator<Object> comparator;
  private String separator;

  public CheckListTransferable(CheckList list, String separator,
	  Comparator<Object> comparator) {
	this.model = list.getModel();
	this.separator = separator;
	this.comparator = comparator;
  }

  @Override
  public DataFlavor[] getTransferDataFlavors() {
	return new DataFlavor[] { CHECKEDS_LIST_FLAVOR, DataFlavor.stringFlavor };
  }

  @Override
  public boolean isDataFlavorSupported(DataFlavor flavor) {
	if (flavor.equals(CHECKEDS_LIST_FLAVOR)
		|| flavor.equals(DataFlavor.stringFlavor)) {
	  return true;
	}

	return false;
  }

  private List<Object> getCheckeds() {
	List<Object> objs = model.getCheckeds();
	if (comparator != null) {
	  Collections.sort(objs, comparator);
	}
	return objs;
  }

  public static String toString(ListCheckModel model, String separator) {
	StringBuilder sb = new StringBuilder();
	List<Object> objs = model.getCheckeds();
	for (int i = 0; i < objs.size(); i++) {
	  Object obj = objs.get(i);
	  sb.append(obj.toString());
	  if (separator != null && i < objs.size() - 1) {
		sb.append(separator);
	  }
	}
	return sb.toString().trim();
  }

  @Override
  public Object getTransferData(DataFlavor flavor)
	  throws UnsupportedFlavorException, IOException {
	if (flavor.equals(DataFlavor.stringFlavor)) {
	  return toString(model, separator);
	}

	if (flavor.equals(CHECKEDS_LIST_FLAVOR)) {
	  return new ArrayList<Object>(getCheckeds());
	}

	return null;
  }

}

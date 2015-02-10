package org.japura.gui.dnd;

import java.awt.datatransfer.Transferable;
import java.util.Comparator;

import javax.swing.JComponent;
import javax.swing.TransferHandler;

import org.japura.gui.CheckList;

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
public class CheckListTransferHandler extends TransferHandler{

  private static final long serialVersionUID = 4519935360824025248L;

  private String elementSeparator;
  private Comparator<Object> comparator;

  public CheckListTransferHandler() {
	elementSeparator = ", ";
  }

  public Comparator<Object> getComparator() {
	return comparator;
  }

  public void setComparator(Comparator<Object> comparator) {
	this.comparator = comparator;
  }

  public String getElementSeparator() {
	return elementSeparator;
  }

  public void setElementSeparator(String elementSeparator) {
	this.elementSeparator = elementSeparator;
  }

  @Override
  public int getSourceActions(JComponent c) {
	return TransferHandler.COPY;
  }

  protected boolean canTransfer(JComponent c) {
	if (c instanceof CheckList) {
	  CheckList list = (CheckList) c;
	  return CheckListTransferable.isAvailableToTransfer(list);
	}
	return false;
  }

  @Override
  protected Transferable createTransferable(JComponent c) {
	if (canTransfer(c)) {
	  return new CheckListTransferable((CheckList) c, getElementSeparator(),
		  getComparator());
	}
	return null;
  }

  @Override
  public boolean canImport(TransferSupport support) {
	return false;
  }
}

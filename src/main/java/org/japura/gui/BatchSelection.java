package org.japura.gui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import org.japura.gui.event.ListCheckListener;
import org.japura.gui.event.ListEvent;
import org.japura.gui.i18n.GUIStringKeys;
import org.japura.gui.model.ListCheckModel;
import org.japura.util.i18n.I18nAdapter;

/**
 * 
 * <P>
 * Copyright (C) 2011-2013 Carlos Eduardo Leite de Andrade
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
public abstract class BatchSelection extends JPanel{

  private static final long serialVersionUID = -5590564660493587009L;
  private ListCheckModel model;

  public BatchSelection() {
	setBackground(Color.WHITE);
  }

  protected void setModel(ListCheckModel model) {
	this.model = model;
  }

  protected ListCheckModel getModel() {
	return model;
  }

  protected abstract void unregisterModel();

  protected abstract void registerModel(ListCheckModel model);

  public static class Link extends BatchSelection{

	private static final long serialVersionUID = -7012666490345732840L;
	private LinkLabel selectAllButton;
	private LinkLabel deselectAllButton;
	private ActionListener selectListener;
	private ActionListener deselectListener;

	public Link() {
	  initialization();
	}

	protected void initialization() {
	  setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	  setLayout(new GridBagLayout());
	  GridBagConstraints gbc = new GridBagConstraints();
	  gbc.gridx = 0;
	  gbc.gridy = 0;
	  add(getSelectAllButton(), gbc);

	  gbc.gridx = 1;
	  gbc.weightx = 1;
	  gbc.anchor = GridBagConstraints.WEST;
	  gbc.insets = new Insets(0, 20, 0, 0);
	  add(getDeselectAllButton(), gbc);
	}

	protected LinkLabel getSelectAllButton() {
	  if (selectAllButton == null) {
		selectAllButton = new LinkLabel();
		selectAllButton.setText(I18nAdapter.getAdapter().getString(
			GUIStringKeys.SELECT_ALL.getKey()));
	  }
	  return selectAllButton;
	}

	protected LinkLabel getDeselectAllButton() {
	  if (deselectAllButton == null) {
		deselectAllButton = new LinkLabel();
		deselectAllButton.setText(I18nAdapter.getAdapter().getString(
			GUIStringKeys.DESELECT_ALL.getKey()));
	  }
	  return deselectAllButton;
	}

	@Override
	protected void unregisterModel() {
	  if (selectListener != null) {
		getSelectAllButton().removeActionListener(selectListener);
	  }
	  if (deselectListener != null) {
		getDeselectAllButton().removeActionListener(deselectListener);
	  }
	  selectListener = null;
	  deselectListener = null;
	}

	@Override
	protected void registerModel(final ListCheckModel model) {
	  setModel(model);
	  selectListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		  model.checkAll();
		}
	  };
	  deselectListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		  model.removeChecks();
		}
	  };

	  getSelectAllButton().addActionListener(selectListener);
	  getDeselectAllButton().addActionListener(deselectListener);
	}
  }

  public static class CheckBox extends BatchSelection{

	private static final long serialVersionUID = 8362937292351414821L;
	private JCheckBox checkBox;
	private ActionListener checkBoxListener;
	private ListCheckListener modelListener;

	public CheckBox() {
	  initialization();
	}

	protected void initialization() {
	  setLayout(new GridBagLayout());
	  GridBagConstraints gbc = new GridBagConstraints();
	  gbc.gridx = 0;
	  gbc.gridy = 0;
	  gbc.weightx = 1;
	  gbc.anchor = GridBagConstraints.WEST;
	  add(getCheckBox(), gbc);
	}

	protected JCheckBox getCheckBox() {
	  if (checkBox == null) {
		checkBox =
			new JCheckBox(I18nAdapter.getAdapter().getString(
				GUIStringKeys.SELECT_DESELECT_ALL.getKey()));
		checkBox.setOpaque(false);
	  }
	  return checkBox;
	}

	@Override
	protected void unregisterModel() {
	  if (checkBoxListener != null) {
		getCheckBox().removeActionListener(checkBoxListener);
	  }
	  if (modelListener != null) {
		getModel().removeListCheckListener(modelListener);
	  }
	  checkBoxListener = null;
	  modelListener = null;
	}

	@Override
	protected void registerModel(final ListCheckModel model) {
	  setModel(model);
	  checkBoxListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		  if (getCheckBox().isSelected()) {
			model.checkAll();
		  } else {
			model.removeChecks();
		  }
		}
	  };
	  modelListener = new ListCheckListener() {
		@Override
		public void removeCheck(ListEvent event) {
		  update(event.getSource());
		}

		@Override
		public void addCheck(ListEvent event) {
		  update(event.getSource());
		}
	  };

	  getCheckBox().addActionListener(checkBoxListener);
	  model.addListCheckListener(modelListener);
	}

	protected void update(ListCheckModel model) {
	  if (model.getSize() == model.getChecksCount()) {
		getCheckBox().setSelected(true);
	  } else {
		getCheckBox().setSelected(false);
	  }
	}

  }

}

package org.japura.gui;

import org.japura.gui.renderer.HighlightCellRenderer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * 
 * <P>
 * Copyright (C) 2010-2015 Carlos Eduardo Leite de Andrade
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
 * 
 */
public class DropDownFilteredList<T> {

  private JPopupMenu popup;
  private JComponent invoker;
  private JTextField field;
  private JPanel root;
  private JList list;
  private DefaultListModel model;
  private List<T> items;
  private boolean caseSensitive;

  public DropDownFilteredList(ButtonTextField buttonField, List<T> items) {
    this(buttonField, items, false);
  }

  public DropDownFilteredList(ButtonTextField buttonField, List<T> items,
    boolean caseSensitive) {
    invoker = buttonField;
    init(buttonField.getField(), items, caseSensitive);
  }

  public DropDownFilteredList(JTextField field, List<T> items) {
    this(field, items, false);
  }

  public DropDownFilteredList(JTextField field, List<T> items,
    boolean caseSensitive) {
    invoker = field;
    init(field, items, caseSensitive);
  }

  private void init(JTextField field, List<T> items, boolean caseSensitive) {
    this.field = field;
    this.caseSensitive = caseSensitive;
    setCellRenderer(new HighlightCellRenderer(caseSensitive));

    field.getDocument().addDocumentListener(new DocumentListener() {
      @Override
      public void insertUpdate(DocumentEvent e) {
        rebuildModel();
      }

      @Override
      public void removeUpdate(DocumentEvent e) {
        rebuildModel();
      }

      @Override
      public void changedUpdate(DocumentEvent e) {
        rebuildModel();
      }
    });

    field.addKeyListener(new KeyAdapter() {
      @Override
      public void keyReleased(KeyEvent e) {
        if (model.size() == 0) {
          getPopup().setVisible(false);
        }
        else if (getPopup().isVisible() == false) {
          showList();
        }

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
          if (getList().getSelectedIndex() == -1 && model.size() > 0) {
            getList().setSelectedIndex(0);
          }

          SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
              getList().requestFocus();
            }
          });
        }
        else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
          getPopup().setVisible(false);
        }
        else {
          SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
              getField().requestFocus();
            }
          });
        }
      }
    });
    setItems(items);
  }

  public void setItems(List<T> items) {
    this.items = items;
    getPopup().setVisible(false);
    rebuildModel();
    getRoot().setPreferredSize(null);
    getRoot().setPreferredSize(getRoot().getPreferredSize());
    getPopup().pack();
  }

  public ListCellRenderer getCellRenderer() {
    return getList().getCellRenderer();
  }

  public void setCellRenderer(ListCellRenderer cellRenderer) {
    getList().setCellRenderer(cellRenderer);
  }

  @SuppressWarnings("unchecked")
  private void chooseSelectedListItem() {
    if (getList().getSelectedIndex() > -1) {
      T item = (T) getList().getSelectedValue();
      getField().setText(item.toString());
      getPopup().setVisible(false);
    }
  }

  private JTextField getField() {
    return field;
  }

  private JPopupMenu getPopup() {
    if (popup == null) {
      popup = new JPopupMenu();
      popup.add(getRoot());
    }
    return popup;
  }

  private JPanel getRoot() {
    if (root == null) {
      root = new JPanel();
      root.setLayout(new GridLayout(1, 1));
      JScrollPane sp = new JScrollPane(getList());
      sp.setBorder(null);
      root.add(sp);
    }
    return root;
  }

  private void rebuildModel() {
    String typedText = getField().getText();
    if (caseSensitive == false) {
      typedText = typedText.toLowerCase();
    }
    if (getCellRenderer() instanceof HighlightCellRenderer) {
      HighlightCellRenderer renderer =
        (HighlightCellRenderer) getCellRenderer();
      renderer.setHighlightText(typedText);
    }
    model = new DefaultListModel();
    for (T item : items) {
      String text = item.toString();
      if (caseSensitive == false)
        text = text.toLowerCase();
      if (text.length() == 0 || text.indexOf(typedText) > -1) {
        model.addElement(item);
      }
    }
    getList().setModel(model);
  }

  public void showList() {
    if (model == null) {
      rebuildModel();
    }
    if (model.getSize() > 0) {
      getPopup().show(invoker, 0, invoker.getHeight());
    }
  }

  private JList getList() {
    if (list == null) {
      list = new JList();
      list.setBorder(BorderFactory.createEmptyBorder(1, 3, 1, 3));
      list.addKeyListener(new KeyAdapter() {
        @Override
        public void keyReleased(KeyEvent e) {
          if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            chooseSelectedListItem();
          }
        }

      });
      list.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          if (e.getClickCount() == 2) {
            chooseSelectedListItem();
          }
        }
      });
    }
    return list;
  }

}
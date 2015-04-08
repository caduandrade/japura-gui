package org.japura.gui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

  private ValueToString<T> valueToString;
  private JPopupMenu popup;
  private JComponent invoker;
  private JTextField field;
  private JPanel root;
  private DropDownList<T> list;
  private DefaultListModel model;
  private Collection<T> items;
  private boolean caseSensitive;
  private List<DropDownFilteredListListener> listeners;
  private int maxVisibleRowCount = 10;

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
    this.listeners = new ArrayList<>();
    this.valueToString = new DefaultValueToString<>();
    this.field = field;
    this.caseSensitive = caseSensitive;

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
        if (model.size() == 0 || e.getKeyCode() == KeyEvent.VK_ENTER) {
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

  public int getMaxVisibleRowCount() {
    return maxVisibleRowCount;
  }

  public void setMaxVisibleRowCount(int count) {
    this.maxVisibleRowCount = Math.max(1, count);
  }

  public void setValueToString(ValueToString<T> valueToString) {
    if (valueToString != null) {
      this.valueToString = valueToString;
    }
  }

  public ValueToString<T> getValueToString() {
    return valueToString;
  }

  public void addListener(DropDownFilteredListListener listener) {
    if (listener != null) {
      this.listeners.add(listener);
    }
  }

  public void removeListener(DropDownFilteredListListener listener) {
    if (listener != null) {
      this.listeners.remove(listener);
    }
  }

  public List<DropDownFilteredListListener> getListeners() {
    return Collections.unmodifiableList(listeners);
  }

  public void setItems(Collection<T> items) {
    this.items = items;
    getPopup().setVisible(false);
    rebuildModel();
    int rows = Math.min(items.size(), getMaxVisibleRowCount());
    getList().setVisibleRowCount(rows);
  }

  public ListCellRenderer getCellRenderer() {
    return getList().getCellRenderer();
  }

  public void setCellRenderer(ListCellRenderer cellRenderer) {
    getList().setCellRenderer(cellRenderer);
  }

  private void chooseSelectedListItem() {
    if (getList().getSelectedIndex() > -1) {
      T item = (T) getList().getSelectedValue();
      String text = getValueToString().valueToString(item);
      getField().setText(text);
      getPopup().setVisible(false);
      for (DropDownFilteredListListener listener : getListeners()) {
        listener.itemSelected(text);
      }
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
    model = new DefaultListModel();
    for (T item : items) {
      String text = getValueToString().valueToString(item);
      if (caseSensitive == false) {
        text = text.toLowerCase();
      }
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

      getPopup().setPreferredSize(null);
      Dimension dim = getPopup().getPreferredSize();
      dim.width = this.field.getWidth();
      getPopup().setPreferredSize(dim);
      getPopup().show(invoker, 0, invoker.getHeight());
    }
  }

  private DropDownList<T> getList() {
    if (list == null) {
      list = new DropDownList();
      list.setCellRenderer(new ListRenderer());
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

  private class DropDownList<E> extends JList<E> {
    @Override
    public boolean getScrollableTracksViewportWidth() {
      return true;
    }
  }

  private class ListRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value,
      int index, boolean isSelected, boolean cellHasFocus) {
      T item = (T) value;
      String text = getValueToString().valueToString(item);
      return super.getListCellRendererComponent(list, text, index, isSelected,
        cellHasFocus);
    }
  }

}

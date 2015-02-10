package org.japura.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import org.japura.gui.renderer.DefaultSplitButtonRenderer;
import org.japura.gui.renderer.SplitButtonRenderer;

/**
 * Copyright (C) 2008-2013 Carlos Eduardo Leite de Andrade
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
public class SplitButton extends JButton{

  private static final long serialVersionUID = 4L;
  public final static Mode BUTTON = Mode.BUTTON;
  public final static Mode MENU = Mode.MENU;
  private Mode mode;
  private int maxLabelWidth;
  private int alignment = SwingConstants.CENTER;
  private int imageWidth = 13;
  private int gap = 5;
  private int separatorGap = 7;
  private String actualButton;
  private boolean fireBlocked;
  private boolean buttonChooserVisible;
  private boolean mouseIn;
  private List<SplitButton.Button> buttons;
  private DefaultListModel listModel;
  private JPopupMenu buttonsChooser;
  private JPanel buttonsRoot;
  private JList buttonsList;
  private ListRenderer listRenderer;
  private SplitButtonRenderer splitButtonRenderer;
  private transient MouseListener originalMouseListener;
  private transient MouseListener handlerMouseListener;

  public SplitButton() {
	this(Mode.BUTTON);
  }

  public SplitButton(Mode mode) {
	if (mode != null) {
	  this.mode = mode;
	} else {
	  this.mode = BUTTON;
	}
	buttons = new ArrayList<SplitButton.Button>();
	splitButtonRenderer = new DefaultSplitButtonRenderer();
	URL url = getClass().getResource("/images/jpr_splitbuttondown.png");

	Insets margin = getMargin();
	margin.right = gap;
	margin.left = gap;
	setMargin(margin);

	super.setHorizontalTextPosition(SwingConstants.LEFT);
	super.setHorizontalAlignment(SwingConstants.RIGHT);
	super.setIcon(new ImageIcon(url));
	setFocusPainted(false);
	originalMouseListener = getMouseListeners()[0];
	removeMouseListener(originalMouseListener);
	addMouseListener(getHandlerMouseListener());
	addComponentListener(new ComponentAdapter() {
	  @Override
	  public void componentResized(ComponentEvent e) {
		reajustTextGap();
	  }
	});
  }

  private SplitButton.Button get(String name) {
	name = ajustName(name);
	if (name == null) {
	  return null;
	}

	for (SplitButton.Button button : buttons) {
	  if (button.isSeparator() == false && button.getName().equals(name)) {
		return button;
	  }
	}
	return null;
  }

  private DefaultListModel getListModel() {
	if (listModel == null) {
	  listModel = new DefaultListModel();
	}
	return listModel;
  }

  public void showButtonsChooser() {
	SwingUtilities.invokeLater(new Runnable() {
	  @Override
	  public void run() {
		if (isEnabled() == false || isShowing() == false) {
		  return;
		}

		buttonChooserVisible = true;
		fireBlocked = true;

		DefaultListModel model = getListModel();

		model.removeAllElements();
		boolean lastWasSeparator = false;
		boolean lastWasButton = false;
		for (SplitButton.Button button : buttons) {
		  if (button.isSeparator() && lastWasButton == true) {
			model.addElement(button);
			lastWasButton = false;
			lastWasSeparator = true;
		  } else if (button.isVisible() && button.isSeparator() == false) {
			model.addElement(button);
			lastWasButton = true;
			lastWasSeparator = false;
		  }
		}

		while (lastWasSeparator) {
		  model.remove(model.size() - 1);
		  lastWasSeparator = false;
		  if (model.size() > 0) {
			SplitButton.Button button =
				(SplitButton.Button) model.get(model.size() - 1);
			if (button.isSeparator()) {
			  lastWasSeparator = true;
			}
		  }
		}

		if (model.size() == 0) {
		  buttonChooserVisible = false;
		  fireBlocked = false;
		  return;
		}

		Dimension dim = getSize();
		Dimension bcDim = getButtonsRoot().getPreferredSize();
		Insets insets = getButtonsChooser().getInsets();
		int width = dim.width;
		int height = bcDim.height + insets.bottom + insets.top;
		Dimension newDim = new Dimension(width, height);
		getButtonsChooser().setPreferredSize(newDim);
		getButtonsChooser().show(SplitButton.this, 0, dim.height);
	  }
	});
  }

  private JPopupMenu getButtonsChooser() {
	if (buttonsChooser == null) {
	  buttonsChooser = new JPopupMenu();
	  buttonsChooser.setName("buttonsChooser");
	  buttonsChooser.add(getButtonsRoot());

	  buttonsChooser.addPopupMenuListener(new PopupMenuListener() {
		@Override
		public void popupMenuCanceled(PopupMenuEvent e) {}

		@Override
		public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
		  if (mouseIn) {
			buttonChooserVisible = true;
		  } else {
			buttonChooserVisible = false;
		  }
		}

		@Override
		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {}
	  });
	}
	return buttonsChooser;
  }

  private JPanel getButtonsRoot() {
	if (buttonsRoot == null) {
	  buttonsRoot = new JPanel();
	  buttonsRoot.setLayout(new BorderLayout());
	  buttonsRoot.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
	  buttonsRoot.add(getButtonsList(), BorderLayout.CENTER);
	}
	return buttonsRoot;
  }

  private JList getButtonsList() {
	if (buttonsList == null) {
	  buttonsList = new JList();
	  listRenderer = new ListRenderer();
	  buttonsList.setCellRenderer(listRenderer);
	  buttonsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	  buttonsList.setModel(getListModel());
	  buttonsList.addListSelectionListener(new ListSelectionListener() {
		@Override
		public void valueChanged(ListSelectionEvent e) {
		  if (e.getValueIsAdjusting() == false) {
			if (buttonsList.getSelectedIndex() > -1) {
			  Button button = (Button) buttonsList.getSelectedValue();
			  if (button.isSeparator() == false && !button.isDisabled()) {
				setCurrentButton(button.getName());
				doClick();
				buttonsList.clearSelection();
			  }
			}
		  }
		}
	  });
	  buttonsList.addMouseMotionListener(new MouseMotionListener() {
		@Override
		public void mouseDragged(MouseEvent e) {}

		@Override
		public void mouseMoved(MouseEvent e) {
		  listRenderer.mouseOverIndex =
			  buttonsList.locationToIndex(e.getPoint());
		  buttonsList.repaint();
		}
	  });
	  buttonsList.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseExited(MouseEvent e) {
		  listRenderer.mouseOverIndex = -1;
		  buttonsList.repaint();
		}
	  });
	}
	return buttonsList;
  }

  private void removeActualListeners() {
	if (actualButton != null) {
	  SplitButton.Button button = get(actualButton);
	  if (button != null) {
		for (ActionListener listener : button.getListeners()) {
		  super.removeActionListener(listener);
		}
	  }
	}
  }

  public void clearCurrentButton() {
	removeActualListeners();
	fireBlocked = false;
	getButtonsChooser().setVisible(false);
	listRenderer.mouseOverIndex = -1;
	actualButton = null;
	if (mode.equals(Mode.BUTTON)) {
	  super.setText("");
	}
	reajustTextGap();
  }

  public void setCurrentButton(Action action) {
	String name = null;
	if (action != null) {
	  name = (String) action.getValue(Action.NAME);
	}
	setCurrentButton(name);
  }

  public void setCurrentButton(String name) {
	clearCurrentButton();

	name = ajustName(name);
	SplitButton.Button button = get(name);
	if (name != null && button != null && button.isDisabled() == false) {
	  actualButton = name;
	  for (ActionListener listener : button.getListeners()) {
		super.addActionListener(listener);
	  }
	  if (mode.equals(Mode.BUTTON)) {
		super.setText(name);
	  }
	}
	reajustTextGap();
  }

  private void reajustTextGap() {
	FontMetrics fm = getFontMetrics(getFont());
	Dimension dim = null;
	if (isShowing()) {
	  dim = getSize();
	} else {
	  dim = getPreferredSize();
	}
	Insets insets = getInsets();
	int avaiableWidth =
		dim.width - insets.left - insets.right - imageWidth - separatorGap - 3
			- gap;
	int d = gap + 3 + separatorGap;
	int width = fm.stringWidth(getText());
	if (alignment == SwingConstants.LEFT) {
	  super.setIconTextGap(avaiableWidth - width + d);
	} else if (alignment == SwingConstants.CENTER) {
	  super.setIconTextGap((avaiableWidth / 2) - ((width) / 2) + d);
	} else if (alignment == SwingConstants.RIGHT) {
	  super.setIconTextGap(gap + 3 + separatorGap);
	}
  }

  @Override
  public final void setHorizontalTextPosition(int textPosition) {}

  /**
   * Sets the horizontal alignment of the text. {@code SplitButton}'s default is
   * {@code SwingConstants.CENTER}.
   * 
   * @param alignment
   *          the alignment value, one of the following values:
   *          <ul>
   *          <li>{@code SwingConstants.RIGHT}
   *          <li>{@code SwingConstants.LEFT}
   *          <li>{@code SwingConstants.CENTER} (default)
   *          </ul>
   * @throws IllegalArgumentException
   *           if the alignment is not one of the valid values
   * 
   */
  @Override
  public final void setHorizontalAlignment(int alignment) {
	if ((alignment == LEFT) || (alignment == CENTER) || (alignment == RIGHT)) {
	  this.alignment = alignment;
	  reajustTextGap();
	} else {
	  throw new IllegalArgumentException();
	}
  }

  @Override
  public final void setIconTextGap(int iconTextGap) {}

  private void calculateMaxLabelWidth() {
	maxLabelWidth = 0;
	FontMetrics fm = getFontMetrics(getFont());
	for (SplitButton.Button button : buttons) {
	  if (button.isSeparator() == false) {
		String name = button.getName();
		maxLabelWidth = Math.max(maxLabelWidth, fm.stringWidth(name));
	  }
	}
	if (mode.equals(Mode.MENU)) {
	  maxLabelWidth = Math.max(maxLabelWidth, fm.stringWidth(getText()));
	}
  }

  @Override
  public Dimension getMinimumSize() {
	if (isMinimumSizeSet()) {
	  return super.getMinimumSize();
	}
	return calculatePreferredSize();
  }

  @Override
  public Dimension getPreferredSize() {
	if (isPreferredSizeSet()) {
	  return super.getPreferredSize();
	}
	return calculatePreferredSize();
  }

  private Dimension calculatePreferredSize() {
	Dimension dim = super.getPreferredSize();
	int width = maxLabelWidth;
	Insets insets = getInsets();
	width += insets.left + insets.right;
	width += gap + imageWidth + separatorGap + 3;
	dim.width = width;
	return dim;
  }

  @Override
  public final void setIcon(Icon defaultIcon) {}

  private String ajustName(String name) {
	if (name == null) {
	  return null;
	}
	return name.trim();
  }

  public boolean addButton(String name) {
	name = ajustName(name);
	if (name != null && name.length() > 0 && get(name) == null) {
	  buttons.add(new Button(name));
	  calculateMaxLabelWidth();
	  if (mode.equals(Mode.BUTTON) && actualButton == null) {
		setCurrentButton(name);
	  }
	  reajustTextGap();
	  return true;
	}
	return false;
  }

  public void addButton(Action action) {
	String name = (String) action.getValue(Action.NAME);
	if (addButton(name)) {
	  addActionListener(name, action);
	}
  }

  public void addButtons(List<Action> actions) {
	for (Action action : actions) {
	  addButton(action);
	}
  }

  public void removeSeparators() {
	List<SplitButton.Button> list = new ArrayList<SplitButton.Button>();
	for (SplitButton.Button button : buttons) {
	  if (button.isSeparator()) {
		list.add(button);
	  }
	}
	for (SplitButton.Button button : list) {
	  buttons.remove(button);
	}
  }

  public void removeButtons() {
	clearCurrentButton();
	buttons.clear();
	calculateMaxLabelWidth();
	reajustTextGap();
  }

  public void removeButton(Action action) {
	String name = (String) action.getValue(Action.NAME);
	removeButton(name);
  }

  public void removeButton(String name) {
	SplitButton.Button button = get(name);
	if (button != null) {
	  if (actualButton.equals(name)) {
		clearCurrentButton();
	  }
	  buttons.remove(button);
	  calculateMaxLabelWidth();
	  reajustTextGap();
	}
  }

  public void addSeparator() {
	buttons.add(new SplitButton.Button());
  }

  @Override
  public final void addActionListener(ActionListener l) {
	if (mode.equals(Mode.BUTTON) && actualButton != null) {
	  addActionListener(actualButton, l);
	}
  }

  public final void addActionListener(String name, ActionListener l) {
	SplitButton.Button button = get(name);
	if (button != null) {
	  List<ActionListener> array = button.getListeners();
	  if (array.contains(l) == false) {
		array.add(l);
		if (mode.equals(Mode.BUTTON) && actualButton != null
			&& actualButton.equals(name)) {
		  super.addActionListener(l);
		}
	  }
	}
  }

  @Override
  public final void removeActionListener(ActionListener l) {
	if (mode.equals(Mode.BUTTON) && actualButton != null) {
	  removeActionListener(actualButton, l);
	}
  }

  public final void removeActionListener(String name, ActionListener l) {
	SplitButton.Button button = get(name);
	if (button != null) {
	  List<ActionListener> array = button.getListeners();
	  array.remove(l);
	  if (actualButton.equals(name)) {
		super.removeActionListener(l);
	  }
	}
  }

  @Override
  public final ActionListener[] getActionListeners() {
	return getActionListeners(actualButton);
  }

  public final ActionListener[] getActionListeners(String name) {
	SplitButton.Button button = get(name);
	if (button != null) {
	  List<ActionListener> array = button.getListeners();
	  return array.toArray(new ActionListener[0]);
	}
	return new ActionListener[] {};
  }

  @Override
  protected final void fireActionPerformed(ActionEvent event) {
	if (fireBlocked == false) {
	  super.fireActionPerformed(event);
	}
  }

  public Mode getMode() {
	return mode;
  }

  @Override
  public final void setText(String text) {
	if (mode.equals(Mode.MENU)) {
	  super.setText(text);
	  calculateMaxLabelWidth();
	}
  }

  @Override
  protected final void paintComponent(Graphics g) {
	super.paintComponent(g);
	if (mode.equals(Mode.BUTTON)) {
	  Dimension dim = getSize();
	  Insets insets = getInsets();
	  int x = dim.width - insets.right - imageWidth - separatorGap;
	  int y = 6;
	  g.setColor(Color.white);
	  g.fillRect(x, y, 3, dim.height - (2 * y));
	  g.setColor(Color.gray);
	  g.drawLine(x + 1, y, x + 1, dim.height - y);
	}
  }

  public void setButtonEnabled(Action action, boolean enabled) {
	String name = (String) action.getValue(Action.NAME);
	setButtonEnabled(name, enabled);
  }

  public void setButtonEnabled(String name, boolean enabled) {
	SplitButton.Button button = get(name);
	if (button != null) {
	  button.setDisabled(!enabled);
	  if (enabled) {
		if (mode.equals(Mode.BUTTON) && actualButton == null) {
		  setCurrentButton(name);
		}
	  } else {
		if (mode.equals(Mode.BUTTON) && actualButton != null
			&& actualButton.equals(name)) {
		  boolean founded = false;
		  for (SplitButton.Button otherButton : buttons) {
			if (!otherButton.isDisabled()) {
			  founded = true;
			  setCurrentButton(otherButton.getName());
			  break;
			}
		  }
		  if (founded == false) {
			clearCurrentButton();
		  }
		}
	  }
	}
  }

  public void setButtonVisible(Action action, boolean visible) {
	String name = (String) action.getValue(Action.NAME);
	setButtonVisible(name, visible);
  }

  public void setButtonVisible(String name, boolean visible) {
	SplitButton.Button button = get(name);
	if (button != null) {
	  button.setVisible(visible);
	}
  }

  public boolean isButtonVisible(Action action) {
	String name = (String) action.getValue(Action.NAME);
	return isButtonVisible(name);
  }

  public boolean isButtonVisible(String name) {
	SplitButton.Button button = get(name);
	if (button != null) {
	  return button.isVisible();
	}
	return false;
  }

  public boolean isButtonEnabled(Action action) {
	String name = (String) action.getValue(Action.NAME);
	return isButtonEnabled(name);
  }

  public boolean isButtonEnabled(String name) {
	SplitButton.Button button = get(name);
	if (button != null) {
	  return !button.isDisabled();
	}
	return false;
  }

  public String getSelectedButton() {
	return actualButton;
  }

  private MouseListener getHandlerMouseListener() {
	if (handlerMouseListener == null) {
	  handlerMouseListener = new MouseListener() {
		@Override
		public void mouseClicked(MouseEvent e) {
		  originalMouseListener.mouseClicked(e);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		  mouseIn = true;
		  originalMouseListener.mouseEntered(e);
		}

		@Override
		public void mouseExited(MouseEvent e) {
		  mouseIn = false;
		  originalMouseListener.mouseExited(e);
		}

		@Override
		public void mousePressed(MouseEvent e) {
		  originalMouseListener.mousePressed(e);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		  if (mode.equals(Mode.MENU)) {
			removeActualListeners();
		  }

		  Dimension dim = getSize();
		  int x = dim.width - getInsets().right - imageWidth - separatorGap;

		  if (buttonChooserVisible == false && buttons.size() > 0
			  && (mode.equals(Mode.MENU) || e.getPoint().x > x)) {
			fireBlocked = true;
			showButtonsChooser();
		  } else {
			getButtonsChooser().setVisible(false);
			listRenderer.mouseOverIndex = -1;
			buttonChooserVisible = false;

		  }
		  originalMouseListener.mouseReleased(e);
		  fireBlocked = false;
		}
	  };
	}
	return handlerMouseListener;
  }

  public SplitButtonRenderer getRenderer() {
	return splitButtonRenderer;
  }

  public void setRenderer(SplitButtonRenderer renderer) {
	if (renderer == null) {
	  renderer = new DefaultSplitButtonRenderer();
	}
	this.splitButtonRenderer = renderer;
  }

  private class ListRenderer implements ListCellRenderer{

	public int mouseOverIndex;

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
												  int index,
												  boolean isSelected,
												  boolean cellHasFocus) {
	  Button button = (Button) value;
	  if (mouseOverIndex == index) {
		cellHasFocus = true;
	  } else {
		cellHasFocus = false;
	  }

	  String name = null;
	  if (button.isSeparator() == false) {
		name = button.getName();
	  }

	  return splitButtonRenderer.getCellRendererComponent(name,
		  button.isSeparator(), cellHasFocus, !button.isDisabled());
	}
  }

  public static enum Mode {
	BUTTON,
	MENU
  }

  private static class Button{
	private String id;
	private boolean separator;
	private boolean visible;
	private boolean disabled;
	private String name;
	private List<ActionListener> listeners;

	public Button() {
	  this(null);
	  separator = true;
	}

	public Button(String name) {
	  id = UUID.randomUUID().toString();
	  listeners = new Vector<ActionListener>();
	  this.name = name;
	  this.visible = true;
	}

	public boolean isSeparator() {
	  return separator;
	}

	public boolean isDisabled() {
	  return disabled;
	}

	public String getName() {
	  return name;
	}

	public boolean isVisible() {
	  return visible;
	}

	public List<ActionListener> getListeners() {
	  return listeners;
	}

	public void setVisible(boolean visible) {
	  this.visible = visible;
	}

	public void setDisabled(boolean disabled) {
	  this.disabled = disabled;
	}

	@Override
	public int hashCode() {
	  final int prime = 31;
	  int result = 1;
	  result = prime * result + ((id == null) ? 0 : id.hashCode());
	  return result;
	}

	@Override
	public boolean equals(Object obj) {
	  if (this == obj)
		return true;
	  if (obj == null)
		return false;
	  if (getClass() != obj.getClass())
		return false;
	  Button other = (Button) obj;
	  if (id == null) {
		if (other.id != null)
		  return false;
	  } else if (!id.equals(other.id))
		return false;
	  return true;
	}

  }

}

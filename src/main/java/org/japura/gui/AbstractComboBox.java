package org.japura.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;

/**
 * <P>
 * Copyright (C) 2011-2012 Carlos Eduardo Leite de Andrade
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
public abstract class AbstractComboBox extends JComponent{

  private static final long serialVersionUID = 5685295148232238680L;
  private Popup popup;
  private EmbeddedComponent embeddedComponent;

  private long lastViewChanged;
  private WrapperComponent wrapperComponent;
  private JComboBox comboBox;

  private String prototypeDisplayValue;

  public AbstractComboBox() {
	setFocusable(true);
	super.setLayout(new BorderLayout());
	add(getWrapperComponent(), BorderLayout.CENTER);

	addFocusListener(new FocusAdapter() {
	  @Override
	  public void focusGained(FocusEvent e) {
		getComboBox().requestFocusInWindow();
	  }
	});

	addAncestorListener(new AncestorListener() {
	  public void ancestorAdded(AncestorEvent event) {
		setPopupVisible(false);
	  }

	  public void ancestorRemoved(AncestorEvent event) {
		setPopupVisible(false);
	  }

	  public void ancestorMoved(AncestorEvent event) {
		if (event.getSource() != AbstractComboBox.this)
		  setPopupVisible(false);
	  }
	});
  }

  protected abstract JComponent getPopupComponent();

  protected final JComboBox getComboBox() {
	if (comboBox == null) {
	  comboBox = new ComboBox();
	}
	return comboBox;
  }

  @Override
  public void setBackground(Color bg) {
	getComboBox().setBackground(bg);
  }

  @Override
  public void setForeground(Color fg) {
	getComboBox().setForeground(fg);
  }

  @Override
  public Color getForeground() {
	return getComboBox().getForeground();
  }

  @Override
  public Color getBackground() {
	return getComboBox().getBackground();
  }

  private WrapperComponent getWrapperComponent() {
	if (wrapperComponent == null) {
	  MouseListener listener = new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
		  long time = Math.abs(lastViewChanged - e.getWhen());
		  if (time > 20) {
			changePopupVisible();
		  }
		}
	  };
	  wrapperComponent = new WrapperComponent(getComboBox(), listener);
	}
	return wrapperComponent;
  }

  public EmbeddedComponent removeEmbeddedComponent() {
	EmbeddedComponent ret = embeddedComponent;
	unregisterEmbeddedComponent(ret.getComponent());
	embeddedComponent = null;
	return ret;
  }

  protected abstract void unregisterEmbeddedComponent(JComponent oldEmbeddedComponent);

  protected abstract void registerEmbeddedComponent(JComponent newEmbeddedComponent);

  public void setEmbeddedComponent(EmbeddedComponent embeddedComponent) {
	if (this.embeddedComponent != null) {
	  JComponent comp = embeddedComponent.getComponent();
	  unregisterEmbeddedComponent(comp);
	}

	this.embeddedComponent = embeddedComponent;

	JComponent comp = embeddedComponent.getComponent();
	registerEmbeddedComponent(comp);
  }

  public EmbeddedComponent getEmbeddedComponent() {
	return embeddedComponent;
  }

  private void changePopupVisible() {
	setPopupVisible(!isPopupVisible());
  }

  public boolean isPopupVisible() {
	return getPopup().isVisible();
  }

  public abstract void setPopupVisible(boolean visible);

  public void setPrototypeDisplayValue(String prototypeDisplayValue) {
	this.prototypeDisplayValue = prototypeDisplayValue;
  }

  public String getPrototypeDisplayValue() {
	return prototypeDisplayValue;
  }

  @Override
  public final void setLayout(LayoutManager mgr) {}

  protected abstract void updateComboBox();

  @Override
  public void setEnabled(boolean enabled) {
	super.setEnabled(enabled);
	getWrapperComponent().setEnabled(enabled);
  }

  protected final Popup getPopup() {
	if (popup == null) {
	  popup = new Popup();
	  popup.setLayout(new BorderLayout());

	  popup.setBorder(BorderFactory.createEmptyBorder());
	  popup.add(getPopupComponent());

	  if (getEmbeddedComponent() != null) {
		Anchor anchor = getEmbeddedComponent().getAnchor();
		if (anchor == null) {
		  throw new IllegalArgumentException("Null Anchor");
		}

		JPanel embeddedPanel = new JPanel();
		embeddedPanel.setLayout(new BorderLayout());

		JComponent comp = getEmbeddedComponent().getComponent();
		embeddedPanel.add(comp);

		if (anchor.equals(Anchor.NORTH)) {
		  popup.add(embeddedPanel, BorderLayout.NORTH);
		  embeddedPanel.setBorder(BorderFactory.createMatteBorder(1, 1, 0, 1,
			  Color.black));
		} else if (anchor.equals(Anchor.SOUTH)) {
		  popup.add(embeddedPanel, BorderLayout.SOUTH);
		  embeddedPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1,
			  Color.black));
		} else {
		  throw new IllegalArgumentException(
			  "Illegal anchor. Must be NORTH or SOUTH");
		}
	  }

	  popup.addMenuKeyListener(new MenuKeyListener() {
		@Override
		public void menuKeyTyped(MenuKeyEvent e) {}

		@Override
		public void menuKeyReleased(MenuKeyEvent e) {}

		@Override
		public void menuKeyPressed(MenuKeyEvent e) {
		  if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			setPopupVisible(false);
		  } else if (e.getKeyCode() == KeyEvent.VK_TAB) {
			setPopupVisible(false);
			if (e.getModifiersEx() == KeyEvent.SHIFT_DOWN_MASK) {
			  transferFocusBackward();
			} else {
			  transferFocus();
			}
		  }
		}
	  });
	}
	return popup;
  }

  private class ComboBox extends JComboBox{

	private static final long serialVersionUID = 10000012219553L;

	@Override
	public void processKeyEvent(KeyEvent e) {
	  if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		AbstractComboBox.this.setPopupVisible(true);
	  }
	  super.processKeyEvent(e);
	}
  }

  protected class Popup extends JPopupMenu{

	private static final long serialVersionUID = -7940039384534412109L;

	public Popup() {
	  for (MenuKeyListener l : getMenuKeyListeners()) {
		removeMenuKeyListener(l);
	  }
	}

	@Override
	public void setVisible(boolean b) {
	  lastViewChanged = System.currentTimeMillis();
	  super.setVisible(b);
	  if (b == false) {
		popup = null;
	  }
	}
  }

}

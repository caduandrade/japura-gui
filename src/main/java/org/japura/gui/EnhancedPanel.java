package org.japura.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;

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
public class EnhancedPanel extends JComponent{

  private static final long serialVersionUID = 2L;
  private JRootPane root;
  private WidgetRoot widgetRoot;
  private GlassPane glassPane;

  public EnhancedPanel() {
	root = new JRootPane();
	glassPane = new GlassPane();
	widgetRoot = new WidgetRoot();

	super.setLayout(new BorderLayout());
	super.add(root);
	setLayout(new FlowLayout());
	root.getLayeredPane().add(widgetRoot, JLayeredPane.MODAL_LAYER);
	root.setGlassPane(glassPane);
  }

  @Override
  public final JRootPane getRootPane() {
	if (getParent() != null) {
	  return SwingUtilities.getRootPane(getParent());
	}
	return null;
  }

  public void setWidgetLayout(LayoutManager layoutManager) {
	widgetRoot.setLayout(layoutManager);
  }

  public LayoutManager getWidgetLayout() {
	return widgetRoot.getLayout();
  }

  public void addWidget(JComponent widget) {
	addWidget(widget, null);
  }

  public void addWidget(JComponent widget, Object constraints) {
	widgetRoot.add(widget, constraints);
  }

  public void removeWidget(JComponent widget) {
	widgetRoot.remove(widget);
  }

  @Override
  public final LayoutManager getLayout() {
	return root.getContentPane().getLayout();
  }

  @Override
  public final void setLayout(LayoutManager mgr) {
	root.getContentPane().setLayout(mgr);
  }

  @Override
  public Color getBackground() {
	return root.getContentPane().getBackground();
  }

  @Override
  public void setBackground(Color bg) {
	root.getContentPane().setBackground(bg);
  }

  @Override
  public final void doLayout() {
	super.doLayout();
	widgetRoot.setBounds(0, 0, getWidth(), getHeight());
  }

  @Override
  public final void remove(Component comp) {
	root.getContentPane().remove(comp);
  }

  @Override
  public final void remove(int index) {
	root.getContentPane().remove(index);
  }

  @Override
  public final void removeAll() {
	root.getContentPane().removeAll();
  }

  @Override
  public final Component add(Component comp) {
	return root.getContentPane().add(comp);
  }

  @Override
  public final Component add(Component comp, int index) {
	return root.getContentPane().add(comp, index);
  }

  @Override
  public final void add(Component comp, Object constraints) {
	root.getContentPane().add(comp, constraints);
  }

  @Override
  public final void add(Component comp, Object constraints, int index) {
	root.getContentPane().add(comp, constraints, index);
  }

  public void setWidgetTranslucentBackground(boolean enabled) {
	widgetRoot.setTranslucentBackgroundEnabled(enabled);
  }

  public boolean isWidgetTranslucentBackground() {
	return widgetRoot.isTranslucentBackgroundEnabled();
  }

  public boolean isEventsBlocked() {
	return glassPane.isVisible();
  }

  public void setEventsBlocked(boolean blocked) {
	glassPane.setVisible(blocked);
	glassPane.setEventsBlocked(blocked);
  }

  private static class GlassPane extends JPanel{
	private static final long serialVersionUID = 1L;

	private MouseAdapter mouseAdapter;
	private KeyAdapter keyAdapter;
	private FocusAdapter focusAdapter;

	public GlassPane() {
	  setOpaque(false);
	  mouseAdapter = new MouseAdapter() {};
	  keyAdapter = new KeyAdapter() {};
	  focusAdapter = new FocusAdapter() {
		@Override
		public void focusLost(FocusEvent e) {
		  requestFocus();
		}
	  };
	}

	public void setEventsBlocked(boolean blocked) {
	  if (blocked) {
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
		addKeyListener(keyAdapter);
		addFocusListener(focusAdapter);
		requestFocus();
	  } else {
		removeMouseListener(mouseAdapter);
		removeMouseMotionListener(mouseAdapter);
		removeKeyListener(keyAdapter);
		removeFocusListener(focusAdapter);
	  }
	}
  }

  private static class WidgetRoot extends JPanel{

	private static final long serialVersionUID = 1L;
	private boolean translucentBackgroundEnabled;

	public WidgetRoot() {
	  setLayout(new WidgetLayout());
	  setOpaque(false);
	  setBackground(new Color(135, 135, 135, 150));
	}

	public void setTranslucentBackgroundEnabled(boolean enabled) {
	  this.translucentBackgroundEnabled = enabled;
	}

	public boolean isTranslucentBackgroundEnabled() {
	  return translucentBackgroundEnabled;
	}

	@Override
	public void paintComponent(Graphics g) {
	  if (isTranslucentBackgroundEnabled()) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(getBackground());
		int width = getRootPane().getWidth();
		int height = getRootPane().getHeight();
		g2d.fill(new Rectangle2D.Double(0, 0, width, height));
	  }
	}
  }

}

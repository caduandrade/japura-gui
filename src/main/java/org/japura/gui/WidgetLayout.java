package org.japura.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;
import java.util.ArrayList;
import java.util.List;

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
public class WidgetLayout implements LayoutManager2{

  private int gap = 5;
  private List<Component> ct;
  private List<Component> cb;
  private List<Component> cc;

  private List<Component> lt;
  private List<Component> lb;
  private List<Component> lc;

  private List<Component> rt;
  private List<Component> rb;
  private List<Component> rc;

  public WidgetLayout() {
	ct = new ArrayList<Component>();
	cc = new ArrayList<Component>();
	cb = new ArrayList<Component>();

	lt = new ArrayList<Component>();
	lc = new ArrayList<Component>();
	lb = new ArrayList<Component>();

	rt = new ArrayList<Component>();
	rc = new ArrayList<Component>();
	rb = new ArrayList<Component>();
  }

  @Override
  public void addLayoutComponent(String name, Component comp) {
	addLayoutComponent(comp, null);
  }

  @Override
  public void removeLayoutComponent(Component comp) {
	ct.remove(comp);
	cc.remove(comp);
	cb.remove(comp);

	lt.remove(comp);
	lc.remove(comp);
	lb.remove(comp);

	rt.remove(comp);
	rc.remove(comp);
	rb.remove(comp);
  }

  @Override
  public Dimension preferredLayoutSize(Container parent) {
	return null;
  }

  @Override
  public Dimension minimumLayoutSize(Container parent) {
	return null;
  }

  private int center(int parent, int comp) {
	parent = parent / 2;
	comp = comp / 2;
	return parent - comp;
  }

  @Override
  public void layoutContainer(Container parent) {
	int th = 0;
	int y = 0;
	int x = 0;

	// CT
	y = gap;
	for (Component c : ct) {
	  Dimension dim = c.getPreferredSize();
	  x = center(parent.getWidth(), dim.width);
	  c.setBounds(x, y, dim.width, dim.height);
	  y += gap + dim.height;
	}

	// CC
	th = 0;
	for (Component c : cc) {
	  Dimension dim = c.getPreferredSize();
	  th += dim.height;
	}
	th += (gap * cc.size() - 1);
	y = center(parent.getHeight(), th);
	for (Component c : cc) {
	  Dimension dim = c.getPreferredSize();
	  x = center(parent.getWidth(), dim.width);
	  c.setBounds(x, y, dim.width, dim.height);
	  y += dim.height + gap;
	}

	// CB
	y = parent.getHeight() - gap;
	for (Component c : cb) {
	  Dimension dim = c.getPreferredSize();
	  y -= dim.getHeight();
	  x = center(parent.getWidth(), dim.width);
	  c.setBounds(x, y, dim.width, dim.height);
	  y -= gap;
	}

	// LT
	y = gap;
	x = gap;
	for (Component c : lt) {
	  Dimension dim = c.getPreferredSize();
	  c.setBounds(x, y, dim.width, dim.height);
	  y += gap + dim.height;
	}

	// LC
	x = gap;
	th = 0;
	for (Component c : lc) {
	  Dimension dim = c.getPreferredSize();
	  th += dim.height;
	}
	th += (gap * lc.size() - 1);
	y = center(parent.getHeight(), th);
	for (Component c : lc) {
	  Dimension dim = c.getPreferredSize();
	  c.setBounds(x, y, dim.width, dim.height);
	  y += dim.height + gap;
	}

	// LB
	x = gap;
	y = parent.getHeight() - gap;
	for (Component c : lb) {
	  Dimension dim = c.getPreferredSize();
	  y -= dim.getHeight();
	  c.setBounds(x, y, dim.width, dim.height);
	  y -= gap;
	}

	// RT
	y = gap;
	for (Component c : rt) {
	  Dimension dim = c.getPreferredSize();
	  x = parent.getWidth() - gap - dim.width;
	  c.setBounds(x, y, dim.width, dim.height);
	  y += gap + dim.height;
	}

	// RC
	th = 0;
	for (Component c : rc) {
	  Dimension dim = c.getPreferredSize();
	  th += dim.height;
	}
	th += (gap * rc.size() - 1);
	y = center(parent.getHeight(), th);
	for (Component c : rc) {
	  Dimension dim = c.getPreferredSize();
	  x = parent.getWidth() - gap - dim.width;
	  c.setBounds(x, y, dim.width, dim.height);
	  y += dim.height + gap;
	}

	// RB
	y = parent.getHeight() - gap;
	for (Component c : rb) {
	  Dimension dim = c.getPreferredSize();
	  x = parent.getWidth() - gap - dim.width;
	  y -= dim.getHeight();
	  c.setBounds(x, y, dim.width, dim.height);
	  y -= gap;
	}
  }

  private boolean contains(Component comp) {
	return cc.contains(comp) || cc.contains(comp) || cb.contains(comp)
		|| ct.contains(comp) || lt.contains(comp) || lc.contains(comp)
		|| lb.contains(comp) || rt.contains(comp) || rc.contains(comp)
		|| rb.contains(comp);
  }

  @Override
  public void addLayoutComponent(Component comp, Object constraints) {
	if (contains(comp)) {
	  return;
	}

	if (constraints == null) {
	  cc.add(comp);
	} else if (constraints instanceof String) {
	  String value = (String) constraints;
	  value = value.toUpperCase();
	  value = value.replaceAll(" ", "");
	  if (value.matches("[CLR],[CTB]") == false) {
		throw new IllegalArgumentException("Illegal constraints: " + value);
	  }
	  String[] vs = value.split(",");
	  if (vs[0].equals("C") && vs[1].equals("T")) {
		ct.add(comp);
	  } else if (vs[0].equals("C") && vs[1].equals("C")) {
		cc.add(comp);
	  } else if (vs[0].equals("C") && vs[1].equals("B")) {
		cb.add(comp);
	  } else if (vs[0].equals("L") && vs[1].equals("T")) {
		lt.add(comp);
	  } else if (vs[0].equals("L") && vs[1].equals("C")) {
		lc.add(comp);
	  } else if (vs[0].equals("L") && vs[1].equals("B")) {
		lb.add(comp);
	  } else if (vs[0].equals("R") && vs[1].equals("T")) {
		rt.add(comp);
	  } else if (vs[0].equals("R") && vs[1].equals("C")) {
		rc.add(comp);
	  } else if (vs[0].equals("R") && vs[1].equals("B")) {
		rb.add(comp);
	  }
	} else {
	  throw new IllegalArgumentException("Wrong constraints for WidgetLayout: "
		  + constraints.getClass().getSimpleName());
	}
  }

  @Override
  public Dimension maximumLayoutSize(Container target) {
	return null;
  }

  @Override
  public float getLayoutAlignmentX(Container target) {
	return 0;
  }

  @Override
  public float getLayoutAlignmentY(Container target) {
	return 0;
  }

  @Override
  public void invalidateLayout(Container target) {}

}

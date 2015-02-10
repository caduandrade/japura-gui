package org.japura.gui.model;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Copyright (C) 2010-2014 Carlos Eduardo Leite de Andrade
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
public abstract class NumberDocument<T extends Number> extends
	AbstractNumberDocument<T, Number>{

  public NumberDocument(Locale locale, boolean acceptFraction) {
	super(locale, acceptFraction);
  }

  private static final long serialVersionUID = 3L;

  protected NumberFormat getNumberFormat() {
	NumberFormat nf = NumberFormat.getInstance(getLocale());
	nf.setGroupingUsed(false);
	return nf;
  }

  @Override
  protected Number parse(String text) {
	NumberFormat n = getNumberFormat();
	try {
	  return n.parse(text);
	} catch (ParseException e) {}
	return null;
  }

  @Override
  protected String format(T value) {
	NumberFormat n = getNumberFormat();
	n.setMaximumFractionDigits(Integer.MAX_VALUE);
	return n.format(value);
  }
}

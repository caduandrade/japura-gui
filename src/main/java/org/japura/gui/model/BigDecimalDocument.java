package org.japura.gui.model;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import javax.swing.text.BadLocationException;

/**
 * Copyright (C) 2014 Carlos Eduardo Leite de Andrade
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
public class BigDecimalDocument extends
	AbstractNumberDocument<BigDecimal, BigDecimal>{

  private static final long serialVersionUID = 2L;

  public BigDecimalDocument() {
	this(null);
  }

  public BigDecimalDocument(Locale locale) {
	super(locale, true);
  }

  public BigDecimal getValue() {
	try {
	  String text = getText();
	  if (text.matches(getValidateRegex())) {
		Number number = parse(text);
		if (number != null) {
		  BigDecimal value = new BigDecimal(number.toString());
		  if (getMinValue() != null && value.compareTo(getMinValue()) < 1) {
			return null;
		  }
		  if (getMaxValue() != null && value.compareTo(getMaxValue()) > 1) {
			return null;
		  }
		  return value;
		}
	  }
	} catch (BadLocationException e) {
	  e.printStackTrace();
	}
	return null;
  }

  public void setValue(BigDecimal value) {
	String text = format(value);
	try {
	  remove(0, getLength());
	  insertString(0, text, null);
	} catch (BadLocationException e) {
	  e.printStackTrace();
	}
  }

  public BigDecimal getMaxValue() {
	return maxValue;
  }

  public void setMaxValue(BigDecimal maxValue) {
	this.maxValue = maxValue;
  }

  public BigDecimal getMinValue() {
	return minValue;
  }

  public void setMinValue(BigDecimal minValue) {
	this.minValue = minValue;
  }

  protected DecimalFormat getFormatter() {
	NumberFormat nf = NumberFormat.getInstance(getLocale());
	nf.setMaximumFractionDigits(Integer.MAX_VALUE);
	nf.setGroupingUsed(false);
	DecimalFormat df = (DecimalFormat) nf;
	df.setParseBigDecimal(true);
	return df;
  }

  @Override
  protected BigDecimal parse(String text) {
	DecimalFormat n = getFormatter();
	try {
	  return (BigDecimal) n.parseObject(text);
	} catch (ParseException e) {}
	return null;
  }

  @Override
  protected String format(BigDecimal value) {
	DecimalFormat n = getFormatter();
	return n.format(value);
  }

  @Override
  protected boolean isAcceptableValue(BigDecimal number) {
	if (getMinValue() != null && number.compareTo(getMinValue()) < 1) {
	  return false;
	}
	if (getMaxValue() != null && number.compareTo(getMaxValue()) > 1) {
	  return false;
	}
	return true;
  }

  public final String getSeparator() {
	return separator;
  }

  public final void completeWithZero() {
	if (separator == null) {
	  return;
	}
	try {
	  String text = getText(0, getLength());
	  if (text.startsWith(separator)) {
		text = "0" + text;
	  }
	  if (text.startsWith("-" + separator)) {
		text = "-0" + text.substring(1);
	  }
	  if (text.endsWith(separator)) {
		text = text + "0";
	  }
	  replace(0, getLength(), text, null);
	} catch (BadLocationException e1) {
	  e1.printStackTrace();
	}
  }

}

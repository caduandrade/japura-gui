package org.japura.gui.model;

import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

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
public abstract class AbstractNumberDocument<T extends Number, P extends Number>
	extends PlainDocument{

  private static final long serialVersionUID = 2L;
  protected String separator;
  private String validateRegex;
  private String insertRegex;
  protected T maxValue;
  private Locale locale;
  protected T minValue;

  /**
   * Constructor
   * 
   * @param locale
   *          {@link Locale} to gets the character used for decimal sign.
   * @param acceptFraction
   */
  public AbstractNumberDocument(Locale locale, boolean acceptFraction) {
	if (locale == null) {
	  locale = Locale.getDefault();
	}
	this.locale = locale;
	if (acceptFraction) {
	  DecimalFormatSymbols dfs = DecimalFormatSymbols.getInstance(locale);
	  separator = Character.toString(dfs.getDecimalSeparator());
	  validateRegex = "([-]{1,1})?[0-9]{1,}([" + separator + "][0-9]{1,})?";
	  insertRegex = "([-]{1,1})?([0-9]{1,})?([" + separator + "])?([0-9]{1,})?";
	} else {
	  validateRegex = "([-]{1,1})?[0-9]{1,}";
	  insertRegex = "([-]{1,1})?([0-9]{1,})?";
	}
  }
  
  protected final Locale getLocale() {
	return locale;
  }
  
  protected String getValidateRegex() {
	return validateRegex;
  }

  protected abstract P parse(String text);

  protected abstract String format(T value);

  protected String getText() throws BadLocationException {
	return getText(0, getLength());
  }

  protected abstract boolean isAcceptableValue(P number);

  @Override
  public void insertString(int offs, String str, AttributeSet a)
	  throws BadLocationException {
	String text = getText();
	String newText =
		text.substring(0, offs) + str + text.substring(offs, text.length());
	if (newText.matches(insertRegex)) {
	  P number = parse(newText);
	  if (number == null || (number != null && isAcceptableValue(number))) {
		super.insertString(offs, str, a);
	  }
	}
  }
}

/* $Id$
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.util.gwt.supersource.java.util;

import java.io.Serializable;

/**
 * This is a very simple variant of {@link java.util.Locale} to allow access in GWT clients.
 * 
 * @author hohwille
 * @since 1.0.0
 */
public final class Locale implements Serializable {

  /** UID for serialization. */
  private static final long serialVersionUID = 9149081749638150636L;

  /** @see java.util.Locale#ROOT */
  public static final Locale ROOT = new Locale("");

  /** @see #getDefault() */
  private static Locale defaultLocale = ROOT;

  /** @see #getLanguage() */
  private final String language;

  /** @see #getCountry() */
  private final String country;

  /** @see #getVariant() */
  private final String variant;

  /**
   * The constructor.
   * 
   * @param language is the {@link #getLanguage() language}.
   */
  public Locale(String language) {

    this(language, "", "");

  }

  /**
   * The constructor.
   * 
   * @param language is the {@link #getLanguage() language}.
   * @param country is the {@link #getCountry() country}.
   */
  public Locale(String language, String country) {

    this(language, country, "");
  }

  /**
   * The constructor.
   * 
   * @param language is the {@link #getLanguage() language}.
   * @param country is the {@link #getCountry() country}.
   * @param variant is the {@link #getVariant() variant}.
   */
  public Locale(String language, String country, String variant) {

    this.language = language.toLowerCase();
    this.country = country.toUpperCase();
    this.variant = variant;
  }

  /**
   * @return the {@link java.util.Locale#getLanguage() language}.
   */
  public String getLanguage() {

    return this.language;
  }

  /**
   * @return the {@link java.util.Locale#getCountry() country}.
   */
  public String getCountry() {

    return this.country;
  }

  /**
   * @return the {@link java.util.Locale#getVariant() variant}.
   */
  public String getVariant() {

    return this.variant;
  }

  /**
   * @return the default {@link Locale}. In this case {@link #ROOT} as there is no.
   */
  public static Locale getDefault() {

    return defaultLocale;
  }

  /**
   * @param locale is the new {@link #getDefault() default locale}.
   */
  public static void setDefault(Locale locale) {

    defaultLocale = locale;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {

    return (this.language.hashCode() << 8) ^ this.country.hashCode() ^ (this.variant.hashCode() << 4);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(Object obj) {

    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Locale other = (Locale) obj;
    if (this.country == null) {
      if (other.country != null) {
        return false;
      }
    } else if (!this.country.equals(other.country)) {
      return false;
    }
    if (this.language == null) {
      if (other.language != null) {
        return false;
      }
    } else if (!this.language.equals(other.language)) {
      return false;
    }
    if (this.variant == null) {
      if (other.variant != null) {
        return false;
      }
    } else if (!this.variant.equals(other.variant)) {
      return false;
    }
    return true;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {

    StringBuilder result = new StringBuilder(this.language);
    if (!this.country.isEmpty()) {
      result.append('_');
      result.append(this.country);
    }
    if (!this.variant.isEmpty() && (result.length() > 0)) {
      result.append('_');
      result.append(this.variant);
    }
    return result.toString();
  }
}

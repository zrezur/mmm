/* $Id$
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.util.nls.impl;

import java.util.Calendar;
import java.util.Locale;

import net.sf.mmm.util.date.Iso8601Util;

/**
 * This is an implementation of {@link net.sf.mmm.util.nls.api.NlsFormatter}
 * using {@link Iso8601Util}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
public class NlsFormatterTimeIso8601 extends AbstractNlsFormatterDate {

  /** The singleton instance. */
  public static final NlsFormatterTimeIso8601 INSTANCE = new NlsFormatterTimeIso8601();

  /**
   * The constructor.
   */
  public NlsFormatterTimeIso8601() {

    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void format(Calendar calendar, Locale locale, Appendable buffer) {

    Iso8601Util.getInstance().formatTime(calendar, true, buffer);
  }

}
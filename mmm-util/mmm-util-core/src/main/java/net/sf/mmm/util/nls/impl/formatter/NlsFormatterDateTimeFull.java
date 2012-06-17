/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.util.nls.impl.formatter;

import java.text.DateFormat;

import javax.inject.Named;
import javax.inject.Singleton;

/**
 * The {@link NlsFormatterDateTime} for {@link net.sf.mmm.util.nls.api.NlsFormatterManager#STYLE_FULL full}
 * {@link #getStyle() style}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 2.0.0
 */
@Singleton
@Named
public class NlsFormatterDateTimeFull extends NlsFormatterDateTime {

  /**
   * The constructor.
   */
  public NlsFormatterDateTimeFull() {

    super(DateFormat.FULL);
  }

}

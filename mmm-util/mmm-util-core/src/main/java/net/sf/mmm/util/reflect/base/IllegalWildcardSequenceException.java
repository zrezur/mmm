/* $Id$
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.util.reflect.base;

import net.sf.mmm.util.NlsBundleUtilCore;
import net.sf.mmm.util.nls.api.NlsRuntimeException;

/**
 * A {@link IllegalWildcardSequenceException} is thrown if a wildcard-type given
 * as string could NOT be parsed because it contains an illegal sequence (e.g.
 * "? implements X").
 * 
 * @see ReflectionUtilImpl#toType(String)
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.3
 */
public class IllegalWildcardSequenceException extends NlsRuntimeException {

  /** UID for serialization. */
  private static final long serialVersionUID = -5003374937127659992L;

  /** Key for the NLS message. */
  private static final String KEY_SEQUENCE = "sequence";

  /**
   * The constructor.
   * 
   * @param sequence is the illegal sequence that was used in a wildcard-type.
   */
  public IllegalWildcardSequenceException(String sequence) {

    super(NlsBundleUtilCore.ERR_TYPE_ILLEGAL_WILDCARD, toMap(KEY_SEQUENCE, sequence));
  }

}
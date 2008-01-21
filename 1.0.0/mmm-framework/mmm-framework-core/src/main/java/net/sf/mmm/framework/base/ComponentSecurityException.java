/* $Id$
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.framework.base;

import net.sf.mmm.framework.api.ComponentException;

/**
 * A {@link ComponentSecurityException} is thrown if a security problem occured
 * when dealing with components.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
public abstract class ComponentSecurityException extends ComponentException {

  /**
   * @see ComponentException#ComponentException(String, Object[])
   */
  public ComponentSecurityException(String internaitionalizedMessage, Object... arguments) {

    super(internaitionalizedMessage, arguments);
  }

  /**
   * @see ComponentException#ComponentException(Throwable, String, Object[])
   */
  public ComponentSecurityException(Throwable nested, String internaitionalizedMessage,
      Object... arguments) {

    super(nested, internaitionalizedMessage, arguments);
  }

}

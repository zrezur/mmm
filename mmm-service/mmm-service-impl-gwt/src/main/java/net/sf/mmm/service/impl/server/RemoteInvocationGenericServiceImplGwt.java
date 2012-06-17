/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.service.impl.server;

import javax.inject.Named;


/**
 * This class extends {@link RemoteInvocationGenericServiceImpl} with ready-to-use CDI annotations.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
@Named(RemoteInvocationGenericServiceImplGwt.CDI_NAME)
public class RemoteInvocationGenericServiceImplGwt extends RemoteInvocationGenericServiceImpl {

  /** The {@link Named name} of this component. */
  public static final String CDI_NAME = "RemoteInvocationGenericServiceImplGwt";

  /**
   * The constructor.
   */
  public RemoteInvocationGenericServiceImplGwt() {

    super();
  }

}

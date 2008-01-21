/* $Id$
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.util.reflect.pojo.api.attribute;

/**
 * This is the interface for an object that carries the {@link #getName() name}
 * of a {@link net.sf.mmm.util.reflect.pojo.api.PojoDescriptor POJO} property.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
public abstract interface PojoAttributeName {

  /**
   * This method gets the programmatic (technical) name of the according
   * property.<br>
   * E.g. for the
   * {@link net.sf.mmm.util.reflect.pojo.api.accessor.PojoPropertyAccessorNonArgMode#GET read}
   * accessor <code>public String getFooBar()</code> the property name would
   * be <code>fooBar</code>.
   * 
   * @see java.beans.PropertyDescriptor#getName()
   * 
   * @return the property name.
   */
  String getName();

}

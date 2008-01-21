/* $Id$
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.util.reflect.pojo.impl.accessor;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import net.sf.mmm.util.reflect.pojo.api.accessor.PojoPropertyAccessorIndexedOneArg;
import net.sf.mmm.util.reflect.pojo.api.accessor.PojoPropertyAccessorIndexedOneArgMode;
import net.sf.mmm.util.reflect.pojo.api.accessor.PojoPropertyAccessorNonArg;
import net.sf.mmm.util.reflect.pojo.base.accessor.AbstractPojoPropertyAccessorMethod;

/**
 * This is the implementation of the {@link PojoPropertyAccessorNonArg}
 * interface for accessing a {@link Method}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
public class PojoPropertyAccessorIndexedOneArgMethod extends AbstractPojoPropertyAccessorMethod
    implements PojoPropertyAccessorIndexedOneArg {

  /** @see #getMode() */
  private final PojoPropertyAccessorIndexedOneArgMode mode;

  /**
   * <code>false</code> if index is first argument, <code>true</code> if
   * second.
   */
  private final boolean inverted;

  /**
   * The constructor.
   * 
   * @param propertyName is the {@link #getName() name} of the property.
   * @param propertyType is the {@link #getPropertyType() generic type} of the
   *        property.
   * @param propertyClass is the {@link #getPropertyClass() raw type} of the
   *        property.
   * @param method is the {@link #getMethod() method} to access.
   * @param mode is the {@link #getMode() mode} of access.
   * @param inverted - <code>false</code> if the index is first
   *        <code>method</code>-argument, <code>true</code> if it is the
   *        second argument.
   */
  public PojoPropertyAccessorIndexedOneArgMethod(String propertyName, Type propertyType,
      Class<?> propertyClass, Method method, PojoPropertyAccessorIndexedOneArgMode mode,
      boolean inverted) {

    super(propertyName, propertyType, propertyClass, method);
    this.mode = mode;
    this.inverted = inverted;
  }

  /**
   * {@inheritDoc}
   */
  public Object invoke(Object pojoInstance, int index, Object item) throws IllegalAccessException,
      InvocationTargetException {

    Integer i = Integer.valueOf(index);
    if (this.inverted) {
      return getMethod().invoke(pojoInstance, item, i);
    } else {
      return getMethod().invoke(pojoInstance, i, item);
    }
  }

  /**
   * {@inheritDoc}
   */
  public PojoPropertyAccessorIndexedOneArgMode getMode() {

    return this.mode;
  }

}

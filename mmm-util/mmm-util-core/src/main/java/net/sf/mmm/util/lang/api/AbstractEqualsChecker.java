/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.util.lang.api;

/**
 * This is the abstract base implementation of {@link EqualsChecker}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 3.0.0
 * @param <VALUE> is the generic type of the values to {@link #isEqual(Object, Object) check}.
 */
public abstract class AbstractEqualsChecker<VALUE> implements EqualsChecker<VALUE> {

  /**
   * The constructor.
   * 
   */
  public AbstractEqualsChecker() {

    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final boolean isEqual(VALUE value1, VALUE value2) {

    if (value1 == value2) {
      return true;
    } else if ((value1 == null) || (value2 == null)) {
      return false;
    }
    return isEqualNotNull(value1, value2);
  }

  /**
   * Called from {@link #isEqual(Object, Object)} if objects are both NOT same and NOT null.
   * 
   * @param value1 is the first value to check.
   * @param value2 is the first value to check.
   * @return <code>true</code> if the given values are considered as equal, <code>false</code> otherwise.
   */
  protected abstract boolean isEqualNotNull(VALUE value1, VALUE value2);

}

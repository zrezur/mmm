/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.persistence.impl.hibernate.usertype;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import net.sf.mmm.util.component.api.NotInitializedException;
import net.sf.mmm.util.component.base.AbstractLoggableComponent;
import net.sf.mmm.util.nls.api.NlsUnsupportedOperationException;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.usertype.UserType;
import org.slf4j.Logger;

/**
 * This is the abstract base implementation of {@link UserType}.
 * 
 * @see org.hibernate.annotations.Type
 * 
 * @param <T> the generic for the adapted datatype.
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public abstract class AbstractUserType<T> extends AbstractLoggableComponent implements UserType {

  /** @see #sqlTypes */
  private final int[] sqlTypes;

  /** @see #returnedClass() */
  private final Class<T> javaType;

  /**
   * The constructor.
   * 
   * @param sqlType is the {@link #sqlTypes() SQL type} used to store the
   *        adapted datatype.
   * @param javaType is the {@link #returnedClass() java class} representing the
   *        adapted datatype.
   */
  public AbstractUserType(int sqlType, Class<T> javaType) {

    super();
    this.sqlTypes = new int[] { sqlType };
    this.javaType = javaType;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Logger getLogger() throws NotInitializedException {

    // hack to allow non component user-types
    // e.g. if used via @UserType
    if (!getInitializationState().isInitialized()) {
      initialize();
    }
    return super.getLogger();
  }

  /**
   * {@inheritDoc}
   */
  public int[] sqlTypes() {

    return this.sqlTypes;
  }

  /**
   * {@inheritDoc}
   */
  public Class<T> returnedClass() {

    return this.javaType;
  }

  /**
   * {@inheritDoc}
   */
  public boolean equals(Object x, Object y) throws HibernateException {

    if (x == null) {
      return (y == null);
    } else {
      return x.equals(y);
    }
  }

  /**
   * {@inheritDoc}
   */
  public int hashCode(Object x) throws HibernateException {

    if (x != null) {
      return x.hashCode();
    }
    return 0;
  }

  /**
   * {@inheritDoc}
   */
  public abstract T nullSafeGet(ResultSet rs, String[] names, SessionImplementor session,
      Object owner) throws SQLException;

  /**
   * {@inheritDoc}
   */
  public Object deepCopy(Object value) throws HibernateException {

    return value;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isMutable() {

    // datatypes should be immutable
    // otherwise this method has to be overridden
    return false;
  }

  /**
   * {@inheritDoc}
   */
  public Serializable disassemble(Object value) throws HibernateException {

    throw new NlsUnsupportedOperationException("disassemble");
  }

  /**
   * {@inheritDoc}
   */
  public Object assemble(Serializable cached, Object owner) throws HibernateException {

    throw new NlsUnsupportedOperationException("assemble");
  }

  /**
   * {@inheritDoc}
   */
  public Object replace(Object original, Object target, Object owner) throws HibernateException {

    throw new NlsUnsupportedOperationException("replace");
  }

}
/* $Id$
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.util.persistence.api;

import java.math.BigDecimal;

/**
 * This is the interface for a persistent entity, which is a object that is
 * stored in a database. Every non-abstract implementation of this interface is
 * simply called a (persistence) <em>entity</em>. It is typically a simple
 * Java-Bean (with some JPA annotations).<br>
 * This interface makes the following assumptions:
 * <ul>
 * <li>A {@link PersistenceEntity} is identified by a {@link #getId() primary
 * key}.</li>
 * <li>If a {@link #getModificationCounter() modification-counter} is used, it
 * should be and <code>int</code> (and no {@link java.sql.Timestamp}).</li>
 * </ul>
 * <b>ATTENTION:</b><br>
 * An entity (instance of this interface) can NOT be considered as a regular
 * java-object. It is better to think of an entity as a direct view into the
 * persistent store. Therefore you should never make modifications (e.g. invoke
 * setters) that are NOT intended to be persisted.<br>
 * Further the underlying persistence-code may create dynamic proxies for your
 * entity. Therefore you should NOT declare methods (getters and setters) as
 * <code>final</code> except you exactly know what you are doing. Additionally
 * {@link #getClass()} may return a subclass of your entity that you never
 * created.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
public interface PersistenceEntity {

  /**
   * This method gets the unique identifier (primary key) of this entity.<br>
   * The unique ID should be an immutable java-object that has a equivalent
   * string-representation that is returned by its implementation of
   * {@link Object#toString()}. Typical types of IDs are:
   * <ul>
   * <li>{@link Long}</li>
   * <li>{@link String}</li>
   * <li>{@link BigDecimal}</li>
   * <li>{@link Integer}</li>
   * </ul>
   * However if your object is identified by something else or your ID has a
   * specific format or assumption you should use a custom class as ID. This
   * will help you to distinguish your ID from regular {@link String}s or
   * {@link Number}s and allows you to implement business-logic (such as
   * validation) directly in your ID-Implementation.<br>
   * Implementations of {@link PersistenceEntity} should override this method
   * and return a more specific type for the ID.
   * 
   * @return the ID of this entity. It may be <code>null</code> if the object is
   *         {@link #isTransient() transient}.
   */
  Object getId();

  /**
   * This method determines if this entity is <em>transient</em>. A
   * {@link PersistenceEntity} is called transient, from the time it is
   * constructed (outside the persistence) until it is
   * {@link PersistenceEntityManager#save(PersistenceEntity) saved}. Otherwise,
   * after it has been saved or when it was
   * {@link PersistenceEntityManager#load(Object) loaded}, it is called
   * <em>persistent</em>.
   * 
   * @return <code>true</code> if transient, <code>false</code> if persistent.
   */
  boolean isTransient();

  /**
   * This method gets the current modification-counter of this entity. Whenever
   * the object gets modified and
   * {@link PersistenceEntityManager#save(PersistenceEntity) saved}, this
   * counter will be increased. The initial value after construction is
   * <code>0</code>.<br>
   * If this feature is NOT supported for some reason, this method should always
   * return <code>0</code>.
   * 
   * @see javax.persistence.Version
   * 
   * @return the current modification-counter.
   */
  int getModificationCounter();

}

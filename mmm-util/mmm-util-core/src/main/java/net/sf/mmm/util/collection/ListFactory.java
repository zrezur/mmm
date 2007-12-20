/* $Id$
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.util.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the interface for a {@link CollectionFactory} that guarantees to
 * {@link #create() create} a {@link List}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
public interface ListFactory extends CollectionFactory {

  /**
   * {@inheritDoc}
   */
  <E> List<E> create();

  /**
   * {@inheritDoc}
   */
  <E> List<E> create(int capacity);

  /** The default instance creating an {@link ArrayList}. */
  ListFactory INSTANCE_ARRAY_LIST = new ListFactory() {

    /**
     * {@inheritDoc}
     */
    public <E> List<E> create() {

      return new ArrayList<E>();
    }

    /**
     * {@inheritDoc}
     */
    public <E> List<E> create(int capacity) {

      return new ArrayList<E>(capacity);
    }
  };

}
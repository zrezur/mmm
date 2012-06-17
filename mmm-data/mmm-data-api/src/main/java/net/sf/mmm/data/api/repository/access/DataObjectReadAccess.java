/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.data.api.repository.access;

import net.sf.mmm.data.api.DataException;
import net.sf.mmm.data.api.DataObjectView;
import net.sf.mmm.data.api.datatype.DataId;
import net.sf.mmm.data.api.entity.resource.DataEntityResourceView;
import net.sf.mmm.util.nls.api.ObjectNotFoundException;

/**
 * This is the abstract interface for any container of content resources.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public interface DataObjectReadAccess {

  /**
   * This method gets the {@link DataObjectView} with the given <code>id</code>.
   * 
   * @see #getById(DataId)
   * 
   * @param <E> is the generic entity type of the requested object.
   * @param id is the unique identifier of the requested object.
   * @param entityClass is the java-class reflecting the type of the requested
   *        entity.
   * @return the object with the given identifier.
   * @throws DataException if no object exists with the given ID.
   */
  <E extends DataObjectView> E getById(DataId id, Class<E> entityClass) throws DataException;

  /**
   * This method gets the {@link DataObjectView} with the given <code>id</code>.
   * 
   * @see net.sf.mmm.data.api.reflection.DataReflectionService#getDataId(DataObjectView)
   * 
   * @param id is the {@link DataId} of the requested {@link DataObjectView}.
   * @return the object with the given identifier.
   * @throws ObjectNotFoundException if no object exists with the given
   *         <code>id</code>.
   */
  DataObjectView getById(DataId id) throws ObjectNotFoundException;

  /**
   * This method gets the {@link DataEntityResourceView} with the given
   * <code>{@link DataEntityResourceView#getPath() path}</code>.
   * 
   * @param path is the {@link DataEntityResourceView#getPath() path} of the
   *        requested object.
   * @return the object with the given <code>path</code>.
   * @throws DataException if the operation failed. This can have one of the
   *         following reasons:
   *         <ul>
   *         <li>the path is illegal.</li>
   *         <li>no resource exists for the given <code>path</code>.</li>
   *         </ul>
   */
  DataEntityResourceView getByPath(String path) throws DataException;

  /**
   * This method gets the {@link DataObjectView} with the given <code>path</code> .
   * 
   * @see #getByPath(String)
   * 
   * @param <E> is the generic entity type of the requested object.
   * @param path is the {@link DataEntityResourceView#getPath() path} of the
   *        requested object.
   * @param entityClass is the java-class reflecting the type of the requested
   *        entity.
   * @return the object with the given <code>path</code>.
   * @throws DataException if the operation failed. This can have one of the
   *         following reasons:
   *         <ul>
   *         <li>the path is illegal.</li>
   *         <li>no resource exists for the given <code>path</code>.</li>
   *         </ul>
   */
  <E extends DataEntityResourceView> E getByPath(String path, Class<E> entityClass)
      throws DataException;

}

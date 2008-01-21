/* $Id$
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.search.engine.api;

/**
 * This is the interface for a query to the {@link SearchEngine search-engine}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
public interface SearchQuery {

  /**
   * This method gets a string representation of this query.
   * 
   * @see SearchQueryBuilder#parseNativeQuery(String)
   * @see SearchQueryBuilder#parseStandardQuery(String, boolean)
   * 
   * @see java.lang.Object#toString()
   */
  String toString();

}

/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.client.ui.api.widget.model;

import java.util.Comparator;

import net.sf.mmm.client.ui.api.widget.UiWidgetWithValue;
import net.sf.mmm.util.value.api.PropertyAccessor;

/**
 * This is the interface for a descriptor that specifies a {@link AbstractUiTableModel#getColumn(int) column}
 * of a table-widget such as {@link net.sf.mmm.client.ui.api.widget.table.UiWidgetListTable}.<br/>
 * <b>ATTENTION:</b><br/>
 * This is an {@link net.sf.mmm.util.component.api.Api#EXTENDABLE_INTERFACE extendable interface}. Use
 * {@link AbstractUiModelTableColumn} to implement.
 * 
 * @param <ROW> is the generic type of the element representing a row of the grid. It should be a java-bean
 *        oriented object. Immutable objects (that have no setters) can also be used but only for read-only
 *        tables.
 * @param <CELL> is the generic type of the values located in the cells of this column.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 * @deprecated will be removed
 */
@Deprecated
public interface UiModelTableColumn<ROW, CELL> {

  /**
   * This method gets the {@link PropertyAccessor} used to {@link PropertyAccessor#getValue(Object) get} and
   * {@link PropertyAccessor#setValue(Object, Object) set} the value.
   * 
   * @return the {@link PropertyAccessor} for the &lt;CELL&gt;.
   */
  PropertyAccessor<ROW, CELL> getPropertyAccessor();

  /**
   * This method gets the {@link Comparator} used to {@link Comparator#compare(Object, Object) compare} and
   * sort the values of this column. If &lt;CELL&gt; implements {@link Comparable} the {@link Comparator}
   * should typically just delegate to {@link Comparable#compareTo(Object)} (unless a more specific sorting is
   * required here).
   * 
   * @return the {@link Comparator} used to sort the values. May be <code>null</code> what will disable the
   *         sorting of this column.
   */
  Comparator<CELL> getSortComparator();

  /**
   * This method creates a new {@link UiWidgetWithValue widget} to render the cell.<br/>
   * <b>ATTENTION:</b><br/>
   * For performance reason these cell widgets might be reused for cells in different rows but the same
   * column. So e.g. if a row gets deleted and a new row is added the widgets of the deleted row may be reused
   * by {@link UiWidgetWithValue#setValue(Object) setting new values}. Also sorting may work this way so that
   * the cell widgets remain in their rows but only the values change. Therefore it is important that you do
   * NOT store additional state information in your widgets (including validators, etc.).
   * 
   * @see net.sf.mmm.client.ui.api.widget.factory.UiWidgetFactoryDatatype#createForDatatype(Class)
   * 
   * @return the new {@link UiWidgetWithValue widget}.
   */
  UiWidgetWithValue<CELL> createCellWidget();

}
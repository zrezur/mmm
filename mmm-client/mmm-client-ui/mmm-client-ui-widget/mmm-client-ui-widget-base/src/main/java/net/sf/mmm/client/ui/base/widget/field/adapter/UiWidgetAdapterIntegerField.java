/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.client.ui.base.widget.field.adapter;

/**
 * This is the interface for a {@link net.sf.mmm.client.ui.base.widget.adapter.UiWidgetAdapter} adapting
 * {@link net.sf.mmm.client.ui.api.widget.field.UiWidgetIntegerField}.
 * 
 * @param <VALUE> is the generic type of the {@link #getValue() value}. Typically {@link Integer} but may also
 *        be a custom datatype.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public interface UiWidgetAdapterIntegerField<VALUE> extends UiWidgetAdapterTextualInputField<VALUE, Integer> {

  // nothing to add

}

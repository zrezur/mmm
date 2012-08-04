/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.base.widget.field.adapter;

import net.sf.mmm.ui.toolkit.api.attribute.AttributeWriteHeightInTextLines;

/**
 * This is the interface for a {@link net.sf.mmm.ui.toolkit.base.widget.adapter.UiWidgetAdapter} adapting
 * {@link net.sf.mmm.ui.toolkit.base.widget.field.AbstractUiWidgetTextArea}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 * @param <WIDGET> is the generic type of {@link #getWidget()}.
 * @param <VALUE> is the generic type of the changed value.
 */
public abstract interface UiWidgetAdapterTextAreaBase<WIDGET, VALUE> extends
    UiWidgetAdapterTextFieldBase<WIDGET, VALUE>, AttributeWriteHeightInTextLines {

  // nothing to add

}
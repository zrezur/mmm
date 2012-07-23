/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.base.widget.adapter;

import net.sf.mmm.ui.toolkit.api.widget.UiWidget;

/**
 * This is the interface for a {@link UiWidgetAdapter} adapting
 * {@link net.sf.mmm.ui.toolkit.api.widget.UiWidgetSingleComposite}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 * @param <WIDGET> is the generic type of {@link #getWidget()}.
 * @param <CHILD> is the generic type of the {@link #setChild(UiWidget) child}.
 */
public interface UiWidgetAdapterSingleComposite<WIDGET, CHILD extends UiWidget> extends UiWidgetAdapter<WIDGET> {

  /**
   * This method sets the single child of this widget. The current child will be replaced (and is therefore
   * removed from the UI).
   * 
   * @param child is the new child to set.
   */
  void setChild(CHILD child);

}

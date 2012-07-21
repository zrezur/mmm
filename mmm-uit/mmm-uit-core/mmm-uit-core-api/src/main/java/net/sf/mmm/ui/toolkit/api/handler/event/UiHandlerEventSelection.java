/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.api.handler.event;

import net.sf.mmm.ui.toolkit.api.feature.UiFeatureSelectedValue;

/**
 * This is the {@link UiHandlerEvent} for the action {@link #onSelection(UiFeatureSelectedValue, boolean)} .
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 * @param <VALUE> is the generic type of the {@link UiFeatureSelectedValue#getSelectedValues() selected
 *        values}.
 */
public interface UiHandlerEventSelection<VALUE> extends UiHandlerEvent {

  /**
   * This method is invoked if an UI object has changed its {@link UiFeatureSelectedValue#getSelectedValues()
   * selected value(s)}.
   * 
   * @param source is the object where the selection has changed.
   * @param programmatic - <code>true</code> if the
   *        {@link net.sf.mmm.ui.toolkit.api.attribute.AttributeWriteSelectedValue#setSelectedValues(java.util.List)
   *        selection was triggered by the program}, <code>false</code> if the selection was performed by the
   *        end-user (e.g by clicking the value items).
   */
  void onSelection(UiFeatureSelectedValue<VALUE> source, boolean programmatic);

}
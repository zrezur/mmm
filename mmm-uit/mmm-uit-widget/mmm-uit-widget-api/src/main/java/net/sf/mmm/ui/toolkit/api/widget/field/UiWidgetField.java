/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.api.widget.field;

import net.sf.mmm.ui.toolkit.api.feature.UiFeatureFocus;
import net.sf.mmm.ui.toolkit.api.feature.UiFeatureValue;
import net.sf.mmm.ui.toolkit.api.widget.UiWidgetRegularAtomic;

/**
 * This is the abstract interface for a {@link UiWidgetRegularAtomic regular atomic widget} that represents a
 * editable field (text field, combobox, text area, radio-buttons, checkbox, etc.).
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 * @param <VALUE> is the generic type of the {@link #getValue() value}.
 */
public abstract interface UiWidgetField<VALUE> extends UiWidgetRegularAtomic, UiFeatureValue<VALUE>, UiFeatureFocus {

  /**
   * {@inheritDoc}
   * 
   * <br/>
   * <b>ATTENTION:</b><br/>
   * If the current value entered by the user can NOT be parsed, this method will catch and ignore the
   * exception and return <code>null</code> instead. If you want to do validation and give feedback to the
   * user please use {@link #getValueOrException()} instead.
   */
  VALUE getValue();

  /**
   * This method is like {@link #getValue()} but does NOT catch exceptions while parsing the value from the
   * user input.
   * 
   * @return the current value of this widget. May be <code>null</code> if empty. If the value type is
   *         {@link String} the empty {@link String} has to be returned if no value has been entered.
   * @throws RuntimeException if the entered value is invalid (e.g. paring caused a
   *         {@link NumberFormatException}).
   */
  VALUE getValueOrException() throws RuntimeException;

  /**
   * This method gets the last value that has been {@link #setValue(Object) set}. If you invoke
   * {@link #setValue(Object)} then later calls to {@link #getValue()} will return the latest value that can
   * be modified by the end-user. This method will ensure to get the value that was set before by the program.
   * 
   * @return the original value.
   */
  VALUE getOriginalValue();

}

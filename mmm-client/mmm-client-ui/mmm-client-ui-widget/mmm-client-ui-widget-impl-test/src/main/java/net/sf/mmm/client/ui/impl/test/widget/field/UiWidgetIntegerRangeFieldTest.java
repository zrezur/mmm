/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.client.ui.impl.test.widget.field;

import net.sf.mmm.client.ui.api.UiContext;
import net.sf.mmm.client.ui.api.widget.field.UiWidgetIntegerRangeField;
import net.sf.mmm.client.ui.base.widget.factory.AbstractUiSingleWidgetFactoryNative;
import net.sf.mmm.client.ui.base.widget.field.AbstractUiWidgetIntegerRangeField;
import net.sf.mmm.client.ui.impl.test.widget.field.adapter.UiWidgetAdapterTestIntegerRangeField;

/**
 * This is the implementation of {@link UiWidgetIntegerRangeField} for testing without a native toolkit.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class UiWidgetIntegerRangeFieldTest extends
    AbstractUiWidgetIntegerRangeField<UiWidgetAdapterTestIntegerRangeField> {

  /**
   * The constructor.
   * 
   * @param context is the {@link #getContext() context}.
   */
  public UiWidgetIntegerRangeFieldTest(UiContext context) {

    super(context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected UiWidgetAdapterTestIntegerRangeField createWidgetAdapter() {

    return new UiWidgetAdapterTestIntegerRangeField();
  }

  /**
   * This inner class is the {@link AbstractUiSingleWidgetFactoryNative factory} for this widget.
   */
  public static class Factory extends AbstractUiSingleWidgetFactoryNative<UiWidgetIntegerRangeField> {

    /**
     * The constructor.
     */
    public Factory() {

      super(UiWidgetIntegerRangeField.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UiWidgetIntegerRangeField create(UiContext context) {

      return new UiWidgetIntegerRangeFieldTest(context);
    }

  }

}

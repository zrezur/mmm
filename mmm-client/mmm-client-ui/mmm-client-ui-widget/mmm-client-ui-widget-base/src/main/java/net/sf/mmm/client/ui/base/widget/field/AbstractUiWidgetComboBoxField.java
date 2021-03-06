/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.client.ui.base.widget.field;

import net.sf.mmm.client.ui.api.UiContext;
import net.sf.mmm.client.ui.api.aria.role.Role;
import net.sf.mmm.client.ui.api.aria.role.RoleCombobox;
import net.sf.mmm.client.ui.api.widget.field.UiWidgetComboBoxField;
import net.sf.mmm.client.ui.base.widget.field.adapter.UiWidgetAdapterComboBoxField;

/**
 * This is the abstract base implementation of {@link UiWidgetComboBoxField}.
 * 
 * @param <VALUE> is the generic type of the {@link #getValue() value}.
 * @param <ADAPTER> is the generic type of {@link #getWidgetAdapter()}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public abstract class AbstractUiWidgetComboBoxField<ADAPTER extends UiWidgetAdapterComboBoxField<VALUE>, VALUE> extends
    AbstractUiWidgetOptionsField<ADAPTER, VALUE> implements UiWidgetComboBoxField<VALUE> {

  /**
   * The constructor.
   * 
   * @param context is the {@link #getContext() context}.
   */
  public AbstractUiWidgetComboBoxField(UiContext context) {

    super(context);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public final RoleCombobox getAriaRole() {

    return (RoleCombobox) super.getAriaRole();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Class<? extends Role> getAriaRoleFixedType() {

    return RoleCombobox.class;
  }

}

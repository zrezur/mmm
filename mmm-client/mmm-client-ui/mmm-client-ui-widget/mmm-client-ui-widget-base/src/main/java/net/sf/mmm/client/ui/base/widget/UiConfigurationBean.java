/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.client.ui.base.widget;

import net.sf.mmm.util.lang.api.HorizontalAlignment;

/**
 * This is an implementation of {@link net.sf.mmm.client.ui.api.widget.UiConfiguration} as Java bean.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class UiConfigurationBean extends AbstractUiConfiguration {

  /**
   * The constructor.
   */
  public UiConfigurationBean() {

    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setTheme(String theme) {

    super.setTheme(theme);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setValidationFailureAlignment(HorizontalAlignment validationFailureAlignment) {

    super.setValidationFailureAlignment(validationFailureAlignment);
  }

}
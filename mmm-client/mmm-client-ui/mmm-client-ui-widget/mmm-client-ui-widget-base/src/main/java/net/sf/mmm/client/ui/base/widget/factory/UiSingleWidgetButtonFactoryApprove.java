/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.client.ui.base.widget.factory;

import net.sf.mmm.client.ui.api.UiContext;
import net.sf.mmm.client.ui.api.event.UiEventClick;
import net.sf.mmm.client.ui.api.handler.event.UiHandlerEventClick;
import net.sf.mmm.client.ui.api.handler.plain.UiHandlerPlain;
import net.sf.mmm.client.ui.api.handler.plain.UiHandlerPlainApprove;
import net.sf.mmm.client.ui.api.widget.core.UiWidgetButton;

/**
 * This is the {@link net.sf.mmm.client.ui.api.widget.factory.UiSingleWidgetButtonFactory} for
 * {@link UiHandlerPlainApprove approve} {@link UiWidgetButton buttons}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class UiSingleWidgetButtonFactoryApprove extends AbstractUiSingleWidgetButtonFactory<UiHandlerPlainApprove> {

  /**
   * The constructor.
   */
  public UiSingleWidgetButtonFactoryApprove() {

    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Class<UiHandlerPlainApprove> getHandlerInterface() {

    return UiHandlerPlainApprove.class;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isInstance(UiHandlerPlain handler) {

    return (handler instanceof UiHandlerPlainApprove);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UiWidgetButton create(UiContext context, final UiHandlerPlainApprove handler,
      boolean preventConfirmationPopup, final Object variant) {

    UiHandlerEventClick clickHandler = new UiHandlerEventClick() {

      @Override
      public void onClick(UiEventClick event) {

        handler.onApprove(variant);
      }
    };
    return createButton(context, getBundle().labelApprove(), clickHandler, null, null);
  }

}

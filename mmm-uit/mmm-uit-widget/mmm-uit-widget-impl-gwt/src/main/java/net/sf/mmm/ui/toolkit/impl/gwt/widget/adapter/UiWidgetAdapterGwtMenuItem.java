/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.impl.gwt.widget.adapter;

import net.sf.mmm.ui.toolkit.api.feature.UiFeatureClick;
import net.sf.mmm.ui.toolkit.api.handler.event.UiHandlerEventClick;
import net.sf.mmm.ui.toolkit.base.widget.adapter.UiWidgetAdapterMenuItem;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.MenuItem;

/**
 * This is the implementation of {@link UiWidgetAdapterMenuItem} using GWT based on {@link MenuItem}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class UiWidgetAdapterGwtMenuItem extends UiWidgetAdapterGwtMenuItemBase implements
    UiWidgetAdapterMenuItem<MenuItem>, Command {

  /** @see #setClickEventSender(UiFeatureClick, UiHandlerEventClick) */
  private UiFeatureClick source;

  /** @see #setClickEventSender(UiFeatureClick, UiHandlerEventClick) */
  private UiHandlerEventClick clickEventSender;

  /**
   * The constructor.
   */
  public UiWidgetAdapterGwtMenuItem() {

    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected MenuItem createWidget() {

    return new MenuItem("", this);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void execute() {

    if (this.clickEventSender != null) {
      this.clickEventSender.onClick(this.source, false);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setClickEventSender(UiFeatureClick eventSource, UiHandlerEventClick eventSender) {

    this.source = eventSource;
    this.clickEventSender = eventSender;
  }

}

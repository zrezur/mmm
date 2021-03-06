/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.client.ui.impl.gwt.widget.window.adapter;

import net.sf.mmm.client.ui.api.common.Length;
import net.sf.mmm.client.ui.api.widget.UiWidgetRegular;
import net.sf.mmm.client.ui.base.widget.window.adapter.UiWidgetAdapterBaseWindow;
import net.sf.mmm.client.ui.impl.gwt.widget.adapter.UiWidgetAdapterGwtDynamicComposite;

import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This is the implementation of {@link UiWidgetAdapterBaseWindow} using GWT based on {@link Panel}.<br/>
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 * @param <WIDGET> is the generic type of {@link #getToplevelWidget()}.
 */
public abstract class UiWidgetAdapterGwtBaseWindow<WIDGET extends Panel> extends
    UiWidgetAdapterGwtDynamicComposite<WIDGET, UiWidgetRegular> implements UiWidgetAdapterBaseWindow {

  /** The container for the children. */
  private final VerticalPanel contentPanel;

  /**
   * The constructor.
   */
  public UiWidgetAdapterGwtBaseWindow() {

    super();
    this.contentPanel = new VerticalPanel();
    this.contentPanel.setWidth("100%");
    getToplevelWidget().add(this.contentPanel);
  }

  /**
   * @return the content panel.
   */
  protected VerticalPanel getContentPanel() {

    return this.contentPanel;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {

    // TODO Auto-generated method stub

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void addChild(UiWidgetRegular child, int index) {

    if (index >= 0) {
      this.contentPanel.insert(getToplevelWidget(child), index);
    } else {
      this.contentPanel.add(getToplevelWidget(child));
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setWidth(Length width) {

    this.contentPanel.setWidth(width.toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setHeight(Length height) {

    this.contentPanel.setHeight(height.toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setSize(Length width, Length height) {

    this.contentPanel.setSize(width.toString(), height.toString());
  }

}

/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.client.ui.impl.gwt.widget.adapter;

import net.sf.mmm.client.ui.api.widget.UiWidget;
import net.sf.mmm.client.ui.base.widget.AbstractUiWidget;
import net.sf.mmm.client.ui.base.widget.adapter.AbstractUiWidgetAdapter;
import net.sf.mmm.util.nls.api.NlsClassCastException;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.ui.UIObject;
import com.google.gwt.user.client.ui.Widget;

/**
 * This is the implementation of {@link net.sf.mmm.client.ui.base.widget.adapter.UiWidgetAdapter} using GWT
 * based on {@link UIObject}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 * @param <WIDGET> is the generic type of {@link #getToplevelWidget()}.
 */
public abstract class UiWidgetAdapterGwt<WIDGET extends UIObject> extends AbstractUiWidgetAdapter<WIDGET> {

  /**
   * The constructor.
   */
  public UiWidgetAdapterGwt() {

    super();
  }

  /**
   * This method is a simplified variant of {@link #getToplevelWidget(UiWidget, Class)}.
   * 
   * @param widget is the {@link UiWidget} to "unwrap".
   * @return the native widget.
   */
  protected static final Widget getToplevelWidget(UiWidget widget) {

    return getToplevelWidget(widget, Widget.class);
  }

  /**
   * This method gets the
   * {@link net.sf.mmm.client.ui.api.widget.UiWidgetFactory#getNativeWidget(net.sf.mmm.client.ui.api.widget.UiWidgetRegular)
   * native widget} of the given <code>widget</code>.
   * 
   * @param <W> is the generic type of <code>widgetType</code>.
   * @param widget is the {@link UiWidget} to "unwrap".
   * @param widgetType is the expected type of the native widget.
   * @return the native widget.
   */
  @SuppressWarnings("unchecked")
  protected static final <W extends UIObject> W getToplevelWidget(UiWidget widget, Class<W> widgetType) {

    Object nativeWidget = AbstractUiWidget.getWidgetAdapter(widget).getToplevelWidget();
    try {
      return (W) nativeWidget;
    } catch (ClassCastException e) {
      throw new NlsClassCastException(e, widget, widgetType);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UIObject getActiveWidget() {

    return getToplevelWidget();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setId(String id) {

    Element element = getToplevelWidget().getElement();
    if (id == null) {
      element.removeAttribute(HTML_ATTRIBUTE_ID);
    } else {
      element.setAttribute(HTML_ATTRIBUTE_ID, id);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setTooltip(String tooltip) {

    getToplevelWidget().setTitle(tooltip);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setAttribute(String name, String value) {

    Element element = getToplevelWidget().getElement();
    if (value == null) {
      element.removeAttribute(name);
    } else {
      element.setAttribute(name, value);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setVisible(boolean visible) {

    getToplevelWidget().setVisible(visible);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setStyles(String styles) {

    getToplevelWidget().setStyleName(styles);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getWidth() {

    // not supported by GWT
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getHeight() {

    // not supported by GWT
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setWidth(String width) {

    getToplevelWidget().setWidth(width);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setHeight(String height) {

    getToplevelWidget().setHeight(height);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setSize(String width, String height) {

    getToplevelWidget().setSize(width, height);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void dispose() {

    // do nothing...
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String createAbsoluteImageUrl(String relativePath) {

    return GWT.getModuleBaseURL() + "gwt/" + getUiWidget().getContext().getConfiguration().getTheme() + "/images/"
        + relativePath;
  }

}

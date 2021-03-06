/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.client.ui.base.widget.adapter;

import net.sf.mmm.client.ui.NlsBundleClientUiRoot;
import net.sf.mmm.client.ui.api.UiContext;
import net.sf.mmm.client.ui.api.attribute.AttributeReadAccessKey;
import net.sf.mmm.client.ui.api.attribute.AttributeReadAltText;
import net.sf.mmm.client.ui.api.attribute.AttributeReadColumnSpan;
import net.sf.mmm.client.ui.api.attribute.AttributeReadFocused;
import net.sf.mmm.client.ui.api.attribute.AttributeReadHeightInRows;
import net.sf.mmm.client.ui.api.attribute.AttributeReadImage;
import net.sf.mmm.client.ui.api.attribute.AttributeReadLabel;
import net.sf.mmm.client.ui.api.attribute.AttributeReadMaximumTextLength;
import net.sf.mmm.client.ui.api.attribute.AttributeReadResizable;
import net.sf.mmm.client.ui.api.attribute.AttributeReadSelectionMode;
import net.sf.mmm.client.ui.api.attribute.AttributeReadUrl;
import net.sf.mmm.client.ui.api.attribute.AttributeReadValidationFailure;
import net.sf.mmm.client.ui.api.common.Length;
import net.sf.mmm.client.ui.api.common.SelectionMode;
import net.sf.mmm.client.ui.api.widget.UiWidgetComposite;
import net.sf.mmm.client.ui.api.widget.core.UiWidgetImage;
import net.sf.mmm.client.ui.api.widget.core.UiWidgetLabel;
import net.sf.mmm.client.ui.base.widget.AbstractUiWidget;
import net.sf.mmm.client.ui.base.widget.core.AbstractUiWidgetLabel;
import net.sf.mmm.util.component.api.AlreadyInitializedException;
import net.sf.mmm.util.lang.api.attribute.AttributeReadTitle;
import net.sf.mmm.util.nls.api.NlsAccess;
import net.sf.mmm.util.nls.api.NlsUnsupportedOperationException;

/**
 * This is the abstract base implementation of {@link UiWidgetAdapter}.
 * 
 * @param <WIDGET> is the generic type of {@link #getToplevelWidget()}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public abstract class AbstractUiWidgetAdapter<WIDGET> implements UiWidgetAdapter, AttributeReadAltText,
    AttributeReadUrl, AttributeReadLabel, AttributeReadTitle<String>, AttributeReadImage<UiWidgetImage>,
    AttributeReadValidationFailure, AttributeReadAccessKey, AttributeReadFocused, AttributeReadMaximumTextLength,
    AttributeReadHeightInRows, AttributeReadColumnSpan, AttributeReadResizable, AttributeReadSelectionMode {

  /** @see #getToplevelWidget() */
  private final WIDGET toplevelWidget;

  /** @see #getUiWidget() */
  private AbstractUiWidget<?> uiWidget;

  /**
   * The constructor.
   */
  public AbstractUiWidgetAdapter() {

    super();
    this.toplevelWidget = createToplevelWidget();
  }

  /**
   * This method creates the {@link #getToplevelWidget() underlying widget}.<br/>
   * <b>ATTENTION:</b><br/>
   * This method is called from the constructor but implemented in sub-classes. You should NOT access or even
   * modify member variables as they are NOT set at this point (even final members). However, this design is
   * done by intention instead of passing the widget as constructor argument to give more flexibility by
   * overriding and also for potential lazy initialization of the widget.
   * 
   * @return a new instance of the {@link #getToplevelWidget() underlying widget}.
   */
  protected abstract WIDGET createToplevelWidget();

  /**
   * {@inheritDoc}
   */
  @Override
  public WIDGET getToplevelWidget() {

    return this.toplevelWidget;
  }

  /**
   * This method gets the active widget. For simple cases this will be the same as
   * {@link #getToplevelWidget()}. In more complex scenarios the {@link #getToplevelWidget() toplevel widget}
   * may only be a container widget and this method returns a child that represents the active part.
   * 
   * @return the active widget.
   */
  public Object getActiveWidget() {

    return this.toplevelWidget;
  }

  /**
   * @return the uiWidget
   */
  public AbstractUiWidget<?> getUiWidget() {

    return this.uiWidget;
  }

  /**
   * @param uiWidget is the uiWidget to set
   */
  public void setUiWidget(AbstractUiWidget<?> uiWidget) {

    if (this.uiWidget != null) {
      throw new AlreadyInitializedException();
    }
    this.uiWidget = uiWidget;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setParent(UiWidgetComposite<?> parent) {

    // do nothing by default...
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getId() {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SelectionMode getSelectionMode() {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setMode(boolean editMode) {

    // do nothing by default
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setSize(Length width, Length height) {

    setWidth(width);
    setHeight(height);
  }

  /**
   * @return the instance of {@link NlsBundleClientUiRoot}.
   */
  protected NlsBundleClientUiRoot getBundle() {

    return NlsAccess.getBundleFactory().createBundle(NlsBundleClientUiRoot.class);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTooltip() {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isVisible() {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isEnabled() {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getStyles() {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isDisposed() {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getAltText() {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getUrl() {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getLabel() {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getTitle() {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UiWidgetImage getImage() {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getValidationFailure() {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getAttribute(String name) {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public char getAccessKey() {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getMaximumTextLength() {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getHeightInRows() {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isResizable() {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int getColumnSpan() {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getPrimaryStyle() {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasStyle(String style) {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean addStyle(String style) {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean removeStyle(String style) {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setPrimaryStyle(String primaryStyle) {

    throw new NlsUnsupportedOperationException();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isStyleDeltaSupported() {

    // false by default
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isFocused() {

    // dummy, will never be called in adapter (only in widget)...
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public AbstractUiWidgetLabel<?> createLabel(UiContext context) {

    return (AbstractUiWidgetLabel<?>) context.getWidgetFactory().create(UiWidgetLabel.class);
  }

}

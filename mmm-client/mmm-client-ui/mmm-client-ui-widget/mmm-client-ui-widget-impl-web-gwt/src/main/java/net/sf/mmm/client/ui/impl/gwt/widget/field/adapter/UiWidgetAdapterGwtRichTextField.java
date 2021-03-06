/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.client.ui.impl.gwt.widget.field.adapter;

import net.sf.mmm.client.ui.base.widget.field.adapter.UiWidgetAdapterRichTextField;
import net.sf.mmm.client.ui.impl.gwt.widget.field.adapter.UiWidgetAdapterGwtRichTextField.MyRichTextArea;

import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.RichTextArea;

/**
 * This is the implementation of {@link UiWidgetAdapterRichTextField} using GWT based on {@link RichTextArea}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class UiWidgetAdapterGwtRichTextField extends UiWidgetAdapterGwtTextAreaBase<MyRichTextArea> implements
    UiWidgetAdapterRichTextField {

  /**
   * The constructor.
   */
  public UiWidgetAdapterGwtRichTextField() {

    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected MyRichTextArea createActiveWidget() {

    return new MyRichTextArea();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setHeightInRows(int rows) {

    getToplevelWidget().setHeight(rows + "em");
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setMaximumTextLength(int length) {

    getToplevelWidget().getElement().setAttribute("maxlength", Integer.toString(length));
  }

  /**
   * This inner class makes {@link RichTextArea} implement {@link HasValue}.
   */
  public static class MyRichTextArea extends RichTextArea implements HasValue<String>, HasChangeHandlers {

    /**
     * The constructor.
     */
    public MyRichTextArea() {

      super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HandlerRegistration addValueChangeHandler(ValueChangeHandler<String> handler) {

      // TODO Auto-generated method stub
      return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HandlerRegistration addChangeHandler(ChangeHandler handler) {

      // TODO Auto-generated method stub
      return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getValue() {

      return getHTML();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(String value) {

      setHTML(value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setValue(String value, boolean fireEvents) {

      setHTML(value);
    }

  }

}

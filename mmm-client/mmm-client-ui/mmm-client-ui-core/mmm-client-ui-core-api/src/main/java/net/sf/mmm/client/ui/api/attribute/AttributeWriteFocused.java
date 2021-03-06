/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.client.ui.api.attribute;

/**
 * This interface gives read and write access to the {@link #isFocused() focused} attribute of an object.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public abstract interface AttributeWriteFocused extends AttributeReadFocused {

  /**
   * This method sets the {@link #isFocused() focus} of this object.<br/>
   * <b>NOTE:</b><br/>
   * You can only set the {@link #isFocused() focus}. To actually remove it, you need to set it in a different
   * widget.
   */
  void setFocused();

}

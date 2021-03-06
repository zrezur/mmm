/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.client.ui.api.common;

/**
 * This interface is used as collection of constants with the names of central image icons.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public interface IconConstants {

  /**
   * The name of the icon for a validation error. The icon is typically showing a red cross but smaller than
   * {@link #ICON_MESSAGE_ERROR}.
   * 
   * @see net.sf.mmm.client.ui.api.attribute.AttributeReadValidationFailure#getValidationFailure()
   */
  String ICON_VALIDATION_FAILURE = "validation-failure.png";

  /**
   * The name of the icon for an error message. The icon is typically showing a red cross.
   * 
   * @see MessageSeverity#ERROR
   */
  String ICON_MESSAGE_ERROR = "message-error.png";

  /**
   * The name of the icon for an warning message. The icon is typically showing a yellow triangle with an
   * exclamation mark.
   * 
   * @see MessageSeverity#WARNING
   */
  String ICON_MESSAGE_WARNING = "message-warning.png";

  /**
   * The name of the icon for an error message. The icon is typically showing a balloon.
   * 
   * @see MessageSeverity#INFORMATION
   */
  String ICON_MESSAGE_INFORMATION = "message-information.png";

  /**
   * The name of the icon for a question message. The icon is typically showing a question mark.
   * 
   * @see MessageSeverity#QUESTION
   */
  String ICON_MESSAGE_QUESTION = "message-question.png";

  /**
   * The name of the icon for the next button. The icon is typically showing an arrow to the right.
   */
  String ICON_BUTTON_NEXT = "next.png";

  /**
   * The name of the icon for the previous button. The icon is typically showing an arrow to the left.
   */
  String ICON_BUTTON_PREVIOUS = "previous.png";

  /**
   * The name of the icon for the up button. The icon is typically showing an arrow up.
   */
  String ICON_BUTTON_UP = "up.png";

  /**
   * The name of the icon for the down button. The icon is typically showing an arrow down.
   */
  String ICON_BUTTON_DOWN = "down.png";

}

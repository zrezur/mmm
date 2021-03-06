/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.client.ui.api.handler.plain;

/**
 * This is the {@link UiHandlerPlain} for the action {@link #onRemove(Object) remove}.
 * 
 * @see UiHandlerPlainDelete
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public interface UiHandlerPlainRemove extends UiHandlerPlain {

  /**
   * This method is invoked for the action <em>remove</em>. This means that something is removed. Unlike
   * {@link UiHandlerPlainDelete#onDelete(Object) delete} this operation will NOT (immediately) perform a persistent
   * change. E.g. this action is appropriate for removing entries from a list locally while the user still has
   * the option to {@link UiHandlerPlainSave#onSave(Object) save} or {@link UiHandlerPlainCancel#onCancel(Object)
   * cancel} his changes.
   * 
   * @param variant is optional the {@link net.sf.mmm.util.lang.api.Variant} to use.
   */
  void onRemove(Object variant);

}

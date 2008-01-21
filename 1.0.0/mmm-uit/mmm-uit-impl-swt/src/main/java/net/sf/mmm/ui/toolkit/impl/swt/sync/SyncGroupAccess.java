/* $Id$
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.impl.swt.sync;

import org.eclipse.swt.widgets.Group;

import net.sf.mmm.ui.toolkit.impl.swt.UIFactorySwt;

/**
 * This class is used for synchronous access on a SWT
 * {@link org.eclipse.swt.widgets.Group}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
public class SyncGroupAccess extends AbstractSyncCompositeAccess {

  /**
   * operation to set the
   * {@link org.eclipse.swt.widgets.Group#setText(String) text} of the group.
   */
  protected static final String OPERATION_SET_TEXT = "setText";

  /** the group to access */
  private Group group;

  /** the text of the group */
  private String text;

  /**
   * The constructor.
   * 
   * @param uiFactory is used to do the synchronization.
   * @param swtStyle is the
   *        {@link org.eclipse.swt.widgets.Widget#getStyle() style} of the
   *        group.
   */
  public SyncGroupAccess(UIFactorySwt uiFactory, int swtStyle) {

    this(uiFactory, swtStyle, null);
  }

  /**
   * The constructor.
   * 
   * @param uiFactory is used to do the synchronization.
   * @param swtStyle is the
   *        {@link org.eclipse.swt.widgets.Widget#getStyle() style} of the
   *        group.
   * @param swtGroup is the group to access.
   */
  public SyncGroupAccess(UIFactorySwt uiFactory, int swtStyle, Group swtGroup) {

    super(uiFactory, swtStyle);
    this.group = swtGroup;
    this.text = null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Group getSwtObject() {

    return this.group;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void performSynchron(String operation) {

    if (operation == OPERATION_SET_TEXT) {
      this.group.setText(this.text);
    } else {
      super.performSynchron(operation);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void createSynchron() {

    this.group = new Group(getParent(), getStyle());
    if (this.text != null) {
      this.group.setText(this.text);
    }
    super.createSynchron();
  }

  /**
   * This method sets the {@link Group#setText(String) text} of the group.
   * 
   * @param newText is the text to set.
   */
  public void setText(String newText) {

    assert (checkReady());
    this.text = newText;
    invoke(OPERATION_SET_TEXT);
  }

  /**
   * This method gets the {@link Group#getText() text} of the group.
   * 
   * @return the text.
   */
  public String getText() {

    return this.text;
  }

}

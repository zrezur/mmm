/* $Id$
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.impl.swt.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;

import net.sf.mmm.ui.toolkit.api.view.menu.UiMenuItem;
import net.sf.mmm.ui.toolkit.api.view.widget.ButtonStyle;
import net.sf.mmm.ui.toolkit.impl.swt.UiFactorySwt;
import net.sf.mmm.ui.toolkit.impl.swt.UiSwtNode;
import net.sf.mmm.ui.toolkit.impl.swt.sync.SyncMenuItemAccess;

/**
 * This class is the implementation of the
 * {@link net.sf.mmm.ui.toolkit.api.view.menu.UiMenuItem} interface using SWT as the
 * UI toolkit.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
public class UIMenuItemImpl extends UiSwtNode implements UiMenuItem {

  /** the SWT menu item */
  private final MenuItem menuItem;

  /** the synchronous access to the menu-item */
  private final SyncMenuItemAccess syncAccess;

  /** the style */
  private final ButtonStyle style;

  /**
   * The constructor.
   * 
   * @param uiFactory is the UIFactorySwt instance.
   * @param parentObject is the parent of this object (may be <code>null</code>).
   * @param text is the {@link #getValue() text} of the menu item.
   * @param itemStyle is the style defining how the item is visualized and
   *        behaves.
   * @param item is the actual SWT menu-item.
   */
  public UIMenuItemImpl(UiFactorySwt uiFactory, UIMenuImpl parentObject, String text,
      ButtonStyle itemStyle, MenuItem item) {

    super(uiFactory, parentObject);
    int swtStyle = UiFactorySwt.convertButtonStyleForMenuItem(itemStyle);
    this.syncAccess = new SyncMenuItemAccess(uiFactory, swtStyle, item, text);
    this.style = itemStyle;
    this.menuItem = item;
  }

  /**
   * This method gets the unwrapped menu item.
   * 
   * @return the menu item.
   */
  protected MenuItem getItem() {

    return this.menuItem;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected boolean doInitializeListener() {

    this.syncAccess.addListener(SWT.Selection, createSwtListener());
    return true;
  }

  /**
   * {@inheritDoc}
   */
  public String getValue() {

    return this.syncAccess.getText();
  }

  /**
   * {@inheritDoc}
   */
  public String getType() {

    return TYPE;
  }

  /**
   * {@inheritDoc}
   */
  public ButtonStyle getButtonStyle() {

    return this.style;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isSelected() {

    return this.syncAccess.isSelected();
  }

  /**
   * {@inheritDoc}
   */
  public void setSelected(boolean selected) {

    this.syncAccess.setSelected(selected);
  }

}
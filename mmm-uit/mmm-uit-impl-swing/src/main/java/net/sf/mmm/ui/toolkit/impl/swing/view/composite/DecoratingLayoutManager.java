/* $Id$
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.impl.swing.view.composite;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager2;

import net.sf.mmm.ui.toolkit.api.view.composite.UiDecoratedComponent;
import net.sf.mmm.ui.toolkit.base.view.composite.AbstractDecoratingLayoutManager;
import net.sf.mmm.ui.toolkit.base.view.composite.Size;

/**
 * This is the layout-manager that organizes the layout for
 * {@link UiDecoratedComponent decorated components}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
class DecoratingLayoutManager extends AbstractDecoratingLayoutManager implements LayoutManager2 {

  /**
   * The constructor.
   * 
   * @param decoratedComponent is the decorated component to layout.
   */
  public DecoratingLayoutManager(UiDecoratedComponent<?, ?, ?> decoratedComponent) {

    super(decoratedComponent);
  }

  /**
   * {@inheritDoc}
   */
  public void layoutContainer(Container parent) {

    doLayout(new Size(parent.getWidth(), parent.getHeight()));
  }

  /**
   * {@inheritDoc}
   */
  public Dimension preferredLayoutSize(Container parent) {

    Size preferredSize = calculateSize();
    return new Dimension(preferredSize.width, preferredSize.height);
  }

  /**
   * {@inheritDoc}
   */
  public void addLayoutComponent(Component comp, Object constraints) {

    // nothing to do...
  }

  /**
   * {@inheritDoc}
   */
  public void addLayoutComponent(String name, Component comp) {

    // nothing to do...
  }

  /**
   * {@inheritDoc}
   */
  public void invalidateLayout(Container target) {

    // nothing to do...
  }

  /**
   * {@inheritDoc}
   */
  public void removeLayoutComponent(Component comp) {

    // nothing to do...
  }

  /**
   * {@inheritDoc}
   */
  public Dimension minimumLayoutSize(Container parent) {

    return preferredLayoutSize(parent);
  }

  /**
   * {@inheritDoc}
   */
  public Dimension maximumLayoutSize(Container target) {

    return new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
  }

  /**
   * {@inheritDoc}
   */
  public float getLayoutAlignmentX(Container target) {

    return 0.5F;
  }

  /**
   * {@inheritDoc}
   */
  public float getLayoutAlignmentY(Container target) {

    return 0.5F;
  }

}
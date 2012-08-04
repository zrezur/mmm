/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.ui.toolkit.base.widget;

import net.sf.mmm.ui.toolkit.api.common.UiMode;
import net.sf.mmm.ui.toolkit.api.widget.UiWidget;
import net.sf.mmm.ui.toolkit.api.widget.UiWidgetComposite;
import net.sf.mmm.ui.toolkit.base.widget.adapter.UiWidgetAdapter;

/**
 * This is the abstract base implementation of {@link UiWidgetComposite}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 * @param <ADAPTER> is the generic type of {@link #getWidgetAdapter()}.
 * @param <CHILD> is the generic type of the {@link #getChild(int) children}.
 */
public abstract class AbstractUiWidgetComposite<ADAPTER extends UiWidgetAdapter<?>, CHILD extends UiWidget> extends
    AbstractUiWidget<ADAPTER> implements UiWidgetComposite<CHILD> {

  /**
   * The constructor.
   * 
   * @param factory is the {@link #getFactory() factory}.
   */
  public AbstractUiWidgetComposite(AbstractUiWidgetFactory<?> factory) {

    super(factory);
  }

  /**
   * {@inheritDoc}
   * 
   * <b>ATTENTION:</b><br/>
   * For ultimate flexibility we did NOT declare this method as final here. However you should only override
   * this if you are really sure what you are doing.
   */
  @Override
  public boolean isModifiedRecursive() {

    int childCount = getChildCount();
    for (int i = 0; i < childCount; i++) {
      CHILD child = getChild(i);
      if (child.isModified()) {
        return true;
      }
    }
    return super.isModified();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  void setModeRecursive(UiMode mode) {

    super.setModeRecursive(mode);
    int childCount = getChildCount();
    for (int i = 0; i < childCount; i++) {
      CHILD child = getChild(i);
      child.setMode(mode);
    }
  }

}
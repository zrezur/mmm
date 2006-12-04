/* $Id$ */
package net.sf.mmm.gui.view.content.api;

import net.sf.mmm.ui.toolkit.api.UIFactory;
import net.sf.mmm.ui.toolkit.api.composite.UIComposite;

/**
 * TODO: this class ...
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
public interface ContentModelEditorView {

  /**
   * This method creates the view for the editor used to view and modify the
   * content-model.
   * 
   * @param uiFactory
   *        is the UI-factory used to create the view.
   * 
   * @return the view.
   */
  UIComposite create(UIFactory uiFactory);

}

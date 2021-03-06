/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.client.ui.impl.gwt.widget.complex.adapter;

import java.awt.Checkbox;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import net.sf.mmm.client.ui.api.common.Length;
import net.sf.mmm.client.ui.api.common.SelectionMode;
import net.sf.mmm.client.ui.api.event.EventType;
import net.sf.mmm.client.ui.api.feature.UiFeatureEvent;
import net.sf.mmm.client.ui.api.handler.event.UiHandlerEvent;
import net.sf.mmm.client.ui.api.widget.UiWidget;
import net.sf.mmm.client.ui.api.widget.complex.UiWidgetAbstractTree.UiTreeModel;
import net.sf.mmm.client.ui.api.widget.complex.UiWidgetAbstractTree.UiTreeNodeRenderer;
import net.sf.mmm.client.ui.api.widget.complex.UiWidgetTree;
import net.sf.mmm.client.ui.base.widget.complex.adapter.UiWidgetAdapterTree;
import net.sf.mmm.client.ui.impl.gwt.handler.event.EventAdapterGwt;
import net.sf.mmm.client.ui.impl.gwt.widget.adapter.MultiSelectionCheckbox;
import net.sf.mmm.client.ui.impl.gwt.widget.adapter.UiWidgetAdapterGwtWidgetActive;
import net.sf.mmm.util.nls.api.IllegalCaseException;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasAllFocusHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Tree;
import com.google.gwt.user.client.ui.TreeItem;

/**
 * This is the implementation of {@link UiWidgetAdapterTree} using GWT based on {@link Tree}.
 * 
 * @param <NODE> is the generic type of the tree-nodes.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public class UiWidgetAdapterGwtTree<NODE> extends UiWidgetAdapterGwtWidgetActive<FlowPanel> implements
    UiWidgetAdapterTree<NODE> {

  /** @see #setRootNode(Object) */
  private NODE rootNode;

  /** @see #setTreeModel(UiTreeModel) */
  private UiTreeModel<NODE> treeModel;

  /** @see #setTreeNodeRenderer(UiTreeNodeRenderer) */
  private UiTreeNodeRenderer<NODE, ?> treeNodeRenderer;

  /** Maps from {@literal <NODE>} to {@link TreeNodeAdapter}. */
  private Map<NODE, TreeNodeAdapter> nodeMap;

  /** @see #setTitle(String) */
  private InlineLabel titleHeader;

  /** @see #getGwtTree() */
  private Tree tree;

  /** @see #getScrollPanel() */
  private ScrollPanel scrollPanel;

  /** @see #setSelectionMode(SelectionMode) */
  private SelectionMode selectionMode;

  /** The {@link TreeNodeAdapter} for {@link #rootNode}. */
  private TreeNodeAdapter rootNodeAdapter;

  /**
   * The constructor.
   */
  public UiWidgetAdapterGwtTree() {

    super();
    this.nodeMap = new HashMap<NODE, TreeNodeAdapter>();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setTreeModel(UiTreeModel<NODE> treeModel) {

    this.treeModel = treeModel;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setTreeNodeRenderer(UiTreeNodeRenderer<NODE, ?> renderer) {

    this.treeNodeRenderer = renderer;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setSelectionMode(SelectionMode selectionMode) {

    if (this.selectionMode == selectionMode) {
      return;
    }
    this.selectionMode = selectionMode;
    if (this.rootNodeAdapter != null) {
      this.rootNodeAdapter.updateSelectionMode();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean setSelectedValue(NODE selectedValue) {

    TreeNodeAdapter treeNodeAdapter = this.nodeMap.get(selectedValue);
    if (treeNodeAdapter != null) {
      if (this.selectionMode == SelectionMode.SINGLE_SELECTION) {
        getGwtTree().setSelectedItem(treeNodeAdapter);
      } else {
        clearMultiSelection();
        treeNodeAdapter.multiSelectionCheckbox.setValue(Boolean.TRUE);
      }
      return true;
    }
    return false;
  }

  /**
   * Clear {@link Checkbox}es of {@link SelectionMode#MULTIPLE_SELECTION}.
   */
  private void clearMultiSelection() {

    for (TreeNodeAdapter adapter : this.nodeMap.values()) {
      adapter.multiSelectionCheckbox.setValue(Boolean.FALSE);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean setSelectedValues(List<NODE> selectedValues) {

    boolean success = false;
    if (this.selectionMode == SelectionMode.MULTIPLE_SELECTION) {
      success = true;
      clearMultiSelection();
      for (NODE node : selectedValues) {
        TreeNodeAdapter treeNodeAdapter = this.nodeMap.get(node);
        if (treeNodeAdapter != null) {
          treeNodeAdapter.multiSelectionCheckbox.setValue(Boolean.TRUE);
        } else {
          success = false;
        }
      }
    }
    return success;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean hasSelectedValue() {

    return (getSelectedValue() != null);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public NODE getSelectedValue() {

    if (this.selectionMode == SelectionMode.SINGLE_SELECTION) {
      TreeNodeAdapter selectedItem = (TreeNodeAdapter) getGwtTree().getSelectedItem();
      if (selectedItem != null) {
        return selectedItem.node;
      }
    } else if (this.selectionMode == SelectionMode.MULTIPLE_SELECTION) {
      for (TreeNodeAdapter adapter : this.nodeMap.values()) {
        if (adapter.multiSelectionCheckbox.getValue().booleanValue()) {
          return adapter.node;
        }
      }
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<NODE> getSelectedValues() {

    List<NODE> selectedValues = new LinkedList<NODE>();
    if (this.selectionMode == SelectionMode.SINGLE_SELECTION) {
      TreeNodeAdapter selectedItem = (TreeNodeAdapter) getGwtTree().getSelectedItem();
      if (selectedItem != null) {
        selectedValues.add(selectedItem.node);
      }
    } else if (this.selectionMode == SelectionMode.MULTIPLE_SELECTION) {
      for (TreeNodeAdapter adapter : this.nodeMap.values()) {
        if (adapter.multiSelectionCheckbox.getValue().booleanValue()) {
          selectedValues.add(adapter.node);
        }
      }
    }
    return selectedValues;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public UiWidget getTreeNodeWidget(NODE node) {

    TreeNodeAdapter treeNodeAdapter = this.nodeMap.get(node);
    if (treeNodeAdapter != null) {
      return treeNodeAdapter.widget;
    }
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setRootNode(NODE node) {

    if (this.rootNode != null) {
      TreeNodeAdapter treeNodeAdapter = this.nodeMap.get(this.rootNode);
      if (treeNodeAdapter != null) {
        treeNodeAdapter.remove();
      }
      this.nodeMap.clear();
    }
    this.rootNode = node;
    this.rootNodeAdapter = createTreeNodeAdapter(this.rootNode);
    this.nodeMap.put(this.rootNode, this.rootNodeAdapter);
    this.rootNodeAdapter.loadChildren();
    getGwtTree().addItem(this.rootNodeAdapter);
  }

  /**
   * @param node is the {@literal <NODE>} to wrap.
   * @return the {@link TreeNodeAdapter} for the given <code>node</code>.
   */
  private TreeNodeAdapter createTreeNodeAdapter(NODE node) {

    UiWidget widget = this.treeNodeRenderer.create(getUiWidget().getContext());
    TreeNodeAdapter treeNodeAdapter = new TreeNodeAdapter(node, widget);
    return treeNodeAdapter;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected EventAdapterGwt createEventAdapter(UiFeatureEvent source, UiHandlerEvent sender) {

    EventAdapterGwtTree adapter = new EventAdapterGwtTree(source, sender);
    return adapter;
  }

  /**
   * {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  @Override
  protected void applyEventAdapterForSelection(EventAdapterGwt adapter) {

    getGwtTree().addSelectionHandler((EventAdapterGwtTree) adapter);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected Focusable getWidgetAsFocusable() {

    return getGwtTree();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected HasKeyPressHandlers getWidgetAsKeyPressHandlers() {

    return getGwtTree();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected HasAllFocusHandlers getWidgetAsHasAllFocusHandlers() {

    return getGwtTree();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setTitle(String title) {

    getTitleHeader().setText(title);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setEnabled(boolean enabled) {

    if (enabled) {
      getGwtTree().removeStyleName("Disabled");
    } else {
      getGwtTree().addStyleName("Disabled");
    }
  }

  /**
   * @param event is the {@link OpenEvent}.
   */
  private void onOpenEvent(OpenEvent<TreeItem> event) {

    TreeNodeAdapter treeNodeAdapter = (TreeNodeAdapter) event.getTarget();
    treeNodeAdapter.loadChildren();
  }

  /**
   * @return the raw GWT {@link Tree}.
   */
  public Tree getGwtTree() {

    if (this.tree == null) {
      this.tree = new Tree();
      OpenHandler<TreeItem> openHandler = new OpenHandler<TreeItem>() {

        /**
         * {@inheritDoc}
         */
        @Override
        public void onOpen(OpenEvent<TreeItem> event) {

          onOpenEvent(event);
        }
      };
      this.tree.addOpenHandler(openHandler);
    }
    return this.tree;
  }

  /**
   * @return the {@link ScrollPanel} containing the {@link #getGwtTree() tree}.
   */
  public ScrollPanel getScrollPanel() {

    if (this.scrollPanel == null) {
      this.scrollPanel = new ScrollPanel(getGwtTree());
    }
    return this.scrollPanel;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setHeight(Length height) {

    getScrollPanel().setHeight(height.toString());
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setWidth(Length width) {

    getScrollPanel().setWidth(width.toString());
  }

  /**
   * @return the titleHeader
   */
  public InlineLabel getTitleHeader() {

    if (this.titleHeader == null) {
      this.titleHeader = new InlineLabel();
      this.titleHeader.setStylePrimaryName("Header");
    }
    return this.titleHeader;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected FlowPanel createToplevelWidget() {

    FlowPanel toplevelWidget = new FlowPanel();
    toplevelWidget.add(getTitleHeader());
    toplevelWidget.add(getScrollPanel());
    toplevelWidget.setStylePrimaryName(UiWidgetTree.PRIMARY_STYLE);
    return toplevelWidget;
  }

  /**
   * This inner class adapts from {@literal <NODE>} to {@link TreeItem}.
   */
  protected class TreeNodeAdapter extends TreeItem implements Consumer<List<NODE>>, ValueChangeHandler<Boolean>,
      ClickHandler {

    /** @see #getNode() */
    private final NODE node;

    /** @see #getWidget() */
    private final UiWidget widget;

    /** @see #loadChildren() */
    private boolean loaded;

    /** @see #getWidgetPanel() */
    private FlowPanel widgetPanel;

    /** The {@link CheckBox} for {@link SelectionMode#MULTIPLE_SELECTION}. */
    private MultiSelectionCheckbox multiSelectionCheckbox;

    /**
     * The dummy constructor.
     */
    private TreeNodeAdapter() {

      super();
      this.loaded = true;
      this.node = null;
      this.widget = null;
      this.widgetPanel = null;
    }

    /**
     * The constructor.
     * 
     * @param node is the unwrapped node (business object).
     * @param widget is the {@link UiWidget} rendering this node.
     */
    public TreeNodeAdapter(NODE node, UiWidget widget) {

      super();
      this.widget = widget;
      this.node = node;
      switch (UiWidgetAdapterGwtTree.this.selectionMode) {
        case SINGLE_SELECTION:
          setWidget(getToplevelWidget(widget));
          break;
        case MULTIPLE_SELECTION:
          initializeMultiSelection();
          break;
        default :
          throw new IllegalCaseException(SelectionMode.class, UiWidgetAdapterGwtTree.this.selectionMode);
      }
      updateNode();
      this.loaded = false;
      // add a dummy child so the node can be expanded for lazy loading...
      addItem(new TreeNodeAdapter());
    }

    /**
     * Updates the UI of this node.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected void updateNode() {

      ((UiTreeNodeRenderer) UiWidgetAdapterGwtTree.this.treeNodeRenderer).assignNodeToWidget(this.node, this.widget);
    }

    /**
     * @return the node
     */
    public NODE getNode() {

      return this.node;
    }

    /**
     * Loads the children of this node asynchronously.
     */
    protected void loadChildren() {

      if (this.loaded) {
        return;
      }
      UiWidgetAdapterGwtTree.this.treeModel.getChildrenAsync(this.node, this);
      this.loaded = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void accept(List<NODE> value) {

      clearChildren();
      for (NODE childNode : value) {
        TreeNodeAdapter childNodeAdapter = createTreeNodeAdapter(childNode);
        UiWidgetAdapterGwtTree.this.nodeMap.put(childNode, childNodeAdapter);
        addItem(childNodeAdapter);
      }
    }

    /**
     * Clears all existing children.
     */
    private void clearChildren() {

      if (this.loaded) {
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
          TreeNodeAdapter child = (TreeNodeAdapter) getChild(i);
          TreeNodeAdapter oldNode = UiWidgetAdapterGwtTree.this.nodeMap.remove(child.node);
          assert (oldNode == child);
        }
      }
      removeItems();
    }

    /**
     * Recursively updates the {@link SelectionMode}.
     */
    private void updateSelectionMode() {

      switch (UiWidgetAdapterGwtTree.this.selectionMode) {
        case SINGLE_SELECTION:
          if (this.widgetPanel != null) {
            this.multiSelectionCheckbox.setVisible(false);
            // TODO: deselect
          }
          break;
        case MULTIPLE_SELECTION:
          if (this.widgetPanel != null) {
            initializeMultiSelection();
          }
          break;
        default :
          throw new IllegalCaseException(SelectionMode.class, UiWidgetAdapterGwtTree.this.selectionMode);
      }

    }

    private void initializeMultiSelection() {

      this.widgetPanel = new FlowPanel();
      this.widgetPanel.setStylePrimaryName("TreeItemContainer");
      this.multiSelectionCheckbox = new MultiSelectionCheckbox();
      this.multiSelectionCheckbox.addClickHandler(this);
      this.widgetPanel.add(this.multiSelectionCheckbox);
      this.widgetPanel.add(getToplevelWidget(this.widget));
      setWidget(this.widgetPanel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClick(ClickEvent event) {

      setCheckboxSelected(this.multiSelectionCheckbox.getValue().booleanValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onValueChange(ValueChangeEvent<Boolean> event) {

      boolean selected = event.getValue().booleanValue();
      setCheckboxSelected(selected);
    }

    private void setCheckboxSelected(boolean selected) {

      String style = "Selected";
      if (selected) {
        this.widgetPanel.addStyleName(style);
      } else {
        this.widgetPanel.removeStyleName(style);
      }
    }
  }

  /**
   * @see UiWidgetAdapterGwtTree#createEventAdapter(UiFeatureEvent, UiHandlerEvent)
   */
  private static class EventAdapterGwtTree extends EventAdapterGwt implements SelectionHandler<TreeItem> {

    /**
     * The constructor.
     * 
     * @param source is the source of the events (typically {@link net.sf.mmm.client.ui.api.widget.UiWidget}).
     * @param sender is the sender of events (an adapter that delegates to the individual handlers/listeners).
     */
    public EventAdapterGwtTree(UiFeatureEvent source, UiHandlerEvent sender) {

      super(source, sender);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onSelection(SelectionEvent<TreeItem> event) {

      fireEvent(EventType.SELECTION_CHANGE);
    }

  }
}

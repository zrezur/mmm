/* Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.client.ui.base.aria.role;

import net.sf.mmm.client.ui.api.aria.role.RoleColumnHeader;

/**
 * This is the implementation of {@link RoleColumnHeader}.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 1.0.0
 */
public final class RoleColumnHeaderImpl extends AbstractRoleGridCellHeader implements RoleColumnHeader {

  /**
   * The constructor.
   */
  public RoleColumnHeaderImpl() {

    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String getName() {

    return ARIA_ROLE_COLUMN_HEADER;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Class<RoleColumnHeader> getRoleInterface() {

    return RoleColumnHeader.class;
  }

}

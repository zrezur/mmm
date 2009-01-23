/* $Id$
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.util.reflect.base;

import net.sf.mmm.framework.base.SpringContainerPool;
import net.sf.mmm.util.reflect.api.AnnotationUtil;

/**
 * This is the test-case for {@link AnnotationUtil} configured using spring.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
@SuppressWarnings("all")
public class AnnotationUtilSpringTest extends AnnotationUtilTest {

  /**
   * {@inheritDoc}
   */
  @Override
  public AnnotationUtil getAnnotationUtil() {

    return SpringContainerPool.getContainer("net/sf/mmm/util/reflect/beans-util-reflect.xml")
        .getComponent(AnnotationUtil.class);
  }

}
/* $Id$
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.util.text.base;

import org.junit.AfterClass;

import net.sf.mmm.framework.base.SpringContainerPool;
import net.sf.mmm.util.text.api.Singularizer;

/**
 * This is the test-case for {@link EnglishSingularizer} configured using
 * spring.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
@SuppressWarnings("all")
public class EnglishSingularizerSpringTest extends EnglishSingularizerTest {

  /** @see #getEnglishSingularizer() */
  private static final String SPRING_CONFIG = "net/sf/mmm/util/text/beans-util-text.xml";

  /**
   * {@inheritDoc}
   */
  @Override
  public Singularizer getEnglishSingularizer() {

    return SpringContainerPool.getContainer(SPRING_CONFIG).getComponent(Singularizer.class);
  }

  /**
   * This method is invoked after all tests of this class have completed.
   */
  @AfterClass
  public static void tearDown() {

    SpringContainerPool.disposeContainer(SPRING_CONFIG);
  }

}

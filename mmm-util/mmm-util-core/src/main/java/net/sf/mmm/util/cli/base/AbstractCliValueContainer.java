/* $Id$
 * Copyright (c) The m-m-m Team, Licensed under the Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0 */
package net.sf.mmm.util.cli.base;

import org.slf4j.Logger;

/**
 * This is the abstract base-implementation of the {@link CliValueContainer}
 * interface.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 * @since 2.0.0
 */
public abstract class AbstractCliValueContainer implements CliValueContainer {

  /** @see #getParameterContainer() */
  private final CliParameterContainer parameterContainer;

  /** @see #getCliState() */
  private final CliState cliState;

  /** @see #getConfiguration() */
  private final CliParserConfiguration configuration;

  /** @see #getLogger() */
  private final Logger logger;

  /**
   * The constructor.
   * 
   * @param parameterContainer is the {@link #getParameterContainer()
   *        parameter-container}.
   * @param cliState is the {@link #getCliState() state}.
   * @param configuration is the {@link #getConfiguration() configuration}.
   * @param logger is the {@link #getLogger() logger}.
   */
  public AbstractCliValueContainer(CliParameterContainer parameterContainer, CliState cliState,
      CliParserConfiguration configuration, Logger logger) {

    super();
    this.parameterContainer = parameterContainer;
    this.cliState = cliState;
    this.configuration = configuration;
    this.logger = logger;
  }

  /**
   * @return the cliState
   */
  public CliState getCliState() {

    return this.cliState;
  }

  /**
   * @return the parameterContainer
   */
  public CliParameterContainer getParameterContainer() {

    return this.parameterContainer;
  }

  /**
   * @return the configuration
   */
  public CliParserConfiguration getConfiguration() {

    return this.configuration;
  }

  /**
   * @return the logger
   */
  public Logger getLogger() {

    return this.logger;
  }

  /**
   * {@inheritDoc}
   */
  public boolean isArrayMapOrCollection() {

    return false;
  }
}

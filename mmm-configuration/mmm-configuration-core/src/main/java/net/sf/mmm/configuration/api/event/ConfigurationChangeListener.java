/* $Id$ */
package net.sf.mmm.configuration.api.event;

import net.sf.mmm.util.event.EventListener;

/**
 * This is the interface a listener needs to implement in order to receive
 * {@link net.sf.mmm.configuration.api.event.ConfigurationChangeEvent events}
 * that notify about changes in the configuration.
 * 
 * @author Joerg Hohwiller (hohwille at users.sourceforge.net)
 */
public interface ConfigurationChangeListener extends EventListener<ConfigurationChangeEvent> {

}
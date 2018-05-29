/*
 * File:   WeldContext.java
 *
 * Created on 28/05/18, 22:25
 */
package com.mycompany.webapi;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

/**
 * @author higor
 */
public class WeldContext {
  public static final WeldContext INSTANCE = new WeldContext();

  private final Weld weld;
  private final WeldContainer container;

  private WeldContext() {
    this.weld = new Weld();
    this.container = weld.initialize();
    Runtime.getRuntime().addShutdownHook(new Thread(() -> weld.shutdown()));
  }

  public <T> T getBean(Class<T> type) {
    return container.instance().select(type).get();
  }
}


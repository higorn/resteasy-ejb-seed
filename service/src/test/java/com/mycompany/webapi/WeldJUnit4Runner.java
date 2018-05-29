/*
 * File:   WeldJUnit4Runner.java
 *
 * Created on 28/05/18, 22:24
 */
package com.mycompany.webapi;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * @author higor
 */
public class WeldJUnit4Runner extends BlockJUnit4ClassRunner {
  /**
   * Creates a BlockJUnit4ClassRunner to run {@code klass}
   *
   * @param klass
   * @throws InitializationError if the test class is malformed.
   */
  public WeldJUnit4Runner(Class<?> klass) throws InitializationError {
    super(klass);
  }

  @Override
  protected Object createTest() throws Exception {
    final Class<?> test = getTestClass().getJavaClass();
    return WeldContext.INSTANCE.getBean(test);
  }
}


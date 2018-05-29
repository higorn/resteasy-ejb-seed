/*
 * File:   ApiV1RestServiceApplication.java
 *
 * Created on 28/05/18, 21:00
 */
package com.mycompany.webapi;

import com.mycompany.webapi.rest.RestaurantRestService;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 * @author higor
 */
@ApplicationPath("/v1")
public class ApiV1RestServiceApplication extends Application {
  @Inject
  private RestaurantRestService restaurantRestService;
  private Set<Object> singletons = new HashSet<>();

  public ApiV1RestServiceApplication() {
  }

  @PostConstruct
  public void init() {
    singletons.add(restaurantRestService);
  }

  @Override
  public Set<Object> getSingletons() {
    return singletons;
  }

  @Override
  public Set<Class<?>> getClasses() {
    return null;
  }
}

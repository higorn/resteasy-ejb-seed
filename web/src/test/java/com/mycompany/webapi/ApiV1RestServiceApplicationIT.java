/*
 * File:   ApiV1RestServiceApplicationIT.java
 *
 * Created on 28/05/18, 22:34
 */
package com.mycompany.webapi;

import com.mycompany.webapi.exception.DefaultExceptionHandler;
import com.mycompany.webapi.model.ApiResponse;
import com.mycompany.webapi.persistence.dao.RestaurantDao;
import com.mycompany.webapi.persistence.dao.jpa.AbstractDaoJpa;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;
import org.jboss.resteasy.test.TestPortProvider;
import org.junit.*;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * @author higor
 */
@RunWith(WeldJUnit4Runner.class)
public class ApiV1RestServiceApplicationIT extends JpaBasedDBTestCase {
  private static final String API_V1_PATH = "/mycompany/api/v1";

  @Inject
  private ApiV1RestServiceApplication application;
  @Inject
  private DefaultExceptionHandler exceptionHandler;
  @Inject
  private RestaurantDao dao;
  private static UndertowJaxrsServer server;
  private Client client;

  @BeforeClass
  public static void beforeClass() {
    System.setProperty("org.jboss.resteasy.port", "8082");
    server = new UndertowJaxrsServer().start();
  }

  @Override
  protected EntityManager getEntityManager() {
    return ((AbstractDaoJpa)dao).getEntityManager();
  }

  @Override
  protected String getDataSetPath() {
    return "/dataset/db.xml";
  }

  @Before
  public void setUp() throws Exception {
    super.setUp();
    ResteasyDeployment deployment = new ResteasyDeployment();
    deployment.setApplication(application);
//    deployment.setProviders(new ArrayList<>(Arrays.asList(new Object[]{exceptionHandler, interceptor})));

    Map<String, String> contextParams = new HashMap<>();
    contextParams.put("resteasy.scan", "true");

    Map<String, String> initParams = new HashMap<>();
    initParams.put("javax.ws.rs.Application", "com.higor.webapi.ApiV1RestServiceApplication");
    initParams.put("resteasy.servlet.mapping.prefix", "/api/v1");

    server.deploy(deployment, "mycompany", contextParams, initParams);
    client = ClientBuilder.newBuilder().build();
  }

  @After
  public void tearDown() {
    client.close();
  }

  @AfterClass
  public static void afterClass() {
    server.stop();
  }

  @Test
  public void itShouldReturnAListOfRestaurants() {
    Response response = client.target(TestPortProvider.generateURL(API_V1_PATH + "/restaurant"))
        .request(MediaType.APPLICATION_JSON)
        .get();
    assertNotNull(response);
    assertEquals(200, response.getStatus());
    ApiResponse<List<LinkedHashMap>> apiResponse = response.readEntity(ApiResponse.class);
    assertNotNull(apiResponse);
    List<LinkedHashMap> restaurantList = apiResponse.getData();
    assertNotNull(restaurantList);
    assertEquals(3, restaurantList.size());
  }

  @Test
  public void itShouldReturnAFilteredListOfRestaurants() {
    Response response = client.target(TestPortProvider.generateURL(API_V1_PATH + "/restaurant?filter=moreira"))
        .request(MediaType.APPLICATION_JSON)
        .get();
    assertNotNull(response);
    assertEquals(200, response.getStatus());
    ApiResponse<List<LinkedHashMap>> apiResponse = response.readEntity(ApiResponse.class);
    assertNotNull(apiResponse);
    List<LinkedHashMap> restaurantList = apiResponse.getData();
    assertNotNull(restaurantList);
    assertEquals(1, restaurantList.size());
    assertEquals("Moreira Burger", restaurantList.get(0).get("name"));
  }
}

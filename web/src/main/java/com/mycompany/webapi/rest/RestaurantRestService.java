package com.mycompany.webapi.rest;


import com.mycompany.webapi.model.ApiResponse;
import com.mycompany.webapi.model.dto.RestaurantDTO;
import com.mycompany.webapi.service.RestaurantService;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Path("/restaurant")
public class RestaurantRestService {

	private static final Logger logger = Logger.getLogger(RestaurantRestService.class.getName());

	@Inject
  private RestaurantService service;

  @GET
  @Path("/")
  @PermitAll
  @Produces(MediaType.APPLICATION_JSON)
  public Response getRestaurants(@DefaultValue("") @QueryParam("filter") String filter) {

    logger.info("Start getRestaurants");

    List<RestaurantDTO> restaurantList = service.getList(filter);

/*    List<RestaurantDTO> restaurantList = new ArrayList<>();
    restaurantList.add(new RestaurantDTO(1, "Lanches da Gringa"));
    restaurantList.add(new RestaurantDTO(2, "Casa di Paolo"));
    restaurantList.add(new RestaurantDTO(3, "Moreira Burger"));*/

    logger.info("End getRestaurants");

    Response.Status status = Response.Status.OK;
    Response response = Response.status(status)
        .entity(new ApiResponse<>(status.getStatusCode(), status.toString(), restaurantList))
        .type(MediaType.APPLICATION_JSON)
        .build();
    return response;
  }

	@GET
	@Path("/{id}")
  @PermitAll
  @Produces(MediaType.APPLICATION_JSON)
	public Response getRestaurant(@PathParam("id") final Integer id) {

    logger.info("Start getRestaurant");
    /*
     * TODO:
     * Restaurant restaurant = service.get(id);
     */
    RestaurantDTO restaurant = new RestaurantDTO(id, "anything");

    logger.info("End getRestaurant");

    Response.Status status = Response.Status.OK;
    Response response = Response.status(status)
        .entity(new ApiResponse<>(status.getStatusCode(), status.toString(), restaurant))
        .type(MediaType.APPLICATION_JSON)
        .build();
    return response;
	}

	@POST
	@Path("/")
  @PermitAll
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
	public Response createRestaurant(final RestaurantDTO restaurant) {

    logger.info("Start createRestaurant");

    /*
     * TODO:
     * restaurantCreated = service.create(restaurant);
     */
    RestaurantDTO restaurantCreated = restaurant;
    restaurantCreated.setId(1);
    restaurantCreated.setName("anything");

    logger.info("End createRestaurant");

    Response.Status status = Response.Status.CREATED;
    Response response = Response.status(status)
        .entity(new ApiResponse<>(status.getStatusCode(), status.toString(), restaurant))
        .type(MediaType.APPLICATION_JSON)
        .build();
    return response;
	}

	@PUT
	@Path("/{id}")
  @RolesAllowed({"user", "admin"})
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
	public Response updateRestaurant(@PathParam("id") final Integer id) {

    logger.info("Start updateRestaurant");

    /*
     * TODO:
     * restaurant = service.update(restaurant);
     */
    RestaurantDTO restaurant = new RestaurantDTO(id, "anything");

    logger.info("End updateRestaurant");

    Response.Status status = Response.Status.ACCEPTED;
    Response response = Response.status(status)
        .entity(new ApiResponse<>(status.getStatusCode(), status.toString(), restaurant))
        .type(MediaType.APPLICATION_JSON)
        .build();
    return response;
	}

	@DELETE
  @Path("/{id}")
  @RolesAllowed({"admin"})
  @Produces(MediaType.APPLICATION_JSON)
	public Response deleteRestaurant(@PathParam("id") final Integer id) {

    logger.info("Start deleteRestaurant");

    /*
     * TODO:
     * service.delete(id);
     */

    logger.info("End deleteRestaurant");

    Response.Status status = Response.Status.ACCEPTED;
    Response response = Response.status(status)
        .entity(new ApiResponse<>(status.getStatusCode(), status.toString(), id))
        .type(MediaType.APPLICATION_JSON)
        .build();
    return response;
	}
}

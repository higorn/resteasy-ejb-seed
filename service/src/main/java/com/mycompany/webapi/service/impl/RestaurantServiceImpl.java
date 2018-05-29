package com.mycompany.webapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.mycompany.webapi.model.dto.RestaurantDTO;
import com.mycompany.webapi.model.entity.Restaurant;
import com.mycompany.webapi.persistence.dao.RestaurantDao;
import com.mycompany.webapi.service.RestaurantService;

@Stateless
public class RestaurantServiceImpl implements RestaurantService {
	@Inject
	private RestaurantDao dao;

	public List<RestaurantDTO> getList(final String filter) {
		List<Restaurant> restaurantList = dao.getList(filter);
		return restaurantList.stream()
			.map(restaurant -> new RestaurantDTO(restaurant.getId(), restaurant.getName()))
			.collect(Collectors.toList());
	}

}

package com.mycompany.webapi.service;

import java.util.List;

import com.mycompany.webapi.model.dto.RestaurantDTO;


public interface RestaurantService {
	List<RestaurantDTO> getList(String filter);
}

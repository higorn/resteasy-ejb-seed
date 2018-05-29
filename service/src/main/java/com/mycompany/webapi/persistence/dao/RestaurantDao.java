package com.mycompany.webapi.persistence.dao;

import java.util.List;

import com.mycompany.webapi.model.entity.Restaurant;

public interface RestaurantDao {

	List<Restaurant> getList(String filter);
}

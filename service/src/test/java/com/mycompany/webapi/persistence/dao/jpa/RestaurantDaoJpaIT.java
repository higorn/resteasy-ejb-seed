package com.mycompany.webapi.persistence.dao.jpa;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.mycompany.webapi.JpaBasedDBTestCase;
import com.mycompany.webapi.WeldJUnit4Runner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.mycompany.webapi.model.entity.Restaurant;
import com.mycompany.webapi.persistence.dao.RestaurantDao;

@RunWith(WeldJUnit4Runner.class)
public class RestaurantDaoJpaIT extends JpaBasedDBTestCase {

	@Inject
	private RestaurantDao dao;

	@Override
	protected String getDataSetPath() {
		return "/dataset/db.xml";
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return ((AbstractDaoJpa)dao).getEntityManager();
	}

	@Before
	public void setUp() throws Exception {
		super.setUp();
	}

	@Test
	public void itShouldGetAListOfRestaurants() {
		List<Restaurant> restaurants = dao.getList("");
		Assert.assertNotNull(restaurants);
		Assert.assertFalse(restaurants.isEmpty());
		Assert.assertEquals(3, restaurants.size());
	}

	@Test
	public void forNullFilterItShouldGetAllRestaurants() {
		List<Restaurant> restaurants = dao.getList(null);
		Assert.assertNotNull(restaurants);
		Assert.assertFalse(restaurants.isEmpty());
		Assert.assertEquals(3, restaurants.size());
	}

	@Test
	public void itShouldGetAllRestaurantsByName() {
		List<Restaurant> restaurants = dao.getList("Moreira");
		Assert.assertNotNull(restaurants);
		Assert.assertFalse(restaurants.isEmpty());
		Assert.assertEquals(1, restaurants.size());
	}

}

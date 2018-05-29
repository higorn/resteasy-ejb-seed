package com.mycompany.webapi.persistence.dao.jpa;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.mycompany.webapi.model.entity.Restaurant;
import com.mycompany.webapi.persistence.config.DataSourceQualifier;
import com.mycompany.webapi.persistence.dao.RestaurantDao;

public class RestaurantDaoJpa extends AbstractDaoJpa implements RestaurantDao {

	@Inject
	public RestaurantDaoJpa(@DataSourceQualifier final EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public List<Restaurant> getList(final String filter) {
		Calendar now = Calendar.getInstance();
		String sql = "SELECT r FROM Restaurant r"
				+ " WHERE"
					+ " lower(r.name) like lower(:name)"
				+ " ORDER BY r.name";

		String _filter = filter == null ? "" : filter;
		final TypedQuery<Restaurant> query = getEntityManager().createQuery(sql, Restaurant.class);
		query.setParameter("name", "%" + _filter + "%");
		return query.getResultList();
	}

}

package com.mycompany.webapi.persistence.dao.jpa;

import javax.persistence.EntityManager;

public abstract class AbstractDaoJpa {

	private EntityManager entityManager;

	public AbstractDaoJpa(final EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
}

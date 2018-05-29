package com.mycompany.webapi.persistence.config;

import javax.annotation.Priority;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.interceptor.Interceptor;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import com.mycompany.webapi.persistence.config.DataSourceQualifier;
import com.mycompany.webapi.persistence.config.EntityManagerProvider;

@Alternative
@Priority(Interceptor.Priority.APPLICATION + EntityManagerProvider.TEST_PRIORITY)
@ApplicationScoped
public class EntityManagerProviderForIT implements EntityManagerProvider {

	@Produces
	@DataSourceQualifier
	public EntityManager produce() {
		return Persistence.createEntityManagerFactory("db-test").createEntityManager();
	}

	public void dispose(@Disposes @DataSourceQualifier final EntityManager manager) {
		if (manager.isOpen()) {
			manager.close();
		}
	}
}

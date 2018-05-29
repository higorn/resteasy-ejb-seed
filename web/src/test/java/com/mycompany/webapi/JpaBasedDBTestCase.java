package com.mycompany.webapi;

import javax.persistence.EntityManager;

import org.dbunit.DatabaseTestCase;
import org.dbunit.DefaultDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;
import org.hibernate.internal.SessionImpl;

public abstract class JpaBasedDBTestCase extends DatabaseTestCase {

	private IDatabaseTester databaseTester;

	protected IDatabaseConnection getConnection() throws Exception {
		return new DatabaseConnection(((SessionImpl) getEntityManager().getDelegate()).connection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		final FlatXmlDataFileLoader loader = new FlatXmlDataFileLoader();
		return loader.load(getDataSetPath());
	}

	protected void setUp() throws Exception {
		getConnection().getConfig().setProperty(DatabaseConfig.FEATURE_ALLOW_EMPTY_FIELDS, true);
//		databaseTester = newDatabaseTester();
		databaseTester = new JdbcDatabaseTester("org.hsqldb.jdbcDriver", "jdbc:hsqldb:mem:db-test", "sa", "");
		databaseTester.setDataSet(getDataSet());
		databaseTester.onSetup();
//		DatabaseOperation.CLEAN_INSERT.execute(getConnection(), getDataSet());
	}
	
	protected void tearDown() throws Exception {
		databaseTester.onTearDown();
	}

	protected abstract EntityManager getEntityManager();

	protected abstract String getDataSetPath();
}

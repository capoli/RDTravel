package com.realdolmen.rdtravel.persistence;

import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.ext.mysql.MySqlDataTypeFactory;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Loads a DBUnit test set before every unit test.
 */
public abstract class DataSetPersistenceTest extends PersistenceTest {
    private static final Logger logger = LoggerFactory.getLogger(DataSetPersistenceTest.class);
//    private static boolean setupCalled = false;

    @Before
    public void loadDataSet() throws Exception {
//        if(!setupCalled) {
            logger.info("Loading dataset");
            IDataSet dataSet = new FlatXmlDataSetBuilder().build(getClass().getResource("/data.xml"));

            IDatabaseConnection connection = new DatabaseConnection(newConnection());
            connection.getConfig().setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new MySqlDataTypeFactory()); // Set factorytype in dbconfig to remove warning
            DatabaseOperation.CLEAN_INSERT.execute(connection, dataSet);
            connection.close();

//            setupCalled = true;
//        }
    }
}

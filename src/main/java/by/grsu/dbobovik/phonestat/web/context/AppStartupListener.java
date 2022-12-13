package by.grsu.dbobovik.phonestat.web.context;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.grsu.dbobovik.phonestat.db.dao.AbstractDao;
import by.grsu.dbobovik.phonestat.db.dao.IDao;
import by.grsu.dbobovik.phonestat.db.dao.impl.*;
import by.grsu.dbobovik.phonestat.db.model.*;

public class AppStartupListener implements ServletContextListener
{
    private static final IDao<Integer, User> userDao = UserDaoImpl.INSTANCE;
    private static final IDao<Integer, Invoice> invoiceDao = InvoiceDaoImpl.INSTANCE;
    private static final IDao<Integer, Connection1> connectionDao = Connection1DaoImpl.INSTANCE;
    private static final IDao<Integer, Service> serviceDao = ServiceDaoImpl.INSTANCE;

    private static final String DB_NAME = "production-db";

    private void initDb() throws SQLException {
		AbstractDao.init(DB_NAME);
		if (!AbstractDao.isDbExist()) {
			System.out.println(String.format("DB '%s' doesn't exist and will be created", DB_NAME));
			AbstractDao.createDbSchema();
			loadInitialData();
		} else {
			System.out.println(String.format("DB '%s' exists", DB_NAME));
		}
	}

    private void loadInitialData() {
		User userEntity = new User();
        userEntity.setName("RandomName");
        userEntity.setSurname("RandomSurname");
        userEntity.setBirthDate(getCurrentTime());
        userEntity.setRole(false);
	    userDao.insert(userEntity);
		System.out.println("created: " + userEntity);

		Service serviceEntity = new Service();
        serviceEntity.setName("RandomName");
        serviceEntity.setCost(11.1);
		serviceDao.insert(serviceEntity);
		System.out.println("created: " + serviceEntity);

		Invoice invoiceEntity = new Invoice();
        invoiceEntity.setInvoice(11.1);
        invoiceEntity.setUserId(userEntity.getId());
		invoiceDao.insert(invoiceEntity);
		System.out.println("created: " + invoiceEntity);

		Connection1 connectionEntity = new Connection1(); 
        connectionEntity.setServiceId(serviceEntity.getId());
        connectionEntity.setUserId(userEntity.getId());
        connectionEntity.setDate(getCurrentTime());
		connectionDao.insert(connectionEntity);
		System.out.println("created: " + connectionEntity);

		System.out.println("initial data created");
	}

    private Timestamp getCurrentTime() {
		return new Timestamp(new Date().getTime());
	}

    @Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("contextInitialized");
		try {
			initDb();
		} catch (SQLException e) {
			throw new RuntimeException("can't init DB", e);
		}
	}

    @Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("contextDestroyed");
	}
}
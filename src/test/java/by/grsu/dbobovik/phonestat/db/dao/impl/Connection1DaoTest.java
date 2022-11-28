package by.grsu.dbobovik.phonestat.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.dbobovik.phonestat.db.dao.IDao;
import by.grsu.dbobovik.phonestat.db.dao.impl.Connection1DaoImpl;
import by.grsu.dbobovik.phonestat.db.dao.impl.ServiceDaoImpl;
import by.grsu.dbobovik.phonestat.db.dao.impl.UserDaoImpl;
import by.grsu.dbobovik.phonestat.db.model.Connection1;
import by.grsu.dbobovik.phonestat.db.model.User;
import by.grsu.dbobovik.phonestat.db.model.Service;

public class Connection1DaoTest extends AbstractTest {
	private static final IDao<Integer, Connection1> connectionDao = Connection1DaoImpl.INSTANCE;
	private static final IDao<Integer, User> userDao = UserDaoImpl.INSTANCE;
	private static final IDao<Integer, Service> serviceDao = ServiceDaoImpl.INSTANCE;

	@Test
	public void testInsert() {
		Connection1 entity = new Connection1();
		entity.setServiceId(saveService().getId());
		entity.setUserId(saveUser().getId());
		entity.setDate(getCurrentTime());
		connectionDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Connection1 entity = new Connection1();
		entity.setServiceId(saveService().getId());
		entity.setUserId(saveUser().getId());
		entity.setDate(getCurrentTime());
		connectionDao.insert(entity);

		Service tempService = saveService();
		entity.setServiceId(tempService.getId());
		connectionDao.update(entity);

		Connection1 updatedEntity = connectionDao.getById(entity.getId());
		Assertions.assertEquals(tempService.getId(), updatedEntity.getServiceId());
	}

	@Test
	public void testDelete() {
		Connection1 entity = new Connection1();
		entity.setUserId(saveUser().getId());
		entity.setServiceId(saveService().getId());
		entity.setDate(getCurrentTime());
		connectionDao.insert(entity);

		connectionDao.delete(entity.getId());

		Assertions.assertNull(connectionDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Connection1 entity = new Connection1();
		entity.setUserId(saveUser().getId());
		entity.setServiceId(saveService().getId());
		entity.setDate(getCurrentTime());
		connectionDao.insert(entity);

		Connection1 selectedEntity = connectionDao.getById(entity.getId());

		Assertions.assertEquals(entity.getUserId(), selectedEntity.getUserId());
		Assertions.assertEquals(entity.getServiceId(), selectedEntity.getServiceId());
		Assertions.assertEquals(entity.getDate(), selectedEntity.getDate());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Connection1 entity = new Connection1();
			entity.setUserId(saveUser().getId());
			entity.setServiceId(saveService().getId());
			entity.setDate(getCurrentTime());
			connectionDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, connectionDao.getAll().size());
	}

	private User saveUser() {
		User entity = new User();
		entity.setName("Ivan");
		entity.setSurname("Ivanov");
		entity.setBirthDate(getCurrentTime());
		entity.setRole(false);
		userDao.insert(entity);
		return entity;
	}

	private Service saveService() {
		Service serviceEntity = new Service();
		serviceEntity.setName("RandomName");
		serviceEntity.setCost(4.4);
		serviceDao.insert(serviceEntity);

		return serviceEntity;
	}
}
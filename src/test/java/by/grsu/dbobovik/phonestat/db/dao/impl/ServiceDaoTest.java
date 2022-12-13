package by.grsu.dbobovik.phonestat.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.dbobovik.phonestat.db.dao.IDao;
import by.grsu.dbobovik.phonestat.db.model.Service;
import by.grsu.dbobovik.phonestat.db.dao.impl.ServiceDaoImpl;

public class ServiceDaoTest extends AbstractTest{
	private static IDao<Integer, Service> serviceDao = ServiceDaoImpl.INSTANCE;
	
	public void testInsert() {
		Service entity = new Service();
		entity.setName("RandomName");
		entity.setCost(1.1);
		serviceDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Service entity = new Service();
		entity.setName("RandomName");
		entity.setCost(1.1);
		serviceDao.insert(entity);

		String tempName = "123";
		entity.setName(tempName);
		serviceDao.update(entity);

		Service updatedEntity = serviceDao.getById(entity.getId());
		Assertions.assertEquals(tempName, updatedEntity.getName());
	}

	@Test
	public void testDelete() {
		Service entity = new Service();
		entity.setName("RandomName");
		entity.setCost(1.1);
		serviceDao.insert(entity);

		serviceDao.delete(entity.getId());

		Assertions.assertNull(serviceDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Service entity = new Service();
		entity.setName("RandomName");
		entity.setCost(1.1);
		serviceDao.insert(entity);

		Service selectedEntity = serviceDao.getById(entity.getId());

		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
		Assertions.assertEquals(entity.getCost(), selectedEntity.getCost());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Service entity = new Service();
			entity.setName("RandomName");
			entity.setCost(1.1);
			serviceDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, serviceDao.getAll().size());
	}
}

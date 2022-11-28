package by.grsu.dbobovik.phonestat.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.dbobovik.phonestat.db.model.User;
import by.grsu.dbobovik.phonestat.db.dao.impl.UserDaoImpl;
import by.grsu.dbobovik.phonestat.db.dao.IDao;

public class UserDaoTest extends AbstractTest{
	private static IDao<Integer, User> userDao = UserDaoImpl.INSTANCE;
	
	@Test
	public void testInsert() {
		User entity = new User();
		entity.setName("RandomName");
		entity.setSurname("randomSurname");
		entity.setBirthDate(getCurrentTime());
		entity.setRole(false);
		userDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		User entity = new User();
		entity.setName("RandomName");
		entity.setSurname("randomSurname");
		entity.setBirthDate(getCurrentTime());
		entity.setRole(false);
		userDao.insert(entity);

		String tempName = "123";
		entity.setName(tempName);
		userDao.update(entity);

		User updatedEntity = userDao.getById(entity.getId());
		Assertions.assertEquals(tempName, updatedEntity.getName());
	}

	@Test
	public void testDelete() {
		User entity = new User();
		entity.setName("RandomName");
		entity.setSurname("randomSurname");
		entity.setBirthDate(getCurrentTime());
		entity.setRole(false);
		userDao.insert(entity);

		userDao.delete(entity.getId());

		Assertions.assertNull(userDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		User entity = new User();
		entity.setName("RandomName");
		entity.setSurname("RandomSurname");
		entity.setBirthDate(getCurrentTime());
		entity.setRole(false);
		userDao.insert(entity);

		User selectedEntity = userDao.getById(entity.getId());

		Assertions.assertEquals(entity.getName(), selectedEntity.getName());
		Assertions.assertEquals(entity.getSurname(), selectedEntity.getSurname());
		Assertions.assertEquals(entity.getBirthDate(), selectedEntity.getBirthDate());
		Assertions.assertEquals(entity.getRole(), selectedEntity.getRole());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			User entity = new User();
			entity.setName("RandomName");
			entity.setSurname("randomSurname");
			entity.setBirthDate(getCurrentTime());
			entity.setRole(false);
			userDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, userDao.getAll().size());
	}
}

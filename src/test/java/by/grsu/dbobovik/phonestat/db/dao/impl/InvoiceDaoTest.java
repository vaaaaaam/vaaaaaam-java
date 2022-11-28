package by.grsu.dbobovik.phonestat.db.dao.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import by.grsu.dbobovik.phonestat.db.dao.IDao;
import by.grsu.dbobovik.phonestat.db.dao.impl.InvoiceDaoImpl;
import by.grsu.dbobovik.phonestat.db.dao.impl.UserDaoImpl;
import by.grsu.dbobovik.phonestat.db.model.Invoice;
import by.grsu.dbobovik.phonestat.db.model.Service;
import by.grsu.dbobovik.phonestat.db.model.User;

public class InvoiceDaoTest extends AbstractTest{
	private static IDao<Integer, User> userDao = UserDaoImpl.INSTANCE;
	private static IDao<Integer, Invoice> invoiceDao = InvoiceDaoImpl.INSTANCE;
	
	@Test
	public void testInsert() {
		Invoice entity = new Invoice();
		entity.setUserId(saveUser().getId());
		entity.setInvoice(1.1);
		invoiceDao.insert(entity);
		Assertions.assertNotNull(entity.getId());
	}

	@Test
	public void testUpdate() {
		Invoice entity = new Invoice();
		entity.setUserId(saveUser().getId());
		entity.setInvoice(1.1);
		invoiceDao.insert(entity);

		User tempUser = saveUser();
		entity.setUserId(tempUser.getId());
		invoiceDao.update(entity);

		Invoice updatedEntity = invoiceDao.getById(entity.getId());
		Assertions.assertEquals(tempUser.getId(), updatedEntity.getUserId());
	}

	@Test
	public void testDelete() {
		Invoice entity = new Invoice();
		entity.setUserId(saveUser().getId());
		entity.setInvoice(1.1);
		invoiceDao.insert(entity);

		invoiceDao.delete(entity.getId());

		Assertions.assertNull(invoiceDao.getById(entity.getId()));
	}

	@Test
	public void testGetById() {
		Invoice entity = new Invoice();
		entity.setUserId(saveUser().getId());
		entity.setInvoice(1.1);
		invoiceDao.insert(entity);

		Invoice selectedEntity = invoiceDao.getById(entity.getId());

		Assertions.assertEquals(entity.getUserId(), selectedEntity.getUserId());
		Assertions.assertEquals(entity.getInvoice(), selectedEntity.getInvoice());
	}

	@Test
	public void testGetAll() {
		int expectedCount = getRandomNumber(1, 5);
		for (int i = 1; i <= expectedCount; i = i + 1) {
			Invoice entity = new Invoice();
			entity.setUserId(saveUser().getId());
			entity.setInvoice(1.1);
			invoiceDao.insert(entity);
		}

		Assertions.assertEquals(expectedCount, invoiceDao.getAll().size());
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
}

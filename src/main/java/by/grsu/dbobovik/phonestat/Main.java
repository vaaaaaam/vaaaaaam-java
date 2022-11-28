package by.grsu.dbobovik.phonestat;

import java.sql.Timestamp;
import java.util.Date;

import by.grsu.dbobovik.phonestat.db.model.Invoice;
import by.grsu.dbobovik.phonestat.db.model.User;
import by.grsu.dbobovik.phonestat.db.model.Connection1;
import by.grsu.dbobovik.phonestat.db.model.Service;

public class Main {

	public static void main(String[] args) {

		User user = new User();
		user.setId(1);
		user.setName("");
		user.setSurname("");
		user.setBirthDate(new Timestamp(new Date().getTime()));
		user.setRole(true);
		System.out.println(user.toString());

		Invoice invoice = new Invoice();
		invoice.setId(1);
		invoice.setInvoice(null);
		invoice.setUserId(2);
		System.out.println(invoice.toString());

		Service service = new Service();
		service.setId(1);
		service.setName(2);
		service.setCost(null);
		System.out.println(service.toString());

		Connection1 connection = new Connection1();
		connection.setId(1);
		connection.setServiceId(2);
		connection.setUserId(3);
		connection.setDate(new Timestamp(new Date().getTime()));
		System.out.println(connection.toString());

	}
}
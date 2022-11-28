package by.grsu.dbobovik.phonestat.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.dbobovik.phonestat.db.dao.AbstractDao;
import by.grsu.dbobovik.phonestat.db.dao.IDao;
import by.grsu.dbobovik.phonestat.db.model.Invoice;

public class InvoiceDaoImpl extends AbstractDao implements IDao<Integer, Invoice>
{
	public static final InvoiceDaoImpl INSTANCE = new InvoiceDaoImpl();

	// private constructor disallows instantiation of this class ('Singleton'
	// pattern) outside of current class
	private InvoiceDaoImpl() 
	{
		super();
	}
	
	public void insert(Invoice entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into invoice(invoice, user_id) values(?,?)");
			pstmt.setDouble(1, entity.getInvoice());
			pstmt.setInt(2, entity.getUserId());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "invoice"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert invoice entity", e);
		}
	}

	@Override
	public void update(Invoice entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update invoice set invoice=?, user_id=? where id=?");
			pstmt.setDouble(1, entity.getInvoice());
			pstmt.setInt(2, entity.getUserId());
			pstmt.setInt(3, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update invoice entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from invoice where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete invoice entity", e);
		}

	}

	@Override
	public Invoice getById(Integer id) {
		Invoice entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from invoice where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get invoice entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Invoice> getAll() {
		List<Invoice> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from invoice");
			while (rs.next()) {
				Invoice entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select invoice entities", e);
		}

		return entitiesList;
	}

	private Invoice rowToEntity(ResultSet rs) throws SQLException {
		Invoice entity = new Invoice();
		entity.setId(rs.getInt("id"));
		entity.setInvoice(rs.getDouble("invoice"));
		entity.setUserId(rs.getInt("user_id"));
		return entity;
	}


}

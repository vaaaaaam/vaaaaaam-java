package by.grsu.dbobovik.phonestat.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.dbobovik.phonestat.db.dao.AbstractDao;
import by.grsu.dbobovik.phonestat.db.dao.IDao;
import by.grsu.dbobovik.phonestat.db.model.Service;

public class ServiceDaoImpl extends AbstractDao implements IDao<Integer, Service> {

	// single instance of this class to be used by the all consumers
	public static final ServiceDaoImpl INSTANCE = new ServiceDaoImpl();

	// private constructor disallows instantiation of this class ('Singleton'
	// pattern) outside of current class
	private ServiceDaoImpl() {
		super();
	}

	@Override
	public void insert(Service entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into service(name, cost) values(?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setDouble(2, entity.getCost());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "service"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert service entity", e);
		}
	}

	@Override
	public void update(Service entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update service set name=?, cost=? where id=?");
			pstmt.setString(1, entity.getName());
			pstmt.setDouble(2, entity.getCost());
			pstmt.setInt(3, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update service entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from service where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete service entity", e);
		}

	}

	@Override
	public Service getById(Integer id) {
		Service entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from service where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get Brand entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Service> getAll() {
		List<Service> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from service");
			while (rs.next()) {
				Service entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select service entities", e);
		}

		return entitiesList;
	}

	private Service rowToEntity(ResultSet rs) throws SQLException {
		Service entity = new Service();
		entity.setId(rs.getInt("id"));
		entity.setName(rs.getString("name"));
		entity.setCost(rs.getDouble("cost"));
		return entity;
	}

}

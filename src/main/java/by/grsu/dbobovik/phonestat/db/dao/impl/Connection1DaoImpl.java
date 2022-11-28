package by.grsu.dbobovik.phonestat.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.dbobovik.phonestat.db.dao.AbstractDao;
import by.grsu.dbobovik.phonestat.db.dao.IDao;
import by.grsu.dbobovik.phonestat.db.model.Connection1;

public class Connection1DaoImpl extends AbstractDao implements IDao<Integer, Connection1>
{
	public static final Connection1DaoImpl INSTANCE = new Connection1DaoImpl();

	// private constructor disallows instantiation of this class ('Singleton'
	// pattern) outside of current class
	private Connection1DaoImpl() 
	{
		super();
	}
	
	public void insert(Connection1 entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into connection(service_id, user_id, date) values(?,?,?)");
			pstmt.setInt(1, entity.getServiceId());
			pstmt.setInt(2, entity.getUserId());
			pstmt.setTimestamp(3, entity.getDate());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "connection"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert connection entity", e);
		}
	}

	@Override
	public void update(Connection1 entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update connection set service_id=?, user_id=?, date=? where id=?");
			pstmt.setInt(1, entity.getServiceId());
			pstmt.setInt(2, entity.getUserId());
			pstmt.setTimestamp(3, entity.getDate());
			pstmt.setInt(4, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update connection entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from connection where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete connection entity", e);
		}

	}

	@Override
	public Connection1 getById(Integer id) {
		Connection1 entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from connection where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get connection entity by id", e);
		}

		return entity;
	}

	@Override
	public List<Connection1> getAll() {
		List<Connection1> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from connection");
			while (rs.next()) {
				Connection1 entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select connection entities", e);
		}

		return entitiesList;
	}

	private Connection1 rowToEntity(ResultSet rs) throws SQLException {
		Connection1 entity = new Connection1();
		entity.setServiceId(rs.getInt("service_id"));
		entity.setUserId(rs.getInt("user_id"));
		entity.setDate(rs.getTimestamp("date"));
		return entity;
	}

}

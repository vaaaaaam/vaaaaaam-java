package by.grsu.dbobovik.phonestat.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.grsu.dbobovik.phonestat.db.dao.AbstractDao;
import by.grsu.dbobovik.phonestat.db.dao.IDao;
import by.grsu.dbobovik.phonestat.db.model.User;

public class UserDaoImpl extends AbstractDao implements IDao<Integer, User>
{
	public static final UserDaoImpl INSTANCE = new UserDaoImpl();

	// private constructor disallows instantiation of this class ('Singleton'
	// pattern) outside of current class
	private UserDaoImpl() {
		super();
	}

	@Override
	public void insert(User entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("insert into user(name, surname, birth_date, role) values(?,?,?,?)");
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getSurname());
			pstmt.setTimestamp(3, entity.getBirthDate());
			pstmt.setBoolean(4, entity.getRole());
			pstmt.executeUpdate();
			entity.setId(getGeneratedId(c, "user"));
		} catch (SQLException e) {
			throw new RuntimeException("can't insert user entity", e);
		}
	}

	@Override
	public void update(User entity) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("update user set name=?, surname=?, birth_date=?, role=? where id=?");
			pstmt.setString(1, entity.getName());
			pstmt.setString(2, entity.getSurname());
			pstmt.setTimestamp(3, entity.getBirthDate());
			pstmt.setBoolean(4, entity.getRole());
			pstmt.setInt(5, entity.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't update user entity", e);
		}
	}

	@Override
	public void delete(Integer id) {
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("delete from user where id=?");
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("can't delete user entity", e);
		}

	}

	@Override
	public User getById(Integer id) {
		User entity = null;
		try (Connection c = createConnection()) {
			PreparedStatement pstmt = c.prepareStatement("select * from user where id=?");
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				entity = rowToEntity(rs);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't get user entity by id", e);
		}

		return entity;
	}

	@Override
	public List<User> getAll() {
		List<User> entitiesList = new ArrayList<>();
		try (Connection c = createConnection()) {
			ResultSet rs = c.createStatement().executeQuery("select * from user");
			while (rs.next()) {
				User entity = rowToEntity(rs);
				entitiesList.add(entity);
			}
		} catch (SQLException e) {
			throw new RuntimeException("can't select user entities", e);
		}

		return entitiesList;
	}

	private User rowToEntity(ResultSet rs) throws SQLException {
		User entity = new User();
		entity.setId(rs.getInt("id"));
		entity.setName(rs.getString("name"));
		entity.setSurname(rs.getString("surname"));
		entity.setBirthDate(rs.getTimestamp("birth_date"));
		entity.setRole(rs.getBoolean("role"));
		return entity;
	}

}

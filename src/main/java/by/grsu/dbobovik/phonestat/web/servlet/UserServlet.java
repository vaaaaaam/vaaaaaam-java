package by.grsu.dbobovik.phonestat.web.servlet;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;

import by.grsu.dbobovik.phonestat.db.dao.IDao;
import by.grsu.dbobovik.phonestat.db.dao.impl.UserDaoImpl;
import by.grsu.dbobovik.phonestat.db.model.User;
import by.grsu.dbobovik.phonestat.web.dto.UserDto;

public class UserServlet extends HttpServlet{
    private static final IDao<Integer, User> userDao = UserDaoImpl.INSTANCE;

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doGet");
		String viewParam = req.getParameter("view");
		if ("edit".equals(viewParam)) {
			handleEditView(req, res);
		} else {
			handleListView(req, res);
		}
	}

	private void handleListView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		List<User> users = userDao.getAll(); // get data

		List<UserDto> dtos = users.stream().map((entity) -> {
			UserDto dto = new UserDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setRole(entity.getRole());
            dto.setBirthDate(entity.getBirthDate());

			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.getRequestDispatcher("user-list.jsp").forward(req, res);
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String userIdStr = req.getParameter("id");
		UserDto dto = new UserDto();
		if (!Strings.isNullOrEmpty(userIdStr)) {
			Integer userId = Integer.parseInt(userIdStr);
			User entity = userDao.getById(userId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setRole(entity.getRole());
            dto.setBirthDate(entity.getBirthDate());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("user-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		User user = new User();
		String userIdStr = req.getParameter("id");
        user.setName(req.getParameter("name"));
		user.setSurname(req.getParameter("surname"));
        user.setBirthDate(Timestamp.valueOf(req.getParameter("date") + " 00:00:00.000"));
        user.setRole(Boolean.parseBoolean(req.getParameter("role")));
		if (Strings.isNullOrEmpty(userIdStr)) {
			userDao.insert(user);
		} else {
			user.setId(Integer.parseInt(userIdStr));
			userDao.update(user);
		}
		res.sendRedirect("/user");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		userDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}

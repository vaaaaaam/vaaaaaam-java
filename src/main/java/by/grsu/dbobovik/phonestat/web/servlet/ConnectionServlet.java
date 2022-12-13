package by.grsu.dbobovik.phonestat.web.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.stream.Collectors;
import com.google.common.base.Strings;

import by.grsu.dbobovik.phonestat.db.dao.IDao;
import by.grsu.dbobovik.phonestat.db.dao.impl.Connection1DaoImpl;
import by.grsu.dbobovik.phonestat.db.model.Connection1;
import by.grsu.dbobovik.phonestat.web.dto.ConnectionDto;
import by.grsu.dbobovik.phonestat.db.dao.impl.ServiceDaoImpl;
import by.grsu.dbobovik.phonestat.db.model.Service;
import by.grsu.dbobovik.phonestat.web.dto.ServiceDto;
import by.grsu.dbobovik.phonestat.db.dao.impl.UserDaoImpl;
import by.grsu.dbobovik.phonestat.db.model.User;
import by.grsu.dbobovik.phonestat.web.dto.UserDto;

public class ConnectionServlet extends HttpServlet
{
    private static final IDao<Integer, Connection1> connectionDao = Connection1DaoImpl.INSTANCE;
    private static final IDao<Integer, Service> serviceDao = ServiceDaoImpl.INSTANCE;
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
		List<Connection1> connections = connectionDao.getAll(); // get data

		List<ConnectionDto> dtos = connections.stream().map((entity) -> {
			ConnectionDto dto = new ConnectionDto();
			dto.setId(entity.getId());
            dto.setDate(entity.getDate());

            User user = userDao.getById(entity.getUserId());
            Service service = serviceDao.getById(entity.getServiceId());

            dto.setUserData(user.getName() + user.getSurname());
            dto.setServiceData(service.getName());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.setAttribute("allUsers", getAllUsersDtos());
		req.setAttribute("allServices", getAllServicesDtos());
		req.getRequestDispatcher("connection-list.jsp").forward(req, res);
	}

	private List<UserDto> getAllUsersDtos() {
		return userDao.getAll().stream().map((entity) -> {
			UserDto dto = new UserDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setSurname(entity.getSurname());
			dto.setBirthDate(entity.getBirthDate());
			dto.setRole(entity.getRole());
			return dto;
		}).collect(Collectors.toList());
	}

	private List<ServiceDto> getAllServicesDtos() {
		return serviceDao.getAll().stream().map((entity) -> {
			ServiceDto dto = new ServiceDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setCost(entity.getCost());
			return dto;
		}).collect(Collectors.toList());
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String connectionIdStr = req.getParameter("id");
		ConnectionDto dto = new ConnectionDto();
		if (!Strings.isNullOrEmpty(connectionIdStr)) {
			Integer connectionId = Integer.parseInt(connectionIdStr);
			Connection1 entity = connectionDao.getById(connectionId);
			dto.setId(entity.getId());
            dto.setServiceId(entity.getServiceId());
            dto.setUserId(entity.getUserId());
            dto.setDate(entity.getDate());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("connection-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Connection1 connection = new Connection1();
		String connectionIdStr = req.getParameter("id");
        String userIdStr = req.getParameter("userId");
        String serviceIdStr = req.getParameter("serviceId");
        connection.setUserId(userIdStr == null ? null : Integer.parseInt(userIdStr));
        connection.setServiceId(serviceIdStr == null ? null : Integer.parseInt(serviceIdStr));
        connection.setDate(Timestamp.valueOf(req.getParameter("date") + " 00:00:00.000"));
		if (Strings.isNullOrEmpty(connectionIdStr)) {
			connectionDao.insert(connection);
		} else {
			connection.setId(Integer.parseInt(serviceIdStr));
			connectionDao.update(connection);
		}
		res.sendRedirect("/connection");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		connectionDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}
package by.grsu.dbobovik.phonestat.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Strings;

import by.grsu.dbobovik.phonestat.db.dao.IDao;
import by.grsu.dbobovik.phonestat.db.dao.impl.ServiceDaoImpl;
import by.grsu.dbobovik.phonestat.db.model.Service;
import by.grsu.dbobovik.phonestat.web.dto.ServiceDto;

public class ServiceServlet extends HttpServlet{
    private static final IDao<Integer, Service> serviceDao = ServiceDaoImpl.INSTANCE;

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
		List<Service> services = serviceDao.getAll(); // get data

		List<ServiceDto> dtos = services.stream().map((entity) -> {
			ServiceDto dto = new ServiceDto();
			dto.setId(entity.getId());
			dto.setName(entity.getName());
            dto.setCost(entity.getCost());

			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.getRequestDispatcher("service-list.jsp").forward(req, res);
	}

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String serviceIdStr = req.getParameter("id");
		ServiceDto dto = new ServiceDto();
		if (!Strings.isNullOrEmpty(serviceIdStr)) {
			Integer serviceId = Integer.parseInt(serviceIdStr);
			Service entity = serviceDao.getById(serviceId);
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setCost(entity.getCost());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("service-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Service service = new Service();
		String serviceIdStr = req.getParameter("id");
        service.setName(req.getParameter("name"));
		service.setCost(Double.parseDouble(req.getParameter("cost")));
		if (Strings.isNullOrEmpty(serviceIdStr)) {
			serviceDao.insert(service);
		} else {
			service.setId(Integer.parseInt(serviceIdStr));
			serviceDao.update(service);
		}
		res.sendRedirect("/service");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		serviceDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}

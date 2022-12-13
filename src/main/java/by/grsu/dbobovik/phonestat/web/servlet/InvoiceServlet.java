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
import by.grsu.dbobovik.phonestat.db.dao.impl.InvoiceDaoImpl;
import by.grsu.dbobovik.phonestat.db.model.Invoice;
import by.grsu.dbobovik.phonestat.db.model.User;
import by.grsu.dbobovik.phonestat.web.dto.UserDto;
import by.grsu.dbobovik.phonestat.db.dao.impl.UserDaoImpl;
import by.grsu.dbobovik.phonestat.web.dto.InvoiceDto;

public class InvoiceServlet extends HttpServlet{
    private static final IDao<Integer, Invoice> invoiceDao = InvoiceDaoImpl.INSTANCE;
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
		List<Invoice> Invoices = invoiceDao.getAll(); // get data

		List<InvoiceDto> dtos = Invoices.stream().map((entity) -> {
			InvoiceDto dto = new InvoiceDto();
			dto.setId(entity.getId());
			dto.setInvoice(entity.getInvoice());

			User user = userDao.getById(entity.getUserId());
			dto.setUserData(user.getName() + user.getSurname());
			return dto;
		}).collect(Collectors.toList());

		req.setAttribute("list", dtos);
		req.setAttribute("allUsers", getAllUsersDtos());
		req.getRequestDispatcher("invoice-list.jsp").forward(req, res);
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

	private void handleEditView(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String invoiceIdStr = req.getParameter("id");
		InvoiceDto dto = new InvoiceDto();
		if (!Strings.isNullOrEmpty(invoiceIdStr)) {
			Integer invoiceId = Integer.parseInt(invoiceIdStr);
			Invoice entity = invoiceDao.getById(invoiceId);
			dto.setId(entity.getId());
			dto.setInvoice(entity.getInvoice());
			dto.setUserId(entity.getUserId());
		}
		req.setAttribute("dto", dto);
		req.getRequestDispatcher("invoice-edit.jsp").forward(req, res);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doPost");
		Invoice invoice = new Invoice();
		String invoiceIdStr = req.getParameter("id");
		String userIdStr = req.getParameter("userId");
		invoice.setInvoice(Double.parseDouble(req.getParameter("invoice")));
		invoice.setUserId(userIdStr == null ? null : Integer.parseInt(userIdStr));
		if (Strings.isNullOrEmpty(invoiceIdStr)) {
			invoiceDao.insert(invoice);
		} else {
			invoice.setId(Integer.parseInt(invoiceIdStr));
			invoiceDao.update(invoice);
		}
		res.sendRedirect("/invoice");
	}

	@Override
	public void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("doDelete");
		invoiceDao.delete(Integer.parseInt(req.getParameter("id")));
	}
}

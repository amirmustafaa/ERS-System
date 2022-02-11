package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.User;
import service.ReimbursementServiceImpl;

public class TicketController {

	static ReimbursementServiceImpl service = new ReimbursementServiceImpl();

	public static void getTicket(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		PrintWriter printer = resp.getWriter();

		printer.write(new ObjectMapper().writeValueAsString(service.getTicketById(1)));

	}

	public static void getTicketByAuthor(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);

		if (session != null) {
			PrintWriter printer = resp.getWriter();
			// System.out.print(req.getSession().getAttribute("user"));
			session = req.getSession(false);
			User user = (User) session.getAttribute("user");

			int id = user.getId();
			printer.write(new ObjectMapper().writeValueAsString(service.getTicketByAuthor(id)));
		}
	}

	public static void getTicketByStatus(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		User user = (User) req.getSession().getAttribute("user");
		HttpSession session = req.getSession(false);

		if (session != null && user.getRoleId() == 2) {
			PrintWriter printer = resp.getWriter();
			String i = req.getParameter("id");
			int id = Integer.parseInt(i);
			printer.write(new ObjectMapper().writeValueAsString(service.getTicketByStatus(id)));
		}
	}

	public static void tickets(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		User user = (User) req.getSession().getAttribute("user");
		if (session != null && user.getRoleId() == 2) {
			PrintWriter printer = resp.getWriter();
			printer.write(new ObjectMapper().writeValueAsString(service.getAllTickets()));
		}

	}

	public static void createTicket(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session = req.getSession(false);
		
		if (session != null) {
			PrintWriter printer = resp.getWriter();
			String am = req.getParameter("amount");
			int amount = Integer.parseInt(am);
			String description = req.getParameter("description");
			String type = req.getParameter("type");
			int typeId;
			if (type.equals("lodging") || type.equals("Lodging")) {
				typeId = 1;
			} else if (type.equals("travel") || type.equals("Travel")) {
				typeId = 2;
			} else if (type.equals("food") || type.equals("Food")) {
				typeId = 3;
			} else {
				typeId = 4;
			}

			User user = (User) req.getSession().getAttribute("user");

			Timestamp timestamp = Timestamp.from(Instant.now());

			printer.write(new ObjectMapper().writeValueAsString(
					service.createTicket(amount, timestamp, description, null, user.getId(), 1, typeId)));
			resp.sendRedirect("/ExpenseReimbursementSystem/resources/html/EmployeeTickets.html");
		}
	}

	public static void approveDeny(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HttpSession session = req.getSession(false);
		User user = (User) req.getSession().getAttribute("user");

		if (session != null && user.getRoleId() == 2) {
			PrintWriter printer = resp.getWriter();
			String i = req.getParameter("id");
			String choice = req.getParameter("choice");
			int id = Integer.parseInt(i);
			printer.write(new ObjectMapper().writeValueAsString(service.approveDeny(id, choice)));
			resp.sendRedirect("/ExpenseReimbursementSystem/resources/html/ManagerTickets.html");
		}
	}

}

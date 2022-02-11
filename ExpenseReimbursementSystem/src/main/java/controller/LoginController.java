package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import models.User;
import service.ReimbursementServiceImpl;

public class LoginController {

	static ReimbursementServiceImpl service = new ReimbursementServiceImpl();

	public static void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter printer = resp.getWriter();

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		User user = service.login(username, password);

		if (user == null) {

			printer.write(new ObjectMapper().writeValueAsString("Incorrect Username or Password"));
		} else {
			req.getSession().setAttribute("user", user);

		}
		if (user.getRoleId() == 1) {
			resp.sendRedirect("/ExpenseReimbursementSystem/resources/html/EmployeeTickets.html");
		} else {
			resp.sendRedirect("/ExpenseReimbursementSystem/resources/html/ManagerTickets.html");
		}
	}
	
	public static void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getSession().invalidate();
		resp.sendRedirect("ExpenseReimbursementSystem/");
	}

}

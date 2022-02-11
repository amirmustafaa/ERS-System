package frontcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import controller.LoginController;
import controller.TicketController;


public class Dispatcher {

	public static void myVirtualRouter(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException{
		System.out.println("\n\n\n\tIn My Dispatcher (myVirtualRouter())");

		System.out.println("Current URL: " + req.getRequestURL());
		System.out.println("Current URI: " + req.getRequestURI());

		switch (req.getRequestURI()) {
		case "/ExpenseReimbursementSystem/user/login":
			LoginController.login(req, resp);
			break;
		case "/ExpenseReimbursementSystem/user/logout":
			LoginController.logout(req, resp);
			break;
		case "/ExpenseReimbursementSystem/user/createticket":
			TicketController.createTicket(req, resp);
			break;
		case "/ExpenseReimbursementSystem/user/ticket":
			TicketController.getTicket(req, resp);
			break;
		case "/ExpenseReimbursementSystem/user/authortickets":
			TicketController.getTicketByAuthor(req, resp);
			break;
		case "/ExpenseReimbursementSystem/user/statustickets":
			TicketController.getTicketByStatus(req, resp);
			break;
			
		case "/ExpenseReimbursementSystem/user/alltickets":
			TicketController.tickets(req, resp);
			break;
		case "/ExpenseReimbursementSystem/user/approvedeny":
			TicketController.approveDeny(req, resp);
			break;
		default:
			System.out.println("Incorrect URI");
			break;
		}
	}
}

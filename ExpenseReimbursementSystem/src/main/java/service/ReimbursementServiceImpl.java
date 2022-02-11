package service;

import java.sql.Blob;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import dao.ReimbursementDaoImpl;
import models.Reimbursement;
import models.User;

public class ReimbursementServiceImpl implements ReimbursementService {

	ReimbursementDaoImpl dao = new ReimbursementDaoImpl();

	@Override
	public User login(String username, String password) {
		return dao.login(username, password);

	}

	@Override
	public Reimbursement createTicket(int amount, Timestamp submitted, String description, Blob receipt, int authorId,
			int statusId, int typeId){
		return dao.createTicket(amount, submitted, description, receipt, authorId, statusId, typeId);

	}

	@Override
	public List<Reimbursement>getTicketByStatus(int id) {
		return dao.getTicketByStatus(id);
		

	}

	@Override
	public List<Reimbursement> getAllTickets() {
		return dao.getAllTickets();
	}
	
	@Override
	public List<Reimbursement>getTicketByAuthor(int id) {
		return dao.getTicketByAuthor(id);

	}

	@Override
	public Reimbursement getTicketById(int id) {
		return dao.getTicketById(id);
		
	}

	@Override
	public boolean approveDeny(int id, String choice) {
		return dao.approveDeny(id, choice);

	}

	

}

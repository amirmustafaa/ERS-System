package dao;

import java.sql.Blob;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import models.Reimbursement;
import models.User;

public interface ReimbursementDao {

	public User login(String username, String password);

	public Reimbursement createTicket(int amount, Timestamp submitted, String description, Blob receipt, int authorId,
			int statusId, int typeId);

	public List<Reimbursement> getTicketByStatus(int id);

	public List<Reimbursement> getTicketByAuthor(int id);
	
	public Reimbursement getTicketById(int id);

	public List<Reimbursement> getAllTickets();

	public boolean approveDeny(int id, String choice);

	public void deleteTicket(int id);
}

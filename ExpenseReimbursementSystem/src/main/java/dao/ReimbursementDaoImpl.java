package dao;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Reimbursement;
import models.User;

public class ReimbursementDaoImpl implements ReimbursementDao {

	static {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Static block has failed me");
		}
	}

	public static String url = "jdbc:postgresql://database-1.cs3rmdygz6ka.us-east-2.rds.amazonaws.com:5432/ExpenseDB?stringtype=unspecified";
	public static String username = "postgres";
	public static String password = "p4ssw0rd";

	@Override
	public User login(String username1, String password1) {
		User user = new User();

		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_users WHERE username = ? AND password = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username1);
			ps.setString(2, password1);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("users_id"), rs.getString("username"), rs.getString("password"),
						rs.getString("first_name"), rs.getString("last_name"), rs.getString("email"),
						rs.getInt("user_role_id"));
		
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (user.getUsername() == null) {
			return null;
		}
		return user;
	}

	@Override
	public Reimbursement createTicket(int amount, Timestamp submitted, String description, Blob receipt, int authorId,
			int statusId, int typeId){
		
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "INSERT INTO ers_reimbursement VALUES(DEFAULT,?,?,?,?,?,?,?,?,?)";
		
			Timestamp time = java.sql.Timestamp.valueOf("2000-01-01 10:10:10.0");
			
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, amount);
			ps.setTimestamp(2, submitted );
			ps.setTimestamp(3, time);
			ps.setString(4, description);
			ps.setBinaryStream(5, null);
			ps.setInt(6, authorId);
			ps.setInt(7, 1);
			ps.setInt(8, statusId);
			ps.setInt(9, typeId);
			ps.executeUpdate();

//			ResultSet rs = ps.getGeneratedKeys();
//			if (rs.next()) {
//				System.out.println("the returned PK: " + rs.getInt("id"));
//			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reimbursement> getTicketByStatus(int id) {
		List<Reimbursement> reimbList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_status_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				reimbList.add(new Reimbursement(rs.getInt("reimb_id"), rs.getInt("reimb_amount"),
						rs.getTimestamp("reimb_submitted").toString(), rs.getTimestamp("reimb_resolved").toString(),
						rs.getString("reimb_description"), rs.getBlob("reimb_receipt"), rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"), rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbList;
	}

	@Override
	public List<Reimbursement> getTicketByAuthor(int id) {
		List<Reimbursement> reimbList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_author = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				reimbList.add(new Reimbursement(rs.getInt("reimb_id"), rs.getInt("reimb_amount"),
						rs.getTimestamp("reimb_submitted").toString(), rs.getTimestamp("reimb_resolved").toString(),
						rs.getString("reimb_description"), rs.getBlob("reimb_receipt"), rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"), rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbList;
	}
	
	@Override
	public Reimbursement getTicketById(int id) {
		Reimbursement reimb = new Reimbursement();
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			String sql = "SELECT * FROM ers_reimbursement WHERE reimb_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reimb = new Reimbursement(rs.getInt("reimb_id"), rs.getInt("reimb_amount"),
						rs.getTimestamp("reimb_submitted").toString(), rs.getTimestamp("reimb_resolved").toString(),
						rs.getString("reimb_description"), rs.getBlob("reimb_receipt"), rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"), rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimb;
	}

	@Override
	public List<Reimbursement> getAllTickets() {
		List<Reimbursement> reimbList = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "SELECT * FROM ers_reimbursement";

			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				reimbList.add(new Reimbursement(rs.getInt("reimb_id"), rs.getInt("reimb_amount"),
						rs.getTimestamp("reimb_submitted").toString(), rs.getTimestamp("reimb_resolved").toString(),
						rs.getString("reimb_description"), rs.getBlob("reimb_receipt"), rs.getInt("reimb_author"),
						rs.getInt("reimb_resolver"), rs.getInt("reimb_status_id"), rs.getInt("reimb_type_id")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return reimbList;
	}

	@Override
	public boolean approveDeny(int id, String choice) {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {
			int statusId = 0;
			String sql1 = "SELECT reimb_status_id FROM ers_reimbursement WHERE reimb_id = ?";
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ps1.setInt(1, id);
			ResultSet rs = ps1.executeQuery();
			while(rs.next()) {
				statusId = rs.getInt("reimb_status_id");
			}
			
			if(statusId != 1) {
				return false;
			}
			String sql = "UPDATE ers_reimbursement SET reimb_status_id = ?, reimb_resolved = ? WHERE reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			if (choice.equals("approve")) {
				ps.setInt(1, 2);
				ps.setTimestamp(2, timestamp);
				ps.setInt(3, id);
			} else {
				ps.setInt(1, 3);
				ps.setTimestamp(2, timestamp);
				ps.setInt(3, id);
			}

			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public void deleteTicket(int id) {
		try (Connection conn = DriverManager.getConnection(url, username, password)) {

			String sql = "DELETE FROM ers_reimbursment WHERE reimb_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, id);
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}

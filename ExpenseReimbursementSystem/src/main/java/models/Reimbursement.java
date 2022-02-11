package models;

import java.sql.Blob;
import java.sql.Timestamp;

public class Reimbursement {

	private int id;
	private double amount;
	private String submitted;
	private String resolved;
	private String description;
	private Blob receipt;
	private int authorId;
	private int resolverId;
	private int statusId;
	private int typeId;

	public Reimbursement() {

	}

	public Reimbursement(int id, double amount, String submitted, String description, Blob receipt, int authorId,
			int statusId, int typeId) {
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.description = description;
		this.receipt = receipt;
		this.authorId = authorId;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	public Reimbursement(int id, double amount, String submitted, String resolved, String description,
			Blob receipt, int authorId, int resolverId, int statusId, int typeId) {
		this.id = id;
		this.amount = amount;
		this.submitted = submitted;
		this.resolved = resolved;
		this.description = description;
		this.receipt = receipt;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.statusId = statusId;
		this.typeId = typeId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getSubmitted() {
		return submitted;
	}

	public void setSubmitted(String submitted) {
		this.submitted = submitted;
	}

	public String getResolved() {
		return resolved;
	}

	public void setResolved(String resolved) {
		this.resolved = resolved;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Blob getReceipt() {
		return receipt;
	}

	public void setReceipt(Blob receipt) {
		this.receipt = receipt;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getResolverId() {
		return resolverId;
	}

	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	@Override
	public String toString() {
		return "Reimbursement [id=" + id + ", amount=" + amount + ", submitted=" + submitted + ", resolved=" + resolved
				+ ", description=" + description + ", receipt=" + receipt + "]";
	}

}

package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Group {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String note;
	private GroupAdmin admin;
	private List<GroupMember> members;
	private List<Expense> expenses;
	private PaymentDue paymentDue;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public GroupAdmin getAdmin() {
		return admin;
	}
	public void setAdmin(GroupAdmin admin) {
		this.admin = admin;
	}
	public List<GroupMember> getMembers() {
		return members;
	}
	public void setMembers(List<GroupMember> members) {
		this.members = members;
	}
	public List<Expense> getExpenses() {
		return expenses;
	}
	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}
	public PaymentDue getPaymentDue() {
		return paymentDue;
	}
	public void setPaymentDue(PaymentDue paymentDue) {
		this.paymentDue = paymentDue;
	}
	
	
	
	
}

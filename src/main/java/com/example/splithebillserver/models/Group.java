package com.example.splithebillserver.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Group {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int group_id;
	private String name;
	private String note;
	private GroupAdmin admin;
	
	@ManyToMany(mappedBy = "group")
	private List<GroupMember> members;
	
	@OneToMany(mappedBy="group")
	private List<Expense> expenses;
	
	@OneToMany(mappedBy="group")
	private List<PaymentDue> paymentDue;
	
	
	public int getGroup_id() {
		return group_id;
	}
	public void setGroup_id(int group_id) {
		this.group_id = group_id;
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
	public List<PaymentDue> getPaymentDue() {
		return paymentDue;
	}
	public void setPaymentDue(List<PaymentDue> paymentDue) {
		this.paymentDue = paymentDue;
	}
	
	
	
	
	
}

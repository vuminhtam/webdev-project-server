package com.example.splithebillserver.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BillGroup {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private String note;
	
	@ManyToOne
	private User admin;
	
	@ManyToMany(fetch = FetchType.EAGER, mappedBy = "groupsAsMember")
	private List<User> members;
	
	@OneToMany(mappedBy="billGroup")
	private List<Expense> expenses;
	
	@OneToMany(mappedBy="billGroup")
	private List<PaymentDue> paymentDue;
	
	
	
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
	public User getAdmin() {
		return admin;
	}
	public void setAdmin(User admin) {
		this.admin = admin;
	}
	public List<User> getMembers() {
		return members;
	}
	public void setMembers(List<User> members) {
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

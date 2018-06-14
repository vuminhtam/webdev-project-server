package com.example.splithebillserver.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PaymentDue {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private Date lastReminded;
	private boolean completed;
	
	private int ammountDue;
	private Date expenseDate;
	private String reminderType;
	
	@ManyToOne
	@JsonIgnore
	private User from;
	@ManyToOne
	@JsonIgnore
	private User to;
	@ManyToOne
	@JsonIgnore
	private Group group;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getLastReminded() {
		return lastReminded;
	}
	public void setLastReminded(Date lastReminded) {
		this.lastReminded = lastReminded;
	}
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public int getAmmountDue() {
		return ammountDue;
	}
	public void setAmmountDue(int ammountDue) {
		this.ammountDue = ammountDue;
	}
	public Date getExpenseDate() {
		return expenseDate;
	}
	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}
	public String getReminderType() {
		return reminderType;
	}
	public void setReminderType(String reminderType) {
		this.reminderType = reminderType;
	}
	public User getFrom() {
		return from;
	}
	public void setFrom(User from) {
		this.from = from;
	}
	public User getTo() {
		return to;
	}
	public void setTo(User to) {
		this.to = to;
	}
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
}

package com.example.splithebillserver.models;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public class Expense {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int ammount;
	private String expenseType;
	private String note;
	private Date expenseDate;

	@ManyToOne
	@JsonIgnore
	private BillGroup billGroup;
	
	public BillGroup getBillGroup() {
		return billGroup;
	}
	public void setBillGroup(BillGroup billGroup) {
		this.billGroup = billGroup;
	}
	@ManyToOne
	@JsonIgnore
	private User expenser; 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAmmount() {
		return ammount;
	}
	public void setAmmount(int ammount) {
		this.ammount = ammount;
	}
	public User getExpenser() {
		return expenser;
	}
	public void setExpenser(User expenser) {
		this.expenser = expenser;
	}
	
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public Date getExpenseDate() {
		return expenseDate;
	}
	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}
	public String getExpenseType() {
		return expenseType;
	}
	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
}

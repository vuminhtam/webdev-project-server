package com.example.splithebillserver.models;

import javax.persistence.Entity;

@Entity
public class TravelExpense extends Expense {
	public TravelExpense(BillGroup group, User mem, int ammount) {
		super(group, mem, ammount);
	}
	private String org;
	private String dest;
	private String transportation; //plane/taxi...
	
	public String getTransportation() {
		return transportation;
	}
	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}
	public String getOrg() {
		return org;
	}
	public void setOrg(String org) {
		this.org = org;
	}
	public String getDest() {
		return dest;
	}
	public void setDest(String dest) {
		this.dest = dest;
	}
	
}

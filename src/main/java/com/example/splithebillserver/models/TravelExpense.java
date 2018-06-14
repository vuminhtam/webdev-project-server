package com.example.splithebillserver.models;

import javax.persistence.Entity;

@Entity
public class TravelExpense extends Expense {
	private String from;
	private String to;
	private String transportation; //plane/taxi...
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getTransportation() {
		return transportation;
	}
	public void setTransportation(String transportation) {
		this.transportation = transportation;
	}
	
}

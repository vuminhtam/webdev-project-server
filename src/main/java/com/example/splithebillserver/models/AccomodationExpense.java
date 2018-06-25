package com.example.splithebillserver.models;

import javax.persistence.Entity;

@Entity
public class AccomodationExpense extends Expense {
	public AccomodationExpense(BillGroup group, User mem, int ammount) {
		super(group, mem, ammount);
		// TODO Auto-generated constructor stub
	}

	private String hotel;

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
}

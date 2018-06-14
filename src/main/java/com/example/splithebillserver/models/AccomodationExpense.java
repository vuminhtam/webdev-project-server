package com.example.splithebillserver.models;

import javax.persistence.Entity;

@Entity
public class AccomodationExpense extends Expense {
	private String hotel;

	public String getHotel() {
		return hotel;
	}

	public void setHotel(String hotel) {
		this.hotel = hotel;
	}
}

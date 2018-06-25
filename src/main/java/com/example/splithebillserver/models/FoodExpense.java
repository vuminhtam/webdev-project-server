package com.example.splithebillserver.models;

import javax.persistence.Entity;

@Entity
public class FoodExpense extends Expense {
	public FoodExpense(BillGroup group, User mem, int ammount) {
		super(group, mem, ammount);
	}

	private String restaurant;

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}
	
}

package models;

import javax.persistence.Entity;

@Entity
public class FoodExpense extends Expense {
	private String restaurant;

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}
	
}

package com.example.splithebillserver.models;

import javax.persistence.Entity;

@Entity
public class OtherExpense extends Expense {

	public OtherExpense(BillGroup group, User mem, int ammount) {
		super(group, mem, ammount);
	}

}

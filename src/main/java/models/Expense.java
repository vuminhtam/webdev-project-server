package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Expense {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int ammount;
	private Group group;
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
	public Group getGroup() {
		return group;
	}
	public void setGroup(Group group) {
		this.group = group;
	}
	public User getExpenser() {
		return expenser;
	}
	public void setExpenser(User expenser) {
		this.expenser = expenser;
	}
	
}

package com.example.splithebillserver.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class GroupAdmin extends User {
	
	@OneToMany(mappedBy = "admin")
	private List<BillGroup> groups;

	public List<BillGroup> getGroup() {
		return groups;
	}

	public void setGroup(List<BillGroup> group) {
		this.groups = group;
	}

	
}

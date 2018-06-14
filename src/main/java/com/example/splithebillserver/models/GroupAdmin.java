package com.example.splithebillserver.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class GroupAdmin extends User {
	
	@OneToMany(mappedBy = "admin")
	private List<Group> groups;

	public List<Group> getGroup() {
		return groups;
	}

	public void setGroup(List<Group> group) {
		this.groups = group;
	}

	
}

package com.example.splithebillserver.models;

import javax.persistence.Entity;

@Entity
public class GroupAdmin extends User {
	private Group group;

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	
}

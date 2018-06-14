package com.example.splithebillserver.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;

@Entity
public class GroupMember extends User {
	
	@ManyToMany(cascade= {CascadeType.ALL})
	 @JoinTable(
		        name = "Group_GroupMember", 
		        joinColumns = { @JoinColumn(name = "person_id") }, 
		        inverseJoinColumns = { @JoinColumn(name = "group_id") }
		    )
	private List<Group> group;

	public List<Group> getGroup() {
		return group;
	}

	public void setGroup(List<Group> group) {
		this.group = group;
	}
	
	
}

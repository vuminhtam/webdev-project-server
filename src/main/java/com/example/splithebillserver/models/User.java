package com.example.splithebillserver.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class User extends Person{
	private String password;
	private String email;
	private String phone;
	private String pictureURL;
	
	@OneToMany(mappedBy="expenser")
	private List<Expense> expenses;
	
	@OneToMany(mappedBy = "admin")
	@JsonIgnore
	private List<BillGroup> groupsAsAdmin;
	
	@ManyToMany(cascade= {CascadeType.ALL}, fetch = FetchType.EAGER)
	@JoinTable(
		        name = "Group_GroupMember", 
		        joinColumns = { @JoinColumn(name = "person_id") }, 
		        inverseJoinColumns = { @JoinColumn(name = "group_id") }
		    )
	@JsonIgnore
	private List<BillGroup> groupsAsMember;
	
	@OneToOne(mappedBy="userId", cascade=CascadeType.ALL, fetch=FetchType.LAZY, optional=true)
	private FacebookUser fbId;
	
	public List<BillGroup> getGroupsAsMember() {
		return groupsAsMember;
	}
	public void setGroupsAsMember(List<BillGroup> groupsAsMember) {
		this.groupsAsMember = groupsAsMember;
	}
	public List<BillGroup> getGroupsAsAdmin() {
		return groupsAsAdmin;
	}
	public void setGroupsAsAdmin(List<BillGroup> groupsAsAdmin) {
		this.groupsAsAdmin = groupsAsAdmin;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPictureURL() {
		return pictureURL;
	}
	public void setPictureURL(String pictureURL) {
		this.pictureURL = pictureURL;
	}
	public List<Expense> getExpenses() {
		return expenses;
	}
	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}
	public FacebookUser getFbId() {
		return fbId;
	}
	public void setFbId(FacebookUser fbId) {
		this.fbId = fbId;
	}
	
	
	
	
}

package com.example.splithebillserver.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FacebookUser {

@Id
private Long fbId;

@OneToOne
@JsonIgnore
private User userId;

public FacebookUser() {
	
}
public FacebookUser(Long fbId, User userId) {
	this.fbId = fbId;
	this.userId = userId;
}

public Long getFbId() {
	return fbId;
}

public void setFbId(Long fbId) {
	this.fbId = fbId;
}

public User getUserId() {
	return userId;
}

public void setUserId(User userId) {
	this.userId = userId;
}
	


}

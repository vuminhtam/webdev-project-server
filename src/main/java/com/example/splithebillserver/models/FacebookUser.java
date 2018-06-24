package com.example.splithebillserver.models;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class FacebookUser {

@Id
private long fbId;

@OneToOne(fetch=FetchType.LAZY)
private User userId;

public long getFbId() {
	return fbId;
}

public void setFbId(long fbId) {
	this.fbId = fbId;
}

public User getUserId() {
	return userId;
}

public void setUserId(User userId) {
	this.userId = userId;
}
	


}

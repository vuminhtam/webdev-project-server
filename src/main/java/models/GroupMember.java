package models;

import javax.persistence.Entity;

@Entity
public class GroupMember extends User {
	private Group group;

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}
	
	
}

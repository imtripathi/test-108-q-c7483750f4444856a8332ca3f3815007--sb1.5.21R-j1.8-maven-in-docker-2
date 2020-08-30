package org.codejudge.sb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class FriendRequest {
@Id
@GeneratedValue
private long id;
private String friendReqFrom;
private String friendReqTo;
private int accepted;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getFriendReqFrom() {
	return friendReqFrom;
}
public void setFriendReqFrom(String friendReqFrom) {
	this.friendReqFrom = friendReqFrom;
}
public String getFriendReqTo() {
	return friendReqTo;
}
public void setFriendReqTo(String friendReqTo) {
	this.friendReqTo = friendReqTo;
}
public int getAccepted() {
	return accepted;
}
public void setAccepted(int accepted) {
	this.accepted = accepted;
}

	
}

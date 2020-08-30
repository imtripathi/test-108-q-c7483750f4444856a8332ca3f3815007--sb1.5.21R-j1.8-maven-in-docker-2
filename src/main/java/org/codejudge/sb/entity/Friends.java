package org.codejudge.sb.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Friends {
	@Id
	@GeneratedValue
	private long id;
	private String friendReqFrom;
	private String friendReqTo;
	private int friend;
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
	public int getFriend() {
		return friend;
	}
	public void setFriend(int friend) {
		this.friend = friend;
	}
	
}

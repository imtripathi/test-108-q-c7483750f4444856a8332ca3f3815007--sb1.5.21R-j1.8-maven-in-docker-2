package org.codejudge.sb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.UniqueElements;

@Entity
@Table(uniqueConstraints= @UniqueConstraint (columnNames= {"username"}))
public class User {
	
	@Id
    @GeneratedValue
    private long userid;
	private String username;
	public long getUserid() {
		return userid;
	}
	public void setUserid(long userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	

}

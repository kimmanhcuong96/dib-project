package com.dib.dib.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "login_table")
public class Login {

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 @Column(name = "id")
	 private Integer iD ;
	 
	 @Column(name = "user_name")
	  private String user;
	  
	 @Column(name = "password")
	 private String password;

	public Integer getiD() {
		return iD;
	}

	public void setiD(Integer iD) {
		this.iD = iD;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	 
	 
}

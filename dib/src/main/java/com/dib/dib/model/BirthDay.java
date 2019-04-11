package com.dib.dib.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nam_sinh")
public class BirthDay {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer iD ;
	
	@Column(name = "nam_sinh")
	private Integer birthDay;

	public Integer getiD() {
		return iD;
	}

	public void setiD(Integer iD) {
		this.iD = iD;
	}

	public Integer getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Integer birthDay) {
		this.birthDay = birthDay;
	}

	
	
}

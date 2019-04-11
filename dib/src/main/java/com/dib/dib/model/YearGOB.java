package com.dib.dib.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nam_di_b")
public class YearGOB {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer iD ;
	
	@Column(name = "nam_di_b")
	private Integer yearGoB;

	public Integer getiD() {
		return iD;
	}

	public void setiD(Integer iD) {
		this.iD = iD;
	}

	public Integer getYearGoB() {
		return yearGoB;
	}

	public void setYearGoB(Integer yearGoB) {
		this.yearGoB = yearGoB;
	}
	
	

}

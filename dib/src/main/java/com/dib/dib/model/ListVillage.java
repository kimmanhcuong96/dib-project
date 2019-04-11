package com.dib.dib.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "thon")
public class ListVillage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer iD ;
	
	@Column(name = "thon")
	private String villageName;

	public Integer getiD() {
		return iD;
	}

	public void setiD(Integer iD) {
		this.iD = iD;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}
	
	
}

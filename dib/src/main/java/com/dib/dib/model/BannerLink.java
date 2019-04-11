package com.dib.dib.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "banner_links")
public class BannerLink {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id ;
	
	@Column(name = "banner_link")
	private String bannerLink;
	
	@Column(name = "position")
	private String position;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBannerLink() {
		return bannerLink;
	}

	public void setBannerLink(String bannerLink) {
		this.bannerLink = bannerLink;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

}

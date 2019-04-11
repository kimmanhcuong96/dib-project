package com.dib.dib.dto;

public class SearchInfoForm {
	private String name;
	private Integer birthDayStart;
	private Integer birthDayEnd;
	private String aliasName;
	private String district;
	private String city;
	private String ward;
	private String village;
	private Integer goBStart;
	private Integer goBEnd;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBirthDayStart() {
		return birthDayStart;
	}
	public void setBirthDayStart(Integer birthDayStart) {
		this.birthDayStart = birthDayStart;
	}
	public Integer getBirthDayEnd() {
		return birthDayEnd;
	}
	public void setBirthDayEnd(Integer birthDayEnd) {
		this.birthDayEnd = birthDayEnd;
	}
	public String getAliasName() {
		return aliasName;
	}
	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getWard() {
		return ward;
	}
	public void setWard(String ward) {
		this.ward = ward;
	}
	public String getVillage() {
		return village;
	}
	public void setVillage(String village) {
		this.village = village;
	}
	public Integer getGoBStart() {
		return goBStart;
	}
	public void setGoBStart(Integer goBStart) {
		this.goBStart = goBStart;
	}
	public Integer getGoBEnd() {
		return goBEnd;
	}
	public void setGoBEnd(Integer goBEnd) {
		this.goBEnd = goBEnd;
	}
	@Override
	public String toString() {
		return "SearchInfoForm [name=" + name + ", birthDayStart=" + birthDayStart + ", birthDayEnd=" + birthDayEnd
				+ ", aliasName=" + aliasName + ", district=" + district + ", city=" + city + ", ward=" + ward
				+ ", village=" + village + ", goBStart=" + goBStart + ", goBEnd=" + goBEnd + "]";
	}
	
	
}

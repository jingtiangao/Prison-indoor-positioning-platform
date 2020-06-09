package com.location.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@SuppressWarnings("serial")
@Entity
@Table(name="ls_region")
public class Region implements Serializable {
private int region_id;
private String regionName;
private String manager;
private String remark;
@Id
@Column(name="region_id",unique=true,nullable=false)
public int getRegion_id() {
	return region_id;
}
public void setRegion_id(int region_id) {
	this.region_id = region_id;
}
@Column(name="region_name",unique = true,nullable=false,length=30)
public String getRegionName() {
	return regionName;
}
public void setRegionName(String regionName) {
	this.regionName = regionName;
}
@Column(name="manager")
public String getManager() {
	return manager;
}
public void setManager(String manager) {
	this.manager = manager;
}
@Column(name="remark")
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
}

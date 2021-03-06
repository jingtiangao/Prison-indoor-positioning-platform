package com.location.entity;
// Generated 2016-11-26 12:39:38 by Hibernate Tools 3.6.0.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * LsArea generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ls_area", catalog = "graininsects")
public class LsArea implements java.io.Serializable {

	private int areaId;
	private String areaName;
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int shape;
	private String remark;

	public LsArea() {
	}

	public LsArea(int areaId, String areaName) {
		this.areaId = areaId;
		this.areaName = areaName;
	}
	public LsArea(int areaId, String areaName, int x1, int y1,
			int x2, int y2, int shape, String remark) {
		this.areaId = areaId;
		this.areaName = areaName;
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.shape = shape;
		this.remark = remark;
	}

	@Id

	@Column(name = "area_id", unique = true, nullable = false)
	public int getAreaId() {
		return this.areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	@Column(name = "area_name", nullable = false,unique = true, length = 30)
	public String getAreaName() {
		return this.areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Column(name = "x1")
	public int getX1() {
		return this.x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	@Column(name = "y1")
	public int getY1() {
		return this.y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	@Column(name = "x2")
	public int getX2() {
		return this.x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	@Column(name = "y2")
	public int getY2() {
		return this.y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	@Column(name = "shape")
	public int getShape() {
		return this.shape;
	}

	public void setShape(int shape) {
		this.shape = shape;
	}

	@Column(name = "remark", length = 300)
	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}

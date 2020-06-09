package com.grain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
	实时数据表，图片记录
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_realdata_pic")
public class RealdataPic implements java.io.Serializable {

	private int id;
	private DeviceRealdata deviceRealdata;
	private String pic;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
	@JsonIgnore
	public DeviceRealdata getDeviceRealdata() {
		return this.deviceRealdata;
	}

	public void setDeviceRealdata(DeviceRealdata deviceRealdata) {
		this.deviceRealdata = deviceRealdata;
	}

	@Column(name = "pic", nullable = false)
	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

}

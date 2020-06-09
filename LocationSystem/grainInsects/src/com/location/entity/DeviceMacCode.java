package com.location.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "ls_device_mac_code")
public class DeviceMacCode implements java.io.Serializable{
	private String device_mac;
	private String device_code;
	@Id
	@Column(name = "device_mac", unique = true, nullable = false)
	public String getDevice_mac() {
		return device_mac;
	}
	public void setDevice_mac(String device_mac) {
		this.device_mac = device_mac;
	}
	@Column(name = "device_code",unique = true, nullable = false)
	public String getDevice_code() {
		return device_code;
	}
	public void setDevice_code(String device_code) {
		this.device_code = device_code;
	}
}

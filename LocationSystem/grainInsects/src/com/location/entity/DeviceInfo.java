package com.location.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "ls_device_info")
public class DeviceInfo implements java.io.Serializable{
	private int accuracy;
	private String build_id;
	private int error_code;
	private int floor_id;
	private String info;
	private String nearest_tag_id;
	private double timestamp_millisecond;
	private int device_id;
	private int x_millimeter;
	private int y_millimeter;
	private int compass;
	private int alarm;
	private String device_mac;
	
	@Id
	@Column(name = "device_id", unique = true, nullable = false)
	public int getDevice_id() {
		return device_id;
	}
	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}
	
	@Column(name = "device_mac", nullable = false)
	public String getDevice_mac() {
		return device_mac;
	}
	public void setDevice_mac(String device_mac) {
		this.device_mac = device_mac;
	}
	@Column(name = "timestamp_millisecond", nullable = false)
	public void setTimestamp_millisecond(double timestamp_millisecond) {
		this.timestamp_millisecond = timestamp_millisecond;
	}
	public Double getTimestamp_millisecond() {
		return timestamp_millisecond;
	}
	
	@Column(name = "accuracy", nullable = false)
	public int getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	@Column(name = "build_id", nullable = false)
	public String getBuild_id() {
		return build_id;
	}
	public void setBuild_id(String build_id) {
		this.build_id = build_id;
	}
	@Column(name = "error_code")
	public Integer getError_code() {
		return error_code;
	}
	public void setError_code(Integer error_code) {
		this.error_code = error_code;
	}
	@Column(name = "floor_id")
	public Integer getFloor_id() {
		return floor_id;
	}
	public void setFloor_id(Integer floor_id) {
		this.floor_id = floor_id;
	}
	@Column(name = "info")
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	@Column(name = "nearest_tag_id")
	public String getNearest_tag_id() {
		return nearest_tag_id;
	}
	public void setNearest_tag_id(String nearest_tag_id) {
		this.nearest_tag_id = nearest_tag_id;
	}
	@Column(name = "timestamp_millisecond")

	public void setTimestamp_millisecond(Double timestamp_millisecond) {
		this.timestamp_millisecond = timestamp_millisecond;
	}

	@Column(name = "x_millimeter")
	public Integer getX_millimeter() {
		return x_millimeter;
	}
	public void setX_millimeter(Integer x_millimeter) {
		this.x_millimeter = x_millimeter;
	}
	@Column(name = "y_millimeter")
	public Integer getY_millimeter() {
		return y_millimeter;
	}
	public void setY_millimeter(Integer y_millimeter) {
		this.y_millimeter = y_millimeter;
	}
	@Column(name = "compass")
	public Integer getCompass() {
		return compass;
	}
	public void setCompass(Integer compass) {
		this.compass = compass;
	}
	@Column(name = "alarm")
	public int getAlarm() {
		return alarm;
	}
	public void setAlarm(int alarm) {
		this.alarm = alarm;
	}

}

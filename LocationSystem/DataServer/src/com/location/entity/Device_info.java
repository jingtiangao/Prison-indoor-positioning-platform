package com.location.entity;


@SuppressWarnings("serial")
public class Device_info implements java.io.Serializable{
	private int device_id;
	private String user_id;
	private int accuracy;
	private String build_id;
	private int error_code;
	private int floor_id;
	private String info;
	private String nearest_tag_id;
	private double timestamp_millisecond;
	private int x_millimeter;
	private int y_millimeter;
	private int compass;
	private boolean alarm;
	public int getDevice_id() {
		return device_id;
	}
	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getAccuracy() {
		return accuracy;
	}
	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}
	public String getBuild_id() {
		return build_id;
	}
	public void setBuild_id(String build_id) {
		this.build_id = build_id;
	}
	public int getError_code() {
		return error_code;
	}
	public void setError_code(int error_code) {
		this.error_code = error_code;
	}
	public int getFloor_id() {
		return floor_id;
	}
	public void setFloor_id(int floor_id) {
		this.floor_id = floor_id;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public String getNearest_tag_id() {
		return nearest_tag_id;
	}
	public void setNearest_tag_id(String nearest_tag_id) {
		this.nearest_tag_id = nearest_tag_id;
	}
	public double getTimestamp_millisecond() {
		return timestamp_millisecond;
	}
	public void setTimestamp_millisecond(double timestamp_millisecond) {
		this.timestamp_millisecond = timestamp_millisecond;
	}
	public int getX_millimeter() {
		return x_millimeter;
	}
	public void setX_millimeter(int x_millimeter) {
		this.x_millimeter = x_millimeter;
	}
	public int getY_millimeter() {
		return y_millimeter;
	}
	public void setY_millimeter(int y_millimeter) {
		this.y_millimeter = y_millimeter;
	}
	public int getCompass() {
		return compass;
	}
	public void setCompass(int compass) {
		this.compass = compass;
	}
	public boolean isAlarm() {
		return alarm;
	}
	public void setAlarm(boolean alarm) {
		this.alarm = alarm;
	}
	
	
}

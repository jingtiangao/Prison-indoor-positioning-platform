package com.location.entity;

import java.util.HashMap;

public class PrisonerInfo {
	private String prisoner_name;
	private String prisoner_code;
	private int state;
	private int x;
	private int y;
	private String head_url;
	
	public PrisonerInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public void setInfo(DeviceInfo deviceInfo,UserInfo userInfo){
		this.prisoner_name = userInfo.getUser_name();
		this.prisoner_code = userInfo.getUser_code();
		this.state = userInfo.getState();
		this.x = deviceInfo.getX_millimeter();
		this.y = deviceInfo.getY_millimeter();
		this.head_url = userInfo.getUser_head();
	}
	
	public HashMap<String, String> toHashMap(){
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("prisoner_name", this.prisoner_name);
		hashMap.put("prisoner_code", this.prisoner_code);
		hashMap.put("state", String.valueOf(this.state));
		hashMap.put("x",String.valueOf(this.x));
		hashMap.put("y",String.valueOf(this.y));
		hashMap.put("head_url",this.head_url);
		return hashMap;
	}

	@Override
	public String toString() {
		return "PrisonerInfo [prisoner_name=" + prisoner_name + ", prisoner_code=" + prisoner_code + ", state=" + state
				+ ", x=" + x + ", y=" + y + ", head_url=" + head_url + "]";
	}

}

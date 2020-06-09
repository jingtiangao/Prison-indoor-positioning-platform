package com.grain.entity.page;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AreaP implements Serializable{
	Long id;
	String fullname;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
}

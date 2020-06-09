package com.grain.entity.page;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Depot implements Serializable{
	String lkbm;
	String lkmc;
	public String getLkbm() {
		return lkbm;
	}
	public void setLkbm(String lkbm) {
		this.lkbm = lkbm;
	}
	public String getLkmc() {
		return lkmc;
	}
	public void setLkmc(String lkmc) {
		this.lkmc = lkmc;
	}
	
}

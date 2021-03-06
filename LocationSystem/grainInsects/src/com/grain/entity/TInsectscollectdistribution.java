package com.grain.entity;
// default package
// Generated 2016-5-20 13:50:23 by Hibernate Tools 5.1.0.Alpha1

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * TInsectscollectdistribution generated by hbm2java
 */
/**
 * 3.0 昆虫采集分布信息表
 * @author Mark
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_insectscollectdistribution")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler",
		"tinsectsdistributions"
		})
public class TInsectscollectdistribution implements java.io.Serializable {

	private String smInsects;
	private String name;
	private List<TInsectsdistribution> TInsectsdistributions = new ArrayList<TInsectsdistribution>(0);

	public TInsectscollectdistribution() {
	}

	public TInsectscollectdistribution(String smInsects, String name) {
		this.smInsects = smInsects;
		this.name = name;
	}

	public TInsectscollectdistribution(String smInsects, String name, List<TInsectsdistribution> TInsectsdistributions) {
		this.smInsects = smInsects;
		this.name = name;
		this.TInsectsdistributions = TInsectsdistributions;
	}

	@Id
	@Column(name = "sm_insects", unique = true, nullable = false, length = 30)
	public String getSmInsects() {
		return this.smInsects;
	}

	public void setSmInsects(String smInsects) {
		this.smInsects = smInsects;
	}

	@Column(name = "name", nullable = false, length = 100)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL},orphanRemoval=true)
	@JoinColumn(name = "sm_insect")
	public List<TInsectsdistribution> getTInsectsdistributions() {
		return this.TInsectsdistributions;
	}

	public void setTInsectsdistributions(List<TInsectsdistribution> TInsectsdistributions) {
		this.TInsectsdistributions = TInsectsdistributions;
	}

}

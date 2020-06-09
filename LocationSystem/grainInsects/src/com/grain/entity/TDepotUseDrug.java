package com.grain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 使用药剂总表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_depot_use_drug")
public class TDepotUseDrug implements java.io.Serializable {

	private Long sm;
	private TDepotInvest TDepotInvest;
	private String drugname;
	private String factory;
	private Float value;
	private String usemethod;
	private String protectkind;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "sm", unique = true, nullable = false)
	public Long getSm() {
		return this.sm;
	}

	public void setSm(Long sm) {
		this.sm = sm;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "psm")
	public TDepotInvest getTDepotInvest() {
		return this.TDepotInvest;
	}

	public void setTDepotInvest(TDepotInvest TDepotInvest) {
		this.TDepotInvest = TDepotInvest;
	}

	@Column(name = "drugname", length = 60)
	public String getDrugname() {
		return this.drugname;
	}

	public void setDrugname(String drugname) {
		this.drugname = drugname;
	}

	@Column(name = "factory", length = 60)
	public String getFactory() {
		return this.factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	@Column(name = "value", precision = 12, scale = 0)
	public Float getValue() {
		return this.value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

	@Column(name = "usemethod", length = 100)
	public String getUsemethod() {
		return this.usemethod;
	}

	public void setUsemethod(String usemethod) {
		this.usemethod = usemethod;
	}

	@Column(name = "protectkind", length = 100)
	public String getProtectkind() {
		return this.protectkind;
	}

	public void setProtectkind(String protectkind) {
		this.protectkind = protectkind;
	}

}

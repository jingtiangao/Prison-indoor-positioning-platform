package com.grain.entity;
// Generated 2016-3-20 22:47:51 by Hibernate Tools 3.5.0.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 分仓使用药剂
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_bin_use_drug")
public class TBinUseDrug implements java.io.Serializable {

	private Long id;
	private TBinInvest TBinInvest;

//	private Long sm;
	private String drugname;
	private String factory;
	private Float value;
	private String usemethod;
	private String protectkind;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
//	@Column(name = "sm")
//	public Long getSm() {
//		return this.sm;
//	}
//
//	public void setSm(Long sm) {
//		this.sm = sm;
//	}

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
	
	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "sm", insertable = false, updatable = false)
	@JoinColumn(name = "sm")
	public TBinInvest getTBinInvest() {
		return this.TBinInvest;
	}

	public void setTBinInvest(TBinInvest TBinInvest) {
		this.TBinInvest = TBinInvest;
	}

}

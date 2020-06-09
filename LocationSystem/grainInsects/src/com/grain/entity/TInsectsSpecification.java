package com.grain.entity;
// Generated 2016-3-20 22:47:51 by Hibernate Tools 3.5.0.Final


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * 特征表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_insects_specification")
public class TInsectsSpecification implements java.io.Serializable {

	private Long id;
	@JsonBackReference
	private TInsectsIndex TInsectsIndex;
	private TCatalogIndex TCatalogIndex;
	private Integer xh;
	private String tz;
	private Integer zqxh;
	private String source;
	private String modifer;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date modifydate;
//	private Set<TInsectsSpecPic> TInsectsSpecPics = new HashSet<TInsectsSpecPic>(0);
	private List<TInsectsSpecPic> TInsectsSpecPics = new ArrayList<TInsectsSpecPic>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
	@JsonBackReference
	public TInsectsIndex getTInsectsIndex() {
		return this.TInsectsIndex;
	}

	@JsonBackReference
	public void setTInsectsIndex(TInsectsIndex TInsectsIndex) {
		this.TInsectsIndex = TInsectsIndex;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "jsjg")
	public TCatalogIndex getTCatalogIndex() {
		return this.TCatalogIndex;
	}

	public void setTCatalogIndex(TCatalogIndex TCatalogIndex) {
		this.TCatalogIndex = TCatalogIndex;
	}

	@Column(name = "xh")
	public Integer getXh() {
		return this.xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}

	@Column(name = "TZ")
	public String getTz() {
		return this.tz;
	}

	public void setTz(String tz) {
		this.tz = tz;
	}

	@Column(name = "zqxh")
	public Integer getZqxh() {
		return this.zqxh;
	}

	public void setZqxh(Integer zqxh) {
		this.zqxh = zqxh;
	}

	@Column(name = "source", length = 100)
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "modifer", length = 30)
	public String getModifer() {
		return this.modifer;
	}

	public void setModifer(String modifer) {
		this.modifer = modifer;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modifydate", length = 19)
	public Date getModifydate() {
		return this.modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TInsectsSpecification")
	public List<TInsectsSpecPic> getTInsectsSpecPics() {
		return this.TInsectsSpecPics;
	}

	public void setTInsectsSpecPics(List<TInsectsSpecPic> TInsectsSpecPics) {
		this.TInsectsSpecPics = TInsectsSpecPics;
	}

}

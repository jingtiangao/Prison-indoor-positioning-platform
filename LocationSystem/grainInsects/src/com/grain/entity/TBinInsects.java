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
 * 分仓害虫信息
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_bin_insects")
public class TBinInsects implements java.io.Serializable {

	private Long id;
	private TBinInvest TBinInvest;

//	private Long sm;
	private String kinds;
	private Float density;
	private String foundloc;
	private String damageloc;
	private String startinsectsdate;
	private String startkilldate;
	private String endkilldate;
	private Integer noninsectsdate;
	private Integer killdifficultlevel;

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

	@Column(name = "kinds", nullable = false, length = 100)
	public String getKinds() {
		return this.kinds;
	}

	public void setKinds(String kinds) {
		this.kinds = kinds;
	}

	@Column(name = "density", precision = 12, scale = 0)
	public Float getDensity() {
		return this.density;
	}

	public void setDensity(Float density) {
		this.density = density;
	}

	@Column(name = "foundloc", nullable = false, length = 60)
	public String getFoundloc() {
		return this.foundloc;
	}

	public void setFoundloc(String foundloc) {
		this.foundloc = foundloc;
	}

	@Column(name = "damageloc", nullable = false, length = 60)
	public String getDamageloc() {
		return this.damageloc;
	}

	public void setDamageloc(String damageloc) {
		this.damageloc = damageloc;
	}

	@Column(name = "startinsectsdate", nullable = false, length = 10)
	public String getStartinsectsdate() {
		return this.startinsectsdate;
	}

	public void setStartinsectsdate(String startinsectsdate) {
		this.startinsectsdate = startinsectsdate;
	}

	@Column(name = "startkilldate", nullable = false, length = 10)
	public String getStartkilldate() {
		return this.startkilldate;
	}

	public void setStartkilldate(String startkilldate) {
		this.startkilldate = startkilldate;
	}

	@Column(name = "endkilldate", nullable = false, length = 10)
	public String getEndkilldate() {
		return this.endkilldate;
	}

	public void setEndkilldate(String endkilldate) {
		this.endkilldate = endkilldate;
	}

	@Column(name = "noninsectsdate", nullable = false)
	public Integer getNoninsectsdate() {
		return this.noninsectsdate;
	}

	public void setNoninsectsdate(Integer noninsectsdate) {
		this.noninsectsdate = noninsectsdate;
	}

	@Column(name = "killdifficultlevel")
	public Integer getKilldifficultlevel() {
		return this.killdifficultlevel;
	}

	public void setKilldifficultlevel(Integer killdifficultlevel) {
		this.killdifficultlevel = killdifficultlevel;
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

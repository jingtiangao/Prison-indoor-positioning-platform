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
 * 总表害虫信息
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_depots_insects")
public class TDepotsInsects implements java.io.Serializable {

	private Long sm;
	private TDepotInvest TDepotInvest;
	private String kinds;
	private Float density;
	private String foundloc;
	private String damagegrain;
	private String startinsectsdate;
	private String startkilldate;
	private String endkilldate;
	private Integer noninsectsdate;
	private Integer killdifficultlevel;

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

	@Column(name = "foundloc")
	public String getFoundloc() {
		return this.foundloc;
	}

	public void setFoundloc(String foundloc) {
		this.foundloc = foundloc;
	}

	@Column(name = "damagegrain")
	public String getDamagegrain() {
		return this.damagegrain;
	}

	public void setDamagegrain(String damagegrain) {
		this.damagegrain = damagegrain;
	}

	@Column(name = "startinsectsdate")
	public String getStartinsectsdate() {
		return this.startinsectsdate;
	}

	public void setStartinsectsdate(String startinsectsdate) {
		this.startinsectsdate = startinsectsdate;
	}

	@Column(name = "startkilldate")
	public String getStartkilldate() {
		return this.startkilldate;
	}

	public void setStartkilldate(String startkilldate) {
		this.startkilldate = startkilldate;
	}

	@Column(name = "endkilldate")
	public String getEndkilldate() {
		return this.endkilldate;
	}

	public void setEndkilldate(String endkilldate) {
		this.endkilldate = endkilldate;
	}

	@Column(name = "noninsectsdate")
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

}

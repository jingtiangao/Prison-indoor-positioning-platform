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
 * 加工厂虫害信息
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_insects_infactory")
public class TInsectsInfactory implements java.io.Serializable {

	private Long id;
	private InsectsOnfactory insectsOnfactory;
	private String kind;
	private String stage;
	private Integer num;
	private String food;
	private String harm;
	private String protectmeasure;
	private String locCollect;
	private String host;


	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	// mark add
	@Column(name = "ID", nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sm_collect")
	public InsectsOnfactory getInsectsOnfactory() {
		return this.insectsOnfactory;
	}

	public void setInsectsOnfactory(InsectsOnfactory insectsOnfactory) {
		this.insectsOnfactory = insectsOnfactory;
	}

	@Column(name = "kind", length = 30)
	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	@Column(name = "stage", length = 30)
	public String getStage() {
		return this.stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}

	@Column(name = "num")
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "food", length = 30)
	public String getFood() {
		return this.food;
	}

	public void setFood(String food) {
		this.food = food;
	}

	@Column(name = "harm", length = 30)
	public String getHarm() {
		return this.harm;
	}

	public void setHarm(String harm) {
		this.harm = harm;
	}

	@Column(name = "protectmeasure", length = 100)
	public String getProtectmeasure() {
		return this.protectmeasure;
	}

	public void setProtectmeasure(String protectmeasure) {
		this.protectmeasure = protectmeasure;
	}

	@Column(name = "loc_collect", length = 30)
	public String getLocCollect() {
		return this.locCollect;
	}

	public void setLocCollect(String locCollect) {
		this.locCollect = locCollect;
	}

	@Column(name = "host", length = 30)
	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
	}

}

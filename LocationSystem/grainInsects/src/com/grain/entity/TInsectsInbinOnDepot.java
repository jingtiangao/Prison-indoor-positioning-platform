package com.grain.entity;
// Generated 2016-3-20 22:47:51 by Hibernate Tools 3.5.0.Final

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 储备库粮仓现场采集调查记录表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_insects_inbin_on_depot")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler",
		"tinsectsOndepots","tinsectpicIndepots"/*, "tgrainbin"*/
		})
public class TInsectsInbinOnDepot implements java.io.Serializable {

	private String smCollection;
	private Grainbin TGrainbin;
	private String collector;
	private String mobile;
	private String companyCollector;
	private String dateCollection;
	private String modifer;
	private String modifydate;
	private List<TInsectpicIndepot> TInsectpicIndepots = new ArrayList<TInsectpicIndepot>(0);
	private List<TInsectsOndepot> TInsectsOndepots = new ArrayList<TInsectsOndepot>(0);


	@Id
	@Column(name = "sm_collection", unique = true, nullable = false, length = 30)
	public String getSmCollection() {
		return this.smCollection;
	}

	public void setSmCollection(String smCollection) {
		this.smCollection = smCollection;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lcbm")
	public Grainbin getTGrainbin() {
		return this.TGrainbin;
	}

	public void setTGrainbin(Grainbin TGrainbin) {
		this.TGrainbin = TGrainbin;
	}

	@Column(name = "collector", length = 30)
	public String getCollector() {
		return this.collector;
	}

	public void setCollector(String collector) {
		this.collector = collector;
	}

	@Column(name = "mobile", length = 30)
	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Column(name = "company_collector", length = 30)
	public String getCompanyCollector() {
		return this.companyCollector;
	}

	public void setCompanyCollector(String companyCollector) {
		this.companyCollector = companyCollector;
	}

	@Column(name = "date_collection", length = 10)
	public String getDateCollection() {
		return this.dateCollection;
	}

	public void setDateCollection(String dateCollection) {
		this.dateCollection = dateCollection;
	}
	
	@Column(name = "modifer", length = 20)
	public String getModifer() {
		return this.modifer;
	}

	public void setModifer(String modifer) {
		this.modifer = modifer;
	}

	@Column(name = "modifydate", length = 10)
	public String getModifydate() {
		return this.modifydate;
	}

	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TInsectsInbinOnDepot", cascade={CascadeType.ALL})
	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL},orphanRemoval=true)
	@JoinColumn(name = "sm_collection")
	public List<TInsectpicIndepot> getTInsectpicIndepots() {
		return this.TInsectpicIndepots;
	}

	public void setTInsectpicIndepots(List<TInsectpicIndepot> TInsectpicIndepots) {
		this.TInsectpicIndepots = TInsectpicIndepots;
	}
	
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TInsectsInbinOnDepot", cascade={CascadeType.ALL})
	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL},orphanRemoval=true)
	@JoinColumn(name = "sm_collection")
	public List<TInsectsOndepot> getTInsectsOndepots() {
		return this.TInsectsOndepots;
	}

	public void setTInsectsOndepots(List<TInsectsOndepot> TInsectsOndepots) {
		this.TInsectsOndepots = TInsectsOndepots;
	}

}

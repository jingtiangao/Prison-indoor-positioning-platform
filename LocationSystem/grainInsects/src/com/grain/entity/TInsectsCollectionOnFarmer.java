package com.grain.entity;
// Generated 2016-3-20 22:47:51 by Hibernate Tools 3.5.0.Final

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
 * 1.1 农户储粮害虫现场采集记录表 generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_insects_collection_on_farmer")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler",
		"insectsCollectPics","insectsInfoOnFarmers", "tfarmer"
		})
public class TInsectsCollectionOnFarmer implements java.io.Serializable {

	private String smCollection;
	private Farmer TFarmer;
	private String collector;
	private String mobile;
	private String company;
	private String dateCollection;
	private String grainname;
	private String harvestdate;
	private String dryingmethod;
	private String entrydate;
	private Integer storeperiod;
	private Integer innum;
	private String purpose; //mark add
	private String storetechnology;
	private String bintype;
	private String container;
	private Float temperature;
	private Float humidity;
	private Float  moisture;	//mark add
	private Float impurity;	//mark add
	private Integer totalinsects;
	private String controltemperaturemeasures;
	private String controlhumiditymeasures;
	private String modifer;
	private String modifydate;
	private List<TInsectsInfoOnFarmer> insectsInfoOnFarmers = new ArrayList<TInsectsInfoOnFarmer>(0);
	private Set<TInsectsCollectPic> insectsCollectPics = new HashSet<TInsectsCollectPic>(0);

	public TInsectsCollectionOnFarmer(){
	}
	
	
	public TInsectsCollectionOnFarmer(String smCollection,
			String mobile, String company, String grainname,
			String harvestdate, String dryingmethod, String entrydate,
			Integer storeperiod, Integer innum, String purpose,
			String storetechnology, String bintype, String container,
			Float temperature, Float humidity, Float moisture, Float impurity,
			String controltemperaturemeasures, String controlhumiditymeasures) {
		this.smCollection = smCollection;
		this.mobile = mobile;
		this.company = company;
		this.grainname = grainname;
		this.harvestdate = harvestdate;
		this.dryingmethod = dryingmethod;
		this.entrydate = entrydate;
		this.storeperiod = storeperiod;
		this.innum = innum;
		this.purpose = purpose;
		this.storetechnology = storetechnology;
		this.bintype = bintype;
		this.container = container;
		this.temperature = temperature;
		this.humidity = humidity;
		this.moisture = moisture;
		this.impurity = impurity;
		this.controltemperaturemeasures = controltemperaturemeasures;
		this.controlhumiditymeasures = controlhumiditymeasures;
	}


	@Id
	@Column(name = "sm_collection", unique = true, nullable = false, length = 30)
	public String getSmCollection() {
		return this.smCollection;
	}

	public void setSmCollection(String smCollection) {
		this.smCollection = smCollection;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sm_farmer", nullable = false)
	public Farmer getTFarmer() {
		return this.TFarmer;
	}

	public void setTFarmer(Farmer TFarmer) {
		this.TFarmer = TFarmer;
	}

	@Column(name = "collector", nullable = false, length = 30)
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

	@Column(name = "company", length = 30)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "date_collection", nullable = false, length = 10)
	public String getDateCollection() {
		return this.dateCollection;
	}

	public void setDateCollection(String dateCollection) {
		this.dateCollection = dateCollection;
	}

	@Column(name = "grainname", length = 30)
	public String getGrainname() {
		return this.grainname;
	}

	public void setGrainname(String grainname) {
		this.grainname = grainname;
	}

	@Column(name = "harvestdate", length = 10)
	public String getHarvestdate() {
		return this.harvestdate;
	}

	public void setHarvestdate(String harvestdate) {
		this.harvestdate = harvestdate;
	}

	@Column(name = "dryingmethod", length = 30)
	public String getDryingmethod() {
		return this.dryingmethod;
	}

	public void setDryingmethod(String dryingmethod) {
		this.dryingmethod = dryingmethod;
	}

	@Column(name = "entrydate", length = 10)
	public String getEntrydate() {
		return this.entrydate;
	}

	public void setEntrydate(String entrydate) {
		this.entrydate = entrydate;
	}

	@Column(name = "storeperiod_")
	public Integer getStoreperiod() {
		return this.storeperiod;
	}

	public void setStoreperiod(Integer storeperiod) {
		this.storeperiod = storeperiod;
	}

	@Column(name = "innum")
	public Integer getInnum() {
		return this.innum;
	}

	public void setInnum(Integer innum) {
		this.innum = innum;
	}

	@Column(name = "storetechnology", length = 30)
	public String getStoretechnology() {
		return this.storetechnology;
	}

	public void setStoretechnology(String storetechnology) {
		this.storetechnology = storetechnology;
	}

	@Column(name = "bintype", length = 30)
	public String getBintype() {
		return this.bintype;
	}

	public void setBintype(String bintype) {
		this.bintype = bintype;
	}

	@Column(name = "container", length = 30)
	public String getContainer() {
		return this.container;
	}

	public void setContainer(String container) {
		this.container = container;
	}

	@Column(name = "temperature", precision = 12, scale = 0)
	public Float getTemperature() {
		return this.temperature;
	}

	public void setTemperature(Float temperature) {
		this.temperature = temperature;
	}

	@Column(name = "humidity", precision = 12, scale = 0)
	public Float getHumidity() {
		return this.humidity;
	}

	public void setHumidity(Float humidity) {
		this.humidity = humidity;
	}

	@Column(name = "totalinsects", nullable = false)
	public Integer getTotalinsects() {
		return this.totalinsects;
	}

	public void setTotalinsects(Integer totalinsects) {
		this.totalinsects = totalinsects;
	}

	@Column(name = "controltemperaturemeasures", length = 30)
	public String getControltemperaturemeasures() {
		return this.controltemperaturemeasures;
	}

	public void setControltemperaturemeasures(String controltemperaturemeasures) {
		this.controltemperaturemeasures = controltemperaturemeasures;
	}

	@Column(name = "controlhumiditymeasures", length = 30)
	public String getControlhumiditymeasures() {
		return this.controlhumiditymeasures;
	}

	public void setControlhumiditymeasures(String controlhumiditymeasures) {
		this.controlhumiditymeasures = controlhumiditymeasures;
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

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TInsectsCollectionOnFarmer", cascade={CascadeType.ALL })
	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL},orphanRemoval=true)
	@JoinColumn(name = "sm_collection")
	public List<TInsectsInfoOnFarmer> getInsectsInfoOnFarmers() {
		return this.insectsInfoOnFarmers;
	}

	public void setInsectsInfoOnFarmers(List<TInsectsInfoOnFarmer> insectsInfoOnFarmers) {
		this.insectsInfoOnFarmers = insectsInfoOnFarmers;
	}

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TInsectsCollectionOnFarmer", cascade={CascadeType.ALL })
	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL},orphanRemoval=true)
	@JoinColumn(name = "sm_collection")
	public Set<TInsectsCollectPic> getInsectsCollectPics() {
		return this.insectsCollectPics;
	}

	public void setInsectsCollectPics(Set<TInsectsCollectPic> insectsCollectPics) {
		this.insectsCollectPics = insectsCollectPics;
	}
	
	@Column(name = "purpose", length = 30)
	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	
	@Column(name = "moisture", precision = 12, scale = 0)
	public Float getMoisture() {
		return moisture;
	}

	public void setMoisture(Float moisture) {
		this.moisture = moisture;
	}
	
	@Column(name = "impurity", precision = 12, scale = 0)
	public Float getImpurity() {
		return impurity;
	}

	public void setImpurity(Float impurity) {
		this.impurity = impurity;
	}
}

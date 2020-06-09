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
 * 加工厂现场采集记录表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_insects_onfactory")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler",
		"tinsectsInfactories","tfactoryPics", "tfactoryInfo"
		})
public class InsectsOnfactory implements java.io.Serializable {

	private String smCollect;
	private FactoryInfo TFactoryInfo;
	private String collector;
	private String mobile;
	private String company;
	private String collectdate;
	private String grainkind;  // Mark add
	private String harvestdate;
	private String drymethod;
	private String indate;
	private Integer saveperiod;
	private Integer innum;
	private String storetechnology;
	private String bintype;
	private String container;
	private Float temperature;
	private Float humidity;
	private Integer totalinsects;
	private String controltemperaturemeasures;
	private String controlhumiditymeasures;
	private String modifer;
	private String modifydate;
	
	private String storageForm; //mark add
	private String qualityChange; //mark add
	private Float grainStackTemperature; //mark add 粮堆温度
	private Float grainMoisture;  //mark add 粮堆水分
	
	private List<TInsectsInfactory> TInsectsInfactories = new ArrayList<TInsectsInfactory>(0);
	private List<TFactoryPic> TFactoryPics = new ArrayList<TFactoryPic>(0);

	@Id
	@Column(name = "sm_collect", unique = true, nullable = false, length = 30)
	public String getSmCollect() {
		return this.smCollect;
	}

	public void setSmCollect(String smCollect) {
		this.smCollect = smCollect;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sm_factory", nullable = false)
	public FactoryInfo getTFactoryInfo() {
		return this.TFactoryInfo;
	}

	public void setTFactoryInfo(FactoryInfo TFactoryInfo) {
		this.TFactoryInfo = TFactoryInfo;
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

	@Column(name = "collectdate", nullable = false, length = 10)
	public String getCollectdate() {
		return this.collectdate;
	}

	public void setCollectdate(String collectdate) {
		this.collectdate = collectdate;
	}

	@Column(name = "grainkind", length = 30)
	public String getGrainkind() {
		return this.grainkind;
	}

	public void setGrainkind(String grainkind) {
		this.grainkind = grainkind;
	}

	@Column(name = "harvestdate", length = 10)
	public String getHarvestdate() {
		return this.harvestdate;
	}

	public void setHarvestdate(String harvestdate) {
		this.harvestdate = harvestdate;
	}

	@Column(name = "drymethod", length = 30)
	public String getDrymethod() {
		return this.drymethod;
	}

	public void setDrymethod(String drymethod) {
		this.drymethod = drymethod;
	}

	@Column(name = "indate", length = 10)
	public String getIndate() {
		return this.indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	@Column(name = "saveperiod")
	public Integer getSaveperiod() {
		return this.saveperiod;
	}

	public void setSaveperiod(Integer saveperiod) {
		this.saveperiod = saveperiod;
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

	// cascade={CascadeType.ALL } mark add
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "insectsOnfactory", cascade={CascadeType.ALL} )
	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL},orphanRemoval=true)
	@JoinColumn(name = "sm_collect")
	public List<TInsectsInfactory> getTInsectsInfactories() {
		return this.TInsectsInfactories;
	}

	public void setTInsectsInfactories(List<TInsectsInfactory> TInsectsInfactories) {
		this.TInsectsInfactories = TInsectsInfactories;
	}
	// cascade={CascadeType.ALL } mark add
//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "insectsOnfactory", cascade={CascadeType.ALL})
	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL},orphanRemoval=true)
	@JoinColumn(name = "sm_collection")
	public List<TFactoryPic> getTFactoryPics() {
		return this.TFactoryPics;
	}

	public void setTFactoryPics(List<TFactoryPic> TFactoryPics) {
		this.TFactoryPics = TFactoryPics;
	}

	@Column(name = "storage_form", length = 30)
	public String getStorageForm() {
		return storageForm;
	}

	public void setStorageForm(String storageForm) {
		this.storageForm = storageForm;
	}

	@Column(name = "quality_change", length = 30)
	public String getQualityChange() {
		return qualityChange;
	}

	public void setQualityChange(String qualityChange) {
		this.qualityChange = qualityChange;
	}

	@Column(name = "grain_stack_temperature", precision = 12, scale = 0)
	public Float getGrainStackTemperature() {
		return grainStackTemperature;
	}

	public void setGrainStackTemperature(Float grainStackTemperature) {
		this.grainStackTemperature = grainStackTemperature;
	}

	@Column(name = "grain_moisture", precision = 12, scale = 0)
	public Float getGrainMoisture() {
		return grainMoisture;
	}

	public void setGrainMoisture(Float grainMoisture) {
		this.grainMoisture = grainMoisture;
	}
}

package com.grain.entity;

import java.util.Date;
import java.util.HashSet;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grain.entity.user.Factoryuser;

/**
 * 加工厂信息表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_factory_info")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler",
		"area","factoryusers", "insectsOnfactories"
		})
public class FactoryInfo implements java.io.Serializable {

	private String smFactory;
	private Area area;
	private String nameFactory;
	private String constructiondate;
	private Float annualpurchase;
	private String majorkindofpurchase;
	private String address;
	private Double longitude;
	private Double latitude;
	private Double altitude;
	private String postcode;
	private String contacts;
	private String phone;
	private String modifer;
	private Date modifydate;
	
	private String mainBintype; //mark add
	
	private Set<Factoryuser> factoryusers = new HashSet<Factoryuser>(0);
	private Set<InsectsOnfactory> insectsOnfactories = new HashSet<InsectsOnfactory>(0);

	@Id
	@Column(name = "sm_factory", unique = true, nullable = false, length = 30)
	public String getSmFactory() {
		return this.smFactory;
	}

	public void setSmFactory(String smFactory) {
		this.smFactory = smFactory;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "direct")
	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	@Column(name = "name_factory", nullable = false, length = 30)
	public String getNameFactory() {
		return this.nameFactory;
	}

	public void setNameFactory(String nameFactory) {
		this.nameFactory = nameFactory;
	}

	@Column(name = "constructiondate", length = 10)
	public String getConstructiondate() {
		return this.constructiondate;
	}

	public void setConstructiondate(String constructiondate) {
		this.constructiondate = constructiondate;
	}

	@Column(name = "Annualpurchase", precision = 12, scale = 0)
	public Float getAnnualpurchase() {
		return this.annualpurchase;
	}

	public void setAnnualpurchase(Float annualpurchase) {
		this.annualpurchase = annualpurchase;
	}

	@Column(name = "majorkindofpurchase", length = 60)
	public String getMajorkindofpurchase() {
		return this.majorkindofpurchase;
	}

	public void setMajorkindofpurchase(String majorkindofpurchase) {
		this.majorkindofpurchase = majorkindofpurchase;
	}

	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "longitude", precision = 22, scale = 0)
	public Double getLongitude() {
		return this.longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	@Column(name = "latitude", precision = 22, scale = 0)
	public Double getLatitude() {
		return this.latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	@Column(name = "altitude", precision = 22, scale = 0)
	public Double getAltitude() {
		return this.altitude;
	}

	public void setAltitude(Double altitude) {
		this.altitude = altitude;
	}

	@Column(name = "postcode", length = 10)
	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	@Column(name = "contacts", length = 30)
	public String getContacts() {
		return this.contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	@Column(name = "phone", nullable = false, length = 60)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TFactoryInfo")
	public Set<Factoryuser> getFactoryusers() {
		return this.factoryusers;
	}

	public void setFactoryusers(Set<Factoryuser> factoryusers) {
		this.factoryusers = factoryusers;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL},orphanRemoval=true)
	@JoinColumn(name = "sm_factory")
	public Set<InsectsOnfactory> getInsectsOnfactories() {
		return this.insectsOnfactories;
	}

	public void setInsectsOnfactories(Set<InsectsOnfactory> insectsOnfactories) {
		this.insectsOnfactories = insectsOnfactories;
	}

	@Column(name = "main_intype", length = 30)
	public String getMainBintype() {
		return mainBintype;
	}

	public void setMainBintype(String mainBintype) {
		this.mainBintype = mainBintype;
	}
}

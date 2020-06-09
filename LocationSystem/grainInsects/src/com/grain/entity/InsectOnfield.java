package com.grain.entity;
// Generated 2016-3-20 22:47:51 by Hibernate Tools 3.5.0.Final

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 野外釆虫表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_insect_onfield")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler",
		"area","tfieldPics"
		})
public class InsectOnfield implements java.io.Serializable {

	private Long id;  // mark
	private Area area;
	private String collector;
	private String mobile;
	private String company;
	private String address;
	private String collectdate;
	private String grainkind;
	private Double longtitude;
	private Double latitude;
	private Double altitude;
	private Float temperature;
	private Float humidity;
	private String kind;
	private String stage;
	private Integer num;
	private String food;
	private String harm;
	private String host;
	private String modifer;
	private String modifydate;
	private List<TFieldPic> TFieldPics = new ArrayList<TFieldPic>(0);
	
	public InsectOnfield(String collector, String mobile, String company, String address,
			String collectdate, String grainkind, Float temperature, Float humidity, String kind, String stage,
			Integer num, String food, String harm, String host) {
		super();
		this.collector = collector;
		this.mobile = mobile;
		this.company = company;
		this.address = address;
		this.collectdate = collectdate;
		this.grainkind = grainkind;
		this.temperature = temperature;
		this.humidity = humidity;
		this.kind = kind;
		this.stage = stage;
		this.num = num;
		this.food = food;
		this.harm = harm;
		this.host = host;
	}

	public InsectOnfield() {
	}

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO) 
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "direct")
	public Area getArea() {
		return this.area;
	}

	public void setArea(Area area) {
		this.area = area;
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

	@Column(name = "company", length = 30)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	
	@Column(name = "address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "grainkind", length = 60)
	public String getGrainkind() {
		return this.grainkind;
	}

	public void setGrainkind(String grainkind) {
		this.grainkind = grainkind;
	}
	
	@Column(name = "collectdate", length = 10)
	public String getCollectdate() {
		return this.collectdate;
	}

	public void setCollectdate(String collectdate) {
		this.collectdate = collectdate;
	}

	@Column(name = "longtitude", precision = 22, scale = 0)
	public Double getLongtitude() {
		return this.longtitude;
	}

	public void setLongtitude(Double longtitude) {
		this.longtitude = longtitude;
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

	@Column(name = "host", length = 30)
	public String getHost() {
		return this.host;
	}

	public void setHost(String host) {
		this.host = host;
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

//	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TInsectOnfield", cascade={CascadeType.ALL})
	@OneToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL},orphanRemoval=true)
	@JoinColumn(name = "pid")
	public List<TFieldPic> getTFieldPics() {
		return this.TFieldPics;
	}

	public void setTFieldPics(List<TFieldPic> TFieldPics) {
		this.TFieldPics = TFieldPics;
	}

}

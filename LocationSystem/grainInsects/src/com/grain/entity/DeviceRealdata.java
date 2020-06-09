package com.grain.entity;

import java.util.HashSet;
import java.util.Set;

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
 * 设备实时数据表,可以有两种方式进行上报：
 * <ol>
 * <li> 移动设备
 * <li> 固定设备
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_device_realdata")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler",
		"tdeviceRegister"
		})
public class DeviceRealdata implements java.io.Serializable {

	private Long id;
	private DeviceRegister TDeviceRegister;
	private Grainbin TGrainbin;
	private Float temperature;
	private Float humidity;
	private Float co2;
	private Float o2;
	private String kind;
	private String stage;
	private Integer num;
	private Integer x;
	private Integer y;
	private Integer z;
	private Double longtitude;
	private Double latitude;
	private Double altitude;
	private String collecttime;
	String modifier;
	String modifytime;
	
	private Set<RealdataPic> realdataPics = new HashSet<RealdataPic>(0);
	String 	source;			//
	String	note;			//
	String 	trapsource;		//

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) 
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "deviceno")
	public DeviceRegister getTDeviceRegister() {
		return this.TDeviceRegister;
	}

	public void setTDeviceRegister(DeviceRegister TDeviceRegister) {
		this.TDeviceRegister = TDeviceRegister;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lcbm")
	@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler",
			"typebin","capacity","structureofbody",
			"structureofroof","designcapacity","designgrainheapheight",
			"longth","width","height",
			"circulatedevice","circulatefan","fanway",
			"elsedevice","contract","phone","note","modifer","modifydate"
			})
	public Grainbin getTGrainbin() {
		return this.TGrainbin;
	}

	public void setTGrainbin(Grainbin TGrainbin) {
		this.TGrainbin = TGrainbin;
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

	@Column(name = "co2", precision = 12, scale = 0)
	public Float getCo2() {
		return this.co2;
	}

	public void setCo2(Float co2) {
		this.co2 = co2;
	}

	@Column(name = "o2", precision = 12, scale = 0)
	public Float getO2() {
		return this.o2;
	}

	public void setO2(Float o2) {
		this.o2 = o2;
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

	@Column(name = "X")
	public Integer getX() {
		return this.x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	@Column(name = "Y")
	public Integer getY() {
		return this.y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	@Column(name = "Z")
	public Integer getZ() {
		return this.z;
	}

	public void setZ(Integer z) {
		this.z = z;
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

	@Column(name = "collecttime", length = 60)
	public String getCollecttime() {
		return this.collecttime;
	}

	public void setCollecttime(String collecttime) {
		this.collecttime = collecttime;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "deviceRealdata",cascade={CascadeType.ALL})
	public Set<RealdataPic> getRealdataPics() {
		return this.realdataPics;
	}

	public void setRealdataPics(Set<RealdataPic> realdataPics) {
		this.realdataPics = realdataPics;
	}
	
	@Column(name="source")
	public String getSource() {
		return source;
	}

	public void setSource(String sourece) {
		this.source = sourece;
	}
	
	@Column(name="modifier")
	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	@Column(name="modifytime")
	public String getModifytime() {
		return modifytime;
	}

	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getTrapsource() {
		return trapsource;
	}

	public void setTrapsource(String trapsource) {
		this.trapsource = trapsource;
	}
	
	
/*	
	*//**
	 * 持久化前处理
	 *//*
	@PrePersist
	public void prePersist() {
		SetModify();
	}
	
	*//**
	 * 更新前处理
	 *//*
	@PreUpdate
	public void preUpdate() {
		SetModify();
	}
	
	private void SetModify(){
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			Principal principal = (Principal) request.getSession().getAttribute(CommonAttributes.Principal);
			if (principal!=null){
				this.modifier = principal.getUsername();
				 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
				this.mofifytime = dateFormat.format(new Date());
			}
		}
	}*/
}

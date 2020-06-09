package com.grain.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.grain.CommonAttributes;
import com.grain.Principal;

/**
 * 氮气调总表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_depot_ca")
public class DepotCa implements java.io.Serializable {

	private Long id;
	private Graindepot graindepot;
	private Integer annual;
	private String factory;
	private Integer cascale;
	private String finishdate;
	private Character ifnormalrun;
	private String problem;//0601
	private String material;
	private Character iftest;
	private String intelligentscale;
	private Character intelligentifnoraluse;
	private Float nitrogendevice;
	private Float controlsystem;
	private Float aircompressor;
	private Float nitrogenroom;
	private Float respirator;
	private Float pipleandfee;
	private Float airpump;
	private Float cableandfee;
	private Float hypoxiaalarm;
	private Float auxiliaryandfee;
	private Float oxygendetectdevice;
	private Float electricityfee;
	private Float chargetime;
	private String chargepro;
	private Float maxconcentration;
	private Float keeplong;
	private Float avggraintemperature;
	private Float highgraintemperature;
	private Float lowestgraintemperture;
	private Float kindbefca;
	private Float densitybefca;
	private Float kindafterca;
	private Float densityafterca;
	private String varconcentration;
	private Integer noninsects;
	private Float fattyacidvaluebef;
	private Float fattyacidvalueafter;
	private String elsecharacter;
	private String suggestion;
	private String reporter;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date reportdate;
	private String modifier;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date modifytime;
	private String phone; //20160530 新增属性

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //新增 自动生成主见
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lkbm", nullable = false)
	public Graindepot getGraindepot() {
		return this.graindepot;
	}

	public void setGraindepot(Graindepot graindepot) {
		this.graindepot = graindepot;
	}

	@Column(name = "annual", nullable = false)
	public Integer getAnnual() {
		return this.annual;
	}

	public void setAnnual(Integer annual) {
		this.annual = annual;
	}

	@Column(name = "factory", length = 100)
	public String getFactory() {
		return this.factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	@Column(name = "CAscale")
	public Integer getCascale() {
		return this.cascale;
	}

	public void setCascale(Integer cascale) {
		this.cascale = cascale;
	}

	@Column(name = "finishdate", length = 10)
	public String getFinishdate() {
		return this.finishdate;
	}

	public void setFinishdate(String finishdate) {
		this.finishdate = finishdate;
	}

	@Column(name = "ifnormalrun", length = 1)
	public Character getIfnormalrun() {
		return this.ifnormalrun;
	}

	public void setIfnormalrun(Character ifnormalrun) {
		this.ifnormalrun = ifnormalrun;
	}

	@Column(name = "problem")
	public String getProblem() {
		return this.problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	@Column(name = "material", length = 60)
	public String getMaterial() {
		return this.material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Column(name = "iftest", length = 1)
	public Character getIftest() {
		return this.iftest;
	}

	public void setIftest(Character iftest) {
		this.iftest = iftest;
	}

	@Column(name = "intelligentscale", length = 60)
	public String getIntelligentscale() {
		return this.intelligentscale;
	}

	public void setIntelligentscale(String intelligentscale) {
		this.intelligentscale = intelligentscale;
	}

	@Column(name = "intelligentifnoraluse", length = 1)
	public Character getIntelligentifnoraluse() {
		return this.intelligentifnoraluse;
	}

	public void setIntelligentifnoraluse(Character intelligentifnoraluse) {
		this.intelligentifnoraluse = intelligentifnoraluse;
	}

	@Column(name = "Nitrogendevice", precision = 12, scale = 0)
	public Float getNitrogendevice() {
		return this.nitrogendevice;
	}

	public void setNitrogendevice(Float nitrogendevice) {
		this.nitrogendevice = nitrogendevice;
	}

	@Column(name = "controlsystem", precision = 12, scale = 0)
	public Float getControlsystem() {
		return this.controlsystem;
	}

	public void setControlsystem(Float controlsystem) {
		this.controlsystem = controlsystem;
	}

	@Column(name = "Aircompressor", precision = 12, scale = 0)
	public Float getAircompressor() {
		return this.aircompressor;
	}

	public void setAircompressor(Float aircompressor) {
		this.aircompressor = aircompressor;
	}

	@Column(name = "Nitrogenroom", precision = 12, scale = 0)
	public Float getNitrogenroom() {
		return this.nitrogenroom;
	}

	public void setNitrogenroom(Float nitrogenroom) {
		this.nitrogenroom = nitrogenroom;
	}

	@Column(name = "Respirator", precision = 12, scale = 0)
	public Float getRespirator() {
		return this.respirator;
	}

	public void setRespirator(Float respirator) {
		this.respirator = respirator;
	}

	@Column(name = "pipleandfee", precision = 12, scale = 0)
	public Float getPipleandfee() {
		return this.pipleandfee;
	}

	public void setPipleandfee(Float pipleandfee) {
		this.pipleandfee = pipleandfee;
	}

	@Column(name = "airpump", precision = 12, scale = 0)
	public Float getAirpump() {
		return this.airpump;
	}

	public void setAirpump(Float airpump) {
		this.airpump = airpump;
	}

	@Column(name = "cableandfee", precision = 12, scale = 0)
	public Float getCableandfee() {
		return this.cableandfee;
	}

	public void setCableandfee(Float cableandfee) {
		this.cableandfee = cableandfee;
	}

	@Column(name = "Hypoxiaalarm", precision = 12, scale = 0)
	public Float getHypoxiaalarm() {
		return this.hypoxiaalarm;
	}

	public void setHypoxiaalarm(Float hypoxiaalarm) {
		this.hypoxiaalarm = hypoxiaalarm;
	}

	@Column(name = "auxiliaryandfee", precision = 12, scale = 0)
	public Float getAuxiliaryandfee() {
		return this.auxiliaryandfee;
	}

	public void setAuxiliaryandfee(Float auxiliaryandfee) {
		this.auxiliaryandfee = auxiliaryandfee;
	}

	@Column(name = "oxygendetectdevice", precision = 12, scale = 0)
	public Float getOxygendetectdevice() {
		return this.oxygendetectdevice;
	}

	public void setOxygendetectdevice(Float oxygendetectdevice) {
		this.oxygendetectdevice = oxygendetectdevice;
	}

	@Column(name = "electricityfee", precision = 12, scale = 0)
	public Float getElectricityfee() {
		return this.electricityfee;
	}

	public void setElectricityfee(Float electricityfee) {
		this.electricityfee = electricityfee;
	}

	@Column(name = "chargetime", precision = 12, scale = 0)
	public Float getChargetime() {
		return this.chargetime;
	}

	public void setChargetime(Float chargetime) {
		this.chargetime = chargetime;
	}

	@Column(name = "chargepro", length = 200)
	public String getChargepro() {
		return this.chargepro;
	}

	public void setChargepro(String chargepro) {
		this.chargepro = chargepro;
	}

	@Column(name = "maxconcentration", precision = 12, scale = 0)
	public Float getMaxconcentration() {
		return this.maxconcentration;
	}

	public void setMaxconcentration(Float maxconcentration) {
		this.maxconcentration = maxconcentration;
	}

	@Column(name = "keeplong", precision = 12, scale = 0)
	public Float getKeeplong() {
		return this.keeplong;
	}

	public void setKeeplong(Float keeplong) {
		this.keeplong = keeplong;
	}

	@Column(name = "avggraintemperature", precision = 12, scale = 0)
	public Float getAvggraintemperature() {
		return this.avggraintemperature;
	}

	public void setAvggraintemperature(Float avggraintemperature) {
		this.avggraintemperature = avggraintemperature;
	}

	@Column(name = "highgraintemperature", precision = 12, scale = 0)
	public Float getHighgraintemperature() {
		return this.highgraintemperature;
	}

	public void setHighgraintemperature(Float highgraintemperature) {
		this.highgraintemperature = highgraintemperature;
	}

	@Column(name = "lowestgraintemperture", precision = 12, scale = 0)
	public Float getLowestgraintemperture() {
		return this.lowestgraintemperture;
	}

	public void setLowestgraintemperture(Float lowestgraintemperture) {
		this.lowestgraintemperture = lowestgraintemperture;
	}

	@Column(name = "kindbefca", precision = 12, scale = 0)
	public Float getKindbefca() {
		return this.kindbefca;
	}

	public void setKindbefca(Float kindbefca) {
		this.kindbefca = kindbefca;
	}

	@Column(name = "densitybefca", precision = 12, scale = 0)
	public Float getDensitybefca() {
		return this.densitybefca;
	}

	public void setDensitybefca(Float densitybefca) {
		this.densitybefca = densitybefca;
	}

	@Column(name = "kindafterca", precision = 12, scale = 0)
	public Float getKindafterca() {
		return this.kindafterca;
	}

	public void setKindafterca(Float kindafterca) {
		this.kindafterca = kindafterca;
	}

	@Column(name = "densityafterca", precision = 12, scale = 0)
	public Float getDensityafterca() {
		return this.densityafterca;
	}

	public void setDensityafterca(Float densityafterca) {
		this.densityafterca = densityafterca;
	}

	@Column(name = "varconcentration", length = 60)
	public String getVarconcentration() {
		return this.varconcentration;
	}

	public void setVarconcentration(String varconcentration) {
		this.varconcentration = varconcentration;
	}

	@Column(name = "noninsects")
	public Integer getNoninsects() {
		return this.noninsects;
	}

	public void setNoninsects(Integer noninsects) {
		this.noninsects = noninsects;
	}

	@Column(name = "fattyacidvaluebef", precision = 12, scale = 0)
	public Float getFattyacidvaluebef() {
		return this.fattyacidvaluebef;
	}

	public void setFattyacidvaluebef(Float fattyacidvaluebef) {
		this.fattyacidvaluebef = fattyacidvaluebef;
	}

	@Column(name = "fattyacidvalueafter", precision = 12, scale = 0)
	public Float getFattyacidvalueafter() {
		return this.fattyacidvalueafter;
	}

	public void setFattyacidvalueafter(Float fattyacidvalueafter) {
		this.fattyacidvalueafter = fattyacidvalueafter;
	}

	@Column(name = "elsecharacter", length = 100)
	public String getElsecharacter() {
		return this.elsecharacter;
	}

	public void setElsecharacter(String elsecharacter) {
		this.elsecharacter = elsecharacter;
	}

	@Column(name = "suggestion")
	public String getSuggestion() {
		return this.suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	@Column(name = "reporter", length = 60)
	public String getReporter() {
		return this.reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "reportdate", length = 10)
	public Date getReportdate() {
		return this.reportdate;
	}

	public void setReportdate(Date reportdate) {
		this.reportdate = reportdate;
	}

	@Column(name = "modifier", length = 60)
	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modifytime", length = 19)
	public Date getModifytime() {
		return this.modifytime;
	}

	public void setModifytime(Date modifytime) {
		this.modifytime = modifytime;
	}

	@Column(name = "phone") //新增phone get和set
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	/**
	 * 持久化前处理
	 */
	@PrePersist
	public void prePersist() {
		SetModify();
	}
	
	/**
	 * 更新前处理
	 */
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
				this.modifytime = new Date();
			}
		}
	}
}

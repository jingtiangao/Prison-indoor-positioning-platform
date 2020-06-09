package com.grain.entity;
// Generated 2016-3-20 22:47:51 by Hibernate Tools 3.5.0.Final

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
 * 防护剂分仓调查表 generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_bin_protected")
public class TBinProtected implements java.io.Serializable {

	private Long id;
	private Grainbin TGrainbin;
	private Integer annual;
	private Float device;
	private String brand;
	private String factory;
	private Float dosage;
	private Float drugfee;
	private Float protective;
	private Float subsidy;
	private Integer keeplong;
	private Float laborfee;
	private Float otherfee;
	private String applymethod;
	private Float applyonsurface;
	private Float applyeverylayer;
	private Float applyonwhole;
	private Float dealonsurface;
	private String otherdeal;
	private Float avggraintemperature;
	private Float highestgraintemperature;
	private Float lowestgraintemperature;
	private Float densitybef;
	private String kindbef;
	private Float densityafter;
	private Integer noninsects;
	private Float fattyvaluebef;
	private Float fattyvalueafter;
	private Float residues;
	private String elsecharacter;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startdate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date enddate;
	private String reporter;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date reportdate;
	private String modifier;
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date modifydate;
	private String phone;// 后添加的
	
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lcbm")
	public Grainbin getTGrainbin() {
		return this.TGrainbin;
	}

	public void setTGrainbin(Grainbin TGrainbin) {
		this.TGrainbin = TGrainbin;
	}

	@Column(name = "Annual", nullable = false)
	public Integer getAnnual() {
		return this.annual;
	}

	public void setAnnual(Integer annual) {
		this.annual = annual;
	}

	@Column(name = "device", precision = 12, scale = 0)
	public Float getDevice() {
		return this.device;
	}

	public void setDevice(Float device) {
		this.device = device;
	}

	@Column(name = "brand", length = 60)
	public String getBrand() {
		return this.brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Column(name = "factory", length = 60)
	public String getFactory() {
		return this.factory;
	}

	public void setFactory(String factory) {
		this.factory = factory;
	}

	@Column(name = "Dosage", precision = 12, scale = 0)
	public Float getDosage() {
		return this.dosage;
	}

	public void setDosage(Float dosage) {
		this.dosage = dosage;
	}

	@Column(name = "drugfee", precision = 12, scale = 0)
	public Float getDrugfee() {
		return this.drugfee;
	}

	public void setDrugfee(Float drugfee) {
		this.drugfee = drugfee;
	}

	@Column(name = "protective", precision = 12, scale = 0)
	public Float getProtective() {
		return this.protective;
	}

	public void setProtective(Float protective) {
		this.protective = protective;
	}

	@Column(name = "subsidy", precision = 12, scale = 0)
	public Float getSubsidy() {
		return this.subsidy;
	}

	public void setSubsidy(Float subsidy) {
		this.subsidy = subsidy;
	}

	@Column(name = "keeplong")
	public Integer getKeeplong() {
		return this.keeplong;
	}

	public void setKeeplong(Integer keeplong) {
		this.keeplong = keeplong;
	}

	@Column(name = "laborfee", precision = 12, scale = 0)
	public Float getLaborfee() {
		return this.laborfee;
	}

	public void setLaborfee(Float laborfee) {
		this.laborfee = laborfee;
	}

	@Column(name = "otherfee", precision = 12, scale = 0)
	public Float getOtherfee() {
		return this.otherfee;
	}

	public void setOtherfee(Float otherfee) {
		this.otherfee = otherfee;
	}

	@Column(name = "applymethod", length = 200)
	public String getApplymethod() {
		return this.applymethod;
	}

	public void setApplymethod(String applymethod) {
		this.applymethod = applymethod;
	}

	@Column(name = "applyonsurface", precision = 12, scale = 0)
	public Float getApplyonsurface() {
		return this.applyonsurface;
	}

	public void setApplyonsurface(Float applyonsurface) {
		this.applyonsurface = applyonsurface;
	}

	@Column(name = "applyeverylayer", precision = 12, scale = 0)
	public Float getApplyeverylayer() {
		return this.applyeverylayer;
	}

	public void setApplyeverylayer(Float applyeverylayer) {
		this.applyeverylayer = applyeverylayer;
	}

	@Column(name = "applyonwhole", precision = 12, scale = 0)
	public Float getApplyonwhole() {
		return this.applyonwhole;
	}

	public void setApplyonwhole(Float applyonwhole) {
		this.applyonwhole = applyonwhole;
	}

	@Column(name = "dealonsurface", precision = 12, scale = 0)
	public Float getDealonsurface() {
		return this.dealonsurface;
	}

	public void setDealonsurface(Float dealonsurface) {
		this.dealonsurface = dealonsurface;
	}

	@Column(name = "otherdeal", length = 200)
	public String getOtherdeal() {
		return this.otherdeal;
	}

	public void setOtherdeal(String otherdeal) {
		this.otherdeal = otherdeal;
	}

	@Column(name = "avggraintemperature", precision = 12, scale = 0)
	public Float getAvggraintemperature() {
		return this.avggraintemperature;
	}

	public void setAvggraintemperature(Float avggraintemperature) {
		this.avggraintemperature = avggraintemperature;
	}

	@Column(name = "highestgraintemperature", precision = 12, scale = 0)
	public Float getHighestgraintemperature() {
		return this.highestgraintemperature;
	}

	public void setHighestgraintemperature(Float highestgraintemperature) {
		this.highestgraintemperature = highestgraintemperature;
	}

	@Column(name = "lowestgraintemperature", precision = 12, scale = 0)
	public Float getLowestgraintemperature() {
		return this.lowestgraintemperature;
	}

	public void setLowestgraintemperature(Float lowestgraintemperature) {
		this.lowestgraintemperature = lowestgraintemperature;
	}

	@Column(name = "densitybef", precision = 12, scale = 0)
	public Float getDensitybef() {
		return this.densitybef;
	}

	public void setDensitybef(Float densitybef) {
		this.densitybef = densitybef;
	}

	@Column(name = "kindbef", length = 100)
	public String getKindbef() {
		return this.kindbef;
	}

	public void setKindbef(String kindbef) {
		this.kindbef = kindbef;
	}

	@Column(name = "densityafter", precision = 12, scale = 0)
	public Float getDensityafter() {
		return this.densityafter;
	}

	public void setDensityafter(Float densityafter) {
		this.densityafter = densityafter;
	}

	@Column(name = "noninsects")
	public Integer getNoninsects() {
		return this.noninsects;
	}

	public void setNoninsects(Integer noninsects) {
		this.noninsects = noninsects;
	}

	@Column(name = "fattyvaluebef", precision = 12, scale = 0)
	public Float getFattyvaluebef() {
		return this.fattyvaluebef;
	}

	public void setFattyvaluebef(Float fattyvaluebef) {
		this.fattyvaluebef = fattyvaluebef;
	}

	@Column(name = "fattyvalueafter", precision = 12, scale = 0)
	public Float getFattyvalueafter() {
		return this.fattyvalueafter;
	}

	public void setFattyvalueafter(Float fattyvalueafter) {
		this.fattyvalueafter = fattyvalueafter;
	}

	@Column(name = "residues", precision = 12, scale = 0)
	public Float getResidues() {
		return this.residues;
	}

	public void setResidues(Float residues) {
		this.residues = residues;
	}

	@Column(name = "elsecharacter", length = 200)
	public String getElsecharacter() {
		return this.elsecharacter;
	}

	public void setElsecharacter(String elsecharacter) {
		this.elsecharacter = elsecharacter;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "startdate", length = 10)
	public Date getStartdate() {
		return this.startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "enddate", length = 10)
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@Column(name = "reporter", length = 20)
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

	@Column(name = "modifier", length = 20)
	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modifydate", length = 19)
	public Date getModifydate() {
		return this.modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
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
				this.modifydate = new Date();
			}
		}
	}

}

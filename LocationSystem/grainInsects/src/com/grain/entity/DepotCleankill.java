package com.grain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
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
 * 空仓杀虫总表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_depot_cleankill")
public class DepotCleankill implements java.io.Serializable {

	private Long id;
	private Graindepot graindepot;
	private Integer annual;
	private String drug;
	private Integer totaluse;
	private Float proportion;
	private String deviceandway;
	private Float device;
	private String brand;
	private Float dosage;
	private Float drugfee;
	private Float protective;
	private Float subsidy;
	private Integer keeplong;
	private Float laborfee;
	private Float otherfee;
	private String applymethod;
	private Float applyonsurface;
	private String otherdeal;
	private Float avggraintemperature;
	private Float densitybef;
	private String kindbef;
	private String kindaf;
	private Float densityafter;
	private Integer noninsects;
	private String suggestion;
	private String reporter;
	private String phone;
	private Date reportdate;
	private String modifier;
	private Date modifydate;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@Column(name = "Annual", nullable = false)
	public Integer getAnnual() {
		return this.annual;
	}

	public void setAnnual(Integer annual) {
		this.annual = annual;
	}

	@Column(name = "drug", length = 100)
	public String getDrug() {
		return this.drug;
	}

	public void setDrug(String drug) {
		this.drug = drug;
	}

	@Column(name = "totaluse")
	public Integer getTotaluse() {
		return this.totaluse;
	}

	public void setTotaluse(Integer totaluse) {
		this.totaluse = totaluse;
	}

	@Column(name = "proportion", precision = 12, scale = 0)
	public Float getProportion() {
		return this.proportion;
	}

	public void setProportion(Float proportion) {
		this.proportion = proportion;
	}

	@Column(name = "deviceandway", length = 100)
	public String getDeviceandway() {
		return this.deviceandway;
	}

	public void setDeviceandway(String deviceandway) {
		this.deviceandway = deviceandway;
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
	
	@Column(name = "kindaf", length = 100)
	public String getKindaf() {
		return kindaf;
	}

	public void setKindaf(String kindaf) {
		this.kindaf = kindaf;
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

	@Column(name = "suggestion")
	@Lob
	public String getSuggestion() {
		return this.suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	@Column(name = "reporter", length = 20)
	public String getReporter() {
		return this.reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}
	
	@Column(name = "phone", length = 30)
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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
				this.modifydate = new Date();
			}
		}
	}
	
}

package com.grain.entity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grain.CommonAttributes;
import com.grain.Principal;

/**
 * 害虫调查总表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_depot_invest")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler",
		"depotGraintemperatures","depotPreprotects","tdepotUseDrugs",
		"depotProtects","tdepotHumidities","tdepotsInsectses",
		"tdepoEnvitemperatures"
		})
public class TDepotInvest implements java.io.Serializable {

	private Long sm;
	private Graindepot graindepot;
	private Integer annual;
	private String maingrain;
	private String suggestion;
	String phone;
	Integer constructionScale;	//建库规模
	Integer grainScale;			//储粮规模
	private String reporter;
	private String reportdate;
	private String modifer;
	private String modifydate;
	private List<TDepotGraintemperature> depotGraintemperatures = new ArrayList<TDepotGraintemperature>(0);
	private List<TDepotPreprotect> depotPreprotects= new ArrayList<TDepotPreprotect>(0);
	private List<TDepotUseDrug> TDepotUseDrugs = new ArrayList<TDepotUseDrug>(0);
	private List<TDepotProtect> depotProtects = new ArrayList<TDepotProtect>(0);;
	private List<DepotHumidity> TDepotHumidities = new ArrayList<DepotHumidity>(0);
	private List<TDepotsInsects> TDepotsInsectses = new ArrayList<TDepotsInsects>(0);
	private List<TDepoEnvitemperature> TDepoEnvitemperatures = new ArrayList<TDepoEnvitemperature>(0);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getSm() {
		return this.sm;
	}

	public void setSm(Long sm) {
		this.sm = sm;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lkbm", nullable = false)
	@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler",
			"area","longtitude","latitude",
			"altitude","hasreal","totalbin",
			"postcode","contact","phone",
			"modifier","modifydate"
			})
	public Graindepot getGraindepot() {
		return this.graindepot;
	}

	public void setGraindepot(Graindepot graindepot) {
		this.graindepot = graindepot;
	}

	@Column(name = "Annual")
	public Integer getAnnual() {
		return this.annual;
	}

	public void setAnnual(Integer annual) {
		this.annual = annual;
	}

	@Column(name = "maingrain", nullable = false, length = 200)
	public String getMaingrain() {
		return this.maingrain;
	}

	public void setMaingrain(String maingrain) {
		this.maingrain = maingrain;
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

	@Column(name = "reportdate", length = 10)
	public String getReportdate() {
		return this.reportdate;
	}

	public void setReportdate(String reportdate) {
		this.reportdate = reportdate;
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
	
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "constructionScale")
	public Integer getConstructionScale() {
		return constructionScale;
	}

	public void setConstructionScale(Integer constructionScale) {
		this.constructionScale = constructionScale;
	}

	@Column(name = "grainScale")
	public Integer getGrainScale() {
		return grainScale;
	}

	public void setGrainScale(Integer grainScale) {
		this.grainScale = grainScale;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TDepotInvest",cascade=CascadeType.ALL)
	public List<TDepotGraintemperature> getDepotGraintemperatures() {
		return this.depotGraintemperatures;
	}

	public void setDepotGraintemperatures(List<TDepotGraintemperature> depotGraintemperatures) {
		this.depotGraintemperatures = depotGraintemperatures;
	}


	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name = "psm")  
	public List<TDepotUseDrug> getTDepotUseDrugs() {
		return this.TDepotUseDrugs;
	}

	public void setTDepotUseDrugs(List<TDepotUseDrug> TDepotUseDrugs) {
		this.TDepotUseDrugs = TDepotUseDrugs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TDepotInvest",cascade=CascadeType.ALL)
	public List<DepotHumidity> getTDepotHumidities() {
		return this.TDepotHumidities;
	}

	public void setTDepotHumidities(List<DepotHumidity> TDepotHumidities) {
		this.TDepotHumidities = TDepotHumidities;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name = "psm")  
	public List<TDepotsInsects> getTDepotsInsectses() {
		return this.TDepotsInsectses;
	}

	public void setTDepotsInsectses(List<TDepotsInsects> TDepotsInsectses) {
		this.TDepotsInsectses = TDepotsInsectses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TDepotInvest",cascade=CascadeType.ALL)
	public List<TDepoEnvitemperature> getTDepoEnvitemperatures() {
		return this.TDepoEnvitemperatures;
	}

	public void setTDepoEnvitemperatures(List<TDepoEnvitemperature> TDepoEnvitemperatures) {
		this.TDepoEnvitemperatures = TDepoEnvitemperatures;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TDepotInvest",cascade=CascadeType.ALL)
	public List<TDepotPreprotect> getDepotPreprotects() {
		return depotPreprotects;
	}

	public void setDepotPreprotects(List<TDepotPreprotect> depotPreprotects) {
		this.depotPreprotects = depotPreprotects;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TDepotInvest",cascade=CascadeType.ALL)
	public List<TDepotProtect> getDepotProtects() {
		return depotProtects;
	}

	public void setDepotProtects(List<TDepotProtect> depotProtects) {
		this.depotProtects = depotProtects;
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
				this.modifer = principal.getUsername();
				 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
				this.modifydate = dateFormat.format(new Date());
			}
		}
	}

}

package com.grain.entity;
// Generated 2016-3-20 22:47:51 by Hibernate Tools 3.5.0.Final

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 磷化氢抗药分仓
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_bin_antiphosphine")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler",
		"TBinAntidrugkindses"
		})
public class TBinAntiphosphine implements java.io.Serializable {

	private Long id;
	private Grainbin TGrainbin;
	private Integer annual;
	private String device;
	private Integer cascale;
	private Float proportion;
	private Integer totaldays;
	private String circulationway;
	private Float highestppm;
	private Integer targetdays;
	private Float avgtemperature;
	private Float highesttemperature;
	private Float lowesttemperature;
	private Float kindbefca;
	private Float densitybefca;
	private Float kindafterca;
	private Float densityafterca;
	private String varconcentration;
	private Integer noninsects;
	private Character ifcage;
	private String cagekind;
	private Integer cagenumbef;
	private Character hasliveinsectsafter;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date startdate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date enddate;
	private String reporter;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date reportdate;
	private String modifier;
	private Date modifytime;
	private List<TBinAntidrugkinds> TBinAntidrugkindses = new ArrayList<TBinAntidrugkinds>(0);


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
	public int getAnnual() {
		return this.annual;
	}

	public void setAnnual(int annual) {
		this.annual = annual;
	}

	@Column(name = "device", length = 100)
	public String getDevice() {
		return this.device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	@Column(name = "CAscale")
	public Integer getCascale() {
		return this.cascale;
	}

	public void setCascale(Integer cascale) {
		this.cascale = cascale;
	}

	@Column(name = "proportion", precision = 12, scale = 0)
	public Float getProportion() {
		return this.proportion;
	}

	public void setProportion(Float proportion) {
		this.proportion = proportion;
	}

	@Column(name = "totaldays")
	public Integer getTotaldays() {
		return this.totaldays;
	}

	public void setTotaldays(Integer totaldays) {
		this.totaldays = totaldays;
	}

	@Column(name = "circulationway", length = 20)
	public String getCirculationway() {
		return this.circulationway;
	}

	public void setCirculationway(String circulationway) {
		this.circulationway = circulationway;
	}

	@Column(name = "highestppm", precision = 12, scale = 0)
	public Float getHighestppm() {
		return this.highestppm;
	}

	public void setHighestppm(Float highestppm) {
		this.highestppm = highestppm;
	}

	@Column(name = "targetdays")
	public Integer getTargetdays() {
		return this.targetdays;
	}

	public void setTargetdays(Integer targetdays) {
		this.targetdays = targetdays;
	}

	@Column(name = "avgtemperature", precision = 12, scale = 0)
	public Float getAvgtemperature() {
		return this.avgtemperature;
	}

	public void setAvgtemperature(Float avgtemperature) {
		this.avgtemperature = avgtemperature;
	}

	@Column(name = "highesttemperature", precision = 12, scale = 0)
	public Float getHighesttemperature() {
		return this.highesttemperature;
	}

	public void setHighesttemperature(Float highesttemperature) {
		this.highesttemperature = highesttemperature;
	}

	@Column(name = "lowesttemperature", precision = 12, scale = 0)
	public Float getLowesttemperature() {
		return this.lowesttemperature;
	}

	public void setLowesttemperature(Float lowesttemperature) {
		this.lowesttemperature = lowesttemperature;
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

	@Column(name = "ifcage", length = 1)
	public Character getIfcage() {
		return this.ifcage;
	}

	public void setIfcage(Character ifcage) {
		this.ifcage = ifcage;
	}

	@Column(name = "cagekind", length = 60)
	public String getCagekind() {
		return this.cagekind;
	}

	public void setCagekind(String cagekind) {
		this.cagekind = cagekind;
	}

	@Column(name = "cagenumbef")
	public Integer getCagenumbef() {
		return this.cagenumbef;
	}

	public void setCagenumbef(Integer cagenumbef) {
		this.cagenumbef = cagenumbef;
	}

	@Column(name = "hasliveinsectsafter", length = 1)
	public Character getHasliveinsectsafter() {
		return this.hasliveinsectsafter;
	}

	public void setHasliveinsectsafter(Character hasliveinsectsafter) {
		this.hasliveinsectsafter = hasliveinsectsafter;
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

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	@JoinColumn(name = "pID")
	public List<TBinAntidrugkinds> getTBinAntidrugkindses() {
		return this.TBinAntidrugkindses;
	}

	public void setTBinAntidrugkindses(List<TBinAntidrugkinds> TBinAntidrugkindses) {
		this.TBinAntidrugkindses = TBinAntidrugkindses;
	}

}

package com.grain.entity;
// Generated 2016-3-20 22:47:51 by Hibernate Tools 3.5.0.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * 分仓仓储管理预防措施
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_bin_preprotect")
public class TBinPreprotect implements java.io.Serializable {

	private Long sm;
	private TBinInvest TBinInvest;
	private Character checkbeforeinput;
	private Character clearbinkill;
	private Character checkafteroutput;
	private Character inputcheck;
	private Float breakagerate;
	private Float watercontent;
	private Float noperfect;
	private Float impurity;
	private String acceptelse;
	private Character flynet;
	private Character flyline;
	private String detectfrequency;
	private Character direct;
	private Character sample;
	private Character trap;
	private String elsedetect;


	@GenericGenerator(name = "generator", strategy = "foreign", parameters = @Parameter(name = "property", value = "TBinInvest"))
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "sm", unique = true, nullable = false)
	public Long getSm() {
		return this.sm;
	}

	public void setSm(Long sm) {
		this.sm = sm;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sm", insertable = false, updatable = false)
	public TBinInvest getTBinInvest() {
		return this.TBinInvest;
	}

	public void setTBinInvest(TBinInvest TBinInvest) {
		this.TBinInvest = TBinInvest;
	}

	@Column(name = "checkbeforeinput", length = 1)
	public Character getCheckbeforeinput() {
		return this.checkbeforeinput;
	}

	public void setCheckbeforeinput(Character checkbeforeinput) {
		this.checkbeforeinput = checkbeforeinput;
	}

	@Column(name = "clearbinkill", length = 1)
	public Character getClearbinkill() {
		return this.clearbinkill;
	}

	public void setClearbinkill(Character clearbinkill) {
		this.clearbinkill = clearbinkill;
	}

	@Column(name = "checkafteroutput", length = 1)
	public Character getCheckafteroutput() {
		return this.checkafteroutput;
	}

	public void setCheckafteroutput(Character checkafteroutput) {
		this.checkafteroutput = checkafteroutput;
	}

	@Column(name = "inputcheck", length = 1)
	public Character getInputcheck() {
		return this.inputcheck;
	}

	public void setInputcheck(Character inputcheck) {
		this.inputcheck = inputcheck;
	}

	@Column(name = "breakagerate", precision = 12, scale = 0)
	public Float getBreakagerate() {
		return this.breakagerate;
	}

	public void setBreakagerate(Float breakagerate) {
		this.breakagerate = breakagerate;
	}

	@Column(name = "watercontent", precision = 12, scale = 0)
	public Float getWatercontent() {
		return this.watercontent;
	}

	public void setWatercontent(Float watercontent) {
		this.watercontent = watercontent;
	}

	@Column(name = "Noperfect", precision = 12, scale = 0)
	public Float getNoperfect() {
		return this.noperfect;
	}

	public void setNoperfect(Float noperfect) {
		this.noperfect = noperfect;
	}

	@Column(name = "impurity", precision = 12, scale = 0)
	public Float getImpurity() {
		return this.impurity;
	}

	public void setImpurity(Float impurity) {
		this.impurity = impurity;
	}

	@Column(name = "acceptelse", length = 60)
	public String getAcceptelse() {
		return this.acceptelse;
	}

	public void setAcceptelse(String acceptelse) {
		this.acceptelse = acceptelse;
	}

	@Column(name = "flynet", length = 1)
	public Character getFlynet() {
		return this.flynet;
	}

	public void setFlynet(Character flynet) {
		this.flynet = flynet;
	}

	@Column(name = "flyline", length = 1)
	public Character getFlyline() {
		return this.flyline;
	}

	public void setFlyline(Character flyline) {
		this.flyline = flyline;
	}

	@Column(name = "detectfrequency", length = 60)
	public String getDetectfrequency() {
		return this.detectfrequency;
	}

	public void setDetectfrequency(String detectfrequency) {
		this.detectfrequency = detectfrequency;
	}

	@Column(name = "direct", length = 1)
	public Character getDirect() {
		return this.direct;
	}

	public void setDirect(Character direct) {
		this.direct = direct;
	}

	@Column(name = "sample", length = 1)
	public Character getSample() {
		return this.sample;
	}

	public void setSample(Character sample) {
		this.sample = sample;
	}

	@Column(name = "trap", length = 1)
	public Character getTrap() {
		return this.trap;
	}

	public void setTrap(Character trap) {
		this.trap = trap;
	}

	@Column(name = "elsedetect", length = 60)
	public String getElsedetect() {
		return this.elsedetect;
	}

	public void setElsedetect(String elsedetect) {
		this.elsedetect = elsedetect;
	}

}

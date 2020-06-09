package com.grain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 仓储管理预防总表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_depot_preprotect")
public class TDepotPreprotect implements java.io.Serializable {

	private Long sm;
	private TDepotInvest TDepotInvest;
	private Boolean checkbeforeinput;
	private Boolean clearbinkill;
	private Boolean checkafteroutput;
	private Boolean inputcheck;
	private Float breakagerate;
	private Float watercontent;
	private Float noperfect;
	private Float impurity;
	private String acceptelse;
	private Boolean flynet;
	private Boolean flyline;
	private String detectfrequency;
	private Boolean direct;
	private Boolean sample;
	private Boolean trap;
	private String elsedetect;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getSm() {
		return this.sm;
	}

	public void setSm(Long sm) {
		this.sm = sm;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "psm")
	public TDepotInvest getTDepotInvest() {
		return this.TDepotInvest;
	}

	public void setTDepotInvest(TDepotInvest TDepotInvest) {
		this.TDepotInvest = TDepotInvest;
	}

	@Column(name = "checkbeforeinput")
	public Boolean getCheckbeforeinput() {
		return this.checkbeforeinput;
	}

	public void setCheckbeforeinput(Boolean checkbeforeinput) {
		this.checkbeforeinput = checkbeforeinput;
	}

	@Column(name = "clearbinkill")
	public Boolean getClearbinkill() {
		return this.clearbinkill;
	}

	public void setClearbinkill(Boolean clearbinkill) {
		this.clearbinkill = clearbinkill;
	}

	@Column(name = "checkafteroutput")
	public Boolean getCheckafteroutput() {
		return this.checkafteroutput;
	}

	public void setCheckafteroutput(Boolean checkafteroutput) {
		this.checkafteroutput = checkafteroutput;
	}

	@Column(name = "inputcheck")
	public Boolean getInputcheck() {
		return this.inputcheck;
	}

	public void setInputcheck(Boolean inputcheck) {
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

	@Column(name = "flynet")
	public Boolean getFlynet() {
		return this.flynet;
	}

	public void setFlynet(Boolean flynet) {
		this.flynet = flynet;
	}

	@Column(name = "flyline")
	public Boolean getFlyline() {
		return this.flyline;
	}

	public void setFlyline(Boolean flyline) {
		this.flyline = flyline;
	}

	@Column(name = "detectfrequency", length = 60)
	public String getDetectfrequency() {
		return this.detectfrequency;
	}

	public void setDetectfrequency(String detectfrequency) {
		this.detectfrequency = detectfrequency;
	}

	@Column(name = "direct")
	public Boolean getDirect() {
		return this.direct;
	}

	public void setDirect(Boolean direct) {
		this.direct = direct;
	}

	@Column(name = "sample")
	public Boolean getSample() {
		return this.sample;
	}

	public void setSample(Boolean sample) {
		this.sample = sample;
	}

	@Column(name = "trap")
	public Boolean getTrap() {
		return this.trap;
	}

	public void setTrap(Boolean trap) {
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

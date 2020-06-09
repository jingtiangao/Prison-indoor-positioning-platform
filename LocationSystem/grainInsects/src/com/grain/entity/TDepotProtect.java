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
 * 害虫防治总表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_depot_protect")
public class TDepotProtect implements java.io.Serializable {

	private Long sm;
	private TDepotInvest TDepotInvest;
	private Boolean flynet;
	private Boolean flyline;
	private Boolean protectant;
	private Boolean inertpowder;
	private Boolean sanitary;
	private String otherpreprotectway;
	private Boolean isolateprotect;
	private Boolean fumigation;
	private Boolean controlledatmosphere;
	private Boolean frozen;
	private Boolean highertemperature;
	private Boolean radiation;
	private Boolean biologicalcontrol;
	private Boolean insecticidaldevice;
	private String otherkillway;
	private Boolean generator;
	private Boolean inbin;
	private Boolean giver;
	private Boolean fancrossing;
	private String othergiveway;
	private Boolean artificialscreening;
	private Boolean carbontubetrap;
	private Boolean corrugatedpapertraps;
	private Boolean frequencyvibrationlighttrap;
	private String otherdetectway;

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

	@Column(name = "protectant")
	public Boolean getProtectant() {
		return this.protectant;
	}

	public void setProtectant(Boolean protectant) {
		this.protectant = protectant;
	}

	@Column(name = "Inertpowder")
	public Boolean getInertpowder() {
		return this.inertpowder;
	}

	public void setInertpowder(Boolean inertpowder) {
		this.inertpowder = inertpowder;
	}

	@Column(name = "sanitary")
	public Boolean getSanitary() {
		return this.sanitary;
	}

	public void setSanitary(Boolean sanitary) {
		this.sanitary = sanitary;
	}

	@Column(name = "otherpreprotectway", length = 60)
	public String getOtherpreprotectway() {
		return this.otherpreprotectway;
	}

	public void setOtherpreprotectway(String otherpreprotectway) {
		this.otherpreprotectway = otherpreprotectway;
	}

	@Column(name = "isolateprotect")
	public Boolean getIsolateprotect() {
		return this.isolateprotect;
	}

	public void setIsolateprotect(Boolean isolateprotect) {
		this.isolateprotect = isolateprotect;
	}

	@Column(name = "Fumigation")
	public Boolean getFumigation() {
		return this.fumigation;
	}

	public void setFumigation(Boolean fumigation) {
		this.fumigation = fumigation;
	}

	@Column(name = "controlledatmosphere")
	public Boolean getControlledatmosphere() {
		return this.controlledatmosphere;
	}

	public void setControlledatmosphere(Boolean controlledatmosphere) {
		this.controlledatmosphere = controlledatmosphere;
	}

	@Column(name = "frozen")
	public Boolean getFrozen() {
		return this.frozen;
	}

	public void setFrozen(Boolean frozen) {
		this.frozen = frozen;
	}

	@Column(name = "highertemperature")
	public Boolean getHighertemperature() {
		return this.highertemperature;
	}

	public void setHighertemperature(Boolean highertemperature) {
		this.highertemperature = highertemperature;
	}

	@Column(name = "Radiation")
	public Boolean getRadiation() {
		return this.radiation;
	}

	public void setRadiation(Boolean radiation) {
		this.radiation = radiation;
	}

	@Column(name = "Biologicalcontrol")
	public Boolean getBiologicalcontrol() {
		return this.biologicalcontrol;
	}

	public void setBiologicalcontrol(Boolean biologicalcontrol) {
		this.biologicalcontrol = biologicalcontrol;
	}

	@Column(name = "Insecticidaldevice")
	public Boolean getInsecticidaldevice() {
		return this.insecticidaldevice;
	}

	public void setInsecticidaldevice(Boolean insecticidaldevice) {
		this.insecticidaldevice = insecticidaldevice;
	}

	@Column(name = "otherkillway", length = 60)
	public String getOtherkillway() {
		return this.otherkillway;
	}

	public void setOtherkillway(String otherkillway) {
		this.otherkillway = otherkillway;
	}

	@Column(name = "Generator")
	public Boolean getGenerator() {
		return this.generator;
	}

	public void setGenerator(Boolean generator) {
		this.generator = generator;
	}

	@Column(name = "inbin")
	public Boolean getInbin() {
		return this.inbin;
	}

	public void setInbin(Boolean inbin) {
		this.inbin = inbin;
	}

	@Column(name = "giver")
	public Boolean getGiver() {
		return this.giver;
	}

	public void setGiver(Boolean giver) {
		this.giver = giver;
	}

	@Column(name = "fancrossing")
	public Boolean getFancrossing() {
		return this.fancrossing;
	}

	public void setFancrossing(Boolean fancrossing) {
		this.fancrossing = fancrossing;
	}

	@Column(name = "othergiveway", length = 60)
	public String getOthergiveway() {
		return this.othergiveway;
	}

	public void setOthergiveway(String othergiveway) {
		this.othergiveway = othergiveway;
	}

	@Column(name = "Artificialscreening")
	public Boolean getArtificialscreening() {
		return this.artificialscreening;
	}

	public void setArtificialscreening(Boolean artificialscreening) {
		this.artificialscreening = artificialscreening;
	}

	@Column(name = "Carbontubetrap")
	public Boolean getCarbontubetrap() {
		return this.carbontubetrap;
	}

	public void setCarbontubetrap(Boolean carbontubetrap) {
		this.carbontubetrap = carbontubetrap;
	}

	@Column(name = "Corrugatedpapertraps")
	public Boolean getCorrugatedpapertraps() {
		return this.corrugatedpapertraps;
	}

	public void setCorrugatedpapertraps(Boolean corrugatedpapertraps) {
		this.corrugatedpapertraps = corrugatedpapertraps;
	}

	@Column(name = "Frequencyvibrationlighttrap")
	public Boolean getFrequencyvibrationlighttrap() {
		return this.frequencyvibrationlighttrap;
	}

	public void setFrequencyvibrationlighttrap(Boolean frequencyvibrationlighttrap) {
		this.frequencyvibrationlighttrap = frequencyvibrationlighttrap;
	}

	@Column(name = "otherdetectway", length = 60)
	public String getOtherdetectway() {
		return this.otherdetectway;
	}

	public void setOtherdetectway(String otherdetectway) {
		this.otherdetectway = otherdetectway;
	}

}

package com.grain.entity;

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
 * 分仓害虫防治
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_bin_protect")
public class TBinProtect implements java.io.Serializable {

	private Long sm;
	private TBinInvest TBinInvest;
	private Character flynet;
	private Character flyline;
	private Character protectant;
	private Character inertpowder;
	private Character sanitary;
	private String otherpreprotectway;
	private Character isolateprotect;
	private Character fumigation;
	private Character controlledatmosphere;
	private Character frozen;
	private Character highertemperature;
	private Character radiation;
	private Character biologicalcontrol;
	private Character insecticidaldevice;
	private String otherkillway;
	private Character generator;
	private Character inbin;
	private Character giver;
	private Character fancrossing;
	private String othergiveway;
	private Character artificialscreening;
	private Character carbontubetrap;
	private Character corrugatedpapertraps;
	private Character frequencyvibrationlighttrap;
	private String otherdetectway;


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

	@Column(name = "flynet", nullable = true, length = 1)
	public Character getFlynet() {
		return this.flynet;
	}

	public void setFlynet(Character flynet) {
		this.flynet = flynet;
	}

	@Column(name = "flyline", nullable = true, length = 1)
	public Character getFlyline() {
		return this.flyline;
	}

	public void setFlyline(Character flyline) {
		this.flyline = flyline;
	}

	@Column(name = "protectant", length = 1)
	public Character getProtectant() {
		return this.protectant;
	}

	public void setProtectant(Character protectant) {
		this.protectant = protectant;
	}

	@Column(name = "Inertpowder", length = 1)
	public Character getInertpowder() {
		return this.inertpowder;
	}

	public void setInertpowder(Character inertpowder) {
		this.inertpowder = inertpowder;
	}

	@Column(name = "sanitary", length = 1)
	public Character getSanitary() {
		return this.sanitary;
	}

	public void setSanitary(Character sanitary) {
		this.sanitary = sanitary;
	}

	@Column(name = "otherpreprotectway", length = 60)
	public String getOtherpreprotectway() {
		return this.otherpreprotectway;
	}

	public void setOtherpreprotectway(String otherpreprotectway) {
		this.otherpreprotectway = otherpreprotectway;
	}

	@Column(name = "isolateprotect", length = 1)
	public Character getIsolateprotect() {
		return this.isolateprotect;
	}

	public void setIsolateprotect(Character isolateprotect) {
		this.isolateprotect = isolateprotect;
	}

	@Column(name = "Fumigation", length = 1)
	public Character getFumigation() {
		return this.fumigation;
	}

	public void setFumigation(Character fumigation) {
		this.fumigation = fumigation;
	}

	@Column(name = "controlledatmosphere", length = 1)
	public Character getControlledatmosphere() {
		return this.controlledatmosphere;
	}

	public void setControlledatmosphere(Character controlledatmosphere) {
		this.controlledatmosphere = controlledatmosphere;
	}

	@Column(name = "frozen", length = 1)
	public Character getFrozen() {
		return this.frozen;
	}

	public void setFrozen(Character frozen) {
		this.frozen = frozen;
	}

	@Column(name = "highertemperature", length = 1)
	public Character getHighertemperature() {
		return this.highertemperature;
	}

	public void setHighertemperature(Character highertemperature) {
		this.highertemperature = highertemperature;
	}

	@Column(name = "Radiation", length = 1)
	public Character getRadiation() {
		return this.radiation;
	}

	public void setRadiation(Character radiation) {
		this.radiation = radiation;
	}

	@Column(name = "Biologicalcontrol", length = 1)
	public Character getBiologicalcontrol() {
		return this.biologicalcontrol;
	}

	public void setBiologicalcontrol(Character biologicalcontrol) {
		this.biologicalcontrol = biologicalcontrol;
	}

	@Column(name = "Insecticidaldevice", length = 1)
	public Character getInsecticidaldevice() {
		return this.insecticidaldevice;
	}

	public void setInsecticidaldevice(Character insecticidaldevice) {
		this.insecticidaldevice = insecticidaldevice;
	}

	@Column(name = "otherkillway", length = 60)
	public String getOtherkillway() {
		return this.otherkillway;
	}

	public void setOtherkillway(String otherkillway) {
		this.otherkillway = otherkillway;
	}

	@Column(name = "Generator", length = 1)
	public Character getGenerator() {
		return this.generator;
	}

	public void setGenerator(Character generator) {
		this.generator = generator;
	}

	@Column(name = "inbin", length = 1)
	public Character getInbin() {
		return this.inbin;
	}

	public void setInbin(Character inbin) {
		this.inbin = inbin;
	}

	@Column(name = "giver", length = 1)
	public Character getGiver() {
		return this.giver;
	}

	public void setGiver(Character giver) {
		this.giver = giver;
	}

	@Column(name = "fancrossing", length = 1)
	public Character getFancrossing() {
		return this.fancrossing;
	}

	public void setFancrossing(Character fancrossing) {
		this.fancrossing = fancrossing;
	}

	@Column(name = "othergiveway", length = 60)
	public String getOthergiveway() {
		return this.othergiveway;
	}

	public void setOthergiveway(String othergiveway) {
		this.othergiveway = othergiveway;
	}

	@Column(name = "Artificialscreening", nullable = true, length = 1)
	public Character getArtificialscreening() {
		return this.artificialscreening;
	}

	public void setArtificialscreening(Character artificialscreening) {
		this.artificialscreening = artificialscreening;
	}

	@Column(name = "Carbontubetrap", length = 1)
	public Character getCarbontubetrap() {
		return this.carbontubetrap;
	}

	public void setCarbontubetrap(Character carbontubetrap) {
		this.carbontubetrap = carbontubetrap;
	}

	@Column(name = "Corrugatedpapertraps", length = 1)
	public Character getCorrugatedpapertraps() {
		return this.corrugatedpapertraps;
	}

	public void setCorrugatedpapertraps(Character corrugatedpapertraps) {
		this.corrugatedpapertraps = corrugatedpapertraps;
	}

	@Column(name = "Frequencyvibrationlighttrap", length = 1)
	public Character getFrequencyvibrationlighttrap() {
		return this.frequencyvibrationlighttrap;
	}

	public void setFrequencyvibrationlighttrap(Character frequencyvibrationlighttrap) {
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

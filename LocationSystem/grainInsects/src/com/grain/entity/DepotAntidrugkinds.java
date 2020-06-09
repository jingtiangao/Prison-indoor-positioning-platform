package com.grain.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 抗药虫种总表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_depot_antidrugkinds")
public class DepotAntidrugkinds implements java.io.Serializable {

	private Long id;
	private DepotAntiphosphine depotAntiphosphine;
	private String kind;
	private Float densityafter;
	private Character ifsecond;
	private String othermeasures;
	private Float resistancevalue;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pID")
	public DepotAntiphosphine getDepotAntiphosphine() {
		return this.depotAntiphosphine;
	}

	public void setDepotAntiphosphine(DepotAntiphosphine depotAntiphosphine) {
		this.depotAntiphosphine = depotAntiphosphine;
	}
	

	@Column(name = "kind", length = 60)
	public String getKind() {
		return this.kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	@Column(name = "densityafter", precision = 12, scale = 0)
	public Float getDensityafter() {
		return this.densityafter;
	}

	public void setDensityafter(Float densityafter) {
		this.densityafter = densityafter;
	}

	@Column(name = "ifsecond", length = 1)
	public Character getIfsecond() {
		return this.ifsecond;
	}

	public void setIfsecond(Character ifsecond) {
		this.ifsecond = ifsecond;
	}

	@Column(name = "othermeasures", length = 100)
	public String getOthermeasures() {
		return this.othermeasures;
	}

	public void setOthermeasures(String othermeasures) {
		this.othermeasures = othermeasures;
	}

	@Column(name = "Resistancevalue", precision = 12, scale = 0)
	public Float getResistancevalue() {
		return this.resistancevalue;
	}

	public void setResistancevalue(Float resistancevalue) {
		this.resistancevalue = resistancevalue;
	}

}

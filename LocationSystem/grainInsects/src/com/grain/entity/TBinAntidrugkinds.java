package com.grain.entity;
// Generated 2016-3-20 22:47:51 by Hibernate Tools 3.5.0.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
/**
 * 抗药虫种分仓
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_bin_antidrugkinds")
public class TBinAntidrugkinds implements java.io.Serializable {

	private Long id;
	private String kind;
	private Float densityafter;
	private Character ifsecond;
	private String othermeasures;
	private Float resistancevalue;
	private TBinAntiphosphine TBinAntiphosphine;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pID")
	@JsonBackReference
	public TBinAntiphosphine getTBinAntiphosphine() {
		return this.TBinAntiphosphine;
	}

	@JsonBackReference
	public void setTBinAntiphosphine(TBinAntiphosphine TBinAntiphosphine) {
		this.TBinAntiphosphine = TBinAntiphosphine;
	}

}

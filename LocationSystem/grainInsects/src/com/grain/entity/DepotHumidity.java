package com.grain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 粮库湿度
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_depot_humidity")
public class DepotHumidity implements java.io.Serializable {

	private int id;
	TDepotInvest TDepotInvest;
	private Float january;
	private Float february;
	private Float march;
	private Float april;
	private Float may;
	private Float june;
	private Float july;
	private Float august;
	private Float september;
	private Float october;
	private Float november;
	private Float december;
	private String reporter;
	private String modifier;


	@Id

	@Column(name = "ID", unique = true, nullable = false)
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column(name = "January", precision = 12, scale = 0)
	public Float getJanuary() {
		return this.january;
	}

	public void setJanuary(Float january) {
		this.january = january;
	}

	@Column(name = "February", precision = 12, scale = 0)
	public Float getFebruary() {
		return this.february;
	}

	public void setFebruary(Float february) {
		this.february = february;
	}

	@Column(name = "March", precision = 12, scale = 0)
	public Float getMarch() {
		return this.march;
	}

	public void setMarch(Float march) {
		this.march = march;
	}

	@Column(name = "April", precision = 12, scale = 0)
	public Float getApril() {
		return this.april;
	}

	public void setApril(Float april) {
		this.april = april;
	}

	@Column(name = "May", precision = 12, scale = 0)
	public Float getMay() {
		return this.may;
	}

	public void setMay(Float may) {
		this.may = may;
	}

	@Column(name = "June", precision = 12, scale = 0)
	public Float getJune() {
		return this.june;
	}

	public void setJune(Float june) {
		this.june = june;
	}

	@Column(name = "July", precision = 12, scale = 0)
	public Float getJuly() {
		return this.july;
	}

	public void setJuly(Float july) {
		this.july = july;
	}

	@Column(name = "August", precision = 12, scale = 0)
	public Float getAugust() {
		return this.august;
	}

	public void setAugust(Float august) {
		this.august = august;
	}

	@Column(name = "September", precision = 12, scale = 0)
	public Float getSeptember() {
		return this.september;
	}

	public void setSeptember(Float september) {
		this.september = september;
	}

	@Column(name = "October", precision = 12, scale = 0)
	public Float getOctober() {
		return this.october;
	}

	public void setOctober(Float october) {
		this.october = october;
	}

	@Column(name = "November", precision = 12, scale = 0)
	public Float getNovember() {
		return this.november;
	}

	public void setNovember(Float november) {
		this.november = november;
	}

	@Column(name = "December", precision = 12, scale = 0)
	public Float getDecember() {
		return this.december;
	}

	public void setDecember(Float december) {
		this.december = december;
	}

	@Column(name = "Reporter", length = 20)
	public String getReporter() {
		return this.reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	@Column(name = "Modifier", length = 20)
	public String getModifier() {
		return this.modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sm")
	public TDepotInvest getTDepotInvest() {
		return TDepotInvest;
	}

	public void setTDepotInvest(TDepotInvest tDepotInvest) {
		TDepotInvest = tDepotInvest;
	}

}

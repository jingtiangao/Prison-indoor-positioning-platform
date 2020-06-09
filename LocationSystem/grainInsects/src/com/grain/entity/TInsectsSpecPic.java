package com.grain.entity;
// Generated 2016-3-20 22:47:51 by Hibernate Tools 3.5.0.Final


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 检索表特征图片
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_insects_spec_pic")
public class TInsectsSpecPic implements java.io.Serializable {

	private Long id;
	private TInsectsSpecification TInsectsSpecification;
	private String js;
	private String tp;
	private String source;
	private String modifer;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date modifydate;

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
	@JoinColumn(name = "PID")
	public TInsectsSpecification getTInsectsSpecification() {
		return this.TInsectsSpecification;
	}

	public void setTInsectsSpecification(TInsectsSpecification TInsectsSpecification) {
		this.TInsectsSpecification = TInsectsSpecification;
	}

	@Column(name = "js", length = 200)
	public String getJs() {
		return this.js;
	}

	public void setJs(String js) {
		this.js = js;
	}

	@Column(name = "tp")
	public String getTp() {
		return this.tp;
	}

	public void setTp(String tp) {
		this.tp = tp;
	}

	@Column(name = "source", length = 100)
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "modifer", length = 30)
	public String getModifer() {
		return this.modifer;
	}

	public void setModifer(String modifer) {
		this.modifer = modifer;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "modifydate", length = 19)
	public Date getModifydate() {
		return this.modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

}

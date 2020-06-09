package com.grain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.grain.entity.user.DepotUser;

/**
 * 工艺信息上报表,可以有两种方式进行上报：
 * <ol>
 * <li> 移动设备
 * <li> 固定设备
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_info_report")
public class InfoReport implements java.io.Serializable {

	private long id;
	private TProcType TProctype;
	private DepotUser depotuser;
	private String lcbm;
	private String proname;
	private Date startdate;
	private Date enddate;
	private String process;
	private String note;
	private Date reporttime;
	private String 	source;

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proc_sm")
	@JsonBackReference
	public TProcType getTProctype() {
		return this.TProctype;
	}

	public void setTProctype(TProcType TProctype) {
		this.TProctype = TProctype;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username")
	public DepotUser getDepotuser() {
		return this.depotuser;
	}

	public void setDepotuser(DepotUser depotuser) {
		this.depotuser = depotuser;
	}

	@Column(name = "lcbm", nullable = false, length = 60)
	public String getLcbm() {
		return this.lcbm;
	}

	public void setLcbm(String lcbm) {
		this.lcbm = lcbm;
	}

	@Column(name = "proname", length = 60)
	public String getProname() {
		return this.proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
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

	@Column(name = "process", nullable = false)
	public String getProcess() {
		return this.process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	@Column(name = "note")
	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "reporttime", length = 19)
	public Date getReporttime() {
		return this.reporttime;
	}

	public void setReporttime(Date reporttime) {
		this.reporttime = reporttime;
	}
	
	@Column(name="source")
	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

}

package com.grain.entity;
// Generated 2016-3-20 22:47:51 by Hibernate Tools 3.5.0.Final

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

/**
 * 检索表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_insects_index")
public class TInsectsIndex implements java.io.Serializable {

	private Long id;
	private String jsbmc;
	private String modifer;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date modifydate;
	private String auditor;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String audittime;
	private String auditadvice;
	private Boolean passaudit;
	private List<TInsectsSpecification> TInsectsSpecifications = new ArrayList<TInsectsSpecification>();
	
	/**
	 * 关联任务表
	 * @author Mark
	 */
	private Set<TaskDistribution> taskDistributions = new HashSet<TaskDistribution>();
	
//	private TaskDistribution taskDistribution;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "jsbmc", nullable = false, length = 100)
	public String getJsbmc() {
		return this.jsbmc;
	}

	public void setJsbmc(String jsbmc) {
		this.jsbmc = jsbmc;
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
	
	@Column(name = "auditor")
	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	@Column(name = "audittime")
	public String getAudittime() {
		return audittime;
	}

	public void setAudittime(String audittime) {
		this.audittime = audittime;
	}

	@Column(name = "passaudit")
	public Boolean getPassaudit() {
		return passaudit;
	}

	public void setPassaudit(Boolean passaudit) {
		this.passaudit = passaudit;
	}
	
	@Column(name = "auditadvice")
	public String getAuditadvice() {
		return auditadvice;
	}

	public void setAuditadvice(String auditadvice) {
		this.auditadvice = auditadvice;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TInsectsIndex",cascade=CascadeType.ALL, orphanRemoval=true)
	public List<TInsectsSpecification> getTInsectsSpecifications() {
		return this.TInsectsSpecifications;
	}

	public void setTInsectsSpecifications(List<TInsectsSpecification> TInsectsSpecifications) {
		this.TInsectsSpecifications = TInsectsSpecifications;
	}

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "taskDistributionId")
//	public TaskDistribution getTaskDistribution() {
//		return taskDistribution;
//	}
//
//	public void setTaskDistribution(TaskDistribution taskDistribution) {
//		this.taskDistribution = taskDistribution;
//	}

	
	
	@ManyToMany(mappedBy = "insectsIndexs", fetch = FetchType.LAZY)
	public Set<TaskDistribution> getTaskDistributions() {
		return taskDistributions;
	}

	public void setTaskDistributions(Set<TaskDistribution> taskDistributions) {
		this.taskDistributions = taskDistributions;
	}
	
	
}

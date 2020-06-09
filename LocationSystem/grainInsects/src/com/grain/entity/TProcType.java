package com.grain.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 防治工艺类型
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_proctype")

public class TProcType implements java.io.Serializable {
	/** 树路径分隔符 */
	private static final String TREE_PATH_SEPARATOR = ",";
	
	private String sm;
	private TProcType parent;
	private String proctype;
	private String source;
	/** 树路径 */
	private String treePath;
	/** 全称 */
	private String fullName;
	
	private String auditor;
	private String audittime;
	private Boolean passaudit;
	
	private String modifer;
	private Date modifydate;
	private List<InfoReport> TInfoReports = new ArrayList<InfoReport>(0);
	private List<TPreventprocess> TPreventprocesses = new ArrayList<TPreventprocess>(0);
	private List<TProcType> children = new ArrayList<TProcType>(0);

	@Id
	@Column(name = "sm", unique = true, nullable = false, length = 10)
	public String getSm() {
		return this.sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
	public TProcType getParent() {
		return this.parent;
	}

	public void setParent(TProcType parent) {
		this.parent = parent;
	}

	@Column(name = "proctype", length = 20)
	public String getProctype() {
		return this.proctype;
	}

	public void setProctype(String proctype) {
		this.proctype = proctype;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TProctype")
	public List<InfoReport> getTInfoReports() {
		return this.TInfoReports;
	}

	public void setTInfoReports(List<InfoReport> TInfoReports) {
		this.TInfoReports = TInfoReports;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TProctype")
	public List<TPreventprocess> getTPreventprocesses() {
		return this.TPreventprocesses;
	}

	public void setTPreventprocesses(List<TPreventprocess> TPreventprocesses) {
		this.TPreventprocesses = TPreventprocesses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
	public List<TProcType> getChildren() {
		return this.children;
	}

	public void setChildren(List<TProcType> children) {
		this.children = children;
	}

	/**
	 * 获取全称
	 * 
	 * @return 全称
	 */
	@Column(nullable = false, length = 500)
	public String getFullName() {
		return fullName;
	}

	/**
	 * 设置全称
	 * 
	 * @param fullName
	 *            全称
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * 获取树路径
	 * 
	 * @return 树路径
	 */
	@Column(nullable = false, updatable = false)
	public String getTreePath() {
		return treePath;
	}

	/**
	 * 设置树路径
	 * 
	 * @param treePath
	 *            树路径
	 */
	public void setTreePath(String treePath) {
		this.treePath = treePath;
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
	
	/**
	 * 持久化前处理
	 */
	@PrePersist
	public void prePersist() {
		TProcType parent = getParent();
		if (parent != null) {
			setFullName(parent.getFullName() + getProctype());
			setTreePath(parent.getTreePath() + parent.getSm() + TREE_PATH_SEPARATOR);
		} else {
			setFullName(getProctype());
			setTreePath(TREE_PATH_SEPARATOR);
		}
	}

	/**
	 * 更新前处理
	 */
	@PreUpdate
	public void preUpdate() {
		TProcType parent = getParent();
		if (parent != null) {
			setFullName(parent.getFullName() + getProctype());
		} else {
			setFullName(getProctype());
		}
	}

	/**
	 * 删除前处理
	 */
	@PreRemove
	public void preRemove() {
		
	}
	
}

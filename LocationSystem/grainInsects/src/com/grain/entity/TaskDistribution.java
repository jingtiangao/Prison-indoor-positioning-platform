package com.grain.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grain.entity.user.Expert;

/**
 * 任务分配实体类
 * @author Mark
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_task_distribution")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler",
		"catalogIndexs","insectsIndexs"
		})
public class TaskDistribution implements Serializable{

	private Long id;
//	private String username;
	private Integer taskType;
	private Date deadLine;
	private Date distributeTime;
	private Date modifyTime;
	private Expert expert;
	private List<TCatalogIndex> catalogIndexs = new ArrayList<TCatalogIndex>();
	private Set<TInsectsIndex> insectsIndexs = new HashSet<TInsectsIndex>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
//	@Column(name="username", length=10)
//	public String getUsername() {
//		return username;
//	}
//	public void setUsername(String username) {
//		this.username = username;
//	}
	
	@Column(name="tasktype")
	public Integer getTaskType() {
		return taskType;
	}
	public void setTaskType(Integer taskType) {
		this.taskType = taskType;
	}
	
	@Column(name="deadline")
	public Date getDeadLine() {
		return deadLine;
	}
	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}
	
	@Column(name="distributetime")
	public Date getDistributeTime() {
		return distributeTime;
	}
	public void setDistributeTime(Date distributeTime) {
		this.distributeTime = distributeTime;
	}
	
	@Column(name="modifytime")
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="t_task", 
			joinColumns = {@JoinColumn(name="catalogId")}, 
			inverseJoinColumns = {@JoinColumn(name = "distributionId")})
	public List<TCatalogIndex> getCatalogIndexs() {
		return catalogIndexs;
	}
	public void setCatalogIndexs(List<TCatalogIndex> catalogIndexs) {
		this.catalogIndexs = catalogIndexs;
	}
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="t_task", 
				joinColumns = {@JoinColumn(name="insectsId")}, 
				inverseJoinColumns = {@JoinColumn(name = "distributionId")})
	public Set<TInsectsIndex> getInsectsIndexs() {
		return insectsIndexs;
	}
	public void setInsectsIndexs(Set<TInsectsIndex> insectsIndexs) {
		this.insectsIndexs = insectsIndexs;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username")
	public Expert getExpert() {
		return expert;
	}
	public void setExpert(Expert expert) {
		this.expert = expert;
	}
	
	/*@ManyToMany(mappedBy = "taskDistributions", fetch = FetchType.LAZY)
	public List<TCatalogIndex> getCatalogIndexs() {
		return catalogIndexs;
	}
	public void setCatalogIndexs(List<TCatalogIndex> catalogIndexs) {
		this.catalogIndexs = catalogIndexs;
	}
	
	@ManyToMany(mappedBy = "taskDistributions", fetch = FetchType.LAZY)
	public Set<TInsectsIndex> getInsectsIndexs() {
		return insectsIndexs;
	}
	public void setInsectsIndexs(Set<TInsectsIndex> insectsIndexs) {
		this.insectsIndexs = insectsIndexs;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username")
	public Expert getExpert() {
		return expert;
	}
	public void setExpert(Expert expert) {
		this.expert = expert;
	}*/
	
	
}
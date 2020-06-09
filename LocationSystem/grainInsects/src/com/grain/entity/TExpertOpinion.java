package com.grain.entity;

import java.util.ArrayList;
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

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grain.entity.user.Expert;

/**
 * 专家意见表 t_expertopinion
 */

@Entity
@Table(name = "t_expertopinion")
public class TExpertOpinion {
	
	private Long id;
	private Expert expert;
	private String topic;
	private Integer optype;
	private String opinion;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String createdate;
	/** 标签 */
	private List<Graindepot> grainDepots = new ArrayList<Graindepot>();
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "username")
	@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler",
			"consultationList","opinionList","password","mobile","modifer","modifydate"
			})
	public Expert getExpert() {
		return expert;
	}
	public void setExpert(Expert expert) {
		this.expert = expert;
	}
	
	@Column(name = "topic", nullable = true)
	public String getTopic() {
		return topic;
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	@Column(name = "optype", nullable = true)
	public Integer getOptype() {
		return optype;
	}
	public void setOptype(Integer optype) {
		this.optype = optype;
	}
	
	@Column(name = "opinion")
	public String getOpinion() {
		return opinion;
	}
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_opinionobject")
	public List<Graindepot> getGrainDepots() {
		return grainDepots;
	}
	public void setGrainDepots(List<Graindepot> grainDepots) {
		this.grainDepots = grainDepots;
	}
	
	@Column(name = "createdate")
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	
}

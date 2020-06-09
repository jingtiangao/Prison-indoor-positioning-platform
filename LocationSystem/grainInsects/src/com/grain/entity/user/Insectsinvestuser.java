package com.grain.entity.user;

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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
	虫调用户表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_insectsinvestuser")
public class Insectsinvestuser implements java.io.Serializable {

	private String username;
	private String pass;
	private String realname;
	private String title;
	private String company;
	private String moblie;
	private Long point;
	private Boolean hasaudit;
	private String modifer;
	private Date modifydate;
	
	/** 角色 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="roleid", nullable = true)
	//@JsonUnwrapped
	@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler",
			"isSystem",	"resourceIds","description","createDate","modifyDate"})
	private Role role; 

	@Id
	@Column(name = "username", unique = true, nullable = false, length = 10)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", length = 255)
	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Column(name = "realname", length = 60)
	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Column(name = "title", length = 40)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "company", length = 100)
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Column(name = "moblie", length = 20)
	public String getMoblie() {
		return this.moblie;
	}

	public void setMoblie(String moblie) {
		this.moblie = moblie;
	}

//	@Column(name = "point", nullable = false)
	@Column(name = "point")
	public Long getPoint() {
		return this.point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	@Column(name = "hasaudit")
	public Boolean getHasaudit() {
		return this.hasaudit;
	}

	public void setHasaudit(Boolean hasaudit) {
		this.hasaudit = hasaudit;
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

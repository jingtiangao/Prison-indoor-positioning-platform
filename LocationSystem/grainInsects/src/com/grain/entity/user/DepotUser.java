package com.grain.entity.user;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grain.entity.Grainbin;
import com.grain.entity.Graindepot;

/**
 * Entity - 粮库用户
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_depotuser")
@SequenceGenerator(name = "sequenceGenerator", sequenceName = "t_depotuser_sequence")
@org.hibernate.annotations.Entity(dynamicInsert=true,dynamicUpdate=true) 
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler",
		"modifer","modifydate","pass"
		})
public class DepotUser implements Serializable {
	
	@Id
	@Column(name = "username", nullable = false, length = 20)
	String username;		//用户
	
	@Column(name = "password")
	String pass;			//密码
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="lkbm", nullable = false)
	@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler",
			"lkdz","longtitude","latitude","altitude",
			"hasreal","totalbin","postcode","contact",
			"phone","modifier","modifydate"
			})
	Graindepot graindepot;			//粮库
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_depotuser_grainbin")
	
	@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler",
			"binProtecteds","depotusers","binPhosphinefumigations",
			"binInertaerosols","binCas","binAntiphosphines",
			"binFlylines","grains","binInvests",
			"bintypePics","insectsInbinOnDepots","binCleankills",
			"deviceRealdatas","graindepot","structureofbody","structureofroof",
			"designcapacity","designgrainheapheight","longth","width",
			"height","circulatedevice","circulatefan","fanway",
			"elsedevice","contract","phone","note",
			"modifer","modifydate"
			})
	Set <Grainbin> grainbins = new HashSet<Grainbin>();			//粮仓
	
	@Column(name = "realname")
	String realname;		//真实姓名

	@Column(name = "mobile")
	String mobile;		//真实姓名
	
	@Column(name = "title")
	String title;			//职称
	
	@Column(name = "manager")
	Boolean manager;			//所长
	
	@Column(name = "hasaudit")
	Boolean hasaudit;			//审核标志
	
	@Column(name = "point")
	Long point;			//积分
	
	@Column(name = "modifer")
	String modifer;			//修改者
	
	@Column(name = "modifydate")
	Date modifydate;			//修改时刻
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean getManager() {
		return manager;
	}

	public void setManager(Boolean manager) {
		this.manager = manager;
	}

	public Boolean getHasaudit() {
		return hasaudit;
	}

	public void setHasaudit(Boolean hasaudit) {
		this.hasaudit = hasaudit;
	}

	public Long getPoint() {
		return point;
	}

	public void setPoint(Long point) {
		this.point = point;
	}

	public Graindepot getGraindepot() {
		return graindepot;
	}

	public void setGraindepot(Graindepot graindepot) {
		this.graindepot = graindepot;
	}


	public String getModifer() {
		return modifer;
	}

	public void setModifer(String modifer) {
		this.modifer = modifer;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}
	
	public Set<Grainbin> getGrainbins() {
		return grainbins;
	}

	public void setGrainbins(Set<Grainbin> grainbins) {
		this.grainbins = grainbins;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@PreUpdate
	public void preUpdate(){
		this.modifydate=new Date();
	}
	
}

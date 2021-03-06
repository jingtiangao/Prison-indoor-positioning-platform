package com.grain.entity;
// Generated 2016-3-20 22:47:51 by Hibernate Tools 3.5.0.Final

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.grain.CommonAttributes;
import com.grain.Principal;

/**
 * 分仓调查表 generated by hbm2java
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_bin_invest")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","handler",
		"tbinProtect","tbinPreprotect","tbinInsectses","tbinUseDrugs"
		})
public class TBinInvest implements java.io.Serializable {

	private Long sm;
	private Grainbin TGrainbin;
	private Integer annual;
	private String reporter;
	private String reportdate;
	private String modifer;
	private String modifydate;
	private List<TBinProtect> TBinProtect = new ArrayList<TBinProtect>(0);
	private List<TBinPreprotect> TBinPreprotect = new ArrayList<TBinPreprotect>(0);
	private List<TBinInsects> TBinInsectses = new ArrayList<TBinInsects>(0);
	private List<TBinUseDrug> TBinUseDrugs = new ArrayList<TBinUseDrug>(0);
	private String phone;// 后添加的
	
	@Column(name = "phone")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "sm", unique = true, nullable = false)
	public Long getSm() {
		return this.sm;
	}

	public void setSm(Long sm) {
		this.sm = sm;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lcbm", nullable = false)
	public Grainbin getTGrainbin() {
		return this.TGrainbin;
	}

	public void setTGrainbin(Grainbin TGrainbin) {
		this.TGrainbin = TGrainbin;
	}

	@Column(name = "Annual", nullable = false)
	public Integer getAnnual() {
		return this.annual;
	}

	public void setAnnual(Integer annual) {
		this.annual = annual;
	}

	@Column(name = "reporter", length = 20)
	public String getReporter() {
		return this.reporter;
	}

	public void setReporter(String reporter) {
		this.reporter = reporter;
	}

	@Column(name = "reportdate", length = 10)
	public String getReportdate() {
		return this.reportdate;
	}

	public void setReportdate(String reportdate) {
		this.reportdate = reportdate;
	}

	@Column(name = "modifer", length = 20)
	public String getModifer() {
		return this.modifer;
	}

	public void setModifer(String modifer) {
		this.modifer = modifer;
	}

	@Column(name = "modifydate", length = 10)
	public String getModifydate() {
		return this.modifydate;
	}

	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TBinInvest",cascade=CascadeType.ALL)
	public List<TBinProtect> getTBinProtect() {
		return this.TBinProtect;
	}

	public void setTBinProtect(List<TBinProtect> TBinProtect) {
		this.TBinProtect = TBinProtect;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TBinInvest",cascade=CascadeType.ALL)
	public List<TBinPreprotect> getTBinPreprotect() {
		return this.TBinPreprotect;
	}

	public void setTBinPreprotect(List<TBinPreprotect> TBinPreprotect) {
		this.TBinPreprotect = TBinPreprotect;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL,orphanRemoval=true)
	public List<TBinInsects> getTBinInsectses() {
		return this.TBinInsectses;
	}

	public void setTBinInsectses(List<TBinInsects> TBinInsectses) {
		this.TBinInsectses = TBinInsectses;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL,orphanRemoval=true)
	public List<TBinUseDrug> getTBinUseDrugs() {
		return this.TBinUseDrugs;
	}

	public void setTBinUseDrugs(List<TBinUseDrug> TBinUseDrugs) {
		this.TBinUseDrugs = TBinUseDrugs;
	}
	
	/**
	 * 持久化前处理
	 */
	@PrePersist
	public void prePersist() {
		SetModify();
	}
	
	/**
	 * 更新前处理
	 */
	@PreUpdate
	public void preUpdate() {
		SetModify();
	}
	
	private void SetModify(){
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			Principal principal = (Principal) request.getSession().getAttribute(CommonAttributes.Principal);
			if (principal!=null){
				this.modifer = principal.getUsername();
				 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");  
				this.modifydate = dateFormat.format(new Date());
			}
		}
	}

}

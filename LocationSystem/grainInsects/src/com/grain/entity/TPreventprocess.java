package com.grain.entity;
// Generated 2016-3-20 22:47:51 by Hibernate Tools 3.5.0.Final

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.servlet.http.HttpServletRequest;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.grain.CommonAttributes;
import com.grain.Principal;

/**
 *  防治工艺表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_preventprocess")

public class TPreventprocess implements java.io.Serializable {

	private String sm;
	
	private TProcType TProctype;
	//private TCatalogIndex TCatalogIndex;
	private String proname;
	private String keywords;
	private String material;
	private String procway;
	private String area;
	private String seasion;
	private String process;
	private String note;
	private String source;
	private String modifer;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date modifydate;
	private List<TPreventprocessdetach> TPreventprocessdetaches = new ArrayList<TPreventprocessdetach>(0);
	//private List<TCatalogIndex> TCatalogIndexes = new ArrayList<TCatalogIndex>(0);
	private Set<TCatalogIndex> catalogIndexs = new HashSet<TCatalogIndex>();
	@Id
	@Column(name = "sm", unique = true, nullable = false, length = 10)
	public String getSm() {
		return this.sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "proctypeid")
	@JsonBackReference
	public TProcType getTProctype() {
		return this.TProctype;
	}

	@JsonBackReference
	public void setTProctype(TProcType TProctype) {
		this.TProctype = TProctype;
	}

	//@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "catalogid")
	@ManyToMany(mappedBy = "tPreventprocesses", fetch = FetchType.LAZY)
	/*public List<TCatalogIndex> getTCatalogIndex() {
		return this.TCatalogIndexes;
	}

	public void setTCatalogIndex(List<TCatalogIndex> TCatalogIndexes) {
		this.TCatalogIndexes = TCatalogIndexes;
	}
	*/
	public Set<TCatalogIndex> getCatalogIndexs() {
		return catalogIndexs;
	}

	public void setCatalogIndexs(Set<TCatalogIndex> catalogIndexs) {
		this.catalogIndexs = catalogIndexs;
	}

	@Column(name = "proname", nullable = false, length = 60)
	public String getProname() {
		return this.proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}

	@Column(name = "keywords", nullable = false, length = 200)
	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Column(name = "material", length = 200)
	public String getMaterial() {
		return this.material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	@Column(name = "procway", length = 200)
	public String getProcway() {
		return this.procway;
	}

	public void setProcway(String procway) {
		this.procway = procway;
	}

	@Column(name = "area", nullable = false, length = 200)
	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Column(name = "seasion", nullable = false, length = 60)
	public String getSeasion() {
		return this.seasion;
	}

	public void setSeasion(String seasion) {
		this.seasion = seasion;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TPreventprocess")
	public List<TPreventprocessdetach> getTPreventprocessdetaches() {
		return this.TPreventprocessdetaches;
	}

	public void setTPreventprocessdetaches(List<TPreventprocessdetach> TPreventprocessdetaches) {
		this.TPreventprocessdetaches = TPreventprocessdetaches;
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
					this.modifydate = new Date();
				}
			}
		}


}

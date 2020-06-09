package com.grain.entity.page;

import java.util.Date;


/**
 * 分类表
 */
@SuppressWarnings("serial")
public class CatalogIndex implements java.io.Serializable {

	private Long id;
	private Long _parentId;
	private String bm;
	private String flm;
	private String mc;
	private String ywm;
	private String zylb;
	private String ymc;
	private String tz;
	private String shxx;
	private String fb;
	private String wh;
	private String keywords;
	private String ms;
	private String source;
	private String auditor;
	private String audittime;
	private Boolean passaudit;
	//用于ztree异步加载的标识符, added by lxq
	private Boolean isParent;
	//用于treegrid异步加载的标识符, added by lxq
	private String state;
	private String auditadvice;
	public String getAuditadvice() {
		return auditadvice;
	}

	public void setAuditadvice(String auditadvice) {
		this.auditadvice = auditadvice;
	}

	private String modifer;
	private Date modifydate;
	/** 树路径 */
	private String treePath;
	/** 层级 */
	private Integer grade;
	/** 是否置顶 */
	private Boolean isTop;
	String iconCls;		//展示图标

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long get_parentId() {
		return _parentId;
	}

	public void set_parentId(Long _parentId) {
		this._parentId = _parentId;
	}

	public String getBm() {
		return this.bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getFlm() {
		return this.flm;
	}

	public void setFlm(String flm) {
		this.flm = flm;
	}

	public String getMc() {
		return this.mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getYwm() {
		return this.ywm;
	}

	public void setYwm(String ywm) {
		this.ywm = ywm;
	}

	public String getZylb() {
		return this.zylb;
	}

	public void setZylb(String zylb) {
		this.zylb = zylb;
	}

	public String getYmc() {
		return this.ymc;
	}

	public void setYmc(String ymc) {
		this.ymc = ymc;
	}

	public String getTz() {
		return this.tz;
	}

	public void setTz(String tz) {
		this.tz = tz;
	}

	public String getShxx() {
		return this.shxx;
	}

	public void setShxx(String shxx) {
		this.shxx = shxx;
	}

	public String getFb() {
		return this.fb;
	}

	public void setFb(String fb) {
		this.fb = fb;
	}

	public String getWh() {
		return this.wh;
	}

	public void setWh(String wh) {
		this.wh = wh;
	}

	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getMs() {
		return this.ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
	}

	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getModifer() {
		return this.modifer;
	}

	public void setModifer(String modifer) {
		this.modifer = modifer;
	}

	public Date getModifydate() {
		return this.modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}


	/**
	 * 获取树路径
	 * 
	 * @return 树路径
	 */
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
	
	/**
	 * 获取层级
	 * 
	 * @return 层级
	 */
	public Integer getGrade() {
		return grade;
	}

	/**
	 * 设置层级
	 * 
	 * @param grade
	 *            层级
	 */
	public void setGrade(Integer grade) {
		this.grade = grade;
	}


	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getAudittime() {
		return audittime;
	}

	public void setAudittime(String audittime) {
		this.audittime = audittime;
	}

	public Boolean getPassaudit() {
		return passaudit;
	}

	public void setPassaudit(Boolean passaudit) {
		this.passaudit = passaudit;
	}
	
	public Boolean getIsTop() {
		return isTop;
	}

	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

}
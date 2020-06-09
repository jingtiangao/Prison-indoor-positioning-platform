package com.grain.entity.page;

/**
 * 防治工艺类型表 added by lxq
 */
@SuppressWarnings("serial")
public class ProcType implements java.io.Serializable {

	private String sm;
	private String _parentId;
	
	private String proctype;
	
	private Boolean isParent;
	
	String iconCls;		//展示图标

	public String getSm() {
		return this.sm;
	}

	public void setSm(String sm) {
		this.sm = sm;
	}

	public String get_parentId() {
		return _parentId;
	}

	public void set_parentId(String _parentId) {
		this._parentId = _parentId;
	}
	public String getProctype() {
		return this.proctype;
	}

	public void setProctype(String proctype) {
		this.proctype = proctype;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	//@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "children")
	public Boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

}
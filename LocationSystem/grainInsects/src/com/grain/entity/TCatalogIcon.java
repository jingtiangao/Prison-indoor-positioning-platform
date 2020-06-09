package com.grain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 分类特征害虫图标 t_catalog_icon
 */

@Entity
@Table(name = "t_catalog_icon")
public class TCatalogIcon {
	
	private Long id;
	private String name;
	private String cssname;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name = "cssname")
	public String getCssname() {
		return cssname;
	}
	public void setCssname(String cssname) {
		this.cssname = cssname;
	}
}

package com.grain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.apache.commons.lang.builder.CompareToBuilder;
import org.springframework.web.multipart.MultipartFile;


/**
 * 加工厂釆虫图片
 * @author Mark
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_factory_pic")
public class TFactoryPic implements java.io.Serializable {

	private Long id;
	private InsectsOnfactory insectsOnfactory;
	/*private String smCollection;
	private String pic;*/
	private Date collecttime;

	/** 标题 */
	private String title;

	/** 原图片 */
	private String source;

	/** 大图片 */
	private String large;

	/** 中图片 */
	private String medium;

	/** 缩略图 */
	private String thumbnail;

	/** 排序 */
	private Integer order;
	
	private MultipartFile file;
	
	public TFactoryPic(){}
		
	public TFactoryPic(String title, Integer order, MultipartFile file) {
		this.title = title;
		this.order = order;
		this.file = file;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	/*@Column(name = "sm_collection", length = 30)
	public String getSmCollection() {
		return this.smCollection;
	}

	public void setSmCollection(String smCollection) {
		this.smCollection = smCollection;
	}

	@Column(name = "pic", nullable = false)
	public String getPic() {
		return this.pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}*/

	@Column(name = "collecttime", length = 19)
	public Date getCollecttime() {
		return this.collecttime;
	}

	public void setCollecttime(Date collecttime) {
		this.collecttime = collecttime;
	}

	/**
	 * 获取标题
	 * 
	 * @return 标题
	 */
	@Column(name = "title", length = 255)
	public String getTitle() {
		return title;
	}

	/**
	 * 设置标题
	 * 
	 * @param title
	 *            标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取原图片
	 * 
	 * @return 原图片
	 */
	@Column(name = "source", length = 255)
	public String getSource() {
		return source;
	}

	/**
	 * 设置原图片
	 * 
	 * @param source
	 *            原图片
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * 获取大图片
	 * 
	 * @return 大图片
	 */
	@Column(name = "large", length = 255)
	public String getLarge() {
		return large;
	}

	/**
	 * 设置大图片
	 * 
	 * @param large
	 *            大图片
	 */
	public void setLarge(String large) {
		this.large = large;
	}

	/**
	 * 获取中图片
	 * 
	 * @return 中图片
	 */
	@Column(name = "medium", length = 255)
	public String getMedium() {
		return medium;
	}

	/**
	 * 设置中图片
	 * 
	 * @param medium
	 *            中图片
	 */
	public void setMedium(String medium) {
		this.medium = medium;
	}

	/**
	 * 获取缩略图
	 * 
	 * @return 缩略图
	 */
	@Column(name = "thumbnail", length = 255)
	public String getThumbnail() {
		return thumbnail;
	}

	/**
	 * 设置缩略图
	 * 
	 * @param thumbnail
	 *            缩略图
	 */
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	/**
	 * 获取排序
	 * 
	 * @return 排序
	 */
	@Min(0)
	@Column(name = "orders", length = 11)
	public Integer getOrder() {
		return order;
	}

	/**
	 * 设置排序
	 * 
	 * @param order
	 *            排序
	 */
	public void setOrder(Integer order) {
		this.order = order;
	}

	/**
	 * 获取文件
	 * 
	 * @return 文件
	 */
	@Transient
	public MultipartFile getFile() {
		return file;
	}

	/**
	 * 设置文件
	 * 
	 * @param file
	 *            文件
	 */
	public void setFile(MultipartFile file) {
		this.file = file;
	}

	/**
	 * 判断是否为空
	 * 
	 * @return 是否为空
	 */
	@Transient
	public boolean isEmpty() {
		return (getFile() == null || getFile().isEmpty() );
	}
	
	/**
	 * 实现compareTo方法
	 * 
	 * @param productImage
	 *            采虫图片
	 * @return 比较结果
	 */
	public int compareTo(TFactoryPic factoryPic) {
		return new CompareToBuilder().append(getOrder(), factoryPic.getOrder()).toComparison();
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sm_collection")
	public InsectsOnfactory getInsectsOnfactory() {
		return this.insectsOnfactory;
	}

	public void setInsectsOnfactory(InsectsOnfactory insectsOnfactory) {
		this.insectsOnfactory = insectsOnfactory;
	}

}

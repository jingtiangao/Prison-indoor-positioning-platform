package com.grain.entity;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 分类表
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "t_catalog_index")
public class TCatalogIndex extends BaseEntity {

	/** 树路径分隔符 */
	public static final String TREE_PATH_SEPARATOR = ",";
	/** 访问路径前缀 */
	private static final String PATH_PREFIX = "/catalogIndex/list";
	
	/** 虫种属性值属性名称前缀 */
	public static final String ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX = "attributeValue";

	/** 访问路径后缀 */
	private static final String PATH_SUFFIX = ".jhtml";
	
	/**
	 * 排序类型
	 */
	public enum OrderType {

		/** 置顶降序 */
		topDesc,

		/** 价格升序 *//*
		priceAsc,

		*//** 价格降序 *//*
		priceDesc,

		*//** 销量降序 *//*
		salesDesc,

		*//** 评分降序 *//*
		scoreDesc,*/

		/** 日期降序 */
		dateDesc,
		
		/* 月点击降序 */
		monthHitsDesc
	}
	
	private TCatalogIndex parent;
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
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String audittime;
	private String auditadvice;
	private Boolean passaudit;

	private String image;		//展示图片位置
	
	String iconCls;		//展示图标
	
	private String modifer;
	/** 树路径 */
	private String treePath;
	/** 层级 */
	private Integer grade;
	/** 是否置顶 */
	private Boolean isTop;
	/** 点击数 */
	private Long hits;
	/** 周点击数 */
	private Long weekHits;
	/** 月点击数 */
	private Long monthHits;

	// 防治工艺
	//private List<TPreventprocess> TPreventprocesses = new ArrayList<TPreventprocess>(0);
	private Set<TPreventprocess> tPreventprocesses = new HashSet<TPreventprocess>();
	// 图片
	private List<TCatalogPic> TCatalogPics = new ArrayList<TCatalogPic>(0);
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_catalogindex_protectprocess")
	public Set<TPreventprocess> gettPreventprocesses() {
		return tPreventprocesses;
	}

	public void settPreventprocesses(Set<TPreventprocess> tPreventprocesses) {
		this.tPreventprocesses = tPreventprocesses;
	}

	// 数字识别参数
	private List<TDigitalFeature> TDigitalFeatures = new ArrayList<TDigitalFeature>(0);
	
	// 检索表特征
	private List<TInsectsSpecification> TInsectsSpecifications = new ArrayList<TInsectsSpecification>(0);

	
	/** 标签 */
	private Set<Tag> tags = new HashSet<Tag>();
	/** 下级分类 */
	private Set<TCatalogIndex> children = new HashSet<TCatalogIndex>();
	
	// mark add
	/** 筛选属性 */
	private Set<Attribute> attributes = new HashSet<Attribute>();

	/**
	 * 任务分配表
	 * @author Mark
	 */
	private List<TaskDistribution> taskDistributions = new ArrayList<TaskDistribution>();
	
//	private TaskDistribution taskDistribution;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PID")
	public TCatalogIndex getParent() {
		return this.parent;
	}

	public void setParent(TCatalogIndex TCatalogIndex) {
		this.parent = TCatalogIndex;
	}

	@Column(name = "BM", nullable = false, length = 30)
	public String getBm() {
		return this.bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	@Column(name = "FLM", nullable = false, length = 30)
	public String getFlm() {
		return this.flm;
	}

	public void setFlm(String flm) {
		this.flm = flm;
	}

	@Column(name = "MC", nullable = false, length = 60)
	public String getMc() {
		return this.mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	@Column(name = "ywm", nullable = false, length = 200)
	public String getYwm() {
		return this.ywm;
	}

	public void setYwm(String ywm) {
		this.ywm = ywm;
	}

	@Column(name = "zylb", length = 100)
	public String getZylb() {
		return this.zylb;
	}

	public void setZylb(String zylb) {
		this.zylb = zylb;
	}

	@Column(name = "YMC", length = 200)
	public String getYmc() {
		return this.ymc;
	}

	public void setYmc(String ymc) {
		this.ymc = ymc;
	}

	@Column(name = "TZ")
	public String getTz() {
		return this.tz;
	}

	public void setTz(String tz) {
		this.tz = tz;
	}

	@Column(name = "shxx")
	public String getShxx() {
		return this.shxx;
	}

	public void setShxx(String shxx) {
		this.shxx = shxx;
	}

	@Column(name = "fb")
	public String getFb() {
		return this.fb;
	}

	public void setFb(String fb) {
		this.fb = fb;
	}

	@Column(name = "wh")
	public String getWh() {
		return this.wh;
	}

	public void setWh(String wh) {
		this.wh = wh;
	}

	@Column(name = "keywords", length = 500)
	public String getKeywords() {
		return this.keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Column(name = "MS")
	public String getMs() {
		return this.ms;
	}

	public void setMs(String ms) {
		this.ms = ms;
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


	//本表操作分类图片表
	@Valid
	@ElementCollection
	@CollectionTable(name ="t_catalog_pic")
	public List<TCatalogPic> getTCatalogPics() {
		return this.TCatalogPics;
	}

	public void setTCatalogPics(List<TCatalogPic> TCatalogPics) {
		this.TCatalogPics = TCatalogPics;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TCatalogIndex")
	public List<TDigitalFeature> getTDigitalFeatures() {
		return this.TDigitalFeatures;
	}

	public void setTDigitalFeatures(List<TDigitalFeature> TDigitalFeatures) {
		this.TDigitalFeatures = TDigitalFeatures;
	}

	/**
	 * 获取标签
	 * 
	 * @return 标签
	 */
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "xx_catalogindex_tag")
	@OrderBy("order asc")
	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	/**
	 * 获取树路径
	 * 
	 * @return 树路径
	 */
	@Column(nullable = false)
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
	@Column(nullable = false)
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

	/**
	 * 获取下级分类
	 * 
	 * @return 下级分类
	 */
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy("id asc")
	public Set<TCatalogIndex> getChildren() {
		return children;
	}

	public void setChildren(Set<TCatalogIndex> children) {
		this.children = children;
	}
	
	/**
	 * 获取筛选属性
	 * 
	 * @return 筛选属性
	 */
	@OneToMany(mappedBy = "insectCategory", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@OrderBy("order asc")
	public Set<Attribute> getAttributes() {
		return attributes;
	}

	/**
	 * 设置筛选属性
	 * 
	 * @param attributes
	 *            筛选属性
	 */
	public void setAttributes(Set<Attribute> attributes) {
		this.attributes = attributes;
	}

	@Column(name = "auditor")
	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	@Column(name = "audittime")
	public String getAudittime() {
		return audittime;
	}

	public void setAudittime(String audittime) {
		this.audittime = audittime;
	}

	@Column(name = "passaudit")
	public Boolean getPassaudit() {
		return passaudit;
	}

	public void setPassaudit(Boolean passaudit) {
		this.passaudit = passaudit;
	}
	
	@Column(name = "auditadvice")
	public String getAuditadvice() {
		return auditadvice;
	}

	public void setAuditadvice(String auditadvice) {
		this.auditadvice = auditadvice;
	}
	
	@Column(name = "isTop")
	public Boolean getIsTop() {
		return isTop;
	}

	public void setIsTop(Boolean isTop) {
		this.isTop = isTop;
	}
	
	@Column(name = "image")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * 获取点击数
	 * 
	 * @return 点击数
	 */
	@Column(nullable = false)
	public Long getHits() {
		return hits;
	}

	/**
	 * 设置点击数
	 * 
	 * @param hits
	 *            点击数
	 */
	public void setHits(Long hits) {
		this.hits = hits;
	}
	
	/**
	 * 获取周点击数
	 * 
	 * @return 周点击数
	 */
	@Field(store = Store.YES, index = Index.NO)
	@Column(nullable = false)
	public Long getWeekHits() {
		return weekHits;
	}

	/**
	 * 设置周点击数
	 * 
	 * @param weekHits
	 *            周点击数
	 */
	public void setWeekHits(Long weekHits) {
		this.weekHits = weekHits;
	}

	/**
	 * 获取月点击数
	 * 
	 * @return 月点击数
	 */
	@Field(store = Store.YES, index = Index.NO)
	@Column(nullable = false)
	public Long getMonthHits() {
		return monthHits;
	}

	/**
	 * 设置月点击数
	 * 
	 * @param monthHits
	 *            月点击数
	 */
	public void setMonthHits(Long monthHits) {
		this.monthHits = monthHits;
	}

	/**
	 * 获取树路径
	 * 
	 * @return 树路径
	 */
	@Transient
	public List<Long> getTreePaths() {
		List<Long> treePaths = new ArrayList<Long>();
		String[] ids = StringUtils.split(getTreePath(), TREE_PATH_SEPARATOR);
		if (ids != null) {
			for (String id : ids) {
				treePaths.add(Long.valueOf(id));
			}
		}
		return treePaths;
	}

	/**
	 * 获取访问路径
	 * 
	 * @return 访问路径
	 */
	@Transient
	public String getPath() {
		
		if (getId() != null) {
			return PATH_PREFIX + "/" + getId() + PATH_SUFFIX;
		}
		return null;
	}
	
	/**
	 * 获取缩略图
	 * 
	 * @return 缩略图
	 */
	@JsonProperty
	@Transient
	public String getThumbnail() {
/*		if (getTCatalogPics() != null && !getTCatalogPics().isEmpty()) {
			return getTCatalogPics().get(0).getThumbnail();
		}*/
		return null;
	}
	
	@Column(name = "iconCls")
	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	
	/**
	 * 获取商品属性值
	 * 
	 * @param attribute
	 *            商品属性
	 * @return 商品属性值
	 */
	@Transient
	public String getAttributeValue(Attribute attribute) {
		if (attribute != null && attribute.getPropertyIndex() != null) {
			try {
				String propertyName = ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + attribute.getPropertyIndex();
				return (String) PropertyUtils.getProperty(this, propertyName);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 设置商品属性值
	 * 
	 * @param attribute
	 *            商品属性
	 * @param value
	 *            商品属性值
	 */
	@Transient
	public void setAttributeValue(Attribute attribute, String value) {
		if (attribute != null && attribute.getPropertyIndex() != null) {
			if (StringUtils.isEmpty(value)) {
				value = null;
			}
			if (value == null || (attribute.getOptions() != null && attribute.getOptions().contains(value))) {
				try {
					String propertyName = ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + attribute.getPropertyIndex();
					PropertyUtils.setProperty(this, propertyName, value);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "TCatalogIndex",cascade=CascadeType.ALL, orphanRemoval=true)
	public List<TInsectsSpecification> getTInsectsSpecifications() {
		return TInsectsSpecifications;
	}

	public void setTInsectsSpecifications(List<TInsectsSpecification> tInsectsSpecifications) {
		TInsectsSpecifications = tInsectsSpecifications;
	}

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "taskDistributionId")
//	public TaskDistribution getTaskDistribution() {
//		return taskDistribution;
//	}
//
//	public void setTaskDistribution(TaskDistribution taskDistribution) {
//		this.taskDistribution = taskDistribution;
//	}

	
	
	
	@ManyToMany(mappedBy = "catalogIndexs", fetch = FetchType.LAZY)
	public List<TaskDistribution> getTaskDistributions() {
		return taskDistributions;
	}

	public void setTaskDistributions(List<TaskDistribution> taskDistributions) {
		this.taskDistributions = taskDistributions;
	}
}

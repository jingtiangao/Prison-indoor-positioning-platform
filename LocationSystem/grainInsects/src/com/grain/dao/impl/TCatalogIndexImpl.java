package com.grain.dao.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import com.grain.Filter;
import com.grain.Order;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.dao.TCatalogIndexDao;
import com.grain.entity.ArticleCategory;
import com.grain.entity.Attribute;
import com.grain.entity.TCatalogIndex;
import com.grain.entity.TCatalogIndex.OrderType;
import com.grain.entity.Tag;

/**
 * Dao - 害虫分类表
 * 
 */
@Repository("tcatalogIndexDaoImpl")
public class TCatalogIndexImpl extends BaseDaoImpl<TCatalogIndex, Long> 
implements TCatalogIndexDao {

	public List<TCatalogIndex> findRoots(Integer count) {
		String jpql = "select catalog from TCatalogIndex catalog where catalog.parent is null order by catalog.id asc";
		TypedQuery<TCatalogIndex> query = entityManager.createQuery(jpql, TCatalogIndex.class).setFlushMode(FlushModeType.COMMIT);
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}
	
	public List<TCatalogIndex> findParents(TCatalogIndex catalogindex, Integer count) {
		if (catalogindex == null || catalogindex.getParent() == null) {
			return Collections.<TCatalogIndex> emptyList();
		}
		String jpql = "select catalog from TCatalogIndex catalog where catalog.id in (:ids) order by catalog.grade asc";
		TypedQuery<TCatalogIndex> query = entityManager.createQuery(jpql, TCatalogIndex.class).setFlushMode(FlushModeType.COMMIT)
				.setParameter("ids", catalogindex.getTreePaths());
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}
	
	/**
	 * 得到catalogindex的所有子类
	 */
	@Override
	public List<TCatalogIndex> findChildren(TCatalogIndex catalogindex, Integer count) {
		TypedQuery<TCatalogIndex> query;
		if (catalogindex != null) {
			String jpql = "select catalog from TCatalogIndex catalog where catalog.treePath like :treePath order by catalog.id asc";
			query = entityManager.createQuery(jpql, TCatalogIndex.class).setFlushMode(FlushModeType.COMMIT)
					.setParameter("treePath", "%" + TCatalogIndex.TREE_PATH_SEPARATOR + catalogindex.getId() + TCatalogIndex.TREE_PATH_SEPARATOR + "%");
		} else {
			String jpql = "select catalog from TCatalogIndex catalog order by catalog.id asc";
			query = entityManager.createQuery(jpql, TCatalogIndex.class).setFlushMode(FlushModeType.COMMIT);
		}
		if (count != null) {
			query.setMaxResults(count);
		}
		return sort(query.getResultList(), catalogindex);
	}
	
	/**
	 * 得到catalogindex的直接子类
	 */
	@Override
	public List<TCatalogIndex> findDirectChildren(TCatalogIndex catalogindex, Integer count) {
		TypedQuery<TCatalogIndex> query;
		if (catalogindex != null) {
			String jpql = "select catalog from TCatalogIndex catalog where catalog.treePath like :treePath order by catalog.id asc";
			query = entityManager.createQuery(jpql, TCatalogIndex.class).setFlushMode(FlushModeType.COMMIT)
					.setParameter("treePath", catalogindex.getTreePath() + catalogindex.getId() + TCatalogIndex.TREE_PATH_SEPARATOR);
		} else {
			String jpql = "select catalog from TCatalogIndex catalog order by catalog.id asc";
			query = entityManager.createQuery(jpql, TCatalogIndex.class).setFlushMode(FlushModeType.COMMIT);
		}
		if (count != null) {
			query.setMaxResults(count);
		}
		return sort(query.getResultList(), catalogindex);
	}
	
	public List<TCatalogIndex> findChildren(TCatalogIndex insectCategory, List<Tag> tags, Map<Attribute, String> attributeValue, Boolean isTop, OrderType orderType, Integer count, List<Filter> filters, List<Order> orders) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TCatalogIndex> criteriaQuery = criteriaBuilder.createQuery(TCatalogIndex.class);
		Root<TCatalogIndex> root = criteriaQuery.from(TCatalogIndex.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (insectCategory != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.equal(root.get("parent"), insectCategory), criteriaBuilder.like(root.get("parent").<String> get("treePath"), "%" + TCatalogIndex.TREE_PATH_SEPARATOR + insectCategory.getId() + TCatalogIndex.TREE_PATH_SEPARATOR + "%")));
		}
		/*if (brand != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("brand"), brand));
		}
		if (promotion != null) {
			Subquery<Product> subquery1 = criteriaQuery.subquery(Product.class);
			Root<Product> subqueryRoot1 = subquery1.from(Product.class);
			subquery1.select(subqueryRoot1);
			subquery1.where(criteriaBuilder.equal(subqueryRoot1, root), criteriaBuilder.equal(subqueryRoot1.join("promotions"), promotion));

			Subquery<Product> subquery2 = criteriaQuery.subquery(Product.class);
			Root<Product> subqueryRoot2 = subquery2.from(Product.class);
			subquery2.select(subqueryRoot2);
			subquery2.where(criteriaBuilder.equal(subqueryRoot2, root), criteriaBuilder.equal(subqueryRoot2.join("productCategory").join("promotions"), promotion));

			Subquery<Product> subquery3 = criteriaQuery.subquery(Product.class);
			Root<Product> subqueryRoot3 = subquery3.from(Product.class);
			subquery3.select(subqueryRoot3);
			subquery3.where(criteriaBuilder.equal(subqueryRoot3, root), criteriaBuilder.equal(subqueryRoot3.join("brand").join("promotions"), promotion));

			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.exists(subquery1), criteriaBuilder.exists(subquery2), criteriaBuilder.exists(subquery3)));
		}*/
		
		
		if (tags != null && !tags.isEmpty()) {
			Subquery<TCatalogIndex> subquery = criteriaQuery.subquery(TCatalogIndex.class);
			Root<TCatalogIndex> subqueryRoot = subquery.from(TCatalogIndex.class);
			subquery.select(subqueryRoot);
			subquery.where(criteriaBuilder.equal(subqueryRoot, root), subqueryRoot.join("tags").in(tags));
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.exists(subquery));
		}
		if (attributeValue != null) {
			for (Entry<Attribute, String> entry : attributeValue.entrySet()) {
				String propertyName = TCatalogIndex.ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + entry.getKey().getPropertyIndex();
				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get(propertyName), entry.getValue()));
			}
		}
		
		/*if (isTop != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isTop"), isTop));
		}*/
		
		/*if (isGift != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isGift"), isGift));
		}
		Path<Integer> stock = root.get("stock");
		Path<Integer> allocatedStock = root.get("allocatedStock");
		if (isOutOfStock != null) {
			if (isOutOfStock) {
				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.isNotNull(stock), criteriaBuilder.lessThanOrEqualTo(stock, allocatedStock));
			} else {
				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.isNull(stock), criteriaBuilder.greaterThan(stock, allocatedStock)));
			}
		}
		if (isStockAlert != null) {
			Setting setting = SettingUtils.get();
			if (isStockAlert) {
				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.isNotNull(stock), criteriaBuilder.lessThanOrEqualTo(stock, criteriaBuilder.sum(allocatedStock, setting.getStockAlertCount())));
			} else {
				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.isNull(stock), criteriaBuilder.greaterThan(stock, criteriaBuilder.sum(allocatedStock, setting.getStockAlertCount()))));
			}
		}*/
		criteriaQuery.where(restrictions);
		
		if (orderType == OrderType.dateDesc) {
			orders.add(Order.desc("createDate"));
		}else if (orderType == OrderType.monthHitsDesc) {
			orders.add(Order.desc("monthHits"));
			orders.add(Order.desc("createDate"));
		}else{ // (orderType == OrderType.topDesc)
			orders.add(Order.desc("isTop"));
			orders.add(Order.desc("createDate"));
		}
		return super.findList(criteriaQuery, null, count, filters, orders);
	}

	/**
	 * 设置treePath、grade并保存
	 * 
	 * @param articleCategory
	 *            害虫分类
	 */
	@Override
	public void persist(TCatalogIndex catalogindex) {
		Assert.notNull(catalogindex);
		setValue(catalogindex);
		super.persist(catalogindex);
	}

	/**
	 * 设置treePath、grade并更新
	 * 
	 * @param articleCategory
	 *            害虫分类
	 * @return 害虫分类
	 */
	@Override
	public TCatalogIndex merge(TCatalogIndex catalogindex) {
		Assert.notNull(catalogindex);
		setValue(catalogindex);
		for (TCatalogIndex category : findChildren(catalogindex, null)) {
			setValue(category);
		}
		return super.merge(catalogindex);
	}
	
	
	@Override
	public boolean snExists(String sn) {
		if (sn == null) {
			return false;
		}
		String jpql = "select count(*) from TCatalogIndex catalog where lower(catalog.bm) = lower(:sn)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT)
				.setParameter("sn", sn).getSingleResult();
		return count > 0;
	}

	@Override
	public boolean mcExists(String mc) {
		if (mc == null) {
			return false;
		}
		String jpql = "select count(*) from TCatalogIndex catalog where lower(catalog.mc) = lower(:mc)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT)
				.setParameter("mc", mc).getSingleResult();
		return count > 0;
	}

	@Override
	public boolean ywmExists(String ywm) {
		if (ywm == null) {
			return false;
		}
		String jpql = "select count(*) from TCatalogIndex catalog where lower(catalog.ywm) = lower(:ywm)";
		Long count = entityManager.createQuery(jpql, Long.class).setFlushMode(FlushModeType.COMMIT)
				.setParameter("ywm", ywm).getSingleResult();
		return count > 0;
	}
	
	/**
	 * 排序害虫分类
	 * 
	 * @param catalogindexs
	 *            害虫分类
	 * @param parent
	 *            上级害虫分类
	 * @return 害虫分类
	 */
	private List<TCatalogIndex> sort(List<TCatalogIndex> catalogindexs, TCatalogIndex parent) {
		List<TCatalogIndex> result = new ArrayList<TCatalogIndex>();
		if (catalogindexs != null) {
			for (TCatalogIndex catalogindex : catalogindexs) {
				if ((catalogindex.getParent() != null && catalogindex.getParent().equals(parent)) 
						|| (catalogindex.getParent() == null && parent == null)) {
					result.add(catalogindex);
					result.addAll(sort(catalogindexs, catalogindex));
				}
			}
		}
		return result;
	}
	
	/**
	 * 设置值
	 * 
	 * @param catalogindex
	 *            害虫分类
	 */
	private void setValue(TCatalogIndex catalogindex) {
		if (catalogindex == null) {
			return;
		}
		TCatalogIndex parent = catalogindex.getParent();
		if (parent != null) {
			catalogindex.setTreePath(parent.getTreePath() + parent.getId() + ArticleCategory.TREE_PATH_SEPARATOR);
		} else {
			catalogindex.setTreePath(ArticleCategory.TREE_PATH_SEPARATOR);
		}
		catalogindex.setGrade(catalogindex.getTreePaths().size());
	}
	
	//mark add
//	public Page<TCatalogIndex> findPage(List<TCatalogIndex> productCategory, List<Tag> tags, Map<Attribute, String> attributeValue, Boolean isTop,OrderType orderType, Pageable pageable){
//		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//		CriteriaQuery<TCatalogIndex> criteriaQuery = criteriaBuilder.createQuery(TCatalogIndex.class);
//		Root<TCatalogIndex> root = criteriaQuery.from(TCatalogIndex.class);
//		criteriaQuery.select(root);
//		Predicate restrictions = criteriaBuilder.conjunction();
//		if (productCategory != null) {
////			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.equal(root.get("parent"), productCategory), criteriaBuilder.like(root.get("parent").<String> get("treePath"), "%" + TCatalogIndex.TREE_PATH_SEPARATOR + productCategory.getId() + TCatalogIndex.TREE_PATH_SEPARATOR + "%")));
//			// zylb重要类别
//			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.equal(root.get("zylb"), productCategory.getZylb()), criteriaBuilder.like(root.get("parent").<String> get("treePath"), "%" + TCatalogIndex.TREE_PATH_SEPARATOR + productCategory.getId() + TCatalogIndex.TREE_PATH_SEPARATOR + "%")));
//		}
///*		if (brand != null) {
//			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("brand"), brand));
//		}
//		if (promotion != null) {
//			Subquery<Product> subquery1 = criteriaQuery.subquery(Product.class);
//			Root<Product> subqueryRoot1 = subquery1.from(Product.class);
//			subquery1.select(subqueryRoot1);
//			subquery1.where(criteriaBuilder.equal(subqueryRoot1, root), criteriaBuilder.equal(subqueryRoot1.join("promotions"), promotion));
//
//			Subquery<Product> subquery2 = criteriaQuery.subquery(Product.class);
//			Root<Product> subqueryRoot2 = subquery2.from(Product.class);
//			subquery2.select(subqueryRoot2);
//			subquery2.where(criteriaBuilder.equal(subqueryRoot2, root), criteriaBuilder.equal(subqueryRoot2.join("productCategory").join("promotions"), promotion));
//
//			Subquery<Product> subquery3 = criteriaQuery.subquery(Product.class);
//			Root<Product> subqueryRoot3 = subquery3.from(Product.class);
//			subquery3.select(subqueryRoot3);
//			subquery3.where(criteriaBuilder.equal(subqueryRoot3, root), criteriaBuilder.equal(subqueryRoot3.join("brand").join("promotions"), promotion));
//
//			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.exists(subquery1), criteriaBuilder.exists(subquery2), criteriaBuilder.exists(subquery3)));
//		}*/
//		if (tags != null && !tags.isEmpty()) {
//			Subquery<TCatalogIndex> subquery = criteriaQuery.subquery(TCatalogIndex.class);
//			Root<TCatalogIndex> subqueryRoot = subquery.from(TCatalogIndex.class);
//			subquery.select(subqueryRoot);
//			subquery.where(criteriaBuilder.equal(subqueryRoot, root), subqueryRoot.join("tags").in(tags));
//			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.exists(subquery));
//		}
//		if (attributeValue != null) {
//			for (Entry<Attribute, String> entry : attributeValue.entrySet()) {
//				String propertyName = TCatalogIndex.ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + entry.getKey().getPropertyIndex();
//				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get(propertyName), entry.getValue()));
//			}
//		}
//		/*if (startPrice != null && endPrice != null && startPrice.compareTo(endPrice) > 0) {
//			BigDecimal temp = startPrice;
//			startPrice = endPrice;
//			endPrice = temp;
//		}
//		if (startPrice != null && startPrice.compareTo(new BigDecimal(0)) >= 0) {
//			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.ge(root.<Number> get("price"), startPrice));
//		}
//		if (endPrice != null && endPrice.compareTo(new BigDecimal(0)) >= 0) {
//			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.le(root.<Number> get("price"), endPrice));
//		}*/
//		/*if (isMarketable != null) {
//			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isMarketable"), isMarketable));
//		}*/
//		/*if (isList != null) {
//			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isList"), isList));
//		}*/
//		if (isTop != null) {
//			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isTop"), isTop));
//		}
//		/*if (isGift != null) {
//			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isGift"), isGift));
//		}*/
//		/*Path<Integer> stock = root.get("stock");
//		Path<Integer> allocatedStock = root.get("allocatedStock");
//		if (isOutOfStock != null) {
//			if (isOutOfStock) {
//				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.isNotNull(stock), criteriaBuilder.lessThanOrEqualTo(stock, allocatedStock));
//			} else {
//				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.isNull(stock), criteriaBuilder.greaterThan(stock, allocatedStock)));
//			}
//		}
//		if (isStockAlert != null) {
//			Setting setting = SettingUtils.get();
//			if (isStockAlert) {
//				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.isNotNull(stock), criteriaBuilder.lessThanOrEqualTo(stock, criteriaBuilder.sum(allocatedStock, setting.getStockAlertCount())));
//			} else {
//				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.isNull(stock), criteriaBuilder.greaterThan(stock, criteriaBuilder.sum(allocatedStock, setting.getStockAlertCount()))));
//			}
//		}*/
//		criteriaQuery.where(restrictions);
//		List<Order> orders = pageable.getOrders();
//		/*if (orderType == OrderType.priceAsc) {
//			orders.add(Order.asc("price"));
//			orders.add(Order.desc("createDate"));
//		} else if (orderType == OrderType.priceDesc) {
//			orders.add(Order.desc("price"));
//			orders.add(Order.desc("createDate"));
//		} else if (orderType == OrderType.salesDesc) {
//			orders.add(Order.desc("sales"));
//			orders.add(Order.desc("createDate"));
//		} else if (orderType == OrderType.scoreDesc) {
//			orders.add(Order.desc("score"));
//			orders.add(Order.desc("createDate"));
//		} else */if (orderType == OrderType.dateDesc) {
//			orders.add(Order.desc("createDate"));
//		} else {
//			orders.add(Order.desc("isTop"));
//			orders.add(Order.desc("modifyDate"));
//		}
//		return super.findPage(criteriaQuery, pageable);
//	}
	
	//mark add
	public Page<TCatalogIndex> findPage(TCatalogIndex productCategory, List<Tag> tags, Map<Attribute, String> attributeValue, Boolean isTop,OrderType orderType, Pageable pageable){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TCatalogIndex> criteriaQuery = criteriaBuilder.createQuery(TCatalogIndex.class);
		Root<TCatalogIndex> root = criteriaQuery.from(TCatalogIndex.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		if (productCategory != null) {
//				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.equal(root.get("parent"), productCategory), criteriaBuilder.like(root.get("parent").<String> get("treePath"), "%" + TCatalogIndex.TREE_PATH_SEPARATOR + productCategory.getId() + TCatalogIndex.TREE_PATH_SEPARATOR + "%")));
			// zylb重要类别
//			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.equal(root.get("zylb"), productCategory.getZylb()), criteriaBuilder.like(root.get("parent").<String> get("treePath"), "%" + TCatalogIndex.TREE_PATH_SEPARATOR + productCategory.getId() + TCatalogIndex.TREE_PATH_SEPARATOR + "%")));
			
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("parent"), productCategory));
		}
/*		if (brand != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("brand"), brand));
		}
		if (promotion != null) {
			Subquery<Product> subquery1 = criteriaQuery.subquery(Product.class);
			Root<Product> subqueryRoot1 = subquery1.from(Product.class);
			subquery1.select(subqueryRoot1);
			subquery1.where(criteriaBuilder.equal(subqueryRoot1, root), criteriaBuilder.equal(subqueryRoot1.join("promotions"), promotion));

			Subquery<Product> subquery2 = criteriaQuery.subquery(Product.class);
			Root<Product> subqueryRoot2 = subquery2.from(Product.class);
			subquery2.select(subqueryRoot2);
			subquery2.where(criteriaBuilder.equal(subqueryRoot2, root), criteriaBuilder.equal(subqueryRoot2.join("productCategory").join("promotions"), promotion));

			Subquery<Product> subquery3 = criteriaQuery.subquery(Product.class);
			Root<Product> subqueryRoot3 = subquery3.from(Product.class);
			subquery3.select(subqueryRoot3);
			subquery3.where(criteriaBuilder.equal(subqueryRoot3, root), criteriaBuilder.equal(subqueryRoot3.join("brand").join("promotions"), promotion));

			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.or(criteriaBuilder.exists(subquery1), criteriaBuilder.exists(subquery2), criteriaBuilder.exists(subquery3)));
		}*/
		if (tags != null && !tags.isEmpty()) {
			Subquery<TCatalogIndex> subquery = criteriaQuery.subquery(TCatalogIndex.class);
			Root<TCatalogIndex> subqueryRoot = subquery.from(TCatalogIndex.class);
			subquery.select(subqueryRoot);
			subquery.where(criteriaBuilder.equal(subqueryRoot, root), subqueryRoot.join("tags").in(tags));
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.exists(subquery));
		}
		if (attributeValue != null) {
			for (Entry<Attribute, String> entry : attributeValue.entrySet()) {
				String propertyName = TCatalogIndex.ATTRIBUTE_VALUE_PROPERTY_NAME_PREFIX + entry.getKey().getPropertyIndex();
				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get(propertyName), entry.getValue()));
			}
		}
		/*if (isTop != null) {
			restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.equal(root.get("isTop"), isTop));
		}*/
		criteriaQuery.where(restrictions);
		List<Order> orders = pageable.getOrders();
		if (orderType == OrderType.dateDesc) {
			orders.add(Order.desc("createDate"));
		} else {
			orders.add(Order.desc("isTop"));
			orders.add(Order.desc("modifyDate"));
		}
		return super.findPage(criteriaQuery, pageable);
	}
		
	//mark add
	public Page<TCatalogIndex> findPage(Pageable pageable) {
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TCatalogIndex> criteriaQuery = criteriaBuilder.createQuery(TCatalogIndex.class);
		Root<TCatalogIndex> root = criteriaQuery.from(TCatalogIndex.class);
		criteriaQuery.select(root);
		return super.findPage(criteriaQuery, pageable);
	}

	@Override
	public List<TCatalogIndex> findByCatalogIndexName(String catalogIndexName, List<Tag> tags, Map<Attribute, String> attributeValue, Boolean isTop,OrderType orderType, Integer count, List<Filter> filters, List<Order> orders) {
		if (catalogIndexName == null) {
			return null;
		}
		try {
			String jpql = "select catalog from TCatalogIndex catalog where lower(catalog.zylb) = lower(:zylb)";
			List<TCatalogIndex> list = entityManager.createQuery(jpql, TCatalogIndex.class).setFlushMode(FlushModeType.COMMIT).setParameter("zylb", catalogIndexName).getResultList();
			return list;
//			return entityManager.createQuery(jpql, TCatalogIndex.class).setFlushMode(FlushModeType.COMMIT).setParameter("zylb", catalogIndexName).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}	
	
	public List<TCatalogIndex> findByCatalogIndexName(String catalogIndexName, Integer count){
		
		TypedQuery<TCatalogIndex> query;
		if (catalogIndexName != null) {
			String jpql = "select catalog from TCatalogIndex catalog where lower(catalog.zylb) = lower(:zylb) order by catalog.id asc";
			query = entityManager.createQuery(jpql, TCatalogIndex.class).setFlushMode(FlushModeType.COMMIT)
					.setParameter("zylb", catalogIndexName);
		} else {
			String jpql = "select catalog from TCatalogIndex catalog order by catalog.id asc";
			query = entityManager.createQuery(jpql, TCatalogIndex.class).setFlushMode(FlushModeType.COMMIT);
		}
		if (count != null) {
			query.setMaxResults(count);
		}
		return query.getResultList();
	}
	
	public Page<TCatalogIndex> findCatalogIndexByKeyword(String keyword, Pageable pageable){
		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<TCatalogIndex> criteriaQuery = criteriaBuilder.createQuery(TCatalogIndex.class);
		Root<TCatalogIndex> root = criteriaQuery.from(TCatalogIndex.class);
		criteriaQuery.select(root);
		Predicate restrictions = criteriaBuilder.conjunction();
		restrictions = criteriaBuilder.and(restrictions, criteriaBuilder.like(root.get("mc").as(String.class), "%" + keyword + "%"));
		criteriaQuery.where(restrictions);
		return findPage(criteriaQuery, pageable);
	}

}
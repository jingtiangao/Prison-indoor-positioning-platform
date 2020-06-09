package com.grain.dao.impl;



import org.springframework.stereotype.Repository;

import com.grain.dao.AttributeDao;
import com.grain.entity.Attribute;

/**
 * Dao - 属性
 * 
 */
@Repository("attributeDaoImpl")
public class AttributeDaoImpl extends BaseDaoImpl<Attribute, Long> implements AttributeDao {

	/**
	 * 设置propertyIndex并保存
	 * 
	 * @param attribute
	 *            属性
	 */
	@Override
	public void persist(Attribute attribute) {

	}

	/**
	 * 清除商品属性值并删除
	 * 
	 * @param attribute
	 *            属性
	 */
	@Override
	public void remove(Attribute attribute) {
		
	}

}
package com.grain.dao;

import java.util.List;

import com.grain.entity.Tag;
import com.grain.entity.Tag.Type;

/**
 * Dao - 标签
 * 
 */
public interface TagDao extends BaseDao<Tag, Long> {

	/**
	 * 查找标签
	 * 
	 * @param type
	 *            类型
	 * @return 标签
	 */
	List<Tag> findList(Type type);

}
package com.grain.dao;

import com.grain.entity.InsectOnfield;

/**
 * Dao - 野外害虫采集
 * @author Mark
 *
 */
public interface FieldCollectionDao extends BaseDao<InsectOnfield, Long>{

	boolean fieldCollectionExists(String id);

	InsectOnfield findById(String id);

}

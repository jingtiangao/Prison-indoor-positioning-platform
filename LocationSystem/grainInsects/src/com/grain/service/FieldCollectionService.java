package com.grain.service;

import com.grain.entity.InsectOnfield;

/**
 * Service - 野外害虫采集
 * @author Mark
 * 
 */
public interface FieldCollectionService extends BaseService<InsectOnfield, Long> {

	boolean fieldCollectionExists(String id);

	InsectOnfield findById(String id);

}
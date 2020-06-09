package com.grain.dao;

import com.grain.entity.TInsectsIdentify;

/**
 * Dao - 昆虫鉴定表
 * @author Mark
 *
 */
public interface TInsectsIdentifyDao extends BaseDao<TInsectsIdentify, String>{

	boolean insectsIdentifyExists(String sm);

	TInsectsIdentify findBySM(String sm);

}

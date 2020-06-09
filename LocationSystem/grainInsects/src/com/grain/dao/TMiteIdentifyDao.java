package com.grain.dao;

import com.grain.entity.TMiteidentify;

/**
 * Dao - 3.2 螨鉴定表
 * @author Mark
 *
 */
public interface TMiteIdentifyDao extends BaseDao<TMiteidentify, String>{

	boolean miteIdentifyExists(String sm);

	TMiteidentify findBySM(String sm);

}

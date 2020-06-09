package com.grain.service;

import com.grain.entity.TMiteidentify;

/**
 * Service - 3.2 螨鉴定表
 * @author Mark
 * 
 */
public interface TMiteIdentifyService extends BaseService<TMiteidentify, String> {

	boolean miteIdentifyExists(String sm);

	TMiteidentify findBySM(String sm);

}
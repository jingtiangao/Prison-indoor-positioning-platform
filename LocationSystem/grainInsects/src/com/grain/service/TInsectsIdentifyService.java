package com.grain.service;

import com.grain.entity.TInsectsIdentify;

/**
 * Service - 昆虫鉴定表
 * @author Mark
 * 
 */
public interface TInsectsIdentifyService extends BaseService<TInsectsIdentify, String> {

	boolean insectsIdentifyExists(String sm);

	TInsectsIdentify findBySM(String sm);

}
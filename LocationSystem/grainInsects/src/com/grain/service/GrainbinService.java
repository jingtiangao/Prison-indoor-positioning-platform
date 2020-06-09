package com.grain.service;

import com.grain.entity.Grainbin;


/**
 * Service - 粮仓
 * 
 */
public interface GrainbinService extends BaseService<Grainbin, String> {

	void save(Grainbin bin, String[] usernames);

}
package com.grain.service;

import com.grain.entity.Grain;


/**
 * Service - 粮食
 * 
 */
public interface GrainService extends BaseService<Grain, Long> {

	Grain findLast(String lcbm);

}
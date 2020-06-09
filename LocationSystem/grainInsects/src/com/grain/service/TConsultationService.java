package com.grain.service;


import com.grain.entity.TConsultation;

/**
 * Service - 咨询
 * 
 */
public interface TConsultationService extends BaseService<TConsultation, Long> {
	
	public String findExpert();

}

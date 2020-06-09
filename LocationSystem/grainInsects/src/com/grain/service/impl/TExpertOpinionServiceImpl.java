package com.grain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grain.dao.TExpertOpinionDao;
import com.grain.entity.TExpertOpinion;
import com.grain.service.TExpertOpinionService;


/**
 * Service - 专家意见
 * 
 */
@Service("tExpertOpinionServiceImpl")
public class TExpertOpinionServiceImpl extends BaseServiceImpl<TExpertOpinion, Long> implements TExpertOpinionService {
	@Resource(name = "tExpertOpinionDaoImpl")
	private TExpertOpinionDao tExpertOpinionDao;

	@Resource(name = "tExpertOpinionDaoImpl")
	public void setBaseDao(TExpertOpinionDao tExpertOpinionDao) {
		super.setBaseDao(tExpertOpinionDao);
	}
}

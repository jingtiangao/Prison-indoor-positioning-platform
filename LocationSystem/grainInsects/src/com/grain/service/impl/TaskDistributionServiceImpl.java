package com.grain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grain.dao.TaskDistributionDao;
import com.grain.entity.TaskDistribution;
import com.grain.service.TaskDistributionService;

/**
 * Service - 分类、检索 任务分配
 * 
 */
@Service("taskDistributionServiceImpl")
public class TaskDistributionServiceImpl extends BaseServiceImpl<TaskDistribution, Long> implements TaskDistributionService {
	@Resource(name = "taskDistributionDaoImpl")
	TaskDistributionDao dao;
	
	@Resource(name = "taskDistributionDaoImpl")
	public void setBaseDao(TaskDistributionDao dao){
		super.setBaseDao(dao);
	}
}
package com.grain.dao.impl;

import org.springframework.stereotype.Repository;

import com.grain.dao.TaskDistributionDao;
import com.grain.entity.TaskDistribution;

/**
 * Dao - 分类、检索 任务分配
 * 
 */
@Repository("taskDistributionDaoImpl")
public class TaskDistributionDaoImpl extends BaseDaoImpl<TaskDistribution, Long> implements TaskDistributionDao {
}
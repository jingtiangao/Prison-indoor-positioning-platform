package com.grain.dao;

import java.util.List;

import com.grain.entity.InfoReport;


/**
 * Dao - 粮仓信息上报
 * 
 */
public interface InfoReportDao extends BaseDao<InfoReport, Long> {
	
	/**
	 * 查找某一用户下的所有上报信息
	 * 
	 * @param count
	 *            数量
	 * @param username
	 *            用户名
	 * @return 全部上报信息
	 */
	List<InfoReport> findInfoReportWithUsername(Integer count, String username);

}

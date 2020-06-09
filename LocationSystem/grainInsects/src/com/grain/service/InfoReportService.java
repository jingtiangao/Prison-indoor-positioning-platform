package com.grain.service;

import java.util.List;

import com.grain.entity.InfoReport;


/**
 * Service - 粮仓信息上报
 * 
 */
public interface InfoReportService extends BaseService<InfoReport, Long> {

	/**
	 * 查找某一用户下的所有上报信息
	 * 
	 * @param count
	 *            数量
	 * @param username
	 *            用户名
	 * @return 全部上报信息
	 */
	List<InfoReport> findInfoReportWithUsername(String username);
}

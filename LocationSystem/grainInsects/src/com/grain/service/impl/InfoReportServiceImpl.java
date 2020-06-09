package com.grain.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grain.dao.InfoReportDao;
import com.grain.entity.InfoReport;
import com.grain.service.InfoReportService;


/**
 * Service - 粮仓防治工艺上报
 * 
 */
@Service("infoReportServiceImpl")
public class InfoReportServiceImpl extends BaseServiceImpl<InfoReport, Long> implements InfoReportService {

	@Resource(name = "infoReportDaoImpl")
	private InfoReportDao infoReportDao;

	@Resource(name = "infoReportDaoImpl")
	public void setBaseDao(InfoReportDao infoReportDao) {
		super.setBaseDao(infoReportDao);
	}
	
	@Transactional(readOnly = true)
	public List<InfoReport> findInfoReportWithUsername(String username) {
		return infoReportDao.findInfoReportWithUsername(null, username);
	}
}

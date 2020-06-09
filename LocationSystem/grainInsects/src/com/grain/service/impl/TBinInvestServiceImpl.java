package com.grain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grain.dao.TBinInvestDao;
import com.grain.entity.TBinInvest;
import com.grain.service.TBinInvestService;

/*
 * 害虫分仓调查表
 */
@Service("tbinInvestServiceImpl")
public class TBinInvestServiceImpl 
	extends BaseServiceImpl<TBinInvest,Long>
	implements TBinInvestService{
	
	@Resource(name = "tbinInvestDaoImpl")
	TBinInvestDao dao;
	
	@Resource(name = "tbinInvestDaoImpl")
	public void setBaseDao(TBinInvestDao dao){
		super.setBaseDao(dao);
		
	}

}
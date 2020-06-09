package com.grain.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.grain.dao.TDepotInvestDao;
import com.grain.entity.TDepotInvest;
import com.grain.service.TDepotInvestService;
/*
 * 害虫调查总表 
 */
@Service("tdepotInvestServiceImpl")
public class TDepotInvestServiceImpl 
	extends BaseServiceImpl<TDepotInvest,Long>
	implements TDepotInvestService{
	
	@Resource(name = "tdepotInvestDaoImpl")
	TDepotInvestDao dao;
	
	@Resource(name = "tdepotInvestDaoImpl")
	public void setBaseDao(TDepotInvestDao dao){
		super.setBaseDao(dao);
		
	}

}

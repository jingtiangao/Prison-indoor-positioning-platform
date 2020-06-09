package com.grain.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.grain.dao.TBinCleankillDao;
import com.grain.entity.TBinCleankill;
import com.grain.service.TBinCleankillService;

@Service("tBinCleankillServiceImpl")
public class TBinCleankillServiceImpl 
	extends BaseServiceImpl<TBinCleankill, Long>
	implements TBinCleankillService{
	
	@Resource(name = "tBinCleankillDaoImpl")
	TBinCleankillDao dao;
	
	@Resource(name = "tBinCleankillDaoImpl")
	public void setBaseDao(TBinCleankillDao dao){
		super.setBaseDao(dao);		
	}
}

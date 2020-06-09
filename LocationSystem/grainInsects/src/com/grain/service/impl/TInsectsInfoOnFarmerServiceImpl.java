package com.grain.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.grain.dao.TInsectsInfoOnFarmerDao;
import com.grain.entity.TInsectsInfoOnFarmer;
import com.grain.service.TInsectsInfoOnFarmerService;

@Service("tInsectsInfoOnFarmerServiceImpl")
public class TInsectsInfoOnFarmerServiceImpl 
	extends BaseServiceImpl<TInsectsInfoOnFarmer,Long>
	implements TInsectsInfoOnFarmerService{
	
	@Resource(name = "tInsectsInfoOnFarmerDaoImpl")
	TInsectsInfoOnFarmerDao dao;
	
	@Resource(name = "tInsectsInfoOnFarmerDaoImpl")
	public void setBaseDao(TInsectsInfoOnFarmerDao dao){
		super.setBaseDao(dao);
		
	}

}

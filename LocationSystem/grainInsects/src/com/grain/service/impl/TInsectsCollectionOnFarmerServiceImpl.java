package com.grain.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.grain.dao.TInsectsCollectionOnFarmerDao;
import com.grain.entity.TInsectsCollectionOnFarmer;
import com.grain.service.TInsectsCollectionOnFarmerService;
/*
 * 农户储粮害虫现场采集记录表
 */
@Service("tInsectsCollectionOnFarmerServiceImpl")
public class TInsectsCollectionOnFarmerServiceImpl 
	extends BaseServiceImpl<TInsectsCollectionOnFarmer,Long>
	implements TInsectsCollectionOnFarmerService{
	
	@Resource(name = "tInsectsCollectionOnFarmerDaoImpl")
	TInsectsCollectionOnFarmerDao dao;
	
	@Resource(name = "tInsectsCollectionOnFarmerDaoImpl")
	public void setBaseDao(TInsectsCollectionOnFarmerDao dao){
		super.setBaseDao(dao);
		
	}

}

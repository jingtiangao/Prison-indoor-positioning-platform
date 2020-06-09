package com.location.service.impl.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grain.service.impl.BaseServiceImpl;
import com.location.dao.user.RegionDao;
import com.location.entity.Region;
import com.location.service.user.RegionService;
@Service("regionServiceImpl")
public class RegionServiceImpl extends BaseServiceImpl<Region, String> implements RegionService {

	@Resource(name="regionDaoImpl")
	private RegionDao regionDao;
	
	@Resource(name="regionDaoImpl")
	public void setBaseDao(RegionDao regionDao) {
		super.setBaseDao(regionDao);
	};
	@Override
	@Transactional(readOnly = true)
	public Region findByName(String regionName) {
		// TODO Auto-generated method stub
		return regionDao.findByName(regionName);
	}

}

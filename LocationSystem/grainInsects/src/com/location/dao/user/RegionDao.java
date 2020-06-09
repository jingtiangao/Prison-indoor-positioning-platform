package com.location.dao.user;

import com.grain.dao.BaseDao;
import com.location.entity.Region;

public interface RegionDao extends BaseDao<Region, String> {
	public Region findByName(String regionName);

}

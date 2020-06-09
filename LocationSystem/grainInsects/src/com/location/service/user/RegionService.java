package com.location.service.user;

import com.grain.service.BaseService;
import com.location.entity.Region;

public interface RegionService extends BaseService<Region, String> {
Region findByName(String regionName);
}

package com.location.service.user;

import com.grain.service.BaseService;
import com.location.entity.LsArea;

public interface LsAreaService extends BaseService<LsArea, String> {
	LsArea findByName(String areaName);
}

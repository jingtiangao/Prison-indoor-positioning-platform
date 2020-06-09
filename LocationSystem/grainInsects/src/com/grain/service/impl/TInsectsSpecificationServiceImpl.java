package com.grain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grain.dao.TInsectsSpecificationDao;
import com.grain.entity.TInsectsSpecification;
import com.grain.service.TInsectsSpecificationService;


@Service("tInsectsSpecificationServiceImpl")
public class TInsectsSpecificationServiceImpl extends BaseServiceImpl<TInsectsSpecification, Long> implements TInsectsSpecificationService {

	@Resource(name = "tInsectsSpecificationDaoImpl")
	private TInsectsSpecificationDao tInsectsSpecificationDao;

	@Resource(name = "tInsectsSpecificationDaoImpl")
	public void setBaseDao(TInsectsSpecificationDao tInsectsSpecificationDao) {
		super.setBaseDao(tInsectsSpecificationDao);
	}
}

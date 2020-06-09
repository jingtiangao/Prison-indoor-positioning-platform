package com.location.service.impl.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grain.service.impl.BaseServiceImpl;
import com.location.dao.user.LsAreaDao;
import com.location.entity.LsArea;
import com.location.service.user.LsAreaService;
@Service("lsAreaServiceImpl")
public class LsAreaServiceImpl extends BaseServiceImpl<LsArea, String> implements LsAreaService {

	@Resource(name="lsAreaDaoImpl")
	private LsAreaDao areadao;
	@Resource(name="lsAreaDaoImpl")
	public void setBaseDao(LsAreaDao areaDao) {
		super.setBaseDao(areaDao);
	}
	@Override
	@Transactional(readOnly = true)
	public LsArea findByName(String areaName) {
		// TODO Auto-generated method stub
		return areadao.findByName(areaName);
	}

}

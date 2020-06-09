package com.grain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grain.dao.RealdataPicDao;
import com.grain.entity.RealdataPic;
import com.grain.service.RealdataPicService;

@Service("realdataPicServiceImpl")
public class RealdataPicServiceImpl 
	extends BaseServiceImpl<RealdataPic,Long>
	implements RealdataPicService{
	
	@Resource(name = "realdataPicDaoImpl")
	RealdataPicDao realPicdao;
	
	@Resource(name = "realdataPicDaoImpl")
	public void setBaseDao(RealdataPicDao realPicdao){
		super.setBaseDao(realPicdao);
		
	}
}

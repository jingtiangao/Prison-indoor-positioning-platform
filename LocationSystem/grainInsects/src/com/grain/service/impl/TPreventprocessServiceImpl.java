package com.grain.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.grain.dao.TPreventprocessDao;
import com.grain.entity.TPreventprocess;
import com.grain.service.TPreventprocessService;


@Service("tpreventprocessServiceImpl")
public class TPreventprocessServiceImpl extends BaseServiceImpl<TPreventprocess,String>
implements TPreventprocessService{
	
	@Resource(name = "tpreventprocessDaoImpl")
	TPreventprocessDao dao;
	
	@Resource(name = "tpreventprocessDaoImpl")
	public void setBaseDao(TPreventprocessDao dao){
		super.setBaseDao(dao);
		
	}
}

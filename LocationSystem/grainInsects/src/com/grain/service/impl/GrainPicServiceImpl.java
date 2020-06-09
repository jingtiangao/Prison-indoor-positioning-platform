package com.grain.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.grain.dao.GrainPicDao;
import com.grain.entity.GrainPic;
import com.grain.service.GrainPicService;
/*
 * 粮食图片信息 t_grain_pic
 */
@Service("grainPicServiceImpl")
public class GrainPicServiceImpl 
	extends BaseServiceImpl<GrainPic,Long>
	implements GrainPicService{
	
	
	@Resource(name = "grainPicDaoImpl")
	public void setBaseDao(GrainPicDao dao){
		super.setBaseDao(dao);
		
	}
	
}

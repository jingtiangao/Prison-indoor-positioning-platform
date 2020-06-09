package com.grain.service.impl;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grain.dao.TDigitalFeatureDao;
import com.grain.dao.user.DepotUserDao;
import com.grain.entity.TDigitalFeature;


import com.grain.service.TDigitalFeatureService;

@Service("tdigitalfeatureServiceImpl")
public class TDigitalFeatureServiceImpl extends BaseServiceImpl<TDigitalFeature,Long>
implements TDigitalFeatureService {

	@Resource(name = "tdigitalfeatureDaoImpl")
	TDigitalFeatureDao dao;
	
	
	@Resource(name = "tdigitalfeatureDaoImpl")
	public void setBaseDao(TDigitalFeatureDao dao){
		super.setBaseDao(dao);
		
	}
	
	public TDigitalFeature update(TDigitalFeature bin,String... ignoreProperties ){
		boolean hasuserupdate=true;
		for (String u: ignoreProperties){
			if (u.equalsIgnoreCase("depotusers")){
				hasuserupdate =false;
				break;
			}
		}
		
		return super.update(bin,ignoreProperties);
	}
	
	public void save(TDigitalFeature bin,String[] usernames){
		Set<String> userset = new HashSet<String>();
		//去掉重复的用户名
		if (usernames!=null&& usernames.length>0)
			for (int i=0;i<usernames.length;i++)
				userset.add(usernames[i]);
		
		
		for(String id: userset){
			List<TDigitalFeature> bins = new ArrayList<TDigitalFeature>();
			
		}
		super.save(bin);
	}
}

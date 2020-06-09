package com.grain.service.impl;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grain.dao.GrainbinDao;
import com.grain.dao.user.DepotUserDao;
import com.grain.entity.Grainbin;
import com.grain.entity.user.DepotUser;
import com.grain.service.GrainbinService;

@Service("grainBinServiceImpl")
public class GrainBinServiceImpl 
	extends BaseServiceImpl<Grainbin,String>
	implements GrainbinService{
	
	@Resource(name = "grainbinDaoImpl")
	GrainbinDao dao;
	
	@Resource(name = "depotUserDaoImpl")
	DepotUserDao userdao;
	
	@Resource(name = "grainbinDaoImpl")
	public void setBaseDao(GrainbinDao dao){
		super.setBaseDao(dao);
		
	}
	
	public Grainbin update(Grainbin bin,String... ignoreProperties ){
		boolean hasuserupdate=true;
		for (String u: ignoreProperties){
			if (u.equalsIgnoreCase("depotusers")){
				hasuserupdate =false;
				break;
			}
		}
		if (hasuserupdate){
			Set<DepotUser> users=dao.find(bin.getLcbm()).getDepotusers();
			users.removeAll(bin.getDepotusers());
			for (DepotUser d: users){		// remove user
				d.getGrainbins().remove(dao.find(bin.getLcbm()));
				userdao.merge(d);
			}
			for (DepotUser x: bin.getDepotusers()){	//add user
				x.getGrainbins().add(bin);
				userdao.merge(x);
			}
		}
		return super.update(bin,ignoreProperties);
	}
	
	public void save(Grainbin bin,String[] usernames){
		Set<String> userset = new HashSet<String>();
		//去掉重复的用户名
		if (usernames!=null&& usernames.length>0)
			for (int i=0;i<usernames.length;i++)
				userset.add(usernames[i]);
		
		
		for(String id: userset){
			Set<Grainbin> bins = new HashSet<Grainbin>();
			DepotUser dp = userdao.find(id);
			bins=dp.getGrainbins();				//找回原来的
			bins.add(bin);
			dp.setGrainbins(bins);
			userdao.persist(dp);
		}
		super.save(bin);
	}
}

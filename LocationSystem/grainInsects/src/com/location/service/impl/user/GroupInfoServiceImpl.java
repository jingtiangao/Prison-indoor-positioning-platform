package com.location.service.impl.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grain.service.impl.BaseServiceImpl;
import com.location.dao.user.GroupInfoDao;
import com.location.entity.GroupInfo;
import com.location.service.user.GroupInfoService;

@Service("groupInfoServiceImpl")
public class GroupInfoServiceImpl extends BaseServiceImpl<GroupInfo, String> implements	GroupInfoService {
	
	@Resource(name = "groupInfoDaoImpl")
	private GroupInfoDao groupInfoDao;
	
	@Resource(name = "groupInfoDaoImpl")
	public void setBaseDao(GroupInfoDao groupInfoDao) {
		super.setBaseDao(groupInfoDao);
	}
	
	@Override
	@Transactional(readOnly = true)
	public GroupInfo findByName(String groupName) {
		// TODO Auto-generated method stub
		return groupInfoDao.findByName(groupName);
	}

}

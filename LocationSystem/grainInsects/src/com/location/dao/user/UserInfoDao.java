package com.location.dao.user;

import java.util.List;

import com.grain.dao.BaseDao;
import com.location.entity.UserInfo;

public interface UserInfoDao extends BaseDao<UserInfo,String>{
/*	Integer[] locationTransfer(UserInfo user_info,int scale);
	Map<String, Integer[]> locationsTransfer(Set<UserInfo> sets,int scale);*/
	UserInfo findByDeviceID(int deviceID);
	public List<UserInfo> findAll();
	List<UserInfo> findByGroupID(int groupID);
	List<UserInfo> findByState(int state);
}

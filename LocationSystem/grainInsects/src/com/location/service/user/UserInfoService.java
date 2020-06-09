package com.location.service.user;

import java.util.List;

import com.grain.service.BaseService;
import com.location.entity.UserInfo;

public interface UserInfoService extends BaseService<UserInfo, String> {
		UserInfo findByDeviceID(int deviceID);
		public List<UserInfo> findAll();
		List<UserInfo> findByGroupID(int groupID);
		List<UserInfo> findByState(int state);
}
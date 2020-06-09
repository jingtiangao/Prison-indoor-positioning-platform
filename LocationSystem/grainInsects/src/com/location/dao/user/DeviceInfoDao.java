package com.location.dao.user;

import java.util.List;

import com.grain.dao.BaseDao;
import com.location.entity.DeviceInfo;

public interface DeviceInfoDao extends BaseDao<DeviceInfo, String> {
		DeviceInfo	findByName(String device_mac);
		public List<DeviceInfo> findAll();
	//	DeviceInfo findByPrisonerCode(String prisoner_code);
		DeviceInfo	findByDeviceID(int device_id);
}

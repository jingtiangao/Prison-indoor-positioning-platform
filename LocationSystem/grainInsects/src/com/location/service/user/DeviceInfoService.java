package com.location.service.user;

import java.util.List;

import com.grain.service.BaseService;
import com.location.entity.DeviceInfo;

public interface DeviceInfoService extends BaseService<DeviceInfo, String> {
	DeviceInfo findByName(String deviceMac);
	List<DeviceInfo> findByParam(String choice);
	//DeviceInfo findByPrisonerCode(String prisoner_code);
	DeviceInfo findByDeviceID(int device_id);
}

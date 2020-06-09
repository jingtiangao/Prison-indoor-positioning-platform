package com.location.service.impl.user;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grain.service.impl.BaseServiceImpl;
import com.location.dao.user.DeviceInfoDao;
import com.location.entity.DeviceInfo;
import com.location.service.user.DeviceInfoService;
@Service("deviceInfoServiceImpl")
public class DeviceInfoServiceImpl extends BaseServiceImpl<DeviceInfo, String> implements DeviceInfoService {

	@Resource(name = "deviceInfoDaoImpl")
	private DeviceInfoDao deviceInfoDao;
	
	@Resource(name = "deviceInfoDaoImpl")
	public void setBaseDao(DeviceInfoDao deviceInfoDao) {
		super.setBaseDao(deviceInfoDao);
	}
	
	@Override
	public DeviceInfo findByName(String deviceMac) {
		// TODO Auto-generated method stub
		return deviceInfoDao.findByName(deviceMac);
	}

	@Override
	public List<DeviceInfo> findByParam(String choice) {
		// TODO Auto-generated method stub
			return deviceInfoDao.findAll();
	}

	//@Override
//	public DeviceInfo findByPrisonerCode(String prisoner_code) {
//		// TODO Auto-generated method stub
//		return deviceInfoDao.findByPrisonerCode(prisoner_code);
//	}

	@Override
	public DeviceInfo findByDeviceID(int device_id) {
		// TODO Auto-generated method stub
		return deviceInfoDao.findByDeviceID(device_id);
	}

}

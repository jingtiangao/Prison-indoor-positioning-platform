package com.location.service.impl.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grain.service.impl.BaseServiceImpl;
import com.location.dao.user.DeviceMacCodeDao;
import com.location.entity.DeviceMacCode;
import com.location.service.user.DeviceMacCodeService;

@Service("deviceMacCodeServiceImpl")
public class DeviceMacCodeServiceImpl extends BaseServiceImpl<DeviceMacCode, String> implements DeviceMacCodeService {
	@Resource(name = "deviceMacCodeDaoImpl")
	private DeviceMacCodeDao deviceDao;
	
	@Resource(name = "deviceMacCodeDaoImpl")
	public void setBaseDao(DeviceMacCodeDao deviceDao){
		super.setBaseDao(deviceDao);
	}

	@Override
	@Transactional(readOnly = true)
	public DeviceMacCode findByName(String device_code) {
		// TODO Auto-generated method stub
		return deviceDao.findByName(device_code);
	}
}

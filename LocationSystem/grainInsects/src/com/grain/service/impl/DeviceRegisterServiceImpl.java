package com.grain.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.grain.dao.DeviceRegisterDao;
import com.grain.entity.DeviceRegister;
import com.grain.service.DeviceRegisterService;

@Service("deviceRegisterServiceImpl")
public class DeviceRegisterServiceImpl 
	extends BaseServiceImpl<DeviceRegister,String>
	implements DeviceRegisterService{
	
	@Resource(name = "deviceRegisterDaoImpl")
	DeviceRegisterDao deviceRegisterdao;
	
	@Resource(name = "DeviceRegisterDaoImpl")
	public void setBaseDao(DeviceRegisterDao dao) {
		super.setBaseDao(dao);
	}

}

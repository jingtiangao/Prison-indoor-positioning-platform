package com.grain.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grain.dao.DeviceRealdataDao;
import com.grain.dao.DeviceRegisterDao;
import com.grain.dao.GrainbinDao;
import com.grain.entity.DeviceRealdata;
import com.grain.entity.DeviceRegister;
import com.grain.entity.Grainbin;
import com.grain.service.DeviceRealdataService;

@Service("deviceRealdataServiceImpl")
public class DeviceRealdataServiceImpl 
	extends BaseServiceImpl<DeviceRealdata,Long>
	implements DeviceRealdataService{
	
	@Resource(name = "deviceRealdataDaoImpl")
	DeviceRealdataDao realdao;
	
	@Resource(name = "grainbinDaoImpl")
	GrainbinDao grainbindao;
	
	@Resource(name = "deviceRegisterDaoImpl")
	DeviceRegisterDao deviceRegisterdao;
	
	@Resource(name = "deviceRealdataDaoImpl")
	public void setBaseDao(DeviceRealdataDao realdao) {
		super.setBaseDao(realdao);
	}

	@Override
	public void saveReal(DeviceRealdata data, String lcbm, String deviceno) {
		// TODO Auto-generated method stub
		Grainbin bin = grainbindao.find(lcbm);
		if (bin!=null)
			data.setTGrainbin(bin);
		DeviceRegister device = deviceRegisterdao.find(deviceno);
		if (device!=null)
			data.setTDeviceRegister(device);
		save(data);
	}
	
	@Transactional(readOnly = true)
	public List<String> findAllKinds(){
		return realdao.findAllKinds(null);
	}
	
	@Transactional(readOnly = true)
	public List<String> findAllSources(String lcbm){
		return realdao.findAllSources(lcbm, null);
	}
}

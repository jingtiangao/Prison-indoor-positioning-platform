package com.grain.dao.impl;

import org.springframework.stereotype.Repository;

import com.grain.dao.DeviceRegisterDao;
import com.grain.entity.DeviceRegister;

/**
 * Dao - 注册设备
 * 
 */
@Repository("deviceRegisterDaoImpl")
public class DeviceRegisterDaoImpl extends BaseDaoImpl<DeviceRegister, String> implements DeviceRegisterDao {


}
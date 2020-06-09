package com.location.dao.Impl.user;

import javax.persistence.FlushModeType;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Repository;

import com.grain.dao.impl.BaseDaoImpl;
import com.location.dao.user.DeviceMacCodeDao;
import com.location.entity.DeviceMacCode;

@Repository("deviceMacCodeDaoImpl")
public class DeviceMacCodeDaoImpl extends BaseDaoImpl<DeviceMacCode, String>implements DeviceMacCodeDao {

	@Override
	public DeviceMacCode findByName(String device_code) {
		if(device_code==null){
			return null;
		}
		try {
			String jpql = "select deviceMacCode from DeviceMacCode deviceMacCode where lower(deviceMacCode.device_code) = lower(:device_code)";
			return entityManager.createQuery(jpql, DeviceMacCode.class).setFlushMode(FlushModeType.COMMIT).setParameter("device_code", device_code).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}

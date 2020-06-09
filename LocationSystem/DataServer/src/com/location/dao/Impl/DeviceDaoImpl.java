package com.location.dao.Impl;

import javax.persistence.NoResultException;

import org.hibernate.Query;
import org.hibernate.Session;

import com.location.dao.DeviceDao;
import com.location.dao.hibernate.HibernateSessionFactory;
import com.location.entity.Device;

public class DeviceDaoImpl implements DeviceDao  {
	public Device findByMac(String deviceMac) {
		Device device=null;
		if(deviceMac==null){
			return null;
		}
		Session session=HibernateSessionFactory.getSessionFactory().openSession();
		try {			
			
			Query query=session.createQuery("from Device where device_mac= :device_mac");
			query.setString("device_mac", deviceMac);
			device=(Device) query.uniqueResult();
			return device;
		} catch (NoResultException e) {
			return null;
		}
	}

}

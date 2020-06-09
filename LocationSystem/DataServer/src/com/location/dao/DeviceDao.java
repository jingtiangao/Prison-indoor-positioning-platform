package com.location.dao;

import com.location.entity.Device;

public interface DeviceDao {
	public Device findByMac(String deviceMac);
}

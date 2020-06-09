package com.location.service.user;

import com.grain.service.BaseService;
import com.location.entity.DeviceMacCode;

public interface DeviceMacCodeService extends BaseService<DeviceMacCode, String> {
	DeviceMacCode findByName(String device_code);
}

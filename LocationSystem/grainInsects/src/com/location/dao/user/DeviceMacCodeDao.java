package com.location.dao.user;

import com.grain.dao.BaseDao;
import com.location.entity.DeviceMacCode;

public interface DeviceMacCodeDao extends BaseDao<DeviceMacCode, String> {
public DeviceMacCode findByName(String deviceCode);
}

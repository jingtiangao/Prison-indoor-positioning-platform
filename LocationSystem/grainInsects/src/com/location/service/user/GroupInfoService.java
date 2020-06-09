package com.location.service.user;

import com.grain.service.BaseService;
import com.location.entity.GroupInfo;

public interface GroupInfoService extends BaseService<GroupInfo, String> {
GroupInfo findByName(String groupName);
}

package com.grain.service;

import com.grain.entity.TDigitalFeature;

public interface TDigitalFeatureService extends BaseService<TDigitalFeature, Long> {

	void save(TDigitalFeature feature, String[] usernames);
}

package com.grain.dao;

import com.grain.entity.Grain;

public interface GrainDao extends BaseDao<Grain,Long> {

	Grain findLast(String lcbm);

}

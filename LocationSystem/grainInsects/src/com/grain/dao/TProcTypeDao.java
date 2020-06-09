package com.grain.dao;

import java.util.List;

import com.grain.entity.TProcType;

public interface TProcTypeDao extends BaseDao<TProcType,String> {

	List<TProcType> findRoots(Integer count);

	boolean snExists(String currentSn);


}

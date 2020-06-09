package com.grain.service;

import java.util.List;

import com.grain.Page;
import com.grain.Pageable;
import com.grain.entity.TCatalogIndex;
import com.grain.entity.TProcType;
import com.grain.entity.page.CatalogIndex;
import com.grain.entity.page.ProcType;


/**
 * Service - 防治工艺类型
 * 
 */
public interface TProcTypeService extends BaseService<TProcType, String> {

	List<TProcType> findRoots();

	boolean snUnique(String previousSn, String sn);

	Page<ProcType> TreeGrid(Pageable pageable, TProcType queryParm);
	
}
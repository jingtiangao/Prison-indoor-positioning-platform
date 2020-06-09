package com.grain.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grain.Page;
import com.grain.Pageable;
import com.grain.dao.TProcTypeDao;
import com.grain.entity.TProcType;
import com.grain.entity.page.ProcType;
import com.grain.service.TProcTypeService;
/*
 *  防治工艺类型
 */
@Service("tprocTypeServiceImpl")
public class TProcTypeServiceImpl 
	extends BaseServiceImpl<TProcType,String>
	implements TProcTypeService{
	
	@Resource(name = "tprocTypeDaoImpl")
	TProcTypeDao dao;
	
	@Resource(name = "tprocTypeDaoImpl")
	public void setBaseDao(TProcTypeDao dao){
		super.setBaseDao(dao);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<TProcType> findRoots() {
		return dao.findRoots(null);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean snUnique(String previousSn, String currentSn) {
		if (StringUtils.equalsIgnoreCase(previousSn, currentSn)) {
			return true;
		} else {
			if (dao.snExists(currentSn)) {
				return false;
			} else {
				return true;
			}
		}
	}

	@Override
	public Page<ProcType> TreeGrid(Pageable pageable, TProcType queryParm) {
			Page<ProcType > lr = new Page<ProcType>();
			Page<TProcType> l = dao.findPage(pageable, queryParm);
			
			if (l==null || l.getRows()==null || l.getRows().size()==0 ) return lr;
			else{
				List<TProcType> rowsList = l.getRows();
				if (rowsList!=null && rowsList.size()>0){
					for (TProcType tc: rowsList){
						if(tc.getParent()==null ^ queryParm.getParent()==null)continue;
						ProcType r = new ProcType();
						BeanUtils.copyProperties(tc, r);
						if (tc.getParent()!= null)
							r.set_parentId(tc.getParent().getSm());
						r.setIsParent(tc.getChildren().size()>0?true:false);
						lr.getRows().add(r);
					}
				}
			}
			lr.setTotal(l.getTotal());
			return lr;
	}
	
}

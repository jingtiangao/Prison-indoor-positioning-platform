package com.grain.dao.impl;

import org.springframework.stereotype.Repository;

import com.grain.dao.TPreventprocessDao;
import com.grain.entity.ArticleCategory;
import com.grain.entity.TCatalogIndex;
import com.grain.entity.TPreventprocess;

@Repository("tpreventprocessDaoImpl")
public class TPreventprocessDaoImpl extends BaseDaoImpl<TPreventprocess, String> 
implements TPreventprocessDao {

}

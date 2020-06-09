package com.grain.service.impl.user;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.grain.CommonAttributes;
import com.grain.Principal;
import com.grain.dao.user.ExpertDao;
import com.grain.entity.user.Expert;
import com.grain.service.impl.BaseServiceImpl;
import com.grain.service.user.ExpertService;
import com.grain.util.StringUtil;

/**
 * Service - 专家用户
 * 
 */
@Service("expertServiceImpl")
public class ExpertServiceImpl extends BaseServiceImpl<Expert, String> implements ExpertService {

	@Resource(name = "expertDaoImpl")
	private ExpertDao expertDao;

	@Resource(name = "expertDaoImpl")
	public void setBaseDao(ExpertDao expertDao) {
		super.setBaseDao(expertDao);
	}
	
	@Transactional(readOnly = true)
	public boolean usernameExists(String username) {
		return expertDao.usernameExists(username);
	}

	@Transactional(readOnly = true)
	public Expert findByUsername(String username) {
		return expertDao.findByUsername(username);
	}

	@Transactional(readOnly = true)
	public boolean hasAutohrize(String userid, String targetURI) {
		return usernameExists(userid);
	}

	@Transactional(readOnly = true)
	public boolean isAuthenticated() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			Principal principal = (Principal) request.getSession().getAttribute(CommonAttributes.Principal);
			if (principal != null) {
				return true;
			}
		}
		return false;
	}

	@Transactional(readOnly = true)
	public Expert getCurrent() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			Principal principal = (Principal) request.getSession().getAttribute(CommonAttributes.Principal);
			if (principal != null) {
				return findByUsername(principal.getUsername());
			}
		}
		return null;
	}

	@Transactional(readOnly = true)
	public String getCurrentUsername() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		if (requestAttributes != null) {
			HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
			Principal principal = (Principal) request.getSession().getAttribute(CommonAttributes.Principal);
			if (principal != null) {
				return principal.getUsername();
			}
		}
		return null;
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void save(Expert expert) {
		String encryptpw = StringUtil.encodePassword(expert.getPassword(),
				"MD5");
		expert.setPassword(encryptpw);
		super.save(expert);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public Expert update(Expert expert) {
		Expert e = find(expert.getUsername());
		if(expert.getPassword() == null){
			expert.setPassword(e.getPassword());
		}else{
			String encryptpw = StringUtil.encodePassword(expert.getPassword(),
					"MD5");
			expert.setPassword(encryptpw);
		}
		return super.update(expert);
	}
	
	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public Expert update(Expert expert, String... ignoreProperties) {
		return super.update(expert, ignoreProperties);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void delete(String id) {
		super.delete(id);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void delete(String... ids) {
		super.delete(ids);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void delete(Expert expert) {
		super.delete(expert);
	}

	@Override
	public boolean loginCheck(Expert expert) {
		Expert ad=findByUsername(expert.getUsername());
		if (ad==null) return false;
		if (!ad.getHasaudit()) return false;
		String encryptpw = StringUtil.encodePassword(expert.getPassword(),
				"MD5");
		if (ad!=null){
			if (ad.getPassword().equalsIgnoreCase(encryptpw))
				return true;
		}
		return false;
	}
	
}
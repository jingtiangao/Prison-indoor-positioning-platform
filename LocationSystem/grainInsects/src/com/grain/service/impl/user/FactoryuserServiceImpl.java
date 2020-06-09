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
import com.grain.dao.user.FactoryuserDao;
import com.grain.entity.user.Factoryuser;
import com.grain.service.impl.BaseServiceImpl;
import com.grain.service.user.FactoryuserService;
import com.grain.util.StringUtil;

/**
 * Service - 加工厂
 * 
 */
@Service("factoryuserServiceImpl")
public class FactoryuserServiceImpl extends BaseServiceImpl<Factoryuser, String> implements FactoryuserService{
	
	@Resource(name = "factoryuserDaoImpl")
	private FactoryuserDao factoryuserDao;
	
	@Resource(name = "factoryuserDaoImpl")
	public void setBaseDao(FactoryuserDao factoryuserDao) {
		super.setBaseDao(factoryuserDao);
	}
	
	@Transactional(readOnly = true)
	public boolean usernameExists(String username) {
		return factoryuserDao.usernameExists(username);
	}

	@Transactional(readOnly = true)
	public Factoryuser findByUsername(String username) {
		return factoryuserDao.findByUsername(username);
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
	public Factoryuser getCurrent() {
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
	public void save(Factoryuser factoryuser) {
		String encryptpw = StringUtil.encodePassword(factoryuser.getPass(),
				"MD5");
		factoryuser.setPass(encryptpw);
		super.save(factoryuser);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public Factoryuser update(Factoryuser factoryuser) {
		Factoryuser e = find(factoryuser.getUsername());
		if(factoryuser.getPass() == null){
			factoryuser.setPass(e.getPass());
		}else{
			String encryptpw = StringUtil.encodePassword(factoryuser.getPass(),
					"MD5");
			factoryuser.setPass(encryptpw);
		}
		return super.update(factoryuser);
	}
	
	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public Factoryuser update(Factoryuser factoryuser, String... ignoreProperties) {
		return super.update(factoryuser, ignoreProperties);
	}
	
	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void updatePassword(Factoryuser factoryuser) {
		if (factoryuser.getPass()!=null){
			String encryptpw = StringUtil.encodePassword(factoryuser.getPass(),
					"MD5");
			factoryuser.setPass(encryptpw);
		}
		update(factoryuser);
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
	public void delete(Factoryuser factoryuser) {
		super.delete(factoryuser);
	}
	
	@Override
	public boolean loginCheck(Factoryuser factoryuser) {
		Factoryuser ur=findByUsername(factoryuser.getUsername());
		String encryptpw = StringUtil.encodePassword(factoryuser.getPass(),
				"MD5");
		if (ur!=null){
			if (ur.getPass().equalsIgnoreCase(encryptpw))
				return true;
		}
		return false;
	}

}

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
import com.grain.dao.user.FarmerUserDao;
import com.grain.entity.user.FarmerUser;
import com.grain.service.impl.BaseServiceImpl;
import com.grain.service.user.FarmerUserService;
import com.grain.util.StringUtil;

/**
 * Service - 农户
 * 
 */
@Service("farmerUserServiceImpl")
public class FarmerUserServiceImpl extends BaseServiceImpl<FarmerUser, String> implements FarmerUserService{
	
	@Resource(name = "farmerUserDaoImpl")
	private FarmerUserDao farmerUserDao;
	
	@Resource(name = "farmerUserDaoImpl")
	public void setBaseDao(FarmerUserDao farmerUserDao) {
		super.setBaseDao(farmerUserDao);
	}

	@Transactional(readOnly = true)
	public boolean usernameExists(String username) {
		return farmerUserDao.usernameExists(username);
	}

	@Transactional(readOnly = true)
	public FarmerUser findByUsername(String username) {
		return farmerUserDao.findByUsername(username);
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
	public FarmerUser getCurrent() {
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
	public void save(FarmerUser farmerUser) {
		String encryptpw = StringUtil.encodePassword(farmerUser.getPassword(),
				"MD5");
		farmerUser.setPassword(encryptpw);
		super.save(farmerUser);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public FarmerUser update(FarmerUser farmerUser) {
		FarmerUser e = find(farmerUser.getUsername());
		if(farmerUser.getPassword() == null){
			farmerUser.setPassword(e.getPassword());
		}else{
			String encryptpw = StringUtil.encodePassword(farmerUser.getPassword(),
					"MD5");
			farmerUser.setPassword(encryptpw);
		}
		return super.update(farmerUser);
	}
	
	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public FarmerUser update(FarmerUser farmerUser, String... ignoreProperties) {
		return super.update(farmerUser, ignoreProperties);
	}
	
	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public void updatePassword(FarmerUser farmerUser) {
		if (farmerUser.getPassword()!=null){
			String encryptpw = StringUtil.encodePassword(farmerUser.getPassword(),
					"MD5");
			farmerUser.setPassword(encryptpw);
		}
		update(farmerUser);
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
	public void delete(FarmerUser farmerUser) {
		super.delete(farmerUser);
	}
	
	@Override
	public boolean loginCheck(FarmerUser farmerUser) {
		FarmerUser ur=findByUsername(farmerUser.getUsername());
		String encryptpw = StringUtil.encodePassword(farmerUser.getPassword(),
				"MD5");
		if (ur!=null){
			if (ur.getPassword().equalsIgnoreCase(encryptpw))
				return true;
		}
		return false;
	}

}

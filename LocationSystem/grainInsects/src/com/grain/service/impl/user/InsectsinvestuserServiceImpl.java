package com.grain.service.impl.user;

import java.util.Date;

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
import com.grain.dao.user.InsectsinvestuserDao;
import com.grain.entity.user.Insectsinvestuser;
import com.grain.service.impl.BaseServiceImpl;
import com.grain.service.user.InsectsinvestuserService;
import com.grain.util.StringUtil;

/**
 * Service - 专家用户
 * 
 */
@Service("insectsinvestuserServiceImpl")
public class InsectsinvestuserServiceImpl extends BaseServiceImpl<Insectsinvestuser, String> implements InsectsinvestuserService {

	@Resource(name = "insectsinvestuserDaoImpl")
	private InsectsinvestuserDao insectsinvestuserDao;

	@Resource(name = "insectsinvestuserDaoImpl")
	public void setBaseDao(InsectsinvestuserDao insectsinvestuserDao) {
		super.setBaseDao(insectsinvestuserDao);
	}
	
	@Transactional(readOnly = true)
	public boolean usernameExists(String username) {
		return insectsinvestuserDao.usernameExists(username);
	}

	@Transactional(readOnly = true)
	public Insectsinvestuser findByUsername(String username) {
		return insectsinvestuserDao.findByUsername(username);
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
	public Insectsinvestuser getCurrent() {
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
	public void save(Insectsinvestuser insectsinvestuser) {
		String encryptpw = StringUtil.encodePassword(insectsinvestuser.getPass(),
				"MD5");
		insectsinvestuser.setPass(encryptpw);
		super.save(insectsinvestuser);
	}

	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public Insectsinvestuser update(Insectsinvestuser insectsinvestuser) {
		Insectsinvestuser e = find(insectsinvestuser.getUsername());
		if(insectsinvestuser.getPass()==null){
			String encryptpw = StringUtil.encodePassword(e.getPass(), "MD5");
			insectsinvestuser.setPass(encryptpw);
		}else{
			insectsinvestuser.setModifydate(new Date());
			if (insectsinvestuser.getPass() != null){
				String encryptpw = StringUtil.encodePassword(insectsinvestuser.getPass(),
						"MD5");
				insectsinvestuser.setPass(encryptpw);
			}
		}
		return super.update(insectsinvestuser);
	}
	
	@Override
	@Transactional
	@CacheEvict(value = "authorization", allEntries = true)
	public Insectsinvestuser update(Insectsinvestuser insectsinvestuser, String... ignoreProperties) {
		return super.update(insectsinvestuser, ignoreProperties);
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
	public void delete(Insectsinvestuser insectsinvestuser) {
		super.delete(insectsinvestuser);
	}

	@Override
	public boolean loginCheck(Insectsinvestuser insectsinvestuser) {
		Insectsinvestuser iiu = findByUsername(insectsinvestuser.getUsername());
		if(iiu == null) return false;
		if(!iiu.getHasaudit()) return false;
		String encryptpw = StringUtil.encodePassword(insectsinvestuser.getPass(), "MD5");
		if(encryptpw.equals(iiu.getPass())){
			return true;
		}
		return false;
	}

}
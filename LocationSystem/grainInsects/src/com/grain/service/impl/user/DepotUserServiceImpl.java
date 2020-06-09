package com.grain.service.impl.user;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.grain.dao.user.DepotUserDao;
import com.grain.entity.user.DepotUser;
import com.grain.service.impl.BaseServiceImpl;
import com.grain.service.user.DepotUserService;
import com.grain.util.StringUtil;

/**
 * Service - 库管员
 * 
 */
@Service("depotUserServiceImpl")
public class DepotUserServiceImpl extends BaseServiceImpl<DepotUser, String> implements DepotUserService {

	@Resource(name = "depotUserDaoImpl")
	private DepotUserDao depotUserDao;

	@Resource(name = "depotUserDaoImpl")
	public void setBaseDao(DepotUserDao depotUserDao) {
		super.setBaseDao(depotUserDao);
	}
	
	@Transactional(readOnly = true)
	public boolean usernameExists(String username) {
		return depotUserDao.usernameExists(username);
	}

	@Transactional(readOnly = true)
	public DepotUser findByUsername(String username) {
		return depotUserDao.findByUsername(username);
	}

	@Transactional(readOnly = true)
	public boolean hasAutohrize(String userid, String targetURI) {
		return usernameExists(userid);
	}

	@Transactional(readOnly = true)
	public boolean isAuthenticated() {

		return false;
	}

	@Transactional(readOnly = true)
	public DepotUser getCurrent() {

		return null;
	}

	@Transactional(readOnly = true)
	public String getCurrentUsername() {

		return null;
	}

	@Override
	@Transactional
	public void save(DepotUser user) {
		if (user.getPass()!=null){
			String encryptpw = StringUtil.encodePassword(user.getPass(),
					"MD5");
			user.setPass(encryptpw);
		}
		super.save(user);
	}

	@Override
	@Transactional
	public DepotUser update(DepotUser user) {
		if (user.getPass()!=null&&user.getPass().trim().length()!=0){
			String encryptpw = StringUtil.encodePassword(user.getPass(),
					"MD5");
			user.setPass(encryptpw);
		}else{
			user.setPass(depotUserDao.find(user.getUsername()).getPass());
		}
		return super.update(user);
	}

	@Override
	@Transactional
	public DepotUser update(DepotUser admin, String... ignoreProperties) {
		return super.update(admin, ignoreProperties);
	}

	@Override
	@Transactional
	public void delete(String id) {
		super.delete(id);
	}

	@Override
	@Transactional
	public void delete(String... ids) {
		super.delete(ids);
	}

	@Override
	@Transactional
	public void delete(DepotUser user) {
		super.delete(user);
	}

	@Override
	public List <Object[]> findGriandepot(Long graindirectionid) {
		return depotUserDao.findGraindepot(graindirectionid);
	}

	@Override
	public boolean loginCheck(DepotUser user) {
		DepotUser ad=findByUsername(user.getUsername());
		if (ad==null) return false;
		if (!ad.getHasaudit()) return false;
		String encryptpw = StringUtil.encodePassword(user.getPass(),
				"MD5");
		if (ad!=null){
			if (ad.getPass().equalsIgnoreCase(encryptpw))
				return true;
		}
		return false;
	}

}
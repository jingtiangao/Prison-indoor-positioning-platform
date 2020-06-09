package com.grain.controller.depoter;

import java.awt.image.BufferedImage;
import java.security.MessageDigest;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.grain.BaiduMapApi;
import com.grain.CommonAttributes;
import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.Principal;
import com.grain.Setting;
import com.grain.Principal.UserType;
import com.grain.Setting.CaptchaType;
import com.grain.entity.Area;
import com.grain.entity.Grainbin;
import com.grain.entity.Graindepot;
import com.grain.entity.Graindirection;
import com.grain.entity.page.Depot;
import com.grain.entity.user.DepotUser;
import com.grain.service.AreaService;
import com.grain.service.CaptchaService;
import com.grain.service.GrainbinService;
import com.grain.service.GraindepotService;
import com.grain.service.GraindirectionService;
import com.grain.service.RSAService;
import com.grain.service.user.DepotUserService;
import com.grain.util.SettingUtils;
import com.grain.util.SpringUtils;
import com.grain.util.WebUtils;
import com.location.entity.DeviceInfo;
import com.location.entity.DeviceMacCode;
import com.location.entity.GroupInfo;
import com.location.entity.PrisonerInfo;
import com.location.entity.UserInfo;
import com.location.service.user.DeviceInfoService;
import com.location.service.user.DeviceMacCodeService;
import com.location.service.user.GroupInfoService;
import com.location.service.user.LsAreaService;
import com.location.service.user.RegionService;
import com.location.service.user.UserInfoService;

/**
 * Controller - 共用
 * 
 */
@Controller
@RequestMapping("depoter/common")
public class CommonController {

	@Resource(name = "rsaServiceImpl")
	private RSAService rsaService;
	@Resource(name = "areaServiceImpl")
	private AreaService areaService;
	@Resource(name = "captchaServiceImpl")
	private CaptchaService captchaService;

	@Resource(name = "depotUserServiceImpl")
	DepotUserService depotuserSrv;
	
	@Resource(name = "graindirectionServiceImpl")
	private GraindirectionService gdSrv;	//储粮区

	@Resource(name="grainDepotServiceImpl")
	GraindepotService depotSrv;	//粮库
	
	@Resource(name="grainBinServiceImpl")
	GrainbinService binService;	//粮仓
	
	@Resource(name = "baiduMapApi")
	BaiduMapApi baidu;
	
//	@Resource(name = "prisonerServiceImpl")
//	PrisonerService prisonerService;
	
	@Resource(name = "deviceMacCodeServiceImpl")
	DeviceMacCodeService deviceMacCodeService;
	
	@Resource(name="userInfoServiceImpl")
	UserInfoService userInfoService;
	
	@Resource(name="regionServiceImpl")
	RegionService regionService;
	
	@Resource(name="groupInfoServiceImpl")
	GroupInfoService groupInfoService;
	
	@Resource(name="lsAreaServiceImpl")
	LsAreaService areaService1;
	
	@Resource(name="deviceInfoServiceImpl")
	DeviceInfoService deviceInfoService;
	/**
	 * 网站关闭
	 */
	@RequestMapping("/site_close")
	public String siteClose() {
		Setting setting = SettingUtils.get();
		if (setting.getIsSiteEnabled()) {
			return "redirect:/";
		} else {
			return "/shop/common/site_close";
		}
	}

	/**
	 * 公钥
	 */
	@RequestMapping(value = "/public_key", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, String> publicKey(HttpServletRequest request) {
		RSAPublicKey publicKey = rsaService.generateKey(request);
		Map<String, String> data = new HashMap<String, String>(); 
		data.put("modulus", Base64.encodeBase64String(publicKey.getModulus().toByteArray()));
		data.put("exponent", Base64.encodeBase64String(publicKey.getPublicExponent().toByteArray()));
		return data;
	}

	/**
	 * 地区
	 */
	@RequestMapping(value = "/area", method = RequestMethod.GET)
	public @ResponseBody
	Map<Long, String> area(Long parentId) {
		List<Area> areas = new ArrayList<Area>();
		Area parent = areaService.find(parentId);
		if (parent != null) {
			areas = new ArrayList<Area>(parent.getChildren());
		} else {
			areas = areaService.findRoots();
		}
		Map<Long, String> options = new HashMap<Long, String>();
		for (Area area : areas) {
			options.put(area.getId(), area.getName());
		}
		return options;
	}

	/**
	 * 验证码
	 */
	@RequestMapping(value = "/captcha", method = RequestMethod.GET)
	public void image(String captchaId, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (StringUtils.isEmpty(captchaId)) {
			captchaId = request.getSession().getId();
		}
		String pragma = new StringBuffer().append("yB").append("-").append("der").append("ewoP").reverse().toString();
		String value = new StringBuffer().append("ten").append(".").append("xxp").append("ohs").reverse().toString();
		response.addHeader(pragma, value);
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setDateHeader("Expires", 0);
		response.setContentType("image/jpeg");

		ServletOutputStream servletOutputStream = null;
		try {
			servletOutputStream = response.getOutputStream();
			BufferedImage bufferedImage = captchaService.buildImage(captchaId);
			ImageIO.write(bufferedImage, "jpg", servletOutputStream);
			servletOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(servletOutputStream);
		}
	}

	/**
	 * 错误提示
	 */
	@RequestMapping("/error")
	public String error() {
		return "depoter/common/error";
	}

	/**
	 * 资源不存在
	 */
	@RequestMapping("/resource_not_found")
	public String resourceNotFound() {
		return "/shop/common/resource_not_found";
	}

	@RequestMapping("/main")
	public String mainEntrance(HttpSession session,ModelMap model) {
		System.out.println("11");
		
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String user="";
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			if (u==null) return "redirect:/depoter/common/login";
			model.addAttribute("manager",u.getManager());
			model.addAttribute("userName",u.getUsername());
			user=p.getUsername();
			String lkbm=depotuserSrv.find(user).getGraindepot().getLkbm();
			Graindepot gd = depotSrv.find(lkbm);
			model.addAttribute("graindepot", gd.getLkmc());
		} else
			return "redirect:/depoter/common/login";
		return "depoter/main";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "depoter/login";
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public Json logout( HttpSession session){
		Json j =new Json();
		if (session!=null)
			session.invalidate();
		j.setSuccess(true);
		return j;
	}
	
	@RequestMapping("/loginsubmit")
	@ResponseBody
	public Json loginsubmit(String captchaId, String captcha, String username,String password , 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Json j =new Json();
		if (!captchaService.isValid(CaptchaType.memberLogin, captchaId, captcha)) {
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("depoter.valid.captchId"));
			return j;
		}
		DepotUser user = new DepotUser();
		user.setUsername(username);
		user.setPass(password==null?"":password);
		if (!depotuserSrv.loginCheck(user)){
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("depoter.valid.username"));
			return j;
		}
		session.invalidate();
		session = request.getSession();

		session.setAttribute(CommonAttributes.Principal, new Principal(UserType.depot, user.getUsername()));
		session.setAttribute(CommonAttributes.BaiduMapApiAccount, baidu.getAccount());
		WebUtils.addCookie(request, response, "depotUsername", user.getUsername());
		j.setSuccess(true);
		if (depotuserSrv.find(username).getManager())
			j.setMsg("manager");
		return j;
	}
	
	@RequestMapping("/success")
	public String success() {
		return "depoter/success";
	}
	
	@RequestMapping("/addDepotUserEntrance")
	public String addDepotUserEntrance(HttpSession session,ModelMap model) throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			model.addAttribute("graindepotid",u.getGraindepot().getLkbm());
			model.addAttribute("lkmc",u.getGraindepot().getLkmc());
		}else
			throw new Exception("session is losed!");
		return "depoter/user/addDepotUser";
	}

	
	@RequestMapping("/addDepotUser")
	@ResponseBody
	public Json addDepotUser(DepotUser entity,String graindepotid){
		Json j = new Json();
		Graindepot gd = depotSrv.find(graindepotid);
		entity.setGraindepot(gd);
		try{
			depotuserSrv.save(entity);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/editSelfEntrance")	//菜单进入 修改个人信息
	public String editSelfEntrance(HttpSession session,ModelMap model) throws Exception{
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username="";
		if (p!=null){
			username=p.getUsername();
		}else
			throw new Exception("session is losed!");
		
		model.addAttribute("user", depotuserSrv.find(username));
		String lkbm=depotuserSrv.find(username).getGraindepot().getLkbm();
		model.addAttribute("lkbm", lkbm);
		Set<Grainbin> wholebins=depotSrv.find(lkbm).getGrainbins();
		
		if (wholebins!=null &&!wholebins.isEmpty() ){
			Set<Grainbin> usedbins=depotuserSrv.find(username).getGrainbins();
			if (usedbins!=null&&!usedbins.isEmpty())
				wholebins.removeAll(usedbins);
		}
		model.addAttribute("nousedbins", wholebins);
		
		return "depoter/user/editUserSelf";
	}
	
	@RequestMapping("/editPasswordEntrance")	//菜单进入 修改个人信息
	public String editPasswordEntrance(HttpSession session,ModelMap model) throws Exception{
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username="";
		if (p!=null){
			username=p.getUsername();
		}else
			throw new Exception("session is losed!");
		
		model.addAttribute("user", depotuserSrv.find(username));
		String lkbm=depotuserSrv.find(username).getGraindepot().getLkbm();
		model.addAttribute("lkbm", lkbm);
		Set<Grainbin> wholebins=depotSrv.find(lkbm).getGrainbins();
		
		if (wholebins!=null &&!wholebins.isEmpty() ){
			Set<Grainbin> usedbins=depotuserSrv.find(username).getGrainbins();
			if (usedbins!=null&&!usedbins.isEmpty())
				wholebins.removeAll(usedbins);
		}
		model.addAttribute("nousedbins", wholebins);
		
		return "depoter/user/editUserSelfPass";
	}
	
	@RequestMapping("/editDepotUserEntrance")
	public String editDepotUserEntrance(String username,ModelMap model ){
		model.addAttribute("user", depotuserSrv.find(username));
		String lkbm=depotuserSrv.find(username).getGraindepot().getLkbm();
		model.addAttribute("lkbm", lkbm);
		Set<Grainbin> wholebins=depotSrv.find(lkbm).getGrainbins();
		
		if (wholebins!=null &&!wholebins.isEmpty() ){
			Set<Grainbin> usedbins=depotuserSrv.find(username).getGrainbins();
			if (usedbins!=null&&!usedbins.isEmpty())
				wholebins.removeAll(usedbins);
		}
		model.addAttribute("nousedbins", wholebins);
		
		return "depoter/user/editDepotUser";
	}
	
	@RequestMapping("/editDepotUser")
	@ResponseBody
	public Json editDepotUser(HttpServletRequest req,
			DepotUser entity,String graindepotid){
		String [] bins=req.getParameterValues("bins");		//服务器端不能直接接受字符串数组，否则会自动去掉首位空格字符
		Json j = new Json();
		Graindepot gd = depotSrv.find(graindepotid);
		entity.setGraindepot(gd);
		
		//去掉重复的粮仓名
		Set<String> binStrset = new HashSet<String>();
		if (bins!=null&& bins.length>0)
			for (int i=0;i<bins.length;i++)
				binStrset.add(bins[i]);
		
		Set<Grainbin> binset= new HashSet<Grainbin>();
		for(String id: binStrset){
			binset.add(binService.find(id));
		}
		entity.setGrainbins(binset);
		try{
			depotuserSrv.update(entity);
			j.setSuccess(true);
			j.setMsg("修改成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/editDepotUserPass")
	@ResponseBody
	public Json editDepotUserPass(HttpServletRequest req,
			DepotUser entity,String graindepotid,@RequestParam(value="oldpassword") String oldpassword){
		String [] bins=req.getParameterValues("bins");		//服务器端不能直接接受字符串数组，否则会自动去掉首位空格字符
		Json j = new Json();
		Graindepot gd = depotSrv.find(graindepotid);
		entity.setGraindepot(gd);
		
		//去掉重复的粮仓名
		Set<String> binStrset = new HashSet<String>();
		if (bins!=null&& bins.length>0)
			for (int i=0;i<bins.length;i++)
				binStrset.add(bins[i]);
		
		Set<Grainbin> binset= new HashSet<Grainbin>();
		for(String id: binStrset){
			binset.add(binService.find(id));
		}
		entity.setGrainbins(binset);
		
		DepotUser depot = depotuserSrv.findByUsername(entity.getUsername());
		String originalpassword = depot.getPass();
		
		String pass = new String();
		 try {
		        //生成实现指定摘要算法的 MessageDigest 对象。
		        MessageDigest md = MessageDigest.getInstance("MD5");  
		        //使用指定的字节数组更新摘要。
		        md.update(oldpassword.getBytes());
		        //通过执行诸如填充之类的最终操作完成哈希计算。
		        byte b[] = md.digest();
		        //生成具体的md5密码到buf数组
		        int i;
		        StringBuffer buf = new StringBuffer("");
		        for (int offset = 0; offset < b.length; offset++) {
		          i = b[offset];
		          if (i < 0)
		            i += 256;
		          if (i < 16)
		            buf.append("0");
		          buf.append(Integer.toHexString(i));
		        }
		        pass = buf.toString();
//		        System.out.println("32位: " + buf.toString());// 32位的加密
//		        System.out.println("16位: " + buf.toString().substring(8, 24));// 16位的加密，其实就是32位加密后的截取
		     }
		     catch (Exception e) {
		       e.printStackTrace();
		     }

		if(originalpassword.equals(pass)){
			try {
				depotuserSrv.update(entity);
				j.setSuccess(true);
				j.setMsg("修改成功");
			} catch (Exception e) {
				e.printStackTrace();
				j.setSuccess(false);
				j.setMsg("修改失败");
			}
		}else{
			j.setSuccess(false);
			j.setMsg("原密码不正确");
		}
		return j;
	}
	
	@RequestMapping("/list")
	public String list() {
		return "depoter/user/list";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Page<DepotUser> getDataGrid(Pageable pageable,DepotUser queryParm,HttpSession session) throws Exception{
		if (queryParm==null )
			queryParm = new DepotUser();
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		
		if (p!=null){
			//设置粮库对象
			DepotUser u = depotuserSrv.find(p.getUsername());
			queryParm.setGraindepot( depotSrv.find(u.getGraindepot().getLkbm()));
		}else
			throw new Exception("session is losed!");
		Page<DepotUser> map=depotuserSrv.findPage(pageable,queryParm);
		return map;
	}
	
	// 删除库管员
	@RequestMapping("/deleteDepotuser")
	@ResponseBody
	public Json deleteDepotuser(String username){
		Json j = new Json();
		DepotUser u = depotuserSrv.find(username);
		if (u==null){
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("depoter.delete.depotuser.error.user"));
			return j;
		}
		if (u.getGrainbins()!=null&& !u.getGrainbins().isEmpty()){
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("depoter.delete.depoteruser.hasbins"));
			return j;
		}
		try{
			depotuserSrv.delete(u);
			j.setSuccess(true);
			j.setMsg(SpringUtils.getMessage("depoter.delete.depoteruser.success"));
		}catch(Exception e){
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
			
	// 获取储粮区域
	@RequestMapping("/getGraindirect")
	@ResponseBody
	public List<Graindirection> getGraindirects(){
		return gdSrv.findAll();
	}
	
	@RequestMapping("/getGraindepot")
	@ResponseBody
	public List<Depot> getGraindepot(Long graindirectionid){
		List<Depot> lst= new ArrayList<Depot>();
		List<Object[]> l=depotuserSrv.findGriandepot(graindirectionid);
		for (int i=0; i<l.size();i++){
			Depot d= new Depot();
			d.setLkbm((String)l.get(i)[0]);
			d.setLkmc((String)l.get(i)[1]);
			lst.add(d);
		}
		return lst;
	}
	
	@RequestMapping(value = "/check_username")
	public @ResponseBody
	boolean checkUserName(String username ) {
		if (StringUtils.isEmpty(username)) {
			return false;
		}
		return !depotuserSrv.existUserName(username);
	}
	
	
//	// add by wuwei
//	private class PrisonInfo{
//		private int user_name;
//		private String status;
//		private String x;
//		private String y;
//		private String head_url;
//		
//		
//		// set the object
//		public PrisonInfo() {
//		}
//		
//		public void setUser_name(String user_name) {
//			this.user_name = user_name;
//		}
//		
//	 
//
//		public void getInfo(DeviceInfo deviceInfo) {
//			// TODO Auto-generated method stub
//			user_name = deviceInfo.getDevice_id();
//			//...
//			
//		}
//		
//	}

//add by guoxinze
	/**
	 * 刚开始做的定位接口，现在已经不需要
	 * @param op_name
	 * @param op_parameter
	 * @return
	 */
	@RequestMapping(value = "/prison_trace", method = RequestMethod.POST)
	public @ResponseBody
	Json getPrisonerTrace(@RequestParam("op_name") String op_name,
								@RequestParam("op_parameter") String op_parameter){
				
	    if(op_name.equals("realtime")){
	    		System.out.println(op_name + " " + op_parameter);
				List<DeviceInfo> l = deviceInfoService.findByParam(op_parameter);	
				List<HashMap<String, String>> ls = new ArrayList<HashMap<String,String>>();
				DeviceInfo p = null;
				for(int i=0;i<l.size();i++){
					HashMap<String, String> hashMap = new HashMap<String, String>();
					p = l.get(i);
					System.out.println(p.getDevice_id()+" "+p.getDevice_id()+" "+String.valueOf(p.getX_millimeter())+" "+String.valueOf(p.getY_millimeter()));
					hashMap.put("UUID", String.valueOf(p.getDevice_id()));
					hashMap.put("status", String.valueOf(p.getError_code()));
					hashMap.put("x",String.valueOf(p.getX_millimeter()));
					hashMap.put("y",String.valueOf(p.getY_millimeter()));
					ls.add(hashMap);
				}
				Json js = new Json();
				js.setObj(ls);
				return js;
	    }else{
	    	return null;
	    }
	}
	/**
	 * 获取所有犯人定位信息接口
	 * 通过先获取UserInfo表中的device_id查找DeviceInfo的相关信息
	 * @param
	 * @return prisoner_info_list: prisoner_name, prisoner_code, head_url, x, y, state。
	 */
	@RequestMapping(value = "/allPrisoner", method =RequestMethod.POST)
	public @ResponseBody
	Json getAllPrisoner(){
		Json j = new Json();
		try{
			List<UserInfo> l = userInfoService.findAll();
			List<HashMap<String, String>> ls = new ArrayList<HashMap<String,String>>();
			UserInfo p = null;
			PrisonerInfo pi = new PrisonerInfo();
			for(int i=0;i<l.size();i++){
				HashMap<String, String> hashMap = new HashMap<String, String>();
				p = l.get(i);
				if(p.getState() == 3){
					continue;
				}else{
					DeviceInfo ui = deviceInfoService.findByDeviceID(p.getDevice_id());
					if(ui == null){//如果在UserInfo表里面而没有在DeviceInfo表里面，就将状态设置成缺勤
						p.setState(2);
						userInfoService.update(p);
						continue;
					}else{//如果找到，那么就设置成正常
						p.setState(1);
						userInfoService.update(p);
					}
					this.groupDistance();
					pi.setInfo(ui, p);
					System.out.println(pi.toString());
					hashMap = pi.toHashMap();
					ls.add(hashMap);
				}
			}
			j.setObj(ls);
			j.setSuccess(true);
			j.setMsg("添加成功!");
		}catch (Exception exception){
			j.setSuccess(false);
			j.setMsg(exception.getMessage());
		}
		
		return j;
	}	
//--------------------end of guoxinze add
	
//add by guoxinze 2016/11/30
	@RequestMapping(value = "/management/device_addOrUpdate", method =RequestMethod.POST)
	public @ResponseBody
	Json addOrUpdateDevice(HttpServletRequest request){
		Json j = new Json();
		DeviceMacCode device = new DeviceMacCode();
		String equipmentID = request.getParameter("InputEquipmentID");
		String deviceMAC = request.getParameter("InputMAC");
		device.setDevice_code(equipmentID);
		device.setDevice_mac(deviceMAC);
		try{
			deviceMacCodeService.update(device);
			j.setSuccess(true);
			j.setMsg("添加成功!");
		}catch (Exception exception){
			j.setSuccess(false);
			j.setMsg(exception.getMessage());
		}
		
		return j;
	}
//--end of guoxinze add
//add by guoxinze 2016/12/09
	/**
	 * 按照犯人编号查询接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "find/prisoner_code" , method =RequestMethod.POST)
	@ResponseBody
	public Json findbyprisonercode(HttpServletRequest request) {
		Json j = new Json();
		String prisoner_code = request.getParameter("prisoner_code");
		String[] pc = prisoner_code.split("\\;");
		for(int m=0;m<pc.length;m++){
			System.out.println(pc[m]);
		}
		int requestNumber = pc.length;
		try {
			List<UserInfo> l = userInfoService.findAll();
			List<HashMap<String, String>> ls = new ArrayList<HashMap<String,String>>();
			UserInfo p = null;
			PrisonerInfo pi = new PrisonerInfo();
			for(int i=0;i<l.size();i++){
				HashMap<String, String> hashMap = new HashMap<String, String>();
				p = l.get(i);
				if(p.getState()==1||p.getState()==4){
					for(int k=0;k<requestNumber;k++){
						System.out.println(k+" "+p.getDevice_id()+" "+p.getUser_name()+" "+p.getUser_head()+" ");
						if(p.getUser_code().equals(pc[k])){
								DeviceInfo ui = deviceInfoService.findByDeviceID(p.getDevice_id());
								pi.setInfo(ui, p);
								System.out.println(pi.toString());
								hashMap = pi.toHashMap();
								ls.add(hashMap);
						}
					}
				}
			}
			j.setObj(ls);
			j.setSuccess(true);
			j.setMsg("添加成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return j;
	}
	/**
	 * 按照组名查找犯人接口
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "find/group_name" , method =RequestMethod.POST)
	@ResponseBody
	public Json findByPrisonerGroup(HttpServletRequest request) {
		Json j = new Json();
		String group_name = request.getParameter("group_name");
		try {
			GroupInfo groupInfo = groupInfoService.findByName(group_name);
			int groupID = groupInfo.getGroupId();
			List<UserInfo> l = userInfoService.findByGroupID(groupID);
			List<HashMap<String, String>> ls = new ArrayList<HashMap<String,String>>();
			UserInfo p = null;
			PrisonerInfo pi = new PrisonerInfo();
			for(int i=0;i<l.size();i++){
				HashMap<String, String> hashMap = new HashMap<String, String>();
				p = l.get(i);
				if(p.getState()==1||p.getState()==4){
					DeviceInfo deviceInfo = deviceInfoService.findByDeviceID(p.getDevice_id());
					pi.setInfo(deviceInfo, p);
					System.out.println(pi.toString());
					hashMap = pi.toHashMap();
					ls.add(hashMap);
				}
			}
			j.setObj(ls);
			j.setSuccess(true);
			j.setMsg("添加成功!");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return j;
	}
//----------------end of guoxinze add
//add of guoxinze 2016/12/9
	/**
	 * 获取所有犯人统计信息接口
	 * 本方法统计所有烦人
	 * @return
	 */
	@RequestMapping(value = "/allprisoner_count", method =RequestMethod.POST)
	@ResponseBody
	public Json allPrisonerCount(){
		Json js = new Json();
		try{
				List<UserInfo> lu=userInfoService.findAll();
				long total_Prisoner_num = userInfoService.count();
				int arrival_Prisoner_num = 0;
				int absence_Prisoner_num = 0;
				int leave_Prisoner_num = 0;
				UserInfo userInfo = null;
				List<HashMap<String, Object>> listHashMap = new ArrayList<HashMap<String,Object>>();
				HashMap<String, Object> ls_absence = new HashMap<String,Object>();
				HashMap<String, Object> ls_leave = new HashMap<String,Object>();
				ls_absence.put("id", "absence_Prisoner_num");
				ls_absence.put("text", "缺勤人员名单");
				ls_absence.put("state", "open");
				ls_leave.put("id", "leave_Prisoner_num");
				ls_leave.put("text", "请假人员名单");
				ls_leave.put("state", "open");
				List<HashMap<String, String>> hashMap1 = new ArrayList<HashMap<String, String>>();
				List<HashMap<String, String>> hashMap2 = new ArrayList<HashMap<String, String>>();
				for(int i=0;i<lu.size();i++){
					userInfo = lu.get(i);
					if(userInfo.getState()==1||userInfo.getState()==4){
						arrival_Prisoner_num++;
					}else if(userInfo.getState()==2){
						absence_Prisoner_num++;
						HashMap<String, String> userhm = new HashMap<String, String>();
						userhm.put("text", userInfo.getUser_name());
						hashMap1.add(userhm);
					}else if(userInfo.getState()==3){
						leave_Prisoner_num++;
						HashMap<String, String> infohm = new HashMap<String, String>();
						infohm.put("text", userInfo.getUser_name());
						hashMap2.add(infohm);
					}else{
						System.out.println("This state value is unavailable!");
					}
				}
				System.out.println("arrival number" +" "+arrival_Prisoner_num+"absence number"+" "+absence_Prisoner_num+" "+"leave number"+" "+leave_Prisoner_num+"total number"+" "+total_Prisoner_num);
				ls_absence.put("children", hashMap1);
				ls_leave.put("children", hashMap2);
				HashMap<String, Object> totalhm = new HashMap<String, Object>();
				HashMap<String, Object> arrivalhm = new HashMap<String, Object>();
				HashMap<String, Object> absencehm = new HashMap<String, Object>();
				HashMap<String, Object> leavehm = new HashMap<String, Object>();
				totalhm.put("id", "total_Prisoner_num");
				totalhm.put("text", String.valueOf(total_Prisoner_num));
				arrivalhm.put("id", "arrival_Prisoner_num");
				arrivalhm.put("text", String.valueOf(arrival_Prisoner_num));
				leavehm.put("id","leave_Prisoner_num");
				leavehm.put("text",String.valueOf(leave_Prisoner_num));
				absencehm.put("id", "leave_Prisoner_num");
				absencehm.put("text", String.valueOf(absence_Prisoner_num));
				listHashMap.add(totalhm);
				listHashMap.add(arrivalhm);
				listHashMap.add(absencehm);
				listHashMap.add(leavehm);
				listHashMap.add(ls_absence);
				listHashMap.add(ls_leave);
				js.setObj(listHashMap);
				js.setSuccess(true);
				js.setMsg("添加成功!");
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return js;
	}
	@RequestMapping(value = "/management/prisoner_addOrUpdate", method =RequestMethod.POST)
	@ResponseBody
	public Json addOrUpdatePrisoner(HttpServletRequest request){
		Json json=new Json();
		UserInfo userInfo=new UserInfo();
		String userName=request.getParameter("InputName");
		String userNumber=request.getParameter("InputNumber");
		String monitor=request.getParameter("InputMonitor");
		String group=request.getParameter("InputGroup");
		String equiment=request.getParameter("InputEquipment");
		int region_id=regionService.findByName(monitor).getRegion_id();
		int group_id=groupInfoService.findByName(group).getGroupId();
		String device_mac=deviceMacCodeService.findByName(equiment).getDevice_mac();
		int device_id=deviceInfoService.findByName(device_mac).getDevice_id();
		userInfo.setUser_name(userName);
		userInfo.setUser_code(userNumber);
		userInfo.setRegion_id(region_id);
		userInfo.setGroup_id(group_id);
		userInfo.setDevice_id(device_id);
		
		try {
			userInfoService.update(userInfo);
			json.setSuccess(true);
			json.setMsg("添加成功！");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
		
	}
	/**
	 * 更新组内最大间距接口
	 * @return 超过组内最大间距的人犯人的信息.即prisoner_info_list
	 */
//	@RequestMapping(value = "/group/maxdistance", method =RequestMethod.POST)
//	@ResponseBody
	public void groupDistance(){
		try {
			List<GroupInfo> groupInfos = groupInfoService.findAll();
			for(int i=0;i<groupInfos.size();i++){
				GroupInfo groupInfo = groupInfos.get(i);
				List<UserInfo> userInfos = userInfoService.findByGroupID(groupInfo.getGroupId());
				boolean flag = true;
				outer:for(int j=0;j<userInfos.size()-1;j++){
					UserInfo userInfo1 = userInfos.get(j);
					System.out.println("name1 "+userInfo1.getUser_name());
					if(userInfo1.getState()==1||userInfo1.getState()==4){
						for(int k=j+1;k<userInfos.size();k++){
							UserInfo userInfo2 = userInfos.get(k);
							System.out.println("name2 "+userInfo2.getUser_name());
							if(userInfo2.getState()==1||userInfo2.getState()==4){
								DeviceInfo deviceInfo1 = deviceInfoService.findByDeviceID(userInfo1.getDevice_id());
								DeviceInfo deviceInfo2 = deviceInfoService.findByDeviceID(userInfo2.getDevice_id());
								double x_distance = Math.abs(deviceInfo1.getX_millimeter()-deviceInfo2.getX_millimeter());
								double y_distance = Math.abs(deviceInfo1.getY_millimeter()-deviceInfo2.getY_millimeter());
								double xy_distance = Math.pow(x_distance, 2)+Math.pow(y_distance, 2);
								double temp = Math.sqrt(xy_distance);

								if(temp>groupInfo.getMaxDistance()){
									flag = false;
									// bug code--- not all prisoners set 4
									for(int m=0;m<userInfos.size();m++){
										UserInfo userInfo3 = userInfos.get(m);
										System.out.println("name3 "+userInfo3.getUser_name());
										if(userInfo3.getState()==1||userInfo3.getState()==4){
											userInfo3.setState(4);
											userInfoService.update(userInfo3);
										}
									}
								}
								// bug
								break outer;
							}
						}
					}
				}
				if(flag){
					for(int n=0;n<userInfos.size();n++){
						UserInfo userInfo4 = userInfos.get(n);
						System.out.println("name4 "+userInfo4.getUser_name());
						if(userInfo4.getState()==1||userInfo4.getState()==4){
							userInfo4.setState(1);
							userInfoService.update(userInfo4);
						}
					}
				}
			}

//			List<GroupInfo> groupInfos = groupInfoService.findAll();
//			for(int i=0;i<groupInfos.size();i++){
//				GroupInfo groupInfo = groupInfos.get(i);
//				List<UserInfo> userInfos = userInfoService.findByGroupID(groupInfo.getGroupId());
//				for(int j=0;j<userInfos.size()-1;j++){
//					UserInfo userInfo1 = userInfos.get(j);
//					if(userInfo1.getState()==1){
//						for(int k=j+1;k<userInfos.size();k++){
//							UserInfo userInfo2 = userInfos.get(k);
//							DeviceInfo deviceInfo1 = deviceInfoService.findByDeviceID(userInfo1.getDevice_id());
//							DeviceInfo deviceInfo2 = deviceInfoService.findByDeviceID(userInfo2.getDevice_id());
//							double x_distance = Math.abs(deviceInfo1.getX_millimeter()-deviceInfo2.getX_millimeter());
//							double y_distance = Math.abs(deviceInfo1.getY_millimeter()-deviceInfo2.getY_millimeter());
//							double xy_distance = Math.pow(x_distance, 2)+Math.pow(y_distance, 2);
//							double temp = Math.sqrt(xy_distance);
//							if(temp>groupInfo.getMaxDistance()){
//								userInfo1.setState(4);
//								userInfo2.setState(4);
//								userInfoService.update(userInfo1);
//								userInfoService.update(userInfo2);							
//							}
//						}
//					}
//				}
//			}
//			List<UserInfo> userInfos = userInfoService.findByState(4);
//			List<HashMap<String, String>> ls = new ArrayList<HashMap<String,String>>();
//			for (int m=0;m<userInfos.size();m++){
//				HashMap<String, String> hashMap = new HashMap<String, String>();
//				UserInfo p = userInfos.get(m);
//				PrisonerInfo pi = new PrisonerInfo();
//				DeviceInfo deviceInfo = deviceInfoService.findByDeviceID(p.getDevice_id());
//				pi.setInfo(deviceInfo, p);
//				System.out.println(pi.toString());
//				hashMap = pi.toHashMap();
//				ls.add(hashMap);
//			}
			
//			
//			
//			List<UserInfo> userInfos = userInfoService.findAll();
//			List<HashMap<String, String>> ls = new ArrayList<HashMap<String,String>>();
//			for (int m=0;m<userInfos.size();m++){
//				HashMap<String, String> hashMap = new HashMap<String, String>();
//				UserInfo p = userInfos.get(m);
//				if(p.getState()==4){
//					PrisonerInfo pi = new PrisonerInfo();
//					DeviceInfo deviceInfo = deviceInfoService.findByDeviceID(p.getDevice_id());
//					pi.setInfo(deviceInfo, p);
//					System.out.println(pi.toString());
//					hashMap = pi.toHashMap();
//					ls.add(hashMap);
//				}
//			}
//			Json js = new Json();
//			js.setObj(ls);
//			js.setSuccess(true);
//			js.setMsg("添加成功!");
//			return js;
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return null;
	}

}
package com.grain.controller.investor;

import java.awt.image.BufferedImage;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.HashMap;
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
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.BaiduMapApi;
import com.grain.CommonAttributes;
import com.grain.Filter;
import com.grain.Filter.Operator;
import com.grain.Json;
import com.grain.Order;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.Principal;
import com.grain.Principal.UserType;
import com.grain.Setting;
import com.grain.Setting.CaptchaType;
import com.grain.entity.Area;
import com.grain.entity.FactoryInfo;
import com.grain.entity.Farmer;
import com.grain.entity.Grainbin;
import com.grain.entity.Graindepot;
import com.grain.entity.InsectOnfield;
import com.grain.entity.InsectsOnfactory;
import com.grain.entity.TFactoryPic;
import com.grain.entity.TFieldPic;
import com.grain.entity.TInsectpicIndepot;
import com.grain.entity.TInsectsCollectPic;
import com.grain.entity.TInsectsCollectionOnFarmer;
import com.grain.entity.TInsectsInbinOnDepot;
import com.grain.entity.TInsectsInfactory;
import com.grain.entity.TInsectsInfoOnFarmer;
import com.grain.entity.TInsectsOndepot;
import com.grain.entity.user.Insectsinvestuser;
import com.grain.service.AreaService;
import com.grain.service.CaptchaService;
import com.grain.service.DepotCollectionService;
import com.grain.service.FactoryCollectionService;
import com.grain.service.FactoryInfoService;
import com.grain.service.FarmerCollectionService;
import com.grain.service.FarmerService;
import com.grain.service.FieldCollectionService;
import com.grain.service.GrainbinService;
import com.grain.service.GraindepotService;
import com.grain.service.RSAService;
import com.grain.service.user.DepotUserService;
import com.grain.service.user.InsectsinvestuserService;
import com.grain.util.SettingUtils;
import com.grain.util.SpringUtils;
import com.grain.util.WebUtils;

/**
 * Controller - 共用
 * 
 */
@Controller("investorCommonController")
@RequestMapping("investor/common")
public class CommonController{
	
	@Resource(name = "rsaServiceImpl")
	private RSAService rsaService;
	@Resource(name = "areaServiceImpl")
	private AreaService areaService;
	@Resource(name = "captchaServiceImpl")
	private CaptchaService captchaService;

	@Resource(name = "depotUserServiceImpl")
	DepotUserService depotuserSrv;
	
	@Resource(name = "insectsinvestuserServiceImpl")
	private InsectsinvestuserService insectsinvestusersrv;
	
	/*@Resource(name = "graindirectionServiceImpl")
	private GraindirectionService gdSrv;	//储粮区
*/
	@Resource(name="grainDepotServiceImpl")
	GraindepotService depotSrv;	//粮库
	
	@Resource(name="grainBinServiceImpl")
	GrainbinService binService;	//粮仓
	
	@Resource(name="depotCollectionServiceImpl")
	private DepotCollectionService depotCollectionService;
	
	@Resource(name = "baiduMapApi")
	BaiduMapApi baidu;
	
	@Resource(name="farmerServiceImpl")
	FarmerService farmerService;
	
	@Resource(name="farmerCollectionServiceImpl")
	FarmerCollectionService farmerCollectionService;
	
	@Resource(name="factoryServiceImpl")
	private FactoryInfoService factoryInfoService;
	
	@Resource(name="factoryCollectionServiceImpl")
	private FactoryCollectionService factoryCollectionService;
	
	@Resource(name="fieldCollectionServiceImpl")
	private FieldCollectionService fieldCollectionService;
	
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
		return "investor/common/error";
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
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
//			Insectsinvestuser u = insectsinvestusersrv.findByUsername(p.getUsername());
			model.addAttribute("logined","logined");
			model.addAttribute("userName", p.getUsername());
		} else {
			return "redirect:/investor/common/login";
		}
		return "investor/main";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "investor/login";
	}
	
	@RequestMapping("/addInvestorEntrance")
	public String addInvestorEntrance() throws Exception {
		
		return "investor/user/register";
	}
	
	@RequestMapping("/register")
	@ResponseBody
	public Json register(Insectsinvestuser entity) {
		Json json = new Json();
		try {
			entity.setHasaudit(false);	// 此处数据库没有默认值，故在此设置
			insectsinvestusersrv.save(entity);
			json.setSuccess(true);
			json.setMsg("注册成功");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	@RequestMapping("/user/editInvestorEntrance")
	public String editInvestorEntrance(HttpSession session, Model model) throws Exception{
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username = "";
		if(p != null){
			username = p.getUsername();
		}else{
			throw new Exception("session is losed!");
		}
		
		model.addAttribute("user", insectsinvestusersrv.findByUsername(username));
		return "investor/user/editUserSelf";
	}
	
	@RequestMapping("/user/editPasswordEntrance")
	public String editPasswordEntrance(HttpSession session, Model model) throws Exception{
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username = "";
		if(p != null){
			username = p.getUsername();
		}else{
			throw new Exception("session is losed!");
		}
		
		model.addAttribute("user", insectsinvestusersrv.findByUsername(username));
		return "investor/user/editPassword";
	}
	
	@RequestMapping("/user/editInvestor")
	@ResponseBody
	public Json editInvestor(Insectsinvestuser insectsinvestuser){
		Json json = new Json();
		insectsinvestuser.setHasaudit(true);
		try {
			insectsinvestusersrv.update(insectsinvestuser);
			json.setSuccess(true);
			json.setMsg("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("修改失败");
		}
		return json;
		
	}
	@RequestMapping("/user/editInvestorPass")
	@ResponseBody
	public Json editInvestorPass(Insectsinvestuser insectsinvestuser,@RequestParam(value="oldpassword") String oldpassword){
		Json json = new Json();
		
		Insectsinvestuser invest = insectsinvestusersrv.findByUsername(insectsinvestuser.getUsername());
		String originalpassword = invest.getPass();
		
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
			insectsinvestuser.setHasaudit(true);
			try {
				insectsinvestusersrv.update(insectsinvestuser);
				json.setSuccess(true);
				json.setMsg("修改成功");
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("修改失败");
			}
		}else{
			json.setSuccess(false);
			json.setMsg("原密码不正确");
		}
		return json;
		
	}
	
	@RequestMapping("/logout")
	@ResponseBody
	public Json logout( HttpSession session){
		Json j =new Json();
		if (session!=null){
			session.invalidate();
			j.setSuccess(true);
			j.setMsg("注销成功");
		}else{
			j.setSuccess(false);
			j.setMsg("注销失败！");
		}
		return j;
	}
	
	@RequestMapping("/loginSubmit")
	@ResponseBody
	public Json loginSubmit(String captchaId, String captcha, String username,String password , 
			HttpServletRequest request, HttpServletResponse response, HttpSession session) {
		Json j =new Json();
		if (!captchaService.isValid(CaptchaType.memberLogin, captchaId, captcha)) {
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("验证码错误"));
			return j;
		}
		Insectsinvestuser user = new Insectsinvestuser();
		user.setUsername(username);
		user.setPass(password==null?"":password);
		
		if (!insectsinvestusersrv.loginCheck(user)){
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("用户名密码错误,或者该用户没有通过审核！"));
			return j;
		}
		session.invalidate();
		session = request.getSession();

		session.setAttribute(CommonAttributes.Principal, new Principal(UserType.invest, user.getUsername()));
		session.setAttribute(CommonAttributes.BaiduMapApiAccount, baidu.getAccount());
		WebUtils.addCookie(request, response, "insectsinvestUsername", user.getUsername());
		j.setSuccess(true);
		/*if (depotuserSrv.find(username).getManager())
			j.setMsg("manager");*/
		j.setMsg("logined");
		return j;
	}
	
	@RequestMapping("/success")
	public String success() {
		return "investor/success";
	}
	
	@RequestMapping(value="myfootprint")
	public String myfootprint(ModelMap model){
		return "investor/myfootprint";
	}
	
	@RequestMapping(value="myInsectsDistributes")
	public String myInsectsDistributes(ModelMap model){
		return "investor/myInsectsDistributes";
	}
	
	@RequestMapping(value="getFootprintFarmerInfo")
	@ResponseBody
	public List<Farmer> getFootprintFarmerInfo(String from, String to){
		List<Farmer> farmersRet = new ArrayList<Farmer>();
		List<Farmer> farmers = farmerService.findAll();
		String collectDate = null;
		for(Farmer farmer:farmers){
			Set<TInsectsCollectionOnFarmer> insectsCollectionOnFarmers = farmer.getInsectsCollectionOnFarmers();
			for(TInsectsCollectionOnFarmer insectsCollectionOnFarmer:insectsCollectionOnFarmers){
				collectDate = insectsCollectionOnFarmer.getDateCollection();
				if(from.compareTo(collectDate)<=0 && to.compareTo(collectDate)>=0){
					farmersRet.add(farmer);
					break;
				}
			}
		}
		return farmersRet;
	}
	
	@RequestMapping(value="getFootprintFarmerRecords")
	@ResponseBody
	public Page<TInsectsCollectionOnFarmer> getFootprintFarmerRecords(Farmer farmer, String pageNumber, Pageable pageable, String from, String to) throws UnsupportedEncodingException{
		pageable.setFilters(getFiltersForFarmer(from, to));
		pageable.setRows(5);
		pageable.setPage(Integer.parseInt(pageNumber));
		TInsectsCollectionOnFarmer insectsCollectionOnFarmer = new TInsectsCollectionOnFarmer();
		insectsCollectionOnFarmer.setTFarmer(farmer);
		Page<TInsectsCollectionOnFarmer> map = farmerCollectionService.findPage(pageable, insectsCollectionOnFarmer);
//		Page<TInsectsCollectionOnFarmer> map = farmerCollectionService.findPage(pageable);
		return map;
	}
	
	@RequestMapping(value="getFootprintFactoryInfo")
	@ResponseBody
	public List<FactoryInfo> getFootprintFactoryInfo(String from, String to){
		
		List<FactoryInfo> factoriesRet = new ArrayList<FactoryInfo>();
		List<FactoryInfo> factories = factoryInfoService.findAll();
		String collectDate = null;
		for(FactoryInfo factoryInfo:factories){
			Set<InsectsOnfactory> insectsOnfactories = factoryInfo.getInsectsOnfactories();
			for(InsectsOnfactory insectsOnfactory:insectsOnfactories){
				collectDate = insectsOnfactory.getCollectdate();
				if(from.compareTo(collectDate)<=0 && to.compareTo(collectDate)>=0){
					factoriesRet.add(factoryInfo);
					break;
				}
			}
		}
		return factoriesRet;
	}
	
	@RequestMapping(value="getFootprintFactoryRecords")
	@ResponseBody
	public Page<InsectsOnfactory> getFootprintFactoryRecords(FactoryInfo factory, String pageNumber, Pageable pageable, String from, String to){
		pageable.setFilters(getFiltersForFactory(from, to));
		pageable.setRows(5);
		pageable.setPage(Integer.parseInt(pageNumber));
		InsectsOnfactory insectsOnfactory = new InsectsOnfactory();
		insectsOnfactory.setTFactoryInfo(factory);
		Page<InsectsOnfactory> map = factoryCollectionService.findPage(pageable, insectsOnfactory);
		return map;
	}
	
	@RequestMapping(value="getFootprintFieldInfo")
	@ResponseBody
	public List<InsectOnfield> getFootprintFieldInfo(String from, String to){
		List<InsectOnfield> fieldsRet = new ArrayList<InsectOnfield>();
		List<InsectOnfield> fields = fieldCollectionService.findAll();
		String collectDate = null;
		for(InsectOnfield field:fields){
			collectDate = field.getCollectdate();
			if(from.compareTo(collectDate)<=0 && to.compareTo(collectDate)>=0){
				fieldsRet.add(field);
			}
		}
		return fieldsRet;
	}
	
	@RequestMapping(value="getFootprintDepotInfo")
	@ResponseBody
	public List<Graindepot> getFootprintDepotInfo(String from, String to){
		List<Graindepot> graindepotsRet = new ArrayList<Graindepot>();
		List<Graindepot> graindepots = depotSrv.findAll();
		String collectDate = null;
		boolean flag = false;
		for(Graindepot graindepot:graindepots){
			flag = false;
			Set<Grainbin> grainbins = graindepot.getGrainbins();
			for(Grainbin grainbin:grainbins){
				Set<TInsectsInbinOnDepot> insectsInbinOnDepots = grainbin.getInsectsInbinOnDepots();
				for(TInsectsInbinOnDepot insectsInbinOnDepot:insectsInbinOnDepots){
					collectDate = insectsInbinOnDepot.getDateCollection();
					if(from.compareTo(collectDate)<=0 && to.compareTo(collectDate)>=0){
						graindepotsRet.add(graindepot);
						flag = true;
						break;
					}
				}
				if(flag)
					break;
			}
		}
		return graindepotsRet;
	}
	
	@RequestMapping(value="getFootprintDepotRecords")
	@ResponseBody
	public Page<TInsectsInbinOnDepot> getFootprintDepotRecords(String lkbm, String pageNumber, Pageable pageable, String from, String to){
		pageable.setFilters(getFiltersForLK(from, to));
		pageable.setRows(5);
		pageable.setPage(Integer.parseInt(pageNumber));
		Graindepot graindepots = depotSrv.find(lkbm);  // 得到粮库
		List<TInsectsInbinOnDepot> inbinOnDepots = new ArrayList<TInsectsInbinOnDepot>();
		for(Grainbin grainbin : graindepots.getGrainbins()){
			for(TInsectsInbinOnDepot inbinOnDepot: grainbin.getInsectsInbinOnDepots()){
				inbinOnDepots.add(inbinOnDepot);
			}
		}
		TInsectsInbinOnDepot inbinOnDepot = new TInsectsInbinOnDepot();
		Page<TInsectsInbinOnDepot> map = depotCollectionService.findPage(pageable, inbinOnDepot);
		map.setRows(inbinOnDepots);
		return map;
	}
	
	/**
	 * 得到登录虫调用户录入的所有虫种
	 * @return
	 */
//	@RequestMapping(value="getAllInsects")
//	@ResponseBody
//	public List<InsectsDistributeP> getAllInsects(HttpSession session){
//		List<InsectsDistributeP> insectsList = new ArrayList<InsectsDistributeP>();
//		Principal principal =(Principal) session.getAttribute(CommonAttributes.Principal);
//		String username = "";
//		if(principal != null){
//			username = principal.getUsername();
//		}else{
//			return null;
//			//throw new Exception("session is losed!");
//		}
//		// 农户的害虫信息
//		List<Filter> filters = new ArrayList<Filter>();
//		Filter filter = new Filter();
//		filter.setProperty("modifer");
//		filter.setValue(username);
//		filter.setOperator(Operator.eq);
//		filters.add(filter);
//		
//		List<Order> orders = new ArrayList<Order>();
//		orders.add(Order.desc("modifydate"));  // 录入日期递减顺序排列
//		List<TInsectsCollectionOnFarmer> insectsCollectionOnFarmers = farmerCollectionService.findList(null, filters, orders);
//		
//		for(TInsectsCollectionOnFarmer insectsCollectionOnFarmer : insectsCollectionOnFarmers){
//			Set<TInsectsCollectPic> insectsCollectPics = insectsCollectionOnFarmer.getInsectsCollectPics();
//			String thumbnail = null;
//			for(TInsectsCollectPic insectsCollectPic : insectsCollectPics){
//				thumbnail = insectsCollectPic.getThumbnail();
//			}
//			Farmer farmer = insectsCollectionOnFarmer.getTFarmer();
//			List<TInsectsInfoOnFarmer> insectsInfoOnFarmers = insectsCollectionOnFarmer.getInsectsInfoOnFarmers();
//			for(TInsectsInfoOnFarmer insectsInfoOnFarmer :insectsInfoOnFarmers){
//				if(insectsInfoOnFarmer.getKind() != null && !"".equals(insectsInfoOnFarmer.getKind().trim())){
//					InsectsDistributeP p = new InsectsDistributeP();
//					p.setLongitude(farmer.getLongitude());
//					p.setLatitude(farmer.getLatitude());
//					
//					p.setId(insectsInfoOnFarmer.getId());  //虫种编号
//					p.setInsectName(insectsInfoOnFarmer.getKind());  // 虫种名
//					p.setThumbnail(thumbnail);
//					p.setNum(insectsInfoOnFarmer.getNum()==null?0:insectsInfoOnFarmer.getNum());  // 害虫数量
//					insectsList.add(p);
//				}
//			}
//		}
//		// 加工厂的害虫信息
//		List<InsectsOnfactory> insectsOnfactories = factoryCollectionService.findList(null, filters, orders);
//		for (InsectsOnfactory insectsOnfactory : insectsOnfactories) {
//			List<TFactoryPic> factoryPics = insectsOnfactory.getTFactoryPics();
//			String thumbnail = null;
//			for(TFactoryPic factoryPic : factoryPics){
//				if(factoryPic.getThumbnail() != null){
//					thumbnail = factoryPic.getThumbnail();
//					break;  // 取第一个图标
//				}
//			}
//			FactoryInfo factoryInfo = insectsOnfactory.getTFactoryInfo();
//			List<TInsectsInfactory> insectsInfactories = insectsOnfactory
//					.getTInsectsInfactories();
//			for (TInsectsInfactory infactory : insectsInfactories) {
//				if(infactory.getKind() != null && !"".equals(infactory.getKind().trim())){
//					InsectsDistributeP p = new InsectsDistributeP();
//					p.setLongitude(factoryInfo.getLongitude());
//					p.setLatitude(factoryInfo.getLatitude());
//
//					p.setId(infactory.getId().intValue()); // 虫种编号
//					p.setInsectName(infactory.getKind()); // 虫种名
//					p.setThumbnail(thumbnail);
//					p.setNum(infactory.getNum()==null?0:infactory.getNum());  // 害虫数量
//					insectsList.add(p);
//				}
//				
//			}
//		}
//		 
//		// 直属库的害虫信息
//		List<TInsectsInbinOnDepot> insectsInbinOnDepots = depotCollectionService.findList(null, filters, orders);
//		for(TInsectsInbinOnDepot inbinOnDepot : insectsInbinOnDepots){
//			List<TInsectpicIndepot> insectpicIndepots = inbinOnDepot.getTInsectpicIndepots();
//			String thumbnail = null;
//			for(TInsectpicIndepot insectpicIndepot : insectpicIndepots){
//				if(insectpicIndepot.getThumbnail() != null){
//					thumbnail = insectpicIndepot.getThumbnail();
//					break;   // 取第一个图标
//				}
//			}
//			Graindepot graindepot = inbinOnDepot.getTGrainbin().getGraindepot();
//			List<TInsectsOndepot> insectsOndepots = inbinOnDepot.getTInsectsOndepots();
//			for(TInsectsOndepot insectsOndepot : insectsOndepots){
//				if(insectsOndepot.getKind() != null  && !"".equals(insectsOndepot.getKind().trim())){
//					InsectsDistributeP p = new InsectsDistributeP();
//					p.setLongitude(graindepot.getLongtitude());
//					p.setLatitude(graindepot.getLatitude());
//					
//					p.setId(insectsOndepot.getId());  //虫种编号
//					p.setInsectName(insectsOndepot.getKind());  // 虫种名
//					
//					p.setThumbnail(thumbnail);
//					p.setNum(insectsOndepot.getNum()==null?0:insectsOndepot.getNum());  // 害虫数量
//					insectsList.add(p);
//				}
//			}
//		}
//		 
//		 // 野外的害虫信息
//		List<InsectOnfield> insectOnfields = fieldCollectionService.findList(null, filters, orders);
////		 List<InsectOnfield> insectOnfields = fieldCollectionService.findAll();
//		for(InsectOnfield insectOnfield : insectOnfields){
//			List<TFieldPic> fieldPics = insectOnfield.getTFieldPics();
//			String thumbnail = null;
//			for(TFieldPic fieldPic : fieldPics){
//				if(fieldPic.getThumbnail() != null){
//					thumbnail = fieldPic.getThumbnail();
//					break;// 取第一个图标
//				}
//			}
//			if(insectOnfield.getKind() != null && !"".equals(insectOnfield.getKind().trim())){
//				InsectsDistributeP p = new InsectsDistributeP();
//				p.setLongitude(insectOnfield.getLongtitude());
//				p.setLatitude(insectOnfield.getLatitude());
//				
//				p.setId(insectOnfield.getId().intValue());  //虫种编号
//				p.setInsectName(insectOnfield.getKind());  // 虫种名
//				p.setThumbnail(thumbnail);
//				p.setNum(insectOnfield.getNum()==null?0:insectOnfield.getNum());  // 害虫数量
//				
//				insectsList.add(p);
//			}
//		 }
//		 return insectsList;
//	}
	
	@RequestMapping(value="getAllInsects")
	@ResponseBody
	public List<Map<String, String>> getAllInsects(HttpSession session){
		List<Map<String, String>> insectsList = new ArrayList<Map<String, String>>();
		Principal principal =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username = "";
		if(principal != null){
			username = principal.getUsername();
		}else{
			return null;
			//throw new Exception("session is losed!");
		}
		// 农户的害虫信息
		List<Filter> filters = new ArrayList<Filter>();
		Filter filter = new Filter();
		filter.setProperty("modifer");
		filter.setValue(username);
		filter.setOperator(Operator.eq);
		filters.add(filter);
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.desc("modifydate"));  // 录入日期递减顺序排列
		List<TInsectsCollectionOnFarmer> insectsCollectionOnFarmers = farmerCollectionService.findList(null, filters, orders);
		
		for(TInsectsCollectionOnFarmer insectsCollectionOnFarmer : insectsCollectionOnFarmers){
			List<TInsectsInfoOnFarmer> insectsInfoOnFarmers = insectsCollectionOnFarmer.getInsectsInfoOnFarmers();
			for(TInsectsInfoOnFarmer insectsInfoOnFarmer :insectsInfoOnFarmers){
				if(insectsInfoOnFarmer.getKind() != null && !"".equals(insectsInfoOnFarmer.getKind().trim())){
					Map<String, String> map = new HashMap<String, String>();
					map.put("insectName", insectsInfoOnFarmer.getKind());
					insectsList.add(map);
				}
			}
		}
		// 加工厂的害虫信息
		List<InsectsOnfactory> insectsOnfactories = factoryCollectionService.findList(null, filters, orders);
		for (InsectsOnfactory insectsOnfactory : insectsOnfactories) {
			List<TInsectsInfactory> insectsInfactories = insectsOnfactory
					.getTInsectsInfactories();
			for (TInsectsInfactory infactory : insectsInfactories) {
				if(infactory.getKind() != null && !"".equals(infactory.getKind().trim())){
					Map<String, String> map = new HashMap<String, String>();
					map.put("insectName", infactory.getKind());
					insectsList.add(map);
				}
			}
		}
		 
		// 直属库的害虫信息
		List<TInsectsInbinOnDepot> insectsInbinOnDepots = depotCollectionService.findList(null, filters, orders);
		for(TInsectsInbinOnDepot inbinOnDepot : insectsInbinOnDepots){
			List<TInsectsOndepot> insectsOndepots = inbinOnDepot.getTInsectsOndepots();
			for(TInsectsOndepot insectsOndepot : insectsOndepots){
				if(insectsOndepot.getKind() != null  && !"".equals(insectsOndepot.getKind().trim())){
					Map<String, String> map = new HashMap<String, String>();
					map.put("insectName", insectsOndepot.getKind());
					insectsList.add(map);
				}
			}
		}
		 
		 // 野外的害虫信息
		List<InsectOnfield> insectOnfields = fieldCollectionService.findList(null, filters, orders);
		for(InsectOnfield insectOnfield : insectOnfields){
			if(insectOnfield.getKind() != null && !"".equals(insectOnfield.getKind().trim())){
				Map<String, String> map = new HashMap<String, String>();
				map.put("insectName", insectOnfield.getKind());
				insectsList.add(map);
			}
		 }
		return insectsList;
	}
	
	/**
	 * 获取虫种的分布
	 * @param from  采集虫种的开始时间
	 * @param to    采集虫种的结束时间
	 * @param insects   要获取的虫种名
	 * @return
	 */
	@RequestMapping("getInsectDistributes")
	@ResponseBody
	public List<Map<String, Object>> getInsectDistributes(String from, String to, String[] insects, HttpSession session){
		
		List<Map<String, Object>> retList = new ArrayList<Map<String,Object>>();
		Map<String, Object> map = null;
		
		Principal principal =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username = "";
		if(principal != null){
			username = principal.getUsername();
		}else{
			return null;
			//throw new Exception("session is losed!");
		}
		// 农户的害虫信息
		List<Filter> filters = new ArrayList<Filter>();
		Filter filter = new Filter();
		filter.setProperty("modifer");
		filter.setValue(username);
		filter.setOperator(Operator.eq);
		filters.add(filter);
		
		List<Order> orders = new ArrayList<Order>();
		orders.add(Order.desc("modifydate"));  // 录入日期递减顺序排列
		List<TInsectsCollectionOnFarmer> insectsCollectionOnFarmers = farmerCollectionService.findList(null, filters, orders);
		
		for(TInsectsCollectionOnFarmer insectsCollectionOnFarmer : insectsCollectionOnFarmers){
			map = new HashMap<String, Object>();
			
			Set<TInsectsCollectPic> insectsCollectPics = insectsCollectionOnFarmer.getInsectsCollectPics();
			String thumbnail = null;
			for(TInsectsCollectPic insectsCollectPic : insectsCollectPics){
				thumbnail = insectsCollectPic.getThumbnail();
				if(thumbnail != null && !"".equals(thumbnail.trim())){
					map.put("thumbnail", thumbnail);  // 虫种图片地址
					break;
				}
			}
			Farmer farmer = insectsCollectionOnFarmer.getTFarmer();
			Double lng = farmer.getLongitude();
			Double lat = farmer.getLatitude();
			List<TInsectsInfoOnFarmer> insectsInfoOnFarmers = insectsCollectionOnFarmer.getInsectsInfoOnFarmers();
			for(TInsectsInfoOnFarmer insectsInfoOnFarmer :insectsInfoOnFarmers){
				if(isInInsects(insectsInfoOnFarmer.getKind(), insects)){  // 过滤虫种
					int flag = -1;
					if(lng != null && lat != null){
						for(int i=0;i<retList.size();i++){
							Map<String, Object> tmp = retList.get(i);
							if(lng == tmp.get("longitude") && lat == tmp.get("latitude")){  // 说明此时已经存在此记录的ip地址
								flag = i;
							}
						}
						if(flag == -1){
							map.put("longitude", lng);
							map.put("latitude", lat);
							map.put("address", farmer.getAddress());
							List<Map<String, Object>> tmpList = new ArrayList<Map<String, Object>>();
							Map<String, Object> tmpMap = new HashMap<String, Object>();
							
							tmpMap.put("kind", insectsInfoOnFarmer.getKind());  // 虫种类别
							tmpMap.put("stage", insectsInfoOnFarmer.getStage());     // 虫态
							tmpMap.put("food", insectsInfoOnFarmer.getFood());  // 虫种食性
							tmpMap.put("harm", insectsInfoOnFarmer.getHarm());  // 虫种危害
							tmpMap.put("host", insectsInfoOnFarmer.getHost());  // 虫种宿主
							tmpMap.put("num", insectsInfoOnFarmer.getNum());    // 虫种数量
							tmpList.add(tmpMap);  // 追加到原记录列表中
							
							map.put("records", tmpList);
						}else{  //此时说明已经有该位置的记录信息
							Map<String, Object> tmp = retList.get(flag);
							List<Map<String, Object>> tmpList = (List<Map<String, Object>>) tmp.get("records");
							Map<String, Object> tmpMap = new HashMap<String, Object>();
							tmpMap.put("kind", insectsInfoOnFarmer.getKind());  // 虫种类别
							tmpMap.put("stage", insectsInfoOnFarmer.getStage());  // 虫态
							tmpMap.put("food", insectsInfoOnFarmer.getFood());  // 虫态
							tmpMap.put("harm", insectsInfoOnFarmer.getHarm());  // 虫种危害
							tmpMap.put("host", insectsInfoOnFarmer.getHost());  // 虫种宿主
							tmpMap.put("num", insectsInfoOnFarmer.getNum());    // 虫种数量
							tmpList.add(tmpMap);  // 追加到原记录列表中
						}
					}
				}
			}
			retList.add(map);
		}
		// 加工厂的害虫信息
		List<InsectsOnfactory> insectsOnfactories = factoryCollectionService.findList(null, filters, orders);
		for (InsectsOnfactory insectsOnfactory : insectsOnfactories) {
			map = new HashMap<String, Object>();
			
			List<TFactoryPic> factoryPics = insectsOnfactory.getTFactoryPics();
			String thumbnail = null;
			for(TFactoryPic factoryPic : factoryPics){
				thumbnail = factoryPic.getThumbnail();
				if(thumbnail  != null && !"".equals(thumbnail.trim())){
					map.put("thumbnail", thumbnail);
					break;  // 取第一个图标
				}
			}
			
			FactoryInfo factoryInfo = insectsOnfactory.getTFactoryInfo();
			Double lng = factoryInfo.getLongitude();
			Double lat = factoryInfo.getLatitude();
			List<TInsectsInfactory> insectsInfactories = insectsOnfactory.getTInsectsInfactories();
			for (TInsectsInfactory infactory : insectsInfactories) {
				if(isInInsects(infactory.getKind(), insects)){  // 过滤虫种
					int flag = -1;
					if(lng != null && lat != null){
						for(int i=0;i<retList.size();i++){
							Map<String, Object> tmp = retList.get(i);
							if(lng == tmp.get("longitude") && lat == tmp.get("latitude")){  // 说明此时已经存在此记录的ip地址
								flag = i;
							}
						}
						if(flag == -1){
							map.put("longitude", lng);
							map.put("latitude", lat);
							map.put("address", factoryInfo.getAddress());
							List<Map<String, Object>> tmpList = new ArrayList<Map<String, Object>>();
							Map<String, Object> tmpMap = new HashMap<String, Object>();
							
							tmpMap.put("kind", infactory.getKind());  // 虫种类别
							tmpMap.put("stage", infactory.getStage());  // 虫态
							tmpMap.put("food", infactory.getFood());  // 食性
							tmpMap.put("harm", infactory.getHarm());  // 虫种危害
							tmpMap.put("host", infactory.getHost());  // 虫种宿主
							tmpMap.put("num", infactory.getNum());    // 虫种数量
							tmpList.add(tmpMap);  // 追加到原记录列表中
							
							map.put("records", tmpList);
						}else{  //此时说明已经有该位置的记录信息
							Map<String, Object> tmp = retList.get(flag);
							List<Map<String, Object>> tmpList = (List<Map<String, Object>>) tmp.get("records");
							Map<String, Object> tmpMap = new HashMap<String, Object>();
							tmpMap.put("kind", infactory.getKind());  // 虫种类别
							tmpMap.put("stage", infactory.getStage());  // 虫态
							tmpMap.put("food", infactory.getFood());  // 食性
							tmpMap.put("harm", infactory.getHarm());  // 虫种危害
							tmpMap.put("host", infactory.getHost());  // 虫种宿主
							tmpMap.put("num", infactory.getNum());    // 虫种数量
							tmpList.add(tmpMap);  // 追加到原记录列表中
						}
					}
				}
			}
			retList.add(map);
		}
		 
		// 直属库的害虫信息
		List<TInsectsInbinOnDepot> insectsInbinOnDepots = depotCollectionService.findList(null, filters, orders);
		for(TInsectsInbinOnDepot inbinOnDepot : insectsInbinOnDepots){
			map = new HashMap<String, Object>();
			
			List<TInsectpicIndepot> insectpicIndepots = inbinOnDepot.getTInsectpicIndepots();
			String thumbnail = null;
			for(TInsectpicIndepot insectpicIndepot : insectpicIndepots){
				thumbnail = insectpicIndepot.getThumbnail();
				if(thumbnail != null && !"".equals(thumbnail.trim())){
					map.put("thumbnail", thumbnail);
					break;   // 取第一个图标
				}
			}
			Graindepot graindepot = inbinOnDepot.getTGrainbin().getGraindepot();
			Double lng = graindepot.getLongtitude();
			Double lat = graindepot.getLatitude();
			List<TInsectsOndepot> insectsOndepots = inbinOnDepot.getTInsectsOndepots();
			for(TInsectsOndepot insectsOndepot : insectsOndepots){
				if(isInInsects(insectsOndepot.getKind(), insects)){  // 过滤指定虫种
					int flag = -1;
					if(lng != null && lat != null){
						for(int i=0;i<retList.size();i++){
							Map<String, Object> tmp = retList.get(i);
							if(lng == tmp.get("longitude") && lat == tmp.get("latitude")){  // 说明此时已经存在此记录的ip地址
								flag = i;
							}
						}
						if(flag == -1){
							map.put("longitude", lng);
							map.put("latitude", lat);
							map.put("address", graindepot.getLkdz());  // 粮库地址
							List<Map<String, Object>> tmpList = new ArrayList<Map<String, Object>>();
							Map<String, Object> tmpMap = new HashMap<String, Object>();
							
							tmpMap.put("kind", insectsOndepot.getKind());  // 虫种类别
							tmpMap.put("stage", insectsOndepot.getStage());  // 虫态
							tmpMap.put("food", insectsOndepot.getFood());  // 食性
							tmpMap.put("harm", insectsOndepot.getHarm());  // 虫种危害
							tmpMap.put("host", insectsOndepot.getHost());  // 虫种宿主
							tmpMap.put("num", insectsOndepot.getNum());    // 虫种数量
							tmpList.add(tmpMap);  // 追加到原记录列表中
							
							map.put("records", tmpList);
						}else{  //此时说明已经有该位置的记录信息
							Map<String, Object> tmp = retList.get(flag);
							List<Map<String, Object>> tmpList = (List<Map<String, Object>>) tmp.get("records");
							Map<String, Object> tmpMap = new HashMap<String, Object>();
							tmpMap.put("kind", insectsOndepot.getKind());  // 虫种类别
							tmpMap.put("stage", insectsOndepot.getStage());  // 虫态
							tmpMap.put("food", insectsOndepot.getFood());  // 食性
							tmpMap.put("harm", insectsOndepot.getHarm());  // 虫种危害
							tmpMap.put("host", insectsOndepot.getHost());  // 虫种宿主
							tmpMap.put("num", insectsOndepot.getNum());    // 虫种数量
							tmpList.add(tmpMap);  // 追加到原记录列表中
						}
					}
				}
			}
			retList.add(map);
		}
		 
		 // 野外的害虫信息
		List<InsectOnfield> insectOnfields = fieldCollectionService.findList(null, filters, orders);
		for(InsectOnfield insectOnfield : insectOnfields){
			map = new HashMap<String, Object>();
			
			List<TFieldPic> fieldPics = insectOnfield.getTFieldPics();
			String thumbnail = null;
			for(TFieldPic fieldPic : fieldPics){
				thumbnail = fieldPic.getThumbnail();
				if(thumbnail != null && !"".equals(thumbnail.trim())){
					map.put("thumbnail", thumbnail);
					break;// 取第一个图标
				}
			}
			Double lng = insectOnfield.getLongtitude();
			Double lat = insectOnfield.getLatitude();
			if(isInInsects(insectOnfield.getKind(), insects)){  // 过滤指定虫种
				int flag = -1;
				if(lng != null && lat != null){
					for(int i=0;i<retList.size();i++){
						Map<String, Object> tmp = retList.get(i);
						if(lng == tmp.get("longitude") && lat == tmp.get("latitude")){  // 说明此时已经存在此记录的ip地址
							flag = i;
						}
					}
					if(flag == -1){
						map.put("longitude", lng);
						map.put("latitude", lat);
						List<Map<String, Object>> tmpList = new ArrayList<Map<String, Object>>();
						Map<String, Object> tmpMap = new HashMap<String, Object>();
						
						tmpMap.put("kind", insectOnfield.getKind());  // 虫种类别
						tmpMap.put("stage", insectOnfield.getStage());  // 虫态
						tmpMap.put("food", insectOnfield.getFood());  // 食性
						tmpMap.put("harm", insectOnfield.getHarm());  // 虫种危害
						tmpMap.put("host", insectOnfield.getHost());  // 虫种宿主
						tmpMap.put("num", insectOnfield.getNum());    // 虫种数量
						tmpList.add(tmpMap);  // 追加到原记录列表中
						
						map.put("records", tmpList);
					}else{  //此时说明已经有该位置的记录信息
						Map<String, Object> tmp = retList.get(flag);
						List<Map<String, Object>> tmpList = (List<Map<String, Object>>) tmp.get("records");
						Map<String, Object> tmpMap = new HashMap<String, Object>();
						tmpMap.put("kind", insectOnfield.getKind());  // 虫种类别
						tmpMap.put("stage", insectOnfield.getStage());  // 虫态
						tmpMap.put("food", insectOnfield.getFood());  // 食性
						tmpMap.put("harm", insectOnfield.getHarm());  // 虫种危害
						tmpMap.put("host", insectOnfield.getHost());  // 虫种宿主
						tmpMap.put("num", insectOnfield.getNum());    // 虫种数量
						tmpList.add(tmpMap);  // 追加到原记录列表中
					}
				}
			}
			retList.add(map);
		}
		
		return retList;
	}
	private List<Filter> getFiltersForFarmer(String startTime, String endTime){
		List<Filter> filters = new ArrayList<Filter>();
		Filter fl;
		if(startTime != null && !startTime.equals("")) {
			fl = new Filter();
			fl.setProperty("dateCollection");
			fl.setValue(startTime);
			fl.setOperator(Operator.ge);
			filters.add(fl);
		}
		if(endTime != null && !endTime.equals("")) {
			fl = new Filter();
			fl.setProperty("dateCollection");
			fl.setValue(endTime);
			fl.setOperator(Operator.le);
			filters.add(fl);
		}
		return filters;
	}
	
	private List<Filter> getFiltersForLK(String startTime, String endTime){
		List<Filter> filters = new ArrayList<Filter>();
		Filter fl;
		if(startTime != null && !startTime.equals("")) {
			fl = new Filter();
			fl.setProperty("dateCollection");
			fl.setValue(startTime);
			fl.setOperator(Operator.ge);
			filters.add(fl);
		}
		if(endTime != null && !endTime.equals("")) {
			fl = new Filter();
			fl.setProperty("dateCollection");
			fl.setValue(endTime);
			fl.setOperator(Operator.le);
			filters.add(fl);
		}
		return filters;
	}
	
	private List<Filter> getFiltersForFactory(String startTime, String endTime){
		List<Filter> filters = new ArrayList<Filter>();
		Filter fl;
		if(startTime != null && !startTime.equals("")) {
			fl = new Filter();
			fl.setProperty("collectdate");
			fl.setValue(startTime);
			fl.setOperator(Operator.ge);
			filters.add(fl);
		}
		if(endTime != null && !endTime.equals("")) {
			fl = new Filter();
			fl.setProperty("collectdate");
			fl.setValue(endTime);
			fl.setOperator(Operator.le);
			filters.add(fl);
		}
		return filters;
	}
	private boolean isInInsects(String insect, String[] insects){
		
		for(int i=0;i<insects.length;i++){
			if(insect != null && !"".equals(insect.trim()) && insect.equals(insects[i])){
				return true;
			}
		}
		return false;
	}
}
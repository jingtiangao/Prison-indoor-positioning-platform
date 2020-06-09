package com.grain.controller.investor;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.grain.CommonAttributes;
import com.grain.FileInfo.FileType;
import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.Principal;
import com.grain.entity.Farmer;
import com.grain.entity.TInsectsCollectPic;
import com.grain.entity.TInsectsCollectionOnFarmer;
import com.grain.entity.TInsectsInfoOnFarmer;
import com.grain.service.FarmerCollectionPicService;
import com.grain.service.FarmerCollectionService;
import com.grain.service.FarmerService;
import com.grain.service.FileService;
import com.grain.util.SpringUtils;


@Controller
@RequestMapping("/investor/farmer")
public class FarmerCollectController{
	
	@Resource(name="farmerServiceImpl")
	FarmerService farmerService;
	
	@Resource(name="farmerCollectionServiceImpl")
	FarmerCollectionService farmerCollectionService;
	
	@Resource(name="farmerCollectionPicServiceImpl")
	FarmerCollectionPicService farmerCollectionPicService;
	
	@Resource(name = "fileServiceImpl")	//file
	private FileService fileService;
	
	@RequestMapping("farmerInfoList")
	public String farmerInfoList(){
		return "/investor/farmer/farmerInfoList";
	}
	
	@RequestMapping("/getFarmerInfoList")
	@ResponseBody
	public Page<Farmer> getFarmerInfoList(Pageable pageable,Farmer queryParm,
			HttpSession session) throws Exception{
//		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		Page<Farmer> map=farmerService.findPage(pageable,queryParm);
		return map;
	}
	
	@RequestMapping(value="/farmerAdd")
	public String farmerAdd(Model model, HttpSession session) throws Exception{
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username = "";
		if(p != null){
			username = p.getUsername();
		}else{
			throw new Exception("session is losed!");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String smFarmer = username + sdf.format(new Date()) + getRandomNumber(6);
		model.addAttribute("smFarmer", smFarmer);
		return "/investor/farmer/farmerAdd";
	}
	
	@RequestMapping(value="/saveFarmer")
	@ResponseBody
	public String saveFarmer(Farmer farmer, HttpSession session) throws Exception{
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username = "";
		if(p != null){
			username = p.getUsername();
		}else{
			throw new Exception("session is losed!");
		}
		String ret = "ret";
		try {
			farmer.setModifer(username);  //设置录入人
			farmerService.save(farmer);
			ret = "ok";
		} catch (Exception e) {
			e.printStackTrace();
			ret = "error";
		}
		return ret;
	}
	
	@RequestMapping(value="farmerEditEntrance")
	public String farmerEditEntrance(String smFarmer, Model model){
		model.addAttribute("farmer", farmerService.findBySMFarmer(smFarmer));
		return "/investor/farmer/farmerEdit";
	}
	
	@RequestMapping(value="farmerEdit")
	@ResponseBody
	public Json farmerEdit(Farmer farmer){
		Json json = new Json();
		
		try {
			if(farmer!=null){
				farmerService.update(farmer,"insectsCollectionOnFarmers");
			}
			json.setSuccess(true);
			json.setMsg("修改成功");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	@RequestMapping(value="/farmerDelete")
	@ResponseBody
	public Json farmerDelete(String smFarmer){
		Json json = new Json();
		 Farmer farmer = farmerService.findBySMFarmer(smFarmer);
		 
		 if(farmer.getInsectsCollectionOnFarmers() != null && !farmer.getInsectsCollectionOnFarmers().isEmpty()){
			 json.setSuccess(false);
			 json.setMsg("存在关联虫害信息记录数据，无法删除!");
			 return json;
		 }
		 try {
			farmerService.delete(smFarmer);
			json.setSuccess(true);
			json.setMsg("删除成功");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	@RequestMapping(value="recordList")
	public String recordList(){
		return "/investor/farmer/recordList";
	}
	// 虫害信息记录操作
	@RequestMapping(value="/getRecordList")
	@ResponseBody
	public Page<TInsectsCollectionOnFarmer> getRecordList(Pageable pageable,TInsectsCollectionOnFarmer queryParm,
			HttpSession session) throws Exception{
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username = "";
		if(p != null){
			username = p.getUsername();
		}else{
			throw new Exception("session is losed!");
		}
		queryParm.setCollector(username);
		Page<TInsectsCollectionOnFarmer> map = farmerCollectionService.findPage(pageable, queryParm);
		return map;
	}
	
	@RequestMapping(value="/recordAddEntrance")
	public String recordAddEntrance(Model model){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String smCollection = sdf.format(new Date()) + getRandomNumber(6);
		model.addAttribute("smCollection", smCollection);
		return "/investor/farmer/recordAdd";
	}
	
	@RequestMapping(value="/recordAdd")
	@ResponseBody
	public Json recordAdd(HttpSession session, HttpServletRequest req, @RequestParam(value="insectsCollectPics_file")MultipartFile[] picFiles) throws Exception{
		Json j = new Json();
		
		// 粮食信息
		String smFarmer = req.getParameter("smFarmer");	// 农户信息
		String smCollection = req.getParameter("smCollection");
		/*String mobile = req.getParameter("mobile");
		String company = req.getParameter("company");*/
		String grainname = req.getParameter("grainname");
		String harvestdate = req.getParameter("harvestdate");
		String dateCollection = req.getParameter("dateCollection");
		String dryingmethod = req.getParameter("dryingmethod");
		String entrydate = req.getParameter("entrydate");
		String storeperiodStr = req.getParameter("storeperiod");
		Integer storeperiod = null;
		if(isValidate(storeperiodStr)){
			storeperiod = Integer.parseInt(storeperiodStr);
		}
		String innumStr = req.getParameter("innum");
		Integer innum = null;
		if(isValidate(innumStr)){
			innum = Integer.parseInt(innumStr);
		}
		String purpose = req.getParameter("purpose");
		String storetechnology = req.getParameter("storetechnology");
		
		// 粮情信息
		String bintype = req.getParameter("bintype");
		String container = req.getParameter("container");
		String temperatureStr = req.getParameter("temperature");
		Float temperature = null;
		if(isValidate(temperatureStr)){
			temperature = Float.parseFloat(temperatureStr);
		}
		String humidityStr = req.getParameter("humidity");
		Float humidity = null;
		if(isValidate(humidityStr)){
			humidity = Float.parseFloat(humidityStr);
		}
		String moistureStr = req.getParameter("moisture");
		Float moisture = null;
		if(isValidate(moistureStr)){
			moisture = Float.parseFloat(moistureStr);
		}
		String impurityStr = req.getParameter("impurity");
		Float impurity = null;
		if(isValidate(impurityStr)){
			impurity = Float.parseFloat(impurityStr);
		}
		String controltemperaturemeasures = req.getParameter("controltemperaturemeasures");
		String controlhumiditymeasures = req.getParameter("controlhumiditymeasures");
		
		// 虫害信息
		String[] locCollect = req.getParameterValues("insectsInfoOnFarmers_locCollect");
		String[] host = req.getParameterValues("insectsInfoOnFarmers_host");
		String[] kind = req.getParameterValues("insectsInfoOnFarmers_kind");
		String[] stage = req.getParameterValues("insectsInfoOnFarmers_stage");
		String[] numStr = req.getParameterValues("insectsInfoOnFarmers_num");
		String[] food = req.getParameterValues("insectsInfoOnFarmers_food");
		String[] harm = req.getParameterValues("insectsInfoOnFarmers_harm");
		String[] protectmeasure = req.getParameterValues("insectsInfoOnFarmers_protectmeasure");
		
		String[] titleStrs = req.getParameterValues("insectsCollectPics_title");
		String[] orderStrs = req.getParameterValues("insectsCollectPics_order");
		
		// 这里的手机号、公司名暂无
		TInsectsCollectionOnFarmer insectsCollection = 
				new TInsectsCollectionOnFarmer(smCollection, "", "", grainname, 
						harvestdate, dryingmethod, entrydate, 
						storeperiod, innum, purpose, storetechnology, 
						bintype, container, temperature, humidity, moisture, 
						impurity, controltemperaturemeasures, controlhumiditymeasures);
		
		// 农户虫害信息
		TInsectsInfoOnFarmer insectsInfo = null;
		int len = 0;
		Integer num = null;
		List<TInsectsInfoOnFarmer> insectsInfoOnFarmers = new ArrayList<TInsectsInfoOnFarmer>(0);
		if(locCollect != null){
			for(int i=0;i<locCollect.length;i++){
				if(isValidate(locCollect[i]) || isValidate(host[i])
						|| isValidate(food[i]) || isValidate(harm[i])
						|| isValidate(kind[i]) || isValidate(numStr[i])
						|| isValidate(protectmeasure[i]) || isValidate(stage[i])
						){
					if(isValidate(numStr[i])){
						num = Integer.parseInt(numStr[i]);
					}
					insectsInfo = new TInsectsInfoOnFarmer(kind[i], stage[i], num, food[i], harm[i], protectmeasure[i], locCollect[i], host[i]);
					insectsInfo.setTInsectsCollectionOnFarmer(insectsCollection);
					insectsInfoOnFarmers.add(insectsInfo);
					len ++;
				}
			}
		}	
		
		// 农户采虫图片
		MultipartFile picFile = null;
		TInsectsCollectPic insectsCollectPic = null;
		Integer order = null;
		Set<TInsectsCollectPic> insectsCollectPics = new HashSet<TInsectsCollectPic>(0);
		for(int i=0;i<picFiles.length;i++){
			
			picFile = picFiles[i];
			if(fileService.isValid(FileType.image, picFile)){
				if(isValidate(orderStrs[i])){
					order = Integer.parseInt(orderStrs[i]);
				}
				insectsCollectPic = new TInsectsCollectPic(titleStrs[i], order, picFile);
				
				String ret = farmerCollectionPicService.build(insectsCollectPic);
				if(ret != null){
					insectsCollectPic.setTInsectsCollectionOnFarmer(insectsCollection);
					insectsCollectPics.add(insectsCollectPic);
				}
			}
		}
		
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username = "";
		if(p != null){
			username = p.getUsername();
		}else{
			throw new Exception("session is losed!");
		}
		Farmer farmer = farmerService.findBySMFarmer(smFarmer);
		// 最后对采集记录 持久化
		insectsCollection.setTotalinsects(len);
		insectsCollection.setTFarmer(farmer);
		insectsCollection.setModifer(username);  // 添加录入人信息
		insectsCollection.setCollector(username);  // 采集者和录入者设置为同一人
		/*insectsCollection.setMobile(mobile);
		insectsCollection.setCompany(company);*/
		insectsCollection.setDateCollection(dateCollection);	// 采集日期设置
		insectsCollection.setModifydate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		
		insectsCollection.setInsectsInfoOnFarmers(insectsInfoOnFarmers);
		insectsCollection.setInsectsCollectPics(insectsCollectPics);
		farmerCollectionService.save(insectsCollection);
		
		j.setSuccess(true);
		j.setMsg("记录添加成功");
		return j;
	}
	
	@RequestMapping(value="/recordEditEntrance")
	public String recordEditEntrance(String smCollection, Model model){
		TInsectsCollectionOnFarmer insectsCollection = farmerCollectionService.findBySMCollection(smCollection);
		Farmer farmer = insectsCollection.getTFarmer();
		model.addAttribute("smFarmer", farmer.getSmFarmer());
		model.addAttribute("insectsCollectionRecord", insectsCollection);
		return "/investor/farmer/recordEdit";
	}
	
	@RequestMapping(value="/recordEdit")
	@ResponseBody
	public Json recordEdit(HttpServletRequest req, @RequestParam(value="insectsCollectPics_file")MultipartFile[] picFiles, HttpSession session){
		Json j = new Json();
		
		// 粮食信息
		String smFarmer = req.getParameter("smFarmer");	// 农户信息
		String smCollection = req.getParameter("smCollection");
		/*String mobile = req.getParameter("mobile");
		String company = req.getParameter("company");*/
		String dateCollection = req.getParameter("dateCollection");
		String grainname = req.getParameter("grainname");
		String harvestdate = req.getParameter("harvestdate");
		String dryingmethod = req.getParameter("dryingmethod");
		String entrydate = req.getParameter("entrydate");
		String storeperiodStr = req.getParameter("storeperiod");
		Integer storeperiod = null;
		if(isValidate(storeperiodStr)){
			storeperiod = Integer.parseInt(storeperiodStr);
		}
		String innumStr = req.getParameter("innum");
		Integer innum = null;
		if(isValidate(innumStr)){
			innum = Integer.parseInt(innumStr);
		}
		String purpose = req.getParameter("purpose");
		String storetechnology = req.getParameter("storetechnology");
		
		// 粮情信息
		String bintype = req.getParameter("bintype");
		String container = req.getParameter("container");
		String temperatureStr = req.getParameter("temperature");
		Float temperature = null;
		if(isValidate(temperatureStr)){
			temperature = Float.parseFloat(temperatureStr);
		}
		String humidityStr = req.getParameter("humidity");
		Float humidity = null;
		if(isValidate(humidityStr)){
			humidity = Float.parseFloat(humidityStr);
		}
		String moistureStr = req.getParameter("moisture");
		Float moisture = null;
		if(isValidate(moistureStr)){
			moisture = Float.parseFloat(moistureStr);
		}
		String impurityStr = req.getParameter("impurity");
		Float impurity = null;
		if(isValidate(impurityStr)){
			impurity = Float.parseFloat(impurityStr);
		}
		String controltemperaturemeasures = req.getParameter("controltemperaturemeasures");
		String controlhumiditymeasures = req.getParameter("controlhumiditymeasures");
		
		// 虫害信息
		String[] idStrs = req.getParameterValues("insectsInfoOnFarmers_id");
		String[] locCollect = req.getParameterValues("insectsInfoOnFarmers_locCollect");
		String[] host = req.getParameterValues("insectsInfoOnFarmers_host");
		String[] kind = req.getParameterValues("insectsInfoOnFarmers_kind");
		String[] stage = req.getParameterValues("insectsInfoOnFarmers_stage");
		String[] numStr = req.getParameterValues("insectsInfoOnFarmers_num");
		String[] food = req.getParameterValues("insectsInfoOnFarmers_food");
		String[] harm = req.getParameterValues("insectsInfoOnFarmers_harm");
		String[] protectmeasure = req.getParameterValues("insectsInfoOnFarmers_protectmeasure");
		
		// 虫害采集图片
		String[] picIdStrs = req.getParameterValues("insectsCollectPics_id");
		String[] titleStrs = req.getParameterValues("insectsCollectPics_title");
		String[] orderStrs = req.getParameterValues("insectsCollectPics_order");
		/*String[] sourceStrs = req.getParameterValues("insectsCollectPics_source");
		String[] largeStrs = req.getParameterValues("insectsCollectPics_large");
		String[] mediumStrs = req.getParameterValues("insectsCollectPics_medium");*/
		String[] thumbnailStrs = req.getParameterValues("insectsCollectPics_thumbnail");
		
		// 这里的手机号、公司名暂无
		TInsectsCollectionOnFarmer insectsCollection = 
				new TInsectsCollectionOnFarmer(smCollection, "", "", grainname, 
						harvestdate, dryingmethod, entrydate, 
						storeperiod, innum, purpose, storetechnology, 
						bintype, container, temperature, humidity, moisture, 
						impurity, controltemperaturemeasures, controlhumiditymeasures);
		
		try {
			// 农户虫害信息
			TInsectsInfoOnFarmer insectsInfo = null;
			int len = 0;
			Integer num = null;
			List<TInsectsInfoOnFarmer> insectsInfoOnFarmers = new ArrayList<TInsectsInfoOnFarmer>(0);
			if(locCollect != null){
				for(int i=0;i<locCollect.length;i++){
					if(isValidate(locCollect[i]) || isValidate(host[i])
							|| isValidate(food[i]) || isValidate(harm[i])
							|| isValidate(kind[i]) || isValidate(numStr[i])
							|| isValidate(protectmeasure[i]) || isValidate(stage[i])
							){
						if(isValidate(numStr[i])){
							num = Integer.parseInt(numStr[i]);
						}
						insectsInfo = new TInsectsInfoOnFarmer(kind[i], stage[i], num, food[i], harm[i], protectmeasure[i], locCollect[i], host[i]);
						Integer id = null;
						if(idStrs!=null && idStrs.length>i && idStrs[i] != null){
							id = Integer.parseInt(idStrs[i]);
							insectsInfo.setId(id);
						}
//						if(id[i] == null){
							insectsInfo.setTInsectsCollectionOnFarmer(insectsCollection);
							insectsInfoOnFarmers.add(insectsInfo);
//						}
						len ++;
					}
				}
			}	
			
			// 农户采虫图片
			MultipartFile picFile = null;
			TInsectsCollectPic insectsCollectPic = null;
			Integer order = null;
			Set<TInsectsCollectPic> insectsCollectPics = new HashSet<TInsectsCollectPic>(0);
			for(int i=0;i<picFiles.length;i++){
				
				picFile = picFiles[i];
				
				if(isValidate(orderStrs[i])){
					order = Integer.parseInt(orderStrs[i]);
				}
				insectsCollectPic = new TInsectsCollectPic(titleStrs[i], order, picFile);
				String ret = null;
				Long picId = null;
				//如果图片不存在，则上传
				if(thumbnailStrs!=null && thumbnailStrs.length>i && thumbnailStrs[i] == null){
					if(fileService.isValid(FileType.image, picFile)){
						j.setSuccess(false);
						j.setMsg(SpringUtils.getMessage("admin.upload.invalid"));
						return j;
					}
					
					ret = farmerCollectionPicService.build(insectsCollectPic);
					if(ret != null){
						insectsCollectPic.setTInsectsCollectionOnFarmer(insectsCollection);
						insectsCollectPics.add(insectsCollectPic);
					}
				}else{ // 图片已经存在，也上传
					if(picIdStrs!=null && picIdStrs.length>i && picIdStrs[i] != null){
						picId = Long.parseLong(picIdStrs[i]);
					}
					String ss = picFile.getOriginalFilename();
					
					if(ss==null || ss.equals("")){// 此次没有修改上传文件
						Set<TInsectsCollectPic> icp = farmerCollectionService.findBySMCollection(smCollection).getInsectsCollectPics();
						TInsectsCollectPic ticp = null;
						for(Iterator<TInsectsCollectPic> iterator = icp.iterator();iterator.hasNext();){
							ticp = iterator.next();
							if(ticp.getId() == picId){
								insectsCollectPic.setId(picId);
								insectsCollectPic.setLarge(ticp.getLarge());
								insectsCollectPic.setMedium(ticp.getMedium());
								insectsCollectPic.setSource(ticp.getSource());
								insectsCollectPic.setThumbnail(ticp.getThumbnail());
								
								insectsCollectPic.setTInsectsCollectionOnFarmer(insectsCollection);
								insectsCollectPics.add(insectsCollectPic);
								break;
							}
						}
					}else{	// 修改了上传文件
						/*if(fileService.isValid(FileType.image, picFile)){
							j.setSuccess(false);
							j.setMsg(SpringUtils.getMessage("admin.upload.invalid"));
							return j;
						}*/
						ret = farmerCollectionPicService.build(insectsCollectPic);
						if(ret != null){
							insectsCollectPic.setId(picId);
							insectsCollectPic.setTInsectsCollectionOnFarmer(insectsCollection);
							insectsCollectPics.add(insectsCollectPic);
						}
					}
				}
			}
			Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
			String username = "";
			if(p != null){
				username = p.getUsername();
			}else{
				throw new Exception("session is losed!");
			}
			// 最后对采集记录 持久化
			insectsCollection.setTotalinsects(len);
			insectsCollection.setTFarmer(farmerService.findBySMFarmer(smFarmer));
			/*insectsCollection.setMobile(mobile);
			insectsCollection.setCompany(company);*/
			insectsCollection.setDateCollection(dateCollection);	// 采集日期设置
			insectsCollection.setCollector(username);
			insectsCollection.setModifydate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			
			insectsCollection.setInsectsInfoOnFarmers(insectsInfoOnFarmers);
			insectsCollection.setInsectsCollectPics(insectsCollectPics);
			
			try{
				farmerCollectionService.update(insectsCollection);
				
				j.setSuccess(true);
				j.setMsg("记录修改成功");
			}catch (Exception e) {
				j.setSuccess(false);
				j.setMsg(e.getMessage());
			}
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping(value="/recordDelete")
	@ResponseBody
	public Json recordDelete(String smCollection){
		Json json = new Json();
		 /*TInsectsCollectionOnFarmer record = farmerCollectionService.findBySMCollection(smCollection);
		 if(record.getInsectsInfoOnFarmers()!=null && !record.getInsectsInfoOnFarmers().isEmpty()){
			 json.setSuccess(false);
			 json.setMsg("存在关联虫害信息调查表数据，无法删除!");
			 return json;
		 }
		 if(record.getInsectsCollectPics()!=null && !record.getInsectsCollectPics().isEmpty()){
			 json.setSuccess(false);
			 json.setMsg("存在关联采虫图片数据，无法删除!");
			 return json;
		 }*/
		 try {
			farmerCollectionService.delete(smCollection);
			json.setSuccess(true);
			json.setMsg("删除成功");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	/**
	 * 判断s是否为空或者是否为空白字符串
	 * @param s
	 * @return
	 */
	private boolean isValidate(String s){
		boolean flag = true;
		
		if(s==null || s.trim().equals(""))
			flag = false;
		
		return flag;
		
	}
	
	private static String getRandomNumber(int len){ 
		char[] c = {'0','1','2','3','4','5','6','7','8','9'};
		Random r = new Random();
		StringBuffer sb = new StringBuffer();
		int k;
		for(int i=0;i<len;i++){
			k = r.nextInt();
			sb.append(c[Math.abs(k % 10)]);
		}
		return sb.toString();
	}
}

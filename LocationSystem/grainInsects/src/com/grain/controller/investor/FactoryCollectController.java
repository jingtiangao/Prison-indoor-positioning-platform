package com.grain.controller.investor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.FileInfo.FileType;
import com.grain.CommonAttributes;
import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.Principal;
import com.grain.entity.FactoryInfo;
import com.grain.entity.InsectsOnfactory;
import com.grain.entity.TFactoryPic;
import com.grain.entity.TInsectsInfactory;
import com.grain.service.FactoryCollectionPicService;
import com.grain.service.FactoryCollectionService;
import com.grain.service.FactoryInfoService;
import com.grain.service.FileService;
import com.grain.util.SpringUtils;


@Controller
@RequestMapping("/investor/factory")
public class FactoryCollectController{
	
	@Resource(name="factoryServiceImpl")
	private FactoryInfoService factoryInfoService;
	
	@Resource(name="factoryCollectionServiceImpl")
	private FactoryCollectionService factoryCollectionService;
	
	@Resource(name = "fileServiceImpl")	//file
	private FileService fileService;
	
	@Resource(name="factoryCollectionPicServiceImpl")
	private FactoryCollectionPicService factoryCollectionPicService;
	
	/**
	 * 进入加工厂信息列表
	 * @return
	 */
	@RequestMapping(value="/factoryInfoList")
	public String factoryInfoList(){
		return "/investor/factory/factoryInfoList";
	}
	
	@RequestMapping("/getFactoryList")
	@ResponseBody
	public Page<FactoryInfo> getList(Pageable pageable,FactoryInfo queryParm,
			HttpSession session) throws Exception{
//		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		Page<FactoryInfo> map=factoryInfoService.findPage(pageable,queryParm);
		return map;
	}
	
	@RequestMapping(value="/factoryAddEntrance")
	public String factoryCollectFactoryAddEntrance(Model model, HttpSession session) throws Exception{
		
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username = "";
		if(p != null){
			username = p.getUsername();
		}else{
			throw new Exception("session is losed!");
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String smFactory = username + sdf.format(new Date()) + getRandomNumber(6);
		model.addAttribute("smFactory", smFactory);
		return "/investor/factory/factoryAdd";
	}
	
	@RequestMapping(value="/factoryAdd")
	@ResponseBody
	public Json factoryAdd(FactoryInfo factoryInfo, HttpServletRequest req, HttpSession session) throws Exception{
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username = "";
		if(p != null){
			username = p.getUsername();
		}else{
			throw new Exception("session is losed!");
		}
		Json j = new Json();
		try {
			factoryInfo.setModifer(username);
			factoryInfoService.save(factoryInfo);
			j.setSuccess(true);
			j.setMsg("添加成功");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping(value="editFactoryEntrance")
	public String editFactoryEntrance(String smFactory, Model model){
		model.addAttribute("factory", factoryInfoService.findBySMFactory(smFactory));
		return "/investor/factory/factoryEdit";
	}
	
	@RequestMapping(value="factoryEdit")
	@ResponseBody
	public Json factoryEdit(FactoryInfo factoryInfo){
		Json json = new Json();
		
		try {
			if(factoryInfo != null){
				factoryInfoService.update(factoryInfo, "factoryusers", "insectsOnfactories");
			}
			json.setSuccess(true);
			json.setMsg("修改成功");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	@RequestMapping(value="factoryDelete")
	@ResponseBody
	public Json factoryDelete(String smFactory){
		Json json = new Json();
		FactoryInfo factory = factoryInfoService.findBySMFactory(smFactory);
		if(factory.getInsectsOnfactories() != null && !factory.getInsectsOnfactories().isEmpty()){
			json.setSuccess(false);
			json.setMsg("存在关联虫害信息记录数据，无法删除!");
			return json;
		}
		try {
			factoryInfoService.delete(smFactory);
			json.setSuccess(true);
			json.setMsg("删除成功");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	/**
	 * 进入加工厂采集记录列表
	 * @return
	 */
	@RequestMapping(value="/recordList")
	public String recordList(){
		return "/investor/factory/recordList";
	}
	
	@RequestMapping(value="/getRecordList")
	@ResponseBody
	public Page<InsectsOnfactory> getRecordList(Pageable pageable,InsectsOnfactory queryParm,
			HttpSession session) throws Exception{
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username = "";
		if(p != null){
			username = p.getUsername();
		}else{
			throw new Exception("session is losed!");
		}
		queryParm.setCollector(username);
		Page<InsectsOnfactory> map = factoryCollectionService.findPage(pageable, queryParm);
		return map;
	}
	
	@RequestMapping(value="/recordAddEntrance", method=RequestMethod.POST)
	public String recordAddEntrance(Model model){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String smCollect = sdf.format(new Date()) + getRandomNumber(6);
		model.addAttribute("smCollect", smCollect);
		
		return "/investor/factory/recordAdd";
	}
	
	@RequestMapping(value="/recordAdd")
	@ResponseBody
	public Json recordAdd(InsectsOnfactory insectsOnfactory, String smFactory, Model model, HttpServletRequest req, HttpSession session) throws Exception{
		Json j = new Json();
		model.addAttribute("smFactory", smFactory);
		
		// 过滤无用虫害信息
		for(Iterator<TInsectsInfactory> iterator = insectsOnfactory.getTInsectsInfactories().iterator();iterator.hasNext();){
			TInsectsInfactory insectsInfactory = iterator.next();
			if(insectsInfactory == null || isEmpty(insectsInfactory)){
				iterator.remove();
				continue;
			}
			insectsInfactory.setInsectsOnfactory(insectsOnfactory);
		}
		
		// 过滤无用图片信息
		for(Iterator<TFactoryPic> iterator = insectsOnfactory.getTFactoryPics().iterator();iterator.hasNext();){
			TFactoryPic factoryPic = iterator.next();
			if(factoryPic == null || factoryPic.isEmpty()){
				iterator.remove();
				continue;
			}
			
			if(factoryPic.getFile() != null && !factoryPic.getFile().isEmpty()){
				if (!fileService.isValid(FileType.image, factoryPic.getFile())) {
					j.setSuccess(false);
					j.setMsg(SpringUtils.getMessage("文件格式/大小无效"));
					return j;
				}
			}
			factoryPic.setInsectsOnfactory(insectsOnfactory);
		}
		
		for(TFactoryPic pic : insectsOnfactory.getTFactoryPics()){
			factoryCollectionPicService.build(pic);
		}
		
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username = "";
		if(p != null){
			username = p.getUsername();
		}else{
			throw new Exception("session is losed!");
		}
		// 暂设置
		FactoryInfo factoryInfo = factoryInfoService.findBySMFactory(smFactory);
		insectsOnfactory.setTFactoryInfo(factoryInfo);
		insectsOnfactory.setModifer(username);   //  添加录入人信息
		insectsOnfactory.setCollector(username);  // 采集者
		insectsOnfactory.setModifydate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		insectsOnfactory.setTotalinsects(insectsOnfactory.getTInsectsInfactories().size());
		try {
			factoryCollectionService.save(insectsOnfactory);
			j.setSuccess(true);
			j.setMsg(SpringUtils.getMessage("添加成功"));
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping(value="/recordDetail", method=RequestMethod.GET)
	public String recordDetail(String smCollect, String smFactory, Model model){
		model.addAttribute("smFactory", smFactory);
		
		model.addAttribute("factoryCollectRecord", factoryCollectionService.findBySMCollection(smCollect));
		return "/investor/factory/recordDetail";
	}
	
	
	@RequestMapping(value="/recordEditEntrance")
	public String recordEditEntrance(String smCollect, Model model){
		InsectsOnfactory insectsOnfactory = factoryCollectionService.findBySMCollection(smCollect);
		FactoryInfo factoryInfo = insectsOnfactory.getTFactoryInfo();
		model.addAttribute("smFactory", factoryInfo.getSmFactory());
		model.addAttribute("factoryCollectRecord", insectsOnfactory);
		model.addAttribute("picTotal", insectsOnfactory.getTFactoryPics().size());
		model.addAttribute("insectsInfoTotal", insectsOnfactory.getTInsectsInfactories().size());
		return "/investor/factory/recordEdit";
	}
	
	@RequestMapping(value="/recordEdit")
	@ResponseBody
	public Json recordEdit(InsectsOnfactory insectsOnfactory, String smCollect, String smFactory, Model model, HttpSession session) throws Exception{
//		model.addAttribute("smFactory", smFactory);
		Json json = new Json();
		
		// 过滤无用虫害信息
		for(Iterator<TInsectsInfactory> iterator = insectsOnfactory.getTInsectsInfactories().iterator();iterator.hasNext();){
			TInsectsInfactory insectsInfactory = iterator.next();
			if(insectsInfactory == null || isEmpty(insectsInfactory)){
				iterator.remove();
				continue;
			}
			insectsInfactory.setInsectsOnfactory(insectsOnfactory);
		}
		
		// 过滤无用图片信息
		for(Iterator<TFactoryPic> iterator = insectsOnfactory.getTFactoryPics().iterator();iterator.hasNext();){
			TFactoryPic factoryPic = iterator.next();
			if(factoryPic == null){
				iterator.remove();
				continue;
			}
			
			if(factoryPic.getFile() != null && !factoryPic.getFile().isEmpty()){
				if (!fileService.isValid(FileType.image, factoryPic.getFile())) {
					json.setSuccess(false);
					json.setMsg(SpringUtils.getMessage("文件格式/大小无效"));
					return json;
				}
			}
			factoryPic.setInsectsOnfactory(insectsOnfactory);
			
			// 判断图片是否重新上传，或者是否增加图片
			if(factoryPic.getId()==null || 
					(factoryPic.getFile()!=null && factoryPic.getFile().getOriginalFilename()!=null && !factoryPic.getFile().getOriginalFilename().trim().equals(""))
					){
				factoryCollectionPicService.build(factoryPic);
			}
		}
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username = "";
		if(p != null){
			username = p.getUsername();
		}else{
			throw new Exception("session is losed!");
		}
		
		// 暂设置
		insectsOnfactory.setTFactoryInfo(factoryInfoService.findBySMFactory(smFactory));
		insectsOnfactory.setTotalinsects(insectsOnfactory.getTInsectsInfactories().size());
		insectsOnfactory.setCollector(username);
		insectsOnfactory.setCollectdate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		try {
			factoryCollectionService.update(insectsOnfactory);
			json.setSuccess(true);
			json.setMsg(SpringUtils.getMessage("修改成功"));
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping(value="recordDelete")
	@ResponseBody
	public Json recordDelete(String smCollect){
		Json json = new Json();
		try {
			factoryCollectionService.delete(smCollect);
			json.setSuccess(true);
			json.setMsg("删除");
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
	
	/**
	 * 判断该TInsectsInfactory是否每个属性都为空
	 * @return
	 */
	public boolean isEmpty(TInsectsInfactory insectsInfactory){
		if(isValidate(insectsInfactory.getLocCollect()) || isValidate(insectsInfactory.getKind())
				|| isValidate(insectsInfactory.getHarm()) || isValidate(insectsInfactory.getFood())
				|| isValidate(insectsInfactory.getStage()) || isValidate(insectsInfactory.getProtectmeasure())
				|| isValidate(insectsInfactory.getHost()) || (insectsInfactory.getNum()!=null))
			return false;
		return true;
		
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

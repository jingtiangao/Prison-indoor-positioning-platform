package com.grain.controller.investor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.FileInfo.FileType;
import com.grain.CommonAttributes;
import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.Principal;
import com.grain.entity.Grainbin;
import com.grain.entity.Graindepot;
import com.grain.entity.TInsectpicIndepot;
import com.grain.entity.TInsectsInbinOnDepot;
import com.grain.entity.TInsectsOndepot;
import com.grain.service.DepotCollectionPicService;
import com.grain.service.DepotCollectionService;
import com.grain.service.FileService;
import com.grain.service.GrainbinService;
import com.grain.service.GraindepotService;
import com.grain.util.SpringUtils;


@Controller
@RequestMapping("/investor/depot")
public class DepotCollectController{
	
	@Resource(name="grainDepotServiceImpl")
	GraindepotService depotService;	//粮库
	
	@Resource(name="grainBinServiceImpl")
	private GrainbinService grainbinService; // 粮仓
	
	@Resource(name="depotCollectionServiceImpl")
	private DepotCollectionService depotCollectionService;
	
	@Resource(name = "fileServiceImpl")	//file
	private FileService fileService;
	
	@Resource(name="depotCollectionPicServiceImpl")
	private DepotCollectionPicService depotCollectionPicService;
	
	@RequestMapping(value="/recordList")
	public String recordList(){
		return "/investor/depot/recordList";
	}
	
	@RequestMapping(value="getDepotList")
	@ResponseBody
	public Page<Graindepot> getDepotList(Pageable pageable, Graindepot queryParam, HttpSession session){
		Page<Graindepot> map = depotService.findPage(pageable, queryParam);
		return map;
	}
	
	@RequestMapping(value="getBinList")
	@ResponseBody
	public Page<Grainbin> getBinList(Pageable pageable, Grainbin queryParam, HttpSession session){
		Page<Grainbin> map = grainbinService.findPage(pageable, queryParam);
		return map;
	}
	
	@RequestMapping(value="/getRecordList")
	@ResponseBody
	public Page<TInsectsInbinOnDepot> getRecordList(Pageable pageable, TInsectsInbinOnDepot queryParam, HttpSession session) throws Exception{
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username = "";
		if(p != null){
			username = p.getUsername();
		}else{
			throw new Exception("session is losed!");
		}
		queryParam.setCollector(username);
		Page<TInsectsInbinOnDepot> map = depotCollectionService.findPage(pageable, queryParam);
		return map;
	}
	
	@RequestMapping(value="getGrainbin")
	@ResponseBody
	public Map<String, Object> getGrainbin(String smCollection){
		Map<String, Object> map = new HashMap<String, Object>();
		Grainbin bin = depotCollectionService.findBySMCollection(smCollection).getTGrainbin();
		map.put("lcbm", bin.getLcbm());
		map.put("lcm", bin.getBinname());
		map.put("contract", bin.getContract());
		return map;
	}
	
	@RequestMapping(value="/recordAddEntrance")
	public String recordAddEntrance(String lcbm, Model model){
		model.addAttribute("lcbm", lcbm);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String smCollection = sdf.format(new Date()) + getRandomNumber(6);
		model.addAttribute("smCollection", smCollection);
		
		return "/investor/depot/recordAdd";
	}
	
	@RequestMapping(value="/recordAdd")
	@ResponseBody
	public Json recordAdd(TInsectsInbinOnDepot insectsInbinOnDepot, String lcbm, Model model, HttpServletRequest req, HttpSession session) throws Exception{
		Json json = new Json();
		
		// 过滤无用虫害信息
		for(Iterator<TInsectsOndepot> iterator = insectsInbinOnDepot.getTInsectsOndepots().iterator();iterator.hasNext();){
			TInsectsOndepot insectsOndepot = iterator.next();
			if(insectsOndepot == null || isEmpty(insectsOndepot)){
				iterator.remove();
				continue;
			}
			insectsOndepot.setTInsectsInbinOnDepot(insectsInbinOnDepot);
		}
		
		// 过滤无用图片信息
		for(Iterator<TInsectpicIndepot> iterator = insectsInbinOnDepot.getTInsectpicIndepots().iterator();iterator.hasNext();){
			TInsectpicIndepot insectPic = iterator.next();
			if(insectPic == null || insectPic.isEmpty()){
				iterator.remove();
				continue;
			}
			
			if(insectPic.getFile() != null && !insectPic.getFile().isEmpty()){
				if (!fileService.isValid(FileType.image, insectPic.getFile())) {
					json.setSuccess(false);
					json.setMsg(SpringUtils.getMessage("文件格式/大小无效"));
					return json;
				}
			}
			insectPic.setTInsectsInbinOnDepot(insectsInbinOnDepot);
		}
		
		for(TInsectpicIndepot pic : insectsInbinOnDepot.getTInsectpicIndepots()){
			depotCollectionPicService.build(pic);
		}
		
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username = "";
		if(p != null){
			username = p.getUsername();
		}else{
			throw new Exception("session is losed!");
		}
		
		// 维护父子关系
		insectsInbinOnDepot.setTGrainbin(grainbinService.find(lcbm));
		try {
			insectsInbinOnDepot.setModifer(username);  // 录入者
			insectsInbinOnDepot.setCollector(username);  // 采集者
			insectsInbinOnDepot.setModifydate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			depotCollectionService.save(insectsInbinOnDepot);
			json.setSuccess(true);
			json.setMsg(SpringUtils.getMessage("添加成功"));
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	@RequestMapping(value="/recordEditEntrance")
	public String recordEditEntrance(String smCollection, Model model){
		TInsectsInbinOnDepot insectsInbinOnDepot = depotCollectionService.findBySMCollection(smCollection);
		Grainbin grainbin = insectsInbinOnDepot.getTGrainbin();
		Graindepot graindepot = grainbin.getGraindepot();
		model.addAttribute("lcbm", grainbin.getLcbm());
		model.addAttribute("lkbm", graindepot.getLkbm());
		
		model.addAttribute("depotCollectRecord", insectsInbinOnDepot);
		model.addAttribute("picTotal", insectsInbinOnDepot.getTInsectpicIndepots().size());
		model.addAttribute("insectsInfoTotal", insectsInbinOnDepot.getTInsectsOndepots().size());
		
		return "/investor/depot/recordEdit";
	}
	
	@RequestMapping(value="/recordEdit")
	@ResponseBody
	public Json recordEdit(TInsectsInbinOnDepot insectsInbinOnDepot, String lcbm, Model model, HttpServletRequest req, HttpSession session) throws Exception{
		Json json = new Json();
		
		// 过滤无用虫害信息
		for(Iterator<TInsectsOndepot> iterator = insectsInbinOnDepot.getTInsectsOndepots().iterator();iterator.hasNext();){
			TInsectsOndepot insectsOndepot = iterator.next();
			if(insectsOndepot == null || isEmpty(insectsOndepot)){
				iterator.remove();
				continue;
			}
			insectsOndepot.setTInsectsInbinOnDepot(insectsInbinOnDepot);
		}
		
		// 过滤无用图片信息
		for(Iterator<TInsectpicIndepot> iterator = insectsInbinOnDepot.getTInsectpicIndepots().iterator();iterator.hasNext();){
			TInsectpicIndepot insectPic = iterator.next();
			if(insectPic == null){
				iterator.remove();
				continue;
			}
			
			if(insectPic.getFile() != null && !insectPic.getFile().isEmpty()){
				if (!fileService.isValid(FileType.image, insectPic.getFile())) {
					json.setSuccess(false);
					json.setMsg(SpringUtils.getMessage("文件格式/大小无效"));
					return json;
				}
			}
			insectPic.setTInsectsInbinOnDepot(insectsInbinOnDepot);
			
			// 判断图片是否重新上传，或者是否增加图片
			if(insectPic.getId()==null || 
					(insectPic.getFile()!=null && insectPic.getFile().getOriginalFilename()!=null && !insectPic.getFile().getOriginalFilename().trim().equals(""))
					){
				depotCollectionPicService.build(insectPic);
			}
		}
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username = "";
		if(p != null){
			username = p.getUsername();
		}else{
			throw new Exception("session is losed!");
		}
		
		// 维护父子关系
		insectsInbinOnDepot.setTGrainbin(grainbinService.find(lcbm));
		insectsInbinOnDepot.setCollector(username);
		insectsInbinOnDepot.setModifydate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		try {
			depotCollectionService.update(insectsInbinOnDepot);
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
	public Json recordDelete(String smCollection){
		Json json = new Json();
		
		try {
			depotCollectionService.delete(smCollection);
			json.setSuccess(true);
			json.setMsg("删除成功");
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		
		return json;
		
	}
	
	
	/**
	 * 判断该TInsectsInfactory是否每个属性都为空
	 * @return
	 */
	public boolean isEmpty(TInsectsOndepot insectsOndepot){
		if(isValidate(insectsOndepot.getLocCollect()) || isValidate(insectsOndepot.getKind())
				|| isValidate(insectsOndepot.getHarm()) || isValidate(insectsOndepot.getFood())
				|| isValidate(insectsOndepot.getStage()) || isValidate(insectsOndepot.getMeasureCtrl())
				|| isValidate(insectsOndepot.getHost()) || (insectsOndepot.getNum()!=null))
			return false;
		return true;
		
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

package com.grain.controller.expert;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.CommonAttributes;
import com.grain.Filter;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.Principal;
import com.grain.Filter.Operator;
import com.grain.dao.DeviceRealdataDao;
import com.grain.entity.DeviceRealdata;
import com.grain.entity.DeviceRegister;
import com.grain.entity.Grainbin;
import com.grain.entity.Graindepot;
import com.grain.entity.RealdataPic;
import com.grain.service.DeviceRealdataService;
import com.grain.service.GrainbinService;
import com.grain.service.GraindepotService;

/**
 * 	实时数据列表
 *
 */
@Controller
@RequestMapping("/expert/realdata")
public class ExpertRealDataController {
	
	@Resource(name="deviceRealdataServiceImpl")
	DeviceRealdataService deviceRealdataService;
	
	@Resource(name = "deviceRealdataDaoImpl")
	DeviceRealdataDao deviceRealdataDao;
	
	@Resource(name="grainDepotServiceImpl")
	private GraindepotService graindepotSrv;//粮库
	
	@Resource(name="grainBinServiceImpl")
	GrainbinService binService;	//粮仓
	
	@RequestMapping("/entrance")
	public String Entrance(){
		return "expert/realdata/list";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Page<DeviceRealdata> getDataGrid(Pageable pageable, String lkbm, String lcbm,
			String collectStart, String collectEnd,
			String kind, String source,
			Integer numStart, Integer numEnd,
			Float temperatureStart, Float temperatureEnd,
			Float humidityStart, Float humidityEnd,
			HttpSession session) {
		if(pageable == null) {
			return null;
		}
		DeviceRealdata queryParm = new DeviceRealdata();
		Set<Grainbin> grainbins = null;
		if (lcbm!=null && !lcbm.equals("-1")){
			Grainbin bin = binService.find(lcbm);
			queryParm.setTGrainbin(bin);
			//System.out.println("if:"+lcbm);
		}else{
			//System.out.println("else:"+lcbm);
			grainbins = new HashSet<Grainbin>();
			Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
			if (p!=null){
				List<Graindepot> graindepots = new ArrayList<Graindepot>();
				if(lkbm !=null && !lkbm.equals("-1")) //找到特定示范库里的粮仓数据
					graindepots.add(graindepotSrv.find(lkbm));
				else //找到所有示范库里的数据
					graindepots = graindepotSrv.findModelDepots();
				int depotSize = graindepots.size();
				for (int k=0; k<depotSize; k++){
					Set<Grainbin> Tgrainbins = graindepots.get(k).getGrainbins();
					grainbins.addAll(Tgrainbins);
				}
			}else
				return null;	//最好是在页面上加以控制，必须输入粮仓，否则应该返回所有粮仓的上传记录
		}
		if(kind != null && !kind.equals("")) {
			queryParm.setKind(kind);
		}
		if(source != null && !source.equals("")) {
			queryParm.setSource(source);
		}
		pageable.setFilters(getFilters(queryParm, collectStart, collectEnd,
				numStart, numEnd, temperatureStart, temperatureEnd,
				humidityStart, humidityEnd,grainbins));
		return deviceRealdataService.findPage(pageable,queryParm);
	}
	
	@RequestMapping("/detail")
	public String getDataDetail(Long id, ModelMap model) {
		DeviceRealdata data = deviceRealdataService.find(id);
		Grainbin bin = data.getTGrainbin();
		DeviceRegister register = data.getTDeviceRegister();
		Set<RealdataPic> realdataPics = data.getRealdataPics();
		model.addAttribute("realdata", data);
		model.addAttribute("grainbin", bin);
		model.addAttribute("deviceRegister", register);
		model.addAttribute("realdataPics", realdataPics);
		return "expert/realdata/detail";
	}
	
	
	/**
	 * 示范库名称列表
	 */
	@RequestMapping("/grainDepots")
	@ResponseBody
	public List<Map<String,Object>> grainDepots(HttpSession session) {
		List<Graindepot> graindepots = new ArrayList<Graindepot>();
		List<Map<String,Object>> gCountList = new ArrayList<Map<String,Object>>();//示范库统计
		
		graindepots = graindepotSrv.findModelDepots();
		int size = graindepots.size();
		if(size >0)
		for(int j = 0; j<size;j++){
			Map<String,Object> details = new HashMap<String,Object>();
			String lkbm = graindepots.get(j).getLkbm();
			String lkmc = graindepots.get(j).getLkmc();
			details.put("lkbm", lkbm);
			details.put("lkmc", lkmc);
			gCountList.add(details);
		}
		return gCountList;
	}
	
	
	/**
	 * 粮仓名称列表
	 */
	@RequestMapping("/grainBins")
	@ResponseBody
	public List<Map<String,Object>> grainBins(HttpSession session, String lkbm) {
		List<Map<String,Object>> bCountList = new ArrayList<Map<String,Object>>();//粮仓统计
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null && lkbm!="" && lkbm!=null){
			Graindepot graindepot = graindepotSrv.find(lkbm);
			Set<Grainbin> grainbins = graindepot.getGrainbins();
			for(Grainbin grainbin : grainbins) {
				String lcbm = grainbin.getLcbm();
				Map<String,Object> details = new HashMap<String,Object>();
				details.put("lcbm", lcbm);
				bCountList.add(details);
			}
		}
		return bCountList;
	}
	
	private List<Filter> getFilters(DeviceRealdata t, String collectStart, String collectEnd,
			Integer numStart, Integer numEnd,
			Float temperatureStart, Float temperatureEnd,
			Float humidityStart, Float humidityEnd,
			Set<Grainbin> grainbins) {
		List<Filter> filters = new ArrayList<Filter>();
		Filter fl;
		if (grainbins!=null){
			fl = new Filter();
			fl.setProperty("TGrainbin");
			fl.setOperator(Operator.in);
			fl.setValue(grainbins);
			filters.add(fl);
		}
/*		if(t.getTGrainbin() != null) {  已经在上面加过一次了。
			fl = new Filter();
			fl.setProperty("TGrainbin");
			fl.setValue(t.getTGrainbin());
			fl.setOperator(Operator.eq);
			filters.add(fl);
		}
		if(t.getKind() != null) {
			fl = new Filter();
			fl.setProperty("kind");
			fl.setValue(t.getKind());
			fl.setOperator(Operator.like);
			filters.add(fl);
		}
		if(t.getSource() != null) {
			fl = new Filter();
			fl.setProperty("source");
			fl.setValue(t.getSource());
			fl.setOperator(Operator.like);
			filters.add(fl);
		}*/
		if(collectStart != null && !collectStart.equals("")) {
			fl = new Filter();
			fl.setProperty("collecttime");
			fl.setValue(collectStart);
			fl.setOperator(Operator.ge);
			filters.add(fl);
		}
		if(collectEnd != null && !collectEnd.equals("")) {
			fl = new Filter();
			fl.setProperty("collecttime");
			fl.setValue(collectEnd);
			fl.setOperator(Operator.le);
			filters.add(fl);
		}
		if(numStart != null) {
			fl = new Filter();
			fl.setProperty("num");
			fl.setValue(numStart);
			fl.setOperator(Operator.ge);
			filters.add(fl);
		}
		if(numEnd != null && numEnd > 0) {
			fl = new Filter();
			fl.setProperty("num");
			fl.setValue(numEnd);
			fl.setOperator(Operator.le);
			filters.add(fl);
		}
		if(temperatureStart != null) {
			fl = new Filter();
			fl.setProperty("temperature");
			fl.setValue(temperatureStart);
			fl.setOperator(Operator.ge);
			filters.add(fl);
		}
		if(temperatureEnd != null) {
			fl = new Filter();
			fl.setProperty("temperature");
			fl.setValue(temperatureEnd);
			fl.setOperator(Operator.le);
			filters.add(fl);
		}
		if(humidityStart != null) {
			fl = new Filter();
			fl.setProperty("humidity");
			fl.setValue(humidityStart);
			fl.setOperator(Operator.ge);
			filters.add(fl);
		}
		if(humidityEnd != null) {
			fl = new Filter();
			fl.setProperty("humidity");
			fl.setValue(humidityEnd);
			fl.setOperator(Operator.le);
			filters.add(fl);
		}
		return filters;
	}
	
}

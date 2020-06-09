package com.grain.controller.depoter;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import com.grain.entity.RealdataPic;
import com.grain.entity.user.DepotUser;
import com.grain.service.DeviceRealdataService;
import com.grain.service.GrainbinService;
import com.grain.service.user.DepotUserService;

/**
 * 	实时数据列表
 *
 */
@Controller
@RequestMapping("/depoter/realdata")
public class RealDataController {
	
	@Resource(name="depotUserServiceImpl")
	private DepotUserService depotuserSrv;
	
	@Resource(name="deviceRealdataServiceImpl")
	DeviceRealdataService deviceRealdataService;
	
	@Resource(name = "deviceRealdataDaoImpl")
	DeviceRealdataDao deviceRealdataDao;
	
	@Resource(name="grainBinServiceImpl")
	GrainbinService binService;	//粮仓
	
	@RequestMapping("/entrance")
	public String Entrance(){
		return "depoter/realdata/list";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Page<DeviceRealdata> getDataGrid(Pageable pageable, String lcbm,
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
			System.out.println("if:"+lcbm);
		}else{
			System.out.println("else:"+lcbm);
			grainbins = new HashSet<Grainbin>();
			Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
			if (p!=null){
				DepotUser u = depotuserSrv.find(p.getUsername());
				grainbins = u.getGrainbins();
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
		return "depoter/realdata/detail";
	}
	
	@RequestMapping("/grainBins")
	@ResponseBody
	public String grainBins(HttpSession session) {
		String json = "";
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			Set<Grainbin> grainbins = u.getGrainbins();
			for(Grainbin grainbin : grainbins) {
				String lcbm = grainbin.getLcbm();
				json = json + ",{\"lcbm\":\"" + lcbm + "\"}";
			}
		}
		if(json.length() > 0) {
			json = "[" + json.substring(1) + "]";
		} else {
			json = "[]";
		}
		return json;
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

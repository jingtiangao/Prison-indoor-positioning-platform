package com.grain.controller.expert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.CommonAttributes;
import com.grain.Filter;
import com.grain.Order;
import com.grain.Principal;
import com.grain.Order.Direction;
import com.grain.Principal.UserType;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.Filter.Operator;
import com.grain.entity.Area;
import com.grain.entity.DeviceRealdata;
import com.grain.entity.Grainbin;
import com.grain.entity.Graindepot;
import com.grain.entity.Graindirection;
import com.grain.service.AreaService;
import com.grain.service.DeviceRealdataService;
import com.grain.service.GrainbinService;
import com.grain.service.GraindepotService;
import com.grain.service.GraindirectionService;
import com.grain.service.user.DepotUserService;

/**
 * 实时数据统计，分为用户和专家
 */
@Controller
@RequestMapping("/expert/monitor")
public class MonitorController {
	
	@Resource(name="deviceRealdataServiceImpl")
	DeviceRealdataService deviceRealdataService;//实时数据表
	
	@Resource(name="depotUserServiceImpl")
	private DepotUserService depotuserSrv;//用户
	
	@Resource(name="grainDepotServiceImpl")
	private GraindepotService graindepotSrv;//粮库
	
	@Resource(name = "graindirectionServiceImpl")
	private GraindirectionService gdSrv;//储粮区
	
	@Resource(name = "areaServiceImpl")
	private AreaService areaService;//地区
	
	@Resource(name="grainBinServiceImpl")
	GrainbinService binService;	//粮仓
	
	@RequestMapping(value = "/insectsTemperatureHunmidity", method = RequestMethod.GET)
	public String chartForInsectsTemperatureHumidity() {
		return "expert/monitor/insectsTemperatureHunmidity";
	}
	
	@RequestMapping(value = "/depotLoc", method = RequestMethod.GET)
	public String depotLoc() {
		return "expert/monitor/depotLoc";
	}
	
	@RequestMapping(value = "/insectsLoc", method = RequestMethod.GET)
	public String chartForInsectsLoc() {
		return "expert/monitor/insectsLoc";
	}
	
	@RequestMapping(value = "/ideaListEntrance", method = RequestMethod.GET)
	public String ideaListEntrance() {
		return "expert/monitor/ideaList";
	}
	
	@RequestMapping(value = "/addIdeaEntrance", method = RequestMethod.POST)
	public String addIdeaEntrance() {
		return "expert/monitor/addIdea";
	}
	
	@RequestMapping(value = "/showIdeaEntrance", method = RequestMethod.GET)
	public String showIdeaEntrance() {
		return "expert/monitor/showIdea";
	}
	
	/*
	 * ----------------------------------示范库分布统计------------------------
	 */
	
	/**
	 * 粮库位置列表
	 */
	@RequestMapping("/grainDepotsLoc")
	@ResponseBody
	public List<Object> grainDepotsLocation(HttpSession session, Long directionId, Long areaId, @RequestParam(value = "lkbms[]", required = false) String[] lkbms) {
		List<Graindepot> graindepots = new ArrayList<Graindepot> ();
		if(lkbms==null){
			graindepots = getGrainDepotsFromParameters(directionId, areaId);
		}else{
			graindepots = graindepotSrv.findList(lkbms);
		}
		List<Map<String,Object>> gCountList = new ArrayList<Map<String,Object>>();//粮库统计
		List<Object> tmp = new ArrayList<Object>();
		int size = graindepots.size();
		if(size >0)
		for(int j = 0; j<size;j++){
			Map<String,Object> details = new HashMap<String,Object>();
			Graindepot depot = graindepots.get(j);
			tmp.add(depot);
		}
		return tmp;
	}
	
	
	//找到储粮区、行政区内的所有示范库
	private List<Graindepot> getGrainDepotsFromParameters(Long directionId,
			Long areaId) {
		List<Graindepot> graindepots = new  ArrayList<Graindepot>();
		if(areaId != null)//按地域取，找到地域内的所有示范库
			graindepots = graindepotSrv.findModelDepotsWithinArea(areaId);
		else if(directionId != null){
			List<Area> areas = areaService.findAreasWithDirection(directionId);
			int areaSize = areas.size();
			for (int k=0; k<areaSize; k++){
				List<Graindepot> graindepot = graindepotSrv.findModelDepotsWithinArea(areas.get(k).getId());
				graindepots.addAll(graindepot);
			}
		}else
			graindepots = graindepotSrv.findModelDepots();
		return graindepots;
	}
	
	/*
	 * ----------------------------------实时数据统计------------------------
	 */

	/**
	 * 粮库的储量区 行政区
	 */
	@RequestMapping(value = "/directionAndArea")
	@ResponseBody
	public Map<String, String> directionAndArea(String lkbm) {
		Map<String, String> result = new  HashMap<String, String>();
		Graindepot depot =  graindepotSrv.find(lkbm);
		Area area = depot.getArea();
		if (area != null) {
			result.put("lkbm", lkbm);
			result.put("areaId", area.getId().toString());
			result.put("directionId", area.getGd().getId().toString());
		}
		return result;
	}
	
	/**
	 * 虫类名称列表
	 */
	@RequestMapping(value = "/catalogIndexNameList")
	@ResponseBody
	public Map<String,Object> catalogIndexNameList() {
		List<String> result = deviceRealdataService.findAllKinds();
		int size = result.size();
		List<Map<String,Object>> l = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < size; i++) {
			Map<String,Object> m = new HashMap<String,Object>();
			m.put("text", result.get(i));
			l.add(m);
		}
		Map<String,Object> r = new HashMap<String,Object>();
		r.put("rows", l);
		r.put("total_count", result.size());
		return r;
	}
	
	
	/**
	 * 储粮区列表
	 */
	@RequestMapping("/getGrainDirections")
	@ResponseBody
	public List<Map<String,Object>> getGrainDirections() {

		List<Graindirection> directionList = gdSrv.findAll();
		List<Map<String,Object>> dCountList = new ArrayList<Map<String,Object>>();//储粮区统计
		int size = directionList.size();
		for(int i=0; i<size; i++){
			Graindirection tempDirection = directionList.get(i);
			Map<String,Object> temp = new HashMap<String,Object>();
			temp.put("id", tempDirection.getId());
			temp.put("name", tempDirection.getFullname());
			dCountList.add(temp);
		}
		return dCountList;
	}
	
	
	/**
	 * 行政区列表
	 */
	@RequestMapping("/getAreas")
	@ResponseBody
	public List<Map<String,Object>> getAreas(Long grainDirectionsId) {
		
		List<Map<String,Object>> aCountList = new ArrayList<Map<String,Object>>();//行政区统计
		List<Area> areaList = areaService.findAreasWithDirection(grainDirectionsId);
		int size = areaList.size();
		for(int i=0; i<size; i++){
			Area tempArea = areaList.get(i);
			Map<String,Object> temp = new HashMap<String,Object>();
			temp.put("id", tempArea.getId());
			temp.put("pid", tempArea.getParent()==null?null:tempArea.getParent().getId());
			temp.put("name", tempArea.getName());
			aCountList.add(temp);
		}
		return aCountList;
	}
	
	
	/**
	 * 粮库名称列表
	 */
	@RequestMapping("/grainDepots")
	@ResponseBody
	public List<Map<String,Object>> grainDepots(HttpSession session, Long areaId) {
		List<Graindepot> graindepots = new ArrayList<Graindepot>();
		List<Map<String,Object>> gCountList = new ArrayList<Map<String,Object>>();//粮库统计
		
		graindepots = graindepotSrv.findModelDepotsWithinArea(areaId);
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
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		UserType type = p.getType();
		Set<Grainbin> grainbins = new HashSet<Grainbin>();
		List<Map<String,Object>> bCountList = new ArrayList<Map<String,Object>>();//粮仓
		if(lkbm != null && !lkbm.equals(""))
			grainbins = graindepotSrv.find(lkbm).getGrainbins();
		
		for(Grainbin grainbin : grainbins) {
			Map<String,Object> details = new HashMap<String,Object>();
			String lcbm = grainbin.getLcbm();
			details.put("lcbm", lcbm);
			bCountList.add(details);
		}
		return bCountList;
	}
	
	
	/**
	 * 粮仓设备列表
	*/
	@RequestMapping("/grainDevices")
	@ResponseBody
	public List<Map<String,Object>> grainDevices(HttpSession session, String lcbm) {
		List<Map<String,Object>> dCountList = new ArrayList<Map<String,Object>>();//设备统计
		List<String> strs = deviceRealdataService.findAllSources(lcbm);
		for(String str : strs) {
			if(!str.equals("")){
				Map<String,Object> tempDevice = new HashMap<String,Object>();
				tempDevice.put("source", str);
				dCountList.add(tempDevice);
			}
		}
		return dCountList;
	}
	 

	/**
	 * 获取虫种温度湿度每天的数据
	 * @throws ParseException
	 */
	@RequestMapping(value = "/insectsHumidityTemperatureDayNumber")
	@ResponseBody
	public Map<String,Object> getInsectsHumidityTemperatureDayNumber(@RequestParam(value = "kinds[]")String []kinds, String collectStart, String collectEnd, Long directionId, Long areaId, String lkbm, String lcbm) throws ParseException {

		Pageable pageable = new Pageable(1,1000);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Map<String,Object> r = new HashMap<String,Object>();
		List<Map<String,Object>> kCountList = new ArrayList<Map<String,Object>>();//虫种统计
		List<Float> hCount = new ArrayList<Float>();//湿度统计
		List<Float> tCount = new ArrayList<Float>();//温度统计
		
		Set<Grainbin> grainbin= getGrainBinsFromParameters(directionId, areaId, lkbm, lcbm);

		try{
		long to = dateFormat.parse(collectEnd).getTime();
		long from = dateFormat.parse(collectStart).getTime();
		int maxDay = (int) ((to - from) / (1000 * 60 * 60 * 24));
		
		//截止日期往后退一天才能把当天的数据也包含进去
		collectEnd = findDayAfterDay(collectEnd, 1, dateFormat);
	
		//取虫种
		if(kinds !=null)
		for (int k=0; k < kinds.length; k++){
			Map<String,Object> kCount = new HashMap<String,Object>();//单个虫种数据
			kCount.put("name", kinds[k]);
			pageable.setFilters(getFilters(collectStart, collectEnd, kinds[k], 0, grainbin, ""));
			pageable.setOrders(getOrders("collecttime"));
			Page<DeviceRealdata> resultList = deviceRealdataService.findPage(pageable);
			List<DeviceRealdata> result = resultList.getRows();
			int size = result.size();
			List<Integer> tempCount = new ArrayList<Integer>();//单个虫种统计
				
			for (int i = 0; i <=maxDay; i++) {
				int set = 0;   
				String tempDate = findDayAfterDay(collectStart, i, dateFormat);	
				int j;
				List<Integer> insectsTempCount = new ArrayList<Integer>();//害虫最大数统计
				for(j = 0; j<size;j++){
					DeviceRealdata data = result.get(j);
					if(data.getCollecttime().indexOf(tempDate) >= 0){
						insectsTempCount.add(data.getNum());
						set = 1;
					}else if(set ==1)
						break;
				}
				if(set == 0)
					tempCount.add(0);
				else
					tempCount.add(Collections.max(insectsTempCount));
			}
				kCount.put("data", tempCount);
				kCountList.add(kCount);
		}
		
		//取温度和湿度
			//设置过滤条件
		pageable.setFilters(getFilters(collectStart, collectEnd, "", 0, grainbin, ""));
			//设置排序方式
		pageable.setOrders(getOrders("collecttime"));
		
		Page<DeviceRealdata> resultList = deviceRealdataService.findPage(pageable);
		List<DeviceRealdata> result = resultList.getRows();
		int size = result.size();
		
		for (int i = 0; i <= maxDay; i++) {
			int set = 0;	   
			String tempDate = findDayAfterDay(collectStart, i, dateFormat);
			List<Float> hTempCount = new ArrayList<Float>();//湿度平均数统计
			List<Float> tTempCount = new ArrayList<Float>();//温度平均数统计
			int j;
			for(j = 0; j<size;j++){
				DeviceRealdata data = result.get(j);
				if(data.getCollecttime().indexOf(tempDate) >= 0){
					tTempCount.add(data.getTemperature());
					hTempCount.add(data.getHumidity());
					set = 1;
				}else if(set == 1)
					break;
			}
			if(set == 0){
				tCount.add(0F);
				hCount.add(0F);
			}else{
				tCount.add(countAverage(tTempCount));
				hCount.add(countAverage(hTempCount));
			}
		}
			r.put("success", true);
		}catch (Exception e){
			r.put("success", false);
			e.printStackTrace();
		}
		r.put("insects", kCountList);
		r.put("temperature", tCount);
		r.put("humidity", hCount);
		return r;
	}

	
	/**
	 * 获取虫种温度湿度每天每个设备的数据
	 * @throws ParseException
	 */
	@RequestMapping(value = "/insectsHumidityTemperatureDeviceNumber")
	@ResponseBody
	public Map<String,Object> getInsectsHumidityTemperatureDeviceNumber(@RequestParam(value = "kinds[]")String []kinds, @RequestParam(value = "sources[]")String []sources, String collectStart, String collectEnd, Long directionId, Long areaId, String lkbm, String lcbm) throws ParseException {
		
		Map<String,Object> r = new HashMap<String,Object> ();
		List<Map<String,Object>> sourceList = new ArrayList<Map<String,Object>>();//分设备统计
		
		Pageable pageable = new Pageable(1,1000);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		
		Set<Grainbin> grainbin= getGrainBinsFromParameters(directionId, areaId, lkbm, lcbm);
		
		try{
		long to = dateFormat.parse(collectEnd).getTime();
		long from = dateFormat.parse(collectStart).getTime();
		int maxDay = (int) ((to - from) / (1000 * 60 * 60 * 24));
		
		//截止日期往后退一天才能把当天的数据也包含进去
		collectEnd = findDayAfterDay(collectEnd, 1, dateFormat);
	
		//取虫种
		if(sources!=null &&kinds !=null)
		for (int s =0; s < sources.length; s++){
			Map<String,Object> re = new HashMap<String,Object>();//单个设备统计列表
			
			List<Map<String,Object>> kCountList = new ArrayList<Map<String,Object>>();//虫种统计
			List<Float> hCount = new ArrayList<Float>();//湿度统计
			List<Float> tCount = new ArrayList<Float>();//温度统计
			
			Integer x, y, z;
			for (int k=0; k < kinds.length; k++){
				Map<String,Object> kCount = new HashMap<String,Object>();//单个虫种数据
				kCount.put("name", kinds[k]);
				pageable.setFilters(getFilters(collectStart, collectEnd, kinds[k], 0, grainbin, sources[s]));
				pageable.setOrders(getOrders("collecttime"));
				Page<DeviceRealdata> resultList = deviceRealdataService.findPage(pageable);
				List<DeviceRealdata> result = resultList.getRows();
				int size = result.size();

				List<Integer> tempCount = new ArrayList<Integer>();//单个虫种统计	
				for (int i = 0; i <=maxDay; i++) {
					int set = 0;
					String tempDate = findDayAfterDay(collectStart, i, dateFormat);
					int j;
					List<Integer> insectsTempCount = new ArrayList<Integer>();//害虫最大数统计
					for(j = 0; j<size;j++){
						DeviceRealdata data = result.get(j);
						if(data.getCollecttime().indexOf(tempDate) >= 0){
							insectsTempCount.add(data.getNum());
							set = 1;
						}else if(set ==1)
							break;
					}
					if(set == 0)
						tempCount.add(0);
					else
						tempCount.add(Collections.max(insectsTempCount));
				}
					kCount.put("data", tempCount);
					kCountList.add(kCount);
			}
			
			//取温度和湿度
				//设置过滤条件
			pageable.setFilters(getFilters(collectStart, collectEnd, "", 0, grainbin, sources[s]));
				//设置排序方式
			pageable.setOrders(getOrders("collecttime"));
			
			Page<DeviceRealdata> resultList = deviceRealdataService.findPage(pageable);
			List<DeviceRealdata> result = resultList.getRows();
			int size = result.size();
			for (int i = 0; i <= maxDay; i++) {
				int set = 0;	   
				String tempDate = findDayAfterDay(collectStart, i, dateFormat);
				List<Float> hTempCount = new ArrayList<Float>();//湿度平均数统计
				List<Float> tTempCount = new ArrayList<Float>();//温度平均数统计
				int j;
				for(j = 0; j<size;j++){
					DeviceRealdata data = result.get(j);
					if(data.getCollecttime().indexOf(tempDate) >= 0){
						tTempCount.add(data.getTemperature());
						hTempCount.add(data.getHumidity());
						set = 1;
					}else if(set == 1)
						break;
				}
				if(set == 0){
					tCount.add(0F);
					hCount.add(0F);
				}else{
					tCount.add(countAverage(tTempCount));
					hCount.add(countAverage(hTempCount));
				}
			}
			
			if(size>0){
				DeviceRealdata data = result.get(size-1);
				x = data.getX()==null?0:data.getX();
				y = data.getY()==null?0:data.getY();
				z = data.getZ()==null?0:data.getZ();
			}else
				x=y=z=0;
			
			re.put("insects", kCountList);
			re.put("temperature", tCount);
			re.put("humidity", hCount);
			re.put("source", sources[s]);
			re.put("x", x);
			re.put("y", y);
			re.put("z", z);
			sourceList.add(re);
		}
			r.put("success", true);
		}catch (Exception e){
			r.put("success", false);
			e.printStackTrace();
		}
		r.put("data", sourceList);
		return r;
	}

	/**
	 * 获取害虫位置数据
	 * @throws ParseException
	 */
	@RequestMapping(value = "/insectsLocationNumber")
	@ResponseBody
	public Map<String,Object> getLocationNumber(@RequestParam(value = "kinds[]")String []kinds, String collectStart, String collectEnd, String all, String lkbm, String lcbm, String areaId) throws ParseException{
		
		Map<String,Object> r = new HashMap<String,Object>();
		Pageable pageable = new Pageable(1,1000);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Map<String,Object>> totalCount = new ArrayList<Map<String,Object>>();//多个虫种数据
		
		//按粮仓取，其实只能取一个
		Set<Grainbin> grainbin = new HashSet<Grainbin>();
		if (lcbm!=null){
			Grainbin bin = binService.find(lcbm);
			grainbin.add(bin);
		}
				
		try{
		//截止日期往后退一天才能把当天的数据也包含进去
		Calendar tp = Calendar.getInstance();
		tp.setTime(dateFormat.parse(collectEnd));
		tp.add(Calendar.DATE, 1);
		collectEnd = dateFormat.format(tp.getTime());
		
		if(kinds !=null){
			for (int k=0; k < kinds.length; k++){
				
				Map<String,Object> kCount = new HashMap<String,Object>();//单个虫种数据
				kCount.put("name", kinds[k]);
				List<Map<String,Object>> mCount = new ArrayList<Map<String,Object>>();//地图范围统计
				
				pageable.setFilters(getFilters(collectStart, collectEnd, kinds[k], 1, grainbin, ""));
				Page<DeviceRealdata> resultList = deviceRealdataService.findPage(pageable);
				List<DeviceRealdata> result = resultList.getRows();
				int size = result.size();
				//Double[][] mapCount = new Double [size][2];
				for(int j = 0; j<size;j++){
					Map<String,Object> count = new HashMap<String,Object>();//地图关系
					DeviceRealdata data = result.get(j);
					/*
					if(j == 0){
						//Map<String,Double> tempLocation = new HashMap<String,Double> ();
						//tempLocation.put("Latitude", data.getLatitude());//经度
						//tempLocation.put("Altitude", data.getAltitude());//纬度
						//pCount.add(tempLocation);
						
						mapCount [j][0] = data.getLongtitude();//经度
						mapCount [j][1] = data.getAltitude();//纬度
					}else{
						
					}*/
					count.put("longtitude", data.getLongtitude());//经度
					count.put("latitude", data.getLatitude());//纬度
					count.put("number", data.getNum());	
					mCount.add(count);
				}
				kCount.put("data", mCount);
				totalCount.add(kCount);
			}
			
		}
			r.put("success", true);
		}catch (Exception e){
			r.put("success", false);
			e.printStackTrace();
		}
		r.put("insects", totalCount);
		return r;
	}

	
	
	private Set<Grainbin> getGrainBinsFromParameters(Long directionId, Long areaId, String lkbm, String lcbm){
		Set<Grainbin> grainbin = new HashSet<Grainbin>();
		if (lcbm != null && !lcbm.equals("")){//按粮仓取，其实只能取一个
			Grainbin bin = binService.find(lcbm);
			grainbin.add(bin);
		}else if(lkbm != null && !lkbm.equals("")){//按粮库取，找到粮库下所有粮仓
			grainbin = graindepotSrv.find(lkbm).getGrainbins(); 
		}else{
			List<Graindepot> graindepots = new  ArrayList<Graindepot>();
			if(areaId != null)//按地域取，找到地域内的所有示范库
				graindepots = graindepotSrv.findModelDepotsWithinArea(areaId);
			else if(directionId != null){//按储粮区取，找到所有行政区，及示范库
				List<Area> areas = areaService.findAreasWithDirection(directionId);
				int areaSize = areas.size();
				for (int k=0; k<areaSize; k++){
					List<Graindepot> graindepot = graindepotSrv.findModelDepotsWithinArea(areas.get(k).getId());
					graindepots.addAll(graindepot);
				}
			}
			else//找到所有示范库
				graindepots = graindepotSrv.findModelDepots();
			int depotSize = graindepots.size();
			for (int k=0; k<depotSize; k++){
				Set<Grainbin> grainbins = graindepots.get(k).getGrainbins();
				grainbin.addAll(grainbins);
			}
		}
		return grainbin;
	}
	
	private List<Filter> getFilters(String collectStart, String collectEnd, String kind, Integer location, Set<Grainbin> grainbin, String source){
		List<Filter> filters = new ArrayList<Filter>();
		Filter fl;
		if (grainbin!=null && grainbin.size() > 0){
			fl = new Filter();
			fl.setProperty("TGrainbin");
			fl.setOperator(Operator.in);
			fl.setValue(grainbin);
			filters.add(fl);
		}
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
		if(kind != null && !kind.equals("")) {
			fl = new Filter();
			fl.setProperty("kind");
			fl.setValue(kind);
			fl.setOperator(Operator.like);
			filters.add(fl);
			
			fl = new Filter();
			fl.setProperty("num");
			fl.setOperator(Operator.isNotNull);
			filters.add(fl);
		}else{
			fl = new Filter();
			fl.setProperty("temperature");
			fl.setOperator(Operator.isNotNull);
			filters.add(fl);
			
			fl = new Filter();
			fl.setProperty("humidity");
			fl.setOperator(Operator.isNotNull);
			filters.add(fl);
			
			fl = new Filter();
			fl.setProperty("temperature");
			fl.setValue(0);
			fl.setOperator(Operator.ne);
			filters.add(fl);
			
			fl = new Filter();
			fl.setProperty("humidity");
			fl.setValue(0);
			fl.setOperator(Operator.ne);
			filters.add(fl);
		}
		if(location != null && !location.equals(0)) {
			fl = new Filter();
			fl.setProperty("latitude");
			fl.setOperator(Operator.isNotNull);
			filters.add(fl);
			
			fl = new Filter();
			fl.setProperty("longtitude");
			fl.setOperator(Operator.isNotNull);
			filters.add(fl);
		}
		if(source != null && !source.equals("")) {
			fl = new Filter();
			fl.setProperty("source");
			fl.setOperator(Operator.eq);
			fl.setValue(source);
			filters.add(fl);
		}
		return filters;
	}
	
	private List<Order> getOrders(String collecttime){
		List<Order> orders = new ArrayList<Order>();
		Order ol;
		if(collecttime != null && !collecttime.equals("")) {
			ol = new Order();
			ol.setProperty("collecttime");
			ol.setDirection(Direction.asc);
			orders.add(ol);
		}
		return orders;
	}
	
	private Float countAverage(List<Float> list){
		Float sum = 0F;
        for(int i=0;i<list.size();i++){
            sum += list.get(i);
        }
        return sum / list.size();
	}
	
	private String findDayAfterDay(String day, Integer count, SimpleDateFormat dateFormat) throws ParseException{
		Calendar tp = Calendar.getInstance();
		tp.setTime(dateFormat.parse(day));
		tp.add(Calendar.DATE, count);
		return dateFormat.format(tp.getTime());
	}

}

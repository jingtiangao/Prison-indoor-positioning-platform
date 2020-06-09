package com.grain.controller.expert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.Filter;
import com.grain.Filter.Operator;
import com.grain.Order;
import com.grain.Order.Direction;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.entity.FactoryInfo;
import com.grain.entity.Farmer;
import com.grain.entity.Grainbin;
import com.grain.entity.Graindepot;
import com.grain.entity.InsectOnfield;
import com.grain.entity.InsectsOnfactory;
import com.grain.entity.TInsectsCollectionOnFarmer;
import com.grain.entity.TInsectsInbinOnDepot;
import com.grain.entity.TInsectsInfactory;
import com.grain.entity.TInsectsInfoOnFarmer;
import com.grain.entity.TInsectsOndepot;
import com.grain.service.FactoryInfoService;
import com.grain.service.FarmerService;
import com.grain.service.InsectOnfieldService;
import com.grain.service.InsectsOnfactoryService;
import com.grain.service.TInsectsCollectionOnFarmerService;
import com.grain.service.TInsectsInbinOnDepotService;
import com.grain.service.TInsectsInfactoryService;
import com.grain.service.TInsectsInfoOnFarmerService;
import com.grain.service.TInsectsOndepotService;

@Controller
@RequestMapping("expert/common")
public class ExpertInsectsDistributionController {

	@Resource(name = "farmerServiceImpl")
	FarmerService farmersrv;

	@Resource(name = "factoryServiceImpl")
	FactoryInfoService factorysrv;

	@Resource(name = "tInsectsCollectionOnFarmerServiceImpl")
	TInsectsCollectionOnFarmerService tInsectsCollectionOnFarmersrv;

	@Resource(name = "insectsOnfactoryServiceImpl")
	InsectsOnfactoryService insectsOnfactorysrv;

	@Resource(name = "tInsectsInbinOnDepotServiceImpl")
	TInsectsInbinOnDepotService tInsectsInbinOnDepotsrv;

	@Resource(name = "insectOnfieldServiceImpl")
	InsectOnfieldService insectOnfieldsrv;

	@Resource(name = "tInsectsInfoOnFarmerServiceImpl")
	TInsectsInfoOnFarmerService tInsectsInfoOnFarmersrv;

	@Resource(name = "tInsectsOndepotServiceImpl")
	TInsectsOndepotService tInsectsOndepotsrv;

	@Resource(name = "tInsectsInfactoryServiceImpl")
	TInsectsInfactoryService tInsectsInfactorysrv;


	// 获取虫种下拉菜单
	@RequestMapping(value = "/insectKindList")
	@ResponseBody
	public Map<String, Object> insectKindList() {
		List<String> result = new ArrayList<>();

		Set<String> resultSetInsectKind = new HashSet<>();
		for (TInsectsInfoOnFarmer tInsectsInfoOnFarmer : tInsectsInfoOnFarmersrv.findAll()) {
			resultSetInsectKind.add(tInsectsInfoOnFarmer.getKind());
		}
		for (TInsectsInfactory tInsectsInfactory : tInsectsInfactorysrv.findAll()) {
			resultSetInsectKind.add(tInsectsInfactory.getKind());
		}
		for (TInsectsOndepot tInsectsOndepot : tInsectsOndepotsrv.findAll()) {
			resultSetInsectKind.add(tInsectsOndepot.getKind());
		}
		for (InsectOnfield insectOnfield : insectOnfieldsrv.findAll()) {
			resultSetInsectKind.add(insectOnfield.getKind());
		}
		for (String tmpKind : resultSetInsectKind) {
			if (tmpKind != null && !tmpKind.trim().equals(""))
				result.add(tmpKind);
		}

		// 构造JSON串
		int size = result.size();
		List<Map<String, Object>> l = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < size; i++) {
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("text", result.get(i));
			l.add(m);
		}
		Map<String, Object> r = new HashMap<String, Object>();
		r.put("rows", l);
		r.put("total_count", result.size());
		return r;
	}


	// 点击查询按钮
	@RequestMapping(value = "/insectsDistributionDoSearchList")
	@ResponseBody
	public Map<String, Object> insectsDistributionDoSearchList(@RequestParam String startTime, String endTime,
			@RequestParam(value = "pos[]") String[] pos, @RequestParam(value = "comp[]") String[] comp,
			@RequestParam(value = "kind[]") String[] kind) {
		List<Object> result = new ArrayList<>();
		System.out.println(pos);
		Pageable pageable = new Pageable(1, 1000);
		Set<String> comps = new HashSet<>();
		for (String tmp : comp) {
			comps.add(tmp);
		}
		Set<String> kinds = new HashSet<>();
		for (String tmp : kind) {
			kinds.add(tmp);
		}

		Set<Farmer> farmerSet = new HashSet<>();
		Set<Graindepot> lkSet = new HashSet<>();
		Set<FactoryInfo> factorySet = new HashSet<>();
		Set<InsectOnfield> filedSet = new HashSet<>();
		for (String position : pos) {
			switch (position) {
			case "农户":
				pageable.setFilters(getFiltersForFarmer(startTime, endTime, comps));
				Page<TInsectsCollectionOnFarmer> TInsectsCollectionOnFarmer_ResultPageList = tInsectsCollectionOnFarmersrv
						.findPage(pageable);
				List<TInsectsCollectionOnFarmer> TInsectsCollectionOnFarmer_ResultList = TInsectsCollectionOnFarmer_ResultPageList
						.getRows();
				for (TInsectsCollectionOnFarmer tmp : TInsectsCollectionOnFarmer_ResultList) {
					List<TInsectsInfoOnFarmer> temps = tmp.getInsectsInfoOnFarmers();
					for (TInsectsInfoOnFarmer temp : temps) {
						for (String kindname : kinds) {
							if (temp.getKind().equalsIgnoreCase(kindname)) {
								farmerSet.add(temp.getTInsectsCollectionOnFarmer().getTFarmer());
							}
						}
					}
					// farmerSet.add(tmp.getTFarmer());
				}
				break;
			case "加工厂":
				pageable.setFilters(getFiltersForFactory(startTime, endTime, comps));
				Page<InsectsOnfactory> InsectsOnfactory_ResultPageList = insectsOnfactorysrv.findPage(pageable);
				List<InsectsOnfactory> InsectsOnfactory_ResultList = InsectsOnfactory_ResultPageList.getRows();
				for (InsectsOnfactory tmp : InsectsOnfactory_ResultList) {
					List<TInsectsInfactory> temps = tmp.getTInsectsInfactories();
					for (TInsectsInfactory temp : temps) {
						for (String kindname : kinds) {
							if (temp.getKind().equalsIgnoreCase(kindname)) {
								factorySet.add(temp.getInsectsOnfactory().getTFactoryInfo());
							}
						}
					}
					// factorySet.add(tmp.getTFactoryInfo());
				}
				break;
			case "粮仓":
				pageable.setFilters(getFiltersForLK(startTime, endTime, comps));
				Page<TInsectsInbinOnDepot> TInsectsInbinOnDepot_ResultPageList = tInsectsInbinOnDepotsrv
						.findPage(pageable);
				List<TInsectsInbinOnDepot> TInsectsInbinOnDepot_ResultList = TInsectsInbinOnDepot_ResultPageList
						.getRows();
				for (TInsectsInbinOnDepot tmp : TInsectsInbinOnDepot_ResultList) {
					List<TInsectsOndepot> temps = tmp.getTInsectsOndepots();
					for (TInsectsOndepot temp : temps) {
						for (String kindname : kinds) {
							if (temp.getKind().equalsIgnoreCase(kindname)) {
								lkSet.add(temp.getTInsectsInbinOnDepot().getTGrainbin().getGraindepot());
							}
						}
					}
					// lkSet.add(tmp.getTGrainbin().getGraindepot());
				}
				break;
			case "野外":
				pageable.setFilters(getFiltersForFiled(startTime, endTime, comps));
				Page<InsectOnfield> InsectOnfield_ResultPageList = insectOnfieldsrv.findPage(pageable);
				List<InsectOnfield> InsectOnfield_ResultList = InsectOnfield_ResultPageList.getRows();
				for (InsectOnfield tmp : InsectOnfield_ResultList) {
					for (String kindname : kinds) {
						if (tmp.getKind().equalsIgnoreCase(kindname)) {
							filedSet.add(tmp);
						}
					}
					// filedSet.add(tmp);
				}
				break;
			}
		}
		Map<String, Object> l = new HashMap<>();
		for (Farmer farmer : farmerSet) {
			l = new HashMap<>();
			l.put("position", "farmer");
			l.put("name", farmer.getNameFamer());
			l.put("sm", farmer.getSmFarmer());
			l.put("address", farmer.getAddress());
			l.put("longitude", farmer.getLongitude());
			l.put("latitude", farmer.getLatitude());
			result.add(l);
		}
		l = new HashMap<>();
		for (FactoryInfo factory : factorySet) {
			l = new HashMap<>();
			l.put("position", "factory");
			l.put("name", factory.getNameFactory());
			l.put("sm", factory.getSmFactory());
			l.put("address", factory.getAddress());
			l.put("longitude", factory.getLongitude());
			l.put("latitude", factory.getLatitude());
			result.add(l);
		}
		l = new HashMap<>();
		for (Graindepot lk : lkSet) {
			l = new HashMap<>();
			l.put("position", "depot");
			l.put("name", lk.getLkmc());
			l.put("sm", lk.getLkbm());
			l.put("address", lk.getLkdz());
			l.put("longitude", lk.getLongtitude());
			l.put("latitude", lk.getLatitude());
			result.add(l);
		}
		l = new HashMap<>();
		for (InsectOnfield filed : filedSet) {
			l = new HashMap<>();
			l.put("position", "field");
			l.put("sm", filed.getId());
			l.put("longitude", filed.getLongtitude());
			l.put("latitude", filed.getLatitude());
			result.add(l);
		}

		Map<String, Object> r = new HashMap<String, Object>();
		r.put("rows", result);
		r.put("total", result.size());
		return r;
	}

	@RequestMapping(value = "/getinsectsDistributionFarmerListRecords")
	@ResponseBody
	public Map<String, Object> getinsectsDistributionFarmerListRecords(@RequestParam String startTime, String endTime,
			@RequestParam(value = "comp[]") String[] comp, String farmer,
			@RequestParam(value = "kind[]") String[] kind) {
		List<RowsInsectsObject> result = new ArrayList<>();

		Set<String> comps = new HashSet<>();
		for (String tmp : comp) {
			comps.add(tmp);
		}

		Set<String> kinds = new HashSet<>();
		for (String tmp : kind) {
			kinds.add(tmp);
		}

		Pageable pageable = new Pageable();
		// pageable.setPage(Integer.parseInt(pageNumber));
		TInsectsCollectionOnFarmer insectsCollectionOnFarmer = new TInsectsCollectionOnFarmer();
		insectsCollectionOnFarmer.setTFarmer(farmersrv.findBySMFarmer(farmer));
		pageable.setOrders(getOrders("dateCollection"));
		pageable.setFilters(getFiltersForFarmer(startTime, endTime, comps));
		Page<TInsectsCollectionOnFarmer> resultPageList = tInsectsCollectionOnFarmersrv.findPage(pageable,
				insectsCollectionOnFarmer);
		List<TInsectsCollectionOnFarmer> resultList = resultPageList.getRows();
		for (TInsectsCollectionOnFarmer tmpInsectsCollectionOnFarmer : resultList) {
			for (TInsectsInfoOnFarmer tmpInsectsInfoOnFarmer : tmpInsectsCollectionOnFarmer.getInsectsInfoOnFarmers()) {
				for (String kindname : kinds) {
					if (tmpInsectsInfoOnFarmer.getKind().equalsIgnoreCase(kindname)) {
						RowsInsectsObject tmpRowsInsectsObject = new RowsInsectsObject();
						tmpRowsInsectsObject.sm_collection = tmpInsectsCollectionOnFarmer.getSmCollection();
						tmpRowsInsectsObject.collector = tmpInsectsCollectionOnFarmer.getCollector();
						tmpRowsInsectsObject.company = tmpInsectsCollectionOnFarmer.getCompany();
						tmpRowsInsectsObject.date_collection = tmpInsectsCollectionOnFarmer.getDateCollection();
						tmpRowsInsectsObject.kind = tmpInsectsInfoOnFarmer.getKind();
						tmpRowsInsectsObject.stage = tmpInsectsInfoOnFarmer.getStage();
						tmpRowsInsectsObject.num = tmpInsectsInfoOnFarmer.getNum();
						tmpRowsInsectsObject.food = tmpInsectsInfoOnFarmer.getFood();
						tmpRowsInsectsObject.harm = tmpInsectsInfoOnFarmer.getHarm();
						tmpRowsInsectsObject.protectmeasure = tmpInsectsInfoOnFarmer.getProtectmeasure();
						tmpRowsInsectsObject.loc_collect = tmpInsectsInfoOnFarmer.getLocCollect();
						tmpRowsInsectsObject.host = tmpInsectsInfoOnFarmer.getHost();
						result.add(tmpRowsInsectsObject);
					}
				}
			}
		}
		Map<String, Object> r = new HashMap<String, Object>();
		r.put("rows", result);
		r.put("total", result.size());
		r.put("pageSize", resultPageList.getPageSize());
		r.put("pageNumber", resultPageList.getPageNumber());
		r.put("totalPages", resultPageList.getTotal());
		return r;
	}

	@RequestMapping(value = "/getinsectsDistributionBinListRecords")
	@ResponseBody
	public Map<String, Object> getinsectsDistributionBinListRecords(@RequestParam String startTime, String endTime,
			@RequestParam(value = "comp[]") String[] comp, Grainbin grainbin,
			@RequestParam(value = "kind[]") String[] kind) {
		List<RowsInsectsObject> result = new ArrayList<>();

		Set<String> comps = new HashSet<>();
		for (String tmp : comp) {
			comps.add(tmp);
		}

		Set<String> kinds = new HashSet<>();
		for (String tmp : kind) {
			kinds.add(tmp);
		}

		Pageable pageable = new Pageable();
		// pageable.setPage(Integer.parseInt(pageNumber));
		TInsectsInbinOnDepot tInsectsInbinOnDepot = new TInsectsInbinOnDepot();
		tInsectsInbinOnDepot.setTGrainbin(grainbin);
		pageable.setOrders(getOrders("dateCollection"));
		pageable.setFilters(getFiltersForLK(startTime, endTime, comps));
		Page<TInsectsInbinOnDepot> resultPageList = tInsectsInbinOnDepotsrv.findPage(pageable, tInsectsInbinOnDepot);
		List<TInsectsInbinOnDepot> resultList = resultPageList.getRows();
		for (TInsectsInbinOnDepot tmpTInsectsInbinOnDepot : resultList) {
			for (TInsectsOndepot tmpInsectsOndepot : tmpTInsectsInbinOnDepot.getTInsectsOndepots()) {
				for (String kindname : kinds) {
					if (tmpInsectsOndepot.getKind().equalsIgnoreCase(kindname)) {
						RowsInsectsObject tmpRowsInsectsObject = new RowsInsectsObject();
						tmpRowsInsectsObject.sm_collection = tmpTInsectsInbinOnDepot.getSmCollection();
						tmpRowsInsectsObject.collector = tmpTInsectsInbinOnDepot.getCollector();
						tmpRowsInsectsObject.company = tmpTInsectsInbinOnDepot.getCompanyCollector();
						tmpRowsInsectsObject.date_collection = tmpTInsectsInbinOnDepot.getDateCollection();
						tmpRowsInsectsObject.kind = tmpInsectsOndepot.getKind();
						tmpRowsInsectsObject.stage = tmpInsectsOndepot.getStage();
						tmpRowsInsectsObject.num = tmpInsectsOndepot.getNum();
						tmpRowsInsectsObject.food = tmpInsectsOndepot.getFood();
						tmpRowsInsectsObject.harm = tmpInsectsOndepot.getHarm();
						tmpRowsInsectsObject.protectmeasure = tmpInsectsOndepot.getMeasureCtrl();
						tmpRowsInsectsObject.loc_collect = tmpInsectsOndepot.getLocCollect();
						tmpRowsInsectsObject.host = tmpInsectsOndepot.getHost();
						result.add(tmpRowsInsectsObject);
					}
				}

			}
		}
		Map<String, Object> r = new HashMap<String, Object>();
		r.put("rows", result);
		r.put("total", result.size());
		r.put("pageSize", resultPageList.getPageSize());
		r.put("pageNumber", resultPageList.getPageNumber());
		r.put("totalPages", resultPageList.getTotal());
		return r;
	}

	
	@RequestMapping(value = "/getinsectsDistributionInsectsOnfactoryListRecords")
	@ResponseBody
	public Map<String, Object> getinsectsDistributionInsectsOnfactoryListRecords(@RequestParam String startTime, String endTime,
			@RequestParam(value = "comp[]") String[] comp, String factoryInfo,@RequestParam(value = "kind[]") String[] kind) {
		List<RowsInsectsObject> result = new ArrayList<>();

		Set<String> comps = new HashSet<>();
		for (String tmp : comp) {
			comps.add(tmp);
		}
		
		Set<String> kinds = new HashSet<>();
		for (String tmp : kind) {
			kinds.add(tmp);
		}

		Pageable pageable = new Pageable();
		// pageable.setPage(Integer.parseInt(pageNumber));
		InsectsOnfactory insectsOnfactory = new InsectsOnfactory();
		insectsOnfactory.setTFactoryInfo(factorysrv.findBySMFactory(factoryInfo));
		pageable.setOrders(getOrders("collectdate"));
		pageable.setFilters(getFiltersForFactory(startTime, endTime, comps));
		Page<InsectsOnfactory> resultPageList = insectsOnfactorysrv.findPage(pageable, insectsOnfactory);
		List<InsectsOnfactory> resultList = resultPageList.getRows();
		for (InsectsOnfactory tmpInsectsOnfactory : resultList) {
			for (TInsectsInfactory tmpTInsectsInfactory : tmpInsectsOnfactory.getTInsectsInfactories()) {
				for (String kindname : kinds) {
					if (tmpTInsectsInfactory.getKind().equalsIgnoreCase(kindname)) {
						RowsInsectsObject tmpRowsInsectsObject = new RowsInsectsObject();
						tmpRowsInsectsObject.sm_collection = tmpInsectsOnfactory.getSmCollect();
						tmpRowsInsectsObject.collector = tmpInsectsOnfactory.getCollector();
						tmpRowsInsectsObject.company = tmpInsectsOnfactory.getCompany();
						tmpRowsInsectsObject.date_collection = tmpInsectsOnfactory.getCollectdate();
						tmpRowsInsectsObject.kind = tmpTInsectsInfactory.getKind();
						tmpRowsInsectsObject.stage = tmpTInsectsInfactory.getStage();
						tmpRowsInsectsObject.num = tmpTInsectsInfactory.getNum();
						tmpRowsInsectsObject.food = tmpTInsectsInfactory.getFood();
						tmpRowsInsectsObject.harm = tmpTInsectsInfactory.getHarm();
						tmpRowsInsectsObject.protectmeasure = tmpTInsectsInfactory.getProtectmeasure();
						tmpRowsInsectsObject.loc_collect = tmpTInsectsInfactory.getLocCollect();
						tmpRowsInsectsObject.host = tmpTInsectsInfactory.getHost();
						result.add(tmpRowsInsectsObject);
					}
				}
				
			}
		}
		Map<String, Object> r = new HashMap<String, Object>();
		r.put("rows", result);
		r.put("total", result.size());
		r.put("pageSize", resultPageList.getPageSize());
		r.put("pageNumber", resultPageList.getPageNumber());
		r.put("totalPages", resultPageList.getTotal());
		return r;
	}
	
	@RequestMapping(value = "/getinsectsDistributionInsectOnfieldListRecords")
	@ResponseBody
	public Map<String, Object> getinsectsDistributionInsectOnfieldListRecords(@RequestParam String startTime, String endTime,
			@RequestParam(value = "comp[]") String[] comp, String longtitude, String latitude,@RequestParam(value = "kind[]") String[] kind) {
		List<RowsInsectsObject> result = new ArrayList<>();

		Set<String> comps = new HashSet<>();
		for (String tmp : comp) {
			comps.add(tmp);
		}
		
		Set<String> kinds = new HashSet<>();
		for (String tmp : kind) {
			kinds.add(tmp);
		}
		// System.out.println(insectOnfield);
		// Long id = Long.parseLong(insectOnfield);
		// InsectOnfield insectOnfield_object = insectOnfieldsrv.find(id);
		System.out.println(longtitude);
		System.out.println(latitude);

		Pageable pageable = new Pageable();
		// pageable.setPage(Integer.parseInt(pageNumber));
		pageable.setOrders(getOrders("id"));
		pageable.setFilters(getFiltersForFiled2(startTime, endTime, comps, longtitude, latitude));
		Page<InsectOnfield> resultPageList = insectOnfieldsrv.findPage(pageable);
		List<InsectOnfield> resultList = resultPageList.getRows();
		for (InsectOnfield tmpInsectOnfield : resultList) {
			for (String kindname : kinds) {
				if (tmpInsectOnfield.getKind().equalsIgnoreCase(kindname)) {
					RowsInsectsObject tmpRowsInsectsObject = new RowsInsectsObject();
					tmpRowsInsectsObject.sm_collection = tmpInsectOnfield.getId().toString();
					tmpRowsInsectsObject.collector = tmpInsectOnfield.getCollector();
					tmpRowsInsectsObject.company = tmpInsectOnfield.getCompany();
					tmpRowsInsectsObject.date_collection = tmpInsectOnfield.getCollectdate();
					tmpRowsInsectsObject.kind = tmpInsectOnfield.getKind();
					tmpRowsInsectsObject.stage = tmpInsectOnfield.getStage();
					tmpRowsInsectsObject.num = tmpInsectOnfield.getNum();
					tmpRowsInsectsObject.food = tmpInsectOnfield.getFood();
					tmpRowsInsectsObject.harm = tmpInsectOnfield.getHarm();
					tmpRowsInsectsObject.host = tmpInsectOnfield.getHost();
					result.add(tmpRowsInsectsObject);
				}
			}
		}
		Map<String, Object> r = new HashMap<String, Object>();
		r.put("rows", result);
		r.put("total", result.size());
		r.put("pageSize", resultPageList.getPageSize());
		r.put("pageNumber", resultPageList.getPageNumber());
		r.put("totalPages", resultPageList.getTotal());
		return r;
	}

	private List<Filter> getFiltersForFarmer(String startTime, String endTime, Set<String> comps) {
		List<Filter> filters = new ArrayList<Filter>();
		Filter fl;
		if (startTime != null && !startTime.equals("")) {
			fl = new Filter();
			fl.setProperty("dateCollection");
			fl.setValue(startTime);
			fl.setOperator(Operator.ge);
			filters.add(fl);
		}
		if (endTime != null && !endTime.equals("")) {
			fl = new Filter();
			fl.setProperty("dateCollection");
			fl.setValue(endTime);
			fl.setOperator(Operator.le);
			filters.add(fl);
		}
		if (comps != null && comps.size() != 0) {
			fl = new Filter();
			fl.setProperty("company");
			fl.setValue(comps);
			fl.setOperator(Operator.in);
			filters.add(fl);
		}
		return filters;
	}

	private List<Filter> getFiltersForLK(String startTime, String endTime, Set<String> comps) {
		List<Filter> filters = new ArrayList<Filter>();
		Filter fl;
		if (startTime != null && !startTime.equals("")) {
			fl = new Filter();
			fl.setProperty("dateCollection");
			fl.setValue(startTime);
			fl.setOperator(Operator.ge);
			filters.add(fl);
		}
		if (endTime != null && !endTime.equals("")) {
			fl = new Filter();
			fl.setProperty("dateCollection");
			fl.setValue(endTime);
			fl.setOperator(Operator.le);
			filters.add(fl);
		}
		if (comps != null && comps.size() != 0) {
			fl = new Filter();
			fl.setProperty("companyCollector");
			fl.setValue(comps);
			fl.setOperator(Operator.in);
			filters.add(fl);
		}
		return filters;
	}

	private List<Filter> getFiltersForFactory(String startTime, String endTime, Set<String> comps) {
		List<Filter> filters = new ArrayList<Filter>();
		Filter fl;
		if (startTime != null && !startTime.equals("")) {
			fl = new Filter();
			fl.setProperty("collectdate");
			fl.setValue(startTime);
			fl.setOperator(Operator.ge);
			filters.add(fl);
		}
		if (endTime != null && !endTime.equals("")) {
			fl = new Filter();
			fl.setProperty("collectdate");
			fl.setValue(endTime);
			fl.setOperator(Operator.le);
			filters.add(fl);
		}
		if (comps != null && comps.size() != 0) {
			fl = new Filter();
			fl.setProperty("company");
			fl.setValue(comps);
			fl.setOperator(Operator.in);
			filters.add(fl);
		}
		return filters;
	}

	private List<Filter> getFiltersForFiled(String startTime, String endTime, Set<String> comps) {
		List<Filter> filters = new ArrayList<Filter>();
		Filter fl;
		if (startTime != null && !startTime.equals("")) {
			fl = new Filter();
			fl.setProperty("collectdate");
			fl.setValue(startTime);
			fl.setOperator(Operator.ge);
			filters.add(fl);
		}
		if (endTime != null && !endTime.equals("")) {
			fl = new Filter();
			fl.setProperty("collectdate");
			fl.setValue(endTime);
			fl.setOperator(Operator.le);
			filters.add(fl);
		}
		if (comps != null && comps.size() != 0) {
			fl = new Filter();
			fl.setProperty("company");
			fl.setValue(comps);
			fl.setOperator(Operator.in);
			filters.add(fl);
		}
		return filters;
	}

	private List<Filter> getFiltersForFiled2(String startTime, String endTime, Set<String> comps, String lnt,
			String lat) {
		List<Filter> filters = new ArrayList<Filter>();
		Filter fl;
		if (startTime != null && !startTime.equals("")) {
			fl = new Filter();
			fl.setProperty("collectdate");
			fl.setValue(startTime);
			fl.setOperator(Operator.ge);
			filters.add(fl);
		}
		if (endTime != null && !endTime.equals("")) {
			fl = new Filter();
			fl.setProperty("collectdate");
			fl.setValue(endTime);
			fl.setOperator(Operator.le);
			filters.add(fl);
		}
		if (lnt != null && !lnt.equals("")) {
			fl = new Filter();
			fl.setProperty("longtitude");
			fl.setValue(lnt);
			fl.setOperator(Operator.eq);
			filters.add(fl);
		}
		if (lat != null && !lat.equals("")) {
			fl = new Filter();
			fl.setProperty("latitude");
			fl.setValue(lat);
			fl.setOperator(Operator.eq);
			filters.add(fl);
		}
		if (comps != null && comps.size() != 0) {
			fl = new Filter();
			fl.setProperty("company");
			fl.setValue(comps);
			fl.setOperator(Operator.in);
			filters.add(fl);
		}
		return filters;
	}

	private List<Order> getOrders(String dateCollection) {
		List<Order> orders = new ArrayList<Order>();
		Order ol;
		if (dateCollection != null && !dateCollection.equals("")) {
			ol = new Order();
			ol.setProperty(dateCollection);
			ol.setDirection(Direction.asc);
			orders.add(ol);
		}
		return orders;
	}

	private class RowsInsectsObject {
		private String sm_collection;
		private String collector;
		private String company;
		private String date_collection;
		private String kind;
		private String stage;
		private Integer num;
		private String food;
		private String harm;
		private String protectmeasure;
		private String loc_collect;
		private String host;

		public String getSm_collection() {
			return sm_collection;
		}

		public void setSm_collection(String sm_collection) {
			this.sm_collection = sm_collection;
		}

		public String getCollector() {
			return collector;
		}

		public void setCollector(String collector) {
			this.collector = collector;
		}

		public String getCompany() {
			return company;
		}

		public void setCompany(String company) {
			this.company = company;
		}

		public String getDate_collection() {
			return date_collection;
		}

		public void setDate_collection(String date_collection) {
			this.date_collection = date_collection;
		}

		public String getKind() {
			return kind;
		}

		public void setKind(String kind) {
			this.kind = kind;
		}

		public String getStage() {
			return stage;
		}

		public void setStage(String stage) {
			this.stage = stage;
		}

		public Integer getNum() {
			return num;
		}

		public void setNum(Integer num) {
			this.num = num;
		}

		public String getFood() {
			return food;
		}

		public void setFood(String food) {
			this.food = food;
		}

		public String getHarm() {
			return harm;
		}

		public void setHarm(String harm) {
			harm = harm;
		}

		public String getProtectmeasure() {
			return protectmeasure;
		}

		public void setProtectmeasure(String protectmeasure) {
			this.protectmeasure = protectmeasure;
		}

		public String getLoc_collect() {
			return loc_collect;
		}

		public void setLoc_collect(String loc_collect) {
			this.loc_collect = loc_collect;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}
	}
}

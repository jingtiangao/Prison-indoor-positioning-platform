package com.grain.controller.depoter;

import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.CommonAttributes;
import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.Principal;
import com.grain.entity.Grainbin;
import com.grain.entity.user.DepotUser;
import com.grain.entity.TBinInertaerosol;
import com.grain.service.GrainbinService;
import com.grain.service.GraindepotService;
import com.grain.service.TDepotInvestService;
import com.grain.service.TBinInertaerosolService;
import com.grain.service.DepotInertaerosolService;
import com.grain.service.user.DepotUserService;


/*
 * 	惰性气溶液分仓调查表
 *  
 */
@Controller
@RequestMapping("depoter/tbininertaerosol")

public class TBinInertaerosolController {
	
	
	@Resource(name="grainDepotServiceImpl")
	GraindepotService depotSrv;	//粮库
	
	@Resource(name="grainBinServiceImpl")
	GrainbinService binService;	//粮仓

	@Resource(name="tdepotInvestServiceImpl")
	TDepotInvestService depotInvestService;	//虫调总表

	
	@Resource(name = "depotUserServiceImpl")
	DepotUserService depotuserSrv;
	
	@Resource(name="depotInertaerosolServiceImpl")
	DepotInertaerosolService depotInertaerosolService;//	惰性气溶液总表
	
	@Resource(name="tbinInertaerosolServiceImpl")
	TBinInertaerosolService tbinInertaerosolService;//	惰性气溶液分仓表
	
	@RequestMapping("/tbin_inertaerosol_list")//惰性气溶液分仓列表
	public String tbinInertaerosolList(){
		
		return "depoter/dataReport/depotInertaerosol/bin_inertaerosol_list";
		//返回的是空表，调用datagrid填充信息
		/*return "depoter/dataReport/depotInvest/depot_invest_list";*/
		
	}
	//
	
	//copy
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
	
	@RequestMapping("/getGrainBin")
	@ResponseBody
	public Page<TBinInertaerosol> getGrainBin(Pageable pageable,TBinInertaerosol queryParm, 
			HttpSession session, String Annual, String lcbm) {
		if (queryParm==null)
			queryParm = new TBinInertaerosol();
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			if(Annual != null) {
				queryParm.setAnnual(Integer.valueOf(Annual));
			}
			if(lcbm != null) {
				queryParm.setTGrainbin(binService.find(lcbm));
			}
			if(queryParm.getTGrainbin() == null) {
				if(!u.getManager()) {
					return null;
				}
			}
		}
		return tbinInertaerosolService.findPage(pageable, queryParm);
	}
	///end copy
	
	
	
	
	/*
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Page<TBinInertaerosol>getDataGrid(Pageable pageable,TBinInertaerosol queryParm, 
			HttpSession session,String Annual,String lcbm)throws Exception
	{
		if (queryParm==null )
			queryParm = new TBinInertaerosol();
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
	如何查询从用户--》粮库---》到仓
 * 
 * 
 * 	if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			
			if(Annual!=null){
				queryParm.setAnnual(Integer.valueOf(Annual));}
			if(lcbm!=null){
				queryParm.setTGrainbin(binService.find(lcbm));}
				
			if(queryParm.setTGrainbin()==null){
				if(!u.getManager()){
					return null;}
			}
		
			TDepotInvest d=depotInvestService.setGraindepot(u.getGraindepot());
			
			queryParm.setGrainBin(d.getGrainbin());
			
			
			*//**打出总表信息*//*
			System.out.println("粮仓编码------>"+queryParm.getGrainbin().getLcbm());
			*//**打出总表信息*//*
		}
		
		*//**打出总表信息*//*
		System.out.println("返回数据总数------>"+tbinInertaerosolService.findPage(pageable, queryParm).getTotal());
		System.out.println("年份------>"+tbinInertaerosolService.findPage(pageable, queryParm).getRows().get(0).getAnnual());
		*//**打出总表信息*//*
		
		return tbinInertaerosolService.findPage(pageable, queryParm);
		
	}*/
	//添加入口
		@RequestMapping("/addBinInertaerosolEntrance")
		public String addBinInertaerosolEntrance(HttpSession session,ModelMap model,String lcbm,Integer annual) 
				throws Exception {
			Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
			if (p!=null){
				DepotUser u = depotuserSrv.find(p.getUsername());
				Grainbin grainbin=binService.find(lcbm);
				
				//粮库基本信息
				model.addAttribute("lkbm",u.getGraindepot().getLkbm());
				model.addAttribute("lkmc",u.getGraindepot().getLkmc());
				model.addAttribute("postcode",u.getGraindepot().getPostcode());
				model.addAttribute("lkdz",u.getGraindepot().getLkdz());
				model.addAttribute("longtitude",u.getGraindepot().getLongtitude());		//经度
				model.addAttribute("altitude",u.getGraindepot().getAltitude());			//海拔
				model.addAttribute("latitude",u.getGraindepot().getLatitude());			//纬度
				
				//粮仓基本信息
				model.addAttribute("lcbm", grainbin.getLcbm());
				model.addAttribute("typebin", grainbin.getTypebin());
				model.addAttribute("capacity", grainbin.getCapacity());
				model.addAttribute("structureofbody", grainbin.getStructureofbody());
				model.addAttribute("designgrainheapheight", grainbin.getDesigngrainheapheight());
				model.addAttribute("longth", grainbin.getLongth());
				model.addAttribute("width", grainbin.getWidth());
				model.addAttribute("height", grainbin.getHeight());
				model.addAttribute("annual", annual);
				
				
				
			}else
				throw new Exception("session is losed!");
			return "depoter/dataReport/depotInertaerosol/addBinInertaerosol";
		}
	
		//添加出口，对输入的处理,需要遍历每个list
		@RequestMapping("/addBinInertaerosol")
		@ResponseBody
		public Json addBinInertaerosol(TBinInertaerosol binInertaerosol,String lcbm,Integer annual){
			Json j = new Json();
			//从页面传上来的东西，关系不会自动维护，需要手工维护主从关系
			binInertaerosol.setAnnual(annual);
			//如何新增一个呢？需要ITERATOR??
			
			try{
				if(lcbm==null){
					System.out.println("no lcbm");
				}
				if(binService.find(lcbm)==null){
					System.out.println("no bin");
				}
				binInertaerosol.setTGrainbin(binService.find(lcbm));
				
				tbinInertaerosolService.save(binInertaerosol);
				j.setSuccess(true);
				j.setMsg("添加成功！");
			}catch (Exception e) {
				j.setSuccess(false);
				j.setMsg(e.getMessage());
				e.printStackTrace();
			}
			return j;
		}
		
		//编辑入口????
		@RequestMapping("/editBinInertaerosolEntrance")
											//不支持string,在入口处将String改成了LONG lcbm
		public String editBinInertaerosolEntrance(HttpSession session,ModelMap model, String lcbm,Integer annual,Long id) 
				throws Exception {
			Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
			if (p!=null){
				/*lcbmString=lcbmString.trim();
				Long lcbm = Long.parseLong(lcbmString);
				*/
//				lcbm=lcbm.trim();
//				Long lcbml=Long.parseLong(lcbm);
				
//				DepotUser u = depotuserSrv.find(p.getUsername());
//				//不支持string,在入口处将String改成了LONG lcbm
//				TBinInertaerosol tbininertaerosol=tbinInertaerosolService.find(id);
//				Grainbin grainbin=binService.find(lcbm);
//			//	Grainbin grainbin =bininertaerosol.getTGrainbin();
				
				DepotUser u = depotuserSrv.find(p.getUsername());
				TBinInertaerosol binInertaerosol = tbinInertaerosolService.find(id);
				Grainbin grainbin = binInertaerosol.getTGrainbin();
				
				
				//粮库基本信息
				model.addAttribute("lkbm",u.getGraindepot().getLkbm());
				model.addAttribute("lkmc",u.getGraindepot().getLkmc());
				model.addAttribute("postcode",u.getGraindepot().getPostcode());
				model.addAttribute("lkdz",u.getGraindepot().getLkdz());
				model.addAttribute("longtitude",u.getGraindepot().getLongtitude());		//经度
				model.addAttribute("altitude",u.getGraindepot().getAltitude());			//海拔
				model.addAttribute("latitude",u.getGraindepot().getLatitude());			//纬度
				
				//粮仓基本信息
				model.addAttribute("grainbin", grainbin);
				model.addAttribute("lcbm", grainbin.getLcbm());
				model.addAttribute("typebin", grainbin.getTypebin());
				model.addAttribute("capacity", grainbin.getCapacity());
				model.addAttribute("structureofbody", grainbin.getStructureofbody());
				model.addAttribute("designgrainheapheight", grainbin.getDesigngrainheapheight());
				model.addAttribute("longth", grainbin.getLongth());
				model.addAttribute("width", grainbin.getWidth());
				model.addAttribute("height", grainbin.getHeight());
				//惰性气溶液基本信息
				model.addAttribute("binInertaerosol", binInertaerosol);
				
				//debug
				System.out.print(lcbm);
			
			}else
				throw new Exception("session is losed!");
			return "depoter/dataReport/depotInertaerosol/editBinInertaerosol";
		}
		
		//不知道
		@RequestMapping("/editBinInertaerosol")
		@ResponseBody
		public Json editBinInertaerosol(TBinInertaerosol tbinInertaerosol, Long id, String lcbm){
			Json j = new Json();
			/*
			lcbmString=lcbmString.trim();
			Long lcbm = Long.parseLong(lcbmString);*/
			
			//问题String如何对应起粮仓和编码
//			TBinInertaerosol tbinInertaerosol2=tbinInertaerosolService.find(id);
//			Grainbin grainbin = tbinInertaerosol2.getTGrainbin();
//			String lcbm1=grainbin.getLcbm();
//			tbinInertaerosol.setAnnual(tbinInertaerosol2.getAnnual());
			try{
				tbinInertaerosol.setTGrainbin(binService.find(lcbm));
				tbinInertaerosolService.update(tbinInertaerosol);
				j.setSuccess(true);
				j.setMsg("更新成功！");
			}catch (Exception e) {
				j.setSuccess(false);
				j.setMsg(e.getMessage());
			}
			return j;
		}

		@RequestMapping("/deleteBinReportRec")
		@ResponseBody
		public Json deleteReportRec(Long id){
			Json j = new Json();
			
			try{
				tbinInertaerosolService.delete(id);
				j.setSuccess(true);
				j.setMsg("删除成功！");
			}catch (Exception e) {   
				j.setSuccess(false);
				j.setMsg(e.getMessage());
			}
			return j;
		}
		
		
}

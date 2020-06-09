package com.grain.controller.depoter;

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



import com.grain.entity.user.DepotUser;


import com.grain.entity.DepotInertaerosol;


import com.grain.service.GrainbinService;
import com.grain.service.GraindepotService;
import com.grain.service.TDepotInvestService;
import com.grain.service.TBinInertaerosolService;
import com.grain.service.DepotInertaerosolService;
import com.grain.service.user.DepotUserService;



/*
 * 	惰性气溶液总表，包括：总表和分仓调查表
 *  
 */
@Controller
@RequestMapping("depoter/depotinertaerosol")
public class DepotInertaerosolController {

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
	
	
	@RequestMapping("/depot_inertaerosol_list")//惰性气溶液总表列表
	public String depotInertaerosolList(){
		
		return "depoter/dataReport/depotInertaerosol/depot_inertaerosol_list";
		//返回的是空表，调用datagrid填充信息
		
	}
	
	
	//
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Page<DepotInertaerosol>getDataGrid(Pageable pageable,DepotInertaerosol queryParm, 
			HttpSession session)throws Exception
	{
		if (queryParm==null )
			queryParm = new DepotInertaerosol();
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
			queryParm.setGraindepot(u.getGraindepot());
			
			
			/**打出总表信息*/
//			System.out.println("粮库编码------>"+queryParm.getGraindepot().getLkbm());
			/**打出总表信息*/
		}
		
/*		开始打数据*
		//打出总表信息
		DepotInertaerosol  queryParm2=new DepotInertaerosol();
		queryParm2.setGraindepot( depotuserSrv.find(p.getUsername()).getGraindepot());
		System.out.println("粮库编码------>"+queryParm2.getGraindepot().getLkbm());
		
		Page<DepotInertaerosol> pageDepotInertaerosol=depotInertaerosolService.findPage(pageable,queryParm2);
		System.out.println("返回数据总数------>"+pageDepotInertaerosol.getTotal());
		System.out.println("年份------>"+pageDepotInertaerosol.getRows().get(0).getAnnual());
		结束打数据**/
		
		/**打出总表信息*/
//		System.out.println("返回数据总数------>"+depotInertaerosolService.findPage(pageable, queryParm).getTotal());
//		System.out.println("年份------>"+depotInertaerosolService.findPage(pageable, queryParm).getRows().get(0).getAnnual());
		/**打出总表信息*/
		
		return depotInertaerosolService.findPage(pageable, queryParm);
		
	}
	//添加入口
	@RequestMapping("/addDepotInertaerosolEntrance")
	public String addDepotInertaerosolEntrance(HttpSession session,ModelMap model) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			DepotUser u = depotuserSrv.find(p.getUsername());
	//想要什么信息
			model.addAttribute("lkbm",u.getGraindepot().getLkbm());
			model.addAttribute("lkmc",u.getGraindepot().getLkmc());
			model.addAttribute("postcode",u.getGraindepot().getPostcode());
			model.addAttribute("lkdz",u.getGraindepot().getLkdz());
			model.addAttribute("longtitude",u.getGraindepot().getLongtitude());		//经度
			model.addAttribute("altitude",u.getGraindepot().getAltitude());			//海拔
			model.addAttribute("latitude",u.getGraindepot().getLatitude());			//纬度
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/depotInertaerosol/addDepotInertaerosol";
	}
	
	//添加出口，对输入的处理
	@RequestMapping("/addDepotInertaerosol")
	@ResponseBody
	public Json addDepotInertaerosol(DepotInertaerosol depotInertaerosol,String lkbm){
		Json j = new Json();
		//从页面传上来的东西，关系不会自动维护，需要手工维护主从关系
	
		try{
			depotInertaerosol.setGraindepot(depotSrv.find(lkbm));
			
			depotInertaerosolService.save(depotInertaerosol);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}
	//编辑入口
	@RequestMapping("/editDepotInertaerosolEntrance")
	public String editDepotInertaerosolEntrance(HttpSession session,ModelMap model, Long id,String lkbm) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			/*lkbmString=lkbmString.trim();
			Long lkbm = Long.parseLong(lkbmString);*/
			
			DepotUser u = depotuserSrv.find(p.getUsername());
			model.addAttribute("lkbm",u.getGraindepot().getLkbm());
			model.addAttribute("lkmc",u.getGraindepot().getLkmc());
			model.addAttribute("postcode",u.getGraindepot().getPostcode());
			model.addAttribute("lkdz",u.getGraindepot().getLkdz());
			model.addAttribute("longtitude",u.getGraindepot().getLongtitude());		//经度
			model.addAttribute("altitude",u.getGraindepot().getAltitude());			//海拔
			model.addAttribute("latitude",u.getGraindepot().getLatitude());			//纬度
			model.addAttribute("depotInertaerosol", depotInertaerosolService.find(id));
			//不知
			/*model.addAttribute("indexInsect", depotInvestService.find(sm).getTDepotsInsectses().size());
			model.addAttribute("indexDrug", depotInvestService.find(sm).getTDepotUseDrugs().size());*/
		}else
			throw new Exception("session is losed!");
		return "depoter/dataReport/depotInertaerosol/editDepotInertaerosol";
	}
	
	
	@RequestMapping("/editDepotInertaerosol")
	@ResponseBody
	public Json editDepotInertaerosol(DepotInertaerosol depotInertaerosol, String lkbm){
		Json j = new Json();
		
		try{
			depotInertaerosol.setGraindepot(depotSrv.find(lkbm));
			depotInertaerosolService.update(depotInertaerosol);
			j.setSuccess(true);
			j.setMsg("更新成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}

/*		try{
			depotInertaerosol.setGraindepot(depotSrv.find(lkbm));
			depotInertaerosolService.update(depotInertaerosol);
			j.setSuccess(true);
			j.setMsg("更新成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}*/

	@RequestMapping("/deleteReportRec")
	@ResponseBody
	public Json deleteReportRec(Long id){
		Json j = new Json();
		
		try{
			depotInertaerosolService.delete(id);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	
}

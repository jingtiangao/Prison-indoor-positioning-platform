package com.grain.controller.investor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.CommonAttributes;
import com.grain.Filter;
import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.Principal;
import com.grain.Filter.Operator;
import com.grain.Order.Direction;
import com.grain.entity.TConsultation;
import com.grain.entity.user.Expert;
import com.grain.entity.user.Insectsinvestuser;
import com.grain.service.TConsultationService;
import com.grain.service.user.ExpertService;
import com.grain.service.user.InsectsinvestuserService;
import com.grain.util.SpringUtils;

@Controller
@RequestMapping("/investor/consult")
public class InsectsinvestuserConsultController {
	
	@Resource(name = "tconsultationServiceImpl")
	TConsultationService consultSrv;
	
	@Resource(name = "insectsinvestuserServiceImpl")
	InsectsinvestuserService insectsinvestuserSrv;
	
	@Resource(name="expertServiceImpl")
	ExpertService expertSrv;
	
	@RequestMapping("/list")
	public String consultList() {
		return "/farmer/consult/list";
	}
	
	@RequestMapping("/publicDatagrid")
	@ResponseBody
	public Page<TConsultation> getPublicConsultDataGrid(Pageable pageable, String startDate, String endDate, TConsultation queryParm,
			HttpSession session)throws Exception {
		if(queryParm == null) 
			queryParm = new TConsultation();
		queryParm.setHasshare(true);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Filter> list = new ArrayList<Filter>();
		Filter filter;
		if(startDate != null && !startDate.equals("")) {
			filter = new Filter();
			filter.setProperty("consulttime");
			filter.setValue(startDate);
			filter.setOperator(Operator.ge);
			list.add(filter);
		}
		if(endDate != null && !endDate.equals("")) {
			Date end = dateFormat.parse(findDayAfterDay(endDate, 1, dateFormat));
			filter = new Filter();
			filter.setProperty("consulttime");
			filter.setValue(dateFormat.format(end));
			filter.setOperator(Operator.le);
			list.add(filter);
		}
		if(pageable==null) {
			pageable = new Pageable();
		}
		list.add(Filter.isNull("zwID"));
		pageable.setFilters(list);
		pageable.setOrderProperty("id");
		pageable.setOrderDirection(Direction.desc);
		
		
		return consultSrv.findPage(pageable, queryParm);
	}
	
	@RequestMapping("/repliedDatagrid")
	@ResponseBody
	public Page<TConsultation> getRepliedConsultDataGrid(Pageable pageable, String startDate, String endDate, TConsultation queryParm,
			HttpSession session)throws Exception {
		if (queryParm==null )
			queryParm = new TConsultation();
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			queryParm.setUsername(p.getUsername());
		}
		if(pageable==null) {
			pageable = new Pageable();
		}
		List<Filter> list = new ArrayList<Filter>();
		Filter filter;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(startDate != null && !startDate.equals("")) {
			filter = new Filter();
			filter.setProperty("consulttime");
			filter.setValue(startDate);
			filter.setOperator(Operator.ge);
			list.add(filter);
		}
		if(endDate != null && !endDate.equals("")) {
			Date end = dateFormat.parse(findDayAfterDay(endDate, 1, dateFormat));
			filter = new Filter();
			filter.setProperty("consulttime");
			filter.setValue(dateFormat.format(end));
			filter.setOperator(Operator.le);
			list.add(filter);
		}
		
		list.add(Filter.isNotNull("experttime"));
		//list.add(Filter.eq("sfzw", false));
		pageable.setFilters(list);
		pageable.setOrderProperty("experttime");
		pageable.setOrderDirection(Direction.desc);
		
//		queryParm.setModifer(factoryuserService.getCurrentUsername());
		return consultSrv.findPage(pageable, queryParm);
	}
	
	@RequestMapping("/unrepliedDatagrid")
	@ResponseBody
	public Page<TConsultation> getUnrepliedConsultDataGrid(Pageable pageable, String startDate, String endDate, TConsultation queryParm,
			HttpSession session)throws Exception {
		if (queryParm==null )
			queryParm = new TConsultation();
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			queryParm.setUsername(p.getUsername());
		}
		if(pageable==null) {
			pageable = new Pageable();
		}
		List<Filter> list = new ArrayList<Filter>();
		Filter filter;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if(startDate != null && !startDate.equals("")) {
			filter = new Filter();
			filter.setProperty("consulttime");
			filter.setValue(startDate);
			filter.setOperator(Operator.ge);
			list.add(filter);
		}
		if(endDate != null && !endDate.equals("")) {
			Date end = dateFormat.parse(findDayAfterDay(endDate, 1, dateFormat));
			filter = new Filter();
			filter.setProperty("consulttime");
			filter.setValue(dateFormat.format(end));
			filter.setOperator(Operator.le);
			list.add(filter);
		}
		
		list.add(Filter.isNull("experttime"));
		list.add(Filter.eq("sfzw", false));
		pageable.setFilters(list);
		pageable.setOrderProperty("id");
		pageable.setOrderDirection(Direction.desc);
		
//		queryParm.setModifer(factoryuserService.getCurrentUsername());
		return consultSrv.findPage(pageable, queryParm);
	}
	
	@RequestMapping("/getExpert")
	@ResponseBody
	public Page<Expert> getExpert(@RequestParam(value="q") String key,Pageable pageable) {
			pageable.setSearchProperty("name");
			pageable.setSearchValue(key);
			return expertSrv.findPage(pageable);
	}
	
	@RequestMapping("/addConsultEntrance")
	public String addConsultEntrance(Model model) {
		model.addAttribute("insectsinvestuser", insectsinvestuserSrv.getCurrent());
		return "/farmer/consult/add";
	}
	
	@RequestMapping("/addConsult")
	@ResponseBody
	public Json addConsultation(HttpServletRequest req, TConsultation consultation, String expertuser) {
		Json json = new Json();
		if(expertuser.equals("-1")) {
			consultation.setExpert(expertSrv.find(consultSrv.findExpert()));
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Insectsinvestuser insectsinvestuser = insectsinvestuserSrv.getCurrent();
		consultation.setUsername(insectsinvestuser.getUsername());
		consultation.setConsulttime(dateFormat.format(new Date()));
		consultation.setSfzw(false);
		consultation.setExpert(expertSrv.findByUsername(expertuser));
		try {
			consultSrv.save(consultation);
			json.setSuccess(true);
			json.setMsg(SpringUtils.getMessage("添加成功"));
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	@RequestMapping("/editConsultEntrance")
	public String editConsultEntrance(Long id, Model model) {
		String[] str = new String[]{"害虫识别","害虫防治","其他"};
		TConsultation consult = consultSrv.find(id);
		model.addAttribute("id", id);
		model.addAttribute("typename", str[consult.getType()]);
		model.addAttribute("expertname", consult.getExpert().getName());
		model.addAttribute("title", consult.getTitle());
		model.addAttribute("type", consult.getType());
		model.addAttribute("describle", consult.getDescrible());
		model.addAttribute("expertuser", consult.getExpert().getUsername());
		model.addAttribute("hasshare", consult.isHasshare());
		
		return "/farmer/consult/edit";
	}
	
	@RequestMapping("/editConsult")
	@ResponseBody
	public Json editConsultation(HttpServletRequest req, Long id, TConsultation consultation, String expertuser) {
		Json json = new Json();
		
		
		if(expertuser.equals("-1")) {

		}
		Insectsinvestuser insectsinvestuser = insectsinvestuserSrv.getCurrent();
		consultation.setUsername(insectsinvestuser.getUsername());
		consultation.setExpert(expertSrv.findByUsername(expertuser));
		try {
			consultation.setId(id);
			consultSrv.update(consultation);
			json.setSuccess(true);
			json.setMsg(SpringUtils.getMessage("修改成功"));
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	@RequestMapping("/deleteConsultation")
	@ResponseBody
	public Json deleteConsultation(Long id) {
		Json j = new Json();
		
		try {
			consultSrv.delete(id);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		
		return j;
	}
	
/*	//查看咨询详细内容以及追问的咨询内容（不用于修改咨询）
	@RequestMapping("/getConsultContent")
	public String pursueConsultationEntrance(Long id, Model model) {
		
		String[] str = new String[]{"害虫识别","害虫防治","其他"};
		TConsultation consult = consultSrv.find(id);
		//判断是否是追问的咨询,以保证consult是根咨询
		if(consult.isSfzw()) {
			consult = consultSrv.find(consult.getZwID());
		}
		model.addAttribute("id", id);
		model.addAttribute("typename", str[consult.getType()]);
		model.addAttribute("expertname", consult.getExpert().getName());
		model.addAttribute("title", consult.getTitle());
		model.addAttribute("type", consult.getType());
		model.addAttribute("describle", consult.getDescrible());
		model.addAttribute("expertuser", consult.getExpert().getUsername());
		
		TConsultation queryParm = new TConsultation();
		queryParm.setZwID(id);
		Pageable pageable = new Pageable();
		pageable.setOrderProperty("id");
		pageable.setOrderDirection(Direction.asc);
		//获取根咨询下的追问咨询page
		model.addAttribute("page", consultSrv.findPage(pageable, queryParm));
		
		return "/farmer/consult/consultcontent";
	}*/
	
	//追问咨询入口
	@RequestMapping("/pursueConsultEntrance")
	public String pursueConsultEntrance(Long id, Model model) {
		TConsultation consult = consultSrv.find(id);
		String[] str = new String[]{"害虫识别","害虫防治","其他"};
		model.addAttribute("title", consult.getTitle());
		model.addAttribute("typename", str[consult.getType()]);
		model.addAttribute("name", consult.getExpert().getName());
		model.addAttribute("describle", consult.getDescrible());
		model.addAttribute("expertadvice", consult.getExpertadvice());
		model.addAttribute("id", id);
		return "/investor/consult/pursue";
	}
	
	//追问咨询提交
	@RequestMapping("/pursueConsult")
	@ResponseBody
	public Json pursueConsultation(HttpServletRequest req, Long id, String describle, String expertuser) {
		Json json = new Json();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		TConsultation oldConsultation =  consultSrv.find(id);
		TConsultation newConsult = new TConsultation();
		newConsult.setConsulttime(dateFormat.format(new Date()));
		newConsult.setDescrible(describle);
		newConsult.setExpert(oldConsultation.getExpert());
		newConsult.setHasshare(oldConsultation.isHasshare());
		newConsult.setTitle(oldConsultation.getTitle());
		newConsult.setType(oldConsultation.getType());
		newConsult.setUsername(oldConsultation.getUsername());
		oldConsultation.setZw(newConsult);
		oldConsultation.setSfzw(true);
		try {
			consultSrv.save(newConsult);
			consultSrv.update(oldConsultation);
			json.setSuccess(true);
			json.setMsg(SpringUtils.getMessage("添加成功"));
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
	
	
	
	private String findDayAfterDay(String day, Integer count, SimpleDateFormat dateFormat) throws ParseException{
		Calendar tp = Calendar.getInstance();
		tp.setTime(dateFormat.parse(day));
		tp.add(Calendar.DATE, count);
		return dateFormat.format(tp.getTime());
	}

}

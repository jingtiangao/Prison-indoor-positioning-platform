package com.grain.controller.expert;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.Filter;
import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.Order.Direction;
import com.grain.entity.TConsultation;
import com.grain.service.TConsultationService;
import com.grain.service.user.ExpertService;
import com.grain.util.SpringUtils;

@RequestMapping("/expert/consult")
@Controller
public class ExpertReplyConsultationController {
	
	//专家用户service
	@Resource(name="expertServiceImpl")
	ExpertService expertService;
	
	//咨询service
	@Resource(name = "tconsultationServiceImpl")
	TConsultationService consultSrv;
	
	@RequestMapping("/list")
	public String getExpertConsultList() {
		return "/expert/consult/list";
	}
	
	@RequestMapping("/repliedDatagrid")
	@ResponseBody
	public Page<TConsultation> getRepliedConsultDataGrid(Pageable pageable, TConsultation queryParm,
			HttpSession session)throws Exception {
		if (queryParm==null )
			queryParm = new TConsultation();
		queryParm.setExpert(expertService.getCurrent());
		if(pageable==null) {
			pageable = new Pageable();
		}
		List<Filter> list = new ArrayList<Filter>();
		list.add(Filter.isNotNull("experttime"));
		list.add(Filter.eq("sfzw", false));
		pageable.setFilters(list);
		pageable.setOrderProperty("id");
		pageable.setOrderDirection(Direction.desc);
		Page<TConsultation> page = consultSrv.findPage(pageable, queryParm);
		List<TConsultation> pageList = page.getRows();
		for (TConsultation tConsultation : pageList) {
			//添加筛选代码
			Pageable pageable1 = new Pageable();
			pageable1.setOrderProperty("id");
			pageable1.setOrderDirection(Direction.desc);
			TConsultation queryParm1 = new TConsultation();
			queryParm1.setSfzw(true);
			queryParm1.setZw(tConsultation);
			Page<TConsultation> page1 = consultSrv.findPage(pageable1, queryParm1);
			
			List<TConsultation> pageList1 = page1.getRows();
			if(pageList1 != null && pageList1.size() != 0) {
				TConsultation zwConsultation = pageList1.get(0);
				if(zwConsultation.getExperttime() == null) {
					pageList.remove(tConsultation);
					page.setTotal(page.getTotal() - 1);
				}		
			}			
		}
//		queryParm.setModifer(factoryuserService.getCurrentUsername());
		return page;
	}
	
	@RequestMapping("/unrepliedDatagrid")
	@ResponseBody
	public Page<TConsultation> getUnrepliedConsultDataGrid(Pageable pageable, TConsultation queryParm,
			HttpSession session)throws Exception {
		if (queryParm==null )
			queryParm = new TConsultation();
		queryParm.setExpert(expertService.getCurrent());
		queryParm.setSfzw(false);
		if(pageable==null) {
			pageable = new Pageable();
		}
		List<Filter> list = new ArrayList<Filter>();
		list.add(Filter.isNull("experttime"));
		pageable.setFilters(list);
		pageable.setOrderProperty("id");
		pageable.setOrderDirection(Direction.desc);
		
		Page<TConsultation> page1 = consultSrv.findPage(pageable, queryParm);
		
		TConsultation queryParm1 = new TConsultation();
		queryParm1.setExpert(expertService.getCurrent());
		queryParm1.setSfzw(true);
		Page<TConsultation> page2 = consultSrv.findPage(pageable, queryParm1);
		List<TConsultation> consultList = page2.getRows();
		HashSet<Long> set = new HashSet<Long>();
		if(consultList != null && consultList.size() != 0) {
			for (TConsultation consult : consultList) {
				set.add(consult.getId());
			}
			for (Long long1 : set) {
				page1.getRows().add(consultSrv.find(long1));
				page1.setTotal(page1.getTotal() + 1);
			}
		}		
//		queryParm.setModifer(factoryuserService.getCurrentUsername());
		return page1;
	}
	
	@RequestMapping("/replyConsultEntrance")
	public String editConsultEntrance(Long id, Model model) {
		String[] str = new String[]{"害虫识别","害虫防治","其他"};
		TConsultation consult = consultSrv.find(id);
		Pageable pageable = new Pageable();
		TConsultation queryParm = new TConsultation();
		queryParm.setZw(consultSrv.find(id));
		queryParm.setSfzw(true);
		model.addAttribute("page", consultSrv.findPage(pageable, queryParm));

		model.addAttribute("id", id);
		model.addAttribute("typename", str[consult.getType()]);
		model.addAttribute("expertname", consult.getExpert().getName());
		model.addAttribute("title", consult.getTitle());
		model.addAttribute("type", consult.getType());
		model.addAttribute("describle", consult.getDescrible());
		model.addAttribute("expertuser", consult.getExpert().getUsername());
		
		return "/expert/consult/reply";
	}
	
	@RequestMapping("/replyConsult")
	@ResponseBody
	public Json editConsultation(HttpServletRequest req,Long id,String expertadvice) {
		Json json = new Json();
		
		TConsultation consultation = consultSrv.find(id);
		
		try {
			consultation.setExperttime(new Date());
			consultation.setExpertadvice(expertadvice);
			consultSrv.update(consultation);
			json.setSuccess(true);
			json.setMsg(SpringUtils.getMessage("修改成功"));
		} catch (Exception e) {
			json.setSuccess(false);
			json.setMsg(e.getMessage());
		}
		return json;
	}
}

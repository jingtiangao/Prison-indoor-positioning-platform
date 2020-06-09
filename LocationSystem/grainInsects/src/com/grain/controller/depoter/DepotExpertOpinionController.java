package com.grain.controller.depoter;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.CommonAttributes;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.Principal;
import com.grain.entity.Graindepot;
import com.grain.entity.TExpertOpinion;
import com.grain.entity.user.DepotUser;
import com.grain.service.GraindepotService;
import com.grain.service.TExpertOpinionService;
import com.grain.service.user.DepotUserService;

@Controller
@RequestMapping("/depoter/expertopinion")
public class DepotExpertOpinionController {

			//专家意见表
			@Resource(name = "tExpertOpinionServiceImpl")
			TExpertOpinionService tExpertOpinionService;
			
			//粮库
			@Resource(name = "grainDepotServiceImpl")
			private GraindepotService grainDepotService;
			
			@Resource(name = "depotUserServiceImpl")
			DepotUserService depotuserSrv;
			
			/**
			 * 列表
			 */
			@RequestMapping(value = "/list" )
			public String list() {
				return "/depoter/opinion/ideaList";
			}
			
			/**
			 * 列表数据
			 */
			@RequestMapping("/dataGrid")
			@ResponseBody
			public List<TExpertOpinion>getDataGrid( Pageable pageable,TExpertOpinion queryParm, 
					HttpSession session)throws Exception
			{
				Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
				DepotUser u = new DepotUser();
				if (queryParm==null )
					queryParm = new TExpertOpinion();
				Page<TExpertOpinion> l = tExpertOpinionService.findPage(pageable, queryParm);
				List <TExpertOpinion> ll = l.getRows();
				List <TExpertOpinion> lr = new ArrayList<TExpertOpinion> ();
				if(p!=null){
					u = depotuserSrv.findByUsername(p.getUsername());
					int size = ll.size();
					for(int i=0; i<size ; i++){
						List<Graindepot> graindepots = ll.get(i).getGrainDepots();
						if(graindepots.contains(u.getGraindepot())){
							lr.add(ll.get(i));
						}
					}
				}
				return lr;
			}

			/**
			 * 查看
			 */
			@RequestMapping(value = "/getIdea")
			public String getIdea(Long id, ModelMap model) {
				model.addAttribute("expertopinion", tExpertOpinionService.find(id));
				model.addAttribute("graindepots", grainDepotService.findAll());
				return "/depoter/opinion/viewIdea";
			}

}

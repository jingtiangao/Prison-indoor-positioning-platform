package com.grain.controller.expert;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.CommonAttributes;
import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.Principal;
import com.grain.entity.Graindepot;
import com.grain.entity.TExpertOpinion;
import com.grain.service.GraindepotService;
import com.grain.service.TExpertOpinionService;
import com.grain.service.user.ExpertService;

@Controller
@RequestMapping("/expert/opinion")
public class ExpertOpinionController {

		//专家意见表
		@Resource(name = "tExpertOpinionServiceImpl")
		TExpertOpinionService tExpertOpinionService;
		
		//粮库
		@Resource(name = "grainDepotServiceImpl")
		private GraindepotService grainDepotService;
		
		//专家
		@Resource(name = "expertServiceImpl")
		private ExpertService expertUserService;
		
		/**
		 * 列表
		 */
		@RequestMapping(value = "/list" )
		public String list() {
			return "/expert/opinion/ideaList";
		}
		
		/**
		 * 列表数据
		 */
		@RequestMapping("/dataGrid")
		@ResponseBody
		public Page<TExpertOpinion>getDataGrid(Pageable pageable,TExpertOpinion queryParm, 
				HttpSession session)throws Exception
		{
			if (queryParm==null )
				queryParm = new TExpertOpinion();
			return tExpertOpinionService.findPage(pageable, queryParm);
		}
		
		/**
		 * 添加
		 * 
		 */
		@RequestMapping(value = "/addIdeaEntrance", method = RequestMethod.POST)
		public String add( HttpSession session, ModelMap model) {
			model.addAttribute("graindepots",grainDepotService.findModelDepots());
			Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
			if(p!=null)
				model.addAttribute("expert",expertUserService.findByUsername(p.getUsername()));
			return "/expert/opinion/addIdea";
		}

		/**
		 * 获得所有粮库
		 * @throws Exception 
		 */
		@RequestMapping(value = "/getGrainDepots", method = RequestMethod.POST)
		@ResponseBody
		public List<Graindepot> getGrainDepots(HttpSession session) throws Exception{
			Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
			if (p == null) throw new Exception("session is losed!");
			return grainDepotService.findAll();
		}
		/**
		 * 添加保存
		 */
		@RequestMapping(value = "/addIdea", method = RequestMethod.POST)
		@ResponseBody
		public Json save(HttpSession session, TExpertOpinion tExpertOpinion, @RequestParam(value = "lkbms[]", required=true)String[] lkbms) {
			Json j = new Json();
			
			try{
				List <Graindepot> grainDepots = new ArrayList<Graindepot>();
				//设置多对多关系的粮库
				if(lkbms.length == 1){
					String t = lkbms[0];
					Graindepot temp = grainDepotService.find(t);
					grainDepots.add(temp);
				}
				else
					grainDepots = grainDepotService.findList(lkbms);
				tExpertOpinion.setGrainDepots(grainDepots);
				
				Date nowDate = new Date();
				SimpleDateFormat at=new SimpleDateFormat("yyyy-MM-dd");
				String createtime=at.format(nowDate);
				tExpertOpinion.setCreatedate(createtime);
				//设置一对多
				Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
				tExpertOpinion.setExpert(expertUserService.findByUsername(p.getUsername()));
				
				tExpertOpinionService.save(tExpertOpinion);
				
				j.setSuccess(true);
				j.setMsg("添加成功！");
			}catch(Exception e){
				j.setSuccess(false);
				j.setMsg(e.getMessage());
				e.printStackTrace();
			}
			return j;
		}

		/**
		 * 查看
		 */
		@RequestMapping(value = "/getIdea")
		public String getIdea(Long id, ModelMap model) {
			model.addAttribute("expertopinion", tExpertOpinionService.find(id));
			model.addAttribute("graindepots", grainDepotService.findAll());
			return "/expert/opinion/viewIdea";
		}
		
		/**
		 * 编辑
		 */
		@RequestMapping(value = "/editIdeaEntrance")
		public String editIdeaEntrance(HttpSession session, Long id, ModelMap model) {
			model.addAttribute("id", id);
			Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
			if(p!=null)
				model.addAttribute("expert",expertUserService.findByUsername(p.getUsername()));
			model.addAttribute("expertopinion", tExpertOpinionService.find(id));
			model.addAttribute("graindepots", grainDepotService.findAll());
			return "/expert/opinion/editIdea";
		}
		
		/**
		 * 编辑保存
		 */
		@RequestMapping(value = "/editIdea", method = RequestMethod.POST)
		@ResponseBody
		public Json editIdea(HttpSession session, TExpertOpinion tExpertOpinion, @RequestParam(value = "lkbms[]", required=true)String[] lkbms) {
			Json j = new Json();
			
			try{
				List <Graindepot> grainDepots = new ArrayList<Graindepot>();
				//设置多对多关系的粮库
				if(lkbms.length == 1){
					String t = lkbms[0];
					Graindepot temp = grainDepotService.find(t);
					grainDepots.add(temp);
				}
				else
					grainDepots = grainDepotService.findList(lkbms);
				tExpertOpinion.setGrainDepots(grainDepots);
				
				tExpertOpinionService.update(tExpertOpinion,"expert","createdate");
				
				j.setSuccess(true);
				j.setMsg("编辑成功！");
			}catch(Exception e){
				j.setSuccess(false);
				j.setMsg(e.getMessage());
				e.printStackTrace();
			}
			return j;
		}

		/**
		 * 删除
		 */
		@RequestMapping(value = "/delete", method = RequestMethod.POST)
		public @ResponseBody
		Json delete(Long id) {
			Json j = new Json();
			try{
				tExpertOpinionService.delete(id);
				j.setSuccess(true);
				j.setMsg("删除成功！");
			}catch(Exception e){
				j.setSuccess(false);
				j.setMsg(e.getMessage());
				e.printStackTrace();
			}
			return j;
		}
}

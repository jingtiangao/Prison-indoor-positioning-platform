package com.grain.controller.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ServletContextAware;

import com.grain.Json;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.entity.Area;
import com.grain.entity.Graindirection;
import com.grain.service.GraindirectionService;
import com.grain.util.SpringUtils;

/**
 * 
 * @author szy
 *	储粮区维护
 */

@Controller
@RequestMapping("/admin/graindirection")
public class GrainDirctionCtrl implements ServletContextAware{

	
	@Value("${system.name}")
	private String systemName;
	@Value("${system.version}")
	private String systemVersion;
	@Value("${system.description}")
	private String systemDescription;
	@Value("${system.show_powered}")
	private Boolean systemShowPowered;
	/** servletContext */
	private ServletContext servletContext;
	
	@Resource(name = "graindirectionServiceImpl")
	private GraindirectionService gdSrv;
	
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}
	
	@RequestMapping("/list")
	public String manager(ModelMap model) {
		//model.addAttribute("FlashMessage", "ssss");
		
		
		model.addAttribute("systemName", systemName);
		model.addAttribute("systemVersion", systemVersion);
		model.addAttribute("systemDescription", systemDescription);
		model.addAttribute("systemShowPowered", systemShowPowered);
		model.addAttribute("javaVersion", System.getProperty("java.version"));
		model.addAttribute("javaHome", System.getProperty("java.home"));
		model.addAttribute("osName", System.getProperty("os.name"));
		model.addAttribute("osArch", System.getProperty("os.arch"));
		model.addAttribute("serverInfo", servletContext.getServerInfo());
		model.addAttribute("servletVersion", servletContext.getMajorVersion() + "." + servletContext.getMinorVersion());
		return "/admin/graindirect/list";
	}
	
	@RequestMapping("/getdata")
	@ResponseBody
	public Page<Graindirection> getDataGrid(Pageable pageable,Graindirection queryParm
			) {
		return gdSrv.findPage(pageable,queryParm);
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Json add(Graindirection gd) {
		Json j = new Json();
		try{
			gdSrv.save(gd);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Graindirection gd) {
		Json j = new Json();
		try {
			gdSrv.update(gd);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}

		return j;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Long id) {
		Json j = new Json();
		Graindirection gd =gdSrv.find(id); 
		if (gd!=null && gd.getAreas()!=null && !gd.getAreas().isEmpty() ){
			j.setSuccess(false);
			j.setMsg(SpringUtils.getMessage("admin.graindirection.deleteExistChildrenNotAllowed"));
			return j;
		}
		try {
			gdSrv.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	/**
	 * 得到指定区域的城市信息
	 * @param id
	 * @return
	 */
	@RequestMapping("getAreas")
	@ResponseBody
	public List<String> getAreas(Long id){
		List<String> myareas = new ArrayList<String>();
		
		/*List<Graindirection> graindirections = gdSrv.findAll();
		for(Graindirection graindirection : graindirections){
			for(Area area : graindirection.getAreas()){
				if(area.getParent() != null){
					myareas.add(area.getName());
				}
			}
		}*/
		Set<Area> areas = gdSrv.find(id).getAreas();
		for(Area area : areas){
			if(area.getParent() != null){
				myareas.add(area.getName());
			}
		}
		return myareas;
	}
}

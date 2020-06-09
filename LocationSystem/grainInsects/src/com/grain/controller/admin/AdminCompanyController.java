package com.grain.controller.admin;

import java.util.Date;

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
import com.grain.entity.Company;
import com.grain.service.CompanyService;
import com.grain.service.user.AdminService;

@Controller
@RequestMapping("/admin/company")
public class AdminCompanyController extends BaseController{
	
	@Resource(name= "companyServiceImpl")
	CompanyService companyService;
	
	@Resource(name="adminServiceImpl")
	AdminService admin;
	

	@RequestMapping("/list")
	public String list() {
		return "/admin/company/list";
	}
		
	@RequestMapping("/getList")
	@ResponseBody
	public Page<Company> getDataGrid(Pageable pageable,Company queryParm, 
			HttpSession session)throws Exception
	{
		if (queryParm==null )
			queryParm = new Company();
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p==null){
			throw new Exception("session is losed!");	
		}
		
		Page<Company> map1 = companyService.findPage(pageable, queryParm);
		return map1;
		
	}
	
	@RequestMapping("/addCompanyEntrance")
	public String addTInsectsIndexEntrance(HttpSession session,ModelMap model) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p==null){
			throw new Exception("session is losed!");	
		}
		return "admin/company/add";
	}
	
	@RequestMapping("/addCompany")
	@ResponseBody
	public Json addCompany(HttpSession session,Company company) throws Exception{
		Json j = new Json();
		//从页面传上来的东西，关系不会自动维护，需要手工维护主从关系
		
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p==null){
			throw new Exception("session is losed!");	
		}
		
		company.setModifer(p.getUsername());
		Date nowDate = new Date();
		company.setModifydate(nowDate);
		
		try {
			companyService.save(company);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;
	}
	
	@RequestMapping("/editCompanyEntrance")
	public String editCompany(HttpSession session,ModelMap model,Long id) 
			throws Exception {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			
			Company company = companyService.find(id);

			model.addAttribute("company", company);
			
			
		}else
			throw new Exception("session is losed!");
		return "admin/company/edit";
	}
	
	@RequestMapping("/editCompany")
	@ResponseBody
	public Json editTInsectsIndex(HttpSession session,Company company) throws Exception{
		Json j = new Json();
		//从页面传上来的东西，关系不会自动维护，需要手工维护主从关系
		
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p==null){
			throw new Exception("session is losed!");	
		}
		
		company.setModifer(p.getUsername());
		Date nowDate = new Date();
		company.setModifydate(nowDate);
		
		try {
			companyService.update(company);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
			e.printStackTrace();
		}
		return j;

	}
	
	@RequestMapping("/deleteCompany")
	@ResponseBody
	public Json deleteReportRec(Long id){
		Json j = new Json();
		
		try{
			companyService.delete(id);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch (Exception e) {
			j.setSuccess(false);
			j.setMsg(e.getMessage());
		}
		return j;
	}
	

}

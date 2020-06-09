package com.grain.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.CommonAttributes;
import com.grain.EasyUItreeNode;
import com.grain.Principal;
import com.grain.service.user.MenuService;

@Controller
@RequestMapping("/admin/menu")
public class AdminMenu {
	@Resource(name = "menuServiceImpl")
	private MenuService mmgr;
	
	@RequestMapping("/display")
	@ResponseBody
	public List<EasyUItreeNode> getMenuNode(HttpSession session,HttpServletRequest req) {
		// 取得当前用户id;
		Principal principal=(Principal) req.getSession().getAttribute(CommonAttributes.Principal);
		String username = principal.getUsername();
		String id = req.getParameter("id");
		return mmgr.getEasyNode(username,id);
	}
}

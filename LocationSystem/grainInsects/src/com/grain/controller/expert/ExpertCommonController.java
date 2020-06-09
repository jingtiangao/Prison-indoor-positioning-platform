package com.grain.controller.expert;

import java.security.MessageDigest;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.CommonAttributes;
import com.grain.Json;
import com.grain.Principal;
import com.grain.entity.user.DepotUser;
import com.grain.entity.user.Expert;
import com.grain.entity.user.FarmerUser;
import com.grain.service.CaptchaService;
import com.grain.service.user.AdminService;
import com.grain.service.user.ExpertService;

@Controller
@RequestMapping("expert/common")
public class ExpertCommonController {
	
	@Resource(name = "captchaServiceImpl")
	private CaptchaService captchaService;
	
	@Resource(name = "expertServiceImpl")
	private ExpertService expertsrv;
	
	@RequestMapping("/main")
	public String mainEntrance(HttpSession session,ModelMap model) {
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		if (p!=null){
			Expert u = expertsrv.findByUsername(p.getUsername());
			if (u==null) return "redirect:/common/login";
			model.addAttribute("userName",u.getUsername());
		} else
			return "redirect:/common/login";
		return "expert/main";
	}
	
	@RequestMapping(value="insectsDistribution")
	public String insectsDistribution(ModelMap model){
		return "expert/footmark/insectsDistribution";
	}
	
	@RequestMapping(value="footMark")
	public String footMark(ModelMap model){
		return "expert/footmark/footMark";
	}
	
	@RequestMapping("/user/editExpertEntrance")
	public String editExpertEntrance(HttpSession session, Model model) throws Exception{
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username = "";
		if(p != null){
			username = p.getUsername();
		}else{
			throw new Exception("session is losed!");
		}
		
		model.addAttribute("user", expertsrv.findByUsername(username));
		return "expert/user/editUserSelf";
	}
	
	@RequestMapping("/user/editPasswordEntrance")
	public String editPasswordEntrance(HttpSession session, Model model) throws Exception{
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username = "";
		if(p != null){
			username = p.getUsername();
		}else{
			throw new Exception("session is losed!");
		}
		
		model.addAttribute("user", expertsrv.findByUsername(username));
		return "expert/user/editPassword";
	}
	
	@RequestMapping("/user/editExpert")
	@ResponseBody
	public Json editInvestor(Expert expert){
		Json json = new Json();
		expert.setHasaudit(true);
		try {
			expertsrv.update(expert);
			json.setSuccess(true);
			json.setMsg("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			json.setSuccess(false);
			json.setMsg("修改失败");
		}
		return json;
		
	}
	
	@RequestMapping("/user/editExpertPass")
	@ResponseBody
	public Json editExpertPass(Expert expert,@RequestParam(value="oldpassword") String oldpassword){
		Json json = new Json();
		
		Expert expertuser = expertsrv.findByUsername(expert.getUsername());
		String originalpassword = expertuser.getPassword();
		
		String pass = new String();
		 try {
		        //生成实现指定摘要算法的 MessageDigest 对象。
		        MessageDigest md = MessageDigest.getInstance("MD5");  
		        //使用指定的字节数组更新摘要。
		        md.update(oldpassword.getBytes());
		        //通过执行诸如填充之类的最终操作完成哈希计算。
		        byte b[] = md.digest();
		        //生成具体的md5密码到buf数组
		        int i;
		        StringBuffer buf = new StringBuffer("");
		        for (int offset = 0; offset < b.length; offset++) {
		          i = b[offset];
		          if (i < 0)
		            i += 256;
		          if (i < 16)
		            buf.append("0");
		          buf.append(Integer.toHexString(i));
		        }
		        pass = buf.toString();
//		        System.out.println("32位: " + buf.toString());// 32位的加密
//		        System.out.println("16位: " + buf.toString().substring(8, 24));// 16位的加密，其实就是32位加密后的截取
		     }
		     catch (Exception e) {
		       e.printStackTrace();
		     }

		if(originalpassword.equals(pass)){
			expert.setHasaudit(true);
			try {
				expertsrv.update(expert);
				json.setSuccess(true);
				json.setMsg("修改成功");
			} catch (Exception e) {
				e.printStackTrace();
				json.setSuccess(false);
				json.setMsg("修改失败");
			}
		}else{
			json.setSuccess(false);
			json.setMsg("原密码不正确");
		}
		return json;
		
	}
	
}

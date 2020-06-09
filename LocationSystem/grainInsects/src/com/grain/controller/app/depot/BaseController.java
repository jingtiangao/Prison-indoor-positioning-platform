package com.grain.controller.app.depot;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.grain.CommonAttributes;
import com.grain.Message;
import com.grain.Principal;
import com.grain.entity.Grainbin;
import com.grain.entity.user.DepotUser;
import com.grain.service.FileService;
import com.grain.service.GrainPicService;
import com.grain.service.GrainService;
import com.grain.service.GrainbinService;
import com.grain.service.GraindepotService;
import com.grain.service.user.DepotUserService;
import com.grain.util.SpringUtils;
import com.grain.util.StringUtil;

import excel.exp.List2Excel;

public class BaseController {
	@Resource(name = "depotUserServiceImpl")
	DepotUserService depotuserSrv;
	
	@Resource(name = "fileServiceImpl")
	FileService fileService;
	
	@Resource(name="grainDepotServiceImpl")
	GraindepotService depotService;	//粮库
	
	@Resource(name="grainBinServiceImpl")
	GrainbinService binService;	//粮仓

	@Resource(name="grainServiceImpl")
	GrainService grainService;	//粮食

	@Resource(name="grainPicServiceImpl")
	GrainPicService grainPicService;	//粮食

	public static String getPath(String Path) {
		StringBuffer sf = new StringBuffer(Path); //
	
		if(isWin()){
		if (!Path.endsWith("\\")) {
			sf.append("\\");
		}
		}
		else if
			(Path.endsWith("\\")){
			int startIndex = sf.indexOf("\\");
			sf.replace(startIndex,startIndex+1,"");
		}
		else if(!Path.endsWith("/")){
		sf.append("/");	
		}
		createDir(Path); //删除目录

		return sf.toString();

	}
	
	  private static boolean isWin() {
    	  String OS = System.getProperty("os.name").toLowerCase();
    	  if(OS.indexOf("windows")>-1)
    		  return true;
    	  else return false;
    }
	  
	public static boolean createDir(String DirName) {
		File f = new File(DirName);
		if (!f.exists()) {
			return f.mkdirs();
		}
		return true;
	}
	
	public static void deleteDir(String path){
		try{
		File file = new File(path);
		if (!file.exists())	createDir(path);
		if(file!=null){
			String [] fileNames = file.list();
			for (int i=0; i<fileNames.length;i++){
				File delFile = new File(path + fileNames[i]);
				delFile.delete();
			}
		}

			
		}catch (Exception e){
			return;
		}
	}

	/**
	 * 导出Excel
	 * 
	 * @author 束欢
	 * @date 2010-05-06
	 * @param request
	 * @param response
	 * @param datas //
	 *            数据list
	 * @param fileName //
	 *            Excel文件名
	 * @param tableTitle //
	 *            Excel表标题
	 * @param configFile //
	 *            配置文件
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	protected void export2Excel(HttpServletRequest request,
			HttpServletResponse response, List datas, String fileName,
			String tableTitle, String configFile) throws IOException,
			UnsupportedEncodingException, FileNotFoundException {
		// 在临时目录下生成Excel：
		String userdir = getUserDir(request);
		String filefullname = userdir + fileName + ".xls";
		String cfgfiledir = getPath(getDir(request) + "pojo2excel");

		List2Excel l2e = List2Excel.getInstance(cfgfiledir + configFile,
				tableTitle, filefullname, datas);
		l2e.generateExcel();

		// 将压缩文件写出到OutStream中去：
		fileName = new String(fileName.getBytes("GBK"),"iso8859-1"); 
		response.addHeader("Content-Disposition", "attachment;filename="
				+ fileName + ".xls");
		response.setHeader("Connection", "close");
		response.setHeader("Content-Type", "application/vnd.ms-excel");

		//String zipfilefullname = userdir + zipFileName;
		FileInputStream fis = new FileInputStream(new File(filefullname));
		BufferedInputStream bis = new BufferedInputStream(fis);
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);

		byte[] bytes = new byte[1024];
		int i = 0;
		while ((i = bis.read(bytes, 0, bytes.length)) != -1) {
			bos.write(bytes);
		}
		bos.flush();
		bis.close();
		bos.close();

		// 删除缓存目录
		deleteDir(userdir);
	}
	
	public String getUserDir(HttpServletRequest request) {
		String path = getPath(getDir(request) + getXgr(request));
		createDir(path); //如果不存在，则自动创建新目录
		return path;
	}
	
	//获取系统的实际根路径
	public String getDir(HttpServletRequest request) {
		return getPath(request.getSession().getServletContext()
				.getRealPath("/"));
	}
	
	protected static String getXgr(javax.servlet.http.HttpServletRequest req) {
		Principal prin = (Principal) req.getSession().getAttribute(CommonAttributes.Principal);
		String username =prin.getUsername();
		return username;
	}
	
	//验证用户,粮库
	public Message isValidUser(String username,/*String lkbm,*/String password){
		if (password==null  )
			return Message.error(SpringUtils.getMessage("depot.valid.erroruser.passnull"));
		
		DepotUser user=depotuserSrv.findByUsername(username);
		if (user==null)
			return Message.error(SpringUtils.getMessage("depot.valid.erroruser"));

		String pass = StringUtil.encodePassword(password,"MD5");
		if (!user.getPass().equals(pass))
			return Message.error(SpringUtils.getMessage("depot.valid.erroruser.passerr"));
		
/*		if (!user.getGraindepot().getLkbm().equalsIgnoreCase(lkbm))
			return Message.error(SpringUtils.getMessage("depot.valid.errormanagelkbm"));
*/		return Message.success("success");
	}
	
	/**
	 * 必须先调用isValidUser(String, String) 来判断是否合法用户
	 * @param username
	 * @return
	 */
	String getLkbm(String username){
		DepotUser user=depotuserSrv.findByUsername(username);
		return user.getGraindepot().getLkbm();
	}
	
	public Message validUserAndDepotAndBin(String username, /*String depot, */String bin,String password) {
		if (password==null  )
			return Message.error(SpringUtils.getMessage("depot.valid.erroruser.passnull"));
		
		DepotUser user=depotuserSrv.findByUsername(username);
		if (user==null)
			return Message.error(SpringUtils.getMessage("depot.valid.erroruser"));

		String pass = StringUtil.encodePassword(password,"MD5");
		if (!user.getPass().equals(pass))
			return Message.error(SpringUtils.getMessage("depot.valid.erroruser.passerr"));

		//验证粮库、粮仓
		if (/*!user.getGraindepot().getLkbm().equalsIgnoreCase(depot)||*/
				!manageGrainbin(user,bin))
			return Message.error(SpringUtils.getMessage("depot.valid.errormanagelcbm"));
		return Message.success("success");
	}
	
	//是否管理该粮仓
	private boolean manageGrainbin(DepotUser user,String bin){
		if (user.getManager()) return true;		//	如果是所长，直接返回 true
		for (Grainbin g: user.getGrainbins()){
			if (g.getLcbm().equalsIgnoreCase(bin))
				return true;
		}
		return false;
	}
	
	Message hasAuthourize(HttpSession session,String entity){
		
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username="";
		if (p!=null){
			username=p.getUsername();
		}else
			return Message.error("session is lost!");
		DepotUser user=depotuserSrv.findByUsername(username);
		if (entity.equals(username) || user.getManager())
			return Message.success("success");
		else
			return Message.error("you have not been authourized!");
	}
	
	Message hasAuthourize(HttpSession session){
		Principal p =(Principal) session.getAttribute(CommonAttributes.Principal);
		String username="";
		if (p!=null){
			username=p.getUsername();
		}else
			return Message.error("session is lost!");
		DepotUser user=depotuserSrv.findByUsername(username);
		if (user.getManager())
			return Message.success("success");
		else
			return Message.error("you have not been authourized!");
	}
	
	boolean isManager(String username){
		DepotUser user=depotuserSrv.findByUsername(username);
		if (user!=null && user.getManager())
			return true;
		else
			return false;
	}
}

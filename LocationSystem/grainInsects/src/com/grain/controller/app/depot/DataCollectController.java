package com.grain.controller.app.depot;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.grain.CommonAttributes;
import com.grain.Filter;
import com.grain.Filter.Operator;
import com.grain.FileInfo.FileType;
import com.grain.Message;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.Message.Type;
import com.grain.entity.DeviceRealdata;
import com.grain.entity.Grain;
import com.grain.entity.GrainPic;
import com.grain.entity.Grainbin;
import com.grain.entity.Graindepot;
import com.grain.entity.RealdataPic;
import com.grain.entity.page.UserMessage;
import com.grain.entity.user.DepotUser;
import com.grain.service.DeviceRealdataService;
import com.grain.util.SpringUtils;
import com.grain.service.RealdataPicService;

/**
 * @author szy
 *  数据传输方式为离散传输，一般不连续
 *	粮库数据采集，主要用来实时和离线采集粮库内的相关虫害信息，主要有两种采集方式：
 *<ol>
 *<li> 移动设备采集
 *<li> 固定设备采集
 **/

@Controller("depotDataCollectController")
@RequestMapping("/app/depot/datacollect")
public class DataCollectController extends BaseController {

	@Resource(name = "deviceRealdataServiceImpl")
	DeviceRealdataService realdataSrv;
	
	@Resource(name = "realdataPicServiceImpl")
	RealdataPicService realpic;
	
	@RequestMapping("/realdata")
	public String realdata(){
		return "depoter/realdata";
	}
	
	/*
	 * 获取粮库基础参数信息
	 * 无值则返回空
	 */
	@RequestMapping(value="/getlk")
	@ResponseBody
	public UserMessage<Graindepot> getLk(/*String lkbm,*/
			String username,String password){
		UserMessage<Graindepot> usermes = new UserMessage<Graindepot>();
		Message mes=isValidUser(username, /*lkbm,*/password);
		if (mes.getType()==Type.error){
			usermes.setType(Type.error);
			usermes.setContent(mes.getContent());
			return usermes;
		}
		usermes.setType(Type.success);
		usermes.setT(depotService.find(getLkbm(username)));	
		return  usermes;
	}

	/*
	 * 获取粮库用户参数信息
	 * 无值则返回空
	 */
	@RequestMapping(value="/getlkusers")
	@ResponseBody
	public UserMessage<Set<DepotUser>> getLkUsers(/*String lkbm,*/
			String username,String password){
		UserMessage<Set<DepotUser>> usermes = new UserMessage<Set<DepotUser>>();
		Message mes=isValidUser(username, /*lkbm,*/password);
		if (mes.getType()==Type.error){
			usermes.setType(Type.error);
			usermes.setContent(mes.getContent());
			return usermes;
		}

		usermes.setType(Type.success);
		if (!isManager(username)){		//如果不是所长，则只能找自己的相关信息
			Set<DepotUser> set = new HashSet<DepotUser>();
			set.add(depotuserSrv.findByUsername(username));
			usermes.setT(set);	
		}else
			usermes.setT(depotService.find(getLkbm(username)).getDepotusers());	
		return  usermes;
	}
	
	/*
	 * 获取某粮库的所有粮仓基础信息，分页查询
	 * 
	 */
	@RequestMapping(value="/getlkalllc")
	@ResponseBody
	public UserMessage<Page<Grainbin>> getlkalllc(/*String lkbm,*/
			String username,String password,
			Pageable pageable,Grainbin queryParm){
		UserMessage<Page<Grainbin>> usermes = new UserMessage<Page<Grainbin>>();
		Message mes=isValidUser(username, /*lkbm,*/password);
		if (mes.getType()==Type.error){
			usermes.setType(Type.error);
			usermes.setContent(mes.getContent());
			return usermes;
		}
		if(pageable.getRows()>10){
			usermes.setType(Type.error);
			usermes.setContent(SpringUtils.getMessage("depot.toomanyrows.everypage"));
			return usermes;
		}
		usermes.setType(Type.success);
		try{
			if (queryParm.getLcbm()==null || queryParm.getLcbm().trim().length()<1 ){ // not input the lcbm
				if (isManager(username)){	// is manager
					queryParm.setGraindepot(depotService.find(getLkbm(username)));
					//usermes.setT(binService.findPage(pageable,queryParm));	
				}else{  //不是所长，则找自己管理的粮仓
					if (depotuserSrv.findByUsername(username).getGrainbins()!=null &&
							!depotuserSrv.findByUsername(username).getGrainbins().isEmpty()){
						List<Filter> filters = new ArrayList<Filter>();
						Filter fl = new Filter();
						Set<String> s = new HashSet<String>();
						for (Grainbin bin : depotuserSrv.findByUsername(username).getGrainbins()){
							s.add(bin.getLcbm());
						}
						fl.setProperty("lcbm");
						fl.setOperator(Operator.in);
						fl.setValue(s);
						filters.add(fl);
						pageable.setFilters(filters);
					}
				}
			}
			usermes.setT(binService.findPage(pageable,queryParm));	
		}catch(Exception e){
			usermes.setType(Type.error);
			usermes.setContent(e.getMessage());
			e.printStackTrace();
		}
		return  usermes;
	}
		

	/*
	 * 获取对某粮库的某个粮仓的数据上传历史数据，分页查询
	 * 
	 */
	@RequestMapping(value="/getHisRealdata")
	@ResponseBody
	public UserMessage<Page<DeviceRealdata>> getHisRealdata(
			/*String lkbm,*/String lcbm,
			String username,String password,
			String start_collecttime,String end_collettime,
			Pageable pageable,DeviceRealdata queryParm){
		UserMessage<Page<DeviceRealdata>> usermes = new UserMessage<Page<DeviceRealdata>>();
		Message mes=validUserAndDepotAndBin(username, /*lkbm,*/ lcbm,password);
		
		if (mes.getType()==Type.error){
			usermes.setType(Type.error);
			usermes.setContent(mes.getContent());
			return usermes;
		}
		if(pageable.getRows()>10){
			usermes.setType(Type.error);
			usermes.setContent(SpringUtils.getMessage("depot.toomanyrows.everypage"));
			return usermes;
		}
		
		List<Filter> filters = new ArrayList<Filter>();
		if (start_collecttime!=null){
			Filter ft = new Filter();
			ft.setProperty("collecttime");
			ft.setValue(start_collecttime);
			ft.setOperator(Operator.ge);
			filters.add(ft);
		}
		if (end_collettime!=null){
			Filter ft = new Filter();
			ft.setProperty("collecttime");
			ft.setValue(end_collettime);
			ft.setOperator(Operator.le);
			filters.add(ft);
		}
		pageable.setFilters(filters);
		usermes.setType(Type.success);
		queryParm.setTGrainbin(binService.find(lcbm));
		try{
			usermes.setT(realdataSrv.findPage(pageable,queryParm));	
		}catch(Exception e){
			usermes.setType(Type.error);
			usermes.setContent(e.getMessage());
		}
		return  usermes;
	}
	
	/*
	 * 获取粮食参数信息，含图片信息
	 * 无值则返回空
	 */
	@RequestMapping(value="/getLastGrain")
	@ResponseBody
	public UserMessage<Grain> getLastGrain(/*String lkbm,*/String lcbm,
			String username,String password){
		UserMessage<Grain> usermes = new UserMessage<Grain>();
		Message mes=validUserAndDepotAndBin(username, /*lkbm,*/ lcbm,password);
		if (mes.getType()==Type.error){
			usermes.setType(Type.error);
			usermes.setContent(mes.getContent());
			return usermes;
		}
		usermes.setType(Type.success);
		
		usermes.setT(grainService.findLast(lcbm));
		return  usermes;
	}
	
	@RequestMapping(value="/deleteGrainPic")
	@ResponseBody
	public Message deleteGrainPic(Long id,/*String lkbm,*/String lcbm,
			String username,String password){
		Message mes=validUserAndDepotAndBin(username, /*lkbm,*/ lcbm,password);
		if (mes.getType()==Type.error){
			return mes;
		}
		try{
			grainPicService.delete(id);
			mes.setType(Type.success);
			mes.setContent("Success");
		}catch(Exception e){
			mes.setType(Type.error);
			mes.setContent(e.getMessage());
		}
		return  mes;
	}
	
	
	@RequestMapping(value="/updateGrain")
	@ResponseBody
	public Message updateGrain(/*String lkbm,*/String lcbm,
			String username,String password,
			Grain data){
		Message mes=validUserAndDepotAndBin(username, /*lkbm,*/ lcbm,password);
		if (mes.getType()==Type.error){
			return mes;
		}
		try{
			grainService.update(data,"grainbin");
			mes.setType(Type.success);
			mes.setId(data.getId());
			mes.setContent("Success");
		}catch(Exception e){
			mes.setType(Type.error);
			mes.setContent(e.getMessage());
		}
		return  mes;
	}
	
	/**
	 * @param username	用户名
	 * @param pass	密码
	 * @param lkbm	粮库编码
	 * @param lcbm	粮仓编码
	 * @param data	实时数据对象
	 * @return 
	 */
	@RequestMapping(value="/uploadData")
	@ResponseBody
	public Message uploadData(
			String username,String password,
			/*String lkbm,*/
			String lcbm,
			String deviceno,
			DeviceRealdata data
			){
		Message mes=validUserAndDepotAndBin(username, /*lkbm,*/ lcbm,password);
		if (mes.getType()==Type.error)
			return mes;
		try{
			data.setModifier(username);
			data.setModifytime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
			realdataSrv.saveReal(data,lcbm,deviceno);
			Long id = data.getId();
			//
			mes.setType(Type.success);
			mes.setId(id);
		} catch (Exception e){
			mes.setType(Type.error);
			mes.setContent(e.getMessage());
		}
		return mes;
	}

	@RequestMapping("/uploadPic")
	@ResponseBody
	public Message uploadPic(HttpServletRequest req,
			String username,String password,
			/*String lkbm,*/
			String lcbm,
			Long pid,
			MultipartFile file
			){
		Message mes=validUserAndDepotAndBin(username, /*lkbm,*/ lcbm,password);
		if (mes.getType()==Type.error)
			return mes;		
		//验证实时数据ID
		if(!realdataSrv.exists(pid)){
			mes.setType(Type.error);
			mes.setContent(SpringUtils.getMessage("depot.valid.errorid"));
			return mes;
		}
		
		//验证文件
		if (!fileService.isValid(FileType.image, file)){
			mes.setType(Type.error);
			mes.setContent(SpringUtils.getMessage("depot.valid.errorfile"));
			return mes;
		}
		String url = fileService.upload(FileType.image, file);
		RealdataPic pic = new RealdataPic();
		pic.setPic(url);
		pic.setDeviceRealdata(realdataSrv.find(pid));
		try{
			realpic.save(pic);
			mes.setUrl(url);
			mes.setType(Type.success);
		}
		catch (Exception e){
			e.printStackTrace();
			mes.setType(Type.error);
			mes.setContent(e.getMessage());
		}
		
		return mes;
	}
	
	/*
	 * 上传实时信息与图片
	 */
	@RequestMapping("/uploadDataAndpic")
	@ResponseBody
	public Message uploadDataAndPic(HttpServletRequest req,
			String username, 
			String password, 
			/*String lkbm, */
			String lcbm,
			String deviceno,
			DeviceRealdata data, 
			MultipartFile file) {
		Message mes = validUserAndDepotAndBin(username, /*lkbm,*/ lcbm,password);
		if (mes.getType() == Type.error)
			return mes;

		// 验证文件
		if (!fileService.isValid(FileType.image, file)) {
			mes.setType(Type.error);
			mes.setContent(SpringUtils.getMessage("depot.valid.errorfile"));
			return mes;
		}
		String url = fileService.uploadLocal(FileType.image,file);
		RealdataPic pic = new RealdataPic();
		pic.setPic(url);
		pic.setDeviceRealdata(data);
		Set<RealdataPic> picSets = new HashSet <RealdataPic>();
		picSets.add(pic);
		try{
			data.setRealdataPics(picSets);
			//保存数据
			data.setModifier(username);
			data.setModifytime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
			realdataSrv.saveReal(data,lcbm,deviceno);
			Long id = data.getId();
			mes.setId(id);
			mes.setUrl(url);
			mes.setType(Type.success);
		}catch (Exception e){
			mes.setType(Type.error);
			mes.setContent(e.getMessage());
			// 删除上传文件
			String path = getDir(req) + CommonAttributes.RealDataPicPath;
			int i= url.lastIndexOf("/");
			String fileName = url.substring(i);
			File delFile = new File(path + fileName);
			delFile.delete();
		}
		return mes;
	}
	
	/*
	 * 上传粮食信息
	 */
	@RequestMapping(value="/uploadGrain")
	@ResponseBody
	public Message uploadGrain(HttpServletRequest req,
			String username,String password,
			/*String lkbm,*/
			String lcbm,
			Grain data
			){
		Message mes = validUserAndDepotAndBin(username, /*lkbm,*/ lcbm,password);
		if (mes.getType()==Type.error)
			return mes;
		
		try{
			data.setGrainbin(binService.find(lcbm));
			data.setModifier(username);
			data.setModifytime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));

			grainService.save(data);
			Long id = data.getId();
			mes.setType(Type.success);
			mes.setId(id);	
		}catch(Exception e){
			mes.setType(Type.error);
			mes.setContent(e.getMessage());
			e.printStackTrace();
		}
		return mes;
	}

	/*
	 * 上传粮食图片信息
	 */
	@RequestMapping(value="/uploadGrainPic")
	@ResponseBody
	public Message uploadGrainPic(HttpServletRequest req,
			String username,String password,
			/*String lkbm,*/
			String lcbm,
			Long pid,
			MultipartFile file
		){
		Message mes = validUserAndDepotAndBin(username,/* lkbm,*/ lcbm,password);
		if (mes.getType()==Type.error)
			return mes;
		
		//验证实时数据ID
		if(!grainService.exists(pid)){
			mes.setType(Type.error);
			mes.setContent(SpringUtils.getMessage("depot.valid.notexists.grainpid"));
			return mes;
		}
		
		//验证文件，只验证空文件，不验证文件类型
		if (!fileService.isValid(FileType.image, file)){
			mes.setType(Type.error);
			mes.setContent(SpringUtils.getMessage("depot.valid.error.grainfile"));
			return mes;
		}
		String url = fileService.uploadLocal(FileType.image, file);
		
		GrainPic pic = new GrainPic();
		pic.setPic(url);
		pic.setTGrain(grainService.find(pid));
		try{
			grainPicService.save(pic);
			mes.setUrl(url);
			mes.setType(Type.success);
		}
		catch (Exception e){
			e.printStackTrace();
			mes.setType(Type.error);
			mes.setContent(e.getMessage());
		}

		return mes;	
	}
	
	/*
	 * 上传实时信息与图片
	 */
	@RequestMapping("/uploadGrainAndPic")
	@ResponseBody
	public Message uploadGrainAndPic(HttpServletRequest req,
			String username, 
			String password, 
			/*String lkbm, */
			String lcbm,
			Grain data, 
			MultipartFile file) {
		Message mes = validUserAndDepotAndBin(username, /*lkbm, */lcbm,password);
		if (mes.getType() == Type.error)
			return mes;

		// 验证文件
		if (!fileService.isValid(FileType.image, file)) {
			mes.setType(Type.error);
			mes.setContent(SpringUtils.getMessage("depot.valid.errorfile"));
			return mes;
		}
		String url = fileService.uploadLocal(FileType.image,file);
		GrainPic pic = new GrainPic();
		pic.setPic(url);
		pic.setTGrain(data);   //主控端维护关系
		Set<GrainPic> picSets = new HashSet <GrainPic>();
		picSets.add(pic);
		try{
			data.setGrainPics(picSets);
			data.setGrainbin(binService.find(lcbm));
			//保存数据
			data.setModifier(username);
			data.setModifytime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));

			grainService.save(data);
			Long id = data.getId();
			mes.setId(id);
			mes.setUrl(url);
			mes.setType(Type.success);
		}catch (Exception e){
			mes.setType(Type.error);
			mes.setContent(e.getMessage());
			// 删除上传文件
			String path = getDir(req) + CommonAttributes.RealDataPicPath;
			int i= url.lastIndexOf("/");
			String fileName = url.substring(i);
			File delFile = new File(path + fileName);
			delFile.delete();
		}
		return mes;
	}

}

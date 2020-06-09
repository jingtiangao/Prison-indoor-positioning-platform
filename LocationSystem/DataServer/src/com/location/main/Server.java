package com.location.main;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.location.dao.Impl.BaseDAO;
import com.location.dao.Impl.DeviceDaoImpl;
import com.location.entity.Device;
import com.location.entity.Device_info;
import com.location.entity.Device_info_history;
import com.location.json.JSON;

public class Server {

	public static final String user_id_list="http://tz.seekcy.com:8085/WebLocate/LocateResults?user_ids=1918E0000020,1918E0000021,1918E0000022,1918E0010027&time_period=5000";
	
	public static void main(String[] args) {
		
		System.out.println("开始接受数据。。。");
		
		Runnable runnable=new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					String json = JSON.getReturnData(user_id_list);
					System.out.println(json);
					
//					String json = "[{\"accuracy\":4.0,\"build_id\":\"26000\",\"error_code\":0,\"floor_id\":4,\"info\":\"\",\"nearest_tag_id\":\"0\",\"timestamp_millisecond\":1459137333190.0,\"user_id\":\"84EB185C9F5A\",\"x_millimeter\":44937,\"y_millimeter\":24413,\"compass\":300,\"alarm\":false},"
//							+ "{\"accuracy\":4.0,\"build_id\":\"26000\",\"error_code\":0,\"floor_id\":4,\"info\":\"\",\"nearest_tag_id\":\"0\",\"timestamp_millisecond\":1459137333190.0,\"user_id\":\"84EB185C9F5B\",\"x_millimeter\":44937,\"y_millimeter\":24413,\"compass\":300,\"alarm\":false}]";
					
					//创建一个Gson对象
					Gson gson = new Gson();
				
					//创建一个JsonParser
					JsonParser parser = new JsonParser();
					//通过JsonParser对象可以把json格式的字符串解析成一个JsonElement对象
					JsonElement el = parser.parse(json);
					//
					JsonObject jObject = el.getAsJsonObject();
					
//					String jString = jObject.get("data").toString();
//					System.out.println(jString);
					JsonElement jElement = jObject.get("data");
					//把JsonElement对象转换成JsonArray
					JsonArray jsonArray = null;
					
					if(jElement.isJsonArray()){
						System.out.println("yes");
						jsonArray = jElement.getAsJsonArray();
					}

					//创建一个BaseDao对象，往数据库写入POJO
					BaseDAO<Device_info> baseDAO = new BaseDAO<>();
					baseDAO.dropAll();
					BaseDAO<Device_info_history> baseDAO2=new BaseDAO<>();
					DeviceDaoImpl deviceDaoImpl=new DeviceDaoImpl();
					//遍历JsonArray对象
					Device_info device_info = null;
					Device_info_history device_info_history=null;
					System.out.println(jsonArray.toString());
					Iterator it = jsonArray.iterator();
					while(it.hasNext()){
						JsonElement jsonElement = (JsonElement) it.next();
						String str = jsonElement.getAsString();
						System.out.println(str);
						
						
						JsonElement e = parser.parse(str);
						//JsonElement转换为JavaBean对象
						device_info = gson.fromJson(e, Device_info.class);
						device_info_history=gson.fromJson(e, Device_info_history.class);	
						String device_mac=device_info.getUser_id();
						Device device=null;
						device=deviceDaoImpl.findByMac(device_mac);
						if (device==null) {
							break;
						}
						int device_id;
						device_id=device.getDevice_id();
						System.out.println(device_id);
						device_info.setDevice_id(device_id);
						device_info_history.setDevice_id(device_id);
//						System.out.println(user_info.getUser_id()+' '+user_info.getTimestamp_millisecond());
						baseDAO.create(device_info);
						baseDAO2.create(device_info_history);
						System.out.println(device_info_history.getDevice_id()+" === "+device_info_history.getTimestamp_millisecond());
						System.out.println(device_info.getDevice_id()+" === "+device_info.getTimestamp_millisecond());
					}
					
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		};
		ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
		// 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
        service.scheduleAtFixedRate(runnable, 1, 3, TimeUnit.SECONDS);
		
	}
}
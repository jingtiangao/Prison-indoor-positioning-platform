package com.grain;

/**
 * 公共参数
 * 
 */
public final class CommonAttributes {

	public static final String Principal = "principal";		//session中的登录用户信息
	public static final String BaiduMapApiAccount = "baidu_map_api_account";
	/** 日期格式配比 */
	public static final String[] DATE_PATTERNS = new String[] { "yyyy", "yyyy-MM", "yyyyMM", "yyyy/MM", "yyyy-MM-dd", "yyyyMMdd", "yyyy/MM/dd", "yyyy-MM-dd HH:mm:ss", "yyyyMMddHHmmss", "yyyy/MM/dd HH:mm:ss" };

	/** shopxx.xml文件路径 */
	public static final String GRAIN_XML_PATH = "/grain.xml";

	/** shopxx.properties文件路径 */
	public static final String GRAIN_PROPERTIES_PATH = "/grain.properties";

	public static final  String SessionInfo =  "sessionInfo";
	//1 直属库实时数据（含app）釆虫图片路径
	public static final String RealDataPicPath = "realdata";
	
	//2 农户储粮釆虫图片 路径
	public static final String FarmerStoredGrainInsectsPicPath = "farmer";
	
	//3 加工厂釆虫图片 path
	public static final String Factory_picPath = "factory";
	
	//4 野外釆虫图片 path
	public static final String Field_picPath = "field";

	//5 储备库釆虫图片 path
	public static final String depot_picPath = "depot";
	
	//6 分类特征图片 path
	public static final String catalog_picPath = "catalog";
	
	//7 特征图片 path
	public static final String digital_picPath = "digital";
	
	//8 防治工艺附件 path
	public static final String preventProcessDetach_Path = "preventProcessDetach";

	//9 诊断申请图片 path
	public static final String remote_diagnosis_path = "remotediagnosis";

	//其他
	public static final String else_path = "upload";
	
	/**
	 * 不可实例化
	 */
	private CommonAttributes() {
	}
}
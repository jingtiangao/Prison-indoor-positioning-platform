package com.grain.controller.admin;


import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grain.service.FileService;
import com.grain.service.TCatalogIndexService;
import com.grain.service.TagService;
import com.grain.util.SpringUtils;
import com.grain.Json;
import com.grain.Message;
import com.grain.Page;
import com.grain.Pageable;
import com.grain.FileInfo.FileType;
import com.grain.entity.BintypePic;
import com.grain.entity.TBinPreprotect;
import com.grain.entity.TBinProtect;
import com.grain.entity.TCatalogIndex;
import com.grain.entity.TCatalogPic;
import com.grain.entity.TDigitalFeature;
import com.grain.entity.TPreventprocess;
import com.grain.entity.Tag;
import com.grain.entity.Tag.Type;
import com.grain.entity.page.CatalogIndex;
import com.sun.jmx.snmp.Timestamp;



public class TProcTypeController extends BaseController{

}

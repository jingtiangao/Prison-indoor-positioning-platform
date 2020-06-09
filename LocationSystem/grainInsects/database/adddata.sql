
INSERT INTO `xx_role` VALUES (1, NOW(),NOW(),null,1,'admin');
INSERT INTO `t_admin` (username,roleid,name,password) VALUES ('aa',1,'szy','c4ca4238a0b923820dcc509a6f75849b');

INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description)  VALUES (-1, NULL, '获取菜单', 'admin/menu/display', NULL, 0, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description)  VALUES (1, NULL, '文件操作', 'admin/file', NULL, 0, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (900, NULL, '系统管理', NULL, 'computer_key', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (901, 900, '角色管理', 'admin/role/manager', 'icon_group', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (902, 901, '角色管理-获取列表', 'admin/role/dataGrid', NULL, 0, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (903, 901, '角色管理-增加角色', 'admin/role/add', NULL, 0, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (904, 901, '角色管理-修改角色', 'admin/role/edit', NULL, 0, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (905, 901, '角色管理-删除角色', 'admin/role/delete', NULL, 0, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (906, 901, '角色管理-授权-进入授权页面', 'admin/role/grantPage', NULL, 0, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (907, 901, '角色管理-授权-权限列表', 'admin/role/tree', NULL, 0, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (908, 901, '角色管理-授权-授权', 'admin/role/grant', NULL, 0, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (909, 900, '用户管理', 'admin/listentrance', 'icon_user', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (910, 909, '用户管理-ajax获取用户列表', 'admin/list', NULL, 0, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (911, 909, '用户管理-删除用户', 'admin/remove', NULL, 0, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (912, 909, '用户管理-修改用户', 'admin/edit', NULL, 0, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (913, 909, '用户管理-进入新增用户界面', 'admin/addEntrance', NULL, 0, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (914, 900, '注销用户', 'admin/logout', NULL, 0, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (915, 909, '角色管理-授权-alltree', 'admin/role/allTree', NULL, 0, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (916, 909, '用户管理-检查重名', 'admin/checkName', NULL, 0, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (917, 909, '用户管理-add', 'admin/add', NULL, 0, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (920, 900, '导航管理', 'admin/navigation/list', 'thumb_up', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (921, 920, '导航管理', 'admin/navigation', null, 0, NULL);


-- 基础资料
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (10000, null, '基础资料', null, 'text_padding_left', 1, NULL);
-- 储粮区
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (10100, 10000, '储粮区', 'admin/graindirection/list', 'world_edit', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (10101, 10100, '储粮区操作', 'admin/graindirection', '', 0, NULL);
-- 地区
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (10200, 10000, '地区', 'admin/area/list', 'map', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (10201, 10200, '地区操作', 'admin/area', '', 0, NULL);
-- 粮库
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (10300, 10000, '粮库信息', 'admin/depot/depot/entrance', 'house', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (10301, 10300, '粮库信息操作', 'admin/depot/depot', '', 0, NULL);
-- 库管员
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (10400, 10000, '库管员', 'admin/depot/user/entrance', 'icon_group', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (10401, 10400, '库管员操作', 'admin/depot/user', NULL, 0, NULL);

-- 专家
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (10500, 10000, '专家用户', 'admin/expert/entrance', 'icon_group', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (10501, 10500, '专家用户-操作', 'admin/expert', '', 0, NULL);

-- 虫调
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (10600, 10000, '虫调用户', 'admin/investor/entrance', 'icon_group', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (10601, 10600, '虫调用户-操作', 'admin/investor', '', 0, NULL);

-- 友情链接
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (10700, 10000, '友情链接', 'admin/friend_link/list', 'link', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (10701, 10700, '友情链接-操作', 'admin/friend_link', '', 0, NULL);

-- plugin
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (10800, 10000, '插件管理', null, 'plugin', 1, NULL);
-- storage_plugin
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (10810, 10800, '存储插件', 'admin/storage_plugin/list', 'plugin_edit', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (10811, 10810, '存储插件-操作', 'admin/storage_plugin', '', 0, NULL);

-- 缓存
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (10900, 10000, '缓存管理', 'admin/cache/clear', 'color_wheel', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (10901, 10900, '缓存管理-操作', 'admin/cache', '', 0, NULL);

-- 静态化
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (11000, 10000, '静态化管理', 'admin/static/build', 'color_wheel', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (11001, 11000, '静态化管理-操作', 'admin/static', '', 0, NULL);

-- setting
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (11100, 10000, '系统设置', 'admin/setting/edit', 'computer', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (11101, 11100, '系统设置-操作', 'admin/setting', '', 0, NULL);

-- 广告
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (40000, null, '广告管理', '', 'bell', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (40100, 40000, '广告位', 'admin/ad_position/list', 'bell_link', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (40101, 40100, '广告位-操作', 'admin/ad_position', '', 0, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (40200, 40000, '广告', 'admin/ad/list', 'bell_add', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (40201, 40200, '广告-操作', 'admin/ad', '', 0, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (40300, 40000, '导航设置', 'admin/navigation/list', 'connect', 1, NULL);

-- 文章分类
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (50000, null, '文章管理', '', 'text_dropcaps', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (50100, 50000, '类别管理', 'admin/article_category/list', 'text_list_numbers', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (50101, 50100, '类别管理-操作', 'admin/article_category', '', 0, NULL);

-- tag
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (50200, 50000, '标签管理', 'admin/tag/list', 'wand', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (50201, 50200, '类别管理-操作', 'admin/tag', '', 0, NULL);

-- article
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (50300, 50000, '文章管理', 'admin/article/list', 'text_list_numbers', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (50301, 50300, '文章管理-操作', 'admin/article', '', 0, NULL);

-- 知识库
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (60000, null, '知识库', '', 'book_open', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (60100, 60000, '害虫类别', 'admin/catalogIndex/list', 'palette', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (60101, 60100, '害虫类别-操作', 'admin/catalogIndex', '', 0, NULL);

-- 工艺类型
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (60200, 60000, '防治工艺类型', 'admin/proctype/list', 'brick', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (60201, 60200, '防治工艺类型-操作', 'admin/proctype', '', 0, NULL);
-- 防治工艺
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (60300, 60000, '防治工艺', 'admin/process/list', 'paintbrush', 1, NULL);
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (60301, 60300, '防治工艺-操作', 'admin/process', '', 0, NULL);

-- 检索表
INSERT INTO `xx_op_register` (id,pid,opname,url,icon,hasdisplay,description) VALUES (60400, 60000, '检索表', 'admin/insectsIndex/list', 'book_open', 1, NULL);

INSERT INTO `xx_role_authority`(roles,ops) VALUES (1, -1);
INSERT INTO `xx_role_authority`(roles,ops) VALUES (1, 1);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 900);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 901);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 902);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 903);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 904);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 905);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 906);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 907);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 908);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 909);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 910);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 911);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 912);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 913);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 914);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 915);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 916);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 917);

INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 920);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 921);

-- 基础资料
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 10000);
-- 储粮区
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 10100);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 10101);
-- area
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 10200);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 10201);
-- 粮库
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 10300);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 10301);
-- 库管员
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 10400);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 10401);
-- 专家
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 10500);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 10501);
-- 虫调
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 10600);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 10601);
-- friend link
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 10700);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 10701);
-- plugin
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 10800);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 10810);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 10811);
-- 缓存
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 10900);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 10901);
-- 静态化
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 11000);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 11001);
-- settting
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 11100);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 11101);


-- 广告
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 40000);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 40100);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 40101);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 40200);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 40201);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 40300);



-- 文章分类
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 50000);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 50100);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 50101);
-- tag
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 50200);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 50201);
-- article
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 50300);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 50301);

-- 知识库
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 60000);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 60100);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 60101);
-- 工艺类型
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 60200);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 60201);
-- 防治工艺
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 60300);
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 60301);

-- 检索表
INSERT INTO `xx_role_authority` (roles,ops) VALUES (1, 60400);

insert into t_graindirection (id,fullname) values(1,'高寒区');
insert into t_graindirection (id,fullname) values(2,'低温区');
insert into t_graindirection (id,fullname) values(3,'干燥区');

insert into xx_area (id, create_date, modify_date,  full_name, name, tree_path, parent,graindirect) values(1, NOW(),NOW(),  '北京市', '北京市', ',', NULL,1);
insert into t_graindepot (lkbm,lkmc,lkdz,direct) VALUES ('10000','北京通州库','北京市通州区果园',1);
insert into t_grainbin (lcbm,lkbm,typebin) VALUES ('1000001','10000','圆筒仓');
insert t_depotuser (username,lkbm,lcbm,hasaudit )values ('aa','10000','1000001',1);
insert xx_plugin_config(id,create_date,modify_date,orders,is_enabled,plugin_id) values(1,NOW(),NOW(),100,1,'filePlugin');


-- add a view
create or REPLACE view v_user as
select a.username ,'admin' role from t_admin a
union
select b.username,'factoryuser' role  from t_factoryuser b
union
select c.username,'farmeruser' role from t_farmeruser c
UNION
select d.username,'expert' role from t_expert d
union
select e.username,'depotuser' role from t_depotuser e
union
select f.username,'insectsinvestuser' role from t_insectsinvestuser f;

-- yl add
ALTER TABLE t_expert
ADD roleid bigint(20) DEFAULT NULL;

ALTER TABLE t_depotuser
ADD roleid bigint(20) DEFAULT NULL;

ALTER TABLE t_factoryuser
ADD roleid bigint(20) DEFAULT NULL;

ALTER TABLE t_farmeruser
ADD roleid bigint(20) DEFAULT NULL;

ALTER TABLE t_insectsinvestuser
ADD roleid bigint(20) DEFAULT NULL;


ALTER table t_expert add CONSTRAINT FK_expert_role FOREIGN KEY(roleid) REFERENCES xx_role(id);

ALTER table t_depotuser add CONSTRAINT FK_depotuser_role FOREIGN KEY(roleid) REFERENCES xx_role(id);

ALTER table t_factoryuser add CONSTRAINT FK_factory_role FOREIGN KEY(roleid) REFERENCES xx_role(id);

ALTER table t_farmeruser add CONSTRAINT FK_farmer_role FOREIGN KEY(roleid) REFERENCES xx_role(id);

ALTER table t_insectsinvestuser add CONSTRAINT FK_insectsinvestuser_role FOREIGN KEY(roleid) REFERENCES xx_role(id);

INSERT INTO `xx_role` (id,create_date,modify_date,description,is_system,name) VALUES ('4', '2016-07-31 21:23:21', '2016-07-31 21:23:24', '虫调人员', '1', 'insectsinvestuser');
INSERT INTO `xx_role` (id,create_date,modify_date,description,is_system,name) VALUES ('5', '2016-07-30 23:20:31', '2016-07-30 23:20:34', '粮库', '1', 'depotuser');
INSERT INTO `xx_role` (id,create_date,modify_date,description,is_system,name) VALUES ('6', '2016-07-31 21:24:14', '2016-07-31 21:24:16', '专家', '1', 'expert');
INSERT INTO `xx_role` (id,create_date,modify_date,description,is_system,name) VALUES ('7', '2016-07-31 21:25:32', '2016-07-31 21:25:36', '农户', '1', 'farmeruser');
INSERT INTO `xx_role` (id,create_date,modify_date,description,is_system,name) VALUES ('8', '2016-08-03 14:45:03', '2016-08-03 14:45:06', '加工厂', '1', 'factoryuser');


INSERT INTO `t_expert` (username,password,name,title,company,mobile,point,contact,Specialty,hasaudit,modifer,modifydate,roleid) VALUES ('expert', 'c4ca4238a0b923820dcc509a6f75849b', 'eee', null, null, null, null, null, null, '1', null, null, '6');

INSERT INTO `t_factoryuser` (username,sm_factory,pass,realname,title,company,moblie,manager,hasaudit,point,modifer,modifydate,roleid) VALUES ('factory', '12000', 'c4ca4238a0b923820dcc509a6f75849b', 'factory', '', '', '', null, '0', '0', '', '2016-07-31 17:49:00', '8');

INSERT INTO `t_farmeruser` (username,sm_farmer,realname,moblie,direct,address,longitude,latitude,altitude,postcode,modifer,modifydate,password,roleid) VALUES ('farmer', '10002', 'farmer', null, '30', null, null, null, null, null, null, '2016-07-31 17:49:18', 'c4ca4238a0b923820dcc509a6f75849b', '7');

INSERT INTO `t_insectsinvestuser` (username,password,realname,title,company,moblie,point,hasaudit,modifer,modifydate,roleid) VALUES ('insect', 'c4ca4238a0b923820dcc509a6f75849b', 'investor', null, null, null, '0', '', null, '2016-07-31 17:49:53', '4');



INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('100000', null, '虫调', null, 'icon_group', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('110000', null, '虫调信息采集', null, 'world_edit', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('110100', '110000', '农户', 'investor/farmer/list', 'icon_group', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('110200', '110000', '加工厂', 'investor/factory/list', 'box', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('110300', '110000', '直属库', 'investor/depot/list', 'bricks', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('110400', '110000', '野外', 'investor/field/list', 'rainbow', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('110500', '110000', '昆虫采集分布登记', 'investor/register/collectDistribution/list', 'tab_edit', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('110600', '110000', '我的足迹', 'investor/common/myfootprint', 'map', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('110700', '110000', '昆虫鉴定登记', 'investor/register/insectsIdentify/list', 'tab_edit', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('110800', '110000', '螨鉴定登记', 'investor/register/miteIdentify/list', 'tab_edit', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('110900', '110000', '我的虫种分布', 'investor/common/myInsectsDistributes', 'map', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('120000', null, '个人中心', null, 'icon_group', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('120100', '120000', '消息中心', '', 'bell', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('120200', '120000', '修改个人信息', 'investor/common/user/editInvestorEntrance', 'cog_edit', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('120300', '120000', '注销', null, 'cog_delete', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('130000', null, '其他', null, null, '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('200000', null, '粮库', '', '', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('210000', null, '粮库信息', null, 'brick', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('210100', '210000', '粮库信息', 'depoter/depot/editDepotEntrance', 'database', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('210200', '210000', '粮仓信息', 'depoter/depot/listbins', 'box', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('220000', null, '数据上报', null, 'database_table', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('220100', '220000', '害虫调查总表', 'depoter/depotinvest/depot_invest_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('220110', '220000', '害虫分舱调查表', 'depoter/depotinvest/bin_invest_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('220200', '220000', '磷化氢抗药总表', 'depoter/depotantiphosphine/depot_antiphosphine_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('220210', '220000', '磷化氢抗药分仓表', 'depoter/depotantiphosphine/bin_antiphosphine_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('220300', '220000', '氮气气调总表', 'depoter/depotca/depot_ca_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('220310', '220000', '氮气气调分仓表', 'depoter/depotca/bin_ca_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('220400', '220000', '磷化氢熏蒸总表', 'depoter/depotphosphinefumigation/depot_phosphinefumigation_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('220410', '220000', '磷化氢熏分仓表', 'depoter/depotphosphinefumigation/bin_phosphinefumigation_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('220500', '220000', '防虫线杀虫总表', 'depoter/depotflyline/depot_flyline_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('220510', '220000', '防虫线杀虫分仓表', 'depoter/depotflyline/bin_flyline_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('220600', '220000', '防护剂总表', 'depoter/depotprotected/depot_protected_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('220610', '220000', '防护剂总表分仓表', 'depoter/depotprotected/bin_protected_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('220700', '220000', '惰性气体溶液总表', 'depoter/depotinertaerosol/depot_inertaerosol_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('220710', '220000', '惰性气体溶液分仓表', 'depoter/tbininertaerosol/tbin_inertaerosol_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('220800', '220000', '空仓杀虫总表', 'depoter/depotcleankill/depot_cleankill_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('220810', '220000', '空仓杀分仓表', 'depoter/depotcleankill/bin_cleankill_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('230000', null, '实时数据', null, 'table_lightning', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('230100', '230000', '数据列表', 'depoter/realdata/entrance', 'text_align_center', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('230200', '230000', '害虫数量、温湿度与时间', 'depoter/realdatacollect/insectsTemperatureHunmidity', 'chart_curve', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('230300', '230000', '害虫分布', 'depoter/realdatacollect/insectsLoc', 'map', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('240000', null, '个人中心', null, 'icon_group', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('240100', '240000', '库管员列表', 'depoter/common/list\r\n', 'icon_group', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('240200', '240000', '修改个人信息', 'depoter/common/editSelfEntrance', 'icon_group_edit', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('250000', null, '其他', null, null, '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('300000', null, '专家', null, '', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('310000', null, '虫调工作', null, 'text_signature', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('310100', '310000', '足迹分布', null, 'map', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('310200', '310000', '昆虫分布', null, 'map', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('320000', null, '防治工艺效果', null, 'color_wheel', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('330000', null, '监测预报', null, 'bell', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('330100', '330000', '示范库分布', null, 'bricks', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('330200', '330000', '数据监控', null, 'contrast', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('330300', '330000', '专家意见', null, 'comments', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('340000', null, '数据审核', null, 'book_edit', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('340100', '340000', '虫螨基础数据审核', null, 'pencil', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('340200', '340000', '检索表审核', null, 'pencil', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('350000', null, '处理咨询', null, 'images', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('400000', null, '农户', null, '', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('410000', null, '虫调录入', 'farmer/enter/list', 'cup_edit', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('420000', null, '虫害咨询', 'farmer/consult/list', 'comments', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('500000', null, '加工厂', null, '', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('510000', null, '虫调录入', 'factory/enter/list', 'cup_edit', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('520000', null, '虫调咨询', 'factory/consult/list', 'comments', '1', null);


INSERT INTO `xx_role_authority` (roles,ops) VALUES ('4', '-1');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '-1');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('6', '-1');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('7', '-1');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('4', '1');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '1');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('6', '1');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('7', '1');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('8', '-1');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('8', '1');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('4', '110000');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('4', '110100');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('4', '110200');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('4', '110300');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('4', '110400');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('4', '110500');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('4', '110600');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('4', '110700');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('4', '110800');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('4', '110900');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('4', '120000');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('4', '120100');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('4', '120200');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('4', '120300');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('4', '130000');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '210000');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '210100');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '210200');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '220000');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '220100');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '220110');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '220200');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '220210');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '220300');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '220310');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '220400');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '220410');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '220500');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '220510');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '220600');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '220610');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '220700');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '220710');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '220800');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '220810');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '230000');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '230100');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '230200');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '230300');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '240000');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '240100');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '240200');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '250000');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('6', '310000');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('6', '310100');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('6', '310200');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('6', '320000');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('6', '330000');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('6', '330100');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('6', '330200');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('6', '330300');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('6', '340000');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('6', '340100');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('6', '340200');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('6', '350000');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('7', '410000');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('7', '420000');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('8', '510000');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('8', '520000');


DELETE FROM t_admin WHERE username = 'bb';

UPDATE t_admin SET username = 'a' WHERE username = 'aa' ;


update `xx_op_register` set url='expert/monitor/depotLoc' where xx_op_register.id='330100';

update `xx_op_register` set url='expert/monitor/insectsTemperatureHunmidity' where xx_op_register.id='330200';
update `xx_op_register` set url='expert/audit/catalogIndex/list' where xx_op_register.id='340100';
update `xx_op_register` set url='expert/audit/insectsIndex/list' where xx_op_register.id='340200';
update `xx_op_register` set url='expert/common/footMark' where xx_op_register.id='310100';
update `xx_op_register` set url='expert/common/insectsDistribution' where xx_op_register.id='310200';

-- 添加公司表
CREATE TABLE `t_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `coding` varchar(10) NOT NULL,
  `company` varchar(100) NOT NULL,
  `modifer` varchar(30) DEFAULT NULL,
  `modifydate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('10210', '10000', '公司', 'admin/company/list', 'building', '1', null);
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('1', '10210');

-- yl add
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('221000', null, '防治工艺上报', 'depoter/inforeport/list', 'database_table', '1', null);
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '221000');

INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('11200', '10000', '图标管理', 'admin/catalogIcon/list', 'images', '1', null);
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('1', '11200');

INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('231000', null, '直属库咨询', null, 'image', '1', null);
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '231000');


update `xx_op_register` set url='expert/opinion/list' where xx_op_register.id='330300';
update `xx_op_register` set url='farmer/enter/list' where xx_op_register.id='410000';
update `xx_op_register` set url='farmer/consult/list' where xx_op_register.id='420000';
update `xx_op_register` set url='factory/enter/list' where xx_op_register.id='510000';
update `xx_op_register` set url='factory/consult/list' where xx_op_register.id='520000';

-- yl
-- add a view
create or REPLACE view v_user as
select a.username ,'admin' role from t_admin a
union
select b.username,'factoryuser' role  from t_factoryuser b
union
select c.username,'farmeruser' role from t_farmeruser c
UNION
select d.username,'expert' role from t_expert d 
union
select e.username,'depotmanager' role from t_depotuser e where e.manager = true
union
select e.username,'depotuser' role from t_depotuser e where e.manager = false
union
select f.username,'insectsinvestuser' role from t_insectsinvestuser f;


UPDATE xx_role SET description = '粮库所长' WHERE description = '粮库' ;
UPDATE xx_role SET name = 'depotmanager' WHERE name = 'depotuser';

INSERT INTO `xx_role` (id,create_date,modify_date,description,is_system,name) VALUES ('9', '2016-08-15 21:45:03', '2016-08-15 21:45:03', '库管员', '1', 'depotuser');


DELETE FROM xx_role_authority WHERE ops = '240100';

UPDATE xx_op_register SET pid = '210000' WHERE id = '240100';
UPDATE xx_op_register SET id = '210300' WHERE id = '240100';

INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '210300');



INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('220001', '220000', '害虫信息', null, 'database_table', '1', null);
INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('220002', '220000', '防治技术', null, 'database_table', '1', null);

INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '220001');
INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '220002');

UPDATE xx_op_register SET opname = '监控预报' WHERE id = '230000';

INSERT INTO `xx_op_register` (id,pid,opname,url,Icon,hasdisplay,description) VALUES ('230400', '230000', '专家意见', null, 'database_table', '1', null);

INSERT INTO `xx_role_authority` (roles,ops) VALUES ('5', '230400');



UPDATE xx_op_register SET pid = '220001' WHERE id = '220100';
UPDATE xx_op_register SET pid = '220001' WHERE id = '220110';

UPDATE xx_op_register SET pid = '220002' WHERE pid = '220000';

UPDATE xx_op_register SET pid = '220000' WHERE id = '220001';
UPDATE xx_op_register SET pid = '220000' WHERE id = '220002';


-- 更新 xx_op_register xx_role_authority
DROP TABLE IF EXISTS `xx_role_authority`;

DROP TABLE IF EXISTS `xx_op_register`;
CREATE TABLE `xx_op_register` (
  `id` int(11) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `opname` varchar(60) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `Icon` varchar(60) DEFAULT NULL,
  `hasdisplay` int(1) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_Reference_76` (`pid`),
  CONSTRAINT `FK_Reference_76` FOREIGN KEY (`pid`) REFERENCES `xx_op_register` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_op_register
-- ----------------------------
INSERT INTO `xx_op_register` VALUES ('-1', null, '获取菜单', 'admin/menu/display', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('0', null, null, 'expert/monitor/depotLoc', null, null, null);
INSERT INTO `xx_op_register` VALUES ('1', null, '文件操作', 'admin/file', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('900', null, '系统管理', null, 'computer_key', '1', null);
INSERT INTO `xx_op_register` VALUES ('901', '900', '角色管理', 'admin/role/manager', 'icon_group', '1', null);
INSERT INTO `xx_op_register` VALUES ('902', '901', '角色管理-获取列表', 'admin/role/dataGrid', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('903', '901', '角色管理-增加角色', 'admin/role/add', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('904', '901', '角色管理-修改角色', 'admin/role/edit', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('905', '901', '角色管理-删除角色', 'admin/role/delete', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('906', '901', '角色管理-授权-进入授权页面', 'admin/role/grantPage', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('907', '901', '角色管理-授权-权限列表', 'admin/role/tree', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('908', '901', '角色管理-授权-授权', 'admin/role/grant', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('909', '900', '用户管理', 'admin/listentrance', 'icon_user', '1', null);
INSERT INTO `xx_op_register` VALUES ('910', '909', '用户管理-ajax获取用户列表', 'admin/list', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('911', '909', '用户管理-删除用户', 'admin/remove', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('912', '909', '用户管理-修改用户', 'admin/edit', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('913', '909', '用户管理-进入新增用户界面', 'admin/addEntrance', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('914', '900', '注销用户', 'admin/logout', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('915', '909', '角色管理-授权-alltree', 'admin/role/allTree', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('916', '909', '用户管理-检查重名', 'admin/checkName', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('917', '909', '用户管理-add', 'admin/add', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('920', '900', '导航管理', 'admin/navigation/list', 'thumb_up', '1', null);
INSERT INTO `xx_op_register` VALUES ('921', '920', '导航管理', 'admin/navigation', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('10000', null, '基础资料', null, 'text_padding_left', '1', null);
INSERT INTO `xx_op_register` VALUES ('10100', '10000', '储粮区', 'admin/graindirection/list', 'world_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('10101', '10100', '储粮区操作', 'admin/graindirection', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('10200', '10000', '地区', 'admin/area/list', 'map', '1', null);
INSERT INTO `xx_op_register` VALUES ('10201', '10200', '地区操作', 'admin/area', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('10210', '10000', '单位', 'admin/company/list', 'building', '1', null);
INSERT INTO `xx_op_register` VALUES ('10300', '10000', '粮库信息', 'admin/depot/depot/entrance', 'house', '1', null);
INSERT INTO `xx_op_register` VALUES ('10301', '10300', '粮库信息操作', 'admin/depot/depot', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('10400', '10000', '库管员', 'admin/depot/user/entrance', 'icon_group', '1', null);
INSERT INTO `xx_op_register` VALUES ('10401', '10400', '库管员操作', 'admin/depot/user', null, '0', null);
INSERT INTO `xx_op_register` VALUES ('10500', '10000', '专家用户', 'admin/expert/entrance', 'icon_group', '1', null);
INSERT INTO `xx_op_register` VALUES ('10501', '10500', '专家用户-操作', 'admin/expert', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('10600', '10000', '虫调用户', 'admin/investor/entrance', 'icon_group', '1', null);
INSERT INTO `xx_op_register` VALUES ('10601', '10600', '虫调用户-操作', 'admin/investor', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('10700', '10000', '友情链接', 'admin/friend_link/list', 'link', '1', null);
INSERT INTO `xx_op_register` VALUES ('10701', '10700', '友情链接-操作', 'admin/friend_link', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('10800', '10000', '插件管理', null, 'plugin', '1', null);
INSERT INTO `xx_op_register` VALUES ('10810', '10800', '存储插件', 'admin/storage_plugin/list', 'plugin_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('10811', '10810', '存储插件-操作', 'admin/storage_plugin', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('10900', '10000', '缓存管理', 'admin/cache/clear', 'color_wheel', '1', null);
INSERT INTO `xx_op_register` VALUES ('10901', '10900', '缓存管理-操作', 'admin/cache', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('11000', '10000', '静态化管理', 'admin/static/build', 'color_wheel', '1', null);
INSERT INTO `xx_op_register` VALUES ('11001', '11000', '静态化管理-操作', 'admin/static', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('11100', '10000', '系统设置', 'admin/setting/edit', 'computer', '1', null);
INSERT INTO `xx_op_register` VALUES ('11101', '11100', '系统设置-操作', 'admin/setting', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('11200', '10000', '图标管理', 'admin/catalogIcon/list', 'images', '1', null);
INSERT INTO `xx_op_register` VALUES ('40000', null, '广告管理', '', 'bell', '1', null);
INSERT INTO `xx_op_register` VALUES ('40100', '40000', '广告位', 'admin/ad_position/list', 'bell_link', '1', null);
INSERT INTO `xx_op_register` VALUES ('40101', '40100', '广告位-操作', 'admin/ad_position', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('40200', '40000', '广告', 'admin/ad/list', 'bell_add', '1', null);
INSERT INTO `xx_op_register` VALUES ('40201', '40200', '广告-操作', 'admin/ad', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('40300', '40000', '导航设置', 'admin/navigation/list', 'connect', '1', null);
INSERT INTO `xx_op_register` VALUES ('50000', null, '文章管理', '', 'text_dropcaps', '1', null);
INSERT INTO `xx_op_register` VALUES ('50100', '50000', '类别管理', 'admin/article_category/list', 'text_list_numbers', '1', null);
INSERT INTO `xx_op_register` VALUES ('50101', '50100', '类别管理-操作', 'admin/article_category', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('50200', '50000', '标签管理', 'admin/tag/list', 'wand', '1', null);
INSERT INTO `xx_op_register` VALUES ('50201', '50200', '类别管理-操作', 'admin/tag', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('50300', '50000', '文章管理', 'admin/article/list', 'text_list_numbers', '1', null);
INSERT INTO `xx_op_register` VALUES ('50301', '50300', '文章管理-操作', 'admin/article', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('60000', null, '知识库', '', 'book_open', '1', null);
INSERT INTO `xx_op_register` VALUES ('60100', '60000', '害虫类别', 'admin/catalogIndex/list', 'palette', '1', null);
INSERT INTO `xx_op_register` VALUES ('60101', '60100', '害虫类别-操作', 'admin/catalogIndex', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('60200', '60000', '防治工艺类型', 'admin/proctype/list', 'brick', '1', null);
INSERT INTO `xx_op_register` VALUES ('60201', '60200', '防治工艺类型-操作', 'admin/proctype', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('60300', '60000', '防治工艺', 'admin/process/list', 'paintbrush', '1', null);
INSERT INTO `xx_op_register` VALUES ('60301', '60300', '防治工艺-操作', 'admin/process', '', '0', null);
INSERT INTO `xx_op_register` VALUES ('60400', '60000', '检索表', 'admin/insectsIndex/list', 'book_open', '1', null);
INSERT INTO `xx_op_register` VALUES ('100000', null, '虫调', null, 'icon_group', '1', null);
INSERT INTO `xx_op_register` VALUES ('110000', null, '虫调信息采集', null, 'world_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('110100', '110000', '农户信息维护', 'investor/farmer/farmerInfoList', 'icon_group', '1', null);
INSERT INTO `xx_op_register` VALUES ('110101', '110000', '农户点釆虫', 'investor/farmer/recordList', 'tab_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('110200', '110000', '加工厂信息维护', 'investor/factory/factoryInfoList', 'icon_group', '1', null);
INSERT INTO `xx_op_register` VALUES ('110201', '110000', '加工厂釆虫', 'investor/factory/recordList', 'tab_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('110300', '110000', '直属库釆虫', 'investor/depot/recordList', 'bricks', '1', null);
INSERT INTO `xx_op_register` VALUES ('110400', '110000', '野外釆虫', 'investor/field/list', 'rainbow', '1', null);
INSERT INTO `xx_op_register` VALUES ('110500', '110000', '昆虫采集分布登记', 'investor/register/collectDistribution/list', 'tab_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('110800', '110000', '我的足迹', 'investor/common/myfootprint', 'map', '1', null);
INSERT INTO `xx_op_register` VALUES ('110700', '110000', '昆虫鉴定登记', 'investor/register/insectsIdentify/list', 'tab_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('110600', '110000', '螨鉴定登记', 'investor/register/miteIdentify/list', 'tab_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('110900', '110000', '我的虫种分布', 'investor/common/myInsectsDistributes', 'map', '1', null);
INSERT INTO `xx_op_register` VALUES ('120000', null, '个人中心', null, 'icon_group', '1', null);
INSERT INTO `xx_op_register` VALUES ('120100', '120000', '消息中心', '', 'bell', '0', null);
INSERT INTO `xx_op_register` VALUES ('120200', '120000', '修改个人信息', 'investor/common/user/editInvestorEntrance', 'cog_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('120300', '120000', '注销', null, 'cog_delete', '0', null);
INSERT INTO `xx_op_register` VALUES ('120400', '120000', '修改密码', 'investor/common/user/editPasswordEntrance', 'icon_group_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('130000', null, '其他', null, null, '1', null);
INSERT INTO `xx_op_register` VALUES ('200000', null, '粮库', '', '', '1', null);
INSERT INTO `xx_op_register` VALUES ('210000', null, '粮库信息', null, 'brick', '1', null);
INSERT INTO `xx_op_register` VALUES ('210100', '210000', '粮库信息', 'depoter/depot/editDepotEntrance', 'database', '1', null);
INSERT INTO `xx_op_register` VALUES ('210200', '210000', '粮仓信息', 'depoter/depot/listbins', 'box', '1', null);
INSERT INTO `xx_op_register` VALUES ('210300', '210000', '库管员列表', 'depoter/common/list\r\n', 'icon_group', '1', null);
INSERT INTO `xx_op_register` VALUES ('220000', null, '数据上报', null, 'database_table', '1', null);
INSERT INTO `xx_op_register` VALUES ('220001', '220000', '害虫信息', null, 'database_table', '1', null);
INSERT INTO `xx_op_register` VALUES ('220002', '220000', '防治效果', null, 'database_table', '1', null);
INSERT INTO `xx_op_register` VALUES ('220100', '220001', '害虫调查总表', 'depoter/depotinvest/depot_invest_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('220110', '220001', '害虫分仓调查表', 'depoter/depotinvest/bin_invest_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('220200', '220002', '磷化氢抗药总表', 'depoter/depotantiphosphine/depot_antiphosphine_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('220210', '220002', '磷化氢抗药分仓表', 'depoter/depotantiphosphine/bin_antiphosphine_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('220300', '220002', '氮气气调总表', 'depoter/depotca/depot_ca_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('220310', '220002', '氮气气调分仓表', 'depoter/depotca/bin_ca_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('220400', '220002', '磷化氢熏蒸总表', 'depoter/depotphosphinefumigation/depot_phosphinefumigation_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('220410', '220002', '磷化氢熏分仓表', 'depoter/depotphosphinefumigation/bin_phosphinefumigation_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('220500', '220002', '防虫线杀虫总表', 'depoter/depotflyline/depot_flyline_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('220510', '220002', '防虫线杀虫分仓表', 'depoter/depotflyline/bin_flyline_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('220600', '220002', '防护剂总表', 'depoter/depotprotected/depot_protected_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('220610', '220002', '防护剂总表分仓表', 'depoter/depotprotected/bin_protected_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('220700', '220002', '惰性气体溶液总表', 'depoter/depotinertaerosol/depot_inertaerosol_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('220710', '220002', '惰性气体溶液分仓表', 'depoter/tbininertaerosol/tbin_inertaerosol_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('220800', '220002', '空仓杀虫总表', 'depoter/depotcleankill/depot_cleankill_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('220810', '220002', '空仓杀分仓表', 'depoter/depotcleankill/bin_cleankill_list', 'text_linespacing', '1', null);
INSERT INTO `xx_op_register` VALUES ('221000', null, '防治工艺上报', 'depoter/inforeport/list', 'database_table', '1', null);
INSERT INTO `xx_op_register` VALUES ('230000', null, '监控预报', null, 'table_lightning', '1', null);
INSERT INTO `xx_op_register` VALUES ('230100', '230000', '数据列表', 'depoter/realdata/entrance', 'text_align_center', '1', null);
INSERT INTO `xx_op_register` VALUES ('230200', '230000', '害虫数量、温湿度与时间', 'depoter/realdatacollect/insectsTemperatureHunmidity', 'chart_curve', '1', null);
INSERT INTO `xx_op_register` VALUES ('230300', '230000', '害虫分布', 'depoter/realdatacollect/insectsLoc', 'map', '1', null);
INSERT INTO `xx_op_register` VALUES ('230400', '230000', '专家意见', 'depoter/expertopinion/list', 'database_table', '1', null);
INSERT INTO `xx_op_register` VALUES ('231000', null, '直属库咨询', null, 'image', '1', null);
INSERT INTO `xx_op_register` VALUES ('240000', null, '个人中心', null, 'icon_group', '1', null);
INSERT INTO `xx_op_register` VALUES ('240200', '240000', '修改个人信息', 'depoter/common/editSelfEntrance', 'icon_group_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('240300', '240000', '修改密码', 'depoter/common/editPasswordEntrance', 'icon_group_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('250000', null, '其他', null, null, '1', null);
INSERT INTO `xx_op_register` VALUES ('300000', null, '专家', null, '', '1', null);
INSERT INTO `xx_op_register` VALUES ('310000', null, '虫调工作', null, 'text_signature', '1', null);
INSERT INTO `xx_op_register` VALUES ('310100', '310000', '足迹分布', 'expert/common/footMark', 'map', '1', null);
INSERT INTO `xx_op_register` VALUES ('310200', '310000', '昆虫分布', 'expert/common/insectsDistribution', 'map', '1', null);
INSERT INTO `xx_op_register` VALUES ('320000', null, '防治工艺效果', '', 'color_wheel', '1', null);
INSERT INTO `xx_op_register` VALUES ('330000', null, '监测预报', null, 'bell', '1', null);
INSERT INTO `xx_op_register` VALUES ('330100', '330000', '示范库分布', 'expert/monitor/depotLoc', 'bricks', '1', null);
INSERT INTO `xx_op_register` VALUES ('330200', '330000', '数据监控', 'expert/monitor/insectsTemperatureHunmidity', 'contrast', '1', null);
INSERT INTO `xx_op_register` VALUES ('330300', '330000', '专家意见', 'expert/opinion/list', 'comments', '1', null);
INSERT INTO `xx_op_register` VALUES ('340000', null, '数据审核', null, 'book_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('340100', '340000', '虫螨基础数据审核', 'expert/audit/catalogIndex/list', 'pencil', '1', null);
INSERT INTO `xx_op_register` VALUES ('340200', '340000', '检索表审核', 'expert/audit/insectsIndex/list', 'pencil', '1', null);
INSERT INTO `xx_op_register` VALUES ('350000', null, '处理咨询', 'expert/consult/list', 'images', '1', null);
INSERT INTO `xx_op_register` VALUES ('360000', null, '个人信息管理', null, 'icon_group', '1', null);
INSERT INTO `xx_op_register` VALUES ('360100', '360000', '消息中心', null, 'bell', '0', null);
INSERT INTO `xx_op_register` VALUES ('360200', '360000', '修改个人信息', 'expert/common/user/editExpertEntrance', 'icon_group_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('360300', '360000', '注销', '', 'cog_delete', '0', null);
INSERT INTO `xx_op_register` VALUES ('360400', '360000', '修改密码', 'expert/common/user/editPasswordEntrance', 'icon_group_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('400000', null, '农户', null, '', '1', null);
INSERT INTO `xx_op_register` VALUES ('410000', null, '害虫上报', 'farmer/enter/list', 'cup_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('420000', null, '害虫咨询', 'farmer/consult/list', 'comments', '1', null);
INSERT INTO `xx_op_register` VALUES ('430000', null, '个人信息管理', null, 'icon_group', '1', null);
INSERT INTO `xx_op_register` VALUES ('430100', '430000', '消息中心', null, 'bell', '0', null);
INSERT INTO `xx_op_register` VALUES ('430200', '430000', '修改个人信息', 'farmer/common/user/editFarmerEntrance', 'icon_group_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('430300', '430000', '注销', null, 'cog_delete', '0', null);
INSERT INTO `xx_op_register` VALUES ('430400', '430000', '修改密码', 'farmer/common/user/editPasswordEntrance', 'icon_group_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('500000', null, '加工厂', null, '', '1', null);
INSERT INTO `xx_op_register` VALUES ('510000', null, '害虫上报', 'factory/enter/list', 'cup_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('520000', null, '害虫咨询', 'factory/consult/list', 'comments', '1', null);
INSERT INTO `xx_op_register` VALUES ('530000', null, '个人信息管理', null, 'icon_group', '1', null);
INSERT INTO `xx_op_register` VALUES ('530100', '530000', '消息中心', null, 'bell', '0', null);
INSERT INTO `xx_op_register` VALUES ('530200', '530000', '修改个人信息', 'factory/common/user/editFactoryEntrance', 'icon_group_edit', '1', null);
INSERT INTO `xx_op_register` VALUES ('530300', '530000', '注销', null, 'cog_delete', '0', null);
INSERT INTO `xx_op_register` VALUES ('530400', '530000', '修改密码', 'factory/common/user/editPasswordEntrance', 'icon_group_edit', '1', null);

DROP TABLE IF EXISTS `xx_role_authority`;
CREATE TABLE `xx_role_authority` (
  `roles` bigint(20) NOT NULL,
  `ops` int(11) NOT NULL,
  PRIMARY KEY (`roles`,`ops`),
  KEY `FK_Reference_77` (`ops`),
  CONSTRAINT `FKE06165D939B03AB0` FOREIGN KEY (`roles`) REFERENCES `xx_role` (`id`),
  CONSTRAINT `FK_Reference_77` FOREIGN KEY (`ops`) REFERENCES `xx_op_register` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xx_role_authority
-- ----------------------------
INSERT INTO `xx_role_authority` VALUES ('1', '-1');
INSERT INTO `xx_role_authority` VALUES ('2', '-1');
INSERT INTO `xx_role_authority` VALUES ('4', '-1');
INSERT INTO `xx_role_authority` VALUES ('5', '-1');
INSERT INTO `xx_role_authority` VALUES ('6', '-1');
INSERT INTO `xx_role_authority` VALUES ('7', '-1');
INSERT INTO `xx_role_authority` VALUES ('8', '-1');
INSERT INTO `xx_role_authority` VALUES ('1', '1');
INSERT INTO `xx_role_authority` VALUES ('2', '1');
INSERT INTO `xx_role_authority` VALUES ('4', '1');
INSERT INTO `xx_role_authority` VALUES ('5', '1');
INSERT INTO `xx_role_authority` VALUES ('6', '1');
INSERT INTO `xx_role_authority` VALUES ('7', '1');
INSERT INTO `xx_role_authority` VALUES ('8', '1');
INSERT INTO `xx_role_authority` VALUES ('1', '900');
INSERT INTO `xx_role_authority` VALUES ('1', '901');
INSERT INTO `xx_role_authority` VALUES ('1', '902');
INSERT INTO `xx_role_authority` VALUES ('1', '903');
INSERT INTO `xx_role_authority` VALUES ('1', '904');
INSERT INTO `xx_role_authority` VALUES ('1', '905');
INSERT INTO `xx_role_authority` VALUES ('1', '906');
INSERT INTO `xx_role_authority` VALUES ('1', '907');
INSERT INTO `xx_role_authority` VALUES ('1', '908');
INSERT INTO `xx_role_authority` VALUES ('1', '909');
INSERT INTO `xx_role_authority` VALUES ('1', '910');
INSERT INTO `xx_role_authority` VALUES ('1', '911');
INSERT INTO `xx_role_authority` VALUES ('1', '912');
INSERT INTO `xx_role_authority` VALUES ('1', '913');
INSERT INTO `xx_role_authority` VALUES ('1', '914');
INSERT INTO `xx_role_authority` VALUES ('1', '915');
INSERT INTO `xx_role_authority` VALUES ('1', '916');
INSERT INTO `xx_role_authority` VALUES ('1', '917');
INSERT INTO `xx_role_authority` VALUES ('1', '920');
INSERT INTO `xx_role_authority` VALUES ('1', '921');
INSERT INTO `xx_role_authority` VALUES ('1', '10000');
INSERT INTO `xx_role_authority` VALUES ('2', '10000');
INSERT INTO `xx_role_authority` VALUES ('1', '10100');
INSERT INTO `xx_role_authority` VALUES ('2', '10100');
INSERT INTO `xx_role_authority` VALUES ('1', '10101');
INSERT INTO `xx_role_authority` VALUES ('2', '10101');
INSERT INTO `xx_role_authority` VALUES ('1', '10200');
INSERT INTO `xx_role_authority` VALUES ('1', '10201');
INSERT INTO `xx_role_authority` VALUES ('1', '10210');
INSERT INTO `xx_role_authority` VALUES ('1', '10300');
INSERT INTO `xx_role_authority` VALUES ('1', '10301');
INSERT INTO `xx_role_authority` VALUES ('1', '10400');
INSERT INTO `xx_role_authority` VALUES ('1', '10401');
INSERT INTO `xx_role_authority` VALUES ('1', '10500');
INSERT INTO `xx_role_authority` VALUES ('1', '10501');
INSERT INTO `xx_role_authority` VALUES ('1', '10600');
INSERT INTO `xx_role_authority` VALUES ('1', '10601');
INSERT INTO `xx_role_authority` VALUES ('1', '10700');
INSERT INTO `xx_role_authority` VALUES ('1', '10701');
INSERT INTO `xx_role_authority` VALUES ('1', '10800');
INSERT INTO `xx_role_authority` VALUES ('1', '10810');
INSERT INTO `xx_role_authority` VALUES ('1', '10811');
INSERT INTO `xx_role_authority` VALUES ('1', '10900');
INSERT INTO `xx_role_authority` VALUES ('1', '10901');
INSERT INTO `xx_role_authority` VALUES ('1', '11000');
INSERT INTO `xx_role_authority` VALUES ('1', '11001');
INSERT INTO `xx_role_authority` VALUES ('1', '11100');
INSERT INTO `xx_role_authority` VALUES ('1', '11101');
INSERT INTO `xx_role_authority` VALUES ('1', '11200');
INSERT INTO `xx_role_authority` VALUES ('1', '40000');
INSERT INTO `xx_role_authority` VALUES ('1', '40100');
INSERT INTO `xx_role_authority` VALUES ('1', '40101');
INSERT INTO `xx_role_authority` VALUES ('1', '40200');
INSERT INTO `xx_role_authority` VALUES ('1', '40201');
INSERT INTO `xx_role_authority` VALUES ('1', '40300');
INSERT INTO `xx_role_authority` VALUES ('1', '50000');
INSERT INTO `xx_role_authority` VALUES ('1', '50100');
INSERT INTO `xx_role_authority` VALUES ('1', '50101');
INSERT INTO `xx_role_authority` VALUES ('1', '50200');
INSERT INTO `xx_role_authority` VALUES ('1', '50201');
INSERT INTO `xx_role_authority` VALUES ('1', '50300');
INSERT INTO `xx_role_authority` VALUES ('1', '50301');
INSERT INTO `xx_role_authority` VALUES ('1', '60000');
INSERT INTO `xx_role_authority` VALUES ('1', '60100');
INSERT INTO `xx_role_authority` VALUES ('1', '60101');
INSERT INTO `xx_role_authority` VALUES ('1', '60200');
INSERT INTO `xx_role_authority` VALUES ('1', '60201');
INSERT INTO `xx_role_authority` VALUES ('1', '60300');
INSERT INTO `xx_role_authority` VALUES ('1', '60301');
INSERT INTO `xx_role_authority` VALUES ('1', '60400');
INSERT INTO `xx_role_authority` VALUES ('4', '110000');
INSERT INTO `xx_role_authority` VALUES ('4', '110100');
INSERT INTO `xx_role_authority` VALUES ('4', '110101');
INSERT INTO `xx_role_authority` VALUES ('4', '110200');
INSERT INTO `xx_role_authority` VALUES ('4', '110201');
INSERT INTO `xx_role_authority` VALUES ('4', '110300');
INSERT INTO `xx_role_authority` VALUES ('4', '110400');
INSERT INTO `xx_role_authority` VALUES ('4', '110500');
INSERT INTO `xx_role_authority` VALUES ('4', '110600');
INSERT INTO `xx_role_authority` VALUES ('4', '110700');
INSERT INTO `xx_role_authority` VALUES ('4', '110800');
INSERT INTO `xx_role_authority` VALUES ('4', '110900');
INSERT INTO `xx_role_authority` VALUES ('4', '120000');
INSERT INTO `xx_role_authority` VALUES ('4', '120100');
INSERT INTO `xx_role_authority` VALUES ('4', '120200');
INSERT INTO `xx_role_authority` VALUES ('4', '120300');
INSERT INTO `xx_role_authority` VALUES ('4', '120400');
INSERT INTO `xx_role_authority` VALUES ('4', '130000');
INSERT INTO `xx_role_authority` VALUES ('5', '210000');
INSERT INTO `xx_role_authority` VALUES ('9', '210000');
INSERT INTO `xx_role_authority` VALUES ('5', '210100');
INSERT INTO `xx_role_authority` VALUES ('9', '210100');
INSERT INTO `xx_role_authority` VALUES ('5', '210200');
INSERT INTO `xx_role_authority` VALUES ('9', '210200');
INSERT INTO `xx_role_authority` VALUES ('5', '210300');
INSERT INTO `xx_role_authority` VALUES ('5', '220000');
INSERT INTO `xx_role_authority` VALUES ('9', '220000');
INSERT INTO `xx_role_authority` VALUES ('5', '220001');
INSERT INTO `xx_role_authority` VALUES ('9', '220001');
INSERT INTO `xx_role_authority` VALUES ('5', '220002');
INSERT INTO `xx_role_authority` VALUES ('9', '220002');
INSERT INTO `xx_role_authority` VALUES ('5', '220100');
INSERT INTO `xx_role_authority` VALUES ('5', '220110');
INSERT INTO `xx_role_authority` VALUES ('9', '220110');
INSERT INTO `xx_role_authority` VALUES ('5', '220200');
INSERT INTO `xx_role_authority` VALUES ('5', '220210');
INSERT INTO `xx_role_authority` VALUES ('9', '220210');
INSERT INTO `xx_role_authority` VALUES ('5', '220300');
INSERT INTO `xx_role_authority` VALUES ('5', '220310');
INSERT INTO `xx_role_authority` VALUES ('9', '220310');
INSERT INTO `xx_role_authority` VALUES ('5', '220400');
INSERT INTO `xx_role_authority` VALUES ('5', '220410');
INSERT INTO `xx_role_authority` VALUES ('9', '220410');
INSERT INTO `xx_role_authority` VALUES ('5', '220500');
INSERT INTO `xx_role_authority` VALUES ('5', '220510');
INSERT INTO `xx_role_authority` VALUES ('9', '220510');
INSERT INTO `xx_role_authority` VALUES ('5', '220600');
INSERT INTO `xx_role_authority` VALUES ('5', '220610');
INSERT INTO `xx_role_authority` VALUES ('9', '220610');
INSERT INTO `xx_role_authority` VALUES ('5', '220700');
INSERT INTO `xx_role_authority` VALUES ('5', '220710');
INSERT INTO `xx_role_authority` VALUES ('9', '220710');
INSERT INTO `xx_role_authority` VALUES ('5', '220800');
INSERT INTO `xx_role_authority` VALUES ('5', '220810');
INSERT INTO `xx_role_authority` VALUES ('9', '220810');
INSERT INTO `xx_role_authority` VALUES ('5', '221000');
INSERT INTO `xx_role_authority` VALUES ('9', '221000');
INSERT INTO `xx_role_authority` VALUES ('5', '230000');
INSERT INTO `xx_role_authority` VALUES ('9', '230000');
INSERT INTO `xx_role_authority` VALUES ('5', '230100');
INSERT INTO `xx_role_authority` VALUES ('9', '230100');
INSERT INTO `xx_role_authority` VALUES ('5', '230200');
INSERT INTO `xx_role_authority` VALUES ('9', '230200');
INSERT INTO `xx_role_authority` VALUES ('5', '230300');
INSERT INTO `xx_role_authority` VALUES ('9', '230300');
INSERT INTO `xx_role_authority` VALUES ('5', '230400');
INSERT INTO `xx_role_authority` VALUES ('9', '230400');
INSERT INTO `xx_role_authority` VALUES ('5', '231000');
INSERT INTO `xx_role_authority` VALUES ('9', '231000');
INSERT INTO `xx_role_authority` VALUES ('5', '240000');
INSERT INTO `xx_role_authority` VALUES ('9', '240000');
INSERT INTO `xx_role_authority` VALUES ('5', '240200');
INSERT INTO `xx_role_authority` VALUES ('9', '240200');
INSERT INTO `xx_role_authority` VALUES ('5', '250000');
INSERT INTO `xx_role_authority` VALUES ('6', '310000');
INSERT INTO `xx_role_authority` VALUES ('6', '310100');
INSERT INTO `xx_role_authority` VALUES ('6', '310200');
INSERT INTO `xx_role_authority` VALUES ('6', '320000');
INSERT INTO `xx_role_authority` VALUES ('6', '330000');
INSERT INTO `xx_role_authority` VALUES ('6', '330100');
INSERT INTO `xx_role_authority` VALUES ('6', '330200');
INSERT INTO `xx_role_authority` VALUES ('6', '330300');
INSERT INTO `xx_role_authority` VALUES ('6', '340000');
INSERT INTO `xx_role_authority` VALUES ('6', '340100');
INSERT INTO `xx_role_authority` VALUES ('6', '340200');
INSERT INTO `xx_role_authority` VALUES ('6', '350000');
INSERT INTO `xx_role_authority` VALUES ('6', '360000');
INSERT INTO `xx_role_authority` VALUES ('6', '360100');
INSERT INTO `xx_role_authority` VALUES ('6', '360200');
INSERT INTO `xx_role_authority` VALUES ('6', '360300');
INSERT INTO `xx_role_authority` VALUES ('6', '360400');
INSERT INTO `xx_role_authority` VALUES ('7', '410000');
INSERT INTO `xx_role_authority` VALUES ('7', '420000');
INSERT INTO `xx_role_authority` VALUES ('7', '430000');
INSERT INTO `xx_role_authority` VALUES ('7', '430100');
INSERT INTO `xx_role_authority` VALUES ('7', '430200');
INSERT INTO `xx_role_authority` VALUES ('7', '430300');
INSERT INTO `xx_role_authority` VALUES ('7', '430400');
INSERT INTO `xx_role_authority` VALUES ('8', '510000');
INSERT INTO `xx_role_authority` VALUES ('8', '520000');
INSERT INTO `xx_role_authority` VALUES ('8', '530000');
INSERT INTO `xx_role_authority` VALUES ('8', '530100');
INSERT INTO `xx_role_authority` VALUES ('8', '530200');
INSERT INTO `xx_role_authority` VALUES ('8', '530300');
INSERT INTO `xx_role_authority` VALUES ('8', '530400');
INSERT INTO `xx_role_authority` VALUES ('5', '240300');
INSERT INTO `xx_role_authority` VALUES ('9', '240300');


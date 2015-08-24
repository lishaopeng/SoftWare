SET FOREIGN_KEY_CHECKS = 0;--end
DROP TABLE IF EXISTS tq_admin;--end
CREATE TABLE tq_admin
(
   admin_id           INT(10) NOT NULL,
   registe_time           DATETIME NOT NULL COMMENT '注册时间',
   registe_ip           VARCHAR(20) COMMENT '注册IP',
   last_login_time           DATETIME COMMENT '最后登录时间',
   last_login_ip           VARCHAR(20) COMMENT '最后登录IP',
   login_count           INT(10) NOT NULL COMMENT '登录次数',
   t_status           SMALLINT(5) NOT NULL COMMENT '状态',
   PRIMARY KEY(admin_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_admin VALUES(1,'2013-04-25 22:22:22.0','0:0:0:0:0:0:0:1','2014-05-26 18:48:33.0','0:0:0:0:0:0:0:1',1270,0);--end
 INSERT INTO tq_admin VALUES(5,'2013-05-27 08:08:04.0','0:0:0:0:0:0:0:1','2014-05-25 22:29:06.0','0:0:0:0:0:0:0:1',28,0);--end
DROP TABLE IF EXISTS tq_admin_channel;--end
CREATE TABLE tq_admin_channel
(
   admin_id           INT(10) NOT NULL,
   channel_id           INT(10) NOT NULL,
   PRIMARY KEY(admin_id,channel_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_admin_check;--end
CREATE TABLE tq_admin_check
(
   check_id           INT(10) NOT NULL AUTO_INCREMENT,
   admin_id           INT(10) NOT NULL COMMENT '管理员ID',
   site_id           INT(10) NOT NULL COMMENT '站点ID',
   mng_status           SMALLINT(5) NOT NULL COMMENT '管理状态',
   is_take_depart           BIT(1) NOT NULL DEFAULT b'1' COMMENT '采用部门栏目权限',
   PRIMARY KEY(check_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_admin_check VALUES(1,1,1,4,1);--end
 INSERT INTO tq_admin_check VALUES(3,5,1,1,1);--end
 INSERT INTO tq_admin_check VALUES(12,1,9,4,1);--end
 INSERT INTO tq_admin_check VALUES(19,1,18,4,1);--end
 INSERT INTO tq_admin_check VALUES(22,1,21,4,1);--end
 INSERT INTO tq_admin_check VALUES(24,1,23,4,1);--end
DROP TABLE IF EXISTS tq_admin_depart;--end
CREATE TABLE tq_admin_depart
(
   depart_id           INT(10) NOT NULL COMMENT '部门ID',
   admin_id           INT(10) NOT NULL COMMENT '管理员ID',
   PRIMARY KEY(admin_id,depart_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_admin_depart VALUES(1,1);--end
 INSERT INTO tq_admin_depart VALUES(2,5);--end
 INSERT INTO tq_admin_depart VALUES(8,1);--end
 INSERT INTO tq_admin_depart VALUES(17,1);--end
 INSERT INTO tq_admin_depart VALUES(20,1);--end
 INSERT INTO tq_admin_depart VALUES(22,1);--end
DROP TABLE IF EXISTS tq_admin_role;--end
CREATE TABLE tq_admin_role
(
   role_id           INT(10) NOT NULL COMMENT '角色ID',
   admin_id           INT(10) NOT NULL COMMENT '管理员ID',
   PRIMARY KEY(admin_id,role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_admin_role VALUES(1,1);--end
 INSERT INTO tq_admin_role VALUES(2,5);--end
 INSERT INTO tq_admin_role VALUES(11,1);--end
 INSERT INTO tq_admin_role VALUES(20,1);--end
 INSERT INTO tq_admin_role VALUES(23,1);--end
 INSERT INTO tq_admin_role VALUES(25,1);--end
DROP TABLE IF EXISTS tq_advert;--end
CREATE TABLE tq_advert
(
   advert_id           INT(10) NOT NULL AUTO_INCREMENT,
   site_id           INT(10) NOT NULL,
   slot_id           INT(10) NOT NULL COMMENT '广告位',
   name           VARCHAR(50) NOT NULL COMMENT '广告名称',
   adv_type           VARCHAR(20) NOT NULL COMMENT '广告类型',
   attr_url           VARCHAR(80) COMMENT '附件地址',
   attr_url2           VARCHAR(100) COMMENT '附件地址2',
   jsstring           VARCHAR(300) COMMENT 'JS内容',
   url           VARCHAR(100) NOT NULL COMMENT '链接',
   start_time           DATE NOT NULL COMMENT '投放时间',
   end_time           DATE COMMENT '结束时间',
   priority           INT(10) NOT NULL COMMENT '优先级',
   weights           INT(10) COMMENT '权重',
   clicks           INT(10) NOT NULL COMMENT '点击次数',
   show_times           INT(10) COMMENT '展现次数',
   aexplain           VARCHAR(500) COMMENT '说明',
   enable           BIT(1) COMMENT '是否启用',
   PRIMARY KEY(advert_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_advert VALUES(1,1,2,'首页通栏广告一','img','/member/upload/pms/201405/26005623vsyf.jpg',null,'','http://www.javapms.com','2014-05-23',null,10,1,0,1,'javapms正式发布',1);--end
 INSERT INTO tq_advert VALUES(4,1,1,'对联广告','img','/member/upload/pms/201405/24093347wscd.jpg',null,'','http://www.javapms.com','2014-05-23','2013-08-29',10,1,0,1,'热烈庆祝javapms V1.3正式版发布',1);--end
 INSERT INTO tq_advert VALUES(5,1,3,'首页漂浮广告','img','/member/upload/pms/201405/241129323b0s.jpg',null,'','http://www.javapms.com/idc/index.jsp','2014-05-24',null,10,null,0,null,'首页漂浮广告',1);--end
DROP TABLE IF EXISTS tq_advert_slot;--end
CREATE TABLE tq_advert_slot
(
   slot_id           INT(10) NOT NULL AUTO_INCREMENT,
   site_id           INT(10) NOT NULL,
   name           VARCHAR(30) NOT NULL COMMENT '广告位名称',
   slot_type           SMALLINT(5) NOT NULL COMMENT '类型',
   height           INT(10) COMMENT '高',
   width           INT(10) COMMENT '宽',
   remain           INT(10) COMMENT '停留时间',
   scrollbar           BIT(1) COMMENT '跟随滚动条',
   sexplain           VARCHAR(500) COMMENT '说明',
   idleholder           BIT(1) COMMENT '空闲时占位',
   rotation           BIT(1) NOT NULL DEFAULT b'1' COMMENT '轮换方式',
   PRIMARY KEY(slot_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_advert_slot VALUES(1,1,'对联广告位',4,300,100,0,0,'对联广告位',0,1);--end
 INSERT INTO tq_advert_slot VALUES(2,1,'首页通栏广告位一',1,121,1000,0,1,'首页通栏广告位',0,1);--end
 INSERT INTO tq_advert_slot VALUES(3,1,'漂浮广告位',2,111,197,0,0,'漂浮广告位',0,1);--end
 INSERT INTO tq_advert_slot VALUES(4,1,'弹窗广告位',3,400,300,0,1,'弹窗广告位',0,1);--end
DROP TABLE IF EXISTS tq_article;--end
CREATE TABLE tq_article
(
   article_id           INT(10) NOT NULL AUTO_INCREMENT,
   channel_id           INT(10) NOT NULL COMMENT '栏目ID',
   user_id           INT(10) COMMENT '用户ID',
   model_id           INT(10) NOT NULL COMMENT '模型ID',
   depart_id           INT(10) COMMENT '录入部门',
   check_id           INT(10) COMMENT '审核人员ID',
   site_id           INT(10) NOT NULL COMMENT '站点ID',
   title           VARCHAR(100) NOT NULL COMMENT '标题',
   short_title           VARCHAR(50) COMMENT '短标题',
   title_color           VARCHAR(10) COMMENT '标题颜色',
   release_date           DATETIME,
   is_bold           BIT(1) DEFAULT b'0' COMMENT '是否加粗',
   is_top           BIT(1) DEFAULT b'0' COMMENT '是否置顶',
   is_recommend           BIT(1) DEFAULT b'0' COMMENT '是否推荐',
   status           TINYINT(3) NOT NULL DEFAULT '2' COMMENT '状态(0:草稿;1:审核中;2:已审核;3:回收站)',
   style           VARCHAR(20) NOT NULL DEFAULT '0' COMMENT '类型',
   role_id           INT(10) COMMENT '审核角色ID',
   PRIMARY KEY(article_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_article VALUES(3,31,1,2,1,1,1,'小风衣大作为 雨天穿出好心情','','','2013-04-09 12:27:26.0',0,0,1,2,',3,',null);--end
 INSERT INTO tq_article VALUES(4,31,1,2,1,1,1,'服装选购时如何确定尺码大小','','','2013-04-16 10:39:00.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(5,5,1,2,1,1,1,'小户型收纳绝佳利器 超容量创意鞋柜','','','2013-04-16 10:51:36.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(6,5,1,2,1,1,1,'五万装修五十平米彩色单身公寓','','','2013-04-16 10:53:54.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(7,40,1,2,1,1,1,'H7N9来袭 关爱家人的5款美食疗方','','','2013-04-16 10:58:04.0',0,0,1,2,',1,',null);--end
 INSERT INTO tq_article VALUES(8,40,1,2,1,1,1,'“夜猫子”夜宵吃啥才健康','','','2013-04-16 11:09:51.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(9,32,1,2,1,1,1,'凤凰古城售票后首个周末多家旅店零入住','','','2013-04-16 11:15:53.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(10,45,1,2,1,1,1,'揭秘户外服装的价格结构','揭秘户外服装的价格结构','','2013-04-16 11:20:03.0',0,0,0,2,',4,',null);--end
 INSERT INTO tq_article VALUES(11,5,1,2,1,1,1,'在家里拥有专属于自己的图书馆不再是梦想','','','2013-04-16 11:21:50.0',0,0,0,2,',4,',null);--end
 INSERT INTO tq_article VALUES(12,40,1,2,1,1,1,'生活常见13类错误饮食搭配','','','2013-04-16 11:24:37.0',0,0,0,2,',4,',null);--end
 INSERT INTO tq_article VALUES(13,31,1,2,1,1,1,'2013春夏流行趋势之闪耀时刻','','','2013-04-16 11:29:07.0',0,0,0,2,',4,',null);--end
 INSERT INTO tq_article VALUES(14,5,1,2,1,1,1,'南昌新政 电动车载12岁以上者罚30','','','2013-04-16 15:20:22.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(15,31,1,2,1,1,1,'印花包臀裙轻松搞定S线条','','','2013-04-16 16:27:04.0',0,0,1,2,',2,',null);--end
 INSERT INTO tq_article VALUES(16,14,1,2,1,1,1,'街拍时冬日混搭百搭尚潮流','','','2013-04-17 09:35:28.0',0,0,0,2,',2,',null);--end
 INSERT INTO tq_article VALUES(17,14,1,2,1,1,1,'混搭女王范儿 性感秒杀装','','','2013-04-17 09:59:47.0',0,0,0,2,',2,',null);--end
 INSERT INTO tq_article VALUES(18,31,1,2,1,1,1,'快速出街 夏日清爽衣','','','2013-04-17 10:02:33.0',0,0,0,2,',2,',null);--end
 INSERT INTO tq_article VALUES(19,42,1,2,1,1,1,'如何辨别玉饰的真假','','','2013-04-17 10:35:54.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(20,15,1,2,1,1,1,'阿迪达斯鞋怎么辨别真假','','','2013-04-17 10:37:31.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(21,15,1,2,1,1,1,'脸形与耳环的搭配','','','2013-04-17 10:38:47.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(22,31,1,2,1,1,1,'丝袜选购的基本技巧','','','2013-04-17 10:40:09.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(23,24,1,2,1,1,1,'最受欢迎欧美超模 街拍风格大揭秘','','','2013-04-17 15:44:05.0',0,0,0,2,',3,',null);--end
 INSERT INTO tq_article VALUES(24,31,1,2,1,1,1,'为中国设计发声','','','2013-04-17 17:10:00.0',0,0,0,2,',3,',null);--end
 INSERT INTO tq_article VALUES(25,44,1,2,1,1,1,'教您区分菠萝和凤梨','','','2013-04-17 21:41:24.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(26,5,1,2,1,1,1,'理性家装 家装“游击队”省钱不省心','','','2013-04-17 22:50:03.0',0,0,0,2,',1,4,',null);--end
 INSERT INTO tq_article VALUES(27,5,1,2,1,1,1,'细数十大奇葩创意厕纸','','','2013-04-18 10:42:46.0',0,0,1,2,',1,4,',null);--end
 INSERT INTO tq_article VALUES(28,5,1,2,1,1,1,'新房装修不得不知的验房知识','','','2013-04-18 10:58:55.0',0,0,0,2,',1,4,',null);--end
 INSERT INTO tq_article VALUES(29,31,1,2,1,1,1,'从树皮衣看衣料的发展过程','','','2013-04-18 11:22:52.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(30,5,1,2,1,1,1,'我国一季度商品房销售面积增八成','','','2013-04-18 11:35:03.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(32,40,1,2,1,1,1,'养生专家谈春季的衣食住行','','','2013-04-19 09:04:45.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(33,26,1,2,1,1,1,'吃冻豆腐能减肥？教你冻豆腐的五种简易做法','','','2013-04-19 09:12:45.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(34,5,1,2,1,1,1,'17个要点能防禽流感：中午通风半小时 饮食多样化','','','2013-04-19 09:14:42.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(35,44,1,2,1,1,1,'如何鉴别优质五花肉 教你3种五花肉简易做法','','','2013-04-19 09:16:36.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(36,46,1,2,1,1,1,'五一期间江西18个旅游景点门票优惠 ','','','2013-04-19 21:42:51.0',0,0,1,2,',1,4,',null);--end
 INSERT INTO tq_article VALUES(37,26,1,2,1,1,1,'不得不知 日常生活中的解毒物质','','','2013-04-20 09:08:09.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(38,5,1,2,1,1,1,'沪铁路局春游40天送客4126万人 创历史新高','','','2013-04-20 09:39:28.0',0,0,0,2,',1,4,',null);--end
 INSERT INTO tq_article VALUES(39,46,1,2,1,1,1,'天津部分景区门票五一优惠 文庙博物馆22元','','','2013-04-20 09:40:18.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(40,32,1,2,1,1,1,'福建土楼人家放下农具“卖”旅游','','','2013-04-20 09:47:40.0',0,0,0,2,',1,4,',null);--end
 INSERT INTO tq_article VALUES(41,5,1,2,1,1,1,'衣食住行碳排放量可“算”出来','','','2013-04-21 08:53:10.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(42,32,1,2,1,1,1,'四川省旅游局正统计雅安周边景区游客情况','','','2013-04-21 08:54:43.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(43,32,1,2,1,1,1,'江西至少有107人正在四川旅游 已报平安','','','2013-04-21 08:55:59.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(44,5,1,2,1,1,1,'一载客300人游轮长江武汉段起火 现场浓烟滚滚','','','2013-04-21 08:58:23.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(45,5,1,2,1,1,1,'成都火车站组织改签、退票 不收退票费','','','2013-04-21 09:00:46.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(46,26,1,2,1,1,1,'您未必知道的柠檬水十大健康功效','','','2013-04-22 08:50:41.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(47,40,1,2,1,1,1,'千万别空腹喝的八种饮品','','','2013-04-22 08:54:05.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(48,5,1,2,1,1,1,'雅安震区天全县喇叭河景区100余名工人被困','','','2013-04-22 08:57:52.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(49,5,1,2,1,1,1,'游客抵制凤凰古城涨价 五一古城客房预订降半','','','2013-04-22 09:00:13.0',0,0,0,2,',1,4,',null);--end
 INSERT INTO tq_article VALUES(50,5,1,2,1,1,1,'四川发布交通管制信息 禁社会车辆自行前往灾区','','','2013-04-22 09:02:11.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(51,44,1,2,1,1,1,'营养丰富的西红柿炖牛肉家常做法','','','2013-04-22 09:21:52.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(52,5,1,2,1,1,1,'西部住房置业担保机构抱团发展','','','2013-04-23 09:16:02.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(53,5,1,2,1,1,1,'一睹为快全世界排名前十位富豪的房子','','','2013-04-23 09:25:44.0',0,1,0,2,',1,4,',null);--end
 INSERT INTO tq_article VALUES(54,32,1,2,1,1,1,'“五一”假期热门航线返程机票吃紧','','','2013-04-24 09:08:07.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(55,32,1,2,1,1,1,'“五一”机票已经升温，一些短期旅游线尤其热门','','','2013-04-24 09:09:15.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(56,26,1,2,1,1,1,'肚子胀气爱放屁惹尴尬 盘点容易引起胀气的食物','','','2013-04-24 09:10:38.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(57,40,1,2,1,1,1,'“炒”菜工程的N个诀窍 如何防止肉类水分流失','','','2013-04-24 09:14:46.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(58,5,1,2,1,1,1,'车库改成住房当心被查处','','','2013-04-24 09:17:24.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(59,5,1,2,1,1,1,'芦山灾区在厦职工可一次性提住房公积金余额','','','2013-04-24 09:18:56.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(60,5,1,2,1,1,1,'西藏旅游迎来“开门红” 一季度总收入3亿元','','','2013-04-25 11:29:57.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(61,5,1,2,1,1,1,'旅游法草案将表决 委员建议古城收费要明确','','','2013-04-25 11:30:34.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(62,32,1,2,1,1,1,'五一欧洲游提高防范意识 少带现金别露富','','','2013-04-25 11:31:15.0',0,0,1,2,',1,4,',null);--end
 INSERT INTO tq_article VALUES(63,5,1,2,1,1,1,'国台办：会采取措施保障大陆游客赴台游安全','','','2013-04-25 11:31:58.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(64,5,1,2,1,1,1,'旅游法最终功能不是停留在限价 更要限权','','','2013-04-25 11:32:31.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(65,25,1,2,1,1,1,'美50名大学生建造零能耗太阳能住房','','','2013-04-25 11:37:44.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(66,5,1,2,1,1,1,'村民户口跟着住房走','','','2013-04-25 11:39:04.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(67,5,1,2,1,1,1,'中国地震局专家： 自建住房别追求','','','2013-04-25 11:40:11.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(68,5,1,2,1,1,1,'400多白衣天使成住房公积金“黑户”','','','2013-04-25 11:43:02.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(69,5,1,2,1,1,1,'把住房产品“知情权”还给购房者','','','2013-04-25 11:50:44.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(71,5,1,2,1,1,1,'中国首部旅游法将实施 “门票经济”将有法可依','','','2013-04-26 21:39:50.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(72,32,1,2,1,1,1,'消协：注意选择正规旅行社 出游注意留证据','','','2013-04-26 21:41:19.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(73,5,1,2,1,1,1,'住房公积金“猫鼠博弈” 已成地方政府“小金库”','','','2013-04-26 21:42:34.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(74,5,1,2,1,1,1,'住房公积金被指劫贫济富 低存低贷模式存弊端','','','2013-04-26 21:43:08.0',0,0,1,2,',1,',null);--end
 INSERT INTO tq_article VALUES(75,42,1,2,1,1,1,'周大福黄金首饰频曝生锈 珠宝并无鉴定证书','','','2013-04-26 21:44:07.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(76,42,1,2,1,1,1,'黄金引疯抢 盘点那些吸睛纯金单品','','','2013-04-26 21:44:41.0',0,0,0,2,',1,4,',null);--end
 INSERT INTO tq_article VALUES(77,40,1,2,1,1,1,'感冒嗓子疼喝杯柠檬蜂蜜水','','','2013-04-26 21:45:23.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(78,40,1,2,1,1,1,'柠檬水十大健康功效','','','2013-04-26 21:45:48.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(82,48,1,4,1,1,1,'处处碰壁 菲律宾“特使”狼狈离台',null,null,'2013-05-15 15:27:25.0',null,1,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(83,48,1,4,1,1,1,'世卫组织提新年龄分段 44岁以下为青年人',null,null,'2013-05-15 15:41:49.0',null,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(84,21,1,4,1,1,1,'王石女友田朴珺未生孩子 复出40万一集',null,null,'2013-05-15 16:02:02.0',null,0,1,2,',1,',null);--end
 INSERT INTO tq_article VALUES(85,48,1,4,1,1,1,'实拍醉酒女子当街大闹 警车上跳热舞',null,null,'2013-05-15 16:11:27.0',null,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(86,48,1,4,1,1,1,'我国中西部多省份网友目击不明飞行物',null,null,'2013-05-15 16:15:47.0',null,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(87,32,1,2,1,1,1,'以背包客的名义 去东北感受清凉一夏','','','2013-05-16 10:00:38.0',0,0,1,2,',1,',null);--end
 INSERT INTO tq_article VALUES(88,48,1,4,1,1,1,'视频体验最经典的简欧风情家居空间设计',null,null,'2013-05-16 10:40:20.0',null,0,1,2,',1,',null);--end
 INSERT INTO tq_article VALUES(89,24,1,2,1,1,1,'医师曝朱莉切乳手术细节 皮特陪伴打气','','','2013-05-16 11:20:25.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(90,21,1,2,1,1,1,'大影节《1942》最佳 黄渤颜丙燕称帝后','','','2013-05-16 11:24:20.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(91,22,1,2,1,1,1,'网传抗日传奇剧被限播 卫视称未收到通知','','','2013-05-16 11:28:18.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(92,22,1,2,1,1,1,'陆贞”赵丽颖被曝坐台整容 回应:无聊','','','2013-05-16 11:32:18.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(94,23,1,2,1,1,1,'容祖儿新歌再被指抄袭 类似前科大起底','','','2013-05-16 11:36:03.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(95,25,1,2,1,1,1,'谷歌开发者大会开幕：地图成为最大亮点','','','2013-05-16 11:42:10.0',0,0,1,2,',1,',null);--end
 INSERT INTO tq_article VALUES(96,25,1,2,1,1,1,'华为中兴否认在欧盟从事不公平贸易','','','2013-05-16 11:48:14.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(97,25,1,2,1,1,1,'富士康开辟新客户乏力：苹果公司另结新欢','','','2013-05-16 11:50:01.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(98,25,1,2,1,1,1,'金山称WPS月活跃用户年底有望过亿','','','2013-05-16 11:51:30.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(99,25,1,2,1,1,1,'联合国提倡多吃昆虫缓解粮荒：解决蛋白质短缺','','','2013-05-16 11:53:27.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(100,25,1,2,1,1,1,'英研制先进无人四旋翼直升机 可自动追踪拍摄','','','2013-05-16 11:55:49.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(101,27,1,2,1,1,1,'创业板指重回千点 五大风险因素正在聚集','','','2013-05-16 11:59:26.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(102,27,1,2,1,1,1,'A股千万富翁数创年内新低','','','2013-05-16 12:00:31.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(104,29,1,2,1,1,1,'美元指数强势触及84关口 欧美传声筒今竞相亮相 ','','','2013-05-16 12:05:53.0',0,0,1,2,',1,',null);--end
 INSERT INTO tq_article VALUES(105,28,1,2,1,1,1,'杠杆基金双盈B及创业板ETF今年涨幅超40%','','','2013-05-16 15:56:11.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(106,28,1,2,1,1,1,'部分基金经理周一抄底上海家化被套 被迫参加股东大会','','','2013-05-16 15:57:23.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(107,30,1,2,1,1,1,'大商所加紧胶合板纤维板期货上市准备工作','','','2013-05-16 15:59:23.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(108,30,1,4,1,1,1,'黄金涨跌之谜 财经郎眼',null,null,'2013-05-16 21:54:43.0',null,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(110,35,1,3,1,1,1,'沈阳浑南新区一小型飞机坠毁 3人受伤',null,null,'2013-05-17 10:47:31.0',null,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(111,35,1,3,1,1,1,'重庆“女子纤夫”再现川江船工风采',null,null,'2013-05-17 00:00:01.0',null,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(112,35,1,3,1,1,1,'丽江酒吧摆“艳遇佛”引游客抚摸',null,null,'2013-05-10 23:48:43.0',null,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(113,35,1,3,1,1,1,'南航新疆招空乘 千余美女角逐80岗位',null,null,'2013-05-17 00:00:02.0',null,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(114,35,1,3,1,1,1,'韩国女子团体明星美貌排行揭晓',null,null,'2013-05-17 00:00:00.0',null,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(115,35,1,3,1,1,1,'十种食物吃多会丢命',null,null,'2013-05-18 00:05:55.0',null,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(116,35,1,3,1,1,1,'玛莎拉蒂车主车展砸车维权 称其有眼无珠',null,null,'2013-05-18 00:05:56.0',null,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(117,6,1,2,1,1,1,'科比不屑禅师比较：MJ和沙克搭档又会怎样？','','','2013-05-18 10:41:20.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(118,6,1,2,1,1,1,'美记者怒批科比狂妄：先超魔术师再和乔丹比','','','2013-05-18 10:47:58.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(119,6,1,2,1,1,1,'球迷不满火箭乱用林书豪：用不好就请交易他','','','2013-05-18 10:49:57.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(120,6,1,2,1,1,1,'季后赛MVP：詹皇高居榜首 库里小加大放异彩','','','2013-05-18 10:56:36.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(121,16,1,2,1,1,1,'国王杯-C罗破门染红皇马加时1-2负 马竞夺冠','','','2013-05-18 11:10:24.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(122,16,1,2,1,1,1,'西甲-巴萨2-1庆夺冠 法尔考破门梅西纪录终结','','','2013-05-18 11:12:54.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(123,17,1,2,1,1,1,'贝克汉姆退役后计划曝光 重回大联盟升任老板','','','2013-05-18 11:15:49.0',0,0,1,2,',1,3,',null);--end
 INSERT INTO tq_article VALUES(124,18,1,2,1,1,1,'李娜调侃球迷回应质疑：大家现在都已成专家','','','2013-05-18 11:18:12.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(125,19,1,2,1,1,1,'F1西班牙站战术回放：阿隆索四停夺冠超预期','','','2013-05-18 11:20:22.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(132,6,1,3,1,1,1,'dsfdsf',null,null,'2013-05-21 18:58:36.0',null,0,0,3,',1,',null);--end
 INSERT INTO tq_article VALUES(134,6,1,2,1,1,1,'测试','','','2013-06-01 22:15:26.0',0,0,0,3,',1,',null);--end
 INSERT INTO tq_article VALUES(135,6,1,2,1,1,1,'测试。。。','','','2013-06-04 10:44:49.0',0,0,0,3,',1,',null);--end
 INSERT INTO tq_article VALUES(136,5,11,2,1,11,1,'dsfsfsdfds','','','2013-06-21 17:55:25.0',0,0,0,3,',1,',null);--end
 INSERT INTO tq_article VALUES(137,5,1,2,null,1,1,'dfgfd','','','2013-10-22 12:33:52.0',0,0,0,3,',1,',null);--end
 INSERT INTO tq_article VALUES(185,5,1,2,1,1,1,'日猜测如中日开战 中国将渡海进攻冲绳台湾','','','2013-12-06 17:28:42.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(187,16,1,2,1,1,1,'恒大总分3-3首夺亚冠 创中国足球历史','','','2014-02-22 15:55:42.0',0,0,1,2,',1,3,',null);--end
 INSERT INTO tq_article VALUES(188,16,1,2,1,1,1,'埃神：恒大需要愿续约 加盟前曾想去巴萨皇马','','','2014-02-22 16:01:18.0',0,0,0,2,',1,2,',null);--end
 INSERT INTO tq_article VALUES(189,6,1,2,1,1,1,'霍华德：誓为火箭拿总冠军 与哈登堪比OK组合','','','2014-02-22 16:15:12.0',0,0,0,2,',1,2,',null);--end
 INSERT INTO tq_article VALUES(190,22,1,2,1,1,1,'崔永元美国传话：不回应辞职','','','2014-02-22 16:17:58.0',0,0,0,2,',1,2,',null);--end
 INSERT INTO tq_article VALUES(191,28,1,2,1,1,1,'基金半年报披露完毕 上半年大赚成长股 ','','','2014-02-22 16:20:58.0',0,0,0,2,',1,2,',null);--end
 INSERT INTO tq_article VALUES(192,27,1,2,1,1,1,'四因素决定结构性牛市仍未结束 冬种机会孕育','','','2014-02-22 16:24:01.0',0,0,0,2,',1,2,',null);--end
 INSERT INTO tq_article VALUES(193,25,1,2,1,1,1,'百度“百发不中”犯了哪三个错误？','','','2014-02-22 16:25:33.0',0,0,0,2,',1,2,',null);--end
 INSERT INTO tq_article VALUES(194,36,1,2,1,1,1,'苹果第四财季净利75亿美元 同比下滑9%','','','2014-02-22 16:27:05.0',0,0,0,2,',1,2,',null);--end
 INSERT INTO tq_article VALUES(195,31,1,2,1,1,1,'爸爸去哪儿热播 李湘女儿飙升为最热萌娃','','','2014-02-22 16:31:54.0',0,0,0,2,',1,2,',null);--end
 INSERT INTO tq_article VALUES(196,31,1,2,1,1,1,'韩国小姐不打扮像易容','','','2014-02-22 16:32:59.0',0,0,0,2,',1,2,',null);--end
 INSERT INTO tq_article VALUES(197,46,1,2,1,1,1,'圣安德鲁斯：重游威廉与凯特邂逅之地','','','2014-02-22 16:34:54.0',0,0,0,2,',1,2,',null);--end
 INSERT INTO tq_article VALUES(198,46,1,2,1,1,1,'永定土楼，光阴里的闽地“围”生活','','','2014-02-22 16:35:45.0',0,0,0,2,',1,2,',null);--end
 INSERT INTO tq_article VALUES(199,214,1,2,1,1,1,'五万装修五十平米彩色单身公寓','','','2013-04-16 10:53:54.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(200,212,1,2,1,1,1,'北上广深房价飙升 加剧决策层对房价泡沫的担忧','','','2013-10-29 20:46:02.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(201,212,1,2,1,1,1,'王健林谈生意、局势与财富观','','','2013-10-29 20:47:55.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(202,212,1,2,1,1,1,'恒大强势进京 40亿入驻豆各庄限房价地块','','','2013-10-29 20:48:39.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(203,212,1,2,1,1,1,'北京新地王诞生 融创楼面价7.3万夺农展馆地块','','','2013-10-29 20:51:43.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(204,212,1,2,1,1,1,'金九银十北京楼市躁动 新建商品住宅持续火爆','','','2013-10-29 20:52:51.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(205,212,1,2,1,1,1,'金九银十北京楼市躁动 新建商品住宅持续火爆','','','2013-10-29 20:52:51.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(206,212,1,2,1,1,1,'张文利：百利机电进一步深化国企改革','','','2013-10-29 20:53:48.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(207,212,1,2,1,1,1,'北京最牛违建仍矗立 房主称月底拆完','','','2013-10-29 20:55:18.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(208,214,1,2,1,1,1,'装修攻略 教你装修最省钱的方法','','','2013-10-29 20:59:28.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(209,212,1,2,1,1,1,'中国建筑前九月销售额超千亿 净利增25.7%','','','2013-10-29 21:01:16.0',0,0,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(210,212,1,2,1,1,1,'“孙家拳”新解','','','2013-10-29 21:03:01.0',0,0,0,2,',1,2,',null);--end
 INSERT INTO tq_article VALUES(211,35,1,3,1,1,1,'中国首次演练多型战机高速公路起降',null,null,'2014-05-25 15:12:15.0',null,1,0,2,',1,',null);--end
 INSERT INTO tq_article VALUES(212,5,1,2,1,1,1,'新疆打掉23个涉恐团伙抓200人 缴200余爆炸装置','','','2014-05-25 22:22:11.0',0,0,0,2,',1,',1);--end
DROP TABLE IF EXISTS tq_article_attachment;--end
CREATE TABLE tq_article_attachment
(
   article_id           INT(10) NOT NULL,
   priority           INT(10) NOT NULL COMMENT '排列顺序',
   att_path           VARCHAR(255) NOT NULL COMMENT '附件路径',
   att_name           VARCHAR(100) NOT NULL COMMENT '附件名称',
   download_count           INT(10) NOT NULL DEFAULT '0' COMMENT '下载次数'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_article_attr;--end
CREATE TABLE tq_article_attr
(
   article_id           INT(10) NOT NULL,
   attr_name           VARCHAR(30) NOT NULL COMMENT '名称',
   attr_value           VARCHAR(255) COMMENT '值'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_article_channel;--end
CREATE TABLE tq_article_channel
(
   channel_id           INT(10) NOT NULL,
   article_id           INT(10) NOT NULL,
   PRIMARY KEY(article_id,channel_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_article_ext;--end
CREATE TABLE tq_article_ext
(
   article_id           INT(10) NOT NULL,
   sub_title           VARCHAR(100) COMMENT '副标题',
   author           VARCHAR(30) COMMENT '作者',
   origin           VARCHAR(50) COMMENT '来源',
   origin_url           VARCHAR(50) COMMENT '来源链接',
   show_index           BIT(1) DEFAULT b'1' COMMENT '是否显示到首页',
   is_red_tape           BIT(1) DEFAULT b'0' COMMENT '是否红头文件',
   red_tape_origin           VARCHAR(100) COMMENT '红头文件来源',
   description           VARCHAR(255) COMMENT '描述',
   comment_control           BIT(1) DEFAULT b'1' COMMENT '是否允许评论',
   updown_control           BIT(1) DEFAULT b'1' COMMENT '顶踩控制',
   link           VARCHAR(100) COMMENT '外部链接',
   tpl_content           VARCHAR(100) COMMENT '指定模板',
   time_day           DATE COMMENT '定时日期',
   time_hour           TIME COMMENT '定时时间',
   tag_str           VARCHAR(50),
   PRIMARY KEY(article_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_article_ext VALUES(3,'','','','',1,0,null,'小风衣大作为 雨天穿出好心情',0,null,'','',null,null,'');--end
 INSERT INTO tq_article_ext VALUES(4,'',null,null,null,1,0,null,'服装选购尺码大小如何看，服装上符号的含义表示什么？教你选购适合自己尺码大小的服装。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(5,'',null,null,null,1,0,null,null,0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(6,'',null,null,null,1,0,null,'北欧风格的客厅装修，小清新最爱简约。一些比较有北欧风格的客厅，加上一些开放式的饭厅等，简洁大方，是他们风格的一向作风，很喜欢，可以作为现代家居装修时参考运用',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(7,'','','','',1,0,null,'关爱家人从一点一滴做起，保证充足的睡眠和合理的饮食，多吃蔬菜、水果和滋补汤水，把身体调养好，才可以防止病毒的入侵！',0,null,'','',null,null,'');--end
 INSERT INTO tq_article_ext VALUES(8,'',null,null,null,1,0,null,'现如今熬夜的人群越来越多，因此很多人就会选择吃夜宵来补充体力以及精力。生活中还有部分人群本身就有吃夜宵的习惯，否则的话就睡不着觉。但是专家特别提醒，晚上应该尽量的避免吃东西，就算要吃也应该吃一些清淡易消化的食物。如果夜宵吃的太过于油腻以及丰盛的话，很有可能会影响肠胃健康。下面营养小厨就给你大家推荐几款适合当做夜宵的食材。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(9,'',null,null,null,1,0,null,null,0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(10,'',null,null,null,1,0,null,null,0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(11,'',null,null,null,1,0,null,null,0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(12,'',null,null,null,1,0,null,'生活中，有些食物的搭配组合已经是由来已久，其美妙的口味也被人们所接受，习惯上也觉得这些种搭配是顺理成章的了。其实，这样搭配吃是不健康的，不仅会影响大人，而且会影响小孩健康成长。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(13,'',null,null,null,1,0,null,'有光泽的面料，金属色印花，彩虹般的光芒，都劲情的呈现在2013春夏的T台上。例如：Alexander Wang，Louise Gray和Mulberry。大量的金属色光泽是2013春夏T台充满了魔力',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(14,'',null,null,null,1,0,null,'近日，经过一年多酝酿，南昌市首部城管条例——《南昌市城市管理条例》3月1日起施行，填补了该市城市管理的法律空白。关于电动车管理，《条例》中明确驾驶电动车载12周岁以上者，罚款30元。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(15,'',null,null,null,1,0,null,'是女人都想要的S线条如何获得？也许你身材不错，个子高挑，但如果穿得不当，S线条也会离你远去。春天这样百花齐放阳光明媚的季节，就用一款印花包臀裙来塑造美妙S型吧！',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(16,'',null,null,null,1,0,null,'时间过的超快的有没有~！冬季了哦~很多MM每天起床面对满衣橱的衣服还是会发呆苦恼自己穿什么~下面和小编一起看看亚洲街头穿搭术！更了解自己衣橱里的衣服到底应该怎么穿搭吧~！',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(17,'',null,null,null,1,0,null,'黑色的休闲背心，外搭红色网眼套头衫，美眉脚上的桃红尖头平跟鞋，与上衣的颜色相呼应，很搭调。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(18,'',null,null,null,1,0,null,'这件有着可爱俏皮卡通图案的长版T恤，胸前的亮片装饰赋予了强烈的视觉冲击感。干净的色调与清新的图案给人愉悦美好的视觉享受',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(19,'',null,null,null,1,0,null,'玉器是一种受资源影响极大的奢侈品。古玉的作伪不消说，就是新玉，也因为资源有限，作伪的行为层出不穷。 一起看看如何识别它的真假吧！',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(20,'',null,null,null,1,0,null,'辨别真假爱迪达斯鞋的小方法分享给大家，看过之后，大家都去看看自己购买的鞋子是什么货品吧！',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(21,'',null,null,null,1,0,null,'你知道自己的脸型适合什么应当的耳环吗？你知道如何搭配会显的更加漂亮吗？小编给你支一招！',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(22,'',null,null,null,1,0,null,'丝袜不仅手感轻柔，让你充分享受温柔呵护，还能让你的玉腿和足尖在薄如蝉翼的丝袜里若隐若现，展现出韵味无穷的朦胧之美；丰韵的色彩和织纹能令你的腿部更具吸引力，为玉足秀腿平添一层性感的光辉。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(23,'',null,null,null,1,0,null,null,0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(24,'',null,null,null,1,0,null,'今日中国“第一夫人”彭丽媛女士随国家主席习近平出访的造型引起广泛热议，“第一夫人”支持中国本土设计，以身作则穿戴中国自主品牌设计的服饰，让大家都看到了她对中国设计师的喜爱，也将提升国人对本土设计的信心。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(25,'',null,null,null,1,0,null,'凤梨和菠萝并非同一种水果，而是长得相似的两种水果。两者的区别有：凤梨的叶子不带齿、味甜，菠萝的叶子带齿，果肉要用盐水泡过才能吃。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(26,'',null,null,null,1,0,null,'作为消费者，更应以理性、成熟的心态和观念来对待家庭装修这一人生的大事，不要贪小便宜吃大亏',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(27,'',null,null,null,1,0,null,null,0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(28,'',null,null,null,1,0,null,'对于很多装修业主而言，验收查房简直就是一个走过场的形式，懵懵懂懂地在一堆的表格上签好字就完事了，而事后发现屋子有啥子问题又投诉无门，因为已经验收过了',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(29,'',null,null,null,1,0,null,'衣食住行，以衣为首，足见人类对于衣服这件事的重视。考古发现在数万年前，人类已穿上极其华丽的衣服。按衣服原料制作技术体系的差异，初步可区分为纺织及无纺织两大系统。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(30,'',null,null,null,1,0,null,'北京市统计局、国家统计局北京调查总队昨日联合发布的数据显示，北京一季度房地产销售增长较快，商品住宅销售面积同比增长超八成，二季度恐进入“放空期”。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(32,'',null,null,null,1,0,null,'春暖花开万物苏醒，朋友们在愉快的踏春游玩旅途中，别忘记给身体做好保健哦',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(33,'',null,null,null,1,0,null,'冻豆腐是我们常吃的，那么吃冻豆腐可以减肥吗？冻豆腐减肥怎么吃呢？下面就来了解一下冻豆腐减肥的功效及吃法。 ',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(34,'',null,null,null,1,0,null,'禽流感的卷土重来，让人们再次开始审视自己的生活习惯。作为普通百姓，如何才能在生活各个方面预防H7N9禽流感？给家里消毒、提前吃药预防到底管用吗？饮食和外出上又该注意什么？ ',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(35,'',null,null,null,1,0,null,'很多人都爱吃五花肉，很多人都偏向于吃那种不肥不瘦的五花肉，所以如何选择质量过关的五花肉就是一个非常重要的事情了。挑选完五花肉，在接下来做几款美食五花肉给家人，就是非常惬意的事情了。 ',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(36,'',null,null,null,1,0,null,'随着“五一”的临近，不少朋友打算趁着小长假出行。昨日，记者从省发改委获悉，“五一”期间(4月29日——5月1日)我省将有18个景点实行门票优惠，方便广大游客游览。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(37,'',null,null,null,1,0,null,'日常生活中常见的解毒食物有哪些?生活中掌握一些中毒急救的保健常识是很有必要的。有很多时候人们由于吃到不健康食品，接触不卫生物品而导致中毒。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(38,'',null,null,null,1,0,null,'据上海铁路局4月19日统计，3月10日至4月18日春游期间，该局累计发送旅客4126.2万人，与去年同比增加460.7万人，增长12.6%，旅客发送总量超过今年春运，创历年春游旅客运输新高',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(39,'',null,null,null,1,0,null,'记者从天津市发展改革委获悉，根据《国家发展改革委办公厅关于落实“五一”期间门票价格优惠措施营造良好旅游价格环境的通知》，本市部分景区“五一”期间实行门票价格优惠。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(40,'',null,null,null,1,0,null,'世界文化遗产“福建土楼”中，最古老和最年轻的土楼均坐落于“土楼之乡”福建永定县境内的初溪土楼群。驱车来到初溪土楼群，一条小溪从山脚潺潺流过，站在石头桥上抬头望去，只见或圆或方的成片土楼依山而建，在山水田园间高低错落。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(41,'',null,null,null,1,0,null,'每烧制1公斤牛肉，碳排放为6.4千克，相当于烧制 25公斤土豆的排放量。在昨天“酷中国低碳生活进社区”活动中，“我爱绿色”环保网站推出的“碳计算器”，直观地给出了人们衣食住行的大致碳排放量。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(42,'',null,null,null,1,0,null,'据中国之声《央广新闻》报道，记者四川省旅游局管理处了解到，目前四川21个市州旅游局正在统计各地旅行社在雅安周边景区游玩的游客情况。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(43,'',null,null,null,1,0,null,'目前通过该旅行社有107名江西游客身在四川，他们主要在乐山峨眉山、九寨沟和成都旅游，全部都很平安，大家还在继续行程。据悉，江西前往四川旅游的旺季一般在暑假，雅安不是江西人前往四川的主要旅游目的地。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(44,'',null,null,null,1,0,null,null,0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(45,'',null,null,null,1,0,null,'具体停运列车信息为：4月20日成都开D5104 D5106 D5174 。成都东开D5108 D5110 D5112 D5114 D5116 D5118 D5134次，重庆北开：D5105 D5107 D5109 D5111 D5133 D5113次停运。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(46,'',null,null,null,1,0,null,'柠檬富含多种抗氧化剂。早上喝1杯柠檬水，就足以让人神清气爽。你知道吗？柠檬水还有多种保健功效。澳大利亚“每日电讯”网站4月8日采访澳大利亚悉尼市营养学家米歇尔·舍瓦莱·黑基，刊出柠檬水的十大功效。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(47,'',null,null,null,1,0,null,'日常生活中，我们经常喝各种饮品，有些饮品是不能空腹喝的，甚至可导致胃溃疡等疾病，那么，究竟有哪些日常饮品是不能空腹喝呢，一起来看看吧。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(48,'',null,null,null,1,0,null,'今日下午记者与天全旅游局雷汉玉局长取得联系。喇叭河景区还有100余景区施工工人被滞留。目前游客与施工工人情况良好，无伤亡，已被安置在景区内的部分小木屋客栈和帐篷里。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(49,'',null,null,null,1,0,null,'五一小长假临近，许多市民又在积极准备出游。和清明节相比，五一拼假的优势让人们可以更从容出游，境内外旅游报名相比清明节增长了1倍。记者了解，热门旅游目的地经济型酒店近日预订已经接近饱和。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(50,'',null,null,null,1,0,null,'四川省公安厅交警总队刚刚发布抗震救灾交通管制信息，禁止所有社会车辆自行前往灾区。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(51,'',null,null,null,1,0,null,'穿好几件衣服，有时候热，热到可以吹风扇。 这样的天气里面人们经常因为身体抵抗能力受不了就得了各种季节病了，西红柿炖牛肉的做法学会了，你也可以在家里面为自己的身体加加油，抵抗天气 的变化。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(52,'',null,null,null,1,0,null,'4月21日，西部省市重庆、四川、云南、陕西、新疆、贵州、青海、甘肃、广西等9省区市，12家住房置业担保机构组成西部住房置业担保行业联席会议，将尝试通过内部成员的信息共享，建立起区域信息共享机制，建立成员间公积金异地贷款代办落实抵押、核实抵押登记等合作机制。\r\n',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(53,'','','','',1,0,null,'他们是福布斯全球富豪榜中的亿万富翁，他们在美国甚至全世界都是响当当的名字，他们的财富在全球富豪榜中都名列前茅，他们的家更是让人惊叹不已，让我们一睹为快',1,null,'','',null,null,'');--end
 INSERT INTO tq_article_ext VALUES(54,'',null,null,null,1,0,null,'本周过后，人们就将迎来一年一度的“五一”假期。按照往年规律，每年的“五一”假期都是春游出游高峰的一个爆发点，今年也不例外。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(55,'',null,null,null,1,0,null,'今年“五一”小长假从4月29日开始，5月1日结束，距今只有半个月时间。目前，“五一”机票已经升温，一些短期旅游线尤其热门。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(56,'',null,null,null,1,0,null,'当身体出现内部器官出现胀气时，是非常难受的，吃的东西不好或者收了凉风都会出现腹胀、胃胀等现象，所谓“气大伤身”说的生气容易导致身体出现疾病，但是当身体出现胀气时也同样会引起。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(57,'',null,null,null,1,0,null,'“炒”大概是中餐中最常用的手法，尤其是川菜，小炒算得上是一大特色。基本的步骤是：肉切好并“码芡”；油烧热；下姜片（或丝、末）翻炒；肉下锅翻炒，术语叫“散仔发白”；加调料，翻炒均匀；下配菜，炒熟；或者勾芡，或者不勾芡，起锅装盘。整个过程就几分钟，如果清炒素菜的话更快。 ',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(58,'',null,null,null,1,0,null,'经了解，该项目部分楼座于2010年1月12日取得了商品房预售许可证具备销售条件。该项目正在办理规划验收手续，暂不具备办证条件。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(59,'',null,null,null,1,0,null,'22日从市住房公积金管理中心获悉，为了帮助芦山地震重灾区在厦职工克服困难，重建家园，自4月22日起，凡是户籍在四川省雅安市芦山县、宝兴县、汉源县、荥经县、天全县、石棉县、雨城区、名山区8个重灾县(区)的在厦职工，可申请一次性提取本人及配偶账户内的住房公积金。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(60,'',null,null,null,1,0,null,'进入4月以来,随着雪域高原气温的回暖，西藏旅游旺季的序幕悄然拉开。连日来，拉萨八廓街、布达拉宫广场、罗布林卡等旅游景点的游客明显增多。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(61,'',null,null,null,1,0,null,'24日下午，全国人大常委会第三次委员长会议决定根据常委会审议意见，对旅游法草案作进一步审议修改后，交付今日全国人大常委会第二次会议闭幕会表决。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(62,'',null,null,null,1,0,null,'近期，我国游客在欧洲特别是法国多次遭当地黑人偷、抢，成为出行安全隐患，给游人和旅行社造成一定程度的损失',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(63,'',null,null,null,1,0,null,'国台办发言人范丽青24日表示，两岸双方都会进一步采取切实措施，保障大陆游客赴台旅游的安全。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(64,'',null,null,null,1,0,null,'正当凤凰通票制引起广泛关注之时，十二届全国人大常委会第二次会议23日审议的《旅游法》草案将焦点对准景区门票，强调景区门票价格不能说涨就涨，应严格规范程序。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(65,'',null,null,null,1,0,null,'由斯坦福工程学院学生设计、50名学生从2013年3月底开始建造的这座太阳能独户住房以木质为主体，面积大约92平方米，有两个卧室、一个卫生间，造价约25万美元。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(66,'',null,null,null,1,0,null,'夜幕降临，312国道旁的相城区望亭镇鹤溪社区广场上，响起了动感十足的音乐，男女老少陆续走到一起，有的兴致勃勃地跳起广场舞，有的天南海北聊起了新鲜事。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(67,'',null,null,null,1,0,null,'国家救援队队员、中国地震局现场评估专家贾群林，在连续参加3天救援后，建议农民自建住房时改变“高、大、空、重”的传统做法，把自家房子盖结实点。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(68,'',null,null,null,1,0,null,'记者接到山东省肿瘤医院多名职工的反映，虽然他们已在该医院工作多年，可单位并没有给他们办理住房公积金缴存登记手续。对此，记者决定一探究竟。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(69,'',null,null,null,1,0,null,null,0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(71,'',null,null,null,1,0,null,'昨日，中国首部旅游法以150票赞成、5票弃权，表决通过。《中华人民共和国旅游法》将于2013年10月1日起施行。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(72,'',null,null,null,1,0,null,'“五一”出游高峰即将到来，在消费者即将出游之际，山西省消费者协会发布今年第6号消费提示，提醒游客在出游时注意选择正规旅行社，',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(73,'',null,null,null,1,0,null,'围绕住房公积金的“猫鼠博弈”始终在进行。虽然通过不断完善的管理制度，已经堵住了大部分漏洞，但仍面临很多现实困境。',0,null,null,null,null,null,'');--end
 INSERT INTO tq_article_ext VALUES(74,'',null,null,null,1,0,null,'作为我国重要的住房保障制度，住房公积金制度为改善城镇居民住房条件发挥了重要作用。而在现实中，“低存低贷”、“强制储蓄”的住房公积金在降低部分缴存者购房成本的同时，也面对着“劫贫济富”、“劫贫济贫”的争议和花样百出的非法套取。',0,null,null,null,null,null,'');--end
 INSERT INTO tq_article_ext VALUES(75,'',null,null,null,1,0,null,'全景网4月24日讯 随着五一长假临近，很多内地居民已经计划去香港买金，香港周大福无疑是首选的大品牌。然而最近有网友反映周大福的黄金饰品会生锈，而在全景网调查中也显示消费者对周大福的不满度最高。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(76,'',null,null,null,1,0,null,'黄金跌价引起了市民的疯抢，网友们对投资黄金进行了热议，接下来就来盘点一下那些让人大跌眼镜的黄金单品，这些东西到底能不能保值呢？',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(77,'',null,null,null,1,0,null,'是药三分毒，感冒了嗓子疼喝杯柠檬蜂蜜水。柠檬具有生津止渴、和胃降逆、化痰止咳的功效。感冒初起时，喝柠檬蜂蜜水可以缓解咽喉疼痛，减少喉咙干燥等不适。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(78,'',null,null,null,1,0,null,'柠檬富含多种抗氧化剂。早上喝1杯柠檬水，就足以让人神清气爽。你知道吗？柠檬水还有多种保健功效。',0,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(82,null,'','中国台湾网','',1,0,null,'据台湾“中央社”报道，台湾当局启动制裁措施，并认为菲律宾授权不足，拒绝接见马尼拉经济文化办事处主席培瑞斯，培瑞斯和菲律宾驻台代表白熙礼今天下午离台。\r\n',null,null,'','',null,null,null);--end
 INSERT INTO tq_article_ext VALUES(83,null,null,null,null,1,0,null,'最近联合国世界卫生组织提出新的年龄分段在微博热传。根据新规，44岁及以下为青年人，45岁至59岁为中年人。对此市民怎么看？新民网记者走上街头听听市民想法。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(84,null,null,'新浪娱乐',null,1,0,null,'2013年5月13日晚，王石离异后的现任女友田朴珺低调出席电影《中国合伙人》首映会，观影后便在工作人员的护送下匆匆离去。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(85,null,null,null,null,1,0,null,'实拍醉酒女子当街大闹 警车上跳热舞',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(86,null,null,'新浪播客',null,1,0,null,'目击者称该飞行物呈V字形，边缘略显红色，放射出超长光亮，光亮照射处可清晰的看出该物体正在向天空中喷出气体，形成云雾状分散开来，目前官方还未证实。\r\n ',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(87,'',null,null,null,1,0,null,'“上午在哈尔滨看冰雪，中午在大连叹海鲜，下午在沈阳赏古迹，晚上到长春听二人转”，随着去年底哈大高铁开始飞奔，一日串游东北三省知名旅游城市已经不再是梦想。\r\n\r\n',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(88,null,null,null,null,1,0,null,'“家装的每个环节都向您透明”这是昶卓设计的服务理念，也是昶卓设计经久不衰的法宝。本期的《工地会说话》就来到昶卓设计位于滨江奥城的一处110平米的工地，感受充满浪漫情调的简欧风格家居空间。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(89,'',null,null,null,1,0,null,'好莱坞性感红星安杰丽娜-朱莉(Angelina Jolie)因为带有缺陷基因BRCA1，毅然接受预防性的双乳房乳腺切除术，以降低罹癌风险，勇气让人叹服！为她施行手术的外科医师克莉丝蒂-朋克(Kristi Funk)在博客上详述整个手术过程。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(90,'',null,null,null,1,0,null,'第20届北京大学生电影节[微博]5月13日晚在奥体中心落下帷幕，冯小刚[微博]导演的《1942》获得最佳影片奖，管虎凭《杀生》获最佳导演奖，影帝、影后分别被黄渤[微博]和颜丙燕[微博]获得。最受大学生欢迎的导演及男女演员分别花落徐峥[微博]、王宝强[微博]和张雨绮[微博]。电影节评委会大奖则由《万箭穿心》和《神探亨特张[微博]》一同获得。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(91,'',null,null,null,1,0,null,'今年以来，抗日剧因“奇幻”情节频现，饱受争议。日前，网传新版广电总局“22条军令”将开始规范卫视黄金档电视剧播出，其中特别规定：所有抗日剧都需重新审查，抗日剧黄金档播出将受到限制',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(92,'',null,null,null,1,0,null,'2006年因为选秀出道的赵丽颖，曾出演《佳期如梦》、《追鱼传奇》、《极品男女日记》、《吉祥天宝》、《云中歌》等多部影视剧集。有网友将她在《陆贞传奇》的剧照与《佳期如梦》时照片对比，发现她容貌相差很大。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(94,'',null,null,null,1,0,null,'早被网友戏谑为“完美复印机”的容祖儿[微博]，歌曲疑似抄袭前科累累。前年被指歌曲《花千树》抄袭马浚伟[微博]旧歌《不再悲观》，如今新歌《另眼相看》日前正式派台，歌曲名字由作词人黄伟文日前于微博征集歌名的游戏而来。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(95,'',null,null,null,1,0,null,'北京时间5月16日凌晨消息，谷歌2013年I/O开发者大会今天在美国加州旧金山芳草地艺术中心开幕，更新一系列软件产品，其中最大亮点是重新设计了谷歌地图，依据搜索历史及其他数据提供更加个性化的服务',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(96,'',null,null,null,1,0,null,'欧盟委员会计划向中国政府发出正式警告，准备就非法补贴问题对华为和中兴通讯进行制裁。昨天，华为和中兴均发布官方声明，否认从事不公平贸易活动。中国政府本周二也作出回应，敦促欧盟避免采取贸易保护主义措施。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(97,'',null,null,null,1,0,null,'手机行业的巨大变革直接影响到了鸿海精密工业(下称“富士康”)的生意，如果不做出改变，它也许会错失更多的机会。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(98,'',null,null,null,1,0,null,'5月15日上午消息，金山办公软件副总裁章庆元昨天表示，2013年年底WPS月活跃用户有望达到1亿，其中桌面端用户将占到6成，移动端用户占到4成。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(99,'',null,null,null,1,0,null,'北京时间5月16日消息，近日，一份联合国报告指出，提高食谱中昆虫的比重将为人类健康、环境保护以及经济发展带来巨大的好处。这份来自联合国粮食及农业组织的报告解释道，许多昆虫“富含蛋白质和优质脂肪，并含有丰富的钙、铁和锌”。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(100,'',null,null,null,1,0,null,'北京时间5月16日消息，据国外媒体报道，英国无人机制造商Universal Air正在研制一款可充当“私人间谍”的四旋翼直升机，能够自行追踪和拍摄高清视频，追踪用户的一举一动。整个过程中，用户无需对其进行操控。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(101,'',null,null,null,1,0,null,'创业板持续走强，从宏观背景看，符合目前经济转型的需要；从资金面看，外围增量资金入市速度很慢，创业板股票总规模适合存量资金运作；从产业结构看，创业板集中了新兴行业公司，符合产业方向。然而，随着创业板反弹加速，各种风险因素正在聚集。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(102,'',null,null,null,1,0,null,'A股四月份连续第三个月下挫，散户投资者的资产急剧缩水。统计数据显示，A股持股市值超过1万元以上的账户数均出现不同程度减少，其中千万富翁的数量创下年内新低，但万元以下的迷你散户却急剧增加。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(104,'',null,null,null,1,0,null,'美元指数高位整理，现交投于83.80附近。隔夜欧元区多国一季度GDP数据普遍逊于预期，打压欧元进一步走低，兑美元刷新六周低点1.2842，美元指数则强势触及84关口。今日市场风险事件依然不少，欧美央行多位高官将陆续发表讲话，传递何种政策信号值得投资者密切留意，此外欧美通胀数据也料将在汇市掀起一番波澜。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(105,'',null,null,null,1,0,null,'杠杆债券基金和创业板基金是今年的明星基金产品，截至昨日，双盈B和创业板相关基金产品今年以来投资收益率首次超过40%大关，领跑包括交易型基金和开放式基金在内的全部基金产品',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(106,'',null,null,null,1,0,null,'自上海家化(65.08,2.09,3.32%)股东大会现场获悉，有部分公募基金经理在本周一上海家化大跌当天试图抄底被套，其中上海某基金经理当天就入手100万股。该人士坦言：“没想到刚进去就吃了一个跌停。我早上5点刚下飞机，立马就感到现场来了。”',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(107,'',null,null,null,1,0,null,'继去年木材纤维板立项后，今年木材胶合板期货又获立项，近期大商所密集组织人员调研胶合板和纤维板市场，以加紧完善两品种期货合约和规则，积极推动“两板”上市准备工作、以现代期货机制服务林木产业发展。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(108,null,null,null,null,1,0,null,'黄金涨跌之谜 财经郎眼',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(110,null,'','网易图片','',1,0,null,'5月16日10时50分许，一架实施人工増雨小型飞机在沈阳浑南新区沈本大道千锦汇酒店附近坠毁并发生爆炸。目前，沈阳消防已赶到现场予以扑救。据现场目击者称，3人在飞机爆炸前脱险，目前1人重伤，2人轻伤。',null,null,'','',null,null,null);--end
 INSERT INTO tq_article_ext VALUES(111,null,'','','',1,0,null,'月16日，重庆国博中心，数十名青年男女赤脚肘臂，手握纤绳表演船工拉纤生活，再现了当年船工风采。据了解，这是当地“印象武隆”表演团队为“渝洽会”开幕助兴。',null,null,'','',null,null,null);--end
 INSERT INTO tq_article_ext VALUES(112,null,'','','',1,0,null,'近日，一则“丽江一酒吧摆价值千万‘艳遇佛’引游客疯狂抚摸”的网帖炒热了丽江古城胭雨酒吧里的一尊佛像，慕名而来的游客摸佛、拜佛，希望以此获得桃花运。然而，也有网友吐槽酒吧行为“亵渎神灵”。',null,null,'','',null,null,null);--end
 INSERT INTO tq_article_ext VALUES(113,null,'','','',1,0,null,'自14日开始的南航新疆招乘还在持续中，南航2013春季乘务（安全）员招募会的200个岗位，吸引了近两千名应聘者参与角逐。虽然此次只有80个空姐招聘名额，另外120个招聘名额留给了“空哥”，但还是吸引了超过一千名美女前来应聘。',null,null,'','',null,null,null);--end
 INSERT INTO tq_article_ext VALUES(114,null,'','','',1,0,null,'据韩媒报道，万万没料到，少女时代第一美的林允儿（润娥），长相也会败在别的女人的手里，在韩国专业机构的调查中，被媒体夸为最强美貌的润娥惜败“国民初恋”秀智。',null,null,'','',null,null,null);--end
 INSERT INTO tq_article_ext VALUES(115,null,'','','',1,0,null,'十种食物吃多会丢命',null,null,'','',null,null,null);--end
 INSERT INTO tq_article_ext VALUES(116,null,'','','',1,0,null,'一辆价值260万的玛莎拉蒂总裁停在会展中心广场，四名男子手持铁锤砸向这辆玛莎拉蒂。车主朋友表示，因福日集团下属欧利行车行修车时旧件当成新件来用，数月没有解决问题。',null,null,'','',null,null,null);--end
 INSERT INTO tq_article_ext VALUES(117,'',null,'腾讯体育',null,1,0,null,'菲尔-杰克逊在新书《11枚戒指：成功的灵魂》中比较了科比-布莱恩特和迈克尔-乔丹这两位时代领袖，作为曾经执教过两个人的主教练，菲尔显然有一定发言权，不过在他的眼里，乔丹是要强过科比的。然而科比本人似乎对这种比较毫无兴趣，他在twitter上进行了回应。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(118,'',null,'腾讯体育',null,1,0,null,'北京时间5月18日消息，科比(微博)早前在推特中激烈反驳禅师的“乔丹更强论”，不过在美国记者看来，飞侠显然没法和篮球上帝相提并论，《RealGm》专栏作家贾罗德-鲁多夫便坦言，科比先超过魔术师再谈超乔丹吧。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(119,'',null,'腾讯体育',null,1,0,null,'林书豪在季后赛表现令人失望，不过，在火箭球迷看来，林的拙劣发挥完全是火箭所导致，有人甚至坦言：如果不能正确使用林书豪，那么请把他交易到一支适合自己的球队。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(120,'',null,'腾讯体育',null,1,0,null,'季后赛打到现在，球迷心中的季后赛MVP是谁？《ESPN》做了一个调查，调查的题目就是你心目当中的季后赛MVP，截止到目前为止，热火的当家球星詹姆斯高居榜首，勇士的库里和灰熊的马克-加索尔分列二三位',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(121,'',null,null,null,1,0,null,'北京时间5月18日凌晨，2012-13赛季国王杯决赛中，皇马(官方微博数据) 主场1-2遭马竞逆转，痛失冠军。C罗(微博数据) 头球首开纪录，迭戈-科斯塔扳平，皇马三次射中立柱，加时赛中米兰达绝杀，穆里尼奥、C罗与马竞的加比先后被罚下。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(122,'',null,null,null,1,0,null,'北京时间5月13日凌晨，西甲第35轮中，巴萨客场2-1逆转马德里竞技，用胜利庆祝了联赛夺冠。梅西首发但提前退场，连续21场联赛进球的纪录告终，法尔考首开纪录，桑切斯扳平比分，比利亚造成加比自摆乌龙。',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(123,'','','','',1,0,null,'北京时间5月18日凌晨，《天空体育》透露，宣布退役后的贝克汉姆极有可能在美国大联盟组织一支全新俱乐部。',null,null,'','',null,null,'');--end
 INSERT INTO tq_article_ext VALUES(124,'sfdsf',null,null,null,1,0,null,'贵为法网冠军，娜姐本赛季的红土比赛打得不理想。斯图加特闯入决赛后，娜姐在红土赛的闪耀就结束，马德里爆冷首轮出局，罗马止步第三轮。最新世界排名也受到影响，将被埃拉尼挤掉下滑至第六名。',0,null,null,null,null,null,'');--end
 INSERT INTO tq_article_ext VALUES(125,'',null,null,null,1,0,null,'阿隆索在第9圈第一次进站更换硬胎，在第21圈再次更换硬胎，到第36圈时换上了中性胎，之后在第49圈再换硬胎。他的队友菲利普·马萨也采用了相似的策略。',0,null,null,null,null,null,'');--end
 INSERT INTO tq_article_ext VALUES(132,null,null,null,null,1,0,null,'sdfsdfsdf',null,null,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(134,'测试',null,null,null,1,0,null,'测试',1,null,null,null,null,null,'测试');--end
 INSERT INTO tq_article_ext VALUES(135,'',null,null,null,1,0,null,'测试。。。',null,null,null,null,null,null,'测试');--end
 INSERT INTO tq_article_ext VALUES(136,'',null,null,null,1,0,null,null,1,null,null,null,null,null,'');--end
 INSERT INTO tq_article_ext VALUES(137,'fgfdg',null,null,null,1,0,null,'fgfdgfdg',1,null,null,null,null,null,'');--end
 INSERT INTO tq_article_ext VALUES(185,'',null,null,null,1,0,null,'核心提示：日本《产经新闻》5日报道称，中国在东海设立防空识别区后，航空自卫队立即召开紧急电视会议。作战中枢航空总队司令和全国三个航空支队的司令、冲绳西南航空混成团司令全部到场，再加上总队直辖部队的指挥官，“表情僵硬”地会聚一堂。',1,null,null,null,null,null,'猜测,中日,开战,中国,渡海,进攻,冲绳,台湾');--end
 INSERT INTO tq_article_ext VALUES(187,'','','','',1,0,null,'北京时间11月9日，在亚冠决赛的次回合比赛中，广州恒大(微博 数据)坐镇天河体育场1-1战平韩国球队首尔FC，虽然双方总比分战成3-3平，不过凭借客场进球优势恒大最终力压对手捧杯，这也是中国球队第一次问鼎亚冠冠军，埃尔克森第58分钟打破僵局，4分钟后德扬扳平比分。',1,1,'','',null,null,'总分,首夺,中国足球,历史');--end
 INSERT INTO tq_article_ext VALUES(188,'','','','',1,0,null,'记者白国华首尔报道 “如果有可能的话，我希望回到主场，在广州再进一球。”赛后，对于2比2的结果，埃尔克森虽然比较满意，但并不满足，他希望在自己的主场终结首尔FC，“冠军，终归恒大”。',1,1,'','',null,null,'需要,续约,加盟,曾想,想去');--end
 INSERT INTO tq_article_ext VALUES(189,'','','','',1,0,null,'北京时间10月28日，《休斯敦纪事报》消息，霍华德在今天对着球迷们保证，要为他们带来总冠军，霍华德还将自己与哈登的二人组和OK组合、斯托克顿与马龙(微博)的组合进行了比较',1,1,'','',null,null,'霍华德,誓为,火箭,总冠军,组合');--end
 INSERT INTO tq_article_ext VALUES(190,'','','','',1,0,null,'记者联系到身在美国的崔永元。他表示不回应辞职央视的事宜。',1,1,'','',null,null,'美国,传话,不回,回应,辞职');--end
 INSERT INTO tq_article_ext VALUES(191,'','','','',1,0,null,'上半年基金盈利205.38亿元，较去年同期近千亿的盈利水平相差甚远。其中，押注成长股的基金赚得盆满钵满，而坚守价值股的基金“账本”相对尴尬。',1,1,'','',null,null,'基金,半年报,披露,完毕,上半年,大赚,成长');--end
 INSERT INTO tq_article_ext VALUES(192,'','','','',1,0,null,'近期股指持续大跌4个交易日，不是秋收行情的结束，而是部分个股风险的集中释放，冬种春生大好机会正孕育中',1,1,'','',null,null,'四因,因素,决定,定结,结构性,牛市,仍未,未结');--end
 INSERT INTO tq_article_ext VALUES(193,'','','','',1,0,null,'试水“互联网金融”的滩头战斗，百度虽然勉强登陆，但品牌损失不小。在我看来，百度犯了三个错误。 ',1,1,'','',null,null,'百度,百发,发不,不中,犯了,三个,错误');--end
 INSERT INTO tq_article_ext VALUES(194,'','','','',1,0,null,'10月29日消息，苹果当日公布了截至9月28日2013财年第四财季财报。报告显示，该季度苹果实现营收375亿美元，同比增长4%；实现净利润75亿美元，去年同期为82亿美元，同比下滑9%；合摊薄后每股收益8.26美元，去年同期为8.67美元。',1,1,'','',null,null,'苹果,第四,净利,美元,同比,比下,下滑');--end
 INSERT INTO tq_article_ext VALUES(195,'','','','',1,0,null,'最近，随着爸爸去哪儿的热播，李湘女儿瞬间飙升为最热萌娃。',1,1,'','',null,null,'爸爸,哪儿,热播,李湘,女儿,飙升,升为,最热');--end
 INSERT INTO tq_article_ext VALUES(196,'','','','',1,0,null,'每年“韩国小姐”选拔大赛拉开帷幕都好像酝酿着一场魔术。',1,1,'','',null,null,'韩国,国小,小姐,不打,打扮,扮像,易容');--end
 INSERT INTO tq_article_ext VALUES(197,'','','','',1,0,null,'就在这座苏格兰城市中，威廉王子与凯特·米德尔顿相遇，大不列颠最古老的高等学府之一就坐落在圣安德鲁斯城内，而高尔夫运动亦诞生于此。在2001年，当凯瑟特·米德尔顿初次踏入苏格兰的圣安德鲁斯大学时，她肯定疑惑，是什么样的命运在等待着自己？',1,1,'','',null,null,'圣安德,安德鲁斯,重游,威廉,凯特,邂逅,之地');--end
 INSERT INTO tq_article_ext VALUES(198,'','','','',1,0,null,'去福建，龙岩的永定土楼是不能不去的，去厦门永定土楼也是不能不去的。',1,1,'','',null,null,'永定,光阴,生活');--end
 INSERT INTO tq_article_ext VALUES(199,'',null,null,null,1,0,null,'北欧风格的客厅装修，小清新最爱简约。一些比较有北欧风格的客厅，加上一些开放式的饭厅等，简洁大方，是他们风格的一向作风，很喜欢，可以作为现代家居装修时参考运用',1,1,null,null,null,null,'装修,彩色,单身公寓');--end
 INSERT INTO tq_article_ext VALUES(200,'',null,null,null,1,0,null,'目前的情况是，在中国的一线大城市，有钱你也不一定买得到房。今年第三季度，中国房地产市场的销量同比增长放缓至21.2%，上一个季度的数据是32.4%，同期住房投资也在减速，新的房地产建设项目预计会在在未来几个月放缓。',1,1,null,null,null,null,'北上,房价,飙升,加剧,决策层,房价,泡沫,担忧');--end
 INSERT INTO tq_article_ext VALUES(201,'','','','',1,0,null,'在闪光灯辉映下，首富先生侃侃而谈。“我觉得我是幸福的，因为我在追求我自己的梦想，我在逐梦当中，每一次离梦越近，我都感觉特别欣慰。 ”王健林说。',1,1,'','',null,null,'王健林,生意,局势,财富');--end
 INSERT INTO tq_article_ext VALUES(202,'','','','',1,0,null,'2013年9月4日，经过43轮竞拍，恒大以40.4亿元配建51500平方米公租房摘得朝阳区豆各庄乡B地块，这也是北京第一块以“限房价竞地价”的方式出让土地。据了解，该地块剩余居住用途建筑规模建设的商品住房销售限价为22000元/平方米。',1,1,'','',null,null,'强势,进京,房价,地块');--end
 INSERT INTO tq_article_ext VALUES(203,'',null,null,null,1,0,null,'2013年9月4日，北京国土局现场，经过69轮拍，融创以21亿元配建27.8万平方米医院面积一举拿下农展馆北路8号住宅地块！经计算农展馆土地溢价率为16%，楼面价为35501元/平方米，考虑到配建的医院成本，楼面价高达73099元/平米，成为北京名副其实的新地王。',1,1,null,null,null,null,'北京,新地,诞生,楼面,面价,农展馆,地块');--end
 INSERT INTO tq_article_ext VALUES(204,'',null,null,null,1,0,null,'在今年的“金九银十”来临之前，楼市在8月已经呈现出超乎以往的热度。据中原地产市场研究中心统计数据显示：8月，全国54城市新建住宅合计签约套数达25.2万套，环比上涨6.4%。这也是最近4个月的最高点。',1,1,null,null,null,null,'北京,楼市,躁动,新建,商品住宅,持续,火爆');--end
 INSERT INTO tq_article_ext VALUES(205,'',null,null,null,1,0,null,'在今年的“金九银十”来临之前，楼市在8月已经呈现出超乎以往的热度。据中原地产市场研究中心统计数据显示：8月，全国54城市新建住宅合计签约套数达25.2万套，环比上涨6.4%。这也是最近4个月的最高点。',1,1,null,null,null,null,'北京,楼市,躁动,新建,商品住宅,持续,火爆');--end
 INSERT INTO tq_article_ext VALUES(206,'','','','',1,0,null,'日前，天津市召开进一步深化国有企业改革推动会，提出国有企业未来五年改革目标，并明确未来五年改革重点，着力推动国企调整重组与资源优化配置，着力推动国企产权多元化与资产证券化，着力完善体制机制与企业管理，加快转型升级步伐，提升质量效益，促进全市经济社会持续健康发展。',1,1,'','',null,null,'张文,百利,机电,进一步,深化,国企改革');--end
 INSERT INTO tq_article_ext VALUES(207,'',null,null,null,1,0,null,'位于北京人济山庄的“最牛违建”拆除进度遭到市民质疑。市民发现自8月15日开拆以来，最底层的假山和房屋从外部看没有太大变化，怀疑房主张必清已经悄然停工。',1,1,null,null,null,null,'北京,违建,矗立,房主,月底');--end
 INSERT INTO tq_article_ext VALUES(208,'',null,null,null,1,0,null,'家，是我们的归宿，希望它能赏心悦目，就希望它会舒适安全，希望它环保健康。而这些希望成真是要付出众多金钱代价，面对装修的朋友都会想同一个问题，那就是怎样装修房子最省钱。',1,1,null,null,null,null,'装修,攻略,教你,装修,最省,省钱,方法');--end
 INSERT INTO tq_article_ext VALUES(209,'',null,null,null,1,0,null,'10月28日，中国建筑股份有限公司发布第三季度报告。1-9月，该公司实现营业收入4,799亿元，同比增长21.8%。其中，三季度实现营业收入1,780亿元，同比增长26.8%。',1,1,null,null,null,null,'中国建筑,九月,销售额,千亿,净利');--end
 INSERT INTO tq_article_ext VALUES(210,'','','','',1,0,null,'此刻的融创正处在甜蜜点。就像NBA季后赛一样，领先的一方通常不会率先做出调整。那么，“孙家拳”的独门之道是什么？',1,1,'','',null,null,'新解');--end
 INSERT INTO tq_article_ext VALUES(211,null,null,null,null,1,0,null,'2014年5月25日，中国军方某型歼击机、某型运输机、某型直升机，首次成功起降中原某高速公路飞机跑道。该高速公路飞机跑道按一级甲类标准建设，可满足三代战机和中小型运输机在战时或紧急情况下应急起降。',1,1,null,null,null,null,null);--end
 INSERT INTO tq_article_ext VALUES(212,'','潘从武','法制网',null,1,0,null,'5月23日新疆启动严打暴恐活动专项行动以来，全区各级公安机关闻令即动，在前期侦查、调查、摸排的基础上主动进攻、严查深挖，于5月25日凌晨实施“零点”抓捕行动，以雷霆之势迅速打掉一批暴恐团伙，抓获一批犯罪嫌疑人，缴获一批制爆工具、材料及管制刀具，对暴力恐怖活动形成“快、稳、准、狠”的严打态势。',1,1,null,null,null,null,'新疆,打掉,团伙,爆炸,装置');--end
DROP TABLE IF EXISTS tq_article_group_view;--end
CREATE TABLE tq_article_group_view
(
   article_id           INT(10) NOT NULL,
   group_id           INT(10) NOT NULL,
   PRIMARY KEY(article_id,group_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_article_group_view VALUES(74,2);--end
 INSERT INTO tq_article_group_view VALUES(74,3);--end
DROP TABLE IF EXISTS tq_article_picture;--end
CREATE TABLE tq_article_picture
(
   article_id           INT(10) NOT NULL,
   priority           INT(10) NOT NULL COMMENT '排列顺序',
   img_path           VARCHAR(100) NOT NULL COMMENT '图片地址',
   description           VARCHAR(255) COMMENT '描述',
   style           VARCHAR(50) COMMENT '状态',
   is_thumb           BIT(1) DEFAULT b'0',
   PRIMARY KEY(article_id,priority)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_article_picture VALUES(3,0,'/member/upload/pms/201405/25124430hwo6.jpg','',',3,',1);--end
 INSERT INTO tq_article_picture VALUES(7,0,'/member/upload/pms/201405/25130318i780.jpg','',',0,',1);--end
 INSERT INTO tq_article_picture VALUES(15,0,'/member/portal/demo/201305/161143591r2c.jpg','',',0,',1);--end
 INSERT INTO tq_article_picture VALUES(16,0,'/member/portal/demo/201304/17094328ogtz.jpg','',',2,',1);--end
 INSERT INTO tq_article_picture VALUES(17,0,'/member/portal/demo/201304/170959433t39.jpg','',',0,2,',1);--end
 INSERT INTO tq_article_picture VALUES(23,0,'/member/portal/demo/201304/17154218u1y3.jpg','',',3,',1);--end
 INSERT INTO tq_article_picture VALUES(24,0,'/member/portal/demo/201304/171706016w5x.jpg','',',0,3,',1);--end
 INSERT INTO tq_article_picture VALUES(26,0,'/member/portal/demo/201304/17225340hzum.jpg','',',0,3,',1);--end
 INSERT INTO tq_article_picture VALUES(82,0,'/member/upload/pms/201405/251247445jnb.jpg','',',0,',1);--end
 INSERT INTO tq_article_picture VALUES(83,0,'/member/portal/demo/201305/15154135t1sq.jpg','',',0,',1);--end
 INSERT INTO tq_article_picture VALUES(84,0,'/member/portal/demo/201305/15160134mxyz.jpg','',',0,',1);--end
 INSERT INTO tq_article_picture VALUES(85,0,'/member/portal/demo/201305/151611239i9c.jpg','',',0,',1);--end
 INSERT INTO tq_article_picture VALUES(86,0,'/member/portal/demo/201305/151615295b4z.jpg','',',0,',1);--end
 INSERT INTO tq_article_picture VALUES(87,0,'/member/portal/demo/201305/16100036m0td.jpg','',',0,',1);--end
 INSERT INTO tq_article_picture VALUES(95,0,'/member/portal/demo/201305/16114632frwb.jpg','',',0,',1);--end
 INSERT INTO tq_article_picture VALUES(104,0,'/member/portal/demo/201305/162343172n95.jpg','',',0,',1);--end
 INSERT INTO tq_article_picture VALUES(110,0,'/member/portal/demo/201305/18001407pl0t.jpg','',',0,',1);--end
 INSERT INTO tq_article_picture VALUES(110,1,'/member/portal/demo/201305/17170626g0u1.jpg','5月16日，辽宁省沈阳市，飞机坠毁现场。CFP供图',',1,',0);--end
 INSERT INTO tq_article_picture VALUES(110,2,'/member/portal/demo/201305/17170626bds3.jpg','5月16日，辽宁省沈阳市，飞机坠毁现场。CFP供图',',',0);--end
 INSERT INTO tq_article_picture VALUES(110,3,'/member/portal/demo/201305/17170626f67r.jpg','5月16日10时50分许，一架实施人工増雨小型飞机在沈阳浑南新区沈本大道千锦汇酒店附近坠毁并发生爆炸。图为5月16日，辽宁省沈阳市，飞机坠毁现场。CFP供图',',',0);--end
 INSERT INTO tq_article_picture VALUES(110,4,'/member/portal/demo/201305/17170627lfra.jpg','5月16日，辽宁省沈阳市，飞机坠毁现场。CFP供图',',',0);--end
 INSERT INTO tq_article_picture VALUES(110,5,'/member/portal/demo/201305/1717062794gi.jpg','5月16日，辽宁省沈阳市，飞机坠毁现场。CFP供图',',',0);--end
 INSERT INTO tq_article_picture VALUES(110,6,'/member/portal/demo/201305/17170627mpln.jpg','5月16日，辽宁省沈阳市，飞机坠毁现场。CFP供图',',',0);--end
 INSERT INTO tq_article_picture VALUES(110,7,'/member/portal/demo/201305/171706271obo.jpg','5月16日，辽宁省沈阳市，飞机坠毁现场。CFP供图',',',0);--end
 INSERT INTO tq_article_picture VALUES(110,8,'/member/portal/demo/201305/17170627uykz.jpg','5月16日，辽宁省沈阳市，飞机坠毁现场。CFP供图',',',0);--end
 INSERT INTO tq_article_picture VALUES(110,9,'/member/portal/demo/201305/17170627n3zg.jpg','5月16日，辽宁省沈阳市，飞机坠毁现场。CFP供图',',',0);--end
 INSERT INTO tq_article_picture VALUES(110,10,'/member/portal/demo/201305/171706280w1e.jpg','5月16日，辽宁省沈阳市，飞机坠毁现场。CFP供图',',',0);--end
 INSERT INTO tq_article_picture VALUES(111,0,'/member/portal/demo/201305/17232709tgi4.jpg','',',0,',1);--end
 INSERT INTO tq_article_picture VALUES(111,1,'/member/portal/demo/201305/1718362136p4.jpg','5月16日，重庆国博中心，数十名青年男女赤脚肘臂，手握纤绳表演船工拉纤生活，再现了当年船工风采。',',',0);--end
 INSERT INTO tq_article_picture VALUES(111,2,'/member/portal/demo/201305/171836218i33.jpg','5月16日，重庆，“女子纤夫”在表演船工拉纤生活。',',',0);--end
 INSERT INTO tq_article_picture VALUES(111,3,'/member/portal/demo/201305/171836223rcp.jpg','5月16日，重庆国博中心，数十名青年男女赤脚肘臂，手握纤绳表演船工拉纤生活，再现了当年船工风采。',',',0);--end
 INSERT INTO tq_article_picture VALUES(111,4,'/member/portal/demo/201305/17183622clfb.jpg','5月16日，重庆，“女子纤夫”在表演船工拉纤生活。',',1,',0);--end
 INSERT INTO tq_article_picture VALUES(111,5,'/member/portal/demo/201305/17183622e5sx.jpg','5月16日，重庆国博中心，数十名青年男女赤脚肘臂，手握纤绳表演船工拉纤生活，再现了当年船工风采。',',',0);--end
 INSERT INTO tq_article_picture VALUES(111,6,'/member/portal/demo/201305/17183622q2w7.jpg','5月16日，重庆，“女子纤夫”在表演船工拉纤生活。',',',0);--end
 INSERT INTO tq_article_picture VALUES(111,7,'/member/portal/demo/201305/17183622hfpw.jpg','5月16日，重庆国博中心，数十名青年男女赤脚肘臂，手握纤绳表演船工拉纤生活，再现了当年船工风采。',',',0);--end
 INSERT INTO tq_article_picture VALUES(112,0,'/member/portal/demo/201305/17234720noz0.jpg','',',0,',1);--end
 INSERT INTO tq_article_picture VALUES(112,1,'/member/portal/demo/201305/172347280hrg.jpg','近日，一则“丽江一酒吧摆价值千万‘艳遇佛’引游客疯狂抚摸”的网帖炒热了丽江古城胭雨酒吧里的一尊佛像，慕名而来的游客摸佛、拜佛，希望以此获得桃花运。然而，也有网友吐槽酒吧行为“亵渎神灵”。',',',0);--end
 INSERT INTO tq_article_picture VALUES(112,2,'/member/portal/demo/201305/17234728catl.jpg','软的灯光，悠扬的吉他弹唱，歌手背后便是一尊高达4米多的巨大欢喜佛。5月12日晚，在丽江古城新华街文翠路段胭雨酒吧内，歌手与欢喜佛同台“演出”，游客更是忙着与佛像“互动”，摸佛、拜佛。网友见图，批评这些行为“亵渎神灵”。',',',0);--end
 INSERT INTO tq_article_picture VALUES(112,3,'/member/portal/demo/201305/17234728qcvq.jpg','“酒吧请来佛像放在舞台上本就让人疑惑，更何况欢喜佛的造型让人想入非非。”市民李女士看了酒吧内的欢喜佛后有些难为情。有网友吐槽“很黄，很暴力”，有网友质疑酒吧“炒作可以，但不要亵渎佛。”',',',0);--end
 INSERT INTO tq_article_picture VALUES(112,4,'/member/portal/demo/201305/17234728zpom.jpg','酒吧负责人蔺先生解释，不能用世俗的眼光看待佛像，其实佛像抱着的这个女人代表智慧。他介绍，因丽江艳遇出名，所以酒吧以艳遇的谐音起名“胭雨”，“我们希望客人有美好的爱情故事，所以酒吧才‘请’来了欢喜佛，还种了姻缘树”。',',1,',0);--end
 INSERT INTO tq_article_picture VALUES(113,0,'/member/portal/demo/201305/17235141le4n.jpg','',',0,',1);--end
 INSERT INTO tq_article_picture VALUES(113,1,'/member/portal/demo/201305/172353406stc.jpg','自14日开始的南航新疆招乘还在持续中，南航2013春季乘务（安全）员招募会的200个岗位，吸引了近两千名应聘者参与角逐。虽然此次只有80个空姐招聘名额，另外120个招聘名额留给了“空哥”，但还是吸引了超过一千名美女前来应聘。',',',0);--end
 INSERT INTO tq_article_picture VALUES(113,2,'/member/portal/demo/201305/17235340uu0j.jpg','自14日开始的南航新疆招乘还在持续中，南航2013春季乘务（安全）员招募会的200个岗位，吸引了近两千名应聘者参与角逐。虽然此次只有80个空姐招聘名额，另外120个招聘名额留给了“空哥”，但还是吸引了超过一千名美女前来应聘。',',',0);--end
 INSERT INTO tq_article_picture VALUES(113,3,'/member/portal/demo/201305/172353406wga.jpg','自14日开始的南航新疆招乘还在持续中，南航2013春季乘务（安全）员招募会的200个岗位，吸引了近两千名应聘者参与角逐。虽然此次只有80个空姐招聘名额，另外120个招聘名额留给了“空哥”，但还是吸引了超过一千名美女前来应聘。',',',0);--end
 INSERT INTO tq_article_picture VALUES(113,4,'/member/portal/demo/201305/17235341bvr1.jpg','自14日开始的南航新疆招乘还在持续中，南航2013春季乘务（安全）员招募会的200个岗位，吸引了近两千名应聘者参与角逐。虽然此次只有80个空姐招聘名额，另外120个招聘名额留给了“空哥”，但还是吸引了超过一千名美女前来应聘。',',',0);--end
 INSERT INTO tq_article_picture VALUES(113,5,'/member/portal/demo/201305/17235341j4u3.jpg','自14日开始的南航新疆招乘还在持续中，南航2013春季乘务（安全）员招募会的200个岗位，吸引了近两千名应聘者参与角逐。虽然此次只有80个空姐招聘名额，另外120个招聘名额留给了“空哥”，但还是吸引了超过一千名美女前来应聘。',',',0);--end
 INSERT INTO tq_article_picture VALUES(113,6,'/member/upload/pms/201405/25150413x7as.jpg','自14日开始的南航新疆招乘还在持续中，南航2013春季乘务（安全）员招募会的200个岗位，吸引了近两千名应聘者参与角逐。虽然此次只有80个空姐招聘名额，另外120个招聘名额留给了“空哥”，但还是吸引了超过一千名美女前来应聘。',',1,',0);--end
 INSERT INTO tq_article_picture VALUES(114,0,'/member/portal/demo/201305/17235844v8sy.jpg','',',0,',1);--end
 INSERT INTO tq_article_picture VALUES(114,1,'/member/portal/demo/201305/17235854eyqg.jpg','据韩媒报道，万万没料到，少女时代第一美的林允儿（润娥），长相也会败在别的女人的手里，在韩国专业机构的调查中，被媒体夸为最强美貌的润娥惜败“国民初恋”秀智。',',',0);--end
 INSERT INTO tq_article_picture VALUES(114,2,'/member/portal/demo/201305/172358552gq5.jpg','据韩媒报道，万万没料到，少女时代第一美的林允儿（润娥），长相也会败在别的女人的手里，在韩国专业机构的调查中，被媒体夸为最强美貌的润娥惜败“国民初恋”秀智。',',1,',0);--end
 INSERT INTO tq_article_picture VALUES(114,3,'/member/portal/demo/201305/17235855lsuo.jpg','据韩媒报道，万万没料到，少女时代第一美的林允儿（润娥），长相也会败在别的女人的手里，在韩国专业机构的调查中，被媒体夸为最强美貌的润娥惜败“国民初恋”秀智。',',',0);--end
 INSERT INTO tq_article_picture VALUES(114,4,'/member/portal/demo/201305/17235855blhu.jpg','据韩媒报道，万万没料到，少女时代第一美的林允儿（润娥），长相也会败在别的女人的手里，在韩国专业机构的调查中，被媒体夸为最强美貌的润娥惜败“国民初恋”秀智。',',',0);--end
 INSERT INTO tq_article_picture VALUES(114,5,'/member/portal/demo/201305/17235855rbp6.jpg','据韩媒报道，万万没料到，少女时代第一美的林允儿（润娥），长相也会败在别的女人的手里，在韩国专业机构的调查中，被媒体夸为最强美貌的润娥惜败“国民初恋”秀智。',',',0);--end
 INSERT INTO tq_article_picture VALUES(115,0,'/member/portal/demo/201305/18000427cpoi.jpg','',',0,',1);--end
 INSERT INTO tq_article_picture VALUES(115,1,'/member/portal/demo/201305/18000443ajkr.jpg','1、泡泡糖　　泡泡糖中的天然橡胶虽无毒，但制泡泡糖所用的一级白片胶是加入了具有一定毒性的硫化促进剂、防老剂等添加剂，多吃会对身体不利。',',',0);--end
 INSERT INTO tq_article_picture VALUES(115,2,'/member/portal/demo/201305/18000444e8db.jpg','2、加糖鲜榨橙汁　　加了糖的橙汁比汽水的热量还要高，糖分也比汽水多。推荐吃原水果。',',',0);--end
 INSERT INTO tq_article_picture VALUES(115,3,'/member/portal/demo/201305/18000444vgr4.jpg','3、松花蛋　　松花蛋含有一定量的铅，常食会引起人体铅中毒。铅中毒时的表现为失眠、贫血、好动、智力减退等。',',',0);--end
 INSERT INTO tq_article_picture VALUES(115,4,'/member/portal/demo/201305/18000444bfay.jpg','4、臭豆腐　　臭豆腐在发酵过程中极易被微生物污染，它还含有大量的挥发性盐基氮和硫化氢等。它还是分解蛋白质的腐败物质，对人体有害。',',',0);--end
 INSERT INTO tq_article_picture VALUES(115,5,'/member/portal/demo/201305/18000444edvv.jpg','5、葵花子　　葵花子中含有不饱和脂肪酸，多吃会消耗大量的胆碱，使体内脂肪代谢发生障碍，并使大量脂肪积聚于肝脏，会严重影响肝细胞的功能。',',',0);--end
 INSERT INTO tq_article_picture VALUES(115,6,'/member/upload/pms/201405/25131602jii2.jpg','5、葵花子　　葵花子中含有不饱和脂肪酸，多吃会消耗大量的胆碱，使体内脂肪代谢发生障碍，并使大量脂肪积聚于肝脏，会严重影响肝细胞的功能。',',1,',0);--end
 INSERT INTO tq_article_picture VALUES(116,0,'/member/portal/demo/201305/180010245rkb.jpg','',',0,',1);--end
 INSERT INTO tq_article_picture VALUES(116,1,'/member/portal/demo/201305/18001030kdj2.jpg','一辆价值260万的玛莎拉蒂总裁停在会展中心广场，四名男子手持铁锤砸向这辆玛莎拉蒂。车主朋友表示，因福日集团下属欧利行车行修车时旧件当成新件来用，数月没有解决问题。',',1,',0);--end
 INSERT INTO tq_article_picture VALUES(116,2,'/member/portal/demo/201305/18001030ey36.jpg','一辆价值260万的玛莎拉蒂总裁停在会展中心广场，四名男子手持铁锤砸向这辆玛莎拉蒂。车主朋友表示，因福日集团下属欧利行车行修车时旧件当成新件来用，数月没有解决问题。',',',0);--end
 INSERT INTO tq_article_picture VALUES(116,3,'/member/portal/demo/201305/18001030acta.jpg','一辆价值260万的玛莎拉蒂总裁停在会展中心广场，四名男子手持铁锤砸向这辆玛莎拉蒂。车主朋友表示，因福日集团下属欧利行车行修车时旧件当成新件来用，数月没有解决问题。',',',0);--end
 INSERT INTO tq_article_picture VALUES(116,4,'/member/portal/demo/201305/1800103175v5.jpg','一辆价值260万的玛莎拉蒂总裁停在会展中心广场，四名男子手持铁锤砸向这辆玛莎拉蒂。车主朋友表示，因福日集团下属欧利行车行修车时旧件当成新件来用，数月没有解决问题。',',',0);--end
 INSERT INTO tq_article_picture VALUES(116,5,'/member/portal/demo/201305/180010310qfu.jpg','一辆价值260万的玛莎拉蒂总裁停在会展中心广场，四名男子手持铁锤砸向这辆玛莎拉蒂。车主朋友表示，因福日集团下属欧利行车行修车时旧件当成新件来用，数月没有解决问题。',',',0);--end
 INSERT INTO tq_article_picture VALUES(123,0,'/member/upload/pms/201405/2512435356mu.jpg','',',0,3,',1);--end
 INSERT INTO tq_article_picture VALUES(132,0,'/member/upload/demo/201305/21185832vfal.jpg','',',',0);--end
 INSERT INTO tq_article_picture VALUES(132,1,'/member/upload/demo/201305/21185832m1rt.jpg','',',',0);--end
 INSERT INTO tq_article_picture VALUES(132,2,'/member/upload/demo/201305/21185903i4ts.jpg','',',',0);--end
 INSERT INTO tq_article_picture VALUES(132,3,'/member/upload/demo/201305/21185915x6yi.jpg','',',',0);--end
 INSERT INTO tq_article_picture VALUES(132,4,'/member/upload/demo/201305/21185915v09d.jpg','',',',0);--end
 INSERT INTO tq_article_picture VALUES(132,5,'/member/upload/demo/201305/211859248k7y.gif','',',',0);--end
 INSERT INTO tq_article_picture VALUES(132,6,'/member/upload/demo/201305/21185944kn1h.jpg','',',',0);--end
 INSERT INTO tq_article_picture VALUES(187,0,'/member/upload/pms/201405/25124300ipxi.jpg','',',0,3,',1);--end
 INSERT INTO tq_article_picture VALUES(188,0,'/member/upload/pms/201405/25125155ezv9.jpg','',',0,2,',1);--end
 INSERT INTO tq_article_picture VALUES(189,0,'/member/upload/pms/201405/25125108ww40.jpg','',',2,0,',1);--end
 INSERT INTO tq_article_picture VALUES(190,0,'/member/upload/pms/201405/251254510546.jpg','',',2,0,',1);--end
 INSERT INTO tq_article_picture VALUES(191,0,'/member/upload/pms/201405/25125645bijq.jpg','',',2,0,',1);--end
 INSERT INTO tq_article_picture VALUES(192,0,'/member/upload/pms/201405/251256232iym.jpg','',',2,0,',1);--end
 INSERT INTO tq_article_picture VALUES(193,0,'/member/upload/pms/201405/25130037f00m.png','',',0,2,',1);--end
 INSERT INTO tq_article_picture VALUES(194,0,'/member/upload/pms/201405/25130022s8wx.png','',',0,2,',1);--end
 INSERT INTO tq_article_picture VALUES(195,0,'/member/upload/pms/201405/251304360oua.jpg','',',0,2,',1);--end
 INSERT INTO tq_article_picture VALUES(196,0,'/member/upload/pms/201405/251304200elq.jpg','',',0,2,',1);--end
 INSERT INTO tq_article_picture VALUES(197,0,'/member/upload/pms/201405/25130546hrbu.jpg','',',0,2,',1);--end
 INSERT INTO tq_article_picture VALUES(198,0,'/member/upload/pms/201405/25130532ocsp.jpg','',',0,2,',1);--end
 INSERT INTO tq_article_picture VALUES(210,0,'/member/upload/pms/201405/25130615q7f0.jpg','',',0,2,',1);--end
 INSERT INTO tq_article_picture VALUES(211,0,'/member/upload/pms/201405/25151124ozoi.jpg','',',0,',1);--end
 INSERT INTO tq_article_picture VALUES(211,1,'/member/upload/pms/201405/25151108v9j0.jpg','2014年5月25日，中国军方某型歼击机、某型运输机、某型直升机，首次成功起降中原某高速公路飞机跑道。该高速公路飞机跑道按一级甲类标准建设，可满足三代战机和中小型运输机在战时或紧急情况下应急起降',',',0);--end
 INSERT INTO tq_article_picture VALUES(211,2,'/member/upload/pms/201405/25151108j7eg.jpg','2014年5月25日，中国军方某型歼击机、某型运输机、某型直升机，首次成功起降中原某高速公路飞机跑道。该高速公路飞机跑道按一级甲类标准建设，可满足三代战机和中小型运输机在战时或紧急情况下应急起降',',',0);--end
 INSERT INTO tq_article_picture VALUES(211,3,'/member/upload/pms/201405/251511084jzo.jpg','2014年5月25日，中国军方某型歼击机、某型运输机、某型直升机，首次成功起降中原某高速公路飞机跑道。该高速公路飞机跑道按一级甲类标准建设，可满足三代战机和中小型运输机在战时或紧急情况下应急起降',',',0);--end
 INSERT INTO tq_article_picture VALUES(211,4,'/member/upload/pms/201405/251511088bt9.jpg','2014年5月25日，中国军方某型歼击机、某型运输机、某型直升机，首次成功起降中原某高速公路飞机跑道。该高速公路飞机跑道按一级甲类标准建设，可满足三代战机和中小型运输机在战时或紧急情况下应急起降',',',0);--end
 INSERT INTO tq_article_picture VALUES(211,5,'/member/upload/pms/201405/25151108mk5v.jpg','2014年5月25日，中国军方某型歼击机、某型运输机、某型直升机，首次成功起降中原某高速公路飞机跑道。该高速公路飞机跑道按一级甲类标准建设，可满足三代战机和中小型运输机在战时或紧急情况下应急起降',',',0);--end
 INSERT INTO tq_article_picture VALUES(211,6,'/member/upload/pms/201405/251511087dzw.jpg','2014年5月25日，中国军方某型歼击机、某型运输机、某型直升机，首次成功起降中原某高速公路飞机跑道。该高速公路飞机跑道按一级甲类标准建设，可满足三代战机和中小型运输机在战时或紧急情况下应急起降',',',0);--end
 INSERT INTO tq_article_picture VALUES(211,7,'/member/upload/pms/201405/25151109tplh.jpg','2014年5月25日，中国军方某型歼击机、某型运输机、某型直升机，首次成功起降中原某高速公路飞机跑道。该高速公路飞机跑道按一级甲类标准建设，可满足三代战机和中小型运输机在战时或紧急情况下应急起降',',',0);--end
 INSERT INTO tq_article_picture VALUES(211,8,'/member/upload/pms/201405/25151127lm0n.jpg','',',1,',0);--end
DROP TABLE IF EXISTS tq_article_sign;--end
CREATE TABLE tq_article_sign
(
   sign_id           INT(10) NOT NULL AUTO_INCREMENT,
   article_id           INT(10) NOT NULL COMMENT '签收文章',
   admin_id           INT(10) NOT NULL COMMENT '签收用户',
   depart_id           INT(10) NOT NULL COMMENT '签收部门',
   sign_time           DATETIME NOT NULL COMMENT '签收时间',
   PRIMARY KEY(sign_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_article_txt;--end
CREATE TABLE tq_article_txt
(
   article_id           INT(10) NOT NULL,
   txt           LONGTEXT COMMENT '文章内容',
   PRIMARY KEY(article_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_article_txt VALUES(3,'<p style=\"text-indent:2em;\">\r\n\t春雨绵绵时节，才不要因为天气而影响出游心情，更不要扰乱自己的搭配情绪，so，下雨天轻薄风衣一定要走起来。如果说风衣只为了抗风才穿那就大错特错了，阴雨天气一件优雅百变的风衣足以让你神采奕奕，春雨伴随着风衣装扮显得浪漫十足，初春用什么来做外衣？看来必选的单品就是风情万种的轻薄风衣啦。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<!-- 导语 end -->\r\n</p>\r\n<div>\r\n\t<p align=\"center\">\r\n\t\t<a href=\"/fashion/edittj/2013/0408783614.shtml\"><img title=\"小风衣大作为 不怕阴雨天没情绪\" alt=\"小风衣大作为 不怕阴雨天没情绪\" src=\"http://p1.yokacdn.com/pic/fashion/edittj/2013/U297P1T1D783613F9DT20130408144558.jpg\" /> </a> \r\n\t</p>\r\n<a href=\"/fashion/edittj/2013/0408783614.shtml\"> \r\n\t<div align=\"left\">\r\n\t\t&nbsp;\r\n\t</div>\r\n</a> \r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<div>\r\n\t</div>\r\n<br />\r\n\t<p align=\"center\">\r\n\t\t<a href=\"/fashion/edittj/2013/0408783614.shtml\"><img title=\"小风衣大作为 不怕阴雨天没情绪\" alt=\"小风衣大作为 不怕阴雨天没情绪\" src=\"http://p1.yokacdn.com/pic/fashion/edittj/2013/U297P1T1D783613F23DT20130408144558.jpg\" /> </a> \r\n\t</p>\r\n<a href=\"/fashion/edittj/2013/0408783614.shtml\"> \r\n\t<div align=\"left\">\r\n\t\t&nbsp;\r\n\t</div>\r\n</a> \r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<div>\r\n\t</div>\r\n<br />\r\n\t<p align=\"center\">\r\n\t\t<a href=\"/fashion/edittj/2013/0408783614.shtml\"><img title=\"小风衣大作为 不怕阴雨天没情绪\" alt=\"小风衣大作为 不怕阴雨天没情绪\" src=\"http://p1.yokacdn.com/pic/fashion/edittj/2013/U297P1T1D783613F25DT20130408144558.jpg\" /> </a> \r\n\t</p>\r\n<a href=\"/fashion/edittj/2013/0408783614.shtml\"> \r\n\t<div align=\"left\">\r\n\t\t&nbsp;\r\n\t</div>\r\n</a> \r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<div>\r\n\t</div>\r\n<br />\r\n\t<p>\r\n\t\t　　偏于运动型的轻薄冲锋衣显得休闲十足，无论是抵抗阴雨的微凉还是混搭的扮酷造型，这样的舒适风衣谁不爱？\r\n\t</p>\r\n<!--page_info=7_1_783613-->\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(4,'<p style=\"text-align:center;\">\r\n\t<strong></strong>\r\n</p>\r\n<strong> \r\n<p class=\"cover\" align=\"center\">\r\n\t<img style=\"width:360px;\" src=\"http://img01.taobaocdn.com/tps/i1/T1NJsTXXNbXXb1upjX.jpg\" width=\"360\" /> \r\n\t<div align=\"left\">\r\n\t\t&nbsp;\r\n\t</div>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<div class=\"cover\">\r\n\t\t<a class=\"J_share\" href=\"javascript:void(0);\"></a><span class=\"btn-tts\"></span>\r\n\t</div>\r\n</strong> \r\n\t<p>\r\n\t\t<strong>服装选购尺码大小如何看</strong> \r\n\t</p>\r\n\t<p>\r\n\t\t服装上符号的含义\r\n\t</p>\r\n\t<p>\r\n\t\t我们在购买服装时，稍留心一下，便可发现每件衣服的上部均有标记或符号，那么，这些符合各表示什么含义呢？\r\n\t</p>\r\n\t<p>\r\n\t\t1、\"L\"表示大号，\"m\"表示中号，\"s\"表示小号，\"xi\"表示特大号，还有\"xxi\"也表示特大号。\r\n\t</p>\r\n\t<p>\r\n\t\t2、\"1\"表示适合身高1.5米的人穿用;\"2\"适合身高1.55米的人的穿用,以此类推,\"3\"代表1.6米，\"4\"代表1.65米,\"5\"代表1.7米,\"6\"代表1.7米,\"7\"代表1.8米,\"8\"代表决权.85米。\r\n\t</p>\r\n\t<p>\r\n\t\t3、\"Y\"表示胸围与腰围相差16厘米，\"YA\"表示相差14厘米、\"A\"表明相差12厘米、\"AB\"表明相差10厘米，\"B\"表明相差8厘米、\"BE\"表明相差4厘米，\"E\"表明相差无几。<br />\r\n做牛仔裤的用布量\r\n\t</p>\r\n\t<p>\r\n\t\t<strong>衣服尺码对照表－－鲁宾汉尺码对照表</strong> \r\n\t</p>\r\n\t<p>\r\n\t\t1、上装尺码为：\r\n\t</p>\r\n\t<p>\r\n\t\t01码表示代码为：\"XXS\"&nbsp;&nbsp;&nbsp;&nbsp; 02码表示代码为：\"XS\"\r\n\t</p>\r\n\t<p>\r\n\t\t03码表示代码为：\"S\"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 04码表示代码为：\"M\"\r\n\t</p>\r\n\t<p>\r\n\t\t05码表示代码为：\"L\"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 06码表示代码为：\"XL\"\r\n\t</p>\r\n\t<p>\r\n\t\t07码表示代码为：\"XXL\"\r\n\t</p>\r\n\t<p>\r\n\t\t2、茄克装尺码为：\r\n\t</p>\r\n\t<p>\r\n\t\t70表示代码为：\"48\"（M）\r\n\t</p>\r\n\t<p>\r\n\t\t71表示代码为：\"50\"（L）\r\n\t</p>\r\n\t<p>\r\n\t\t72表示代码为：\"52\"（XL）\r\n\t</p>\r\n\t<p>\r\n\t\t73表示代码为：\"54\"（XXL）\r\n\t</p>\r\n\t<p>\r\n\t\t3、裤装尺码为：\r\n\t</p>\r\n\t<p>\r\n\t\t26代表腰围为：\"1.9尺\"&nbsp;&nbsp;&nbsp;&nbsp; 27代表腰围为：\"2.0尺\"\r\n\t</p>\r\n\t<p>\r\n\t\t28代表腰围为：\"2.1尺\"&nbsp;&nbsp;&nbsp;&nbsp; 29代表腰围为：\"2.2尺\"\r\n\t</p>\r\n\t<p>\r\n\t\t30代表腰围为：\"2.3尺\"&nbsp;&nbsp;&nbsp;&nbsp; 31代表腰围为：\"2.4尺\"\r\n\t</p>\r\n\t<p>\r\n\t\t32代表腰围为：\"2.5尺\"&nbsp;&nbsp;&nbsp;&nbsp; 33代表腰围为：\"2.6尺\"\r\n\t</p>\r\n\t<p>\r\n\t\t34代表腰围为：\"2.7尺\"&nbsp;&nbsp;&nbsp;&nbsp; 36代表腰围为：\"2.8尺\"\r\n\t</p>\r\n\t<p>\r\n\t\t38代表腰围为：\"2.9尺\"&nbsp;&nbsp;&nbsp;&nbsp; 40代表腰围为：\"3.0尺\"\r\n\t</p>\r\n\t<p>\r\n\t\t42代表腰围为：\"3.1尺\"&nbsp;&nbsp;&nbsp;&nbsp; 44代表腰围为：\"3.2尺\"\r\n\t</p>\r\n\t<p>\r\n\t\t50代表腰围为：\"3.4尺\"&nbsp;&nbsp;&nbsp;&nbsp; 52代表腰围为：\"3.5尺\"\r\n\t</p>\r\n\t<p>\r\n\t\t54代表腰围为：\"3.6尺\"\r\n\t</p>\r\n\t<p>\r\n\t\t4、西服装尺码为：\r\n\t</p>\r\n\t<p>\r\n\t\t80表示代码为：\"145\"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 81表示代码为：\"150\"\r\n\t</p>\r\n\t<p>\r\n\t\t82表示代码为：\"155\"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 83表示代码为：\"160\"\r\n\t</p>\r\n\t<p>\r\n\t\t84表示代码为：\"165\"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 85表示代码为：\"170\"\r\n\t</p>\r\n\t<p>\r\n\t\t86表示代码为：\"175\"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 87表示代码为：\"180\"\r\n\t</p>\r\n\t<p>\r\n\t\t88表示代码为：\"185\"\r\n\t</p>\r\n\t<p>\r\n\t\t5、衬衣装尺码为：\r\n\t</p>\r\n\t<p>\r\n\t\t60表示代码为：\"38\"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 61表示代码为：\"39\"\r\n\t</p>\r\n\t<p>\r\n\t\t62表示代码为：\"40\"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 63表示代码为：\"41\"\r\n\t</p>\r\n\t<p>\r\n\t\t64表示代码为：\"42\"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 65表示代码为：\"43\"\r\n\t</p>\r\n\t<p>\r\n\t\t66表示代码为：\"44\"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 67表示代码为：\"45\"\r\n\t</p>\r\n\t<p style=\"text-align:left;\">\r\n\t\t68表示代码为：\"46\"<span> </span><span></span>\r\n\t</p>\r\n\t<p style=\"text-align:center;\">\r\n\t\t<span></span> \r\n\t\t<p class=\"cover\" align=\"center\">\r\n\t\t\t<img style=\"width:360px;\" src=\"http://img01.taobaocdn.com/tps/i1/T1iAgTXg8aXXb1upjX.jpg\" width=\"360\" /> \r\n\t\t\t<div align=\"left\">\r\n\t\t\t\t&nbsp;\r\n\t\t\t</div>\r\n\t\t\t<p>\r\n\t\t\t\t<br />\r\n\t\t\t</p>\r\n\t\t\t<div class=\"cover\">\r\n\t\t\t\t<a class=\"J_share\" href=\"javascript:void(0);\"></a><span class=\"btn-tts\"></span>\r\n\t\t\t</div>\r\n<span></span> \r\n\t\t\t<p>\r\n\t\t\t\t<strong>怎样识别服装的\"号\"和\"型\"</strong> \r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t全国服装的统一号型所说的\"号\"，是指人身高的厘米数，它影响到的部位是衣长、袖长、裤长；所说的\"型\"，指的是人的体围厘米数，它影响的部位是腰围、臀围。一个人只能使用同一个\"号\"，而不能使用同一个\"型\"，因为上衣和裤子的\"型\"必须分开使用。例如你身高170厘米，基本胸围为88厘米，基本腰围为73厘米，那么就适合穿170－88号的上衣，170－73型的裤子。 <br />\r\n怎样识别国家统一服装号型\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t从1992年4月1日起，全国开始实施统一的GB1335－1991《服装号型》国家标准它是由纺织工业部制定、国家技术监督局批准发布的，为此，原来的GB1335－1981服装标准即行废止。<br />\r\n&nbsp;&nbsp;&nbsp; 新标准全套共有男子、女子、儿童3项独立标准，其中男子和女子两项属于强制执行标准，儿童作为推荐性标准。新标准是以身高、净体胸围、净体腰围以及腰落差作为号型命名的依据，对每一个号型列出了制作服装所必须的10个关键控制部位尺寸。号型表示方法为： <br />\r\n\"（号）／型、（体型代号）\"&nbsp;&nbsp; 其中：\"号\"表示身高，\"型\"表示净体胸围或净体腰围：\"体型代号\"表示胸围与腰围尺寸之差，以Y、A、B、C表示。例如：170／88A、175／96B等。 <br />\r\n服装新标准的实施，可使我国95％左右的消费者能买到适体服装。消费者只需记住自己的身高、胸围的体型，便可解决上、下装配套的问题。<br />\r\n服装尺码换算参照表\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t1、女 装 （外衣、裙装、恤衫、上装、套装）\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t标准尺码明细\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t中国 (cm)&nbsp;&nbsp; 160-165/84-86&nbsp;&nbsp; 165-170/88-90&nbsp;&nbsp; 167-172/92-96 168-173/98-102 170-176/106-110\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t国 际&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; XS&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; S&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; M&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; L&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; XL\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t美 国&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 4-6&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 8-10&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 12-14&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 16-18\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t欧 洲&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 34&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 34-36&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 38-40&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 42&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 44\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t男 装 （外衣、恤衫、套装）\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t标准尺码明细\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t中国 (cm)&nbsp;&nbsp; 165/88-90&nbsp;&nbsp;&nbsp; 170/96-98&nbsp;&nbsp;&nbsp; 175/108-110&nbsp;&nbsp;&nbsp; 180/118-122&nbsp;&nbsp;&nbsp; 185/126-130\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t国 际&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; S&nbsp;&nbsp;&nbsp;&nbsp; M&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; L&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; XL&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; XXL\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t2、男 装 （衬衫）\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t标准尺码明细\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t中国 (cm) 36-37 38-39 40-42&nbsp;&nbsp; 43-44&nbsp;&nbsp; 45-47\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t国 际&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; S&nbsp;&nbsp;&nbsp; M&nbsp;&nbsp;&nbsp; L&nbsp;&nbsp;&nbsp; XL&nbsp;&nbsp; XXL\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t3、男 装 （裤装）\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t标 准尺码明细\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t尺 码&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 42&nbsp;&nbsp;&nbsp; 44&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 46&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 48&nbsp;&nbsp;&nbsp;&nbsp; 50\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t腰 围&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 68-72 cm&nbsp;&nbsp;&nbsp; 71-76 cm&nbsp;&nbsp;&nbsp; 75-80 cm&nbsp;&nbsp;&nbsp; 79-84 cm&nbsp;&nbsp; 83-88cm\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t裤 度&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 99 cm&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 101.5 cm&nbsp;&nbsp;&nbsp;&nbsp; 104 cm&nbsp;&nbsp;&nbsp;&nbsp; 106.5 cm&nbsp;&nbsp;&nbsp; 109 cm\r\n\t\t\t</p>');--end
 INSERT INTO tq_article_txt VALUES(5,'<p style=\"text-indent:2em;\">\r\n\t可以根据鞋子的大小任意调节，可以做到随心所欲，桐木家具特点不变形，环保大方耐潮湿，高贵典雅～\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img src=\"http://img4.tbcdn.cn/tfscom/T1hFlKXEBaXXXXXXXX_620x10000.jpg\" /> \r\n\t<div align=\"left\">\r\n\t\t&nbsp;\r\n\t</div>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t<br />\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t宜家组合防尘收纳鞋架创意鞋柜，超强的收纳功能让你藏下更多。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\" align=\"center\">\r\n\t\t<img src=\"http://img4.tbcdn.cn/tfscom/T1LUdJXxdbXXXXXXXX_620x10000.jpg\" /> \r\n\t\t<div align=\"left\">\r\n\t\t\t&nbsp;\r\n\t\t</div>\r\n\t\t<p>\r\n\t\t\t<br />\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t<br />\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t九款不同的造型和色彩设计让你随时都能够选中自己喜欢的一款。\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\" align=\"center\">\r\n\t\t\t<img src=\"http://img4.tbcdn.cn/tfscom/T15pVKXuxbXXXXXXXX_620x10000.jpg\" /> \r\n\t\t\t<div align=\"left\">\r\n\t\t\t\t&nbsp;\r\n\t\t\t</div>\r\n\t\t\t<p>\r\n\t\t\t\t<br />\r\n\t\t\t</p>\r\n\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t<br />\r\n\t\t\t</p>\r\n\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t宜家创意进门防尘鞋柜，超薄客厅玄关塑料大鞋柜，可以分开来放，收纳也是绝对的赞啊。\r\n\t\t\t</p>\r\n\t\t\t<p style=\"text-indent:2em;\" align=\"center\">\r\n\t\t\t\t<img src=\"http://img2.tbcdn.cn/tfscom/T1VC0JXzNbXXXXXXXX_620x10000.jpg\" /> \r\n\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t&nbsp;\r\n\t\t\t\t</div>\r\n\t\t\t\t<p>\r\n\t\t\t\t\t<br />\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t<br />\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t主形的支撑设计，撑开鞋面，透气性良好，鞋子这样就不会产生异味，收纳也是很方便的。\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\" align=\"center\">\r\n\t\t\t\t\t<img src=\"http://img1.tbcdn.cn/tfscom/T1EmhJXtFbXXXXXXXX_620x10000.jpg\" /> \r\n\t\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t\t&nbsp;\r\n\t\t\t\t\t</div>\r\n\t\t\t\t\t<p>\r\n\t\t\t\t\t\t<br />\r\n\t\t\t\t\t</p>\r\n\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t<br />\r\n\t\t\t\t\t</p>\r\n\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t总感觉家里的鞋柜是越来越小，空间怎么都不够用，面对拥挤的鞋柜总让人头疼不已，此款鞋架，人性化设计，将鞋子的收纳立体化，上下两层，充分利用鞋架、鞋柜内空间，可以放下便宜更多的鞋子，而且也不会让鞋子被掠夺变形、变脏\r\n\t\t\t\t\t</p>\r\n\t\t\t\t\t<p align=\"center\">\r\n\t\t\t\t\t\t<img src=\"http://img1.tbcdn.cn/tfscom/T19atKXAVaXXXXXXXX_620x10000.jpg\" /> \r\n\t\t\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t\t\t&nbsp;\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t<p>\r\n\t\t\t\t\t\t\t<br />\r\n\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t<p>\r\n\t\t\t\t\t\t\t<br />\r\n\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t<br />\r\n\t\t\t\t\t\t</p>');--end
 INSERT INTO tq_article_txt VALUES(6,'<p style=\"text-indent:2em;\">\r\n\t北欧风格的客厅装修，小清新最爱简约。一些比较有北欧风格的客厅，加上一些开放式的饭厅等，简洁大方，是他们风格的一向作风，很喜欢，可以作为现代家居装修时参考运用。\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img src=\"http://img3.tbcdn.cn/tfscom/T1USnvXcXpXXXXXXXX_620x10000.jpg\" /> \r\n\t<div align=\"left\">\r\n\t\t&nbsp;\r\n\t</div>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t<br />\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t对于时尚现代的年轻人来说，简洁实用的榻榻米是最近几年比较受欢迎的装修新宠了，除了能满足日常休闲储物的功能，每当有客人到来时它还可以是一张宽大舒适的床哦!\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t一、粉蓝沙发 不拥挤的客厅\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\" align=\"center\">\r\n\t\t<img src=\"http://img3.tbcdn.cn/tfscom/T1d5HwXXljXXXXXXXX_620x10000.jpg\" /> \r\n\t\t<div align=\"left\">\r\n\t\t\t&nbsp;\r\n\t\t</div>\r\n\t\t<p>\r\n\t\t\t<br />\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t<br />\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t北欧风格设计貌似不经意的搭配之下，一切又如浑然天成般光彩夺目。任何一个空间，总有一个视觉中心，而这个中心的主导者就是色彩。\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\" align=\"center\">\r\n\t\t\t<img src=\"http://img4.tbcdn.cn/tfscom/T1NuzwXc4kXXXXXXXX_620x10000.jpg\" /> \r\n\t\t\t<div align=\"left\">\r\n\t\t\t\t&nbsp;\r\n\t\t\t</div>\r\n\t\t\t<p>\r\n\t\t\t\t<br />\r\n\t\t\t</p>\r\n\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t<br />\r\n\t\t\t</p>\r\n\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t同一类色阶的深浅搭配，没有太多的家具，仅具备了生活的必须，然而每一处精心设计的绿意，占据了室内的最佳角度，让生活充满了无限活力。\r\n\t\t\t</p>\r\n\t\t\t<p style=\"text-indent:2em;\" align=\"center\">\r\n\t\t\t\t<img src=\"http://img4.tbcdn.cn/tfscom/T1qfPvXoVjXXXXXXXX_620x10000.jpg\" /> \r\n\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t&nbsp;\r\n\t\t\t\t</div>\r\n\t\t\t\t<p>\r\n\t\t\t\t\t<br />\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t<br />\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t整个客厅的采光很好，让人的心情也随之阳光起来。色彩的运用使客厅和过道浑然天成。简单的布置，沙发，茶几，电视满足了最基本的闲暇娱乐时光。电视柜上的布帘，随意摆放的靠垫，都选择了大花型图案，顿时打破了空间的单一感。\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\" align=\"center\">\r\n\t\t\t\t\t<img src=\"http://img4.tbcdn.cn/tfscom/T1ydvxXbReXXXXXXXX_620x10000.jpg\" /> \r\n\t\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t\t&nbsp;\r\n\t\t\t\t\t</div>\r\n\t\t\t\t\t<p>\r\n\t\t\t\t\t\t<br />\r\n\t\t\t\t\t</p>\r\n\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t<br />\r\n\t\t\t\t\t</p>\r\n\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t整套房间的布置随处可见木质装饰品和绿色植物，仿佛嗅到了春天泥土的芳香。另外，墙面上一些孩童照片，以及墙面上生机勃勃的手绘绿色藤蔓植物，充满了柔美之感。\r\n\t\t\t\t\t</p>\r\n\t\t\t\t\t<p style=\"text-indent:2em;\" align=\"center\">\r\n\t\t\t\t\t\t<img src=\"http://img4.tbcdn.cn/tfscom/T1upbxXnXfXXXXXXXX_620x10000.jpg\" /> \r\n\t\t\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t\t\t&nbsp;\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t<p>\r\n\t\t\t\t\t\t\t<br />\r\n\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t<br />\r\n\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t博大的白，无任何倾向性的色，是北欧简约风中最常见的颜色。用这样简单的中性色作为空间主调，即使家居整体没有艳丽色彩的点缀，然而加入了些许闪亮的饰品，也能打造出绝佳的品质。\r\n\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t<p style=\"text-indent:2em;\" align=\"center\">\r\n\t\t\t\t\t\t\t<img src=\"http://img2.tbcdn.cn/tfscom/T17o_wXgJgXXXXXXXX_620x10000.jpg\" /> \r\n\t\t\t\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t\t\t\t&nbsp;\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t<p>\r\n\t\t\t\t\t\t\t\t<br />\r\n\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t\t<br />\r\n\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t\t客厅一角，即被做成餐厅，墙面延伸了客厅背景墙的藤蔓花纹;略带欧式风格装饰的四方桌为餐桌，用红砖砌成的隔断墙面将空间完美的分割开来。\r\n\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t<p style=\"text-indent:2em;\" align=\"center\">\r\n\t\t\t\t\t\t\t\t<img src=\"http://img1.tbcdn.cn/tfscom/T1dwPwXoliXXXXXXXX_620x10000.jpg\" /> \r\n\t\t\t\t\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t\t\t\t\t&nbsp;\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t\t<p>\r\n\t\t\t\t\t\t\t\t\t<br />\r\n\t\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t\t\t<br />\r\n\t\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t\t\t简约的白色餐桌上点缀着略带传统感觉的餐巾和茶具，体现出主人不一样的生活品味。\r\n\t\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t\t\t<img src=\"http://img1.tbcdn.cn/tfscom/T1hpvwXatnXXXXXXXX_620x10000.jpg\" /> \r\n\t\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t\t\t开放式的厨房设计让空间感觉更宽阔，在做菜在同时还能和家人一起交流聊天让原本单调的厨房顿时也趣味盎然。\r\n\t\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t\t\t<img src=\"http://img2.tbcdn.cn/tfscom/T1c6vwXitiXXXXXXXX_620x10000.jpg\" /> \r\n\t\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t\t\t在靠近阳台的走廊小道，主人精心挑选了一款简约镂花的隔断，让空间的层次更感丰富精致!仔细看地面的仿古地砖也别具一格的印着朵朵旺开的玫瑰。\r\n\t\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t\t\t<img src=\"http://img3.tbcdn.cn/tfscom/T1WR6wXcphXXXXXXXX_620x10000.jpg\" /> \r\n\t\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t\t\t这个角落是女主人的最爱，这里有她最爱的书籍杂志和毛茸茸的公仔们，每当工作忙碌了一天之后这里就是最好的放松之地了。\r\n\t\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t\t\t<img src=\"http://img3.tbcdn.cn/tfscom/T1WR6wXcphXXXXXXXX_620x10000.jpg\" /> \r\n\t\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t\t\t这个角落是女主人的最爱，这里有她最爱的书籍杂志和毛茸茸的公仔们，每当工作忙碌了一天之后这里就是最好的放松之地了。\r\n\t\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t\t\t<img src=\"http://img2.tbcdn.cn/tfscom/T1j8DxXjhXXXXXXXXX_620x10000.jpg\" /> \r\n\t\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t\t\t冬日的午后，来这里小憩，享受玻璃窗洒下的大片阳光，温暖宜人，一本好书、一杯好茶、一曲好音乐便是半天。\r\n\t\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t\t\t<br />\r\n\t\t\t\t\t\t\t\t</p>');--end
 INSERT INTO tq_article_txt VALUES(7,'<p style=\"text-indent:2em;\">\r\n\t全国目前H7N9禽流感病例数目一度增长，人们对禽流感的恐慌造成的社会危害远远大于疾病本身，病毒来袭，我们唯有增强抵抗力，才能防止病毒的入侵。接下来为大家介绍5款简单易做的食疗方，既可以增强抵抗力预防感冒，又可以清热祛火，很适合干燥的春季。关爱家人，从饮食做起，有个健康的好身体，才可以更好的工作和生活。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t一、冰糖雪梨\r\n</p>\r\n<p align=\"center\">\r\n\t<img width=\"328\" height=\"300\" class=\"conimg\" alt=\"H7N9来袭 5款食疗方关爱家人\" src=\"http://images.meishij.net/p/20130410/32f9508d5a3c0afc81d8c21ce656dc7f.jpg\" 5款食疗方关爱家人?=\"\">\r\n\t<div align=\"left\">\r\n\t\t&nbsp;\r\n\t</div>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t原料：雪梨 4个、冰糖 100克\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t步骤：\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t1、把雪梨去皮切块，把去皮的梨块和冰糖放锅内，加清水(水的量依据自己的喜好)\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t2、开火煮，大火煮开后转小火继续煮20分钟左右即可\r\n\t</p>\r\n\t<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t二、南瓜绿豆汤\r\n\t</p>\r\n\t<p align=\"center\">\r\n\t\t<img width=\"328\" height=\"300\" class=\"conimg\" alt=\"H7N9来袭 5款食疗方关爱家人\" src=\"http://images.meishij.net/p/20130410/a8ddee4d75fe807b554f1d2c580c013d.jpg\" 5款食疗方关爱家人?=\"\">\r\n\t\t<div align=\"left\">\r\n\t\t\t&nbsp;\r\n\t\t</div>\r\n\t\t<p>\r\n\t\t\t<br />\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t原料：绿豆2小把、南瓜300克\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t步骤：\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t1、绿豆洗净，用水泡半个小时(泡后煮出来的绿豆更嫩滑，节约煮的时间)\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t2、锅中加入适量水，接通电源，绿豆沥干水分，倒入锅内\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t3、南瓜削皮，去瓤，洗净，切成2厘米左右的南瓜块\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t4、切完南瓜，绿豆也煮差不多了，绿豆煮到开花\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t5、倒入切好的南瓜块，中火开始煮，煮到南瓜变软即可(变软后南瓜会浮上来)\r\n\t\t</p>\r\n\t\t<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n\t\t<p>\r\n\t\t\t<br />\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t三、木瓜玉米甜汤\r\n\t\t</p>\r\n\t\t<p align=\"center\">\r\n\t\t\t<img width=\"328\" height=\"300\" class=\"conimg\" alt=\"H7N9来袭 5款食疗方关爱家人\" src=\"http://images.meishij.net/p/20130410/aaade4bb1e57ae557e072064952d8636.jpg\" 5款食疗方关爱家人?=\"\">\r\n\t\t\t<div align=\"left\">\r\n\t\t\t\t&nbsp;\r\n\t\t\t</div>\r\n\t\t\t<p>\r\n\t\t\t\t<br />\r\n\t\t\t</p>\r\n\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t主料：木瓜 500克、甜玉米 2个\r\n\t\t\t</p>\r\n\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t辅料：黄豆 50克\r\n\t\t\t</p>\r\n\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t调料：冰糖 30克、水 2000克、蜂蜜 4汤勺\r\n\t\t\t</p>\r\n\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t步骤：\r\n\t\t\t</p>\r\n\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t1、大豆洗净，泡水12小时备用;木瓜去皮去瓤，切块备用，玉米洗净备用\r\n\t\t\t</p>\r\n\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t2、以上材料一起放入锅中，大火煮滚后维持火力10分钟。转文火，加冰糖，煲40~50分钟;温度降到80度左右调入蜂蜜就可以吃\r\n\t\t\t</p>\r\n\t\t\t<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n\t\t\t<p>\r\n\t\t\t\t<br />\r\n\t\t\t</p>\r\n\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t四、大骨冬瓜汤\r\n\t\t\t</p>\r\n\t\t\t<p align=\"center\">\r\n\t\t\t\t<img width=\"328\" height=\"300\" class=\"conimg\" alt=\"H7N9来袭 5款食疗方关爱家人\" src=\"http://images.meishij.net/p/20130410/8c52cbbd97abc3fdeb5eb30029d0e432.jpg\" 5款食疗方关爱家人?=\"\">\r\n\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t&nbsp;\r\n\t\t\t\t</div>\r\n\t\t\t\t<p>\r\n\t\t\t\t\t<br />\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t主料：猪大骨头 3大块、冬瓜 1/4个\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t调料：小葱 1根、姜 3片、食盐 3克、料酒 3克\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t步骤：\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t1、腿骨让卖家给剁开，洗净控干，冬瓜去皮切块，小葱挽结，姜切片，腿骨放入锅中大火煮开，吐尽血水后捞出洗干净\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t2、砂锅中一次性放足水，放入筒骨、姜片、小葱和料酒，大火烧开后，小火慢炖1小时，放入冬瓜块继续炖20分钟左右，直到冬瓜软烂变透明为止，放入适量盐，再煮两分钟即可，喝时撒些葱花味道很好哦\r\n\t\t\t\t</p>\r\n\t\t\t\t<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n\t\t\t\t<p>\r\n\t\t\t\t\t<br />\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t五、蜜枣红糖姜水\r\n\t\t\t\t</p>\r\n\t\t\t\t<p align=\"center\">\r\n\t\t\t\t\t<img width=\"328\" height=\"300\" class=\"conimg\" alt=\"H7N9来袭 5款食疗方关爱家人\" src=\"http://images.meishij.net/p/20130410/6351d0c80cbeeab10cb302e6149a5eb6.jpg\" 5款食疗方关爱家人?=\"\">\r\n\t\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t\t&nbsp;\r\n\t\t\t\t\t</div>\r\n\t\t\t\t\t<p>\r\n\t\t\t\t\t\t<br />\r\n\t\t\t\t\t</p>\r\n\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t主料：蜜枣(无核) 50克、姜 10克\r\n\t\t\t\t\t</p>\r\n\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t调料：水 1000克、红糖 3大勺\r\n\t\t\t\t\t</p>\r\n\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t步骤：\r\n\t\t\t\t\t</p>\r\n\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t1、将姜去皮，切成长丝\r\n\t\t\t\t\t</p>\r\n\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t2、煮锅中添入冷水，大火烧开，放入姜丝，转小火，加盖煮约10分钟\r\n\t\t\t\t\t</p>\r\n\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t3、放入蜜枣和红糖\r\n\t\t\t\t\t</p>\r\n\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t4、用勺子搅匀，继续加盖煮约15分钟即可\r\n\t\t\t\t\t</p>\r\n\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t<br />\r\n\t\t\t\t\t</p>');--end
 INSERT INTO tq_article_txt VALUES(8,'<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t现如今熬夜的人群越来越多，因此很多人就会选择吃夜宵来补充体力以及精力。生活中还有部分人群本身就有吃夜宵的习惯，否则的话就睡不着觉。但是专家特别提醒，晚上应该尽量的避免吃东西，就算要吃也应该吃一些清淡易消化的食物。如果夜宵吃的太过于油腻以及丰盛的话，很有可能会影响肠胃健康。下面营养小厨就给你大家推荐几款适合当做夜宵的食材。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 经过实验研究发现，番茄中所含有的热量非常低。每100克番茄仅含有19大卡的热量，因此一些有吃宵夜习惯同时又害怕肥胖的人群，这个时候不妨多吃些番茄。食用方法有很多，但最好是制成沙拉食用，以免营养流失。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 还有在吃夜宵的时候应该尽量的避免各种富含有高热量以及高脂肪的食物，这类食物不仅十分难以消化，同时还有可能会刀子肥胖。长时间的肥胖会诱发个各种慢性心脑血管疾病，严重影响人体健康。因此在吃夜宵的时候，不妨选择一些热量低脂肪量少的食物，各种豆类食物。不管是是什么颜色的豆，它们都几乎不含有脂肪以及热量，食用后不用担心肥胖。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 淀粉类、奶类同样是健康夜宵首选，例如清淡的粥、汤粉面、牛奶、燕麦等。淀粉能充分地与水分结合，既提供热能，又不乏大量水分，而且味道鲜美、易食易消化；而麦片除了高钙、低脂外，还含有丰富的维他命A、B群，以及女性最需要的铁质和叶酸，有的麦片还标榜无糖，最适合那些既要保持身材又要补充营养的女性。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(9,'<p style=\"text-indent:2em;\">\r\n\t据红网报道， 4月13日下午4点，凤凰县政府召开新闻发布会，回应了对景区收门票事件的各种疑问。发布会再次强调此次景区收门票是为了规范景区管理，同时表示4月11日发生在沱江北门码头的聚集事件，是部分以无证拉客谋利为业的人员，邀约少数歇业店主，还沿路邀约甚至威胁正常营业的店铺关门停业，去北门码头游行。其中4名与执法人员发生冲突的参与者被带离现场。整个过程没有与游客发生冲突，无武警参与。目前景区秩序已恢复正常。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t对于社会各界质疑的问题，发布会进行了解释。值得一提的是，在谈到对于游客减少的损失时，发布会认为，景区是否收费并不是所有游客选择是否进入景区的第一要素，关键在于景区的品质，所以政府的补救方法是提升景区品质。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<a href=\"http://img2.fengniao.com/product/110/699/ceEkzskFxoKaU.jpg\"></a>\r\n</p>\r\n<a href=\"http://img2.fengniao.com/product/110/699/ceEkzskFxoKaU.jpg\"> \r\n<p>\r\n\t<br />\r\n</p>\r\n<p align=\"center\">\r\n\t<img title=\"凤凰古城售票后首个周末多家旅店零入住\" alt=\"凤凰古城售票后首个周末多家旅店零入住\" src=\"http://img2.fengniao.com/product/110_500x2000/699/ceEkzskFxoKaU.jpg\" width=\"500\" height=\"348\" /> \r\n\t<div align=\"left\">\r\n\t\t</a>&nbsp;\r\n\t</div>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t<strong>门票新政的过程是如何出台的？</strong> \r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t县委常委会议、县政府常委会议成立领导小组和组建8个专门工作组。相继召开了新闻发布会、全县动员大会，组织相关工作组深入细致地开展宣传动员，充分听取居民和各行业各阶层不同的意见和建议。通过宣传和沟通，城区绝大多数的居民和经营业主表示理解和支持。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t规范景区管理，是否只有收费？\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t规范景区管理不仅仅是收费，根本目的也不是为了收费。在凤凰古城既是风景名胜区又是居民生活区的现状下，要达到规范管理、保护古城、实现旅游产业健康可持续发展，凭票进入景区是当下的现实选择、必然选择。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t在具体的管理操作过程中，尽量减少对本地居民生活造成的不便，下一步将做到：进一步细化门票管理办法细则，充分做到人性化管理；在条件成熟的时候逐步推进产业转型，努力发展以文化、休闲、服务等方式为主的产业经济。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t<strong>商家因此受到损失，如何弥补？</strong> \r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t家庭旅馆、古城商铺、拉客人员及沱江河下游农家船，是当前利益调整中矛盾最大的群体。政府将从三方面疏导：一是家庭旅馆，发挥景区管理服务公司和旅游商会的桥梁作用，促成古城区内部分小宾馆的联合经营。二是逐步推行民族讲解员制度，拓宽景区服务旅游从业人员培训和就业渠道，引导拉客人员依法经营。三是加强沱江河下游风光带、夜景亮化工程建设，支持下游农家船融入市场化经营，为城区周边群众长期稳定就业搭建平台。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t动态\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t<strong>周末不少旅店的住宿率为</strong><strong>0</strong> \r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t凤凰古城售票迎来第一个周末，这个平日里游客数量相对比较多的时间段里，古城旅游和往常有什么不一样呢？\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t13日，北门城楼外的沱江码头一改前几天的冷清，在沱江泛舟的起点，出现了游客排队等待登船的场面。不过记者观察发现，当中还是以戴帽子、举旗子的团队游客为主。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t凤凰县政府13日公布了最近从门票发售到13日的运营情况，购票人数在这四天达到了17441人，门票收入突破227万元，很多都是团体游客。而最近三天，散客的票卖了200张。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t沱江大桥下的农家船码头，与虹桥上游的热闹场面相比，这个周末这里更显冷清。原本前一天还对周末游客抱着一丝希望的农家船主，13日反倒是安下心来蒙头大睡了。睡觉、聊天成了绝大多数船主的选择。老船主谭师傅告诉记者，这些农家船都是当地居民的船只，从凤凰古城售票开始，这一百多条船就这么停着了。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t尽管现在是旅游旺季，又是周末，但不少旅店的住宿率为0。在以前的黄金周，这些旅店基本都是爆满，但现在，不少旅店无人问津，还有一些旅店开出去的房间也只有两三间。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t<br />\r\n\t</p>');--end
 INSERT INTO tq_article_txt VALUES(10,'<p style=\"text-indent:2em;\">\r\n\t&nbsp;Timberland作为全球知名户外品牌受到追捧。近日其产品的成本结构被曝光，引发关注。以一双标价1890元人民币的户外鞋为例，其生产成本（含原材料）378元（占20%），行政管理费用163.5元（占8.6%）；销售渠道和广告820.3元（占43.4%）；税收378元（占20%）；净利润151.2元（占8%）。\r\n</p>\r\n<a href=\"http://img2.fengniao.com/product/110/35/ceUfC1f462GVw.jpg\"> \r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img title=\"你的钱都买了什么 揭秘户外服装的价格结构\" alt=\"你的钱都买了什么 揭秘户外服装的价格结构\" src=\"http://img2.fengniao.com/product/110/35/ceUfC1f462GVw.jpg\" width=\"460\" height=\"364\" /> \r\n\t<div align=\"left\">\r\n\t\t&nbsp;\r\n\t</div>\r\n</a> \r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t<br />\r\nTimerland 户外鞋\r\n\t</p>\r\n\t<p align=\"center\">\r\n\t\t<a href=\"http://img2.fengniao.com/product/110/892/ceJ8PTMCk3t8I.jpg\"><img title=\"你的钱都买了什么 揭秘户外服装的价格结构\" alt=\"你的钱都买了什么 揭秘户外服装的价格结构\" src=\"http://img2.fengniao.com/product/110/892/ceJ8PTMCk3t8I.jpg\" width=\"500\" height=\"478\" /> \r\n\t\t<div align=\"left\">\r\n\t\t\t&nbsp;\r\n\t\t</div>\r\n</a> \r\n\t\t<p>\r\n\t\t\t<br />\r\n\t\t</p>\r\n\t\t<p>\r\n\t\t\t<br />\r\n价格结构\r\n\t\t</p>\r\n\t\t<p align=\"center\">\r\n\t\t\t<a href=\"http://img2.fengniao.com/product/110/894/ceIiitYU3eUU.jpg\"><img title=\"你的钱都买了什么 揭秘户外服装的价格结构\" alt=\"你的钱都买了什么 揭秘户外服装的价格结构\" src=\"http://img2.fengniao.com/product/110/894/ceIiitYU3eUU.jpg\" width=\"500\" height=\"224\" /> \r\n\t\t\t<div align=\"left\">\r\n\t\t\t\t&nbsp;\r\n\t\t\t</div>\r\n</a> \r\n\t\t\t<p>\r\n\t\t\t\t<br />\r\n\t\t\t</p>\r\n\t\t\t<p>\r\n\t\t\t\t<br />\r\n\t\t\t</p>\r\n\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t从中可以看到，渠道流通和广告推广环节是Timberland价格虚高的最主要原因，而高税收与行政管理费用位列第二，真正在产品本身花的钱少之又少，如何从这两个环节入手，改善品牌产品的成本结构，让品牌商经营更加良性，让消费者得到实惠，应该是未来发展的方向。\r\n\t\t\t</p>\r\n\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\tTimberland作为全球领先户外品牌， 旗下有Timberland、Timberland PRO、SmartWool、Timberland Boot Company等品牌，通过提供高质量、讲究细节的工艺制品，来满足不同户外环境下的需求。 Timberland的产品透过北美洲、欧洲、亚洲、拉丁美洲、南非及中东的百货公司、专卖店和Timberland直营店营销全球。Timberland在承诺对股东，雇员和消费者利益和责任的同时，做到对自然环境负责。\r\n\t\t\t</p>\r\n\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t<br />\r\n\t\t\t</p>');--end
 INSERT INTO tq_article_txt VALUES(11,'<p style=\"text-indent:2em;\">\r\n\t喜欢读书的朋友们家中可能也有很多藏书，为了收藏这些书籍，需要有很好的储藏方案，比如设计一个“家庭图书馆”，可以将图书馆建在客厅里，如果家中没有这样的图书馆，就只能把书籍们存放在角落里了。建这样一个家庭图书馆，要考虑舒适的座位和良好的照明系统，下面10个杰出的家庭图书馆设计思路，或许能给你带来一些灵感。\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img border=\"0\" src=\"http://shequ.bj100.com/attachments/photo/Mon_1108/12681_9d6b1313722730498ce24f394f407.jpg\" /> \r\n\t<div align=\"left\">\r\n\t\t&nbsp;&nbsp;<br />\r\n&nbsp;<br />\r\n和外面的图书馆没啥区别嘛\r\n\t</div>\r\n\t<p style=\"text-indent:2em;\" align=\"center\">\r\n\t\t<img border=\"0\" src=\"http://shequ.bj100.com/attachments/photo/Mon_1108/12681_fc551313722775973d86412b1b0d8.jpg\" /> \r\n\t\t<div align=\"left\">\r\n\t\t\t&nbsp;\r\n\t\t</div>\r\n\t\t<p>\r\n\t\t\t<br />\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t<br />\r\n\t\t</p>');--end
 INSERT INTO tq_article_txt VALUES(12,'<p style=\"text-indent:2em;\">\r\n\t1、土豆烧牛肉：由于土豆和牛肉在被消化时所需的胃酸的浓度不同，就势必延长食物在胃中的滞留时间，从而引起胃肠消化吸收时间的延长，久而久之，必然导致肠胃功能的紊乱。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t2、小葱拌豆腐：豆腐中的钙与葱中的草酸，会结合成白色沉淀物--草酸钙，同样造成人体对钙的吸收困难。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t3、豆浆冲鸡蛋：鸡蛋中的粘液性蛋白会与豆浆中的胰蛋白酶结合，从而失去二者应有的营养价值。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t4、茶叶煮鸡蛋：茶叶中除生物碱外，还有酸性物质，这些化合物与鸡蛋中的铁元素结合，对胃有刺激作用，且不利于消化吸收。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t5、炒鸡蛋放味精：鸡蛋本身含有许多与味精成分相同的谷氨酸，所以炒鸡蛋时放味精，不仅增加不了鲜味，反而会破坏和掩盖鸡蛋的天然鲜味。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t6、红白萝卜混吃：白萝卜中的维生素C含量极高，但红萝卜中却含有一种叫抗坏血酸的分解酵素，它会破坏白萝卜中的维生素C。一旦红白萝卜配合，白萝卜中的维生素C就会丧失殆尽。不仅如此，在与含维生素C的蔬菜配合烹调时，红萝卜都充当了破坏者的角色。还有胡瓜、南瓜等也含有类似红萝卜的分解酵素。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t7、萝卜水果同吃：近年来科学家们发现，萝卜等十字花科蔬菜进入人体后，经代谢很快就会产生一种抗甲状腺的物质---硫氰酸。该物质产生的多少与摄入量成正比。此时，如果摄入含大量植物色素的水果如橘子、梨、苹果、葡萄等，这些水果中的类黄酮物质在肠道被细菌分解，转化成羟苯甲酸及阿魏酸，它们可加强硫氰酸抑制甲状腺的作用，从而诱发或导致甲状腺肿。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t8、海味与水果同食：海味中的鱼、虾、藻类，含有丰富的蛋白质和钙等营养物质，如果与含有鞣酸的水果同食，不仅会降低蛋白质的营养价值，且易使海味中的钙质与鞣酸结合成一种新的不易消化的物质，这种物质会刺激胃而引起不适，使人出现肚子痛、呕吐、恶心等症状。含鞣酸较多的水果有柿子、葡萄、石榴、山楂、青果等。因此这些水果不宜与海味菜同时食用，以间隔两个小时为宜。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t9、牛奶与橘子同食：刚喝完牛奶就吃橘子，牛奶中的蛋白质就会先与橘子中的果酸和维生素C相遇而凝固成块，影响消化吸收，而且还会使人发生腹胀、腹痛、腹泻等症状。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t10、酒与胡萝卜同食：最近，美国食品专家告诫人们：酒与胡萝卜同食是很危险的。专家指出，因为胡萝卜中丰富的β胡萝卜素与酒精一同进入人体，就会在肝脏中产生毒素，从而引起肝病。特别是在饮用胡萝卜汁后不要马上去饮酒。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t11、白酒与汽水同饮：因为白酒、汽水同饮后会很快使酒精在全身挥发，并生产大量的二氧化碳，对胃、肠、肝、肾等器官有严重危害，对心脑血管也有损害。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t12、吃肉时喝茶：有的人在吃肉食、海味等高蛋白食物后，不久就喝茶，以为能帮助消化。殊不知，茶叶中的大量鞣酸与蛋白质结合，会生成具有收敛性的鞣酸蛋白质，使肠蠕动减慢，从而延长粪便在肠道内滞留的时间。既容易形成便秘，又增加有毒和致癌物质被人体吸收的可能性。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t13、橘子别与黄瓜同食\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t橘子忌与黄瓜同食。黄瓜中的维生素C分解酶会破坏橘子中所含的多种维生素，而使橘子的营养价值降低。橘子也不宜与萝卜同食。据报道，萝卜在体内会代谢产生一种抗甲状腺物质——硫氰酸，若与橘子同食，橘子中的类黄酮物质会转化成羟苯甲酸而加强硫氰酸抑制甲状腺的作用，从而诱发甲状腺肿。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(13,'<p style=\"text-indent:2em;\">\r\n\t有光泽的面料，金属色印花，彩虹般的光芒，都劲情的呈现在2013春夏的T台上。例如：Alexander Wang，Louise Gray和Mulberry。大量的金属色光泽是2013春夏T台充满了魔力，当受到光线照射，色彩变得丰富，轻薄的面料漂浮在身体上发射出神秘的光芒。Burberry Prorsum和Jonathan Saunders走极简路线，线条简洁，经典廓形是成功的关键。博柏利-珀松 (Burberry Prorsum) 让彩虹色电光面料本身成为焦点，至于其它方面，仅仅保持品牌原有的简洁线条和经典廓形就足够。迪奥 (Dior) 让具有光泽感绿色、鹅黄色欧根纱因细节褶皱设计呈现出波光粼粼的视觉效果，灵动活泼。沉溺于最颓废的方式——亮光泽。虽然闪闪惹人爱，但是富有Disco精神的光感时装驾驭起来需要些功力，最好选择一款Diane von Furstenberg彩色亮片礼服搭配奢华的配饰，牛仔裤和匡威。尽量选择最闪亮的面料，配件应保持最小和样式时髦的款式。\r\n</p>\r\n<p align=\"center\">\r\n\t<img border=\"1\" alt=\"\" src=\"http://img2.trends.com.cn/upload/130409/1304091546572043.jpg\" /> \r\n\t<div align=\"left\">\r\n\t\t&nbsp;\r\n\t</div>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<div>\r\n\t</div>\r\n\t<div>\r\n\t\t<div>\r\n\t\t\t<strong>Christian Dior</strong> \r\n\t\t</div>\r\n\t\t<div>\r\n\t\t\t　　未来感反光面料裙装的登场，柔和的水粉色、丰富的褶皱和巧妙的光效，共同营造出“波光粼粼”的效果，拉夫·西蒙 (Raf Simons) 展示自己对迪奥 (Dior) 的改变的时间到了。“自由”是拉夫·西蒙 (Raf Simons) 为Dior 2013春夏女装定下的主题，造型更为简练的西装上衣、A字裙、不对称垂褶上衣与黑色短裤的搭配，为迪奥 (Dior) 女郎带来的是更轻盈、更干练的新风貌。\r\n\t\t</div>\r\n\t\t<p align=\"center\">\r\n\t\t\t<img border=\"1\" alt=\"\" src=\"http://img2.trends.com.cn/upload/130409/1304091546572022.jpg\" /> \r\n\t\t\t<div align=\"left\">\r\n\t\t\t\t&nbsp;\r\n\t\t\t</div>\r\n\t\t\t<p>\r\n\t\t\t\t<br />\r\n\t\t\t</p>\r\n\t\t\t<div>\r\n\t\t\t</div>\r\n\t\t\t<div>\r\n\t\t\t\t<strong>Christian Dior</strong> \r\n\t\t\t</div>\r\n\t\t\t<div>\r\n\t\t\t\t　　太过大胆的变动和太过保守的致敬，都会让新入主品牌的设计师受到诟病，而拉夫·西蒙 (Raf Simons) 本次的“中庸之道”，却可以让他赢得不少的掌声。黑色紧身上衣与手绘玫瑰长裙的组合，拉夫·西蒙 (Raf Simons) 坚守的“纯粹主义”，与迪奥 (Dior) 对女性优美轮廓的极致追求、对华丽的定义，在这样的造型中融为一体。\r\n\t\t\t</div>\r\n\t\t\t\t</div>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t<br />\r\n\t\t\t\t</p>');--end
 INSERT INTO tq_article_txt VALUES(14,'<p style=\"text-indent:2em;\">\r\n\t&nbsp; 随着电动车事故的不断增多，全国各地相继出台了一系列加强对电动车管理的法规，从严禁在机动车道形式，到法定的适骑年龄，但是不管是怎样的严厉法规，其目的终究是为了更好的管理日益增多的电动车。近日，经过一年多酝酿，南昌市首部城管条例——《南昌市城市管理条例》3月1日起施行，填补了该市城市管理的法律空白。关于电动车管理，《条例》中明确驾驶电动车载12周岁以上者，罚款30元。\r\n</p>\r\n<p align=\"center\">\r\n\t<img alt=\"\" src=\"/member/portal/demo/201304/16151931jixc.jpg\" /> \r\n\t<div align=\"left\">\r\n\t\t&nbsp;\r\n\t</div>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t关于城市垃圾的处置，条例给出了明确的规定。而有关日常出行的规定，是该条例中的“重头戏”。比如，驾驶电动自行车限载一名12周岁以下未成年人，若违反规定，将被罚款。<br />\r\n<br />\r\n&nbsp;&nbsp;&nbsp;&nbsp; 市民经常可以看到，一些店面把商品摆到路边经营。对此，条例中提到，临街店面超出经营场所门窗、外墙进行店外经营、作业、堆放货物或者展示商品的，由城市管理行政执法部门责令改正；拒不改正的，处二百元以上一千元以下罚款。\r\n\t</p>');--end
 INSERT INTO tq_article_txt VALUES(15,'<p style=\"text-indent:2em;\">\r\n\t是女人都想要的S线条如何获得？也许你身材不错，个子高挑，但如果穿得不当，S线条也会离你远去。春天这样百花齐放阳光明媚的季节，就用一款印花包臀裙来塑造美妙S型吧！你可以选性感的超短连衣裙点亮街头，也可以用半身裙搭配优雅的衬衫风靡办公室。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<!-- 导语 end -->\r\n</p>\r\n<div>\r\n</div>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img title=\"印花包臀裙营造美妙线条浪漫气质\" alt=\"印花包臀裙营造美妙线条浪漫气质\" src=\"http://p1.yokacdn.com/pic/fashion/edittj/2013/U15P1T1D785417F9DT20130412140703.jpg\" /> \r\n\t<div align=\"left\">\r\n\t\t&nbsp;\r\n\t</div>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<div>\r\n\t</div>\r\n\t<div align=\"center\">\r\n\t\t印花包臀裙营造美妙线条浪漫气质<br />\r\n\t</div>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t　　印花包臀裙包含了两个重要的春季流行元素：紧身和花色。紧身的结构帮你轻松展现自己的身材线条，散发性感女人味，而迷人的花朵则带来春季活跃的气氛，唤醒你对美丽的追求欲望。你可以选择超短款的连衣裙秀出性感美腿，也可以将半身裙与利落的衬衫搭配作为你的办公室造型，总之不能错过在这个季节展示S线条的大好时机！\r\n\t</p>\r\n\t<div>\r\n\t\t<br />\r\n\t</div>\r\n\t<p style=\"text-indent:2em;\" align=\"center\">\r\n\t\t<img title=\"泡泡袖白衬衫搭配印花一步裙\" alt=\"泡泡袖白衬衫搭配印花一步裙\" src=\"http://p1.yokacdn.com/pic/fashion/edittj/2013/U15P1T1D785417F23DT20130412140703.jpg\" /> \r\n\t\t<div align=\"left\">\r\n\t\t\t&nbsp;\r\n\t\t</div>\r\n\t\t<p>\r\n\t\t\t<br />\r\n\t\t</p>\r\n\t\t<div>\r\n\t\t</div>\r\n\t\t<div align=\"center\">\r\n\t\t\t泡泡袖白衬衫搭配印花一步裙<br />\r\n\t\t</div>\r\n\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t　　白色泡泡袖衬衫款式简洁造型别致，收腰和宽袖的视觉对比令胸腰比例更加明显，搭配绿色印花一步裙在整体轮廓上形成X形态，女人味十足又不失名媛气质。白色高跟鞋与衬衫相互呼应整体感更强，充分把握了办公室着装的精髓，时尚而不花俏，性感不失端庄。\r\n\t\t</p>\r\n\t\t<div>\r\n\t\t\t<br />\r\n\t\t</div>\r\n\t\t<p style=\"text-indent:2em;\" align=\"center\">\r\n\t\t\t<img title=\"包臀裙营造优美臀部线条\" alt=\"包臀裙营造优美臀部线条\" src=\"http://p1.yokacdn.com/pic/fashion/edittj/2013/U15P1T1D785417F25DT20130412140703.jpg\" /> \r\n\t\t\t<div align=\"left\">\r\n\t\t\t\t&nbsp;\r\n\t\t\t</div>\r\n\t\t\t<p>\r\n\t\t\t\t<br />\r\n\t\t\t</p>\r\n\t\t\t<div>\r\n\t\t\t</div>\r\n\t\t\t<div align=\"center\">\r\n\t\t\t\t包臀裙营造优美臀部线条<br />\r\n\t\t\t</div>\r\n\t\t\t<p style=\"text-indent:2em;\" align=\"center\">\r\n\t\t\t\t<img title=\"泡泡袖更有宫廷华丽范儿\" alt=\"泡泡袖更有宫廷华丽范儿\" src=\"http://p1.yokacdn.com/pic/fashion/edittj/2013/U15P1T1D785417F62DT20130412140703.jpg\" /> \r\n\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t&nbsp;\r\n\t\t\t\t</div>\r\n\t\t\t\t<p>\r\n\t\t\t\t\t<br />\r\n\t\t\t\t</p>\r\n\t\t\t\t<div>\r\n\t\t\t\t</div>\r\n\t\t\t\t<div align=\"center\">\r\n\t\t\t\t\t泡泡袖更有宫廷华丽范儿\r\n\t\t\t\t</div>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t<br />\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t<br />\r\n\t\t\t\t</p>');--end
 INSERT INTO tq_article_txt VALUES(16,'<p style=\"text-indent:2em;\">\r\n\t<p align=\"center\">\r\n\t\t<img style=\"width:450px;\" src=\"http://img03.taobaocdn.com/sns_outer/i3/T1FbY2XkBhXXb1upjX.jpg\" width=\"450\" /> \r\n\t\t<div align=\"left\">\r\n\t\t\t&nbsp;\r\n\t\t</div>\r\n\t\t<p>\r\n\t\t\t<br />\r\n\t\t</p>\r\n\t\t<p>\r\n\t\t\t<br />\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t圆领纯白色卫衣~卫衣上的印花图案会起到很好的减龄作用~有时候有些事情就是这么简单！冰激凌色系淡蓝色用作内搭~可以让脸部的变得有轮廓~下身卷边牛仔短裤~黑色打底裤保暖又显瘦~高筒翻毛平底靴~拼接的颜色就是这么可爱~！\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t<p align=\"center\">\r\n\t\t\t\t<img style=\"width:450px;\" src=\"http://img02.taobaocdn.com/sns_outer/i2/T1dIr2XdRhXXb1upjX.jpg\" width=\"450\" /> \r\n\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t&nbsp;\r\n\t\t\t\t</div>\r\n\t\t\t\t<p>\r\n\t\t\t\t\t<br />\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t<br />\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t深蓝色针织圆领套头毛衣~香蕉图案真的是很可爱哦~下身淡蓝色小脚紧身裤！一双new balance绝对的热单啊~！\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t<p align=\"center\">\r\n\t\t\t\t\t\t<img style=\"width:450px;\" src=\"http://img03.taobaocdn.com/sns_outer/i3/T1pNb3Xb4aXXb1upjX.jpg\" width=\"450\" /> \r\n\t\t\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t\t\t&nbsp;\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t<p>\r\n\t\t\t\t\t\t\t<br />\r\n\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t<br />\r\n\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t浅驼色就是这么给人温暖的感觉~前系带式大衣有修身的作用哦~下身紧身黑色小脚裤~与长筒皮靴一条围巾保暖~超女人又时尚~！\r\n\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t<br />\r\n\t\t\t\t\t\t</p>');--end
 INSERT INTO tq_article_txt VALUES(17,'<p style=\"text-indent:2em;\">\r\n\t<p align=\"center\">\r\n\t\t<img src=\"http://img03.taobaocdn.com/tps/i3/T1hgjqXkXpXXb1upjX.jpg\" />\r\n\t\t<div align=\"left\">\r\n\t\t\t&nbsp;\r\n\t\t</div>\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t<p align=\"center\">\r\n\t\t\t<img src=\"http://img01.taobaocdn.com/tps/i1/T1LavrXnhiXXXXXXXX.jpg\" />\r\n\t\t\t<div align=\"left\">\r\n\t\t\t\t&nbsp;\r\n\t\t\t</div>\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t黑色的休闲背心，外搭红色网眼套头衫，美眉脚上的桃红尖头平跟鞋，与上衣的颜色相呼应，很搭调。下身的白色压褶裙，很时尚、清凉的款式。\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t<p align=\"center\">\r\n\t\t\t\t<img src=\"http://img01.taobaocdn.com/tps/i1/T1eg2rXedgXXXXXXXX.jpg\" />\r\n\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t&nbsp;\r\n\t\t\t\t</div>\r\n\t\t\t</p>\r\n\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t深粉色的雪纺上装，配以粉橙色的短裤、白色的尖头高跟鞋，这样的搭配简单而时尚，上重下轻的搭法，很好的拉长了身材的比例，显高效果不错。\r\n\t\t\t</p>\r\n\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t<p align=\"center\">\r\n\t\t\t\t\t<img src=\"http://img01.taobaocdn.com/tps/i1/T1pJjrXbxhXXXXXXXX.jpg\" />\r\n\t\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t\t&nbsp;\r\n\t\t\t\t\t</div>\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t红色的雪纺长裙，的确是一款很惊艳的裙子。袖口的开衩，以及裙摆处的压褶设计，这些细节上的精致设计，就足以打动每个女孩的心。\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t<p align=\"center\">\r\n\t\t\t\t\t\t<img src=\"http://img01.taobaocdn.com/tps/i1/T1F2_rXd8fXXXXXXXX.jpg\" />\r\n\t\t\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t\t\t&nbsp;\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t</p>\r\n\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t有时候，换上休闲的T恤，型格的黑色裤装，走一下个性、张扬路线，也挺有范儿的哦！\r\n\t\t\t\t\t</p>\r\n\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t<p align=\"center\">\r\n\t\t\t\t\t\t\t<img src=\"http://img01.taobaocdn.com/tps/i1/T1OR6pXjJuXXXXXXXX.jpg\" />\r\n\t\t\t\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t\t\t\t&nbsp;\r\n\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t单看这件黑白横条纹的背心式长裙，或许你会觉得有些单调，但外搭一件荧光色的开衫之后，感觉是不是马上变了呢？\r\n\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t<p align=\"center\">\r\n\t\t\t\t\t\t\t\t<img src=\"http://img01.taobaocdn.com/tps/i1/T1zwrrXcdfXXXXXXXX.jpg\" />\r\n\t\t\t\t\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t\t\t\t\t&nbsp;\r\n\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t\t这款白色连衣裙的设计，非常的简单。因为简单，所以优雅。\r\n\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t\t<p align=\"center\">\r\n\t\t\t\t\t\t\t\t\t<img src=\"http://img01.taobaocdn.com/tps/i1/T14vrsXidXXXXXXXXX.jpg\" />\r\n\t\t\t\t\t\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t\t\t\t\t\t&nbsp;\r\n\t\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t\t\t穿上黑色的单品，即使不是性感的款式，也会略带性感的韵味呢。下半身的透视设计，融入了2012年最火的时尚元素。\r\n\t\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t\t\t<p align=\"center\">\r\n\t\t\t\t\t\t\t\t\t\t<img src=\"http://img01.taobaocdn.com/tps/i1/T1a8HqXaVmXXXXXXXX.jpg\" />\r\n\t\t\t\t\t\t\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t\t\t\t\t\t\t&nbsp;\r\n\t\t\t\t\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t\t\t\t这款连衣裙，光是颜色也很吸引人的眼球，精美的百褶，设计感很强烈。\r\n\t\t\t\t\t\t\t\t\t</p>');--end
 INSERT INTO tq_article_txt VALUES(18,'<p style=\"text-indent:2em;\">\r\n\t<p align=\"center\">\r\n\t\t<img style=\"width:360px;\" src=\"http://img04.taobaocdn.com/tps/i4/T1vq6qXepkXXb1upjX.jpg\" width=\"360\" /> \r\n\t\t<div align=\"left\">\r\n\t\t\t&nbsp;\r\n\t\t</div>\r\n\t\t<p>\r\n\t\t\t<br />\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t&nbsp;\r\n\t\t</p>\r\n\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t<p align=\"center\">\r\n\t\t\t\t<img style=\"width:450px;\" src=\"http://img01.taobaocdn.com/tps/i1/T1U1PoXdNsXXXXXXXX.jpg\" width=\"450\" /> \r\n\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t&nbsp;\r\n\t\t\t\t</div>\r\n\t\t\t\t<p>\r\n\t\t\t\t\t<br />\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t&nbsp;\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t卡通图案撞色袖口T恤+白色卷边九分裤\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t显高显瘦指数：★★★★★\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t超级讨巧的卡通图案让女生们一眼爱上。今年超流行的撞色设计提高时尚感。卡通图案上立体的蝴蝶结装饰更是这件T恤的点睛之笔哦!搭配白色卷边九分裤，将衣身扎进裤腰里，显高又显瘦哦!\r\n\t\t\t\t</p>\r\n\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t<p align=\"center\">\r\n\t\t\t\t\t\t<img style=\"width:450px;\" src=\"http://img01.taobaocdn.com/tps/i1/T1eWnpXdNlXXXXXXXX.jpg\" width=\"450\" /> \r\n\t\t\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t\t\t&nbsp;\r\n\t\t\t\t\t\t</div>\r\n\t\t\t\t\t\t<p>\r\n\t\t\t\t\t\t\t<br />\r\n\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t&nbsp;\r\n\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t流苏亮片装饰长版T恤+包臀裙\r\n\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t显高显瘦指数：★★★★★\r\n\t\t\t\t\t\t</p>\r\n\t\t\t\t\t\t<p style=\"text-indent:2em;\">\r\n\t\t\t\t\t\t\t局部的流苏和亮片装饰让整件T恤充满时尚气息，宽松的版型绝对是藏匿赘肉的秘密武器。搭配紧身包臀裙，满分造型!\r\n\t\t\t\t\t\t</p>');--end
 INSERT INTO tq_article_txt VALUES(19,'<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img style=\"width:360px;\" src=\"http://img04.taobaocdn.com/tps/i4/T1g2hXXxJaXXb1upjX.jpg\" width=\"360\" /> \r\n\t<div align=\"left\">\r\n\t\t&nbsp;\r\n\t</div>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t玉器的几大品鉴点\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t1、是看颜色\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t颜色是评估玉品质最重要的因素。颜色达到匀、阳、浓、正的玉为上品。“匀”是指均匀；“阳”是指色泽鲜明，给人以开朗、无郁结之感；“浓”是指颜色比较深；“正”是指没有其他杂色混在一起。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t2、是看质地\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t玉是硅酸盐在高温和高压下形成的多晶体矿物，其组成晶体的大小，会直接影响到经过琢磨后的光滑程度、透明度及色调。因此，多晶体结构越细密，玉的质地就越好。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t3、是看透明度\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t透明度是与质地相辅相成的物理现象。质地越幼细，透明度就越高。如果玉的通透程度犹如玻璃一样，其内晶体的细密程度就可以使光线直透而不受阻挡。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t4、是看后天加工\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t玉被开采出来时只是和矿石一样，必须由经验丰富的专业工匠将石中的有色部分小心地切割出不同的饰物形状，然后加工打磨和雕琢， 经抛光上蜡，才能到市场上出售。加工中完全未经任何漂白褪色或染色处理的为“A”级，价值为最高；被漂白褪色的为“B”级，价值则次之；被染色的“C”级 价值较低。优良的后天加工，可使玉锦上添花，价值倍增。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t5、看裂纹\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t玉上的裂纹可能是在开采或加工期间造成。有了裂纹后，无论其颜色、质地和透明度如何好，都会影响到它的价值。有时裂纹在其表面并不明显，但在阳光下仔细观察就可以看到。尤其是被漂白褪色或被染色的玉，裂纹皆为常见现象。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t在辨别玉质量的同时，还要防止人造仿玉的以假乱真。人造仿玉是用玻璃、塑胶等材料染色后制成。玻璃仿玉大多内含气泡，色调鲜艳程度高，绿色带有很强的亮光，颜色与真玉有别。塑胶仿玉比真玉轻，透明度极差，色调暗哑，与真玉相差甚远。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t下面介绍玉器的真假辨别方法\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t1、水鉴别法\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t将一滴水滴在玉上，如成露珠状久不散开者真玉；水滴很快消失的是伪劣货。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t2、手触摸法\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t若是真玉用手摸一摸，有冰凉润滑之感。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t3、视察法\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t将玉器朝向光明处，如阳光、灯光处，如果颜色剔透、绿色均匀分布就是真玉。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t4、舌舐法\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t舌尖舐真玉有涩的感觉；而假玉则无涩的感觉。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t5、放大镜观看\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t将选购的玉器放在放大镜下观看，主要是有无裂痕，无裂痕者为上乘优质玉，有裂痕者为次之。即使是真玉，有裂痕的其价值亦大减，裂痕越多越明显的，价值也就越低。\r\n\t</p>');--end
 INSERT INTO tq_article_txt VALUES(20,'<p>\r\n\t<p align=\"center\">\r\n\t\t<img style=\"width:360px;\" src=\"http://img03.taobaocdn.com/tps/i3/T157Q.XhddXXb1upjX.jpg\" width=\"360\" />\r\n\t\t<div align=\"left\">\r\n\t\t\t&nbsp;\r\n\t\t</div>\r\n\t</p>\r\n\t<p>\r\n\t\t<strong>真鞋篇</strong>\r\n\t</p>\r\n\t<p>\r\n\t\t<strong>一、正品</strong>\r\n\t</p>\r\n\t<p>\r\n\t\t正品的概念很明确，就是在nike ADIDAS的正规授权厂，由技术娴熟的工人使用合格的材料生产出来的鞋，通过了严格检验，在指定专卖店销售的鞋。\r\n\t</p>\r\n\t<p>\r\n\t\t<strong>二、A品</strong>\r\n\t</p>\r\n\t<p>\r\n\t\tA品等于上市前的正品。A品的意思就是授权工厂按严格工艺控制制作的通过了正规检测的鞋，A品和正品唯一的差别就是：A品是出售前的正品，A品一旦在正规销售渠道上市，就是正品了。国外这些鞋公司是没有自己的鞋厂的，他们靠认证授权鞋厂产鞋，然后检测人员验证合格后发放到各销售通路去，A品就是这些。\r\n\t</p>\r\n\t<p>\r\n\t\t<strong>三、B品</strong>\r\n\t</p>\r\n\t<p>\r\n\t\t对于质量检测不合格的鞋会打上一个红色的B的标记，然后集中销毁。所以B品就是说的质量不过关或者质量有瑕疵的真品。真的所谓B品，正规检测时候一般有一个B的标志的（不过不排除特殊流出的）。 猫腻在于，在部分检测或者销毁的时候，内部工作人员将B品偷运出来地下流通到市面。 还有一种情况就是，检测人员将部分质量过关的鞋（A品）也擅自打上B的标记或者混同着，这样便于偷运出来。这种鞋其实没有质量问题。\r\n\t</p>\r\n\t<p>\r\n\t\t狭义下的正品：除了打折的价格，正品的价格都是公司指定的全国统一价格。除了正规渠道和正式代理出售（包括一些小店二传手），其他的都不是正品。正品一定是带原装鞋盒。\r\n\t</p>\r\n\t<p>\r\n\t\t广义的正品：也就是说，真正的厂鞋（原厂鞋），包括A、B品，都是授权厂家生产的鞋，和专卖的正品质量没有区别（专卖店也有部分B品，B标记已经经过处理）也可以算是正品。\r\n\t</p>\r\n\t<p>\r\n\t\t<strong>假鞋篇</strong>\r\n\t</p>\r\n\t<p>\r\n\t\t<strong>一、原厂A货（厂鞋，原厂鞋）</strong>\r\n\t</p>\r\n\t<p>\r\n\t\t厂鞋这个概念是最虚渺的一个概念了，其实本来所谓的厂鞋是指的NIKE，ADIDAS的大的正规授权厂，全国只有指定的几家（此外还有一些厂的生产质 量不错，也会不定期接到NIKE，ADIDAS的临时或短期订单），由这些厂生产的才能叫厂鞋。厂鞋包括合格的、不合格的，真正意义上的原厂A货就是指那些合格的，可以进专卖店的鞋，也就是说，真正的原厂A货就是正品！\r\n\t</p>\r\n\t<p>\r\n\t\t但是，现在厂鞋基本已经成了网络上很多骗子对自己假鞋的吹捧、包装和欺骗的代名词，动不动就神秘兮兮的告诉你我的是原厂鞋，质量绝对好，就是正品之类的，其实无非是高精仿或者外贸鞋，甚至有用普通仿鞋号称厂鞋欺骗消费者，可以说现在网上95％的所谓厂鞋都是仿鞋。\r\n\t</p>\r\n\t<p>\r\n\t\t很多厂鞋都款式丰富，新款老款一应俱全，尺码从小到大一个不缺，试想，真正的正品，或者说真品，岂是那么容易这样整批整批的完整流通出来。如果是真品鞋，何必还要故弄玄虚的称其为原厂鞋，而不直接说是真品呢？\r\n\t</p>\r\n\t<p>\r\n\t\t<strong>二、原厂料拼装鞋（组装鞋）</strong>\r\n\t</p>\r\n\t<p>\r\n\t\t原厂料拼装鞋（组装鞋）就是假鞋的一种！ 拼鞋是利用指定鞋厂里生产多余的材料，偷出来的材料，不合格的材料（材料也有选用检测的），从厂里废品垃圾里捡来的破烂等等，然后躲在某个角落自己用低下的工具拼出来的鞋子。 可以肯定的说，所谓拼鞋也只是用了点点部分的原材料，而且各部件还可能不是配套的（比如42的鞋底配41的鞋面）。为什么看鞋标容易分出拼鞋，道理就在这 里。\r\n\t</p>\r\n\t<p>\r\n\t\t<strong>三、走私货（水货）</strong>\r\n\t</p>\r\n\t<p>\r\n\t\t这个说法也很多，但是可以说是最没有意义的说法，水货原来指国外走私过来的东西。以前在中国没有指定鞋厂的时候，倒是的确有水货，现在鞋子大部分都是中国产，made in China，何来走私的概念？当然，也有从国外带回来的在国外买的特殊款式的鞋的确是正品，但这就不能算走私了。所以，当某卖家提到“走私正品”等等时， 八成就是假鞋了。\r\n\t</p>\r\n\t<p>\r\n\t\t<strong>四、高精仿（超精仿）</strong>\r\n\t</p>\r\n\t<p>\r\n\t\t有真就有假，假的也得分层次。高精仿就是假鞋里层次最高的，现在很多高精仿的仿真程度已经逼近100％。这种鞋首先是外观和真品无二，只有用仪器才能测出细微差别，其次它的用料和做工也比一般仿鞋好出一大块。一般人根本看不出其真假，需要专家来鉴别，对于部分仿的特别好的鞋（主要是些款式比较老，仿鞋技术已经很成熟，或者是款式比较常见，仿制难度不高的鞋），非得专家级的专家才能辨别。\r\n\t</p>');--end
 INSERT INTO tq_article_txt VALUES(21,'<p style=\"text-align:center;\">\r\n\t<p class=\"cover\" align=\"center\">\r\n\t\t<img src=\"http://img02.taobaocdn.com/tps/i2/T1jAQ9XidbXXb1upjX.jpg\" width=\"350\" height=\"350\" />\r\n\t\t<div align=\"left\">\r\n\t\t\t&nbsp;\r\n\t\t</div>\r\n\t</p>\r\n\t<div class=\"cover\">\r\n\t\t<span class=\"btn-tts\"> </span>\r\n\t</div>\r\n\t<p>\r\n\t\t<strong><span></span><span>卵圆形脸</span></strong>\r\n\t</p>\r\n\t<p>\r\n\t\t<span></span><span>卵圆形脸佩带任何形状的耳环效果都不错，但是要注意耳环的大小要与新娘的整体感觉相符，要看她是属于身材娇小还是比较高大的来选择。由于卵圆形轮廓比较柔和，所以如果选择相似的轮廓形状的耳环，如珍珠，水滴形，圆圈状或卵形的耳环是最好不过的了。</span>\r\n\t</p>\r\n\t<p>\r\n\t\t<strong><span></span><span>圆形脸</span></strong>\r\n\t</p>\r\n\t<p>\r\n\t\t<span></span><span>圆形脸可以通过耳环来达到拉长的效果。水滴形的耳环线条轮廓柔和，而且形状很适合圆形脸的人。</span>\r\n\t</p>\r\n\t<p>\r\n\t\t<strong><span></span><span>椭圆形脸</span></strong>\r\n\t</p>\r\n\t<p>\r\n\t\t<span></span><span>椭圆形脸的人最好用耳钉，这样可以横向吸引人的目光，达到最佳效果。</span>\r\n\t</p>\r\n\t<p>\r\n\t\t<strong><span></span><span>心形脸</span></strong>\r\n\t</p>\r\n\t<p>\r\n\t\t<span></span><span>心形脸可以选择那种下端宽，上端窄的耳环，用来平衡窄下巴的感觉。像水滴形，三角形，或耳钉都不错。</span>\r\n\t</p>\r\n\t<p>\r\n\t\t<strong><span></span><span>菱形脸</span></strong>\r\n\t</p>\r\n\t<p>\r\n\t\t<span></span><span>菱形脸的人可以参照卵圆形的原则来选择耳环。但是这种脸形也适合那种变幻多姿的，棱角分明的耳环。所以线条分明的钻石耳环配菱形脸的效果也非常好。</span>\r\n\t</p>\r\n\t<p>\r\n\t\t<strong><span></span><span>正方形脸</span></strong>\r\n\t</p>\r\n\t<p>\r\n\t\t<span></span><span>水滴形耳环可以拉长正方形脸，是其最佳选择。</span>\r\n\t</p>\r\n\t<p>\r\n\t\t<strong><span></span><span>长方形脸</span></strong>\r\n\t</p>\r\n\t<p>\r\n\t\t<span></span><span>对于长方形脸的人来说，还是戴耳钉比较合适。</span>\r\n\t</p>\r\n\t<p>\r\n\t\t<strong><span></span><span>三角形脸</span></strong>\r\n\t</p>\r\n\t<p>\r\n\t\t<span></span><span>三角形脸的人选择耳环的原则与心形脸的原则相似，但是耳环需要有更明显的棱角，耳环下端要比上端宽。</span>\r\n\t</p>\r\n\t<p>\r\n\t\t<strong><span></span><span>耳环的式样</span></strong>\r\n\t</p>\r\n\t<p>\r\n\t\t<span></span><span>通常可分为传统式、改良式、现代式三类。</span>\r\n\t</p>\r\n\t<p>\r\n\t\t<strong><span></span><span>传统式耳环</span></strong>\r\n\t</p>\r\n\t<p>\r\n\t\t<span></span><span>传统式耳环主要有双丝花耳环、珍珠花形耳环和饰钻如意耳环。其结构有套穿式和插针式。</span>\r\n\t</p>\r\n\t<p>\r\n\t\t<strong><span></span><span>改良式耳环</span></strong>\r\n\t</p>\r\n\t<p>\r\n\t\t<span></span><span>改良式耳环是在传统式基础上改良创新，如把耳环做成福、禄、寿、喜等吉祥文字，经艺术加工，别具风格。</span>\r\n\t</p>\r\n\t<p>\r\n\t\t<strong><span></span><span>现代式耳环</span></strong>\r\n\t</p>\r\n\t<p>\r\n\t\t<span></span><span>现代式耳环内容广泛而抽象，如爆炸式、闪光式、回旋式等，其造型色调多姿多彩，尽收眼底。</span>\r\n\t</p>\r\n\t<p>\r\n\t\t<strong><span></span><span>耳环的选择主要应与脸型和肤色以及服装相匹配</span></strong>\r\n\t</p>\r\n\t<p>\r\n\t\t<span></span><span>（1）椭圆型脸，以选能适当增加脸部阔度感的耳环为佳，例如大方形或大圆形耳环，其他非长型耳环亦可。</span>\r\n\t</p>\r\n\t<p>\r\n\t\t<span></span><span>（2）戴眼镜的女士，宜选色泽淡雅、结构简单的耳环，不宜选戴色彩缤纷、结构复杂的款式。</span>\r\n\t</p>\r\n\t<p>\r\n\t\t<span></span><span>（3）圆型脸，不宜选择增加脸部阔度感的耳环，那些过分夸张式的垂挂式耳环也不合适。可选叶型、长型、之字型、长方型，眼泪型等，传统的贴耳型珠宝钻石耳环亦可。</span>\r\n\t</p>\r\n\t<p>\r\n\t\t<span></span><span>（4）颈项较长的女士，以选垂挂式耳环为佳；相反颈短者，不宜选用垂挂式，而以选戴贴耳式更佳。</span>\r\n\t</p>\r\n\t<p>\r\n\t\t<span></span><span>（5）方型脸，可选具有卷曲线条的耳环，或选圆形、钮型、垂挂式均可，但不宜选方型耳环。</span>\r\n\t</p>\r\n\t<p>\r\n\t\t<span></span><span>（6）耳环色泽与肤色的配合不可忽视。肤色较白的，适于佩戴淡红色或暗红色耳环；黄色皮肤者宜戴红色耳环；肤色较黑者可选白色耳环；古铜肤色者宜选戴白色耳环；金黄色耳环对于各种肤色都可选用。</span>\r\n\t</p>\r\n\t<p>\r\n\t\t<span></span><span>（7）心型脸，以选能增加脸的下半部的阔度感的耳环为宜，如三角型、大圆型等夸张的垂挂式耳环均可。</span>\r\n\t</p>');--end
 INSERT INTO tq_article_txt VALUES(22,'<p style=\"text-align:center;\">\r\n\t<p class=\"cover\" align=\"center\">\r\n\t\t<img src=\"http://img04.taobaocdn.com/tps/i4/T1nuk8XglXXXb1upjX.jpg\" width=\"353\" height=\"353\" /> \r\n\t\t<div align=\"left\">\r\n\t\t\t&nbsp;\r\n\t\t</div>\r\n\t\t<p>\r\n\t\t\t<br />\r\n\t\t</p>\r\n\t\t<div class=\"cover\">\r\n\t\t\t<span class=\"btn-tts\"></span>\r\n\t\t</div>\r\n\t\t<p>\r\n\t\t\t<strong>挑选丝袜三要素：</strong> \r\n\t\t</p>\r\n\t\t<p>\r\n\t\t\t1、选择好质地：袜子好像内衣，采用不同质地编织成的袜子穿在身上的感觉也不同，价格也相差很大。一般来说100%日本鹅绒的为高档丝袜，平滑 柔软，弹性极佳。无论是加厚还是超薄都十分耐穿。采用包芯丝材料制成的为中档丝袜，具有超高弹性，还不易勾丝。普通15D丝袜，虽然紧帖性、柔滑性不及前 两者，但价格实惠，因而也深受喜爱。&nbsp;\r\n\t\t</p>\r\n\t\t<p>\r\n\t\t\t2、选色调：由于短裙的风行，袜子在视觉上的分量已越来越大，可以说，袜子的色调体现着一个女性的气质。肤色是永恒的色彩，可以和各种时装搭配；灰色自然大方，以配素色服装为佳。体态优美的女性，不妨选高档电子提花袜，既显示时尚气息，又可更好地衬托优美的体态。&nbsp;\r\n\t\t</p>\r\n\t\t<p>\r\n\t\t\t3、看光泽：看得见肌肤才是好丝袜：好的丝袜，即使是秋冬穿的厚丝袜，也应有光泽，令美腿若隐若现并透出朦胧的肤色。反之腿就会像木头做的似的，生硬，无动感。&nbsp;\r\n\t\t</p>\r\n\t\t<p>\r\n\t\t\t<strong>选购好丝袜的小提示：</strong> \r\n\t\t</p>\r\n\t\t<p>\r\n\t\t\t当然好丝袜也应与腿部高度契合。还有要注意的就是如果腿部皮肤有过敏史的女性，应适当选择质地为纯棉或者透气性好的袜子，同时，也应当考虑夏天阳光强烈，出汗多等因素。 同时，女性在购买长统袜时，还要注意丝袜在包装袋内所呈现的颜色，要比穿在腿上时的颜色深。因此，在挑选时要选择比自己所喜欢的颜色略深一些的。\r\n\t\t</p>');--end
 INSERT INTO tq_article_txt VALUES(23,'<p align=\"center\">\r\n\t<img id=\"articlePicImg\" class=\"img_border\" border=\"1\" alt=\"点击查看下一张\" src=\"http://img2.trends.com.cn/upload/130319/13031915433781717.jpg\" /> \r\n\t<div align=\"left\">\r\n\t\t&nbsp;\r\n\t</div>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p align=\"center\">\r\n\t\t凯特·莫斯大摇大摆地走在伦敦的街头,灰色的连身裤,一件裘皮大衣和一个五彩缤纷的蛇皮袋。\r\n\t</p>\r\n\t<p align=\"left\">\r\n\t\t&nbsp;\r\n\t</p>\r\n\t<p align=\"left\">\r\n\t\t&nbsp;\r\n\t</p>\r\n\t<p>\r\n\t\t　　Kate Moss刚刚回归Louis Vuitton 2013秋冬的T台，毫无疑问，我们喜爱模特们在T台上的走秀，也许我们更欣赏她们演绎自己的风格。每个女孩都有她自己独特的审美，我们爱她们的原因也各有不同。我们感谢Kate Moss证明了豹纹单品可以伴随着一切。Miranda Kerr 可以把运动鞋穿的高雅，Rosie Huntington-Whiteley玩转层次混搭。T台场外性感的维多利亚的天使超模们，也可以演绎轻松休闲的性感，超模Cara Delevingne则是朋克英伦风，Karlie Kloss是经典的美国风。欧美街拍一向是引领潮流的风向标，一起来看看我们所喜爱的超模们在街拍时刻带给我们什么样的风格。\r\n\t</p>\r\n\t<p align=\"left\">\r\n\t\t&nbsp;\r\n\t</p>\r\n\t<p align=\"right\">\r\n\t\t&nbsp;\r\n\t</p>');--end
 INSERT INTO tq_article_txt VALUES(24,'<p style=\"text-indent:2em;\">\r\n\t3月30日，随着第8届中国超级模特大赛总决赛桂冠的尘埃落定，伴着北京饭店外徐徐怒放的玉兰花，梅赛德斯-奔驰中国国际时装周2013/2014秋冬系列发布圆满结束。从中国国际时装周创立之初坚持至今的“原创、首发”已经得到了国内外品牌和设计师的充分认可，矗立于中国潜力巨大市场之前的这个时尚桥头堡，被越来越多的时尚从业者认可重视。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n　　在本届梅赛德斯-奔驰中国国际时装周上，中国的设计力量在设计理念、造型色彩、形象塑造、文化传递等方面体现出了国际水准，本土参与品牌与设计师的设计水准和品质相当突出，特别是设计师在细节上的精益求精，正在与国际同步。更多地走出去，更多地引进来，更多的交流与合作并存，才能让中国国际时装周变得真正国际化，而不仅仅只是个独自玩乐的大派对。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(25,'<p>\r\n\t　　凤梨和菠萝并非同一种水果，而是长得相似的两种水果。两者的区别有：凤梨的叶子不带齿、味甜，菠萝的叶子带齿，果肉要用盐水泡过才能吃。<br />\r\n<br />\r\n<strong>菠萝的营养和食疗价值有哪些</strong><strong>?</strong><br />\r\n<br />\r\n　　菠萝属于凤梨科凤梨属多年生草本果树植物，营养生长迅速，生产周期短，年平均气温23摄氏度以上的地区终年可以生长。<br />\r\n　　菠萝果实营养丰富，果肉中除含有还原糖、蔗糖、蛋白质、粗纤维和有机酸外，还含有人体必需的维生素C、胡萝卜素、硫胺素、尼克酸等维生素。以及易为人体吸收的钙、铁、镁等微量元素。菠萝果汁、果皮及茎所含有的蛋白酶，能帮助蛋白质的消化，增进食欲；医疗上有治疗多种炎症、消化不良、利尿、通经、驱寄生虫等效果，对神经和肠胃有一定的医疗作用。<br />\r\n<br />\r\n<strong>凤梨的营养和食疗价值有哪些</strong><strong>?</strong><br />\r\n<br />\r\n　　凤梨为凤梨科植物凤梨的果实。台湾府志中解释：凤梨，叶薄而阔，而缘有刺，果生於叶丛中，果皮似波罗蜜而色黄，味甘而微酸，先端具绿叶一簇，形似凤尾，故名。<br />\r\n　　凤梨果实含丰富的营养成分，糖类、碳水化合物、有机酸、氨基酸、尼克酸、蛋白质、脂肪及维他命A、B、C、G;核黄素、胡萝卜素、硫胺素、膳食纤维;无机成分如：铁、镁、钾、钠、钙、磷等。\r\n</p>\r\n<p>\r\n\t<br />\r\n</p>\r\n<p>\r\n\t<strong><span>菠萝和凤梨的区别：</span></strong> \r\n</p>\r\n<p>\r\n\t<strong><span></span></strong>&nbsp;\r\n</p>\r\n<p>\r\n\t<strong><span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span></strong>通常菠萝的栽培品种分4类，即卡因类、皇后类、西班牙类和杂交种类。有的地方管菠萝叫凤梨，其实指的是卡因类，<span>是世界上种植最广的。著名的嘟乐就是这一种。</span><span>严格地凤梨是菠萝的一个品种，不是所有的菠萝都叫凤梨的。</span> \r\n</p>\r\n<p>\r\n\t<span></span>&nbsp;\r\n</p>\r\n<p align=\"center\">\r\n\t<img style=\"width:272px;height:286px;\" alt=\"\" src=\"/member/portal/demo/201304/17213821fxnf.jpg\" width=\"272\" height=\"301\" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\r\n</p>\r\n<p align=\"center\">\r\n\t<strong>凤梨&nbsp;&nbsp; </strong>\r\n</p>\r\n<p align=\"center\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p align=\"center\">\r\n\t<br />\r\n</p>\r\n<p align=\"center\">\r\n\t<img style=\"width:255px;height:283px;\" alt=\"\" src=\"/member/portal/demo/201304/17213823s3q6.jpg\" width=\"344\" height=\"733\" />&nbsp;\r\n</p>\r\n<p align=\"center\">\r\n\t<strong>菠萝</strong> \r\n</p>\r\n<p>\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p>\r\n\t<span><span><strong><span><span><strong></strong></span></span>区别：</strong></span></span> \r\n</p>\r\n<p>\r\n\t<span><span>1）叶子凤梨的叶子没有齿，菠萝的叶子有齿。</span></span> \r\n</p>\r\n<p>\r\n\t<span><span></span></span>2）菠萝身凤梨身上的网格呈圆形，菠萝身上的网格有明显的棱角。凤梨的种窝浅，菠萝的种窝深。\r\n</p>\r\n<p>\r\n\t3）味道凤梨的味道更甘甜 菠萝有点涩。\r\n</p>\r\n<p>\r\n\t4）吃法凤梨可以直接食用菠萝需要泡一下盐水再食用；\r\n</p>\r\n<p>\r\n\t<br />\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(26,'<p style=\"text-indent:2em;\">\r\n\t作为消费者，更应以理性、成熟的心态和观念来对待家庭装修这一人生的大事，不要贪小便宜吃大亏。\r\n</p>\r\n<p>\r\n\t<br />\r\n</p>\r\n<p align=\"center\">\r\n\t<img alt=\"\" src=\"/member/portal/demo/201304/17225330nqjs.jpg\" /> \r\n\t<div align=\"left\">\r\n\t\t&nbsp;\r\n\t</div>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t1、查验施工人员上岗证\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t就算是正规家装公司也都会存在一些施工问题，其问题主要出现在施工项目监管和施工人员的技术上。所以对于消费者来说，在装修人员到家里以后，首先要做的就是查看施工人员是否持有上岗证，再准备施工。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t2、警惕“野装修”骗术\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t某装饰总经理：我们不排除野装修中也有一些不错的，但这毕竟只是少数。消费者选择野装修是一种冒险行为。据我了解：野装修中欺诈、蒙骗的情况非常严重，他们在施工中不是在质量上就是在数量上大做文章。偷工减料、偷梁换柱、以次充好也是他们的一贯招数。最严重的是消费者在上当受骗后连维权都难，售后服务根本得不到保障。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t面对野装修的种种诱惑，消费者一定要理智地作出选择，千万不要被他们的骗术所蒙蔽。最简单的方法就是在装修之前多比较比较，不要盲目下决定，更不要轻易相信野装修所谓的“优惠”。在装修之前将装修项目罗列清楚，比完单价比数量，最后比总价。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t随着时代的变迁，消费者消费的日趋理性化，野装修也在不断变身，很多野装修已经办了营业执照，找了办公室，甚至连名字也叫得比较响亮了，什么某某高端设计室……这时，消费者更应该擦亮眼睛，增加自己的分辨力，在分辨装饰公司时，首先要看资质，其次是注册资金。大部分“野装修”都是一人做全活，在资质和规模以及工程质量上和正规家装公司是没法比的。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t监理支招：揭穿游击队装修的五大骗术\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t陷阱1：工艺等说明模糊不清\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t家装游击队在报价时由于业主更多地会关注单项的价格，而经常忽略工艺说明，尤其是用什么材料、什么规格、什么等级以及甲醛、苯含量等方面的规定。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t支招：一定要当面核实清楚材质、规格和等级，并落实到纸面上，作为合同的附件出现，以免自己追悔莫及，打起官司或申辩的时候没有证据也说不清楚，任由家装游击队随意提供不合格、不环保的材料。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t陷阱2：单项面积容易做手脚\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t一般业主也是关注单项的价格，至于实际的面积一般是大致估计，实际上这一块也是家装游击队做手脚的地方，如果每项面积都稍微增加一些，单项价格又高，那么少则几百，多则几千就出去了。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t支招：单项价格谈定了以后，一定要不怕辛苦，和家装游击队一起把单项的面积尺寸丈量一下，并记下来，落实到纸面上，并算清楚单项的总价格是多少，作为合同的附件，以免到时就面积和尺寸的问题扯皮。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t陷阱3：客户报价单上多欺诈\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t在最后提供给客户的报价单上做手脚。一般情况下，家装游击队与业主之间在最后签订合同前都会来回就报价单修改3-4回，关注的焦点从单价到面积、总价和工艺说明，如果谈得差不多，对方会给业主一个最后给定的样本，要求业主确认并签合同。这时一定要注意不能想当然和以前谈的一样，一些奸诈的家装游击队就会在工艺说明或面积上做些手脚，简化一些工艺或者对某些材质进行偷换。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t支招：在签订合同时，一定要亲自逐项比较和核对相应的条款，是否跟家装游击队谈妥的最后条件一致，不能忽略任何细节，不给奸商留下任何可乘之机。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t陷阱4：进场时提供劣质产品\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t虽然在合同上和报价单上写得很清楚，该用什么材质、什么规格、什么等级的产品，但一些抱有侥幸心态的家装游击队总会铤而走险，改头换面来个以假充真，如果业主不细心的话，劣质材料就这样偷偷进场了。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t支招：材料进场的时候，最好要亲自到场，带上合同和报价单，并请懂行的人或监理到场一起验收。并在合同签订时规定，家装游击队进任何一种材料必须提早一至两天通知业主本人或监理，否则材料出现任何问题责任和误工均由家装游击队负责。一定要坚持原则，不行就是不行。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t陷阱5：水电路数据模糊不清\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t水电路改造最重要，但是对于家装游击队来说，这也是最重要的盈利点。所以在做设计的时候，就要严格控制预算。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t家装游击队都会以水电路改造的具体数字以现场实际数据为准，现在很难估算为理由，拒绝提前告诉你水电改造一共要花多少钱。结果到业主结账的时候，往往发现自己要为这个项目多支付三四千元，这样就非常被动。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t支招：在签订装修合同前，要求设计出示水电路改造图纸，并对照图纸严格计算出下列项目可能发生的数量：电话线改造，电源插座改造，开关面板改造，水路改造，有线电视线路改造，网线改造，并就此算出合理的费用。这样就可以避免增项费用过高了。\r\n\t</p>');--end
 INSERT INTO tq_article_txt VALUES(27,'<p>\r\n\t<strong>一、美金厕纸</strong> \r\n</p>\r\n<p>\r\n\t&nbsp;&nbsp;&nbsp;&nbsp;这个莫非就是现实版的“金钱如粪土”吗？\r\n</p>\r\n<p>\r\n\t<br />\r\n</p>\r\n<p align=\"center\">\r\n\t<img alt=\"\" src=\"/member/portal/demo/201304/18104903l2lr.jpg\" /> \r\n\t<div align=\"left\">\r\n\t\t&nbsp;<br />\r\n<strong>二、铁皮厕纸</strong> \r\n\t</div>\r\n\t<p>\r\n\t\t&nbsp;人家评价左边那个“又实用，又能防身，还是回收产品。环保。还能多功能。下面用完了，还能刮刮胡子。”\r\n\t</p>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p align=\"center\">\r\n\t\t&nbsp;<img alt=\"\" src=\"/member/portal/demo/201304/18105048v01f.jpg\" />&nbsp;\r\n\t</p>\r\n\t<p>\r\n\t\t<br />\r\n<strong>三、数字游戏厕纸</strong> \r\n\t</p>\r\n\t<p>\r\n\t\t&nbsp;&nbsp;&nbsp;&nbsp;万一那个人没有做出来就不出来了，外面等的还人不憋死。。。\r\n\t</p>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p align=\"center\">\r\n\t\t&nbsp;<img alt=\"\" src=\"/member/portal/demo/201304/18105109pyt9.jpg\" />&nbsp;\r\n\t</p>\r\n\t<p align=\"center\">\r\n\t\t<br />\r\n\t</p>\r\n\t<p>\r\n\t\t<strong>四、恐怖小说厕纸</strong> \r\n\t</p>\r\n\t<p>\r\n\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;要是还沒看完就被人用了肯定会气死了，小女生还是别用了吧\r\n\t</p>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p align=\"center\">\r\n\t\t&nbsp;<img alt=\"\" src=\"/member/portal/demo/201304/18105123oi5d.jpg\" />&nbsp;\r\n\t</p>\r\n\t<p>\r\n\t\t<br />\r\n<strong>五、折纸厕纸</strong> \r\n\t</p>\r\n\t<p>\r\n\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;哈~好打发时间，出来能教自个孩子了~\r\n\t</p>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p align=\"center\">\r\n\t\t&nbsp;<img alt=\"\" src=\"/member/portal/demo/201304/181051394sau.jpg\" />&nbsp;\r\n\t</p>\r\n\t<p>\r\n\t\t<br />\r\n<strong>六、绿色和平厕纸</strong> \r\n\t</p>\r\n\t<p>\r\n\t\t&nbsp;&nbsp;&nbsp;&nbsp;爱护自然从我做起，少用两张吧，如果能用手，那我也不反对。。。\r\n\t</p>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p align=\"center\">\r\n\t\t&nbsp;<img alt=\"\" src=\"/member/portal/demo/201304/181051575c6v.jpg\" />&nbsp;\r\n\t</p>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p>\r\n\t\t<strong>七、情人节日厕纸</strong> \r\n\t</p>\r\n\t<p>\r\n\t\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;爱她，就给Ta用这个~\r\n\t</p>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p align=\"center\">\r\n\t\t<img alt=\"\" src=\"/member/portal/demo/201304/18105211uykm.jpg\" />&nbsp;&nbsp;\r\n\t</p>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p>\r\n\t\t<strong>八、仙人掌厕纸</strong> \r\n\t</p>\r\n\t<p>\r\n\t\t&nbsp;&nbsp;&nbsp;&nbsp;谁发明的、谁用。。。\r\n\t</p>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p align=\"center\">\r\n\t\t<img alt=\"\" src=\"/member/portal/demo/201304/18105228k6rm.jpg\" />&nbsp;&nbsp;\r\n\t</p>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p>\r\n\t\t<strong>九、犯罪现场厕纸</strong> \r\n\t</p>\r\n\t<p>\r\n\t\t&nbsp;&nbsp;&nbsp;&nbsp;感觉很不吉利。。。\r\n\t</p>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p align=\"center\">\r\n\t\t&nbsp;<img alt=\"\" src=\"/member/portal/demo/201304/18105243k8xq.jpg\" />&nbsp;\r\n\t</p>\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p>\r\n\t\t<strong>十、黑色厕纸</strong> \r\n\t</p>\r\n\t<p align=\"left\">\r\n\t\t&nbsp;&nbsp;&nbsp;&nbsp;哥们，你敢用我就敢送。。。\r\n\t</p>\r\n\t<p align=\"center\">\r\n\t\t<br />\r\n\t</p>\r\n\t<p align=\"center\">\r\n\t\t<img alt=\"\" src=\"/member/portal/demo/201304/18105302exc3.jpg\" /> \r\n\t\t<div align=\"left\">\r\n\t\t\t&nbsp;\r\n\t\t</div>\r\n\t\t<p>\r\n\t\t\t<br />\r\n\t\t</p>\r\n&nbsp;\r\n\t\t<p>\r\n\t\t\t<br />\r\n\t\t</p>');--end
 INSERT INTO tq_article_txt VALUES(28,'<p>\r\n\t<strong>Part1：墙面验收基本点 装修千万勿忽略</strong> \r\n</p>\r\n<p>\r\n\t　　墙面验收是装修验收的基本点，墙面外观、开裂情况、垂直度与平整度都会影响到整个墙面的装修效果，因此不可忽略。\r\n</p>\r\n<p style=\"text-align:center;\">\r\n\t<img title=\"买房怎能不验房？菜鸟须知的验房知识\" alt=\"买房怎能不验房？菜鸟须知的验房知识\" src=\"http://img0.pchouse.com.cn/pchouse/1304/09/263692_6.jpg\" /> \r\n</p>\r\n<p>\r\n\t<strong>1、检查墙体是否开裂</strong> \r\n</p>\r\n<p style=\"text-align:center;\">\r\n\t<strong><img title=\"买房怎能不验房？菜鸟须知的验房知识\" alt=\"买房怎能不验房？菜鸟须知的验房知识\" src=\"http://img0.pchouse.com.cn/pchouse/1304/09/263692_5.jpg\" /></strong> \r\n</p>\r\n<p>\r\n\t　　针对于毛胚房，必须着重检查墙面是否有色差、开裂，有没有一些脱皮、透底，或者说一些锈点的质量问题，有这些观感上的问题需要安排整改。对于天花板检查时，要注意天花板是否有开裂情况，如果开裂，后期整改必须沿着缝刮开5厘米把底面全部铲掉，铲掉以后再贴绷带或者是布进行一些加固的措施。\r\n</p>\r\n<p>\r\n\t<strong>2、敲击检查墙面空鼓</strong> \r\n</p>\r\n<p style=\"text-align:center;\">\r\n\t<img title=\"买房怎能不验房？菜鸟须知的验房知识\" alt=\"买房怎能不验房？菜鸟须知的验房知识\" src=\"http://img0.pchouse.com.cn/pchouse/1304/09/263692_2.jpg\" /> \r\n</p>\r\n<p>\r\n\t　　检查墙面的时候，用伸缩响鼓锤敲一下，听一下声音，看是否有空鼓。如果说有空鼓的话，可能会有整体面层脱落或者说开裂这样的风险。则必须要加以重视。\r\n</p>\r\n<p>\r\n\t<strong>3、墙体垂直度检查</strong> \r\n</p>\r\n<p style=\"text-align:center;\">\r\n\t<img title=\"买房怎能不验房？菜鸟须知的验房知识\" alt=\"买房怎能不验房？菜鸟须知的验房知识\" src=\"http://img0.pchouse.com.cn/pchouse/1304/09/263692_4.jpg\" /> \r\n</p>\r\n<p>\r\n\t　　用阴阳角尺来检测所有阴阳角的方正度，按照国家要求阴阳角的偏差不能超过3毫米。超过3毫米则视为墙面不够垂直。\r\n</p>\r\n<p>\r\n\t<strong>4、墙面平整度检查</strong> \r\n</p>\r\n<p style=\"text-align:center;\">\r\n\t<strong><img title=\"买房怎能不验房？菜鸟须知的验房知识\" alt=\"买房怎能不验房？菜鸟须知的验房知识\" src=\"http://img0.pchouse.com.cn/pchouse/1304/09/263692_3.jpg\" /></strong> \r\n</p>\r\n<p>\r\n\t　　用2米靠尺和塞尺来检查墙面的平整度，国家规范要求墙面平整度偏差不得超过3毫米，超过3毫米的视为平整度不足。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(29,'　　参观海南省博物馆，笔者意外见到了海南岛黎族人制作的、有着非常古老历史和传播范围的树皮衣。<br />\r\n<br />\r\n　　衣食住行，以衣为首，足见人类对于衣服这件事的重视。考古发现在数万年前，人类已穿上极其华丽的衣服。按衣服原料制作技术体系的差异，初步可区分为纺织及无纺织两大系统。纺织布是由经纬线组合，由织机加工而成的布。无纺织布简称无纺布，是由原料经化学的作用或物理机械方法制成的布料。树皮布是植物性的无纺布之一。<br />\r\n<br />\r\n　　现今考古发现最早的纺织证据见于近东地区伊拉克，在距今9000年的黏土上压有纺织品的痕迹。中国的黄河及长江流域则在距今7000年前已流行用纺轮纺线用，利用葛或麻的纤维，织制衣服。河姆渡遗址，出土了近7000年前的纺织工具。<br />\r\n<br />\r\n　　人们对用动物的皮制作衣服并不陌生，而植物性树皮布衣服出现的年代更早，约在数万年前已出现。可以说，直到十六世纪欧洲文化向世界扩张前，地球各大洲广泛生活着穿树皮布的民族。<br />\r\n<br />\r\n　　据古代典籍记载，至少在3000年以前海南岛便出现了树皮布。由晋人裴洲《东观汉记》一书可知，汉代已有用树皮布做冠的记载，当时边疆少数民族还以树皮布制衣裳、被褥。由于树皮布本身容易腐烂，难以作为历史证据久远留存，唯有制作树皮布的工具石拍遗留了下来。<br />\r\n<br />\r\n　　越来越多的考古学证据支持世界树皮布文化可能起源于中国南方。约七千年前的深圳咸头岭遗址，出土了不同时期的树皮布石拍。碳十四测年石拍上限，在6600年前或者更早。这是迄今所知世界上最早的树皮布石拍，比东南亚地区已发现的石拍早了3000多年。更令人讶异的是咸头岭遗址的早晚阶段，均出土圆角长方形石拍，早期的沟槽痕均为两面，晚期单面拍面比较常见，这不仅反映咸头岭遗址早晚期石拍的差异，更说明珠江口树皮布石拍渊远流长及自身演化进展的特质。<br />\r\n<br />\r\n　　东亚、太平洋岛屿以至中美洲地区树皮布的传统，是人类衣服文化重要的一环。海南省白沙、昌江、陵水、乐东及五指山等地，都是树皮布文化的范围。这些地区也是黎族自治县。但在我国民族地区会制作树皮布的民族不仅限于黎族、海南岛的苗族，云南的傣族、哈尼族、基诺族、克木人、布郎等族也曾经制作和使用树皮布。台湾地区树皮布文化的传统渊远流长，发现树皮布石拍的遗址，几乎分布全岛的范围，共有36处，暗示了树皮布的传统曾一度在台湾岛上有过全面繁荣的时期。就目前所见，台湾的树皮布石拍并非土生土长，更可能是跨越海洋传入岛上的一种外来文化。上世纪50年代越南中部山区的一些民族仍穿着树皮衣服，而在菲律宾的伊特拜亚特岛上，节日期间仍可见居民穿着树皮衣服。<br />\r\n<br />\r\n　　在学术领域，东南亚及太平洋岛屿上一亿五千万南岛语民族的祖先来源问题，一直是东亚地区考古学、语言学与民族学等研究的聚焦所在。树皮布技术称得上是南岛语民族最重要的文化要素之一。如果有一条传播路径的话，应该是由南中国起源，扩散至中南半岛，然后到达菲律宾，再至中美洲的传播途径。<br />\r\n<br />\r\n　　从历史发展的大循环来看，不同的制衣技术应该有相当长的时间是分庭抗礼的。距今7000多年前或更早的阶段，黄河及长江流域流行纺轮和织机，显示了纺织布衣服的流行；而珠江水系一带，出现了发达的树皮布石拍。而在世界范围内，考古发现的石拍分布范围，大致在北纬30°以南的地域。这一地域亦与桑科植物之分布范围基本上一致。一般来说，热带榕属植物、楮树、环纹榕、见血封喉等植物的树皮均可用作树皮衣的制作及使用。<br />\r\n<br />\r\n　　将一种看似简单的文化现象放在一个广阔的地域里去研究，总会找到共同的文化渊源，即使是树皮布文化，原来也曾那样的风光旖旎。树皮布和同样发源于中国的纺织丝布，都是具有世界性影响的重大发明。丝织品由丝绸之路，自中国西向在陆路上流通远达西欧，最后进入英伦三岛。树皮布技术自南中国南向经中南半岛，席卷东南亚岛屿后，从海路上跨过太平洋岛屿进入中美洲。而且树皮布与造纸技术有着密切关系，在中美洲树皮布更广泛被用作纸的书写载体，对中美洲的历史影响至为深远。');--end
 INSERT INTO tq_article_txt VALUES(30,'<p>\r\n\t　　北京市统计局、国家统计局北京调查总队昨日联合发布的数据显示，北京一季度房地产销售增长较快，商品住宅销售面积同比增长超八成，二季度恐进入“放空期”。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　此外，经初步核算，北京一季度实现地区生产总值（GDP）4101.2亿元人民币，按可比价格计算，同比增长7.9%。增速较去年全年提高0.1个百分点。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　■房地产\r\n</p>\r\n<br />\r\n<p>\r\n\t　　“国五条”出台后购房需求提前释放\r\n</p>\r\n<br />\r\n<p>\r\n\t　　一季度，北京市完成房地产开发投资494.1亿元，同比增长9.4%。北京商品房销售面积396.6万平方米，同比增长82.7％；其中，商品住宅销售面积300.7万平方米，增长80.7％。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　北京市统计局副局长、新闻发言人于秀琴表示，商品房销售之所以出现大幅增长，主要有两方面的原因。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　一是基数较低，去年一季度同期是下降态势，而商品房销售量回升出现在去年下半年，今年一季度的销售量是在去年较低的基础上上涨，所以幅度较高。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　二是因为在“国五条”出台后，在政策预期和市场预期的双重作用下，部分购房需求提前释放。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　展望二季度，于秀琴说，“国五条”的出台以及对相关细则的预期导致北京今年2-3月的商品房销量猛增，4月可能出现一个“放空期”。她说，房地产调控政策是为了打击不合理、投机的需求，在一定程度上缓解房地产市场出现持续过热的现象，今年商品住宅新开工面积同比下降与其也有关联。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　对于未来的整体经济走势，于秀琴预计，今年经济增长速度将要好于2012年，一季度情况基本符合预期。虽然综合各方面的因素来看，由于有些领域的经计量提前释放，后续压力很大，如房地产业，但后期经济上行的势头要略微大于下行压力，全年完成8%左右的增长目标可以预期。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　■其它数据\r\n</p>\r\n<br />\r\n<p>\r\n\t　　餐饮收入降4.2%\r\n</p>\r\n<br />\r\n<p>\r\n\t　　一季度，北京实现社会消费品零售额2008.8亿元，比上年同期增长9.4%。增速较上年同期回落4.9%个百分点。按消费形态分，商品零售额增长11.1%，餐饮收入下降4.2%。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　此前中央强调转变作风，这将多大程度影响消费品市场？\r\n</p>\r\n<br />\r\n<p>\r\n\t　　对此，于秀琴指出，住宿餐饮业收入下降在预期之内，但对消费市场的影响非常微小。于秀琴表示，相关涉及行业如娱乐教育、住宿餐饮业，二者虽然一季度增速都较低，但两个行业在总体经济中占比非常小，住宿餐饮占比只在2%左右，其收入下降对北京经济的影响只是零点零几个百分点。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(32,'<p style=\"text-indent:2em;\">\r\n\t&nbsp; 春暖花开万物苏醒，朋友们在愉快的踏春游玩旅途中，别忘记给身体做好保健哦。今天小编特意邀请了几位养生专家来分享养生秘诀\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t重庆翠京元有机农业专业合作社副理事长林小平：春季多吃蔬菜。如何判断有机农产品？主要看“两个报告和一证一号”，即：环境监测报告、产品检测报告、有机证书、国家认监委的统一有机认证编号。有机蔬菜最好生吃，这样才能保证营养不流失。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t重庆龙人养生美容保健培训学校校长李世洪：现在春季有很多人都不懂养生，以最平常的喝水吃饭为例，不要感觉渴了才喝水、感觉饿了才吃东西。因为当你感觉到时，已经是身体告警了，长此以往免疫力会下降，身体的五脏六腑会出现问题，导致疾病发生。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t重庆市百卉生物科技有限公司的总经理徐晓红：很多美女喜欢跟风买护肤品，看别人用了好就去买来用，其实是不对的。春季干燥，多数人的皮肤处于“亚健康”状态，在没养好皮肤以前，抹任何护肤品都是徒劳。注意保湿，皮肤也应该定期做体检，然后对症内调。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t人丹帝品网创始人、百度认证网络营销推广师、百科网三宝百科行家 匡继鹏：推荐一种简单的方法：将西洋参、三七、天麻和灵芝，混合打磨成粉，每天早晚各一小勺用温水冲服，可综合调理身体。但需要注意的是，参头不能吃，因为其中有种成分不太适合所有人。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t重庆新概念营养俱乐部负责人张泽金：春困秋乏，春季要适量运动、均衡营养、快乐心态和充足睡眠。虽然很多人都知道这些养生概念，但要做到不是那么容易。我们希望在全市范围内推广营养早餐，让所有人都能拥有“吃饭养生”这一简单易行的养生方式。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(33,'<p>\r\n\t　　冻豆腐，新鲜的豆腐经过急冻之后，其内部的组织结构发生变化，形态变成蜂窝状，但是营养成分，特别是所含的维生素、蛋白质基本没什么损失，而且豆腐经过冷冻，能够产生一种帮助消耗人体脂肪的酸性物质，能够有效促进肠道和全身脂肪的代谢，达到减肥的效果。\r\n</p>\r\n<br />\r\n<p>\r\n\t<strong>　　冻豆腐的制作方法</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　冻豆腐的制作方法其实很简单，把新鲜的豆腐放在冰箱里冷冻，冷冻成型后，冻豆腐就制作完成啦，随时可以取出来做成各种美食。不过要化开它可费劲了，如果用微波炉解冻要足足10分钟才可以搞定，所以先切好再冻才是上策哦。\r\n</p>\r\n<br />\r\n<p>\r\n\t　<strong>　冻豆腐的多种吃法</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　豆腐的内部有无数的小孔，这些小孔里面都充满了水分。里面的水分结成冰，原来的小孔便被冰撑大了，整块豆腐就被挤压成网络形状。等到冰融化成水从豆腐里跑掉以后，就留下了数不清的孔洞，使豆腐变得象泡沫塑料一样。冻豆腐经过烹调，这些孔洞里都灌进了汤汁，吃起来不但富有弹性，而且味道也格外鲜美可口。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n　一、居家菜 冻豆腐金针汤<br />\r\n<p>\r\n\t　　原料：冻豆腐1块，泡好的黄花50克，榨菜丝15克，金针菇80克\r\n</p>\r\n<br />\r\n<p>\r\n\t　　辅料：肉清汤、盐、胡椒粉各适量，香菜30克\r\n</p>\r\n<br />\r\n<p>\r\n\t　　做法：\r\n</p>\r\n<br />\r\n<p>\r\n\t　　1、冻豆腐解冻，用清水洗净，沥干水分，切小块;黄花及金针菇去蒂，分别洗净、沥干，金针菇对切两半;榨菜洗净;香菜洗净、切小段。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　2、锅置火上，倒入肉清汤烧开，加冻豆腐块煮至入味，依序加入黄花、金针菇、榨菜和盐煮熟，盛入碗中，撒上香菜段和胡椒粉即可。\r\n</p>\r\n<br />\r\n<p>\r\n\t<strong>　　二、白菜炖冻豆腐</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　原料：白菜1/3棵，冻豆腐两块(现在深圳的豆腐越来越小)，肉汤半碗，葱姜蒜各少许，红色小辣椒两个。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　做法：锅中倒油烧至八成热，放入葱姜蒜爆香，放入白菜炒5分钟，放入冻豆腐，加入肉汤。红色小辣椒整个放入。加盐、酱油、水，炖10分钟留有汤汁即可。(我不喜欢吃五花肉，所以用肉汤来炖。也可以先炒大块五花肉之后炒白菜来炖的。)\r\n</p>\r\n<br />\r\n<p>\r\n\t　　此菜是东北常见的家常菜，味道咸香，肉香渗人冻豆腐的空间之中，不腻。汤汁包裹白菜，深入豆腐，香啊!\r\n</p>\r\n三、冻豆腐炖鲢鱼<br />\r\n<p>\r\n\t　　原料：冻豆腐、鲢鱼头、笋干、薏米、葱、姜\r\n</p>\r\n<br />\r\n<p>\r\n\t　　调料：盐、白糖、胡椒粉、料酒\r\n</p>\r\n<br />\r\n<p>\r\n\t　　做法：\r\n</p>\r\n<br />\r\n<p>\r\n\t　　1、将冻豆腐切块，葱切段、姜切片，鱼头洗净，锅中放少许油，下葱姜煸出香味后放入鱼头煎成两面金黄，烹少许料酒，放入笋干和薏米;\r\n</p>\r\n<br />\r\n<p>\r\n\t　　2、锅中加适量开水，放入豆腐，大火炖20-30分钟，加盐、胡椒粉、白糖调味，出锅撒上葱花即可。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　特点：冻豆腐软韧，富有弹性，汤鲜清澈，开胃爽口。\r\n</p>\r\n<br />\r\n<p>\r\n\t<strong>　　四、酸菜粉丝冻豆腐</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　原料：冻豆腐、酸菜、龙口粉丝、口蘑、松蘑、香菜\r\n</p>\r\n<br />\r\n<p>\r\n\t　　做法：将冻豆腐切成 <br />\r\n2厘米见方的块，下开水锅中焯透，用凉水漂凉。将口蘑、松蘑分别水发留原汤。洗净泥沙。粉丝用温水泡软，剪成20厘米长的段，酸菜去外层老帮，逐片片成极薄长片，顺长切丝，越细越好。香菜洗净切段装小盘中。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　取火锅一个，松蘑码底，粉丝放上，再将酸菜码上，下入豆腐，最上层是口蘑。炒锅上火，下口蘑，松蘑，原汤烧开，加精盐，胡椒粉，花生油倒入火锅中，加盖。上席时点燃木炭，同上小盘香菜段随意下火锅菜上调味增香。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　菜品特点：软，嫩，鲜香，滑爽，汤醇味酸可口。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(34,'<p>\r\n\t　　为了弄清这些问题，《生命时报》记者采访了卫生部中日友好医院中医呼吸科教授晁恩祥，中国农业大学食品学院副教授范志红，西安体育学院运动医学教研室副教授苟波，首都医科大学附属北京地坛医院感染性疾病诊疗中心教授李兴旺、感染科教授蔡皓东，解放军总医院呼吸科教授刘又宁，以及天津药物研究院李红珠副研究员，并综合世界卫生组织和中国疾病预防控制中心给出的指导意见，为您提供详细的预防办法。\r\n</p>\r\n<br />\r\n<p>\r\n\t<strong>　　起居</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　1.六步搓洗法洗手40秒。晁恩祥教授表示，生活中的病毒和细菌种类繁多，任何抑菌洗手液都只能起到一定的抑菌作用，不能单独预防某种疾病。从保证个人卫生方面考虑，抑菌洗手液或肥皂可以加强清洁效果。世卫组织建议，在准备食物前、中、后，吃东西前，使用卫生间后，处理动物或其排泄物后，手脏时，照顾家中病人时要洗手。洗手时应对指尖、指缝、拇指进行搓洗，使用六步搓洗法，洗手40秒或以上。第一步，掌心相对，手指并拢，互相摩擦；第二步，手心对手背，沿指缝互相搓洗；第三步，掌心相对，双手交叉沿指缝互相摩擦；第四步，两手互握，互相摩擦；第五步，一手握另一手拇指，在掌心旋转摩擦；第六步，指尖摩擦掌心。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　2.一周给家里做一次扫除。李红珠博士认为，目前H7N9禽流感未达到流行态势，如果不是从事接触家禽的工作，就没必要对家里进行消毒。过多使用消毒液，对环境也会造成污染。但随着气温的升高，病菌和病毒开始活跃，需要定期对家里进行大扫除，比如一周一次等。平时可以在家备上一瓶84消毒液，以备不时之需。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　3.上午10点到下午3点间通风半小时。虽然很多城市的空气质量不佳，但可以选择车流量较小且阳光充沛的时间开窗。李红珠建议，最好能在每天上午10点到下午3点之间，选择阳光好的时段开窗通风半小时；如果不能，也可选择在晚上下班后、车流量变小后通风半小时。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　4.回家后换衣换鞋。李红珠说，这是一种好的生活习惯，对预防禽流感作用不大。但养成进门换鞋换衣服的习惯，可以减少灰尘与致病菌的带入，有助于保持家居环境的清洁。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　5.出现早期流感症状就可去就诊。不论是中药板蓝根，还是西药达菲都无法起到预防禽流感的作用。李兴旺教授指出，达菲是一种治疗性药物，可在流感发病两天内迅速控制病情，但没有预防作用。大家不需要吃药预防流感。一旦出现流感早期症状，如咳嗽、发烧、全身肌肉酸痛等，可去医院就诊。只要在发展为肺炎之前采取正确的治疗举措，就没什么可怕的。\r\n</p>\r\n　餐饮<br />\r\n<p>\r\n\t　　1.吃肉一定要煮熟。世卫建议，吃煮熟的食物不会传播流感病毒。蔡皓东表示，禽流感病毒对外界环境的抵抗力不强，在100摄氏度的高温中，病毒1分钟内即可被消灭；70摄氏度时，几分钟便可被杀死。一些喜欢吃半生肉质的人最好改吃全熟的，烹调时保证肉质没有粉色的半生部分。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　2.鸡蛋买回家洗洗。范志红指出，最好选择经过表面消毒的干净蛋，这些蛋外表经过消毒，不容易传染病毒细菌。如果选择菜市场的普通鸡蛋，挑选时不要用手直接抓，套个塑料袋再接触。无论蛋壳上有没有粪便和羽毛，接触后都要洗手；剥下的蛋壳要立刻丢进垃圾桶。另外，建议存放鸡蛋前先清洗一下，最好放到密封的盒子里单独存放。鸡蛋也要烹饪成全熟再吃，暂时不吃开水冲蛋或蛋黄不熟的鸡蛋。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　3.养成良好的厨房卫生习惯。入厨之前要认真洗手，厨房里面的生熟食物分开存放，生肉和熟食要分别使用不同的案板刀具，接触过生肉、生鱼、生蛋之后要洗手、刷水池。厨房里的抹布、筷子笼等要定期高温消毒。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　4.饮食多样化，均衡营养。在日常饮食中要注意保证充足的蛋白质摄入，注意鱼虾、蛋、奶、及豆类的摄入；多吃富含维生素A的食物，如深色蔬菜和水果；注意锌和铁的摄入，吃一些动物肝脏、黑木耳、干果等。多吃清淡少盐的食物，多喝水。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p>\r\n\t<strong>　　运动</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　1.坚持有氧运动。苟波说，适当的体育锻炼能增强身体抵抗力，推荐的运动是快走、慢跑和游泳。一些人喜欢跳舞，最好选择在公园或广场等比较开阔的地方，少去密闭的室内，如果舞伴中有感冒者最好远离。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　2.运动强度中等适中。苟波指出，春天早晚温差大，运动强度不要太大，中等温和的强度最合适，即在运动后感觉稍累、气喘。也可以计算心率来测量强度，一般运动后心率最好不要超过170减去年龄的数值。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　3.不要带病运动。有人觉得感冒后一身大汗可以缓解症状，其实不然。即便是轻度感冒后，也应避免强度大的运动。因为高强度锻炼，虽然可以暂时缓解症状，但由于运动后免疫细胞会进入休息状态，不利于抵御感冒病毒，可能令原症状出现反弹，甚至引发其他疾病。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p>\r\n\t<strong>　　出行</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　目前H7N9禽流感的感染源及传播模式尚不能确定，为了防止感染，可以在生活中选择一些更安全和卫生的出行方式。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　1.外出时远离有禽类的区域。刘又宁建议，到菜市场时，应避免接触活动物，少去有生禽宰杀的集市。城市中有些广场可以喂鸽子，一些公园养有孔雀。外出游玩时，最好远离这些地点，特别是抵抗力较低的老人、小孩、孕妇等。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　2.外出就餐少吃路边摊。范志红提醒，尽量不在路边摊或卫生条件差的餐馆就餐。餐具可以用开水烫一下再用。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　3.出行戴口罩，骑车最好再戴上帽子。外出戴口罩是一种预防普通流感的方式，如果你已经患上感冒，这更是尊重别人的一种表现。为了降低疾病传染的可能，在咳嗽或打喷嚏时，最好用医用口罩、纸巾、袖子、肘部遮盖口鼻，用过的纸巾在使用后尽快扔入有盖垃圾箱，在接触到呼吸道分泌物后，及时洗手。为了保护自己，在别人打喷嚏时，最好能转头避一下。灰尘中也能携带一些病菌，因此需要长时间处在室外的人，或者骑车在外的人，除戴口罩外，最好还能戴上帽子，以防头发上聚集灰尘和病菌。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　4.备两双薄手套，每天出门戴一双。公共场所的扶手、门拉手、电梯按钮等都是病菌的聚集地，可以准备两双薄手套作为替换，每天出门戴一双，当晚回家清洗。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　5.鸟粪沾身，最好用消毒液清洗。城市活禽少，但鸟却不少。除需要避免接触死禽外，如果外出时有鸟粪落到身上，应在回家后，在落有鸟粪处喷上消毒液，然后将这个部位放在滴入消毒液的水中浸泡一下，再戴上塑胶手套进行清洗。如果有鸟粪落在车上，也应及时清理，并洒上一点消毒液，以防万一。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(35,'<p>\r\n\t<strong>　　如何鉴别质量好的五花肉：</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t<strong>　　优质五花肉的特点：</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　1、肥瘦适当五花肉就是层层肥瘦相间，比例接近吃起来才会不油不涩，口感恰到好处。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　2、富有弹性稍微捏、按，好的五花肉质弹性佳，猪皮表面细致，不会过干或过油。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　3、颜色鲜红 新鲜五花肉正常应该是鲜红色的，若颜色呈现不正常，千万别选购。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　4、色泽明亮 明亮的色泽代表五花肉新鲜，过暗很可能是不新鲜了;而太鲜艳则很可能经过人工处理。\r\n</p>\r\n<br />\r\n<p>\r\n\t　<strong>　不良五花肉的特点：</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　1、油脂分布不均匀油脂分布在五花肉的位置要适当，最好一层一层，作起料理入口才会吃到交杂的口感;若是部分油脂分布不匀，很可能一口都是肥油，或是一口都是干瘦肉了。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　2、松软无弹性 失去弹性，并且松垮的五花肉，肯定不新鲜，选购时要注意。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　3、色泽苍白或暗红过于苍白且摸起来湿湿的，或是太干应并呈现暗红色的五花肉，都是不良品，避免选购。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　4、腥臭味重正常的五花肉，不应该很腥臭的，若产生不好闻的气味，这块五花肉很可能已经腐败了。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　很多人在挑选五花肉时并没有太过的注意，其实利用这些技巧挑选优质的五花肉，吃了对人体的益处更多。\r\n</p>\r\n<strong>　五花肉的做法：</strong><br />\r\n<p>\r\n\t<strong>　　豆豉蒸五花肉</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　材料：五花肉300克、脱皮栗子150克、豆皮结150克、生姜10克、大蒜15克\r\n</p>\r\n<br />\r\n<p>\r\n\t　　做法：\r\n</p>\r\n<br />\r\n<p>\r\n\t　　1、五花肉洗净切片;生姜、大蒜去皮切碎备用。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　2、无花肉片中中依次加入料酒、生抽、腐乳汁、豆豉辣酱、蚝油、生姜大蒜碎、淀粉、色拉油拌匀，腌制20分钟。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　3、脱皮板栗和豆皮结中加入豆豉辣酱拌匀，放在笼屉底部，再将腌制好的五花肉片铺上，入锅蒸30分钟即可。\r\n</p>\r\n<p>\r\n\t　　<strong>蔬菜五花肉卷</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　材料：五花肉一斤、胡萝卜一根、蒜薹一把、cook100烤肉料50克\r\n</p>\r\n<br />\r\n<p>\r\n\t　　做法：\r\n</p>\r\n<br />\r\n<p>\r\n\t　　1.五花肉切成三指宽的大片。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　2.烤肉料50克\r\n</p>\r\n<br />\r\n<p>\r\n\t　　3.烤肉料用50克清水调匀，倒入五花肉低温腌制3个小时以上。(我另外还加了一汤匙的生抽进去一起腌制)\r\n</p>\r\n<br />\r\n<p>\r\n\t　　4.蒜薹和胡萝卜切一指长的条。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　5.把蒜薹和胡萝卜卷进腌制好的五花肉里，用牙签固定好封口。烤箱预热，180度、上下火、中层25分钟，中间拿出翻面一次。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<strong>　蜜汁五花肉</strong><br />\r\n<p>\r\n\t　　材料：五花肉、姜片、料酒、鸡精、老抽、精盐、色拉油、白芝麻、叉烧酱。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　做法：\r\n</p>\r\n<br />\r\n<p>\r\n\t　　1.把五花肉洗净切成大小适合的块备用，\r\n</p>\r\n<br />\r\n<p>\r\n\t　　2.炒锅加热倒入色拉油，放入姜片爆出香味，接着把肉块倒进锅里翻炒。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　3.中火炒至肉块出油，表面呈黄色时，倒入料酒，加入两勺叉烧酱，一汤勺老抽，少许精盐炒至肉块上色。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　4.把炒锅中的五花肉盛出换入砂锅中，加适量热水浸没肉块为好大火煮开，再用小火慢煮半小时左右。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　5.出锅装盘，撒上白芝麻就可以上桌了。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(36,'<p style=\"text-indent:2em;\">\r\n\t随着“五一”的临近，不少朋友打算趁着小长假出行。昨日，记者从省发改委获悉，“五一”期间(4月29日——5月1日)我省将有18个景点实行门票优惠，方便广大游客游览。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t据了解，省发改委决定于今年“五一”期间对庐山、井冈山、龙虎山等18个景区门票价格统一实行优惠。凡是列入优惠范围的景区，要于规定时间前在景区售票处显著位置公布门票价格的优惠时间和优惠幅度，对不按照规定实行优惠的景区，价格主管部门将依法坚决予以查处并向社会公布。(记者 <br />\r\n黄欢)\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t江西省“五一”景区门票优惠表\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t序号 景区名称 景区等级 现行门票价格 优惠后门票价格 优惠幅度%\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t1庐山<br />\r\n5A 180 140 22\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t2井冈山<br />\r\n5A 190 150 21\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t3 龙虎山 5A 150 118 21\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t4 新余市仙女湖 4A 90 70 22\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t5 景德镇市瑶里 4A 150 118 21\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t6 景德镇古窑民俗博览区 4A 95 75 21\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t7 宜春市明月山 4A 120 95 21\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t8 宁都县翠微峰 4A 80 60 25\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t9 上饶市三清山田园牧歌 4A 150 118 21\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t10 上饶市鄱阳湖国家湿地公园 4A 120 95 21\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t11 婺源县文化与生态旅游区(联票) 3A 210 165 21\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t12 万载县竹山洞 3A 78 60 23\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t13 宜春市酌江溶洞 3A 70 55 21\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t14 万年县神农源 3A 60 45 25\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t15 婺源县篁岭 120 95 21\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t16 九江市柘林湖 100 78 22\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t17 庐山三叠泉 64 50 22\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t18 星子县秀峰 63 50 21\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(37,'<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img alt=\"\" src=\"/member/portal/demo/201304/20090444lkir.jpg\" /> \r\n</p>\r\n<div align=\"left\">\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t日常生活中常见的解毒食物有哪些?生活中掌握一些中毒急救的保健常识是很有必要的。有很多时候人们由于吃到不健康食品，接触不卫生物品而导致中毒。往往我们需要了解一些中毒急救的常识，知道一些常见解毒物质来为我们的健康做保障。下面介绍几种日常生活中的解毒物质。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>解毒四杰——木耳、猪血、绿豆、蜂蜜</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img alt=\"\" src=\"/member/portal/demo/201304/20090550wdyt.jpg\" /> \r\n</p>\r\n<div align=\"center\">\r\n\t<strong>木耳</strong> \r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t木耳因生长在背阴潮湿的环境中，中医认为有补气活血、凉血滋润的作用，能够消除血液中的热毒。此外，木耳因具有很强的滑肠作用，经常食用可将肠道内的大部分毒素带出体外。\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"left\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img alt=\"\" src=\"/member/portal/demo/201304/20090715a5dn.jpg\" /> \r\n</p>\r\n<div align=\"center\">\r\n\t<strong>猪血</strong> \r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t猪血也具有很强的滑肠作用，经常食用可将肠道内的大部分毒素带出体外。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img alt=\"\" src=\"/member/portal/demo/201304/20090924j04i.jpg\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<strong>绿豆</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t祖国医学认为，绿豆性味甘寒，解金石、砒霜、草木诸毒。对重金属、农药中毒以及其它各种食物中毒均有防治作用。加速有毒物质在体内的代谢转化向外排泄，绿豆汤是最好的解毒水剂。因此，经常接触铅、砷、镉、化肥、农药等有害物质者，在日常饮食中尤其应多吃些绿豆汤、绿豆粥、绿豆芽。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img alt=\"\" src=\"/member/portal/demo/201304/20091232r2cm.jpg\" /> \r\n</p>\r\n<div align=\"center\">\r\n\t<strong>蜂蜜</strong> \r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t蜂蜜生食性凉能清热，熟食性温可补中气，味道甜柔且具润肠、解毒、止痛等功能。印度民间把蜂蜜看成“使人愉快和保持青春的良药”。\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"left\">\r\n\t<strong>解毒君子——苦瓜、苦茶</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"left\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img alt=\"\" src=\"/member/portal/demo/201304/20091403q4bw.jpg\" /> \r\n</p>\r\n<div align=\"center\">\r\n\t<strong>苦瓜</strong> \r\n</div>\r\n<p style=\"text-indent:2em;\" align=\"left\">\r\n\t一般说来，苦味食品都口感略苦，余味甘甜，具有解毒功能，并可清热去热。苦瓜具有消暑涤热、明目解毒之功效。科学家对苦瓜所含成分进行分析后发现，苦瓜中存在一种具有明显抗癌生理活性的蛋白质，这种蛋白质能够激发体内免疫系统防御功能，增加免疫细胞的活性，消除体内的有害物质。\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"left\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img alt=\"\" src=\"/member/portal/demo/201304/200917208xwe.jpg\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<strong>苦茶</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"left\">\r\n\t中医认为，茶叶味甘苦，性微寒，能缓解多种毒素。茶叶中含有一种丰富活性物质茶多酚，具有解毒作用。茶多酚作为一种天然抗氧化剂，可清除活性氧自由基;其对重金属离子沉淀或还原，可作为生物碱中毒的解毒剂。另外，茶多酚能提高机体的抗氧化能力，降低血脂，缓解血液高凝状态，增强细胞弹性，防止血栓形成，缓解或延缓动脉粥样硬化和高血压的发生。\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"left\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>排毒小卒——日常蔬</strong><strong>菜</strong><strong></strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-align:center;text-indent:2em;\">\r\n\t<img alt=\"\" src=\"/member/portal/demo/201304/20092200k5ln.jpg\" /> \r\n</p>\r\n<p style=\"text-align:center;text-indent:2em;\">\r\n\t<strong>西红柿 冬瓜</strong> \r\n</p>\r\n<p style=\"text-align:left;text-indent:2em;\">\r\n\t在我们常食的蔬菜中，也不乏解毒功臣者，如西红柿甘酸微寒，可清热解毒、生津止渴、凉血活血;冬瓜甘淡微寒，清热解毒、利尿消肿、化痰止渴作用明显；\r\n</p>\r\n<p style=\"text-align:left;text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-align:center;text-indent:2em;\">\r\n\t<img alt=\"\" src=\"/member/portal/demo/201304/20092445fa78.jpg\" /> \r\n</p>\r\n<p style=\"text-align:center;text-indent:2em;\">\r\n\t<strong>丝瓜 芹</strong><strong>菜</strong><span id=\"__kindeditor_bookmark_end_23__\"></span> \r\n</p>\r\n<div style=\"text-align:left;\">\r\n\t&nbsp; &nbsp; &nbsp; &nbsp;&nbsp;丝瓜甘平性寒，有清热凉血、解毒活血作用;黄瓜、竹笋能清热利尿；芹菜可清热利水、凉血清肝热，具有降血压之功效；胡萝卜可与重金属汞结合将其排出体外；大蒜可使体内铅的浓度下降；蘑菇可清洁血液；红薯、芋头、土豆等具有清洁肠道的作用。\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-align:left;text-indent:2em;\">\r\n\t<strong></strong><strong>排毒使者——海带</strong><strong></strong> \r\n</p>\r\n<p style=\"text-align:left;text-indent:2em;\">\r\n\t<strong><br />\r\n</strong>\r\n</p>\r\n<p style=\"text-align:center;text-indent:2em;\">\r\n\t<strong><img alt=\"\" src=\"/member/portal/demo/201304/20092258icmq.jpg\" /><span style=\"font-weight:normal;\" class=\"Apple-style-span\"></span></strong> \r\n</p>\r\n<p style=\"text-align:center;text-indent:2em;\">\r\n\t<strong>海带</strong> \r\n</p>\r\n<p style=\"text-align:left;text-indent:2em;\">\r\n\t<strong><span style=\"font-weight:normal;\" class=\"Apple-style-span\">海带中含有一种叫作硫酸多糖的物质，能够吸收血管中的胆固醇，并把它们排出体外，使血液中的胆固醇保持正常含量。海带表面有一层略带甜味的白色粉末，是极具医疗价值的甘露醇，它具有良好的利尿作用，可以治疗肾功能衰竭、药物中毒、浮肿等。</span></strong> \r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(38,'<p>\r\n\t　　中新网南京4月19日电 (陶利平 朱晓颖)据上海铁路局4月19日统计，3月10日至4月18日春游期间，该局累计发送旅客4126.2万人，与去年同比增加460.7万人，增长12.6%，旅客发送总量超过今年春运，创历年春游旅客运输新高。\r\n</p>\r\n<p>\r\n\t　　今年春游40天，长三角地区客流集中，特别是双休日沪宁、沪杭、京沪、合宁、合武等高铁沿线旅游景区车站旅游客流较高。据统计，春游40天该局发送长三角地区短途旅客3073万人，占全部发送总量的74.5%。\r\n</p>\r\n<p>\r\n\t　　随着春游运输结束，该局将迎来“五一”小长假运输和“红色之旅”出游客流高峰，客流将集中在沪宁杭、甬台温沿海铁路和合宁、合武客运专线等沿线的旅游城市和车站，以及江西、湖南、安徽等革命老区。该局针对早晚客流集中出行的特点，将对京沪等高铁按照高峰列车运行图开足各趟图定高铁列车，对部分高铁列车延长运行区段、重联运行，并安排加开临时客车58.5对，尽最大努力满足旅客出行需求。同时，为方便旅客出游，该局还将在5月份组织增开上海至邯郸、宜春、丽水、衢州、九江，杭州至太原、株洲，南京至洛阳和淮北至贵阳等19趟旅游专列。\r\n</p>\r\n<p>\r\n\t　　目前，“五一”小长假车票已开始发售，广大市民可通过12306铁路客服网站或拨打该局95105105订票电话等方式，提前购买车票。满六人及以上且乘车日期、车次、到站相同的市民，可通过拨打12306客服电话提前办理团体车票预订手续。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(39,'<p>\r\n\t　　记者从天津市发展改革委获悉，根据《国家发展改革委办公厅关于落实“五一”期间门票价格优惠措施营造良好旅游价格环境的通知》，本市部分景区“五一”期间实行门票价格优惠。\r\n</p>\r\n<p>\r\n\t　　据悉，根据《通知》，今年“五一”期间，文庙博物馆门票价格由每人每券30元优惠为每人每券22元；大沽口炮台遗址博物馆门票价格由每人每券30元优惠为每人每券22元。实行门票价格优惠的时间为4月29日至5月1日，从5月2日起恢复为原门票价格，其他门票价格优惠减免范围仍按照现行有关规定执行，对符合规定实行半票优惠政策的，应当以上述优惠后的门票价格为基数计价。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(40,'<p>\r\n\t　　世界文化遗产“福建土楼”中，最古老和最年轻的土楼均坐落于“土楼之乡”福建永定县境内的初溪土楼群。驱车来到初溪土楼群，一条小溪从山脚潺潺流过，站在石头桥上抬头望去，只见或圆或方的成片土楼依山而建，在山水田园间高低错落。\r\n</p>\r\n<p>\r\n\t　　虽然不是周末，停车坪上仍然停着十几辆小汽车。福建省客家土楼旅游发展有限公司总经理沈锦松告诉记者，“现在，珠三角地区的自驾车游客对永定土楼感兴趣的越来越多”，但永定土楼还只是个观光点，酒店等旅游配套服务设施仍不足。\r\n</p>\r\n<p>\r\n\t　　随着“福建土楼”列入世遗，永定客家土楼的知名度越来越高。如今，无论是官方，还是土楼里的“原住民”，都乐于“营销”卖旅游。\r\n</p>\r\n<p>\r\n\t　　在土楼里开旅馆，成为不少土楼人家的选择。初溪馀庆楼的楼主徐恒地，便将土楼旅馆生意做得有声有色。\r\n</p>\r\n<p>\r\n\t　　有着300年历史的馀庆楼里，不少穿着时尚的青年人穿梭其间，而徐恒地正在一间小屋内的电脑前，不断刷新网页，手机短信提示声连连响起。\r\n</p>\r\n<p>\r\n\t　　徐恒地说，自家土楼来得最多的是外国人和台湾人，“他们都是通过网络联系到自己，并自己找上门来投宿。”说话间，徐恒地将刚接到的一条短信递给记者看，“你看，又有客人向我订了五个房间，明天过来。”\r\n</p>\r\n<p>\r\n\t　　坚实土墙、环形走廊、木门木窗木楼梯木楼板……一切都是那么古朴自然。随意推开一扇木门，徐恒地的土楼旅馆客房里电视、空调等现代家电应有尽有。徐恒地将古老土楼里40多个房间改造成旅馆客房，这花了他将近半年的时间。\r\n</p>\r\n<p>\r\n\t　　2008年，“福建土楼”列入世遗名录。次年，在外做生意的徐恒地瞅准土楼旅游的商机，毅然返乡创业，开起了土楼旅馆。\r\n</p>\r\n<p>\r\n\t　　沈锦松对土楼人家的回乡创业印象尤深，他说，“土楼旅游的世遗效应已经显现，不仅有门票分红，还有多种生意可做，现在土楼人家大多不外出务工或做生意了”。\r\n</p>\r\n<p>\r\n\t　　“你们要是旺季来，我根本没空跟你讲话。”采访间，离开电脑的徐恒地又“马不停蹄”地用手机上网，一边聊着，一边不时低头查看手机。\r\n</p>\r\n<p>\r\n\t　　上网成为了徐恒地的日常工作。他登录各大旅游网站，在论坛、微博上发帖发图片，推销他的土楼旅馆，也不忘推销土楼之旅。这让身居大山里的他尝到了甜头。\r\n</p>\r\n<p>\r\n\t　　“网络是我们最有效最节约成本的营销途径，我们的顾客都是在网上看到我们的信息，除了我们自己发布消息，也有来过的游客在网上与网友分享，这样我们的知名度也就不断提高。”徐恒地有着他自己独到的生意经。\r\n</p>\r\n<p>\r\n\t　　穿行于初溪土楼群，不时能看到摆摊卖特产的小贩。原本以务农为生的土楼女子黄婉华，看到近年来游客不断增多，也放下农具，靠着祖祖辈辈赖以为家的土楼做起了小本生意。\r\n</p>\r\n<p>\r\n\t　　茶叶、花生、梅菜干，黄婉华向游客兜售的都是她自家种的土特产，“原生态”是她做好土楼生意的最大“卖点”。正在挑拣茶叶的黄婉华告诉记者，旺季的时候每天大约有五六百元的收入。\r\n</p>\r\n<p>\r\n\t　　“这比种地来得轻松多了。”黄婉华笑称，看到游客这么多，自己已经不满足于单纯卖特产，“接下来想在自家土楼里开个饭馆，让游客在古朴的土楼里吃到正宗的客家菜，这样也能加深感受嘛。”\r\n</p>\r\n<p>\r\n\t　　作为世遗“福建土楼”的核心区，永定现存土楼23000多座，列入世遗的有23座。保护和开发土楼成为了当地政府的大事。沈锦松说，“看土楼主要是了解客家文化，对文化保护好、开发好、营销好才能有一个响当当的客家土楼品牌。”\r\n</p>\r\n<p>\r\n\t　　近期，《土楼回响》大型交响乐、大型原生态歌舞《土楼神韵》、歌剧《土楼》等在马来西亚、新加坡、台湾等地上演，引发海外新一轮对客家土楼对客家文化的追捧。沈锦松说，土楼品牌营销好了，老百姓跟着老祖宗留下的这份遗产可以享更多的福。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(41,'<p style=\"text-indent:2em;\">\r\n\t烧制1公斤牛肉碳排放量达6.4千克\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t衣食住行碳排放量可“算”出来\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t每烧制1公斤牛肉，碳排放为6.4千克，相当于烧制 25公斤土豆的排放量。在昨天“酷中国低碳生活进社区”活动中，“我爱绿色”环保网站推出的“碳计算器”，直观地给出了人们衣食住行的大致碳排放量。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t每烧制1公斤食物，苹果、土豆等素食的碳排放量约为0.3千克，鱼肉、猪肉约为1.4千克，而牛肉为6.4千克。肉食的碳排放量远高于素食，因此，环保人士建议人们每月吃素食1天。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t使用笔记本电脑1小时，碳排放约为0.02千克，使用台式电脑1小时，碳排放约为0.06千克；1台空调每小时碳排放平均为0.6千克；电视机每小时碳排放约为0.1千克；每用1度电，碳排放为1千克。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t打出租车碳排放最高，每公里为0.5千克，从高到低依次为私家车0.22千克，飞机头等舱0.182千克，商务舱0.137千克，经济舱0.09千克，公交车0.06千克，摩托车0.055千克，火车0.05千克。每乘坐1次地铁，碳排放平均为1千克。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t武汉市环保专家说，用“碳计算器”计算衣食住行的碳排放，得出的虽是粗略数据，但让人们在这种趣味游戏中了解环保知识，很有意义。有兴趣的读者可以上网算一算，自己每天衣食住行的碳排放量到底有多少。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(42,'<p>\r\n\t　　据中国之声《央广新闻》报道，记者四川省旅游局管理处了解到，目前四川21个市州旅游局正在统计各地旅行社在雅安周边景区游玩的游客情况。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p>\r\n\t　　在雅安旅游亲历雅安地震的肖先生表示，今天早上8点多时他正在四川雅安龙景山风景区，当时旁边是索道车，能够感受到摇晃非常强烈。肖先生说，整个风景区老房子都震塌了，新房子都震裂了，很多地方都震碎了。\r\n</p>\r\n<br />\r\n<br />');--end
 INSERT INTO tq_article_txt VALUES(43,'<p>\r\n\t　　记者张愉报道：4月20日上午，四川雅安发生7级地震，周边地区都有强烈震感。目前是否有江西游客在四川？记者采访了负责江西市场的成都童话假期国际旅行社(下称成都童话假期国旅)相关负责人代刚获悉，目前通过该旅行社有107名江西游客身在四川，他们主要在乐山峨眉山、九寨沟和成都旅游，全部都很平安，大家还在继续行程。据悉，江西前往四川旅游的旺季一般在暑假，雅安不是江西人前往四川的主要旅游目的地。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　20日上午8时02分，四川雅安地区发生7级地震，随后发生多次余震，震区周边有强烈震感。代刚告诉记者，地震发生后，他们第一时间就和四川当地取得了联系，确认目前有107名江西游客正在四川旅游。在地震发生当时，游客们都感觉到了震感，不过这107人都很安全。代刚说上午8时40分旅行社再次和四川当地联系时通讯已经不畅，不过大家通过微信、短信可以正常交流。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　记者在采访中获悉，目前并不是江西游客前往四川旅游的旺季。“一般在暑假的时候去的人比较多。”代刚介绍说，乐山峨眉山、九寨沟和成都是江西游客到四川去的比较集中的地区。发生地震的雅安地区也有一些旅游景点，但是江西游客去的比较少。“雅安那边有个碧峰峡，还有熊猫基地。我们做江西市场有十多年，接待的去碧峰峡的江西游客不会超过100人，属于比较特殊的线，而且一般也是七八月会比较多去。”代刚说。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　代刚告诉记者，目前在四川的江西游客还在按照原计划继续行程。如果没有接到国家旅游局或是四川省旅游局的通知，之后江西前往四川的旅游团也会继续正常出团。不过如果有旅客想退团，旅行社方面会按照正常操作接受。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(44,'<div align=\"center\">\r\n\t<img class=\"flag_bigP\" alt=\"\" align=\"middle\" src=\"http://photocdn.sohu.com/20130420/Img373383922.jpg\" /> \r\n</div>\r\n<div>\r\n\t&nbsp;\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t新华网武汉４月２０日电（记者&nbsp; 李鹏翔、皮曙初）２０日１１时２４分许，武汉市１１９指挥中心接到报警，一载客３００人左右的游轮在长江武汉天兴洲附近江面起火，浓烟滚滚。武汉市消防部门调集青山、江岸等６个中队的消防官兵前往救援。目前，游轮上旅客已被紧急疏散，通过消防官兵现场施救，火势被基本控制。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(45,'<p style=\"text-indent:2em;\">\r\n\t因地震灾害影响，成都火车站始发动车、列车停运，成都火车站工作人员表示，退票不收退票费。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t具体停运列车信息为：4月20日成都开D5104 D5106 D5174 。成都东开D5108 D5110 D5112 D5114 D5116&nbsp; D5118 D5134次，重庆北开：D5105 D5107 D5109 D5111 D5133 D5113次停运。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t2013年4月20日成都开T8904次 T8906次停运。4月20日绵阳开T8903次，T8905次停运。&nbsp; 4月20日成都到T8901次车底担当当日成都开T8910次用。 4月20日绵阳到T8902次车底担当当日绵阳开T8907次用。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t因地震灾害影响：4月20日成都开D5195，成都东开D5184 D5198, 达州开D5195 D5185 D5197次停运。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 因地震灾害影响：4月20日南充开D5193／2 、D5173次；重庆北开D5191/4次停运；4月20日成都开D5164次、南充开D5163次停运。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(46,'<strong>1．提高免疫力。</strong><br />\r\n<p>\r\n\t　　柠檬水中丰富的维生素C有助于免疫系统抗击感冒。黑基表示，柠檬水还有助于促进人体对铁的吸收，因为维生素C可以使非血红素铁的生物利用率提高4倍。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　<strong>2．促进伤口愈合。</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　维生素C是促进伤口愈合，保持骨骼、组织和软骨健康的重要营养素之一。维生素C还有助于中和自由基，抑制体内炎症。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　<strong>3．抗击皮肤衰老。</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　黑基表示，柠檬水的抗氧化作用有助于应对体内自由基损害，缓解衰老进程。其富含的维生素C能帮助氨基酸合成胶原，保护皮肤，防止皱纹早生。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　<strong>4．降低食欲。</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　柠檬水中含有可溶性纤维素果胶，有助于增强饱腹感，进而控制食欲，防止过量饮食。保持更长时间的饱腹感，还有助于少吃零食，从而控制体重。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<strong>5．助消化。</strong><br />\r\n<p>\r\n\t　　柠檬水有益消化道健康，可刺激胃液分泌，帮助消化，促进排便，清理肠道。柠檬水还能缓解消化不良、烧心和胀气等症状。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　<strong>6．净化尿道。</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　柠檬水具有利尿作用。喝柠檬水有助于人体毒素更快排出，进而净化机体，保持健康。柠檬水还有助于改变尿道pH值，防止有害菌滋生。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　<strong>7．降低尿液酸度。</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　柠檬水中虽然有柠檬酸，但其中的钾、钙等离子都可以与酸根离子结合，能降低尿液酸性，有益健康，是典型的碱性食品。\r\n</p>\r\n　<strong>8．清新口气。</strong><br />\r\n<p>\r\n\t　　柠檬水具有杀菌作用，特别能杀死口腔中的有害细菌，从而保持口气清新。早餐喝一杯柠檬水比咖啡更提神。黑基建议，柠檬水别喝冰镇的，室温的最佳。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　<strong>9．改善血液循环。</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　柠檬水中富含钾，能帮助控制血压，缓解压力。柠檬水中还含有维生素P，有助于增强毛细血管弹性，改善血液循环。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　<strong>10．益肝排毒。</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　柠檬水有助于肝脏排毒，通过促进胆汁生成而净化肝脏。柠檬水还有助于控制胆汁过量，减少体内黏液质生成，帮助溶解胆结石。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(47,'<p>\r\n\t　　<strong>1.豆浆不能空腹喝</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　生活中不少人习惯早晨空腹喝豆浆，尤其是非常肌饿时，觉得喝一杯豆浆能迅速填饱肚子，并认为空腹喝豆浆容易吸收、最补身体。这是不对的。从营养角度讲，豆浆是蛋白质含量很丰富的饮料，但是它只有在摄入足量淀粉食品后才能不被作为热量来消耗，而真正发挥补益的作用。空腹喝豆浆则会使蛋白质浪费的同时又使体内营养失去平衡，从而加重消化、泌尿系统的负担。所以，饮豆浆的同时吃些面包、饼干、糕点、馒头等淀粉类食品，或在早饭后1—2小时喝，可使豆浆中蛋白质等在淀粉的作用下，与胃液较充分地发生酶解，使营养物质被充分吸收。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　<strong>2.蜂蜜水不能空腹喝</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　蜂蜜的食用时间大有讲究，建议在饭后1.5-2小时后喝为宜。空腹喝蜂蜜水容易使体内酸性增加，时间长了就会胃酸过多而得胃溃疡或十二指肠溃疡。\r\n</p>\r\n<p>\r\n\t　<strong>　3.减肥茶不能空腹喝</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　春天正是健身减肥，秀出好身材的最佳时机。很多为了矫正身材，防止发胖的职场白领都借助减肥茶来控制体重。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　茶，基本上是我们熟悉的不能再熟悉的饮品了，大家基本都知道茶不能饭后马上喝，最好半个小时再饮用。但是很多人往往没有注意到，茶也不应该空腹 <br />\r\n喝。古人云：“不饮空心茶”正是这个意思。因为茶叶中含有咖啡因等生物碱，空腹饮茶易使肠道吸收咖啡碱过多，容易引起胃肠不适食欲减退。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　<strong>4.咖啡不能空腹喝</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　首先，咖啡会刺激胃酸分泌，即便是在不空腹状态下饮用，也会对胃有刺激性，所以，空腹饮用原本就有刺激性的咖啡，很容易引起“胃气受伤”，从而影响胃功能的消化和吸收，严重者会导致胃溃疡，而胃溃疡患者更应该注意不要空腹喝咖啡。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　其次，咖啡含有丰富的糖类和能量，空腹喝咖啡不但伤胃而且还容易发胖。\r\n</p>\r\n　<strong>5.酸奶不能空腹喝</strong><br />\r\n<p>\r\n\t　　人在通常状况下，胃液的PH值在1-3之间，空腹时，胃液呈现酸性，PH值在2以下，不适合酸奶中活性乳酸菌的生长。只有当胃部PH值比较高，才能让酸奶中的乳酸菌充分生长，有利于健康。营养学专家介绍：一般来说，饭后30分钟到2个小时之间饮用酸奶效果最佳。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　<strong>6.桃花茶不能空腹喝</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　桃花茶性寒有利水、活血、通便功效，但久服会耗人阴血,损元气。体寒、脾胃寒的喝多了不好，一会出现狂拉肚子的情况。空腹最好别喝。\r\n</p>\r\n　<strong>7.果汁不能空腹喝</strong><br />\r\n<p>\r\n\t　　睡了一个晚上，人体血液粘稠度会增加。所以，营养学家建议早上喝一杯清水，以稀释体液。但如果这个时候喝果汁，非但不能起到稀释体液的作用，还会增加肠胃的负担。\r\n</p>\r\n<br />\r\n<p>\r\n\t　<strong>　8.碳酸饮料不能空腹喝</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　很多上班白领为了节约时间，早晨起来后夹着一瓶饮料就出门了。但是你知道吗，空腹喝饮料坏处多。汽水和可乐等碳酸饮料中大都含有柠檬酸，在代谢过程中会加速钙的排泄，降低血液中钙的含量，长期饮用会导致缺钙。而另一些饮料有利于排尿作用，清晨饮用非但不能有效补充机体缺少的水分，还会增加集体对水分的要求，反而造成体内缺水。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(48,'<p>\r\n\t　　今日下午记者与天全旅游局雷汉玉局长取得联系。喇叭河景区还有100余景区施工工人被滞留。目前游客与施工工人情况良好，无伤亡，已被安置在景区内的部分小木屋客栈和帐篷里。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　雷汉玉介绍，目前喇叭河景区道路、通信、电力严重损毁。通往景区的道路，出现多处塌方，被巨石堵死了。目前，蒲江交通局已派来了挖掘机和铲车，正在全力以赴打通道路，力争能在今晚救出被困人员。景区被围困的游客，大多来自成都。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(49,'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 五一小长假临近，许多市民又在积极准备出游。和清明节相比，五一拼假的优势让人们可以更从容出游，境内外旅游报名相比清明节增长了1倍。记者了解，热门旅游目的地经济型酒店近日预订已经接近饱和。而刚刚涨价的凤凰古城也在首个长假遭到游客抵制，五一古城客栈预订却比平日骤降了50%。<br />\r\n<br />\r\n　　五一拼假出游人数倍增<br />\r\n<br />\r\n　　记者从北京多家旅行社了解到，由于今年五一游客利用年假可拼出5至10天的长假，在出境游举家出行的欧美长线游成为热门，目前已经提前结束报名，部分东南亚海岛等仍可接受报名。<br />\r\n<br />\r\n　　众信旅游副总监王振玥告诉记者，由于报名游客人数多，五一出境游短线产品的价格也呈现出小幅上扬的态势，价格涨幅在10%至20%左右；长线产品价格与往年同期基本持平，并无明显上涨。报名人数相较往年同期约有1倍增长，与清明假期出游人数相比，增长高达2倍。<br />\r\n<br />\r\n　　经济型抢手商务型冷<br />\r\n<br />\r\n　　“五一”小长假前后的一周时间，不少热门城市的酒店预订情况已经出现火爆局面，尤其是经济型连锁酒店的预订情况。酷讯旅游数据监测显示，七天、汉庭、如家、锦江之星等150元至200元的经济型酒店的预订比例最高达到了78.4%，杭州、南京、上海、厦门、扬州等热门旅游城市的大部分门店已经全部接近满房。<br />\r\n<br />\r\n　　与经济型酒店火爆遭遇相比，商务和高星级酒店的预订情况则冷清了不少。业内人士透露，“五一”期间，游客主要是以家庭或团队为单位，这类客人入住酒店多数是自掏腰包，因此会更加控制住宿成本。虽然各大商务城市的酒店价格因公务消费减少而大幅下降，不少甚至出现了跳水价格，但游客仍不买账。<br />\r\n<br />\r\n　　在预订技巧上，游客可以多方比较酒店的价格。很多酒店在第三方代理网站上的会比垂直搜索出的便宜。<br />\r\n<br />\r\n　　凤凰客栈预订急降五成<br />\r\n<br />\r\n　　原本免费的凤凰古城开始收取148元的门票，许多网友将抵制付诸行动。据淘宝旅行数据显示，以“凤凰”作为关键词，最近七天的搜索指数环比下降20.3%，与去年同期相比下降34.8%。从最近的成交情况看，最近一周凤凰客栈的预订量与3月平均相比，下降了50%。<br />\r\n<br />\r\n　　淘宝旅行负责人李鑫告诉记者，古城收费受冲击较大的就是客栈。目前凤凰古城范围内的客栈有大大小小1000家，从最近的成交情况看，最近一周客栈的预订量与3月平均相比，下降了50%。与此同时，凤凰古城内的客栈预订出现了城内冷城外热的情况，原本游客都爱住古城内的特色客栈，但是由于148元的套票，把游客都赶往了城外的客栈。<br />');--end
 INSERT INTO tq_article_txt VALUES(50,'<p style=\"text-indent:2em;\">\r\n\t四川省公安厅交警总队刚刚发布抗震救灾交通管制信息，禁止所有社会车辆自行前往灾区。<br />\r\n<br />\r\n　　管制信息通告：一、所有社会车辆一律不得自行前往灾区。二、通往凉山、攀枝花、甘孜等方向过境车辆禁止通行京昆高速成都至荥经路段。三、成都至雅安区间车辆禁止通行京昆高速成雅路段，从成温邛高速和老成雅公路通行。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(51,'<p style=\"text-indent:2em;\">\r\n\t穿好几件衣服，有时候热，热到可以吹风扇。 这样的天气里面人们经常因为身体抵抗能力受不了就得了各种季节病了，西红柿炖牛肉的做法学会了，你也可以在家里面为自己的身体加加油，抵抗天气 的变化。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n下面这道菜说的是西红柿炖牛肉做法，西红柿炖牛肉的做法也可相当简单的。<br />\r\n只要注意一些做这道菜的生活小常识小窍门，跟着我所说的步骤来，照样简单，你也可以成为一个合格的大厨。<br />\r\n西红柿炖牛肉不但健脾还有增加身体的抵抗力，是难得的一道家常菜。<br />\r\n西红柿具有丰富的营养，对人体的好处可以说是相当多的，它被称为是菜中之果。维生素A原的含量是相当多的，这种元素可以对我们的骨骼发育相当有用<br />\r\n，促进骨骼的生长，更可以有效防治佝偻病、眼干燥症。<br />\r\n牛肉中含有丰富的肌氨酸,牛肉中的肌氨酸比任何食物中的含量都要高出许多，他可以促进人体肌肉的增长，增强力量是相当有效的，为什么国外的人都喜<br />\r\n欢吃牛肉，为什么这些人吃了牛肉个个都长的那么大只，就是因为牛肉中有大量的肌氨酸原因。<br />\r\n西红柿炖牛肉是以西红柿和牛肉为主的家常菜，口味不会太重，偏淡，补铁又可以补血，营养价值可以说别的菜是没得比的。<br />\r\n下面来看食材都有哪些：\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t下面看一下做法：<br />\r\n1：牛肉洗净沥干水分，切麻将大小的块，然后汆一下水；西红柿洗净用开水烫去皮，切块；洋葱洗净切块。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n2：将汆好水的牛肉、西红柿、洋葱放入砂锅内，淋入番茄酱，然后一次性加够热水（注意啊，一定要是热水），大火烧开后转小火慢炖一个半小时左右，<br />\r\n出锅前加盐，撒上香菜即可。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n西红柿炖牛肉的做法的一些小窍门，这个可得注意了，直接影响到口感和食相：<br />\r\n1、汤水量要一次加足，不可中途添水。若汤不够，只能加热水或开水，千万不能中途加凉水，否则开锅的肉遇到凉水，易使肉表面收缩变紧，热量不易内<br />\r\n传，肉质会变得即硬又皮，不好嚼咽。<br />\r\n2、汤调好后，再放适量盐。<br />\r\n3、一定要炖烂一点,如果你有高压锅就不需要这么长时间了。<br />\r\n做上这一道美味的小菜，一家吃围着吃，相当的安逸呀\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(52,'<p style=\"text-indent:2em;\">\r\n\t4月21日，西部省市重庆、四川、云南、陕西、新疆、贵州、青海、甘肃、广西等9省区市，12家住房置业担保机构组成西部住房置业担保行业联席会议，将尝试通过内部成员的信息共享，建立起区域信息共享机制，建立成员间公积金异地贷款代办落实抵押、核实抵押登记等合作机制。<br />\r\n&nbsp;<br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;据介绍，住房置业担保工作始于2000年，期间全国住房置业担保机构已成立了100多家，累计担保个人住房贷款余额5000多亿元，有效防范和化解了公积金贷款风险。但由于全国住房置业担保机构规模大小不一且分散，致使部分机构在经营范围、担保额度、风险控制、抵押物处理、税收政策等方面缺乏统一、明确的标准和规范。因此，重庆置业担保公司倡议建立西部地区住房置业担保行业联席会议制度，加强西部住房置业担保行业间的沟通，相互取长补短、资源共享，抱团发展。<br />\r\n<br />\r\n&nbsp;&nbsp;&nbsp;&nbsp; 据了解，目前西部地区内蒙、广西、重庆、四川、贵州、云南、西藏、新疆等12个省市有包括国有和民营的置业担保公司12家，总注册资本金6.75亿元。<br />\r\n<br />\r\n&nbsp;&nbsp;&nbsp;&nbsp; 重庆市住房置业担保有限公司董事长彭正钧介绍，西部住房置业担保行业联席会议为西部地区住房置业担保机构资源组成的行业自律性联系载体。西部住房置业担保行业联席会议成立后，将通过内部成员的相互沟通、交流，实现区域性合作和资本联营。未来，西部住房置业担保行业联席会议将通过联席会议的合作机制，建立行业培训机制等各种方式，加强泛西部地区住房置业担保机构的信息交流和业务合作，优化区域资源配置，实现西部住房置业担保行业可持续发展。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(53,'<p style=\"text-indent:2em;\">\r\n\t他们是福布斯全球富豪榜中的亿万富翁，他们在美国甚至全世界都是响当当的名字，他们的财富在全球富豪榜中都名列前茅，他们的家更是让人惊叹不已，让我们一睹为快。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p align=\"center\">\r\n\t<img width=\"180\" height=\"178\" alt=\"\" src=\"http://news.xinhuanet.com/photo/2011-10/09/122134030_1751n.jpg\" />&nbsp;\r\n</p>\r\n<p align=\"center\" style=\"text-indent:2em;\">\r\n\t卡洛斯·斯利姆·埃卢（Carlos Slim Helu）\r\n</p>\r\n<p align=\"center\" style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p align=\"center\">\r\n\t<img width=\"480\" height=\"339\" alt=\"\" src=\"http://news.xinhuanet.com/photo/2011-10/09/122134030_491n.jpg\" /> \r\n</p>\r\n<div align=\"left\">\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t卡洛斯·斯利姆·埃卢净资产：740亿美元，是全球首富的墨西哥电信大亨，砸下4,400万美元买下了希曼斯公爵公馆（Duke-Semans <br />\r\nmansion）城区艺术住宅，这座豪宅打破了最高纪录高价。\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p>\r\n\t<br />\r\n</p>\r\n<p align=\"center\">\r\n\t<img width=\"180\" height=\"178\" alt=\"\" src=\"http://news.xinhuanet.com/photo/2011-10/09/122134030_1761n.jpg\" /> \r\n</p>\r\n<p align=\"center\" style=\"text-indent:2em;\">\r\n\t<span>比尔·盖茨</span> \r\n</p>\r\n<p align=\"center\" style=\"text-indent:2em;\">\r\n\t<span><br />\r\n</span> \r\n</p>\r\n<p align=\"center\">\r\n\t<img width=\"480\" height=\"336\" alt=\"\" src=\"http://news.xinhuanet.com/photo/2011-10/09/122134030_1771n.jpg\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t比尔·盖茨净资产:560亿美元，他是美国微软公司的董事长，曾任微软CEO和首席软件设计师，并持有公司超过8%的普通股，也是公司最大的个人股东。2011年，比尔·盖茨以560亿美元资产列福布斯全球富豪榜第2位。\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p>\r\n\t<br />\r\n</p>\r\n<p align=\"center\">\r\n\t<img width=\"180\" height=\"185\" alt=\"\" src=\"http://news.xinhuanet.com/photo/2011-10/09/122134030_1781n.jpg\" /> \r\n</p>\r\n<p align=\"center\" style=\"text-indent:2em;\">\r\n\t<span>沃伦·巴菲特（Warren Edward Buffett） </span> \r\n</p>\r\n<p align=\"center\" style=\"text-indent:2em;\">\r\n\t<span><br />\r\n</span> \r\n</p>\r\n<p align=\"center\">\r\n\t<img width=\"480\" height=\"336\" alt=\"\" src=\"http://news.xinhuanet.com/photo/2011-10/09/122134030_1791n.jpg\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t沃伦·巴菲特净资产:500亿美元，他是可口可乐公司的领头人物，他的衣食父母就是一代又一代热衷于此种饮料的人们，是大家用自己的钞票堆砌出来的亿万富翁。\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p>\r\n\t<br />\r\n</p>\r\n<p align=\"center\">\r\n\t<img width=\"300\" height=\"404\" alt=\"\" src=\"http://news.xinhuanet.com/photo/2011-10/09/122134030_1801n.jpg\" /> \r\n</p>\r\n<p align=\"center\" style=\"text-indent:2em;\">\r\n\t<span>拉里·埃里森（Larry Ellision） </span> \r\n</p>\r\n<p align=\"center\" style=\"text-indent:2em;\">\r\n\t<span></span>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<!--mainContent end-->\r\n</p>\r\n<p align=\"center\">\r\n\t<img width=\"480\" height=\"336\" alt=\"\" src=\"http://news.xinhuanet.com/photo/2011-10/09/122134030_1811n.jpg\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t拉里·埃里森净资产:395亿美元，美国ORACLE公司的创办人，该公司主要生产数据库产品，也是主要的网络计算机的倡导者。\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p>\r\n\t<br />\r\n</p>\r\n<p align=\"center\">\r\n\t<img width=\"180\" height=\"186\" alt=\"\" src=\"http://news.xinhuanet.com/photo/2011-10/09/122134030_1821n.jpg\" /> \r\n</p>\r\n<p align=\"center\" style=\"text-indent:2em;\">\r\n\t<span>穆凯什·阿姆巴尼</span> \r\n</p>\r\n<p align=\"center\" style=\"text-indent:2em;\">\r\n\t<span><br />\r\n</span> \r\n</p>\r\n<p align=\"center\">\r\n\t<img width=\"380\" height=\"491\" alt=\"\" src=\"http://news.xinhuanet.com/photo/2011-10/09/122134030_1831n.jpg\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t穆凯什·阿姆巴尼净资产：270亿美元，“Antilia”27层高，173米，市价6.3亿英镑，有600多名管理人员，9部电梯，可以俯瞰整个城市和海景，是迄今为止世界上规模最大的私人住宅。\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p>\r\n\t<br />\r\n</p>\r\n<p align=\"center\">\r\n\t<img width=\"180\" height=\"186\" alt=\"\" src=\"http://news.xinhuanet.com/photo/2011-10/09/122134030_1841n.jpg\" /> \r\n</p>\r\n<p align=\"center\" style=\"text-indent:2em;\">\r\n\t<span>克里斯蒂·沃尔顿（Christy Walton）</span> \r\n</p>\r\n<p align=\"center\" style=\"text-indent:2em;\">\r\n\t<span><br />\r\n</span> \r\n</p>\r\n<p align=\"center\">\r\n\t<img width=\"480\" height=\"336\" alt=\"\" src=\"http://news.xinhuanet.com/photo/2011-10/09/122134030_1851n.jpg\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t克里斯蒂·沃尔顿净资产:265亿美元，克里斯蒂的丈夫约翰，同时也是沃尔玛创始人山姆·沃顿的第二个儿子，于2005年死于空难，克里斯蒂由此一夜之间成了亿万富翁。\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p>\r\n\t<br />\r\n</p>\r\n<p align=\"center\">\r\n\t<img title=\"全世界最有钱的十位富豪 房子到底长啥样(组图)&#10;点击图片查看下一页\" alt=\"\" src=\"http://imgs.soufun.com/news/2011_06/23/news/1308791140164_000.jpg\" /> \r\n</p>\r\n<p style=\"text-align:center;text-indent:2em;\">\r\n\t<span>谢尔登·埃德森（Sheldon Adelson）</span> \r\n</p>\r\n<p style=\"text-align:center;text-indent:2em;\">\r\n\t<span><br />\r\n</span> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<!--mainContent end-->\r\n</p>\r\n<p align=\"center\">\r\n\t<img title=\"全世界最有钱的十位富豪 房子到底长啥样(组图)&#10;点击图片查看下一页\" alt=\"\" src=\"http://imgs.soufun.com/news/2011_06/23/news/1308791139385_000.jpg\" /> \r\n</p>\r\n<p align=\"center\" style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t谢尔登·埃德森净资产:233亿美元，埃德森在美国、澳门和新加坡都是响当当的名字，他以卖报起家，现已成为赌博业最重量级的人物之一。\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p>\r\n\t<br />\r\n</p>\r\n<p align=\"center\">\r\n\t<img title=\"全世界最有钱的十位富豪 房子到底长啥样(组图)&#10;点击图片查看下一页\" alt=\"\" src=\"http://imgs.soufun.com/news/2011_06/23/news/1308791141431_000.jpg\" /> \r\n</p>\r\n<p style=\"text-align:center;text-indent:2em;\">\r\n\t<span>吉姆·沃尔顿（Jim C. Walton）</span> \r\n</p>\r\n<p style=\"text-align:center;text-indent:2em;\">\r\n\t<span><br />\r\n</span> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<!--mainContent end-->\r\n</p>\r\n<p align=\"center\">\r\n\t<img title=\"全世界最有钱的十位富豪 房子到底长啥样(组图)&#10;点击图片查看下一页\" alt=\"\" src=\"http://imgs.soufun.com/news/2011_06/23/news/1308791140904_000.jpg\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t吉姆·沃尔顿净资产：213亿美元，他是沃尔玛连锁店创始人——萨姆·沃顿（死于1992年）的儿子，沃尔玛目前是全球最大的零售商，吉姆现在是Arvest集团，一个社区银行组织的负责人。\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p>\r\n\t<br />\r\n</p>\r\n<p align=\"center\">\r\n\t<img title=\"全世界最有钱的十位富豪 房子到底长啥样(组图)&#10;点击图片查看下一页\" alt=\"\" src=\"http://imgs.soufun.com/news/2011_06/23/news/1308791142777_000.jpg\" /> \r\n</p>\r\n<p style=\"text-align:center;text-indent:2em;\">\r\n\t<span>罗伯森·沃尔顿</span> \r\n</p>\r\n<p style=\"text-align:center;text-indent:2em;\">\r\n\t<span><br />\r\n</span> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<!--mainContent end-->\r\n</p>\r\n<p align=\"center\">\r\n\t<img title=\"全世界最有钱的十位富豪 房子到底长啥样(组图)&#10;点击图片查看下一页\" alt=\"\" src=\"http://imgs.soufun.com/news/2011_06/23/news/1308791142382_000.jpg\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t罗伯森·沃尔顿净资产:210亿美元，任沃尔玛百货公司董事长11年，家里有网球场、游泳池，足不出户就可以锻炼身体。\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p>\r\n\t<br />\r\n</p>\r\n<p align=\"center\">\r\n\t<img title=\"全世界最有钱的十位富豪 房子到底长啥样(组图)&#10;点击图片查看下一页\" alt=\"\" src=\"http://imgs.soufun.com/news/2011_06/23/news/1308791145482_000.jpg\" /> \r\n</p>\r\n<p style=\"text-align:center;text-indent:2em;\">\r\n\t<span>艾丽斯·沃尔顿（Alice L. Walton）</span> \r\n</p>\r\n<p style=\"text-align:center;text-indent:2em;\">\r\n\t<span><br />\r\n</span> \r\n</p>\r\n<p align=\"center\">\r\n\t<img title=\"全世界最有钱的十位富豪 房子到底长啥样(组图)&#10;点击图片查看下一页\" alt=\"\" src=\"http://imgs.soufun.com/news/2011_06/23/news/1308791143842_000.jpg\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t艾丽斯·沃尔顿净资产:155亿美元，沃尔玛创始人山姆·沃尔顿的长女，艾丽斯·沃尔顿以200亿美元的资产在全球富豪中排名第六位，是地球上最有钱的女人。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(54,'<p style=\"text-indent:2em;\">\r\n\t本周过后，人们就将迎来一年一度的“五一”假期。按照往年规律，每年的“五一”假期都是春游出游高峰的一个爆发点，今年也不例外。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t同程网机票事业部发布的最新数据表明，目前，节前游北京、上海、广州、杭州等地飞往三亚、厦门、海口、昆明等春节热门旅游城市的机票已基本售罄。另外，从上周开始，“五一”假期过后上述热门航线的返程机票(5月1日至4日)余票已不多，价格也处于高位，最高折扣也仅为7折左右，其中，5月1日的返程票不乏全价票且供应紧张。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t针对节日前后机票供求趋势的剧烈波动，同程网特别发布了“拼假攻略”为“五一”出游人群提供出行建议。“拼假攻略”建议大家在5月2日和5月3日两天休假以拼凑出长达7天的假期，这样不仅可以充分享受轻松的旅途，还可以避开机票、酒店等服务的预订高峰，这一方案获得了众多旅游爱好者的支持。另外，同程网还通过微博、微信等渠道实时发布有关打折机票、酒店和景点门票的最新信息，旅游爱好者们只要通过关注上述渠道发布的官方消息即可获取有价值的出游资讯。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(55,'<p style=\"text-indent:2em;\">\r\n\t　今年“五一”小长假从4月29日开始，5月1日结束，距今只有半个月时间。目前，“五一”机票已经升温，一些短期旅游线尤其热门。<br />\r\n<br />\r\n　　昨天，萧山机场民航售票处发布了最新机票信息，商务航线如杭州飞北京，“五一”期间价格比往年略有提高；另外，受广交会影响，杭州飞广州航班基本全价，只有4月30日晚班机票有少量8折。<br />\r\n<br />\r\n　　具体价格为：杭州—北京，690元(6折)；杭州—深圳，630元(5折)；杭州—广州，1050元(全价)，4月30日晚840元(8折)；杭州—武汉，355元(5折)。<br />\r\n<br />\r\n　　如果你想去热门的旅游目的地，推荐去昆明、三亚、贵阳、青岛，“五一”期间这些地方的机票价格基本在4到5折之间，很实惠。<br />\r\n<br />\r\n　　具体价格为：杭州—厦门，360元(4折)；杭州—桂林，726元(6折)；杭州—三亚，700元(4折)；杭州—海口，924元(6折)；杭州—青岛，360元(4折)；杭州—昆明，732-915元(4-5折)；杭州—贵阳，429元(3折)；杭州—西安，400元(3.5折)；杭州—成都，720元(4.5折)。<br />\r\n<br />\r\n　　要说明的是，机票价格随机性比较强，以上价格都是昨天的票价，而且不包含180元的机场建设费和燃油费。购票前，可拨打86668666萧山机场售票处，或到各售票网站查询。<br />\r\n<br />\r\n　　另外，还有两个购票小窍门告诉大家：一是不少航空公司推出了来回程直减和提前购票优惠，来回程选择同一航空公司部分航班，优惠幅度在5%-10%不等；二是早晚航班机票价格相对较低，可以节省出行成本。<br />\r\n<br />\r\n　　前往厦门的动车票很紧张<br />\r\n<br />\r\n　　除了飞机外，选择动车出行也是时下便捷的好办法。<br />\r\n<br />\r\n　　目前，火车网络购票和电话订票的预售期为20天(含当天)，按预售期计算，现在大家就可以上网或打电话预订“五一”小长假的火车票了。<br />\r\n<br />\r\n　　铁路部门预计，今年长三角地区短途游客流会比较集中，建议大家提前买好火车票。<br />\r\n<br />\r\n　　按以往经验，长假期间，前往上海、南京等方向的车票，至少要提前2天买才有座位票；而往宁波、台州、温州、福州、厦门等方向的火车票，至少要提前5天购买，否则连站票都没了。<br />\r\n<br />\r\n　　昨天下午火车票信息：4月29日，杭州有3趟开往厦门的动车组，上午的两趟车已没有座位票；下午一趟车还有余票，不过这趟车要晚上9:30才到厦门。其余方向，杭州往宁波、台州、温州、上海、南京等方向还有余票。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(56,'<p>\r\n\t　　避免胀气，首先我们就要从饮食上注意调理。哪些食物容易胀气呢？下面一起来了解一下。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　<strong>引起胀气的蔬菜</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　豆类和十字花科蔬菜，如西兰花、花椰菜、芽甘蓝和卷心菜中含有一种复合糖叫蜜三糖，这种糖比其他种类的糖更难被人体吸收，当它在肠内被艰难吸收的同时，就会产生副产品——气体。但是，不要因为这些食物易产气就放弃食用，你可以同时食用高纤维食物来改善胀气的状况。\r\n</p>\r\n<br />\r\n<p>\r\n\t　<strong>　饮食少放盐</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　一次性吃盐过量会让身体存水，从而产生胀气。因此要尽量避免高盐食品，如包装食品、油炸食品，尤其是罐装浓汤和方便面，一份含有的盐分就接近人体一天的需求量。应多食用新鲜蔬菜和全麦食品。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　<strong>糖醇含有气体</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　糖醇是一种甜味剂，多存在于口香糖和其他无糖食品中，糖醇能够部分被消化，消化的同时也会产生气体。想避免胀气，在购买食品的时候，仔细检查一下其中是否含有糖醇一类的成分：山梨糖醇、麦芽糖醇和木糖醇等。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<strong>　牛奶宜选不含糖</strong><br />\r\n<p>\r\n\t　　如果喝牛奶一小时内，感到胀气或腹泻，甚至更严重，这是典型的乳糖不耐的症状。如果你因为乳糖不耐而胀气，最好的选择不是放弃牛奶，而是饮用那些不含乳糖的牛奶，或者吃一些帮助分解乳糖的药片。\r\n</p>\r\n<br />\r\n<p>\r\n\t　<strong>　维生素不宜恶补</strong> \r\n</p>\r\n<br />\r\n<p>\r\n\t　　富含纤维的食品可以帮助消除胀气，但如果以前吃的纤维素少，现在突然加大摄入量，同样会让你感到肚胀难受。因此，在饮食中逐渐加入纤维食品，就可以顺利摆脱胀气烦恼。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p>\r\n\t　　肚子胀气很难受。胀气的时候趴着或揉揉肚子，有利于肠道的蠕动，可促进排气，缓解肚胀，也可服用保和丸、香砂养胃丸、槟榔四消丸等药物以健脾行气、消食导滞。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　不过，若胀气持续一周以上，且伴随严重腹部疼痛、腹泻、黑便、血便等情形的，则应到医院肠胃科就诊，以免延误病情。另外，平时吃东西时，细嚼慢咽，不要一次吃得太多；饭后洗洗碗散散步，或者做一些轻缓的运动。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(57,'<p>\r\n\t　　从食品工程的角度来说，“炒”是一个典型的“高温快速”的加工过程。在高温下（通常炒菜的油温在200℃～300℃），不管是肉还是菜都会快速变熟。而对于肉或者菜中的香味，因为其损失程度受时间的影响更大一些，所以快速炒熟的肉和菜更容易保持天然的香味。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　对于肉而言，其中的水分很关键，因为水分流失的同时，许多香味物质也流失了，从而使肉变得干而无味。“码芡”可以很好地解决这个问题。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　码芡通常用淀粉（也有人将它叫做“生粉”），用水化开淀粉，加入盐、味精，与切好的肉混合，最后，肉的表面会有薄薄的一层淀粉。饭店里的淀粉是预先在水里泡了很长时间的，淀粉的水化更充分，效果更好。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　肉下锅之后，这层淀粉形成了对肉的保护层，大大减少了肉中水分的流失，因而也减少了香味的流失。加上淀粉中的调料很好地附着在了肉的表面，所以码过芡的肉用高温炒出来会显得嫩滑。但是淀粉加多了也不好，淀粉保护层如果太厚的话会影响热量往肉内部的传递，因此需要更长的时间才能炒熟，反而得不偿失。炒出来的成品太黏，也影响外形。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<table class=\"tableImg ke-zeroborder\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\r\n\t<br />\r\n\t<tbody>\r\n\t\t<br />\r\n\t\t<tr>\r\n\t\t\t<br />\r\n\t\t\t<td style=\"text-align:center;\">\r\n\t\t\t\t<img class=\"flag_bigP\" alt=\"\" align=\"middle\" src=\"http://photocdn.sohu.com/20130423/Img373688128.jpg\" /> \r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t</tbody>\r\n</table>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p>\r\n\t　　很多蔬菜，尤其是叶子菜，本身很薄，在高温下很快就被炒熟了，比如空心菜、豌豆苗、菠菜等。所以清炒素菜的关键在于动作要快，一次不能炒太多，下锅后快速翻炒，迅速加入调料，菜蔫了就出锅。炒得好的素菜应该保持着天然的绿色。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<table class=\"tableImg ke-zeroborder\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\" align=\"center\">\r\n\t<br />\r\n\t<tbody>\r\n\t\t<br />\r\n\t\t<tr>\r\n\t\t\t<br />\r\n\t\t\t<td style=\"text-align:center;\">\r\n\t\t\t\t<img class=\"flag_bigP\" alt=\"\" align=\"middle\" src=\"http://photocdn.sohu.com/20130423/Img373688129.jpg\" /> \r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t</tbody>\r\n</table>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n　　炒菜的原料需要切得均匀，不管是主料还是配料，否则小块的先熟，等到大块的熟了，小块的已经熟烂了。对于切片的菜，重要的是厚薄均匀，片的大小对于熟的速度影响很小，只影响美观；对于切丝的菜，则是粗细均匀更重要，而长短只影响美观。蔬菜的不同部位熟的速度相差较大，比如炒菠菜时最好把叶子和叶柄分开，先下叶柄炒一会儿再下叶子，豌豆苗则问题不大。而空心菜则应该把茎和叶分开，茎（有很多人是不要这部分的，如果要的话）可以先炒或者炒到大半熟时再下叶子。\r\n<p>\r\n\t&nbsp;\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(58,'<p>\r\n\t<b>  车库乱装修将集中查处</b> \r\n</p>\r\n<br />\r\n<p>\r\n\t  孙先生：李沧东城水岸小区，请问何时可以办理房产证？已经拖延了很长时间了。\r\n</p>\r\n<br />\r\n<p>\r\n\t  房师傅：经了解，该项目部分楼座于2010年1月12日取得了商品房预售许可证具备销售条件。该项目正在办理规划验收手续，暂不具备办证条件。\r\n</p>\r\n<br />\r\n<p>\r\n\t  李女士：最近开发区齐长城小区很多住户都在装修车库。装修完还要住人，物业也不管，如果车库都能改用途，那不全乱了？改变用途是不是不合法的？\r\n</p>\r\n<br />\r\n<p>\r\n\t  房师傅：经了解，对于此事，开发区政府进行了落实，该小区内部分业主将车库进行了装修，黄岛国土资源分局已对装修情况进行了初步统计，下一步将根据统计的情况逐一核实。对确有违法装修行为，影响了房屋结构安全的，黄岛国土资源分局执法人员将根据相关法律法规对其进行查处。\r\n</p>\r\n<br />\r\n<p>\r\n\t  李女士：现在堵车严重，茵悦小城前面市民公园空地能不能规划停车场？请问有这样的规划吗？\r\n</p>\r\n<br />\r\n<p>\r\n\t  房师傅：根据城阳区政府上报市政府的《城阳区春城路两侧控制性详细规划》，规划中明阳路向东延伸通至青威路，明阳路南侧与实验二小门前规划有一集中的社会停车场，建成后可以缓解停车难问题。该片规划已经经市城规委审议通过，正在进入公示程序。\r\n</p>\r\n<br />\r\n<p>\r\n\t  郑先生：我是外地户口，想申请公租房，请问要走什么程序，应该怎么做？\r\n</p>\r\n<br />\r\n<p>\r\n\t  房师傅：新就业职工、外来务工人员申请公共租赁住房，根据规定，由其所在单位统一向单位所在地的区住房保障机构提出。目前青岛市建设的面向新就业职工、外来务工人员的公共租赁住房正在建设和筹集中，暂无可配租的房源，下步待房源基本具备配租条件后，将按规定组织实施配租。\r\n</p>\r\n<br />\r\n<p>\r\n\t<b>  烂尾服装城正积极引资</b> \r\n</p>\r\n<br />\r\n<p>\r\n\t  郑女士：2007年我们去城阳服装城买了铺面，当时真是拿出一辈子积蓄，想做点生意改善一下生活，可没想到现在竟然成了这样，至今没建成，到底怎么回事？\r\n</p>\r\n<br />\r\n<p>\r\n\t  房师傅：今年对于城阳服装城的问题，本报曾进行报道。最新进展是为尽快启动该项目，目前相关街道正在积极协助投资方与台湾、浙江等地的合作方洽谈合作事宜，督促投资方推进项目建设，争取早日营业。该项目周边仲村旧村改造和龙湖开发项目已经开工，相邻的总投资100亿元的天安数码产业城项目已开工建设，青岛实验高中已在该项目东侧选址，通过周边各项目的拉动，未来几年该项目周边人气将迅速积聚，对项目的启动将起到积极的作用。关于投资问题，建议投资人可以通过法律途径维护自己的合法权益。\r\n</p>\r\n<br />\r\n<p>\r\n\t  李先生：买易佳和府两年了，到底何时能交房？\r\n</p>\r\n<br />\r\n<p>\r\n\t  房师傅：经了解，该项目分两期交付，一期合同约定交付时间为2013年3月31日，二期合同约定交付时间7月31日，因工程施工缓慢，一期未能按时交付。目前该项目已进入装修阶段，计划8月底前全部交付。城阳区城建部门已责令开发公司加快施工进度，并按照合同约定赔偿业主违约金。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(59,'<p style=\"text-indent:2em;\">\r\n\t22日从市住房公积金管理中心获悉，为了帮助芦山地震重灾区在厦职工克服困难，重建家园，自4月22日起，凡是户籍在四川省雅安市芦山县、宝兴县、汉源县、荥经县、天全县、石棉县、雨城区、名山区8个重灾县(区)的在厦职工，可申请一次性提取本人及配偶账户内的住房公积金。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p>\r\n\t<strong>申领材料</strong> \r\n</p>\r\n<p>\r\n\t1.职工本人身份证原件及复印件;\r\n</p>\r\n<p>\r\n\t2.职工所在单位盖章的《厦门市住房公积金支取/转移申请表》;\r\n</p>\r\n<p>\r\n\t3.提取配偶住房公积金的，还应提供夫妻关系证明;\r\n</p>\r\n<p>\r\n\t4.委托他人代办的，还应提供代办人身份证原件及复印件。\r\n</p>\r\n<p>\r\n\t<br />\r\n</p>\r\n<p>\r\n\t<strong>申领时间</strong> \r\n</p>\r\n<p>\r\n\t符合条件的职工可在2013年12月31日前申请一次提取个人住房公积金账户内的余额。\r\n</p>\r\n<p>\r\n\t<br />\r\n</p>\r\n<p>\r\n\t<strong>申领地点</strong> \r\n</p>\r\n<p>\r\n\t1.市政务服务中心(湖里区枋湖云顶北路842号)一层C厅市住房公积金管理中心。\r\n</p>\r\n<p>\r\n\t2.同安区祥平西路土地大楼一楼住房公积金窗口地震受灾职工绿色通道。\r\n</p>\r\n<p>\r\n\t<br />\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(60,'<p>\r\n\t　　本报拉萨电 <br />\r\n（记者扎西）进入4月以来,随着雪域高原气温的回暖，西藏旅游旺季的序幕悄然拉开。连日来，拉萨八廓街、布达拉宫广场、罗布林卡等旅游景点的游客明显增多。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　记者从西藏自治区旅游局获悉：西藏今年第一季度累计接待游客300599人次，实现旅游总收入30026万元，同比增长31.1%。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　据介绍，去年以来，西藏持续在内地主要客源地开展有针对性的宣传推广活动，不断加大“冬游西藏”促销力度，并与旅游文化企业积极开展以“四大节庆”为主的复合营销等活动，使得旅游支撑面越来越广，质量也越来越高，旅游呈现出大发展、快发展的良好态势。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　《 人民日报海外版 》（ 2013年04月25日 第 08 版）\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(61,'<p>\r\n\t　　24日下午，全国人大常委会第三次委员长会议决定根据常委会审议意见，对旅游法草案作进一步审议修改后，交付今日全国人大常委会第二次会议闭幕会表决。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　24日，全国人大常委会组成人员分组审议旅游法草案时，就景区门票问题展开热议。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　“针对这次凤凰古城收费的问题，我要谈谈看法。”全国人大常委会委员吴晓灵说，凤凰突然把古城封闭起来，要收费。他们的理由是，周庄、平遥这样的古城可以收费，我们就不可以收费吗？事实上，一些地方把古城圈起来收费的效果并不好，有的甚至在亏损经营，并没有因为收费而形成良性循环。吴晓灵建议在旅游法当中对景区设立有一个法律规定，什么情况下、通过什么程序可以设立景区，这个问题应该在旅游法草案中进一步完善。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　全国人大常委会委员王毅说，事实上，旅游法草案中对景区收费已经有了明确规定：利用公共资源建设的景区的门票和景区内的游览场所、交通工具等另行收费项目，实行政府定价或者政府指导价，严格控制价格上涨。拟提高价格的，应当举行听证会，征求旅游者、经营者和有关方面的意见，论证其必要性、可行性。王毅表示，像凤凰古城收费这件事情，在旅游法草案中是比较难以界定的，因为整个古城不全是公共投资的，其中有一部分是私人投资的，这种情况该如何定义？因此，他建议旅游法草案对于景区的定义能够更加清楚一些，以便在处理这种案例时可以找到适用的法律条文。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(62,'<p>\r\n\t　　近期，我国游客在欧洲特别是法国多次遭当地黑人偷、抢，成为出行安全隐患，给游人和旅行社造成一定程度的损失，就安全出游应该注意哪些事项，本报记者专访成都中国青年旅行社副总经理张祥静女士，对游客给出一定的指导与建议。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　张祥静建议，游客调整出游理念，尽量少带现金出游，避免造成不必要的损失；应避免炫富行为，避免成为偷抢的对象；同时应该选择正规的旅行社出游，不仅仅看重旅游产品的价钱高低，更要看重出游的品质，一般价格相对较高的旅游产品，出行的交通工具、饮食、住宿等条件都会更好一些，出行安全就多一份保障。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　成都中青旅在出游时，不仅仅会提供详细的出行明细表，在回程之后让游客填写回访表存档，日前成都中青旅欧洲五条专线已经合并，成立了欧洲旅游公司，以便更好地为游客服务。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(63,'<p>\r\n\t　　国台办发言人范丽青24日表示，两岸双方都会进一步采取切实措施，保障大陆游客赴台旅游的安全。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　国台办24日举办例行新闻发布会，有记者问：近日大陆游客赴台旅游再次出现了安全问题。请问发言人，对赴台游的安全问题，您怎么看？\r\n</p>\r\n<br />\r\n<p>\r\n\t　　范丽青表示，政府一直高度重视大陆游客赴台旅游的安全问题。在海旅会和台旅会举行的磋商中，也多次就做好赴台旅游的安全保障问题进行过沟通和研究。两岸双方都会进一步采取切实措施，做好大陆游客赴台旅游的安全保障工作，避免发生旅游安全事故。希望大陆游客高高兴兴地去，平平安安地回。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(64,'<p>\r\n\t　　正当凤凰通票制引起广泛关注之时，十二届全国人大常委会第二次会议23日审议的《旅游法》草案将焦点对准景区门票，强调景区门票价格不能说涨就涨，应严格规范程序。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　景点门票价格频涨，引发公愤以及舆论纷纷，恐怕是旅游法草案顺应民意，将景区门票上涨作为审议重点的原因。但是，旅游法的最终功能不是停留在限价上，更要“限权”。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　旅游法首先要限制的是景区经营方的权力。国内不少旅游景点实现的是经营承包制，不过应认识到，旅游景点是有别于一般商品的特殊资源，理应被界定为公共资产，产权拥有者指向广大民众。但目前实行的是所有权与管理权分离。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　而现在最容易引发弊端的就是，某些地方将景区管理偷换概念变成了景区经营，企业就打着经营旗号变着法子，设计出各种明涨或者暗涨的路径。旅游法必须要校正这种思维误区，要明确景区只能公益化管理而不能商业化经营的价值核心。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　旅游法还要限制的是某些地方政府的权力。湖南凤凰“涨价”就是地方政府权力不受限制的典型案例。尽管当地官方信誓旦旦地说，政府绝对没有参与门票分成。可是其一系列动作，包括所谓约谈当地商家不准停业，都让人怀疑其中藏有官商利益互通的猫腻。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　旅游法应明确规定，地方政府不得干预景区经营，不能将景区当成自家财产。也许大家对旅游法能否具有如此效力持怀疑态度。实际上，旅游法要限制住地方政府的权力，人大就可发挥主导作用，维护公共利益。\r\n</p>\r\n<br />\r\n<p>\r\n\t　　景区不讲理的乱涨价背后，反映出来的是景区产权与管理权界定混乱、某些承包企业与地方政府权力滥用等深层次因素，而旅游法需要做的就是正本清源，让公益归位、责任守位、权力不再越位。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(65,'<p align=\"center\">\r\n\t<img alt=\"\" src=\"/member/portal/demo/201304/251136439rgs.jpg\" /> \r\n\t<div align=\"left\">\r\n\t\t&nbsp;&nbsp;\r\n\t</div>\r\n\t<p style=\"text-indent:2em;\" align=\"center\">\r\n\t\t图为零能耗太阳能住房模型。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\" align=\"left\">\r\n\t\t<br />\r\n&nbsp;<br />\r\n　　由斯坦福工程学院学生设计、50名学生从2013年3月底开始建造的这座太阳能独户住房以木质为主体，面积大约92平方米，有两个卧室、一个卫生间，造价约25万美元。这座太阳能住房以零净耗能、造价相对低廉为特色，核心是一个标准化中央控制组件，用于调适所有管道、煤气和水电供应以及供暖和制冷。 <br />\r\n新华社发\r\n\t</p>');--end
 INSERT INTO tq_article_txt VALUES(66,'<p style=\"text-indent:2em;\">\r\n\t夜幕降临，312国道旁的相城区望亭镇鹤溪社区广场上，响起了动感十足的音乐，男女老少陆续走到一起，有的兴致勃勃地跳起广场舞，有的天南海北聊起了新鲜事。<br />\r\n<br />\r\n　　鹤溪社区是由7个村的动迁农民组成的复合型社区，与许多涉农社区一样，居民的户口还留在已经不见踪影的农村，他们的社会福利乃至生老病死，村里想管管不着，社区要管管不了。人户分离，使进城农民成了没有着落的游民，成了城乡一体化中社会管理的一个难题。<br />\r\n<br />\r\n　　从2011年起，望亭镇通过“户籍平移”，把1702户动迁家庭6956位农村居民的户口移进了鹤溪社区，用统一的福利、触手可及的社区服务，破题复合型社区管理。<br />\r\n<br />\r\n　　“想管管不了”逼出新办法<br />\r\n<br />\r\n　　鹤溪社区成立于2010年1月，2500多户居民中，1700多户是何家角、新埂、迎湖等7个行政村的动迁户，还有800来户是商品房住宅。尽管是社区的“当家人”，但鹤溪社区党支部书记许春兴在上任之初，就面临了一系列管理上的难题。<br />\r\n<br />\r\n　　大多数动迁居民搬入社区后，户口仍留在原村，而报销医保、领取社保、申请救助、出具证明等需求都与户籍挂钩，社区没法管。对原来的行政村来说，居民分散在新社区不同楼幢，固定电话大多换了号，有了新政策、新信息人难找、联系不上是常有的状况。而居民也是一头雾水，有人觉得自己还是村里人，社区管不着；有人认为已经住进社区了，不再受村里管。一来二去，形成了“村里管不着，社区管不了”的尴尬局面。<br />\r\n<br />\r\n　　这类社区的管理服务出现盲区，根源在于“人户分离”。在多次调研、讨论后，望亭镇作出了“户口跟着住房走”的决定，对全镇动迁居民实行户籍平移，在坚持自愿的基础上，动员动迁居民把原先散落在各村的户口统一迁入现居地。<br />\r\n<br />\r\n　　统一享福利送上“定心丸”<br />\r\n<br />\r\n　　望亭镇成立了户籍平移工作小组，对全镇居民安置房房源、户籍人口信息等情况进行摸底。<br />\r\n<br />\r\n　　户籍平移不是户籍信息的简单变更，更牵涉着动迁居民的利益。与许多地方一样，望亭的村级经济发展有落差，村民的股红、福利也有高低。面对这种状况，望亭镇对各村居民的福利待遇进行调查，及时出台配套政策，维持居民资产量化股、福利股不变。<br />\r\n<br />\r\n　　统一享福利，从制度上给动迁居民吃了“定心丸”。年过七旬的王荣皋是何家角村的动迁居民，搬进鹤溪社区后，“恋根”的他没有随儿辈们“户籍平移”。去年，王荣皋生了三场大病，每一场病的医疗费报销都是住在鹤溪社区的孙子跑回原村办理的。12月中旬的一天，孙子听社区工作人员说，王大爷可以向户籍所在地申请农村大病医疗救助，截止日期是12月20日。人户分离，险些误了该有的福利和补助，王荣皋的孙子赶紧把申请递回了原村，并动员老人把户口迁到社区。当老人的5000元补助金下来后，社区工作人员连同200元“敬老金”、村里的年底股红，一起送上了门，王荣皋欣喜不已。<br />\r\n<br />\r\n　　2500户居民共筑一个“家”<br />\r\n<br />\r\n　　随着越来越多动迁居民平移户籍成了“社区人”，鹤溪社区建成投用2000多平方米的社区服务中心，内设一站式大厅、社区警务室、社区卫生服务站、居家托养室、0至3岁早教室、健身室、图书室等配套设施，还组建舞蹈、太极拳、腰鼓等5支文艺团队，1702户动迁居民和800户商品房住户，组成了一个其乐融融的“大家庭”。<br />\r\n<br />\r\n　　鹤溪社区还通过建立居民代表大会、居民楼道长和党群议事会等制度，把动迁农民变成社区“管理者”，提升居民自治的能力。去年，鹤溪社区建立起一支50多人的志愿者队伍，何家角村51岁的动迁居民邹美华就是其中一员。宣传政策、帮腿脚不便的老人跑腿成了她“工作”的重点。去年发放市民卡期间，邹美华还和其他志愿者一起把市民卡一家家送上门，并细致地解答市民卡激活等问题。<br />\r\n<br />\r\n　　户籍平移，还给基层换届选举带来了福音。以往村里选举，村干部们起早贪黑到处找村民，往往还会扑空。而户籍平移让鹤溪社区摸清了底数，村民参选率大幅提升。在去年选举镇人大代表中尝到了“甜头”的许春兴，接下来还打算把社区居民的党组织关系平移到社区，让党员们过上丰富多彩的组织生活。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(67,'<p style=\"text-indent:2em;\">\r\n\t国家救援队队员、中国地震局现场评估专家贾群林，在连续参加3天救援后，建议农民自建住房时改变“高、大、空、重”的传统做法，把自家房子盖结实点。\r\n</p>\r\n<br />\r\n<p style=\"text-indent:2em;\">\r\n\t所谓“高、大、空、重”，就是随意加高住房，房间跨度大、纵深大、空间大，把重要生活资料如粮食等放在顶层。\r\n</p>\r\n<br />\r\n<p style=\"text-indent:2em;\">\r\n\t贾群林说，从这几天救援情况看，农村房屋损毁率高达90%左右。结合农村实际，他建议：一要选好建房的基础。最好不要选择自行回填，尤其是山坡地回填的地方盖房。芦山县龙门乡王家村就有不少这样的案例，教训惨痛。二是要注意选择正规设计部门设计的图纸。不要贪图便宜，不要盲目攀比，最好每一层都打圈梁，要有抗震柱，保持建筑的整体稳定性。三是要注意投资比例。许多人愿花5000元去贴外墙瓷砖，却不愿花5000元给承重墙埋几根钢筋。应把钱花在基础上，增加圈梁、钢筋等。\r\n</p>\r\n<br />\r\n<p style=\"text-indent:2em;\">\r\n\t贾群林建议，重建一定要注重抗震因素，在面对地震等自然灾害时为自己上一道“安全阀”。(\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(68,'　　中工网讯 (记者丛民)近日，记者接到山东省肿瘤医院多名职工的反映，虽然他们已在该医院工作多年，可单位并没有给他们办理住房公积金缴存登记手续。对此，记者决定一探究竟。<br />\r\n<br />\r\n　　刘女士从2005年至今，已经在山东省肿瘤医院工作了近8年时间。她告诉记者，医院像她这样被人为划分为“二等公民”的人员有400多人。虽然从事的工作和“编制内”的人员并无区别，但收入却相当悬殊。“同工不同酬，谁让我们没编制。工资如此，可是我们属于住房公积金缴存范围内的职工啊。”刘女士对医院的做法十分不解。<br />\r\n<br />\r\n　　曾在该肿瘤医院工作多年的郭女士也向记者反映，她当初从事的是护士岗位工作。“我们辛苦工作为医院创造效益的同时，医院却剥夺了我们应有的保障，这种做法极不负责。正是因为这家医院不给交公积金，我只好选择离开。”她告诉记者，同样是没有编制，她现在就职的医院就按照规定为其缴纳了劳动保险、住房公积金等。<br />\r\n<br />\r\n　　山东省肿瘤医院人事科一位不愿透露姓名的工作人员称，之所以没有给这400多名职工交公积金是不知道要交，这部分人是编制以外的招聘人员或临时工，其中一部分职工的工作关系不在本单位，有的挂靠在劳务派遣公司， <br />\r\n所以一直都没有给他们缴纳相关的住房公积金。<br />\r\n<br />\r\n　　济南市住房公积金管理中心相关负责人告诉记者，按照《住房公积金管理条例》规定，济南行政区域内的国家机关、国有企业、城镇集体企业、外商投资企业、城镇私营企业及其他城镇企业、事业单位等的在岗职工均应按月按比例主动缴存住房公积金。缴存职工的范围包括与单位形成劳动关系，并领取劳动报酬，用工期在1年以上的劳动者。<br />\r\n<br />\r\n　　该负责人指出，无论劳动者是否为在编职工、临时工，只要劳动者与该单位形成事实劳动关系，单位都要为劳动者交住房公积金。山东省肿瘤医院的做法明显违反了这一规定。<br />\r\n<br />\r\n　　日前，记者到该院进一步采访此事时，进展并不顺利。该院分管人力资源的徐科长称，记者需要先通过该院院务处联系。然而，当记者联系院务处时，一位尚姓工作人员又表示，此事需要请示院领导，随后又以院领导在开会等理由加以推脱。随后几天，记者多次拨打电话与该工作人员联系，均未得到任何实质性的回复。截至发稿之日，记者仍未在此事上得到来自院方的任何答复。<br />\r\n<br />\r\n　　作者：丛民来源中工网--《工人日报》) <br />');--end
 INSERT INTO tq_article_txt VALUES(69,'<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img alt=\"\" src=\"http://newpaper.dahe.cn/hnsb/images/2013-04/25/C04/C04b001.jpg\" width=\"500\" height=\"333\" />\r\n</p>\r\n<div>\r\n\t&nbsp;\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t在政策不断紧缩，市场竞争激烈的今天，要赢得购房者认同，开发企业除了在房价上“下功夫”之外，是否还应真正做到让购房者明明白白消费\r\n</p>\r\n<div>\r\n\t&nbsp;\r\n</div>\r\n<p align=\"center\">\r\n\t<img alt=\"\" src=\"http://newpaper.dahe.cn/hnsb/images/2013-04/25/C04/C04b002.jpg\" width=\"500\" height=\"333\" />\r\n</p>\r\n<p>\r\n\t<br />\r\n</p>\r\n<div align=\"center\">\r\n\t预验房开了郑州地产业交房先河\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n　　家是心灵的港湾，也是每个人内心的牵挂。相对于购房者穷其所有的艰辛和努力，把住房产品“知情权”还给购房者，对开发商来说，也许不应视为一件“难事”、“麻烦事”。然而，现实是，“建房容易，交房难”正在成为一个越来越广泛、越来越普遍的社会问题。<br />\r\n<br />\r\n　　而就是在这种背景下，最近永威·翡翠城推出了“预验房”活动，让客户先看房，有问题马上整改，等到正式验房的时候，一边交钱，一边交钥匙。这样也不会让业主长时间等待，大大减少交房等待时间，提高交房效率。这也是目前郑州第一个推行“预验房”的住宅项目。在涉及入住这一不少开发商“避之不及”的敏感话题面前，永威·翡翠城的“高调”和“勇敢”，彰显了永威对自身品质充满自信，也获得了更多购房者的关注和认可。<br />\r\n　　<br />\r\n　　<strong>预验房之后</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>迫不及待想搬新家</strong><br />\r\n<br />\r\n　　“没想到永威·翡翠城会这么做。”业主刘先生说，接到永威·翡翠城工作人员邀请他们来参加预验房活动的邀请时，他感到很意外，“从来没听说过预验房，不明白为什么会这么做，还以为是走走形式”。<br />\r\n<br />\r\n　　可是当他按约定时间来到售楼处时，才发现物业工作人员早早地就在售楼处等候，保安们有序地为每一位到场的业主引导车辆。前来验房的业主也是络绎不绝。“大家在公司相关人员的陪同下，前往自己购置的新家园。来的业主们有带着家人的，有带着朋友的，也有带着专业验房公司的专家的，大家有说有笑，感觉既是一次预验房，也是一次业主们和永威·翡翠城提前进行的美好约会，好一派其乐融融的和谐场景。”刘先生说。<br />\r\n<br />\r\n　　“好气派的大堂……”刚进入9号楼的入户大堂，业主李女士就不由得发出一声赞叹。看到永威·翡翠城的入户大堂选用了高档大理石材铺装，加上美轮美奂的雕塑，不少业主都说：这样的大堂，才能真正显示主人的身份。进入室内，开始住房工程的验收阶段，业主们带来的专业的验房者开始发挥作用。<br />\r\n<br />\r\n　　“我也做房地产这么多年了，永威的房子做得确实不错，如此之高的交付标准，说实话真的不容易……”一位参加验房的人说，“在预交付阶段能做得如此出色的楼盘真的很少见，基本所有项目和指标都能达到正式交付的要求，永威·翡翠城算是一个了。”<br />\r\n<br />\r\n　　更值得一提的是，永威·翡翠城园区绿化全部采用原貌种植的方法，克服了大面积移植过程中的种种困难，完整保留树种原有形态，大大地提高了树种的成活率，同时也为业主提供了更高的绿化观赏性。行路旁的水景，碧绿的草坪，让很多小业主流连忘返。<br />\r\n<br />\r\n　　进入室内，住房工程的验收才真正开始。业主们仔细地检查起房间的各个细节，并不时地询问身旁的工程人员。为了方便业主们顺利验房，置业顾问还专门准备了一张验房表单，表单上详细列出了验房中的重点项目。\r\n</p>\r\n<hr style=\"page-break-after:always;\" class=\"ke-pagebreak\" />\r\n<p>\r\n\t<br />\r\n　　“房子质量很好，细节也做得很精细，有点超乎我的想象。我们全家都来到了现场，验房专家特别认真，房子的里里外外没有一处被落下。预验房过程中，工作人员全程陪同，详细为我们这些业主答疑解惑，从房间大小到层高、水电，从门窗、工艺甚至到外墙保温，无不被验房专家严苛审验。特别是预验房结束后，工作人员就我们关心和需要优化的地方做了详细记录，并承诺将在房子正式交付之前进行完善和解决。先不说其他的，就这种态度，我都觉得非常好。这次预验房让我心里更踏实了，现在都已经迫不及待想搬新家了。”参加完预验房活动，刘先生频频点头说道。<br />\r\n<br />\r\n　　<strong>“新鲜事”背后</strong><br />\r\n<br />\r\n<strong>　　让购房者明明白白消费</strong><br />\r\n<br />\r\n　　有关人士认为，市场经济发展了30年，“利益”成了市场价值的主流。买房是头等大事，但在商品房预售制度这一背景下，购房者只能摸着石头过河，往往花了钱还得靠运气，让购房者对开发商越来越缺乏信任。因此永威·翡翠城推出的预验房，实质上是把某些“知情权”还给了消费者，虽然这本来就属开发商应做之事，但也算“新鲜”。更为重要的是，由此为郑州楼市提了一个醒，在政策不断紧缩，市场竞争激烈的今天，要赢得购房者认同，开发企业除了在房价上“下功夫”之外，是否还应真正做到让购房者明明白白消费。<br />\r\n<br />\r\n　　差不多算是楼市资深人士的业主张先生投资过很多套房子，他表示，他投资的上个房子，在去年年底拿到了新房的钥匙，欢欢喜喜地去看房子却发现墙面出现了裂缝，开发商却说是温度变化造成的伸缩缝，不影响使用。张先生有些懊恼地告诉记者，没有专业的人来鉴别问题是否严重，如果开发商不给修，就只有通过“对簿公堂”来解决了。<br />\r\n<br />\r\n　　“对于我们这些普通老百姓来说，买房子以刚需居多，就算是投资也是想买合格的，转手的时候也好进行买卖。但是房子作为高价值、不可复制的一种特殊产品，买房者如果没有专业知识，不懂得如何鉴别，最终极可能导致自己的利益受损。之前郑州市出台的交房政策，我觉得验房制度还需要完善、细化一些，比如可以增加一项\"预验房\"活动，就像永威这样。在正式交房前，提前让业主验房，要是业主在预验房过程中，发现不符合合同中约定条款的情况，能要求对方整改，这样未来可以减少房屋质量纠纷。”<br />\r\n<br />\r\n　　让业主对自己购买的住宅产品满意度达到百分之百，开发商也许有点“追求完美”。不过，永威·翡翠城预验房活动显然得到了业主的认同，不少接受采访的业主表示，房子未到交付期，就邀请业主前来预验房，并对业主“挑”出的问题进行解决、整改，这在他们看来“是一件新鲜事”。<br />\r\n<br />\r\n　　“这也是一件好事，看了(房子质量)没问题，大家都高兴，如果有问题，现在提出来，要求解决，免得日后\"扯皮\"。”接受采访的业主张女士说，接到邀请后，她还特意召集几位“懂建筑”的朋友来一起验房。<br />\r\n<br />\r\n<strong>　　诚信永威</strong><br />\r\n<br />\r\n<strong>　　好品质不怕验</strong><br />\r\n<br />\r\n　　现在看来，永威·翡翠城此次针对4月26日一期的交付而进行的预验房活动，获得了广大业主和业界人士的高度评价。对于交房这个敏感话题，很多开发商往往“避之不及”，但是永威置业秉着“开放、专业、真诚服务客户”的态度对待，目的是希望业主能在预验房活动中发现问题，解决问题，从而在交房日顺利愉快地拿到钥匙。同时这也是永威标杆品质的一个有力见证，是永威置业自信和真诚的表现。一业主称，预验房开了郑州地产业交房先河，这样不仅让我们看到了永威的品质，更加增强我们对永威品牌与品质的信心。<br />\r\n<br />\r\n　　“我们历来倡导在问题和矛盾面前不退缩、不逃避，积极面对，努力寻求最佳解决方案，妥善解决问题和矛盾。”永威置业营销总监王刚表示，开展面积公示、预验房活动，就是要让业主提前了解各项细节，给业主充足的时间进行咨询，让业主在正式交房前做到心里有数，从而在正式交房时，心里明明白白，更加放心。<br />\r\n<br />\r\n　　据了解，活动期间，永威·翡翠城广大业主不仅可以掌握销售的面积信息，了解交房应缴纳的各项费用，查验所购房屋的建筑质量，更可以体验小区物业的贴心服务。此次开放式预验房活动，现场安排物业人员全程陪伴，认真听取业主的要求和意见，并将业主整改需求填写记录在册。业主提出的整改要求，物业公司会在第一时间报至开发公司工程部及时进行整改。<br />\r\n<br />\r\n　　王刚说，做最好的产品，给客户提供最满意的服务，是永威一直为之努力的，永威置业在与业主们的沟通过程中，充分认识到一个楼盘的交付不仅意味着项目建造阶段的结束，也是物业工作的开始。<br />\r\n<br />\r\n　　“永威置业要做\"中原精细化地产的扛旗者\"。品质、诚信，我们承诺的一定会做到。作为有着雄厚资金实力和建设经验的开发商，我们希望能够提升郑州房地产的水准，无论是户型、景观、装备、建材还是建造一个和谐的社区，我们要把郑州整个房地产的市场拉到一个更高的水平，为郑州市民呈上更多的高品质楼盘。”王刚认为，在交房前为业主提供这样全透明的验房活动非常有意义，也能够让业主更加放心。“同时我们也希望给业主搭建一个合理的沟通渠道。”\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(71,'<p>\r\n\t&nbsp;　　昨日，中国首部旅游法以150票赞成、5票弃权，表决通过。《中华人民共和国旅游法》将于2013年10月1日起施行。该法律规定，今后景区提高门票价格应提前六个月公布，而且门票是否该涨还须举行听证会论证。&nbsp;\r\n</p>\r\n<p>\r\n\t<br />\r\n&nbsp;　　<strong>立法三十年磨一剑&nbsp;</strong> \r\n</p>\r\n<p>\r\n\t<br />\r\n&nbsp;　　昨日通过的首部旅游法，也是十二届全国人大常委会通过的第一部法律，共十章，包括总则、旅游者、旅游经营、旅游规则和促进等，共一百一十二条。&nbsp;\r\n</p>\r\n<p>\r\n\t<br />\r\n&nbsp;　　昨日下午，在全国人大常委会举行的旅游法新闻发布会上，全国人大常委会委员、全国人大财经委副主任委员尹中卿用“三十年磨一剑”，描述旅游法的立法过程。&nbsp;\r\n</p>\r\n<p>\r\n\t<br />\r\n&nbsp;　　他表示，1982年，国务院有关部门就着手起草旅游法。1988年，旅游法曾列入七届全国人大常委会立法规划。由于我国旅游业刚刚发展起来，各个方面对旅游立法涉及的一些重要问题认识不尽一致，旅游立法一直没有提上正式议程，“我们起个大早，赶个大晚”。&nbsp;\r\n</p>\r\n<p>\r\n\t<br />\r\n&nbsp;　　<strong>禁低价揽客安排购物</strong> \r\n</p>\r\n<p>\r\n\t<br />\r\n&nbsp;　　全国人大常委会法工委经济法室主任王超英表示，这部法律的聚焦点是权益保护，最大的亮点是规范“零负团费”和景区门票。\r\n</p>\r\n<p>\r\n\t<br />\r\n&nbsp;　　旅游法规定，利用公共资源建设的景区的门票以及景区内的游览场所、交通工具等另行收费项目，实行政府定价或者政府指导价，严格控制价格上涨。拟收费或者提高价格的，应当举行听证会，征求旅游者、经营者和有关方面的意见，论证其必要性、可行性。旅游法还规定，景区提高门票价格应当提前六个月公布。\r\n</p>\r\n<p>\r\n\t<br />\r\n&nbsp;　　针对屡禁不绝的“零负团费”旅游，旅游法规定，旅行社不得以不合理的低价组织旅游活动，诱骗旅游者，并通过安排购物或者另行付费旅游项目获取回扣等不正当利益。旅游法规定，旅行社组织、接待旅游者，不得指定具体购物场所，不得安排另行付费旅游项目。同时规定，经双方协商一致或者旅游者要求，且不影响其他旅游者行程安排的除外。\r\n</p>\r\n<p>\r\n\t<br />\r\n<strong>&nbsp;　　■ 焦点</strong> \r\n</p>\r\n<p>\r\n\t<br />\r\n&nbsp;&nbsp;　　发改委回应“凤凰涨价”\r\n</p>\r\n<p>\r\n\t<br />\r\n&nbsp;　　称景观归全民，不该以有钱没钱来划线\r\n</p>\r\n<p>\r\n\t<br />\r\n&nbsp;　　昨日的发布会上，国家发改委社会发展司司长王威回应凤凰古城涨价时表示，自然文化、珍贵遗产，一个是大自然的杰作，一个是祖先的遗作，应该是全体中国人民，甚至是全世界人民都应该享受的，不应该以有钱没钱来划线，希望大家都能领略、都能感受这种杰作，将来一定会逐步地降价或者低票制。\r\n</p>\r\n<p>\r\n\t<br />\r\n&nbsp;　　王威表示，中国处在一个发展阶段，国家要干的事情比较多，遗产保护也有很多事情要办，各地可能对价格也都在进行探索和管理。但是，探索和管理应该有一些原则：公平、公正，利益相关方要充分研究、论证，最终实现一个方方面面都能接受的局面。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(72,'<p>\r\n\t　　新华网太原4月25日专电(记者魏飚)“五一”出游高峰即将到来，在消费者即将出游之际，山西省消费者协会发布今年第6号消费提示，提醒游客在出游时注意选择正规旅行社，在出游过程中发现旅行社有违规行为时注意保留证据，作为日后维权依据。\r\n</p>\r\n<p>\r\n\t　　山西省消协提醒说，游客在选择旅游产品时，不要被价格牵着走，而要结合“吃、住、行、游、购、娱”等旅游要素，分析旅游产品的品质，以防充满购物陷阱的“零负团费”产品。签订合同时，明确旅行的行程安排、所乘交通工具及旅行中的吃、住档次和标准等。\r\n</p>\r\n<p>\r\n\t　　在异地购物时，游客要选择信誉较好的正规商场，要求商家开具发票，在发票上标明购买商品的名称、数量、单价，并加盖商家发票专用章或财务章。山西省消协提醒消费者，保存好旅行社的收费发票、景点门票、购物发票等消费凭证。对旅行社擅自更改项目所产生的费用，游客更要索取发票，以便作为发生纠纷时投诉举报的依据。(\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(73,'<p style=\"text-indent:2em;\">\r\n\t围绕住房公积金的“猫鼠博弈”始终在进行。虽然通过不断完善的管理制度，已经堵住了大部分漏洞，但仍面临很多现实困境。对此，接受《经济参考报》记者采访的专家指出，解决住房公积金制度在现实中暴露的诸多问题，不仅应严厉打击非法套取行为，也应统筹设计，使住房公积金制度进一步完善。\r\n</p>\r\n<p>\r\n\t<br />\r\n</p>\r\n　首先，加强信息联网，严厉打击非法套取住房公积金的行为。中央财经大学民生经济研究中心主任李永壮认为，在现有制度框架下，应及时填补政策漏洞，将住房公积金信息与民政部门、社保部门以及公安部门进一步打通，加强信息联网，对各类非法套取公积金的行为予以严厉打击。\r\n<p>\r\n\t　　其次，加强各地住房公积金统筹程度，提高公积金增值收益。\r\n</p>\r\n<p>\r\n\t　　中央财经大学金融学院党委副书记李德峰认为，住房公积金沉淀资金增值不畅，除了投资渠道有限以外，另一个重要原因就是资金过于分散，统筹程度太低，无法实现集中投资。分散各地的住房公积金管理中心，既缺乏投资经验，又缺乏投资能力。增强公积金统筹程度，一方面可以加强公积金的区域间调剂，实现资金的充分利用，另一方面也可以实现集中委托、集中投资，通过专业机构投资债券市场、货币市场乃至资本市场，寻求资金升值，提升运作效率。\r\n</p>\r\n<p>\r\n\t　　第三，改善公积金增值分配机制，以盈利反哺缴存者。\r\n</p>\r\n<p>\r\n\t　　李永壮认为，按照现有分配体系，住房公积金赚多赚少都与缴存者无关，中低收入职工成为制度的最大“付出者”，进而导致非法套取公积金的行为花样百出。应改善公积金增值的分配机制，将增值收益除去贷款风险准备金、管理经费后的部分，以提高利率等形式反哺缴存者，使缴存者沉淀资金摆脱“贬值状态”。由此，既可以增加中低收入职工缴存公积金的积极性，又可以减少住房公积金拓展投资渠道的社会阻力。\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p>\r\n\t　　第四，通过“限高保低”等政策设计，保证制度红利向中低收入者倾斜。\r\n</p>\r\n<p>\r\n\t　　中央财经大学城市与房地产管理系主任易成栋认为，应采取“限高保低”的政策：严格规定公积金及补充公积金缴纳上限，严格规定只能用于购买家庭首套住房，并控制高收入群体的贷款额度，防止高收入群体过多占用住房公积金资源；对于中低收入群体，应给予一定的政策性担保，提高其贷款额度，并给予一定的利率优惠，使其真正成为制度受益者。与此同时，还可以通过亲属间代缴代取等制度设计，扩大公积金的受益范围，增加住房公积金的吸引力。\r\n</p>\r\n<p>\r\n\t　　专家指出，从长远来看，可考虑在事业单位改革的框架下，使住房公积金回归金融属性，成立全国统筹的政策性住房银行，以市场化手段提高公积金使用效率，增强社会效益。也可以参照新加坡的中央公积金制度，将住房公积金统筹到社保体系中。而从现阶段来看，亟须转变住房公积金的“准财政资金”性质，使住房公积金真正变成缴存者的“香饽饽”，而不是地方政府的“小金库”，使住房公积金的使用，真正体现制度设计的初衷。\r\n</p>\r\n<p>\r\n\t<br />\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(74,'<p style=\"text-indent:2em;\">\r\n\t“正常情况下提不出的公积金，交给我们，只要15天就能提出来。淘宝提取金额不同，手续费的标准也不同，提取金额30万以上，手续费是10%；提取金额10万左右，手续费是17%。”一位自称可办理提取公积金业务的男子对《经济参考报》记者说。\r\n</p>\r\n<div>\r\n\t&nbsp;\r\n</div>\r\n<div align=\"center\">\r\n\t<table style=\"border-collapse:collapse;\" class=\"MsoNormalTable\" align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\">\r\n\t\t<tbody>\r\n\t\t\t<tr>\r\n\t\t\t\t<td style=\"background:#C6D9F1;\" colspan=\"5\" width=\"568\">\r\n\t\t\t\t\t<p style=\"text-align:left;\" class=\"MsoNormal\" align=\"left\">\r\n\t\t\t\t\t\t<span style=\"font-family:宋体;\">表名：</span><span>LEAD_MAILBOX<span>&nbsp;&nbsp; </span></span><span style=\"font-family:宋体;\">中文表名：领导信箱</span><span></span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr>\r\n\t\t\t\t<td style=\"background:#EEECE1;\" width=\"128\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span style=\"font-family:宋体;\">字段</span><span></span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td style=\"background:#EEECE1;\" width=\"135\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span style=\"font-family:宋体;\">类型</span><span></span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td style=\"background:#EEECE1;\" width=\"113\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span style=\"font-family:宋体;\">是否允许为空</span><span></span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td style=\"background:#EEECE1;\" width=\"84\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span style=\"font-family:宋体;\">默认值</span><span></span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td style=\"background:#EEECE1;\" width=\"109\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span style=\"font-family:宋体;\">注释</span><span></span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr>\r\n\t\t\t\t<td width=\"128\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>MAILBOX_ID</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"135\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>INTEGER</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td valign=\"top\" width=\"113\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span style=\"font-family:宋体;\">否</span><span></span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"84\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"109\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr>\r\n\t\t\t\t<td width=\"128\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>TITLE</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"135\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>VARCHAR2(150)</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td valign=\"top\" width=\"113\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span style=\"font-family:宋体;\">否</span><span></span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"84\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"109\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr>\r\n\t\t\t\t<td width=\"128\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>CONTENT</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"135\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>CLOB</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td valign=\"top\" width=\"113\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span style=\"font-family:宋体;\">否</span><span></span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"84\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"109\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr>\r\n\t\t\t\t<td width=\"128\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>REPLY</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"135\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>CLOB</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"113\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<strong><span style=\"font-family:宋体;font-size:9pt;font-weight:normal;\">是</span></strong><span></span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"84\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"109\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr>\r\n\t\t\t\t<td width=\"128\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>NAME</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"135\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>VARCHAR2(20)</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td valign=\"top\" width=\"113\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span style=\"font-family:宋体;\">否</span><span></span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"84\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"109\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr>\r\n\t\t\t\t<td width=\"128\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>GENDER</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"135\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>INTEGER</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td valign=\"top\" width=\"113\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span style=\"font-family:宋体;\">否</span><span></span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"84\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"109\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr>\r\n\t\t\t\t<td width=\"128\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>EMAIL</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"135\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>VARCHAR2(50)</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"113\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<strong><span style=\"font-family:宋体;font-size:9pt;font-weight:normal;\">是</span></strong><span></span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"84\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"109\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr>\r\n\t\t\t\t<td width=\"128\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>MOBILE</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"135\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>VARCHAR2(50)</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"113\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<strong><span style=\"font-family:宋体;font-size:9pt;font-weight:normal;\">是</span></strong><span></span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"84\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"109\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr>\r\n\t\t\t\t<td width=\"128\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>UNIT</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"135\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>VARCHAR2(100)</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"113\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<strong><span style=\"font-family:宋体;font-size:9pt;font-weight:normal;\">是</span></strong><span></span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"84\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"109\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr>\r\n\t\t\t\t<td width=\"128\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>ADDRESS</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"135\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>VARCHAR2(150)</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"113\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<strong><span style=\"font-family:宋体;font-size:9pt;font-weight:normal;\">是</span></strong><span></span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"84\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"109\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr>\r\n\t\t\t\t<td width=\"128\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>CREATE_TIME</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"135\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>DATE</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td valign=\"top\" width=\"113\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span style=\"font-family:宋体;\">否</span><span></span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"84\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"109\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr>\r\n\t\t\t\t<td width=\"128\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>IS_SHOW</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"135\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>INTEGER</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td valign=\"top\" width=\"113\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span style=\"font-family:宋体;\">否</span><span></span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"84\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"109\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t\t<tr>\r\n\t\t\t\t<td width=\"128\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>REPLY_TIME</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"135\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span>DATE</span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"113\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<strong><span style=\"font-family:宋体;font-size:9pt;font-weight:normal;\">是</span></strong><span></span> \r\n\t\t\t\t\t</p>\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"84\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t\t<td width=\"109\">\r\n\t\t\t\t\t<p style=\"text-align:center;\" class=\"MsoNormal\" align=\"center\">\r\n\t\t\t\t\t\t<span></span> \r\n\t\t\t\t\t</p>\r\n<br />\r\n\t\t\t\t</td>\r\n\t\t\t</tr>\r\n\t\t</tbody>\r\n\t</table>\r\n</div>\r\n<div align=\"center\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t作为我国重要的住房保障制度，住房公积金制度为改善城镇居民住房条件发挥了重要作用。而在现实中，“低存低贷”、“强制储蓄”的住房公积金在降低部分缴存者购房成本的同时，也面对着“劫贫济富”、“劫贫济贫”的争议和花样百出的非法套取。对此，专家指出，解决住房公积金制度在现实中暴露的诸多问题，不仅应严厉打击非法套取，也应统筹设计，增强各地公积金统筹程度，并通过拓展投资、投资收益反哺、“限高保低”等多项措施，确保更多中低收入缴存者享受制度红利。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>非法套取手段花样百出</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t《经济参考报》记者采访了解到，在我国很多城市，“套取公积金”已成为半公开的地下产业。不仅街头“提取公积金”广告屡见不鲜，在百度搜索“提取公积金”关键词，也可以发现大量协助缴存人非法套取住房公积金的信息。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t通过百度搜索，记者联系到一位自称某投资理财有限公司负责人的男子。他表示，按照规定，买房、租房、大修等原因都可以提取公积金，但是提取的各种标准很严格。如果不按照规定的用途，住房公积金是很难提出来的，所以才会有他们这个行当。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t“以前人们主要通过虚假购房凭证套公积金，现在住房公积金中心与房管局、央行征信系统以及公民身份信息系统已经联网，用这种办法套取公积金根本不可能；过去还有通过假租房套公积金的，签一份虚假的租房协议，一个月房租几万块钱，一次提一年的，也能一次提出几十万公积金，但是这种办法‘太假了’，公积金中心也采取了相关的限制措施，租房提取不能超过一定的标准，所以这种办法也不行。”他说。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t业内人士指出，在目前的制度设计下，公积金管理中心与许多公民基本信息都未能联网，对于一些非法套取行为，虽然能通过实地考察、实地调档等方式予以遏制，但时间成本和人力成本太高。除此之外，确实有一些套取方式游走在法律边缘，没有明确的法律予以监管，这都是很现实又亟须解决的问题。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>住房公积金制度社会效益大</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t专家表示，尽管在现实中出现了花样百出的非法套取行为，但我国的住房公积金制度在20年的实践中，仍然体现了显著的社会效益。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t“在今天看来，住房公积金主要是用来个人住房贷款的，而在制度设计之初，主要是为了给房改筹资。”中央财经大学金融学院党委副书记李德峰表示，“上世纪90年代初，上海房改遇到巨额的资金缺口，于是借鉴新加坡的中央公积金制度，由个人的工资出一部分钱，企业出一部分钱，支援全市的住房建设。由于上海的公积金‘试点’卓有成效，不久之后，各地也陆陆续续建立了住房公积金。”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t据了解，各地住房公积金制度建立之初，主要用于支援“房改”、“危改”。以最早一批建立住房公积金制度的天津市为例，1992年5月，在天津市建设资金奇缺的情况下，天津市住房公积金管理中心首次向河西区平改办、天津起重设备厂发放住房公积金项目贷款1000万元和100万元。截至2000年底，中心已累计向总面积4900多万平方米的1400多个项目投放建设资金110亿元，占“八五”、“九五”期间天津全市住房建设资金的四分之一。在住房公积金的支援下，著名的大片危改项目谦德庄、春华里得以顺利完成，华苑、梅江等大片居住小区也得以启动。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t随着“房改”逐渐退出历史舞台，住房公积金的主要职能也演变成支持职工住房贷款以及支援保障房建设。中央财经大学民生经济研究中心主任李永壮认为，住房公积金制度最大的历史贡献在于，从源头上建立起单位、个人共同负担的机制，促进了住房的公平分配、公开分配和按劳分配，有效避免了福利分房时代的诸多弊端。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t同样以天津为例，截至2012年底，天津全市共有3.8万家单位235.9万名职工建立住房公积金账户，累计归集住房公积金1543.1亿元，向56.8万户职工家庭发放个人住房公积金贷款1194.8亿元。由于公积金贷款利率较低，有效减轻了职工的贷款负担。另外，在保障房建设方面，1997年，天津通过住房公积金增值建设廉租房———普康里；2010年，天津市成为全国首批28个试点开办住房公积金项目贷款支持保障房建设的城市之一，利用住房公积金贷款共向秋丽家园、秋怡家园、民盛园、天欣家园四个公租房项目发放贷款12.3亿元。通过支援保障房建设，解决了大量低收入群体的住房问题，社会效益非常显著。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>为何被指“劫贫济富”</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t北京大学房地产金融研究中心主任冯科认为，许多高收入的单位，缴存住房公积金的金额和比例都很高，作为一种“福利”，提高住房公积金既能达到避税目的，又能使员工享受廉价的公积金贷款，可谓一举两得。“穷人”的钱支援了“富人”，恰恰违背了住房公积金保护中低收入者利益的制度设计初衷，因此住房公积金的制度红利要流向谁的问题必须明确。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t中国房地产学会副会长陈国强认为，住房公积金是我国住房分配货币化、社会化和法制化的主要形式，住房公积金制度也是我国住房社会保障制度的重要组成。有专家表示，如今住房公积金的主要作用已经转为支持个人住房贷款。对于各地频现的“非法套取住房公积金”行为，应该理性看待，并对“猫鼠博弈”背后的深层问题有所反思。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t“近年来房价上涨过快，是催生非法套取住房公积金行为的重要原因。”李德峰认为，“在房价高企的背景下，很多中低收入缴存者无力支付首付，所缴存住房公积金能获得的贷款额度也不够买房。比如在北京，很多低收入家庭如果仅靠公积金贷款是无力买房的，他们缴存的住房公积金长期沉淀在个人账户上，无法提取。”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t业内人士认为，对这些“只缴不取”的低收入群体而言，缴存住房公积金既不能达到购房目的，又降低了当期收益。而他们缴存的公积金则被用于向收入相对更高、能支付得起首付的人贷款，并以廉价的公积金贷款买房。这也是住房公积金屡屡被指“劫贫济富”的根源所在。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t广东佛山市住房公积金管理中心工作人员冯颢指出，大部分中低收入职工没有足够的支付能力去购房建房，没有足够的偿还能力申请贷款，只能把钱存着，为别人提供贷款资金。他们实际上是以损失自己的购买力而为这个制度作出贡献，支撑这个制度正常运作和发挥作用。这个现象是通过减少多数缴存人的效用或福利，去增进少数人的效用或福利，恰恰与帕累托最优的实现相背离。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>“低存低贷”运作模式存弊端</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t值得注意的是，住房公积金制度的“低存低贷”政策，暗藏多重问题。业内人士认为“低存”带来的直接问题就是缴存者的收益过低，而这个问题首先源于公积金的投资渠道过于狭窄。按照现有规定，住房公积金的增值途径只有三种：第一是住房公积金贷款，赚取存贷利差；第二是存在银行作中长期存款；第三则是投资国债。而这三种增值渠道，根本无法保证较高的收益水平。在保障“低贷”的背景下，就更难实现“高存”。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t对于职工个人的住房公积金存款收益，1999年9月21日央行规定：“个人的住房公积金存款，当年归集的仍按结息挂牌公告的活期存款利率计息；上年结转的仍按结息日挂牌公告的三个月整存整取存款利率计息；个人住房公积金存款按年结息”，这个规定至今一直没有变化。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t按此规定，住房公积金的存款利息最高也就是三个月定期存款利率，不仅低于银行一年期存款利率，更低于市面上的各种理财产品的收益率。由此可见，对于缴存人来讲，大量的沉淀资金如果无法取出，事实上根本跑不赢CPI，处于贬值状态。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t与此同时，住房公积金的贷款利率也低于商业银行贷款，而“低贷”恰恰是以对缴存者的“低存”为基础。通过“低存低贷”，进而实现住房公积金制度的“互助性”。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t住房公积金增值的分配机制，决定了即使住房公积金获得了较高的增值，也与缴存者无关。按照现有规定，住房公积金的收益分配包括两个部分，一是职工个人住房公积金存款的收益，二是增值收益。管理机构运作住房公积金产生的业务收入，减去业务收入(其中包括职工个人住房公积金存款利息)后才作为“增值收益”核算。“增值收益”的分配次序为：第一，贷款风险准备金；第二，管理经费；第三，城市廉租房建设补充资金。也就是说，无论住房公积金增值多少，缴存者所获得收益都是锁定的。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t冯颢指出，这种制度设计，在住房公积金制度实施的初期应该是出于对职工个人收益的保护，因为业务收入必须先分配了职工的存款利息，才可计算“增值收益”，哪怕没有“增值收益”，也必须先分配职工个人存款利息。但随着住房公积金制度的发展，这种设计不仅与保护职工个人存款收益的目的越离越远，甚至起到了“损害”的作用。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t业内人士指出，这种“低存低贷”的运作模式产生了很多弊病。首先，住房公积金投资过于低效，导致大量沉淀资金长期处于贬值状态；其次，缴存者缴存收益过低，且与住房公积金投资收益几乎无关，这一方面致使缴存者不支持拓展公积金投资渠道，另一方面导致社会上套取公积金的行为愈演愈烈。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t专家指出，以住房公积金的增值收益为城市廉租房建设补充资金，同样带来“劫贫济贫”的问题———大量中低收入职工本身难以获得公积金贷款，其收入又没有低到住廉租房的标准，他们缴存资金的收益事实上资助了比他们收入更低的群体。这导致中低收入职工既帮助了比起收入高的人贷款，又帮助了比其收入低的人住廉租房，成为住房公积金制度的“净付出者”。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(75,'<p align=\"center\">\r\n\t<img alt=\"\" src=\"/member/portal/demo/201304/26091904dqtq.jpg\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t另外，香港周大福所销售的珠宝也未能出示鉴定证书，而当初郑裕彤所提出的\"一口价\"如今只是一句空口号，周大福\"货不真价不实\"，你还敢去买吗？\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>周大福：金饰店\"不满度\"第一名 卖的黄金会生锈</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t在全景网调查哪家金饰店最\"坑爹\"的调查中，周大福以37.78%的得票率高居\"坑爹\"排行榜第一位，远远抛开第二名周六福10个点。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t4月11日，\"April_沫\"在微博反映，她在商场的周大福专柜买了一个福星娃娃吊坠，戴了一年不到，发现该黄金吊坠居然\"生锈\"了。周大福工作人员回应，只需将生\"锈\"部位烧一下即可，但消费者并不满意。周大福官方微博回答：\"在佩戴过程中，如果黄金与含汞化妆品内的汞接触，会形成白色合金。\"\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t其实，\"April_沫\"反映的情况并不鲜见。如果说\"April_沫\"的吊坠是因为与含汞的化妆品接触而产生的反应，那么\"一只小巴哥\"的金镯子一次未戴却出现生锈现象的情况又是怎么回事呢？\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t3月14日，网友\"一只小巴哥\"在微博反映，买来做嫁妆一次未戴的千足金镯子生锈了，从图中可见，生锈的地方呈现红色，并不是周大福所解释的白色合金，而是通常意义上理解的\"红锈\"，即铁锈。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t周大福对此表示，黄金本来就会氧化，即使在柜台灯光下的也会这样。然而，众所周知，黄金是不活泼金属，不易被氧化，\"是金子在哪都会发光\"，只要是真金，即使是埋在地下，都不应该生锈，拿出来都会放光彩。然而周大福的金镯子却出现了锈迹，熟悉黄金饰品的人士认为，这种情况很有可能是因为成色不足，质地有假。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>香港周大福珠宝无鉴定证书</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t在全景网的调查中，青睐香港购金的人数是内地的3倍，周大福饰金价4月23日香港网络商城报价HK$15,000/両，约HK$400/克，按当日汇率换算成人民币是318元/克。与同样周大福内地当日报价372元/克相比较，每克便宜了50元以上，香港内地金饰价差悬殊，吸引了大量的内地居民前往香港购金。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t香港尖沙咀海港城附近的一家周大福的分店里，一款标价HKD22000元的18K红宝石戒指，一位姓冼的销售员介绍，这款红宝石是属于纯天然的红宝石，没有任何杂质。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t但当询及是否有相关的鉴定证书的时候，该销售员表示香港周大福红蓝宝石类的珠宝都没宝石鉴定证书，只有周大福出具的统一单据可以证明，因为周大福拥有80年的历史，拥有高额度的信誉和保证。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t深圳周大福的红蓝宝石却都配有由北京国家珠宝玉石质量监督检验中心出具的鉴定证书。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t基于周大福的知名度以及周大福是香港本土品牌，消费者更多的选择了香港周大福进行消费，但周大福却赤裸裸地利用了消费者的信任，消费着自己近百年建立的信誉。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t2008年7月，广东省工商局公布了2008年第2季度对省内流通领域销售的金银珠宝、进口保健品商品质量监测情况，其中，标称周大福珠宝金行(深圳)有限公司生产的\"周大福18K金红宝石戒指\"珠宝鉴定项目不合格。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t离上次抽检不合格已经快5年，香港周大福是单纯的\"店大欺客\"，还是试图以不主动送检取得鉴定证书来躲避监督检测？\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>不实在的\"一口价\"</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t上世纪80年代黄金饰品的折扣满天飞，周大福当时的掌门人郑裕彤提出了\"一口价\"的策略，以成本加上合理的利润创新推行珠宝首饰一口价，绝不降价促销，\"一口价\"就是货真价实的另一代名词。直到现在，很多消费者对\"一口价\"仍深信不疑。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t但是，在尖沙咀周大福金店里，对于\"一口价\"标明HKD22000元的18K红宝石戒指，店员很干脆地表示可以给个VIP价，折完是HKD17000元，大概8折。同样，在深圳周大福，对于红宝石戒指也可以申请到9.5折的优惠。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t郑裕彤的\"一口价\"到了郑家纯这一代，显然已成笑话，如今的周大福货不真，价不实，单纯消费着消费者对\"周大福\"这个品牌的信任。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>相关：</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t周大福(01929)4月23日港股收盘价HKD10.140，从2013年年初的HKD12.859元左右，一路下跌至今已超2成。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(76,'<p align=\"center\">\r\n\t<img alt=\"\" src=\"http://photocdn.sohu.com/20130425/Img374049925.jpg\" width=\"600\" height=\"450\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t由阿拉伯一富豪2亿8千万欧元打造出来的世界最强最贵的车。8缸1000马力。最高时速120公里/基本不上路，因为平均每跑100公里将磨损掉近50克黄金，其样子就是天降奇兵里的翻版 。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t据它的主人说：“如果把他看成艺术品，它值一亿英镑或许更高，因为世界著名艺术大师的绘画作品有不少都在一亿美元左右，绘画作品只能看，我的劳斯莱斯不但能看而且能开动；但它绝非是世界上最好的汽车，如果把它看成一辆一般的汽车，它可能只值395镑，可能会还少一点。\r\n</p>\r\n<hr style=\"page-break-after:always;\" class=\"ke-pagebreak\" />\r\n<p>\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p align=\"center\">\r\n\t<img alt=\"\" src=\"http://photocdn.sohu.com/20130425/Img374049926.jpg\" width=\"600\" height=\"450\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"left\">\r\n\t这款镀金自行车是由一家名为Aurumania的公司纯手工打造，镀有24克拉黄金和600多颗施华洛世奇水晶，价值102418.60美元。\r\n</p>\r\n<hr style=\"page-break-after:always;\" class=\"ke-pagebreak\" />\r\n<p>\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"left\">\r\n\t<br />\r\n</p>\r\n<p align=\"center\">\r\n\t<img alt=\"\" src=\"http://photocdn.sohu.com/20130425/Img374049927.jpg\" width=\"600\" height=\"450\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"left\">\r\n\t这款婴儿车装有音响系统和绸缎内饰，用24克拉的黄金打造，就连车轮也是合金辐条。此款豪华婴儿车价格不菲，约为6000英镑。\r\n</p>\r\n<hr style=\"page-break-after:always;\" class=\"ke-pagebreak\" />\r\n<p>\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"left\">\r\n\t<br />\r\n</p>\r\n<p align=\"center\">\r\n\t<img alt=\"\" src=\"http://photocdn.sohu.com/20130425/Img374049928.jpg\" width=\"600\" height=\"450\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"left\">\r\n\t英国 Gold &amp; Co. 公司针对有钱人打造了24k镀金版 iPhone 5，并将在迪拜一家购物中心发售。世界上第一台Gold iPad已经被拍卖，钱款用于慈善事业。Miansai 的14K纯金奢华iphone 4外壳。这件净重10g真金打造的外壳，售价高达1万美元。\r\n</p>\r\n<hr style=\"page-break-after:always;\" class=\"ke-pagebreak\" />\r\n<p>\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"left\">\r\n\t<br />\r\n</p>\r\n<p align=\"center\">\r\n\t<img alt=\"\" src=\"http://photocdn.sohu.com/20130425/Img374049929.jpg\" width=\"600\" height=\"450\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"left\">\r\n\t世界上最昂贵的厕所位于香港，建于2001年。整个洗手间花费380公斤纯金和6200公斤宝石。总费用为3800万美元，现在该厕所价值8000万美元。安徽省芜湖市商业步行街展示的一只重680克、由纯金打造的高跟鞋吸引了许多顾客。黄金的内衣胸罩价值189万美元，用钻石和黄金圈打造而成。\r\n</p>\r\n<hr style=\"page-break-after:always;\" class=\"ke-pagebreak\" />\r\n<p>\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"left\">\r\n\t<br />\r\n</p>\r\n<p align=\"center\">\r\n\t<img alt=\"\" src=\"http://photocdn.sohu.com/20130425/Img374049930.jpg\" width=\"600\" height=\"450\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"left\">\r\n\t你没看错，这个由20盎司白金和黄金、87颗正方形祖母绿切割丽泽钻石、5.63克拉浓彩金钻、548颗圆形丽泽钻石做成的冰淇淋。同样美容品也有出黄金版，黄金胶囊每个长20mm，这些黄金胶囊中，最值钱的部分便是内部的24K金箔。阿玛尼限量版Rose d\'Arabie香水外观上，设计师也混合了纯金薄片，使整个香水瓶散发着耀眼的金光。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(77,'<p align=\"center\">\r\n\t<img alt=\"\" src=\"/member/portal/demo/201304/26093215xnnw.jpg\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t中国人听到别人咳嗽，总是习惯地问一声：看医生了没？这句话听在西方人耳朵里，觉得莫名其妙。因为通常他们总说：喝杯柠檬蜂蜜水吧！一般来说，吃药不能治愈病毒感染所引起的感冒，也不能缩短感冒时间，只能让你症状减轻一些。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t其实，不必花钱、浪费时间去看医生，你也可以好好照顾自己。成人一年平均大约感冒2～4次，有些小秘方，看起来不起眼，却可以让自己在这些日子舒服一些，比如喝杯柠檬蜂蜜水。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t中医认为，柠檬具有生津止渴、和胃降逆、化痰止咳的功效。感冒初起时，喝柠檬蜂蜜水可以缓解咽喉疼痛，减少喉咙干燥等不适。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t此外，喝足够的水可以避免鼻腔、喉咙干燥，同时稀释痰和鼻涕。同时要避免饮用含咖啡因的咖啡、茶、可乐，以及含酒精的饮料，因为咖啡因和酒精都会促进水分排出，造成脱水。卧床休息也是加速复原的好方法。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(78,'<p style=\"text-indent:2em;\">\r\n\t柠檬富含多种抗氧化剂。早上喝1杯柠檬水，就足以让人神清气爽。你知道吗？柠檬水还有多种保健功效。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>1.提高免疫力。</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t柠檬水中丰富的维生素C有助于免疫系统抗击感冒。黑基表示，柠檬水还有助于促进人体对铁的吸收，因为维生素C可以使非血红素铁的生物利用率提高4倍。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>2.促进伤口愈合。</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t维生素C是促进伤口愈合，保持骨骼、组织和软骨健康的重要营养素之一。维生素C还有助于中和自由基，抑制体内炎症。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>3.抗击皮肤衰老。</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t黑基表示，柠檬水的抗氧化作用有助于应对体内自由基损害，缓解衰老进程。其富含的维生素C能帮助氨基酸合成胶原，保护皮肤，防止皱纹早生。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p align=\"center\">\r\n\t<img border=\"0\" src=\"http://i7.meishichina.com/Health/UploadFiles/201304/2013042517263172.jpg\" width=\"375\" height=\"500\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>4.降低食欲。</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t柠檬水中含有可溶性纤维素果胶，有助于增强饱腹感，进而控制食欲，防止过量饮食。保持更长时间的饱腹感，还有助于少吃零食，从而控制体重。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<hr style=\"page-break-after:always;\" class=\"ke-pagebreak\" />\r\n\t<p>\r\n\t\t<br />\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t&nbsp;\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t<strong>5.助消化。</strong> \r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t柠檬水有益消化道健康，可刺激胃液分泌，帮助消化，促进排便，清理肠道。柠檬水还能缓解消化不良、烧心和胀气等症状。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t&nbsp;\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t<strong>6.净化尿道。</strong> \r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t柠檬水具有利尿作用。喝柠檬水有助于人体毒素更快排出，进而净化机体，保持健康。柠檬水还有助于改变尿道pH值，防止有害菌滋生。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t&nbsp;\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t<strong>7.降低尿液酸度。</strong> \r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t柠檬水中虽然有柠檬酸，但其中的钾、钙等离子都可以与酸根离子结合，能降低尿液酸性，有益健康，是典型的碱性食品。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t&nbsp;\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t<strong>8.清新口气。</strong> \r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t柠檬水具有杀菌作用，特别能杀死口腔中的有害细菌，从而保持口气清新。早餐喝一杯柠檬水比咖啡更提神。黑基建议，柠檬水别喝冰镇的，室温的最佳。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t&nbsp;\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t<strong>9.改善血液循环。</strong> \r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t柠檬水中富含钾，能帮助控制血压，缓解压力。柠檬水中还含有维生素P，有助于增强毛细血管弹性，改善血液循环。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t&nbsp;\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t<strong>10.益肝排毒。</strong> \r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t柠檬水有助于肝脏排毒，通过促进胆汁生成而净化肝脏。柠檬水还有助于控制胆汁过量，减少体内黏液质生成，帮助溶解胆结石。\r\n\t</p>\r\n\t<p style=\"text-indent:2em;\">\r\n\t\t&nbsp;\r\n\t</p>');--end
 INSERT INTO tq_article_txt VALUES(82,'<p align=\"center\" style=\"text-indent:2em;\">\r\n\t<embed src=\"../plugin/kindeditor/plugins/flash/swf/Flvplayer.swf?vcastr_file=http://flv101.v1.cn/cloud/20130518/550866.flv\" type=\"application/x-shockwave-flash\" width=\"550\" height=\"400\" quality=\"high\" />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t中国台湾网5月16日消息　据台湾“中央社”报道，台湾当局启动制裁措施，并认为菲律宾授权不足，拒绝接见马尼拉经济文化办事处主席培瑞斯，培瑞斯和菲律宾驻台代表白熙礼今天下午离台。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t被菲律宾总统府宣布为总统阿基诺三世个人代表的培瑞斯（Amadeo Perez）上午从商务旅馆离开，进入马尼拉经济文化办事处，遭媒体包围采访，但不发言响应。接近11时30分左右，培瑞斯和白熙礼搭车离开，前往桃园国际机场，搭乘下午班机返回菲律宾。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t培瑞斯昨天抵台，随即计划前往台当局“外交部”，但“外交部”认为授权不足拒见。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t培瑞斯透过民间向屏东琉球乡长蔡天裕表达要到小琉球，向广大兴28号死者家属致意，也遭家属及蔡天裕拒绝。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t另外，针对菲律宾公务船枪击台湾渔船事件，台当局不满菲律宾响应，启动11项制裁措施，其中包括要求菲律宾驻台代表返菲协助妥善处理。白熙礼在第一波制裁后就已获通知，因机位、补位关系，在今天和培瑞斯一起返回菲律宾。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(83,'<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t最近联合国世界卫生组织提出新的年龄分段在微博热传。根据新规，44岁及以下为青年人，45岁至59岁为中年人。对此市民怎么看？新民网记者走上街头听听市民想法。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t采访中记者发现，对于新的划分方法，大多数市民都表示\"可以接受\"。有市民表示，随着生活水平生活质量的提高，原有的\"青年\"、\"中年\"划分方式确实不太适应现在的实际情况，应该有所改变。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t有市民坦言，一下子把青年人的\"界限\"提高到45岁，让已经年过三十、以为早已脱离\"年轻人\"队伍的自己十分惊喜，\"感觉自己赚到了十几年青春！\"\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t然而也有市民指出，单纯地靠年龄来划分并不十分准确，\"年龄可以是一个参考标杆，但重要的还是心态。心态年轻人就年轻！\"而几乎所有受访市民都表示希望永远拥有年轻的心态，\"希望自己永远年轻！\"\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(84,'<p align=\"center\">\r\n\t<embed src=\"http://v.ku6vms.com/phpvms/player/html/vid/scCnHwziT27tT6uk/style/zEAn9SLYaDE./\" type=\"video/x-ms-asf-plugin\" width=\"550\" height=\"400\" autostart=\"false\" loop=\"true\" />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t2013年5月13日晚，王石离异后的现任女友田朴珺低调出席电影《中国合伙人》首映会，观影后便在工作人员的护送下匆匆离去。女星田朴珺与王石忘年恋被踢爆后，有人爆料田朴珺已为王石生下儿子，事实上据南都娱乐周刊获悉，田朴珺和王石还没有孩子，并且王石在认识田朴珺之前已经和前妻王江穗办完了离婚手续。对于田朴珺在《中国合伙人》联合制片人的角色，此前不少媒体是因为王石的裙带关系。事实上，田朴珺与导演陈可辛相识甚久，而据剧组一位知情人透露，田朴珺动用了一些朋友的关系协调拍摄，帮了陈可辛不小的忙，此前久未有作品的田朴珺近日也因和王石的恋情身价暴涨，据悉有投资人拿着40万一集的合同找到她身边的工作人员，田朴珺暂时婉拒。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(85,'实拍醉酒女子当街大闹 警车上跳热舞');--end
 INSERT INTO tq_article_txt VALUES(86,'<p align=\"center\">\r\n\t<embed src=\"http://v.ku6vms.com/phpvms/player/html/vid/hPp1TgNynqPTpJne/style/zEAn9SLYaDE./\" type=\"application/x-shockwave-flash\" width=\"550\" height=\"400\" quality=\"high\" />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;2013年5月13日晚21时左右，我国中西部多个省份网友目击UFO。<br />\r\n<br />\r\n　　包括广西北海、贵州贵阳、云南蒙自、湖北武汉、重庆、云南思茅、陕西安康、云南红河、贵州遵义、湖南长沙、重庆永川。<br />\r\n<br />\r\n　　目击者称该飞行物呈V字形，边缘略显红色，放射出超长光亮，光亮照射处可清晰的看出该物体正在向天空中喷出气体，形成云雾状分散开来，目前官方还未证实。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(87,'<p style=\"text-indent:2em;\">\r\n\t“上午在哈尔滨看冰雪，中午在大连叹海鲜，下午在沈阳赏古迹，晚上到长春听二人转”，随着去年底哈大高铁开始飞奔，一日串游东北三省知名旅游城市已经不再是梦想。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t半个月前，纵贯黑龙江、吉林、辽宁三省的哈大高铁启动首个夏季运行图，列车按最高时速300公里运行，从黑龙江哈尔滨至辽宁大连最快仅需3.5小时，你完全可以自行设计由大连进哈尔滨出，或是哈尔滨进大连出的自助游线路，买好机票，跳上高铁，自主规划三省串游的行程。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<a></a> \r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img title=\"　　长白山天池 图/全景\" alt=\"　　长白山天池 图/全景\" src=\"http://i2.sinaimg.cn/travel/2013/0514/U7116P704DT20130514134237.jpg\" />\r\n</p>\r\n<div align=\"center\">\r\n\t　　长白山天池 图/全景\r\n</div>\r\n<div align=\"center\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t眼下，素以寒冷著称的东北也开始进入明媚的夏天。对大多数只知东北冰雪而不知其他的广东人来说，有了高铁的助力，已经可以像走读西部那样，以一个背包客的名义，去一站站地感受东北夏天清凉豪爽的另一种个性。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t1 受访人：吴江\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t广之旅国内游东北部经理\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t专家观点 　山水为主，城市为辅\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t因东北与内蒙古交界，夏季联游内蒙古的产品也较为多见，如从黑龙江伊春、五大连池一带开始游览，而后前往内蒙古海拉尔，游览满洲里、阿尔山等地，再从内蒙搭乘一晚的火车返回哈尔滨，这样的线路行程在10天左右。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t由于是长线游览，机票价格较贵，在广东游客心目中，东北是冬天旅游的首选，独特的冰雪使得性价比得以体现，相对来说，夏天的性价比显得不是特别高。然而夏季的东北也极富看点——以山水为主，城市为辅。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p align=\"center\">\r\n\t<img title=\"　　黑龙江三江平原的绿色农田 　图/肖殿昌(东方IC) \" alt=\"　　黑龙江三江平原的绿色农田 　图/肖殿昌(东方IC) \" src=\"http://i1.sinaimg.cn/travel/2013/0514/U7116P704DT20130514134308.jpg\" />\r\n</p>\r\n<div align=\"center\">\r\n\t　　黑龙江三江平原的绿色农田 　图/肖殿昌(东方IC)\r\n</div>\r\n<div align=\"center\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t广东人印象中的东北游以冰雪为主，集中在冬季，而夏季的东北线路以长白山等山水为主打，度假特色更鲜明。而通常往东北的各线路都会联游三省的主要旅游城市，如当下热卖的线路涵盖了大连、沈阳、长春、长白山、镜泊湖、牡丹江、哈尔滨等城市及景区，最常见是由大连或沈阳进，哈尔滨出，行程在六七天左右。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t对南方市场来说，哈大高铁的开通对于团队游产品并无太多改变，其原因除了高铁费用较高之外，还因高铁沿线经过的主要为城市，对需前往各个景点的团队游客来说反而增添周折。因此，目前的东北三省联游产品在当地主要依靠旅游大巴游览，部分产品会安排客人在长春到哈尔滨这一段搭乘高铁体验一下。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(88,'<p style=\"text-indent:28px;\" align=\"center\">\r\n\t<embed src=\"../plugin/kindeditor/plugins/flash/swf/Flvplayer.swf?vcastr_file=http://video6.house365.com/stream/2013/04/09/13654798385163919ed4978.flv\" type=\"application/x-shockwave-flash\" width=\"550\" height=\"400\" quality=\"high\" /><strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:28px;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:28px;\">\r\n\t<strong>亮点一：乳白色基调打造宁静悠远氛围</strong> \r\n</p>\r\n<p style=\"text-indent:28px;\">\r\n\t这处工地目前刚刚完成木工阶段，设计风格为简欧风格。设计师主要运用了白色基调，营造出一种典雅高贵的气质和浪漫恬静的情怀。据了解，房主是位很有事业心的年轻创业者，希望的家是能够让他在工作疲惫之余能迅速放松下来的场所，所以设计师王旭滔在设计中将房屋的主色调定位为高端的乳白色。细节上，雅致的浅咖啡色和湿润的驼色搭配显得恰到好处，相得益彰，总体视觉效果丰盈，意境宁静，让人充满愉悦感。\r\n</p>\r\n<p style=\"text-indent:28px;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:28px;\">\r\n\t<strong>亮点二：严谨的计算将设计留存为经典</strong> \r\n</p>\r\n<p style=\"text-indent:28px;\">\r\n\t简欧风格在形式上很浪漫，但在构成上却是非常严谨。线板的空箱比例，整体的对应及胶圈，立面的层次和韵律，都是在图纸上经过详细计算的结果。这些复杂的幕后设计工作不仅仅是打造风格，更是要将设计留存为经典，经受住时间的考验。\r\n</p>\r\n<p style=\"text-indent:28px;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:28px;\">\r\n\t<strong>亮点三：定制化设计天然岩材让家更温馨</strong> \r\n</p>\r\n<p style=\"text-indent:28px;\">\r\n\t天然岩材灵动、自然、鲜活的独特魅力让不少人向往，却又在装修中很难把控，让人望而却步。昶卓设计提供岩材定制化装修，尽可能保证天然大理石拼花尺度的完整，不拘泥的空间也让设计师有更多发挥的余地，天然的纹理和色彩的多样性为设计师提供了得天独厚的设计条件。天然岩材的应用不仅让家更环保，也让家更温馨。\r\n</p>\r\n<p style=\"text-indent:28px;\">\r\n\t完美的设计遇上严谨的施工，并坚持全心全意的为业主解决一切装修问题，于是成就了备受关注的昶桌设计。昶卓设计总经理黄莉常说，高标准的施工工艺不仅能达到优质的预期效果，更能为业主省钱省心，真正做到后顾无忧。\r\n</p>\r\n<p style=\"text-indent:28px;\">\r\n\t<br />\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(89,'<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img alt=\"朱莉在比佛利山粉红莲花乳房中心动切乳手术，该中心官网首页换上她的照片。\" src=\"http://i3.sinaimg.cn/ent/s/u/w/2013-05-15/U3349P28T3D3921149F326DT20130515143520.jpg\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t朱莉在比佛利山粉红莲花乳房中心动切乳手术，该中心官网首页换上她的照片。\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<!-- publish_helper name=\'原始正文\' p_id=\'28\' t_id=\'3\' d_id=\'3921149\' f_id=\'274\' -->\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t新浪娱乐讯 北京时间5月15日消息，据微博“ETtoday新闻云”报道，好莱坞性感红星安杰丽娜-朱莉(Angelina Jolie)因为带有缺陷基因BRCA1，毅然接受预防性的双乳房乳腺切除术，以降低罹癌风险，勇气让人叹服！为她施行手术的外科医师克莉丝蒂-朋克(Kristi Funk)在博客上详述整个手术过程。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<div>\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t朱莉的切乳一系列手术是从今年2月2日的“乳头保留”(nipple delay)，到4月27日的乳房重建为止。朋克指出，历经乳房组织切除与义乳填充，朱莉展现积极正面的态度，甚至才动切乳手术的第4天，她就活力充沛地起身，投入下一部电影的拍摄计划。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t朋克指出，朱莉是在2月16日接受最主要的切乳手术，2天后检验报告出炉，所有的乳房组织都是良性的，到了第4天，她看到朱莉不但活力充沛，“还在家里两面牆上，贴着将执导的电影拍摄流程记事板。”而朱莉当时“胸部左右两边各有3个手术引流袋，分别系在腰间的送紧带上。”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t朋克也亲睹布莱德彼特(Brad Pitt)对朱莉的爱，每一场手术，他都在场，“她麻醉退去醒来后，彼特都在她身边打气。”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t朱莉是自己投书到《纽约时报》，披露接受预防性双乳切除术，使她罹患乳癌的机率从87%下降到5%以下，从此6个孩子不必再怕失去妈咪，也让她与小布关係更亲密。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(90,'<p style=\"text-indent:2em;\">\r\n\t第20届北京大学生电影节[微博]5月13日晚在奥体中心落下帷幕，冯小刚[微博]导演的《1942》获得最佳影片奖，管虎凭《杀生》获最佳导演奖，影帝、影后分别被黄渤[微博]和颜丙燕[微博]获得。最受大学生欢迎的导演及男女演员分别花落徐峥[微博]、王宝强[微博]和张雨绮[微博]。电影节评委会大奖则由《万箭穿心》和《神探亨特张[微博]》一同获得。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>兼顾艺术与商业 《杀生》《泰囧》出彩</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t作为大学生自己的电影节，在国内大大小小的影展和电影节的冲击下，要保持原有的影响力以至做大做强越来越难。但本届大学生电影节的颁奖礼却超乎寻常地长。获奖影片更兼顾了艺术与商业。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t冯小刚导演的《1942》获得最佳影片奖，管虎凭《杀生》获最佳导演奖，影帝、影后分别被黄渤和颜丙燕获得。最受大学生欢迎的导演及男女演员分别花落徐峥、王宝强和张雨绮。电影节评委会大奖则由《万箭穿心》和《神探亨特张》一同获得。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t除此之外，电影节将最佳处女作奖颁给了电影《鹅卵石》，最佳新人奖颁给了叶兰(《梅姐》主演)和周文奕(《甜蜜18岁》主演)，最佳编剧奖给了自编自导《边境风云》的程耳，最佳观赏效果奖不出意料由《画皮2》获得。表演艺术特别奖被《飞跃老人院》全体主演捧走。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>改设低成本电影奖 保护小片积极性</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t跟上届相比，本届大学生电影节的奖项设置有了明显的变化，原来为电视电影设置的多个奖项换成了“低成本电影”奖，令影片入围的范围更加扩大，也让更多优秀的小电影走进大学生、年轻人的视线。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t岳红凭借电影《深呼吸》获得了本届电影节最佳低成本电影女演员奖，最佳低成本电影男演员奖由《孙子从美国来》中爷爷的扮演者罗京民获得，最佳低成本电影导演奖更生出“三黄蛋”，由《天津闲人》的导演郑大圣，《孙子从美国来》导演曲江涛[微博]以及《有事找王江》的导演李彦廷共同获得。同时，《天津闲人》还获得了最佳低成本电影奖。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t而大学生电影节组委会大奖颁给了黄宏执导的反映抗震救灾中人性故事的电影《倾城》，艺术探索奖则由非常个性化的电影《告诉他们，我乘白鹤去了》获得，连该片导演本人都直呼完全没想到。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(91,'<p style=\"text-indent:2em;\" align=\"center\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img alt=\"华妃娘娘在《箭在弦上》……各种违和啊！！！\" src=\"http://i1.sinaimg.cn/ent/v/m/2013-05-16/U5912P28T3D3921853F329DT20130516104626.jpg\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t华妃娘娘在《箭在弦上》……各种违和啊！！！<!-- publish_helper name=\'原始正文\' p_id=\'28\' t_id=\'3\' d_id=\'3921853\' f_id=\'274\' -->\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t●“妹子鞭子把行驶中火车抽停了……一鞭子把火车抽停了……一鞭子，火车停了！！”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t●“……男主角没有死，一只老鹰把他拽起来了，《神雕侠侣》和《指环王》在此刻泪流满面啊。”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t●“我本来以为能看到少年安培师安倍军曹大战龙虎山老道的段，结果日本人派来的是……日本道士。”——— 日前网络热帖，编剧吐槽抗日神级雷剧的神展开路线。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t南都讯 记者蔡丽怡 吴莎 今年以来，抗日剧因“奇幻”情节频现，饱受争议。日前，网传新版广电总局“22条军令”将开始规范卫视黄金档电视剧播出，其中特别规定：所有抗日剧都需重新审查，抗日剧黄金档播出将受到限制；抗日传奇剧、戏说剧等被直接喊停，已经过审的剧目将被转至非黄金档；现实题材剧目将占据卫视播出总量的50%；南都记者在广电总局官网并未查到这条通知，而各大卫视也表示仍未收到正式通知。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>南方的各大卫视表示即便限播也影响不大</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t广电总局限制抗战剧的消息传出后，有消息称，已有部分卫视调整了黄金档剧目播出计划。其中，原定于近日登陆四川卫视的抗日题材剧《尖刀战士》已被古装剧《大明嫔妃》取代；河北卫视原定于5月22日接档的《裂变》也改以年代情感励志为基调进行宣传；央视马上要播出的《猎杀》的推广语也由“女性丛林抗战”更改为“青春抗战励志剧”。而湖南卫视[微博]等黄金档以年轻受众群为主的卫视，零抗战剧编排，故影响最小。南都记者就此回访多位卫视宣传负责人，对方均表示尚未接到总局正式通知，南方的各卫视均表示，即使有通知下来，也影响不大，但会密切关注相关动向。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t江苏卫视[微博]：即使有通知对我们也没有影响，因为我们现在播的《宝贝》，以及之后的《而立之年》、《新恋爱时代》都不是抗日剧，再之后就是古装剧了，一直到暑假都没有抗日剧的安排。”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>东方卫视：“没有接到通知，并且我们也没有抗日剧在播或要播。”</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t浙江卫视[微博]：“接档现在正在播出的《宝贝》的有可能是《上阵父子兵》，是一部抗日剧。但我们也只是在微博上看到有这个通知，但没有领导开会通知此事，目前不知道会不会有影响。”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t广东卫视：“只看到网上有消息说，我们并没有接到总局的正式通知。广东卫视播出的抗战剧基本属于主旋律正剧，我们从来没有播过抗战神剧。目前正是我们排播抗战剧比较密集的档期，正在播出的《一门三师令》不受影响，而且收视率还挺靠前的，5月29日排播的《捍卫者》也会如期播出，接档的是《刘伯承》，这部是广电总局推荐播出的抗战剧，这部是广电总局推荐播出的抗战剧，更加不会受到影响。”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>挑战人类想象力的神剧，你忍够了没有网友表示不是该出手了，是早该出手了</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t今年以来，抗日剧屡屡受到争议。从“手撕鬼子”到“裸背敬礼”再到，。雷人情节频繁出现，不仅引得网友声讨，连央视也站出来，批评抗日剧“比着看谁俗、争着看谁二”。而本周的网络热帖，编剧吐槽抗日神级雷剧的神展开剧本，也将网友对“挑战人类想象力的抗战神剧”的忍耐值耗完，所以，而作为审批部门，广电总局是否会出台新的规定也日益受到关注。网传抗日传奇剧、戏说剧等将禁止播出，虽还未被证实，就引发热转，网友列队表示：“不是该出手了，是早该出手了”。\r\n</p>\r\n<hr style=\"page-break-after:always;\" class=\"ke-pagebreak\" />\r\n<p>\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>抗日神剧为什么还会有市场？</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t抗日神剧为什么一直都有市场？以抗战剧为主打的本土频道T V S-1南方经视黄天文总监告诉记者：“我们频道主打动作战争大剧，但是在目前的市场上，像《士兵突击》这种现代战争戏很少，古装战争戏也不多，所以大部分的产品还是抗战剧。频道曾专门组织专家、观众、编剧开过讨论会，专家认为这种戏说、传奇式的抗战剧不好、歪曲历史；而一些观众却认为，看这类剧时根本没把它当历史、真实看待，只是当成武侠小说般看，满足一下个人的英雄浪漫情结而已。有为数不少的老干部、军人、知识分子还专门要求重播、多播这类剧呢。在我们频道，只要含有子弹拐弯、轻功或射箭打鬼子等奇幻情节的抗战剧，收视一定会比普通抗日正剧收视要高。”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>神点评</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t抗日雷剧，本就是限制的产物\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<div>\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t大量不着调的抗日雷剧引起的争议，终于收到了效果，据说，广电总局将对抗日剧的制作播出进行限制，包括重审抗日剧，对播出时段和总量进行控制，抗日传奇和戏说剧，将被禁止播出。但我怀疑，抗日剧，本身就是限制的产物。看过一些抗日剧之后，觉得它们的确古怪，仔细分析，得出结论，其实这些抗日剧，是经过改装的宫斗、武侠、奇幻、涉案、职场厚黑剧。宫斗武侠不能拍，涉案厚黑有风险，不如把这些剧的情节元素放进抗日剧里，奸妃换做特务头子，武林邪恶势力改成皇军，武林秘籍变成志士名单。抗日题材政治正确，容易通过，情色、暴力元素，放在抗日剧里，也容易得到宽容。抗日剧于是成了一个筐，什么都往里装，难免透着古怪、透着俗。比如，盗墓小说改编电视剧，肯定是无法通过的，但如果把盗墓改成寻宝，盗墓人改成寻宝小分队，为了筹措抗日经费而上山入海，“粽子”改成日本鬼子，故事完全讲得通，观众也一定看得津津有味。盗墓小说里，打起“粽子”来，一打就是一大堆，换成打鬼子，恐怕更过瘾。事实上，已经有抗日剧用过这些元素，虽然没有盗墓小说那么神乎其神，但情节框架，分明就是从盗墓小说里借来的。显然，许多抗日剧，是别的类型电视剧的借壳还魂，除了家庭苦情戏婆媳大战没有被抗日剧借壳，许多被限制的题材，基本上都在抗日剧里换装出现了。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t所以，这些抗日剧尽管雷人，照样受到欢迎。从本质上讲，它们其实是武侠、奇情、冒险、涉案剧，只不过，剧中人穿的是军装，开的是现代的汽车，要在一个特定的历史阶段活动，原来那些类型剧里所拥有的元素，抗日剧里一点都不缺。一旦发现了这点，观众很快就和抗日剧制作者达成了默契。限制抗日剧的制作播出，肯定会收到效果，但那些情节元素，也一定会被挤压到别的安全题材里。只有限制是不够的，限制的结果，不过是制造出新的古怪剧种，倒不如让各种题材各得其所，都有表现的机会，让属于武侠的归武侠，奇幻的归奇幻。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(92,'<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img border=\"1\" src=\"http://i3.sinaimg.cn/ent/cr/2013/0516/1628650687.jpg\" />\r\n</p>\r\n<div align=\"center\">\r\n\t赵丽颖早前出演《佳期如梦》（左图）\r\n</div>\r\n<div align=\"center\">\r\n\t&nbsp;\r\n</div>\r\n<p align=\"center\">\r\n\t<img border=\"1\" src=\"http://i1.sinaimg.cn/ent/cr/2013/0516/3461095025.jpg\" />\r\n</p>\r\n<div align=\"center\">\r\n\t现在主演《陆贞传奇》（右图）的剧照\r\n</div>\r\n<div align=\"center\">\r\n\t&nbsp;\r\n</div>\r\n<p align=\"center\">\r\n\t<img border=\"1\" src=\"http://i0.sinaimg.cn/ent/cr/2013/0516/1288226342.jpg\" />\r\n</p>\r\n<div align=\"center\">\r\n\t赵丽颖与陈晓在《陆贞传奇》中饰演情侣，戏外也传出戏假情真的传闻\r\n</div>\r\n<div align=\"center\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t信息时报[微博]讯 （记者 何珊） 出道7年多的赵丽颖[微博]，并不属于一炮而红的明星，有着漫长的“打酱油”生涯，近期却因为湖南卫视[微博]播出的《陆贞传奇》而走红，她跟剧中情侣陈晓[微博]传出疑似戏假情真，日前又有传赵丽颖有过坐台和整容的经历。赵丽颖的经纪人兼海润经纪副总裁赫茹，接受信息时报记者采访，怒斥这个传闻“太无聊”，并且在微博澄清是赵丽颖旧照当年是吃胖了，而有份参与制作《陆贞传奇》的于正[微博]，则透过微博力挺暗指有人“泼脏水”。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>“坐台”是两年前旧料</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t2006年因为选秀出道的赵丽颖，曾出演《佳期如梦》、《追鱼传奇》、《极品男女日记》、《吉祥天宝》、《云中歌》等多部影视剧集。有网友将她在《陆贞传奇》的剧照与《佳期如梦》时照片对比，发现她容貌相差很大。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<div>\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t经纪人赫茹透过微博回应，称赵丽颖在拍《佳期如梦》期间，跟同剧演员海吃，所以吃胖了。有份参与《陆贞传奇》制作的于正，也在微博力挺赵丽颖，写道：“我很好奇，为什么每个红起来的演员都会被编织不堪的过去？都会被曝整容，为什么就没有人相信他们付出了比常人多十倍的努力？为什么他们必须被脏水浇完，坦然接受之后才能涅槃成爷？这是啥心态？反正我是不理解！大陆贞加油！”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t对于坐台一说，据记者调查，其实早在2011年赵丽颖被指曾做“外围女”，她的公开身份是模特、演员、歌手，实际是经常陪人吃喝玩乐，甚至外出旅行，从而得到不菲的“小费”，当时正值《新还珠格格》上映，赵丽颖当时知名度不高，爆料者还在微博写道赵丽颖曾经私信他，想了解他到底知道多少，此次是这条旧料再度被翻出来，而赵丽颖的经纪人表示传闻“太无聊”。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>陈晓：赵丽颖很吸引我</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t在《陆贞传奇》饰演情侣的陈晓和赵丽颖，早前被拍到两人在机场的亲密照片。陈晓被问及是否真的戏假情真？他表示：“对赵丽颖有好感，但是不是爱还不清楚。”由于该剧是两人的第三度合作，陈晓也承认跟赵丽颖找到默契，感情戏自然水到渠成。不过自认为性格内向的陈晓谈到感情时表示：“我（在爱情上）是很被动的人，有好感的人也不知道该怎样去主动追。”\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(94,'<p class=\"img_wrapper\" align=\"center\">\r\n\t<img src=\"http://i3.sinaimg.cn/ent/2013/0515/U5204P28DT20130515143102.jpg\" />\r\n</p>\r\n<div class=\"img_wrapper\" align=\"center\">\r\n\t<span class=\"img_descr\">“你们这样说，有如一刀插我心。”(设计对白，图为祖儿《活该》MV资料图)</span>\r\n</div>\r\n<div class=\"img_wrapper\" align=\"center\">\r\n\t<span class=\"img_descr\"></span>&nbsp;\r\n</div>\r\n<p>\r\n\t　　南都讯 记者王击凡 实习生张洵 早被网友戏谑为“完美复印机”的容祖儿[微博]，歌曲疑似抄袭前科累累。前年被指歌曲《花千树》抄袭马浚伟[微博]旧歌《不再悲观》，如今新歌《另眼相看》日前正式派台，歌曲名字由作词人黄伟文日前于微博征集歌名的游戏而来。令人意想不到的是，不少网友听完新歌后表示与英国女歌手A dele的《R um our H as It》在前奏部分相似，有抄袭嫌疑，遭到香港网友炮轰。除此之外，也有网友表示新歌与韩国女歌手李夏怡的《1.2.3.4》在唱法和编曲上相似。苦等一阵才等到容祖儿广东新歌派台，如今新歌却又陷入抄袭风波，亦令部分网友感到失望。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p>\r\n\t　　《另眼相看》歌词讲述到从前不看好自己的人都纷纷改变了态度，有死忠歌迷表示此曲适合献给“反容”的人，但歌词也提到“但是你偏偏尚待我很差”，配合这次网友们的抄袭指控，是否略显唏嘘？容祖儿又是否如歌词所说，能做到“捱下去你也会爱上我的”呢？而至于新歌是否抄袭，就要留给听众们自行判断了。容祖儿昨晚发布微博回应称，新歌是与作曲人冯翰铭花了很多时间完成的，两人的合作充满信任，并对新歌回馈理想感到高兴，但未正面回应疑似抄袭的说法。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p>\r\n\t　　<strong>容祖儿疑似抄袭不完全记录</strong>\r\n</p>\r\n<p>\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p>\r\n\t　　<strong>MV抄袭</strong>\r\n</p>\r\n<p>\r\n\t　　2002年，网友指容祖儿《一面之缘》M V抄袭滨崎步<a class=\"wt_article_link\" href=\"http://weibo.com/u/2291250415?zw=ent\" target=\"_blank\">[微博]</a>的《Evolution》。\r\n</p>\r\n<p>\r\n\t　　2007年，网友指容祖儿《在你的左右》M V无论概念、镜头、道具等均与美国歌手M andyM oore的《extraordinary》一样，如上图的叠罗汉场景。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p>\r\n\t　　<strong>衣着抄袭</strong>\r\n</p>\r\n<p>\r\n\t　　2007年，被指与演唱会及音乐颁奖礼内身穿之服饰均抄袭国际著名时尚设计“V IK T OR&amp;ROLF07春夏新装”，更被嘲讽为“烧鹅湖”。当中容祖儿在上述颁奖礼(叱咤颁奖礼)内的服饰，更触怒与之相关的时装品牌，并导致该公司扬言不再让容祖儿租借旗下品牌的任何作品。\r\n</p>\r\n<p>\r\n\t　　2006年，网友指出容祖儿多个造型，与郑秀文[微博]早年造型雷同。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p>\r\n\t　　<strong>歌曲抄袭</strong>\r\n</p>\r\n<p>\r\n\t　　2007年，《逃》被指抄袭SheenaEaston的《T elefone》。\r\n</p>\r\n<p>\r\n\t　　2009年，《很忙》被指抄袭O neRepublic的《A pologize》。\r\n</p>\r\n<p>\r\n\t　　2009年，《双冠军》被指抄袭FaithH ill的《T hereyou‘ll be》(电影《珍珠港》主题曲)。\r\n</p>\r\n<p>\r\n\t　　2010年，《桃色冒险》被指抄袭东京事变的《林檎钛呗》。\r\n</p>\r\n<p>\r\n\t　　2011年，《花千树》被指抄袭张宇的情有独钟(国语版)/马浚伟的不再悲观(粤语版)(注：两首歌为同一首歌的两个版本)。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p>\r\n\t　　<strong>广告抄袭</strong>\r\n</p>\r\n<p>\r\n\t　　2009年，容祖儿百老汇广告，被指很多场面和较早的微软Zune广告雷同。\r\n</p>\r\n<p>\r\n\t　　2012年，容祖儿百老汇广告中的红唇造型概念与美国女歌手Pink在颁奖典礼的造型雷同。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p>\r\n\t　　<strong>唱片封面抄袭</strong>\r\n</p>\r\n<p>\r\n\t　　2007年，容祖儿推出国语大碟《小小》封面，和邹静93年推出大碟《邹静》相似。\r\n</p>\r\n<p>\r\n\t　　2009年，网民发现容祖儿国语专辑《很忙》封面，与两年前日本滨崎步的专辑《A B est 2：B lack》内页概念雷同。\r\n</p>\r\n<p>\r\n\t　　2009年，《双冠军》所用的封面宣传照，与M acy Gray的《Big》雷同。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(95,'<p style=\"text-indent:2em;\">\r\n\t北京时间5月16日凌晨消息，谷歌2013年I/O开发者大会今天在美国加州旧金山芳草地艺术中心开幕，更新一系列软件产品，其中最大亮点是重新设计了谷歌地图，依据搜索历史及其他数据提供更加个性化的服务。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>地图</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t新版谷歌地图还能展示用户的Google+好友发布的本地商户点评。Google+是谷歌2011年推出的社交网络，在本次发布会上成为焦点之一。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t谷歌宣布，将在谷歌地图中整合自家团购服务Google Offers，方便商家向用户提供优惠券。例如，当用户搜索“咖啡馆”，并在谷歌地图中找到星巴克时，点击“更多信息”，就能看到星巴克提供的优惠券。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t随着越来越多的人借助电子导航上班、回家，地图正成为移动设备最常用的功能之一。这让地图服务成为谷歌的战略资产之一。超过100万家企业在自家网站和应用中嵌入了谷歌地图，谷歌可从中获取大量收入。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t谷歌主要竞争对手苹果公司去年从iPhone和iPad中移除谷歌地图，转而内置自家地图服务，用户评价参差不齐。与此同时，谷歌地图已成为苹果设备有史以来下载量最大的应用之一。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t另一方面，知情人士透露，Facebook正在与以色列众包地图创业公司Waze谈判，开出了10亿美元的收购报价。目前，Facebook需要依赖微软的必应地图。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>搜索</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t在本届大会上，谷歌将原本用于Android智能手机的语音网页搜索功能拓展至PC端。用户只需打开Chrome浏览器，面对麦克风大声说“OK，Google”，并说出搜索请求，即可获得反馈。用户也可通过点击Chrome浏览器的麦克风按钮实现这一功能。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t谷歌搜索业务高管艾米特•辛格尔(Amit Singhal)称，谷歌希望复制美国著名科幻电视剧《星际迷航》中太空船的超级电脑所支持的功能：只需说出命令，就能获得答案。语音搜索则是这一愿景的一部分。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t为了实现“对话式搜索”，谷歌构建了一个包含5.7亿个词条的数据库，覆盖了人、地点、事物及彼此关联等信息。借助这一数据库，谷歌搜索能够给出简短而精确的回答，例如一张地图、一幅照片，而非一大堆超链接。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t例如，用户搜索了一个附近的城市，然后问“它距离这儿有多远”，谷歌就会提供一张地图，告诉用户开车或乘坐公共交通工具前往目的地的方法。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>Android</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t谷歌今天宣布，Android设备总激活量已达9亿部，一年前这一数字为4亿部，两年前则仅有1亿部。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t谷歌Android业务高管雨果•巴拉(Hugo Barra)在会上透露，Android系统的每用户营收比一年前增长了150%。这一收入主要通过“应用内购买”等途径获得。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t目前，众多硬件厂商的智能手机和平板电脑采用Android系统，预装了谷歌的多项服务，包括网页搜索、YouTube和地图等，为谷歌带来大量收入。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\tAndroid已超越苹果的iOS，成为全球第一大智能手机操作系统。美国市场研究公司IDC的数据显示，Android平板电脑出货量首次超越iPad，位居全球第一。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>Google Play</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t谷歌今天还推出了教育版Google Play应用商店，为学校等机构提供专属应用，意欲扩大Android设备在课堂中的流行程度。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t教育版Google Play将于今年秋天正式上线，将提供大量专门为儿童开发的应用，如算术技巧等。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t谷歌Android业务高管克里斯•耶尔加(Chris Yerga)称，教师在下载应用后，教室内的其他Android设备将能够立即使用这款应用。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t谷歌已经在全球教育体系中推广搭载Chrome操作系统的设备，但尚未针对Android设备宣布此类计划。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>Google Apps</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t谷歌两大开源生态系统——Android和Chrome都支持谷歌在线软件套装Google Apps，其中包含文字处理和协作工具等，是微软Office办公软件套装的竞争对手。用户可通过PC和移动设备访问Google Apps。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t谷歌Chrome和Android业务负责人桑达•皮采(Sundar Pichai)表示，全美排名前100名的大学中，有74所在使用Google Apps，包括7所“常春藤盟校”。他说：“增长势头非常迅猛。”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t个人用户可以免费使用Google Apps，企业则需支付一些费用。知情人士透露，谷歌每年可从该业务获得约10亿美元的收入。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>Chrome</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t谷歌今天宣布，Chrome浏览器的月活跃用户量已超7.5亿，去年同期为4.5亿。这款浏览器预装了谷歌搜索引擎，能够带来收入。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t皮采称，Chrome已成为“全球最流行的浏览器”。不过，一些市场研究公司认为，Chrome的全球市场份额仍位居微软IE浏览器之后。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\tChrome还被谷歌改头换面，成为一款支持笔记本电脑和移动设备的操作系统。但第三方数据显示，它在上述两个领域的份额几乎可以忽略不计。谷歌拒绝透露目前售出了多少台Chrome设备。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(96,'<p>\r\n\t　　新京报讯 (记者林其玲)有媒体报道称，欧盟委员会计划向中国政府发出正式警告，准备就非法补贴问题对华为和中兴通讯进行制裁。昨天，华为和中兴均发布官方声明，否认从事不公平贸易活动。中国政府本周二也作出回应，敦促欧盟避免采取贸易保护主义措施。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p>\r\n\t　　<strong>欧盟可能启动调查</strong>\r\n</p>\r\n<p>\r\n\t　　周一，一名欧盟官员透露，欧盟贸易专员卡洛·德古赫特将向欧盟委员会其他成员寻求支持，希望对华为、中兴的调查能在本周三的会议上启动。一些欧盟官员认为，华为和中兴接受中国政府的巨额补贴，从而能够以较低的价格在欧洲市场进行不公平竞争。\r\n</p>\r\n<p>\r\n\t　　有媒体报道称，欧盟电信产业占到整个欧盟国内生产总值的4.8%。过去五年，基站价格大跌，中国对欧盟基站出口额飙升。中欧已就此问题举行了几轮谈判。欧盟委员会官员一度设法寻求中国的承诺，让中方在欧洲出售产品时高于一个设定的最低价格，但会谈无果而终。\r\n</p>\r\n<p>\r\n\t　　德古赫特今年2月接受路透社采访时表示，中国在电信市场上所占份额正在增长，令人十分担心。\r\n</p>\r\n<p>\r\n\t　　不过，由于担心此调查引发中国运营商的反击，丧失LTE合同，爱立信、阿朗和诺西并不愿意参与这项调查。爱立信负责政府和行业关系事务的主管奥夫·佩尔森昨天表示，爱立信反对欧盟进行这种调查。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p>\r\n\t　　<strong>中方希望欧盟不要贸易保护</strong>\r\n</p>\r\n<p>\r\n\t　　中国外交部发言人洪磊在例行记者会上对此作出了回应，他说：“希望欧方从中欧合作大局出发，为中欧经贸关系的健康稳定发展创造良好条件，不要动辄采用贸易保护主义措施。”洪磊称，中欧双方无线通信企业在对方市场均具有巨大商业利益，任何贸易限制措施都势必会影响正常的贸易投资活动，对双方在该领域的合作不利。\r\n</p>\r\n<p>\r\n\t　　华为和中兴都否认从事不公平贸易活动。昨天华为发表声明称，其获得成功是因为通过有效的技术创新帮助客户降低整体运营成本，而非低价策略。“华为也反对欧盟这种根据传闻就开展调查的行为。”\r\n</p>\r\n<div style=\"margin:10px 20px 10px 0px;\" id=\"hzh_div\" class=\"otherContent_01\">\r\n</div>\r\n<p>\r\n\t　　中兴则表示，中兴通讯致力于公开透明的运营，遵守 WTO和相关国家贸易规则。一名中兴高管表示，目前中兴在欧洲多个国家有终端产品和电信设备产品销售，如果此次遭到调查，其电信设备产品销售可能会受到影响。“欧盟的调查不太可能发现任何问题。这件事情将不了了之。”该人士称。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p>\r\n\t　　<strong>■ 背景</strong>\r\n</p>\r\n<p>\r\n\t　　<strong>华为中兴海外“行路难”</strong>\r\n</p>\r\n<p>\r\n\t　　这已不是华为中兴第一次在海外遭遇“行路难”。华为是仅次于爱立信的全球第二大电信设备制造商，中兴则排名第五。随着公司的扩张，他们均面临着来自西方政府的阻力。\r\n</p>\r\n<p>\r\n\t　　去年美国政府曾两度以威胁信息安全为由调查这两家公司，最终这两家公司的电信设备被明令禁止不准采购。今年1月，欧盟曾提出要求称，华为中兴接受中国政府对其出口的非法补贴，须将其出口商品的价格上调29%，以此降低其在欧盟的市场竞争力。\r\n</p>\r\n<p>\r\n\t　　据知情人士披露，根据欧盟的法律，若无人提起诉讼，欧盟无权对企业展开调查。而爱立信等担心中国可能采取报复行动，并不愿意参与此事。今年1月的“调查事件”也因此不了了之。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(97,'<p style=\"text-indent:2em;\">\r\n\t<span style=\"font-family:KaiTi_GB2312,KaiTi;\">“在拓展新客户进展较慢的情况下，富士康原有订单也有被抢走的风险，这种势头在这一季开始显现。”</span> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t手机行业的巨大变革直接影响到了鸿海精密工业(下称“富士康”)的生意，如果不做出改变，它也许会错失更多的机会。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t“在拓展新客户进展较慢的情况下，富士康原有订单也有被抢走的风险，这种势头在这一季开始显现。”台湾一不愿意透露姓名的分析师对记者表示，一方面大客户订单遭遇分流，另一方面拓展新客户乏力，富士康正在遭受同行的夹击。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>苹果“劈腿”重击富士康</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t自苹果成为了市场的主宰后，富士康就从中获益匪浅，但这也就造成了一个几年来令郭台铭头疼不已的局面：富士康一方面可以从苹果的成功中获利，另一方面也要承担苹果自身业绩下滑或苹果的订单转移带来的损失。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t富士康近日发布的2013年第一季度财报数字显示，该季度合并营收为新台币8090亿元，较2012年同期衰退近20%，毛利率为5.68%，较2012年同比下滑了近1个百分点。更糟糕的是，其平板组装上的竞争对手台湾厂商和硕营收却增长31%，至1952.7亿元新台币。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t从去年10月开始，iPad mini的上市便拉动了和硕营收的强劲增长，去年第四季起和硕平板电脑开始大量出货。而据台湾分析师此前对记者表示，在苹果的新一轮产品中和硕获得的订单量也许超出所有人预期。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t一名长期跟踪苹果的凯基证券分析师称，你可以想象，苹果不喜欢只有一家供应商的局面，对和硕来说，最重要的是证明自己能像鸿海那样，胜任iPhone或iPad的制造工作。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>拓展新客户乏力</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t正如其他苹果供应链上的公司一样，富士康显然知道拓展新客户是降低风险的一种方式，但目前看来并不顺利。在富士康旗下的上市公司富士康国际(02038.HK)的2012年财报中明确表示，未来希望借助投资研发及专注新客户，扩大客户基础。而此前，富士康国际也创下了上市7年来的最大亏损纪录。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t但是，一接近富士康人士对记者表示，按照富士康规模化的生产方式，小的手机订单不仅会造成生产成本过高，客户响应速度也会低于市场反应速度，这也是富士康拓展新客户时最需要考虑的核心问题。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t郭台铭在今年3月份拜访了国内手机厂商小米，小米总裁林斌3月21日在微博表示，郭台铭拜访小米并与董事长雷军展开会谈。外界猜测，富士康抑或取得小米手机的订单，但富士康方面对此表示不予评价。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t“小米的市场占有率不高，无法为富士康带来可观的收益，而其他国产手机在富士康代工的基本为低端机型，尚未听说过有高端旗舰级订单。”上述人士说。\r\n</p>\r\n<div style=\"margin:10px 20px 10px 0px;\" id=\"hzh_div\" class=\"otherContent_01\">\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t“订单并不好拿，富士康的对手也在争取潜在客户。”上述接近富士康人士还举出了比亚迪(002594.SZ)的例子。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t在刚刚发布的比亚迪今年一季度财报中，归属于上市公司股东的净利润为1.12亿元，同比增加315.63%。该公司称，集团成功开拓了全球手机领导厂商的智能手机新项目和平板电脑等新产品及业务，由此推动了集团手机部件及组装业务收入及利润的大幅增长。有接近比亚迪人士对记者表示，三星和华为的业务是此轮比亚迪电子增长的重要引擎。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t去年，华为手机销量大幅上涨，本应是富士康最应争取的代工客户。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t而拓展三星这个客户，似乎是个不太现实的考虑。过去在不同的场合，郭台铭毫不吝啬表达对三星的看法，“只要它做的，我一定联合大家来(对抗)，我一定要打它一棒。”在一场晚宴上，郭台铭振臂疾呼，号召台湾知名的IT及家电企业家们加入他的“打狼”行动，而这只狼就是三星。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(98,'<p class=\"img_wrapper\" align=\"center\">\r\n\t<img title=\"金山办公软件副总裁章庆元\" alt=\"金山办公软件副总裁章庆元\" src=\"http://i3.sinaimg.cn/IT/2013/0515/U5081P2DT20130515121019.jpg\" />\r\n</p>\r\n<div class=\"img_wrapper\" align=\"center\">\r\n\t<span class=\"img_descr\">金山办公软件副总裁章庆元</span>\r\n</div>\r\n<div class=\"img_wrapper\" align=\"center\">\r\n\t<span class=\"img_descr\"></span>&nbsp;\r\n</div>\r\n<p>\r\n\t　　新浪科技讯 5月15日上午消息，金山办公软件副总裁章庆元昨天表示，2013年年底WPS月活跃用户有望达到1亿，其中桌面端用户将占到6成，移动端用户占到4成。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p>\r\n\t　　<strong>将推新版WPS</strong>\r\n</p>\r\n<p>\r\n\t　　金山WPS将于5月16日发布WPS Office 2013，章庆元昨天与媒体沟通时透露，新版软件将会有新的用户界面， 压缩了常用工具面板，给予用户更大的操作空间。同时，软件交互界面也换成了灰色的主色调。\r\n</p>\r\n<p>\r\n\t　　新版软件的一个特色是增加了一款全新的办公服务组件——“轻办公”。利用“轻办公”，用户可以直接对编辑的文档进行存储，而后用户还可以建立一个圈子，把与该文档相关的人加进圈子，大家就可以在圈子里针对文档进行讨论、修改。\r\n</p>\r\n<p>\r\n\t　　目前，用户可以使用新浪微博、腾讯QQ、金山快盘账号直接登陆“轻办公”，而据章庆元透露，后续还可能会整合米聊、微信等用户常用账号。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p>\r\n\t　　<strong>月活跃用户有望达到1亿</strong>\r\n</p>\r\n<div style=\"margin:10px 20px 10px 0px;\" id=\"hzh_div\" class=\"otherContent_01\">\r\n</div>\r\n<p>\r\n\t　　目前WPS拥有2亿用户，产品覆盖Windows、Android、iOS、Linux等多个平台。随着Android平台产品的快速发展，章庆元表示，2013年年底前WPS月活跃用户有望达到1亿，其中桌面端用户将占到6成，移动端用户占到4成。\r\n</p>\r\n<p>\r\n\t　　他透露，目前金山Android平台的WPS产品每天安装量达到30万，月活跃用户已经达到2000万。他预计，今年年底这一数字将增长值4000万，并成为Android平台上最大的办公软件产品。截至2013年4月底，Android版WPS Office用户数量已经突破6000万。\r\n</p>\r\n<p>\r\n\t　　受益于国内正版化策略，金山WPS的桌面端产品也出现了大幅的销量增长。去年应用软件业务营收为5.44亿元，同比增长67%。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p>\r\n\t　　<strong>在线办公软件是补充</strong>\r\n</p>\r\n<p>\r\n\t　　针对办公软件向云端发展的趋势，章庆元表示，网络版办公软件更多的是一种补充，客户端将是办公软件的主要形态。他表示，云端办公软件会影响工作效率，并且用户可能并不买账。\r\n</p>\r\n<p>\r\n\t　　他表示，金山WPS将会主要面向80%的大众用户，并以互联网产品的开发节奏，进行不断改进。他透露，金山WPS每2个月会进行一次产品功能的升级。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(99,'<p class=\"img_wrapper\" align=\"center\">\r\n\t<img title=\"联合国的报告称，许多政府已经开始认真考虑利用诸如蚂蚁等昆虫制成食品工业的原料 \" alt=\"联合国的报告称，许多政府已经开始认真考虑利用诸如蚂蚁等昆虫制成食品工业的原料 \" src=\"http://i2.sinaimg.cn/IT/2013/0516/U2727P2DT20130516092633.jpg\" />\r\n</p>\r\n<div class=\"img_wrapper\" align=\"center\">\r\n\t<span class=\"img_descr\">联合国的报告称，许多政府已经开始认真考虑利用诸如蚂蚁等昆虫制成食品工业的原料 </span>\r\n</div>\r\n<div style=\"margin:10px 20px 10px 0px;\" id=\"hzh_div\" class=\"otherContent_01\">\r\n</div>\r\n<p>\r\n\t　　新浪科技讯 北京时间5月16日消息，近日，一份联合国报告指出，提高食谱中昆虫的比重将为人类健康、环境保护以及经济发展带来巨大的好处。这份来自联合国粮食及农业组织的报告解释道，许多昆虫“富含蛋白质和优质脂肪，并含有丰富的钙、铁和锌”。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p>\r\n\t　　在环境影响方面，昆虫需要的空间比牛、羊等牲畜要小得多，这意味着更少的森林被砍伐，以及其他的开荒活动；昆虫产生的温室气体也少得多。此外，有许多昆虫还可以以人类或其他动物的垃圾和排泄物为食。\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p>\r\n\t　　报告称：“昆虫具有很高的营养价值，很低的温室气体排放量，对土地的需求很低，而且对食物的转化效率很高，这一切使它们得以保障粮食安全，并成为解决蛋白质短缺的方案之一。”\r\n</p>\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p>\r\n\t　　事实上，许多地方的人们早已经将昆虫列入食谱，但在西方国家还很少见。妨碍昆虫成为全球性食物的一大因素在于：“食虫”观念尚未被大部分人接受，而且以昆虫为中心的食品工业尚未建立起来。报告中指出：“在西方世界，消费者对昆虫食物的接受度与食品价格、可感知的环境收益，以及以美味的昆虫源蛋白质产品为基础的餐饮业发展有关。”\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(100,'<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img title=\"Universal Air公司当前的四旋翼直升机使用远程遥控装置控制，新版本将具备自行追踪用户手机信号的能力\" alt=\"Universal Air公司当前的四旋翼直升机使用远程遥控装置控制，新版本将具备自行追踪用户手机信号的能力\" src=\"http://i1.sinaimg.cn/IT/2013/0516/U2727P2DT20130516101934.jpg\" />\r\n</p>\r\n<div align=\"center\">\r\n\t　　Universal Air公司当前的四旋翼直升机使用远程遥控装置控制，新版本将具备自行追踪用户手机信号的能力\r\n</div>\r\n<div align=\"center\">\r\n\t&nbsp;\r\n</div>\r\n<p align=\"center\">\r\n\t<img title=\"这款无人机采用4个旋翼，用户可以在家中进行组装。当前的系统非常先进，可以采用抛入空中的方式起飞，未来的版本可以利用Wi-Fi信号和控制器应用程序追踪用户的一举一动\" alt=\"这款无人机采用4个旋翼，用户可以在家中进行组装。当前的系统非常先进，可以采用抛入空中的方式起飞，未来的版本可以利用Wi-Fi信号和控制器应用程序追踪用户的一举一动\" src=\"http://i1.sinaimg.cn/IT/2013/0516/U2727P2DT20130516102025.jpg\" />\r\n</p>\r\n<p align=\"center\">\r\n\t&nbsp;\r\n</p>\r\n<div>\r\n\t　　这款无人机采用4个旋翼，用户可以在家中进行组装。当前的系统非常先进，可以采用抛入空中的方式起飞，未来的版本可以利用Wi-Fi信号和控制器应用程序追踪用户的一举一动\r\n</div>\r\n<div>\r\n\t&nbsp;\r\n</div>\r\n<p align=\"center\">\r\n\t<img title=\"这款无人机可以捕捉下用户的一举一动，例如在河边散步或者进行极限运动\" alt=\"这款无人机可以捕捉下用户的一举一动，例如在河边散步或者进行极限运动\" src=\"http://i3.sinaimg.cn/IT/2013/0516/U2727P2DT20130516102045.jpg\" />\r\n</p>\r\n<div align=\"center\">\r\n\t这款无人机可以捕捉下用户的一举一动，例如在河边散步或者进行极限运动\r\n</div>\r\n<div align=\"center\">\r\n\t&nbsp;\r\n</div>\r\n<p align=\"center\">\r\n\t<img title=\"当前的版本通过地图软件控制，让其按照事先设定的路线飞行。未来的版本可以追踪手机信号\" alt=\"当前的版本通过地图软件控制，让其按照事先设定的路线飞行。未来的版本可以追踪手机信号\" src=\"http://i3.sinaimg.cn/IT/2013/0516/U2727P2DT20130516102058.jpg\" />\r\n</p>\r\n<div align=\"center\">\r\n\t当前的版本通过地图软件控制，让其按照事先设定的路线飞行。未来的版本可以追踪手机信号\r\n</div>\r\n<div align=\"center\">\r\n\t&nbsp;\r\n</div>\r\n<p align=\"center\">\r\n\t<img title=\"R10无人机与一个DIY工具包一同发售。下一个版本可自动追踪用户并拍摄视频，将于2014年初上市，主要面向极限运动市场\" alt=\"R10无人机与一个DIY工具包一同发售。下一个版本可自动追踪用户并拍摄视频，将于2014年初上市，主要面向极限运动市场\" src=\"http://i2.sinaimg.cn/IT/2013/0516/U2727P2DT20130516102116.jpg\" />\r\n</p>\r\n<p align=\"center\">\r\n\t&nbsp;\r\n</p>\r\n<div>\r\n\t　　R10无人机与一个DIY工具包一同发售。下一个版本可自动追踪用户并拍摄视频，将于2014年初上市，主要面向极限运动市场\r\n</div>\r\n<div>\r\n\t&nbsp;\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t新浪科技讯 北京时间5月16日消息，据国外媒体报道，英国无人机制造商Universal Air正在研制一款可充当“私人间谍”的四旋翼直升机，能够自行追踪和拍摄高清视频，追踪用户的一举一动。整个过程中，用户无需对其进行操控。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<div>\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t这款四旋翼直升机可以利用手机的Wi-Fi信号对用户进行追踪。Universal Air公司相信这款无人机能够成为极限运动爱好者的宠儿，用于捕捉他们的每一个精彩瞬间。据悉，这个“私人间谍”是在四旋翼直升机R10的基础上研制的。R10造价190英镑(约合295美元)，在Universal Air公司成功获得启动资金后研制。当前的版本利用远程遥控器或者游戏控制器控制。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\tUniversal Air公司联合创始人马克斯-布鲁纳表示：“R10将成为研究人员、业余爱好者和社区开发人员的一个有用工具。Universal Air公司正将R10作为一个平台，用于未来的空中设备。”布鲁纳和一群好友最初研制R10是在2011年，当时他们还是牛津大学和剑桥大学的学生。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\tUniversal Air公司的新一代四旋翼直升机将于2014年初上市，能够自动追踪用户。这款全自动四旋翼直升机针对的是极限运动市场，装有机载跟踪信标，能够追踪手机的Wi-Fi信号。Universal Air公司表示这款无人机可以追踪用户，无论他们去哪。即使滑雪或者骑着山地车冲下山坡，也能进行追踪。布鲁纳在接受CNET采访时表示，用户需要做的就是在手机和这款无人机之间建立Wi-Fi连接。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(101,'<p style=\"text-indent:2em;\">\r\n\t近期创业板指数一枝独秀，连创反弹新高，并于昨日重上千点大关。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t创业板持续走强，从宏观背景看，符合目前经济转型的需要；从资金面看，外围增量资金入市速度很慢，创业板股票总规模适合存量资金运作；从产业结构看，创业板集中了新兴行业公司，符合产业方向。然而，随着创业板反弹加速，各种风险因素正在聚集。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t其一，高估值风险。周三创业板指数以全天最高点1012点收市，半年内累计反弹幅度超70%，远超上证指数和深证成指。同时，当前创业板整体41.78倍的市盈率，远高于沪市主板11倍、深市主板20倍、中小板30倍的水平，凸显估值风险。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t其二，限售股减持风险。创业板指反弹至千点，也给公司大股东、高管减持带来机会。5月份共计有34家公司16.26亿股限售股解禁，解禁市值突破350亿元。进入5月份以来，创业板上市公司每天均有减持公告披露。截至5月14日，今年已经有75家创业板上市公司发布了股东减持公告，几乎是去年全年遭到减持的创业板上市公司的数量总和。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<div>\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t其三，新股发行冲击。从舆论呼吁看，新股发行改革必须改变“三高”现象。为了解决“三高”问题，证监会可能作出“打包发行，集中上市”的技术安排，从而促进价格回归和降低发行市盈率，为市场提供货真价实的投资标的，以吸引更多增量资金入场。由此可见，“三高”顽疾势在必除。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t其四，成长性风险。环比数据显示，创业板首批28家公司2009年至2012年三季度的营业收入和净利润均呈现增长势头下滑态势。表面上看，28家公司营业收入不错，但净利润的增长显然不能让市场满意，平均20%以上的净利润增长仅仅达到A股市场的平均水平，与当初发行时的高估值并不匹配。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t其五，个股风险加大。周二收盘，乐视网(41.280,-1.02,-2.41%)、掌趣科技(61.860,0.66,1.08%)等前期强势股跌停，反映了市场资金对过高股价的恐惧，开始兑现利润。即使周三资金杀回马枪，这几只个股也未能收复周二的大阴线。此外，近期股价反弹幅度较大的数码视讯(21.450,0.40,1.90%)被股东减持数量最多，为1510万股。创业板可能陷入愈减持愈拉升的怪圈。如果这种现象再次出现并扩散，那么对创业板整体走势将造成较强的负面影响。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(102,'<p style=\"text-indent:2em;\">\r\n\tA股四月份连续第三个月下挫，散户投资者的资产急剧缩水。统计数据显示，A股持股市值超过1万元以上的账户数均出现不同程度减少，其中千万富翁的数量创下年内新低，但万元以下的迷你散户却急剧增加。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t中登公司最新发布的投资者账户市值分布表显示，4月份持股市值超过1000万元的自然人账户数减少423个至1.77万户，创下2012年12月份以来的新低。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t此外，持股市值在 500 万元-1000万元的账户数减少1068个，100万元-500万元的账户数减少1.31万个，50万元-100万元的账户数减少2.43万个，10万元-50万元的账户数减少16万个，1万元-10万元的账户数骤减23万个。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<div>\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t不过，持股市值低于1万元的账户数却出现大幅增加，由2030.34万户升至2059.19万户，单月净增加28.85万户。市场分析人士指出，高净值账户数减少而低净值账户数大幅增加，反映出A股1月份以来持续下跌令投资者的资产不断缩水，部分高净值账户降为低净值账户。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t受低迷经济数据、禽流感疫情扩散以及IPO重启忧虑打击，中国股市4月份连续第三个月下跌，上证指数全月下跌2.62%，两市总市值合计蒸发超过5500亿元，而从1月份的高点算起，上证指数三个月的累计跌幅已经接近一成。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t事实上，A股的持续下跌令投资者的入市步伐正在放缓。4月沪深两市合计日均新开 A股账户数为1.66万户。其中沪市和深市平均每日新开 A股账户数环比降幅均在五成左右，日均基金开户数也环比减少了25%。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t值得关注的是，1万元以上账户数的减少量远超过1万元以下账户的增加量，表明部分投资者选择离开股市。数据显示，截至四月末，A股的持仓账户数为5474.85万户，环比减少14.44万户或0.26%，创下3月初以来的新低，而空仓账户占比则升至58.97%，较上月增加0.2个百分点。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(104,'<p>\r\n\t　　汇通网5月16日讯——周四(5月16日)亚市早盘，美元指数高位整理，现交投于83.80附近。隔夜欧元区多国一季度GDP数据普遍逊于预期，打压欧元进一步走低，兑美元刷新六周低点1.2842，美元指数则强势触及84关口。今日市场风险事件依然不少，欧美央行多位高官将陆续发表讲话，传递何种政策信号值得投资者密切留意，此外欧美通胀数据也料将在汇市掀起一番波澜。\r\n</p>\r\n<p>\r\n\t　　<strong>欧元区经济一季度继续衰退，美指强势触及84关口</strong> \r\n</p>\r\n<p>\r\n\t　　欧元区相关国家周三公布了2013年一季度GDP初值。数据结果显示，欧元区经济整体继续衰退，其中德国经济增长接近停滞，法国陷入衰退，意大利连续7季度收缩、西班牙同样陷入深度萎缩。\r\n</p>\r\n<p>\r\n\t　　数据显示，欧元区第一季度本地生产总值(GDP)初值季率萎缩0.2%，预期下降0.1%，去年第四季度为下降0.6%；GDP初值年率下滑1.0%，预期下降0.9%，去年第四季度为下滑0.9%。\r\n</p>\r\n<p>\r\n\t　　欧元区GDP连续6个季度下滑，创下自欧元创立以来最长记录，超过了2008-2009年雷曼破产后的衰退时长记录，尽管衰退的严重程度仍有所不如。\r\n</p>\r\n<p>\r\n\t　　ING的经济学家Peter Vanden Houte表示：“所有希望都寄托在外部需求的回暖上，然而由于美国的财政紧缩和中国的复苏存疑，净出口对经济的推动作用并不大。”\r\n</p>\r\n<p>\r\n\t　　4月份商业调查显示，欧元区经济可能还会在今年第二季度再度下滑。欧元区最近一次GDP实现增长是在2011年第三季度，当时德国经济增速在3%以上，而出现衰退的国家也基本只限于希腊、爱尔兰和葡萄牙等小国家。\r\n</p>\r\n<p>\r\n\t　　不过，占欧元区经济产值近三分之一的德国今年以来几乎未显现出快速回升的迹象。第一季度德国经济仅较去年第四季度仅增长0.1%，远低于经济学家预期。与此同时，三年前起始于希腊的经济滑坡已经蔓延至仅次于德国的欧元区另外三大经济体——法国、意大利和西班牙，这三个国家的经济总量占欧元区GDP的一半。\r\n</p>\r\n<p>\r\n\t　　数据如此黯淡，令欧洲经济的萧条近况一览无遗，欧州央行宽松预期升温导致欧元隔夜继续承压下跌，兑美元刷新六周低点1.2842，美元指数则强势触及84关口。\r\n</p>\r\n<p>\r\n\t　　尽管隔夜美国数据也表现疲软，但数据并未引发担忧，美股也继续创出新高。数据显示，纽约州制造业活动在5月意外萎缩，因新订单和制成品出货减少。通胀数据也显示，美国4月生产者物价指数(PPI)录得三年最大跌幅，直指通胀压力疲弱。\r\n</p>\r\n<p>\r\n\t　　Commonwealth Foreign Exchange首席市场分析师Omer Esiner表示：“尽管步伐缓慢，但美国经济看来正在改善，而欧元区GDP数据巩固了欧洲央行会追加宽松政策的预期，要么降息，要么采取负存款利率等非传统举措。”\r\n</p>\r\n<hr style=\"page-break-after:always;\" class=\"ke-pagebreak\" />\r\n<p>\r\n\t<br />\r\n</p>\r\n<p>\r\n\t　　<strong>欧美“传声筒”今竞相亮相，通胀数据同样不容忽视</strong> \r\n</p>\r\n<p>\r\n\t　　今日市场风险事件依然不少，欧美央行多位高官将陆续发表讲话，传递何种政策信号值得投资者密切留意，此外欧美通胀数据也料将在汇市掀起一番波澜。\r\n</p>\r\n<p>\r\n\t　　今日欧洲央行行长德拉基和执委普雷特将分别发表讲话，其中尤其是德拉基的发言值得投资者重点关注。考虑到隔夜的GDP数据显示欧元区经济形势依然恶化，德拉基会否进一步表露对负利率等宽松政策的渴望，将决定欧元今日走势前景。\r\n</p>\r\n<p>\r\n\t　　本月2日，欧洲央行时隔近一年重启降息，将欧元区基准利率降至历史最低的0.5%，并表示一旦有必要，将采取进一步的刺激措施。欧洲央行决策委员之一的维斯科13日也再次表示，在必要时，央行可能考虑将存款利率降至前所未有的负值。若日内德拉基在货币政策方面表露更多宽松意愿，将打压欧元进一步下行。\r\n</p>\r\n<p>\r\n\t　　此外，今日美联储方面发表讲话的官员也不少。费城联储主席普罗索将在本周第二度亮相，此外达拉斯联储主席费舍尔、波士顿联储主席罗森格伦、旧金山联储主席威廉姆斯、里奇蒙联储主席莱克也将陆续登场。\r\n</p>\r\n<p>\r\n\t　　费城联储主席普罗索周二在斯德哥尔摩表示美联储应该放缓并在随后终止QE购债。他警告称如果美联储未能在下个月削减其购债规模，美联储将面临信誉受损的风险。他明确的表示希望退出程序现在就开始启动。普罗索说道：“尽管(就业市场)更多的进展当然立项。但我相信目前的证据已经显示就业市场显著改善。因此，开始放缓QE购债速率是合适的。”\r\n</p>\r\n<p>\r\n\t　　此前在上周五收盘后，WSJ记者“美联储通讯社”Hilsenrath发表题为“美联储制定退出刺激的计划”的报道，称美联储已经制定了结束前所未有的850亿美元/月购债计划的策略，这一策略旨在保持灵活性并且能够管理高度不确定的市场预期。\r\n</p>\r\n<p>\r\n\t　　市场将继续密切关注美联储官员的表态以寻找美联储计划更清晰的信号。若能有更多鸽派官员改变立场，转而支持提前缩减QE，将进一步激发市场对美联储改变政策的预期，利好美元。\r\n</p>\r\n<p>\r\n\t　　而从数据角度看，今日投资者显然也没法清闲。到了欧洲时段，欧元区将有一系列通胀数据公布。由于此前欧洲央行多次暗示存在着继续降息的可能，而过去一周公布的欧洲各项经济数据也都差强人意，因此市场正在观望欧洲经济的未来走势，并有比较浓厚的做空情绪存在。目前市场预期CPI数据将持平，如果实际数据差于预期，则将进一步打击本已问题重重的欧元区。\r\n</p>\r\n<p>\r\n\t　　到了北美时段，美国也将发布包括CPI在内的多项经济数据。隔夜美国4月PPI创三年最大降幅，对美联储鹰派而言显然不是个好消息。尽管近期美国就业市场再度显露复苏势头，但通胀状况依然不温不火，甚至有下降的势头，这无疑仍允许美联储维持超宽松的货币政策。日内CPI数据究竟是升是降，将对美联储6月决议时的政策制定起到重大影响。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(105,'<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img src=\"http://image.sinajs.cn/newchart/daily/n/sz159915.gif?1368665994\" />\r\n</p>\r\n<div align=\"center\">\r\n\t查看最新行情\r\n</div>\r\n<div align=\"center\">\r\n\t&nbsp;\r\n</div>\r\n<div>\r\n</div>\r\n<div>\r\n</div>\r\n<p align=\"center\">\r\n\t<img src=\"http://image.sinajs.cn/newchart/daily/n/sz150081.gif?1368665994\" />\r\n</p>\r\n<div>\r\n</div>\r\n<div align=\"center\">\r\n\t查看最新行情\r\n</div>\r\n<div align=\"center\">\r\n\t&nbsp;\r\n</div>\r\n<div>\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t<!--单图 end--><!-- publish_helper name=\'原始正文\' p_id=\'31\' t_id=\'1\' d_id=\'15478392\' f_id=\'45\' -->\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t证券时报记者 杨磊\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t杠杆债券基金和创业板基金是今年的明星基金产品，截至昨日，双盈B和创业板相关基金产品今年以来投资收益率首次超过40%大关，领跑包括交易型基金和开放式基金在内的全部基金产品。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t据悉，双盈B是一只杠杆债券基金，由信诚基金公司管理，截至5月14日今年以来涨幅已达39.9%，随着昨日大涨3.02%，该基金今年以来涨幅已达44.13%。该基金今年以来经历过4轮上涨，1月、3月、4月和5月上涨幅度分别为11.14%、7.82%、5.7%和11.19%。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t今年年初该基金折价交易5%左右，到5月14日已溢价交易7.36%，折溢价变化给该基金带来至少12%的上涨。基金单位净值上涨是双盈B价格上涨最大来源，今年年初到5月14日，该基金单位净值大涨22.88%，一方面由于该基金投资的债券品种上涨，另一方面由于该基金有比较高的投资杠杆，初始投资杠杆超过3倍。\r\n</p>\r\n<div>\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t事实上，不仅今年以来杠杆债券基金表现优异，2012年同样有非常优异的表现，去年有两只杠杆债券基金价(1375.60,-20.60,-1.48%)格上涨超过40%，分别为双翼B和互利B，同样是当年全部基金中投资收益率最高的产品。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t今年基金市场一大黑马是创业板相关基金产品。易方达基金[微博]旗下跟踪创业板指数的创业板交易型开放式指数基金(ETF)涨幅领先，截至5月15日，创业板今年以来价格涨幅达41.65%，同期创业板指数上涨幅度为41.86%，创业板交易价格很好地跟踪了指数的市场表现。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t据悉，创业板今年以来表现领先的主要原因在于中小盘成长股的优异表现，今年以来股市投资风格转向中小盘成长股，大盘蓝筹和周期股表现较弱，更进一步凸现创业板市场的优异表现。此外，融通创业板指数基金和易方达创业板ETF联接也受益于创业板市场大涨，今年以来收益率接近40%。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t在开放式主动偏股基金方面，今年以来业绩最好的主动偏股基金投资业绩不到40%。天相统计显示，今年年初到5月14日业绩最好的主动偏股基金是上投双动力，收益率为33.81%，即使算上5月15日的净值上涨，该基金今年以来收益率也不到40%。\r\n</p>\r\n<div>\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t<!-- news_keyword_pub,overseas_futures,hf_GC --><!-- publish_helper_end -->\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t声明：此消息系转载自合作媒体，新浪网登载此文出于传递更多信息之目的，并不意味着赞同其观点或证实其描述。文章内容仅供参考，不构成投资建议。投资者据此操作，风险自担。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(106,'<p style=\"text-indent:2em;\">\r\n\t自上海家化(65.08,2.09,3.32%)股东大会现场获悉，有部分公募基金经理在本周一上海家化大跌当天试图抄底被套，其中上海某基金经理当天就入手100万股。该人士坦言：“没想到刚进去就吃了一个跌停。我早上5点刚下飞机，立马就感到现场来了。”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t5月13日当天上海家化无故大跌，一度放量大跌超过7%，成交量高达12.7亿元，为前一交易日的14倍。对于股价走势一直坚挺，业绩增长强劲并且是正宗消费白马股的上海家化，如此暴跌显然吸引了不少机构的关注。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t据新浪财经了解，当天有好几家公募基金经理不同程度的买入了上海家化。“上海家化平时量很小，很难有这样的暴跌机会进入。只是没想到刚进去，就吃到一个套。”参与了当天抄底的某基金经理自己吐槽。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t网友调侃称：“部分信息不对称的投资人当天选择买入，而信息准确的人选择了当天卖出。”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t昨日上海家化复牌一字跌停，一季度基金持有上海家化合计达1.56亿股，依此估算经过昨日暴跌，基金持股市值当日蒸发10.6亿元。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(107,'<p style=\"text-indent:2em;\">\r\n\t密集调研积极完善合约和规则\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t记者从大商所获悉，继去年木材纤维板立项后，今年木材胶合板期货又获立项，近期大商所密集组织人员调研胶合板和纤维板市场，以加紧完善两品种期货合约和规则，积极推动“两板”上市准备工作、以现代期货机制服务林木产业发展。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t华北、华东、华南和西南地区是国内林木加工、流通和消费集中地，今年以来上述地区都留下了大商所农产品(6.30,0.30,5.00%)事业部人员的调研足迹。在相关生产、贸易、消费、仓储、质检等企业和行业组织，调研人员针对胶合板与纤维板的生产消费、流通贸易、质量检验、产品储运等问题进行了深入的调查和论证，目的是进一步完善初步完成的合约和规则制度。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t在浙沪地区，调研人员重点走访了兔宝宝(4.07,0.07,1.75%)集团和杭州夹板市场。兔宝宝是全国十大胶合板生产企业之一，杭州夹板市场是杭州地区最大的人造板批发零售市场，商户超过1000家，其销售与贸易模式具有相当的代表性。在这里，调研人员对当地市场基本情况、相关板材贸易模式和贸易消费习惯、质量升贴水、仓储情况进行了深入了解。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t山东是国内胶合板的主要集散地，在这里，调研人员对胶合板生产进入门槛、胶合板生产企业的产品质量、同类板材不同尺寸间价差关系及当地贸易模式、胶合板质量检验等进行了了解，还对当地纤维板生产、消费及贸易物流情况进行了调研。\r\n</p>\r\n<hr style=\"page-break-after:always;\" class=\"ke-pagebreak\" />\r\n<p>\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t在广东和西南地区，调研人员走访了广西南宁丰林木业集团、高峰林场人造板企业集团、东莞美时家具有限公司等企业，详细了解纤维板质量标准、家具企业采购模式、品牌对价格影响等情况。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t大商所农产品事业部相关人士告诉记者，目前交易所已选择胶合板中的细木工板和纤维板中的中密度纤维板作为这两类品种的交易标的，近期的调研表明两品种在现货贸易中占比较大，代表性强，完全适合作为期货交易标的和交割标准品，同时今年来的系列调研论证为两品种合约和规则的完善提供了依据。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<div>\r\n</div>\r\n<p style=\"text-indent:2em;\">\r\n\t据市场机构人士介绍，我国是胶合板、纤维板生产消费第一大国，2011年胶合板产量达1.18亿立方米，其中符合产业发展方向且易于标准化的为细木工板，占比近20%，因此细木工板作为期货交易标的物具有合理性；而上世纪90年代国内部分期交所上市交易的“进口胶合板期货”，随着我国成为胶合板的净出口国，已失去原有市场基础。在国内纤维板市场，2011年其产量达到5562万立方米，其中用于家具制造的中密度纤维板占89%，国标对该类纤维板有专门的标准，因此选择中密度纤维板作为期货交易标的符合现货市场实际。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t胶合板与纤维板同属木材加工行业的重要产品，二者均在建筑装潢、家具制造和交通运输工具制造等基础性行业中有广泛用途，是国民经济发展中重要的基础性产业之一，因此调研中市场机构人士认为，上市相关林产品期货品种，将填补国内林产品期货品种空白，进一步健全大商所农产品品种序列，拓展大商所为第一产业的服务范围，对于国内林业产业发展意义重大。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t在走访调研中，市场各方均表示出对胶合板和纤维板期货推出的支持与期待，而据记者了解，大商所也通过走访调研积极与相关部门沟通，并与行业机构开展合作，多方推动品种上市工作的开展。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t针对下一步相关工作，交易所农产品事业部有关人员表示，经过近期调研走访及与相关机构合作，目前交易所正在积极推动合约和规则论证及品种上市申请工作，交易所希望能及早推动相关品种上市，丰富和完善农业品种体系，服务林业产业发展。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(108,'<p align=\"center\">\r\n\t&nbsp;\r\n</p>\r\n<p align=\"center\">\r\n\t<embed src=\"http://v.ku6vms.com/phpvms/player/html/vid/1ZPfM3euTTl9pzUt/style/zEAn9SLYaDE./\" type=\"application/x-shockwave-flash\" width=\"550\" height=\"400\" quality=\"high\" /> \r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(117,'<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img alt=\"科比不屑禅师比较：MJ和沙克搭档又会怎样？\" src=\"http://img1.gtimg.com/sports/pics/hv1/188/99/1329/86443658.jpg\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t科比<!--keyword-->(微博)<!--/keyword-->禅师欲起嘴仗\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<!--keyword--><!--/keyword-->腾讯体育<!--keyword--><!--/keyword-->讯 菲尔-杰克逊在新书《11枚戒指：成功的灵魂》中比较了<!--keyword--><!--/keyword-->科比-布莱恩特<!--keyword--><!--/keyword-->和迈克尔-乔丹这两位时代领袖，作为曾经执教过两个人的主教练，菲尔显然有一定发言权，不过在他的眼里，乔丹是要强过科比的。然而科比本人似乎对这种比较毫无兴趣，他在twitter上进行了回应。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>科比推特回应不屑与乔丹比较</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t由于菲尔新书关于乔丹和科比比较的内容曝光，因此网络上讨论声四起。而菲尔也在自己的twitter上写道：“各位球迷，请大家不要在文字上面钻字眼啦。如论如何，我能够在执教生涯中经历两位最伟大的得分后卫，都是莫大的荣幸。”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t科比向来和禅师在twitter上有很多交流，之前科比曾经公开征集好书，希望给自己休赛期养伤时阅读，菲尔就在twitter上给科比提供建议。而科比最近似乎产生了增加纹身的想法，也得到了禅师在twitter上的支持。对于禅师的这番话，科比说：“比较我和乔丹，就像比较苹果和橘子。我很想知道如果当年迈克尔是和沙克-奥尼尔搭档的话，大家的观点又会是怎样的。#不同的角色#，#不同的职业生涯道路#”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t看起来科比对于拿自己和乔丹比较并没有什么兴趣，他拿苹果和橘子比较的意思就是告诉大家，有人喜欢甜的，有人喜欢酸的，永远没有人能说苹果比橘子更好吃，因为不具有太多可比性。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<hr style=\"page-break-after:always;\" class=\"ke-pagebreak\" />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>禅师新书乔丹强于科比惹争议</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t杰克逊在自己的新书《11枚戒指：成功的灵魂》中，将乔丹与科比从人缘、防守、代表作上进行了对比。杰克逊认为乔丹个人魅力更好，与队友的关系更融洽，而科比在职业生涯初期独来独往，后来才逐渐改善，增加了与队友的交往。杰克逊表示乔丹防守更好，防守端的能力和态度都是相当出色的，而科比尽管防守水平也很高，但更喜欢赌博式防御，有时候会因此付出代价。同时从代表作上迈克尔进攻的时候，善于用自己的身体和力量（提高进攻效率）。科比得分的时候，喜欢用出手数去堆。迈克尔更加强壮，肩膀更宽，身板更结实。他还有一双大手，让他可以随意控制球，作出各种假动作。就乔丹与科比的比较对于外界惹起了很大的争议。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>美记怒批科比狂妄</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t科比早前在推特中激烈反驳禅师的“乔丹更强论”，不过在美国记者看来，飞侠显然没法和篮球上帝相提并论，《RealGm》专栏作家贾罗德-鲁多夫便坦言，科比先超过魔术师再谈超乔丹吧。鲁多夫也认为，科比是当代最出色的球员，但和乔丹比，仍然有着不小的距离。就连“魔术师”约翰逊也比目前的科比要强，所以飞侠在拿自己和乔丹争高下的同时，还需要正视自己的地位，直到如今，在外界看来，在科比职业生涯的前半段依旧是沾了奥尼尔<!--keyword-->(微博)<!--/keyword-->的光。“在科比还是新秀的时候，奥尼尔就已经是联盟50大巨星之一了，而且<!--keyword--><!--/keyword-->湖人<!--keyword--><!--/keyword-->的三连冠也是在鲨鱼主导时拿到的，就连魔术师也比科比要强，所以你现在还不能拿他去和乔丹比，先比过魔术师，再来谈乔丹吧。”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>禅师未婚妻力挺科比胜乔丹</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t湖人副总裁珍妮是杰克逊的未婚妻，但她在科比与乔丹比较的这个问题上，并不支持杰克逊的观点。珍妮于推特中表示，在她看来科比才是最好的。“菲尔，这是我的个人看法，关于科比与乔丹比较这个问题，我认为科比才是最棒的，”珍妮写道。广大“科蜜”一致对珍妮和科比表示支持，纷纷在两人的推特中留言，称只有“黑曼巴（科比的绰号）”才是最伟大的。“珍妮，你认为科比是最棒的，我也是这么想的，”一位球迷在珍妮的推特留言道“科密”激烈反应\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t“科比，你不必担心，我认为你依旧是历史最伟大的球员，”球迷凯文给科比留言道。“科比，无论什么情况，你都是我最喜欢的球员，”球迷哈基姆在科比推特留言，“你就是传奇，不要在乎那些无聊的东西，你就是最好的，太崇拜你了。”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t科比球迷的激烈反应，令一向淡定的杰克逊也有些撑不住了，他更新推特尝试平息争论。“球迷们，大家注意一下，”杰克逊写道，“不要在文字上纠缠了，乔丹与科比都是最优秀的得分后卫，我能够得到执教他们的机会，是莫大的幸运。”\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(118,'<p style=\"text-indent:2em;\">\r\n\t北京时间5月18日消息，科比<!--keyword-->(微博)<!--/keyword-->早前在推特中激烈反驳禅师的“乔丹更强论”，不过在美国记者看来，飞侠显然没法和篮球上帝相提并论，《RealGm》专栏作家贾罗德-鲁多夫便坦言，科比先超过魔术师再谈超乔丹吧。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t菲尔-杰克逊早前在自己的新书《11枚戒指：成功的灵魂》中将乔丹与科比进行了对比，在他看来，飞侠在出手选择以及命中率方面都不低飞人，而这段话随后也遭到了科比的反驳，“我很想知道，如果当年迈克尔搭档的是奥尼尔<!--keyword-->(微博)<!--/keyword-->，大家又会怎么评价？”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t科比这番言论显然是在暗示鲨鱼当年抢夺了自己的光芒，对于飞侠的狂妄态度，《RealGm》专栏作家贾罗德-鲁多夫也看不下去了，他在自己的推特上发出了连珠炮式的言论，来抨击科比，“如果乔丹的搭档是鲨鱼、加索尔和<!--keyword--><!--/keyword-->拜纳姆<!--keyword--><!--/keyword-->，那么最终的结果就是年年拿总冠军外加常规赛70胜，科比能做到这些吗？”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t与此同时，鲁多夫也认为，科比是当代最出色的球员，但和乔丹比，仍然有着不小的距离。“科比纵然不错，或许可以说，他是这个时代里最出色的球员，他的技术很全面，但是要说和乔丹相似，这完全是夸大他的作用了，毕竟，乔丹是篮球上帝，而科比只是一个普通的传教士，传教士也很伟大，但他显然不是上帝。”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t在鲁多夫看来，就连“魔术师”约翰逊也比目前的科比要强，所以飞侠在拿自己和乔丹争高下的同时，还需要正视自己的地位，直到如今，在外界看来，在科比职业生涯的前半段依旧是沾了奥尼尔的光。“在科比还是新秀的时候，奥尼尔就已经是联盟50大巨星之一了，而且<!--keyword--><!--/keyword-->湖人<!--keyword--><!--/keyword-->的三连冠也是在鲨鱼主导时拿到的，就连魔术师也比科比要强，所以你现在还不能拿他去和乔丹比，先比过魔术师，再来谈乔丹吧。”\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(119,'<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img alt=\"球迷不满火箭乱用林书豪：用不好就请交易他\" src=\"http://img1.gtimg.com/sports/pics/hv1/127/117/1329/86448187.jpg\" />\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<!--keyword--><!--/keyword-->北京时间5月18日消息，林书豪在季后赛表现令人失望，不过，在火箭球迷看来，林的拙劣发挥完全是火箭所导致，有人甚至坦言：如果不能正确使用林书豪，那么请把他交易到一支适合自己的球队。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t在加盟火箭后，林书豪逐渐褪去了林旋风的光芒，在常规赛里，他的表现时有起伏，场均拿到13.4分6.1次助攻，而到了季后赛，林更是无从找到方向，场均只有4分2次助攻，除了胸部受伤导致状态大减外，火箭对林书豪的使用也受到了火箭球迷的质疑。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t在<!--keyword--><!--/keyword-->尼克斯<!--keyword--><!--/keyword-->时期，林书豪是球队绝对的后场发动机，球总是牢牢的黏在他的手中，由他去决定攻击还是传球，然而在火箭，林却丧失了这种权限，在麦克海尔的战术里，哈登是球队绝对的掌控者，在这种情况下，林书豪实际上变成了一位球权的过渡者，而在进攻一端，他只有进行无球跑动或是潜伏在外线充当定点射手，但这并非是林书豪最擅长的作战风格。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t一位名为凯文的网友指出：在2012-13赛季的多场比赛里，林书豪在球队中扮演的作用都像是一个接球即投的得分后卫，当他和哈登在一起时，他的usage rate（球权占用率）只有20%，控卫的职能实际上已经被架空，而在场上也可以看到，林书豪经常会去拉开空间，而所做的这一切都为了配合哈登的单打。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t即便林书豪打得好，麦克海尔也经常会将其放在场下，而在一些时候，尽管哈登已经遭遇到双人夹击，但麦帅还是会冲着林书豪大喊，让他把球交给前者，林书豪很难去碰球实施随心所欲的攻击，而球迷更多看到的是他一次次无奈地选择在中远距离跳投，伴随而至的是令人揪心的命中率。任何球员碰到这种情况，都很难去解决。作为火箭球迷，显然不希望球队为了迎合某位明星球员而去牺牲其他人的利益，如果休斯顿仍然无法正确使用林书豪，那么干脆就把他交易到其他球队。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t另外一位名为Lights out的网友也同意这样的观点，他坦言，请停止把林书豪当做一个孩子看待，他能够去照顾自己，能够去控制大局，他的挡拆进攻是一流的，但无球跑动和射术却有待改善，林需要去改善自己的弱点，但同时他也需要获得更舒服的战术支持。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t署名为斯蒂芬的网友坦言，林需要打出更高效的投篮命中率，但麦克海尔也同样需要学会更好的使用他，总是把林当做投手来使用，这无疑是一种浪费。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(120,'<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<img alt=\"季后赛MVP：詹皇高居榜首 库里小加大放异彩\" src=\"http://img1.gtimg.com/sports/pics/hv1/60/118/1329/86448375.jpg\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t<!--keyword--><!--/keyword-->詹姆斯<!--keyword--><!--/keyword-->高居季后赛MVP榜首\r\n</p>\r\n<p style=\"text-indent:2em;\" align=\"center\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<!--keyword--><!--/keyword-->腾讯体育<!--keyword--><!--/keyword-->讯 北京时间5月18日，季后赛打到现在，球迷心中的季后赛MVP是谁？《ESPN》做了一个调查，调查的题目就是你心目当中的季后赛MVP，截止到目前为止，<!--keyword--><!--/keyword-->热火<!--keyword--><!--/keyword-->的当家球星詹姆斯高居榜首，<!--keyword--><!--/keyword-->勇士<!--keyword--><!--/keyword-->的<!--keyword--><!--/keyword-->库里<!--keyword--><!--/keyword-->和<!--keyword--><!--/keyword-->灰熊<!--keyword--><!--/keyword-->的<!--keyword--><!--/keyword-->马克-加索尔<!--keyword--><!--/keyword-->分列二三位——\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>詹姆斯居首毫无悬念</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t在这项调查当中，球迷可以选择10名球员，这10名球员有先后的顺序关系，截止到目前为止，詹姆斯得到了28017分，高居所有候选人的第一名，库里和马克-加索尔紧随其后。在征战季后赛的时候，詹姆斯拿到了本赛季常规赛的MVP，同时也入选了最佳防守第一阵容。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t到了季后赛，詹姆斯表现依然相当稳定，在他的带领下，热火在季后赛里面一骑绝尘，首轮热火横扫<!--keyword--><!--/keyword-->雄鹿<!--keyword--><!--/keyword-->，让詹宁斯的豪言成为了笑谈。次轮热火遇到终结他们27连胜的<!--keyword--><!--/keyword-->公牛<!--keyword--><!--/keyword-->，詹姆斯率领队友顽强拼搏，终于让公牛臣服。在其他的球队都还在季后赛当中苦苦挣扎的时候，詹姆斯和他的队友却早早地开始休养生息，准备接下来的大战了。\r\n</p>\r\n<hr style=\"page-break-after:always;\" class=\"ke-pagebreak\" />\r\n<p>\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>库里大放异彩</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t本赛季，库里开始展露出巨星的潜质，在常规赛库里表现就非常出色，本赛季库里命中了272个三分球，这打破了热火球员<!--keyword--><!--/keyword-->雷-阿伦<!--keyword--><!--/keyword-->在2005-06赛季创造的纪录。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t到了季后赛里面，库里更是大放异彩。在季后赛首轮的第一场比赛当中，库里20投9中，三分球10投4中，为球队贡献了19分9次助攻和4个篮板，虽然勇士在最后关头被<!--keyword--><!--/keyword-->米勒<!--keyword--><!--/keyword-->击败，不过库里的表现可圈可点。而在第二场比赛时，库里23投13中，三分球10投4中，为勇士砍下30分、3个篮板、13次助攻、3抢断，表现极为出色，其中在第二节，库里就一人独砍15分。带领勇士大胜<!--keyword--><!--/keyword-->掘金<!--keyword--><!--/keyword-->，从而拉响了淘汰掘金的号角。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t虽然在次轮勇士不敌<!--keyword--><!--/keyword-->马刺<!--keyword--><!--/keyword-->惨遭淘汰，不过库里的表现也可圈可点，在第一场比赛当中库里就砍下44分11次助攻，而在第二场比赛当中，库里砍下22分。当他无缘西部决赛时流泪的那一刻，很多勇士球迷的心都碎了。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<!--keyword--><!--/keyword--><strong>小加索尔</strong><!--keyword--><!--/keyword--><strong>无愧联盟顶级中锋</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t本赛季在小加索尔的帮助下，灰熊的防守效率为97.4，高居联盟第二，仅次于<!--keyword--><!--/keyword-->步行者<!--keyword--><!--/keyword-->。当小加索尔在场时，灰熊每100回合只丢掉95.4分；而当小加索尔不在球场上时，灰熊每100回合丢掉102.2分。这足可以看出小加索尔的防守功力。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t灰熊在季后赛当中能够过关斩将，和小加索尔的出色表现有很大关系，能够将上赛季西部冠军<!--keyword--><!--/keyword-->雷霆<!--keyword--><!--/keyword-->淘汰出局，加索尔做出了很大的贡献，在北京时间2013年5月14日，小加索尔砍下了23分11个篮板，外加6次盖帽，让雷霆再次输掉了比赛。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t另外，排在第4位到第10位的MVP候选人分别是：帕克<!--keyword-->(微博)<!--/keyword-->、兰多夫、<!--keyword--><!--/keyword-->杜兰特<!--keyword--><!--/keyword-->、<!--keyword--><!--/keyword-->乔治<!--keyword--><!--/keyword-->、康利、<!--keyword--><!--/keyword-->安东尼<!--keyword--><!--/keyword-->和波什<!--keyword-->(微博)<!--/keyword-->。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(121,'<p style=\"text-indent:2em;\">\r\n\t北京时间5月18日凌晨，2012-13赛季<!--keyword--><!--/keyword-->国王<!--keyword--><!--/keyword-->杯决赛中，皇马<!--keyword-->(官方微博数据) <!--/keyword-->主场1-2遭马竞逆转，痛失冠军。C罗<!--keyword-->(微博数据) <!--/keyword-->头球首开纪录，迭戈-科斯塔扳平，皇马三次射中立柱，加时赛中米兰达绝杀，穆里尼奥、C罗与马竞的加比先后被罚下。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>比赛焦点</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t马竞历史上第10次国王杯封王，其中9冠是在伯纳乌球场夺得，成为第四支冠军数上双的球队。为马竞扳平比分的前锋迭戈-科斯塔，国王杯4个客场3场进球，并以8球成为赛事头号射手。去年欧洲超级杯加上本赛季国王杯，米兰达均为马竞取得进球，并双双取胜，他的后防搭档戈丁则保持着职业生涯5场决赛全胜的纪录。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t此役进球是C罗本赛季第7个国王杯进球，同时也是自穆里尼奥上任以来，C罗第17个国王杯进球，最近三个赛季名列全西班牙第一。C罗在生涯参加过的杯赛决赛中已打入8球，其中5球是头球，比例达到62.5%。而C罗吃到的红牌，则是他加盟皇马后第三次被罚下。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t皇马14年来首次输给马竞，上一次皇马德比输给马竞，还是1999-2000赛季的联赛。此役出任替补的<!--keyword--><!--/keyword-->卡西利亚斯<!--keyword--><!--/keyword-->，本赛季在国王杯没丢过球，而迭戈-洛佩斯3场丢了3球。此役是国王杯历史上第21次进入加时赛，上一次是2011年皇马击败巴萨<!--keyword--><!--/keyword-->。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t皇马第38次参加国王杯决赛，只获得18冠排名第3，巴萨26冠第一，毕尔巴鄂23冠第二，20次输掉决赛则是国王杯纪录。在伯纳乌进行的国王杯决赛马德里德比中，皇马对马竞四连败。皇马三次中柱，创下本赛季国王杯单场中柱次数纪录。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>精彩回放</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t第14分钟，<!--keyword--><!--/keyword-->厄齐尔<!--keyword--><!--/keyword-->开出角球，C罗摆脱戈丁纠缠头球攻门得手，1-0。第35分钟，法尔考中场连续摆脱两人后送出直塞，迭戈-科斯塔突入禁区左侧斜射打入球门右下角，1-1，马竞扳平比分。第41分钟，厄齐尔弧顶处左脚抽射打中右侧立柱弹出。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t第61分钟，<!--keyword--><!--/keyword-->本泽马<!--keyword--><!--/keyword-->左脚推射打中立柱弹出，厄齐尔禁区内扣球过人起脚抽射，被皇马旧将<!--keyword--><!--/keyword-->胡安弗兰<!--keyword--><!--/keyword-->门线前将球挡出。第68分钟，C罗任意球从人墙下穿过，击中左侧立柱弹回。第77分钟，穆里尼奥冲出技术区抗议裁判被驱逐出场。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t第98分钟，科凯右路传中，米兰达前点甩头攻门，1-2，皇马落后。第114分钟，加比对C罗犯规，葡萄牙人倒地后有个踢人报复动作，被主裁红牌罚下，库尔图瓦被看台上飞下的杂物砸中倒地不起，两队教练球员场边形成混战。第125分钟，加比吃到第二张黄牌也被罚下。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(122,'<p style=\"text-indent:2em;\">\r\n\t北京时间5月13日凌晨，西甲<!--keyword--><!--/keyword-->第35轮中，巴萨<!--keyword--><!--/keyword-->客场2-1逆转马德里竞技，用胜利庆祝了联赛夺冠。梅西<!--keyword--><!--/keyword-->首发但提前退场，连续21场联赛进球的纪录告终，法尔考首开纪录，桑切斯<!--keyword--><!--/keyword-->扳平比分，比利亚<!--keyword--><!--/keyword-->造成加比自摆乌龙。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>比赛焦点</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t由于昨日皇马<!--keyword-->(官方微博数据) <!--/keyword-->战平，巴萨不战而获得第22座西甲冠军奖杯，此役赛前马竞球员在球员通道口列队，向冠军队致敬。目前，巴萨以79个冠军领先皇马的77冠，为西甲之最。巴萨连续27个联赛客场取得进球，刷新了上赛季本队刚创下的连续26场进球的西甲纪录，本赛季联赛客战巴萨已打入50球，同样打破本队单赛季49球的纪录，距离西甲历史纪录只差1球。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t梅西时隔5轮重返首发，此前阿根廷人打入200个进球只用了179场比赛，而C罗<!--keyword--><!--/keyword-->攻入200球则花了197场。马竞是梅西最喜欢的对手，联赛中此前对马竞打入17球，多于其他所有球队。这已是小跳蚤连续第5个赛季代表巴萨出场超过50场，但下半时无法坚持提前下场，连续21场联赛凡是出场就进球的纪录（总计33球）也告终止。巴萨官方透露，梅西自己表示是腿部肌肉不适让他选择退场，次日将进行检查，《世界体育报》则披露，伤势会让梅西休战一个月，本赛季不会再出场。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t法尔考打入了本赛季西甲联赛第1000球，并成为本赛季第二个两回合对垒巴萨都有进球的西甲球员，前一位 是皇家社会中场冈萨洛-卡斯特罗。加盟马竞以来，法尔考在<!--keyword--><!--/keyword-->卡尔德隆<!--keyword--><!--/keyword-->球场35次联赛出战打入35球，场均一 球，效率堪比梅西C罗，加盟马竞后7次出战巴萨和皇马，哥伦比亚人分别打入3球和2球。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t桑切斯扳平比分后脱衣庆祝，内衣上写着“我爱你妈妈”，母亲节献礼，但被主裁佩雷斯-蒙特罗黄牌警告。智利人加盟巴萨以来已为球队打入25球，上赛季15球本赛季10球，自从4月以来，桑切斯直接参与巴萨近17个联赛进球中的10球，5球5助攻。为其助攻的<!--keyword--><!--/keyword-->小法<!--keyword--><!--/keyword-->，本赛季联赛助攻11次，比上赛季多3次。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>精彩回放</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong></strong>&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t两队上半时比赛节奏缓慢，第45分钟，特略<!--keyword-->(微博)<!--/keyword-->禁区左侧兜射远角擦立柱偏出。第51分钟，加比前场铲断得球后倒地捅传，法尔考中路突入禁区低射破门，巴萨0-1落后。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t第72分钟，桑切斯与<!--keyword--><!--/keyword-->法布雷加斯<!--keyword--><!--/keyword-->踢墙配合，智利人禁区内推射扳平，1-1。梅西无法坚持提前退场，巴萨场上只剩10人。第80分钟，特略左翼突破到底线回敲，比利亚包抄推射，加比自摆乌龙，2-1。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(123,'<p align=\"center\" style=\"text-indent:2em;\">\r\n\t<img alt=\"贝克汉姆退役后计划曝光 重回大联盟升任老板\" src=\"http://img1.gtimg.com/sports/pics/hv1/88/91/1329/86441518.jpg\" /> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t北京时间5月18日凌晨，《天空体育》透露，宣布退役后的贝克汉姆极有可能在美国大联盟组织一支全新俱乐部。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t退役前，贝克汉姆已经担任<!--keyword--><!--/keyword-->中超<!--keyword--><!--/keyword-->推广大使、英国天空广播公司形象大使，但万人迷显然不会满足于此，《天空体育》透露贝克汉姆有可能成为美国大联盟俱乐部的老板。贝克汉姆2007年签约洛杉矶银河时，其合同中包括一个特别条款：当贝克汉姆不再为美国大联盟效力时，可以以一个固定的价格拥有一家新俱乐部。这一俗称“扩军费”的价格高达5000万美元，但贝克汉姆合同规定仅为2500万美元。早在2009年披露这一条款时，贝克汉姆就曾激动的表示：“我已经拥有扩军费条款，这让我非常兴奋。”2010年，美国媒体就曾透露贝克汉姆希望购买一家俱乐部或新组建一家俱乐部，但最终不了了之。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t在决定退役后，贝克汉姆与其管理团队讨论了是否选择使用这一条款。美国大联盟执行副总裁库尔特芒什表示：“贝克汉姆已经与他的顾问、管理层进行了初步讨论。”美国大联盟目前有19支球队，但在美国东南部还没有球队，而<!--keyword--><!--/keyword-->迈阿密<!--keyword--><!--/keyword-->被媒体视作小贝的落脚点，库尔特芒什透露贝克汉姆还没有选择最终在那座城市组建新俱乐部：“（迈阿密）是众多选择中的一个，但还有其他选择。”这些选择包括明尼阿波利斯、坦帕和圣安东尼奥。库尔特芒什表示：“在某个时间点，贝克汉姆会使用这一条款。我们可以通过这一过程确保贝克汉姆和美国大联盟的完美契合，但我们现在还没有完成。”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t值得一提的是，贝克汉姆极有可能与曼城<!--keyword-->(官方微博数据) <!--/keyword-->老板曼苏尔酋长合作完成这一计划。天空体育四月底时就曾透露，曼苏尔酋长计划花一亿欧元在纽约组建俱乐部，与贝克汉姆的联手极有可能事半功倍。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(124,'<p style=\"text-indent:2em;\">\r\n\t贵为法网冠军，娜姐本赛季的红土比赛，打得不理想。斯图加特闯入决赛后，娜姐在红土赛的闪耀就结束了，马德里爆冷首轮出局，罗马也止步第三轮。作为上届亚军，娜姐损失了不少积分，最新的世界排名也受到影响，将被埃拉尼挤掉，下滑至第六名。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t赛后，不少球迷在网上质疑娜姐的表现，比如失误太多，或者战术不当。面对网友提出的各种质疑，李娜调侃道：“大家现在都是职业专家啊。”紧接着她又补充说。“难道就不能接受我一场失利吗？”记者也笑着回答。“当然可以接受，大家都是鼓励你，球场没有常胜将军。”李娜听完后也是笑着点头。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t结束罗马之行后，李娜的下一站就是法网，她也简要地谈了之后的安排。“现在去法网还有点早，应该先回慕尼黑，和团队的其他人汇合，和当地的医生、训练师聊聊备战计划，然后就要去巴黎了。”\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(125,'<p style=\"text-indent:2em;\">\r\n\t在刚刚落幕的2013赛季<!--keyword--><!--/keyword-->F1<!--keyword--><!--/keyword-->西班牙大奖赛中，主场作战的两届世界冠军费尔南多·阿隆索四停获胜，亚军则被采取三停策略的基米·莱科宁摘得。这也是<!--keyword--><!--/keyword-->阿隆索<!--keyword--><!--/keyword-->的第32场胜利和第二个主场冠军；同样来自法拉利车队的马萨尽管被罚至第九位发车，却获得第三，并在今年首次登上领奖台。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t阿隆索从第五位发车夺冠，在创造了一个新记录的同时，也再次证明了正确运用轮胎策略来拼抢名次的重要性。与阿隆索相比，莱科宁使用了截然不同的策略，他仅三次进站，并紧随阿隆索，但最后还是落后了不到10秒。莱科宁是唯一一位使用倍耐力P Zero白色中性胎完成前三赛段，然后换上P Zero橙色硬胎进行最后冲刺的车手。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t此站结束之后，冠军之争愈加激烈，在车手积分榜上，第一名与第三名之差仅在17分之内。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t大部分车手都选择以P Zero白色中性胎起跑，除了玛鲁西亚车队的两名车手和卡特汉姆车队的车手查尔斯·皮克。由于较早进站，阿隆索成功超过了维特尔，此后，西班牙人对轮胎策略进行了完美演绎，成功保住领先位置。\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p>\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t倍耐力赛事运动总监Paul Hembery表示：“轮胎策略又一次成为了西班牙大奖赛的焦点，如往常一样，由于加泰罗尼亚赛道的独特性，它对轮胎要求十分严苛。也正因为如此，我们看到了较高的轮胎退化程度，不过在之后的比赛中，这种情况应该会有所缓解。我们的目标是在每站比赛中实现两到三停，相比之下，本场比赛的四停策略是有些多了。事实上，这种情况只在土耳其出现过一次，那是我们重返F1的第一年。我们将做出一些调整，初步计划是能赶上英国站，以确保实现我们的目标并迅速解决问题。恭喜费尔南多·阿隆索与法拉利车队，从始至终，他们都表现积极，奋力向终点迈进，并最终完美演绎了四停策略，在西班牙摘得一个皆大欢喜的冠军头衔。从本周末一开始，他们就着手准备轮胎策略，不仅在排位赛中聪明地使用了轮胎，还在正赛中充分利用轮胎完成了数次精彩超车。”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>赛果总结：</strong> \r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t大众预测三停将会成为致胜策略，但事实上阿隆索使用了四停策略。他在第9圈第一次进站更换硬胎，在第21圈再次更换硬胎，到第36圈时换上了中性胎，之后在第49圈再换硬胎。他的队友菲利普·马萨也采用了相似的策略。\r\n</p>\r\n<div>\r\n\t&nbsp;\r\n</div>\r\n<p align=\"center\" style=\"text-indent:2em;\">\r\n\t<img alt=\"F1西班牙站战术回放：阿隆索四停夺冠超预期\" src=\"http://img1.gtimg.com/sports/pics/hv1/158/96/1325/86182763.png\" /> \r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(134,'测试');--end
 INSERT INTO tq_article_txt VALUES(135,'当事方是东方闪电');--end
 INSERT INTO tq_article_txt VALUES(136,'dfdddgfddg');--end
 INSERT INTO tq_article_txt VALUES(137,'sfsdfdsfdsf');--end
 INSERT INTO tq_article_txt VALUES(185,'<p style=\"text-indent:2em;\">\r\n\t中国设立航空识别区，让日本自卫队为如何应对伤神。航空自卫队甚至由此设想出10-20年后日中冲突的三种可能，并商讨应对方案。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t日本《产经新闻》5日报道称，<strong>中国在东海设立防空识别区后，航空自卫队立即召开紧急电视会议。作战中枢航空总队司令和全国三个航空支队的司令、冲绳西南航空混成团司令全部到场，再加上总队直辖部队的指挥官，“表情僵硬”地会聚一堂。</strong>总队司令发出指令，“西部和西南航空混成团要严正应对领空侵犯措施”;“考虑到飞行员的负担，北部和中部部队也要提供支援”。但各部队司令官则不断提出具体问题。如“战斗机紧急出动时接近(中国)飞机的距离多大合适？在怎样的阶段才能使用武器？”\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t报\r\n道说，航空自卫队F-15战斗机今年在东海频繁飞行，旨在探测中国军方雷达的弱点。自卫队的结论是，中国雷达最远探测距离可以到达钓鱼岛上空，但只能发现\r\n一定高度以上飞行的飞机。而航空自卫队最西端的雷达可以涵盖钓鱼岛东南大约340公里的宫古岛，对于防守钓鱼岛十分有利。<strong>“如果中国夺走钓鱼岛，优势就会被逆转”。</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<br />\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(187,'<p align=\"center\" style=\"color:#000000;text-indent:0px;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"\" src=\"http://demo.javapms.com/member/upload/demo/201311/09223726jk8s.jpg\" /> \r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t北京时间11月9日，在亚冠决赛的次回合比赛中，广州恒大坐镇天河体育场1-1战平韩国球队首尔FC，虽然双方总比分战成3-3平，不过凭借客场进球优势恒大最终力压对手捧杯，这也是中国球队第一次问鼎亚冠冠军，埃尔克森第58分钟打破僵局，4分钟后德扬扳平比分。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t<strong>比赛焦点</strong> \r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t开创历史：之前10个亚冠冠军中，韩国球队夺走了其中的四次，日本球队也是两次捧杯，沙特的伊蒂哈德更是成为唯一一支两次捧杯的球队，阿联酋和卡塔尔球队也各自捧杯一次，恒大的捧杯也开创了中国球队的历史。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t24年中国球队再登顶亚洲：90年（实为89年，为世预赛延期）辽足问鼎亚俱杯冠军后，中国球队从此就未在亚洲赛事中问鼎，大连万达1998年亚俱杯亚军，大连实德(<span class=\"infoMblog\">微博</span>)2001年亚洲优胜者杯亚军，恒大这一次登顶也一改24年的历史。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t恒大破魔咒：除了05赛季卫冕亚冠冠军的伊蒂哈德之外，其他9个赛季的亚冠冠军队中都没有当赛季的亚冠最佳射手，这也是亚冠史上的一大魔咒，但是随着恒大夺冠，穆里奇荣膺金靴，这个魔咒也是被来自中超的恒大打破，冠军成员穆里奇以13个进球成为本赛季亚冠的金靴。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t主场不败但留一遗憾：决赛次回合之前的6个主场比赛，恒大取得了5胜1平进16球且不失一球的战绩，随着本场比赛1-1战和对手捧杯，恒大本赛季的7个亚冠主场也是未尝败绩，不过主场不丢球的纪录却被德扬终结。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t逢韩球队不胜：虽然力压首尔登顶，但是本赛季亚冠恒大和韩国球队交手四次，小组赛两平全北现代，决赛两回合和首尔FC的比赛也都打成平局，未能在对阵韩国球队时取得一场胜利，也是恒大登顶中的遗憾。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t<strong>精彩回放</strong> \r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t第13分钟，孔卡后场送出长传，穆里奇利用速度优势得球杀入禁区，虽然被首尔后卫在禁区内放倒，不过主裁拒绝判罚点球。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t第15分钟，埃尔克森右路将球分给张琳芃，张琳芃底线附近扣过防守球员，随即将球倒三角传给点球点附近的孔卡，孔卡转身低射，虽然首尔FC门将对此球毫无办法，但是皮球却鬼使神差的擦着立柱偏出，恒大错失打破僵局的良机。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t第20分钟，孔卡接穆里奇的分球后，禁区前沿稍作调整便是一脚冷射，但是皮球打在立柱外侧飞出，短短5分钟之内孔卡连续错失两次良机。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t第33分钟，张琳芃飞身放倒德扬，主裁向其出示了本场比赛的第一张黄牌。上半场补时阶段，何大成对穆里奇犯规，主裁终于向其出示了黄牌。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t第49分钟，穆里奇禁区内和黄博文做出一个漂亮的二过一配合，不过穆里奇在形成单刀的情况下最后的推射被金龙(<span class=\"infoMblog\">微博</span>)大神奇的倒地封出。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t第58分钟，恒大后场抢断得手，穆里奇中场送出长传，埃尔克森禁区线上卸下皮球后，晃开首尔FC的最后一名防守球员，随后面对金龙大轻松的将球送入死角，恒大终于打破僵局，1-0，总比分3-2。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t第62分钟，埃斯库德罗禁区前沿将球交给德扬，德扬在埃斯库德罗的掩护下突入禁区，随后一脚射门洞开曾诚(<span class=\"infoMblog\">微博</span>)把守的城池，首尔FC将比分扳为1-1平，总比分3-3。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t第86分钟，尹日禄在无法得到皮球的情况下，飞铲用鞋底狠狠的蹬在冯潇霆 的膝盖之上，但是主裁仍没有任何表示。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t广州恒大出场阵容（4-3-3）：\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t门将：19-曾诚；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t后卫：32-孙祥 、28-金英权 、6-冯潇霆、5-张琳芃；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t中场：16-黄博文、10-郑智、37-赵旭日（67分钟29-郜林）；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t前锋：11-穆里奇、16-孔卡、9-埃尔克森\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t首尔FC出场阵容（4-2-3-1）：\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t门将：1-金龙大；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t后卫：5-车杜里、4-金周荣、6-金珍圭、8-安迪尔森；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t后腰：16-何大成（85分钟17-崔琁态）、22-高明振；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t前卫：21-高尧韩（46分钟24-尹日禄）、11-莫利纳、9-埃斯库德罗；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t前锋：10-德扬\r\n</p>\r\n<br class=\"Apple-interchange-newline\" />');--end
 INSERT INTO tq_article_txt VALUES(188,'<p align=\"center\" style=\"color:#000000;text-indent:0px;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"埃神：恒大需要愿续约 加盟前曾想去巴萨皇马\" src=\"http://img1.gtimg.com/sports/pics/hv1/111/145/1447/94128261.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p align=\"center\" style=\"color:#000000;text-indent:0px;font-family:宋体;font-size:10.5pt;font-style:normal;font-weight:normal;\">\r\n\t决赛首回合埃神为恒大攻入扳平一球\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t记者白国华首尔报道 “如果有可能的话，我希望回到主场，在广州再进一球。”赛后，对于2比2的结果，埃尔克森虽然比较满意，但并不满足，他希望在自己的主场终结首尔FC，“冠军，终归恒大”。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t<strong>埃神更危险</strong> \r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t10月26日的比赛，尽管先丢1球，但恒大没有慌乱，第30分钟，黄博文发出角球，人丛中埃尔克森甩开紧贴自己的河大成，跃起头球，皮球弹地后撞入球门死角，1比1。进球后，埃尔克森非常激动，和队友拥抱，感谢主，向球迷振臂高呼……\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t这是埃尔克森在亚冠中的第5球，而他只打了5场比赛，场均1球。由于小组赛巴里奥斯的存在，他并未获得亚冠资格，但埃尔克森明确表示，自己会在看台上为队友加油，因为大家是一个集体，此外，自己也会时刻准备着，“如果给我机会，我不会让大家失望。”1/8决赛开启，埃尔克森登场，对莱赫维亚，主场1球，客场2球；打柏太阳神，主场首开纪录；战首尔，又是他扳平了比分。算上中超，埃尔克森本赛季为恒大出场32次，打入27球并奉献了10个助攻，不愧埃神称号。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t赛前，首尔FC方面谈论最多的是孔卡和穆里奇，而不是没打小组赛的埃尔克森，埃斯库德罗刺激恒大，谈及的也是孔、穆。不过，韩媒就提醒首尔，比孔卡和穆里奇更恐怖的是埃尔克森。《最佳11人》的记者金泰锡认为，虽然孔卡和穆里奇导演了上赛季5比1狂扫全北的大戏，但埃尔克森更强。他还说，虽然本赛季才加盟恒大，但无论是在亚冠还是中超，他都显示了自己强势的攻击力。“在亚洲，埃尔克森绝对是顶尖水平，相比孔卡以及穆里奇，埃尔克森是更危险的箭头人物，所以，首尔FC的后卫们必须全力以赴，限制他的发挥”。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t<strong>有点小紧张</strong> \r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t对于决赛，埃尔克森充满渴望，“首尔很强，但亚冠冠军是我们今年最大的任务。”对于首尔方面的挑衅，他表示嘴巴长在别人嘴上，“我们不能阻止别人说什么，解决问题的唯一办法是‘场上见’，两回合击败首尔，看到时候他们还说什么。”\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t不过，随着决赛时间越来越近，埃尔克森也表示，自己有点小紧张，“到了这个时候，我们心情免不了会有些复杂，这其中既有点焦虑，又有点紧张和开心。”在接受巴西媒体《Surgiu》采访时，埃尔克森坦诚相对，“足球场上确实什么都可能发生，我们做好了打两回合的准备。”他还说，本年度亚冠，中国足球表现非常出色，“堪称魔幻”，恒大是这次进步的助推者，“我很骄傲，能成为这个集体的一员，我非常希望，能获得亚冠奖杯”。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t应该说，26日的比赛，埃尔克森表现非常积极，打入1球，而且，竭力为队友制造机会，在首尔将比分扳平后，他为穆里奇制造了一个单刀的机会，可惜“鸡爷”的状态并不是很好，否则，恒大就可能绝杀对手，让首尔更加绝望，不过，2比2的比分还是让恒大在两回合决赛中占据了先手。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t<strong>主场争胜利</strong> \r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t“虽然没赢球，但是，我们打入了两个客场进球，可以说，这非常重要。”埃尔克森赛后表示，回到主场，“只要一个0比0，或者1比1的平局，我们就能获得冠军。”形势乐观，但和恒大其他球员一样，埃神也表示，恒大不会满足平局，“我们会全力争胜，我也希望能再有进球。冠军，终归恒大”。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t由于在中超和亚冠表现出色，而且年轻，所以，埃尔克森收到了不少来自欧洲的邀请，“去欧洲踢球，是很多球员的梦想，我也不例外。”埃尔克森说，不过，他话锋一转，表示在恒大的合同期内，不会考虑寻找新东家，“我在这里非常开心，无论是足球还是生活。”他甚至表示，如果可能，自己愿意续约。“当然，前提是恒大还需要我。”如果留不下，他想去欧洲，来恒大前，五大联赛的不少球队都对他感兴趣，埃神说，届时他希望能去豪门，比如皇马、巴萨或者拜仁。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t赛季初，恒大以570万欧元将他从博塔弗戈带到广州，合同期4年。与孔卡不同，埃尔克森勇敢尝试广州的一切，包括美食，他对兰州拉面赞不绝口。现在的埃神，和球队已完全融合。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t“在这里，我真的很快乐。”埃尔克森说：“球队、球迷，都给了我很大的支持，我觉得应该回报他们。”现在，他自己已锁定了中超金靴，亚冠上虽然打入5球，但由于没打小组赛，和穆里奇差距甚大，但明年，他希望竞争亚冠最佳射手，“当然，现在最关键的是11月9日的亚冠第二回合决赛，球队的胜利是第一位的。”埃尔克森说。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(189,'<p align=\"center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"霍华德：誓为火箭拿总冠军 与哈登堪比OK组合\" src=\"http://img1.gtimg.com/sports/pics/hv1/6/119/1447/94121526.jpg\" /> \r\n</p>\r\n<div align=\"center\" style=\"color:#000000;\">\r\n\t霍华德：与哈登堪比OK组合\r\n</div>\r\n<div class=\"mbCardUserDetail\" style=\"color:#000000;\">\r\n\t<span class=\"infoMblog\">腾讯体育</span>讯 北京时间10月28日，《休斯敦纪事报》消息，霍华德在今天对着球迷们保证，要为他们带来总冠军，霍华德还将自己与哈登的二人组和OK组合、斯托克顿与马龙(<span class=\"infoMblog\">微博</span>)的组合进行了比较——\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t“球迷朋友们，我知道你们想要一枚总冠军戒指，其实这也是我们的想法，我们内心里面也希望能够得到一枚总冠军戒指。”霍华德说。霍华德还表示自己对总冠军戒指相当渴望，不过他也很清醒地认识到，想要拿到一枚总冠军戒指并非易事，“我想我们可以成为一支非常优秀的球队，不过我想这并不会很容易。接下来我们还有一些事情要证明。”霍华德说。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t霍华德还谈到了他与哈登的组合，“你们可以去看一看那些打出了相当出色的赛季，或者是那些夺冠的球队，他们都有自己的超级组合，”霍华德说，“奥尼尔和科比，魔术师和贾巴尔，德雷克斯勒和大梦，甚至斯托克顿和马龙，都是相当具有威力的组合。这也许是我和哈登在一起打球值得期待的地方，我和哈登也能够成为类似的二人组，只要我们能够打出巅峰的表现，我们夺冠梦想就有可能会实现。”\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t霍华德之所以这样豪言壮语，和他目前自己所处的新环境，以及火箭在季前赛收获的一波6连胜有一定的关系。在火箭，霍华德除了受到球迷的热烈欢迎之外，他也受到了队友的欢迎，他与哈登等球员的关系相当不错。这让他有了一种“回家”的感觉，在这样的氛围之下，霍华德更能够发挥出自己的实力。而火箭的实力对比前几个赛季有了很大的进步，算上霍华德，再加上詹姆斯-哈登、林书豪、帕森斯、阿西克这么多年轻才俊，如果新赛季霍华德与哈登等人能够保持健康，并且发挥出自己最大的威力，那么火箭将有可能在西部乃至全联盟刮起红色狂潮，到时候火箭或许就能够迎来真正的大场面。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(190,'<p align=\"center\" style=\"color:#000000;text-indent:0px;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"崔永元美国传话：辞职央视？我不回应\" src=\"http://img1.gtimg.com/ent/pics/hv1/238/88/1447/94113853.jpg\" />&nbsp;\r\n</p>\r\n<p align=\"center\" style=\"color:#000000;text-indent:2em;font-family:宋体;font-size:10.5pt;font-style:normal;font-weight:normal;\">\r\n\t崔永元\r\n</p>\r\n<p align=\"left\" style=\"color:#000000;text-indent:2em;font-family:宋体;font-size:10.5pt;font-style:normal;font-weight:normal;\">\r\n\t几天来，有关崔永元办完离职手续正式离开央视的消息，被闹得沸沸扬扬。昨日上午，有媒体称，崔永元已确认加盟河南卫视，除了担任《一起读书吧》节目的选书顾问，还将与台湾著名漫画家蔡志忠、著名文化学者钱文忠一起担任《成语英雄》的嘉宾。昨日中午1时，华西都市报记者独家连线到了正带着团队在美国拍纪录片的崔永元本人。华西都市报记者直问：“崔永元老师，你是否从央视辞职？”崔永元大耍太极，幽默地对华西都市报记者表示：“我不回应这事。”\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t<strong>崔永元三次拒绝：不回应</strong> \r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t三天前，有媒体称崔永元已办完了离职手续，准备成立工作室。对于离开央视的原因，有知情人爆料称崔永元希望投身电影圈，又有消息说他将加盟河南卫视。10月21日，崔永元通过微博透露了行踪，原来他已飞赴美国。昨日中午北京时间1时许，华西都市报记者很顺利地拨通了崔永元的电话。崔永元在接华西都市报记者的独家电话采访时，态度很热情。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t“最近说你要从央视辞职的消息，炒作得很凶。”“是不是啊？我还不知道呢。”崔永元回答说。“你到底从央视辞职没有？”崔永元说：“我不回应这事。”“有媒体报道说，你已办完了离职手续？”崔永元仍坚持：“我不回应。”华西都市报记者又问：“你在美国吗？”崔永元说：“对，我在美国。”20分钟后，为了再次核实崔永元辞职央视的传闻，华西都市报记者又给崔永元发去了短消息。崔永元很快回了短信：“谢谢您，我的回答是：不回应”。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t<strong>一点证实：崔永元只去河南卫视当嘉宾</strong> \r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t今年5月9日，华西都市报记者应河南卫视邀请，曾去采访赵忠祥[微博]主持的《中国感动》的新闻发布会。记者发现河南卫视为了抓节目质量，今年“挖人才”动作很大，不仅邀请了央视的赵忠祥、马东主持节目，如今又把崔永元“挖去”担任河南卫视《成语英雄》的嘉宾。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t昨晚，华西都市报记者电话采访河南卫视品牌推广部的负责人张毅。张毅向记者证实说：“崔永元确实将担任《成语英雄》的嘉宾。至于他为什么偏爱与河南卫视的合作，这可能与崔永元的文化情结有关，节目的文化气息与他的气质比较契合。”张毅说，“至于崔永元是否辞职央视，我不清楚。他只是来当嘉宾，并不是调来。”紧接着，张毅神秘地表示：“要知道崔永元是否辞职央视，11月1日《成语英雄》将在郑州举办大型新闻发布会，崔永元要出席。”\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t昨晚，华西都市报记者再次电话采访央视总编室负责媒体宣传的张先生。他谨慎表态说，“我们没有得到崔永元辞职央视的消息。”崔永元\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(191,'<p style=\"color:#000000;text-indent:2em;\">\r\n\t72家基金公司旗下基金半年报今日披露完毕。统计显示，上半年基金整体盈利超过200亿，押注成长股基金获利颇丰；结构性行情下基金换手率和交易佣金水涨船高。展望后市投资，基金表示将权衡估值与成长，在回补低估值蓝筹的同时，更加坚定地将资金进一步向优质个股集中。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t天相统计显示，上半年基金盈利205.38亿元，较去年同期近千亿的盈利水平相差甚远。其中，押注成长股的基金赚得盆满钵满，上投内需以17.14亿的盈利水平成为上半年最“赚钱”的基金；而坚守价值股的基金“账本”相对尴尬。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t结构性行情激活了基金的操作热情，上半年主动偏股型基金换手率高达1.2853倍，与去年上、下半年1.0539倍和0.9502倍的水平相比回升明显。与此同时，其支付的交易佣金水涨船高达到25.30亿，同比增幅28.76%。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t费用方面，上半年基金管理费收入增长7.75%，远低于支付给销售渠道的客户维护费13.69%的增幅，部分小公司的生存压力日益凸显。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t展望后市投资，成长股经历普涨之后，总体估值已然偏高，增加了基金的选股难度，而蓝筹板块的悲观预期呈现出自我强化的趋势，这或许又在孕育投资机会。在此逻辑之下基金均衡配置的意愿有所提升。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t大成创新成长基金预期，短期市场结构性机会仍存，将继续保持相对均衡的配置，权衡估值与成长，继续买入价值低估且稳定增长的蓝筹类股票和估值合理行业发展空间巨大的成长类股票。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(192,'<p align=\"center\" style=\"color:#000000;text-indent:0px;\">\r\n\t<img title=\"上证指数(000001)\" style=\"border:0px currentColor;\" alt=\"上证指数(000001)\" src=\"http://img.gtimg.cn/images/kline/hushen/indexs/day/000001.png?0.8444874294094227\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:0px;\">\r\n\t<br />\r\n</p>\r\n<p class=\"pictext\" style=\"text-align:left;color:#000000;text-indent:2em;\">\r\n\t近期股指持续大跌4个交易日，不是秋收行情的结束，而是部分个股风险的集中释放，冬种春生大好机会正孕育中\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t■本报记者 张晓峰\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t上周大盘出现连续四连跌，<span>上证指数</span>一举跌破2150点支撑位，回补2143点缺口，同时失守60日和120日两条重要均线，市场情绪开始从过去的乐观逐渐转向悲观，不少投资者认为本轮秋收行情结束，中级调整来临。尽管近期股指剧烈震荡超出预期，但笔者综合各方观点认为，短期继续下跌幅度有限，阶段性调整利于结构性牛市进一步展开。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t首先，基本面决定了结构性牛市并没有结束。从经济数据看，今年GDP增速将可达到7.6%，高于政府年初提出的7.5%的目标，并打破了多数经济学家和分析师今年来一直坚持“GDP7%的增速都保不住，很有可能回到6%”的预期，以及“中国经济将会硬着落”的悲观预测。经济企稳，更有利于结构性牛市的持续。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t从经济政策来看，政府的“调结构、转方式、经济转型、产业升级、大力发展高新技术产业”的方针没有改变，反而得到政府领导人进一步的强调，这也支持了新兴产业为代表的结构性牛市将得以继续。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t其次，改革红利将继续推动A股走牛。举世瞩目的十八届三中全会召开在即，从目前的消息面来看，这次全会主要是研究全面深化改革问题，改革的力度和深度可能都要超出大家的想象。这一轮改革范围之广，力度之大，都将是空前的，必将强有力地推动我国经济社会各领域的深刻变革。如果改革超出大家的想象和预期，我们有理由相信改革会大幅度的解放生产力，改革带来的红利目前在市场中完全还没有释放出来，改革必然会促A股走牛。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t第三，供求关系决定了上升趋势的形成。当前宏观经济的情况来看，IPO重启的大门依然紧闭，在货币政策趋于中性的大背景下，股市的资金不会因为IPO而“失血”，流动性会保持相对宽裕。在IPO开闸之前，股市反弹行情就难言结束。反过来看，IPO开闸也需要良好的市场氛围，股市会因为管理层的改革而振奋，保护中小投资者的利益是大势所趋，未来更加重视散户投资者也将是<span>中国资本</span>市场的必然趋势。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t特别值得一提的是，新股发行将推行优先股制度，解决“一股独大”，将控股股东的部分股权变为优先股，以杜绝新的大小非没完没了地产生、减持并冲击市场。实行优先股后，政府将可以批准养老基金、住房公积金等长线资金入市，减轻新股扩容对市场资金带来的压力。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t第四，从技术面看，经过一个阶段的调整，上证指数跌破年线之后已经下探到60日线附近，从成交量的情况来看近几个交易日两市下跌过程中一直缩量，预计下跌动能也在慢慢衰竭。与此同时，上涨指数已经兵临2140点半年线支撑位。连续的调整消耗了一定的下跌动能，不少强势股近期跌幅已达30%以上，2140点半年线附近有望构筑支撑，短线技术性反抽可期。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t从上证指数周K线看，周MACD已经三次背离四次金叉，发出了很强的大底部信号，这是1990年以来首次出现的强烈背离的现象，技术特征预示1849点是很大级别的低点，底部可靠程度是非常高的底背离。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t总体来看，今年赢家的一条宝贵经验：轻指数、重个股。这个轻指数，不仅指轻上证指数，也指轻<span>创业板</span>指数。因此，近期股指大跌4个交易日，不是秋收行情的结束，而是部分个股风险的集中释放，冬种春生大好机会正孕育中。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(193,'<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"百度“百发不中”犯了哪三个错误？\" src=\"http://img4.cache.netease.com/tech/2013/10/29/2013102907373242a5e.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:0px;\">\r\n\t<br />\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t百度敲锣打鼓昭告天下的“百发”产品上线开卖、吃了闭门羹的用户骂声一片，估计让两家网站偷着松了口气：铁道部的12306网以后可以说，国内互联网BAT三巨头之一的重头产品、流量压力远远不如春运抢火车票，居然一上线就死火；阿里更可以偷着得意，原因你懂的。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t试水“互联网金融”的滩头战斗，百度虽然勉强登陆，但品牌损失不小。在我看来，百度犯了三个错误。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t首先是战略性错误，它选错了战场。百度的互联网金融战略选择货币市场基金入手，这是对余额宝的简单跟随，在阿里选定的战场、擅长的产品、熟悉的用户上竞争，以己之短，战彼之长，结局可想而知。从战略上，百度应该把视线越过余额宝，把互联网金融的主战场、主话题拉回到金融搜索上来，夺取互联网金融战场的制空权。它做的应该是整个生态圈的生意，而不是陷入产品的价格战中自找麻烦。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t第二个错误，是它在用户体验这个互联网公司主场的失败。平时我参加传统金融机构和互联网人的讨论时，互联网人像咒语一样念念不休的四个字就是“用户体验”。然而，百度理财比诺曼底还艰难的登陆，打破了互联网公司“把用户体验做到极致”的神话。从此，传统金融机构可以抬起头来了，“让用户爽，宁有种乎？”\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t第三个错误是营销。最近百度的公关和营销狼性大增，这是好事，但是方向和准头有待提高。比如，它这次的产品和品牌颇为杂乱，以至于写文章时我一直在头疼到底用哪个词更准确：百度金融？百度理财？百发？百付宝？百度理财B？或者是华夏现金增利？这些品牌之间亲属关系复杂，很难不把用户搞晕。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t还有，此前大张旗鼓的8%收益率，为何变成了“供参考”的4.933%？8%的概念到底还有没有？隐性承诺是否已经作废？解释沟通远远不足。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t当然，开张首日被用户刷爆，纵有千般不是，也是幸福的烦恼。百度的团队们不妨既开庆功宴，又开反省会。百度理财的官方微博，在上线前夜发了一条极有先见之明的微博：每一年都会至少做一个让你后悔至极且事后想自挖双目的蠢决定，而那个决定，在当时看来都是极为正确的……不必自责，因为我们需要用一生来长大。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t借用那句熟悉的英文翻译腔：我不能同意更多。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(194,'<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"苹果第四财季净利75亿美元 同比下滑9%\" src=\"http://img5.cache.netease.com/tech/2013/10/29/201310290512408bcff_550.png\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t网易科技讯 10月29日消息，苹果当日公布了截至9月28日2013财年第四财季财报。报告显示，该季度苹果实现营收375亿美元，同比增长4%；实现净利润75亿美元，去年同期为82亿美元，同比下滑9%；合摊薄后每股收益8.26美元，去年同期为8.67美元。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t<b>第四财季业绩摘要</b> \r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·营收为375.72亿美元，去年同期为359.66亿美元，同比增长4%；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·净利润为75.12亿美元，去年同期为82.23亿美元，同比下滑9%；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·合摊薄后每股收益8.26美元，去年同期为8.67美元，同比下滑5%。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·毛利率为37%，去年同期为40%；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·国际营收占季度总营收的60%；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·iPhone销售量为3380万部，去年同期为2690万部，创新9月季度里的历史最佳销售记录；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·iPad销售量为1410万台，去年同期为1400万部；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·Mac电脑销售为460万台，去年同期为490万部；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t苹果董事会已宣布每股3.05美元的股息分红计划。此次派息将于2013年11月14日兑现给所有在2013年11月11日当天登记在册的股东。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t“我们很高兴宣布以创纪录的四季度营收完成了这个令人惊叹的财年，该季度我们售出了将近3400万部iPhone。”苹果首席执行官蒂姆·库克（Tim Cook）表示，“我们对假日旺季的即将到来感到兴奋，我们的新产品包括有iPhone 5c和iPhone 5s、iOS 7、新视网膜屏iPad mini、超轻薄iPad Air、新MacBook Pro、全新设计的Mac Pro、OS X Mavericks以及下一代为OS X和iOS设计的iWork和iLife应用。”\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t“我们本季度生成运营现金99亿美元，并通过分红和股票回购向股东们返回了78亿美元现金。我们的资本返回计划现在还剩余360亿美元。”苹果首席财务官彼得·奥本海默（Peter Oppenheimer）表示。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t<b>2013财年第四财季业绩展望</b> \r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·营收将在550亿美元至580亿美元之间\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·毛利率将在36.5%至37.5%之间\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·运营支出将在44亿至45亿美元之间\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·其他收入为2亿美元\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·有效税率为26.25%\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t<b>地区业绩</b> \r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·美洲地区营收为139.41亿美元，上季度为144.05亿美元，去年同期为138.10亿美元，环比下滑3%，同比增长1%；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·欧洲地区营收为80.05亿美元，上季度为76.14亿美元，去年同期为80.23亿美元，环比增长5%，同比基本持平；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·大中华地区营收为57.33亿美元，上季度为46.41亿美元，去年同期为54.27亿美元，环比增长24%，同比增长6%；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·日本地区营收为33.41亿美元，上季度为25.43亿美元，去年同期为23.67亿美元，环比下增长31%，同比增长41%；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·其他亚太地区营收为19.80亿美元，上季度为20.46亿美元，去年同期为21.10亿美元，环比下滑3%，同比下滑6%；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·零售专卖店营收为44.72亿美元，上季度为40.74亿美元，去年同期为42.29亿美元，环比增长10%，同比增长6%。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t<b>产品营收</b> \r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·iPhone及相关产品和服务营收为195.10亿美元，上季度为181.54亿美元，去年同期为166.45亿美元，环比增长7%，同比增长17%；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·iPad及相关产品和服务营收为61.88亿美元，上季度为63.74亿美元，去年同期为71.33亿美元，环比下滑3%，同比下滑13%；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·Mac电脑及相关服务营收为56.24亿美元，上季度为48.93亿美元，去年同期为66.17亿美元，环比增长15%，同比下滑15%；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·iPod营收为5.73亿美元，上季度为7.33亿美元，去年同期为8.20亿美元，环比下滑22%，同比下滑30%；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·iTunes、软件及服务销售为42.60亿美元，上季度为39.90亿美元，去年同期为34.96亿美元，环比增长7%，同比增长22%；\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t·外围设备及其他硬件营收为13.19亿美元，上季度为11.79亿美元，去年同期为12.55亿美元，环比增长12%，同比增长5%。（卢鑫）\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(195,'<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"李湘女儿小白富美\" src=\"http://img3.cache.netease.com/lady/2013/10/27/2013102722212954b41.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t<b>李湘女儿小小年纪标签多：中国版贝小七、阿玛尼、小公主、小炫富姐、小白富美</b> \r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t自从《爸爸去哪儿》开播以后，李湘女儿王诗龄瞬间飙升为最热萌娃，中国版的“贝小七”。更有知情人表示，这个四岁的姑娘可是从小穿着阿玛尼，住着豪华公主房，完完全全的小白富美范儿。网友笑称：看来在还没有意识的情况下，就已经朝着炫富姐进发了！\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"李湘女儿小白富美\" src=\"http://img2.cache.netease.com/lady/2013/10/27/201310272309378bdb8_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t最近，网上传得最多的，就是王诗龄小朋友的私房照。家中的钢琴、大牌的童装、以及大导演和大主持人爸妈爱的红木家具，都让这个宝贝女儿的成长环境受到了观众的感叹于唏嘘，这孩子生活条件也太富足了吧！\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"李湘女儿小白富美\" src=\"http://img5.cache.netease.com/lady/2013/10/27/20131027231048c0591_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t李湘与王岳伦的女儿王诗龄才四岁，但从节目中看出，古灵精怪的大小姐劲儿十足，把篮子给别人提着，自己想出来的奖惩办法——狗尾巴草送给拍她拍得最美的摄像师傅，与街边小姐妹搭讪“我不是大明星，我是小公主”，统统都显示着这个孩子的非同一般。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t<b>星二代选美赛 比演艺圈竞争还激烈的萌娃界！</b> \r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"李湘女儿小白富美\" src=\"http://img4.cache.netease.com/lady/2013/10/27/20131027225051d0005_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t10月26日，是陆毅鲍蕾女儿五周岁的生日，晚间陆毅微博晒出为女儿庆生的照片，女儿乖巧恬静似公主。敢于曝光自己的女儿，这在小编看来正是通往《爸爸去哪儿》的第一步，顺着照片看出，陆毅的家装修精致豪华，这就是陆毅家小公主的“生存空间”。\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p style=\"color:#000000;text-indent:0px;\">\r\n\t<span></span>&nbsp;\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"李湘女儿小白富美\" src=\"http://img6.cache.netease.com/lady/2013/10/27/20131027225102121e0_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\tCindy经过《爸爸去哪儿》一曝光，瞬间成为了国内“一线”小童星，乖巧伶俐地她很是讨观众喜爱。而与跳水世界冠军爸爸——田亮的互动，也显示出一位小公主在家里的地位，父母的溺爱。\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"李湘女儿小白富美\" src=\"http://img2.cache.netease.com/lady/2013/10/27/20131027225113675ca.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t黄磊女儿黄多多，作为下一季爸爸去哪儿呼声最高的种子选手之一，从小也过着优渥的生活。不仅妈妈孙莉是美女演员，爸爸黄磊更是娱乐圈中的泰斗型人物，遗传了爸妈优良基因的多多不仅长得好看，因为长期跟随导演父亲出入舞台，对于舞台更是毫不怯场，星范儿十足。\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p style=\"color:#000000;text-indent:0px;\">\r\n\t<br />\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"李湘女儿小白富美\" src=\"http://img4.cache.netease.com/lady/2013/10/27/201310272253449f49d.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t钟爱高跟鞋的小苏瑞从三岁开始就拥有了自己的第一双高跟鞋。苏瑞穿高跟鞋的频繁亮相也引发了时尚萌娃们的青睐，而各大品牌也纷纷推出儿童款高跟鞋。这一高跟鞋热潮被网友们笑称是“苏瑞效应”。\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"李湘女儿小白富美\" src=\"http://img5.cache.netease.com/lady/2013/10/27/2013102722521666e7b.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t当一般的孩子还在穿“西瓜太郎”，从小被Marc Jacobs, Stella McCartney,&nbsp;Chloé等包围长大的小七，对各大品牌将会了如指掌。喝燕窝如喝白开水一般浸润在时尚圈里，是件多么可恨的事。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(196,'<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"韩国小姐\" src=\"http://img2.cache.netease.com/lady/2013/10/28/2013102817461950305.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t每年“韩国小姐”选拔大赛拉开帷幕都好像酝酿着一场魔术，小编就知道，话题一定不会少了。果不其然，近日一组丽们参选前的培训照片证明，造型师打扮过和没打扮的姑娘们，简直好比被易容，更有犀利网友称：死活不信这是同一个人！\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p style=\"color:#000000;text-indent:0px;\">\r\n\t<span></span>&nbsp;\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t<b>拜托！哪个能做到打扮不打扮差不多？</b> \r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t<b></b> \r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"韩国小姐\" src=\"http://img2.cache.netease.com/lady/2013/10/28/2013102817540990bf2.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t这位11号选手，衣着最平庸、脸部也毫无秀丽可言，这是就是她最最原生态的样子。但，通过魔术师一般的造型师之手可就瞬息万变了，名媛范儿的衣着搭配，秀丽的脸部轮廓，瞬间从邋遢范提升到白富美！\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p style=\"color:#000000;text-indent:0px;\">\r\n\t<br />\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"韩国小姐\" src=\"http://img4.cache.netease.com/lady/2013/10/28/20131028175412c5cac.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t再看看这位13号饼“脸胖阿姨”，朴素的衣着和没有精气神的发型让她缺乏韵味，而“易容”后的她却让人震惊，这个魔术也太神奇了吧！鹅黄深V连衣裙让气色与贤淑味儿跃然而出。\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p style=\"color:#000000;text-indent:0px;\">\r\n\t<br />\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"韩国小姐\" src=\"http://img3.cache.netease.com/lady/2013/10/28/2013102817541484297.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t终于看到一个还算相似的了，虽然易容前后仍然颇为不同，但至少满足了人们的一个夙愿：好歹打扮前也能看，打扮后更好看。这本就是一场美女视觉盛宴，可千万要实话实说哦！\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p style=\"color:#000000;text-indent:0px;\">\r\n\t<br />\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"韩国小姐\" src=\"http://img6.cache.netease.com/lady/2013/10/28/201310281754176abde.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t飞到云端接下来就荡到谷底，虽然已然吐槽了一路，但这位20号佳丽让小编已经无力吐槽。如果真的确定这是一个人的话，那评语只能是：基因微错乱了。<span></span> \r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"韩国小姐\" src=\"http://img2.cache.netease.com/lady/2013/10/28/20131028175641fc334.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t把所有佳丽朋友的“原生态”造型放在一起来欣赏，这真是一件需要忍耐的事儿啊！上一届比赛时就有人说，韩国小姐全图是在玩连连看么，只能靠着发型分辨佳丽的不同，有时她们的发型和穿着都傻傻分不清楚啊！\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p style=\"color:#000000;text-indent:0px;\">\r\n\t<br />\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t<b>各地选美有特色 港姐比基尼最耐看泰国姐姐雌雄难辨</b> \r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"韩国小姐\" src=\"http://img4.cache.netease.com/lady/2013/10/28/201310281759082f83e.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t2013年香港小姐从海选开始就引发众人的热议。因为选手的素质太过参差，甚至有龅牙粗腿的“佳丽”亮相选美现场。如今，到了20强的角逐，虽然与之前的素质有了很大的改观，这个时候重点就凸现出来了，论港姐大赛，那是比基尼最好看，不过今年的港姐冠军可木有胸……\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p style=\"color:#000000;text-indent:0px;\">\r\n\t<br />\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"韩国小姐\" src=\"http://img4.cache.netease.com/lady/2013/10/28/2013102817591084cc8.jpg\" />&nbsp;\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t还记得《泰囧》中电梯美女“电梯里有俩2货”的经典台词么？这部电影捧红了泰国“美女”Rose，美艳动人的Rose实则也是人妖。网友们借此联想到，泰国小姐这20位佳丽中，有多少本是男儿身？想到这，网友不禁猜想佳丽们的“男女比例”究竟是什么。所以这就决定了泰国小姐大赛的特点：雌雄同体。\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t<br />\r\n</p>\r\n<br class=\"Apple-interchange-newline\" />');--end
 INSERT INTO tq_article_txt VALUES(197,'<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:1px solid #C9C8C8;\" alt=\"圣安德鲁斯：重游威廉与凯特邂逅之地\" src=\"http://img1.cache.netease.com/catchpic/8/8F/8F59D1F377B3AC570CD9180AC6C31B62.jpg\" border=\"0\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;这座城市有16000名居民，其中大多数是学生。他们就好像生活在一只气泡中，与外界隔绝。大学校长在欢迎词中曾告诉大家，当他们的学业即将结束时，每十个学生里就有一个会与自己的同窗成亲。但对凯特来讲，校长当时的这番话丝毫没有提到，她要嫁的那位同学拥有皇家血统。\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t与不列颠王室主题有关的童话故事总是那么令人着迷，而凯特和威廉王子之间的浪漫情缘始于一片充满魅力的天地。圣安德鲁斯堪称营造氛围的理想之地：城内有三条主要街道，而它的数座高尔夫球场拥有好几百年历史，古老情韵深深渗透进了城市之中，浸润着中世纪的大学校舍，还有海边的城堡遗迹。学生和高尔夫球手就是此地的主角。而这两种角色居然跨越了将近600年历史在这里相遇。因为圣安德鲁斯不仅设有苏格兰最古老的大学，还是高尔夫运动的诞生之地。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(198,'<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"永定土楼，光阴里的闽地“围”生活\" src=\"http://img4.cache.netease.com/travel/2013/10/28/201310281434568b393_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<span style=\"color:#000000;\">&nbsp;</span> \r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t去福建，龙岩的永定土楼是不能不去的，去厦门永定土楼也是不能不去的。这座距离厦门大约200多公里的福建特色建筑，让我喜欢至极。这是我第一次来永定洪坑，也是第一次走进土楼。然而，憾时间太短，留给我去近距离触摸它的时间太短；遗憾没能登上土楼的高层居高临下的感受他的“围”。\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:0px;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"永定土楼，光阴里的闽地“围”生活\" src=\"http://img5.cache.netease.com/travel/2013/10/28/20131028143514f43d0_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:0px;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"永定土楼，光阴里的闽地“围”生活\" src=\"http://img1.cache.netease.com/travel/2013/10/28/2013102814361639455_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t永定土楼是世界上独一无二的神奇的山区民居建筑，是中国古建筑的一朵奇葩。2008年7月，成功列入世界遗产名录。它历史悠久、风格独特，规模宏大、结构精巧。\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:0px;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"永定土楼，光阴里的闽地“围”生活\" src=\"http://img3.cache.netease.com/travel/2013/10/28/2013102814363416052_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t土楼分方形和圆形两种。龙岩地区共有著名的圆楼360座，著名的方楼4000多座。庆云楼就是一组保存完整的巨大方土楼。可惜，留个我们的时间并不多，这里仅仅是我们路过地。\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:0px;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"永定土楼，光阴里的闽地“围”生活\" src=\"http://img6.cache.netease.com/travel/2013/10/28/20131028143651aed9c_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t进入振成楼仰看楼顶，三只红色灯笼让背后的建筑变得更有气势。土楼的气势不仅仅在福建，不仅仅在中国，更在世界里。1995年它的建筑模型与北京天坛作为中国南北圆形建筑代表参加了美国落杉矶世界建筑展览会，引起了轰动，被誉为“东方建筑明珠”。\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p style=\"color:#000000;text-indent:0px;\">\r\n\t<br />\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:0px;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"永定土楼，光阴里的闽地“围”生活\" src=\"http://img3.cache.netease.com/travel/2013/10/28/20131028143724352e3_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:0px;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"永定土楼，光阴里的闽地“围”生活\" src=\"http://img3.cache.netease.com/travel/2013/10/28/20131028143727374df_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t客家土楼建筑闪耀着客家人的智慧，它具有防震、防火、防御多种功能，通风和采光良好，而且冬暖夏凉。此外，几乎每个土楼都会有很大的院子，院子内外都可以用来晾晒东西。\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:0px;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"永定土楼，光阴里的闽地“围”生活\" src=\"http://img2.cache.netease.com/travel/2013/10/28/20131028143746976e3_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t去皮的柿子，晒过之后，再经过炭炉烘烤，味道会变得特别鲜美。\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:0px;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"永定土楼，光阴里的闽地“围”生活\" src=\"http://img5.cache.netease.com/travel/2013/10/28/2013102814380429d6b_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t它的结构还体现了客家人世代相传的团结友爱传统。再振成楼上住了有几户人家。在过去，几百人住在同一幢大屋内，朝夕相处，和睦共居的场景也时常见。这体现了客家人淳朴敦厚的秉性。\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:0px;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"永定土楼，光阴里的闽地“围”生活\" src=\"http://img4.cache.netease.com/travel/2013/10/28/2013102814382557ec2_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t一进入土楼，你立即就能感觉到那种深沉的历史感和温和的气氛。\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:0px;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"永定土楼，光阴里的闽地“围”生活\" src=\"http://img3.cache.netease.com/travel/2013/10/28/2013102814384397621_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t土楼群落里的小店。\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:0px;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"永定土楼，光阴里的闽地“围”生活\" src=\"http://img5.cache.netease.com/travel/2013/10/28/20131028143903bc98f_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t旅游攻略：\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t1. 永定客家土楼民俗文化村景区（洪坑）门票联票价格：90.00元（包括振成楼、奎聚楼、福裕楼、如升楼等核心景点）\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t2. 振福楼景区（南溪）门票：50.00元（包括振福楼、衍香楼、环极楼）\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t3. 初溪土楼群门票：70.00元（包括集庆楼、绳庆楼、善庆楼、庚庆楼、共庆楼、博物馆）\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t4. 高头镇高北村土楼群门票：50.00元（包括承启楼、世泽楼与五云楼等代表建筑）\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t5. 振成楼门票：50.00元（包括振成楼、奎聚楼、福裕楼、如升楼等核心景点）\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t6. 承启楼门票：30.00元（包括承启楼、世泽楼与五云楼等代表建筑）\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p style=\"color:#000000;text-indent:0px;\">\r\n\t<br />\r\n</p>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:0px;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"永定土楼，光阴里的闽地“围”生活\" src=\"http://img2.cache.netease.com/travel/2013/10/28/201310281442393ab61_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:0px;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"永定土楼，光阴里的闽地“围”生活\" src=\"http://img5.cache.netease.com/travel/2013/10/28/2013102814424254fad_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:0px;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"永定土楼，光阴里的闽地“围”生活\" src=\"http://img2.cache.netease.com/travel/2013/10/28/20131028144244512e9_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:0px;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"永定土楼，光阴里的闽地“围”生活\" src=\"http://img1.cache.netease.com/travel/2013/10/28/20131028144247e7557_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>\r\n<p align=\"center\" class=\"f_center\" style=\"color:#000000;text-indent:0px;\">\r\n\t<img style=\"border:0px currentColor;\" alt=\"永定土楼，光阴里的闽地“围”生活\" src=\"http://img1.cache.netease.com/travel/2013/10/28/2013102814424917811_550.jpg\" /> \r\n</p>\r\n<div align=\"left\" style=\"color:#000000;\">\r\n\t&nbsp;\r\n</div>');--end
 INSERT INTO tq_article_txt VALUES(199,'<p style=\"color:#000000;text-indent:2em;\">\r\n\t北欧风格的客厅装修，小清新最爱简约。一些比较有北欧风格的客厅，加上一些开放式的饭厅等，简洁大方，是他们风格的一向作风，很喜欢，可以作为现代家居装修时参考运用。\r\n</p>\r\n<p align=\"center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" src=\"http://img3.tbcdn.cn/tfscom/T1USnvXcXpXXXXXXXX_620x10000.jpg\" />&nbsp;\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t对于时尚现代的年轻人来说，简洁实用的榻榻米是最近几年比较受欢迎的装修新宠了，除了能满足日常休闲储物的功能，每当有客人到来时它还可以是一张宽大舒适的床哦!\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p style=\"color:#000000;text-indent:0px;\">\r\n\t<br />\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t一、粉蓝沙发 不拥挤的客厅\r\n</p>\r\n<p align=\"center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" src=\"http://img3.tbcdn.cn/tfscom/T1d5HwXXljXXXXXXXX_620x10000.jpg\" />&nbsp;\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t北欧风格设计貌似不经意的搭配之下，一切又如浑然天成般光彩夺目。任何一个空间，总有一个视觉中心，而这个中心的主导者就是色彩。\r\n</p>\r\n<p align=\"center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" src=\"http://img4.tbcdn.cn/tfscom/T1NuzwXc4kXXXXXXXX_620x10000.jpg\" />&nbsp;\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t同一类色阶的深浅搭配，没有太多的家具，仅具备了生活的必须，然而每一处精心设计的绿意，占据了室内的最佳角度，让生活充满了无限活力。\r\n</p>\r\n<p align=\"center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" src=\"http://img4.tbcdn.cn/tfscom/T1qfPvXoVjXXXXXXXX_620x10000.jpg\" />&nbsp;\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t整个客厅的采光很好，让人的心情也随之阳光起来。色彩的运用使客厅和过道浑然天成。简单的布置，沙发，茶几，电视满足了最基本的闲暇娱乐时光。电视柜上的布帘，随意摆放的靠垫，都选择了大花型图案，顿时打破了空间的单一感。\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p style=\"color:#000000;text-indent:0px;\">\r\n\t<br />\r\n</p>\r\n<p align=\"center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" src=\"http://img4.tbcdn.cn/tfscom/T1ydvxXbReXXXXXXXX_620x10000.jpg\" />&nbsp;\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t整套房间的布置随处可见木质装饰品和绿色植物，仿佛嗅到了春天泥土的芳香。另外，墙面上一些孩童照片，以及墙面上生机勃勃的手绘绿色藤蔓植物，充满了柔美之感。\r\n</p>\r\n<p align=\"center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" src=\"http://img4.tbcdn.cn/tfscom/T1upbxXnXfXXXXXXXX_620x10000.jpg\" />&nbsp;\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t博大的白，无任何倾向性的色，是北欧简约风中最常见的颜色。用这样简单的中性色作为空间主调，即使家居整体没有艳丽色彩的点缀，然而加入了些许闪亮的饰品，也能打造出绝佳的品质。\r\n</p>\r\n<p align=\"center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" src=\"http://img2.tbcdn.cn/tfscom/T17o_wXgJgXXXXXXXX_620x10000.jpg\" />&nbsp;\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t客厅一角，即被做成餐厅，墙面延伸了客厅背景墙的藤蔓花纹;略带欧式风格装饰的四方桌为餐桌，用红砖砌成的隔断墙面将空间完美的分割开来。\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p style=\"color:#000000;text-indent:0px;\">\r\n\t<br />\r\n</p>\r\n<p align=\"center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" src=\"http://img1.tbcdn.cn/tfscom/T1dwPwXoliXXXXXXXX_620x10000.jpg\" />&nbsp;\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t简约的白色餐桌上点缀着略带传统感觉的餐巾和茶具，体现出主人不一样的生活品味。\r\n</p>\r\n<p align=\"center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" src=\"http://img1.tbcdn.cn/tfscom/T1hpvwXatnXXXXXXXX_620x10000.jpg\" />&nbsp;\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t开放式的厨房设计让空间感觉更宽阔，在做菜在同时还能和家人一起交流聊天让原本单调的厨房顿时也趣味盎然。\r\n</p>\r\n<p align=\"center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" src=\"http://img2.tbcdn.cn/tfscom/T1c6vwXitiXXXXXXXX_620x10000.jpg\" />&nbsp;\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t在靠近阳台的走廊小道，主人精心挑选了一款简约镂花的隔断，让空间的层次更感丰富精致!仔细看地面的仿古地砖也别具一格的印着朵朵旺开的玫瑰。\r\n</p>\r\n<p align=\"center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" src=\"http://img3.tbcdn.cn/tfscom/T1WR6wXcphXXXXXXXX_620x10000.jpg\" />&nbsp;\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p style=\"color:#000000;text-indent:0px;\">\r\n\t<br />\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t这个角落是女主人的最爱，这里有她最爱的书籍杂志和毛茸茸的公仔们，每当工作忙碌了一天之后这里就是最好的放松之地了。\r\n</p>\r\n<p align=\"center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" src=\"http://img3.tbcdn.cn/tfscom/T1WR6wXcphXXXXXXXX_620x10000.jpg\" />&nbsp;\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t这个角落是女主人的最爱，这里有她最爱的书籍杂志和毛茸茸的公仔们，每当工作忙碌了一天之后这里就是最好的放松之地了。\r\n</p>\r\n<p align=\"center\" style=\"color:#000000;text-indent:2em;\">\r\n\t<img style=\"border:0px currentColor;\" src=\"http://img2.tbcdn.cn/tfscom/T1j8DxXjhXXXXXXXXX_620x10000.jpg\" />&nbsp;\r\n</p>\r\n<p style=\"color:#000000;text-indent:2em;\">\r\n\t冬日的午后，来这里小憩，享受玻璃窗洒下的大片阳光，温暖宜人，一本好书、一杯好茶、一曲好音乐便是半天。\r\n</p>\r\n<br class=\"Apple-interchange-newline\" />');--end
 INSERT INTO tq_article_txt VALUES(200,'<p style=\"text-align:center;color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t<img width=\"550\" height=\"328\" style=\"border:0px currentColor;\" alt=\"资料图\" src=\"http://src.house.sina.com.cn/imp/imp/deal/61/7c/0/c328276feb1e7ab14972e2d054c_p1_mk1.jpg\" /><br />\r\n<span class=\"img_wrapper_img_descr\">资料图</span>\r\n</p>\r\n<span style=\"color:#333333;background-color:#FFFFFF;\">  彭博首席经济学家迈克尔·麦克唐纳，分享了一张中国房价同比涨幅的图表，并且附言道：</span><span style=\"color:#000000;\"></span>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  “我在飞往上海的航班上与大家分享：下面这张通俗易懂的图表，很可能会让中国的决策层头痛不已。”\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  目前的情况是，在中国的一线大城市，有钱你也不一定买得到房。今年第三季度，中国房地产市场的销量同比增长放缓至21.2%，上一个季度的数据是32.4%，同期住房投资也在减速，新的房地产建设项目预计会在在未来几个月放缓。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <b>法国兴业银行经济学家姚伟指出：</b>\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  “短期内中国一线大城市房源紧张的局面不会改变。”\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  为了调控快速上涨的房价，北京市出台了七项新的法规，又被俗称为“京七条”，为的是让北京的中低收入人群有机会买得起自住型商品房。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  据财新网报道，所谓自住型商品房，北京市住建委副主任王荣武解释为“四限”，即限价：自住型商品房销售均价比同地段、同品质商品房低30%左右；限户型：套型建筑面积以90平方米以下为主；限转让：原则上5年内不得转让；限购：名下无房或仅有一套住房的北京户籍家庭，和在京连续5年以上缴纳社会保险或纳税的非北京户籍人士可购买。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  但是要有效解决中国一线大城市的房价泡沫问题，“京七条”这样的政策还远远不够，姚伟指出：\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  “在政策方面，我们看不出有什么针对房地产市场的重大紧缩动作，只有银行在收缩住房抵押贷款的规模。很多针对房价泡沫的长效政策，如房产税、土地改革、强化保障性住房的资金来源等，仍然进展缓慢。”\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  根据统计局最新公布的9月份70个大中城市住宅销售价格变动情况，65个城市新房价格环比上涨，69个城市同比上涨，环比最高涨幅为1.9%，同比最高涨幅为20.6%。北京、上海、广州、深圳涨幅均超过20%，创下2011年1月以来的最高涨幅。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(201,'<p style=\"text-align:center;color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t<img width=\"550\" style=\"border:0px currentColor;\" src=\"http://src.house.sina.com.cn/imp/imp/deal/29/36/3/57ad122cc17269c9f55c6f1caeb_p1_mk1.jpg\" /> \r\n</p>\r\n<p style=\"text-align:center;color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t万达董事长王健林\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 10月27日，在合肥几条主干道的路灯上，随处可见万达文化旅游城的广告标牌。这天是合肥万达城的奠基仪式。继哈尔滨、南昌、青岛之后，万达的文化旅游产业棋局今年落定第四个项目。为此，他们再掷350亿。乐居深度报道汇总》》\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 就在前一天，为了赶上当晚的球赛，王健林特意嘱咐下属将媒体采访前移。当他从面见当地政府人士的贵宾室转到采访间时，闪光灯瞬间爆发，统一着正装的下属们前呼后拥，让这位首富先生显得范儿十足——这段时间，他一直活跃在公众视野里。最近的十天内他带领万达跑步收编，收购了北京与湖北的两家旅行社。作为此前网友热议青岛“土豪明星盛宴”的幕后推手，现在，他再借合肥万达城释放对文化旅游业的野心。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 他的意向是在国内做满10个万达城，最终辐射全国，主题乐园、酒店、电影依然是主要元素。而选定合肥更多是基于万达广场在此的不错收益。据他们的竞争对手监测，合肥万达广场月均销售额多达260余万元。<b>在安徽，王健林的目标是让万达成为第一个千亿投资企业。</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 但全国四个项目、近1400亿的总投资额让许多人认为万达正在冒险。在采访中，王健林说每一个万达城经营期至少10年。加上资金回流，在项目的前几年，万达至多每年投入50—60亿。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; “如果我判断文化旅游业是回报率非常低的行业，那我就不会做了。”大概多年雷厉风行的军人作风也影响了王健林的语言风格。在采访中，他声音虽低，却简洁有力。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 王健林认为，中国正经历迅猛城市化进程，他选定的城市人口每年都有少则几十万的递增。这预示着文化旅游业还有更大的成长空间，仅主题公园中国就可以有数百个。“文化旅游业不缺需求，缺有效产品的供给。”他说。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 一些数据支撑了他的判断。<b>现在万达院线税后回报率超10个百分点。今年夏天，万达几个度假区的平均酒店入住率达85%，他预计未来至少还会提升10%到15%。</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 强调差异化依然是万达制胜文化旅游业的秘籍。不同于迪斯尼，万达城60%—70%的项目都在室内。在合肥万达城的开工典礼上，大队人马打起安徽凤阳花鼓助阵——除了吸纳蜚声国际的合作伙伴，万达也结合城市当地文化特点打造项目。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 这位商业大佬也关注政策空间。对于多年房地产调控，王健林认为不算成功，在越来越多刚需面前，仅打击房价很难取得成效。在他眼里，即便建立长效机制也难以同时取悦官员、商人与百姓。“解决房子的问题对世界各国政府都是高难度考题，做的好的极少。”王健林说。他还倡导两年后非核心城市都无需限购。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 现在，王健林拥有的文化、旅游、商业帝国为他带来了86亿美元的身家。<b>他声称自己感到幸福是因为一直在逐梦——他要把万达带到世界500强企业前100名的位置上。</b>截至今年上半年，万达集团收入745.1亿元，同比增长33%。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 在采访中，他总结了自己的底气来源：幸运，因为做生意赶上了商业大发展的时代。持续努力，脚踏实地，尤其在创造了万达独有商业模式的基础上。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 记者：万达在安徽的布局是什么？为什么会选择在合肥打造文化旅游城？它在万达整个战略中处于一个怎样的位置？</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 王健林：安徽是中部大省，万达在安徽的布局很大，现在已经是第六个项目，还没有包括已经在签约的。总部署已接近10个。我们在安徽发展的特点是双赢，每一个项目都重视。<strong>我希望万达成为在安徽的第一个千亿投资企业。</strong>现在的目标应该很快会达到。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 在合肥有两个原因。第一是合肥自身发展的规模，这七八年以来，合肥进入了高速增长期。从人口不到100万，到现在已是370万市区人口，城市每年增长30万人，速度惊人。而且合肥的城市骨架拉的也特别开，这种发展规模和态势吸引了我。第二就是我们在合肥这些年的投资效益都超出了预期，包括政府服务的效率和环境也不错。这是一个非常大的动力。合肥包河区是万达唯一一个既有万达广场，又有万达旅游区的地方，万达城里还有七八家酒店。再加上高铁时代到来，合肥成了交通枢纽，沪陇线，京广线，好多高铁都在这里交汇，合肥又居于中部，从全国各地过来时间都比较短。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 记者：在安徽，近的有芜湖方特，远的有上海迪斯尼，万达城在这两者之间有什么特点能吸引人过去？这个项目怎么考虑安徽本土的文化，有没有可能被复制？</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 王健林：迪斯尼在内地只有一个，还没开出来。我跟你说一个数据，美国3亿人口，现在有270几个主题公园，在美国人流最多的主题公园并不是迪斯尼，是六旗主题公园，它的旅游公司比迪斯尼要多得多。<b>我喜欢用数据分析来支持我的判断，来投资我的决策，我觉得中国是可以支撑至少一百个、甚至数百个主题公园这种旅游规模的。但是如何做到大家都能生存？就是差异化。</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 每一个项目如果都是一个模式、一个内容就坏了。等我十个项目完全布局成功后，会引导大流，想好一个完整的产业链，这样一圈就来了，可以派生成很多盈利点，我们是有很成熟的思考的，要做到每一个项目都独具当地文化特色。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 明年3、4月份，合肥的主题公园设计就完成了，可以戴眼镜看3D。我们有两个电影娱乐科技的项目，是专门请美国的高手做的。还有目前中国第一个第四代的室内水公园，是最创新的上下两层，目前还没有别人在做，这就是特色。我们现在正在创新，要不然我这个投资就麻烦了，吸引人的程度就降低了。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t<br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 合肥的主题公园还涉及概念性旅游，我们不会跟别人一样，而是以湿地项目为主。安徽的冬天也是很麻烦的，夏季有雨季，我们考虑任何一个万达城，大概60%到70%的项目都是在室内进行的，这个就是跟迪斯尼最大的差异化。<b>不瞒你说，我在无锡可能要投资更大一个项目，我的目标就是奔着迪斯尼去的，我想用我们万达的事实来证明，中国人做的旅游项目可以和美国所谓知名品牌抗衡，我们将来就比两个核心指标——旅游人次、旅游收益。</b>2015年底迪斯尼就开园了，我们无锡的项目大概是在2016年底前后，所以大概2017、2018年就会有准确的数据出来，看谁的指标更高。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><b>记者：这个项目会如何利用巢湖得天独厚的自然资源，在生态环保方面有没有一些考虑？</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 王健林：我们在巢湖边上利用了原来的一个小水系，挖了一个十几公顷的湖，做了五六层的度假型酒店，秀也是在湖边布置的。这条河目前水质不太好，在进入布置前要做一个水处理，我们对设计公司下的指标就是要达到三类水标准。它很安全，比巢湖现在的水要好的多，环保就是这么处理的。这七八个度假酒店我们还做了近两公里长的人造沙滩，这样大家可以有海边度假的感觉。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 另外，我们的广场、酒店、旅游设施，全部按照国家住建部绿色一星标准规划设计，万达几年前内部就有强性指标，任何项目拿不到绿色一星的认证，他们就要受处罚，而且更重要的不是设计认证，还有运营认证，这个更难。现在我们拿的设计认证大概是运营认证的3倍左右。运营认证是要求运行到两年才能评估，可能还有一个制度时间。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><b>记者：万达最近跑步收编，在短短十天内连续收购了北京和湖北的两家旅行社。万达对旅游产业链的布局是什么？</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t<strong></strong><br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 王健林：这个去年就定了，我们是希望做全产业链。我们不动产的前期设计归建设部门、管理部门管，这样会产生更多比较效应。做旅游也是这样，现在做平台，还需要旅行社支撑。除此之外，有自己营收更好，不排除将来会有航空领域。这些做通以后，对旅游产业影响是非常大的。收购旅行社仅仅是开始，凡是投资大的文化旅游城的地区我们都会考虑，定位区域优势，而且旅行社的投资资金对万达来讲我觉得是可以忽略不计的。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 记者：在短短一年内，从哈尔滨、南昌、青岛到合肥，这是第四个万达城，你们投资了1400亿。巨额投资下怎么保证集团资金方面的运转？</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 王健林：合肥项目总投资350亿。我们内部给这个项目的经营期是10年，但是可能会轮流在三、四年完成，房地产价值会提升。哈尔滨的经营期也是10年，青岛是12年，我们任何一个项目经营期至少达到10年。不能简单的把总投资就作为当年的投资，这是极其错误的概念，总投资是在这个项目完成之内的所有投资额相加。如果合肥要400亿，前几年投资大，可能每年投资50、60亿就够了。而且企业拿出一部分资金，还可以获得一部分贷款，我们还有房地产预售，这几个相加。很多人习惯把我们宣布各地的总投资相加，说今年投了2000亿，现金从哪来？我觉得相加的这个人简直不会用自己的脑袋思考这个问题，不是简单的把总投资相加就等于当年的投资。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 记者：万达文化产业的转型越来越明朗，到2020年商业地产的收入占比会到50%以下，但文化产业的资金回流与增长都相对缓慢。这种转型会给万达带来哪些风险？</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 王健林：这个问题的论点首先就是错误的。现在很多学者分析说文化旅游回报率低，这是不对的，你不能用文化事业的眼光来分析文化产业，若从这个角度确实养了很多剧团，效率很低，需要国家财政负担。但是就文化产业本身，比如国家搞电影院线这几年都是亏损，民营企业搞就发展很快，如果亏损怎么会支撑每年四千块平米的速度。所以我说2017、2018年中国电影市场会超过北美，2022、2023年会是北美的两倍，就是基于每年的增长。简单说<b>万达院线税后回报率百分之十几，怎么能说文化产业就一定不挣钱？</b>我给你举一个例子，一个小小的张家界，这个地级市里有三台节目在演，其中投资个把亿的节目两年就收回投资。如果我判断文化旅游业是回报率非常低的行业那我就不会做了。文化旅游对我来讲第一首先是一个没有天花板的行业，它的增长是无极限的；第二我也认为它是一个回报率很理想的行业。我们度假区今年夏天的平均酒店入住率85%，今年我预计至少还会再提升10%到15%的百分点。现在十一黄金周拥堵的状况说明，中国消费者已经升级到需要文化旅游的状况了，我的看法是<b>文化旅游行业不缺需求，缺有效产品的供给</b>，所以我是充满信心的对它进行投资的。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><b>记者：为了解决项目资金的问题，有没有考虑成立民营银行？</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 王健林：没有这个计划。现在银行赚钱是垄断造成的，假如真正放开利润就平均化了，而且银行也是高风险行业，我觉得我在现有的不动产、文化、旅游，这几个产业可以做得足够大，而且效益也足够好，所以我暂时不会考虑去做民营银行，我不认为做民营银行就一定比我做其他的挣的多。如果许可权不放开，全国就那么几家，那还能挣钱。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 记者：所有的万达城都会有持有物业和可售物业的配比，配比情况如何？您曾说未来大部分城市可能都不会再调控了，为什么？</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 王健林：配比没有准确的计算过。比方说合肥的项目投资两百亿，回收期是十到十五年，财务会有风险，会通过适度的开发写字楼、商住楼稀释一部分。每一个项目要因地制宜，合肥项目销售面积不多，可能我们的持有率比别的城市要大，因为它在省政府旁边，土地资源较紧缺。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 我说两年后无需调控的是非核心城市，北上广这些城市不一样，它可能有全球性的购房者、投资人。除此之外的很多城市我觉得无需限购，现在有些地方房子价格卖的都比较低了，商业物业的投资基本是城市化到百分之七八十。现在在美国，城镇化是94%，购物中心每年都在减少。如果中国城镇化率达到80%左右，我估计不动产的过程也会结束。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 记者：您的意思是北上广的房地产市场将来需求还会增长，不会像三四线城市那样出现供大于求的局面。可中国人在一线城市似乎有很多虚假需求，如果这类投资性虚假需求大大压缩，会不会导致房地产市场会很快供过于求？</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <b>  <br />\r\n</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 王健林：认为北上广或像合肥、长沙这样的二线城市的需求是投资撑起来的，这个看法是完全错误的。最近十年来北京城市人口净增750万，上海这十年净增800万，广州每年大概新增60万人，合肥平均每年新增30万人。如果有10万人买房，就相当于北京当年供应的总量。许多所谓专家说北上广的房地产是虚假的，可能马上要崩盘，是因为根本不看这些数字。现在中国就是一个由农村到小城，从中城到特大城市的城市化进程。且现在北上广全都限购。北京去年购房95%是首次购房，现在还打击这个行为。所以我说我们现在的房地产需求不能简单的说都是泡沫，房地产市场基本还是以刚性需求为主，特别在限购的城市里。十年九调控，加了这么多政策，现在还是在增长，这是刚沫了。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 记者：近五年房地产调控不断加码，这给房地产行业带来哪些质变？您对长效机制有什么看法或期待？</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 王健林：我觉得总体看，这些年调控不算成功。首先调控的定位发生了偏差，把它定成控制房价上涨，而不是定位在其他方面，你做更大的努力效果也不好。中国正在城市化快速形成当中，中国经济体也是向着世界第一大经济体发展，人民还在增加，你却压着房价上涨。如果每个政府致力于每年建多少公租房、经适房，怎么建立一套体系，商品市场如何发展，可能比这个调控要好的多。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t<br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 至于建立长效机制，因为它还没出来，我不好评价。我看也很难有什么机制能够做到大家都满意。不要去希望哪个人出几招，就成为领导、商人、百姓都满意的高招。这个招基本上出不来。因为解决房子问题对世界各国政府都是高难度考题，做的好的极少。新加坡做的好，50%到60%的人是住在政府的房里，他们感觉不到痛苦，但原因是它人少。总的说来解决好这个问题很困难，我不主张像有些经济学家或者学者那样寄希望于建立长效机制，觉得出台以后中国就没有房地产的难题了，大家住房的痛苦指数一下子就降低了。这个问题是一个极其复杂的社会问题，牵扯到多方利益，难度也非常大。其实只要做的好一点点，就应该给予肯定，不要想着做到圆满才是做好。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><b>记者：根据福布斯的数据，您现在已经有86亿美元的净资产，成为中国首富。请您分享一下您的财富观。作为中国最富有的人，您感觉幸福吗？</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 王健林：幸福观看怎么看，我觉得我是幸福的，因为我在追求我自己的梦想，我在逐梦当中，每一次离梦越近，我都感觉特别欣慰。所以我觉得很幸福。有的人可能觉得吃一顿好的也很幸福，买一套房子很幸福，但是我现在的幸福价值观跟别人不太一样。我经常跟我的同仁讲，我们是非常幸运的一批人，第一是时代的幸运，早三十年，晚三十年没戏了，早三十年没有拼搏的可能性，晚三十年你发展不出来；第二个是我们自己本身很幸运。是做生意，而不是去从政，也不是搞科研，我正好赶上了商业大发展时代；再一个我们的商业模式也幸运，我们正好创造了这种商业模式，现在又有一种新的商业模式，把这几个结合在一起，以我们的平台、发展速度，如果再坚持努力几年，就可能真的创造一个全世界一流的超级企业出来。这就是我的幸福观。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t<br />\r\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 第二说到财富观，这个真的很难分享。你说我怎么去教那些创业者像我这样呢？太困难了，我觉得就脚踏实地。我们今年已经推了一个创业计划，就是每年拿五千万，十年拿五个亿。也不光拿钱，在万达所有的广场上，每一个都拿两到三个门店来支持年轻的朋友，创业成功率我估计90%以上。因为如果失败了，那个广场的总经理就麻烦了，他一定要帮那个年轻人出主意，他只要做了别人就不会再招商了，要确保他有成功率。如果卖饺子，这个商城就不会再卖饺子。我觉得这么创业，这个人干一个店干的好，也不排除还可以干第二个，第三个，慢慢做。除此之外，我还在想，我这个模式最大的问题是不能复制到全国，只有万达一个品牌，我们能不能想一个新的模式，一个便于复制的创业计划。尽可能多的扶持青年人，特别是大学生能创业。当然创业究竟能走多远，那还要看多种因素。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(202,'<p style=\"text-align:center;color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t<img width=\"520\" height=\"389\" title=\"北京国土局拍卖现场\" style=\"border:0px currentColor;\" alt=\"北京国土局拍卖现场\" src=\"http://src.house.sina.com.cn/imp/imp/deal/0f/c5/4/33caa52b6c8603a61096e5e27f7_p1_mk1.jpg\" /><br />\r\n<span class=\"img_wrapper_img_descr\">北京国土局拍卖现场</span> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <b>新浪乐居讯(编辑 宋云龙 邱瑟)</b><span class=\"Apple-converted-space\">&nbsp;</span>2013年9月4日，经过43轮竞拍，恒大以40.4亿元配建51500平方米公租房摘得朝阳区豆各庄乡B地块，这也是北京第一块以“限房价竞地价”的方式出让土地。据了解，该地块剩余居住用途建筑规模建设的商品住房销售限价为22000元/平方米。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  此次朝阳区豆各庄乡土地储备项目B地块、C地块二类居住、公建混合住宅、托幼、环保设施用地(配建公共租赁住房)拍卖共吸引了全国9家知名房企到场参与竞拍，其中包括：融创、中海、住总首开联合体、恒大、合景泰富、富力、中铁建、招商、葛洲坝。最终恒大以40.4亿配建51500平方米公租房力压群雄摘得该地块，溢价率为，折合楼面价13713元/平方米，如剔除配建的公租房，该地块的楼面价达15453元/平方米。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  值得一提的是，这也是2013年恒大在北京摘得的第二宗土地，此前7月3日恒大以35.6亿元将沙河地块收入囊中，至此恒大已经在京拿地76亿元整，可谓来势汹汹。豆各庄地块的40.4亿元成交价，也是2013年第三高的成交价，此前仅有玉渊潭乡和昌平区东小口镇地块突破40亿元大关。在国土局的公告中显示，该地块剩余居住用途建筑规模建设的商品住房销售限价为22000元/平方米，套型建筑面积全部为90平方米以内，单套商品住房总价控制在200万元以内，这也是北京在6月公布“限房价竞地价”政策以来，首宗以此法出让的土地。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  公开资料显示，北京市朝阳区豆各庄乡土地储备项目B地块、C地块二类居住、公建混合住宅、托幼、环保设施用地建筑控制规模294605平方米，地块位于朝阳区豆各庄乡黄厂村，用地性质为R2二类居住用地、F2公建混合住宅用地、R53托幼用地、U44环保设施用地。该地块的起始价为24.5亿元，在竞拍前共收到8份报价。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <b>限房价、竞地价：</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  名词解释：在土地出让之前的限制条件中，就约定了入市的价格，开发商根据自己成本和合理利润竞争土地价格。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  相比其他土地出让方式，这种出让方式，不仅可以限制出让价格，也不可能出现高溢价土地。对商品房住宅销售市场以及土地市场都有明显的调整作用。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(203,'<p style=\"text-align:center;color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t<img width=\"520\" height=\"389\" title=\"北京市国土局交易大厅现场人头攒动\" style=\"border:0px currentColor;\" alt=\"北京市国土局交易大厅现场人头攒动\" src=\"http://src.house.sina.com.cn/imp/imp/deal/4d/ae/d/c08c1a362fa474641f6ef5e0408_p1_mk1.jpg\" /><br />\r\n<span class=\"img_wrapper_img_descr\">北京市国土局交易大厅现场人头攒动</span>\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t &nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><b>新浪乐居讯(编辑 邱瑟 宋云龙)</b><span class=\"Apple-converted-space\">&nbsp;</span>2013年9月4日，北京国土局现场，经过69轮拍，融创以21亿元配建27.8万平方米医院面积一举拿下农展馆北路8号住宅地块！经计算农展馆土地溢价率为16%，楼面价为35501元/平方米，考虑到配建的医院成本，<b>楼面价高达73099元/平米，成为北京名副其实的新地王。</b>\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <b>8家房企“逐鹿”东三环绝版好地</b>\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  今天到场参与竞拍的8家房企分别是：住总、首成、华发、和裕、懋源、合景泰富、融创、中海。虽然没有孙河乡地块或房山理工大地块拍卖时那样数量众多，但面对这块东三环的绝版好地众房企也都虎视眈眈。此前，新浪乐居记者曾实地走访过农展馆地块，相比去年火速拆迁时的一片狼藉，现在的土地已经十分平整，空旷的地块上仅剩几颗树木显得格外安静，让人格外期待究竟谁能够入驻。记者从地块工作人员了解到，从二次挂牌开始后，每天都有2~3批房企来看地，甚至有房企豪言一定要将该地块“收入囊中”。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  今天的拍卖开始不到10分钟，经过9轮就由融创以21亿元达到合理价格上限。转而进入配建医院面积的竞拍，经过60轮配建面积竞拍，融创以配建27.8万平方米亿元面积力压群雄成为农展馆地块新主人。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  北京土地储备中心官网信息显示，截止9月3日已收到8次报价，最高价为18.7亿元。北京市朝阳区农展馆北路8号位于东三环东侧，原北京军区总医院东院旧址上。该地块四至范围是：东至规划枣营西一路东红线，南至国土证(国军陆朝国用(2007划)01344号)边界，西至全国农业展览馆现状围墙，北至国土证(国军陆朝国用(2007划)01344号)边界。农展馆地块其优越的地理位置，成为近期北京土地市场的焦点。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <b>楼面价达73099元/平米造北京新地王</b>\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  随着主持人一声锤落，北京国土局二层大厅也炸开了，“27.8万平，这肯定是北京新地王了”很多现场的房企代表和媒体都在议论着。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  根据标书，农展馆地块建筑面积为59152平方米，达到合理价格上限后，将转为竞报异地建设医院面积的方式确定竞得人。其规定异地建设医院的建造单价为8000元/平米，竞得人需按照现场竞报异地建设医院的面积向军区总医院支付相应的建设费用。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  最终经过69轮竞拍，融创以21亿元加异地配建27.8万平方米医院面积如愿摘得农展馆8号地，以8000元/平米的建造单价计算，融创将支付给军区总医院22.24亿元的建设费用，因此，融创拿地总成本已达43.24亿元。以此计算，农展馆地块最终楼面价高达73099元/平方米，成为北京新科单价地王。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  2013年北京已接连拍出夏家胡同和孙河乡两块地王，本次农展馆地块73099元的楼面价使得“地王”的桂冠再度易主。据了解，2012年11月27日，该地块就曾挂牌入市，成为准地王的候选。但同年12月19日，地块出让因故暂停。2013年8月2日，该地块重新入市，相比之前的起价20亿，新挂出的地块降低起价到18亿，但这丝毫没有影响农展馆地块成为北京新“地王”。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <b>融创不嫌贵 和裕很遗憾</b>\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  融创负责人对新浪乐居表示：“价格在意料之中可接受，要以发展眼光看，绝版地块符合企业战略路线。”新浪乐居记者在国土局前方了解到，拍卖过程中，和裕与最终拿地的融创竞争十分激烈，和裕现场的拍地负责人在竞拍结束后，也遗憾地对记者表示：“农展馆地块非常稀缺，上市一块就少一块，我们非常希望能够拿它，但是已经超过我们的测算价格上限，所以很遗憾。”\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <b>楼面价突破7万 突破市场想象力</b>\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  中原地产市场研究部总监张大伟分析认为：“如果按照7.3万的楼面价计算，叠加建安、管理、税费、资金成本。成本则起码高达10万，而如果剔除其中不可销售部分，入市的成本价已经达到12万以上。可以说这一价格已经全面超过北京在售的所有物业。”\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t&nbsp;&nbsp;&nbsp;&nbsp;“按照我们的算法，剔除10%的公建，其实这块地的楼面价已经达到8万/平，未来要入市，绝对不会低于16万元每平，而且在我们看来，3环内的房价，20万元/平米指日可待。”合生霄云路8号一位中层领导向新浪乐居透露，在拍地之前，融创曾经到其项目做过市调，开发商对于这块地价的估算价值就在7万元左右。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  此外，目前全国最大的医院是协和医院，总建筑面积是49万平米，因此本次拍卖的配建医院面积非常巨大。这也显示出了北京土地市场的火热，2013年整体土地出让金已经达到了1098亿。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(204,'<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  在今年的“金九银十”来临之前，楼市在8月已经呈现出超乎以往的热度。据中原地产市场研究中心统计数据显示：8月，全国54城市新建住宅合计签约套数达25.2万套，环比上涨6.4%。这也是最近4个月的最高点。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  不管是住宅成交，还是土地成交，今年的“金九银十”无疑是在一片“涨”声中拉开序幕。在北京房地产市场躁动初起的同时，全国主要城市的整体房价也在持续上涨。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  值得注意的是，在一片普涨的态势之下，温州、芜湖等城市却要靠政策“松绑”来救市。而同时，郑州、南京等城市还在进一步收紧调控措施。楼市是否走到了十字路口？多地城市调控政策“一松一紧”的态势意味着什么？\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <b>供应增加暂难降楼市高温 调控两级分化或倒逼长效机制出台</b>\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  中原地产市场研究部统计数据显示：今年前8月，北京新建住宅(剔除保障房)合计签约59039套，总建筑面积成交701万平方米，均为最近5年来的第二高点。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  根据今年8月北京百盘价格动态调查结果显示，100个重点楼盘中，仅有4个楼盘降价，降价项目多为尾盘出清。而在北京等地楼市出现价格上涨的同时，部分城市的调控政策却出现了“一松一紧”的态势。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  “中国楼市已经出现了事实上的冰火两重天：一二线城市呈量价齐上涨，而三四线城市则出现了库存积压，卖地难。”中原地产市场研究部总监张大伟在接受《每日经济新闻》记者采访时表示，在预计长效机制暂不出台的情况下，各地调控也将呈现两级分化。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <span class=\"Apple-converted-space\">&nbsp;</span><b>新建商品住宅持续火爆</b>\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  “现在不找关系根本排不上号。”这是今年以来，记者在北京商品房市场听到最多的一句话。同时，多位业内人士在同记者的闲聊中提到，周围的亲戚朋友都在托关系买房。“现在的状况应该说是前所未有，大家一致对后市看涨。”一位开发商人士称。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  需求旺盛助推了成交火爆，加之难以缓解的供需矛盾，北京的“日光盘”更是频频出现。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  以中建国际港为例，7月推盘当日共推出了1462套房源，但共有3813组购房者参与摇号。中建国际港的热销仅仅只是一个开始，7月中下旬以来，陆续有6个项目出现“日光”。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  根据今年8月北京百盘价格动态调查结果显示，100个重点楼盘中，仅有4个楼盘降价，降价项目多为尾盘出清。此外，34个项目出现价格上涨，最高涨幅高达25%。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  “新建商品住宅市场持续火爆的原因，一方面是具备购房资格的需求依然庞大；另一方面，也与当前的政策环境相关。”链家地产市场研究部张旭对记者表示。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  “从当前项目排号、预售的情况看，供需矛盾有加深的迹象。”如张旭所言，楼市供应在8月份已经出现了上涨，但对于需求旺盛的北京来说，依然是“僧多粥少”。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  来自亚豪机构的月度数据显示，8月份北京开盘的28个项目累计新增套数6503套，仅次于7月份7592套的供应高峰。7、8月份北京楼市累计新增供量达1.4万套，已接近今年二季度1.7万套的推盘量。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  即便如此，从整体供需对比而言，8月份楼市供应维持在6500套，而成交却达8708套。“供应的高位仍然不挡供需矛盾的加大，其根本原因在于房企与政府、房企与市场两个矛盾的集中爆发。”亚豪机构副总经理高姗认为，前两年土地供应的成色不足导致房企对土地极度渴求，土地市场的供需矛盾虽然在今年有所缓解，但短期内仍然难以收效，直接导致了地价的飙升；另一方面，高地价托高了房价的上涨空间。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <b>地价高烧推高房价预期</b>\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  抢地潮的袭来直接导致了房价上涨的预期。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  8月22日，位于北京房山长阳的理工大学9号地被万年基业收入囊中。万年基业以15.74亿元总价加上配建4.2万平方米限价房创房山区域新地王，溢价达49.9%。至此，房山楼面价突破2万元，“面粉”价赶超“面包”价的情况再次出现。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  “房山都要卖到3万元了，其他区域怎么可能不涨。”北京一位开发商告诉《每日经济新闻》记者，该公司即将在9月入市的项目此前预计的销售价格一直保持在3.5万元/平方米，但房山拍地之后，也在酝酿涨价。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  地价高烧的不仅是北京。8月26~28日，广州、厦门、上海多宗地块成交，溢价均超过了100%，最高达到185%。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  统计数据显示，2013年8月，10个典型城市土地出让金收入为811.6亿元，与2012年同期相比增长近1.5倍，较2011年同期增长31.3%。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  购地金额居高不下反映的是今年以来房企资金面的宽裕。7月份10家标杆房企权益购地金额412亿元，为2009年以来的第三高位，仅次于2009年9月的434亿元和12月的473亿元。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  “如果下半年房地产政策面依然维持现状，标杆房企量价齐升的势头将继续保持。土地市场方面，一二线城市的优质地块仍将是标杆房企争夺的焦点。”张大伟表示，房企对后市集体看多，对土地的渴求空前。从整体市场走势来看，土地作为房地产市场的唯一原料，高地价明显刺激房价上涨。“争抢激烈的土地市场会很快将价格信息传递到住宅交易市场，楼市房价在‘金九银十’依然将维持上涨的趋势。”\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  &nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><b>刚需刚改房源金九齐发力<br />\r\n</b>\r\n</p>\r\n<table align=\"center\" bordercolor=\"#f0f0f0\" style=\"color:#333333;font-family:&quot;sans serif&quot;, tahoma, verdana, helvetica;border-collapse:collapse;\" bgcolor=\"#000000\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\">\r\n\t<tbody>\r\n\t</tbody>\r\n</table>\r\n<table align=\"center\" class=\"ke-zeroborder\" style=\"width:450px;height:200px;color:#333333;font-family:&quot;sans serif&quot;, tahoma, verdana, helvetica;border-collapse:collapse;background-color:#FFFFFF;\" border=\"0\">\r\n\t<tbody>\r\n\t\t<tr>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FF0000;\">\r\n\t\t\t\t<div align=\"center\">\r\n\t\t\t\t\t<span style=\"color:#FFFFFF;\"><b>项目名称</b></span>\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FF0000;\">\r\n\t\t\t\t<div align=\"center\">\r\n\t\t\t\t\t<span style=\"color:#FFFFFF;\"><b>价格</b></span>\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FF0000;\">\r\n\t\t\t\t<div align=\"center\">\r\n\t\t\t\t\t<span style=\"color:#FFFFFF;\"><b>主力户型</b></span>\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FF0000;\">\r\n\t\t\t\t<div align=\"center\">\r\n\t\t\t\t\t<span style=\"color:#FFFFFF;\"><b>最新动态</b><br />\r\n</span>\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"center\">\r\n\t\t\t\t\t北京华侨城\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3 STYLE1\" style=\"border:1px solid #000000;text-align:center;background-color:#FFFFFF;\">\r\n\t\t\t\t均价40500元/平米\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;text-align:center;background-color:#FFFFFF;\">\r\n\t\t\t\t<p style=\"color:#333333;\">\r\n\t\t\t\t\t170、180平米三居，286平米四居\r\n\t\t\t\t</p>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t预存3万总房款减5万，预存4万总房款减8万\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"center\">\r\n\t\t\t\t\t金隅翡丽铂爵郡\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div class=\"STYLE1\" style=\"text-align:center;\">\r\n\t\t\t\t\t<i>&nbsp;</i>预计39000元/平米\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;text-align:center;background-color:#FFFFFF;\">\r\n\t\t\t\t<p style=\"color:#333333;\">\r\n\t\t\t\t\t155-170平阔景大三居\r\n\t\t\t\t</p>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"left\" class=\"STYLE1\">\r\n\t\t\t\t\t电梯洋房，低密社区，临多条地铁公交线路\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"center\">\r\n\t\t\t\t\t丽景长安\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"center\" class=\"STYLE1\">\r\n\t\t\t\t\t待定\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3 STYLE1\" style=\"border:1px solid #000000;text-align:center;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t<p id=\"jsCon\" style=\"color:#333333;\">\r\n\t\t\t\t\t\t  136-196平米3居\r\n\t\t\t\t\t</p>\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;text-align:left;background-color:#FFFFFF;\">\r\n\t\t\t\t预计9月底开盘，房源为精装修\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"center\">\r\n\t\t\t\t\t金域公园\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;text-align:center;background-color:#FFFFFF;\">\r\n\t\t\t\t待定\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;text-align:center;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t85-95平米二三居\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"left\" class=\"STYLE1\">\r\n\t\t\t\t\t9月1日排卡启动样板间开放\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"center\">\r\n\t\t\t\t\t鑫苑·鑫都汇\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"center\" class=\"STYLE1\">\r\n\t\t\t\t\t<div style=\"text-align:center;\">\r\n\t\t\t\t\t\t预计均价18000-20000元/平米\r\n\t\t\t\t\t</div>\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3 STYLE1\" style=\"border:1px solid #000000;text-align:center;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t80-110平方米二三居\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"left\" class=\"STYLE1\">\r\n\t\t\t\t\t7月24日售楼处盛大开放，预计10月份入市\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t</tbody>\r\n</table>\r\n<br class=\"Apple-interchange-newline\" />');--end
 INSERT INTO tq_article_txt VALUES(205,'<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  在今年的“金九银十”来临之前，楼市在8月已经呈现出超乎以往的热度。据中原地产市场研究中心统计数据显示：8月，全国54城市新建住宅合计签约套数达25.2万套，环比上涨6.4%。这也是最近4个月的最高点。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  不管是住宅成交，还是土地成交，今年的“金九银十”无疑是在一片“涨”声中拉开序幕。在北京房地产市场躁动初起的同时，全国主要城市的整体房价也在持续上涨。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  值得注意的是，在一片普涨的态势之下，温州、芜湖等城市却要靠政策“松绑”来救市。而同时，郑州、南京等城市还在进一步收紧调控措施。楼市是否走到了十字路口？多地城市调控政策“一松一紧”的态势意味着什么？\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <b>供应增加暂难降楼市高温 调控两级分化或倒逼长效机制出台</b>\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  中原地产市场研究部统计数据显示：今年前8月，北京新建住宅(剔除保障房)合计签约59039套，总建筑面积成交701万平方米，均为最近5年来的第二高点。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  根据今年8月北京百盘价格动态调查结果显示，100个重点楼盘中，仅有4个楼盘降价，降价项目多为尾盘出清。而在北京等地楼市出现价格上涨的同时，部分城市的调控政策却出现了“一松一紧”的态势。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  “中国楼市已经出现了事实上的冰火两重天：一二线城市呈量价齐上涨，而三四线城市则出现了库存积压，卖地难。”中原地产市场研究部总监张大伟在接受《每日经济新闻》记者采访时表示，在预计长效机制暂不出台的情况下，各地调控也将呈现两级分化。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <span class=\"Apple-converted-space\">&nbsp;</span><b>新建商品住宅持续火爆</b>\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  “现在不找关系根本排不上号。”这是今年以来，记者在北京商品房市场听到最多的一句话。同时，多位业内人士在同记者的闲聊中提到，周围的亲戚朋友都在托关系买房。“现在的状况应该说是前所未有，大家一致对后市看涨。”一位开发商人士称。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  需求旺盛助推了成交火爆，加之难以缓解的供需矛盾，北京的“日光盘”更是频频出现。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  以中建国际港为例，7月推盘当日共推出了1462套房源，但共有3813组购房者参与摇号。中建国际港的热销仅仅只是一个开始，7月中下旬以来，陆续有6个项目出现“日光”。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  根据今年8月北京百盘价格动态调查结果显示，100个重点楼盘中，仅有4个楼盘降价，降价项目多为尾盘出清。此外，34个项目出现价格上涨，最高涨幅高达25%。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  “新建商品住宅市场持续火爆的原因，一方面是具备购房资格的需求依然庞大；另一方面，也与当前的政策环境相关。”链家地产市场研究部张旭对记者表示。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  “从当前项目排号、预售的情况看，供需矛盾有加深的迹象。”如张旭所言，楼市供应在8月份已经出现了上涨，但对于需求旺盛的北京来说，依然是“僧多粥少”。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  来自亚豪机构的月度数据显示，8月份北京开盘的28个项目累计新增套数6503套，仅次于7月份7592套的供应高峰。7、8月份北京楼市累计新增供量达1.4万套，已接近今年二季度1.7万套的推盘量。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  即便如此，从整体供需对比而言，8月份楼市供应维持在6500套，而成交却达8708套。“供应的高位仍然不挡供需矛盾的加大，其根本原因在于房企与政府、房企与市场两个矛盾的集中爆发。”亚豪机构副总经理高姗认为，前两年土地供应的成色不足导致房企对土地极度渴求，土地市场的供需矛盾虽然在今年有所缓解，但短期内仍然难以收效，直接导致了地价的飙升；另一方面，高地价托高了房价的上涨空间。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <b>地价高烧推高房价预期</b>\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  抢地潮的袭来直接导致了房价上涨的预期。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  8月22日，位于北京房山长阳的理工大学9号地被万年基业收入囊中。万年基业以15.74亿元总价加上配建4.2万平方米限价房创房山区域新地王，溢价达49.9%。至此，房山楼面价突破2万元，“面粉”价赶超“面包”价的情况再次出现。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  “房山都要卖到3万元了，其他区域怎么可能不涨。”北京一位开发商告诉《每日经济新闻》记者，该公司即将在9月入市的项目此前预计的销售价格一直保持在3.5万元/平方米，但房山拍地之后，也在酝酿涨价。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  地价高烧的不仅是北京。8月26~28日，广州、厦门、上海多宗地块成交，溢价均超过了100%，最高达到185%。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  统计数据显示，2013年8月，10个典型城市土地出让金收入为811.6亿元，与2012年同期相比增长近1.5倍，较2011年同期增长31.3%。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  购地金额居高不下反映的是今年以来房企资金面的宽裕。7月份10家标杆房企权益购地金额412亿元，为2009年以来的第三高位，仅次于2009年9月的434亿元和12月的473亿元。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  “如果下半年房地产政策面依然维持现状，标杆房企量价齐升的势头将继续保持。土地市场方面，一二线城市的优质地块仍将是标杆房企争夺的焦点。”张大伟表示，房企对后市集体看多，对土地的渴求空前。从整体市场走势来看，土地作为房地产市场的唯一原料，高地价明显刺激房价上涨。“争抢激烈的土地市场会很快将价格信息传递到住宅交易市场，楼市房价在‘金九银十’依然将维持上涨的趋势。”\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  &nbsp;<span class=\"Apple-converted-space\">&nbsp;</span><b>刚需刚改房源金九齐发力<br />\r\n</b>\r\n</p>\r\n<table align=\"center\" bordercolor=\"#f0f0f0\" style=\"color:#333333;font-family:&quot;sans serif&quot;, tahoma, verdana, helvetica;border-collapse:collapse;\" bgcolor=\"#000000\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\">\r\n\t<tbody>\r\n\t</tbody>\r\n</table>\r\n<table align=\"center\" class=\"ke-zeroborder\" style=\"width:450px;height:200px;color:#333333;font-family:&quot;sans serif&quot;, tahoma, verdana, helvetica;border-collapse:collapse;background-color:#FFFFFF;\" border=\"0\">\r\n\t<tbody>\r\n\t\t<tr>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FF0000;\">\r\n\t\t\t\t<div align=\"center\">\r\n\t\t\t\t\t<span style=\"color:#FFFFFF;\"><b>项目名称</b></span>\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FF0000;\">\r\n\t\t\t\t<div align=\"center\">\r\n\t\t\t\t\t<span style=\"color:#FFFFFF;\"><b>价格</b></span>\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FF0000;\">\r\n\t\t\t\t<div align=\"center\">\r\n\t\t\t\t\t<span style=\"color:#FFFFFF;\"><b>主力户型</b></span>\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FF0000;\">\r\n\t\t\t\t<div align=\"center\">\r\n\t\t\t\t\t<span style=\"color:#FFFFFF;\"><b>最新动态</b><br />\r\n</span>\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"center\">\r\n\t\t\t\t\t北京华侨城\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3 STYLE1\" style=\"border:1px solid #000000;text-align:center;background-color:#FFFFFF;\">\r\n\t\t\t\t均价40500元/平米\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;text-align:center;background-color:#FFFFFF;\">\r\n\t\t\t\t<p style=\"color:#333333;\">\r\n\t\t\t\t\t170、180平米三居，286平米四居\r\n\t\t\t\t</p>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t预存3万总房款减5万，预存4万总房款减8万\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"center\">\r\n\t\t\t\t\t金隅翡丽铂爵郡\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div class=\"STYLE1\" style=\"text-align:center;\">\r\n\t\t\t\t\t<i>&nbsp;</i>预计39000元/平米\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;text-align:center;background-color:#FFFFFF;\">\r\n\t\t\t\t<p style=\"color:#333333;\">\r\n\t\t\t\t\t155-170平阔景大三居\r\n\t\t\t\t</p>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"left\" class=\"STYLE1\">\r\n\t\t\t\t\t电梯洋房，低密社区，临多条地铁公交线路\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"center\">\r\n\t\t\t\t\t丽景长安\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"center\" class=\"STYLE1\">\r\n\t\t\t\t\t待定\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3 STYLE1\" style=\"border:1px solid #000000;text-align:center;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t<p id=\"jsCon\" style=\"color:#333333;\">\r\n\t\t\t\t\t\t  136-196平米3居\r\n\t\t\t\t\t</p>\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;text-align:left;background-color:#FFFFFF;\">\r\n\t\t\t\t预计9月底开盘，房源为精装修\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"center\">\r\n\t\t\t\t\t金域公园\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;text-align:center;background-color:#FFFFFF;\">\r\n\t\t\t\t待定\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;text-align:center;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t85-95平米二三居\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"left\" class=\"STYLE1\">\r\n\t\t\t\t\t9月1日排卡启动样板间开放\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t\t<tr>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"center\">\r\n\t\t\t\t\t鑫苑·鑫都汇\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"center\" class=\"STYLE1\">\r\n\t\t\t\t\t<div style=\"text-align:center;\">\r\n\t\t\t\t\t\t预计均价18000-20000元/平米\r\n\t\t\t\t\t</div>\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3 STYLE1\" style=\"border:1px solid #000000;text-align:center;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"left\">\r\n\t\t\t\t\t80-110平方米二三居\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t\t<td class=\"STYLE3\" style=\"border:1px solid #000000;background-color:#FFFFFF;\">\r\n\t\t\t\t<div align=\"left\" class=\"STYLE1\">\r\n\t\t\t\t\t7月24日售楼处盛大开放，预计10月份入市\r\n\t\t\t\t</div>\r\n\t\t\t</td>\r\n\t\t</tr>\r\n\t</tbody>\r\n</table>\r\n<br class=\"Apple-interchange-newline\" />');--end
 INSERT INTO tq_article_txt VALUES(206,'<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  日前，天津市召开进一步深化国有企业改革推动会，提出国有企业未来五年改革目标，并明确未来五年改革重点，着力推动国企调整重组与资源优化配置，着力推动国企产权多元化与资产证券化，着力完善体制机制与企业管理，加快转型升级步伐，提升质量效益，促进全市经济社会持续健康发展。为进一步深入贯彻落实国有企业改革推动会议精神，天津百利机电控股集团公司董事长张文利及时部署召开会议，并下发通知，就当前深化国有企业改革工作，进一步明确任务要求。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  张文利表示：一是要认真研究未来五年(2013-2017)深化国企改革工作部署，按时间节点要求狠抓落实；二是企业要认真组织开展情况摸底调查，总部将按业务板块分类逐户进行分析，为研究总体方案提供决策依据；三是各企业要科学制定深化国有企业改革五年总体方案，提出各年度目标任务，分时间节点加快推进企业改革工作；四是认真研究产权多元化改革、精心组织对标进位、项目洽谈合作等，高起点、高标准、高质量部署今年改革工作。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  张文利相信，这个拥有完全自主知识产权的项目的研发及产业化，将极大推动机电相关行业的发展，带动相关经济的发展，将开创一片前所未有的新天地。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(207,'<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  新京报讯(记者申志民)近日，位于北京人济山庄的“最牛违建”拆除进度遭到市民质疑。市民发现自8月15日开拆以来，最底层的假山和房屋从外部看没有太大变化，怀疑房主张必清已经悄然停工。昨日，张必清否认称，违建一直按计划拆除，月底能拆完。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  楼顶假山、长廊仍在\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  “都拆除两个多月了，怎么感觉还是原来的样子，几乎未动”，昨日，北京海淀区人济山庄小区，业主曹先生指着最牛违建拆违现场说。人济山庄及附近紫竹院公园，多位市民议论，质疑拆违存在停工、怠工现象。担心拆违进程慢，等(媒体)风声过去后不了了之。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  截至昨日，“最牛违建”所在的居民楼四周仍搭着防护网。远远看去，多名穿着红色反光背心的工人在违建楼顶晃动。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  楼顶依然可见违建外形，与拆违前变化不大，顶楼四周可见枯树枯草，违建一侧长廊依然存在，周围仍有假山石紧贴顶楼楼体。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  张必清的2605房房门紧闭，与以往不同的是，房门上的城管下达的拆违通知单已不见踪影，偶尔听到楼顶嘭嘭的声音。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  昨日，人济山庄一保安称，“最牛违建”刚开拆时动静很大，最近半个月里，发现虽然有人在施工，但运输垃圾的频次降低。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  房主称外围没拆是假象\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  此前，城管部门及最牛违建主人张必清称，拆违工作已经进入尾声，本月月底能按计划拆完。但面对公众对拆违进程的质疑，城管部门称，若10月底拆不完，城管或帮忙拆违。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  “大家看到没拆，是外围的假象，其实已经快拆完了”，昨日，张必清电话中称，拆违工作依然有序进行，最近几日加快了进程，公众看到的是拆违中为防安全搭起的外部框架，内部结构的违建差不多已经拆完。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  张必清说，目前拆违现场留有大量的违建垃圾，需要向外运输。“为不影响市民日常使用电梯，往往在夜间运输垃圾，所以显得进程缓慢。”\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  张必清直言，拆违工作月底能够完工，因安全问题，不需要城管等有关部门帮忙拆违。“月底拆违完成后，违建垃圾运输和恢复，可能需要几日”，张必清称。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  ■ 讲述\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  张必清称因拆房一病不起\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  “我现在仍不忍看拆违现场”，时隔两个多月，张必清依然对违建持以深厚感情。他称，对于公众而言，这个房子是违建，对他而言，则是一个孩子。“我对它感情很深，不忍心看到拆违中惨不忍睹的样子。”\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  张必清称，因“拆违”一事，他一度生病不起。“就像一个孩子，今天拆卸掉一个胳膊，明天拆一条大腿，心里难受。”\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  张必清此前曾希望城管等有关部门手下留情，保留违建部分绿植，但被城管部门否决，要求其恢复建筑原貌。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  【事件回顾】\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  8月12日 北京人济山庄“最牛违建”被曝光，一高层建筑房顶盖别墅，假山大树俱全。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  8月13日 北京城管限期楼顶别墅15日内拆除，否则强拆；最牛违建主人张必清被媒体曝光，指其靠点穴治病。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  8月14日 最牛违建业主所涉房产被冻结。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  8月15日 开始拆除：“最牛违建”开拆，房主张必清说十天内拆完。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(208,'<p style=\"color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t  家，是我们的归宿，希望它能赏心悦目，就希望它会舒适安全，希望它环保健康。而这些希望成真是要付出众多金钱代价，面对装修的朋友都会想同一个问题，那就是怎样装修房子最省钱。\r\n</p>\r\n<p style=\"color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t  装修房子是最麻烦的事情，一般人一辈子也只遇到一次两次，所以对这一行根本不了解，什么装修费用啊，材料采购啊，洁具性能啦等等一概不清楚，所以遇到这样的事情，心里一点底也没有，不知道装修的顺序，采购的顺序。装修的成本控制，结果最后的修价格比报出的心里价位高很多。装修房子降低费用将是永恒的话题，不管你的房子的修总费用如何，只要你牚握了一些方法，你一定会省钱的。下面就随小编一起看看怎样装修房子最省钱-教你装修最省钱的方法吧。\r\n</p>\r\n<p style=\"text-align:center;color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t<img width=\"311\" height=\"370\" title=\"\" style=\"border:1px solid #000000;\" alt=\"省钱装修\" src=\"http://src.house.sina.com.cn/imp/imp/deal/33/1a/5/f238ea119f6ffbd37ff6950bd8e_p1_mk1.jpg\" /><br />\r\n<span class=\"img_wrapper_img_descr\">省钱装修</span>\r\n</p>\r\n<p style=\"color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t  控制装修费用，要从以下几个方面来着手。\r\n</p>\r\n<p style=\"color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t  <b>一、严格按设计预算来装修</b>\r\n</p>\r\n<p style=\"color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t  不要在施工过程中，听从包工头或设计师的劝告，今天换个材料，明天加个<span class=\"iHotText\" style=\"color:#004385;text-decoration:underline;\">床</span>头<span class=\"iHotText\" style=\"color:#004385;text-decoration:underline;\">柜</span>，那样装修费用会增加很多。\r\n</p>\r\n<p style=\"text-align:center;color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t<img style=\"border:1px solid #000000;\" alt=\"省钱装修\" src=\"http://src.house.sina.com.cn/imp/imp/deal/b3/39/8/18fa1a1e030f401e9ec889d3551_p1_mk1.jpg\" /><br />\r\n<span class=\"img_wrapper_img_descr\">省钱装修</span>\r\n</p>\r\n<p style=\"color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t  <b>二、从装修公司的报价和结算方面省</b>\r\n</p>\r\n<p style=\"color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t  1、看装修公司的采购明细，是不是很多本来应该装修公司掏钱买的东西，最后变成你自己花钱买了？\r\n</p>\r\n<p style=\"color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t  2、按装修合同所列的项目核算装修款，注意大项目和细节的重复付款。\r\n</p>\r\n<p style=\"color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t  3、核算实际的装修面积，要按实际的丈量面积为准，不要让装修公司按建筑面积计算。\r\n</p>\r\n<p style=\"color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t  4、保留设计师的预算单，防止设计师在工程量上和材料上做手脚。\r\n</p>\r\n<p style=\"text-align:center;color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t<img style=\"border:1px solid #000000;\" alt=\"省钱装修\" src=\"http://src.house.sina.com.cn/imp/imp/deal/78/c0/a/4f2a8b0a8ea1794e19807c5e9fd_p1_mk1.jpg\" /><br />\r\n<span class=\"img_wrapper_img_descr\">省钱装修</span>\r\n</p>\r\n<p style=\"color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t  <b>三、从购买材料上节省装修费用</b>\r\n</p>\r\n<p style=\"color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t  材料的费用占装修费用的比例很大，并且相同质量不同品牌的材料，价格相差几倍都有的，所以，材料费用的控制是比较重要的。那么如何控制装修材料的费用呢，大致从以下几步来解决：\r\n</p>\r\n<p style=\"color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t  1、每一种的装修材料都有上、中、下几个等级之分，选择好你的装修档次和装修风格，然后根据档次购买。同一品牌的材料，特别是陶瓷材料，在正常销售的情况下，质量是没多大区别的，区别大的是外观，比如马桶、瓷砖等等。特价品正常情况下也没什么问题，这些产品的价格式可以会便宜一半以上。\r\n</p>\r\n<p style=\"color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t  2、选择时间购买，你会省得更多。一般来说，家居建材市场在开业的时候，促销的力度会很大，如果在这时候购买特价品，那么整个材料费用会省很多的，其它的如五一和十一也是两大打折的主要时间，这些时候你会省差不多一半的材料费用，这里说的都是购买特价品。\r\n</p>\r\n<p style=\"color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t  3、购买材料时，可以让设计师跟着谈好款式，遇到打折的时候去买最划算。\r\n</p>\r\n<p style=\"color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t  4、为体现档次，可以采取大部分便宜小部分贵”的办法，多数的材料和做法采用便宜的，少数画龙点睛的部分采用高价位的，这样看起来会有较高的格调。\r\n</p>\r\n<p style=\"text-align:center;color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t<img width=\"500\" height=\"360\" style=\"border:1px solid #000000;\" alt=\"省钱装修\" src=\"http://src.house.sina.com.cn/imp/imp/deal/d5/49/f/2f0702e148026bb9dee6abd9a71_p1_mk1.jpg\" /><br />\r\n<span class=\"img_wrapper_img_descr\">省钱装修</span>\r\n</p>\r\n<p style=\"color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t  <b> 四、找对装修公司也是省钱和省心的方法</b>\r\n</p>\r\n<p style=\"color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t  1、大的装修公司运作的成本高，收费也偏高，如果做的是中小档次的装修可以找中小公司。\r\n</p>\r\n<p style=\"color:#000000;text-indent:0px;background-color:#F5F8FD;\">\r\n\t  2、找装修公司一定要选有资质的，千万不要直接找包工头，这样出现矛盾的事例数不胜数。那样你的成本说不定会高很多，质量也没有保障。\r\n</p>\r\n<br class=\"Apple-interchange-newline\" />');--end
 INSERT INTO tq_article_txt VALUES(209,'<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  10月28日，中国建筑股份有限公司发布第三季度报告。1-9月，该公司实现营业收入4,799亿元，同比增长21.8%。其中，三季度实现营业收入1,780亿元，同比增长26.8%。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  报告显示，前九月，中国建筑实现毛利547.7亿元，同比增长16.7%。毛利率为11.4%，同比下降0.5个百分点。实现营业利润255.8亿元，同比增长30.4%。归属上市公司股东净利润134.2亿元，同比增长25.7%。基本每股收益0.45元。期末，归属上市公司股东净资产1,115.7亿元，比年初增长9.5%。每股净资产3.72元。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  前三季，中国建筑地产业务销售额约1,129亿元，其中中海地产848亿元，中建地产281亿元(均包含了合约销售和认购销售，下同)，同比增长24.8%；销售面积947万平方米，包括中海地产660万平方米、中建地产287万平方米)，同比增长24.4%。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  另于1-9月，中国建筑建筑业务新签合同额约9,194亿元，同比增长36.2%。其中，基础设施业务新签合同额约1,370亿元，同比增长58.9%。房建业务施工面积68,559万平方米，同比增长22.8%；新开工面积18,256万平方米，同比增长31.8%；竣工面积3,510万平方米，同比增长30.1%。新增土地储备方面约853万平方米；期末拥有土地储备约6,966万平方米。\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(210,'<p style=\"text-align:center;color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t<img width=\"450\" height=\"300\" style=\"border:0px currentColor;\" src=\"http://src.house.sina.com.cn/imp/imp/deal/a9/4d/1/a04918bbfd416e356137225f183_p1_mk1.jpg\" /><br />\r\n融创中国董事长孙宏斌<span class=\"img_wrapper_img_descr\">&nbsp;</span> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  “顺驰当年跑得快，摔了一跤。融创汲取教训，已经跑出去800米了，大家还在纠缠当年摔跤是因为没吃好，还是天气原因。不知道业界为什么说我激进。”不管你是否相信，在回答媒体的质疑时，孙宏斌表露出的困惑与无辜绝不是刻意为之。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  切割当年的顺驰和现在的融创，孙宏斌表现得信心十足。今年前9个月，融创实现合约销售额超过去年全年，日前他将2013年销售目标提升至500亿元。届时，融创将跻身一线房企俱乐部，这张门票当年顺驰渴望企及，却功亏一篑。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  前度“孙郎”今又来。在销售持续攀高的同时，融创俨然成为土地市场的“土豪”。从年初开始，几乎每个月融创都是公开市场项目收购和竞拍土地的主角，9月初更是将全国楼面单价的刻度标高至7.3万元/平米。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  视觉冲击的后果是，业界开始在融创身上发现了几分顺驰的影子。不过，孙宏斌坚信自己不是孟浪之人，此融创绝非彼顺驰，他认为融创在产品能力、现金管控上进步太多。外界对融创有误解，辩解的唯一方法就是业绩。上市三年来，融创半年一次半年一次(指年中报和年报)地证明，已经有6次业绩超预期。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <b>“如果证明到十次，大家肯定会改变对我们的看法。”他说。</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <b>&nbsp;</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <em><b>“我没买过贵地，买的地都挺便宜。地价贵要看跟什么参照物去比。我在一、二线城市的最核心区段拿地，价钱当然不会低。”</b></em> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  多个城市和区段的“地王”制造者宣称自己买地还捡了便宜，在座的媒体记者不信服地笑了。这让孙宏斌离开坐席，打开PPT投影为大家解释。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  上海盛世滨江项目是融创今年在上海最大的收获，这个占据黄浦江核心江景资源的大盘体量高达67.5万平米。该项目通过上海融绿平台购入，双方分摊80亿的收购成本。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  “楼面地价不加开发税费是一万四千多，加上税费两万二到两万五，这个盘的单价是七万到八万。”无需多解释，这个项目看起来有高毛利保证。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  9月18日获取的天津天拖厂百亿地块是总价“地王”，单价万元出头。这是天津近年来出让的核心地段的巨幅地块。孙宏斌在地图上指出了近期出让的可参照地块，楼面价最低的一宗也要高出70%以上。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  在竞拍尘埃落定之前，国际大行瑞信专门发报告称，项目平均售价将达3万元，如果以底价+50%溢价(对应楼面价约1.35万元)竞得，毛利率可达31%。根据最终出价，天拖厂地块的毛利率远高于此，这将成为融创大本营市场未来的开发体量和利润支撑。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  针对亦庄地块(楼面单价2.8万元)，孙宏斌表示，项目就在1.6万亩南海子公园边上，扣除限价房面积后，商品房容积率只有0.9，具有打造稀缺景观资源低密产品的潜质。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  为了说明地价不贵，孙宏斌拿北京孙河区域做对比：“孙河组团将来有7~8家开发商在做，<b>亦庄核心区目前只有中信地产和我们两家，可以说是坐庄，竞争较少</b>。”他认为亦庄有产业支撑，禀赋要优于孙河。孙河已经成为北京“地王”的“代言”，楼面价摸高至4.6万元。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  对于万众瞩目的北京农展馆地块，孙宏斌也振振有词：“这块地是皇冠上的明珠，现场竞拍的有四家都举到了7万以上。”这幅位于东三环传统豪宅区的地块，是闹市中一处隐秘的所在。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <b>孙宏斌指着项目边上的宽阔湖面和绿地，表示农展馆整体卖到15万元问题不大，滨水的楼王户型要卖到30万元。</b>在缺水的北京，滨水而居往往是奢望。如果项目溢价能够上去，农展馆地块似乎也有很大的安全边际。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  用高端产品创造溢价，进入竞争蓝海的开发思路，在融创得到坚决贯彻。同时，孙宏斌高度关注集中拿地对公司现金流的冲击，在付款条件和现金平衡上也做了详细谋划。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  以天拖厂地块为例，103.2亿土地出让金融创和合作方天房集团按照51：49的权益分摊。但双方约定，首期土地款(51.6亿)融创支付20亿，大头明年支付；与之相反，天方集团今年支付土地款的大头。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  在盛世滨江项目上，上海融绿借用境外银团提供的并购贷款，借鸡生蛋减轻自有资金收购的压力，同时项目安排不菲的面积由上海市政府回购。<b>限价房开发企业素来不喜，但孙宏斌看到的是现金流。在亦庄项目里，限价房贡献的现金流可以平衡低密度部分的成本和开发周期，以战养战。</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <b>“宋总(绿城董事长宋卫平)评价我：碰见靠谱的我比谁都靠谱，碰见不靠谱的我比谁都不靠谱。”</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  孙宏斌和宋卫平这两个都“曾经受过伤的男人”的合作，愉快程度甚至超过了预期。2012年6月展开的股权合作涉及9个项目，345万平米土地储备。现在双方已经通过合资公司上海融绿拓展第三方项目资源。收购盛世滨江、上海花溢花城和杭州之江壹号项目，上海融绿都充当收购主体。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  双方合作的基础依然牢固，绿城的工程部门有远高出同行的话语权，融创的营销能力更为业内称道。香港资本市场一贯青睐大而美的白马公司，上海融绿由融创并表，体现在报表上的庞大收入规模将有助于其获得市场关注，提升估值并进而提高融资能力。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  孙宏斌不吝对绿城的溢美之词：“跟绿城合作之后，融创产品能力有很大的提升。如果没有产品能力，就不敢在一、二线城市的核心区段拿单价比较高的项目。”\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <b>“以前学绿城，是拍照参观，偷偷摸摸学，现在绿城的研发和工程对融创全部开放。”</b>孙宏斌透露，9月19日在苏州有一个针对合院产品的研讨会，融创的工程人员向绿城学习，准备将这一创新产品用在亦庄项目上。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  苏州御园和桃花源是双方首期合作资产包中的两个项目。这两个位于苏州金鸡湖CBD核心区的低密住宅，借用了苏州园林的造园手法，将屋宇空间与院落有机结合，创造出了合院这一创新产品形态。在谑称立几个水泥桩子都可以卖高价的北京，孙宏斌乐观认为市场将接受合院产品10万元以上的均价。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  除了绿城之外，融创的“合伙人”还包括保利、方兴、葛洲坝、九龙仓、世茂和天津泰达，其中与保利和方兴合作了多个项目。公开信息披露，2012年，融创获取742万平米土地储备，其中权益土地366万平米；在今年上半年获取的216万平米土地中，权益土储97万平米。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  引入“合伙人”，有助于降低开发风险。同时，孙宏斌认为，核心区段土地竞争激烈，引入合作可以减少竞争。从合作的深度上，融创已经成为合作平台最开放的地产公司。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <b>“现在是融创毛利率的最低点，做高端产品毛利率低是没有道理的，允许投资者对融创有不同的看法，我整天在市场上泡着，对市场有判断。”</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  国际大行德银今年来持续看空融创，已经发了数篇重磅报告，质疑的一大焦点是融创陷入毛利率下滑和负债率飙升的两难处境。融创2013年中期净负债率是高于行业平均水平的72%，毛利率下降至20.8%的新低。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <b>低廉的毛利水平与高端项目的定位形成强烈的反差。“</b>苏州御园和桃花源等融绿平台上部分高价地项目，对融创毛利率的拖累将在2015年以后变得微不足道。”孙宏斌表示。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  同时，融创毛利率低还受到财务处理手法的影响。过去几年来，项目收购成为融创项目获取的重要途径，结算成本中包含了收购项目的溢价。融创收购的项目基本已经开工，其中不少是在售项目，以评估后增值的价格计入成本，短期对毛利率的压制可想而知。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  高端项目的配套成本支出多发生在前期，随着在售资源的释放，毛利率将呈现出上场的曲线。此外，像西山壹号院项目一样，融创可以根据市况进一步收购项目中合作方的剩余股权，提升核心盈利。有国际大行做出测算，短期融创的核心盈利可以看到50亿。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  用孙宏斌的话来讲，2010年上市以来，融创每半个年度的业绩都在超预期。而通常为了上市冲业绩，上市后第二个年度盈利比较平淡。但是，2011年，凭借西山壹号院的逆势热销，融创超额完成销售目标，更为重要的是，融创坚定了在中心城市打造高端产品的定位。反映在结算均价上，从2010年的9300元/平米上升到2013年上半年的2.8万元/平米。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  <strong>从未偏离一、二线城市的融创省却了从三、四线回归的弯路，并享有业内几乎最小的管理半径。</strong>在限购限贷的楼市调整中异军突起，融创在内地销售排行榜业已排定的座次中抢占一席。\r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  融创正处在甜蜜点。就像NBA季后赛一样，获胜的一方无需率先做调整。孙宏斌注意到亦庄和农展馆拍地后，融创股价都分别有6%的调整。他不以为然<b>：“如果跟投资者一个看法，见识就拉到跟他们一个水平线上了。短期内投资者需要教育，他们不知道这些地块有多好，过一段时间就会发现我买得真好。”</b> \r\n</p>\r\n<p style=\"color:#333333;text-indent:0px;background-color:#FFFFFF;\">\r\n\t  融创是京沪资产占比最高的全国性房企之一，支撑孙宏斌的信心是，京沪今年700多万平米的供应根本满足不了市场需求，“在历史成交高峰，京沪每年都分别有2000多万平米的销售，况且北京今年700多万的住宅供应中，商品房只占到其中的一半。”\r\n</p>');--end
 INSERT INTO tq_article_txt VALUES(212,'<p style=\"text-indent:2em;\">\r\n\t5月23日新疆启动严打暴恐活动专项行动以来，全区各级公安机关闻令即动，在前期侦查、调查、摸排的基础上主动进攻、严查深挖，于5月25日凌晨实施“零点”抓捕行动，以雷霆之势迅速打掉一批暴恐团伙，抓获一批犯罪嫌疑人，缴获一批制爆工具、材料及管制刀具，对暴力恐怖活动形成“快、稳、准、狠”的严打态势。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t公安机关的抓捕行动重点针对“在网上传播暴恐音视频、宣传煽动‘圣战’人员”“有现实危害的涉恐重点人员和涉宗教极端重点人员”“多次参与危安、涉暴轻微犯罪未经处理尚未悔改人员”“今年以来参与暴恐团伙和宗教极端团伙人员”四类人员开展。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t23日以来，新疆和田、喀什、阿克苏等重点地区公安机关组织力量对“四类”人员进行梳理排查，至25日凌晨共抓获一批犯罪嫌疑人。目前，公安机关已开展进一步审讯深挖工作。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t此次抓捕的涉暴力恐怖犯罪的嫌疑人基本以80后、90后为主体，他们大多通过互联网和多媒体卡等载体观看暴恐音视频，传播宗教极端思想，学习“制爆方法”和“体能训练方式”，借助QQ群、短信、微信以及非法讲经点等交流制爆经验，宣扬“圣战”思想，密谋袭击目标等。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>据悉，5月以来，和田、喀什、阿克苏三个地区公安机关针对复杂、严峻维稳形势，严查深挖各类违法犯罪活动，共打掉23个涉恐涉爆和宗教极端犯罪团伙，抓获200余名犯罪嫌疑人，收缴200余枚各类爆炸装置。</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t新疆各地公安机关将继续重点围绕暴力恐怖和宗教极端团伙、制枪制爆窝点、恐怖训练窝点以及具有现实危害的暴力恐怖和宗教极端分子、暴力恐怖和宗教极端违法犯罪在逃人员，下重手、出重拳，坚决把暴力恐怖分子的嚣张气焰打下去。\r\n</p>\r\n<hr class=\"ke-pagebreak\" style=\"page-break-after:always;\" />\r\n<p>\r\n\t&nbsp;\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>全国开展为期一年严打暴恐专项行动</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t人民网北京5月25日电 据公安部网站消息，公安部5月25日召开严厉打击暴力恐怖活动专项行动内地部署视频会。公安部党委副书记、常务副部长杨焕宁指出，经中央批准，国家反恐工作领导小组决定，以新疆为主战场，其他省区市积极配合，开展为期一年的严厉打击暴力恐怖活动专项行动，这是有效应对当前严峻复杂的反恐怖斗争形势的迫切需要，也是全力维护社会大局稳定、确保国家长治久安的战略举措。各地公安机关要以习近平总书记关于加强反恐怖斗争一系列重要指示精神为指导，切实把思想认识统一到中央决策部署上来，在国家反恐怖工作领导小组和各级党委、政府统一领导下，高举法治旗帜、反恐怖旗帜和维护各族人民群众根本利益的旗帜，集中各方面力量，综合运用多种方式，下重手、出重拳，真正打出声威、打出实效，坚决遏制新疆暴力恐怖活动多发频发势头，坚决防止暴力恐怖、宗教极端活动向内地发展蔓延，确保全国及新疆社会大局的稳定。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t杨焕宁要求，内地公安机关要牢固树立“全国一盘棋”思想，在党委、政府的领导下，统一协调、整体推进专项行动。要加强地区之间以及与有关部门、单位的配合衔接，打好整体仗、合成仗。要正确把握、严格执行党的民族宗教政策，精准打击暴力恐怖和宗教极端犯罪分子，依法保护少数民族群众合法权益。要大力宣传党和政府打击暴力恐怖活动的鲜明立场和坚定决心，教育引导广大人民群众切实增强反恐、防恐意识。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t新疆、河南、浙江、广东、云南五省区公安厅和公安部治安局负责同志在会上发言，介绍交流了有关情况和开展专项行动的安排意见。5月23日，新疆自治区公安厅已在全疆启动了严厉打击暴力恐怖活动专项行动。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t<strong>新疆发布打击暴恐通告：30天内自首可减轻处罚</strong>\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t本报乌鲁木齐5月24日电（记者戴岚、韩立群）新疆维吾尔自治区高级人民法院、人民检察院、公安厅联合发布《关于依法严厉打击暴力恐怖活动的通告》，通告自24日起施行。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t通告明令：严禁组织、领导、参加恐怖组织，严禁实施或者煽动实施暴力恐怖活动，严禁以任何方式直接或者间接资助、支持、庇护恐怖活动、恐怖组织、恐怖活动人员；严禁制作、贩卖、运输、传播、复制、持有载有暴力恐怖、宗教极端思想内容的宣传品、移动存储介质、新型电子产品、标识及物品，严禁组织、策划、实施或者煽动实施宗教极端违法犯罪活动；严禁非法制造、买卖、运输、储存、托运、寄递、携带枪支、弹药、易燃易爆及管制刀具等危爆物品，严禁传授、传播制枪制爆技术、方法；严禁偷越国（边）境或者组织、策划、煽动、运送、协助他人偷越国（边）境。\r\n</p>\r\n<p style=\"text-indent:2em;\">\r\n\t通告称，实施上述行为的违法犯罪分子，自本通告发布之日起30日内投案自首，可争取宽大处理。凡在通告期限内主动投案自首的，依法从轻或减轻处罚；投案自首并有重大立功表现的，依法减轻或免除处罚。\r\n</p>');--end
DROP TABLE IF EXISTS tq_article_type;--end
CREATE TABLE tq_article_type
(
   type_id           INT(10) NOT NULL AUTO_INCREMENT,
   type_name           VARCHAR(20) NOT NULL COMMENT '名称',
   has_image           BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否有图片',
   is_disabled           BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否禁用',
   PRIMARY KEY(type_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_article_type VALUES(1,'普通',0,0);--end
 INSERT INTO tq_article_type VALUES(2,'图文',1,0);--end
 INSERT INTO tq_article_type VALUES(3,'焦点',1,0);--end
 INSERT INTO tq_article_type VALUES(4,'头条',0,0);--end
DROP TABLE IF EXISTS tq_category;--end
CREATE TABLE tq_category
(
   category_id           INT(10) NOT NULL AUTO_INCREMENT,
   site_id           INT(10) NOT NULL COMMENT '站点ID',
   name           VARCHAR(50) NOT NULL COMMENT '分区名称',
   priority           INT(10) NOT NULL COMMENT '序号',
   PRIMARY KEY(category_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_category VALUES(1,1,'发展建议',1);--end
DROP TABLE IF EXISTS tq_channel;--end
CREATE TABLE tq_channel
(
   channel_id           INT(10) NOT NULL AUTO_INCREMENT,
   site_id           INT(10) NOT NULL COMMENT '站点ID',
   parent_id           INT(10) COMMENT '父栏目ID',
   channel_name           VARCHAR(50) COMMENT '栏目名称',
   channel_path           VARCHAR(30) COMMENT '栏目路径',
   chnl_number           VARCHAR(100) COMMENT '栏目编号',
   priority           INT(10) NOT NULL DEFAULT '10' COMMENT '排列顺序',
   is_alone           BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否单页',
   is_show           BIT(1) NOT NULL DEFAULT b'1' COMMENT '是否显示',
   flow_id           INT(10) COMMENT '工作流ID',
   depart_id           INT(10) NOT NULL DEFAULT '1' COMMENT '添加部门',
   PRIMARY KEY(channel_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_channel VALUES(1,1,null,'新闻','news','-1-',1,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(2,1,null,'体育','sports','-2-',2,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(3,1,null,'娱乐','ent','-3-',3,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(4,1,null,'财经','finance','-4-',4,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(5,1,1,'国内','china','-1-5-',10,0,1,5,1);--end
 INSERT INTO tq_channel VALUES(6,1,2,'NBA','nba','-2-6-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(11,1,1,'国际','world','-1-11-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(12,1,1,'军事','mil','-1-12-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(13,1,null,'科技','tech','-13-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(14,1,null,'健康','health','-14-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(15,1,null,'时尚','style','-15-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(16,1,2,'足球','football','-2-16-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(17,1,2,'综合','others','-2-17-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(18,1,2,'网球','tennis','-2-18-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(19,1,2,'F1赛车','f1','-2-19-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(20,1,null,'旅游','travel','-20-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(21,1,3,'电影','film','-3-21-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(22,1,3,'电视','tv','-3-22-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(23,1,3,'音乐','music','-3-23-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(24,1,3,'明星','star','-3-24-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(25,1,13,'互联网','internet','-13-25-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(26,1,14,'保健','healthcare','-14-26-',1,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(27,1,4,'股票','stock','-4-27-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(28,1,4,'基金','fund','-4-28-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(29,1,4,'外汇','forex','-4-29-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(30,1,4,'期货','futuremarket','-4-30-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(31,1,15,'服装','clothes','-15-31-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(32,1,20,'游记','youji','-20-32-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(33,1,null,'其它','qita','-33-',10,0,0,null,1);--end
 INSERT INTO tq_channel VALUES(34,1,33,'关于我们','pl','-33-34',10,1,1,null,1);--end
 INSERT INTO tq_channel VALUES(35,1,1,'图片','photo','-1-35-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(36,1,13,'IT','it','-13-36-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(37,1,13,'家电','elec','-13-37-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(38,1,13,'探索','discovery','-13-38-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(39,1,14,'药品','medicine','-14-39-',4,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(40,1,14,'养生','yangsheng','-14-40-',2,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(41,1,14,'名医','mingyi','-14-41-',3,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(42,1,15,'饰品','shipin','-15-42-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(43,1,15,'名车','car','-15-43-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(44,1,15,'美食','foods','-15-44-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(45,1,20,'户外','huwai','-20-45-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(46,1,20,'景点','jingdian','-20-46-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(47,1,20,'特产','techan','-20-47-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(48,1,1,'视频','videos','-1-48-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(49,1,33,'网站导航','guide','-33-49-',10,1,1,null,1);--end
 INSERT INTO tq_channel VALUES(113,21,null,'新闻','news','-113-',1,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(114,21,113,'国际','world','-1-114-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(163,23,null,'新闻','news','-163-',1,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(164,23,163,'国内','china','-1-164-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(165,23,163,'图片','photo','-1-165-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(166,23,163,'军事','mil','-1-166-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(167,23,163,'视频','videos','-1-167-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(168,23,163,'国际','world','-1-168-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(169,23,null,'体育','sports','-169-',2,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(170,23,169,'NBA','nba','-2-170-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(171,23,169,'足球','football','-2-171-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(172,23,169,'网球','tennis','-2-172-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(173,23,169,'F1赛车','f1','-2-173-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(174,23,169,'综合','others','-2-174-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(175,23,null,'娱乐','ent','-175-',3,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(176,23,175,'电视','tv','-3-176-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(177,23,175,'音乐','music','-3-177-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(178,23,175,'明星','star','-3-178-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(179,23,175,'电影','film','-3-179-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(180,23,null,'财经','finance','-180-',4,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(181,23,180,'外汇','forex','-4-181-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(182,23,180,'基金','fund','-4-182-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(183,23,180,'期货','futuremarket','-4-183-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(184,23,180,'股票','stock','-4-184-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(185,23,null,'科技','tech','-185-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(186,23,185,'互联网','internet','-13-186-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(187,23,185,'IT','it','-13-187-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(188,23,185,'探索','discovery','-13-188-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(189,23,185,'家电','elec','-13-189-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(190,23,null,'健康','health','-190-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(191,23,190,'保健','healthcare','-14-191-',1,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(192,23,190,'养生','yangsheng','-14-192-',2,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(193,23,190,'名医','mingyi','-14-193-',3,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(194,23,190,'药品','medicine','-14-194-',4,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(195,23,null,'尚品','style','-195-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(196,23,195,'饰品','shipin','-15-196-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(197,23,195,'美食','foods','-15-197-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(198,23,195,'名车','car','-15-198-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(199,23,195,'服装','clothes','-15-199-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(200,23,null,'旅游','travel','-200-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(201,23,200,'户外','huwai','-20-201-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(202,23,200,'特产','techan','-20-202-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(203,23,200,'景点','jingdian','-20-203-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(204,23,200,'游记','youji','-20-204-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(205,23,null,'其它','qita','-205-',10,0,0,null,1);--end
 INSERT INTO tq_channel VALUES(206,23,205,'网站导航','guide','-33-206-',10,1,1,null,1);--end
 INSERT INTO tq_channel VALUES(207,23,205,'调查问卷测试','dcwj','-33-207-',10,1,1,null,1);--end
 INSERT INTO tq_channel VALUES(208,23,205,'广告测试','advtest','-33-208-',10,1,1,null,1);--end
 INSERT INTO tq_channel VALUES(209,23,205,'关于我们','pl','-33-209-',10,1,1,null,1);--end
 INSERT INTO tq_channel VALUES(210,23,null,'测试','testone','-210-',10,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(211,1,null,'房产','house','-211-',9,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(212,1,211,'快讯','fcnews','-211-212-',1,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(213,1,211,'家居','home','-211-213-',2,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(214,1,211,'装修','fitments','-211-214-',3,0,1,null,1);--end
 INSERT INTO tq_channel VALUES(215,1,211,'投资','fctz','-211-215-',4,0,1,null,1);--end
DROP TABLE IF EXISTS tq_channel_ext;--end
CREATE TABLE tq_channel_ext
(
   channel_id           INT(10) NOT NULL,
   link           VARCHAR(255) COMMENT '外部链接',
   tpl_channel           VARCHAR(100) COMMENT '栏目页模板',
   comment_control           BIT(1) NOT NULL DEFAULT b'1' COMMENT '是否允许评论',
   updown_control           BIT(1) NOT NULL DEFAULT b'1' COMMENT '顶踩控制',
   is_blank           BIT(1) NOT NULL DEFAULT b'1' COMMENT '是否新窗口打开',
   title           VARCHAR(100) COMMENT 'TITLE',
   keywords           VARCHAR(100) COMMENT 'KEYWORDS',
   description           VARCHAR(255) COMMENT 'DESCRIPTION',
   is_static_channel           BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否生成栏目静态页',
   is_static_doc           BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否生成文档静态页',
   is_sign           BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否需签收',
   img_src           VARCHAR(100) COMMENT '栏目图片',
   PRIMARY KEY(channel_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_channel_ext VALUES(1,null,'/doc/channel/默认栏目页.html',1,0,1,'新闻','新闻','新闻',1,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(2,null,'/doc/channel/默认栏目页.html',1,0,1,'体育','体育','体育',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(3,null,'/doc/channel/默认栏目页.html',0,1,1,'娱乐','娱乐','娱乐',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(4,null,'/doc/channel/默认栏目页.html',0,1,1,'财经','财经','财经',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(5,null,'/doc/channel/默认栏目页.html',1,0,1,'国内','国内','国内',1,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(6,null,'/doc/channel/默认栏目页.html',0,1,1,'NBA','NBA','NBA',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(11,null,'/doc/channel/默认栏目页.html',1,0,1,'国际','国际','国际',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(12,null,'/doc/channel/默认栏目页.html',1,0,1,'军事','军事','军事',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(13,null,'/doc/channel/默认栏目页.html',0,1,1,'科技','科技','科技',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(14,null,'/doc/channel/默认栏目页.html',0,1,1,'健康','健康','健康',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(15,null,'/doc/channel/默认栏目页.html',0,1,1,'时尚','时尚','时尚',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(16,null,'/doc/channel/默认栏目页.html',0,1,1,'足球','足球','足球',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(17,null,'/doc/channel/默认栏目页.html',0,1,1,'综合','综合','综合',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(18,null,'/doc/channel/默认栏目页.html',0,1,1,'网球','网球','网球',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(19,null,'/doc/channel/默认栏目页.html',0,1,1,'F1赛车','F1赛车','F1赛车',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(20,null,'/doc/channel/默认栏目页.html',0,1,1,'旅游','旅游','旅游',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(21,null,'/doc/channel/默认栏目页.html',0,1,1,'电影','电影','电影',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(22,null,'/doc/channel/默认栏目页.html',0,1,1,'电视','电视','电视',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(23,null,'/doc/channel/默认栏目页.html',0,1,1,'音乐','音乐','音乐',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(24,null,'/doc/channel/默认栏目页.html',0,1,1,'明星','明星','明星',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(25,null,'/doc/channel/默认栏目页.html',0,1,1,'互联网','互联网','互联网',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(26,null,'/doc/channel/默认栏目页.html',0,1,1,'保健','保健','保健',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(27,null,'/doc/channel/默认栏目页.html',0,1,1,'股票','股票','股票',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(28,null,'/doc/channel/默认栏目页.html',0,1,1,'基金','基金','基金',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(29,null,'/doc/channel/默认栏目页.html',0,1,1,'外汇','外汇','外汇',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(30,null,'/doc/channel/默认栏目页.html',0,1,1,'期货','期货','期货',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(31,null,'/doc/channel/默认栏目页.html',0,1,1,'服装','服装','服装',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(32,null,'/doc/channel/默认栏目页.html',0,1,1,'游记','游记','游记',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(33,null,'/alone/默认单页.html',1,0,1,'其它','其它','其它',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(34,null,'/doc/alone/默认单页.html',0,1,1,'关于我们','评论','关于我们',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(35,null,'/doc/channel/默认栏目页.html',0,1,1,'图片','图片','图片',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(36,null,'/doc/channel/默认栏目页.html',0,1,1,'IT','IT','IT',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(37,null,'/doc/channel/默认栏目页.html',0,1,1,'家电','家电','家电',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(38,null,'/doc/channel/默认栏目页.html',0,1,1,'探索','探索','探索',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(39,null,'/doc/channel/默认栏目页.html',0,1,1,'药品','药品','药品',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(40,null,'/doc/channel/默认栏目页.html',0,1,1,'养生','养生','养生',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(41,null,'/doc/channel/默认栏目页.html',0,1,1,'名医','名医','名医',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(42,null,'/doc/channel/默认栏目页.html',0,1,1,'饰品','饰品','饰品',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(43,null,'/doc/channel/默认栏目页.html',0,1,1,'名车','名车','名车',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(44,null,'/doc/channel/默认栏目页.html',0,1,1,'美食','美食','美食',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(45,null,'/doc/channel/默认栏目页.html',0,1,1,'户外','户外','户外',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(46,null,'/doc/channel/默认栏目页.html',0,1,1,'景点','景点','景点',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(47,null,'/doc/channel/默认栏目页.html',0,1,1,'特产','特产','特产',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(48,null,'/doc/channel/默认栏目页.html',0,1,1,'视频','视频','视频',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(49,null,'/doc/alone/网站地图.html',0,1,1,'网站导航','网站导航','网站导航',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(113,null,'/doc/channel/默认栏目页.html',0,1,1,'新闻','新闻','新闻',1,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(114,null,'/doc/channel/默认栏目页.html',0,1,1,'国际','国际','国际',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(163,null,'/doc/channel/默认栏目页.html',0,1,1,'新闻','新闻','新闻',1,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(164,null,'/doc/channel/默认栏目页.html',0,1,1,'国内','国内','国内',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(165,null,'/doc/channel/默认栏目页.html',0,1,1,'图片','图片','图片',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(166,null,'/doc/channel/默认栏目页.html',0,1,1,'军事','军事','军事',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(167,null,'/doc/channel/默认栏目页.html',0,1,1,'视频','视频','视频',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(168,null,'/doc/channel/默认栏目页.html',0,1,1,'国际','国际','国际',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(169,null,'/doc/channel/默认栏目页.html',0,1,1,'体育','体育','体育',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(170,null,'/doc/channel/默认栏目页.html',0,1,1,'NBA','NBA','NBA',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(171,null,'/doc/channel/默认栏目页.html',0,1,1,'足球','足球','足球',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(172,null,'/doc/channel/默认栏目页.html',0,1,1,'网球','网球','网球',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(173,null,'/doc/channel/默认栏目页.html',0,1,1,'F1赛车','F1赛车','F1赛车',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(174,null,'/doc/channel/默认栏目页.html',0,1,1,'综合','综合','综合',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(175,null,'/doc/channel/默认栏目页.html',0,1,1,'娱乐','娱乐','娱乐',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(176,null,'/doc/channel/默认栏目页.html',0,1,1,'电视','电视','电视',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(177,null,'/doc/channel/默认栏目页.html',0,1,1,'音乐','音乐','音乐',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(178,null,'/doc/channel/默认栏目页.html',0,1,1,'明星','明星','明星',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(179,null,'/doc/channel/默认栏目页.html',0,1,1,'电影','电影','电影',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(180,null,'/doc/channel/默认栏目页.html',0,1,1,'财经','财经','财经',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(181,null,'/doc/channel/默认栏目页.html',0,1,1,'外汇','外汇','外汇',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(182,null,'/doc/channel/默认栏目页.html',0,1,1,'基金','基金','基金',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(183,null,'/doc/channel/默认栏目页.html',0,1,1,'期货','期货','期货',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(184,null,'/doc/channel/默认栏目页.html',0,1,1,'股票','股票','股票',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(185,null,'/doc/channel/默认栏目页.html',0,1,1,'科技','科技','科技',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(186,null,'/doc/channel/默认栏目页.html',0,1,1,'互联网','互联网','互联网',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(187,null,'/doc/channel/默认栏目页.html',0,1,1,'IT','IT','IT',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(188,null,'/doc/channel/默认栏目页.html',0,1,1,'探索','探索','探索',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(189,null,'/doc/channel/默认栏目页.html',0,1,1,'家电','家电','家电',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(190,null,'/doc/channel/默认栏目页.html',0,1,1,'健康','健康','健康',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(191,null,'/doc/channel/默认栏目页.html',0,1,1,'保健','保健','保健',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(192,null,'/doc/channel/默认栏目页.html',0,1,1,'养生','养生','养生',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(193,null,'/doc/channel/默认栏目页.html',0,1,1,'名医','名医','名医',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(194,null,'/doc/channel/默认栏目页.html',0,1,1,'药品','药品','药品',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(195,null,'/doc/channel/默认栏目页.html',0,1,1,'尚品','尚品','尚品',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(196,null,'/doc/channel/默认栏目页.html',0,1,1,'饰品','饰品','饰品',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(197,null,'/doc/channel/默认栏目页.html',0,1,1,'美食','美食','美食',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(198,null,'/doc/channel/默认栏目页.html',0,1,1,'名车','名车','名车',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(199,null,'/doc/channel/默认栏目页.html',0,1,1,'服装','服装','服装',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(200,null,'/doc/channel/默认栏目页.html',0,1,1,'旅游','旅游','旅游',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(201,null,'/doc/channel/默认栏目页.html',0,1,1,'户外','户外','户外',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(202,null,'/doc/channel/默认栏目页.html',0,1,1,'特产','特产','特产',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(203,null,'/doc/channel/默认栏目页.html',0,1,1,'景点','景点','景点',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(204,null,'/doc/channel/默认栏目页.html',0,1,1,'游记','游记','游记',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(205,null,'/alone/默认单页.html',1,0,1,'其它','其它','其它',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(206,null,'/doc/alone/网站地图.html',0,1,1,'网站导航','网站导航','网站导航',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(207,null,'/doc/alone/问卷调查.html',1,1,1,'调查问卷测试','调查问卷测试','调查问卷测试',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(208,null,'/doc/channel/test.html',1,1,1,'广告测试','广告测试','广告测试',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(209,null,'/doc/alone/默认单页.html',0,1,1,'关于我们','评论','关于我们',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(210,null,'/doc/channel/默认栏目页.html',1,1,1,'测试','测试','测试',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(211,null,'/doc/channel/默认栏目页.html',1,1,1,'房产','房产','房产',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(212,null,'/doc/channel/默认栏目页.html',1,1,1,'快讯','快讯','快讯',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(213,null,'/doc/channel/默认栏目页.html',1,1,1,'家居','家居','家居',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(214,null,'/doc/channel/默认栏目页.html',1,1,1,'装修','装修','装修',0,0,0,null);--end
 INSERT INTO tq_channel_ext VALUES(215,null,'/doc/channel/默认栏目页.html',1,1,1,'投资','投资','投资',0,0,0,null);--end
DROP TABLE IF EXISTS tq_channel_txt;--end
CREATE TABLE tq_channel_txt
(
   channel_id           INT(10) NOT NULL,
   txt           LONGTEXT COMMENT '栏目内容',
   PRIMARY KEY(channel_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_channel_txt VALUES(34,'关于我们');--end
 INSERT INTO tq_channel_txt VALUES(209,'关于我们');--end
DROP TABLE IF EXISTS tq_chnl_group_contri;--end
CREATE TABLE tq_chnl_group_contri
(
   channel_id           INT(10) NOT NULL,
   group_id           INT(10) NOT NULL,
   PRIMARY KEY(channel_id,group_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_chnl_group_contri VALUES(1,1);--end
 INSERT INTO tq_chnl_group_contri VALUES(1,2);--end
 INSERT INTO tq_chnl_group_contri VALUES(1,3);--end
 INSERT INTO tq_chnl_group_contri VALUES(2,1);--end
 INSERT INTO tq_chnl_group_contri VALUES(2,2);--end
 INSERT INTO tq_chnl_group_contri VALUES(2,3);--end
 INSERT INTO tq_chnl_group_contri VALUES(5,1);--end
 INSERT INTO tq_chnl_group_contri VALUES(5,2);--end
 INSERT INTO tq_chnl_group_contri VALUES(5,3);--end
 INSERT INTO tq_chnl_group_contri VALUES(11,1);--end
 INSERT INTO tq_chnl_group_contri VALUES(11,2);--end
 INSERT INTO tq_chnl_group_contri VALUES(11,3);--end
 INSERT INTO tq_chnl_group_contri VALUES(12,1);--end
 INSERT INTO tq_chnl_group_contri VALUES(12,2);--end
 INSERT INTO tq_chnl_group_contri VALUES(12,3);--end
DROP TABLE IF EXISTS tq_chnl_group_view;--end
CREATE TABLE tq_chnl_group_view
(
   channel_id           INT(10) NOT NULL,
   group_id           INT(10) NOT NULL,
   PRIMARY KEY(channel_id,group_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_chnl_tpl_selection;--end
CREATE TABLE tq_chnl_tpl_selection
(
   chnl_id           INT(10) NOT NULL,
   model_id           INT(10) NOT NULL,
   tpl_doc           VARCHAR(100),
   PRIMARY KEY(chnl_id,model_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_chnl_tpl_selection VALUES(1,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(1,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(1,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(1,5,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(2,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(2,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(2,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(3,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(3,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(3,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(3,5,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(4,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(4,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(4,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(4,5,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(5,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(5,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(5,5,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(6,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(6,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(11,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(11,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(12,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(12,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(13,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(13,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(13,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(13,5,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(14,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(14,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(14,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(14,5,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(15,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(15,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(15,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(15,5,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(16,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(16,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(17,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(17,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(18,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(18,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(19,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(19,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(20,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(20,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(20,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(20,5,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(21,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(21,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(21,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(22,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(22,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(22,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(23,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(23,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(24,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(24,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(25,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(25,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(26,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(26,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(27,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(27,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(28,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(28,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(29,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(30,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(30,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(31,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(31,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(32,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(32,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(35,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(35,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(36,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(36,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(37,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(37,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(38,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(38,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(39,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(40,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(40,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(41,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(42,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(43,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(43,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(44,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(44,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(45,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(46,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(46,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(47,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(47,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(48,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(113,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(113,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(113,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(113,5,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(114,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(114,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(163,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(163,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(163,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(163,5,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(164,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(164,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(164,5,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(165,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(165,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(166,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(166,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(167,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(168,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(168,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(169,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(169,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(169,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(170,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(170,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(171,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(171,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(172,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(172,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(173,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(173,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(174,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(174,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(175,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(175,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(175,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(175,5,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(176,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(176,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(176,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(177,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(177,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(178,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(178,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(179,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(179,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(179,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(180,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(180,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(180,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(180,5,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(181,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(182,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(182,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(183,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(183,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(184,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(184,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(185,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(185,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(185,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(185,5,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(186,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(186,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(187,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(187,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(188,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(188,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(189,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(189,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(190,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(190,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(190,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(190,5,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(191,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(191,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(192,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(192,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(193,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(194,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(195,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(195,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(195,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(195,5,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(196,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(197,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(197,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(198,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(198,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(199,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(199,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(200,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(200,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(200,4,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(200,5,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(201,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(202,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(202,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(203,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(203,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(204,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(204,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(210,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(210,3,'/doc/article/图集内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(211,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(212,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(213,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(214,2,'/doc/article/默认内容页.html');--end
 INSERT INTO tq_chnl_tpl_selection VALUES(215,2,'/doc/article/默认内容页.html');--end
DROP TABLE IF EXISTS tq_comment;--end
CREATE TABLE tq_comment
(
   comment_id           INT(10) NOT NULL AUTO_INCREMENT,
   site_id           INT(10) NOT NULL COMMENT '站点ID',
   doc_id           INT(10) NOT NULL COMMENT '文档ID',
   user_id           INT(10) COMMENT '用户ID',
   parent_id           INT(10) COMMENT '评论ID',
   ups           INT(10) NOT NULL COMMENT '赞同数量',
   create_time           DATETIME NOT NULL COMMENT '评论时间',
   is_checked           BIT(1) NOT NULL COMMENT '是否审核',
   last_time           DATETIME NOT NULL COMMENT '最后回复时间',
   PRIMARY KEY(comment_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_comment VALUES(16,1,82,1,null,0,'2013-05-21 10:56:41.0',1,'2013-05-21 10:56:41.0');--end
 INSERT INTO tq_comment VALUES(17,1,112,1,null,0,'2013-05-21 10:57:38.0',1,'2013-05-21 10:57:38.0');--end
 INSERT INTO tq_comment VALUES(18,1,120,1,null,0,'2013-05-21 11:00:04.0',1,'2013-05-21 11:00:04.0');--end
 INSERT INTO tq_comment VALUES(19,1,123,1,null,0,'2013-05-21 11:02:31.0',1,'2013-05-21 11:02:31.0');--end
 INSERT INTO tq_comment VALUES(20,1,111,1,null,0,'2013-05-21 11:03:50.0',1,'2013-05-21 11:03:50.0');--end
 INSERT INTO tq_comment VALUES(26,1,212,3,null,0,'2014-05-25 22:35:06.0',1,'2014-05-25 22:49:21.0');--end
 INSERT INTO tq_comment VALUES(27,1,212,3,26,0,'2014-05-25 22:47:46.0',1,'2014-05-25 22:47:46.0');--end
 INSERT INTO tq_comment VALUES(28,1,212,3,26,0,'2014-05-25 22:49:21.0',1,'2014-05-25 22:49:21.0');--end
 INSERT INTO tq_comment VALUES(29,1,212,3,null,0,'2014-05-26 10:40:10.0',1,'2014-05-26 10:40:10.0');--end
 INSERT INTO tq_comment VALUES(30,1,211,3,null,0,'2014-05-26 10:41:20.0',1,'2014-05-26 10:41:20.0');--end
 INSERT INTO tq_comment VALUES(31,1,194,null,null,0,'2014-05-26 10:44:51.0',1,'2014-05-26 10:44:51.0');--end
 INSERT INTO tq_comment VALUES(32,1,196,null,null,0,'2014-05-26 10:45:11.0',1,'2014-05-26 10:45:11.0');--end
 INSERT INTO tq_comment VALUES(33,1,193,null,null,0,'2014-05-26 17:38:18.0',1,'2014-05-26 17:38:18.0');--end
 INSERT INTO tq_comment VALUES(34,1,193,1,null,0,'2014-05-26 17:42:36.0',1,'2014-05-26 17:42:36.0');--end
DROP TABLE IF EXISTS tq_comment_ext;--end
CREATE TABLE tq_comment_ext
(
   comment_id           INT(10) NOT NULL,
   ip           VARCHAR(20) NOT NULL COMMENT '评论IP',
   content           LONGTEXT NOT NULL COMMENT '评论内容',
   PRIMARY KEY(comment_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_comment_ext VALUES(16,'0:0:0:0:0:0:0:1','这是应得的下场，大快人心');--end
 INSERT INTO tq_comment_ext VALUES(17,'0:0:0:0:0:0:0:1','真实世界之大，无奇不有啊，这样也能行，服了，就没有相关部门管管？');--end
 INSERT INTO tq_comment_ext VALUES(18,'0:0:0:0:0:0:0:1','大猩猩现在是无敌了，还一群好队友，看好热火夺冠，库里自从蒙塔艾莉丝走后就出彩了，当只为亏的大当家，单节20分的火力让波波维奇也无奈的直摇头~');--end
 INSERT INTO tq_comment_ext VALUES(19,'0:0:0:0:0:0:0:1','当初的小贝也成老贝了，时光如梭，自然规律，没办法，只能感叹，祝福贝克汉姆，也和姚明一样做华丽转身，做大老板~');--end
 INSERT INTO tq_comment_ext VALUES(20,'0:0:0:0:0:0:0:1','在体育馆里面装模作样的，作秀呐，有本事到江里拉真船啊');--end
 INSERT INTO tq_comment_ext VALUES(26,'0:0:0:0:0:0:0:1','对恐怖分子，就是要严厉打击，维护国家统一，人民生活稳定。');--end
 INSERT INTO tq_comment_ext VALUES(27,'0:0:0:0:0:0:0:1','坚决反对恐怖行为');--end
 INSERT INTO tq_comment_ext VALUES(28,'0:0:0:0:0:0:0:1','没有人性的犯罪分子');--end
 INSERT INTO tq_comment_ext VALUES(29,'0:0:0:0:0:0:0:1','对恐怖分子，抓住后不奢望他变成好人，而是让他变成死人');--end
 INSERT INTO tq_comment_ext VALUES(30,'0:0:0:0:0:0:0:1','这样飞机跑道就多了');--end
 INSERT INTO tq_comment_ext VALUES(31,'0:0:0:0:0:0:0:1','叫美国偷窥监视他国，都不买了，赞成的顶起');--end
 INSERT INTO tq_comment_ext VALUES(32,'0:0:0:0:0:0:0:1','都一个模子里刻出来的，流水线工程');--end
 INSERT INTO tq_comment_ext VALUES(33,'0:0:0:0:0:0:0:1','互联网经融不好做了');--end
 INSERT INTO tq_comment_ext VALUES(34,'0:0:0:0:0:0:0:1','百度加油');--end
DROP TABLE IF EXISTS tq_company_fairs;--end
CREATE TABLE tq_company_fairs
(
   comfairs_id           INT(10) NOT NULL AUTO_INCREMENT,
   company_id           INT(10) NOT NULL,
   fairs_id           INT(10) NOT NULL,
   showcase           VARCHAR(20) NOT NULL,
   show_time           SMALLINT(5) NOT NULL,
   create_time           DATETIME NOT NULL,
   PRIMARY KEY(comfairs_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_company_favorite;--end
CREATE TABLE tq_company_favorite
(
   favorite_id           INT(10) NOT NULL AUTO_INCREMENT,
   company_id           INT(10) NOT NULL,
   resume_id           INT(10) NOT NULL,
   create_time           DATETIME NOT NULL,
   PRIMARY KEY(favorite_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_company_info;--end
CREATE TABLE tq_company_info
(
   company_id           INT(10) NOT NULL AUTO_INCREMENT,
   name           VARCHAR(50) NOT NULL,
   link_man           VARCHAR(30) NOT NULL,
   link_tel           VARCHAR(20) NOT NULL,
   com_nature           INT(10),
   com_scale           SMALLINT(5),
   com_industry1           INT(10),
   com_industry2           INT(10),
   is_check           SMALLINT(5) NOT NULL,
   is_commend           SMALLINT(5) NOT NULL,
   is_show           SMALLINT(5) NOT NULL,
   last_post_time           DATETIME,
   vip_type           INT(10),
   apply_type           INT(10),
   PRIMARY KEY(company_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_company_info_ext;--end
CREATE TABLE tq_company_info_ext
(
   company_id           INT(10) NOT NULL,
   address           VARCHAR(200),
   zip_code           VARCHAR(20),
   web_url           VARCHAR(50),
   email           VARCHAR(50),
   phone           VARCHAR(20),
   fax           VARCHAR(20),
   synopsis           VARCHAR(2000),
   reg_capital           INT(10),
   set_up           VARCHAR(20),
   city           VARCHAR(50),
   is_vip           SMALLINT(5) NOT NULL DEFAULT '0',
   dredge_time           DATETIME,
   vip_day           INT(10),
   apply_vip           SMALLINT(5) NOT NULL DEFAULT '0',
   apply_time           DATETIME,
   need_login           SMALLINT(5) DEFAULT '0',
   PRIMARY KEY(company_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_consul;--end
CREATE TABLE tq_consul
(
   consul_id           INT(10) NOT NULL AUTO_INCREMENT,
   article_id           INT(10) NOT NULL,
   consul_user           INT(10),
   reply_user           INT(10),
   site_id           INT(10) NOT NULL,
   title           VARCHAR(50) NOT NULL,
   create_time           DATETIME NOT NULL,
   reply_time           DATETIME,
   PRIMARY KEY(consul_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_consul_ext;--end
CREATE TABLE tq_consul_ext
(
   consul_id           INT(10) NOT NULL,
   content           VARCHAR(3000) NOT NULL,
   reply           VARCHAR(3000),
   PRIMARY KEY(consul_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_database_config;--end
CREATE TABLE tq_database_config
(
   config_id           INT(10) NOT NULL,
   inter           INT(10) NOT NULL COMMENT '备份间隔',
   backup_time           INT(10) NOT NULL COMMENT '备份时间',
   pre_time           DATETIME COMMENT '上次备份时间',
   PRIMARY KEY(config_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_depart;--end
CREATE TABLE tq_depart
(
   depart_id           INT(10) NOT NULL AUTO_INCREMENT,
   site_id           INT(10) NOT NULL COMMENT '站点ID',
   parent_id           INT(10) COMMENT '上级部门',
   flow_id           INT(10) COMMENT '当前采用的工作流',
   name           VARCHAR(50) NOT NULL COMMENT '部门名称',
   tree_number           VARCHAR(150) COMMENT '部门编号',
   visit_path           VARCHAR(30) COMMENT '独立访问路径',
   priority           INT(10) NOT NULL COMMENT '排序',
   is_show           BIT(1) NOT NULL COMMENT '前台是否显示',
   create_time           DATETIME NOT NULL COMMENT '添加时间',
   is_all_channel           BIT(1) NOT NULL DEFAULT b'0' COMMENT '所有栏目权限',
   short_name           VARCHAR(50) COMMENT '部门简称',
   PRIMARY KEY(depart_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_depart VALUES(1,1,null,null,'信息中心','-1-','',1,0,'2013-04-25 22:22:22.0',1,null);--end
 INSERT INTO tq_depart VALUES(2,1,1,null,'新闻编辑部','-1-2-','',2,1,'2013-06-17 16:03:29.0',0,'');--end
 INSERT INTO tq_depart VALUES(4,1,1,null,'新闻审核部','-1-4-','',3,1,'2013-06-21 16:34:36.0',0,'');--end
 INSERT INTO tq_depart VALUES(8,9,null,5,'信息中心','-8-','',1,0,'2013-04-25 22:22:22.0',0,null);--end
 INSERT INTO tq_depart VALUES(17,18,null,null,'信息中心','-17-','',1,0,'2013-04-25 22:22:22.0',0,null);--end
 INSERT INTO tq_depart VALUES(20,21,null,null,'信息中心','-20-','',1,0,'2013-04-25 22:22:22.0',0,null);--end
 INSERT INTO tq_depart VALUES(22,23,null,null,'信息中心','-22-','',1,0,'2013-04-25 22:22:22.0',0,null);--end
DROP TABLE IF EXISTS tq_depart_channel;--end
CREATE TABLE tq_depart_channel
(
   depart_id           INT(10) NOT NULL,
   channel_id           INT(10) NOT NULL,
   PRIMARY KEY(channel_id,depart_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_depart_channel VALUES(2,1);--end
 INSERT INTO tq_depart_channel VALUES(4,1);--end
 INSERT INTO tq_depart_channel VALUES(2,2);--end
 INSERT INTO tq_depart_channel VALUES(4,2);--end
 INSERT INTO tq_depart_channel VALUES(2,3);--end
 INSERT INTO tq_depart_channel VALUES(4,3);--end
 INSERT INTO tq_depart_channel VALUES(2,4);--end
 INSERT INTO tq_depart_channel VALUES(4,4);--end
 INSERT INTO tq_depart_channel VALUES(2,5);--end
 INSERT INTO tq_depart_channel VALUES(4,5);--end
 INSERT INTO tq_depart_channel VALUES(2,6);--end
 INSERT INTO tq_depart_channel VALUES(4,6);--end
 INSERT INTO tq_depart_channel VALUES(2,11);--end
 INSERT INTO tq_depart_channel VALUES(4,11);--end
 INSERT INTO tq_depart_channel VALUES(2,12);--end
 INSERT INTO tq_depart_channel VALUES(4,12);--end
 INSERT INTO tq_depart_channel VALUES(4,13);--end
 INSERT INTO tq_depart_channel VALUES(4,14);--end
 INSERT INTO tq_depart_channel VALUES(4,15);--end
 INSERT INTO tq_depart_channel VALUES(2,16);--end
 INSERT INTO tq_depart_channel VALUES(4,16);--end
 INSERT INTO tq_depart_channel VALUES(2,17);--end
 INSERT INTO tq_depart_channel VALUES(4,17);--end
 INSERT INTO tq_depart_channel VALUES(2,18);--end
 INSERT INTO tq_depart_channel VALUES(4,18);--end
 INSERT INTO tq_depart_channel VALUES(2,19);--end
 INSERT INTO tq_depart_channel VALUES(4,19);--end
 INSERT INTO tq_depart_channel VALUES(4,20);--end
 INSERT INTO tq_depart_channel VALUES(2,21);--end
 INSERT INTO tq_depart_channel VALUES(4,21);--end
 INSERT INTO tq_depart_channel VALUES(2,22);--end
 INSERT INTO tq_depart_channel VALUES(4,22);--end
 INSERT INTO tq_depart_channel VALUES(2,23);--end
 INSERT INTO tq_depart_channel VALUES(4,23);--end
 INSERT INTO tq_depart_channel VALUES(2,24);--end
 INSERT INTO tq_depart_channel VALUES(4,24);--end
 INSERT INTO tq_depart_channel VALUES(4,25);--end
 INSERT INTO tq_depart_channel VALUES(4,26);--end
 INSERT INTO tq_depart_channel VALUES(2,27);--end
 INSERT INTO tq_depart_channel VALUES(4,27);--end
 INSERT INTO tq_depart_channel VALUES(2,28);--end
 INSERT INTO tq_depart_channel VALUES(4,28);--end
 INSERT INTO tq_depart_channel VALUES(2,29);--end
 INSERT INTO tq_depart_channel VALUES(4,29);--end
 INSERT INTO tq_depart_channel VALUES(2,30);--end
 INSERT INTO tq_depart_channel VALUES(4,30);--end
 INSERT INTO tq_depart_channel VALUES(4,31);--end
 INSERT INTO tq_depart_channel VALUES(4,32);--end
 INSERT INTO tq_depart_channel VALUES(4,35);--end
 INSERT INTO tq_depart_channel VALUES(4,36);--end
 INSERT INTO tq_depart_channel VALUES(4,37);--end
 INSERT INTO tq_depart_channel VALUES(4,38);--end
 INSERT INTO tq_depart_channel VALUES(4,39);--end
 INSERT INTO tq_depart_channel VALUES(4,40);--end
 INSERT INTO tq_depart_channel VALUES(4,41);--end
 INSERT INTO tq_depart_channel VALUES(4,42);--end
 INSERT INTO tq_depart_channel VALUES(4,43);--end
 INSERT INTO tq_depart_channel VALUES(4,44);--end
 INSERT INTO tq_depart_channel VALUES(4,45);--end
 INSERT INTO tq_depart_channel VALUES(4,46);--end
 INSERT INTO tq_depart_channel VALUES(4,47);--end
 INSERT INTO tq_depart_channel VALUES(4,48);--end
 INSERT INTO tq_depart_channel VALUES(4,211);--end
 INSERT INTO tq_depart_channel VALUES(4,212);--end
 INSERT INTO tq_depart_channel VALUES(4,213);--end
 INSERT INTO tq_depart_channel VALUES(4,214);--end
 INSERT INTO tq_depart_channel VALUES(4,215);--end
DROP TABLE IF EXISTS tq_doc_statistics;--end
CREATE TABLE tq_doc_statistics
(
   doc_id           INT(10) NOT NULL,
   views_count           INT(10) NOT NULL,
   ups           INT(10) NOT NULL DEFAULT '0' COMMENT '赞同数',
   treads           INT(10) NOT NULL DEFAULT '0' COMMENT '反对数',
   PRIMARY KEY(doc_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_doc_statistics VALUES(3,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(4,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(5,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(6,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(7,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(8,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(9,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(10,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(11,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(12,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(13,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(14,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(15,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(16,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(17,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(18,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(19,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(20,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(21,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(22,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(23,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(24,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(25,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(26,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(27,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(28,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(29,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(30,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(32,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(33,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(34,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(35,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(36,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(37,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(38,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(39,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(40,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(41,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(42,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(43,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(44,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(45,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(46,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(47,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(48,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(49,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(50,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(51,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(52,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(53,20,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(54,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(55,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(56,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(57,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(58,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(59,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(60,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(61,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(62,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(63,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(64,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(65,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(66,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(67,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(68,941,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(69,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(71,1045,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(72,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(73,906,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(74,6,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(75,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(76,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(77,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(78,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(82,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(83,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(84,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(85,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(86,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(87,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(88,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(89,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(90,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(91,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(92,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(94,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(95,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(96,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(97,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(98,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(99,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(100,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(101,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(102,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(104,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(105,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(106,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(107,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(108,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(110,42,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(111,29,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(112,8,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(113,10,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(114,21,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(115,12,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(116,31,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(117,2,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(118,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(119,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(120,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(121,14,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(122,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(123,5,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(124,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(125,13,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(132,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(134,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(135,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(136,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(137,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(185,1,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(187,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(188,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(189,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(190,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(191,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(192,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(193,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(194,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(195,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(196,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(197,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(198,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(199,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(200,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(201,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(202,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(203,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(204,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(205,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(206,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(207,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(208,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(209,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(210,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(211,0,0,0);--end
 INSERT INTO tq_doc_statistics VALUES(212,3,0,0);--end
DROP TABLE IF EXISTS tq_economy_type;--end
CREATE TABLE tq_economy_type
(
   type_id           INT(10) NOT NULL AUTO_INCREMENT,
   type_code           VARCHAR(10) NOT NULL COMMENT '编码',
   type_name           VARCHAR(50) NOT NULL COMMENT '名称',
   PRIMARY KEY(type_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_edu_exp;--end
CREATE TABLE tq_edu_exp
(
   edu_id           INT(10) NOT NULL AUTO_INCREMENT,
   resume_id           INT(10) NOT NULL COMMENT '简历ID',
   special_id           INT(10) NOT NULL COMMENT '专业ID',
   start_time           VARCHAR(30) NOT NULL COMMENT '开始时间',
   end_time           VARCHAR(30) NOT NULL COMMENT '截止时间',
   school           VARCHAR(50) NOT NULL COMMENT '所在学校',
   educational           SMALLINT(5) NOT NULL COMMENT '学历',
   degree           SMALLINT(5) COMMENT '学位',
   is_oversea           SMALLINT(5) NOT NULL COMMENT '是否有海外经历',
   description           VARCHAR(2000) COMMENT '描述',
   create_time           DATETIME NOT NULL COMMENT '创建时间',
   PRIMARY KEY(edu_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_evaluate;--end
CREATE TABLE tq_evaluate
(
   evaluate_id           INT(10) NOT NULL,
   article_id           INT(10) NOT NULL,
   evaluate_user           INT(10),
   reply_user           INT(10),
   site_id           INT(10) NOT NULL,
   level           INT(10) NOT NULL,
   create_time           DATETIME NOT NULL,
   reply_time           DATETIME,
   PRIMARY KEY(evaluate_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_evaluate_ext;--end
CREATE TABLE tq_evaluate_ext
(
   evaluate_id           INT(10) NOT NULL,
   content           VARCHAR(3000) NOT NULL,
   reply           VARCHAR(3000),
   PRIMARY KEY(evaluate_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_flow_channel;--end
CREATE TABLE tq_flow_channel
(
   channel_id           INT(10) NOT NULL,
   flow_id           INT(10) NOT NULL,
   PRIMARY KEY(channel_id,flow_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_flow_detail;--end
CREATE TABLE tq_flow_detail
(
   detail_id           INT(10) NOT NULL AUTO_INCREMENT,
   doc_id           INT(10) NOT NULL COMMENT '文档ID',
   user_id           INT(10) NOT NULL COMMENT '操作人员',
   role_id           INT(10) NOT NULL COMMENT '操作角色',
   back_initial           BIT(1) NOT NULL DEFAULT b'1' COMMENT '是否退回初始状态',
   is_checked           BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否审核通过',
   reason           VARCHAR(100) COMMENT '操作原因',
   priority           INT(10) NOT NULL COMMENT '排序',
   create_time           DATETIME NOT NULL COMMENT '操作时间',
   PRIMARY KEY(detail_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_flow_step;--end
CREATE TABLE tq_flow_step
(
   step_id           INT(10) NOT NULL AUTO_INCREMENT,
   flow_id           INT(10) NOT NULL,
   step           INT(10) NOT NULL,
   role_id           INT(10) NOT NULL,
   PRIMARY KEY(step_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_flow_step VALUES(40,5,1,2);--end
 INSERT INTO tq_flow_step VALUES(41,5,2,1);--end
DROP TABLE IF EXISTS tq_foreign_lang;--end
CREATE TABLE tq_foreign_lang
(
   lang_id           INT(10) NOT NULL AUTO_INCREMENT,
   lang_code           VARCHAR(10) NOT NULL COMMENT '编码',
   lang_name           VARCHAR(50) NOT NULL COMMENT '名称',
   PRIMARY KEY(lang_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_forum;--end
CREATE TABLE tq_forum
(
   forum_id           INT(10) NOT NULL AUTO_INCREMENT,
   site_id           INT(10) NOT NULL COMMENT '站点ID',
   category_id           INT(10) NOT NULL COMMENT '分类ID',
   last_theme_id           INT(10) COMMENT '最后回复主题',
   last_replyer_id           INT(10) COMMENT '最后回复用户',
   name           VARCHAR(50) NOT NULL COMMENT '板块名称',
   priority           INT(10) NOT NULL COMMENT '序号',
   theme_total           INT(10) NOT NULL COMMENT '主题总数',
   reply_total           INT(10) NOT NULL COMMENT '回复总数',
   theme_today           INT(10) NOT NULL COMMENT '今日主题数',
   reply_today           INT(10) NOT NULL COMMENT '今日回复数',
   moderators           VARCHAR(50) COMMENT '版主',
   PRIMARY KEY(forum_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_forum VALUES(1,1,1,20,1,'发展建议',1,12,3,0,0,null);--end
DROP TABLE IF EXISTS tq_forum_ext;--end
CREATE TABLE tq_forum_ext
(
   forum_id           INT(10) NOT NULL,
   keywords           VARCHAR(200) COMMENT '板块关键字',
   description           VARCHAR(255) COMMENT '板块描述',
   rule           VARCHAR(255) COMMENT '板块规则',
   tpl_content           VARCHAR(150) COMMENT '模板地址',
   PRIMARY KEY(forum_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_forum_ext VALUES(1,'发展建议','发展建议','1 本版块只允许普通用户回复\r\n2 安装使用中遇到问题请先查看的相应帮助. 常见修改可以先查精华区或搜索, 也许会给你节省时间;\r\n3 安装使用方面的问题请到程序讨论区','/extrafunc/forum/帖子列表.html');--end
DROP TABLE IF EXISTS tq_forum_operate;--end
CREATE TABLE tq_forum_operate
(
   operate_id           INT(10) NOT NULL AUTO_INCREMENT,
   site_id           INT(10) NOT NULL COMMENT '站点ID',
   operate_admin           INT(10) NOT NULL COMMENT '操作管理员',
   operate_target           INT(10) NOT NULL COMMENT '操作对象',
   target_type           VARCHAR(30) NOT NULL COMMENT '对象类型',
   name           VARCHAR(30) NOT NULL COMMENT '操作名称',
   reason           VARCHAR(100) COMMENT '操作理由',
   operate_time           DATETIME NOT NULL COMMENT '操作时间',
   PRIMARY KEY(operate_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_forum_operate VALUES(1,1,1,14,'THEME','屏蔽','我要屏蔽','2013-05-31 13:29:28.0');--end
 INSERT INTO tq_forum_operate VALUES(2,1,1,13,'THEME','屏蔽','dfgfdg','2013-05-31 13:46:02.0');--end
 INSERT INTO tq_forum_operate VALUES(3,1,1,12,'THEME','置顶','ssfsdfdsf','2013-05-31 15:53:03.0');--end
 INSERT INTO tq_forum_operate VALUES(4,1,1,11,'THEME','高亮','sfsdfsf','2013-05-31 16:18:35.0');--end
 INSERT INTO tq_forum_operate VALUES(5,1,1,8,'THEME','高亮','sfdsfsd','2013-06-03 11:22:30.0');--end
 INSERT INTO tq_forum_operate VALUES(6,1,1,8,'THEME','置顶','sfsdf','2013-06-03 11:22:46.0');--end
 INSERT INTO tq_forum_operate VALUES(7,1,1,18,'THEME','置顶','fdgfdgfdg','2013-06-03 12:01:25.0');--end
 INSERT INTO tq_forum_operate VALUES(8,1,1,17,'THEME','置顶','fdgdfg','2013-06-03 12:01:39.0');--end
 INSERT INTO tq_forum_operate VALUES(9,1,1,17,'THEME','高亮','gfdgfdg','2013-06-03 12:01:57.0');--end
 INSERT INTO tq_forum_operate VALUES(10,1,1,20,'POSTS','编辑','无','2013-11-04 10:44:55.0');--end
DROP TABLE IF EXISTS tq_forum_statis;--end
CREATE TABLE tq_forum_statis
(
   statis_id           INT(10) NOT NULL,
   posts_today           INT(10) NOT NULL COMMENT '今日发帖数',
   posts_yestoday           INT(10) NOT NULL COMMENT '昨日发帖数',
   highest_day           INT(10) NOT NULL COMMENT '最高日发帖数',
   posts_total           INT(10) NOT NULL COMMENT '总发帖数',
   PRIMARY KEY(statis_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_forum_statis VALUES(1,14,0,0,14);--end
DROP TABLE IF EXISTS tq_gen_table;--end
CREATE TABLE tq_gen_table
(
   tg_gen_name           VARCHAR(50) NOT NULL,
   tq_gen_value           INT(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_gen_table VALUES('tq_article',219);--end
 INSERT INTO tq_gen_table VALUES('tq_log',1982);--end
 INSERT INTO tq_gen_table VALUES('tq_channel',217);--end
 INSERT INTO tq_gen_table VALUES('tq_model',8);--end
 INSERT INTO tq_gen_table VALUES('tq_model_field',116);--end
 INSERT INTO tq_gen_table VALUES('tq_article_type',9);--end
 INSERT INTO tq_gen_table VALUES('tq_work_flow',2);--end
 INSERT INTO tq_gen_table VALUES('tq_flow_step',42);--end
 INSERT INTO tq_gen_table VALUES('tq_comment',35);--end
 INSERT INTO tq_gen_table VALUES('tq_message_type',3);--end
 INSERT INTO tq_gen_table VALUES('tq_advert_slot',5);--end
 INSERT INTO tq_gen_table VALUES('tq_advert',6);--end
 INSERT INTO tq_gen_table VALUES('tq_questionnaire',2);--end
 INSERT INTO tq_gen_table VALUES('tq_survey_theme',7);--end
 INSERT INTO tq_gen_table VALUES('tq_category',2);--end
 INSERT INTO tq_gen_table VALUES('tq_forum',2);--end
 INSERT INTO tq_gen_table VALUES('tq_mailbox_type',2);--end
 INSERT INTO tq_gen_table VALUES('tq_user',14);--end
 INSERT INTO tq_gen_table VALUES('tq_group',5);--end
 INSERT INTO tq_gen_table VALUES('tq_role',26);--end
 INSERT INTO tq_gen_table VALUES('tq_depart',23);--end
 INSERT INTO tq_gen_table VALUES('tq_keyword',4);--end
 INSERT INTO tq_gen_table VALUES('tq_sensitivity',3);--end
 INSERT INTO tq_gen_table VALUES('tq_site',24);--end
 INSERT INTO tq_gen_table VALUES('tq_admin_check',25);--end
 INSERT INTO tq_gen_table VALUES('tq_visit_statistics',150);--end
 INSERT INTO tq_gen_table VALUES('tq_links_type',2);--end
 INSERT INTO tq_gen_table VALUES('tq_links',3);--end
DROP TABLE IF EXISTS tq_group;--end
CREATE TABLE tq_group
(
   group_id           INT(10) NOT NULL AUTO_INCREMENT,
   site_id           INT(10) NOT NULL COMMENT '站点',
   name           VARCHAR(30) NOT NULL COMMENT '会员组名称',
   priority           INT(10) NOT NULL COMMENT '排序',
   is_all_perm           BIT(1) NOT NULL COMMENT '是否拥有所有权限',
   is_regist_show           BIT(1) NOT NULL COMMENT '是否为可注册会员组',
   PRIMARY KEY(group_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_group VALUES(1,1,'普通会员',1,1,1);--end
 INSERT INTO tq_group VALUES(2,1,'白金会员',3,1,0);--end
 INSERT INTO tq_group VALUES(3,1,'青铜会员',2,1,0);--end
DROP TABLE IF EXISTS tq_group_perm;--end
CREATE TABLE tq_group_perm
(
   group_id           INT(10) NOT NULL COMMENT '会员组',
   perms           LONGTEXT COMMENT '权限集合',
   PRIMARY KEY(group_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_group_perm VALUES(1,null);--end
 INSERT INTO tq_group_perm VALUES(2,null);--end
 INSERT INTO tq_group_perm VALUES(3,null);--end
DROP TABLE IF EXISTS tq_industry;--end
CREATE TABLE tq_industry
(
   industry_id           INT(10) NOT NULL AUTO_INCREMENT,
   parent_id           INT(10),
   industry_code           VARCHAR(10) NOT NULL COMMENT '编码',
   industry_name           VARCHAR(50) NOT NULL COMMENT '名称',
   PRIMARY KEY(industry_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_job_fairs;--end
CREATE TABLE tq_job_fairs
(
   fairs_id           INT(10) NOT NULL AUTO_INCREMENT,
   fairs_name           VARCHAR(50) NOT NULL,
   fairs_theme           VARCHAR(100) NOT NULL,
   start_time           DATETIME NOT NULL,
   end_time           DATETIME NOT NULL,
   create_time           DATETIME NOT NULL,
   PRIMARY KEY(fairs_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_job_fairs_ext;--end
CREATE TABLE tq_job_fairs_ext
(
   fairs_id           INT(10) NOT NULL,
   tpl_address           VARCHAR(100),
   introduction           VARCHAR(2000) NOT NULL,
   host_organ           VARCHAR(50) NOT NULL,
   undertake           VARCHAR(50),
   city_name           VARCHAR(100) NOT NULL,
   place           VARCHAR(100) NOT NULL,
   link_man           VARCHAR(30) NOT NULL,
   link_tel           VARCHAR(20) NOT NULL,
   email           VARCHAR(30) NOT NULL,
   PRIMARY KEY(fairs_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_job_intent;--end
CREATE TABLE tq_job_intent
(
   intent_id           INT(10) NOT NULL AUTO_INCREMENT,
   metier_id           INT(10) NOT NULL COMMENT '期望从事职业',
   industry_id           INT(10) COMMENT '期望从事行业',
   work_place           VARCHAR(50) NOT NULL COMMENT '期望工作地点',
   expect_wage           SMALLINT(5) NOT NULL COMMENT '期望待遇',
   work_nature           VARCHAR(10) NOT NULL COMMENT '期望工作性质',
   title           VARCHAR(50) NOT NULL,
   create_time           DATETIME NOT NULL,
   update_time           DATETIME,
   metier_id2           INT(10),
   PRIMARY KEY(intent_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_job_post;--end
CREATE TABLE tq_job_post
(
   job_id           INT(10) NOT NULL AUTO_INCREMENT,
   company_id           INT(10) NOT NULL,
   job_name           VARCHAR(40) NOT NULL,
   work_address           VARCHAR(100) NOT NULL,
   educational           SMALLINT(5) NOT NULL,
   recruit_count           INT(10) NOT NULL,
   start_time           DATETIME NOT NULL,
   end_time           DATETIME NOT NULL,
   refresh_time           DATETIME,
   is_check           SMALLINT(5) NOT NULL,
   metier_id           INT(10) NOT NULL,
   create_time           DATETIME NOT NULL,
   gender           SMALLINT(5) NOT NULL DEFAULT '-1',
   min_age           INT(10),
   max_age           INT(10),
   PRIMARY KEY(job_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_job_post_ext;--end
CREATE TABLE tq_job_post_ext
(
   job_id           INT(10) NOT NULL,
   job_type           SMALLINT(5) NOT NULL,
   appeal           VARCHAR(2000) NOT NULL,
   job_nature           VARCHAR(1000),
   others           VARCHAR(2000),
   is_onbusiness           SMALLINT(5) NOT NULL,
   wage           SMALLINT(5) NOT NULL,
   work_exp           SMALLINT(5) NOT NULL DEFAULT '0',
   PRIMARY KEY(job_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_keyword;--end
CREATE TABLE tq_keyword
(
   keyword_id           INT(10) NOT NULL AUTO_INCREMENT,
   site_id           INT(10) NOT NULL,
   name           VARCHAR(100) NOT NULL COMMENT '关键字',
   url           VARCHAR(100) NOT NULL COMMENT '关键字链接',
   is_bold           BIT(1) NOT NULL COMMENT '是否加粗',
   is_underline           BIT(1) NOT NULL COMMENT '是否有下划线',
   is_enable           BIT(1) NOT NULL COMMENT '是否启用',
   PRIMARY KEY(keyword_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_keyword VALUES(1,1,'淘宝','http://www.taobao.com',1,1,1);--end
 INSERT INTO tq_keyword VALUES(3,1,'javapms','http://www.javapms.com',0,1,1);--end
DROP TABLE IF EXISTS tq_lang_ability;--end
CREATE TABLE tq_lang_ability
(
   ability_id           INT(10) NOT NULL AUTO_INCREMENT,
   resume_id           INT(10) NOT NULL COMMENT '简历ID',
   lang_id           INT(10),
   cert_type           VARCHAR(30),
   grade           VARCHAR(30),
   literacy           SMALLINT(5),
   communications           SMALLINT(5),
   PRIMARY KEY(ability_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_links;--end
CREATE TABLE tq_links
(
   links_id           INT(10) NOT NULL,
   site_id           INT(10) NOT NULL,
   type_id           INT(10) NOT NULL COMMENT '分类ID',
   links_name           VARCHAR(50) NOT NULL COMMENT '友情链接名称',
   links_icon           VARCHAR(100) COMMENT '友情链接图标',
   links_url           VARCHAR(100) COMMENT '友情链接URL',
   priority           INT(10) NOT NULL COMMENT '排序',
   is_show           BIT(1) NOT NULL COMMENT '是否显示',
   show_icon           BIT(1) NOT NULL COMMENT '是否图标显示',
   PRIMARY KEY(links_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_links VALUES(1,1,1,'JAVAPMS官网',null,'http://www.javapms.com',1,1,0);--end
 INSERT INTO tq_links VALUES(2,1,1,'百姓生活网',null,'http://www.jmenhu.com',2,1,0);--end
DROP TABLE IF EXISTS tq_links_type;--end
CREATE TABLE tq_links_type
(
   type_id           INT(10) NOT NULL,
   site_id           INT(10) NOT NULL,
   name           VARCHAR(50) NOT NULL COMMENT '分类名称',
   priority           INT(10) NOT NULL COMMENT '排序',
   PRIMARY KEY(type_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_links_type VALUES(1,1,'文字链接',1);--end
DROP TABLE IF EXISTS tq_log;--end
CREATE TABLE tq_log
(
   log_id           INT(10) NOT NULL AUTO_INCREMENT,
   user_id           INT(10),
   site_id           INT(10),
   category           INT(10) NOT NULL COMMENT '日志类型',
   log_time           DATETIME NOT NULL COMMENT '日志时间',
   ip           VARCHAR(50) COMMENT 'IP地址',
   url           VARCHAR(255) COMMENT 'URL地址',
   title           VARCHAR(255) COMMENT '日志标题',
   content           VARCHAR(255) COMMENT '日志内容',
   PRIMARY KEY(log_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_log VALUES(1977,1,1,3,'2014-05-26 18:47:10.0','0:0:0:0:0:0:0:1','/javapms/admin/doc/o_ajax_delete.do','删除文档','id=216;title=sdf');--end
 INSERT INTO tq_log VALUES(1978,1,1,3,'2014-05-26 18:49:05.0','0:0:0:0:0:0:0:1','/javapms/admin/doc/o_save.do','添加文档','id=218;title=a');--end
 INSERT INTO tq_log VALUES(1979,1,1,3,'2014-05-26 18:49:16.0','0:0:0:0:0:0:0:1','/javapms/admin/doc/o_update.do','修改文档','id=218;title=a');--end
 INSERT INTO tq_log VALUES(1980,1,1,3,'2014-05-26 18:49:24.0','0:0:0:0:0:0:0:1','/javapms/admin/doc/o_update.do','修改文档','id=218;title=a');--end
 INSERT INTO tq_log VALUES(1981,1,1,3,'2014-05-26 18:49:36.0','0:0:0:0:0:0:0:1','/javapms/admin/doc/o_ajax_delete.do','删除文档','id=218;title=a');--end
DROP TABLE IF EXISTS tq_mailbox;--end
CREATE TABLE tq_mailbox
(
   mailbox_id           INT(10) NOT NULL AUTO_INCREMENT,
   site_id           INT(10) NOT NULL DEFAULT '0' COMMENT '站点ID',
   depart_id           INT(10) COMMENT '部门ID',
   type_id           INT(10) NOT NULL COMMENT '分类ID',
   title           VARCHAR(50) NOT NULL COMMENT '标题',
   name           VARCHAR(20) NOT NULL COMMENT '姓名',
   mobile           VARCHAR(20) NOT NULL COMMENT '电话',
   email           VARCHAR(50) NOT NULL COMMENT '电子邮箱',
   address           VARCHAR(150) COMMENT '联系地址',
   zipcode           VARCHAR(20) COMMENT '邮编',
   status           SMALLINT(5) NOT NULL COMMENT '状态',
   is_show           BIT(1) NOT NULL COMMENT '是否公开',
   create_time           DATETIME NOT NULL COMMENT '写信时间',
   reply_time           DATETIME COMMENT '回复时间',
   PRIMARY KEY(mailbox_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_mailbox_ext;--end
CREATE TABLE tq_mailbox_ext
(
   mailbox_id           INT(10) NOT NULL,
   content           VARCHAR(2000) NOT NULL COMMENT '内容',
   reply           VARCHAR(2000) COMMENT '回复',
   PRIMARY KEY(mailbox_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_mailbox_type;--end
CREATE TABLE tq_mailbox_type
(
   type_id           INT(10) NOT NULL AUTO_INCREMENT,
   site_id           INT(10) NOT NULL DEFAULT '0' COMMENT '站点ID',
   name           VARCHAR(20) NOT NULL COMMENT '名称',
   priority           INT(10) NOT NULL COMMENT '排序',
   PRIMARY KEY(type_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_mailbox_type VALUES(1,1,'咨询',1);--end
DROP TABLE IF EXISTS tq_member;--end
CREATE TABLE tq_member
(
   member_id           INT(10) NOT NULL,
   avatar           VARCHAR(100) COMMENT '头像',
   gender           SMALLINT(5) NOT NULL COMMENT '性别',
   birthday           DATE COMMENT '生日',
   address           VARCHAR(100) COMMENT '住址',
   signature           VARCHAR(255) COMMENT '个性签名',
   registe_time           DATETIME NOT NULL COMMENT '注册时间',
   registe_ip           VARCHAR(20) COMMENT '注册IP',
   last_login_time           DATETIME COMMENT '最后登录时间',
   last_login_ip           VARCHAR(20) COMMENT '最后登录IP',
   login_count           INT(10) NOT NULL COMMENT '登录次数',
   t_status           SMALLINT(5) NOT NULL COMMENT '状态',
   PRIMARY KEY(member_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_member VALUES(1,'/member/upload/pms/201405/25124618cjtl.png',1,'2013-10-01','江西省南昌市','倾力打造JAVA、JSP门户管理系统品牌。','2013-08-06 16:09:41.0','0:0:0:0:0:0:0:1','2013-08-06 16:09:41.0',null,1,0);--end
 INSERT INTO tq_member VALUES(3,'/member/upload/pms/201405/26180116f9ii.png',0,'1989-05-10','trytry','生命在于折腾~','2013-04-27 10:38:03.0','0:0:0:0:0:0:0:1','2014-05-26 18:47:53.0','0:0:0:0:0:0:0:1',31,0);--end
DROP TABLE IF EXISTS tq_member_group;--end
CREATE TABLE tq_member_group
(
   group_id           INT(10) NOT NULL COMMENT '会员组ID',
   member_id           INT(10) NOT NULL COMMENT '会员ID',
   PRIMARY KEY(group_id,member_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_member_group VALUES(1,3);--end
DROP TABLE IF EXISTS tq_message_board;--end
CREATE TABLE tq_message_board
(
   board_id           INT(10) NOT NULL AUTO_INCREMENT,
   site_id           INT(10) NOT NULL COMMENT '站点ID',
   type_id           INT(10) NOT NULL COMMENT '分类ID',
   title           VARCHAR(50) COMMENT '标题',
   name           VARCHAR(20) COMMENT '姓名',
   mobile           VARCHAR(20) COMMENT '电话',
   email           VARCHAR(50) COMMENT '电子邮箱',
   address           VARCHAR(150) COMMENT '联系地址',
   zipcode           VARCHAR(20) COMMENT '邮编',
   is_show           BIT(1) NOT NULL COMMENT '是否公开',
   create_time           DATETIME NOT NULL COMMENT '提交时间',
   reply_time           DATETIME COMMENT '回复时间',
   PRIMARY KEY(board_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_message_board_ext;--end
CREATE TABLE tq_message_board_ext
(
   board_id           INT(10) NOT NULL,
   content           VARCHAR(2000) NOT NULL COMMENT '内容',
   reply           VARCHAR(2000) COMMENT '回复',
   PRIMARY KEY(board_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_message_receive;--end
CREATE TABLE tq_message_receive
(
   message_id           INT(10) NOT NULL,
   content           TEXT COMMENT '接收人',
   PRIMARY KEY(message_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_message_type;--end
CREATE TABLE tq_message_type
(
   type_id           INT(10) NOT NULL AUTO_INCREMENT,
   site_id           INT(10) NOT NULL COMMENT '站点ID',
   name           VARCHAR(20) NOT NULL COMMENT '名称',
   priority           INT(10) NOT NULL COMMENT '排序',
   PRIMARY KEY(type_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_message_type VALUES(1,1,'咨询',10);--end
DROP TABLE IF EXISTS tq_metier;--end
CREATE TABLE tq_metier
(
   metier_id           INT(10) NOT NULL AUTO_INCREMENT,
   parent_id           INT(10),
   metier_code           VARCHAR(10) NOT NULL COMMENT '编码',
   metier_name           VARCHAR(50) NOT NULL COMMENT '名称',
   PRIMARY KEY(metier_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_model;--end
CREATE TABLE tq_model
(
   model_id           INT(10) NOT NULL AUTO_INCREMENT,
   model_name           VARCHAR(100) NOT NULL COMMENT '名称',
   model_icon           VARCHAR(30) NOT NULL COMMENT '图标',
   model_tpl_doc           VARCHAR(100) COMMENT '内容页模板',
   model_tpl_print           VARCHAR(100) COMMENT '打印页模板',
   model_tpl_search           VARCHAR(100) NOT NULL COMMENT '搜索页模板',
   model_tpl_advsearch           VARCHAR(100) NOT NULL COMMENT '高级搜索页模板',
   model_tpl_comment           VARCHAR(100) NOT NULL COMMENT '评论页模板',
   priority           INT(10) NOT NULL DEFAULT '10' COMMENT '排列顺序',
   is_disabled           BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否禁用',
   PRIMARY KEY(model_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_model VALUES(2,'新闻','/img/icon/xw.gif','/doc/article/默认内容页.html','','/extrafunc/search/搜索结果页.html','','',1,0);--end
 INSERT INTO tq_model VALUES(3,'图集','/img/icon/tj.gif','/doc/article/图集内容页.html','/article/默认内容页.html','/extrafunc/search/图片搜索结果页.html','/alone/默认单页.html','/alone/默认单页.html',2,0);--end
 INSERT INTO tq_model VALUES(4,'视频','/img/icon/sp3.gif','/doc/article/默认内容页.html','','/extrafunc/search/搜索结果页.html','','',3,0);--end
 INSERT INTO tq_model VALUES(5,'专题','/img/icon/zt.gif','/doc/article/默认内容页.html','','/extrafunc/search/搜索结果页.html','','',4,0);--end
DROP TABLE IF EXISTS tq_model_field;--end
CREATE TABLE tq_model_field
(
   field_id           INT(10) NOT NULL AUTO_INCREMENT,
   model_id           INT(10) NOT NULL,
   name           VARCHAR(50) NOT NULL COMMENT '字段名称',
   label           VARCHAR(100) NOT NULL COMMENT '字段别名',
   priority           INT(10) NOT NULL DEFAULT '70' COMMENT '排列顺序',
   text_size           VARCHAR(20) COMMENT '长度',
   text_maxlength           VARCHAR(10) COMMENT '最大长度',
   width           VARCHAR(10) COMMENT '输入框宽度',
   height           VARCHAR(3) COMMENT '输入框高度',
   tip           VARCHAR(255) COMMENT '说明',
   value_list           VARCHAR(255) COMMENT '可选值列表',
   data_type           INT(10) NOT NULL COMMENT '数据类型',
   is_required           BIT(1) NOT NULL DEFAULT b'1' COMMENT '是否必填',
   is_single           BIT(1) NOT NULL DEFAULT b'1' COMMENT '是否并列',
   is_economy           BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否系统字段',
   is_show           BIT(1) NOT NULL DEFAULT b'1' COMMENT '是否显示',
   PRIMARY KEY(field_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_model_field VALUES(1,2,'channelId','栏目',1,null,null,null,null,null,null,9,1,1,1,1);--end
 INSERT INTO tq_model_field VALUES(2,2,'title','标题',2,null,null,null,null,null,null,1,1,1,1,1);--end
 INSERT INTO tq_model_field VALUES(3,2,'shortTitle','短标题',5,null,null,null,null,null,null,1,0,0,1,1);--end
 INSERT INTO tq_model_field VALUES(4,2,'titleColor','标题颜色',4,null,null,null,null,null,null,1,0,0,1,1);--end
 INSERT INTO tq_model_field VALUES(5,2,'subTitle','副标题',3,null,null,null,null,null,null,1,0,0,1,1);--end
 INSERT INTO tq_model_field VALUES(6,2,'tagStr','Tag标签',6,'25','','','','','',1,0,0,1,1);--end
 INSERT INTO tq_model_field VALUES(7,2,'description','摘要',7,null,null,null,null,null,null,2,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(8,2,'author','作者',9,null,null,null,null,null,null,1,0,0,1,1);--end
 INSERT INTO tq_model_field VALUES(9,2,'origin','来源',8,null,null,null,null,null,null,1,0,0,1,1);--end
 INSERT INTO tq_model_field VALUES(10,2,'style','类型',10,null,'','','','','',8,0,0,1,1);--end
 INSERT INTO tq_model_field VALUES(11,2,'recommend','属性',11,null,'','','','','',8,0,0,1,1);--end
 INSERT INTO tq_model_field VALUES(12,2,'showIndex','显示到首页',22,null,null,null,null,null,null,7,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(13,2,'redTape','红头文件',18,null,null,null,null,null,null,1,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(14,2,'viewGroups','访问权限',21,null,null,null,null,null,null,8,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(15,2,'tplContent','内容模板',14,null,null,null,null,null,null,1,1,0,1,1);--end
 INSERT INTO tq_model_field VALUES(16,2,'atts','附件',20,null,null,null,null,null,null,10,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(17,2,'releaseDate','发布时间',15,null,null,null,null,null,null,6,1,0,1,1);--end
 INSERT INTO tq_model_field VALUES(18,2,'link','外部链接',16,null,null,null,null,null,null,1,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(19,2,'commentControl','评论控制',17,null,null,null,null,null,null,7,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(20,2,'updownControl','顶踩控制',19,null,null,null,null,null,null,7,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(21,2,'txt','内容',13,null,null,null,null,null,null,3,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(22,2,'picture','缩略图',12,null,null,null,null,null,null,10,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(23,2,'pics','组图',23,null,null,null,null,null,null,10,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(24,3,'channelId','栏目',1,null,null,null,null,null,null,9,1,1,1,1);--end
 INSERT INTO tq_model_field VALUES(25,3,'title','标题',2,null,null,null,null,null,null,1,1,1,1,1);--end
 INSERT INTO tq_model_field VALUES(26,3,'shortTitle','短标题',12,null,null,null,null,null,null,1,0,0,1,0);--end
 INSERT INTO tq_model_field VALUES(27,3,'titleColor','标题颜色',13,null,null,null,null,null,null,1,0,0,1,0);--end
 INSERT INTO tq_model_field VALUES(28,3,'subTitle','副标题',21,null,null,null,null,null,null,1,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(29,3,'tagStr','Tag标签',14,null,null,null,null,null,null,1,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(30,3,'description','摘要',3,null,null,null,null,null,null,2,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(31,3,'author','作者',5,null,null,null,null,null,null,1,0,0,1,1);--end
 INSERT INTO tq_model_field VALUES(32,3,'origin','来源',4,null,null,null,null,null,null,1,0,0,1,1);--end
 INSERT INTO tq_model_field VALUES(33,3,'style','新闻类型',6,null,null,null,null,null,null,8,0,0,1,1);--end
 INSERT INTO tq_model_field VALUES(34,3,'recommend','属性',7,null,'','','','','',8,0,0,1,1);--end
 INSERT INTO tq_model_field VALUES(35,3,'showIndex','显示到首页',22,null,null,null,null,null,null,7,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(36,3,'redTape','红头文件',23,null,null,null,null,null,null,1,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(37,3,'viewGroups','访问权限',20,null,null,null,null,null,null,8,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(38,3,'tplContent','内容模板',10,null,null,null,null,null,null,1,1,0,1,1);--end
 INSERT INTO tq_model_field VALUES(39,3,'atts','附件',19,null,null,null,null,null,null,10,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(40,3,'releaseDate','发布时间',11,null,null,null,null,null,null,6,1,0,1,1);--end
 INSERT INTO tq_model_field VALUES(41,3,'link','外部链接',16,null,null,null,null,null,null,1,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(42,3,'commentControl','评论控制',17,null,null,null,null,null,null,7,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(43,3,'updownControl','顶踩控制',18,null,null,null,null,null,null,7,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(44,3,'txt','内容',15,null,null,null,null,null,null,3,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(45,3,'picture','缩略图',8,null,null,null,null,null,null,10,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(46,3,'pics','组图',9,null,null,null,null,null,null,10,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(47,4,'channelId','栏目',1,null,null,null,null,null,null,9,1,1,1,1);--end
 INSERT INTO tq_model_field VALUES(48,4,'title','标题',2,null,null,null,null,null,null,1,1,1,1,1);--end
 INSERT INTO tq_model_field VALUES(49,4,'shortTitle','短标题',12,null,null,null,null,null,null,1,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(50,4,'titleColor','标题颜色',13,null,null,null,null,null,null,1,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(51,4,'subTitle','副标题',14,null,null,null,null,null,null,1,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(52,4,'tagStr','Tag标签',15,null,null,null,null,null,null,1,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(53,4,'description','摘要',7,null,null,null,null,null,null,2,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(54,4,'author','作者',4,null,null,null,null,null,null,1,0,0,1,1);--end
 INSERT INTO tq_model_field VALUES(55,4,'origin','来源',3,null,null,null,null,null,null,1,0,0,1,1);--end
 INSERT INTO tq_model_field VALUES(56,4,'style','新闻类型',5,null,null,null,null,null,null,8,0,0,1,1);--end
 INSERT INTO tq_model_field VALUES(57,4,'recommend','属性',6,null,'','','','','',8,0,0,1,1);--end
 INSERT INTO tq_model_field VALUES(58,4,'showIndex','显示到首页',16,null,null,null,null,null,null,7,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(59,4,'redTape','红头文件',17,null,null,null,null,null,null,1,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(60,4,'viewGroups','访问权限',18,null,null,null,null,null,null,8,0,0,1,0);--end
 INSERT INTO tq_model_field VALUES(61,4,'tplContent','内容模板',10,null,null,null,null,null,null,1,1,0,1,1);--end
 INSERT INTO tq_model_field VALUES(62,4,'atts','附件',19,null,null,null,null,null,null,10,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(63,4,'releaseDate','发布时间',11,null,null,null,null,null,null,6,1,0,1,1);--end
 INSERT INTO tq_model_field VALUES(64,4,'link','外部链接',20,null,null,null,null,null,null,1,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(65,4,'commentControl','评论控制',21,null,null,null,null,null,null,7,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(66,4,'updownControl','顶踩控制',22,null,null,null,null,null,null,7,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(67,4,'txt','内容',9,null,null,null,null,null,null,3,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(68,4,'picture','缩略图',8,null,null,null,null,null,null,10,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(69,4,'pics','组图',23,null,null,null,null,null,null,10,0,1,1,0);--end
 INSERT INTO tq_model_field VALUES(70,5,'channelId','栏目',1,null,null,null,null,null,null,9,1,1,1,1);--end
 INSERT INTO tq_model_field VALUES(71,5,'title','标题',2,null,null,null,null,null,null,1,1,1,1,1);--end
 INSERT INTO tq_model_field VALUES(72,5,'shortTitle','短标题',3,null,null,null,null,null,null,1,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(73,5,'titleColor','标题颜色',4,null,null,null,null,null,null,1,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(74,5,'subTitle','副标题',5,null,null,null,null,null,null,1,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(75,5,'tagStr','Tag标签',6,null,null,null,null,null,null,1,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(76,5,'description','摘要',7,null,null,null,null,null,null,2,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(77,5,'author','作者',8,null,null,null,null,null,null,1,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(78,5,'origin','来源',9,null,null,null,null,null,null,1,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(79,5,'style','新闻类型',10,null,null,null,null,null,null,8,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(80,5,'recommend','推荐',11,null,null,null,null,null,null,8,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(81,5,'showIndex','显示到首页',12,null,null,null,null,null,null,7,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(82,5,'redTape','红头文件',13,null,null,null,null,null,null,1,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(83,5,'viewGroups','访问权限',14,null,null,null,null,null,null,8,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(84,5,'tplContent','内容模板',15,null,null,null,null,null,null,1,1,1,1,1);--end
 INSERT INTO tq_model_field VALUES(85,5,'atts','附件',16,null,null,null,null,null,null,10,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(86,5,'releaseDate','发布时间',17,null,null,null,null,null,null,6,1,1,1,1);--end
 INSERT INTO tq_model_field VALUES(87,5,'link','外部链接',18,null,null,null,null,null,null,1,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(88,5,'commentControl','评论控制',19,null,null,null,null,null,null,7,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(89,5,'updownControl','顶踩控制',20,null,null,null,null,null,null,7,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(90,5,'txt','内容',21,null,null,null,null,null,null,3,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(91,5,'picture','缩略图',22,null,null,null,null,null,null,10,0,1,1,1);--end
 INSERT INTO tq_model_field VALUES(92,5,'pics','组图',23,null,null,null,null,null,null,10,0,1,1,1);--end
DROP TABLE IF EXISTS tq_personal_favorite;--end
CREATE TABLE tq_personal_favorite
(
   favorite_id           INT(10) NOT NULL AUTO_INCREMENT,
   personal_id           INT(10) NOT NULL,
   job_id           INT(10) NOT NULL,
   create_time           DATETIME NOT NULL,
   PRIMARY KEY(favorite_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_personal_info;--end
CREATE TABLE tq_personal_info
(
   personal_id           INT(10) NOT NULL AUTO_INCREMENT,
   work_exp           SMALLINT(5) NOT NULL COMMENT '工作经验',
   is_work           SMALLINT(5) NOT NULL COMMENT '是否在职',
   is_check           SMALLINT(5) NOT NULL COMMENT '是否审核',
   is_commend           SMALLINT(5) NOT NULL COMMENT '是否推荐',
   avatar           VARCHAR(50),
   realname           VARCHAR(50),
   gender           SMALLINT(5),
   birthday           DATETIME,
   educational           SMALLINT(5),
   special_id           INT(10),
   PRIMARY KEY(personal_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_personal_info_ext;--end
CREATE TABLE tq_personal_info_ext
(
   personal_id           INT(10) NOT NULL,
   card_type           SMALLINT(5),
   idcard           VARCHAR(50),
   address           VARCHAR(100),
   commun_type1           SMALLINT(5),
   commun1           VARCHAR(30),
   commun_type2           SMALLINT(5),
   commun2           VARCHAR(30),
   marriage           SMALLINT(5),
   political           VARCHAR(10),
   nation           VARCHAR(20),
   PRIMARY KEY(personal_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_petition;--end
CREATE TABLE tq_petition
(
   petition_id           INT(10) NOT NULL AUTO_INCREMENT,
   site_id           INT(10) NOT NULL COMMENT '站点ID',
   type_id           INT(10) NOT NULL COMMENT '分类ID',
   title           VARCHAR(50) NOT NULL COMMENT '标题',
   name           VARCHAR(20) NOT NULL COMMENT '姓名',
   mobile           VARCHAR(20) NOT NULL COMMENT '电话',
   email           VARCHAR(50) NOT NULL COMMENT '电子邮箱',
   address           VARCHAR(150) COMMENT '联系地址',
   zipcode           VARCHAR(20) COMMENT '邮编',
   status           SMALLINT(5) NOT NULL COMMENT '状态',
   is_show           BIT(1) NOT NULL COMMENT '是否公开',
   create_time           DATETIME NOT NULL COMMENT '提交时间',
   reply_time           DATETIME COMMENT '回复时间',
   PRIMARY KEY(petition_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_petition_ext;--end
CREATE TABLE tq_petition_ext
(
   petition_id           INT(10) NOT NULL,
   content           VARCHAR(2000) NOT NULL COMMENT '内容',
   reply           VARCHAR(2000) COMMENT '回复',
   PRIMARY KEY(petition_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_petition_type;--end
CREATE TABLE tq_petition_type
(
   type_id           INT(10) NOT NULL AUTO_INCREMENT,
   site_id           INT(10) NOT NULL DEFAULT '0' COMMENT '站点ID',
   name           VARCHAR(20) NOT NULL COMMENT '名称',
   priority           INT(10) NOT NULL COMMENT '排序',
   PRIMARY KEY(type_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_posts;--end
CREATE TABLE tq_posts
(
   posts_id           INT(10) NOT NULL AUTO_INCREMENT,
   site_id           INT(10) NOT NULL COMMENT '站点ID',
   theme_id           INT(10) NOT NULL COMMENT '主题ID',
   creater_id           INT(10) COMMENT '发帖会员',
   title           VARCHAR(100) COMMENT '帖子标题',
   status           INT(10) NOT NULL COMMENT '帖子状态',
   is_affix           BIT(1) NOT NULL COMMENT '是否有附件',
   is_img           BIT(1) NOT NULL COMMENT '是否有图片',
   is_hidden           BIT(1) NOT NULL COMMENT '是否有图片',
   floor           INT(10) COMMENT '楼层',
   create_time           DATETIME NOT NULL COMMENT '发帖时间',
   quote_id           INT(10) COMMENT '引用帖子ID',
   PRIMARY KEY(posts_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_posts VALUES(1,1,1,1,'测试发布帖子',0,0,0,0,1,'2013-05-26 16:20:37.0',null);--end
 INSERT INTO tq_posts VALUES(2,1,2,1,'法的',0,0,0,0,1,'2013-05-26 21:25:53.0',null);--end
 INSERT INTO tq_posts VALUES(3,1,2,1,null,0,0,0,0,2,'2013-05-26 22:01:12.0',null);--end
 INSERT INTO tq_posts VALUES(4,1,2,1,null,0,0,0,0,3,'2013-05-26 23:19:35.0',null);--end
 INSERT INTO tq_posts VALUES(5,1,3,1,'asdsad',0,1,0,0,1,'2013-05-27 15:11:33.0',null);--end
 INSERT INTO tq_posts VALUES(6,1,4,1,'dsgfdgdf',0,1,0,0,1,'2013-05-27 15:29:40.0',null);--end
 INSERT INTO tq_posts VALUES(7,1,5,1,'sfdsfdsfsd',0,0,0,1,1,'2013-05-27 15:32:58.0',null);--end
 INSERT INTO tq_posts VALUES(8,1,5,1,null,0,0,0,0,2,'2013-05-28 11:45:53.0',null);--end
 INSERT INTO tq_posts VALUES(9,1,5,1,null,0,0,0,0,3,'2013-05-28 14:52:12.0',7);--end
 INSERT INTO tq_posts VALUES(10,1,5,3,null,0,0,0,0,4,'2013-05-28 17:11:21.0',null);--end
 INSERT INTO tq_posts VALUES(11,1,5,3,null,0,0,0,0,5,'2013-05-28 17:15:42.0',null);--end
 INSERT INTO tq_posts VALUES(12,1,6,3,'美女图',0,1,0,0,1,'2013-05-28 17:52:00.0',null);--end
 INSERT INTO tq_posts VALUES(13,1,7,3,'测试隐藏贴',0,0,0,1,1,'2013-05-28 17:54:55.0',null);--end
 INSERT INTO tq_posts VALUES(14,1,7,3,null,0,0,0,0,2,'2013-05-28 17:55:15.0',13);--end
 INSERT INTO tq_posts VALUES(15,1,7,3,null,0,0,0,0,3,'2013-05-29 16:47:09.0',null);--end
 INSERT INTO tq_posts VALUES(16,1,7,3,null,0,0,0,0,4,'2013-05-29 16:49:09.0',null);--end
 INSERT INTO tq_posts VALUES(17,1,7,3,null,0,0,0,0,5,'2013-05-29 19:50:23.0',null);--end
 INSERT INTO tq_posts VALUES(19,1,7,3,null,0,0,0,0,6,'2013-05-30 10:44:55.0',null);--end
 INSERT INTO tq_posts VALUES(20,1,8,3,null,0,0,0,1,1,'2013-05-30 10:58:16.0',null);--end
 INSERT INTO tq_posts VALUES(21,1,9,3,'fddfgdfg',0,0,0,0,1,'2013-05-30 15:23:42.0',null);--end
 INSERT INTO tq_posts VALUES(22,1,10,3,'fhfgh',0,0,0,0,1,'2013-05-30 15:25:13.0',null);--end
 INSERT INTO tq_posts VALUES(23,1,11,3,'ghfhf',0,0,0,0,1,'2013-05-30 15:37:19.0',null);--end
 INSERT INTO tq_posts VALUES(24,1,12,3,'fdgfdgd',0,0,0,0,1,'2013-05-30 15:39:20.0',null);--end
 INSERT INTO tq_posts VALUES(25,1,13,3,'dsfdsfs',-1,0,0,0,1,'2013-05-30 15:40:19.0',null);--end
 INSERT INTO tq_posts VALUES(26,1,14,3,'dgfdgfdg',-1,0,0,0,1,'2013-05-30 15:41:18.0',null);--end
 INSERT INTO tq_posts VALUES(27,1,15,1,'dgdfgdfgdfg',0,1,1,0,1,'2013-05-31 16:59:16.0',null);--end
 INSERT INTO tq_posts VALUES(28,1,16,1,'fdgdfg',0,0,0,0,1,'2013-05-31 18:14:17.0',null);--end
 INSERT INTO tq_posts VALUES(29,1,17,1,'xzcxzc',0,0,0,0,1,'2013-05-31 21:35:07.0',null);--end
 INSERT INTO tq_posts VALUES(30,1,18,1,'dfgfd',0,0,0,0,1,'2013-06-03 09:30:35.0',null);--end
 INSERT INTO tq_posts VALUES(31,1,19,1,'dfg',0,0,0,0,1,'2013-06-05 08:59:33.0',null);--end
 INSERT INTO tq_posts VALUES(32,1,20,1,'测试编辑帖子',0,0,0,0,1,'2013-11-04 10:52:37.0',null);--end
DROP TABLE IF EXISTS tq_posts_attach;--end
CREATE TABLE tq_posts_attach
(
   posts_id           INT(10) NOT NULL COMMENT '帖子ID',
   name           VARCHAR(100) COMMENT '附件名称',
   description           VARCHAR(255) COMMENT '附件描述',
   file_path           VARCHAR(100) NOT NULL COMMENT '文件地址',
   file_name           VARCHAR(50) COMMENT '文件名称',
   file_size           INT(10) COMMENT '文件大小',
   is_img           BIT(1) NOT NULL COMMENT '是否为图片',
   priority           INT(10)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_posts_attach VALUES(5,'1.jpg',null,'/member/upload/demo/201305/27151133jmw8.jpg','1.jpg',42417,1,0);--end
 INSERT INTO tq_posts_attach VALUES(6,'12.jpg',null,'/member/upload/demo/201305/27152940yd4n.jpg','12.jpg',22946,1,0);--end
 INSERT INTO tq_posts_attach VALUES(12,'12.jpg',null,'/member/upload/demo/201305/28175200la3k.jpg','12.jpg',22946,1,0);--end
 INSERT INTO tq_posts_attach VALUES(27,'12.jpg',null,'/member/upload/demo/201305/31165916ua39.jpg','12.jpg',22946,1,0);--end
DROP TABLE IF EXISTS tq_posts_ext;--end
CREATE TABLE tq_posts_ext
(
   posts_id           INT(10) NOT NULL,
   editer_id           INT(10) COMMENT '修改会员ID',
   create_ip           VARCHAR(20) NOT NULL COMMENT '发布IP',
   edit_time           DATETIME COMMENT '修改时间',
   edit_ip           VARCHAR(20) COMMENT '修改IP',
   edit_count           INT(10) NOT NULL COMMENT '修改次数',
   PRIMARY KEY(posts_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_posts_ext VALUES(1,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(2,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(3,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(4,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(5,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(6,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(7,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(8,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(9,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(10,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(11,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(12,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(13,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(14,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(15,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(16,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(17,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(19,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(20,1,'0:0:0:0:0:0:0:1',null,null,1);--end
 INSERT INTO tq_posts_ext VALUES(21,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(22,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(23,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(24,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(25,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(26,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(27,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(28,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(29,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(30,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(31,null,'0:0:0:0:0:0:0:1',null,null,0);--end
 INSERT INTO tq_posts_ext VALUES(32,null,'0:0:0:0:0:0:0:1',null,null,0);--end
DROP TABLE IF EXISTS tq_posts_txt;--end
CREATE TABLE tq_posts_txt
(
   posts_id           INT(10) NOT NULL,
   content           LONGTEXT COMMENT '内容',
   PRIMARY KEY(posts_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_posts_txt VALUES(1,'测试发布帖子');--end
 INSERT INTO tq_posts_txt VALUES(2,'个梵蒂冈');--end
 INSERT INTO tq_posts_txt VALUES(3,'松岛枫松岛枫');--end
 INSERT INTO tq_posts_txt VALUES(4,'fdhfhfghf');--end
 INSERT INTO tq_posts_txt VALUES(5,'[localimg]1[/localimg]asdasdasd');--end
 INSERT INTO tq_posts_txt VALUES(6,'[attachment]0[/attachment]gfdgfdgfd');--end
 INSERT INTO tq_posts_txt VALUES(7,'[hide]sdfsdfsdfs[/hide]');--end
 INSERT INTO tq_posts_txt VALUES(8,'                            [color=red]此处是被引用的隐藏帖[/color]\r\n                            gdfgdfgfdgdfgdfg');--end
 INSERT INTO tq_posts_txt VALUES(9,'\r\n                         asdasdasdasda   ');--end
 INSERT INTO tq_posts_txt VALUES(10,'sfdfgfdgdfg');--end
 INSERT INTO tq_posts_txt VALUES(11,'dfsfsdfsdf');--end
 INSERT INTO tq_posts_txt VALUES(12,'gfdgdfg[attachment]0[/attachment]');--end
 INSERT INTO tq_posts_txt VALUES(13,'青年教师是高校教师队伍的重要组成部分，是推动高等教育事业科学发展、办好人民满意高等教育的重要力量。青年教师与学生年龄接近，与学生接触较多，对学生的思想行为影响更直接，他们的思想政治素质和道德情操对学生的健康成长具有重要的示范引导作用。加强和改进高校青年教师思想政治工作，对于全面贯彻党的教育方针、确保高校坚持社会主义办学方向、培养德智体美全面发展的社会主义建设者和接班人，具有重大而深远的意义。\r\n\r\n[hide]当前，高校青年教师主体积极健康向上，拥护党的领导，对坚持和发展中国特色社会主义充满信心，热爱教书育人事业，关心关爱学生，为高等教育事业发展做出重要贡献。同时也应看到，少数青年教师政治信仰迷茫、理想信念模糊、职业情感与职业道德淡化、服务意识不强，个别教师言行失范、不能为人师表；一些地方和高校对青年教师思想政治工作重视不够、工作方法不多、工作针对性和实效性不强。各地各高校党组织要充分认识新形势下加强和改进青年教师思想政治建设的重要性，切实把加强青年教师思想政治工作摆到更加突出的位置，进一步增强工作的主动性、积极性和创造性，通过政治上主动引导、专业上着力培养、生活上热情关心，促进广大青年教师坚定理想信念、练就过硬本领、勇于创新创造、矢志艰苦奋斗、锤炼高尚品格，全面提高思想政治素质和业务能力。[/hide]\r\n');--end
 INSERT INTO tq_posts_txt VALUES(14,'\r\n佛顶骨电饭锅的郭殿方过放电\r\n                            ');--end
 INSERT INTO tq_posts_txt VALUES(15,'                            sdfsdfsdfsdf');--end
 INSERT INTO tq_posts_txt VALUES(16,'                          dsgfdgfdgfdg  ');--end
 INSERT INTO tq_posts_txt VALUES(17,'gfdgdfgfdgfd');--end
 INSERT INTO tq_posts_txt VALUES(19,'fdgdfgfdgdf');--end
 INSERT INTO tq_posts_txt VALUES(20,'[hide]测试隐藏贴测试隐藏贴测试隐藏贴测试隐藏贴测试隐藏贴测试隐藏贴adsad[/hide]');--end
 INSERT INTO tq_posts_txt VALUES(21,'dfgdfgdfg[smiley=4]');--end
 INSERT INTO tq_posts_txt VALUES(22,'fghfghfgh');--end
 INSERT INTO tq_posts_txt VALUES(23,'ghgfhfghfgh');--end
 INSERT INTO tq_posts_txt VALUES(24,'fgdfgdfg');--end
 INSERT INTO tq_posts_txt VALUES(25,'dfsdfsdf');--end
 INSERT INTO tq_posts_txt VALUES(26,'dfgfdgfdg');--end
 INSERT INTO tq_posts_txt VALUES(27,'[attachment]0[/attachment]fdgfdgdfgfd');--end
 INSERT INTO tq_posts_txt VALUES(28,'fdgdfgdfgfd[smiley=4][smiley=8]');--end
 INSERT INTO tq_posts_txt VALUES(29,'zxczxcxzc');--end
 INSERT INTO tq_posts_txt VALUES(30,'gfdgfdg');--end
 INSERT INTO tq_posts_txt VALUES(31,'fdgfdgfdg');--end
 INSERT INTO tq_posts_txt VALUES(32,'测试编辑帖子测试编辑帖子测试编辑帖子测试编辑帖子');--end
DROP TABLE IF EXISTS tq_profess_post;--end
CREATE TABLE tq_profess_post
(
   post_id           INT(10) NOT NULL AUTO_INCREMENT,
   post_code           VARCHAR(10) NOT NULL COMMENT '编码',
   post_name           VARCHAR(50) NOT NULL COMMENT '名称',
   PRIMARY KEY(post_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_program_download;--end
CREATE TABLE tq_program_download
(
   download_id           INT(10) NOT NULL AUTO_INCREMENT,
   count           INT(10) NOT NULL,
   PRIMARY KEY(download_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_program_download VALUES(1,2);--end
DROP TABLE IF EXISTS tq_qq_bind;--end
CREATE TABLE tq_qq_bind
(
   bind_id           INT(10) NOT NULL,
   username           VARCHAR(100) NOT NULL COMMENT '绑定的用户名',
   openid           VARCHAR(100) NOT NULL COMMENT 'id',
   openkey           VARCHAR(100) NOT NULL COMMENT 'key',
   bind_time           DATETIME NOT NULL COMMENT '绑定时间',
   PRIMARY KEY(bind_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_question_detail;--end
CREATE TABLE tq_question_detail
(
   detail_id           INT(10) NOT NULL AUTO_INCREMENT,
   question_id           INT(10) NOT NULL COMMENT '问卷ID',
   user_id           INT(10) COMMENT '投票会员ID',
   ip           VARCHAR(50) COMMENT '投票IP',
   create_time           DATETIME NOT NULL COMMENT '投票时间',
   PRIMARY KEY(detail_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_question_detail VALUES(1,1,null,'0:0:0:0:0:0:0:1','2013-10-30 16:20:52.0');--end
 INSERT INTO tq_question_detail VALUES(2,1,null,'0:0:0:0:0:0:0:1','2013-10-30 16:22:24.0');--end
 INSERT INTO tq_question_detail VALUES(3,1,null,'0:0:0:0:0:0:0:1','2013-10-30 16:42:20.0');--end
 INSERT INTO tq_question_detail VALUES(4,1,null,'0:0:0:0:0:0:0:1','2013-10-30 16:44:46.0');--end
 INSERT INTO tq_question_detail VALUES(5,1,null,'0:0:0:0:0:0:0:1','2013-10-30 16:45:55.0');--end
 INSERT INTO tq_question_detail VALUES(6,1,null,'0:0:0:0:0:0:0:1','2013-10-30 16:52:12.0');--end
 INSERT INTO tq_question_detail VALUES(7,1,null,'0:0:0:0:0:0:0:1','2013-10-30 17:08:24.0');--end
 INSERT INTO tq_question_detail VALUES(8,1,1,'0:0:0:0:0:0:0:1','2013-10-30 17:09:01.0');--end
 INSERT INTO tq_question_detail VALUES(9,1,1,'0:0:0:0:0:0:0:1','2013-10-30 17:14:19.0');--end
 INSERT INTO tq_question_detail VALUES(10,1,1,'0:0:0:0:0:0:0:1','2013-10-30 17:14:24.0');--end
 INSERT INTO tq_question_detail VALUES(11,1,null,'0:0:0:0:0:0:0:1','2013-10-30 17:15:17.0');--end
 INSERT INTO tq_question_detail VALUES(12,1,null,'0:0:0:0:0:0:0:1','2013-10-30 17:16:39.0');--end
 INSERT INTO tq_question_detail VALUES(13,1,null,'0:0:0:0:0:0:0:1','2013-10-30 17:19:26.0');--end
 INSERT INTO tq_question_detail VALUES(14,1,null,'0:0:0:0:0:0:0:1','2013-10-31 11:10:40.0');--end
 INSERT INTO tq_question_detail VALUES(15,1,1,'0:0:0:0:0:0:0:1','2013-10-31 15:12:16.0');--end
DROP TABLE IF EXISTS tq_questionnaire;--end
CREATE TABLE tq_questionnaire
(
   naire_id           INT(10) NOT NULL AUTO_INCREMENT,
   site_id           INT(10) NOT NULL COMMENT '站点ID',
   name           VARCHAR(50) NOT NULL COMMENT '调查主题',
   description           VARCHAR(300) COMMENT '调查描述',
   repeate_time           INT(10) NOT NULL COMMENT '重复投票时间',
   is_restrict_ip           BIT(1) NOT NULL COMMENT '是否限制IP重复投票',
   is_need_login           BIT(1) NOT NULL COMMENT '是否需登录',
   create_time           DATETIME NOT NULL COMMENT '添加时间',
   start_time           DATE NOT NULL COMMENT '调查开始时间',
   end_time           DATE COMMENT '调查结束时间',
   enable           BIT(1) NOT NULL COMMENT '是否启用',
   PRIMARY KEY(naire_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_questionnaire VALUES(1,1,'javapms用户满意度调查','JAVAPMS门户管理系统正式版已经发布，您在使用的过程中有什么样的感受呢？为更好的改进系统，提升用户体验，特此举行一次JAVAPMS用户满意度调查，欢迎您给我们提出宝贵使用意见和建议，谢谢!',1,1,0,'2013-08-26 10:48:51.0','2014-05-25',null,1);--end
DROP TABLE IF EXISTS tq_resume;--end
CREATE TABLE tq_resume
(
   resume_id           INT(10) NOT NULL AUTO_INCREMENT,
   personal_id           INT(10) NOT NULL COMMENT '个人ID',
   name           VARCHAR(30) NOT NULL COMMENT '简历名称',
   resume_lang           VARCHAR(20) NOT NULL COMMENT '简历语言',
   resume_open           SMALLINT(5) NOT NULL COMMENT '开启状态',
   create_time           DATETIME NOT NULL COMMENT '创建时间',
   update_time           DATETIME COMMENT '更新时间',
   is_apply_def           SMALLINT(5) NOT NULL DEFAULT '0',
   PRIMARY KEY(resume_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_resume_apply;--end
CREATE TABLE tq_resume_apply
(
   apply_id           INT(10) NOT NULL AUTO_INCREMENT,
   resume_id           INT(10) NOT NULL,
   job_id           INT(10) NOT NULL,
   create_time           DATETIME NOT NULL,
   is_read           SMALLINT(5) NOT NULL,
   PRIMARY KEY(apply_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_resume_ext;--end
CREATE TABLE tq_resume_ext
(
   resume_id           INT(10) NOT NULL,
   evaluate           VARCHAR(2000),
   skill_special           VARCHAR(2000),
   work_exp           VARCHAR(2000),
   PRIMARY KEY(resume_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_role;--end
CREATE TABLE tq_role
(
   role_id           INT(10) NOT NULL AUTO_INCREMENT,
   site_id           INT(10) NOT NULL DEFAULT '1' COMMENT '站点ID',
   name           VARCHAR(50) NOT NULL COMMENT '角色名称',
   priority           INT(10) NOT NULL COMMENT '排序',
   is_all_perm           BIT(1) NOT NULL COMMENT '是否拥有所有权限',
   PRIMARY KEY(role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_role VALUES(1,1,'超级管理员',1,1);--end
 INSERT INTO tq_role VALUES(2,1,'信息报送员',2,0);--end
 INSERT INTO tq_role VALUES(11,9,'超级管理员',1,1);--end
 INSERT INTO tq_role VALUES(20,18,'超级管理员',1,1);--end
 INSERT INTO tq_role VALUES(23,21,'超级管理员',1,1);--end
 INSERT INTO tq_role VALUES(25,23,'超级管理员',1,1);--end
DROP TABLE IF EXISTS tq_role_perm;--end
CREATE TABLE tq_role_perm
(
   role_id           INT(10) NOT NULL COMMENT '角色ID',
   perms           LONGTEXT COMMENT '权限集合',
   PRIMARY KEY(role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_role_perm VALUES(1,'');--end
 INSERT INTO tq_role_perm VALUES(2,'admin:workspace:index,admin:workspace:right,admin:workspace:mgr,admin:workspace:infoopt,admin:workspace:info,admin:workspace:infoupdate,admin:workspace:passopt,admin:workspace:passedit,admin:workspace:passupdate,admin:doccenter,admin:doc:mgr,admin:doc:addopt,admin:doc:add,admin:doc:save,admin:doc:editopt,admin:doc:edit,admin:doc:update,admin:doc:list,admin:doc:delete,admin:doc:cycle,admin:doc:reduct,');--end
 INSERT INTO tq_role_perm VALUES(11,'');--end
 INSERT INTO tq_role_perm VALUES(20,'');--end
 INSERT INTO tq_role_perm VALUES(23,'');--end
 INSERT INTO tq_role_perm VALUES(25,'');--end
DROP TABLE IF EXISTS tq_sensitivity;--end
CREATE TABLE tq_sensitivity
(
   sensitivity_id           INT(10) NOT NULL AUTO_INCREMENT,
   search           VARCHAR(255) NOT NULL COMMENT '敏感词',
   replacement           VARCHAR(255) NOT NULL COMMENT '替换词',
   PRIMARY KEY(sensitivity_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_sensitivity VALUES(2,'法轮功','***');--end
DROP TABLE IF EXISTS tq_site;--end
CREATE TABLE tq_site
(
   site_id           INT(10) NOT NULL AUTO_INCREMENT,
   domain           VARCHAR(50) NOT NULL COMMENT '域名',
   site_path           VARCHAR(20) NOT NULL COMMENT '路径',
   site_name           VARCHAR(100) NOT NULL COMMENT '网站名称',
   short_name           VARCHAR(100) NOT NULL COMMENT '简短名称',
   context_path           VARCHAR(20) COMMENT '上下文',
   port           INT(10) COMMENT '端口',
   tpl_style           VARCHAR(50) NOT NULL DEFAULT 'default' COMMENT '网站风格',
   title           VARCHAR(80) COMMENT 'title',
   keywords           VARCHAR(100) COMMENT '关键字',
   description           VARCHAR(255) COMMENT '描述',
   is_recover           BIT(1) NOT NULL DEFAULT b'1' COMMENT '开启回收站',
   is_static_channel           SMALLINT(5) NOT NULL DEFAULT '0' COMMENT '生成栏目静态页策略',
   is_static_doc           SMALLINT(5) NOT NULL DEFAULT '0' COMMENT '生成文档静态页策略',
   is_static_suffix           BIT(1) NOT NULL DEFAULT b'0' COMMENT '是否带有后缀',
   tpl_index           VARCHAR(50) COMMENT '首页模板',
   update_time           DATETIME COMMENT '最后更新时间',
   is_terminus           BIT(1) NOT NULL DEFAULT b'1' COMMENT '是否为总站',
   PRIMARY KEY(site_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_site VALUES(1,'localhost','pms','javapms门户管理系统','javapms','/javapms',8080,'default','javapms门户管理系统','java,jsp,cms,pms,html5,免费,门户,商业免费,管理系统,门户系统','JAVAPMS是J2EE技术核心架构，面向广大站长、软件开发者、程序爱好者、网页设计师，适用于个人站长、商业企业、政府机构、教育机构和其它各种单位组织的信息门户网站建设，是一款技术先进、安全稳定、操作友好、注重用户体验的JAVA门户管理系统，倾力打造JAVA、JSP门户管理系统品牌。',1,0,0,1,'/doc/index/首页.html','2014-05-26 18:49:35.0',1);--end
 INSERT INTO tq_site VALUES(9,'localhost','test','子站','','',8080,'default','','','',1,1,1,1,null,null,0);--end
 INSERT INTO tq_site VALUES(18,'localhost','fdgfdg','fdgfdg','','',8080,'default','','','',1,1,1,1,null,null,0);--end
 INSERT INTO tq_site VALUES(21,'localhost','sdfsd','dsfsd','','',8080,'default','','','',1,1,1,1,null,'2013-12-03 12:31:10.0',0);--end
 INSERT INTO tq_site VALUES(23,'localhost','zizhan1','子站1','','',8080,'default','子站1','子站1','子站1',1,1,1,1,null,'2013-12-07 14:20:24.0',0);--end
DROP TABLE IF EXISTS tq_site_config;--end
CREATE TABLE tq_site_config
(
   config_id           INT(10) NOT NULL,
   comment_check           BIT(1) NOT NULL COMMENT '评论是否需审核',
   comment_login           BIT(1) NOT NULL COMMENT '评论是否需登录',
   message_check           BIT(1) NOT NULL COMMENT '留言是否需审核',
   message_login           BIT(1) NOT NULL COMMENT '留言是否需登录',
   message_name           BIT(1) NOT NULL COMMENT '留言联系人是否显示',
   message_mobile           BIT(1) NOT NULL COMMENT '留言联系电话是否显示',
   message_email           BIT(1) NOT NULL COMMENT '留言email是否显示',
   message_address           BIT(1) NOT NULL COMMENT '留言地址是否显示',
   message_zipcode           BIT(1) NOT NULL COMMENT '留言邮编是否显示',
   reg_open           BIT(1) NOT NULL COMMENT '注册是否开启',
   reg_min           INT(10) COMMENT '会员名最小长度',
   reg_max           INT(10) COMMENT '会员名最大长度',
   reg_check           BIT(1) NOT NULL COMMENT '注册会员是否需审核',
   login_count           INT(10) COMMENT '每天登录失败次数限制',
   PRIMARY KEY(config_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_site_config VALUES(1,0,0,0,0,0,0,0,0,0,1,null,null,0,3);--end
DROP TABLE IF EXISTS tq_site_message;--end
CREATE TABLE tq_site_message
(
   message_id           INT(10) NOT NULL AUTO_INCREMENT,
   send_id           INT(10) NOT NULL COMMENT '发送人ID',
   title           VARCHAR(50) COMMENT '标题',
   content           VARCHAR(1000) NOT NULL COMMENT '信件内容',
   is_group           BIT(1) COMMENT '是否为群发短信',
   status           INT(10) NOT NULL COMMENT '发送方状态',
   create_time           DATETIME NOT NULL COMMENT '发送时间',
   PRIMARY KEY(message_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_site_message_status;--end
CREATE TABLE tq_site_message_status
(
   status_id           INT(10) NOT NULL AUTO_INCREMENT,
   message_id           INT(10) NOT NULL COMMENT '信件ID',
   receive_id           INT(10) NOT NULL COMMENT '接收人ID',
   status           INT(10) NOT NULL COMMENT '接收方状态:-1,垃圾箱,0,未读,1，已读',
   PRIMARY KEY(status_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_specialty;--end
CREATE TABLE tq_specialty
(
   special_id           INT(10) NOT NULL AUTO_INCREMENT,
   parent_id           INT(10),
   special_code           VARCHAR(10) NOT NULL COMMENT '编码',
   special_name           VARCHAR(50) NOT NULL COMMENT '名称',
   PRIMARY KEY(special_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_survey_detail;--end
CREATE TABLE tq_survey_detail
(
   detail_id           INT(10) NOT NULL AUTO_INCREMENT,
   user_id           INT(10) COMMENT '反馈会员ID',
   survey_id           INT(10) NOT NULL COMMENT '调查项ID',
   content           VARCHAR(1000) COMMENT '反馈内容',
   create_time           DATETIME NOT NULL COMMENT '反馈时间',
   PRIMARY KEY(detail_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_survey_detail VALUES(1,1,6,'继续加油','2013-10-31 15:12:16.0');--end
 INSERT INTO tq_survey_detail VALUES(2,1,5,'很好','2013-10-31 15:12:16.0');--end
DROP TABLE IF EXISTS tq_survey_item;--end
CREATE TABLE tq_survey_item
(
   theme_id           INT(10) NOT NULL COMMENT '主题ID',
   name           VARCHAR(50) NOT NULL COMMENT '投票项名称',
   votes           INT(10) NOT NULL COMMENT '票数',
   priority           INT(10) NOT NULL COMMENT '排序'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_survey_item VALUES(1,'男',0,1);--end
 INSERT INTO tq_survey_item VALUES(1,'女',1,2);--end
 INSERT INTO tq_survey_item VALUES(3,'搜索引擎',0,1);--end
 INSERT INTO tq_survey_item VALUES(3,'朋友介绍',1,2);--end
 INSERT INTO tq_survey_item VALUES(3,'通知公告',0,3);--end
 INSERT INTO tq_survey_item VALUES(3,'QQ群或论坛',0,4);--end
 INSERT INTO tq_survey_item VALUES(4,'十分满意',0,1);--end
 INSERT INTO tq_survey_item VALUES(4,'基本满意',0,2);--end
 INSERT INTO tq_survey_item VALUES(4,'不满意',1,3);--end
DROP TABLE IF EXISTS tq_survey_theme;--end
CREATE TABLE tq_survey_theme
(
   theme_id           INT(10) NOT NULL AUTO_INCREMENT,
   naire_id           INT(10) NOT NULL COMMENT '问卷调查ID',
   title           VARCHAR(50) NOT NULL COMMENT '标题',
   survey_type           INT(10) NOT NULL COMMENT '问卷类型',
   total_count           INT(10) COMMENT '最大选项个数',
   maxlength           INT(10) COMMENT '最大长度',
   show_type           INT(10) COMMENT '显示形式',
   priority           INT(10) NOT NULL COMMENT '排序',
   PRIMARY KEY(theme_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_survey_theme VALUES(1,1,'您的性别？',1,1,null,1,1);--end
 INSERT INTO tq_survey_theme VALUES(2,1,'您的工作职位？',2,null,50,4,2);--end
 INSERT INTO tq_survey_theme VALUES(3,1,'您最初是从哪里得知道新版系统的？',1,1,null,1,10);--end
 INSERT INTO tq_survey_theme VALUES(4,1,'您目前对新版网站满意吗？',1,3,null,1,10);--end
 INSERT INTO tq_survey_theme VALUES(5,1,'请您给新版系统提出宝贵的意见或建议',2,null,500,5,10);--end
 INSERT INTO tq_survey_theme VALUES(6,1,'您有什么好的想法分享下',2,null,200,5,10);--end
DROP TABLE IF EXISTS tq_theme;--end
CREATE TABLE tq_theme
(
   theme_id           INT(10) NOT NULL AUTO_INCREMENT,
   site_id           INT(10) NOT NULL COMMENT '站点ID',
   forum_id           INT(10) NOT NULL COMMENT '版块ID',
   creater_id           INT(10) COMMENT '发帖会员',
   last_replyer_id           INT(10) COMMENT '最后回复会员',
   title           VARCHAR(150) NOT NULL COMMENT '主题名称',
   views_count           INT(10) NOT NULL COMMENT '访问次数',
   reply_count           INT(10) NOT NULL COMMENT '回复次数',
   is_lock           BIT(1) NOT NULL COMMENT '是否锁定',
   is_essena           BIT(1) COMMENT '是否为精华帖',
   is_bold           BIT(1) COMMENT '是否加粗',
   is_italic           BIT(1) COMMENT '是否斜体',
   color           VARCHAR(50) COMMENT '标题颜色',
   top_time           DATE COMMENT '置顶截止日期',
   essena_time           DATE COMMENT '精华截止日期',
   lock_time           DATE COMMENT '锁定截止日期',
   status           INT(10) NOT NULL COMMENT '主题状态',
   is_affix           BIT(1) NOT NULL COMMENT '是否有附件',
   is_img           BIT(1) NOT NULL COMMENT '是否有图片',
   is_moder_reply           BIT(1) NOT NULL COMMENT '版主是否已回复',
   last_reply_time           DATETIME COMMENT '最后回复时间',
   create_time           DATETIME NOT NULL COMMENT '发布时间',
   light_time           DATE COMMENT '高亮截止时间',
   PRIMARY KEY(theme_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_theme VALUES(1,1,1,1,1,'测试发布帖子',0,0,0,0,0,0,null,null,null,null,0,0,0,0,'2013-05-26 16:20:37.0','2013-05-26 16:20:37.0',null);--end
 INSERT INTO tq_theme VALUES(2,1,1,1,1,'法的',0,0,0,0,0,0,null,null,null,null,0,0,0,1,'2013-05-26 23:19:35.0','2013-05-26 21:25:53.0',null);--end
 INSERT INTO tq_theme VALUES(3,1,1,1,1,'asdsad',0,0,0,0,0,0,null,null,null,null,0,1,0,0,'2013-05-27 15:11:33.0','2013-05-27 15:11:33.0',null);--end
 INSERT INTO tq_theme VALUES(4,1,1,1,1,'dsgfdgdf',0,0,0,0,0,0,null,null,null,null,0,1,0,0,'2013-05-27 15:29:40.0','2013-05-27 15:29:40.0',null);--end
 INSERT INTO tq_theme VALUES(5,1,1,1,3,'sfdsfdsfsd',0,0,0,0,0,0,null,null,null,null,0,0,0,1,'2013-05-28 17:15:42.0','2013-05-27 15:32:58.0',null);--end
 INSERT INTO tq_theme VALUES(6,1,1,3,3,'美女图',0,0,0,0,0,0,null,null,null,null,0,1,0,0,'2013-05-28 17:52:00.0','2013-05-28 17:52:00.0',null);--end
 INSERT INTO tq_theme VALUES(7,1,1,3,3,'测试隐藏贴',0,4,0,0,0,0,null,null,null,null,0,0,0,0,'2013-05-30 10:44:55.0','2013-05-28 17:54:55.0',null);--end
 INSERT INTO tq_theme VALUES(8,1,1,3,3,'测试隐藏贴',6,0,0,0,1,null,'FF0000',null,null,null,2,0,0,0,'2013-05-30 10:58:16.0','2013-05-30 10:58:16.0',null);--end
 INSERT INTO tq_theme VALUES(9,1,1,3,3,'fddfgdfg',0,0,0,0,0,0,null,null,null,null,0,0,0,0,'2013-05-30 15:23:42.0','2013-05-30 15:23:42.0',null);--end
 INSERT INTO tq_theme VALUES(10,1,1,3,3,'fhfgh',0,0,0,0,0,0,null,null,null,null,0,0,0,0,'2013-05-30 15:25:13.0','2013-05-30 15:25:13.0',null);--end
 INSERT INTO tq_theme VALUES(11,1,1,3,3,'ghfhf',6,0,0,0,1,null,'FFFF00',null,null,null,0,0,0,0,'2013-05-30 15:37:19.0','2013-05-30 15:37:19.0','2013-06-06');--end
 INSERT INTO tq_theme VALUES(12,1,1,3,3,'fdgfdgd',5,0,0,0,0,0,null,null,null,null,0,0,0,0,'2013-05-30 15:39:20.0','2013-05-30 15:39:20.0',null);--end
 INSERT INTO tq_theme VALUES(13,1,1,3,3,'dsfdsfs',2,0,0,0,0,0,null,null,null,null,-1,0,0,0,'2013-05-30 15:40:19.0','2013-05-30 15:40:19.0',null);--end
 INSERT INTO tq_theme VALUES(14,1,1,3,3,'dgfdgfdg',6,0,0,0,0,0,null,null,null,null,-1,0,0,0,'2013-05-30 15:41:18.0','2013-05-30 15:41:18.0',null);--end
 INSERT INTO tq_theme VALUES(15,1,1,1,1,'dgdfgdfgdfg',6,0,0,0,0,0,null,null,null,null,0,1,1,0,'2013-05-31 16:59:15.0','2013-05-31 16:59:15.0',null);--end
 INSERT INTO tq_theme VALUES(16,1,1,1,1,'fdgdfg',7,0,0,0,0,0,null,null,null,null,0,0,0,0,'2013-05-31 18:14:17.0','2013-05-31 18:14:17.0',null);--end
 INSERT INTO tq_theme VALUES(17,1,1,1,1,'xzcxzc',2,0,0,0,1,null,'FF0000',null,null,null,1,0,0,0,'2013-05-31 21:35:07.0','2013-05-31 21:35:07.0',null);--end
 INSERT INTO tq_theme VALUES(18,1,1,1,1,'dfgfd',9,0,0,0,0,0,null,null,null,null,2,0,0,0,'2013-06-03 09:30:35.0','2013-06-03 09:30:35.0',null);--end
 INSERT INTO tq_theme VALUES(19,1,1,1,1,'dfg',3,0,0,0,0,0,null,null,null,null,0,0,0,0,'2013-06-05 08:59:33.0','2013-06-05 08:59:33.0',null);--end
 INSERT INTO tq_theme VALUES(20,1,1,1,1,'测试编辑帖子',0,0,0,0,0,0,null,null,null,null,0,0,0,0,'2013-11-04 10:52:37.0','2013-11-04 10:52:37.0',null);--end
DROP TABLE IF EXISTS tq_theme_txt;--end
CREATE TABLE tq_theme_txt
(
   theme_id           INT(10) NOT NULL,
   content           LONGTEXT COMMENT '回复内容记录',
   PRIMARY KEY(theme_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_theme_txt VALUES(1,',');--end
 INSERT INTO tq_theme_txt VALUES(2,',');--end
 INSERT INTO tq_theme_txt VALUES(3,',');--end
 INSERT INTO tq_theme_txt VALUES(4,',');--end
 INSERT INTO tq_theme_txt VALUES(5,',3,');--end
 INSERT INTO tq_theme_txt VALUES(6,',');--end
 INSERT INTO tq_theme_txt VALUES(7,',3,');--end
 INSERT INTO tq_theme_txt VALUES(8,',');--end
 INSERT INTO tq_theme_txt VALUES(9,',');--end
 INSERT INTO tq_theme_txt VALUES(10,',');--end
 INSERT INTO tq_theme_txt VALUES(11,',');--end
 INSERT INTO tq_theme_txt VALUES(12,',');--end
 INSERT INTO tq_theme_txt VALUES(13,',');--end
 INSERT INTO tq_theme_txt VALUES(14,',');--end
 INSERT INTO tq_theme_txt VALUES(15,',');--end
 INSERT INTO tq_theme_txt VALUES(16,',');--end
 INSERT INTO tq_theme_txt VALUES(17,',');--end
 INSERT INTO tq_theme_txt VALUES(18,',');--end
 INSERT INTO tq_theme_txt VALUES(19,',');--end
 INSERT INTO tq_theme_txt VALUES(20,',');--end
DROP TABLE IF EXISTS tq_thirdparty_bind;--end
CREATE TABLE tq_thirdparty_bind
(
   bind_id           INT(10) NOT NULL,
   username           VARCHAR(100) NOT NULL COMMENT '绑定的用户名',
   openid           VARCHAR(100) NOT NULL COMMENT 'id',
   openkey           VARCHAR(100) NOT NULL COMMENT 'key',
   bind_time           DATETIME NOT NULL COMMENT '绑定时间',
   bind_type           VARCHAR(50) NOT NULL COMMENT '绑定类型,如QQ，新浪',
   PRIMARY KEY(bind_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_thirdparty_config;--end
CREATE TABLE tq_thirdparty_config
(
   config_id           INT(10) NOT NULL,
   qq_key           VARCHAR(100) COMMENT '申请到的QQkey',
   sina_key           VARCHAR(100) COMMENT '申请到的新浪key',
   PRIMARY KEY(config_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_train;--end
CREATE TABLE tq_train
(
   train_id           INT(10) NOT NULL,
   resume_id           INT(10) NOT NULL,
   start_time           VARCHAR(30) NOT NULL,
   end_time           VARCHAR(30) NOT NULL,
   training           VARCHAR(50) NOT NULL,
   train_course           VARCHAR(50) NOT NULL,
   cert_name           VARCHAR(20),
   description           VARCHAR(2000),
   create_time           DATETIME NOT NULL,
   PRIMARY KEY(train_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_unit_type;--end
CREATE TABLE tq_unit_type
(
   type_id           INT(10) NOT NULL AUTO_INCREMENT,
   type_code           VARCHAR(10) NOT NULL COMMENT '编码',
   type_name           VARCHAR(50) NOT NULL COMMENT '名称',
   PRIMARY KEY(type_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_user;--end
CREATE TABLE tq_user
(
   user_id           INT(10) NOT NULL AUTO_INCREMENT,
   username           VARCHAR(50) NOT NULL COMMENT '用户名',
   t_password           VARCHAR(50) NOT NULL COMMENT '密码',
   email           VARCHAR(50) NOT NULL COMMENT '邮箱',
   real_name           VARCHAR(50) COMMENT '真实姓名',
   phone           VARCHAR(20) COMMENT '电话',
   mobile           VARCHAR(20) COMMENT '手机',
   fail_count           INT(10) NOT NULL DEFAULT '0' COMMENT '登录失败次数',
   t_status           SMALLINT(5) NOT NULL COMMENT '状态',
   last_fail_time           DATETIME COMMENT '最后登录失败时间',
   PRIMARY KEY(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_user VALUES(1,'admin','1fee1fbceaa9436e749d7a275b23d2bb','javapms@163.com','万有引力','0791-86520256','18907090737',2,0,'2014-05-26 11:06:09.0');--end
 INSERT INTO tq_user VALUES(3,'test','4297f44b13955235245b2497399d7a93','ewr@163.com','张珊','36456546','456546546',0,0,null);--end
 INSERT INTO tq_user VALUES(5,'demo','fe01ce2a7fbac8fafaed7c982a04e229','dsfdsf@163.com','李思','','',0,0,null);--end
DROP TABLE IF EXISTS tq_user_bind;--end
CREATE TABLE tq_user_bind
(
   bind_id           INT(10) NOT NULL AUTO_INCREMENT,
   user_id           INT(10) NOT NULL,
   username           VARCHAR(100) NOT NULL COMMENT '用户名',
   pass           VARCHAR(100) NOT NULL COMMENT '密码',
   status           SMALLINT(5) NOT NULL COMMENT '系统类型',
   PRIMARY KEY(bind_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_user_forum;--end
CREATE TABLE tq_user_forum
(
   user_id           INT(10) NOT NULL,
   avatar           VARCHAR(50) COMMENT '头像',
   essena_count           INT(10) NOT NULL COMMENT '精华帖数量',
   theme_count           INT(10) NOT NULL COMMENT '主题数量',
   reply_count           INT(10) NOT NULL COMMENT '回复数量',
   point           INT(10) NOT NULL COMMENT '积分',
   status           INT(10) NOT NULL COMMENT '状态',
   status_time           DATE COMMENT '截止时间',
   signature           VARCHAR(255) COMMENT '个性签名',
   PRIMARY KEY(user_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_user_forum VALUES(1,null,0,6,0,18,0,null,null);--end
 INSERT INTO tq_user_forum VALUES(3,null,0,7,1,22,0,null,null);--end
DROP TABLE IF EXISTS tq_vip_type;--end
CREATE TABLE tq_vip_type
(
   type_id           INT(10) NOT NULL AUTO_INCREMENT,
   type_name           VARCHAR(50) NOT NULL,
   day_count           INT(10) NOT NULL,
   post_count           INT(10) NOT NULL,
   favorite_count           INT(10) NOT NULL,
   audition_count           INT(10) NOT NULL,
   receipt_count           INT(10) NOT NULL,
   send_count           INT(10) NOT NULL,
   setup_login           SMALLINT(5) DEFAULT '0',
   PRIMARY KEY(type_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_visit_statistics;--end
CREATE TABLE tq_visit_statistics
(
   visit_id           INT(10) NOT NULL,
   site_id           INT(10) NOT NULL COMMENT '站点ID',
   url           VARCHAR(150) NOT NULL COMMENT '访问地址',
   ip           VARCHAR(30) COMMENT '访问IP',
   cookie           VARCHAR(100) COMMENT '访问COOKIE',
   visit_time           DATETIME NOT NULL COMMENT '访问时间',
   visit_hour           INT(10) NOT NULL COMMENT '访问小时',
   visit_min           INT(10) NOT NULL COMMENT '访问分钟',
   PRIMARY KEY(visit_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_visit_statistics VALUES(24,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 15:48:26.0',15,48);--end
 INSERT INTO tq_visit_statistics VALUES(25,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 15:49:53.0',15,49);--end
 INSERT INTO tq_visit_statistics VALUES(26,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 15:50:13.0',15,50);--end
 INSERT INTO tq_visit_statistics VALUES(27,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 17:01:24.0',17,1);--end
 INSERT INTO tq_visit_statistics VALUES(28,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 20:32:41.0',20,32);--end
 INSERT INTO tq_visit_statistics VALUES(29,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 21:35:48.0',21,35);--end
 INSERT INTO tq_visit_statistics VALUES(30,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','4e6d7a0c60664ed68ca8c1bcf901c306','2014-05-25 21:56:56.0',21,56);--end
 INSERT INTO tq_visit_statistics VALUES(31,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','4e6d7a0c60664ed68ca8c1bcf901c306','2014-05-25 21:58:18.0',21,58);--end
 INSERT INTO tq_visit_statistics VALUES(32,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:19:12.0',22,19);--end
 INSERT INTO tq_visit_statistics VALUES(33,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:19:29.0',22,19);--end
 INSERT INTO tq_visit_statistics VALUES(34,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:22:17.0',22,22);--end
 INSERT INTO tq_visit_statistics VALUES(35,1,'http://localhost:8080/javapms/china/212.html','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:22:29.0',22,22);--end
 INSERT INTO tq_visit_statistics VALUES(36,1,'http://localhost:8080/javapms/china/212_2.html','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:22:33.0',22,22);--end
 INSERT INTO tq_visit_statistics VALUES(37,1,'http://localhost:8080/javapms/comment-212.jsp','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:22:44.0',22,22);--end
 INSERT INTO tq_visit_statistics VALUES(38,1,'http://localhost:8080/javapms/china/212.html','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:34:17.0',22,34);--end
 INSERT INTO tq_visit_statistics VALUES(39,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:34:42.0',22,34);--end
 INSERT INTO tq_visit_statistics VALUES(40,1,'http://localhost:8080/javapms/china/212.html','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:34:44.0',22,34);--end
 INSERT INTO tq_visit_statistics VALUES(41,1,'http://localhost:8080/javapms/china/212.html','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:35:11.0',22,35);--end
 INSERT INTO tq_visit_statistics VALUES(42,1,'http://localhost:8080/javapms/comment-212.jsp','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:35:17.0',22,35);--end
 INSERT INTO tq_visit_statistics VALUES(43,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:35:41.0',22,35);--end
 INSERT INTO tq_visit_statistics VALUES(44,1,'http://localhost:8080/javapms/china/212.html','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:38:58.0',22,38);--end
 INSERT INTO tq_visit_statistics VALUES(45,1,'http://localhost:8080/javapms/comment-212.jsp','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:41:55.0',22,41);--end
 INSERT INTO tq_visit_statistics VALUES(46,1,'http://localhost:8080/javapms/comment-212.jsp','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:47:52.0',22,47);--end
 INSERT INTO tq_visit_statistics VALUES(47,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-26 00:41:40.0',0,41);--end
 INSERT INTO tq_visit_statistics VALUES(48,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-26 00:41:59.0',0,41);--end
 INSERT INTO tq_visit_statistics VALUES(49,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-26 00:43:29.0',0,43);--end
 INSERT INTO tq_visit_statistics VALUES(50,1,'http://localhost:8080/javapms/forum.jsp','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-26 00:43:38.0',0,43);--end
 INSERT INTO tq_visit_statistics VALUES(51,1,'http://localhost:8080/javapms/themeList-1.jsp','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-26 00:43:41.0',0,43);--end
 INSERT INTO tq_visit_statistics VALUES(52,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-26 00:56:29.0',0,56);--end
 INSERT INTO tq_visit_statistics VALUES(53,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-26 00:56:37.0',0,56);--end
 INSERT INTO tq_visit_statistics VALUES(54,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-26 00:57:01.0',0,57);--end
 INSERT INTO tq_visit_statistics VALUES(55,1,'http://localhost:8080/comment-212.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:06:45.0',9,6);--end
 INSERT INTO tq_visit_statistics VALUES(56,1,'http://localhost:8080/forum.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:06:50.0',9,6);--end
 INSERT INTO tq_visit_statistics VALUES(57,1,'http://localhost:8080/themeList-1.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:06:52.0',9,6);--end
 INSERT INTO tq_visit_statistics VALUES(58,1,'http://localhost:8080/messageboard.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:06:56.0',9,6);--end
 INSERT INTO tq_visit_statistics VALUES(59,1,'http://localhost:8080/questionList.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:07:04.0',9,7);--end
 INSERT INTO tq_visit_statistics VALUES(60,1,'http://localhost:8080/question-1.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:07:09.0',9,7);--end
 INSERT INTO tq_visit_statistics VALUES(61,1,'http://localhost:8080/search-mId-3-q-1.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:07:21.0',9,7);--end
 INSERT INTO tq_visit_statistics VALUES(62,1,'http://localhost:8080/search-mId-3-q-%E8%BD%A6.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:07:29.0',9,7);--end
 INSERT INTO tq_visit_statistics VALUES(63,1,'http://localhost:8080/search-mId-3-q-%E8%BD%A6.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:07:38.0',9,7);--end
 INSERT INTO tq_visit_statistics VALUES(64,1,'http://localhost:8080/search-mId-3-q-%E8%BD%A6.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:07:54.0',9,7);--end
 INSERT INTO tq_visit_statistics VALUES(65,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:07:57.0',9,7);--end
 INSERT INTO tq_visit_statistics VALUES(66,1,'http://localhost:8080/forum.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:08:08.0',9,8);--end
 INSERT INTO tq_visit_statistics VALUES(67,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:10:47.0',9,10);--end
 INSERT INTO tq_visit_statistics VALUES(68,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:25:10.0',9,25);--end
 INSERT INTO tq_visit_statistics VALUES(69,1,'http://localhost:8080/china/214.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:25:14.0',9,25);--end
 INSERT INTO tq_visit_statistics VALUES(70,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:42:10.0',9,42);--end
 INSERT INTO tq_visit_statistics VALUES(71,1,'http://localhost:8080/photo/211.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:42:26.0',9,42);--end
 INSERT INTO tq_visit_statistics VALUES(72,1,'http://localhost:8080/search-mId-3-q-%E5%A5%B3.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:42:59.0',9,42);--end
 INSERT INTO tq_visit_statistics VALUES(73,1,'http://localhost:8080/guide/index.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:43:02.0',9,43);--end
 INSERT INTO tq_visit_statistics VALUES(74,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:43:08.0',9,43);--end
 INSERT INTO tq_visit_statistics VALUES(75,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:45:24.0',9,45);--end
 INSERT INTO tq_visit_statistics VALUES(76,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:51:08.0',9,51);--end
 INSERT INTO tq_visit_statistics VALUES(77,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','66eea6dcbec04e6ba5d1e6f26774429d','2014-05-26 09:51:14.0',9,51);--end
 INSERT INTO tq_visit_statistics VALUES(78,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','85c6a8a6ae1f494e9cd0964b720f28d1','2014-05-26 09:53:36.0',9,53);--end
 INSERT INTO tq_visit_statistics VALUES(79,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:04:38.0',10,4);--end
 INSERT INTO tq_visit_statistics VALUES(80,1,'http://localhost:8080/fcnews/index.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:22:21.0',10,22);--end
 INSERT INTO tq_visit_statistics VALUES(81,1,'http://localhost:8080/videos/82.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:22:43.0',10,22);--end
 INSERT INTO tq_visit_statistics VALUES(82,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:26:46.0',10,26);--end
 INSERT INTO tq_visit_statistics VALUES(83,1,'http://localhost:8080/china/53.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:26:48.0',10,26);--end
 INSERT INTO tq_visit_statistics VALUES(84,1,'http://localhost:8080/china/53_2.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:26:51.0',10,26);--end
 INSERT INTO tq_visit_statistics VALUES(85,1,'http://localhost:8080/china/53_3.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:26:53.0',10,26);--end
 INSERT INTO tq_visit_statistics VALUES(86,1,'http://localhost:8080/china/53_4.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:26:55.0',10,26);--end
 INSERT INTO tq_visit_statistics VALUES(87,1,'http://localhost:8080/china/53_5.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:26:59.0',10,26);--end
 INSERT INTO tq_visit_statistics VALUES(88,1,'http://localhost:8080/china/53_6.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:27:02.0',10,27);--end
 INSERT INTO tq_visit_statistics VALUES(89,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:35:30.0',10,35);--end
 INSERT INTO tq_visit_statistics VALUES(90,1,'http://localhost:8080/china/53.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:35:37.0',10,35);--end
 INSERT INTO tq_visit_statistics VALUES(91,1,'http://localhost:8080/comment-53.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:37:41.0',10,37);--end
 INSERT INTO tq_visit_statistics VALUES(92,1,'http://localhost:8080/comment-53.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:38:06.0',10,38);--end
 INSERT INTO tq_visit_statistics VALUES(93,1,'http://localhost:8080/china/53.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:38:44.0',10,38);--end
 INSERT INTO tq_visit_statistics VALUES(94,1,'http://localhost:8080/china/212.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:38:50.0',10,38);--end
 INSERT INTO tq_visit_statistics VALUES(95,1,'http://localhost:8080/comment-212.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:38:52.0',10,38);--end
 INSERT INTO tq_visit_statistics VALUES(96,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:40:27.0',10,40);--end
 INSERT INTO tq_visit_statistics VALUES(97,1,'http://localhost:8080/photo/211.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:40:43.0',10,40);--end
 INSERT INTO tq_visit_statistics VALUES(98,1,'http://localhost:8080/photo/211.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:41:57.0',10,41);--end
 INSERT INTO tq_visit_statistics VALUES(99,1,'http://localhost:8080/comment-211.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:42:00.0',10,42);--end
 INSERT INTO tq_visit_statistics VALUES(100,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:43:15.0',10,43);--end
 INSERT INTO tq_visit_statistics VALUES(101,1,'http://localhost:8080/china/53.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:43:17.0',10,43);--end
 INSERT INTO tq_visit_statistics VALUES(102,1,'http://localhost:8080/jingdian/197.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:43:57.0',10,43);--end
 INSERT INTO tq_visit_statistics VALUES(103,1,'http://localhost:8080/china/53.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:44:09.0',10,44);--end
 INSERT INTO tq_visit_statistics VALUES(104,1,'http://localhost:8080/it/194.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:44:16.0',10,44);--end
 INSERT INTO tq_visit_statistics VALUES(105,1,'http://localhost:8080/clothes/196.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:44:56.0',10,44);--end
 INSERT INTO tq_visit_statistics VALUES(106,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 11:06:34.0',11,6);--end
 INSERT INTO tq_visit_statistics VALUES(107,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 11:07:52.0',11,7);--end
 INSERT INTO tq_visit_statistics VALUES(108,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 11:09:27.0',11,9);--end
 INSERT INTO tq_visit_statistics VALUES(109,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 11:25:02.0',11,25);--end
 INSERT INTO tq_visit_statistics VALUES(110,1,'http://localhost:8080/china/53.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 11:25:06.0',11,25);--end
 INSERT INTO tq_visit_statistics VALUES(111,1,'http://localhost:8080/china/53_2.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 11:25:09.0',11,25);--end
 INSERT INTO tq_visit_statistics VALUES(112,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:37:30.0',17,37);--end
 INSERT INTO tq_visit_statistics VALUES(113,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:37:59.0',17,37);--end
 INSERT INTO tq_visit_statistics VALUES(114,1,'http://localhost:8080/internet/193.html','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:38:05.0',17,38);--end
 INSERT INTO tq_visit_statistics VALUES(115,1,'http://localhost:8080/comment-193.jsp','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:38:07.0',17,38);--end
 INSERT INTO tq_visit_statistics VALUES(116,1,'http://localhost:8080/comment-193.jsp','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:38:25.0',17,38);--end
 INSERT INTO tq_visit_statistics VALUES(117,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:38:53.0',17,38);--end
 INSERT INTO tq_visit_statistics VALUES(118,1,'http://localhost:8080/china/53.html','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:38:54.0',17,38);--end
 INSERT INTO tq_visit_statistics VALUES(119,1,'http://localhost:8080/internet/193.html','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:40:57.0',17,40);--end
 INSERT INTO tq_visit_statistics VALUES(120,1,'http://localhost:8080/comment-193.jsp','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:41:04.0',17,41);--end
 INSERT INTO tq_visit_statistics VALUES(121,1,'http://localhost:8080/comment-193.jsp','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:44:35.0',17,44);--end
 INSERT INTO tq_visit_statistics VALUES(122,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:46:54.0',17,46);--end
 INSERT INTO tq_visit_statistics VALUES(123,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:46:55.0',17,46);--end
 INSERT INTO tq_visit_statistics VALUES(124,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:48:23.0',17,48);--end
 INSERT INTO tq_visit_statistics VALUES(125,1,'http://localhost:8080/china/index.html','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:48:36.0',17,48);--end
 INSERT INTO tq_visit_statistics VALUES(126,1,'http://localhost:8080/china/212.html','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:48:42.0',17,48);--end
 INSERT INTO tq_visit_statistics VALUES(127,1,'http://localhost:8080/china/212.html','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:48:45.0',17,48);--end
 INSERT INTO tq_visit_statistics VALUES(128,1,'http://localhost:8080/china/212.html','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:48:48.0',17,48);--end
 INSERT INTO tq_visit_statistics VALUES(129,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:49:19.0',17,49);--end
 INSERT INTO tq_visit_statistics VALUES(130,1,'http://localhost:8080/china/215.html','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:49:24.0',17,49);--end
 INSERT INTO tq_visit_statistics VALUES(131,1,'http://localhost:8080/china/index.html','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:49:27.0',17,49);--end
 INSERT INTO tq_visit_statistics VALUES(132,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','85c6a8a6ae1f494e9cd0964b720f28d1','2014-05-26 17:53:06.0',17,53);--end
 INSERT INTO tq_visit_statistics VALUES(133,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','85c6a8a6ae1f494e9cd0964b720f28d1','2014-05-26 17:53:14.0',17,53);--end
 INSERT INTO tq_visit_statistics VALUES(134,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','85c6a8a6ae1f494e9cd0964b720f28d1','2014-05-26 17:53:20.0',17,53);--end
 INSERT INTO tq_visit_statistics VALUES(135,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','85c6a8a6ae1f494e9cd0964b720f28d1','2014-05-26 17:53:22.0',17,53);--end
 INSERT INTO tq_visit_statistics VALUES(136,1,'http://localhost:8080/news/index.html','0:0:0:0:0:0:0:1','85c6a8a6ae1f494e9cd0964b720f28d1','2014-05-26 17:53:27.0',17,53);--end
 INSERT INTO tq_visit_statistics VALUES(137,1,'http://localhost:8080/news/index.html','0:0:0:0:0:0:0:1','85c6a8a6ae1f494e9cd0964b720f28d1','2014-05-26 17:53:30.0',17,53);--end
 INSERT INTO tq_visit_statistics VALUES(138,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:53:35.0',17,53);--end
 INSERT INTO tq_visit_statistics VALUES(139,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:54:40.0',17,54);--end
 INSERT INTO tq_visit_statistics VALUES(140,1,'http://localhost:8080/news/index.html','0:0:0:0:0:0:0:1','85c6a8a6ae1f494e9cd0964b720f28d1','2014-05-26 17:54:44.0',17,54);--end
 INSERT INTO tq_visit_statistics VALUES(141,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 18:00:50.0',18,0);--end
 INSERT INTO tq_visit_statistics VALUES(142,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 18:00:57.0',18,0);--end
 INSERT INTO tq_visit_statistics VALUES(143,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 18:01:36.0',18,1);--end
 INSERT INTO tq_visit_statistics VALUES(144,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 18:46:35.0',18,46);--end
 INSERT INTO tq_visit_statistics VALUES(145,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 18:47:39.0',18,47);--end
 INSERT INTO tq_visit_statistics VALUES(146,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 18:47:47.0',18,47);--end
 INSERT INTO tq_visit_statistics VALUES(147,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 18:47:54.0',18,47);--end
 INSERT INTO tq_visit_statistics VALUES(148,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 18:48:26.0',18,48);--end
 INSERT INTO tq_visit_statistics VALUES(149,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 18:50:10.0',18,50);--end
DROP TABLE IF EXISTS tq_work_exp;--end
CREATE TABLE tq_work_exp
(
   exp_id           INT(10) NOT NULL AUTO_INCREMENT,
   resume_id           INT(10) NOT NULL COMMENT '简历ID',
   metier_id           INT(10) NOT NULL COMMENT '职业ID',
   start_time           VARCHAR(30) NOT NULL COMMENT '开始时间',
   end_time           VARCHAR(30) NOT NULL COMMENT '截止时间',
   com_name           VARCHAR(50) NOT NULL COMMENT '公司名称',
   com_scale           SMALLINT(5) NOT NULL COMMENT '公司规模',
   com_nature           INT(10) COMMENT '公司性质',
   com_industry           INT(10) COMMENT '公司行业',
   depart_name           VARCHAR(50) COMMENT '所在部门',
   wage           SMALLINT(5) NOT NULL COMMENT '待遇情况',
   is_oversea           SMALLINT(5) NOT NULL COMMENT '是否有海外经历',
   description           VARCHAR(2000) COMMENT '描述',
   create_time           DATETIME NOT NULL COMMENT '创建时间',
   PRIMARY KEY(exp_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
DROP TABLE IF EXISTS tq_work_flow;--end
CREATE TABLE tq_work_flow
(
   flow_id           INT(10) NOT NULL AUTO_INCREMENT,
   flow_name           VARCHAR(50) NOT NULL COMMENT '工作流名称',
   description           VARCHAR(200) COMMENT '描述',
   create_time           DATETIME NOT NULL COMMENT '录入时间',
   step_count           INT(10) NOT NULL DEFAULT '2',
   site_id           INT(10) NOT NULL DEFAULT '1' COMMENT '站点ID',
   PRIMARY KEY(flow_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;--end
 INSERT INTO tq_work_flow VALUES(5,'新闻报送','新闻报送（信息报送员-->管理员）','2013-06-21 17:31:57.0',2,1);--end
ALTER TABLE tq_admin ADD CONSTRAINT fk_admin_to_user FOREIGN KEY (admin_id) REFERENCES tq_user (user_id);--end
ALTER TABLE tq_admin_check ADD CONSTRAINT fk_admincheck_to_admin FOREIGN KEY (admin_id) REFERENCES tq_admin (admin_id);--end
ALTER TABLE tq_admin_check ADD CONSTRAINT fk_admincheck_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_admin_depart ADD CONSTRAINT fk_admin_depart_to_admin FOREIGN KEY (admin_id) REFERENCES tq_admin (admin_id);--end
ALTER TABLE tq_admin_depart ADD CONSTRAINT fk_admin_depart_to_depart FOREIGN KEY (depart_id) REFERENCES tq_depart (depart_id);--end
ALTER TABLE tq_admin_role ADD CONSTRAINT fk_adminrole_to_admin FOREIGN KEY (admin_id) REFERENCES tq_admin (admin_id);--end
ALTER TABLE tq_admin_role ADD CONSTRAINT fk_adminrole_to_role FOREIGN KEY (role_id) REFERENCES tq_role (role_id);--end
ALTER TABLE tq_advert ADD CONSTRAINT fk_advert_slot_to_advert FOREIGN KEY (slot_id) REFERENCES tq_advert_slot (slot_id);--end
ALTER TABLE tq_advert ADD CONSTRAINT fk_advert_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_advert_slot ADD CONSTRAINT fk_advert_slot_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_article ADD CONSTRAINT fk_tq_articlechannel FOREIGN KEY (channel_id) REFERENCES tq_channel (channel_id);--end
ALTER TABLE tq_article ADD CONSTRAINT fk_article_to_depart FOREIGN KEY (depart_id) REFERENCES tq_depart (depart_id);--end
ALTER TABLE tq_article ADD CONSTRAINT fk_article_to_role FOREIGN KEY (role_id) REFERENCES tq_role (role_id);--end
ALTER TABLE tq_article ADD CONSTRAINT fk_tq_article_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_article_attachment ADD CONSTRAINT fk_tq_attachment_article FOREIGN KEY (article_id) REFERENCES tq_article (article_id);--end
ALTER TABLE tq_article_attr ADD CONSTRAINT fk_tq_attr_article FOREIGN KEY (article_id) REFERENCES tq_article (article_id);--end
ALTER TABLE tq_article_channel ADD CONSTRAINT fk_tq_article_channel FOREIGN KEY (channel_id) REFERENCES tq_channel (channel_id);--end
ALTER TABLE tq_article_channel ADD CONSTRAINT fk_tq_channel_article FOREIGN KEY (article_id) REFERENCES tq_article (article_id);--end
ALTER TABLE tq_article_ext ADD CONSTRAINT fk_tq_ext_article FOREIGN KEY (article_id) REFERENCES tq_article (article_id);--end
ALTER TABLE tq_article_group_view ADD CONSTRAINT fk_tq_group_article_v FOREIGN KEY (article_id) REFERENCES tq_article (article_id);--end
ALTER TABLE tq_article_picture ADD CONSTRAINT fk_tq_picture_article FOREIGN KEY (article_id) REFERENCES tq_article (article_id);--end
ALTER TABLE tq_article_sign ADD CONSTRAINT fk_article_sign_to_article FOREIGN KEY (article_id) REFERENCES tq_article (article_id);--end
ALTER TABLE tq_article_sign ADD CONSTRAINT fk_article_sign_to_admin FOREIGN KEY (admin_id) REFERENCES tq_admin (admin_id);--end
ALTER TABLE tq_article_sign ADD CONSTRAINT fk_article_sign_to_depart FOREIGN KEY (depart_id) REFERENCES tq_depart (depart_id);--end
ALTER TABLE tq_article_txt ADD CONSTRAINT fk_tq_txt_article FOREIGN KEY (article_id) REFERENCES tq_article (article_id);--end
ALTER TABLE tq_category ADD CONSTRAINT fk_category_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_channel ADD CONSTRAINT fk_tq_channel_parent FOREIGN KEY (parent_id) REFERENCES tq_channel (channel_id);--end
ALTER TABLE tq_channel ADD CONSTRAINT fk_tq_channel_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_channel_ext ADD CONSTRAINT fk_tq_ext_channel FOREIGN KEY (channel_id) REFERENCES tq_channel (channel_id);--end
ALTER TABLE tq_channel_txt ADD CONSTRAINT fk_tq_txt_channel FOREIGN KEY (channel_id) REFERENCES tq_channel (channel_id);--end
ALTER TABLE tq_chnl_group_contri ADD CONSTRAINT fk_tq_group_channel_c FOREIGN KEY (channel_id) REFERENCES tq_channel (channel_id);--end
ALTER TABLE tq_chnl_group_view ADD CONSTRAINT fk_tq_group_channel_v FOREIGN KEY (channel_id) REFERENCES tq_channel (channel_id);--end
ALTER TABLE tq_comment ADD CONSTRAINT fk_comment_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_comment ADD CONSTRAINT fk_comment_to_comment FOREIGN KEY (parent_id) REFERENCES tq_comment (comment_id);--end
ALTER TABLE tq_comment ADD CONSTRAINT fk_comment_to_doc FOREIGN KEY (doc_id) REFERENCES tq_article (article_id);--end
ALTER TABLE tq_comment ADD CONSTRAINT fk_comment_to_user FOREIGN KEY (user_id) REFERENCES tq_user (user_id);--end
ALTER TABLE tq_comment_ext ADD CONSTRAINT fk_commentext_to_comment FOREIGN KEY (comment_id) REFERENCES tq_comment (comment_id);--end
ALTER TABLE tq_company_fairs ADD CONSTRAINT fk_company_comfairs FOREIGN KEY (company_id) REFERENCES tq_company_info (company_id);--end
ALTER TABLE tq_company_fairs ADD CONSTRAINT fk_fairs_comfairs FOREIGN KEY (fairs_id) REFERENCES tq_job_fairs (fairs_id);--end
ALTER TABLE tq_company_favorite ADD CONSTRAINT fk_company_resume FOREIGN KEY (company_id) REFERENCES tq_company_info (company_id);--end
ALTER TABLE tq_company_favorite ADD CONSTRAINT fk_resume_company FOREIGN KEY (resume_id) REFERENCES tq_resume (resume_id);--end
ALTER TABLE tq_company_info ADD CONSTRAINT FK_TYPE_TO_VIP FOREIGN KEY (vip_type) REFERENCES tq_vip_type (type_id);--end
ALTER TABLE tq_consul ADD CONSTRAINT fk_consul_to_article FOREIGN KEY (article_id) REFERENCES tq_article (article_id);--end
ALTER TABLE tq_consul ADD CONSTRAINT fk_consul_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_depart ADD CONSTRAINT fk_depart_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_depart ADD CONSTRAINT fk_depart_to_depart FOREIGN KEY (parent_id) REFERENCES tq_depart (depart_id);--end
ALTER TABLE tq_depart ADD CONSTRAINT fk_depart_to_workflow FOREIGN KEY (flow_id) REFERENCES tq_work_flow (flow_id);--end
ALTER TABLE tq_depart_channel ADD CONSTRAINT fk_departchannel_channel FOREIGN KEY (channel_id) REFERENCES tq_channel (channel_id);--end
ALTER TABLE tq_doc_statistics ADD CONSTRAINT fk_statis_to_doc FOREIGN KEY (doc_id) REFERENCES tq_article (article_id);--end
ALTER TABLE tq_evaluate ADD CONSTRAINT fk_evaluate_to_article FOREIGN KEY (article_id) REFERENCES tq_article (article_id);--end
ALTER TABLE tq_evaluate ADD CONSTRAINT fk_evaluate_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_flow_channel ADD CONSTRAINT fk_flowchannel_channel FOREIGN KEY (channel_id) REFERENCES tq_channel (channel_id);--end
ALTER TABLE tq_flow_channel ADD CONSTRAINT fk_flowchannel_flow FOREIGN KEY (flow_id) REFERENCES tq_work_flow (flow_id);--end
ALTER TABLE tq_flow_detail ADD CONSTRAINT fk_flowdetail_to_role FOREIGN KEY (role_id) REFERENCES tq_role (role_id);--end
ALTER TABLE tq_flow_detail ADD CONSTRAINT fk_flowdetail_to_doc FOREIGN KEY (doc_id) REFERENCES tq_article (article_id);--end
ALTER TABLE tq_flow_detail ADD CONSTRAINT fk_flowdetail_to_user FOREIGN KEY (user_id) REFERENCES tq_user (user_id);--end
ALTER TABLE tq_flow_step ADD CONSTRAINT fk_flowstep_flow FOREIGN KEY (flow_id) REFERENCES tq_work_flow (flow_id);--end
ALTER TABLE tq_forum ADD CONSTRAINT fk_forum_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_forum ADD CONSTRAINT fk_forum_to_category FOREIGN KEY (category_id) REFERENCES tq_category (category_id);--end
ALTER TABLE tq_forum ADD CONSTRAINT fk_forum_to_reply FOREIGN KEY (last_replyer_id) REFERENCES tq_user (user_id);--end
ALTER TABLE tq_forum ADD CONSTRAINT fk_forum_to_theme FOREIGN KEY (last_theme_id) REFERENCES tq_theme (theme_id);--end
ALTER TABLE tq_forum_ext ADD CONSTRAINT fk_forumext_to_forum FOREIGN KEY (forum_id) REFERENCES tq_forum (forum_id);--end
ALTER TABLE tq_forum_operate ADD CONSTRAINT fk_operate_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_forum_operate ADD CONSTRAINT fk_operate_to_user FOREIGN KEY (operate_admin) REFERENCES tq_user (user_id);--end
ALTER TABLE tq_forum_statis ADD CONSTRAINT fk_forumstatis_to_site FOREIGN KEY (statis_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_group ADD CONSTRAINT fk_group_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_group_perm ADD CONSTRAINT fk_groupprem_to_group FOREIGN KEY (group_id) REFERENCES tq_group (group_id);--end
ALTER TABLE tq_job_post ADD CONSTRAINT fk_job_company FOREIGN KEY (company_id) REFERENCES tq_company_info (company_id);--end
ALTER TABLE tq_keyword ADD CONSTRAINT fk_keyword_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_links ADD CONSTRAINT fk_links_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_links_type ADD CONSTRAINT fk_links_type_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_log ADD CONSTRAINT fk_tq_log_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_mailbox ADD CONSTRAINT fk_mailbox_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_mailbox ADD CONSTRAINT fk_mailbox_to_depart FOREIGN KEY (depart_id) REFERENCES tq_depart (depart_id);--end
ALTER TABLE tq_mailbox ADD CONSTRAINT fk_mailbox_to_type FOREIGN KEY (type_id) REFERENCES tq_mailbox_type (type_id);--end
ALTER TABLE tq_mailbox_ext ADD CONSTRAINT fk_mailboxext_to_mailbox FOREIGN KEY (mailbox_id) REFERENCES tq_mailbox (mailbox_id);--end
ALTER TABLE tq_mailbox_type ADD CONSTRAINT fk_mailboxtype_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_member ADD CONSTRAINT fk_member_to_user FOREIGN KEY (member_id) REFERENCES tq_user (user_id);--end
ALTER TABLE tq_member_group ADD CONSTRAINT fk_membergroup_to_group FOREIGN KEY (group_id) REFERENCES tq_group (group_id);--end
ALTER TABLE tq_member_group ADD CONSTRAINT fk_membergroup_to_member FOREIGN KEY (member_id) REFERENCES tq_member (member_id);--end
ALTER TABLE tq_message_board ADD CONSTRAINT fk_message_board_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_message_board ADD CONSTRAINT fk_message_board_to_type FOREIGN KEY (type_id) REFERENCES tq_message_type (type_id);--end
ALTER TABLE tq_message_board_ext ADD CONSTRAINT fk_board_ext_to_message_board FOREIGN KEY (board_id) REFERENCES tq_message_board (board_id);--end
ALTER TABLE tq_message_receive ADD CONSTRAINT fk_message_receive_to_message FOREIGN KEY (message_id) REFERENCES tq_site_message (message_id);--end
ALTER TABLE tq_message_type ADD CONSTRAINT fk_message_type_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_model_field ADD CONSTRAINT fk_tq_item_model FOREIGN KEY (model_id) REFERENCES tq_model (model_id);--end
ALTER TABLE tq_personal_favorite ADD CONSTRAINT fk_job_personal FOREIGN KEY (job_id) REFERENCES tq_job_post (job_id);--end
ALTER TABLE tq_personal_favorite ADD CONSTRAINT fk_personal_job FOREIGN KEY (personal_id) REFERENCES tq_personal_info (personal_id);--end
ALTER TABLE tq_personal_info ADD CONSTRAINT FK_PERSONAL_SPECIAL FOREIGN KEY (special_id) REFERENCES tq_specialty (special_id);--end
ALTER TABLE tq_petition ADD CONSTRAINT fk_petition_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_petition ADD CONSTRAINT fk_petition_to_type FOREIGN KEY (type_id) REFERENCES tq_petition_type (type_id);--end
ALTER TABLE tq_petition_ext ADD CONSTRAINT fk_petitionext_to_petition FOREIGN KEY (petition_id) REFERENCES tq_petition (petition_id);--end
ALTER TABLE tq_petition_type ADD CONSTRAINT fk_petitiontype_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_posts ADD CONSTRAINT fk_posts_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_posts ADD CONSTRAINT fk_posts_to_creater FOREIGN KEY (creater_id) REFERENCES tq_user (user_id);--end
ALTER TABLE tq_posts ADD CONSTRAINT fk_posts_to_theme FOREIGN KEY (theme_id) REFERENCES tq_theme (theme_id);--end
ALTER TABLE tq_posts_attach ADD CONSTRAINT fk_postattach_to_posts FOREIGN KEY (posts_id) REFERENCES tq_posts (posts_id);--end
ALTER TABLE tq_posts_ext ADD CONSTRAINT fk_postsext_to_editer FOREIGN KEY (editer_id) REFERENCES tq_user (user_id);--end
ALTER TABLE tq_posts_ext ADD CONSTRAINT fk_postsext_to_posts FOREIGN KEY (posts_id) REFERENCES tq_posts (posts_id);--end
ALTER TABLE tq_posts_txt ADD CONSTRAINT fk_poststxt_to_posts FOREIGN KEY (posts_id) REFERENCES tq_posts (posts_id);--end
ALTER TABLE tq_question_detail ADD CONSTRAINT fk_question_detail_to_question FOREIGN KEY (question_id) REFERENCES tq_questionnaire (naire_id);--end
ALTER TABLE tq_questionnaire ADD CONSTRAINT fk_questionnaire_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_resume ADD CONSTRAINT fk_resume_personal FOREIGN KEY (personal_id) REFERENCES tq_personal_info (personal_id);--end
ALTER TABLE tq_resume_apply ADD CONSTRAINT fk_job_resume_app FOREIGN KEY (job_id) REFERENCES tq_job_post (job_id);--end
ALTER TABLE tq_resume_apply ADD CONSTRAINT fk_resume_job_app FOREIGN KEY (resume_id) REFERENCES tq_resume (resume_id);--end
ALTER TABLE tq_role ADD CONSTRAINT fk_role_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_role_perm ADD CONSTRAINT fk_roleperm_to_role FOREIGN KEY (role_id) REFERENCES tq_role (role_id);--end
ALTER TABLE tq_site_message ADD CONSTRAINT fk_site_message_to_user FOREIGN KEY (send_id) REFERENCES tq_user (user_id);--end
ALTER TABLE tq_site_message_status ADD CONSTRAINT fk_message_status_to_message FOREIGN KEY (message_id) REFERENCES tq_site_message (message_id);--end
ALTER TABLE tq_site_message_status ADD CONSTRAINT fk_message_status_to_user FOREIGN KEY (receive_id) REFERENCES tq_user (user_id);--end
ALTER TABLE tq_survey_detail ADD CONSTRAINT fk_survey_detail_to_survey FOREIGN KEY (survey_id) REFERENCES tq_survey_theme (theme_id);--end
ALTER TABLE tq_survey_item ADD CONSTRAINT fk_survey_item_to_theme FOREIGN KEY (theme_id) REFERENCES tq_survey_theme (theme_id);--end
ALTER TABLE tq_survey_theme ADD CONSTRAINT fk_survey_theme_to_naire FOREIGN KEY (naire_id) REFERENCES tq_questionnaire (naire_id);--end
ALTER TABLE tq_theme ADD CONSTRAINT fk_theme_to_replyuser FOREIGN KEY (last_replyer_id) REFERENCES tq_user (user_id);--end
ALTER TABLE tq_theme ADD CONSTRAINT fk_theme_to_createuser FOREIGN KEY (creater_id) REFERENCES tq_user (user_id);--end
ALTER TABLE tq_theme ADD CONSTRAINT fk_theme_to_forum FOREIGN KEY (forum_id) REFERENCES tq_forum (forum_id);--end
ALTER TABLE tq_theme ADD CONSTRAINT fk_theme_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
ALTER TABLE tq_theme_txt ADD CONSTRAINT fk_themetxt_to_theme FOREIGN KEY (theme_id) REFERENCES tq_theme (theme_id);--end
ALTER TABLE tq_user_forum ADD CONSTRAINT fk_userforum_to_user FOREIGN KEY (user_id) REFERENCES tq_user (user_id);--end
ALTER TABLE tq_work_flow ADD CONSTRAINT fk_workflow_to_site FOREIGN KEY (site_id) REFERENCES tq_site (site_id);--end
SET FOREIGN_KEY_CHECKS = 1;--end

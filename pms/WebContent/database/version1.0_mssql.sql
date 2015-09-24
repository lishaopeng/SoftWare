
CREATE DATABASE /*!32312 IF NOT EXISTS*/ pms  /*!40100 DEFAULT CHARACTER SET utf8 */;

USE  pms ;

CREATE TABLE  tq_admin  (
   admin_id  int NOT NULL,
   registe_time  datetime NOT NULL COMMENT '注册时间',
   registe_ip  varchar(20) DEFAULT NULL COMMENT '注册IP',
   last_login_time  datetime DEFAULT NULL COMMENT '最后登录时间',
   last_login_ip  varchar(20) DEFAULT NULL COMMENT '最后登录IP',
   login_count  int NOT NULL COMMENT '登录次数',
   t_status  int NOT NULL COMMENT '状态',
  PRIMARY KEY ( admin_id ),
  CONSTRAINT  fk_admin_to_user  FOREIGN KEY ( admin_id ) REFERENCES  tq_user  ( user_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


insert  into  tq_admin ( admin_id , registe_time , registe_ip , last_login_time , last_login_ip , login_count , t_status ) values (1,'2013-04-25 22:22:22','0:0:0:0:0:0:0:1','2015-09-09 15:42:52','0:0:0:0:0:0:0:1',1281,0),(5,'2013-05-27 08:08:04','0:0:0:0:0:0:0:1','2014-05-25 22:29:06','0:0:0:0:0:0:0:1',28,0);

CREATE TABLE  tq_admin_channel  (
   admin_id  int NOT NULL,
   channel_id  int NOT NULL,
  PRIMARY KEY ( admin_id , channel_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE  tq_admin_check  (
   check_id  int NOT NULL AUTO_INCREMENT,
   admin_id  int NOT NULL COMMENT '管理员ID',
   site_id  int NOT NULL COMMENT '站点ID',
   mng_status  int NOT NULL COMMENT '管理状态',
   is_take_depart  bit(1) NOT NULL DEFAULT b'1' COMMENT '采用部门栏目权限',
  PRIMARY KEY ( check_id ),
  KEY  fk_admincheck_to_admin  ( admin_id ),
  KEY  fk_admincheck_to_site  ( site_id ),
  CONSTRAINT  fk_admincheck_to_admin  FOREIGN KEY ( admin_id ) REFERENCES  tq_admin  ( admin_id ),
  CONSTRAINT  fk_admincheck_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id )
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

insert  into  tq_admin_check ( check_id , admin_id , site_id , mng_status , is_take_depart ) values (1,1,1,4,''),(3,5,1,1,''),(12,1,9,4,''),(19,1,18,4,''),(22,1,21,4,''),(24,1,23,4,'');

CREATE TABLE  tq_admin_depart  (
   depart_id  int NOT NULL COMMENT '部门ID',
   admin_id  int NOT NULL COMMENT '管理员ID',
  PRIMARY KEY ( admin_id , depart_id ),
  KEY  fk_admin_depart_to_depart  ( depart_id ),
  CONSTRAINT  fk_admin_depart_to_admin  FOREIGN KEY ( admin_id ) REFERENCES  tq_admin  ( admin_id ),
  CONSTRAINT  fk_admin_depart_to_depart  FOREIGN KEY ( depart_id ) REFERENCES  tq_depart  ( depart_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_admin_depart  */

insert  into  tq_admin_depart ( depart_id , admin_id ) values (1,1),(2,5),(8,1),(17,1),(20,1),(22,1);

/*Table structure for table  tq_admin_role  */

DROP TABLE IF EXISTS  tq_admin_role ;

CREATE TABLE  tq_admin_role  (
   role_id  int NOT NULL COMMENT '角色ID',
   admin_id  int NOT NULL COMMENT '管理员ID',
  PRIMARY KEY ( admin_id , role_id ),
  KEY  fk_adminrole_to_role  ( role_id ),
  CONSTRAINT  fk_adminrole_to_admin  FOREIGN KEY ( admin_id ) REFERENCES  tq_admin  ( admin_id ),
  CONSTRAINT  fk_adminrole_to_role  FOREIGN KEY ( role_id ) REFERENCES  tq_role  ( role_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_admin_role  */

insert  into  tq_admin_role ( role_id , admin_id ) values (1,1),(2,5),(11,1),(20,1),(23,1),(25,1);

/*Table structure for table  tq_advert  */

DROP TABLE IF EXISTS  tq_advert ;

CREATE TABLE  tq_advert  (
   advert_id  int NOT NULL AUTO_INCREMENT,
   site_id  int NOT NULL,
   slot_id  int NOT NULL COMMENT '广告位',
   name  varchar(50) NOT NULL COMMENT '广告名称',
   adv_type  varchar(20) NOT NULL COMMENT '广告类型',
   attr_url  varchar(80) DEFAULT NULL COMMENT '附件地址',
   attr_url2  varchar(100) DEFAULT NULL COMMENT '附件地址2',
   jsstring  varchar(300) DEFAULT NULL COMMENT 'JS内容',
   url  varchar(100) NOT NULL COMMENT '链接',
   start_time  date NOT NULL COMMENT '投放时间',
   end_time  date DEFAULT NULL COMMENT '结束时间',
   priority  int NOT NULL COMMENT '优先级',
   weights  int DEFAULT NULL COMMENT '权重',
   clicks  int NOT NULL COMMENT '点击次数',
   show_times  int DEFAULT NULL COMMENT '展现次数',
   aexplain  varchar(500) DEFAULT NULL COMMENT '说明',
   enable  bit(1) DEFAULT NULL COMMENT '是否启用',
  PRIMARY KEY ( advert_id ),
  KEY  fk_advert_slot_to_advert  ( slot_id ),
  KEY  fk_advert_to_site  ( site_id ),
  CONSTRAINT  fk_advert_slot_to_advert  FOREIGN KEY ( slot_id ) REFERENCES  tq_advert_slot  ( slot_id ),
  CONSTRAINT  fk_advert_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id )
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table  tq_advert  */

insert  into  tq_advert ( advert_id , site_id , slot_id , name , adv_type , attr_url , attr_url2 , jsstring , url , start_time , end_time , priority , weights , clicks , show_times , aexplain , enable ) values (1,1,2,'首页通栏广告一','img','/member/upload/pms/201405/26005623vsyf.jpg',NULL,'','http://baidu.com','2014-05-23',NULL,10,1,0,1,'广告位','\0'),(4,1,1,'对联广告','img','/member/upload/pms/201405/24093347wscd.jpg',NULL,'','www.baidu.com','2014-05-23','2013-08-29',10,1,0,1,'对联广告位','\0'),(5,1,3,'首页漂浮广告','img','/member/upload/pms/201405/241129323b0s.jpg',NULL,'','www.baidu.com','2014-05-24',NULL,10,NULL,0,NULL,'首页漂浮广告','\0');

/*Table structure for table  tq_advert_slot  */

DROP TABLE IF EXISTS  tq_advert_slot ;

CREATE TABLE  tq_advert_slot  (
   slot_id  int NOT NULL AUTO_INCREMENT,
   site_id  int NOT NULL,
   name  varchar(30) NOT NULL COMMENT '广告位名称',
   slot_type  int NOT NULL COMMENT '类型',
   height  int DEFAULT NULL COMMENT '高',
   width  int DEFAULT NULL COMMENT '宽',
   remain  int DEFAULT NULL COMMENT '停留时间',
   scrollbar  bit(1) DEFAULT NULL COMMENT '跟随滚动条',
   sexplain  varchar(500) DEFAULT NULL COMMENT '说明',
   idleholder  bit(1) DEFAULT NULL COMMENT '空闲时占位',
   rotation  bit(1) NOT NULL DEFAULT b'1' COMMENT '轮换方式',
  PRIMARY KEY ( slot_id ),
  KEY  fk_advert_slot_to_site  ( site_id ),
  CONSTRAINT  fk_advert_slot_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id )
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table  tq_advert_slot  */

insert  into  tq_advert_slot ( slot_id , site_id , name , slot_type , height , width , remain , scrollbar , sexplain , idleholder , rotation ) values (1,1,'对联广告位',4,300,100,0,'\0','对联广告位','\0',''),(2,1,'首页通栏广告位一',1,121,1000,0,'','首页通栏广告位','\0',''),(3,1,'漂浮广告位',2,111,197,0,'\0','漂浮广告位','\0',''),(4,1,'弹窗广告位',3,400,300,0,'','弹窗广告位','\0','');

/*Table structure for table  tq_article  */

DROP TABLE IF EXISTS  tq_article ;

CREATE TABLE  tq_article  (
   article_id  int NOT NULL AUTO_INCREMENT,
   channel_id  int NOT NULL COMMENT '栏目ID',
   user_id  int DEFAULT NULL COMMENT '用户ID',
   model_id  int NOT NULL COMMENT '模型ID',
   depart_id  int DEFAULT NULL COMMENT '录入部门',
   check_id  int DEFAULT NULL COMMENT '审核人员ID',
   site_id  int NOT NULL COMMENT '站点ID',
   title  varchar(100) NOT NULL COMMENT '标题',
   short_title  varchar(50) DEFAULT NULL COMMENT '短标题',
   title_color  varchar(10) DEFAULT NULL COMMENT '标题颜色',
   release_date  datetime DEFAULT NULL,
   is_bold  bit(1) DEFAULT b'0' COMMENT '是否加粗',
   is_top  bit(1) DEFAULT b'0' COMMENT '是否置顶',
   is_recommend  bit(1) DEFAULT b'0' COMMENT '是否推荐',
   status  tinyint(3) NOT NULL DEFAULT '2' COMMENT '状态(0:草稿;1:审核中;2:已审核;3:回收站)',
   style  varchar(20) NOT NULL DEFAULT '0' COMMENT '类型',
   role_id  int DEFAULT NULL COMMENT '审核角色ID',
  PRIMARY KEY ( article_id ),
  KEY  fk_tq_articlechannel  ( channel_id ),
  KEY  fk_article_to_depart  ( depart_id ),
  KEY  fk_article_to_role  ( role_id ),
  KEY  fk_tq_article_site  ( site_id ),
  CONSTRAINT  fk_article_to_depart  FOREIGN KEY ( depart_id ) REFERENCES  tq_depart  ( depart_id ),
  CONSTRAINT  fk_article_to_role  FOREIGN KEY ( role_id ) REFERENCES  tq_role  ( role_id ),
  CONSTRAINT  fk_tq_article_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id ),
  CONSTRAINT  fk_tq_articlechannel  FOREIGN KEY ( channel_id ) REFERENCES  tq_channel  ( channel_id )
) ENGINE=InnoDB AUTO_INCREMENT=213 DEFAULT CHARSET=utf8;

/*Data for the table  tq_article  */

/*Table structure for table  tq_article_attachment  */

DROP TABLE IF EXISTS  tq_article_attachment ;

CREATE TABLE  tq_article_attachment  (
   article_id  int NOT NULL,
   priority  int NOT NULL COMMENT '排列顺序',
   att_path  varchar(255) NOT NULL COMMENT '附件路径',
   att_name  varchar(100) NOT NULL COMMENT '附件名称',
   download_count  int NOT NULL DEFAULT '0' COMMENT '下载次数',
  KEY  fk_tq_attachment_article  ( article_id ),
  CONSTRAINT  fk_tq_attachment_article  FOREIGN KEY ( article_id ) REFERENCES  tq_article  ( article_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_article_attachment  */

/*Table structure for table  tq_article_attr  */

DROP TABLE IF EXISTS  tq_article_attr ;

CREATE TABLE  tq_article_attr  (
   article_id  int NOT NULL,
   attr_name  varchar(30) NOT NULL COMMENT '名称',
   attr_value  varchar(255) DEFAULT NULL COMMENT '值',
  KEY  fk_tq_attr_article  ( article_id ),
  CONSTRAINT  fk_tq_attr_article  FOREIGN KEY ( article_id ) REFERENCES  tq_article  ( article_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_article_attr  */

/*Table structure for table  tq_article_channel  */

DROP TABLE IF EXISTS  tq_article_channel ;

CREATE TABLE  tq_article_channel  (
   channel_id  int NOT NULL,
   article_id  int NOT NULL,
  PRIMARY KEY ( article_id , channel_id ),
  KEY  fk_tq_article_channel  ( channel_id ),
  CONSTRAINT  fk_tq_article_channel  FOREIGN KEY ( channel_id ) REFERENCES  tq_channel  ( channel_id ),
  CONSTRAINT  fk_tq_channel_article  FOREIGN KEY ( article_id ) REFERENCES  tq_article  ( article_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_article_channel  */

/*Table structure for table  tq_article_ext  */

DROP TABLE IF EXISTS  tq_article_ext ;

CREATE TABLE  tq_article_ext  (
   article_id  int NOT NULL,
   sub_title  varchar(100) DEFAULT NULL COMMENT '副标题',
   author  varchar(30) DEFAULT NULL COMMENT '作者',
   origin  varchar(50) DEFAULT NULL COMMENT '来源',
   origin_url  varchar(50) DEFAULT NULL COMMENT '来源链接',
   show_index  bit(1) DEFAULT b'1' COMMENT '是否显示到首页',
   is_red_tape  bit(1) DEFAULT b'0' COMMENT '是否红头文件',
   red_tape_origin  varchar(100) DEFAULT NULL COMMENT '红头文件来源',
   description  varchar(255) DEFAULT NULL COMMENT '描述',
   comment_control  bit(1) DEFAULT b'1' COMMENT '是否允许评论',
   updown_control  bit(1) DEFAULT b'1' COMMENT '顶踩控制',
   link  varchar(100) DEFAULT NULL COMMENT '外部链接',
   tpl_content  varchar(100) DEFAULT NULL COMMENT '指定模板',
   time_day  date DEFAULT NULL COMMENT '定时日期',
   time_hour  time DEFAULT NULL COMMENT '定时时间',
   tag_str  varchar(50) DEFAULT NULL,
  PRIMARY KEY ( article_id ),
  CONSTRAINT  fk_tq_ext_article  FOREIGN KEY ( article_id ) REFERENCES  tq_article  ( article_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_article_ext  */

/*Table structure for table  tq_article_group_view  */

DROP TABLE IF EXISTS  tq_article_group_view ;

CREATE TABLE  tq_article_group_view  (
   article_id  int NOT NULL,
   group_id  int NOT NULL,
  PRIMARY KEY ( article_id , group_id ),
  CONSTRAINT  fk_tq_group_article_v  FOREIGN KEY ( article_id ) REFERENCES  tq_article  ( article_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_article_group_view  */

/*Table structure for table  tq_article_picture  */

DROP TABLE IF EXISTS  tq_article_picture ;

CREATE TABLE  tq_article_picture  (
   article_id  int NOT NULL,
   priority  int NOT NULL COMMENT '排列顺序',
   img_path  varchar(100) NOT NULL COMMENT '图片地址',
   description  varchar(255) DEFAULT NULL COMMENT '描述',
   style  varchar(50) DEFAULT NULL COMMENT '状态',
   is_thumb  bit(1) DEFAULT b'0',
  PRIMARY KEY ( article_id , priority ),
  CONSTRAINT  fk_tq_picture_article  FOREIGN KEY ( article_id ) REFERENCES  tq_article  ( article_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_article_picture  */

/*Table structure for table  tq_article_sign  */

DROP TABLE IF EXISTS  tq_article_sign ;

CREATE TABLE  tq_article_sign  (
   sign_id  int NOT NULL AUTO_INCREMENT,
   article_id  int NOT NULL COMMENT '签收文章',
   admin_id  int NOT NULL COMMENT '签收用户',
   depart_id  int NOT NULL COMMENT '签收部门',
   sign_time  datetime NOT NULL COMMENT '签收时间',
  PRIMARY KEY ( sign_id ),
  KEY  fk_article_sign_to_article  ( article_id ),
  KEY  fk_article_sign_to_admin  ( admin_id ),
  KEY  fk_article_sign_to_depart  ( depart_id ),
  CONSTRAINT  fk_article_sign_to_admin  FOREIGN KEY ( admin_id ) REFERENCES  tq_admin  ( admin_id ),
  CONSTRAINT  fk_article_sign_to_article  FOREIGN KEY ( article_id ) REFERENCES  tq_article  ( article_id ),
  CONSTRAINT  fk_article_sign_to_depart  FOREIGN KEY ( depart_id ) REFERENCES  tq_depart  ( depart_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_article_sign  */

/*Table structure for table  tq_article_txt  */

DROP TABLE IF EXISTS  tq_article_txt ;

CREATE TABLE  tq_article_txt  (
   article_id  int NOT NULL,
   txt  longtext COMMENT '文章内容',
  PRIMARY KEY ( article_id ),
  CONSTRAINT  fk_tq_txt_article  FOREIGN KEY ( article_id ) REFERENCES  tq_article  ( article_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_article_txt  */

/*Table structure for table  tq_article_type  */

DROP TABLE IF EXISTS  tq_article_type ;

CREATE TABLE  tq_article_type  (
   type_id  int NOT NULL AUTO_INCREMENT,
   type_name  varchar(20) NOT NULL COMMENT '名称',
   has_image  bit(1) NOT NULL DEFAULT b'0' COMMENT '是否有图片',
   is_disabled  bit(1) NOT NULL DEFAULT b'0' COMMENT '是否禁用',
  PRIMARY KEY ( type_id )
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table  tq_article_type  */

insert  into  tq_article_type ( type_id , type_name , has_image , is_disabled ) values (1,'普通','\0','\0'),(2,'图文','','\0'),(3,'焦点','','\0'),(4,'头条','\0','\0');

/*Table structure for table  tq_category  */

DROP TABLE IF EXISTS  tq_category ;

CREATE TABLE  tq_category  (
   category_id  int NOT NULL AUTO_INCREMENT,
   site_id  int NOT NULL COMMENT '站点ID',
   name  varchar(50) NOT NULL COMMENT '分区名称',
   priority  int NOT NULL COMMENT '序号',
  PRIMARY KEY ( category_id ),
  KEY  fk_category_to_site  ( site_id ),
  CONSTRAINT  fk_category_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id )
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table  tq_category  */

insert  into  tq_category ( category_id , site_id , name , priority ) values (1,1,'发展建议',1);

/*Table structure for table  tq_channel  */

DROP TABLE IF EXISTS  tq_channel ;

CREATE TABLE  tq_channel  (
   channel_id  int NOT NULL AUTO_INCREMENT,
   site_id  int NOT NULL COMMENT '站点ID',
   parent_id  int DEFAULT NULL COMMENT '父栏目ID',
   channel_name  varchar(50) DEFAULT NULL COMMENT '栏目名称',
   channel_path  varchar(30) DEFAULT NULL COMMENT '栏目路径',
   chnl_number  varchar(100) DEFAULT NULL COMMENT '栏目编号',
   priority  int NOT NULL DEFAULT '10' COMMENT '排列顺序',
   is_alone  bit(1) NOT NULL DEFAULT b'0' COMMENT '是否单页',
   is_show  bit(1) NOT NULL DEFAULT b'1' COMMENT '是否显示',
   flow_id  int DEFAULT NULL COMMENT '工作流ID',
   depart_id  int NOT NULL DEFAULT '1' COMMENT '添加部门',
  PRIMARY KEY ( channel_id ),
  KEY  fk_tq_channel_parent  ( parent_id ),
  KEY  fk_tq_channel_site  ( site_id ),
  CONSTRAINT  fk_tq_channel_parent  FOREIGN KEY ( parent_id ) REFERENCES  tq_channel  ( channel_id ),
  CONSTRAINT  fk_tq_channel_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id )
) ENGINE=InnoDB AUTO_INCREMENT=218 DEFAULT CHARSET=utf8;

/*Data for the table  tq_channel  */

insert  into  tq_channel ( channel_id , site_id , parent_id , channel_name , channel_path , chnl_number , priority , is_alone , is_show , flow_id , depart_id ) values (1,1,NULL,'科室简介','news','-1-',1,'\0','',NULL,1),(2,1,NULL,'工作动态','sports','-2-',2,'\0','',NULL,1),(3,1,NULL,'法律法规','ent','-3-',3,'\0','',NULL,1),(4,1,NULL,'岗位职责','finance','-4-',4,'\0','',NULL,1),(13,1,NULL,'规章制度','tech','-13-',10,'\0','',NULL,1),(14,1,NULL,'国库券','health','-14-',10,'\0','',NULL,1),(15,1,NULL,'学习园地','style','-15-',10,'\0','',NULL,1),(211,1,NULL,'信息调研','house','-211-',9,'\0','',NULL,1),(217,1,NULL,'荣誉展示','honor','-217-',10,'\0','',NULL,1);

/*Table structure for table  tq_channel_ext  */

DROP TABLE IF EXISTS  tq_channel_ext ;

CREATE TABLE  tq_channel_ext  (
   channel_id  int NOT NULL,
   link  varchar(255) DEFAULT NULL COMMENT '外部链接',
   tpl_channel  varchar(100) DEFAULT NULL COMMENT '栏目页模板',
   comment_control  bit(1) NOT NULL DEFAULT b'1' COMMENT '是否允许评论',
   updown_control  bit(1) NOT NULL DEFAULT b'1' COMMENT '顶踩控制',
   is_blank  bit(1) NOT NULL DEFAULT b'1' COMMENT '是否新窗口打开',
   title  varchar(100) DEFAULT NULL COMMENT 'TITLE',
   keywords  varchar(100) DEFAULT NULL COMMENT 'KEYWORDS',
   description  varchar(255) DEFAULT NULL COMMENT 'DESCRIPTION',
   is_static_channel  bit(1) NOT NULL DEFAULT b'0' COMMENT '是否生成栏目静态页',
   is_static_doc  bit(1) NOT NULL DEFAULT b'0' COMMENT '是否生成文档静态页',
   is_sign  bit(1) NOT NULL DEFAULT b'0' COMMENT '是否需签收',
   img_src  varchar(100) DEFAULT NULL COMMENT '栏目图片',
  PRIMARY KEY ( channel_id ),
  CONSTRAINT  fk_tq_ext_channel  FOREIGN KEY ( channel_id ) REFERENCES  tq_channel  ( channel_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_channel_ext  */

insert  into  tq_channel_ext ( channel_id , link , tpl_channel , comment_control , updown_control , is_blank , title , keywords , description , is_static_channel , is_static_doc , is_sign , img_src ) values (1,NULL,'/doc/channel/chanel.html','\0','\0','','科室简介','科室简介','科室简介','','','\0',NULL),(2,NULL,'/doc/channel/chanel.html','\0','\0','','工作动态','工作动态','工作动态','','','\0',NULL),(3,NULL,'/doc/channel/chanel.html','\0','\0','','法律法规','法律法规','法律法规','','','\0',NULL),(4,NULL,'/doc/channel/chanel.html','\0','\0','','岗位职责','岗位职责','岗位职责','','','\0',NULL),(13,NULL,'/doc/channel/chanel.html','\0','\0','','规章制度','规章制度','规章制度','','','\0',NULL),(14,NULL,'/doc/channel/chanel.html','\0','\0','','国库券','国库券','国库券','','','\0',NULL),(15,NULL,'/doc/channel/chanel.html','\0','\0','','学习园地','学习园地','学习园地','','','\0',NULL),(211,NULL,'/doc/channel/chanel.html','\0','\0','','信息调研','信息调研','信息调研','','','\0',NULL),(217,NULL,'/doc/channel/chanel.html','\0','\0','','荣誉展示','荣誉展示','荣誉展示','','','\0',NULL);

/*Table structure for table  tq_channel_txt  */

DROP TABLE IF EXISTS  tq_channel_txt ;

CREATE TABLE  tq_channel_txt  (
   channel_id  int NOT NULL,
   txt  longtext COMMENT '栏目内容',
  PRIMARY KEY ( channel_id ),
  CONSTRAINT  fk_tq_txt_channel  FOREIGN KEY ( channel_id ) REFERENCES  tq_channel  ( channel_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_channel_txt  */

/*Table structure for table  tq_chnl_group_contri  */

DROP TABLE IF EXISTS  tq_chnl_group_contri ;

CREATE TABLE  tq_chnl_group_contri  (
   channel_id  int NOT NULL,
   group_id  int NOT NULL,
  PRIMARY KEY ( channel_id , group_id ),
  CONSTRAINT  fk_tq_group_channel_c  FOREIGN KEY ( channel_id ) REFERENCES  tq_channel  ( channel_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_chnl_group_contri  */

insert  into  tq_chnl_group_contri ( channel_id , group_id ) values (1,1),(1,2),(1,3),(2,1),(2,2),(2,3);

/*Table structure for table  tq_chnl_group_view  */

DROP TABLE IF EXISTS  tq_chnl_group_view ;

CREATE TABLE  tq_chnl_group_view  (
   channel_id  int NOT NULL,
   group_id  int NOT NULL,
  PRIMARY KEY ( channel_id , group_id ),
  CONSTRAINT  fk_tq_group_channel_v  FOREIGN KEY ( channel_id ) REFERENCES  tq_channel  ( channel_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_chnl_group_view  */

/*Table structure for table  tq_chnl_tpl_selection  */

DROP TABLE IF EXISTS  tq_chnl_tpl_selection ;

CREATE TABLE  tq_chnl_tpl_selection  (
   chnl_id  int NOT NULL,
   model_id  int NOT NULL,
   tpl_doc  varchar(100) DEFAULT NULL,
  PRIMARY KEY ( chnl_id , model_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_chnl_tpl_selection  */

insert  into  tq_chnl_tpl_selection ( chnl_id , model_id , tpl_doc ) values (1,2,'/doc/article/content.html'),(1,3,'/doc/article/atlas.html'),(1,4,'/doc/article/content.html'),(1,5,'/doc/article/content.html'),(2,2,'/doc/article/content.html'),(2,3,'/doc/article/atlas.html'),(2,4,'/doc/article/content.html'),(3,2,'/doc/article/content.html'),(3,3,'/doc/article/atlas.html'),(3,4,'/doc/article/content.html'),(3,5,'/doc/article/content.html'),(4,2,'/doc/article/content.html'),(4,3,'/doc/article/atlas.html'),(4,4,'/doc/article/content.html'),(4,5,'/doc/article/content.html'),(13,2,'/doc/article/content.html'),(13,3,'/doc/article/atlas.html'),(13,4,'/doc/article/content.html'),(13,5,'/doc/article/content.html'),(14,2,'/doc/article/atlas.html'),(14,3,'/doc/article/content.html'),(14,4,'/doc/article/content.html'),(14,5,'/doc/article/content.html'),(15,2,'/doc/article/atlas.html'),(15,3,'/doc/article/content.html'),(15,4,'/doc/article/content.html'),(15,5,'/doc/article/content.html'),(113,2,'/doc/article/默认内容页.html'),(113,3,'/doc/article/图集内容页.html'),(113,4,'/doc/article/默认内容页.html'),(113,5,'/doc/article/默认内容页.html'),(114,2,'/doc/article/默认内容页.html'),(114,3,'/doc/article/图集内容页.html'),(163,2,'/doc/article/默认内容页.html'),(163,3,'/doc/article/图集内容页.html'),(163,4,'/doc/article/默认内容页.html'),(163,5,'/doc/article/默认内容页.html'),(164,2,'/doc/article/默认内容页.html'),(164,3,'/doc/article/图集内容页.html'),(164,5,'/doc/article/默认内容页.html'),(165,2,'/doc/article/默认内容页.html'),(165,3,'/doc/article/图集内容页.html'),(166,2,'/doc/article/默认内容页.html'),(166,3,'/doc/article/图集内容页.html'),(167,4,'/doc/article/默认内容页.html'),(168,2,'/doc/article/默认内容页.html'),(168,3,'/doc/article/图集内容页.html'),(169,2,'/doc/article/默认内容页.html'),(169,3,'/doc/article/图集内容页.html'),(169,4,'/doc/article/默认内容页.html'),(170,2,'/doc/article/默认内容页.html'),(170,3,'/doc/article/图集内容页.html'),(171,2,'/doc/article/默认内容页.html'),(171,3,'/doc/article/图集内容页.html'),(172,2,'/doc/article/默认内容页.html'),(172,3,'/doc/article/图集内容页.html'),(173,2,'/doc/article/默认内容页.html'),(173,3,'/doc/article/图集内容页.html'),(174,2,'/doc/article/默认内容页.html'),(174,3,'/doc/article/图集内容页.html'),(175,2,'/doc/article/默认内容页.html'),(175,3,'/doc/article/图集内容页.html'),(175,4,'/doc/article/默认内容页.html'),(175,5,'/doc/article/默认内容页.html'),(176,2,'/doc/article/默认内容页.html'),(176,3,'/doc/article/图集内容页.html'),(176,4,'/doc/article/默认内容页.html'),(177,2,'/doc/article/默认内容页.html'),(177,3,'/doc/article/图集内容页.html'),(178,2,'/doc/article/默认内容页.html'),(178,3,'/doc/article/图集内容页.html'),(179,2,'/doc/article/默认内容页.html'),(179,3,'/doc/article/图集内容页.html'),(179,4,'/doc/article/默认内容页.html'),(180,2,'/doc/article/默认内容页.html'),(180,3,'/doc/article/图集内容页.html'),(180,4,'/doc/article/默认内容页.html'),(180,5,'/doc/article/默认内容页.html'),(181,2,'/doc/article/默认内容页.html'),(182,2,'/doc/article/默认内容页.html'),(182,3,'/doc/article/图集内容页.html'),(183,2,'/doc/article/默认内容页.html'),(183,4,'/doc/article/默认内容页.html'),(184,2,'/doc/article/默认内容页.html'),(184,3,'/doc/article/图集内容页.html'),(185,2,'/doc/article/默认内容页.html'),(185,3,'/doc/article/图集内容页.html'),(185,4,'/doc/article/默认内容页.html'),(185,5,'/doc/article/默认内容页.html'),(186,2,'/doc/article/默认内容页.html'),(186,3,'/doc/article/图集内容页.html'),(187,2,'/doc/article/默认内容页.html'),(187,3,'/doc/article/图集内容页.html'),(188,2,'/doc/article/默认内容页.html'),(188,3,'/doc/article/图集内容页.html'),(189,2,'/doc/article/默认内容页.html'),(189,3,'/doc/article/图集内容页.html'),(190,2,'/doc/article/默认内容页.html'),(190,3,'/doc/article/图集内容页.html'),(190,4,'/doc/article/默认内容页.html'),(190,5,'/doc/article/默认内容页.html'),(191,2,'/doc/article/默认内容页.html'),(191,3,'/doc/article/图集内容页.html'),(192,2,'/doc/article/默认内容页.html'),(192,3,'/doc/article/图集内容页.html'),(193,2,'/doc/article/默认内容页.html'),(194,2,'/doc/article/默认内容页.html'),(195,2,'/doc/article/默认内容页.html'),(195,3,'/doc/article/图集内容页.html'),(195,4,'/doc/article/默认内容页.html'),(195,5,'/doc/article/默认内容页.html'),(196,2,'/doc/article/默认内容页.html'),(197,2,'/doc/article/默认内容页.html'),(197,3,'/doc/article/图集内容页.html'),(198,2,'/doc/article/默认内容页.html'),(198,3,'/doc/article/图集内容页.html'),(199,2,'/doc/article/默认内容页.html'),(199,3,'/doc/article/图集内容页.html'),(200,2,'/doc/article/默认内容页.html'),(200,3,'/doc/article/图集内容页.html'),(200,4,'/doc/article/默认内容页.html'),(200,5,'/doc/article/默认内容页.html'),(201,2,'/doc/article/默认内容页.html'),(202,2,'/doc/article/默认内容页.html'),(202,3,'/doc/article/图集内容页.html'),(203,2,'/doc/article/默认内容页.html'),(203,3,'/doc/article/图集内容页.html'),(204,2,'/doc/article/默认内容页.html'),(204,3,'/doc/article/图集内容页.html'),(210,2,'/doc/article/默认内容页.html'),(210,3,'/doc/article/图集内容页.html'),(211,2,'/doc/article/content.html'),(211,3,'/doc/article/atlas.html'),(211,4,'/doc/article/content.html'),(211,5,'/doc/article/content.html'),(217,2,'/doc/article/content.html'),(217,3,'/doc/article/atlas.html'),(217,4,'/doc/article/content.html'),(217,5,'/doc/article/content.html');

/*Table structure for table  tq_comment  */

DROP TABLE IF EXISTS  tq_comment ;

CREATE TABLE  tq_comment  (
   comment_id  int NOT NULL AUTO_INCREMENT,
   site_id  int NOT NULL COMMENT '站点ID',
   doc_id  int NOT NULL COMMENT '文档ID',
   user_id  int DEFAULT NULL COMMENT '用户ID',
   parent_id  int DEFAULT NULL COMMENT '评论ID',
   ups  int NOT NULL COMMENT '赞同数量',
   create_time  datetime NOT NULL COMMENT '评论时间',
   is_checked  bit(1) NOT NULL COMMENT '是否审核',
   last_time  datetime NOT NULL COMMENT '最后回复时间',
  PRIMARY KEY ( comment_id ),
  KEY  fk_comment_to_site  ( site_id ),
  KEY  fk_comment_to_comment  ( parent_id ),
  KEY  fk_comment_to_doc  ( doc_id ),
  KEY  fk_comment_to_user  ( user_id ),
  CONSTRAINT  fk_comment_to_comment  FOREIGN KEY ( parent_id ) REFERENCES  tq_comment  ( comment_id ),
  CONSTRAINT  fk_comment_to_doc  FOREIGN KEY ( doc_id ) REFERENCES  tq_article  ( article_id ),
  CONSTRAINT  fk_comment_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id ),
  CONSTRAINT  fk_comment_to_user  FOREIGN KEY ( user_id ) REFERENCES  tq_user  ( user_id )
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table  tq_comment  */

/*Table structure for table  tq_comment_ext  */

DROP TABLE IF EXISTS  tq_comment_ext ;

CREATE TABLE  tq_comment_ext  (
   comment_id  int NOT NULL,
   ip  varchar(20) NOT NULL COMMENT '评论IP',
   content  longtext NOT NULL COMMENT '评论内容',
  PRIMARY KEY ( comment_id ),
  CONSTRAINT  fk_commentext_to_comment  FOREIGN KEY ( comment_id ) REFERENCES  tq_comment  ( comment_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_comment_ext  */

/*Table structure for table  tq_company_fairs  */

DROP TABLE IF EXISTS  tq_company_fairs ;

CREATE TABLE  tq_company_fairs  (
   comfairs_id  int NOT NULL AUTO_INCREMENT,
   company_id  int NOT NULL,
   fairs_id  int NOT NULL,
   showcase  varchar(20) NOT NULL,
   show_time  int NOT NULL,
   create_time  datetime NOT NULL,
  PRIMARY KEY ( comfairs_id ),
  KEY  fk_company_comfairs  ( company_id ),
  KEY  fk_fairs_comfairs  ( fairs_id ),
  CONSTRAINT  fk_company_comfairs  FOREIGN KEY ( company_id ) REFERENCES  tq_company_info  ( company_id ),
  CONSTRAINT  fk_fairs_comfairs  FOREIGN KEY ( fairs_id ) REFERENCES  tq_job_fairs  ( fairs_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_company_fairs  */

/*Table structure for table  tq_company_favorite  */

DROP TABLE IF EXISTS  tq_company_favorite ;

CREATE TABLE  tq_company_favorite  (
   favorite_id  int NOT NULL AUTO_INCREMENT,
   company_id  int NOT NULL,
   resume_id  int NOT NULL,
   create_time  datetime NOT NULL,
  PRIMARY KEY ( favorite_id ),
  KEY  fk_company_resume  ( company_id ),
  KEY  fk_resume_company  ( resume_id ),
  CONSTRAINT  fk_company_resume  FOREIGN KEY ( company_id ) REFERENCES  tq_company_info  ( company_id ),
  CONSTRAINT  fk_resume_company  FOREIGN KEY ( resume_id ) REFERENCES  tq_resume  ( resume_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_company_favorite  */

/*Table structure for table  tq_company_info  */

DROP TABLE IF EXISTS  tq_company_info ;

CREATE TABLE  tq_company_info  (
   company_id  int NOT NULL AUTO_INCREMENT,
   name  varchar(50) NOT NULL,
   link_man  varchar(30) NOT NULL,
   link_tel  varchar(20) NOT NULL,
   com_nature  int DEFAULT NULL,
   com_scale  int DEFAULT NULL,
   com_industry1  int DEFAULT NULL,
   com_industry2  int DEFAULT NULL,
   is_check  int NOT NULL,
   is_commend  int NOT NULL,
   is_show  int NOT NULL,
   last_post_time  datetime DEFAULT NULL,
   vip_type  int DEFAULT NULL,
   apply_type  int DEFAULT NULL,
  PRIMARY KEY ( company_id ),
  KEY  FK_TYPE_TO_VIP  ( vip_type ),
  CONSTRAINT  FK_TYPE_TO_VIP  FOREIGN KEY ( vip_type ) REFERENCES  tq_vip_type  ( type_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_company_info  */

/*Table structure for table  tq_company_info_ext  */

DROP TABLE IF EXISTS  tq_company_info_ext ;

CREATE TABLE  tq_company_info_ext  (
   company_id  int NOT NULL,
   address  varchar(200) DEFAULT NULL,
   zip_code  varchar(20) DEFAULT NULL,
   web_url  varchar(50) DEFAULT NULL,
   email  varchar(50) DEFAULT NULL,
   phone  varchar(20) DEFAULT NULL,
   fax  varchar(20) DEFAULT NULL,
   synopsis  varchar(2000) DEFAULT NULL,
   reg_capital  int DEFAULT NULL,
   set_up  varchar(20) DEFAULT NULL,
   city  varchar(50) DEFAULT NULL,
   is_vip  int NOT NULL DEFAULT '0',
   dredge_time  datetime DEFAULT NULL,
   vip_day  int DEFAULT NULL,
   apply_vip  int NOT NULL DEFAULT '0',
   apply_time  datetime DEFAULT NULL,
   need_login  int DEFAULT '0',
  PRIMARY KEY ( company_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_company_info_ext  */

/*Table structure for table  tq_consul  */

DROP TABLE IF EXISTS  tq_consul ;

CREATE TABLE  tq_consul  (
   consul_id  int NOT NULL AUTO_INCREMENT,
   article_id  int NOT NULL,
   consul_user  int DEFAULT NULL,
   reply_user  int DEFAULT NULL,
   site_id  int NOT NULL,
   title  varchar(50) NOT NULL,
   create_time  datetime NOT NULL,
   reply_time  datetime DEFAULT NULL,
  PRIMARY KEY ( consul_id ),
  KEY  fk_consul_to_article  ( article_id ),
  KEY  fk_consul_to_site  ( site_id ),
  CONSTRAINT  fk_consul_to_article  FOREIGN KEY ( article_id ) REFERENCES  tq_article  ( article_id ),
  CONSTRAINT  fk_consul_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_consul  */

/*Table structure for table  tq_consul_ext  */

DROP TABLE IF EXISTS  tq_consul_ext ;

CREATE TABLE  tq_consul_ext  (
   consul_id  int NOT NULL,
   content  varchar(3000) NOT NULL,
   reply  varchar(3000) DEFAULT NULL,
  PRIMARY KEY ( consul_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_consul_ext  */

/*Table structure for table  tq_database_config  */

DROP TABLE IF EXISTS  tq_database_config ;

CREATE TABLE  tq_database_config  (
   config_id  int NOT NULL,
   inter  int NOT NULL COMMENT '备份间隔',
   backup_time  int NOT NULL COMMENT '备份时间',
   pre_time  datetime DEFAULT NULL COMMENT '上次备份时间',
  PRIMARY KEY ( config_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_database_config  */

/*Table structure for table  tq_depart  */

DROP TABLE IF EXISTS  tq_depart ;

CREATE TABLE  tq_depart  (
   depart_id  int NOT NULL AUTO_INCREMENT,
   site_id  int NOT NULL COMMENT '站点ID',
   parent_id  int DEFAULT NULL COMMENT '上级部门',
   flow_id  int DEFAULT NULL COMMENT '当前采用的工作流',
   name  varchar(50) NOT NULL COMMENT '部门名称',
   tree_number  varchar(150) DEFAULT NULL COMMENT '部门编号',
   visit_path  varchar(30) DEFAULT NULL COMMENT '独立访问路径',
   priority  int NOT NULL COMMENT '排序',
   is_show  bit(1) NOT NULL COMMENT '前台是否显示',
   create_time  datetime NOT NULL COMMENT '添加时间',
   is_all_channel  bit(1) NOT NULL DEFAULT b'0' COMMENT '所有栏目权限',
   short_name  varchar(50) DEFAULT NULL COMMENT '部门简称',
  PRIMARY KEY ( depart_id ),
  KEY  fk_depart_to_site  ( site_id ),
  KEY  fk_depart_to_depart  ( parent_id ),
  KEY  fk_depart_to_workflow  ( flow_id ),
  CONSTRAINT  fk_depart_to_depart  FOREIGN KEY ( parent_id ) REFERENCES  tq_depart  ( depart_id ),
  CONSTRAINT  fk_depart_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id ),
  CONSTRAINT  fk_depart_to_workflow  FOREIGN KEY ( flow_id ) REFERENCES  tq_work_flow  ( flow_id )
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;

/*Data for the table  tq_depart  */

insert  into  tq_depart ( depart_id , site_id , parent_id , flow_id , name , tree_number , visit_path , priority , is_show , create_time , is_all_channel , short_name ) values (1,1,NULL,NULL,'信息中心','-1-','',1,'\0','2013-04-25 22:22:22','',NULL),(2,1,1,NULL,'新闻编辑部','-1-2-','',2,'','2013-06-17 16:03:29','\0',''),(4,1,1,NULL,'新闻审核部','-1-4-','',3,'','2013-06-21 16:34:36','\0',''),(8,9,NULL,5,'信息中心','-8-','',1,'\0','2013-04-25 22:22:22','\0',NULL),(17,18,NULL,NULL,'信息中心','-17-','',1,'\0','2013-04-25 22:22:22','\0',NULL),(20,21,NULL,NULL,'信息中心','-20-','',1,'\0','2013-04-25 22:22:22','\0',NULL),(22,23,NULL,NULL,'信息中心','-22-','',1,'\0','2013-04-25 22:22:22','\0',NULL);

/*Table structure for table  tq_depart_channel  */

DROP TABLE IF EXISTS  tq_depart_channel ;

CREATE TABLE  tq_depart_channel  (
   depart_id  int NOT NULL,
   channel_id  int NOT NULL,
  PRIMARY KEY ( channel_id , depart_id ),
  CONSTRAINT  fk_departchannel_channel  FOREIGN KEY ( channel_id ) REFERENCES  tq_channel  ( channel_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_depart_channel  */

insert  into  tq_depart_channel ( depart_id , channel_id ) values (2,1),(4,1),(2,2),(4,2),(2,3),(4,3),(2,4),(4,4),(4,13),(4,14),(4,15),(4,211);

/*Table structure for table  tq_doc_statistics  */

DROP TABLE IF EXISTS  tq_doc_statistics ;

CREATE TABLE  tq_doc_statistics  (
   doc_id  int NOT NULL,
   views_count  int NOT NULL,
   ups  int NOT NULL DEFAULT '0' COMMENT '赞同数',
   treads  int NOT NULL DEFAULT '0' COMMENT '反对数',
  PRIMARY KEY ( doc_id ),
  CONSTRAINT  fk_statis_to_doc  FOREIGN KEY ( doc_id ) REFERENCES  tq_article  ( article_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_doc_statistics  */

/*Table structure for table  tq_economy_type  */

DROP TABLE IF EXISTS  tq_economy_type ;

CREATE TABLE  tq_economy_type  (
   type_id  int NOT NULL AUTO_INCREMENT,
   type_code  varchar(10) NOT NULL COMMENT '编码',
   type_name  varchar(50) NOT NULL COMMENT '名称',
  PRIMARY KEY ( type_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_economy_type  */

/*Table structure for table  tq_edu_exp  */

DROP TABLE IF EXISTS  tq_edu_exp ;

CREATE TABLE  tq_edu_exp  (
   edu_id  int NOT NULL AUTO_INCREMENT,
   resume_id  int NOT NULL COMMENT '简历ID',
   special_id  int NOT NULL COMMENT '专业ID',
   start_time  varchar(30) NOT NULL COMMENT '开始时间',
   end_time  varchar(30) NOT NULL COMMENT '截止时间',
   school  varchar(50) NOT NULL COMMENT '所在学校',
   educational  int NOT NULL COMMENT '学历',
   degree  int DEFAULT NULL COMMENT '学位',
   is_oversea  int NOT NULL COMMENT '是否有海外经历',
   description  varchar(2000) DEFAULT NULL COMMENT '描述',
   create_time  datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY ( edu_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_edu_exp  */

/*Table structure for table  tq_evaluate  */

DROP TABLE IF EXISTS  tq_evaluate ;

CREATE TABLE  tq_evaluate  (
   evaluate_id  int NOT NULL,
   article_id  int NOT NULL,
   evaluate_user  int DEFAULT NULL,
   reply_user  int DEFAULT NULL,
   site_id  int NOT NULL,
   level  int NOT NULL,
   create_time  datetime NOT NULL,
   reply_time  datetime DEFAULT NULL,
  PRIMARY KEY ( evaluate_id ),
  KEY  fk_evaluate_to_article  ( article_id ),
  KEY  fk_evaluate_to_site  ( site_id ),
  CONSTRAINT  fk_evaluate_to_article  FOREIGN KEY ( article_id ) REFERENCES  tq_article  ( article_id ),
  CONSTRAINT  fk_evaluate_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_evaluate  */

/*Table structure for table  tq_evaluate_ext  */

DROP TABLE IF EXISTS  tq_evaluate_ext ;

CREATE TABLE  tq_evaluate_ext  (
   evaluate_id  int NOT NULL,
   content  varchar(3000) NOT NULL,
   reply  varchar(3000) DEFAULT NULL,
  PRIMARY KEY ( evaluate_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_evaluate_ext  */

/*Table structure for table  tq_flow_channel  */

DROP TABLE IF EXISTS  tq_flow_channel ;

CREATE TABLE  tq_flow_channel  (
   channel_id  int NOT NULL,
   flow_id  int NOT NULL,
  PRIMARY KEY ( channel_id , flow_id ),
  KEY  fk_flowchannel_flow  ( flow_id ),
  CONSTRAINT  fk_flowchannel_channel  FOREIGN KEY ( channel_id ) REFERENCES  tq_channel  ( channel_id ),
  CONSTRAINT  fk_flowchannel_flow  FOREIGN KEY ( flow_id ) REFERENCES  tq_work_flow  ( flow_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_flow_channel  */

/*Table structure for table  tq_flow_detail  */

DROP TABLE IF EXISTS  tq_flow_detail ;

CREATE TABLE  tq_flow_detail  (
   detail_id  int NOT NULL AUTO_INCREMENT,
   doc_id  int NOT NULL COMMENT '文档ID',
   user_id  int NOT NULL COMMENT '操作人员',
   role_id  int NOT NULL COMMENT '操作角色',
   back_initial  bit(1) NOT NULL DEFAULT b'1' COMMENT '是否退回初始状态',
   is_checked  bit(1) NOT NULL DEFAULT b'0' COMMENT '是否审核通过',
   reason  varchar(100) DEFAULT NULL COMMENT '操作原因',
   priority  int NOT NULL COMMENT '排序',
   create_time  datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY ( detail_id ),
  KEY  fk_flowdetail_to_role  ( role_id ),
  KEY  fk_flowdetail_to_doc  ( doc_id ),
  KEY  fk_flowdetail_to_user  ( user_id ),
  CONSTRAINT  fk_flowdetail_to_doc  FOREIGN KEY ( doc_id ) REFERENCES  tq_article  ( article_id ),
  CONSTRAINT  fk_flowdetail_to_role  FOREIGN KEY ( role_id ) REFERENCES  tq_role  ( role_id ),
  CONSTRAINT  fk_flowdetail_to_user  FOREIGN KEY ( user_id ) REFERENCES  tq_user  ( user_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_flow_detail  */

/*Table structure for table  tq_flow_step  */

DROP TABLE IF EXISTS  tq_flow_step ;

CREATE TABLE  tq_flow_step  (
   step_id  int NOT NULL AUTO_INCREMENT,
   flow_id  int NOT NULL,
   step  int NOT NULL,
   role_id  int NOT NULL,
  PRIMARY KEY ( step_id ),
  KEY  fk_flowstep_flow  ( flow_id ),
  CONSTRAINT  fk_flowstep_flow  FOREIGN KEY ( flow_id ) REFERENCES  tq_work_flow  ( flow_id )
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

/*Data for the table  tq_flow_step  */

insert  into  tq_flow_step ( step_id , flow_id , step , role_id ) values (40,5,1,2),(41,5,2,1);

/*Table structure for table  tq_foreign_lang  */

DROP TABLE IF EXISTS  tq_foreign_lang ;

CREATE TABLE  tq_foreign_lang  (
   lang_id  int NOT NULL AUTO_INCREMENT,
   lang_code  varchar(10) NOT NULL COMMENT '编码',
   lang_name  varchar(50) NOT NULL COMMENT '名称',
  PRIMARY KEY ( lang_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_foreign_lang  */

/*Table structure for table  tq_forum  */

DROP TABLE IF EXISTS  tq_forum ;

CREATE TABLE  tq_forum  (
   forum_id  int NOT NULL AUTO_INCREMENT,
   site_id  int NOT NULL COMMENT '站点ID',
   category_id  int NOT NULL COMMENT '分类ID',
   last_theme_id  int DEFAULT NULL COMMENT '最后回复主题',
   last_replyer_id  int DEFAULT NULL COMMENT '最后回复用户',
   name  varchar(50) NOT NULL COMMENT '板块名称',
   priority  int NOT NULL COMMENT '序号',
   theme_total  int NOT NULL COMMENT '主题总数',
   reply_total  int NOT NULL COMMENT '回复总数',
   theme_today  int NOT NULL COMMENT '今日主题数',
   reply_today  int NOT NULL COMMENT '今日回复数',
   moderators  varchar(50) DEFAULT NULL COMMENT '版主',
  PRIMARY KEY ( forum_id ),
  KEY  fk_forum_to_site  ( site_id ),
  KEY  fk_forum_to_category  ( category_id ),
  KEY  fk_forum_to_reply  ( last_replyer_id ),
  KEY  fk_forum_to_theme  ( last_theme_id ),
  CONSTRAINT  fk_forum_to_category  FOREIGN KEY ( category_id ) REFERENCES  tq_category  ( category_id ),
  CONSTRAINT  fk_forum_to_reply  FOREIGN KEY ( last_replyer_id ) REFERENCES  tq_user  ( user_id ),
  CONSTRAINT  fk_forum_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id ),
  CONSTRAINT  fk_forum_to_theme  FOREIGN KEY ( last_theme_id ) REFERENCES  tq_theme  ( theme_id )
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table  tq_forum  */

insert  into  tq_forum ( forum_id , site_id , category_id , last_theme_id , last_replyer_id , name , priority , theme_total , reply_total , theme_today , reply_today , moderators ) values (1,1,1,20,1,'发展建议',1,12,3,0,0,NULL);

/*Table structure for table  tq_forum_ext  */

DROP TABLE IF EXISTS  tq_forum_ext ;

CREATE TABLE  tq_forum_ext  (
   forum_id  int NOT NULL,
   keywords  varchar(200) DEFAULT NULL COMMENT '板块关键字',
   description  varchar(255) DEFAULT NULL COMMENT '板块描述',
   rule  varchar(255) DEFAULT NULL COMMENT '板块规则',
   tpl_content  varchar(150) DEFAULT NULL COMMENT '模板地址',
  PRIMARY KEY ( forum_id ),
  CONSTRAINT  fk_forumext_to_forum  FOREIGN KEY ( forum_id ) REFERENCES  tq_forum  ( forum_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_forum_ext  */

insert  into  tq_forum_ext ( forum_id , keywords , description , rule , tpl_content ) values (1,'发展建议','发展建议','1 本版块只允许普通用户回复\r\n2 安装使用中遇到问题请先查看的相应帮助. 常见修改可以先查精华区或搜索, 也许会给你节省时间;\r\n3 安装使用方面的问题请到程序讨论区','/extrafunc/forum/帖子列表.html');

/*Table structure for table  tq_forum_operate  */

DROP TABLE IF EXISTS  tq_forum_operate ;

CREATE TABLE  tq_forum_operate  (
   operate_id  int NOT NULL AUTO_INCREMENT,
   site_id  int NOT NULL COMMENT '站点ID',
   operate_admin  int NOT NULL COMMENT '操作管理员',
   operate_target  int NOT NULL COMMENT '操作对象',
   target_type  varchar(30) NOT NULL COMMENT '对象类型',
   name  varchar(30) NOT NULL COMMENT '操作名称',
   reason  varchar(100) DEFAULT NULL COMMENT '操作理由',
   operate_time  datetime NOT NULL COMMENT '操作时间',
  PRIMARY KEY ( operate_id ),
  KEY  fk_operate_to_site  ( site_id ),
  KEY  fk_operate_to_user  ( operate_admin ),
  CONSTRAINT  fk_operate_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id ),
  CONSTRAINT  fk_operate_to_user  FOREIGN KEY ( operate_admin ) REFERENCES  tq_user  ( user_id )
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

/*Data for the table  tq_forum_operate  */

insert  into  tq_forum_operate ( operate_id , site_id , operate_admin , operate_target , target_type , name , reason , operate_time ) values (1,1,1,14,'THEME','屏蔽','我要屏蔽','2013-05-31 13:29:28'),(2,1,1,13,'THEME','屏蔽','dfgfdg','2013-05-31 13:46:02'),(3,1,1,12,'THEME','置顶','ssfsdfdsf','2013-05-31 15:53:03'),(4,1,1,11,'THEME','高亮','sfsdfsf','2013-05-31 16:18:35'),(5,1,1,8,'THEME','高亮','sfdsfsd','2013-06-03 11:22:30'),(6,1,1,8,'THEME','置顶','sfsdf','2013-06-03 11:22:46'),(7,1,1,18,'THEME','置顶','fdgfdgfdg','2013-06-03 12:01:25'),(8,1,1,17,'THEME','置顶','fdgdfg','2013-06-03 12:01:39'),(9,1,1,17,'THEME','高亮','gfdgfdg','2013-06-03 12:01:57'),(10,1,1,20,'POSTS','编辑','无','2013-11-04 10:44:55');

/*Table structure for table  tq_forum_statis  */

DROP TABLE IF EXISTS  tq_forum_statis ;

CREATE TABLE  tq_forum_statis  (
   statis_id  int NOT NULL,
   posts_today  int NOT NULL COMMENT '今日发帖数',
   posts_yestoday  int NOT NULL COMMENT '昨日发帖数',
   highest_day  int NOT NULL COMMENT '最高日发帖数',
   posts_total  int NOT NULL COMMENT '总发帖数',
  PRIMARY KEY ( statis_id ),
  CONSTRAINT  fk_forumstatis_to_site  FOREIGN KEY ( statis_id ) REFERENCES  tq_site  ( site_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_forum_statis  */

insert  into  tq_forum_statis ( statis_id , posts_today , posts_yestoday , highest_day , posts_total ) values (1,14,0,0,14);

/*Table structure for table  tq_gen_table  */

DROP TABLE IF EXISTS  tq_gen_table ;

CREATE TABLE  tq_gen_table  (
   tg_gen_name  varchar(50) NOT NULL,
   tq_gen_value  int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_gen_table  */

insert  into  tq_gen_table ( tg_gen_name , tq_gen_value ) values ('tq_article',219),('tq_log',2066),('tq_channel',218),('tq_model',8),('tq_model_field',116),('tq_article_type',9),('tq_work_flow',2),('tq_flow_step',42),('tq_comment',35),('tq_message_type',3),('tq_advert_slot',5),('tq_advert',6),('tq_questionnaire',2),('tq_survey_theme',7),('tq_category',2),('tq_forum',2),('tq_mailbox_type',2),('tq_user',14),('tq_group',5),('tq_role',26),('tq_depart',23),('tq_keyword',4),('tq_sensitivity',3),('tq_site',24),('tq_admin_check',25),('tq_visit_statistics',170),('tq_links_type',2),('tq_links',3);

/*Table structure for table  tq_group  */

DROP TABLE IF EXISTS  tq_group ;

CREATE TABLE  tq_group  (
   group_id  int NOT NULL AUTO_INCREMENT,
   site_id  int NOT NULL COMMENT '站点',
   name  varchar(30) NOT NULL COMMENT '会员组名称',
   priority  int NOT NULL COMMENT '排序',
   is_all_perm  bit(1) NOT NULL COMMENT '是否拥有所有权限',
   is_regist_show  bit(1) NOT NULL COMMENT '是否为可注册会员组',
  PRIMARY KEY ( group_id ),
  KEY  fk_group_to_site  ( site_id ),
  CONSTRAINT  fk_group_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id )
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table  tq_group  */

insert  into  tq_group ( group_id , site_id , name , priority , is_all_perm , is_regist_show ) values (1,1,'普通会员',1,'',''),(2,1,'白金会员',3,'','\0'),(3,1,'青铜会员',2,'','\0');

/*Table structure for table  tq_group_perm  */

DROP TABLE IF EXISTS  tq_group_perm ;

CREATE TABLE  tq_group_perm  (
   group_id  int NOT NULL COMMENT '会员组',
   perms  longtext COMMENT '权限集合',
  PRIMARY KEY ( group_id ),
  CONSTRAINT  fk_groupprem_to_group  FOREIGN KEY ( group_id ) REFERENCES  tq_group  ( group_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_group_perm  */

insert  into  tq_group_perm ( group_id , perms ) values (1,NULL),(2,NULL),(3,NULL);

/*Table structure for table  tq_industry  */

DROP TABLE IF EXISTS  tq_industry ;

CREATE TABLE  tq_industry  (
   industry_id  int NOT NULL AUTO_INCREMENT,
   parent_id  int DEFAULT NULL,
   industry_code  varchar(10) NOT NULL COMMENT '编码',
   industry_name  varchar(50) NOT NULL COMMENT '名称',
  PRIMARY KEY ( industry_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_industry  */

/*Table structure for table  tq_job_fairs  */

DROP TABLE IF EXISTS  tq_job_fairs ;

CREATE TABLE  tq_job_fairs  (
   fairs_id  int NOT NULL AUTO_INCREMENT,
   fairs_name  varchar(50) NOT NULL,
   fairs_theme  varchar(100) NOT NULL,
   start_time  datetime NOT NULL,
   end_time  datetime NOT NULL,
   create_time  datetime NOT NULL,
  PRIMARY KEY ( fairs_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_job_fairs  */

/*Table structure for table  tq_job_fairs_ext  */

DROP TABLE IF EXISTS  tq_job_fairs_ext ;

CREATE TABLE  tq_job_fairs_ext  (
   fairs_id  int NOT NULL,
   tpl_address  varchar(100) DEFAULT NULL,
   introduction  varchar(2000) NOT NULL,
   host_organ  varchar(50) NOT NULL,
   undertake  varchar(50) DEFAULT NULL,
   city_name  varchar(100) NOT NULL,
   place  varchar(100) NOT NULL,
   link_man  varchar(30) NOT NULL,
   link_tel  varchar(20) NOT NULL,
   email  varchar(30) NOT NULL,
  PRIMARY KEY ( fairs_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_job_fairs_ext  */

/*Table structure for table  tq_job_intent  */

DROP TABLE IF EXISTS  tq_job_intent ;

CREATE TABLE  tq_job_intent  (
   intent_id  int NOT NULL AUTO_INCREMENT,
   metier_id  int NOT NULL COMMENT '期望从事职业',
   industry_id  int DEFAULT NULL COMMENT '期望从事行业',
   work_place  varchar(50) NOT NULL COMMENT '期望工作地点',
   expect_wage  int NOT NULL COMMENT '期望待遇',
   work_nature  varchar(10) NOT NULL COMMENT '期望工作性质',
   title  varchar(50) NOT NULL,
   create_time  datetime NOT NULL,
   update_time  datetime DEFAULT NULL,
   metier_id2  int DEFAULT NULL,
  PRIMARY KEY ( intent_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_job_intent  */

/*Table structure for table  tq_job_post  */

DROP TABLE IF EXISTS  tq_job_post ;

CREATE TABLE  tq_job_post  (
   job_id  int NOT NULL AUTO_INCREMENT,
   company_id  int NOT NULL,
   job_name  varchar(40) NOT NULL,
   work_address  varchar(100) NOT NULL,
   educational  int NOT NULL,
   recruit_count  int NOT NULL,
   start_time  datetime NOT NULL,
   end_time  datetime NOT NULL,
   refresh_time  datetime DEFAULT NULL,
   is_check  int NOT NULL,
   metier_id  int NOT NULL,
   create_time  datetime NOT NULL,
   gender  int NOT NULL DEFAULT '-1',
   min_age  int DEFAULT NULL,
   max_age  int DEFAULT NULL,
  PRIMARY KEY ( job_id ),
  KEY  fk_job_company  ( company_id ),
  CONSTRAINT  fk_job_company  FOREIGN KEY ( company_id ) REFERENCES  tq_company_info  ( company_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_job_post  */

/*Table structure for table  tq_job_post_ext  */

DROP TABLE IF EXISTS  tq_job_post_ext ;

CREATE TABLE  tq_job_post_ext  (
   job_id  int NOT NULL,
   job_type  int NOT NULL,
   appeal  varchar(2000) NOT NULL,
   job_nature  varchar(1000) DEFAULT NULL,
   others  varchar(2000) DEFAULT NULL,
   is_onbusiness  int NOT NULL,
   wage  int NOT NULL,
   work_exp  int NOT NULL DEFAULT '0',
  PRIMARY KEY ( job_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_job_post_ext  */

/*Table structure for table  tq_keyword  */

DROP TABLE IF EXISTS  tq_keyword ;

CREATE TABLE  tq_keyword  (
   keyword_id  int NOT NULL AUTO_INCREMENT,
   site_id  int NOT NULL,
   name  varchar(100) NOT NULL COMMENT '关键字',
   url  varchar(100) NOT NULL COMMENT '关键字链接',
   is_bold  bit(1) NOT NULL COMMENT '是否加粗',
   is_underline  bit(1) NOT NULL COMMENT '是否有下划线',
   is_enable  bit(1) NOT NULL COMMENT '是否启用',
  PRIMARY KEY ( keyword_id ),
  KEY  fk_keyword_to_site  ( site_id ),
  CONSTRAINT  fk_keyword_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_keyword  */

/*Table structure for table  tq_lang_ability  */

DROP TABLE IF EXISTS  tq_lang_ability ;

CREATE TABLE  tq_lang_ability  (
   ability_id  int NOT NULL AUTO_INCREMENT,
   resume_id  int NOT NULL COMMENT '简历ID',
   lang_id  int DEFAULT NULL,
   cert_type  varchar(30) DEFAULT NULL,
   grade  varchar(30) DEFAULT NULL,
   literacy  int DEFAULT NULL,
   communications  int DEFAULT NULL,
  PRIMARY KEY ( ability_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_lang_ability  */

/*Table structure for table  tq_links  */

DROP TABLE IF EXISTS  tq_links ;

CREATE TABLE  tq_links  (
   links_id  int NOT NULL,
   site_id  int NOT NULL,
   type_id  int NOT NULL COMMENT '分类ID',
   links_name  varchar(50) NOT NULL COMMENT '友情链接名称',
   links_icon  varchar(100) DEFAULT NULL COMMENT '友情链接图标',
   links_url  varchar(100) DEFAULT NULL COMMENT '友情链接URL',
   priority  int NOT NULL COMMENT '排序',
   is_show  bit(1) NOT NULL COMMENT '是否显示',
   show_icon  bit(1) NOT NULL COMMENT '是否图标显示',
  PRIMARY KEY ( links_id ),
  KEY  fk_links_to_site  ( site_id ),
  CONSTRAINT  fk_links_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_links  */

insert  into  tq_links ( links_id , site_id , type_id , links_name , links_icon , links_url , priority , is_show , show_icon ) values (1,1,1,'百度',NULL,'http://www.baidu.com',1,'','\0'),(2,1,1,'必应',NULL,'http://www.biying.cn',2,'','\0');

/*Table structure for table  tq_links_type  */

DROP TABLE IF EXISTS  tq_links_type ;

CREATE TABLE  tq_links_type  (
   type_id  int NOT NULL,
   site_id  int NOT NULL,
   name  varchar(50) NOT NULL COMMENT '分类名称',
   priority  int NOT NULL COMMENT '排序',
  PRIMARY KEY ( type_id ),
  KEY  fk_links_type_to_site  ( site_id ),
  CONSTRAINT  fk_links_type_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_links_type  */

insert  into  tq_links_type ( type_id , site_id , name , priority ) values (1,1,'文字链接',1);

/*Table structure for table  tq_log  */

DROP TABLE IF EXISTS  tq_log ;

CREATE TABLE  tq_log  (
   log_id  int NOT NULL AUTO_INCREMENT,
   user_id  int DEFAULT NULL,
   site_id  int DEFAULT NULL,
   category  int NOT NULL COMMENT '日志类型',
   log_time  datetime NOT NULL COMMENT '日志时间',
   ip  varchar(50) DEFAULT NULL COMMENT 'IP地址',
   url  varchar(255) DEFAULT NULL COMMENT 'URL地址',
   title  varchar(255) DEFAULT NULL COMMENT '日志标题',
   content  varchar(255) DEFAULT NULL COMMENT '日志内容',
  PRIMARY KEY ( log_id ),
  KEY  fk_tq_log_site  ( site_id ),
  CONSTRAINT  fk_tq_log_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id )
) ENGINE=InnoDB AUTO_INCREMENT=2066 DEFAULT CHARSET=utf8;

/*Data for the table  tq_log  */

insert  into  tq_log ( log_id , user_id , site_id , category , log_time , ip , url , title , content ) values (1977,1,1,3,'2014-05-26 18:47:10','0:0:0:0:0:0:0:1','/javapms/admin/doc/o_ajax_delete.do','删除文档','id=216;title=sdf'),(1978,1,1,3,'2014-05-26 18:49:05','0:0:0:0:0:0:0:1','/javapms/admin/doc/o_save.do','添加文档','id=218;title=a'),(1979,1,1,3,'2014-05-26 18:49:16','0:0:0:0:0:0:0:1','/javapms/admin/doc/o_update.do','修改文档','id=218;title=a'),(1980,1,1,3,'2014-05-26 18:49:24','0:0:0:0:0:0:0:1','/javapms/admin/doc/o_update.do','修改文档','id=218;title=a'),(1981,1,1,3,'2014-05-26 18:49:36','0:0:0:0:0:0:0:1','/javapms/admin/doc/o_ajax_delete.do','删除文档','id=218;title=a'),(1982,1,1,3,'2015-08-23 10:33:39','127.0.0.1','/admin/keyword/o_ajax_delete.do','删除关键字','id=3;name=javapms'),(1983,1,1,3,'2015-08-23 10:33:39','127.0.0.1','/admin/keyword/o_ajax_delete.do','删除关键字','id=1;name=淘宝'),(1984,1,1,3,'2015-09-09 15:14:35','0:0:0:0:0:0:0:1','/admin/channel/o_update.do','channel.log.update','id=5;name=国内'),(1985,1,1,3,'2015-09-09 15:45:52','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=6;title=NBA'),(1986,1,1,3,'2015-09-09 15:45:52','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=16;title=足球'),(1987,1,1,3,'2015-09-09 15:45:52','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=17;title=综合'),(1988,1,1,3,'2015-09-09 15:45:52','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=18;title=网球'),(1989,1,1,3,'2015-09-09 15:45:52','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=19;title=F1赛车'),(1990,1,1,3,'2015-09-09 15:46:06','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=21;title=电影'),(1991,1,1,3,'2015-09-09 15:46:06','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=22;title=电视'),(1992,1,1,3,'2015-09-09 15:46:06','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=23;title=音乐'),(1993,1,1,3,'2015-09-09 15:46:06','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=24;title=明星'),(1994,1,1,3,'2015-09-09 15:46:21','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=27;title=股票'),(1995,1,1,3,'2015-09-09 15:46:21','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=28;title=基金'),(1996,1,1,3,'2015-09-09 15:46:21','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=29;title=外汇'),(1997,1,1,3,'2015-09-09 15:46:22','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=30;title=期货'),(1998,1,1,3,'2015-09-09 15:46:34','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=212;title=快讯'),(1999,1,1,3,'2015-09-09 15:46:34','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=213;title=家居'),(2000,1,1,3,'2015-09-09 15:46:34','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=214;title=装修'),(2001,1,1,3,'2015-09-09 15:46:34','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=215;title=投资'),(2002,1,1,3,'2015-09-09 15:46:45','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=25;title=互联网'),(2003,1,1,3,'2015-09-09 15:46:46','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=36;title=IT'),(2004,1,1,3,'2015-09-09 15:46:46','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=37;title=家电'),(2005,1,1,3,'2015-09-09 15:46:46','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=38;title=探索'),(2006,1,1,3,'2015-09-09 15:47:17','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=31;title=服装'),(2007,1,1,3,'2015-09-09 15:47:17','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=42;title=饰品'),(2008,1,1,3,'2015-09-09 15:47:17','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=43;title=名车'),(2009,1,1,3,'2015-09-09 15:47:18','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=44;title=美食'),(2010,1,1,3,'2015-09-09 15:47:30','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=32;title=游记'),(2011,1,1,3,'2015-09-09 15:47:30','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=45;title=户外'),(2012,1,1,3,'2015-09-09 15:47:30','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=46;title=景点'),(2013,1,1,3,'2015-09-09 15:47:30','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=47;title=特产'),(2014,1,1,3,'2015-09-09 15:47:47','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=34;title=关于我们'),(2015,1,1,3,'2015-09-09 15:47:47','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=49;title=网站导航'),(2016,1,1,3,'2015-09-09 15:48:29','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=26;title=保健'),(2017,1,1,3,'2015-09-09 15:48:29','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=40;title=养生'),(2018,1,1,3,'2015-09-09 15:48:29','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=41;title=名医'),(2019,1,1,3,'2015-09-09 15:48:29','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=39;title=药品'),(2020,1,1,3,'2015-09-09 15:49:34','0:0:0:0:0:0:0:1','/admin/doc/o_ajax_delete.do','删除文档','id=59;title=芦山灾区在厦职工可一次性提住房公积金余额'),(2021,1,1,3,'2015-09-09 15:49:34','0:0:0:0:0:0:0:1','/admin/doc/o_ajax_delete.do','删除文档','id=58;title=车库改成住房当心被查处'),(2022,1,1,3,'2015-09-09 15:49:35','0:0:0:0:0:0:0:1','/admin/doc/o_ajax_delete.do','删除文档','id=53;title=一睹为快全世界排名前十位富豪的房子'),(2023,1,1,3,'2015-09-09 15:49:35','0:0:0:0:0:0:0:1','/admin/doc/o_ajax_delete.do','删除文档','id=52;title=西部住房置业担保机构抱团发展'),(2024,1,1,3,'2015-09-09 15:49:35','0:0:0:0:0:0:0:1','/admin/doc/o_ajax_delete.do','删除文档','id=50;title=四川发布交通管制信息 禁社会车辆自行前往灾区'),(2025,1,1,3,'2015-09-09 15:49:35','0:0:0:0:0:0:0:1','/admin/doc/o_ajax_delete.do','删除文档','id=49;title=游客抵制凤凰古城涨价 五一古城客房预订降半'),(2026,1,1,3,'2015-09-09 15:49:35','0:0:0:0:0:0:0:1','/admin/doc/o_ajax_delete.do','删除文档','id=48;title=雅安震区天全县喇叭河景区100余名工人被困'),(2027,1,1,3,'2015-09-09 15:49:35','0:0:0:0:0:0:0:1','/admin/doc/o_ajax_delete.do','删除文档','id=45;title=成都火车站组织改签、退票 不收退票费'),(2028,1,1,3,'2015-09-09 15:49:35','0:0:0:0:0:0:0:1','/admin/doc/o_ajax_delete.do','删除文档','id=44;title=一载客300人游轮长江武汉段起火 现场浓烟滚滚'),(2029,1,1,3,'2015-09-09 15:49:35','0:0:0:0:0:0:0:1','/admin/doc/o_ajax_delete.do','删除文档','id=41;title=衣食住行碳排放量可“算”出来'),(2030,1,1,3,'2015-09-09 15:49:36','0:0:0:0:0:0:0:1','/admin/doc/o_ajax_delete.do','删除文档','id=38;title=沪铁路局春游40天送客4126万人 创历史新高'),(2031,1,1,3,'2015-09-09 15:49:36','0:0:0:0:0:0:0:1','/admin/doc/o_ajax_delete.do','删除文档','id=34;title=17个要点能防禽流感：中午通风半小时 饮食多样化'),(2032,1,1,3,'2015-09-09 15:49:36','0:0:0:0:0:0:0:1','/admin/doc/o_ajax_delete.do','删除文档','id=30;title=我国一季度商品房销售面积增八成'),(2033,1,1,3,'2015-09-09 15:49:36','0:0:0:0:0:0:0:1','/admin/doc/o_ajax_delete.do','删除文档','id=28;title=新房装修不得不知的验房知识'),(2034,1,1,3,'2015-09-09 15:49:36','0:0:0:0:0:0:0:1','/admin/doc/o_ajax_delete.do','删除文档','id=27;title=细数十大奇葩创意厕纸'),(2035,1,1,3,'2015-09-09 15:49:43','0:0:0:0:0:0:0:1','/admin/doc/o_ajax_delete.do','删除文档','id=26;title=理性家装 家装“游击队”省钱不省心'),(2036,1,1,3,'2015-09-09 15:49:43','0:0:0:0:0:0:0:1','/admin/doc/o_ajax_delete.do','删除文档','id=14;title=南昌新政 电动车载12岁以上者罚30'),(2037,1,1,3,'2015-09-09 15:49:43','0:0:0:0:0:0:0:1','/admin/doc/o_ajax_delete.do','删除文档','id=11;title=在家里拥有专属于自己的图书馆不再是梦想'),(2038,1,1,3,'2015-09-09 15:49:44','0:0:0:0:0:0:0:1','/admin/doc/o_ajax_delete.do','删除文档','id=6;title=五万装修五十平米彩色单身公寓'),(2039,1,1,3,'2015-09-09 15:49:44','0:0:0:0:0:0:0:1','/admin/doc/o_ajax_delete.do','删除文档','id=5;title=小户型收纳绝佳利器 超容量创意鞋柜'),(2040,1,1,3,'2015-09-09 15:50:25','0:0:0:0:0:0:0:1','/admin/model/o_update.do','修改模型','id=2;name=新闻'),(2041,1,1,3,'2015-09-09 15:50:49','0:0:0:0:0:0:0:1','/admin/model/o_update.do','修改模型','id=3;name=图集'),(2042,1,1,3,'2015-09-09 15:51:02','0:0:0:0:0:0:0:1','/admin/model/o_update.do','修改模型','id=4;name=视频'),(2043,1,1,3,'2015-09-09 15:51:12','0:0:0:0:0:0:0:1','/admin/model/o_update.do','修改模型','id=5;name=专题'),(2044,1,1,3,'2015-09-09 15:52:21','0:0:0:0:0:0:0:1','/admin/channel/o_update.do','channel.log.update','id=1;name=科室简介'),(2045,1,1,3,'2015-09-09 15:53:03','0:0:0:0:0:0:0:1','/admin/channel/o_update.do','channel.log.update','id=1;name=科室简介'),(2046,1,1,3,'2015-09-09 15:53:31','0:0:0:0:0:0:0:1','/admin/channel/o_update.do','channel.log.update','id=2;name=体育'),(2047,1,1,3,'2015-09-09 15:54:02','0:0:0:0:0:0:0:1','/admin/channel/o_update.do','channel.log.update','id=2;name=工作动态'),(2048,1,1,3,'2015-09-09 15:54:26','0:0:0:0:0:0:0:1','/admin/channel/o_update.do','channel.log.update','id=3;name=娱乐'),(2049,1,1,3,'2015-09-09 15:54:58','0:0:0:0:0:0:0:1','/admin/channel/o_update.do','channel.log.update','id=3;name=法律法规'),(2050,1,1,3,'2015-09-09 15:55:41','0:0:0:0:0:0:0:1','/admin/channel/o_update.do','channel.log.update','id=4;name=岗位职责'),(2051,1,1,3,'2015-09-09 15:56:30','0:0:0:0:0:0:0:1','/admin/channel/o_update.do','channel.log.update','id=211;name=信息调研'),(2052,1,1,3,'2015-09-09 15:57:41','0:0:0:0:0:0:0:1','/admin/channel/o_update.do','channel.log.update','id=13;name=规章制度'),(2053,1,1,3,'2015-09-09 15:58:46','0:0:0:0:0:0:0:1','/admin/channel/o_update.do','channel.log.update','id=14;name=国库券'),(2054,1,1,3,'2015-09-09 15:59:32','0:0:0:0:0:0:0:1','/admin/channel/o_update.do','channel.log.update','id=15;name=学习园地'),(2055,1,1,3,'2015-09-09 15:59:56','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=20;title=旅游'),(2056,1,1,3,'2015-09-09 15:59:56','0:0:0:0:0:0:0:1','/admin/channel/o_ajax_delete.do','channel.log.delete','id=33;title=其它'),(2057,1,1,3,'2015-09-09 16:01:06','0:0:0:0:0:0:0:1','/admin/channel/o_save.do','channel.log.save','id=217;title=荣誉展示'),(2058,1,1,3,'2015-09-09 16:01:20','0:0:0:0:0:0:0:1','/admin/channel/o_update.do','channel.log.update','id=1;name=科室简介'),(2059,1,1,3,'2015-09-09 16:01:30','0:0:0:0:0:0:0:1','/admin/channel/o_update.do','channel.log.update','id=2;name=工作动态'),(2060,1,1,3,'2015-09-09 16:01:39','0:0:0:0:0:0:0:1','/admin/channel/o_update.do','channel.log.update','id=3;name=法律法规'),(2061,1,1,3,'2015-09-09 16:01:49','0:0:0:0:0:0:0:1','/admin/channel/o_update.do','channel.log.update','id=4;name=岗位职责'),(2062,1,1,3,'2015-09-09 16:01:58','0:0:0:0:0:0:0:1','/admin/channel/o_update.do','channel.log.update','id=211;name=信息调研'),(2063,1,1,3,'2015-09-09 16:02:09','0:0:0:0:0:0:0:1','/admin/channel/o_update.do','channel.log.update','id=13;name=规章制度'),(2064,1,1,3,'2015-09-09 16:02:17','0:0:0:0:0:0:0:1','/admin/channel/o_update.do','channel.log.update','id=14;name=国库券'),(2065,1,1,3,'2015-09-09 16:02:26','0:0:0:0:0:0:0:1','/admin/channel/o_update.do','channel.log.update','id=15;name=学习园地');

/*Table structure for table  tq_mailbox  */

DROP TABLE IF EXISTS  tq_mailbox ;

CREATE TABLE  tq_mailbox  (
   mailbox_id  int NOT NULL AUTO_INCREMENT,
   site_id  int NOT NULL DEFAULT '0' COMMENT '站点ID',
   depart_id  int DEFAULT NULL COMMENT '部门ID',
   type_id  int NOT NULL COMMENT '分类ID',
   title  varchar(50) NOT NULL COMMENT '标题',
   name  varchar(20) NOT NULL COMMENT '姓名',
   mobile  varchar(20) NOT NULL COMMENT '电话',
   email  varchar(50) NOT NULL COMMENT '电子邮箱',
   address  varchar(150) DEFAULT NULL COMMENT '联系地址',
   zipcode  varchar(20) DEFAULT NULL COMMENT '邮编',
   status  int NOT NULL COMMENT '状态',
   is_show  bit(1) NOT NULL COMMENT '是否公开',
   create_time  datetime NOT NULL COMMENT '写信时间',
   reply_time  datetime DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY ( mailbox_id ),
  KEY  fk_mailbox_to_site  ( site_id ),
  KEY  fk_mailbox_to_depart  ( depart_id ),
  KEY  fk_mailbox_to_type  ( type_id ),
  CONSTRAINT  fk_mailbox_to_depart  FOREIGN KEY ( depart_id ) REFERENCES  tq_depart  ( depart_id ),
  CONSTRAINT  fk_mailbox_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id ),
  CONSTRAINT  fk_mailbox_to_type  FOREIGN KEY ( type_id ) REFERENCES  tq_mailbox_type  ( type_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_mailbox  */

/*Table structure for table  tq_mailbox_ext  */

DROP TABLE IF EXISTS  tq_mailbox_ext ;

CREATE TABLE  tq_mailbox_ext  (
   mailbox_id  int NOT NULL,
   content  varchar(2000) NOT NULL COMMENT '内容',
   reply  varchar(2000) DEFAULT NULL COMMENT '回复',
  PRIMARY KEY ( mailbox_id ),
  CONSTRAINT  fk_mailboxext_to_mailbox  FOREIGN KEY ( mailbox_id ) REFERENCES  tq_mailbox  ( mailbox_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_mailbox_ext  */

/*Table structure for table  tq_mailbox_type  */

DROP TABLE IF EXISTS  tq_mailbox_type ;

CREATE TABLE  tq_mailbox_type  (
   type_id  int NOT NULL AUTO_INCREMENT,
   site_id  int NOT NULL DEFAULT '0' COMMENT '站点ID',
   name  varchar(20) NOT NULL COMMENT '名称',
   priority  int NOT NULL COMMENT '排序',
  PRIMARY KEY ( type_id ),
  KEY  fk_mailboxtype_to_site  ( site_id ),
  CONSTRAINT  fk_mailboxtype_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id )
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table  tq_mailbox_type  */

insert  into  tq_mailbox_type ( type_id , site_id , name , priority ) values (1,1,'咨询',1);

/*Table structure for table  tq_member  */

DROP TABLE IF EXISTS  tq_member ;

CREATE TABLE  tq_member  (
   member_id  int NOT NULL,
   avatar  varchar(100) DEFAULT NULL COMMENT '头像',
   gender  int NOT NULL COMMENT '性别',
   birthday  date DEFAULT NULL COMMENT '生日',
   address  varchar(100) DEFAULT NULL COMMENT '住址',
   signature  varchar(255) DEFAULT NULL COMMENT '个性签名',
   registe_time  datetime NOT NULL COMMENT '注册时间',
   registe_ip  varchar(20) DEFAULT NULL COMMENT '注册IP',
   last_login_time  datetime DEFAULT NULL COMMENT '最后登录时间',
   last_login_ip  varchar(20) DEFAULT NULL COMMENT '最后登录IP',
   login_count  int NOT NULL COMMENT '登录次数',
   t_status  int NOT NULL COMMENT '状态',
  PRIMARY KEY ( member_id ),
  CONSTRAINT  fk_member_to_user  FOREIGN KEY ( member_id ) REFERENCES  tq_user  ( user_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_member  */

insert  into  tq_member ( member_id , avatar , gender , birthday , address , signature , registe_time , registe_ip , last_login_time , last_login_ip , login_count , t_status ) values (1,'/member/upload/pms/201405/25124618cjtl.png',1,'2013-10-01','江西省南昌市','倾力打造JAVA、JSP门户管理系统品牌。','2013-08-06 16:09:41','0:0:0:0:0:0:0:1','2013-08-06 16:09:41',NULL,1,0),(3,'/member/upload/pms/201405/26180116f9ii.png',0,'1989-05-10','trytry','生命在于折腾~','2013-04-27 10:38:03','0:0:0:0:0:0:0:1','2014-05-26 18:47:53','0:0:0:0:0:0:0:1',31,0);

/*Table structure for table  tq_member_group  */

DROP TABLE IF EXISTS  tq_member_group ;

CREATE TABLE  tq_member_group  (
   group_id  int NOT NULL COMMENT '会员组ID',
   member_id  int NOT NULL COMMENT '会员ID',
  PRIMARY KEY ( group_id , member_id ),
  KEY  fk_membergroup_to_member  ( member_id ),
  CONSTRAINT  fk_membergroup_to_group  FOREIGN KEY ( group_id ) REFERENCES  tq_group  ( group_id ),
  CONSTRAINT  fk_membergroup_to_member  FOREIGN KEY ( member_id ) REFERENCES  tq_member  ( member_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_member_group  */

insert  into  tq_member_group ( group_id , member_id ) values (1,3);

/*Table structure for table  tq_message_board  */

DROP TABLE IF EXISTS  tq_message_board ;

CREATE TABLE  tq_message_board  (
   board_id  int NOT NULL AUTO_INCREMENT,
   site_id  int NOT NULL COMMENT '站点ID',
   type_id  int NOT NULL COMMENT '分类ID',
   title  varchar(50) DEFAULT NULL COMMENT '标题',
   name  varchar(20) DEFAULT NULL COMMENT '姓名',
   mobile  varchar(20) DEFAULT NULL COMMENT '电话',
   email  varchar(50) DEFAULT NULL COMMENT '电子邮箱',
   address  varchar(150) DEFAULT NULL COMMENT '联系地址',
   zipcode  varchar(20) DEFAULT NULL COMMENT '邮编',
   is_show  bit(1) NOT NULL COMMENT '是否公开',
   create_time  datetime NOT NULL COMMENT '提交时间',
   reply_time  datetime DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY ( board_id ),
  KEY  fk_message_board_to_site  ( site_id ),
  KEY  fk_message_board_to_type  ( type_id ),
  CONSTRAINT  fk_message_board_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id ),
  CONSTRAINT  fk_message_board_to_type  FOREIGN KEY ( type_id ) REFERENCES  tq_message_type  ( type_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_message_board  */

/*Table structure for table  tq_message_board_ext  */

DROP TABLE IF EXISTS  tq_message_board_ext ;

CREATE TABLE  tq_message_board_ext  (
   board_id  int NOT NULL,
   content  varchar(2000) NOT NULL COMMENT '内容',
   reply  varchar(2000) DEFAULT NULL COMMENT '回复',
  PRIMARY KEY ( board_id ),
  CONSTRAINT  fk_board_ext_to_message_board  FOREIGN KEY ( board_id ) REFERENCES  tq_message_board  ( board_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_message_board_ext  */

/*Table structure for table  tq_message_receive  */

DROP TABLE IF EXISTS  tq_message_receive ;

CREATE TABLE  tq_message_receive  (
   message_id  int NOT NULL,
   content  text COMMENT '接收人',
  PRIMARY KEY ( message_id ),
  CONSTRAINT  fk_message_receive_to_message  FOREIGN KEY ( message_id ) REFERENCES  tq_site_message  ( message_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_message_receive  */

/*Table structure for table  tq_message_type  */

DROP TABLE IF EXISTS  tq_message_type ;

CREATE TABLE  tq_message_type  (
   type_id  int NOT NULL AUTO_INCREMENT,
   site_id  int NOT NULL COMMENT '站点ID',
   name  varchar(20) NOT NULL COMMENT '名称',
   priority  int NOT NULL COMMENT '排序',
  PRIMARY KEY ( type_id ),
  KEY  fk_message_type_to_site  ( site_id ),
  CONSTRAINT  fk_message_type_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id )
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table  tq_message_type  */

insert  into  tq_message_type ( type_id , site_id , name , priority ) values (1,1,'咨询',10);

/*Table structure for table  tq_metier  */

DROP TABLE IF EXISTS  tq_metier ;

CREATE TABLE  tq_metier  (
   metier_id  int NOT NULL AUTO_INCREMENT,
   parent_id  int DEFAULT NULL,
   metier_code  varchar(10) NOT NULL COMMENT '编码',
   metier_name  varchar(50) NOT NULL COMMENT '名称',
  PRIMARY KEY ( metier_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_metier  */

/*Table structure for table  tq_model  */

DROP TABLE IF EXISTS  tq_model ;

CREATE TABLE  tq_model  (
   model_id  int NOT NULL AUTO_INCREMENT,
   model_name  varchar(100) NOT NULL COMMENT '名称',
   model_icon  varchar(30) NOT NULL COMMENT '图标',
   model_tpl_doc  varchar(100) DEFAULT NULL COMMENT '内容页模板',
   model_tpl_print  varchar(100) DEFAULT NULL COMMENT '打印页模板',
   model_tpl_search  varchar(100) NOT NULL COMMENT '搜索页模板',
   model_tpl_advsearch  varchar(100) NOT NULL COMMENT '高级搜索页模板',
   model_tpl_comment  varchar(100) NOT NULL COMMENT '评论页模板',
   priority  int NOT NULL DEFAULT '10' COMMENT '排列顺序',
   is_disabled  bit(1) NOT NULL DEFAULT b'0' COMMENT '是否禁用',
  PRIMARY KEY ( model_id )
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table  tq_model  */

insert  into  tq_model ( model_id , model_name , model_icon , model_tpl_doc , model_tpl_print , model_tpl_search , model_tpl_advsearch , model_tpl_comment , priority , is_disabled ) values (2,'新闻','/img/icon/xw.gif','/doc/article/content.html','','/extrafunc/search/搜索结果页.html','','',1,'\0'),(3,'图集','/img/icon/tj.gif','/doc/article/atlas.html','/doc/article/content.html','/extrafunc/search/图片搜索结果页.html','/alone/默认单页.html','/alone/默认单页.html',2,'\0'),(4,'视频','/img/icon/sp3.gif','/doc/article/content.html','','/extrafunc/search/搜索结果页.html','','',3,'\0'),(5,'专题','/img/icon/zt.gif','/doc/article/content.html','','/extrafunc/search/搜索结果页.html','','',4,'\0');

/*Table structure for table  tq_model_field  */

DROP TABLE IF EXISTS  tq_model_field ;

CREATE TABLE  tq_model_field  (
   field_id  int NOT NULL AUTO_INCREMENT,
   model_id  int NOT NULL,
   name  varchar(50) NOT NULL COMMENT '字段名称',
   label  varchar(100) NOT NULL COMMENT '字段别名',
   priority  int NOT NULL DEFAULT '70' COMMENT '排列顺序',
   text_size  varchar(20) DEFAULT NULL COMMENT '长度',
   text_maxlength  varchar(10) DEFAULT NULL COMMENT '最大长度',
   width  varchar(10) DEFAULT NULL COMMENT '输入框宽度',
   height  varchar(3) DEFAULT NULL COMMENT '输入框高度',
   tip  varchar(255) DEFAULT NULL COMMENT '说明',
   value_list  varchar(255) DEFAULT NULL COMMENT '可选值列表',
   data_type  int NOT NULL COMMENT '数据类型',
   is_required  bit(1) NOT NULL DEFAULT b'1' COMMENT '是否必填',
   is_single  bit(1) NOT NULL DEFAULT b'1' COMMENT '是否并列',
   is_economy  bit(1) NOT NULL DEFAULT b'0' COMMENT '是否系统字段',
   is_show  bit(1) NOT NULL DEFAULT b'1' COMMENT '是否显示',
  PRIMARY KEY ( field_id ),
  KEY  fk_tq_item_model  ( model_id ),
  CONSTRAINT  fk_tq_item_model  FOREIGN KEY ( model_id ) REFERENCES  tq_model  ( model_id )
) ENGINE=InnoDB AUTO_INCREMENT=93 DEFAULT CHARSET=utf8;

/*Data for the table  tq_model_field  */

insert  into  tq_model_field ( field_id , model_id , name , label , priority , text_size , text_maxlength , width , height , tip , value_list , data_type , is_required , is_single , is_economy , is_show ) values (1,2,'channelId','栏目',1,NULL,NULL,NULL,NULL,NULL,NULL,9,'','','',''),(2,2,'title','标题',2,NULL,NULL,NULL,NULL,NULL,NULL,1,'','','',''),(3,2,'shortTitle','短标题',5,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','\0','',''),(4,2,'titleColor','标题颜色',4,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','\0','',''),(5,2,'subTitle','副标题',3,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','\0','',''),(6,2,'tagStr','Tag标签',6,'25','','','','','',1,'\0','\0','',''),(7,2,'description','摘要',7,NULL,NULL,NULL,NULL,NULL,NULL,2,'\0','','',''),(8,2,'author','作者',9,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','\0','',''),(9,2,'origin','来源',8,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','\0','',''),(10,2,'style','类型',10,NULL,'','','','','',8,'\0','\0','',''),(11,2,'recommend','属性',11,NULL,'','','','','',8,'\0','\0','',''),(12,2,'showIndex','显示到首页',22,NULL,NULL,NULL,NULL,NULL,NULL,7,'\0','','','\0'),(13,2,'redTape','红头文件',18,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','','','\0'),(14,2,'viewGroups','访问权限',21,NULL,NULL,NULL,NULL,NULL,NULL,8,'\0','','','\0'),(15,2,'tplContent','内容模板',14,NULL,NULL,NULL,NULL,NULL,NULL,1,'','\0','',''),(16,2,'atts','附件',20,NULL,NULL,NULL,NULL,NULL,NULL,10,'\0','','','\0'),(17,2,'releaseDate','发布时间',15,NULL,NULL,NULL,NULL,NULL,NULL,6,'','\0','',''),(18,2,'link','外部链接',16,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','','',''),(19,2,'commentControl','评论控制',17,NULL,NULL,NULL,NULL,NULL,NULL,7,'\0','','','\0'),(20,2,'updownControl','顶踩控制',19,NULL,NULL,NULL,NULL,NULL,NULL,7,'\0','','','\0'),(21,2,'txt','内容',13,NULL,NULL,NULL,NULL,NULL,NULL,3,'\0','','',''),(22,2,'picture','缩略图',12,NULL,NULL,NULL,NULL,NULL,NULL,10,'\0','','',''),(23,2,'pics','组图',23,NULL,NULL,NULL,NULL,NULL,NULL,10,'\0','','','\0'),(24,3,'channelId','栏目',1,NULL,NULL,NULL,NULL,NULL,NULL,9,'','','',''),(25,3,'title','标题',2,NULL,NULL,NULL,NULL,NULL,NULL,1,'','','',''),(26,3,'shortTitle','短标题',12,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','\0','','\0'),(27,3,'titleColor','标题颜色',13,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','\0','','\0'),(28,3,'subTitle','副标题',21,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','','','\0'),(29,3,'tagStr','Tag标签',14,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','','','\0'),(30,3,'description','摘要',3,NULL,NULL,NULL,NULL,NULL,NULL,2,'\0','','',''),(31,3,'author','作者',5,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','\0','',''),(32,3,'origin','来源',4,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','\0','',''),(33,3,'style','新闻类型',6,NULL,NULL,NULL,NULL,NULL,NULL,8,'\0','\0','',''),(34,3,'recommend','属性',7,NULL,'','','','','',8,'\0','\0','',''),(35,3,'showIndex','显示到首页',22,NULL,NULL,NULL,NULL,NULL,NULL,7,'\0','','','\0'),(36,3,'redTape','红头文件',23,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','','','\0'),(37,3,'viewGroups','访问权限',20,NULL,NULL,NULL,NULL,NULL,NULL,8,'\0','','','\0'),(38,3,'tplContent','内容模板',10,NULL,NULL,NULL,NULL,NULL,NULL,1,'','\0','',''),(39,3,'atts','附件',19,NULL,NULL,NULL,NULL,NULL,NULL,10,'\0','','','\0'),(40,3,'releaseDate','发布时间',11,NULL,NULL,NULL,NULL,NULL,NULL,6,'','\0','',''),(41,3,'link','外部链接',16,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','','','\0'),(42,3,'commentControl','评论控制',17,NULL,NULL,NULL,NULL,NULL,NULL,7,'\0','','','\0'),(43,3,'updownControl','顶踩控制',18,NULL,NULL,NULL,NULL,NULL,NULL,7,'\0','','','\0'),(44,3,'txt','内容',15,NULL,NULL,NULL,NULL,NULL,NULL,3,'\0','','','\0'),(45,3,'picture','缩略图',8,NULL,NULL,NULL,NULL,NULL,NULL,10,'\0','','',''),(46,3,'pics','组图',9,NULL,NULL,NULL,NULL,NULL,NULL,10,'\0','','',''),(47,4,'channelId','栏目',1,NULL,NULL,NULL,NULL,NULL,NULL,9,'','','',''),(48,4,'title','标题',2,NULL,NULL,NULL,NULL,NULL,NULL,1,'','','',''),(49,4,'shortTitle','短标题',12,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','','','\0'),(50,4,'titleColor','标题颜色',13,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','','','\0'),(51,4,'subTitle','副标题',14,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','','','\0'),(52,4,'tagStr','Tag标签',15,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','','','\0'),(53,4,'description','摘要',7,NULL,NULL,NULL,NULL,NULL,NULL,2,'\0','','',''),(54,4,'author','作者',4,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','\0','',''),(55,4,'origin','来源',3,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','\0','',''),(56,4,'style','新闻类型',5,NULL,NULL,NULL,NULL,NULL,NULL,8,'\0','\0','',''),(57,4,'recommend','属性',6,NULL,'','','','','',8,'\0','\0','',''),(58,4,'showIndex','显示到首页',16,NULL,NULL,NULL,NULL,NULL,NULL,7,'\0','','','\0'),(59,4,'redTape','红头文件',17,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','','','\0'),(60,4,'viewGroups','访问权限',18,NULL,NULL,NULL,NULL,NULL,NULL,8,'\0','\0','','\0'),(61,4,'tplContent','内容模板',10,NULL,NULL,NULL,NULL,NULL,NULL,1,'','\0','',''),(62,4,'atts','附件',19,NULL,NULL,NULL,NULL,NULL,NULL,10,'\0','','','\0'),(63,4,'releaseDate','发布时间',11,NULL,NULL,NULL,NULL,NULL,NULL,6,'','\0','',''),(64,4,'link','外部链接',20,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','','','\0'),(65,4,'commentControl','评论控制',21,NULL,NULL,NULL,NULL,NULL,NULL,7,'\0','','','\0'),(66,4,'updownControl','顶踩控制',22,NULL,NULL,NULL,NULL,NULL,NULL,7,'\0','','','\0'),(67,4,'txt','内容',9,NULL,NULL,NULL,NULL,NULL,NULL,3,'\0','','',''),(68,4,'picture','缩略图',8,NULL,NULL,NULL,NULL,NULL,NULL,10,'\0','','',''),(69,4,'pics','组图',23,NULL,NULL,NULL,NULL,NULL,NULL,10,'\0','','','\0'),(70,5,'channelId','栏目',1,NULL,NULL,NULL,NULL,NULL,NULL,9,'','','',''),(71,5,'title','标题',2,NULL,NULL,NULL,NULL,NULL,NULL,1,'','','',''),(72,5,'shortTitle','短标题',3,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','','',''),(73,5,'titleColor','标题颜色',4,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','','',''),(74,5,'subTitle','副标题',5,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','','',''),(75,5,'tagStr','Tag标签',6,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','','',''),(76,5,'description','摘要',7,NULL,NULL,NULL,NULL,NULL,NULL,2,'\0','','',''),(77,5,'author','作者',8,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','','',''),(78,5,'origin','来源',9,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','','',''),(79,5,'style','新闻类型',10,NULL,NULL,NULL,NULL,NULL,NULL,8,'\0','','',''),(80,5,'recommend','推荐',11,NULL,NULL,NULL,NULL,NULL,NULL,8,'\0','','',''),(81,5,'showIndex','显示到首页',12,NULL,NULL,NULL,NULL,NULL,NULL,7,'\0','','',''),(82,5,'redTape','红头文件',13,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','','',''),(83,5,'viewGroups','访问权限',14,NULL,NULL,NULL,NULL,NULL,NULL,8,'\0','','',''),(84,5,'tplContent','内容模板',15,NULL,NULL,NULL,NULL,NULL,NULL,1,'','','',''),(85,5,'atts','附件',16,NULL,NULL,NULL,NULL,NULL,NULL,10,'\0','','',''),(86,5,'releaseDate','发布时间',17,NULL,NULL,NULL,NULL,NULL,NULL,6,'','','',''),(87,5,'link','外部链接',18,NULL,NULL,NULL,NULL,NULL,NULL,1,'\0','','',''),(88,5,'commentControl','评论控制',19,NULL,NULL,NULL,NULL,NULL,NULL,7,'\0','','',''),(89,5,'updownControl','顶踩控制',20,NULL,NULL,NULL,NULL,NULL,NULL,7,'\0','','',''),(90,5,'txt','内容',21,NULL,NULL,NULL,NULL,NULL,NULL,3,'\0','','',''),(91,5,'picture','缩略图',22,NULL,NULL,NULL,NULL,NULL,NULL,10,'\0','','',''),(92,5,'pics','组图',23,NULL,NULL,NULL,NULL,NULL,NULL,10,'\0','','','');

/*Table structure for table  tq_personal_favorite  */

DROP TABLE IF EXISTS  tq_personal_favorite ;

CREATE TABLE  tq_personal_favorite  (
   favorite_id  int NOT NULL AUTO_INCREMENT,
   personal_id  int NOT NULL,
   job_id  int NOT NULL,
   create_time  datetime NOT NULL,
  PRIMARY KEY ( favorite_id ),
  KEY  fk_job_personal  ( job_id ),
  KEY  fk_personal_job  ( personal_id ),
  CONSTRAINT  fk_job_personal  FOREIGN KEY ( job_id ) REFERENCES  tq_job_post  ( job_id ),
  CONSTRAINT  fk_personal_job  FOREIGN KEY ( personal_id ) REFERENCES  tq_personal_info  ( personal_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_personal_favorite  */

/*Table structure for table  tq_personal_info  */

DROP TABLE IF EXISTS  tq_personal_info ;

CREATE TABLE  tq_personal_info  (
   personal_id  int NOT NULL AUTO_INCREMENT,
   work_exp  int NOT NULL COMMENT '工作经验',
   is_work  int NOT NULL COMMENT '是否在职',
   is_check  int NOT NULL COMMENT '是否审核',
   is_commend  int NOT NULL COMMENT '是否推荐',
   avatar  varchar(50) DEFAULT NULL,
   realname  varchar(50) DEFAULT NULL,
   gender  int DEFAULT NULL,
   birthday  datetime DEFAULT NULL,
   educational  int DEFAULT NULL,
   special_id  int DEFAULT NULL,
  PRIMARY KEY ( personal_id ),
  KEY  FK_PERSONAL_SPECIAL  ( special_id ),
  CONSTRAINT  FK_PERSONAL_SPECIAL  FOREIGN KEY ( special_id ) REFERENCES  tq_specialty  ( special_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_personal_info  */

/*Table structure for table  tq_personal_info_ext  */

DROP TABLE IF EXISTS  tq_personal_info_ext ;

CREATE TABLE  tq_personal_info_ext  (
   personal_id  int NOT NULL,
   card_type  int DEFAULT NULL,
   idcard  varchar(50) DEFAULT NULL,
   address  varchar(100) DEFAULT NULL,
   commun_type1  int DEFAULT NULL,
   commun1  varchar(30) DEFAULT NULL,
   commun_type2  int DEFAULT NULL,
   commun2  varchar(30) DEFAULT NULL,
   marriage  int DEFAULT NULL,
   political  varchar(10) DEFAULT NULL,
   nation  varchar(20) DEFAULT NULL,
  PRIMARY KEY ( personal_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_personal_info_ext  */

/*Table structure for table  tq_petition  */

DROP TABLE IF EXISTS  tq_petition ;

CREATE TABLE  tq_petition  (
   petition_id  int NOT NULL AUTO_INCREMENT,
   site_id  int NOT NULL COMMENT '站点ID',
   type_id  int NOT NULL COMMENT '分类ID',
   title  varchar(50) NOT NULL COMMENT '标题',
   name  varchar(20) NOT NULL COMMENT '姓名',
   mobile  varchar(20) NOT NULL COMMENT '电话',
   email  varchar(50) NOT NULL COMMENT '电子邮箱',
   address  varchar(150) DEFAULT NULL COMMENT '联系地址',
   zipcode  varchar(20) DEFAULT NULL COMMENT '邮编',
   status  int NOT NULL COMMENT '状态',
   is_show  bit(1) NOT NULL COMMENT '是否公开',
   create_time  datetime NOT NULL COMMENT '提交时间',
   reply_time  datetime DEFAULT NULL COMMENT '回复时间',
  PRIMARY KEY ( petition_id ),
  KEY  fk_petition_to_site  ( site_id ),
  KEY  fk_petition_to_type  ( type_id ),
  CONSTRAINT  fk_petition_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id ),
  CONSTRAINT  fk_petition_to_type  FOREIGN KEY ( type_id ) REFERENCES  tq_petition_type  ( type_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_petition  */

/*Table structure for table  tq_petition_ext  */

DROP TABLE IF EXISTS  tq_petition_ext ;

CREATE TABLE  tq_petition_ext  (
   petition_id  int NOT NULL,
   content  varchar(2000) NOT NULL COMMENT '内容',
   reply  varchar(2000) DEFAULT NULL COMMENT '回复',
  PRIMARY KEY ( petition_id ),
  CONSTRAINT  fk_petitionext_to_petition  FOREIGN KEY ( petition_id ) REFERENCES  tq_petition  ( petition_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_petition_ext  */

/*Table structure for table  tq_petition_type  */

DROP TABLE IF EXISTS  tq_petition_type ;

CREATE TABLE  tq_petition_type  (
   type_id  int NOT NULL AUTO_INCREMENT,
   site_id  int NOT NULL DEFAULT '0' COMMENT '站点ID',
   name  varchar(20) NOT NULL COMMENT '名称',
   priority  int NOT NULL COMMENT '排序',
  PRIMARY KEY ( type_id ),
  KEY  fk_petitiontype_to_site  ( site_id ),
  CONSTRAINT  fk_petitiontype_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_petition_type  */

/*Table structure for table  tq_posts  */

DROP TABLE IF EXISTS  tq_posts ;

CREATE TABLE  tq_posts  (
   posts_id  int NOT NULL AUTO_INCREMENT,
   site_id  int NOT NULL COMMENT '站点ID',
   theme_id  int NOT NULL COMMENT '主题ID',
   creater_id  int DEFAULT NULL COMMENT '发帖会员',
   title  varchar(100) DEFAULT NULL COMMENT '帖子标题',
   status  int NOT NULL COMMENT '帖子状态',
   is_affix  bit(1) NOT NULL COMMENT '是否有附件',
   is_img  bit(1) NOT NULL COMMENT '是否有图片',
   is_hidden  bit(1) NOT NULL COMMENT '是否有图片',
   floor  int DEFAULT NULL COMMENT '楼层',
   create_time  datetime NOT NULL COMMENT '发帖时间',
   quote_id  int DEFAULT NULL COMMENT '引用帖子ID',
  PRIMARY KEY ( posts_id ),
  KEY  fk_posts_to_site  ( site_id ),
  KEY  fk_posts_to_creater  ( creater_id ),
  KEY  fk_posts_to_theme  ( theme_id ),
  CONSTRAINT  fk_posts_to_creater  FOREIGN KEY ( creater_id ) REFERENCES  tq_user  ( user_id ),
  CONSTRAINT  fk_posts_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id ),
  CONSTRAINT  fk_posts_to_theme  FOREIGN KEY ( theme_id ) REFERENCES  tq_theme  ( theme_id )
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

/*Data for the table  tq_posts  */

insert  into  tq_posts ( posts_id , site_id , theme_id , creater_id , title , status , is_affix , is_img , is_hidden , floor , create_time , quote_id ) values (1,1,1,1,'测试发布帖子',0,'\0','\0','\0',1,'2013-05-26 16:20:37',NULL),(2,1,2,1,'法的',0,'\0','\0','\0',1,'2013-05-26 21:25:53',NULL),(3,1,2,1,NULL,0,'\0','\0','\0',2,'2013-05-26 22:01:12',NULL),(4,1,2,1,NULL,0,'\0','\0','\0',3,'2013-05-26 23:19:35',NULL),(5,1,3,1,'asdsad',0,'','\0','\0',1,'2013-05-27 15:11:33',NULL),(6,1,4,1,'dsgfdgdf',0,'','\0','\0',1,'2013-05-27 15:29:40',NULL),(7,1,5,1,'sfdsfdsfsd',0,'\0','\0','',1,'2013-05-27 15:32:58',NULL),(8,1,5,1,NULL,0,'\0','\0','\0',2,'2013-05-28 11:45:53',NULL),(9,1,5,1,NULL,0,'\0','\0','\0',3,'2013-05-28 14:52:12',7),(10,1,5,3,NULL,0,'\0','\0','\0',4,'2013-05-28 17:11:21',NULL),(11,1,5,3,NULL,0,'\0','\0','\0',5,'2013-05-28 17:15:42',NULL),(12,1,6,3,'美女图',0,'','\0','\0',1,'2013-05-28 17:52:00',NULL),(13,1,7,3,'测试隐藏贴',0,'\0','\0','',1,'2013-05-28 17:54:55',NULL),(14,1,7,3,NULL,0,'\0','\0','\0',2,'2013-05-28 17:55:15',13),(15,1,7,3,NULL,0,'\0','\0','\0',3,'2013-05-29 16:47:09',NULL),(16,1,7,3,NULL,0,'\0','\0','\0',4,'2013-05-29 16:49:09',NULL),(17,1,7,3,NULL,0,'\0','\0','\0',5,'2013-05-29 19:50:23',NULL),(19,1,7,3,NULL,0,'\0','\0','\0',6,'2013-05-30 10:44:55',NULL),(20,1,8,3,NULL,0,'\0','\0','',1,'2013-05-30 10:58:16',NULL),(21,1,9,3,'fddfgdfg',0,'\0','\0','\0',1,'2013-05-30 15:23:42',NULL),(22,1,10,3,'fhfgh',0,'\0','\0','\0',1,'2013-05-30 15:25:13',NULL),(23,1,11,3,'ghfhf',0,'\0','\0','\0',1,'2013-05-30 15:37:19',NULL),(24,1,12,3,'fdgfdgd',0,'\0','\0','\0',1,'2013-05-30 15:39:20',NULL),(25,1,13,3,'dsfdsfs',-1,'\0','\0','\0',1,'2013-05-30 15:40:19',NULL),(26,1,14,3,'dgfdgfdg',-1,'\0','\0','\0',1,'2013-05-30 15:41:18',NULL),(27,1,15,1,'dgdfgdfgdfg',0,'','','\0',1,'2013-05-31 16:59:16',NULL),(28,1,16,1,'fdgdfg',0,'\0','\0','\0',1,'2013-05-31 18:14:17',NULL),(29,1,17,1,'xzcxzc',0,'\0','\0','\0',1,'2013-05-31 21:35:07',NULL),(30,1,18,1,'dfgfd',0,'\0','\0','\0',1,'2013-06-03 09:30:35',NULL),(31,1,19,1,'dfg',0,'\0','\0','\0',1,'2013-06-05 08:59:33',NULL),(32,1,20,1,'测试编辑帖子',0,'\0','\0','\0',1,'2013-11-04 10:52:37',NULL);

/*Table structure for table  tq_posts_attach  */

DROP TABLE IF EXISTS  tq_posts_attach ;

CREATE TABLE  tq_posts_attach  (
   posts_id  int NOT NULL COMMENT '帖子ID',
   name  varchar(100) DEFAULT NULL COMMENT '附件名称',
   description  varchar(255) DEFAULT NULL COMMENT '附件描述',
   file_path  varchar(100) NOT NULL COMMENT '文件地址',
   file_name  varchar(50) DEFAULT NULL COMMENT '文件名称',
   file_size  int DEFAULT NULL COMMENT '文件大小',
   is_img  bit(1) NOT NULL COMMENT '是否为图片',
   priority  int DEFAULT NULL,
  KEY  fk_postattach_to_posts  ( posts_id ),
  CONSTRAINT  fk_postattach_to_posts  FOREIGN KEY ( posts_id ) REFERENCES  tq_posts  ( posts_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_posts_attach  */

insert  into  tq_posts_attach ( posts_id , name , description , file_path , file_name , file_size , is_img , priority ) values (5,'1.jpg',NULL,'/member/upload/demo/201305/27151133jmw8.jpg','1.jpg',42417,'',0),(6,'12.jpg',NULL,'/member/upload/demo/201305/27152940yd4n.jpg','12.jpg',22946,'',0),(12,'12.jpg',NULL,'/member/upload/demo/201305/28175200la3k.jpg','12.jpg',22946,'',0),(27,'12.jpg',NULL,'/member/upload/demo/201305/31165916ua39.jpg','12.jpg',22946,'',0);

/*Table structure for table  tq_posts_ext  */

DROP TABLE IF EXISTS  tq_posts_ext ;

CREATE TABLE  tq_posts_ext  (
   posts_id  int NOT NULL,
   editer_id  int DEFAULT NULL COMMENT '修改会员ID',
   create_ip  varchar(20) NOT NULL COMMENT '发布IP',
   edit_time  datetime DEFAULT NULL COMMENT '修改时间',
   edit_ip  varchar(20) DEFAULT NULL COMMENT '修改IP',
   edit_count  int NOT NULL COMMENT '修改次数',
  PRIMARY KEY ( posts_id ),
  KEY  fk_postsext_to_editer  ( editer_id ),
  CONSTRAINT  fk_postsext_to_editer  FOREIGN KEY ( editer_id ) REFERENCES  tq_user  ( user_id ),
  CONSTRAINT  fk_postsext_to_posts  FOREIGN KEY ( posts_id ) REFERENCES  tq_posts  ( posts_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_posts_ext  */

insert  into  tq_posts_ext ( posts_id , editer_id , create_ip , edit_time , edit_ip , edit_count ) values (1,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(2,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(3,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(4,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(5,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(6,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(7,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(8,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(9,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(10,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(11,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(12,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(13,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(14,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(15,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(16,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(17,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(19,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(20,1,'0:0:0:0:0:0:0:1',NULL,NULL,1),(21,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(22,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(23,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(24,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(25,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(26,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(27,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(28,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(29,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(30,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(31,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0),(32,NULL,'0:0:0:0:0:0:0:1',NULL,NULL,0);

/*Table structure for table  tq_posts_txt  */

DROP TABLE IF EXISTS  tq_posts_txt ;

CREATE TABLE  tq_posts_txt  (
   posts_id  int NOT NULL,
   content  longtext COMMENT '内容',
  PRIMARY KEY ( posts_id ),
  CONSTRAINT  fk_poststxt_to_posts  FOREIGN KEY ( posts_id ) REFERENCES  tq_posts  ( posts_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_posts_txt  */

insert  into  tq_posts_txt ( posts_id , content ) values (1,'测试发布帖子'),(2,'个梵蒂冈'),(3,'松岛枫松岛枫'),(4,'fdhfhfghf'),(5,'[localimg]1[/localimg]asdasdasd'),(6,'[attachment]0[/attachment]gfdgfdgfd'),(7,'[hide]sdfsdfsdfs[/hide]'),(8,'                            [color=red]此处是被引用的隐藏帖[/color]\r\n                            gdfgdfgfdgdfgdfg'),(9,'\r\n                         asdasdasdasda   '),(10,'sfdfgfdgdfg'),(11,'dfsfsdfsdf'),(12,'gfdgdfg[attachment]0[/attachment]'),(13,'青年教师是高校教师队伍的重要组成部分，是推动高等教育事业科学发展、办好人民满意高等教育的重要力量。青年教师与学生年龄接近，与学生接触较多，对学生的思想行为影响更直接，他们的思想政治素质和道德情操对学生的健康成长具有重要的示范引导作用。加强和改进高校青年教师思想政治工作，对于全面贯彻党的教育方针、确保高校坚持社会主义办学方向、培养德智体美全面发展的社会主义建设者和接班人，具有重大而深远的意义。\r\n\r\n[hide]当前，高校青年教师主体积极健康向上，拥护党的领导，对坚持和发展中国特色社会主义充满信心，热爱教书育人事业，关心关爱学生，为高等教育事业发展做出重要贡献。同时也应看到，少数青年教师政治信仰迷茫、理想信念模糊、职业情感与职业道德淡化、服务意识不强，个别教师言行失范、不能为人师表；一些地方和高校对青年教师思想政治工作重视不够、工作方法不多、工作针对性和实效性不强。各地各高校党组织要充分认识新形势下加强和改进青年教师思想政治建设的重要性，切实把加强青年教师思想政治工作摆到更加突出的位置，进一步增强工作的主动性、积极性和创造性，通过政治上主动引导、专业上着力培养、生活上热情关心，促进广大青年教师坚定理想信念、练就过硬本领、勇于创新创造、矢志艰苦奋斗、锤炼高尚品格，全面提高思想政治素质和业务能力。[/hide]\r\n'),(14,'\r\n佛顶骨电饭锅的郭殿方过放电\r\n                            '),(15,'                            sdfsdfsdfsdf'),(16,'                          dsgfdgfdgfdg  '),(17,'gfdgdfgfdgfd'),(19,'fdgdfgfdgdf'),(20,'[hide]测试隐藏贴测试隐藏贴测试隐藏贴测试隐藏贴测试隐藏贴测试隐藏贴adsad[/hide]'),(21,'dfgdfgdfg[smiley=4]'),(22,'fghfghfgh'),(23,'ghgfhfghfgh'),(24,'fgdfgdfg'),(25,'dfsdfsdf'),(26,'dfgfdgfdg'),(27,'[attachment]0[/attachment]fdgfdgdfgfd'),(28,'fdgdfgdfgfd[smiley=4][smiley=8]'),(29,'zxczxcxzc'),(30,'gfdgfdg'),(31,'fdgfdgfdg'),(32,'测试编辑帖子测试编辑帖子测试编辑帖子测试编辑帖子');

/*Table structure for table  tq_profess_post  */

DROP TABLE IF EXISTS  tq_profess_post ;

CREATE TABLE  tq_profess_post  (
   post_id  int NOT NULL AUTO_INCREMENT,
   post_code  varchar(10) NOT NULL COMMENT '编码',
   post_name  varchar(50) NOT NULL COMMENT '名称',
  PRIMARY KEY ( post_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_profess_post  */

/*Table structure for table  tq_program_download  */

DROP TABLE IF EXISTS  tq_program_download ;

CREATE TABLE  tq_program_download  (
   download_id  int NOT NULL AUTO_INCREMENT,
   count  int NOT NULL,
  PRIMARY KEY ( download_id )
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table  tq_program_download  */

insert  into  tq_program_download ( download_id , count ) values (1,2);

/*Table structure for table  tq_qq_bind  */

DROP TABLE IF EXISTS  tq_qq_bind ;

CREATE TABLE  tq_qq_bind  (
   bind_id  int NOT NULL,
   username  varchar(100) NOT NULL COMMENT '绑定的用户名',
   openid  varchar(100) NOT NULL COMMENT 'id',
   openkey  varchar(100) NOT NULL COMMENT 'key',
   bind_time  datetime NOT NULL COMMENT '绑定时间',
  PRIMARY KEY ( bind_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_qq_bind  */

/*Table structure for table  tq_question_detail  */

DROP TABLE IF EXISTS  tq_question_detail ;

CREATE TABLE  tq_question_detail  (
   detail_id  int NOT NULL AUTO_INCREMENT,
   question_id  int NOT NULL COMMENT '问卷ID',
   user_id  int DEFAULT NULL COMMENT '投票会员ID',
   ip  varchar(50) DEFAULT NULL COMMENT '投票IP',
   create_time  datetime NOT NULL COMMENT '投票时间',
  PRIMARY KEY ( detail_id ),
  KEY  fk_question_detail_to_question  ( question_id ),
  CONSTRAINT  fk_question_detail_to_question  FOREIGN KEY ( question_id ) REFERENCES  tq_questionnaire  ( naire_id )
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table  tq_question_detail  */

insert  into  tq_question_detail ( detail_id , question_id , user_id , ip , create_time ) values (1,1,NULL,'0:0:0:0:0:0:0:1','2013-10-30 16:20:52'),(2,1,NULL,'0:0:0:0:0:0:0:1','2013-10-30 16:22:24'),(3,1,NULL,'0:0:0:0:0:0:0:1','2013-10-30 16:42:20'),(4,1,NULL,'0:0:0:0:0:0:0:1','2013-10-30 16:44:46'),(5,1,NULL,'0:0:0:0:0:0:0:1','2013-10-30 16:45:55'),(6,1,NULL,'0:0:0:0:0:0:0:1','2013-10-30 16:52:12'),(7,1,NULL,'0:0:0:0:0:0:0:1','2013-10-30 17:08:24'),(8,1,1,'0:0:0:0:0:0:0:1','2013-10-30 17:09:01'),(9,1,1,'0:0:0:0:0:0:0:1','2013-10-30 17:14:19'),(10,1,1,'0:0:0:0:0:0:0:1','2013-10-30 17:14:24'),(11,1,NULL,'0:0:0:0:0:0:0:1','2013-10-30 17:15:17'),(12,1,NULL,'0:0:0:0:0:0:0:1','2013-10-30 17:16:39'),(13,1,NULL,'0:0:0:0:0:0:0:1','2013-10-30 17:19:26'),(14,1,NULL,'0:0:0:0:0:0:0:1','2013-10-31 11:10:40'),(15,1,1,'0:0:0:0:0:0:0:1','2013-10-31 15:12:16');

/*Table structure for table  tq_questionnaire  */

DROP TABLE IF EXISTS  tq_questionnaire ;

CREATE TABLE  tq_questionnaire  (
   naire_id  int NOT NULL AUTO_INCREMENT,
   site_id  int NOT NULL COMMENT '站点ID',
   name  varchar(50) NOT NULL COMMENT '调查主题',
   description  varchar(300) DEFAULT NULL COMMENT '调查描述',
   repeate_time  int NOT NULL COMMENT '重复投票时间',
   is_restrict_ip  bit(1) NOT NULL COMMENT '是否限制IP重复投票',
   is_need_login  bit(1) NOT NULL COMMENT '是否需登录',
   create_time  datetime NOT NULL COMMENT '添加时间',
   start_time  date NOT NULL COMMENT '调查开始时间',
   end_time  date DEFAULT NULL COMMENT '调查结束时间',
   enable  bit(1) NOT NULL COMMENT '是否启用',
  PRIMARY KEY ( naire_id ),
  KEY  fk_questionnaire_to_site  ( site_id ),
  CONSTRAINT  fk_questionnaire_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id )
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table  tq_questionnaire  */

insert  into  tq_questionnaire ( naire_id , site_id , name , description , repeate_time , is_restrict_ip , is_need_login , create_time , start_time , end_time , enable ) values (1,1,'javapms用户满意度调查','JAVAPMS门户管理系统正式版已经发布，您在使用的过程中有什么样的感受呢？为更好的改进系统，提升用户体验，特此举行一次JAVAPMS用户满意度调查，欢迎您给我们提出宝贵使用意见和建议，谢谢!',1,'','\0','2013-08-26 10:48:51','2014-05-25',NULL,'');

/*Table structure for table  tq_resume  */

DROP TABLE IF EXISTS  tq_resume ;

CREATE TABLE  tq_resume  (
   resume_id  int NOT NULL AUTO_INCREMENT,
   personal_id  int NOT NULL COMMENT '个人ID',
   name  varchar(30) NOT NULL COMMENT '简历名称',
   resume_lang  varchar(20) NOT NULL COMMENT '简历语言',
   resume_open  int NOT NULL COMMENT '开启状态',
   create_time  datetime NOT NULL COMMENT '创建时间',
   update_time  datetime DEFAULT NULL COMMENT '更新时间',
   is_apply_def  int NOT NULL DEFAULT '0',
  PRIMARY KEY ( resume_id ),
  KEY  fk_resume_personal  ( personal_id ),
  CONSTRAINT  fk_resume_personal  FOREIGN KEY ( personal_id ) REFERENCES  tq_personal_info  ( personal_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_resume  */

/*Table structure for table  tq_resume_apply  */

DROP TABLE IF EXISTS  tq_resume_apply ;

CREATE TABLE  tq_resume_apply  (
   apply_id  int NOT NULL AUTO_INCREMENT,
   resume_id  int NOT NULL,
   job_id  int NOT NULL,
   create_time  datetime NOT NULL,
   is_read  int NOT NULL,
  PRIMARY KEY ( apply_id ),
  KEY  fk_job_resume_app  ( job_id ),
  KEY  fk_resume_job_app  ( resume_id ),
  CONSTRAINT  fk_job_resume_app  FOREIGN KEY ( job_id ) REFERENCES  tq_job_post  ( job_id ),
  CONSTRAINT  fk_resume_job_app  FOREIGN KEY ( resume_id ) REFERENCES  tq_resume  ( resume_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_resume_apply  */

/*Table structure for table  tq_resume_ext  */

DROP TABLE IF EXISTS  tq_resume_ext ;

CREATE TABLE  tq_resume_ext  (
   resume_id  int NOT NULL,
   evaluate  varchar(2000) DEFAULT NULL,
   skill_special  varchar(2000) DEFAULT NULL,
   work_exp  varchar(2000) DEFAULT NULL,
  PRIMARY KEY ( resume_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_resume_ext  */

/*Table structure for table  tq_role  */

DROP TABLE IF EXISTS  tq_role ;

CREATE TABLE  tq_role  (
   role_id  int NOT NULL AUTO_INCREMENT,
   site_id  int NOT NULL DEFAULT '1' COMMENT '站点ID',
   name  varchar(50) NOT NULL COMMENT '角色名称',
   priority  int NOT NULL COMMENT '排序',
   is_all_perm  bit(1) NOT NULL COMMENT '是否拥有所有权限',
  PRIMARY KEY ( role_id ),
  KEY  fk_role_to_site  ( site_id ),
  CONSTRAINT  fk_role_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id )
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

/*Data for the table  tq_role  */

insert  into  tq_role ( role_id , site_id , name , priority , is_all_perm ) values (1,1,'超级管理员',1,''),(2,1,'信息报送员',2,'\0'),(11,9,'超级管理员',1,''),(20,18,'超级管理员',1,''),(23,21,'超级管理员',1,''),(25,23,'超级管理员',1,'');

/*Table structure for table  tq_role_perm  */

DROP TABLE IF EXISTS  tq_role_perm ;

CREATE TABLE  tq_role_perm  (
   role_id  int NOT NULL COMMENT '角色ID',
   perms  longtext COMMENT '权限集合',
  PRIMARY KEY ( role_id ),
  CONSTRAINT  fk_roleperm_to_role  FOREIGN KEY ( role_id ) REFERENCES  tq_role  ( role_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_role_perm  */

insert  into  tq_role_perm ( role_id , perms ) values (1,''),(2,'admin:workspace:index,admin:workspace:right,admin:workspace:mgr,admin:workspace:infoopt,admin:workspace:info,admin:workspace:infoupdate,admin:workspace:passopt,admin:workspace:passedit,admin:workspace:passupdate,admin:doccenter,admin:doc:mgr,admin:doc:addopt,admin:doc:add,admin:doc:save,admin:doc:editopt,admin:doc:edit,admin:doc:update,admin:doc:list,admin:doc:delete,admin:doc:cycle,admin:doc:reduct,'),(11,''),(20,''),(23,''),(25,'');

/*Table structure for table  tq_sensitivity  */

DROP TABLE IF EXISTS  tq_sensitivity ;

CREATE TABLE  tq_sensitivity  (
   sensitivity_id  int NOT NULL AUTO_INCREMENT,
   search  varchar(255) NOT NULL COMMENT '敏感词',
   replacement  varchar(255) NOT NULL COMMENT '替换词',
  PRIMARY KEY ( sensitivity_id )
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table  tq_sensitivity  */

insert  into  tq_sensitivity ( sensitivity_id , search , replacement ) values (2,'法轮功','***');

/*Table structure for table  tq_site  */

DROP TABLE IF EXISTS  tq_site ;

CREATE TABLE  tq_site  (
   site_id  int NOT NULL AUTO_INCREMENT,
   domain  varchar(50) NOT NULL COMMENT '域名',
   site_path  varchar(20) NOT NULL COMMENT '路径',
   site_name  varchar(100) NOT NULL COMMENT '网站名称',
   short_name  varchar(100) NOT NULL COMMENT '简短名称',
   context_path  varchar(20) DEFAULT NULL COMMENT '上下文',
   port  int DEFAULT NULL COMMENT '端口',
   tpl_style  varchar(50) NOT NULL DEFAULT 'default' COMMENT '网站风格',
   title  varchar(80) DEFAULT NULL COMMENT 'title',
   keywords  varchar(100) DEFAULT NULL COMMENT '关键字',
   description  varchar(255) DEFAULT NULL COMMENT '描述',
   is_recover  bit(1) NOT NULL DEFAULT b'1' COMMENT '开启回收站',
   is_static_channel  int NOT NULL DEFAULT '0' COMMENT '生成栏目静态页策略',
   is_static_doc  int NOT NULL DEFAULT '0' COMMENT '生成文档静态页策略',
   is_static_suffix  bit(1) NOT NULL DEFAULT b'0' COMMENT '是否带有后缀',
   tpl_index  varchar(50) DEFAULT NULL COMMENT '首页模板',
   update_time  datetime DEFAULT NULL COMMENT '最后更新时间',
   is_terminus  bit(1) NOT NULL DEFAULT b'1' COMMENT '是否为总站',
  PRIMARY KEY ( site_id )
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

/*Data for the table  tq_site  */

insert  into  tq_site ( site_id , domain , site_path , site_name , short_name , context_path , port , tpl_style , title , keywords , description , is_recover , is_static_channel , is_static_doc , is_static_suffix , tpl_index , update_time , is_terminus ) values (1,'localhost','pms','人民银行赤峰市中心支行国库科','人民银行赤峰市中心支行国库科',NULL,8080,'default','','','','',1,1,'','/doc/index/index.html','2015-09-09 16:02:26',''),(9,'localhost','test','子站','','',8080,'default','','','','',1,1,'',NULL,NULL,'\0'),(18,'localhost','fdgfdg','fdgfdg','','',8080,'default','','','','',1,1,'',NULL,NULL,'\0'),(21,'localhost','sdfsd','dsfsd','','',8080,'default','','','','',1,1,'',NULL,'2013-12-03 12:31:10','\0'),(23,'localhost','zizhan1','子站1','','',8080,'default','子站1','子站1','子站1','',1,1,'',NULL,'2013-12-07 14:20:24','\0');

/*Table structure for table  tq_site_config  */

DROP TABLE IF EXISTS  tq_site_config ;

CREATE TABLE  tq_site_config  (
   config_id  int NOT NULL,
   comment_check  bit(1) NOT NULL COMMENT '评论是否需审核',
   comment_login  bit(1) NOT NULL COMMENT '评论是否需登录',
   message_check  bit(1) NOT NULL COMMENT '留言是否需审核',
   message_login  bit(1) NOT NULL COMMENT '留言是否需登录',
   message_name  bit(1) NOT NULL COMMENT '留言联系人是否显示',
   message_mobile  bit(1) NOT NULL COMMENT '留言联系电话是否显示',
   message_email  bit(1) NOT NULL COMMENT '留言email是否显示',
   message_address  bit(1) NOT NULL COMMENT '留言地址是否显示',
   message_zipcode  bit(1) NOT NULL COMMENT '留言邮编是否显示',
   reg_open  bit(1) NOT NULL COMMENT '注册是否开启',
   reg_min  int DEFAULT NULL COMMENT '会员名最小长度',
   reg_max  int DEFAULT NULL COMMENT '会员名最大长度',
   reg_check  bit(1) NOT NULL COMMENT '注册会员是否需审核',
   login_count  int DEFAULT NULL COMMENT '每天登录失败次数限制',
  PRIMARY KEY ( config_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_site_config  */

insert  into  tq_site_config ( config_id , comment_check , comment_login , message_check , message_login , message_name , message_mobile , message_email , message_address , message_zipcode , reg_open , reg_min , reg_max , reg_check , login_count ) values (1,'\0','\0','\0','\0','\0','\0','\0','\0','\0','',NULL,NULL,'\0',3);

/*Table structure for table  tq_site_message  */

DROP TABLE IF EXISTS  tq_site_message ;

CREATE TABLE  tq_site_message  (
   message_id  int NOT NULL AUTO_INCREMENT,
   send_id  int NOT NULL COMMENT '发送人ID',
   title  varchar(50) DEFAULT NULL COMMENT '标题',
   content  varchar(1000) NOT NULL COMMENT '信件内容',
   is_group  bit(1) DEFAULT NULL COMMENT '是否为群发短信',
   status  int NOT NULL COMMENT '发送方状态',
   create_time  datetime NOT NULL COMMENT '发送时间',
  PRIMARY KEY ( message_id ),
  KEY  fk_site_message_to_user  ( send_id ),
  CONSTRAINT  fk_site_message_to_user  FOREIGN KEY ( send_id ) REFERENCES  tq_user  ( user_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_site_message  */

/*Table structure for table  tq_site_message_status  */

DROP TABLE IF EXISTS  tq_site_message_status ;

CREATE TABLE  tq_site_message_status  (
   status_id  int NOT NULL AUTO_INCREMENT,
   message_id  int NOT NULL COMMENT '信件ID',
   receive_id  int NOT NULL COMMENT '接收人ID',
   status  int NOT NULL COMMENT '接收方状态:-1,垃圾箱,0,未读,1，已读',
  PRIMARY KEY ( status_id ),
  KEY  fk_message_status_to_message  ( message_id ),
  KEY  fk_message_status_to_user  ( receive_id ),
  CONSTRAINT  fk_message_status_to_message  FOREIGN KEY ( message_id ) REFERENCES  tq_site_message  ( message_id ),
  CONSTRAINT  fk_message_status_to_user  FOREIGN KEY ( receive_id ) REFERENCES  tq_user  ( user_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_site_message_status  */

/*Table structure for table  tq_specialty  */

DROP TABLE IF EXISTS  tq_specialty ;

CREATE TABLE  tq_specialty  (
   special_id  int NOT NULL AUTO_INCREMENT,
   parent_id  int DEFAULT NULL,
   special_code  varchar(10) NOT NULL COMMENT '编码',
   special_name  varchar(50) NOT NULL COMMENT '名称',
  PRIMARY KEY ( special_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_specialty  */

/*Table structure for table  tq_survey_detail  */

DROP TABLE IF EXISTS  tq_survey_detail ;

CREATE TABLE  tq_survey_detail  (
   detail_id  int NOT NULL AUTO_INCREMENT,
   user_id  int DEFAULT NULL COMMENT '反馈会员ID',
   survey_id  int NOT NULL COMMENT '调查项ID',
   content  varchar(1000) DEFAULT NULL COMMENT '反馈内容',
   create_time  datetime NOT NULL COMMENT '反馈时间',
  PRIMARY KEY ( detail_id ),
  KEY  fk_survey_detail_to_survey  ( survey_id ),
  CONSTRAINT  fk_survey_detail_to_survey  FOREIGN KEY ( survey_id ) REFERENCES  tq_survey_theme  ( theme_id )
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table  tq_survey_detail  */

insert  into  tq_survey_detail ( detail_id , user_id , survey_id , content , create_time ) values (1,1,6,'继续加油','2013-10-31 15:12:16'),(2,1,5,'很好','2013-10-31 15:12:16');

/*Table structure for table  tq_survey_item  */

DROP TABLE IF EXISTS  tq_survey_item ;

CREATE TABLE  tq_survey_item  (
   theme_id  int NOT NULL COMMENT '主题ID',
   name  varchar(50) NOT NULL COMMENT '投票项名称',
   votes  int NOT NULL COMMENT '票数',
   priority  int NOT NULL COMMENT '排序',
  KEY  fk_survey_item_to_theme  ( theme_id ),
  CONSTRAINT  fk_survey_item_to_theme  FOREIGN KEY ( theme_id ) REFERENCES  tq_survey_theme  ( theme_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_survey_item  */

insert  into  tq_survey_item ( theme_id , name , votes , priority ) values (1,'男',0,1),(1,'女',1,2),(3,'搜索引擎',0,1),(3,'朋友介绍',1,2),(3,'通知公告',0,3),(3,'QQ群或论坛',0,4),(4,'十分满意',0,1),(4,'基本满意',0,2),(4,'不满意',1,3);

/*Table structure for table  tq_survey_theme  */

DROP TABLE IF EXISTS  tq_survey_theme ;

CREATE TABLE  tq_survey_theme  (
   theme_id  int NOT NULL AUTO_INCREMENT,
   naire_id  int NOT NULL COMMENT '问卷调查ID',
   title  varchar(50) NOT NULL COMMENT '标题',
   survey_type  int NOT NULL COMMENT '问卷类型',
   total_count  int DEFAULT NULL COMMENT '最大选项个数',
   maxlength  int DEFAULT NULL COMMENT '最大长度',
   show_type  int DEFAULT NULL COMMENT '显示形式',
   priority  int NOT NULL COMMENT '排序',
  PRIMARY KEY ( theme_id ),
  KEY  fk_survey_theme_to_naire  ( naire_id ),
  CONSTRAINT  fk_survey_theme_to_naire  FOREIGN KEY ( naire_id ) REFERENCES  tq_questionnaire  ( naire_id )
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

/*Data for the table  tq_survey_theme  */

insert  into  tq_survey_theme ( theme_id , naire_id , title , survey_type , total_count , maxlength , show_type , priority ) values (1,1,'您的性别？',1,1,NULL,1,1),(2,1,'您的工作职位？',2,NULL,50,4,2),(3,1,'您最初是从哪里得知道新版系统的？',1,1,NULL,1,10),(4,1,'您目前对新版网站满意吗？',1,3,NULL,1,10),(5,1,'请您给新版系统提出宝贵的意见或建议',2,NULL,500,5,10),(6,1,'您有什么好的想法分享下',2,NULL,200,5,10);

/*Table structure for table  tq_theme  */

DROP TABLE IF EXISTS  tq_theme ;

CREATE TABLE  tq_theme  (
   theme_id  int NOT NULL AUTO_INCREMENT,
   site_id  int NOT NULL COMMENT '站点ID',
   forum_id  int NOT NULL COMMENT '版块ID',
   creater_id  int DEFAULT NULL COMMENT '发帖会员',
   last_replyer_id  int DEFAULT NULL COMMENT '最后回复会员',
   title  varchar(150) NOT NULL COMMENT '主题名称',
   views_count  int NOT NULL COMMENT '访问次数',
   reply_count  int NOT NULL COMMENT '回复次数',
   is_lock  bit(1) NOT NULL COMMENT '是否锁定',
   is_essena  bit(1) DEFAULT NULL COMMENT '是否为精华帖',
   is_bold  bit(1) DEFAULT NULL COMMENT '是否加粗',
   is_italic  bit(1) DEFAULT NULL COMMENT '是否斜体',
   color  varchar(50) DEFAULT NULL COMMENT '标题颜色',
   top_time  date DEFAULT NULL COMMENT '置顶截止日期',
   essena_time  date DEFAULT NULL COMMENT '精华截止日期',
   lock_time  date DEFAULT NULL COMMENT '锁定截止日期',
   status  int NOT NULL COMMENT '主题状态',
   is_affix  bit(1) NOT NULL COMMENT '是否有附件',
   is_img  bit(1) NOT NULL COMMENT '是否有图片',
   is_moder_reply  bit(1) NOT NULL COMMENT '版主是否已回复',
   last_reply_time  datetime DEFAULT NULL COMMENT '最后回复时间',
   create_time  datetime NOT NULL COMMENT '发布时间',
   light_time  date DEFAULT NULL COMMENT '高亮截止时间',
  PRIMARY KEY ( theme_id ),
  KEY  fk_theme_to_replyuser  ( last_replyer_id ),
  KEY  fk_theme_to_createuser  ( creater_id ),
  KEY  fk_theme_to_forum  ( forum_id ),
  KEY  fk_theme_to_site  ( site_id ),
  CONSTRAINT  fk_theme_to_createuser  FOREIGN KEY ( creater_id ) REFERENCES  tq_user  ( user_id ),
  CONSTRAINT  fk_theme_to_forum  FOREIGN KEY ( forum_id ) REFERENCES  tq_forum  ( forum_id ),
  CONSTRAINT  fk_theme_to_replyuser  FOREIGN KEY ( last_replyer_id ) REFERENCES  tq_user  ( user_id ),
  CONSTRAINT  fk_theme_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id )
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table  tq_theme  */

insert  into  tq_theme ( theme_id , site_id , forum_id , creater_id , last_replyer_id , title , views_count , reply_count , is_lock , is_essena , is_bold , is_italic , color , top_time , essena_time , lock_time , status , is_affix , is_img , is_moder_reply , last_reply_time , create_time , light_time ) values (1,1,1,1,1,'测试发布帖子',0,0,'\0','\0','\0','\0',NULL,NULL,NULL,NULL,0,'\0','\0','\0','2013-05-26 16:20:37','2013-05-26 16:20:37',NULL),(2,1,1,1,1,'法的',0,0,'\0','\0','\0','\0',NULL,NULL,NULL,NULL,0,'\0','\0','','2013-05-26 23:19:35','2013-05-26 21:25:53',NULL),(3,1,1,1,1,'asdsad',0,0,'\0','\0','\0','\0',NULL,NULL,NULL,NULL,0,'','\0','\0','2013-05-27 15:11:33','2013-05-27 15:11:33',NULL),(4,1,1,1,1,'dsgfdgdf',0,0,'\0','\0','\0','\0',NULL,NULL,NULL,NULL,0,'','\0','\0','2013-05-27 15:29:40','2013-05-27 15:29:40',NULL),(5,1,1,1,3,'sfdsfdsfsd',0,0,'\0','\0','\0','\0',NULL,NULL,NULL,NULL,0,'\0','\0','','2013-05-28 17:15:42','2013-05-27 15:32:58',NULL),(6,1,1,3,3,'美女图',0,0,'\0','\0','\0','\0',NULL,NULL,NULL,NULL,0,'','\0','\0','2013-05-28 17:52:00','2013-05-28 17:52:00',NULL),(7,1,1,3,3,'测试隐藏贴',0,4,'\0','\0','\0','\0',NULL,NULL,NULL,NULL,0,'\0','\0','\0','2013-05-30 10:44:55','2013-05-28 17:54:55',NULL),(8,1,1,3,3,'测试隐藏贴',6,0,'\0','\0','',NULL,'FF0000',NULL,NULL,NULL,2,'\0','\0','\0','2013-05-30 10:58:16','2013-05-30 10:58:16',NULL),(9,1,1,3,3,'fddfgdfg',0,0,'\0','\0','\0','\0',NULL,NULL,NULL,NULL,0,'\0','\0','\0','2013-05-30 15:23:42','2013-05-30 15:23:42',NULL),(10,1,1,3,3,'fhfgh',0,0,'\0','\0','\0','\0',NULL,NULL,NULL,NULL,0,'\0','\0','\0','2013-05-30 15:25:13','2013-05-30 15:25:13',NULL),(11,1,1,3,3,'ghfhf',6,0,'\0','\0','',NULL,'FFFF00',NULL,NULL,NULL,0,'\0','\0','\0','2013-05-30 15:37:19','2013-05-30 15:37:19','2013-06-06'),(12,1,1,3,3,'fdgfdgd',5,0,'\0','\0','\0','\0',NULL,NULL,NULL,NULL,0,'\0','\0','\0','2013-05-30 15:39:20','2013-05-30 15:39:20',NULL),(13,1,1,3,3,'dsfdsfs',2,0,'\0','\0','\0','\0',NULL,NULL,NULL,NULL,-1,'\0','\0','\0','2013-05-30 15:40:19','2013-05-30 15:40:19',NULL),(14,1,1,3,3,'dgfdgfdg',6,0,'\0','\0','\0','\0',NULL,NULL,NULL,NULL,-1,'\0','\0','\0','2013-05-30 15:41:18','2013-05-30 15:41:18',NULL),(15,1,1,1,1,'dgdfgdfgdfg',6,0,'\0','\0','\0','\0',NULL,NULL,NULL,NULL,0,'','','\0','2013-05-31 16:59:15','2013-05-31 16:59:15',NULL),(16,1,1,1,1,'fdgdfg',7,0,'\0','\0','\0','\0',NULL,NULL,NULL,NULL,0,'\0','\0','\0','2013-05-31 18:14:17','2013-05-31 18:14:17',NULL),(17,1,1,1,1,'xzcxzc',2,0,'\0','\0','',NULL,'FF0000',NULL,NULL,NULL,1,'\0','\0','\0','2013-05-31 21:35:07','2013-05-31 21:35:07',NULL),(18,1,1,1,1,'dfgfd',9,0,'\0','\0','\0','\0',NULL,NULL,NULL,NULL,2,'\0','\0','\0','2013-06-03 09:30:35','2013-06-03 09:30:35',NULL),(19,1,1,1,1,'dfg',3,0,'\0','\0','\0','\0',NULL,NULL,NULL,NULL,0,'\0','\0','\0','2013-06-05 08:59:33','2013-06-05 08:59:33',NULL),(20,1,1,1,1,'测试编辑帖子',0,0,'\0','\0','\0','\0',NULL,NULL,NULL,NULL,0,'\0','\0','\0','2013-11-04 10:52:37','2013-11-04 10:52:37',NULL);

/*Table structure for table  tq_theme_txt  */

DROP TABLE IF EXISTS  tq_theme_txt ;

CREATE TABLE  tq_theme_txt  (
   theme_id  int NOT NULL,
   content  longtext COMMENT '回复内容记录',
  PRIMARY KEY ( theme_id ),
  CONSTRAINT  fk_themetxt_to_theme  FOREIGN KEY ( theme_id ) REFERENCES  tq_theme  ( theme_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_theme_txt  */

insert  into  tq_theme_txt ( theme_id , content ) values (1,','),(2,','),(3,','),(4,','),(5,',3,'),(6,','),(7,',3,'),(8,','),(9,','),(10,','),(11,','),(12,','),(13,','),(14,','),(15,','),(16,','),(17,','),(18,','),(19,','),(20,',');

/*Table structure for table  tq_thirdparty_bind  */

DROP TABLE IF EXISTS  tq_thirdparty_bind ;

CREATE TABLE  tq_thirdparty_bind  (
   bind_id  int NOT NULL,
   username  varchar(100) NOT NULL COMMENT '绑定的用户名',
   openid  varchar(100) NOT NULL COMMENT 'id',
   openkey  varchar(100) NOT NULL COMMENT 'key',
   bind_time  datetime NOT NULL COMMENT '绑定时间',
   bind_type  varchar(50) NOT NULL COMMENT '绑定类型,如QQ，新浪',
  PRIMARY KEY ( bind_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_thirdparty_bind  */

/*Table structure for table  tq_thirdparty_config  */

DROP TABLE IF EXISTS  tq_thirdparty_config ;

CREATE TABLE  tq_thirdparty_config  (
   config_id  int NOT NULL,
   qq_key  varchar(100) DEFAULT NULL COMMENT '申请到的QQkey',
   sina_key  varchar(100) DEFAULT NULL COMMENT '申请到的新浪key',
  PRIMARY KEY ( config_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_thirdparty_config  */

/*Table structure for table  tq_train  */

DROP TABLE IF EXISTS  tq_train ;

CREATE TABLE  tq_train  (
   train_id  int NOT NULL,
   resume_id  int NOT NULL,
   start_time  varchar(30) NOT NULL,
   end_time  varchar(30) NOT NULL,
   training  varchar(50) NOT NULL,
   train_course  varchar(50) NOT NULL,
   cert_name  varchar(20) DEFAULT NULL,
   description  varchar(2000) DEFAULT NULL,
   create_time  datetime NOT NULL,
  PRIMARY KEY ( train_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_train  */

/*Table structure for table  tq_unit_type  */

DROP TABLE IF EXISTS  tq_unit_type ;

CREATE TABLE  tq_unit_type  (
   type_id  int NOT NULL AUTO_INCREMENT,
   type_code  varchar(10) NOT NULL COMMENT '编码',
   type_name  varchar(50) NOT NULL COMMENT '名称',
  PRIMARY KEY ( type_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_unit_type  */

/*Table structure for table  tq_user  */

DROP TABLE IF EXISTS  tq_user ;

CREATE TABLE  tq_user  (
   user_id  int NOT NULL AUTO_INCREMENT,
   username  varchar(50) NOT NULL COMMENT '用户名',
   t_password  varchar(50) NOT NULL COMMENT '密码',
   email  varchar(50) NOT NULL COMMENT '邮箱',
   real_name  varchar(50) DEFAULT NULL COMMENT '真实姓名',
   phone  varchar(20) DEFAULT NULL COMMENT '电话',
   mobile  varchar(20) DEFAULT NULL COMMENT '手机',
   fail_count  int NOT NULL DEFAULT '0' COMMENT '登录失败次数',
   t_status  int NOT NULL COMMENT '状态',
   last_fail_time  datetime DEFAULT NULL COMMENT '最后登录失败时间',
  PRIMARY KEY ( user_id )
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table  tq_user  */

insert  into  tq_user ( user_id , username , t_password , email , real_name , phone , mobile , fail_count , t_status , last_fail_time ) values (1,'admin','21232f297a57a5a743894a0e4a801fc3','623406671@qq.com','pms管理员','','15332182270',2,0,'2015-09-09 15:08:52'),(3,'test','4297f44b13955235245b2497399d7a93','ewr@163.com','张珊','36456546','456546546',0,0,NULL),(5,'demo','fe01ce2a7fbac8fafaed7c982a04e229','dsfdsf@163.com','李思','','',0,0,NULL);

/*Table structure for table  tq_user_bind  */

DROP TABLE IF EXISTS  tq_user_bind ;

CREATE TABLE  tq_user_bind  (
   bind_id  int NOT NULL AUTO_INCREMENT,
   user_id  int NOT NULL,
   username  varchar(100) NOT NULL COMMENT '用户名',
   pass  varchar(100) NOT NULL COMMENT '密码',
   status  int NOT NULL COMMENT '系统类型',
  PRIMARY KEY ( bind_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_user_bind  */

/*Table structure for table  tq_user_forum  */

DROP TABLE IF EXISTS  tq_user_forum ;

CREATE TABLE  tq_user_forum  (
   user_id  int NOT NULL,
   avatar  varchar(50) DEFAULT NULL COMMENT '头像',
   essena_count  int NOT NULL COMMENT '精华帖数量',
   theme_count  int NOT NULL COMMENT '主题数量',
   reply_count  int NOT NULL COMMENT '回复数量',
   point  int NOT NULL COMMENT '积分',
   status  int NOT NULL COMMENT '状态',
   status_time  date DEFAULT NULL COMMENT '截止时间',
   signature  varchar(255) DEFAULT NULL COMMENT '个性签名',
  PRIMARY KEY ( user_id ),
  CONSTRAINT  fk_userforum_to_user  FOREIGN KEY ( user_id ) REFERENCES  tq_user  ( user_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_user_forum  */

insert  into  tq_user_forum ( user_id , avatar , essena_count , theme_count , reply_count , point , status , status_time , signature ) values (1,NULL,0,6,0,18,0,NULL,NULL),(3,NULL,0,7,1,22,0,NULL,NULL);

/*Table structure for table  tq_vip_type  */

DROP TABLE IF EXISTS  tq_vip_type ;

CREATE TABLE  tq_vip_type  (
   type_id  int NOT NULL AUTO_INCREMENT,
   type_name  varchar(50) NOT NULL,
   day_count  int NOT NULL,
   post_count  int NOT NULL,
   favorite_count  int NOT NULL,
   audition_count  int NOT NULL,
   receipt_count  int NOT NULL,
   send_count  int NOT NULL,
   setup_login  int DEFAULT '0',
  PRIMARY KEY ( type_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_vip_type  */

/*Table structure for table  tq_visit_statistics  */

DROP TABLE IF EXISTS  tq_visit_statistics ;

CREATE TABLE  tq_visit_statistics  (
   visit_id  int NOT NULL,
   site_id  int NOT NULL COMMENT '站点ID',
   url  varchar(150) NOT NULL COMMENT '访问地址',
   ip  varchar(30) DEFAULT NULL COMMENT '访问IP',
   cookie  varchar(100) DEFAULT NULL COMMENT '访问COOKIE',
   visit_time  datetime NOT NULL COMMENT '访问时间',
   visit_hour  int NOT NULL COMMENT '访问小时',
   visit_min  int NOT NULL COMMENT '访问分钟',
  PRIMARY KEY ( visit_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_visit_statistics  */

insert  into  tq_visit_statistics ( visit_id , site_id , url , ip , cookie , visit_time , visit_hour , visit_min ) values (24,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 15:48:26',15,48),(25,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 15:49:53',15,49),(26,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 15:50:13',15,50),(27,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 17:01:24',17,1),(28,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 20:32:41',20,32),(29,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 21:35:48',21,35),(30,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','4e6d7a0c60664ed68ca8c1bcf901c306','2014-05-25 21:56:56',21,56),(31,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','4e6d7a0c60664ed68ca8c1bcf901c306','2014-05-25 21:58:18',21,58),(32,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:19:12',22,19),(33,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:19:29',22,19),(34,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:22:17',22,22),(35,1,'http://localhost:8080/javapms/china/212.html','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:22:29',22,22),(36,1,'http://localhost:8080/javapms/china/212_2.html','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:22:33',22,22),(37,1,'http://localhost:8080/javapms/comment-212.jsp','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:22:44',22,22),(38,1,'http://localhost:8080/javapms/china/212.html','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:34:17',22,34),(39,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:34:42',22,34),(40,1,'http://localhost:8080/javapms/china/212.html','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:34:44',22,34),(41,1,'http://localhost:8080/javapms/china/212.html','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:35:11',22,35),(42,1,'http://localhost:8080/javapms/comment-212.jsp','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:35:17',22,35),(43,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:35:41',22,35),(44,1,'http://localhost:8080/javapms/china/212.html','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:38:58',22,38),(45,1,'http://localhost:8080/javapms/comment-212.jsp','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:41:55',22,41),(46,1,'http://localhost:8080/javapms/comment-212.jsp','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-25 22:47:52',22,47),(47,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-26 00:41:40',0,41),(48,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-26 00:41:59',0,41),(49,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-26 00:43:29',0,43),(50,1,'http://localhost:8080/javapms/forum.jsp','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-26 00:43:38',0,43),(51,1,'http://localhost:8080/javapms/themeList-1.jsp','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-26 00:43:41',0,43),(52,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-26 00:56:29',0,56),(53,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-26 00:56:37',0,56),(54,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','2353143123f145eb8efa7f1a6ba005ed','2014-05-26 00:57:01',0,57),(55,1,'http://localhost:8080/comment-212.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:06:45',9,6),(56,1,'http://localhost:8080/forum.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:06:50',9,6),(57,1,'http://localhost:8080/themeList-1.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:06:52',9,6),(58,1,'http://localhost:8080/messageboard.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:06:56',9,6),(59,1,'http://localhost:8080/questionList.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:07:04',9,7),(60,1,'http://localhost:8080/question-1.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:07:09',9,7),(61,1,'http://localhost:8080/search-mId-3-q-1.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:07:21',9,7),(62,1,'http://localhost:8080/search-mId-3-q-%E8%BD%A6.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:07:29',9,7),(63,1,'http://localhost:8080/search-mId-3-q-%E8%BD%A6.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:07:38',9,7),(64,1,'http://localhost:8080/search-mId-3-q-%E8%BD%A6.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:07:54',9,7),(65,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:07:57',9,7),(66,1,'http://localhost:8080/forum.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:08:08',9,8),(67,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:10:47',9,10),(68,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:25:10',9,25),(69,1,'http://localhost:8080/china/214.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:25:14',9,25),(70,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:42:10',9,42),(71,1,'http://localhost:8080/photo/211.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:42:26',9,42),(72,1,'http://localhost:8080/search-mId-3-q-%E5%A5%B3.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:42:59',9,42),(73,1,'http://localhost:8080/guide/index.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:43:02',9,43),(74,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:43:08',9,43),(75,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:45:24',9,45),(76,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 09:51:08',9,51),(77,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','66eea6dcbec04e6ba5d1e6f26774429d','2014-05-26 09:51:14',9,51),(78,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','85c6a8a6ae1f494e9cd0964b720f28d1','2014-05-26 09:53:36',9,53),(79,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:04:38',10,4),(80,1,'http://localhost:8080/fcnews/index.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:22:21',10,22),(81,1,'http://localhost:8080/videos/82.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:22:43',10,22),(82,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:26:46',10,26),(83,1,'http://localhost:8080/china/53.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:26:48',10,26),(84,1,'http://localhost:8080/china/53_2.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:26:51',10,26),(85,1,'http://localhost:8080/china/53_3.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:26:53',10,26),(86,1,'http://localhost:8080/china/53_4.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:26:55',10,26),(87,1,'http://localhost:8080/china/53_5.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:26:59',10,26),(88,1,'http://localhost:8080/china/53_6.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:27:02',10,27),(89,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:35:30',10,35),(90,1,'http://localhost:8080/china/53.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:35:37',10,35),(91,1,'http://localhost:8080/comment-53.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:37:41',10,37),(92,1,'http://localhost:8080/comment-53.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:38:06',10,38),(93,1,'http://localhost:8080/china/53.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:38:44',10,38),(94,1,'http://localhost:8080/china/212.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:38:50',10,38),(95,1,'http://localhost:8080/comment-212.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:38:52',10,38),(96,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:40:27',10,40),(97,1,'http://localhost:8080/photo/211.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:40:43',10,40),(98,1,'http://localhost:8080/photo/211.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:41:57',10,41),(99,1,'http://localhost:8080/comment-211.jsp','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:42:00',10,42),(100,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:43:15',10,43),(101,1,'http://localhost:8080/china/53.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:43:17',10,43),(102,1,'http://localhost:8080/jingdian/197.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:43:57',10,43),(103,1,'http://localhost:8080/china/53.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:44:09',10,44),(104,1,'http://localhost:8080/it/194.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:44:16',10,44),(105,1,'http://localhost:8080/clothes/196.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 10:44:56',10,44),(106,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 11:06:34',11,6),(107,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 11:07:52',11,7),(108,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 11:09:27',11,9),(109,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 11:25:02',11,25),(110,1,'http://localhost:8080/china/53.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 11:25:06',11,25),(111,1,'http://localhost:8080/china/53_2.html','0:0:0:0:0:0:0:1','86686186bd2a4b7991e160236c91e9a7','2014-05-26 11:25:09',11,25),(112,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:37:30',17,37),(113,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:37:59',17,37),(114,1,'http://localhost:8080/internet/193.html','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:38:05',17,38),(115,1,'http://localhost:8080/comment-193.jsp','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:38:07',17,38),(116,1,'http://localhost:8080/comment-193.jsp','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:38:25',17,38),(117,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:38:53',17,38),(118,1,'http://localhost:8080/china/53.html','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:38:54',17,38),(119,1,'http://localhost:8080/internet/193.html','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:40:57',17,40),(120,1,'http://localhost:8080/comment-193.jsp','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:41:04',17,41),(121,1,'http://localhost:8080/comment-193.jsp','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:44:35',17,44),(122,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:46:54',17,46),(123,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:46:55',17,46),(124,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:48:23',17,48),(125,1,'http://localhost:8080/china/index.html','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:48:36',17,48),(126,1,'http://localhost:8080/china/212.html','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:48:42',17,48),(127,1,'http://localhost:8080/china/212.html','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:48:45',17,48),(128,1,'http://localhost:8080/china/212.html','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:48:48',17,48),(129,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:49:19',17,49),(130,1,'http://localhost:8080/china/215.html','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:49:24',17,49),(131,1,'http://localhost:8080/china/index.html','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:49:27',17,49),(132,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','85c6a8a6ae1f494e9cd0964b720f28d1','2014-05-26 17:53:06',17,53),(133,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','85c6a8a6ae1f494e9cd0964b720f28d1','2014-05-26 17:53:14',17,53),(134,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','85c6a8a6ae1f494e9cd0964b720f28d1','2014-05-26 17:53:20',17,53),(135,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','85c6a8a6ae1f494e9cd0964b720f28d1','2014-05-26 17:53:22',17,53),(136,1,'http://localhost:8080/news/index.html','0:0:0:0:0:0:0:1','85c6a8a6ae1f494e9cd0964b720f28d1','2014-05-26 17:53:27',17,53),(137,1,'http://localhost:8080/news/index.html','0:0:0:0:0:0:0:1','85c6a8a6ae1f494e9cd0964b720f28d1','2014-05-26 17:53:30',17,53),(138,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:53:35',17,53),(139,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 17:54:40',17,54),(140,1,'http://localhost:8080/news/index.html','0:0:0:0:0:0:0:1','85c6a8a6ae1f494e9cd0964b720f28d1','2014-05-26 17:54:44',17,54),(141,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 18:00:50',18,0),(142,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 18:00:57',18,0),(143,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 18:01:36',18,1),(144,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 18:46:35',18,46),(145,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 18:47:39',18,47),(146,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 18:47:47',18,47),(147,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 18:47:54',18,47),(148,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 18:48:26',18,48),(149,1,'http://localhost:8080/javapms/','0:0:0:0:0:0:0:1','8b237315d2da442f9cf2ff0a5f9b9907','2014-05-26 18:50:10',18,50),(150,1,'http://localhost:8080/index.jsp','127.0.0.1','5b5997a10dfc42f6ae904c4dc217240e','2015-08-23 10:51:56',10,51),(151,1,'http://localhost:8080/guide/index.jsp','127.0.0.1','5b5997a10dfc42f6ae904c4dc217240e','2015-08-23 10:52:17',10,52),(152,1,'http://localhost:8080/pms/index.jsp','127.0.0.1','5b5997a10dfc42f6ae904c4dc217240e','2015-08-24 11:44:36',11,44),(153,1,'http://localhost:8080/pms/index.jsp','127.0.0.1','5b5997a10dfc42f6ae904c4dc217240e','2015-08-24 12:53:18',12,53),(154,1,'http://localhost:8080/pms/index.jsp','127.0.0.1','5b5997a10dfc42f6ae904c4dc217240e','2015-08-24 12:53:59',12,53),(155,1,'http://localhost:8080/pms/index.jsp','127.0.0.1','5b5997a10dfc42f6ae904c4dc217240e','2015-08-24 16:53:55',16,53),(156,1,'http://localhost:8080/index.jsp','0:0:0:0:0:0:0:1','5b5997a10dfc42f6ae904c4dc217240e','2015-09-09 16:14:43',16,14),(157,1,'http://localhost:8080/index.jsp','0:0:0:0:0:0:0:1','5b5997a10dfc42f6ae904c4dc217240e','2015-09-09 16:16:14',16,16),(158,1,'http://localhost:8080/health/index.html','0:0:0:0:0:0:0:1','5b5997a10dfc42f6ae904c4dc217240e','2015-09-09 16:16:35',16,16),(159,1,'http://localhost:8080/honor/index.html','0:0:0:0:0:0:0:1','5b5997a10dfc42f6ae904c4dc217240e','2015-09-09 16:16:38',16,16),(160,1,'http://localhost:8080/style/index.html','0:0:0:0:0:0:0:1','5b5997a10dfc42f6ae904c4dc217240e','2015-09-09 16:16:40',16,16),(161,1,'http://localhost:8080/index.jsp','0:0:0:0:0:0:0:1','5b5997a10dfc42f6ae904c4dc217240e','2015-09-09 16:17:30',16,17),(162,1,'http://localhost:8080/index.jsp','0:0:0:0:0:0:0:1','5b5997a10dfc42f6ae904c4dc217240e','2015-09-09 16:18:00',16,18),(163,1,'http://localhost:8080/index.jsp','0:0:0:0:0:0:0:1','5b5997a10dfc42f6ae904c4dc217240e','2015-09-09 16:19:23',16,19),(164,1,'http://localhost:8080/index.jsp','0:0:0:0:0:0:0:1','5b5997a10dfc42f6ae904c4dc217240e','2015-09-09 16:20:22',16,20),(165,1,'http://localhost:8080/index.jsp','0:0:0:0:0:0:0:1','5b5997a10dfc42f6ae904c4dc217240e','2015-09-09 16:20:43',16,20),(166,1,'http://localhost:8080/style/index.html','0:0:0:0:0:0:0:1','5b5997a10dfc42f6ae904c4dc217240e','2015-09-09 16:21:27',16,21),(167,1,'http://localhost:8080/','0:0:0:0:0:0:0:1','5b5997a10dfc42f6ae904c4dc217240e','2015-09-09 16:21:30',16,21),(168,1,'http://localhost:8080/finance/index.html','0:0:0:0:0:0:0:1','5b5997a10dfc42f6ae904c4dc217240e','2015-09-09 16:21:32',16,21),(169,1,'http://localhost:8080/sports/index.html','0:0:0:0:0:0:0:1','5b5997a10dfc42f6ae904c4dc217240e','2015-09-09 16:21:48',16,21);

/*Table structure for table  tq_work_exp  */

DROP TABLE IF EXISTS  tq_work_exp ;

CREATE TABLE  tq_work_exp  (
   exp_id  int NOT NULL AUTO_INCREMENT,
   resume_id  int NOT NULL COMMENT '简历ID',
   metier_id  int NOT NULL COMMENT '职业ID',
   start_time  varchar(30) NOT NULL COMMENT '开始时间',
   end_time  varchar(30) NOT NULL COMMENT '截止时间',
   com_name  varchar(50) NOT NULL COMMENT '公司名称',
   com_scale  int NOT NULL COMMENT '公司规模',
   com_nature  int DEFAULT NULL COMMENT '公司性质',
   com_industry  int DEFAULT NULL COMMENT '公司行业',
   depart_name  varchar(50) DEFAULT NULL COMMENT '所在部门',
   wage  int NOT NULL COMMENT '待遇情况',
   is_oversea  int NOT NULL COMMENT '是否有海外经历',
   description  varchar(2000) DEFAULT NULL COMMENT '描述',
   create_time  datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY ( exp_id )
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table  tq_work_exp  */

/*Table structure for table  tq_work_flow  */

DROP TABLE IF EXISTS  tq_work_flow ;

CREATE TABLE  tq_work_flow  (
   flow_id  int NOT NULL AUTO_INCREMENT,
   flow_name  varchar(50) NOT NULL COMMENT '工作流名称',
   description  varchar(200) DEFAULT NULL COMMENT '描述',
   create_time  datetime NOT NULL COMMENT '录入时间',
   step_count  int NOT NULL DEFAULT '2',
   site_id  int NOT NULL DEFAULT '1' COMMENT '站点ID',
  PRIMARY KEY ( flow_id ),
  KEY  fk_workflow_to_site  ( site_id ),
  CONSTRAINT  fk_workflow_to_site  FOREIGN KEY ( site_id ) REFERENCES  tq_site  ( site_id )
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table  tq_work_flow  */

insert  into  tq_work_flow ( flow_id , flow_name , description , create_time , step_count , site_id ) values (5,'新闻报送','新闻报送（信息报送员-->管理员）','2013-06-21 17:31:57',2,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;

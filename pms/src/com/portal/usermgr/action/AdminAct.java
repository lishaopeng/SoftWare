/*     */ package com.portal.usermgr.action;
/*     */ 
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.javapms.basic.utils.ServicesUtils;
/*     */ import com.portal.datacenter.operatedata.service.LogService;
/*     */ import com.portal.doccenter.entity.Channel;
/*     */ import com.portal.doccenter.service.ChannelService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.usermgr.entity.Admin;
/*     */ import com.portal.usermgr.entity.Depart;
/*     */ import com.portal.usermgr.entity.Role;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.AdminService;
/*     */ import com.portal.usermgr.service.RoleService;
/*     */ import com.portal.usermgr.service.UserService;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.shiro.authz.annotation.RequiresPermissions;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class AdminAct
/*     */ {
/*  37 */   private static final Logger log = LoggerFactory.getLogger(AdminAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private AdminService service;
/*     */ 
/*     */   @Autowired
/*     */   private UserService userService;
/*     */ 
/*     */   @Autowired
/*     */   private ChannelService channelService;
/*     */ 
/*     */   @Autowired
/*     */   private RoleService roleService;
/*     */ 
/*     */   @Autowired
/*     */   private LogService logService;
/*     */ 
/*  42 */   @RequestMapping({"/admin/v_chnltree.do"})
/*     */   public String chnltree(Integer adminId, HttpServletRequest request, HttpServletResponse response, ModelMap model) { Integer siteId = ContextTools.getSiteId(request);
/*  43 */     List chnlList = this.channelService.getChannelBySite(siteId, null, 
/*  44 */       null, null, null, null);
/*  45 */     model.addAttribute("chnlList", chnlList);
/*  46 */     if (adminId != null) {
/*  47 */       Integer[] channelIds = Channel.fetchIds(this.service.findById(adminId)
/*  48 */         .getChannels(siteId));
/*  49 */       model.addAttribute("chnlIds", channelIds);
/*     */     }
/*  51 */     response.setHeader("Cache-Control", "no-cache");
/*  52 */     response.setContentType("text/json;charset=UTF-8");
/*  53 */     return "userMgr/depart/tree"; }
/*     */ 
/*     */   @RequiresPermissions({"admin:admin:list"})
/*     */   @RequestMapping({"/admin/v_list.do"})
/*     */   public String list() {
/*  59 */     return "userMgr/admin/list";
/*     */   }
/*     */   @RequiresPermissions({"admin:admin:add"})
/*     */   @RequestMapping({"/admin/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model) {
/*  65 */     Site site = ContextTools.getSite(request);
/*  66 */     User user = ContextTools.getUser(request);
/*  67 */     Depart depart = user.getAdmin().getDepart(site.getId());
/*  68 */     List roleList = this.roleService.getListBySite(site.getId());
/*  69 */     model.addAttribute("roleList", roleList);
/*  70 */     model.addAttribute("depart", depart);
/*  71 */     model.addAttribute("user", user);
/*  72 */     return "userMgr/admin/add";
/*     */   }
/*     */   @RequiresPermissions({"admin:admin:edit"})
/*     */   @RequestMapping({"/admin/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  78 */     Site site = ContextTools.getSite(request);
/*  79 */     User user = ContextTools.getUser(request);
/*  80 */     Admin admin = this.service.findById(id);
/*  81 */     Role role = admin.getRole(site.getId());
/*  82 */     Depart depart = admin.getDepart(site.getId());
/*  83 */     List roleList = this.roleService.getListBySite(site.getId());
/*  84 */     model.addAttribute("roleList", roleList);
/*  85 */     model.addAttribute("user", user);
/*  86 */     model.addAttribute("depart", depart);
/*  87 */     model.addAttribute("admin", admin);
/*  88 */     model.addAttribute("site", site);
/*  89 */     model.addAttribute("roleId", role.getId());
/*  90 */     model.addAttribute("manageStatus", admin.getManageStatus(site.getId()));
/*  91 */     return "userMgr/admin/edit";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:admin:save"})
/*     */   @RequestMapping({"/admin/o_save.do"})
/*     */   public String save(User user, Admin admin, Integer roleId, Integer departId, Byte manageStatus, Boolean takeDepart, Integer[] channelIds, HttpServletRequest request, ModelMap model)
/*     */   {
/*  99 */     if (this.userService.findByUsername(user.getUsername()) != null) {
/* 100 */       model.addAttribute("msg", "该用户已经存在，添加失败!");
/* 101 */       model.addAttribute("status", Integer.valueOf(0));
/* 102 */       return add(request, model);
/*     */     }
/* 104 */     Site site = ContextTools.getSite(request);
/* 105 */     String ip = ServicesUtils.getIpAddr(request);
/* 106 */     admin = this.service.saveAdmin(user, admin, ip, roleId, departId, site, 
/* 107 */       manageStatus, takeDepart, channelIds);
/* 108 */     log.info("save Admin id={}", admin.getId());
/* 109 */     model.addAttribute("msg", "管理员添加成功!");
/* 110 */     model.addAttribute("status", Integer.valueOf(1));
/* 111 */     return add(request, model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:admin:update"})
/*     */   @RequestMapping({"/admin/o_update.do"})
/*     */   public String update(User user, Admin bean, Integer roleId, Integer departId, Byte manageStatus, Boolean takeDepart, Integer[] channelIds, HttpServletRequest request, ModelMap model)
/*     */   {
/* 119 */     Site site = ContextTools.getSite(request);
/* 120 */     bean = this.service.updateAdmin(user, bean, roleId, departId, site, 
/* 121 */       manageStatus, takeDepart, channelIds);
/* 122 */     log.info("update Admin id={}.", bean.getId());
/* 123 */     model.addAttribute("msg", "管理员修改成功!");
/* 124 */     return edit(bean.getId(), request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/admin/jsonData.do"})
/*     */   public String dataPageByJosn(String key, Integer departId, String sortname, String sortorder, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 132 */     Site site = ContextTools.getSite(request);
/* 133 */     User user = ContextTools.getUser(request);
/*     */ 
/* 135 */     if ((!user.getAdmin().getManageStatus(site.getId())
/* 135 */       .equals(Byte.valueOf((byte)4))) && 
/* 136 */       (user.getAdmin().getDepart(site.getId()) != null)) {
/* 137 */       departId = user.getAdmin().getDepart(site.getId()).getId();
/*     */     }
/*     */ 
/* 140 */     Page p = this.service.getPage(key, site.getId(), departId, null, 
/* 141 */       sortname, sortorder, page.intValue(), pagesize.intValue());
/* 142 */     model.addAttribute("p", p);
/* 143 */     model.addAttribute("siteId", site.getId());
/* 144 */     response.setHeader("Cache-Control", "no-cache");
/* 145 */     response.setContentType("text/json;charset=UTF-8");
/* 146 */     return "userMgr/admin/data";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:admin:delete"})
/*     */   @RequestMapping({"/admin/o_ajax_delete.do"})
/*     */   public void deleteAdmin(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 153 */     JSONObject json = new JSONObject();
/* 154 */     Admin[] beans = this.service.deleteByIds(ids);
/* 155 */     for (Admin bean : beans) {
/* 156 */       log.info("delete Admin id={}", bean.getId());
/* 157 */       this.logService.operating(request, "删除管理员", "id=" + bean.getId() + 
/* 158 */         ";name=" + bean.getUser().getUsername());
/*     */     }
/* 160 */     json.put("success", true);
/* 161 */     json.put("status", 1);
/* 162 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:admin:updatePass"})
/*     */   @RequestMapping({"/admin/o_updatePass.do"})
/*     */   public String updatePass(Integer adminId, String password, ModelMap model) {
/* 169 */     this.service.updatePass(adminId, password);
/* 170 */     log.info("update Admin Password id={}.", adminId);
/* 171 */     model.addAttribute("msg", "密码修改成功!");
/* 172 */     return list();
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/admin/o_checkuser.do"})
/*     */   public void checkUser(String username, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 178 */     User user = this.userService.findByUsername(username);
/* 179 */     if (user != null) {
/* 180 */       ResponseUtils.renderJson(response, "false");
/* 181 */       return;
/*     */     }
/* 183 */     ResponseUtils.renderJson(response, "true");
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.action.AdminAct
 * JD-Core Version:    0.6.1
 */
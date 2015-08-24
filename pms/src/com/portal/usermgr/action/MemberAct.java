/*     */ package com.portal.usermgr.action;
/*     */ 
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.javapms.basic.utils.ServicesUtils;
/*     */ import com.portal.datacenter.operatedata.service.LogService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.usermgr.entity.Member;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.GroupService;
/*     */ import com.portal.usermgr.service.MemberService;
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
/*     */ public class MemberAct
/*     */ {
/*  33 */   private static final Logger log = LoggerFactory.getLogger(MemberAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private MemberService service;
/*     */ 
/*     */   @Autowired
/*     */   private UserService userService;
/*     */ 
/*     */   @Autowired
/*     */   private GroupService groupService;
/*     */ 
/*     */   @Autowired
/*     */   private LogService logService;
/*     */ 
/*  38 */   @RequiresPermissions({"admin:member:list"})
/*     */   @RequestMapping({"/member/v_list.do"})
/*     */   public String list(HttpServletRequest request, ModelMap model) { Site site = ContextTools.getSite(request);
/*  39 */     List groupList = this.groupService.getList(site.getId(), null, null);
/*  40 */     model.addAttribute("groupList", groupList);
/*  41 */     return "userMgr/member/list"; }
/*     */ 
/*     */   @RequiresPermissions({"admin:member:add"})
/*     */   @RequestMapping({"/member/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model) {
/*  47 */     Site site = ContextTools.getSite(request);
/*  48 */     List groupList = this.groupService.getList(site.getId(), null, null);
/*  49 */     model.addAttribute("groupList", groupList);
/*  50 */     return "userMgr/member/add";
/*     */   }
/*     */   @RequiresPermissions({"admin:member:edit"})
/*     */   @RequestMapping({"/member/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  56 */     Site site = ContextTools.getSite(request);
/*  57 */     List groupList = this.groupService.getList(site.getId(), null, null);
/*  58 */     model.addAttribute("member", this.service.findById(id));
/*  59 */     model.addAttribute("groupList", groupList);
/*  60 */     model.addAttribute("siteId", site.getId());
/*  61 */     return "userMgr/member/edit";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:member:save"})
/*     */   @RequestMapping({"/member/o_save.do"})
/*     */   public String save(User user, Member bean, Integer groupId, HttpServletRequest request, ModelMap model) {
/*  68 */     if (this.userService.findByUsername(user.getUsername()) != null) {
/*  69 */       model.addAttribute("msg", "该用户已经存在，添加失败!");
/*  70 */       model.addAttribute("status", Integer.valueOf(0));
/*  71 */       return add(request, model);
/*     */     }
/*  73 */     String ip = ServicesUtils.getIpAddr(request);
/*  74 */     bean = this.service.registerMember(user, bean, ip, groupId);
/*  75 */     log.info("save Member id={}", bean.getId());
/*  76 */     model.addAttribute("msg", "会员添加成功!");
/*  77 */     model.addAttribute("status", Integer.valueOf(1));
/*  78 */     return add(request, model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:member:update"})
/*     */   @RequestMapping({"/member/o_update.do"})
/*     */   public String update(User user, Member bean, Integer groupId, HttpServletRequest request, ModelMap model) {
/*  85 */     Site site = ContextTools.getSite(request);
/*  86 */     bean = this.service.updateMember(user, bean, groupId, site.getId());
/*  87 */     log.info("update Member id={}.", bean.getId());
/*  88 */     model.addAttribute("msg", "会员修改成功!");
/*  89 */     return edit(bean.getId(), request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/jsonData.do"})
/*     */   public String dataPageByJosn(String key, Integer groupId, String sortname, String sortorder, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  97 */     Site site = ContextTools.getSite(request);
/*  98 */     Page p = this.service.getPage(key, site.getId(), groupId, 
/*  99 */       sortname, sortorder, page.intValue(), pagesize.intValue());
/* 100 */     model.addAttribute("p", p);
/* 101 */     model.addAttribute("siteId", site.getId());
/* 102 */     response.setHeader("Cache-Control", "no-cache");
/* 103 */     response.setContentType("text/json;charset=UTF-8");
/* 104 */     return "userMgr/member/data";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:member:delete"})
/*     */   @RequestMapping({"/member/o_ajax_delete.do"})
/*     */   public void deleteRole(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 111 */     JSONObject json = new JSONObject();
/* 112 */     Member[] beans = this.service.deleteByIds(ids);
/* 113 */     for (Member bean : beans) {
/* 114 */       log.info("delete Member id={}", bean.getId());
/* 115 */       this.logService.operating(request, "删除会员", "id=" + bean.getId() + 
/* 116 */         ";name=" + bean.getUser().getUsername());
/*     */     }
/* 118 */     json.put("success", true);
/* 119 */     json.put("status", 1);
/* 120 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:member:updatePass"})
/*     */   @RequestMapping({"/member/o_updatePass.do"})
/*     */   public String updatePass(Integer memberId, String password, HttpServletRequest request, ModelMap model)
/*     */   {
/* 128 */     this.service.updatePass(memberId, password);
/* 129 */     log.info("update Member Password id={}.", memberId);
/* 130 */     model.addAttribute("msg", "密码修改成功!");
/* 131 */     return list(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/member/o_checkuser.do"})
/*     */   public void checkUser(String username, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 137 */     User user = this.userService.findByUsername(username);
/* 138 */     if (user != null) {
/* 139 */       ResponseUtils.renderJson(response, "false");
/* 140 */       return;
/*     */     }
/* 142 */     ResponseUtils.renderJson(response, "true");
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.action.MemberAct
 * JD-Core Version:    0.6.1
 */
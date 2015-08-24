/*     */ package com.portal.govcenter.action;
/*     */ 
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.portal.datacenter.operatedata.service.LogService;
/*     */ import com.portal.govcenter.entity.Mailbox;
/*     */ import com.portal.govcenter.entity.MailboxExt;
/*     */ import com.portal.govcenter.service.MailboxService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.usermgr.entity.Admin;
/*     */ import com.portal.usermgr.entity.User;
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
/*     */ public class MailboxAct
/*     */ {
/*  28 */   private static final Logger log = LoggerFactory.getLogger(MailboxAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private MailboxService service;
/*     */ 
/*     */   @Autowired
/*     */   private LogService logService;
/*     */ 
/*  34 */   @RequiresPermissions({"admin:mailbox:list"})
/*     */   @RequestMapping({"/mailbox/v_list.do"})
/*     */   public String list(Integer typeId, HttpServletRequest request, ModelMap model) { Site site = ContextTools.getSite(request);
/*  35 */     User user = ContextTools.getUser(request);
/*  36 */     if (user.getAdmin().haveAllManage(site.getId()))
/*  37 */       model.addAttribute("all", Boolean.valueOf(true));
/*     */     else {
/*  39 */       model.addAttribute("all", Boolean.valueOf(false));
/*     */     }
/*  41 */     model.addAttribute("typeId", typeId);
/*  42 */     return "govCenter/mailbox/list"; }
/*     */ 
/*     */   @RequiresPermissions({"admin:mailbox:add"})
/*     */   @RequestMapping({"/mailbox/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model) {
/*  48 */     return "govCenter/mailbox/add";
/*     */   }
/*     */   @RequiresPermissions({"admin:mailbox:edit"})
/*     */   @RequestMapping({"/mailbox/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  54 */     model.addAttribute("mailbox", this.service.findById(id));
/*  55 */     return "govCenter/mailbox/edit";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:mailbox:save"})
/*     */   @RequestMapping({"/mailbox/o_save.do"})
/*     */   public String save(Mailbox bean, MailboxExt ext, Integer departId, Integer typeId, HttpServletRequest request, ModelMap model) {
/*  62 */     Site site = ContextTools.getSite(request);
/*  63 */     bean = this.service.save(bean, ext, site, departId, typeId);
/*  64 */     log.info("save Mailbox id={}", bean.getId());
/*  65 */     return add(request, model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:mailbox:update"})
/*     */   @RequestMapping({"/mailbox/o_update.do"})
/*     */   public String update(Mailbox bean, MailboxExt ext, Integer pageNo, HttpServletRequest request, ModelMap model) {
/*  72 */     Site site = ContextTools.getSite(request);
/*  73 */     User user = ContextTools.getUser(request);
/*  74 */     bean = this.service.update(bean, ext, user, site.getId());
/*  75 */     log.info("update Mailbox id={}.", bean.getId());
/*  76 */     model.addAttribute("msg", "信件回复成功!");
/*  77 */     return edit(bean.getId(), request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/mailbox/jsonData.do"})
/*     */   public String dataPageByJosn(String name, Integer typeId, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  84 */     Site site = ContextTools.getSite(request);
/*  85 */     User user = ContextTools.getUser(request);
/*  86 */     Page p = this.service.getPage(name, site.getId(), user, typeId, page.intValue(), 
/*  87 */       pagesize.intValue());
/*  88 */     model.addAttribute("p", p);
/*  89 */     response.setHeader("Cache-Control", "no-cache");
/*  90 */     response.setContentType("text/json;charset=UTF-8");
/*  91 */     return "govCenter/mailbox/data";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:mailbox:delete"})
/*     */   @RequestMapping({"/mailbox/o_ajax_delete.do"})
/*     */   public void deleteMailbox(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/*  98 */     JSONObject json = new JSONObject();
/*  99 */     Mailbox[] beans = this.service.deleteByIds(ids);
/* 100 */     for (Mailbox bean : beans) {
/* 101 */       log.info("delete Mailbox id={}", bean.getId());
/* 102 */       this.logService.operating(request, "删除局长信箱", "id=" + bean.getId());
/*     */     }
/* 104 */     json.put("success", true);
/* 105 */     json.put("status", 1);
/* 106 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:mailbox:show"})
/*     */   @RequestMapping({"/mailbox/o_ajax_show.do"})
/*     */   public void showMailbox(Integer id, HttpServletRequest request, HttpServletResponse response) throws JSONException
/*     */   {
/* 114 */     JSONObject json = new JSONObject();
/* 115 */     Mailbox mailbox = this.service.findById(id);
/* 116 */     if (mailbox == null) {
/* 117 */       json.put("success", false);
/* 118 */       json.put("status", 0);
/* 119 */       ResponseUtils.renderJson(response, json.toString());
/* 120 */       return;
/*     */     }
/* 122 */     this.service.showMailbox(id);
/* 123 */     json.put("success", true);
/* 124 */     json.put("status", 1);
/* 125 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/mailbox/o_forward.do"})
/*     */   public String forward(Integer id, Integer departId, Integer typeId, HttpServletRequest request, ModelMap model)
/*     */   {
/* 132 */     this.service.forwardMailbox(id, departId);
/* 133 */     return list(typeId, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/mailbox/o_back.do"})
/*     */   public String back(Integer id, Integer typeId, HttpServletRequest request, ModelMap model)
/*     */   {
/* 139 */     this.service.backMailbox(id);
/* 140 */     return list(typeId, request, model);
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.govcenter.action.MailboxAct
 * JD-Core Version:    0.6.1
 */
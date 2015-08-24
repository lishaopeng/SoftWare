/*     */ package com.portal.sysmgr.action;
/*     */ 
/*     */ /*     */ import java.util.List;

/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;

/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.shiro.authz.annotation.RequiresPermissions;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;

import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.service.SiteService;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.usermgr.entity.User;
/*     */ 
/*     */ @Controller
/*     */ public class SiteAct
/*     */ {
/*  27 */   private static final Logger log = LoggerFactory.getLogger(SiteAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private SiteService service;
/*     */ 
/*  32 */   @RequiresPermissions({"admin:sites:list"})
/*     */   @RequestMapping({"/sites/v_list.do"})
/*     */   public String list(HttpServletRequest request) { return "sysMgr/siteConf/sites/list"; }
/*     */ 
/*     */   @RequiresPermissions({"admin:sites:add"})
/*     */   @RequestMapping({"/sites/v_add.do"})
/*     */   public String add(ModelMap model) {
/*  38 */     return "sysMgr/siteConf/sites/add";
/*     */   }
/*     */   @RequiresPermissions({"admin:sites:edit"})
/*     */   @RequestMapping({"/sites/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  44 */     model.addAttribute("site", this.service.findById(id));
/*  45 */     return "sysMgr/siteConf/sites/edit";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:sites:save"})
/*     */   @RequestMapping({"/sites/o_save.do"})
/*     */   public String save(Site bean, Integer[] channelIds, HttpServletRequest request, ModelMap model) {
/*  52 */     Site site = ContextTools.getSite(request);
/*  53 */     User user = ContextTools.getUser(request);
/*  54 */     bean = this.service.save(bean, user, site.getId(), channelIds);
/*  55 */     log.info("save Site id={}", bean.getId());
/*  56 */     return "redirect:v_list.do";
/*     */   }
/*     */   @RequiresPermissions({"admin:sites:update"})
/*     */   @RequestMapping({"/sites/o_update.do"})
/*     */   public String update(Site bean, HttpServletRequest request, ModelMap model) {
/*  62 */     bean = this.service.update(bean);
/*  63 */     model.addAttribute("msg", "站点修改成功");
/*  64 */     log.info("update Site success. id={}", bean.getId());
/*  65 */     return edit(bean.getId(), request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/sites/jsonData.do"})
/*     */   public String dataPageByJosn(String name, Integer typeId, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  72 */     List list = this.service.getList();
/*  73 */     model.addAttribute("list", list);
/*  74 */     response.setHeader("Cache-Control", "no-cache");
/*  75 */     response.setContentType("text/json;charset=UTF-8");
/*  76 */     return "sysMgr/siteConf/sites/data";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:sites:delete"})
/*     */   @RequestMapping({"/sites/o_ajax_delete.do"})
/*     */   public void delete(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/*  83 */     JSONObject json = new JSONObject();
/*  84 */     Site[] beans = this.service.deleteByIds(ids);
/*  85 */     for (Site bean : beans) {
/*  86 */       log.info("delete Site id={}", bean.getId());
/*     */     }
/*  88 */     json.put("success", true);
/*  89 */     json.put("status", 1);
/*  90 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:sites:sys_edit"})
/*     */   @RequestMapping({"/site/v_sys_edit.do"})
/*     */   public String sysEdit(HttpServletRequest request, ModelMap model) {
/*  97 */     Site site = ContextTools.getSite(request);
/*  98 */     model.addAttribute("site", site);
/*  99 */     return "sysMgr/siteConf/sites/sys_edit";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:sites:sys_update"})
/*     */   @RequestMapping({"/site/o_sys_update.do"})
/*     */   public String sysUpdate(Site bean, HttpServletRequest request, ModelMap model) {
/* 106 */     bean = this.service.update(bean);
/* 107 */     model.addAttribute("msg", "站点修改成功");
/* 108 */     log.info("update Site success. id={}", bean.getId());
/* 109 */     return sysEdit(request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/sites/v_checkDomain.do"})
/*     */   public void checkDomainJson(String domain, HttpServletResponse response)
/*     */   {
/*     */     String pass;
/* 115 */     if (StringUtils.isBlank(domain))
/* 116 */       pass = "false";
/*     */     else {
/* 118 */       pass = this.service.findByDomain(domain, false) == null ? "true" : 
/* 119 */         "false";
/*     */     }
/* 121 */     ResponseUtils.renderJson(response, pass);
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.action.SiteAct
 * JD-Core Version:    0.6.1
 */
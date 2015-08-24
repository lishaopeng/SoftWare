/*    */ package com.portal.sysmgr.action;
/*    */ 
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.entity.SiteConfig;
/*    */ import com.portal.sysmgr.service.SiteConfigService;
/*    */ import com.portal.sysmgr.utils.ContextTools;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.shiro.authz.annotation.RequiresPermissions;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/config"})
/*    */ public class SiteConfigAct
/*    */ {
/* 23 */   private static final Logger log = LoggerFactory.getLogger(SiteConfigAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private SiteConfigService service;
/*    */ 
/* 28 */   @RequiresPermissions({"admin:config:edit"})
/*    */   @RequestMapping({"/v_edit.do"})
/*    */   public String edit(HttpServletRequest request, ModelMap model) { Site site = ContextTools.getSite(request);
/* 29 */     model.addAttribute("config", this.service.findById(site.getId()));
/* 30 */     return "sysMgr/siteConf/config/edit"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:config:update"})
/*    */   @RequestMapping({"/o_update.do"})
/*    */   public String update(SiteConfig bean, HttpServletRequest request, ModelMap model)
/*    */   {
/* 37 */     Site site = ContextTools.getSite(request);
/* 38 */     bean = this.service.update(bean, site);
/* 39 */     model.addAttribute("msg", "保存成功");
/* 40 */     log.info("update SiteConfig id={}.", bean.getId());
/* 41 */     return edit(request, model);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.action.SiteConfigAct
 * JD-Core Version:    0.6.1
 */
/*    */ package com.portal.sysmgr.action;
/*    */ 
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.entity.ThirdpartyConfig;
/*    */ import com.portal.sysmgr.service.ThirdpartyConfigService;
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
/*    */ @RequestMapping({"/partyConfig"})
/*    */ public class ThirdpartyConfigAct
/*    */ {
/* 23 */   private static final Logger log = LoggerFactory.getLogger(ThirdpartyConfigAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private ThirdpartyConfigService service;
/*    */ 
/* 28 */   @RequiresPermissions({"admin:partyConfig:edit"})
/*    */   @RequestMapping({"/v_edit.do"})
/*    */   public String edit(HttpServletRequest request, ModelMap model) { Site site = ContextTools.getSite(request);
/* 29 */     model.addAttribute("config", this.service.findById(site.getId()));
/* 30 */     return "sysMgr/partyConfig/edit"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:partyConfig:update"})
/*    */   @RequestMapping({"/o_update.do"})
/*    */   public String update(ThirdpartyConfig bean, HttpServletRequest request, ModelMap model)
/*    */   {
/* 37 */     Site site = ContextTools.getSite(request);
/* 38 */     bean = this.service.update(bean, site);
/* 39 */     model.addAttribute("msg", "保存成功");
/* 40 */     log.info("update ThirdpartyConfig id={}.", bean.getId());
/* 41 */     return edit(request, model);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.action.ThirdpartyConfigAct
 * JD-Core Version:    0.6.1
 */
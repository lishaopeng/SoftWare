/*    */ package com.portal.sysmgr.action;
/*    */ 
/*    */ import com.portal.sysmgr.entity.DatabaseConfig;
/*    */ import com.portal.sysmgr.service.DatabaseConfigService;
/*    */ import org.apache.shiro.authz.annotation.RequiresPermissions;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ @RequestMapping({"/dbconfig"})
/*    */ public class DatabaseConfigAct
/*    */ {
/* 19 */   private static final Logger log = LoggerFactory.getLogger(DatabaseConfigAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private DatabaseConfigService service;
/*    */ 
/* 24 */   @RequiresPermissions({"admin:dbconfig:edit"})
/*    */   @RequestMapping({"/v_edit.do"})
/*    */   public String edit(ModelMap model) { model.addAttribute("dbconfig", this.service.findUnique());
/* 25 */     return "dataCenter/dbback/configedit"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:dbconfig:update"})
/*    */   @RequestMapping({"/o_update.do"})
/*    */   public String update(DatabaseConfig bean, ModelMap model) {
/* 31 */     bean = this.service.update(bean);
/* 32 */     model.addAttribute("msg", "备份配置保存成功");
/* 33 */     log.info("update DatabaseConfig id={}.", bean.getId());
/* 34 */     return edit(model);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.action.DatabaseConfigAct
 * JD-Core Version:    0.6.1
 */
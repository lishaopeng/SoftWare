/*    */ package com.portal.datacenter.commdata.action;
/*    */ 
/*    */ import com.portal.datacenter.commdata.entity.ForeignLang;
/*    */ import com.portal.datacenter.commdata.service.ForeignLangService;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import org.apache.shiro.authz.annotation.RequiresPermissions;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class ForeignLangAct
/*    */ {
/* 20 */   private static final Logger log = LoggerFactory.getLogger(ForeignLangAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private ForeignLangService service;
/*    */ 
/* 26 */   @RequiresPermissions({"admin:foreignlang:list"})
/*    */   @RequestMapping({"/foreignlang/v_list.do"})
/*    */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { Page page = this.service.getPage(pageNo.intValue(), 20);
/* 27 */     model.addAttribute("page", page);
/* 28 */     return "foreignlang/list"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:foreignlang:add"})
/*    */   @RequestMapping({"/foreignlang/v_add.do"})
/*    */   public String add(ModelMap model) {
/* 34 */     return "foreignlang/add";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:foreignlang:edit"})
/*    */   @RequestMapping({"/foreignlang/v_edit.do"})
/*    */   public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 41 */     model.addAttribute("foreignLang", this.service.findById(id));
/* 42 */     model.addAttribute("pageNo", pageNo);
/* 43 */     return "foreignlang/edit";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:foreignlang:save"})
/*    */   @RequestMapping({"/foreignlang/o_save.do"})
/*    */   public String save(ForeignLang bean, HttpServletRequest request, ModelMap model) {
/* 50 */     bean = this.service.save(bean);
/* 51 */     log.info("save ForeignLang id={}", bean.getId());
/* 52 */     return "redirect:v_list.do";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:foreignlang:update"})
/*    */   @RequestMapping({"/foreignlang/o_update.do"})
/*    */   public String update(ForeignLang bean, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 59 */     bean = this.service.update(bean);
/* 60 */     log.info("update ForeignLang id={}.", bean.getId());
/* 61 */     return list(pageNo, request, model);
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:foreignlang:delete"})
/*    */   @RequestMapping({"/foreignlang/o_delete.do"})
/*    */   public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 68 */     ForeignLang[] beans = this.service.deleteByIds(ids);
/* 69 */     for (ForeignLang bean : beans) {
/* 70 */       log.info("delete ForeignLang id={}", bean.getId());
/*    */     }
/* 72 */     return list(pageNo, request, model);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.action.ForeignLangAct
 * JD-Core Version:    0.6.1
 */
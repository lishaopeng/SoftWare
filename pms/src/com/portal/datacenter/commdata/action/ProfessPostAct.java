/*    */ package com.portal.datacenter.commdata.action;
/*    */ 
/*    */ import com.portal.datacenter.commdata.entity.ProfessPost;
/*    */ import com.portal.datacenter.commdata.service.ProfessPostService;
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
/*    */ public class ProfessPostAct
/*    */ {
/* 20 */   private static final Logger log = LoggerFactory.getLogger(ProfessPostAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private ProfessPostService service;
/*    */ 
/* 26 */   @RequiresPermissions({"admin:professfost:list"})
/*    */   @RequestMapping({"/professfost/v_list.do"})
/*    */   public String list(Integer pageNo, HttpServletRequest request, ModelMap model) { Page page = this.service.getPage(pageNo.intValue(), 20);
/* 27 */     model.addAttribute("page", page);
/* 28 */     return "professfost/list"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:professfost:add"})
/*    */   @RequestMapping({"/professfost/v_add.do"})
/*    */   public String add(ModelMap model) {
/* 34 */     return "professfost/add";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:professfost:edit"})
/*    */   @RequestMapping({"/professfost/v_edit.do"})
/*    */   public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 41 */     ProfessPost post = this.service.findById(id);
/* 42 */     model.addAttribute("professPost", post);
/* 43 */     model.addAttribute("pageNo", pageNo);
/* 44 */     return "professfost/edit";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:professfost:save"})
/*    */   @RequestMapping({"/professfost/o_save.do"})
/*    */   public String save(ProfessPost bean, HttpServletRequest request, ModelMap model) {
/* 51 */     bean = this.service.save(bean);
/* 52 */     log.info("save ProfessPost id={}", bean.getId());
/* 53 */     return "redirect:v_list.do";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:professfost:update"})
/*    */   @RequestMapping({"/professfost/o_update.do"})
/*    */   public String update(ProfessPost bean, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 60 */     bean = this.service.update(bean);
/* 61 */     log.info("update ProfessPost id={}.", bean.getId());
/* 62 */     return list(pageNo, request, model);
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:professfost:delete"})
/*    */   @RequestMapping({"/professfost/o_delete.do"})
/*    */   public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 69 */     ProfessPost[] beans = this.service.deleteByIds(ids);
/* 70 */     for (ProfessPost bean : beans) {
/* 71 */       log.info("delete ProfessPost id={}", bean.getId());
/*    */     }
/* 73 */     return list(pageNo, request, model);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.action.ProfessPostAct
 * JD-Core Version:    0.6.1
 */
/*    */ package com.portal.datacenter.commdata.action;
/*    */ 
/*    */ import com.portal.datacenter.commdata.entity.Specialty;
/*    */ import com.portal.datacenter.commdata.service.SpecialtyService;
/*    */ import java.util.List;
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
/*    */ public class SpecialtyAct
/*    */ {
/* 22 */   private static final Logger log = LoggerFactory.getLogger(SpecialtyAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private SpecialtyService service;
/*    */ 
/* 28 */   @RequiresPermissions({"admin:specialty:list"})
/*    */   @RequestMapping({"/specialty/v_list.do"})
/*    */   public String list(String key, Integer pageNo, HttpServletRequest request, ModelMap model) { Page p = this.service.getPage(key, pageNo.intValue(), 20);
/* 29 */     model.addAttribute("p", p);
/* 30 */     model.addAttribute("key", key);
/* 31 */     return "specialty/list"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:specialty:add"})
/*    */   @RequestMapping({"/specialty/v_add.do"})
/*    */   public String add(ModelMap model) {
/* 37 */     List specialtyList = this.service.getSpecialtyList(null);
/* 38 */     model.addAttribute("specialtyList", specialtyList);
/* 39 */     return "specialty/add";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:specialty:edit"})
/*    */   @RequestMapping({"/specialty/v_edit.do"})
/*    */   public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 46 */     List specialtyList = this.service.getSpecialtyList(id);
/* 47 */     Specialty specialty = this.service.findById(id);
/* 48 */     if (specialty.getParent() != null) {
/* 49 */       model.addAttribute("parentId", specialty.getParent().getId());
/*    */     }
/* 51 */     model.addAttribute("specialtyList", Boolean.valueOf(specialtyList.remove(specialty)));
/* 52 */     model.addAttribute("specialty", specialty);
/* 53 */     model.addAttribute("pageNo", pageNo);
/* 54 */     return "specialty/edit";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:specialty:save"})
/*    */   @RequestMapping({"/specialty/o_save.do"})
/*    */   public String save(Specialty bean, Integer parentId, HttpServletRequest request, ModelMap model) {
/* 61 */     bean = this.service.save(bean, parentId);
/* 62 */     log.info("save Specialty id={}", bean.getId());
/* 63 */     return "redirect:v_list.do";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:specialty:update"})
/*    */   @RequestMapping({"/specialty/o_update.do"})
/*    */   public String update(Specialty bean, Integer parentId, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 70 */     bean = this.service.update(bean, parentId);
/* 71 */     log.info("update Specialty id={}.", bean.getId());
/* 72 */     return list(null, pageNo, request, model);
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:specialty:delete"})
/*    */   @RequestMapping({"/specialty/o_delete.do"})
/*    */   public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 79 */     Specialty[] beans = this.service.deleteByIds(ids);
/* 80 */     for (Specialty bean : beans) {
/* 81 */       log.info("delete Specialty id={}", bean.getId());
/*    */     }
/* 83 */     return list(null, pageNo, request, model);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.action.SpecialtyAct
 * JD-Core Version:    0.6.1
 */
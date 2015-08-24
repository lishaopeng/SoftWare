/*    */ package com.portal.datacenter.commdata.action;
/*    */ 
/*    */ import com.portal.datacenter.commdata.entity.Industry;
/*    */ import com.portal.datacenter.commdata.service.IndustryService;
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
/*    */ public class IndustryAct
/*    */ {
/* 22 */   private static final Logger log = LoggerFactory.getLogger(IndustryAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private IndustryService service;
/*    */ 
/* 28 */   @RequiresPermissions({"admin:industry:list"})
/*    */   @RequestMapping({"/industry/v_list.do"})
/*    */   public String list(String key, Integer pageNo, HttpServletRequest request, ModelMap model) { Page page = this.service.getPage(key, pageNo.intValue(), 20);
/* 29 */     model.addAttribute("page", page);
/* 30 */     model.addAttribute("key", key);
/* 31 */     return "industry/list"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:industry:add"})
/*    */   @RequestMapping({"/industry/v_add.do"})
/*    */   public String add(ModelMap model) {
/* 37 */     List industryList = this.service.getIndustryList(null);
/* 38 */     model.addAttribute("industryList", industryList);
/* 39 */     return "industry/add";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:industry:edit"})
/*    */   @RequestMapping({"/industry/v_edit.do"})
/*    */   public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 46 */     List industryList = this.service.getIndustryList(id);
/* 47 */     Industry industry = this.service.findById(id);
/* 48 */     if (industry.getParent() != null) {
/* 49 */       model.addAttribute("parentId", industry.getParent().getId());
/*    */     }
/* 51 */     model.addAttribute("industryList", industryList);
/* 52 */     model.addAttribute("industry", industry);
/* 53 */     model.addAttribute("pageNo", pageNo);
/* 54 */     return "industry/edit";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:industry:save"})
/*    */   @RequestMapping({"/industry/o_save.do"})
/*    */   public String save(Industry bean, Integer parentId, HttpServletRequest request, ModelMap model) {
/* 61 */     bean = this.service.save(bean, parentId);
/* 62 */     log.info("save Industry id={}", bean.getId());
/* 63 */     return "redirect:v_list.do";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:industry:update"})
/*    */   @RequestMapping({"/industry/o_update.do"})
/*    */   public String update(Industry bean, Integer parentId, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 70 */     bean = this.service.update(bean, parentId);
/* 71 */     log.info("update Industry id={}.", bean.getId());
/* 72 */     return list(null, pageNo, request, model);
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:industry:delete"})
/*    */   @RequestMapping({"/industry/o_delete.do"})
/*    */   public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 79 */     Industry[] beans = this.service.deleteByIds(ids);
/* 80 */     for (Industry bean : beans) {
/* 81 */       log.info("delete Industry id={}", bean.getId());
/*    */     }
/* 83 */     return list(null, pageNo, request, model);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.action.IndustryAct
 * JD-Core Version:    0.6.1
 */
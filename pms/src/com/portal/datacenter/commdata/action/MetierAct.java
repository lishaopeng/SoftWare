/*    */ package com.portal.datacenter.commdata.action;
/*    */ 
/*    */ import com.portal.datacenter.commdata.entity.Metier;
/*    */ import com.portal.datacenter.commdata.service.MetierService;
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
/*    */ public class MetierAct
/*    */ {
/* 21 */   private static final Logger log = LoggerFactory.getLogger(MetierAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private MetierService service;
/*    */ 
/* 27 */   @RequiresPermissions({"admin:metier:list"})
/*    */   @RequestMapping({"/metier/v_list.do"})
/*    */   public String list(String key, Integer pageNo, HttpServletRequest request, ModelMap model) { Page page = this.service.getPage(key, pageNo.intValue(), 20);
/* 28 */     model.addAttribute("page", page);
/* 29 */     model.addAttribute("key", key);
/* 30 */     return "metier/list"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:metier:add"})
/*    */   @RequestMapping({"/metier/v_add.do"})
/*    */   public String add(ModelMap model) {
/* 36 */     List metierList = this.service.getMetierList(null);
/* 37 */     model.addAttribute("metierList", metierList);
/* 38 */     return "metier/add";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:metier:edit"})
/*    */   @RequestMapping({"/metier/v_edit.do"})
/*    */   public String edit(Integer id, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 45 */     Metier metier = this.service.findById(id);
/* 46 */     List metierList = this.service.getMetierList(id);
/* 47 */     if (metier.getParent() != null) {
/* 48 */       model.addAttribute("parentId", metier.getParent().getId());
/*    */     }
/* 50 */     model.addAttribute("metierList", metierList);
/* 51 */     model.addAttribute("metier", metier);
/* 52 */     model.addAttribute("pageNo", pageNo);
/* 53 */     return "metier/edit";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:metier:save"})
/*    */   @RequestMapping({"/metier/o_save.do"})
/*    */   public String save(Metier bean, Integer parentId, HttpServletRequest request, ModelMap model) {
/* 60 */     bean = this.service.save(bean, parentId);
/* 61 */     log.info("save Metier id={}", bean.getId());
/* 62 */     return "redirect:v_list.do";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:metier:update"})
/*    */   @RequestMapping({"/metier/o_update.do"})
/*    */   public String update(Metier bean, Integer parentId, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 69 */     bean = this.service.update(bean, parentId);
/* 70 */     log.info("update Metier id={}.", bean.getId());
/* 71 */     return list(null, pageNo, request, model);
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:metier:delete"})
/*    */   @RequestMapping({"/metier/o_delete.do"})
/*    */   public String delete(Integer[] ids, Integer pageNo, HttpServletRequest request, ModelMap model) {
/* 78 */     Metier[] beans = this.service.deleteByIds(ids);
/* 79 */     for (Metier bean : beans) {
/* 80 */       log.info("delete Metier id={}", bean.getId());
/*    */     }
/* 82 */     return list(null, pageNo, request, model);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.action.MetierAct
 * JD-Core Version:    0.6.1
 */
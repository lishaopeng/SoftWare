/*    */ package com.portal.extrafunc.action;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.datacenter.operatedata.service.LogService;
/*    */ import com.portal.extrafunc.entity.Category;
/*    */ import com.portal.extrafunc.service.CategoryService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.utils.ContextTools;
/*    */ import java.util.List;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.shiro.authz.annotation.RequiresPermissions;
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class CategoryAct
/*    */ {
/* 28 */   private static final Logger log = LoggerFactory.getLogger(CategoryAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private CategoryService service;
/*    */ 
/*    */   @Autowired
/*    */   private LogService logService;
/*    */ 
/* 33 */   @RequiresPermissions({"admin:category:list"})
/*    */   @RequestMapping({"/category/v_list.do"})
/*    */   public String list() { return "extraFunc/category/list"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:category:add"})
/*    */   @RequestMapping({"/category/v_add.do"})
/*    */   public String add() {
/* 39 */     return "extraFunc/category/add";
/*    */   }
/*    */   @RequiresPermissions({"admin:category:edit"})
/*    */   @RequestMapping({"/category/v_edit.do"})
/*    */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/* 45 */     model.addAttribute("category", this.service.findById(id));
/* 46 */     return "extraFunc/category/edit";
/*    */   }
/*    */   @RequiresPermissions({"admin:category:save"})
/*    */   @RequestMapping({"/category/o_save.do"})
/*    */   public String save(Category bean, HttpServletRequest request, ModelMap model) {
/* 52 */     Site site = ContextTools.getSite(request);
/* 53 */     bean = this.service.save(bean, site);
/* 54 */     log.info("save Category id={}", bean.getId());
/* 55 */     this.logService.operating(request, "添加分类", "id=" + bean.getId());
/* 56 */     model.addAttribute("msg", "分类添加成功!");
/* 57 */     return list();
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:category:update"})
/*    */   @RequestMapping({"/category/o_update.do"})
/*    */   public String update(Category bean, HttpServletRequest request, ModelMap model) {
/* 64 */     bean = this.service.update(bean);
/* 65 */     log.info("update Category id={}.", bean.getId());
/* 66 */     this.logService.operating(request, "修改分类", "id=" + bean.getId());
/* 67 */     model.addAttribute("msg", "分类修改成功!");
/* 68 */     return list();
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/category/jsonData.do"})
/*    */   public String dataPageByJosn(String sortname, String sortorder, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 75 */     Site site = ContextTools.getSite(request);
/* 76 */     List list = this.service
/* 77 */       .getList(site.getId(), sortname, sortorder);
/* 78 */     model.addAttribute("list", list);
/* 79 */     response.setHeader("Cache-Control", "no-cache");
/* 80 */     response.setContentType("text/json;charset=UTF-8");
/* 81 */     return "extraFunc/category/data";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:category:delete"})
/*    */   @RequestMapping({"/category/o_ajax_delete.do"})
/*    */   public void deleteCategory(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 88 */     JSONObject json = new JSONObject();
/* 89 */     Category[] beans = this.service.deleteByIds(ids);
/* 90 */     for (Category bean : beans) {
/* 91 */       log.info("delete Category id={}", bean.getId());
/* 92 */       this.logService.operating(request, "删除分类", "id=" + bean.getId());
/*    */     }
/* 94 */     json.put("success", true);
/* 95 */     json.put("status", 1);
/* 96 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.CategoryAct
 * JD-Core Version:    0.6.1
 */
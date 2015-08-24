/*    */ package com.portal.extrafunc.action;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.extrafunc.entity.LinksType;
/*    */ import com.portal.extrafunc.service.LinksTypeService;
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
/*    */ @RequestMapping({"/linksType"})
/*    */ public class LinksTypeAct
/*    */ {
/* 28 */   private static final Logger log = LoggerFactory.getLogger(LinksTypeAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private LinksTypeService service;
/*    */ 
/* 33 */   @RequiresPermissions({"admin:linksType:list"})
/*    */   @RequestMapping({"/v_list.do"})
/*    */   public String list() { return "extraFunc/links/type/list"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:linksType:add"})
/*    */   @RequestMapping({"/v_add.do"})
/*    */   public String add(ModelMap model) {
/* 39 */     return "extraFunc/links/type/add";
/*    */   }
/*    */   @RequiresPermissions({"admin:linksType:edit"})
/*    */   @RequestMapping({"/v_edit.do"})
/*    */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/* 45 */     model.addAttribute("linksType", this.service.findById(id));
/* 46 */     return "extraFunc/links/type/edit";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:linksType:edit"})
/*    */   @RequestMapping({"/o_save.do"})
/*    */   public String save(LinksType bean, HttpServletRequest request, ModelMap model) {
/* 53 */     Site site = ContextTools.getSite(request);
/* 54 */     bean = this.service.save(bean, site);
/* 55 */     log.info("save LinksType id={}", bean.getId());
/* 56 */     return list();
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:linksType:update"})
/*    */   @RequestMapping({"/o_update.do"})
/*    */   public String update(LinksType bean, HttpServletRequest request, ModelMap model) {
/* 63 */     bean = this.service.update(bean);
/* 64 */     log.info("update LinksType id={}.", bean.getId());
/* 65 */     return list();
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/jsonData.do"})
/*    */   public String dataPageByJosn(String sortname, String sortorder, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 72 */     Integer siteId = ContextTools.getSiteId(request);
/* 73 */     List list = this.service.getList(siteId, sortname, sortorder);
/* 74 */     model.addAttribute("list", list);
/* 75 */     response.setHeader("Cache-Control", "no-cache");
/* 76 */     response.setContentType("text/json;charset=UTF-8");
/* 77 */     return "extraFunc/links/type/data";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:linksType:delete"})
/*    */   @RequestMapping({"/o_ajax_delete.do"})
/*    */   public void deleteLinksType(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 84 */     JSONObject json = new JSONObject();
/* 85 */     LinksType[] beans = this.service.deleteByIds(ids);
/* 86 */     for (LinksType bean : beans) {
/* 87 */       log.info("delete LinksType id={}", bean.getId());
/*    */     }
/* 89 */     json.put("success", true);
/* 90 */     json.put("status", 1);
/* 91 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.LinksTypeAct
 * JD-Core Version:    0.6.1
 */
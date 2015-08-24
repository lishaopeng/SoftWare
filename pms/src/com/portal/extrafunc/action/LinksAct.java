/*     */ package com.portal.extrafunc.action;
/*     */ 
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.portal.extrafunc.entity.Links;
/*     */ import com.portal.extrafunc.service.LinksService;
/*     */ import com.portal.extrafunc.service.LinksTypeService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.shiro.authz.annotation.RequiresPermissions;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ @RequestMapping({"/links"})
/*     */ public class LinksAct
/*     */ {
/*  30 */   private static final Logger log = LoggerFactory.getLogger(LinksAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private LinksService service;
/*     */ 
/*     */   @Autowired
/*     */   private LinksTypeService typeService;
/*     */ 
/*  35 */   @RequiresPermissions({"admin:links:list"})
/*     */   @RequestMapping({"/v_list.do"})
/*     */   public String list() { return "extraFunc/links/list"; }
/*     */ 
/*     */   @RequiresPermissions({"admin:links:add"})
/*     */   @RequestMapping({"/v_add.do"})
/*     */   public String add(HttpServletRequest request, ModelMap model) {
/*  41 */     Integer siteId = ContextTools.getSiteId(request);
/*  42 */     List typeList = this.typeService.getList(siteId, null, null);
/*  43 */     model.addAttribute("typeList", typeList);
/*  44 */     return "extraFunc/links/add";
/*     */   }
/*     */   @RequiresPermissions({"admin:links:edit"})
/*     */   @RequestMapping({"/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  50 */     Integer siteId = ContextTools.getSiteId(request);
/*  51 */     List typeList = this.typeService.getList(siteId, null, null);
/*  52 */     model.addAttribute("typeList", typeList);
/*  53 */     model.addAttribute("links", this.service.findById(id));
/*  54 */     return "extraFunc/links/edit";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:links:edit"})
/*     */   @RequestMapping({"/o_save.do"})
/*     */   public String save(Links bean, Integer typeId, HttpServletRequest request, ModelMap model) {
/*  61 */     Site site = ContextTools.getSite(request);
/*  62 */     bean = this.service.save(bean, typeId, site);
/*  63 */     log.info("save Links id={}", bean.getId());
/*  64 */     model.addAttribute("msg", "友情链接添加成功!");
/*  65 */     return add(request, model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:links:update"})
/*     */   @RequestMapping({"/o_update.do"})
/*     */   public String update(Links bean, Integer typeId, HttpServletRequest request, ModelMap model) {
/*  72 */     bean = this.service.update(bean, typeId);
/*  73 */     log.info("update Links id={}.", bean.getId());
/*  74 */     model.addAttribute("msg", "友情链接修改成功!");
/*  75 */     return edit(bean.getId(), request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/jsonData.do"})
/*     */   public String dataPageByJosn(Integer typeId, String sortname, String sortorder, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  83 */     Integer siteId = ContextTools.getSiteId(request);
/*  84 */     Page p = this.service.getPage(siteId, typeId, sortname, sortorder, 
/*  85 */       page.intValue(), pagesize.intValue());
/*  86 */     model.addAttribute("p", p);
/*  87 */     response.setHeader("Cache-Control", "no-cache");
/*  88 */     response.setContentType("text/json;charset=UTF-8");
/*  89 */     return "extraFunc/links/data";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:links:delete"})
/*     */   @RequestMapping({"/o_ajax_delete.do"})
/*     */   public void deleteLinks(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/*  96 */     JSONObject json = new JSONObject();
/*  97 */     Links[] beans = this.service.deleteByIds(ids);
/*  98 */     for (Links bean : beans) {
/*  99 */       log.info("delete Links id={}", bean.getId());
/*     */     }
/* 101 */     json.put("success", true);
/* 102 */     json.put("status", 1);
/* 103 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.LinksAct
 * JD-Core Version:    0.6.1
 */
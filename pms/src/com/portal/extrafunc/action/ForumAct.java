/*     */ package com.portal.extrafunc.action;
/*     */ 
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.portal.extrafunc.entity.Category;
/*     */ import com.portal.extrafunc.entity.Forum;
/*     */ import com.portal.extrafunc.entity.ForumExt;
/*     */ import com.portal.extrafunc.service.CategoryService;
/*     */ import com.portal.extrafunc.service.ForumService;
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
/*     */ public class ForumAct
/*     */ {
/*  30 */   private static final Logger log = LoggerFactory.getLogger(ForumAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private ForumService service;
/*     */ 
/*     */   @Autowired
/*     */   private CategoryService categoryService;
/*     */ 
/*  36 */   @RequiresPermissions({"admin:forum:list"})
/*     */   @RequestMapping({"/forum/v_list.do"})
/*     */   public String list(Integer categoryId, HttpServletRequest request, ModelMap model) { Site site = ContextTools.getSite(request);
/*  37 */     List categoryList = this.categoryService.getList(site.getId(), 
/*  38 */       null, null);
/*  39 */     model.addAttribute("categoryList", categoryList);
/*  40 */     model.addAttribute("categoryId", categoryId);
/*  41 */     return "extraFunc/forum/list"; }
/*     */ 
/*     */   @RequiresPermissions({"admin:forum:save"})
/*     */   @RequestMapping({"/forum/o_save.do"})
/*     */   public String save(Forum bean, ForumExt ext, Integer categoryId, HttpServletRequest request, ModelMap model)
/*     */   {
/*  48 */     Site site = ContextTools.getSite(request);
/*  49 */     bean = this.service.save(bean, ext, site, categoryId);
/*  50 */     log.info("save Forum id={}", bean.getId());
/*  51 */     model.addAttribute("msg", "板块添加成功!");
/*  52 */     return list(null, request, model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:forum:update"})
/*     */   @RequestMapping({"/forum/o_update.do"})
/*     */   public String update(Forum bean, ForumExt ext, Integer categoryId, HttpServletRequest request, ModelMap model) {
/*  59 */     bean = this.service.update(bean, ext, categoryId);
/*  60 */     log.info("update Forum id={}.", bean.getId());
/*  61 */     model.addAttribute("msg", "板块修改成功!");
/*  62 */     return list(null, request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/forum/jsonData.do"})
/*     */   public String dataPageByJosn(Integer categoryId, String sortname, String sortorder, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  70 */     Site site = ContextTools.getSite(request);
/*  71 */     Page p = this.service.getPage(site.getId(), categoryId, sortname, 
/*  72 */       sortorder, page.intValue(), pagesize.intValue());
/*  73 */     model.addAttribute("p", p);
/*  74 */     response.setHeader("Cache-Control", "no-cache");
/*  75 */     response.setContentType("text/json;charset=UTF-8");
/*  76 */     return "extraFunc/forum/data";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:forum:delete"})
/*     */   @RequestMapping({"/forum/o_ajax_delete.do"})
/*     */   public void deleteForum(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/*  83 */     JSONObject json = new JSONObject();
/*  84 */     Forum[] beans = this.service.deleteByIds(ids);
/*  85 */     for (Forum bean : beans) {
/*  86 */       log.info("delete Forum id={}", bean.getId());
/*     */     }
/*  88 */     json.put("success", true);
/*  89 */     json.put("status", 1);
/*  90 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:forum:findbyid"})
/*     */   @RequestMapping({"/forum/o_ajax_find.do"})
/*     */   public void findForum(Integer id, HttpServletRequest request, HttpServletResponse response) throws JSONException
/*     */   {
/*  98 */     JSONObject json = new JSONObject();
/*  99 */     Forum bean = this.service.findById(id);
/* 100 */     json.put("name", bean.getName());
/* 101 */     json.put("keywords", bean.getKeywords());
/* 102 */     json.put("description", bean.getDescription());
/* 103 */     json.put("rule", bean.getRule());
/* 104 */     json.put("categoryId", bean.getCategory().getId());
/* 105 */     json.put("tplContent", bean.getTplContent());
/* 106 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.ForumAct
 * JD-Core Version:    0.6.1
 */
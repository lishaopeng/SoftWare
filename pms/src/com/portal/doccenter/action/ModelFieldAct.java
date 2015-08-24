/*     */ package com.portal.doccenter.action;
/*     */ 
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.portal.doccenter.entity.Model;
/*     */ import com.portal.doccenter.entity.ModelField;
/*     */ import com.portal.doccenter.service.ModelFieldService;
/*     */ import com.portal.doccenter.service.ModelService;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.shiro.authz.annotation.RequiresPermissions;
/*     */ import org.json.JSONException;
/*     */ import org.json.JSONObject;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Controller;
/*     */ import org.springframework.ui.ModelMap;
/*     */ import org.springframework.web.bind.annotation.RequestMapping;
/*     */ 
/*     */ @Controller
/*     */ public class ModelFieldAct
/*     */ {
/*  27 */   private static final Logger log = LoggerFactory.getLogger(ModelFieldAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private ModelService modelService;
/*     */ 
/*     */   @Autowired
/*     */   private ModelFieldService service;
/*     */ 
/*  33 */   @RequiresPermissions({"admin:field:list"})
/*     */   @RequestMapping({"/field/v_list.do"})
/*     */   public String list(Integer modelId, HttpServletRequest request, ModelMap model) { model.addAttribute("modelId", modelId);
/*  34 */     return "docCenter/config/model/field/list"; }
/*     */ 
/*     */   @RequiresPermissions({"admin:field:add"})
/*     */   @RequestMapping({"/field/v_add.do"})
/*     */   public String add(Integer modelId, ModelMap model) {
/*  40 */     Model m = this.modelService.findById(modelId);
/*  41 */     model.addAttribute("model", m);
/*  42 */     model.addAttribute("modelId", modelId);
/*  43 */     return "docCenter/config/model/field/add";
/*     */   }
/*     */   @RequiresPermissions({"admin:field:edit"})
/*     */   @RequestMapping({"/field/v_edit.do"})
/*     */   public String edit(Integer id, ModelMap model) {
/*  49 */     ModelField field = this.service.findById(id);
/*  50 */     model.addAttribute("field", field);
/*  51 */     return "docCenter/config/model/field/edit";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:field:save"})
/*     */   @RequestMapping({"/field/o_save.do"})
/*     */   public String save(ModelField bean, Integer modelId, HttpServletRequest request, ModelMap model) {
/*  58 */     bean = this.service.save(bean, modelId);
/*  59 */     log.info("update ModelItem id={}.", bean.getId());
/*  60 */     model.addAttribute("modelId", bean.getModel().getId());
/*  61 */     model.addAttribute("msg", "字段添加成功!");
/*  62 */     return add(modelId, model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:field:update"})
/*     */   @RequestMapping({"/field/o_update.do"})
/*     */   public String update(ModelField bean, HttpServletRequest request, ModelMap model) {
/*  69 */     bean = this.service.update(bean);
/*  70 */     log.info("update ModelItem id={}.", bean.getId());
/*  71 */     model.addAttribute("modelId", bean.getModel().getId());
/*  72 */     model.addAttribute("msg", "字段修改成功!");
/*  73 */     return edit(bean.getId(), model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/field/jsonData.do"})
/*     */   public String dataPageByJosn(Integer modelId, String sortname, String sortorder, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  81 */     List list = this.service.getList(modelId, true, sortname, 
/*  82 */       sortorder);
/*  83 */     model.addAttribute("list", list);
/*  84 */     response.setHeader("Cache-Control", "no-cache");
/*  85 */     response.setContentType("text/json;charset=UTF-8");
/*  86 */     return "docCenter/config/model/field/dataJson";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:field:delete"})
/*     */   @RequestMapping({"/field/o_ajax_delete.do"})
/*     */   public void deleteItem(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/*  93 */     JSONObject json = new JSONObject();
/*  94 */     ModelField[] beans = this.service.deleteByIds(ids);
/*  95 */     for (ModelField bean : beans) {
/*  96 */       log.info("delete Model id={}", bean.getId());
/*     */     }
/*  98 */     json.put("success", true);
/*  99 */     json.put("status", 1);
/* 100 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:field:priority"})
/*     */   @RequestMapping({"/field/o_priority.do"})
/*     */   public void itemPriority(Integer id, Integer priority, HttpServletRequest request, HttpServletResponse response)
/*     */     throws JSONException
/*     */   {
/* 109 */     JSONObject json = new JSONObject();
/* 110 */     this.service.updatePriority(id, priority);
/* 111 */     json.put("success", true);
/* 112 */     json.put("status", 1);
/* 113 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:field:single"})
/*     */   @RequestMapping({"/field/o_single.do"})
/*     */   public void itemSingle(Integer id, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 121 */     ModelField bean = this.service.findById(id);
/* 122 */     if (bean.getSingle().booleanValue()) {
/* 123 */       bean.setSingle(Boolean.valueOf(false));
/* 124 */       ResponseUtils.renderJson(response, "false");
/*     */     } else {
/* 126 */       bean.setSingle(Boolean.valueOf(true));
/* 127 */       ResponseUtils.renderJson(response, "true");
/*     */     }
/* 129 */     this.service.update(bean);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:field:show"})
/*     */   @RequestMapping({"/field/o_show.do"})
/*     */   public void itemShow(Integer id, HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 137 */     ModelField bean = this.service.findById(id);
/* 138 */     if (bean.getShow().booleanValue()) {
/* 139 */       bean.setShow(Boolean.valueOf(false));
/* 140 */       ResponseUtils.renderJson(response, "false");
/*     */     } else {
/* 142 */       bean.setShow(Boolean.valueOf(true));
/* 143 */       ResponseUtils.renderJson(response, "true");
/*     */     }
/* 145 */     this.service.update(bean);
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.ModelFieldAct
 * JD-Core Version:    0.6.1
 */
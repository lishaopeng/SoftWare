/*    */ package com.portal.extrafunc.action;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.extrafunc.entity.SurveyTheme;
/*    */ import com.portal.extrafunc.service.SurveyThemeService;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.shiro.authz.annotation.RequiresPermissions;
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ import org.slf4j.Logger;
/*    */ import org.slf4j.LoggerFactory;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class SurveyThemeAct
/*    */ {
/* 24 */   private static final Logger log = LoggerFactory.getLogger(SurveyThemeAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private SurveyThemeService service;
/*    */ 
/* 29 */   @RequiresPermissions({"admin:surveytheme:list"})
/*    */   @RequestMapping({"/surveytheme/v_list.do"})
/*    */   public String list(Integer naireId, ModelMap model) { model.addAttribute("naireId", naireId);
/* 30 */     return "extraFunc/questionnaire/surveytheme/list"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:surveytheme:add"})
/*    */   @RequestMapping({"/surveytheme/v_add.do"})
/*    */   public String add(Integer naireId, ModelMap model) {
/* 36 */     model.addAttribute("naireId", naireId);
/* 37 */     return "extraFunc/questionnaire/surveytheme/add";
/*    */   }
/*    */   @RequiresPermissions({"admin:surveytheme:edit"})
/*    */   @RequestMapping({"/surveytheme/v_edit.do"})
/*    */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/* 43 */     model.addAttribute("surveyTheme", this.service.findById(id));
/* 44 */     return "extraFunc/questionnaire/surveytheme/edit";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:surveytheme:save"})
/*    */   @RequestMapping({"/surveytheme/o_save.do"})
/*    */   public String save(SurveyTheme bean, Integer naireId, Integer showType1, Integer showType2, String[] names, Integer[] votes, Integer[] prioritys, HttpServletRequest request, ModelMap model)
/*    */   {
/* 52 */     bean = this.service.save(bean, naireId, showType1, showType2, names, votes, 
/* 53 */       prioritys);
/* 54 */     log.info("save SurveyTheme id={}", bean.getId());
/* 55 */     model.addAttribute("msg", "调查项添加成功!");
/* 56 */     return add(naireId, model);
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:surveytheme:update"})
/*    */   @RequestMapping({"/surveytheme/o_update.do"})
/*    */   public String update(SurveyTheme bean, Integer showType1, Integer showType2, String[] names, Integer[] votes, Integer[] prioritys, HttpServletRequest request, ModelMap model)
/*    */   {
/* 64 */     bean = this.service.update(bean, showType1, showType2, names, votes, 
/* 65 */       prioritys);
/* 66 */     log.info("update SurveyTheme id={}.", bean.getId());
/* 67 */     model.addAttribute("msg", "调查项修改成功!");
/* 68 */     return edit(bean.getId(), request, model);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/surveytheme/jsonData.do"})
/*    */   public String dataPageByJosn(Integer naireId, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 75 */     Page p = this.service.getPage(naireId, page.intValue(), pagesize.intValue());
/* 76 */     model.addAttribute("p", p);
/* 77 */     response.setHeader("Cache-Control", "no-cache");
/* 78 */     response.setContentType("text/json;charset=UTF-8");
/* 79 */     return "extraFunc/questionnaire/surveytheme/data";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:surveytheme:delete"})
/*    */   @RequestMapping({"/surveytheme/o_ajax_delete.do"})
/*    */   public void deleteSurveyTheme(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 86 */     JSONObject json = new JSONObject();
/* 87 */     SurveyTheme[] beans = this.service.deleteByIds(ids);
/* 88 */     for (SurveyTheme bean : beans) {
/* 89 */       log.info("delete SurveyTheme id={}", bean.getId());
/*    */     }
/* 91 */     json.put("success", true);
/* 92 */     json.put("status", 1);
/* 93 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.SurveyThemeAct
 * JD-Core Version:    0.6.1
 */
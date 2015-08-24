/*     */ package com.portal.extrafunc.action;
/*     */ 
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.portal.extrafunc.entity.Questionnaire;
/*     */ import com.portal.extrafunc.entity.SurveyTheme;
/*     */ import com.portal.extrafunc.service.QuestionnaireService;
/*     */ import com.portal.extrafunc.service.SurveyThemeService;
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
/*     */ public class QuestionnaireAct
/*     */ {
/*  30 */   private static final Logger log = LoggerFactory.getLogger(QuestionnaireAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private QuestionnaireService service;
/*     */ 
/*     */   @Autowired
/*     */   private SurveyThemeService themeService;
/*     */ 
/*  35 */   @RequiresPermissions({"admin:questionnaire:list"})
/*     */   @RequestMapping({"/questionnaire/v_list.do"})
/*     */   public String list() { return "extraFunc/questionnaire/list"; }
/*     */ 
/*     */   @RequiresPermissions({"admin:questionnaire:add"})
/*     */   @RequestMapping({"/questionnaire/v_add.do"})
/*     */   public String add(ModelMap model) {
/*  41 */     return "extraFunc/questionnaire/add";
/*     */   }
/*     */   @RequiresPermissions({"admin:questionnaire:edit"})
/*     */   @RequestMapping({"/questionnaire/v_edit.do"})
/*     */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/*  47 */     model.addAttribute("questionnaire", this.service.findById(id));
/*  48 */     return "extraFunc/questionnaire/edit";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:questionnaire:save"})
/*     */   @RequestMapping({"/questionnaire/o_save.do"})
/*     */   public String save(Questionnaire bean, HttpServletRequest request, ModelMap model) {
/*  55 */     Site site = ContextTools.getSite(request);
/*  56 */     bean = this.service.save(bean, site);
/*  57 */     log.info("save Questionnaire id={}", bean.getId());
/*  58 */     model.addAttribute("msg", "调查问卷添加成功!");
/*  59 */     return add(model);
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:questionnaire:update"})
/*     */   @RequestMapping({"/questionnaire/o_update.do"})
/*     */   public String update(Questionnaire bean, HttpServletRequest request, ModelMap model) {
/*  66 */     bean = this.service.update(bean);
/*  67 */     log.info("update Questionnaire id={}.", bean.getId());
/*  68 */     model.addAttribute("msg", "调查问卷修改成功!");
/*  69 */     return edit(bean.getId(), request, model);
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/questionnaire/jsonData.do"})
/*     */   public String dataPageByJosn(Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  76 */     Site site = ContextTools.getSite(request);
/*  77 */     Page p = this.service.getPage(site.getId(), false, page.intValue(), pagesize.intValue());
/*  78 */     model.addAttribute("p", p);
/*  79 */     response.setHeader("Cache-Control", "no-cache");
/*  80 */     response.setContentType("text/json;charset=UTF-8");
/*  81 */     return "extraFunc/questionnaire/data";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:questionnaire:delete"})
/*     */   @RequestMapping({"/questionnaire/o_ajax_delete.do"})
/*     */   public void deleteQuestionnaire(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/*  88 */     JSONObject json = new JSONObject();
/*  89 */     Questionnaire[] beans = this.service.deleteByIds(ids);
/*  90 */     for (Questionnaire bean : beans) {
/*  91 */       log.info("delete Questionnaire id={}", bean.getId());
/*     */     }
/*  93 */     json.put("success", true);
/*  94 */     json.put("status", 1);
/*  95 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:questionnaire:result"})
/*     */   @RequestMapping({"/questionnaire/v_result.do"})
/*     */   public String result(Integer id, HttpServletRequest request, ModelMap model) {
/* 102 */     List themeList = this.themeService.getList(id);
/* 103 */     model.addAttribute("question", this.service.findById(id));
/* 104 */     model.addAttribute("themeList", themeList);
/* 105 */     model.addAttribute("NORMAL", SurveyTheme.NORMAL);
/* 106 */     return "extraFunc/questionnaire/result";
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.QuestionnaireAct
 * JD-Core Version:    0.6.1
 */
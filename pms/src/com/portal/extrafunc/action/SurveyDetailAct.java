/*    */ package com.portal.extrafunc.action;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.extrafunc.entity.SurveyDetail;
/*    */ import com.portal.extrafunc.entity.SurveyTheme;
/*    */ import com.portal.extrafunc.service.SurveyDetailService;
/*    */ import com.portal.extrafunc.service.SurveyThemeService;
/*    */ import com.portal.sysmgr.utils.ContextTools;
/*    */ import com.portal.usermgr.entity.User;
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
/*    */ public class SurveyDetailAct
/*    */ {
/* 28 */   private static final Logger log = LoggerFactory.getLogger(SurveyDetailAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private SurveyDetailService service;
/*    */ 
/*    */   @Autowired
/*    */   private SurveyThemeService themeService;
/*    */ 
/* 33 */   @RequiresPermissions({"admin:surveydetail:list"})
/*    */   @RequestMapping({"/surveydetail/v_list.do"})
/*    */   public String list() { return "surveydetail/list"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:surveydetail:add"})
/*    */   @RequestMapping({"/surveydetail/v_add.do"})
/*    */   public String add(ModelMap model) {
/* 39 */     return "surveydetail/add";
/*    */   }
/*    */   @RequiresPermissions({"admin:surveydetail:edit"})
/*    */   @RequestMapping({"/surveydetail/v_edit.do"})
/*    */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/* 45 */     model.addAttribute("surveyDetail", this.service.findById(id));
/* 46 */     return "surveydetail/edit";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:surveydetail:edit"})
/*    */   @RequestMapping({"/surveydetail/o_save.do"})
/*    */   public String save(String content, Integer surveyId, HttpServletRequest request, ModelMap model) {
/* 53 */     User user = ContextTools.getUser(request);
/* 54 */     SurveyTheme st = this.themeService.findById(surveyId);
/* 55 */     this.service.save(content, st, user);
/* 56 */     return add(model);
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:surveydetail:update"})
/*    */   @RequestMapping({"/surveydetail/o_update.do"})
/*    */   public String update(SurveyDetail bean, HttpServletRequest request, ModelMap model) {
/* 63 */     bean = this.service.update(bean);
/* 64 */     log.info("update SurveyDetail id={}.", bean.getId());
/* 65 */     return edit(bean.getId(), request, model);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/surveydetail/jsonData.do"})
/*    */   public String dataPageByJosn(Integer surveyId, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 72 */     Page p = this.service.getPage(surveyId, page.intValue(), pagesize.intValue());
/* 73 */     model.addAttribute("p", p);
/* 74 */     response.setHeader("Cache-Control", "no-cache");
/* 75 */     response.setContentType("text/json;charset=UTF-8");
/* 76 */     return "surveydetail/data";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:surveydetail:delete"})
/*    */   @RequestMapping({"/surveydetail/o_ajax_delete.do"})
/*    */   public void deleteSurveyDetail(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 83 */     JSONObject json = new JSONObject();
/* 84 */     SurveyDetail[] beans = this.service.deleteByIds(ids);
/* 85 */     for (SurveyDetail bean : beans) {
/* 86 */       log.info("delete SurveyDetail id={}", bean.getId());
/*    */     }
/* 88 */     json.put("success", true);
/* 89 */     json.put("status", 1);
/* 90 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.SurveyDetailAct
 * JD-Core Version:    0.6.1
 */
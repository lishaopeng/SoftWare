/*    */ package com.portal.extrafunc.action;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.extrafunc.entity.QuestionDetail;
/*    */ import com.portal.extrafunc.service.QuestionDetailService;
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
/*    */ public class QuestionDetailAct
/*    */ {
/* 24 */   private static final Logger log = LoggerFactory.getLogger(QuestionDetailAct.class);
/*    */ 
/*    */   @Autowired
/*    */   private QuestionDetailService service;
/*    */ 
/* 29 */   @RequiresPermissions({"admin:questiondetail:list"})
/*    */   @RequestMapping({"/questiondetail/v_list.do"})
/*    */   public String list() { return "questiondetail/list"; }
/*    */ 
/*    */   @RequiresPermissions({"admin:questiondetail:add"})
/*    */   @RequestMapping({"/questiondetail/v_add.do"})
/*    */   public String add(ModelMap model) {
/* 35 */     return "questiondetail/add";
/*    */   }
/*    */   @RequiresPermissions({"admin:questiondetail:edit"})
/*    */   @RequestMapping({"/questiondetail/v_edit.do"})
/*    */   public String edit(Integer id, HttpServletRequest request, ModelMap model) {
/* 41 */     model.addAttribute("questionDetail", this.service.findById(id));
/* 42 */     return "questiondetail/edit";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:questiondetail:update"})
/*    */   @RequestMapping({"/questiondetail/o_update.do"})
/*    */   public String update(QuestionDetail bean, HttpServletRequest request, ModelMap model) {
/* 49 */     bean = this.service.update(bean);
/* 50 */     log.info("update QuestionDetail id={}.", bean.getId());
/* 51 */     return edit(bean.getId(), request, model);
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/questiondetail/jsonData.do"})
/*    */   public String dataPageByJosn(Integer questionId, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */   {
/* 58 */     Page p = this.service.getPage(questionId, page.intValue(), pagesize.intValue());
/* 59 */     model.addAttribute("p", p);
/* 60 */     response.setHeader("Cache-Control", "no-cache");
/* 61 */     response.setContentType("text/json;charset=UTF-8");
/* 62 */     return "questiondetail/data";
/*    */   }
/*    */ 
/*    */   @RequiresPermissions({"admin:questiondetail:delete"})
/*    */   @RequestMapping({"/questiondetail/o_ajax_delete.do"})
/*    */   public void deleteQuestionDetail(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 69 */     JSONObject json = new JSONObject();
/* 70 */     QuestionDetail[] beans = this.service.deleteByIds(ids);
/* 71 */     for (QuestionDetail bean : beans) {
/* 72 */       log.info("delete QuestionDetail id={}", bean.getId());
/*    */     }
/* 74 */     json.put("success", true);
/* 75 */     json.put("status", 1);
/* 76 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.QuestionDetailAct
 * JD-Core Version:    0.6.1
 */
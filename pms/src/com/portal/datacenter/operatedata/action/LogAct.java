/*     */ package com.portal.datacenter.operatedata.action;
/*     */ 
/*     */ import com.javapms.basic.utils.ResponseUtils;
/*     */ import com.portal.datacenter.operatedata.entity.Log;
/*     */ import com.portal.datacenter.operatedata.service.LogService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
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
/*     */ public class LogAct
/*     */ {
/*  25 */   private static final Logger log = LoggerFactory.getLogger(LogAct.class);
/*     */ 
/*     */   @Autowired
/*     */   private LogService manager;
/*     */ 
/*  30 */   @RequiresPermissions({"admin:log:list_operating"})
/*     */   @RequestMapping({"/log/v_list_operating.do"})
/*     */   public String listOperating() { return "dataCenter/logData/operaLog"; }
/*     */ 
/*     */   @RequiresPermissions({"admin:log:list_success"})
/*     */   @RequestMapping({"/log/v_list_success.do"})
/*     */   public String listLoginSuccess() {
/*  36 */     return "dataCenter/logData/sloginLog";
/*     */   }
/*     */   @RequiresPermissions({"admin:log:list_failure"})
/*     */   @RequestMapping({"/log/v_list_failure.do"})
/*     */   public String listLoginFailure() {
/*  42 */     return "dataCenter/logData/floginLog";
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/operating/jsonData.do"})
/*     */   public String operatingPageByJosn(String queryUsername, String queryTitle, String queryIp, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  50 */     Site site = ContextTools.getSite(request);
/*  51 */     Page p = this.manager.getPage(Integer.valueOf(3), site.getId(), 
/*  52 */       queryUsername, queryTitle, queryIp, page.intValue(), pagesize.intValue());
/*  53 */     model.addAttribute("p", p);
/*  54 */     response.setHeader("Cache-Control", "no-cache");
/*  55 */     response.setContentType("text/json;charset=UTF-8");
/*  56 */     return "dataCenter/logData/opdatatree";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:operating:delete"})
/*     */   @RequestMapping({"/operating/o_ajax_delete.do"})
/*     */   public void deleteOperating(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/*  63 */     JSONObject json = new JSONObject();
/*  64 */     Log[] beans = this.manager.deleteByIds(ids);
/*  65 */     for (Log bean : beans) {
/*  66 */       log.info("delete Log id={}", bean.getId());
/*     */     }
/*  68 */     json.put("success", true);
/*  69 */     json.put("status", 1);
/*  70 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:operating:clear"})
/*     */   @RequestMapping({"/operating/o_ajax_clear.do"})
/*     */   public void clearOperating(Integer days, HttpServletRequest request, HttpServletResponse response) throws JSONException
/*     */   {
/*  78 */     JSONObject json = new JSONObject();
/*  79 */     Site site = ContextTools.getSite(request);
/*  80 */     this.manager.deleteBatch(Integer.valueOf(3), site.getId(), days);
/*  81 */     json.put("success", true);
/*  82 */     json.put("status", 1);
/*  83 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/logsuccess/jsonData.do"})
/*     */   public String logsuccessPageByJosn(String queryUsername, String queryTitle, String queryIp, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/*  92 */     Page p = this.manager.getPage(Integer.valueOf(1), null, queryUsername, 
/*  93 */       queryTitle, queryIp, page.intValue(), pagesize.intValue());
/*  94 */     model.addAttribute("p", p);
/*  95 */     response.setHeader("Cache-Control", "no-cache");
/*  96 */     response.setContentType("text/json;charset=UTF-8");
/*  97 */     return "dataCenter/logData/sdatatree";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:logsuccess:delete"})
/*     */   @RequestMapping({"/logsuccess/o_ajax_delete.do"})
/*     */   public void deleteLogsuccess(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 104 */     JSONObject json = new JSONObject();
/* 105 */     Log[] beans = this.manager.deleteByIds(ids);
/* 106 */     for (Log bean : beans) {
/* 107 */       log.info("delete Log id={}", bean.getId());
/*     */     }
/* 109 */     json.put("success", true);
/* 110 */     json.put("status", 1);
/* 111 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:logsuccess:clear"})
/*     */   @RequestMapping({"/logsuccess/o_ajax_clear.do"})
/*     */   public void clearLogsuccess(Integer days, HttpServletRequest request, HttpServletResponse response) throws JSONException
/*     */   {
/* 119 */     JSONObject json = new JSONObject();
/* 120 */     if (days == null) {
/* 121 */       days = Integer.valueOf(0);
/*     */     }
/* 123 */     this.manager.deleteBatch(Integer.valueOf(1), null, days);
/* 124 */     json.put("success", true);
/* 125 */     json.put("status", 1);
/* 126 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequestMapping({"/logfailure/jsonData.do"})
/*     */   public String logfailurePageByJosn(String queryUsername, String queryTitle, String queryIp, Integer page, Integer pagesize, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*     */   {
/* 135 */     Page p = this.manager.getPage(Integer.valueOf(2), null, null, 
/* 136 */       queryTitle, queryIp, page.intValue(), pagesize.intValue());
/* 137 */     model.addAttribute("p", p);
/* 138 */     response.setHeader("Cache-Control", "no-cache");
/* 139 */     response.setContentType("text/json;charset=UTF-8");
/* 140 */     return "dataCenter/logData/fdatatree";
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:logfailure:delete"})
/*     */   @RequestMapping({"/logfailure/o_ajax_delete.do"})
/*     */   public void deleteLogfailure(Integer[] ids, HttpServletRequest request, HttpServletResponse response) throws JSONException {
/* 147 */     JSONObject json = new JSONObject();
/* 148 */     Log[] beans = this.manager.deleteByIds(ids);
/* 149 */     for (Log bean : beans) {
/* 150 */       log.info("delete Log id={}", bean.getId());
/*     */     }
/* 152 */     json.put("success", true);
/* 153 */     json.put("status", 1);
/* 154 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ 
/*     */   @RequiresPermissions({"admin:logfailure:clear"})
/*     */   @RequestMapping({"/logfailure/o_ajax_clear.do"})
/*     */   public void clearLogfailure(Integer days, HttpServletRequest request, HttpServletResponse response) throws JSONException
/*     */   {
/* 162 */     JSONObject json = new JSONObject();
/* 163 */     if (days == null) {
/* 164 */       days = Integer.valueOf(0);
/*     */     }
/* 166 */     this.manager.deleteBatch(Integer.valueOf(2), null, days);
/* 167 */     json.put("success", true);
/* 168 */     json.put("status", 1);
/* 169 */     ResponseUtils.renderJson(response, json.toString());
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.operatedata.action.LogAct
 * JD-Core Version:    0.6.1
 */
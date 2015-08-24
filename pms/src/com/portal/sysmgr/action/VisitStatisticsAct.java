/*    */ package com.portal.sysmgr.action;
/*    */ 
/*    */ import com.javapms.basic.utils.ResponseUtils;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.service.VisitStatisticsService;
/*    */ import com.portal.sysmgr.utils.ContextTools;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.json.JSONException;
/*    */ import org.json.JSONObject;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.ui.ModelMap;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class VisitStatisticsAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private VisitStatisticsService statisticsService;
/*    */ 
/*    */   @RequestMapping({"/views/jsonData.do"})
/*    */   public void dataPageByJosn(String datestr, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */     throws JSONException
/*    */   {
/* 24 */     Site site = ContextTools.getSite(request);
/* 25 */     Long[] views = new Long[24];
/* 26 */     for (int i = 0; i < 24; i++) {
/* 27 */       views[i] = Long.valueOf(this.statisticsService.getStatisticsByHour(site.getId(), Integer.valueOf(i)));
/*    */     }
/* 29 */     JSONObject json = new JSONObject();
/* 30 */     json.put("data", views);
/* 31 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ 
/*    */   @RequestMapping({"/views/monthData.do"})
/*    */   public void dataPageMonth(String datestr, HttpServletRequest request, HttpServletResponse response, ModelMap model)
/*    */     throws JSONException
/*    */   {
/* 38 */     Site site = ContextTools.getSite(request);
/* 39 */     Long[] views = new Long[24];
/* 40 */     for (int i = 0; i < 24; i++) {
/* 41 */       views[i] = Long.valueOf(this.statisticsService.getStatisticsByHour(site.getId(), Integer.valueOf(i)));
/*    */     }
/* 43 */     JSONObject json = new JSONObject();
/* 44 */     json.put("data", views);
/* 45 */     ResponseUtils.renderJson(response, json.toString());
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.action.VisitStatisticsAct
 * JD-Core Version:    0.6.1
 */
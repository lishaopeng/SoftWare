/*    */ package com.portal.sysmgr.action.fnt;
/*    */ 
/*    */ import com.javapms.basic.utils.ServicesUtils;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.service.VisitStatisticsService;
/*    */ import com.portal.sysmgr.utils.ContextTools;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Controller;
/*    */ import org.springframework.web.bind.annotation.RequestMapping;
/*    */ 
/*    */ @Controller
/*    */ public class VisitStatisticsAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private VisitStatisticsService statisticsService;
/*    */ 
/*    */   @RequestMapping({"/visitViews.jsp"})
/*    */   public void visitViews(HttpServletRequest request, HttpServletResponse response)
/*    */   {
/* 21 */     Site site = ContextTools.getSite(request);
/* 22 */     String url = ServicesUtils.getQueryParam(request, "url");
/* 23 */     String ip = ServicesUtils.getIpAddr(request);
/* 24 */     String cookie = ContextTools.getIdentityCookie(request, response);
/* 25 */     this.statisticsService.save(site, url, ip, cookie);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.action.fnt.VisitStatisticsAct
 * JD-Core Version:    0.6.1
 */
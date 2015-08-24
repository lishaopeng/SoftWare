/*    */ package com.portal.sysmgr.interceptor;
/*    */ 
/*    */ import com.javapms.basic.utils.StringBeanUtils;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.service.SiteService;
/*    */ import com.portal.sysmgr.utils.ContextTools;
/*    */ import com.portal.usermgr.entity.User;
/*    */ import com.portal.usermgr.service.UserService;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.http.HttpServletRequest;
/*    */ import javax.servlet.http.HttpServletResponse;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.apache.shiro.SecurityUtils;
/*    */ import org.apache.shiro.subject.Subject;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*    */ import org.springframework.web.util.UrlPathHelper;
/*    */ 
/*    */ public class ViewInterceptor extends HandlerInterceptorAdapter
/*    */ {
/*    */   private SiteService siteService;
/*    */   private UserService userService;
/*    */ 
/*    */   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
/*    */     throws ServletException
/*    */   {
/* 34 */     Site site = getSite(request, response);
/* 35 */     ContextTools.setSite(request, site);
/* 36 */     UrlPathHelper helper = new UrlPathHelper();
/* 37 */     String queryString = helper.getOriginatingQueryString(request);
/* 38 */     if ((!StringUtils.isBlank(queryString)) && 
/* 39 */       (StringBeanUtils.hasHtml(queryString))) {
/*    */       try {
/* 41 */         response.setContentType("text/html;charset=UTF-8");
/* 42 */         response.sendError(404);
/*    */       } catch (IOException e) {
/* 44 */         e.printStackTrace();
/*    */       }
/*    */     }
/*    */ 
/* 48 */     Subject subject = SecurityUtils.getSubject();
/* 49 */     if (subject.isAuthenticated()) {
/* 50 */       String username = (String)subject.getPrincipal();
/* 51 */       User user = this.userService.findByUsername(username);
/* 52 */       ContextTools.setUser(request, user);
/*    */     }
/* 54 */     return true;
/*    */   }
/*    */ 
/*    */   private Site getSite(HttpServletRequest request, HttpServletResponse response)
/*    */   {
/* 59 */     Site site = getByDomain(request);
/* 60 */     if (site == null) {
/* 61 */       site = getByDefault();
/*    */     }
/* 63 */     if (site == null) {
/* 64 */       throw new RuntimeException("cannot get site!");
/*    */     }
/* 66 */     return site;
/*    */   }
/*    */ 
/*    */   private Site getByDomain(HttpServletRequest request)
/*    */   {
/* 71 */     String domain = request.getServerName();
/* 72 */     if (!StringUtils.isBlank(domain)) {
/* 73 */       return this.siteService.findByDomain(domain, true);
/*    */     }
/* 75 */     return null;
/*    */   }
/*    */ 
/*    */   private Site getByDefault() {
/* 79 */     List list = this.siteService.getListFromCache();
/* 80 */     if (list.size() > 0) {
/* 81 */       return (Site)list.get(0);
/*    */     }
/* 83 */     return null;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setSiteService(SiteService siteService)
/*    */   {
/* 92 */     this.siteService = siteService;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setUserService(UserService userService) {
/* 97 */     this.userService = userService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.interceptor.ViewInterceptor
 * JD-Core Version:    0.6.1
 */
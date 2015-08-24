/*     */ package com.portal.sysmgr.interceptor;
/*     */ 
/*     */ import com.javapms.basic.utils.StringBeanUtils;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.service.SiteService;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.UserService;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.shiro.SecurityUtils;
/*     */ import org.apache.shiro.subject.Subject;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.web.servlet.ModelAndView;
/*     */ import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
/*     */ import org.springframework.web.util.UrlPathHelper;
/*     */ 
/*     */ public class AdminInterceptor extends HandlerInterceptorAdapter
/*     */ {
/*     */   private SiteService siteService;
/*     */   private UserService userService;
/*     */ 
/*     */   public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
/*     */     throws Exception
/*     */   {
/*  34 */     Site site = getSite(request, response);
/*  35 */     ContextTools.setSite(request, site);
/*  36 */     UrlPathHelper helper = new UrlPathHelper();
/*  37 */     String queryString = helper.getOriginatingQueryString(request);
/*  38 */     if ((!StringUtils.isBlank(queryString)) && 
/*  39 */       (StringBeanUtils.hasHtml(queryString))) {
/*     */       try {
/*  41 */         response.setContentType("text/html;charset=UTF-8");
/*  42 */         response.sendError(404);
/*     */       } catch (IOException e) {
/*  44 */         e.printStackTrace();
/*     */       }
/*     */     }
/*     */ 
/*  48 */     Subject subject = SecurityUtils.getSubject();
/*  49 */     if ((subject.isAuthenticated()) || (subject.isRemembered())) {
/*  50 */       String username = (String)subject.getPrincipal();
/*  51 */       User user = this.userService.findByUsername(username);
/*  52 */       ContextTools.setUser(request, user);
/*     */     }
/*  54 */     return true;
/*     */   }
/*     */ 
/*     */   public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav)
/*     */     throws Exception
/*     */   {
/*     */   }
/*     */ 
/*     */   private Site getSite(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/*  66 */     Site site = getByDomain(request);
/*  67 */     if (site == null) {
/*  68 */       site = getByDefault();
/*     */     }
/*  70 */     if (site == null) {
/*  71 */       throw new RuntimeException("cannot get site!");
/*     */     }
/*  73 */     return site;
/*     */   }
/*     */ 
/*     */   private Site getByDomain(HttpServletRequest request)
/*     */   {
/*  78 */     String domain = request.getServerName();
/*  79 */     if (!StringUtils.isBlank(domain)) {
/*  80 */       return this.siteService.findByDomain(domain, true);
/*     */     }
/*  82 */     return null;
/*     */   }
/*     */ 
/*     */   private Site getByDefault() {
/*  86 */     List list = this.siteService.getListFromCache();
/*  87 */     if (list.size() > 0) {
/*  88 */       return (Site)list.get(0);
/*     */     }
/*  90 */     return null;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setSiteService(SiteService siteService)
/*     */   {
/*  99 */     this.siteService = siteService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setUserService(UserService userService) {
/* 104 */     this.userService = userService;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.interceptor.AdminInterceptor
 * JD-Core Version:    0.6.1
 */
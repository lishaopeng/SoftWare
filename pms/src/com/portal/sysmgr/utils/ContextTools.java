/*     */ package com.portal.sysmgr.utils;
/*     */ 
/*     */ /*     */ import java.util.UUID;

/*     */ import javax.servlet.ServletRequest;
/*     */ import javax.servlet.http.Cookie;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;

/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.web.util.WebUtils;

import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.entity.SiteConfig;
/*     */ import com.portal.usermgr.entity.User;
/*     */ 
/*     */ public class ContextTools
/*     */ {
/*     */   public static final String USER_KEY = "_request_user_key";
/*     */   public static final String SITE_KEY = "_request_site_key";
/*     */   public static final String SITE_CONFIG_KEY = "_request_config_key";
/*     */   public static final String IDENTITY_COOKIE_NAME = "_javapms";
/* 127 */   private static ThreadLocal<Site> siteHolder = new ThreadLocal();
/*     */ 
/* 132 */   private static ThreadLocal<Integer> totalpageHolder = new ThreadLocal();
/*     */ 
/*     */   public static User getUser(ServletRequest request)
/*     */   {
/*  44 */     return (User)request.getAttribute("_request_user_key");
/*     */   }
/*     */ 
/*     */   public static Integer getUserId(ServletRequest request) {
/*  48 */     if (getUser(request) != null) {
/*  49 */       return getUser(request).getId();
/*     */     }
/*  51 */     return null;
/*     */   }
/*     */ 
/*     */   public static void setUser(ServletRequest request, User user)
/*     */   {
/*  61 */     request.setAttribute("_request_user_key", user);
/*     */   }
/*     */ 
/*     */   public static Site getSite(ServletRequest request)
/*     */   {
/*  71 */     return (Site)request.getAttribute("_request_site_key");
/*     */   }
/*     */ 
/*     */   public static Integer getSiteId(ServletRequest request) {
/*  75 */     return getSite(request).getId();
/*     */   }
/*     */ 
/*     */   public static SiteConfig getSiteConfig(ServletRequest request) {
/*  79 */     return (SiteConfig)request.getAttribute("_request_config_key");
/*     */   }
/*     */ 
/*     */   public static void setSite(ServletRequest request, Site site)
/*     */   {
/*  89 */     request.setAttribute("_request_site_key", site);
/*     */   }
/*     */ 
/*     */   public static void setSiteConfig(ServletRequest request, SiteConfig siteConfig)
/*     */   {
/* 100 */     request.setAttribute("_request_config_key", siteConfig);
/*     */   }
/*     */ 
/*     */   public static String getIdentityCookie(HttpServletRequest request, HttpServletResponse response)
/*     */   {
/* 106 */     Cookie cookie = WebUtils.getCookie(request, "_javapms");
/*     */     String value;
/* 107 */     if ((cookie != null) && (StringUtils.isNotBlank(cookie.getValue()))) {
/* 108 */       value = cookie.getValue();
/*     */     } else {
/* 110 */       value = UUID.randomUUID().toString();
/* 111 */       value = StringUtils.remove(value, '-');
/* 112 */       cookie = new Cookie("_javapms", value);
/* 113 */       String ctx = request.getContextPath();
/* 114 */       if (StringUtils.isBlank(ctx)) {
/* 115 */         ctx = "/";
/*     */       }
/* 117 */       cookie.setPath(ctx);
/* 118 */       cookie.setMaxAge(2147483647);
/* 119 */       response.addCookie(cookie);
/*     */     }
/* 121 */     return value;
/*     */   }
/*     */ 
/*     */   public static void setSite(Site site)
/*     */   {
/* 135 */     siteHolder.set(site);
/*     */   }
/*     */ 
/*     */   public static Site getSite() {
/* 139 */     return siteHolder.get();
/*     */   }
/*     */ 
/*     */   public static void resetSite() {
/* 143 */     siteHolder.remove();
/*     */   }
/*     */ 
/*     */   public static void setTotalPages(Integer totalPages) {
/* 147 */     totalpageHolder.set(totalPages);
/*     */   }
/*     */ 
/*     */   public static Integer getTotalPages() {
/* 151 */     return totalpageHolder.get();
/*     */   }
/*     */ 
/*     */   public static void resetTotalPages() {
/* 155 */     totalpageHolder.remove();
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.utils.ContextTools
 * JD-Core Version:    0.6.1
 */
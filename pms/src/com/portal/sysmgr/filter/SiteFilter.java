/*    */ package com.portal.sysmgr.filter;
/*    */ 
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.service.SiteService;
/*    */ import com.portal.sysmgr.utils.ContextTools;
/*    */ import java.io.IOException;
/*    */ import java.util.List;
/*    */ import javax.servlet.Filter;
/*    */ import javax.servlet.FilterChain;
/*    */ import javax.servlet.FilterConfig;
/*    */ import javax.servlet.ServletException;
/*    */ import javax.servlet.ServletRequest;
/*    */ import javax.servlet.ServletResponse;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class SiteFilter
/*    */   implements Filter
/*    */ {
/*    */   private SiteService siteService;
/*    */ 
/*    */   public void destroy()
/*    */   {
/*    */   }
/*    */ 
/*    */   public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
/*    */     throws IOException, ServletException
/*    */   {
/* 41 */     Site site = getSite(request);
/* 42 */     ContextTools.setSite(site);
/* 43 */     ContextTools.setSite(request, site);
/* 44 */     chain.doFilter(request, response);
/* 45 */     ContextTools.resetSite();
/*    */   }
/*    */ 
/*    */   public void init(FilterConfig fConfig)
/*    */     throws ServletException
/*    */   {
/*    */   }
/*    */ 
/*    */   private Site getSite(ServletRequest request)
/*    */   {
/* 56 */     Site site = getByDomain(request);
/* 57 */     if (site == null) {
/* 58 */       site = getByDefault();
/*    */     }
/* 60 */     if (site == null) {
/* 61 */       throw new RuntimeException("site not found!");
/*    */     }
/* 63 */     return site;
/*    */   }
/*    */ 
/*    */   private Site getByDomain(ServletRequest request)
/*    */   {
/* 68 */     String domain = request.getServerName();
/* 69 */     if (!StringUtils.isBlank(domain)) {
/* 70 */       return this.siteService.findByDomain(domain, true);
/*    */     }
/* 72 */     return null;
/*    */   }
/*    */ 
/*    */   private Site getByDefault() {
/* 76 */     List list = this.siteService.getListFromCache();
/* 77 */     if (list.size() > 0) {
/* 78 */       return (Site)list.get(0);
/*    */     }
/* 80 */     return null;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setSiteService(SiteService siteService)
/*    */   {
/* 88 */     this.siteService = siteService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.filter.SiteFilter
 * JD-Core Version:    0.6.1
 */
/*    */ package com.portal.sysmgr.listener;
/*    */ 
/*    */ import com.portal.doccenter.service.ArticleService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.event.DelDepartEvent;
/*    */ import com.portal.sysmgr.event.DelRoleEvent;
/*    */ import com.portal.sysmgr.event.DelSiteEvent;
/*    */ import com.portal.sysmgr.event.DelUserEvent;
/*    */ import com.portal.usermgr.entity.Depart;
/*    */ import com.portal.usermgr.entity.Role;
/*    */ import com.portal.usermgr.entity.User;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ import org.springframework.context.event.SmartApplicationListener;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class DelArticleListener
/*    */   implements SmartApplicationListener
/*    */ {
/*    */   private ArticleService articleService;
/*    */ 
/*    */   public void onApplicationEvent(ApplicationEvent baseEvent)
/*    */   {
/* 25 */     if ((baseEvent instanceof DelSiteEvent)) {
/* 26 */       DelSiteEvent event = (DelSiteEvent)baseEvent;
/* 27 */       Site site = event.getSite();
/* 28 */       this.articleService.delDocBySite(site.getId());
/*    */     }
/* 30 */     if ((baseEvent instanceof DelUserEvent)) {
/* 31 */       DelUserEvent event = (DelUserEvent)baseEvent;
/* 32 */       User user = event.getUser();
/* 33 */       this.articleService.delDocByInputUser(user.getId());
/* 34 */       this.articleService.updateDocByCheckUser(user.getId());
/*    */     }
/* 36 */     if ((baseEvent instanceof DelRoleEvent)) {
/* 37 */       DelRoleEvent event = (DelRoleEvent)baseEvent;
/* 38 */       Role role = event.getRole();
/* 39 */       this.articleService.updateDocByRole(role.getId());
/*    */     }
/* 41 */     if ((baseEvent instanceof DelDepartEvent)) {
/* 42 */       DelDepartEvent event = (DelDepartEvent)baseEvent;
/* 43 */       Depart depart = event.getDepart();
/* 44 */       this.articleService.updateDocByInputDepart(depart.getId());
/*    */     }
/*    */   }
/*    */ 
/*    */   public int getOrder()
/*    */   {
/* 50 */     return 2;
/*    */   }
/*    */ 
/*    */   public boolean supportsEventType(Class<? extends ApplicationEvent> arg0)
/*    */   {
/* 55 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean supportsSourceType(Class<?> arg0)
/*    */   {
/* 60 */     return false;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setArticleService(ArticleService articleService)
/*    */   {
/* 67 */     this.articleService = articleService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.listener.DelArticleListener
 * JD-Core Version:    0.6.1
 */
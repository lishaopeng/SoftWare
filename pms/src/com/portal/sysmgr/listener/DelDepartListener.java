/*    */ package com.portal.sysmgr.listener;
/*    */ 
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.event.DelSiteEvent;
/*    */ import com.portal.usermgr.service.DepartService;
/*    */ import java.io.PrintStream;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ import org.springframework.context.event.SmartApplicationListener;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class DelDepartListener
/*    */   implements SmartApplicationListener
/*    */ {
/*    */   private DepartService departService;
/*    */ 
/*    */   public void onApplicationEvent(ApplicationEvent baseEvent)
/*    */   {
/* 19 */     System.out.println("-----------删除文章..部门");
/* 20 */     if ((baseEvent instanceof DelSiteEvent)) {
/* 21 */       DelSiteEvent event = (DelSiteEvent)baseEvent;
/* 22 */       Site site = event.getSite();
/* 23 */       this.departService.deleteBySiteId(site.getId());
/*    */     }
/*    */   }
/*    */ 
/*    */   public int getOrder()
/*    */   {
/* 29 */     return 0;
/*    */   }
/*    */ 
/*    */   public boolean supportsEventType(Class<? extends ApplicationEvent> arg0)
/*    */   {
/* 34 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean supportsSourceType(Class<?> arg0)
/*    */   {
/* 39 */     return false;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDepartService(DepartService departService)
/*    */   {
/* 46 */     this.departService = departService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.listener.DelDepartListener
 * JD-Core Version:    0.6.1
 */
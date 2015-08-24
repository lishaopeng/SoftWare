/*    */ package com.portal.sysmgr.listener;
/*    */ 
/*    */ import com.portal.extrafunc.service.CategoryService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.event.DelSiteEvent;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ import org.springframework.context.event.SmartApplicationListener;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class DelCategoryListener
/*    */   implements SmartApplicationListener
/*    */ {
/*    */   private CategoryService categoryService;
/*    */ 
/*    */   public void onApplicationEvent(ApplicationEvent baseEvent)
/*    */   {
/* 19 */     if ((baseEvent instanceof DelSiteEvent)) {
/* 20 */       DelSiteEvent event = (DelSiteEvent)baseEvent;
/* 21 */       Site site = event.getSite();
/* 22 */       this.categoryService.deleteBySiteId(site.getId());
/*    */     }
/*    */   }
/*    */ 
/*    */   public int getOrder()
/*    */   {
/* 28 */     return 0;
/*    */   }
/*    */ 
/*    */   public boolean supportsEventType(Class<? extends ApplicationEvent> arg0)
/*    */   {
/* 33 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean supportsSourceType(Class<?> arg0)
/*    */   {
/* 38 */     return false;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setCategoryService(CategoryService categoryService)
/*    */   {
/* 45 */     this.categoryService = categoryService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.listener.DelCategoryListener
 * JD-Core Version:    0.6.1
 */
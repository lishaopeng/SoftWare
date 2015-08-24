/*    */ package com.portal.sysmgr.listener;
/*    */ 
/*    */ import com.portal.extrafunc.service.AdvertSlotService;
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
/*    */ public class DelAdvertSlotListener
/*    */   implements SmartApplicationListener
/*    */ {
/*    */   private AdvertSlotService slotService;
/*    */ 
/*    */   public void onApplicationEvent(ApplicationEvent baseEvent)
/*    */   {
/* 19 */     if ((baseEvent instanceof DelSiteEvent)) {
/* 20 */       DelSiteEvent event = (DelSiteEvent)baseEvent;
/* 21 */       Site site = event.getSite();
/* 22 */       this.slotService.deleteBySiteId(site.getId());
/*    */     }
/*    */   }
/*    */ 
/*    */   public int getOrder()
/*    */   {
/* 28 */     return 2;
/*    */   }
/*    */ 
/*    */   public boolean supportsEventType(Class<? extends ApplicationEvent> evenType)
/*    */   {
/* 33 */     if (evenType == DelSiteEvent.class) {
/* 34 */       return true;
/*    */     }
/* 36 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean supportsSourceType(Class<?> sourceType)
/*    */   {
/* 41 */     if (sourceType == Site.class) {
/* 42 */       return true;
/*    */     }
/* 44 */     return false;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setSlotService(AdvertSlotService slotService)
/*    */   {
/* 51 */     this.slotService = slotService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.listener.DelAdvertSlotListener
 * JD-Core Version:    0.6.1
 */
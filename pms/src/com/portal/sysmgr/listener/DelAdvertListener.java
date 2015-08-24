/*    */ package com.portal.sysmgr.listener;
/*    */ 
/*    */ import com.portal.extrafunc.entity.AdvertSlot;
/*    */ import com.portal.extrafunc.service.AdvertService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.event.DelAdvertSlotEvent;
/*    */ import com.portal.sysmgr.event.DelSiteEvent;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ import org.springframework.context.event.SmartApplicationListener;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class DelAdvertListener
/*    */   implements SmartApplicationListener
/*    */ {
/*    */   private AdvertService advertService;
/*    */ 
/*    */   public void onApplicationEvent(ApplicationEvent baseEvent)
/*    */   {
/* 21 */     if ((baseEvent instanceof DelSiteEvent)) {
/* 22 */       DelSiteEvent event = (DelSiteEvent)baseEvent;
/* 23 */       Site site = event.getSite();
/* 24 */       this.advertService.deleteBySiteId(site.getId());
/*    */     }
/* 26 */     if ((baseEvent instanceof DelAdvertSlotEvent)) {
/* 27 */       DelAdvertSlotEvent event = (DelAdvertSlotEvent)baseEvent;
/* 28 */       AdvertSlot slot = event.getAdvertSlot();
/* 29 */       this.advertService.deleteBySlotId(slot.getId());
/*    */     }
/*    */   }
/*    */ 
/*    */   public int getOrder()
/*    */   {
/* 35 */     return 1;
/*    */   }
/*    */ 
/*    */   public boolean supportsEventType(Class<? extends ApplicationEvent> evenType)
/*    */   {
/* 40 */     if (evenType == DelSiteEvent.class) {
/* 41 */       return true;
/*    */     }
/* 43 */     if (evenType == DelAdvertSlotEvent.class) {
/* 44 */       return true;
/*    */     }
/* 46 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean supportsSourceType(Class<?> sourceType)
/*    */   {
/* 51 */     if (sourceType == Site.class) {
/* 52 */       return true;
/*    */     }
/* 54 */     if (sourceType == AdvertSlot.class) {
/* 55 */       return true;
/*    */     }
/* 57 */     return false;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setAdvertService(AdvertService advertService)
/*    */   {
/* 64 */     this.advertService = advertService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.listener.DelAdvertListener
 * JD-Core Version:    0.6.1
 */
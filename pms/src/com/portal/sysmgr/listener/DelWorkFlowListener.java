/*    */ package com.portal.sysmgr.listener;
/*    */ 
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ import org.springframework.context.event.SmartApplicationListener;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class DelWorkFlowListener
/*    */   implements SmartApplicationListener
/*    */ {
/*    */   public void onApplicationEvent(ApplicationEvent arg0)
/*    */   {
/*    */   }
/*    */ 
/*    */   public int getOrder()
/*    */   {
/* 21 */     return 0;
/*    */   }
/*    */ 
/*    */   public boolean supportsEventType(Class<? extends ApplicationEvent> arg0)
/*    */   {
/* 27 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean supportsSourceType(Class<?> arg0)
/*    */   {
/* 33 */     return false;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.listener.DelWorkFlowListener
 * JD-Core Version:    0.6.1
 */
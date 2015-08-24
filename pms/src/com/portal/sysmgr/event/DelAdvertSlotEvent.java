/*    */ package com.portal.sysmgr.event;
/*    */ 
/*    */ import com.portal.extrafunc.entity.AdvertSlot;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ 
/*    */ public class DelAdvertSlotEvent extends ApplicationEvent
/*    */ {
/*    */   public DelAdvertSlotEvent(AdvertSlot slot)
/*    */   {
/* 18 */     super(slot);
/*    */   }
/*    */ 
/*    */   public AdvertSlot getAdvertSlot() {
/* 22 */     return (AdvertSlot)super.getSource();
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.event.DelAdvertSlotEvent
 * JD-Core Version:    0.6.1
 */
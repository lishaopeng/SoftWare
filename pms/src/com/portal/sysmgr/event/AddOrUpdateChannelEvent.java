/*    */ package com.portal.sysmgr.event;
/*    */ 
/*    */ import com.portal.doccenter.entity.Channel;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ 
/*    */ public class AddOrUpdateChannelEvent extends ApplicationEvent
/*    */ {
/*    */   public AddOrUpdateChannelEvent(Channel c)
/*    */   {
/* 18 */     super(c);
/*    */   }
/*    */ 
/*    */   public Channel getChannel() {
/* 22 */     return (Channel)super.getSource();
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.event.AddOrUpdateChannelEvent
 * JD-Core Version:    0.6.1
 */
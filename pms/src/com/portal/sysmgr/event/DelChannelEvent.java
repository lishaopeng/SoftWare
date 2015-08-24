/*    */ package com.portal.sysmgr.event;
/*    */ 
/*    */ import com.portal.doccenter.entity.Channel;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ 
/*    */ public class DelChannelEvent extends ApplicationEvent
/*    */ {
/*    */   public DelChannelEvent(Channel c)
/*    */   {
/* 18 */     super(c);
/*    */   }
/*    */ 
/*    */   public Channel getChannel() {
/* 22 */     return (Channel)super.getSource();
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.event.DelChannelEvent
 * JD-Core Version:    0.6.1
 */
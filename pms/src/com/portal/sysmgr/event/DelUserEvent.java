/*    */ package com.portal.sysmgr.event;
/*    */ 
/*    */ import com.portal.usermgr.entity.User;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ 
/*    */ public class DelUserEvent extends ApplicationEvent
/*    */ {
/*    */   public DelUserEvent(User user)
/*    */   {
/* 18 */     super(user);
/*    */   }
/*    */ 
/*    */   public User getUser() {
/* 22 */     return (User)super.getSource();
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.event.DelUserEvent
 * JD-Core Version:    0.6.1
 */
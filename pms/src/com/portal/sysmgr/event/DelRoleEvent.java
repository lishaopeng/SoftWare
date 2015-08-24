/*    */ package com.portal.sysmgr.event;
/*    */ 
/*    */ import com.portal.usermgr.entity.Role;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ 
/*    */ public class DelRoleEvent extends ApplicationEvent
/*    */ {
/*    */   public DelRoleEvent(Role role)
/*    */   {
/* 18 */     super(role);
/*    */   }
/*    */ 
/*    */   public Role getRole() {
/* 22 */     return (Role)super.getSource();
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.event.DelRoleEvent
 * JD-Core Version:    0.6.1
 */
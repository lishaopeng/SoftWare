/*    */ package com.portal.sysmgr.event;
/*    */ 
/*    */ import com.portal.usermgr.entity.Depart;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ 
/*    */ public class DelDepartEvent extends ApplicationEvent
/*    */ {
/*    */   public DelDepartEvent(Depart depart)
/*    */   {
/* 18 */     super(depart);
/*    */   }
/*    */ 
/*    */   public Depart getDepart() {
/* 22 */     return (Depart)super.getSource();
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.event.DelDepartEvent
 * JD-Core Version:    0.6.1
 */
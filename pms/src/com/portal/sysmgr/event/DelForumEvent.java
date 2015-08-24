/*    */ package com.portal.sysmgr.event;
/*    */ 
/*    */ import com.portal.extrafunc.entity.Forum;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ 
/*    */ public class DelForumEvent extends ApplicationEvent
/*    */ {
/*    */   public DelForumEvent(Forum forum)
/*    */   {
/* 18 */     super(forum);
/*    */   }
/*    */ 
/*    */   public Forum getForum() {
/* 22 */     return (Forum)super.getSource();
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.event.DelForumEvent
 * JD-Core Version:    0.6.1
 */
/*    */ package com.portal.sysmgr.event;
/*    */ 
/*    */ import com.portal.extrafunc.entity.Theme;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ 
/*    */ public class DelThemeEvent extends ApplicationEvent
/*    */ {
/*    */   public DelThemeEvent(Theme theme)
/*    */   {
/* 18 */     super(theme);
/*    */   }
/*    */ 
/*    */   public Theme getTheme() {
/* 22 */     return (Theme)super.getSource();
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.event.DelThemeEvent
 * JD-Core Version:    0.6.1
 */
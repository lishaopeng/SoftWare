/*    */ package com.portal.extrafunc.action.fnt;
/*    */ 
/*    */ import com.portal.extrafunc.service.ThemeService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class ThemeJobAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private ThemeService themeService;
/*    */ 
/*    */   public void themeStatusCheck()
/*    */   {
/* 17 */     this.themeService.themeTopCheck();
/* 18 */     this.themeService.themeLightCheck();
/* 19 */     this.themeService.themeLockCheck();
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.fnt.ThemeJobAct
 * JD-Core Version:    0.6.1
 */
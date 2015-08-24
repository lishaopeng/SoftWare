/*    */ package com.portal.extrafunc.action.fnt;
/*    */ 
/*    */ import com.portal.extrafunc.action.cache.ThemeStatisCache;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class ThemeStatisAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private ThemeStatisCache statisCache;
/*    */ 
/*    */   public void statisToDb()
/*    */   {
/* 17 */     this.statisCache.statisToDB();
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.fnt.ThemeStatisAct
 * JD-Core Version:    0.6.1
 */
/*    */ package com.portal.extrafunc.action.fnt;
/*    */ 
/*    */ import com.portal.extrafunc.action.cache.PostsCheckCache;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class PostsCheckAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private PostsCheckCache checkCache;
/*    */ 
/*    */   public void refreshCheck()
/*    */   {
/* 17 */     this.checkCache.refreshCheck();
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.fnt.PostsCheckAct
 * JD-Core Version:    0.6.1
 */
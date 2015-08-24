/*    */ package com.portal.doccenter.action.fnt;
/*    */ 
/*    */ import com.portal.doccenter.action.fnt.cache.DocViewsCountCache;
/*    */ import com.portal.extrafunc.action.cache.CommentUpCache;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class DocStatisAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private DocViewsCountCache viewsCountCache;
/*    */ 
/*    */   @Autowired
/*    */   private CommentUpCache commentUpCache;
/*    */ 
/*    */   public void docViewsCount()
/*    */   {
/* 18 */     this.viewsCountCache.viewsToDB();
/*    */   }
/*    */ 
/*    */   public void upsCount() {
/* 22 */     this.commentUpCache.upsToDB();
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.fnt.DocStatisAct
 * JD-Core Version:    0.6.1
 */
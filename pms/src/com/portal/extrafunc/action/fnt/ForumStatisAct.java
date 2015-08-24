/*    */ package com.portal.extrafunc.action.fnt;
/*    */ 
/*    */ import com.portal.extrafunc.action.cache.ForumCache;
/*    */ import com.portal.extrafunc.action.cache.ForumStatisCache;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ public class ForumStatisAct
/*    */ {
/*    */ 
/*    */   @Autowired
/*    */   private ForumStatisCache statisCache;
/*    */ 
/*    */   @Autowired
/*    */   private ForumCache forumCache;
/*    */ 
/*    */   public void statisToDb()
/*    */   {
/* 18 */     this.statisCache.statisToDB();
/*    */   }
/*    */ 
/*    */   public void statisOneday() {
/* 22 */     this.statisCache.statisOneday();
/*    */   }
/*    */ 
/*    */   public void forumToDb() {
/* 26 */     this.forumCache.statisToDB();
/*    */   }
/*    */ 
/*    */   public void forumOneday() {
/* 30 */     this.forumCache.statisOneday();
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.fnt.ForumStatisAct
 * JD-Core Version:    0.6.1
 */
/*    */ package com.portal.sysmgr.event;
/*    */ 
/*    */ import com.portal.doccenter.entity.Article;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ 
/*    */ public class DelArticleEvent extends ApplicationEvent
/*    */ {
/*    */   private int type;
/*    */ 
/*    */   public DelArticleEvent(Article a, Integer type)
/*    */   {
/* 20 */     super(a);
/* 21 */     this.type = type.intValue();
/*    */   }
/*    */ 
/*    */   public Article getArticle() {
/* 25 */     return (Article)super.getSource();
/*    */   }
/*    */ 
/*    */   public Integer getType() {
/* 29 */     return Integer.valueOf(this.type);
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.event.DelArticleEvent
 * JD-Core Version:    0.6.1
 */
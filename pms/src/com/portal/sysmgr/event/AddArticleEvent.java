/*    */ package com.portal.sysmgr.event;
/*    */ 
/*    */ import com.portal.doccenter.entity.Article;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ 
/*    */ public class AddArticleEvent extends ApplicationEvent
/*    */ {
/*    */   public AddArticleEvent(Article a)
/*    */   {
/* 18 */     super(a);
/*    */   }
/*    */ 
/*    */   public Article getArticle() {
/* 22 */     return (Article)super.getSource();
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.event.AddArticleEvent
 * JD-Core Version:    0.6.1
 */
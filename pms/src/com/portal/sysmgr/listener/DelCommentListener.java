/*    */ package com.portal.sysmgr.listener;
/*    */ 
/*    */ import com.portal.doccenter.entity.Article;
/*    */ import com.portal.doccenter.entity.Channel;
/*    */ import com.portal.extrafunc.service.CommentService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.event.DelArticleEvent;
/*    */ import com.portal.sysmgr.event.DelSiteEvent;
/*    */ import com.portal.sysmgr.event.DelUserEvent;
/*    */ import com.portal.sysmgr.event.EmptyArticleEvent;
/*    */ import com.portal.usermgr.entity.User;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ import org.springframework.context.event.SmartApplicationListener;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class DelCommentListener
/*    */   implements SmartApplicationListener
/*    */ {
/*    */   private CommentService commentService;
/*    */ 
/*    */   public void onApplicationEvent(ApplicationEvent baseEvent)
/*    */   {
/* 25 */     if ((baseEvent instanceof DelUserEvent)) {
/* 26 */       DelUserEvent event = (DelUserEvent)baseEvent;
/* 27 */       User user = event.getUser();
/* 28 */       this.commentService.deleteByUserId(user.getId());
/*    */     }
/* 30 */     if ((baseEvent instanceof DelArticleEvent)) {
/* 31 */       DelArticleEvent event = (DelArticleEvent)baseEvent;
/* 32 */       Integer type = event.getType();
/* 33 */       if (type.intValue() == 0) {
/* 34 */         Article a = event.getArticle();
/* 35 */         this.commentService.deleteByDocId(a.getId());
/*    */       }
/*    */     }
/* 38 */     if ((baseEvent instanceof DelSiteEvent)) {
/* 39 */       DelSiteEvent event = (DelSiteEvent)baseEvent;
/* 40 */       Site site = event.getSite();
/* 41 */       this.commentService.deleteBySiteId(site.getId());
/*    */     }
/* 43 */     if ((baseEvent instanceof EmptyArticleEvent)) {
/* 44 */       EmptyArticleEvent event = (EmptyArticleEvent)baseEvent;
/* 45 */       Channel c = event.getChannel();
/* 46 */       this.commentService.deleteByTreeNumber(c.getNumber());
/*    */     }
/*    */   }
/*    */ 
/*    */   public int getOrder()
/*    */   {
/* 52 */     return 1;
/*    */   }
/*    */ 
/*    */   public boolean supportsEventType(Class<? extends ApplicationEvent> evenType)
/*    */   {
/* 57 */     if (evenType == DelSiteEvent.class) {
/* 58 */       return true;
/*    */     }
/* 60 */     if (evenType == DelUserEvent.class) {
/* 61 */       return true;
/*    */     }
/* 63 */     if (evenType == DelArticleEvent.class) {
/* 64 */       return true;
/*    */     }
/* 66 */     if (evenType == EmptyArticleEvent.class) {
/* 67 */       return true;
/*    */     }
/* 69 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean supportsSourceType(Class<?> sourceType)
/*    */   {
/* 74 */     if (sourceType == Site.class) {
/* 75 */       return true;
/*    */     }
/* 77 */     if (sourceType == User.class) {
/* 78 */       return true;
/*    */     }
/* 80 */     if (sourceType == Article.class) {
/* 81 */       return true;
/*    */     }
/* 83 */     if (sourceType == Channel.class) {
/* 84 */       return true;
/*    */     }
/* 86 */     return false;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setCommentService(CommentService commentService)
/*    */   {
/* 93 */     this.commentService = commentService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.listener.DelCommentListener
 * JD-Core Version:    0.6.1
 */
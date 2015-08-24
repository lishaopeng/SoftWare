/*     */ package com.portal.sysmgr.staticpage;
/*     */ 
/*     */ import com.portal.doccenter.entity.Article;
/*     */ import com.portal.doccenter.entity.Channel;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.event.AddArticleEvent;
/*     */ import com.portal.sysmgr.event.AddOrUpdateChannelEvent;
/*     */ import com.portal.sysmgr.event.DelArticleEvent;
/*     */ import com.portal.sysmgr.event.DelChannelEvent;
/*     */ import com.portal.sysmgr.event.EmptyArticleEvent;
/*     */ import com.portal.sysmgr.event.UpdateArticleEvent;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.context.ApplicationEvent;
/*     */ import org.springframework.context.event.SmartApplicationListener;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class StaticThreadImpl
/*     */   implements SmartApplicationListener
/*     */ {
/*     */   private StaticPageService staticPageService;
/*     */ 
/*     */   public void onApplicationEvent(ApplicationEvent baseEvent)
/*     */   {
/*  33 */     if ((baseEvent instanceof AddArticleEvent)) {
/*  34 */       Article a = ((AddArticleEvent)baseEvent).getArticle();
/*  35 */       a.getSite().initTime();
/*  36 */       this.staticPageService.staticArticle(a);
/*  37 */       this.staticPageService.staticIndex(a.getSite());
/*     */     }
/*  39 */     if ((baseEvent instanceof UpdateArticleEvent)) {
/*  40 */       UpdateArticleEvent event = (UpdateArticleEvent)baseEvent;
/*  41 */       Article a = event.getArticle();
/*  42 */       a.getSite().initTime();
/*  43 */       this.staticPageService.staticArticle(a);
/*  44 */       this.staticPageService.staticIndex(a.getSite());
/*     */     }
/*  46 */     if ((baseEvent instanceof DelArticleEvent)) {
/*  47 */       DelArticleEvent event = (DelArticleEvent)baseEvent;
/*  48 */       Article a = event.getArticle();
/*  49 */       a.getSite().initTime();
/*  50 */       this.staticPageService.deleteStaticArticle(a);
/*  51 */       this.staticPageService.staticIndex(a.getSite());
/*     */     }
/*  53 */     if ((baseEvent instanceof EmptyArticleEvent)) {
/*  54 */       EmptyArticleEvent event = (EmptyArticleEvent)baseEvent;
/*  55 */       Channel c = event.getChannel();
/*  56 */       c.getSite().initTime();
/*  57 */       this.staticPageService.deleteAllStaticArticle(c);
/*  58 */       this.staticPageService.staticIndex(c.getSite());
/*     */     }
/*  60 */     if ((baseEvent instanceof AddOrUpdateChannelEvent)) {
/*  61 */       AddOrUpdateChannelEvent event = (AddOrUpdateChannelEvent)baseEvent;
/*  62 */       Channel c = event.getChannel();
/*  63 */       c.getSite().initTime();
/*  64 */       this.staticPageService.staticChannel(c);
/*  65 */       this.staticPageService.staticIndex(c.getSite());
/*     */     }
/*  67 */     if ((baseEvent instanceof DelChannelEvent)) {
/*  68 */       DelChannelEvent event = (DelChannelEvent)baseEvent;
/*  69 */       Channel c = event.getChannel();
/*  70 */       c.getSite().initTime();
/*  71 */       this.staticPageService.deleteStaticChannel(c);
/*  72 */       this.staticPageService.staticIndex(c.getSite());
/*     */     }
/*     */   }
/*     */ 
/*     */   public int getOrder()
/*     */   {
/*  78 */     return 0;
/*     */   }
/*     */ 
/*     */   public boolean supportsEventType(Class<? extends ApplicationEvent> evenType)
/*     */   {
/*  83 */     if (evenType == AddArticleEvent.class) {
/*  84 */       return true;
/*     */     }
/*  86 */     if (evenType == UpdateArticleEvent.class) {
/*  87 */       return true;
/*     */     }
/*  89 */     if (evenType == DelArticleEvent.class) {
/*  90 */       return true;
/*     */     }
/*  92 */     if (evenType == EmptyArticleEvent.class) {
/*  93 */       return true;
/*     */     }
/*  95 */     if (evenType == AddOrUpdateChannelEvent.class) {
/*  96 */       return true;
/*     */     }
/*  98 */     if (evenType == DelChannelEvent.class) {
/*  99 */       return true;
/*     */     }
/* 101 */     return false;
/*     */   }
/*     */ 
/*     */   public boolean supportsSourceType(Class<?> sourceType)
/*     */   {
/* 106 */     if (sourceType == Article.class) {
/* 107 */       return true;
/*     */     }
/* 109 */     if (sourceType == Channel.class) {
/* 110 */       return true;
/*     */     }
/* 112 */     return false;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setStaticPageService(StaticPageService staticPageService)
/*     */   {
/* 119 */     this.staticPageService = staticPageService;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.staticpage.StaticThreadImpl
 * JD-Core Version:    0.6.1
 */
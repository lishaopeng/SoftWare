/*    */ package com.portal.sysmgr.listener;
/*    */ 
/*    */ import com.portal.extrafunc.entity.Category;
/*    */ import com.portal.extrafunc.entity.Forum;
/*    */ import com.portal.extrafunc.entity.Theme;
/*    */ import com.portal.extrafunc.service.PostsService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.event.DelCategoryEvent;
/*    */ import com.portal.sysmgr.event.DelForumEvent;
/*    */ import com.portal.sysmgr.event.DelSiteEvent;
/*    */ import com.portal.sysmgr.event.DelThemeEvent;
/*    */ import com.portal.sysmgr.event.DelUserEvent;
/*    */ import com.portal.usermgr.entity.User;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ import org.springframework.context.event.SmartApplicationListener;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class DelPostsListener
/*    */   implements SmartApplicationListener
/*    */ {
/*    */   private PostsService postsService;
/*    */ 
/*    */   public void onApplicationEvent(ApplicationEvent baseEvent)
/*    */   {
/* 27 */     if ((baseEvent instanceof DelSiteEvent)) {
/* 28 */       DelSiteEvent event = (DelSiteEvent)baseEvent;
/* 29 */       Site site = event.getSite();
/* 30 */       this.postsService.deletePosts(site.getId(), null, null, null, null);
/*    */     }
/* 32 */     if ((baseEvent instanceof DelCategoryEvent)) {
/* 33 */       DelCategoryEvent event = (DelCategoryEvent)baseEvent;
/* 34 */       Category c = event.getCategory();
/* 35 */       this.postsService.deletePosts(null, c.getId(), null, null, null);
/*    */     }
/* 37 */     if ((baseEvent instanceof DelForumEvent)) {
/* 38 */       DelForumEvent event = (DelForumEvent)baseEvent;
/* 39 */       Forum f = event.getForum();
/* 40 */       this.postsService.deletePosts(null, null, f.getId(), null, null);
/*    */     }
/* 42 */     if ((baseEvent instanceof DelThemeEvent)) {
/* 43 */       DelThemeEvent event = (DelThemeEvent)baseEvent;
/* 44 */       Theme t = event.getTheme();
/* 45 */       this.postsService.deletePosts(null, null, null, t.getId(), null);
/*    */     }
/* 47 */     if ((baseEvent instanceof DelUserEvent)) {
/* 48 */       DelUserEvent event = (DelUserEvent)baseEvent;
/* 49 */       User user = event.getUser();
/* 50 */       this.postsService.deletePosts(null, null, null, null, user.getId());
/*    */     }
/*    */   }
/*    */ 
/*    */   public int getOrder()
/*    */   {
/* 56 */     return 0;
/*    */   }
/*    */ 
/*    */   public boolean supportsEventType(Class<? extends ApplicationEvent> arg0)
/*    */   {
/* 61 */     return false;
/*    */   }
/*    */ 
/*    */   public boolean supportsSourceType(Class<?> arg0)
/*    */   {
/* 66 */     return false;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setPostsService(PostsService postsService)
/*    */   {
/* 73 */     this.postsService = postsService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.listener.DelPostsListener
 * JD-Core Version:    0.6.1
 */
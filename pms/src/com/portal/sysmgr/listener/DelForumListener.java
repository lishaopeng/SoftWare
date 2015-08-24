/*    */ package com.portal.sysmgr.listener;
/*    */ 
/*    */ /*    */ import java.util.List;

/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.ApplicationEvent;
/*    */ import org.springframework.context.event.SmartApplicationListener;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;

import com.portal.extrafunc.entity.Category;
/*    */ import com.portal.extrafunc.entity.Forum;
/*    */ import com.portal.extrafunc.entity.Posts;
/*    */ import com.portal.extrafunc.service.ForumService;
/*    */ import com.portal.extrafunc.service.PostsService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.event.DelCategoryEvent;
/*    */ import com.portal.sysmgr.event.DelSiteEvent;
/*    */ import com.portal.sysmgr.event.DelUserEvent;
/*    */ import com.portal.usermgr.entity.User;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class DelForumListener
/*    */   implements SmartApplicationListener
/*    */ {
/*    */   private ForumService forumService;
/*    */   private PostsService postsService;
/*    */ 
/*    */   @Override
public void onApplicationEvent(ApplicationEvent baseEvent)
/*    */   {
/* 28 */     if ((baseEvent instanceof DelSiteEvent)) {
/* 29 */       DelSiteEvent event = (DelSiteEvent)baseEvent;
/* 30 */       Site site = event.getSite();
/* 31 */       this.forumService.deleteBySiteId(site.getId());
/*    */     }
/* 33 */     if ((baseEvent instanceof DelCategoryEvent)) {
/* 34 */       DelCategoryEvent event = (DelCategoryEvent)baseEvent;
/* 35 */       Category c = event.getCategory();
/* 36 */       this.forumService.deleteByCategoryId(c.getId());
/*    */     }
/* 38 */     if ((baseEvent instanceof DelUserEvent)) {
/* 39 */       DelUserEvent event = (DelUserEvent)baseEvent;
/* 40 */       User user = event.getUser();
			/* 41 */List<Forum> list = this.forumService.getList();
/* 42 */       for (Forum f : list) {
/* 43 */         Posts p = this.postsService.getLastPostsByUserAndForum(f.getId(), 
/* 44 */           user.getId());
/* 45 */         if (p != null) {
/* 46 */           f.setLastTheme(p.getTheme());
/* 47 */           f.setLastReplyer(p.getCreater());
/*    */         }
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   @Override
public int getOrder()
/*    */   {
/* 55 */     return 0;
/*    */   }
/*    */ 
/*    */   @Override
public boolean supportsEventType(Class<? extends ApplicationEvent> arg0)
/*    */   {
/* 60 */     return false;
/*    */   }
/*    */ 
/*    */   @Override
public boolean supportsSourceType(Class<?> arg0)
/*    */   {
/* 65 */     return false;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setForumService(ForumService forumService)
/*    */   {
/* 73 */     this.forumService = forumService;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setPostsService(PostsService postsService) {
/* 78 */     this.postsService = postsService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.listener.DelForumListener
 * JD-Core Version:    0.6.1
 */
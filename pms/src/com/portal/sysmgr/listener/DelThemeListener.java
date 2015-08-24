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
/*    */ import com.portal.extrafunc.entity.Theme;
/*    */ import com.portal.extrafunc.service.PostsService;
/*    */ import com.portal.extrafunc.service.ThemeService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.event.DelCategoryEvent;
/*    */ import com.portal.sysmgr.event.DelForumEvent;
/*    */ import com.portal.sysmgr.event.DelSiteEvent;
/*    */ import com.portal.sysmgr.event.DelUserEvent;
/*    */ import com.portal.usermgr.entity.User;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class DelThemeListener
/*    */   implements SmartApplicationListener
/*    */ {
/*    */   private ThemeService themeService;
/*    */   private PostsService postsService;
/*    */ 
/*    */   @Override
public void onApplicationEvent(ApplicationEvent baseEvent)
/*    */   {
/* 30 */     if ((baseEvent instanceof DelSiteEvent)) {
/* 31 */       DelSiteEvent event = (DelSiteEvent)baseEvent;
/* 32 */       Site site = event.getSite();
/* 33 */       this.themeService.deleteBySiteId(site.getId());
/*    */     }
/* 35 */     if ((baseEvent instanceof DelCategoryEvent)) {
/* 36 */       DelCategoryEvent event = (DelCategoryEvent)baseEvent;
/* 37 */       Category c = event.getCategory();
/* 38 */       this.themeService.deleteByCategoryId(c.getId());
/*    */     }
/* 40 */     if ((baseEvent instanceof DelForumEvent)) {
/* 41 */       DelForumEvent event = (DelForumEvent)baseEvent;
/* 42 */       Forum f = event.getForum();
/* 43 */       this.themeService.deleteByForumId(f.getId());
/*    */     }
/* 45 */     if ((baseEvent instanceof DelUserEvent)) {
/* 46 */       DelUserEvent event = (DelUserEvent)baseEvent;
/* 47 */       User user = event.getUser();
/* 48 */       this.themeService.deleteByUserId(user.getId());
			/* 49 */List<Theme> list = this.themeService.getThemeAll();
/* 50 */       for (Theme t : list) {
/* 51 */         Posts p = this.postsService.getLastPostsByUserAndTheme(t.getId(), 
/* 52 */           user.getId());
/* 53 */         if (p != null)
/* 54 */           t.setLastReplyer(p.getCreater());
/*    */       }
/*    */     }
/*    */   }
/*    */ 
/*    */   @Override
public int getOrder()
/*    */   {
/* 62 */     return 0;
/*    */   }
/*    */ 
/*    */   @Override
public boolean supportsEventType(Class<? extends ApplicationEvent> arg0)
/*    */   {
/* 67 */     return false;
/*    */   }
/*    */ 
/*    */   @Override
public boolean supportsSourceType(Class<?> arg0)
/*    */   {
/* 72 */     return false;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setThemeService(ThemeService themeService)
/*    */   {
/* 80 */     this.themeService = themeService;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setPostsService(PostsService postsService) {
/* 85 */     this.postsService = postsService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.listener.DelThemeListener
 * JD-Core Version:    0.6.1
 */
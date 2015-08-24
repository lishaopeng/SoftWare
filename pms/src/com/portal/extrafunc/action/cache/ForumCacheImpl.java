/*     */ package com.portal.extrafunc.action.cache;
/*     */ 
/*     */ /*     */ import java.util.List;

/*     */ import net.sf.ehcache.Ehcache;
/*     */ import net.sf.ehcache.Element;

/*     */ import org.springframework.beans.factory.DisposableBean;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.beans.factory.annotation.Qualifier;

import com.portal.extrafunc.entity.Forum;
/*     */ import com.portal.extrafunc.entity.Theme;
/*     */ import com.portal.extrafunc.service.ForumService;
/*     */ 
/*     */ public class ForumCacheImpl
/*     */   implements ForumCache, DisposableBean
/*     */ {
/*     */   private ForumService forumService;
/*     */   private Ehcache cache;
/*     */ 
/*     */   @Override
public Forum updateForum(Theme theme, Integer themeTotal, Integer replyTotal, Integer themeToday, Integer replyToday)
/*     */   {
/*  27 */     Integer forumId = theme.getForum().getId();
/*  28 */     Element e = this.cache.get(forumId);
/*     */     Forum f;
/*  30 */     if (e != null)
/*  31 */       f = (Forum)e.getObjectValue();
/*     */     else {
/*  33 */       f = this.forumService.findById(forumId);
/*     */     }
/*  35 */     if (f == null) {
/*  36 */       return null;
/*     */     }
/*  38 */     f.setThemeTotal(Integer.valueOf(f.getThemeTotal().intValue() + themeTotal.intValue()));
/*  39 */     f.setReplyTotal(Integer.valueOf(f.getReplyTotal().intValue() + replyTotal.intValue()));
/*  40 */     f.setThemeToday(Integer.valueOf(f.getThemeToday().intValue() + themeToday.intValue()));
/*  41 */     f.setReplyToday(Integer.valueOf(f.getReplyToday().intValue() + replyToday.intValue()));
/*  42 */     f.setLastTheme(theme);
/*  43 */     f.setLastReplyer(theme.getLastReplyer());
/*  44 */     this.cache.put(new Element(forumId, f));
/*  45 */     return f;
/*     */   }
/*     */ 
/*     */   @Override
public Forum getForum(Integer forumId) {
/*  49 */     Element e = this.cache.get(forumId);
/*     */     Forum f;
/*  51 */     if (e != null)
/*  52 */       f = (Forum)e.getObjectValue();
/*     */     else {
/*  54 */       f = this.forumService.findById(forumId);
/*     */     }
/*  56 */     if (f == null) {
/*  57 */       return null;
/*     */     }
/*  59 */     return f;
/*     */   }
/*     */ 
/*     */   @Override
public void statisToDB()
/*     */   {
		/* 64 */List<Integer> keys = this.cache.getKeys();
/*  65 */     if (keys.size() <= 0) {
/*  66 */       return;
/*     */     }
/*  68 */     for (Integer id : keys) {
/*  69 */       Element e = this.cache.get(id);
/*  70 */       if (e != null) {
/*  71 */         Forum forum = (Forum)e.getObjectValue();
/*  72 */         this.forumService.updateForum(forum);
/*     */       }
/*     */     }
/*  75 */     this.cache.removeAll();
/*     */   }
/*     */ 
/*     */   @Override
public void statisOneday() {
		/* 79 */List<Forum> flist = this.forumService.getList();
/*  80 */     for (Forum f : flist) {
/*  81 */       Element e = this.cache.get(f.getId());
/*  82 */       if (e != null) {
/*  83 */         Forum forum = (Forum)e.getObjectValue();
/*  84 */         this.forumService.updateForumOnday(forum);
/*     */       } else {
/*  86 */         this.forumService.updateForumOnday(f);
/*     */       }
/*     */     }
/*  89 */     this.cache.removeAll();
/*     */   }
/*     */ 
/*     */   @Override
public void destroy()
/*     */     throws Exception
/*     */   {
		/* 97 */List<Integer> keys = this.cache.getKeys();
/*  98 */     if (keys.size() <= 0) {
/*  99 */       return;
/*     */     }
/* 101 */     for (Integer id : keys) {
/* 102 */       Element e = this.cache.get(id);
/* 103 */       Forum forum = (Forum)e.getObjectValue();
/* 104 */       this.forumService.updateForum(forum);
/*     */     }
/* 106 */     this.cache.removeAll();
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setForumService(ForumService forumService)
/*     */   {
/* 115 */     this.forumService = forumService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setCache(@Qualifier("forum") Ehcache cache) {
/* 120 */     this.cache = cache;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.cache.ForumCacheImpl
 * JD-Core Version:    0.6.1
 */
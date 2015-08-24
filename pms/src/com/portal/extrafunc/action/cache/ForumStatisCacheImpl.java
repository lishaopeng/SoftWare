/*     */ package com.portal.extrafunc.action.cache;
/*     */ 
/*     */ /*     */ import java.util.List;

/*     */ import net.sf.ehcache.Ehcache;
/*     */ import net.sf.ehcache.Element;

/*     */ import org.springframework.beans.factory.DisposableBean;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.beans.factory.annotation.Qualifier;

import com.portal.extrafunc.entity.ForumStatis;
/*     */ import com.portal.extrafunc.service.ForumStatisService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ 
/*     */ public class ForumStatisCacheImpl
/*     */   implements ForumStatisCache, DisposableBean
/*     */ {
/*     */   private ForumStatisService statisService;
/*     */   private Ehcache cache;
/*     */ 
/*     */   @Override
public ForumStatis updateStatis(Site site, Integer postsToday, Integer postsTotal)
/*     */   {
/*  27 */     Element e = this.cache.get(site.getId());
/*     */     ForumStatis fstatis;
/*  29 */     if (e != null)
/*  30 */       fstatis = (ForumStatis)e.getObjectValue();
/*     */     else {
/*  32 */       fstatis = this.statisService.findById(site.getId());
/*     */     }
/*  34 */     if (fstatis == null) {
/*  35 */       fstatis = this.statisService.save(site);
/*     */     }
/*  37 */     fstatis.setPostsToday(Integer.valueOf(fstatis.getPostsToday().intValue() + postsToday.intValue()));
/*  38 */     fstatis.setPostsTotal(Integer.valueOf(fstatis.getPostsTotal().intValue() + postsTotal.intValue()));
/*  39 */     this.cache.put(new Element(site.getId(), fstatis));
/*  40 */     return fstatis;
/*     */   }
/*     */ 
/*     */   @Override
public ForumStatis getStatis(Site site) {
/*  44 */     Element e = this.cache.get(site.getId());
/*     */     ForumStatis fstatis;
/*  46 */     if (e != null)
/*  47 */       fstatis = (ForumStatis)e.getObjectValue();
/*     */     else {
/*  49 */       fstatis = this.statisService.findById(site.getId());
/*     */     }
/*  51 */     if (fstatis == null) {
/*  52 */       fstatis = this.statisService.save(site);
/*     */     }
/*  54 */     return fstatis;
/*     */   }
/*     */ 
/*     */   @Override
public void statisToDB()
/*     */   {
		/* 59 */List<Integer> keys = this.cache.getKeys();
/*  60 */     if (keys.size() <= 0) {
/*  61 */       return;
/*     */     }
/*  63 */     for (Integer id : keys) {
/*  64 */       Element e = this.cache.get(id);
/*  65 */       if (e != null) {
/*  66 */         ForumStatis fstatis = (ForumStatis)e.getObjectValue();
/*  67 */         this.statisService.update(id, fstatis.getPostsToday(), 
/*  68 */           fstatis.getPostsTotal());
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   @Override
public void statisOneday()
/*     */   {
		/* 75 */List<Integer> keys = this.cache.getKeys();
/*  76 */     if (keys.size() <= 0) {
/*  77 */       return;
/*     */     }
/*  79 */     for (Integer id : keys) {
/*  80 */       Element e = this.cache.get(id);
/*  81 */       if (e != null) {
/*  82 */         ForumStatis fstatis = (ForumStatis)e.getObjectValue();
/*  83 */         this.statisService.updateOnday(id, fstatis.getPostsToday(), 
/*  84 */           fstatis.getPostsTotal());
/*     */       }
/*     */     }
/*  87 */     this.cache.removeAll();
/*     */   }
/*     */ 
/*     */   @Override
public void destroy()
/*     */     throws Exception
/*     */   {
		/* 95 */List<Integer> keys = this.cache.getKeys();
/*  96 */     if (keys.size() <= 0) {
/*  97 */       return;
/*     */     }
/*  99 */     for (Integer id : keys) {
/* 100 */       Element e = this.cache.get(id);
/* 101 */       ForumStatis fstatis = (ForumStatis)e.getObjectValue();
/* 102 */       this.statisService.update(id, fstatis.getPostsToday(), 
/* 103 */         fstatis.getPostsTotal());
/*     */     }
/* 105 */     this.cache.removeAll();
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setStatisService(ForumStatisService statisService)
/*     */   {
/* 114 */     this.statisService = statisService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setCache(@Qualifier("forumStatis") Ehcache cache) {
/* 119 */     this.cache = cache;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.action.cache.ForumStatisCacheImpl
 * JD-Core Version:    0.6.1
 */
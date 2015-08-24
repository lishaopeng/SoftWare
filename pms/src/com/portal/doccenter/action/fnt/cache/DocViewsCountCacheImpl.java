/*     */ package com.portal.doccenter.action.fnt.cache;
/*     */ 
/*     */ /*     */ import java.util.List;

/*     */ import net.sf.ehcache.Ehcache;
/*     */ import net.sf.ehcache.Element;

/*     */ import org.springframework.beans.factory.DisposableBean;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.beans.factory.annotation.Qualifier;

import com.portal.doccenter.entity.Article;
/*     */ import com.portal.doccenter.entity.DocStatis;
/*     */ import com.portal.doccenter.service.ArticleService;
/*     */ import com.portal.doccenter.service.DocStatisService;
/*     */ 
/*     */ public class DocViewsCountCacheImpl
/*     */   implements DocViewsCountCache, DisposableBean
/*     */ {
/*     */   private ArticleService articleService;
/*     */   private DocStatisService statisService;
/*     */   private Ehcache cache;
/*     */ 
/*     */   @Override
public Integer viewsCount(Integer docId)
/*     */   {
/*  28 */     DocStatis statis = this.statisService.findById(docId);
/*  29 */     if (statis == null) {
/*  30 */       Article doc = this.articleService.findById(docId);
/*  31 */       if (doc == null) {
/*  32 */         return null;
/*     */       }
/*  34 */       statis = this.statisService.save(doc);
/*     */     }
/*  36 */     Element e = this.cache.get(docId);
/*     */     Integer views;
/*  38 */     if (e != null)
/*  39 */       views = Integer.valueOf(((Integer)e.getValue()).intValue() + 1);
/*     */     else {
/*  41 */       views = statis.getViewsCount();
/*     */     }
/*  43 */     this.cache.put(new Element(docId, views));
/*  44 */     return views;
/*     */   }
/*     */ 
/*     */   @Override
public void viewsToDB()
/*     */   {
		/* 49 */List<Integer> keys = this.cache.getKeys();
/*  50 */     if (keys.size() <= 0) {
/*  51 */       return;
/*     */     }
/*  53 */     for (Integer id : keys) {
/*  54 */       Element e = this.cache.get(id);
/*  55 */       Integer viewsCount = (Integer)e.getValue();
/*  56 */       DocStatis statis = this.statisService.findById(id);
/*  57 */       if (statis == null) {
/*  58 */         Article doc = this.articleService.findById(id);
/*  59 */         if (doc == null) {
/*  60 */           return;
/*     */         }
/*  62 */         statis = this.statisService.save(doc);
/*     */       }
/*  64 */       this.statisService.update(id, viewsCount);
/*     */     }
/*  66 */     this.cache.removeAll();
/*     */   }
/*     */ 
/*     */   @Override
public void destroy()
/*     */     throws Exception
/*     */   {
		/* 74 */List<Integer> keys = this.cache.getKeys();
/*  75 */     if (keys.size() <= 0) {
/*  76 */       return;
/*     */     }
/*  78 */     for (Integer id : keys) {
/*  79 */       Element e = this.cache.get(id);
/*  80 */       Integer viewsCount = (Integer)e.getValue();
/*  81 */       this.statisService.update(id, viewsCount);
/*     */     }
/*  83 */     this.cache.removeAll();
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setArticleService(ArticleService articleService)
/*     */   {
/*  92 */     this.articleService = articleService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setStatisService(DocStatisService statisService) {
/*  97 */     this.statisService = statisService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setCache(@Qualifier("docViews") Ehcache cache) {
/* 102 */     this.cache = cache;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.action.fnt.cache.DocViewsCountCacheImpl
 * JD-Core Version:    0.6.1
 */
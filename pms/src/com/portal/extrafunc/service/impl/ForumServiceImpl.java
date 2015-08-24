/*     */ package com.portal.extrafunc.service.impl;
/*     */ 
/*     */ import com.portal.extrafunc.dao.ForumDao;
/*     */ import com.portal.extrafunc.entity.Forum;
/*     */ import com.portal.extrafunc.entity.ForumExt;
/*     */ import com.portal.extrafunc.service.CategoryService;
/*     */ import com.portal.extrafunc.service.ForumExtService;
/*     */ import com.portal.extrafunc.service.ForumService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.event.DelForumEvent;
/*     */ import java.util.List;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class ForumServiceImpl
/*     */   implements ForumService
/*     */ {
/*     */   private ForumDao dao;
/*     */   private CategoryService categoryService;
/*     */   private ForumExtService extService;
/*     */   private ApplicationContext applicationContext;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Page<Forum> getPage(Integer siteId, Integer categoryId, String sortname, String sortorder, int pageNo, int pageSize)
/*     */   {
/*  26 */     return this.dao.getPage(siteId, categoryId, sortname, sortorder, pageNo, 
/*  27 */       pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Forum> getList(Integer categoryId) {
/*  32 */     return this.dao.getList(categoryId);
/*     */   }
/*     */ 
/*     */   public List<Forum> getList() {
/*  36 */     return this.dao.getList();
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Forum findById(Integer id) {
/*  41 */     Forum entity = (Forum)this.dao.findById(id);
/*  42 */     return entity;
/*     */   }
/*     */ 
/*     */   public Forum save(Forum bean, ForumExt ext, Site site, Integer categoryId) {
/*  46 */     bean.setSite(site);
/*  47 */     bean.setCategory(this.categoryService.findById(categoryId));
/*  48 */     bean.init();
/*  49 */     this.dao.save(bean);
/*  50 */     this.extService.save(ext, bean);
/*  51 */     return bean;
/*     */   }
/*     */ 
/*     */   public Forum updateForum(Forum forum) {
/*  55 */     Forum f = findById(forum.getId());
/*  56 */     f.setThemeTotal(forum.getThemeTotal());
/*  57 */     f.setReplyTotal(forum.getReplyTotal());
/*  58 */     f.setThemeToday(forum.getThemeToday());
/*  59 */     f.setReplyToday(forum.getReplyToday());
/*  60 */     f.setLastTheme(forum.getLastTheme());
/*  61 */     f.setLastReplyer(forum.getLastReplyer());
/*  62 */     return f;
/*     */   }
/*     */ 
/*     */   public Forum updateForumOnday(Forum forum) {
/*  66 */     Forum f = findById(forum.getId());
/*  67 */     f.setThemeTotal(forum.getThemeTotal());
/*  68 */     f.setReplyTotal(forum.getReplyTotal());
/*  69 */     f.setThemeToday(Integer.valueOf(0));
/*  70 */     f.setReplyToday(Integer.valueOf(0));
/*  71 */     f.setLastTheme(forum.getLastTheme());
/*  72 */     f.setLastReplyer(forum.getLastReplyer());
/*  73 */     return f;
/*     */   }
/*     */ 
/*     */   public Forum update(Forum bean, ForumExt ext, Integer categoryId) {
/*  77 */     bean = (Forum)this.dao.update(bean);
/*  78 */     bean.setCategory(this.categoryService.findById(categoryId));
/*  79 */     this.extService.update(ext, bean);
/*  80 */     return bean;
/*     */   }
/*     */ 
/*     */   public int deleteByCategoryId(Integer categoryId) {
/*  84 */     this.extService.deleteByCategoryId(categoryId);
/*  85 */     return this.dao.deleteByCategoryId(categoryId);
/*     */   }
/*     */ 
/*     */   public int deleteBySiteId(Integer siteId) {
/*  89 */     this.extService.deleteBySiteId(siteId);
/*  90 */     return this.dao.deleteBySiteId(siteId);
/*     */   }
/*     */ 
/*     */   public Forum deleteById(Integer id) {
/*  94 */     Forum bean = (Forum)this.dao.deleteById(id);
/*  95 */     this.applicationContext.publishEvent(new DelForumEvent(bean));
/*  96 */     return bean;
/*     */   }
/*     */ 
/*     */   public Forum[] deleteByIds(Integer[] ids) {
/* 100 */     Forum[] beans = new Forum[ids.length];
/* 101 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 102 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 104 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(ForumDao dao)
/*     */   {
/* 114 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setCategoryService(CategoryService categoryService) {
/* 119 */     this.categoryService = categoryService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setExtService(ForumExtService extService) {
/* 124 */     this.extService = extService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setApplicationContext(ApplicationContext applicationContext) {
/* 129 */     this.applicationContext = applicationContext;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.ForumServiceImpl
 * JD-Core Version:    0.6.1
 */
/*    */ package com.portal.doccenter.service.impl;
/*    */ 
/*    */ import com.portal.doccenter.dao.DocStatisDao;
/*    */ import com.portal.doccenter.entity.Article;
/*    */ import com.portal.doccenter.entity.DocStatis;
/*    */ import com.portal.doccenter.service.DocStatisService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class DocStatisServiceImpl
/*    */   implements DocStatisService
/*    */ {
/*    */   private DocStatisDao dao;
/*    */ 
/*    */   public DocStatis save(Article article)
/*    */   {
/* 16 */     DocStatis statis = new DocStatis();
/* 17 */     statis.setDoc(article);
/* 18 */     statis.init();
/* 19 */     this.dao.save(statis);
/* 20 */     article.setDocStatis(statis);
/* 21 */     return statis;
/*    */   }
/*    */ 
/*    */   public DocStatis save(Article article, DocStatis statis) {
/* 25 */     statis.setDoc(article);
/* 26 */     statis.init();
/* 27 */     this.dao.save(statis);
/* 28 */     article.setDocStatis(statis);
/* 29 */     return statis;
/*    */   }
/*    */ 
/*    */   public DocStatis update(Integer statisId, Integer viewsCount) {
/* 33 */     DocStatis entity = (DocStatis)this.dao.findById(statisId);
/* 34 */     entity.setViewsCount(viewsCount);
/* 35 */     return entity;
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public DocStatis findById(Integer id) {
/* 40 */     DocStatis entity = (DocStatis)this.dao.findById(id);
/* 41 */     return entity;
/*    */   }
/*    */ 
/*    */   public DocStatis ups(Integer id) {
/* 45 */     DocStatis entity = (DocStatis)this.dao.findById(id);
/* 46 */     if (entity.getUps() != null)
/* 47 */       entity.setUps(Integer.valueOf(entity.getUps().intValue() + 1));
/*    */     else {
/* 49 */       entity.setUps(Integer.valueOf(1));
/*    */     }
/* 51 */     return entity;
/*    */   }
/*    */ 
/*    */   public DocStatis treads(Integer id) {
/* 55 */     DocStatis entity = (DocStatis)this.dao.findById(id);
/* 56 */     if (entity.getTreads() != null)
/* 57 */       entity.setTreads(Integer.valueOf(entity.getTreads().intValue() + 1));
/*    */     else {
/* 59 */       entity.setTreads(Integer.valueOf(1));
/*    */     }
/* 61 */     return entity;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(DocStatisDao dao)
/*    */   {
/* 68 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.service.impl.DocStatisServiceImpl
 * JD-Core Version:    0.6.1
 */
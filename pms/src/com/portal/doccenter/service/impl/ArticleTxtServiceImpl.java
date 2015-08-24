/*    */ package com.portal.doccenter.service.impl;
/*    */ 
/*    */ import com.portal.doccenter.dao.ArticleTxtDao;
/*    */ import com.portal.doccenter.entity.Article;
/*    */ import com.portal.doccenter.entity.ArticleTxt;
/*    */ import com.portal.doccenter.service.ArticleTxtService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ArticleTxtServiceImpl
/*    */   implements ArticleTxtService
/*    */ {
/*    */   private ArticleTxtDao dao;
/*    */ 
/*    */   public ArticleTxt save(ArticleTxt txt, Article article)
/*    */   {
/* 16 */     if (txt.isAllBlank()) {
/* 17 */       return null;
/*    */     }
/* 19 */     txt.setArticle(article);
/* 20 */     txt.init();
/* 21 */     this.dao.save(txt);
/* 22 */     article.setArticleTxt(txt);
/* 23 */     return txt;
/*    */   }
/*    */ 
/*    */   public ArticleTxt update(ArticleTxt txt, Article article)
/*    */   {
/* 28 */     ArticleTxt entity = (ArticleTxt)this.dao.findById(article.getId());
/* 29 */     if (entity == null) {
/* 30 */       entity = save(txt, article);
/* 31 */       return entity;
/*    */     }
/* 33 */     entity = (ArticleTxt)this.dao.update(txt);
/* 34 */     entity.blankToNull();
/* 35 */     return entity;
/*    */   }
/*    */ 
/*    */   public int delDocByInputUser(Integer userId)
/*    */   {
/* 40 */     return this.dao.delDocByInputUser(userId);
/*    */   }
/*    */ 
/*    */   public int delDocBySite(Integer siteId) {
/* 44 */     return this.dao.delDocBySite(siteId);
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ArticleTxtDao dao)
/*    */   {
/* 51 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.service.impl.ArticleTxtServiceImpl
 * JD-Core Version:    0.6.1
 */
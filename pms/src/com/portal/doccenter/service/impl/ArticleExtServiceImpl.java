/*    */ package com.portal.doccenter.service.impl;
/*    */ 
/*    */ import com.portal.doccenter.dao.ArticleExtDao;
/*    */ import com.portal.doccenter.entity.Article;
/*    */ import com.portal.doccenter.entity.ArticleExt;
/*    */ import com.portal.doccenter.service.ArticleExtService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ArticleExtServiceImpl
/*    */   implements ArticleExtService
/*    */ {
/*    */   private ArticleExtDao dao;
/*    */ 
/*    */   public ArticleExt save(ArticleExt ext, Article article)
/*    */   {
/* 16 */     article.setArticleExt(ext);
/* 17 */     ext.setArticle(article);
/* 18 */     ext.init();
/* 19 */     this.dao.save(ext);
/* 20 */     article.setArticleExt(ext);
/* 21 */     return ext;
/*    */   }
/*    */ 
/*    */   public ArticleExt update(ArticleExt bean) {
/* 25 */     bean = (ArticleExt)this.dao.update(bean);
/* 26 */     return bean;
/*    */   }
/*    */ 
/*    */   public int delDocByInputUser(Integer userId) {
/* 30 */     return this.dao.delDocByInputUser(userId);
/*    */   }
/*    */ 
/*    */   public int delDocBySite(Integer siteId) {
/* 34 */     return this.dao.delDocBySite(siteId);
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ArticleExtDao dao)
/*    */   {
/* 41 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.service.impl.ArticleExtServiceImpl
 * JD-Core Version:    0.6.1
 */
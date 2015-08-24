/*    */ package com.portal.doccenter.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.doccenter.dao.ArticleQueryDao;
/*    */ import com.portal.doccenter.entity.Article;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.CacheMode;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.ScrollMode;
/*    */ import org.hibernate.ScrollableResults;
/*    */ import org.hibernate.Session;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ArticleQueryDaoImpl extends QueryDaoImpl<Article, Integer>
/*    */   implements ArticleQueryDao
/*    */ {
/*    */   public int emptyArticlePage(String treeNumber)
/*    */   {
/* 21 */     Criteria crit = createCriteria();
/* 22 */     if (!StringUtils.isBlank(treeNumber)) {
/* 23 */       crit.createAlias("channel", "c");
/* 24 */       crit.add(Restrictions.like("c.number", treeNumber + "%"));
/*    */     }
/* 26 */     Session session = getSession();
/* 27 */     ScrollableResults articles = crit.setCacheMode(CacheMode.IGNORE)
/* 28 */       .scroll(ScrollMode.FORWARD_ONLY);
/*    */ 
/* 30 */     int count = 0;
/* 31 */     while (articles.next()) {
/* 32 */       Article article = (Article)articles.get(0);
/* 33 */       session.delete(article);
/*    */ 
/* 35 */       count++; if (count % 20 == 0) {
/* 36 */         session.clear();
/*    */       }
/*    */     }
/* 39 */     return count;
/*    */   }
/*    */ 
/*    */   protected Class<Article> getEntityClass()
/*    */   {
/* 44 */     return Article.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.dao.impl.ArticleQueryDaoImpl
 * JD-Core Version:    0.6.1
 */
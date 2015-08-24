/*    */ package com.portal.doccenter.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.doccenter.dao.ArticleSignDao;
/*    */ import com.portal.doccenter.entity.ArticleSign;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ArticleSignDaoImpl extends QueryDaoImpl<ArticleSign, Integer>
/*    */   implements ArticleSignDao
/*    */ {
/*    */   protected Class<ArticleSign> getEntityClass()
/*    */   {
/* 14 */     return ArticleSign.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.dao.impl.ArticleSignDaoImpl
 * JD-Core Version:    0.6.1
 */
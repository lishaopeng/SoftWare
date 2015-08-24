/*    */ package com.portal.doccenter.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.doccenter.dao.ArticleTxtDao;
/*    */ import com.portal.doccenter.entity.ArticleTxt;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ArticleTxtDaoImpl extends QueryDaoImpl<ArticleTxt, Integer>
/*    */   implements ArticleTxtDao
/*    */ {
/*    */   public int delDocByInputUser(Integer userId)
/*    */   {
/* 14 */     String hql = "delete from ArticleTxt bean where bean.id in (select a.id from Article a where a.user.id=?)";
/*    */ 
/* 16 */     return executeQuery(hql, new Object[] { userId });
/*    */   }
/*    */ 
/*    */   public int delDocBySite(Integer siteId) {
/* 20 */     String hql = "delete from ArticleTxt bean where bean.id in (select a.id from Article a where a.site.id=?)";
/*    */ 
/* 22 */     return executeQuery(hql, new Object[] { siteId });
/*    */   }
/*    */ 
/*    */   protected Class<ArticleTxt> getEntityClass()
/*    */   {
/* 27 */     return ArticleTxt.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.dao.impl.ArticleTxtDaoImpl
 * JD-Core Version:    0.6.1
 */
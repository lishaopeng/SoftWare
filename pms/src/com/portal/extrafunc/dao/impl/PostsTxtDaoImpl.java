/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.PostsTxtDao;
/*    */ import com.portal.extrafunc.entity.PostsTxt;
/*    */ import org.hibernate.Criteria;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class PostsTxtDaoImpl extends QueryDaoImpl<PostsTxt, Integer>
/*    */   implements PostsTxtDao
/*    */ {
/*    */   public Page<PostsTxt> getPage(int pageNo, int pageSize)
/*    */   {
/* 14 */     Criteria crit = createCriteria();
/* 15 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   protected Class<PostsTxt> getEntityClass()
/*    */   {
/* 20 */     return PostsTxt.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.PostsTxtDaoImpl
 * JD-Core Version:    0.6.1
 */
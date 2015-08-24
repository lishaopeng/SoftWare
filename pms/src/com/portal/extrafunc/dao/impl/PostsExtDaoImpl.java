/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.PostsExtDao;
/*    */ import com.portal.extrafunc.entity.PostsExt;
/*    */ import org.hibernate.Criteria;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class PostsExtDaoImpl extends QueryDaoImpl<PostsExt, Integer>
/*    */   implements PostsExtDao
/*    */ {
/*    */   public Page<PostsExt> getPage(int pageNo, int pageSize)
/*    */   {
/* 14 */     Criteria crit = createCriteria();
/* 15 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   protected Class<PostsExt> getEntityClass()
/*    */   {
/* 20 */     return PostsExt.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.PostsExtDaoImpl
 * JD-Core Version:    0.6.1
 */
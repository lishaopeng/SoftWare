/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.ForumStatisDao;
/*    */ import com.portal.extrafunc.entity.ForumStatis;
/*    */ import org.hibernate.Criteria;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ForumStatisDaoImpl extends QueryDaoImpl<ForumStatis, Integer>
/*    */   implements ForumStatisDao
/*    */ {
/*    */   public Page<ForumStatis> getPage(int pageNo, int pageSize)
/*    */   {
/* 14 */     Criteria crit = createCriteria();
/* 15 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   protected Class<ForumStatis> getEntityClass()
/*    */   {
/* 20 */     return ForumStatis.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.ForumStatisDaoImpl
 * JD-Core Version:    0.6.1
 */
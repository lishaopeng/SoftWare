/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.ForumOperateDao;
/*    */ import com.portal.extrafunc.entity.ForumOperate;
/*    */ import org.hibernate.Criteria;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ForumOperateDaoImpl extends QueryDaoImpl<ForumOperate, Integer>
/*    */   implements ForumOperateDao
/*    */ {
/*    */   public Page<ForumOperate> getPage(int pageNo, int pageSize)
/*    */   {
/* 14 */     Criteria crit = createCriteria();
/* 15 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   protected Class<ForumOperate> getEntityClass()
/*    */   {
/* 20 */     return ForumOperate.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.ForumOperateDaoImpl
 * JD-Core Version:    0.6.1
 */
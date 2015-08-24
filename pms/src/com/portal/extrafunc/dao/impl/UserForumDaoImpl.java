/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.UserForumDao;
/*    */ import com.portal.extrafunc.entity.UserForum;
/*    */ import org.hibernate.Criteria;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class UserForumDaoImpl extends QueryDaoImpl<UserForum, Integer>
/*    */   implements UserForumDao
/*    */ {
/*    */   public Page<UserForum> getPage(int pageNo, int pageSize)
/*    */   {
/* 14 */     Criteria crit = createCriteria();
/* 15 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   protected Class<UserForum> getEntityClass()
/*    */   {
/* 20 */     return UserForum.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.UserForumDaoImpl
 * JD-Core Version:    0.6.1
 */
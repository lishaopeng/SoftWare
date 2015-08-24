/*    */ package com.portal.usermgr.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.usermgr.dao.UserDao;
/*    */ import com.portal.usermgr.entity.User;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Projections;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class UserDaoImpl extends QueryDaoImpl<User, Integer>
/*    */   implements UserDao
/*    */ {
/*    */   public int getAllUserCount()
/*    */   {
/* 16 */     Criteria crit = createCriteria();
/* 17 */     crit.setProjection(Projections.count("id"));
/* 18 */     return ((Number)crit.uniqueResult()).intValue();
/*    */   }
/*    */ 
/*    */   public User findByUsername(String username) {
/* 22 */     Criteria crit = createCriteria();
/* 23 */     crit.add(Restrictions.eq("username", username));
/* 24 */     return (User)findUnique(crit);
/*    */   }
/*    */ 
/*    */   protected Class<User> getEntityClass()
/*    */   {
/* 29 */     return User.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.impl.UserDaoImpl
 * JD-Core Version:    0.6.1
 */
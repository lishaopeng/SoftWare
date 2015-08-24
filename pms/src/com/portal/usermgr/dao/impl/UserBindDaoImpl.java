/*    */ package com.portal.usermgr.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.usermgr.dao.UserBindDao;
/*    */ import com.portal.usermgr.entity.UserBind;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class UserBindDaoImpl extends QueryDaoImpl<UserBind, Integer>
/*    */   implements UserBindDao
/*    */ {
/*    */   public UserBind getBindByUser(Integer userId, Integer status)
/*    */   {
/* 16 */     Criteria crit = createCriteria();
/* 17 */     crit.add(Restrictions.eq("user.id", userId));
/* 18 */     crit.add(Restrictions.eq("status", status));
/* 19 */     return (UserBind)findUnique(crit);
/*    */   }
/*    */ 
/*    */   protected Class<UserBind> getEntityClass()
/*    */   {
/* 24 */     return UserBind.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.impl.UserBindDaoImpl
 * JD-Core Version:    0.6.1
 */
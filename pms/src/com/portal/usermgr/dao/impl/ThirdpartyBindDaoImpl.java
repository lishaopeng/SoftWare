/*    */ package com.portal.usermgr.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.usermgr.dao.ThirdpartyBindDao;
/*    */ import com.portal.usermgr.entity.ThirdpartyBind;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ThirdpartyBindDaoImpl extends QueryDaoImpl<ThirdpartyBind, Integer>
/*    */   implements ThirdpartyBindDao
/*    */ {
/*    */   public ThirdpartyBind findByOpenId(String openid, String bindType)
/*    */   {
/* 16 */     Criteria crit = createCriteria();
/* 17 */     crit.add(Restrictions.eq("openid", openid));
/* 18 */     crit.add(Restrictions.eq("bindType", bindType));
/* 19 */     return (ThirdpartyBind)findUnique(crit);
/*    */   }
/*    */ 
/*    */   protected Class<ThirdpartyBind> getEntityClass()
/*    */   {
/* 24 */     return ThirdpartyBind.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.impl.ThirdpartyBindDaoImpl
 * JD-Core Version:    0.6.1
 */
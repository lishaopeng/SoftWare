/*    */ package com.portal.usermgr.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.usermgr.dao.AdminCheckDao;
/*    */ import com.portal.usermgr.entity.AdminCheck;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class AdminCheckDaoImpl extends QueryDaoImpl<AdminCheck, Integer>
/*    */   implements AdminCheckDao
/*    */ {
/*    */   public int deleteBySiteId(Integer siteId)
/*    */   {
/* 14 */     String hql = "delete from AdminCheck bean where bean.site.id=?";
/* 15 */     return executeQuery(hql, new Object[] { siteId });
/*    */   }
/*    */ 
/*    */   protected Class<AdminCheck> getEntityClass()
/*    */   {
/* 20 */     return AdminCheck.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.impl.AdminCheckDaoImpl
 * JD-Core Version:    0.6.1
 */
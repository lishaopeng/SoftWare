/*    */ package com.portal.sysmgr.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.sysmgr.dao.DatabaseConfigDao;
/*    */ import com.portal.sysmgr.entity.DatabaseConfig;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class DatabaseConfigDaoImpl extends QueryDaoImpl<DatabaseConfig, Integer>
/*    */   implements DatabaseConfigDao
/*    */ {
/*    */   public DatabaseConfig findUnique()
/*    */   {
/* 16 */     Criteria crit = createCriteria();
/* 17 */     crit.addOrder(Order.asc("id"));
/* 18 */     return (DatabaseConfig)findUnique(crit);
/*    */   }
/*    */ 
/*    */   protected Class<DatabaseConfig> getEntityClass()
/*    */   {
/* 23 */     return DatabaseConfig.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.dao.impl.DatabaseConfigDaoImpl
 * JD-Core Version:    0.6.1
 */
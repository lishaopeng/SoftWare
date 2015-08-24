/*    */ package com.portal.sysmgr.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.sysmgr.dao.ThirdpartyConfigDao;
/*    */ import com.portal.sysmgr.entity.ThirdpartyConfig;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ThirdpartyConfigDaoImpl extends QueryDaoImpl<ThirdpartyConfig, Integer>
/*    */   implements ThirdpartyConfigDao
/*    */ {
/*    */   protected Class<ThirdpartyConfig> getEntityClass()
/*    */   {
/* 14 */     return ThirdpartyConfig.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.dao.impl.ThirdpartyConfigDaoImpl
 * JD-Core Version:    0.6.1
 */
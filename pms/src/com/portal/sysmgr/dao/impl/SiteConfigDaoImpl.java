/*    */ package com.portal.sysmgr.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.sysmgr.dao.SiteConfigDao;
/*    */ import com.portal.sysmgr.entity.SiteConfig;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class SiteConfigDaoImpl extends QueryDaoImpl<SiteConfig, Integer>
/*    */   implements SiteConfigDao
/*    */ {
/*    */   protected Class<SiteConfig> getEntityClass()
/*    */   {
/* 14 */     return SiteConfig.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.dao.impl.SiteConfigDaoImpl
 * JD-Core Version:    0.6.1
 */
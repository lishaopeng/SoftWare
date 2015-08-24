/*    */ package com.portal.doccenter.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.doccenter.dao.DocStatisDao;
/*    */ import com.portal.doccenter.entity.DocStatis;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class DocStatisDaoImpl extends QueryDaoImpl<DocStatis, Integer>
/*    */   implements DocStatisDao
/*    */ {
/*    */   protected Class<DocStatis> getEntityClass()
/*    */   {
/* 16 */     return DocStatis.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.dao.impl.DocStatisDaoImpl
 * JD-Core Version:    0.6.1
 */
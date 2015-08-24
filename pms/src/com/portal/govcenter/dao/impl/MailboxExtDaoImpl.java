/*    */ package com.portal.govcenter.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.govcenter.dao.MailboxExtDao;
/*    */ import com.portal.govcenter.entity.MailboxExt;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class MailboxExtDaoImpl extends QueryDaoImpl<MailboxExt, Integer>
/*    */   implements MailboxExtDao
/*    */ {
/*    */   protected Class<MailboxExt> getEntityClass()
/*    */   {
/* 14 */     return MailboxExt.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.govcenter.dao.impl.MailboxExtDaoImpl
 * JD-Core Version:    0.6.1
 */
/*    */ package com.portal.doccenter.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.doccenter.dao.ChannelExtDao;
/*    */ import com.portal.doccenter.entity.ChannelExt;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ChannelExtDaoImpl extends QueryDaoImpl<ChannelExt, Integer>
/*    */   implements ChannelExtDao
/*    */ {
/*    */   protected Class<ChannelExt> getEntityClass()
/*    */   {
/* 15 */     return ChannelExt.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.dao.impl.ChannelExtDaoImpl
 * JD-Core Version:    0.6.1
 */
/*    */ package com.portal.doccenter.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.doccenter.dao.ChannelTxtDao;
/*    */ import com.portal.doccenter.entity.ChannelTxt;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ChannelTxtDaoImpl extends QueryDaoImpl<ChannelTxt, Integer>
/*    */   implements ChannelTxtDao
/*    */ {
/*    */   protected Class<ChannelTxt> getEntityClass()
/*    */   {
/* 15 */     return ChannelTxt.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.dao.impl.ChannelTxtDaoImpl
 * JD-Core Version:    0.6.1
 */
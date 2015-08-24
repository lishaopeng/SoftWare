/*    */ package com.portal.usermgr.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.usermgr.dao.MessageReceiveDao;
/*    */ import com.portal.usermgr.entity.MessageReceive;
/*    */ import org.hibernate.Criteria;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class MessageReceiveDaoImpl extends QueryDaoImpl<MessageReceive, Integer>
/*    */   implements MessageReceiveDao
/*    */ {
/*    */   public Page<MessageReceive> getPage(int pageNo, int pageSize)
/*    */   {
/* 14 */     Criteria crit = createCriteria();
/* 15 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   protected Class<MessageReceive> getEntityClass()
/*    */   {
/* 20 */     return MessageReceive.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.impl.MessageReceiveDaoImpl
 * JD-Core Version:    0.6.1
 */
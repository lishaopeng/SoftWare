/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.MessageBoardExtDao;
/*    */ import com.portal.extrafunc.entity.MessageBoardExt;
/*    */ import org.hibernate.Criteria;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class MessageBoardExtDaoImpl extends QueryDaoImpl<MessageBoardExt, Integer>
/*    */   implements MessageBoardExtDao
/*    */ {
/*    */   public Page<MessageBoardExt> getPage(int pageNo, int pageSize)
/*    */   {
/* 16 */     Criteria crit = createCriteria();
/* 17 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   protected Class<MessageBoardExt> getEntityClass()
/*    */   {
/* 22 */     return MessageBoardExt.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.MessageBoardExtDaoImpl
 * JD-Core Version:    0.6.1
 */
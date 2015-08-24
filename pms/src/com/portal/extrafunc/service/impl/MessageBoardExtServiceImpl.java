/*    */ package com.portal.extrafunc.service.impl;
/*    */ 
/*    */ import com.portal.extrafunc.dao.MessageBoardExtDao;
/*    */ import com.portal.extrafunc.entity.MessageBoard;
/*    */ import com.portal.extrafunc.entity.MessageBoardExt;
/*    */ import com.portal.extrafunc.service.MessageBoardExtService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class MessageBoardExtServiceImpl
/*    */   implements MessageBoardExtService
/*    */ {
/*    */   private MessageBoardExtDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<MessageBoardExt> getPage(int pageNo, int pageSize)
/*    */   {
/* 18 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public MessageBoardExt findById(Integer id) {
/* 23 */     MessageBoardExt entity = (MessageBoardExt)this.dao.findById(id);
/* 24 */     return entity;
/*    */   }
/*    */ 
/*    */   public MessageBoardExt save(MessageBoard MessageBoard, MessageBoardExt bean) {
/* 28 */     bean.setBoard(MessageBoard);
/* 29 */     this.dao.save(bean);
/* 30 */     MessageBoard.setExt(bean);
/* 31 */     return bean;
/*    */   }
/*    */ 
/*    */   public MessageBoardExt update(MessageBoardExt bean) {
/* 35 */     bean = (MessageBoardExt)this.dao.update(bean);
/* 36 */     return bean;
/*    */   }
/*    */ 
/*    */   public MessageBoardExt deleteById(Integer id) {
/* 40 */     MessageBoardExt bean = (MessageBoardExt)this.dao.deleteById(id);
/* 41 */     return bean;
/*    */   }
/*    */ 
/*    */   public MessageBoardExt[] deleteByIds(Integer[] ids) {
/* 45 */     MessageBoardExt[] beans = new MessageBoardExt[ids.length];
/* 46 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 47 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 49 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(MessageBoardExtDao dao)
/*    */   {
/* 56 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.MessageBoardExtServiceImpl
 * JD-Core Version:    0.6.1
 */
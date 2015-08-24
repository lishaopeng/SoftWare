/*    */ package com.portal.usermgr.service.impl;
/*    */ 
/*    */ import com.portal.usermgr.dao.MessageReceiveDao;
/*    */ import com.portal.usermgr.entity.MessageReceive;
/*    */ import com.portal.usermgr.entity.SiteMessage;
/*    */ import com.portal.usermgr.service.MessageReceiveService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class MessageReceiveServiceImpl
/*    */   implements MessageReceiveService
/*    */ {
/*    */   private MessageReceiveDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<MessageReceive> getPage(int pageNo, int pageSize)
/*    */   {
/* 18 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public MessageReceive findById(Integer id) {
/* 23 */     MessageReceive entity = (MessageReceive)this.dao.findById(id);
/* 24 */     return entity;
/*    */   }
/*    */ 
/*    */   public MessageReceive save(SiteMessage message, Integer[] receiveIds) {
/* 28 */     MessageReceive bean = new MessageReceive();
/* 29 */     String ids = ",";
/* 30 */     for (Integer receiveId : receiveIds) {
/* 31 */       ids = ids + receiveId + ",";
/*    */     }
/* 33 */     bean.setContent(ids);
/* 34 */     bean.setMessage(message);
/* 35 */     this.dao.save(bean);
/* 36 */     return bean;
/*    */   }
/*    */ 
/*    */   public MessageReceive update(MessageReceive bean) {
/* 40 */     bean = (MessageReceive)this.dao.update(bean);
/* 41 */     return bean;
/*    */   }
/*    */ 
/*    */   public MessageReceive deleteById(Integer id) {
/* 45 */     MessageReceive bean = (MessageReceive)this.dao.deleteById(id);
/* 46 */     return bean;
/*    */   }
/*    */ 
/*    */   public MessageReceive[] deleteByIds(Integer[] ids) {
/* 50 */     MessageReceive[] beans = new MessageReceive[ids.length];
/* 51 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 52 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 54 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(MessageReceiveDao dao)
/*    */   {
/* 61 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.service.impl.MessageReceiveServiceImpl
 * JD-Core Version:    0.6.1
 */
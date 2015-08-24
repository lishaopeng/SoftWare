/*    */ package com.portal.extrafunc.service.impl;
/*    */ 
/*    */ import com.portal.extrafunc.dao.MessageTypeDao;
/*    */ import com.portal.extrafunc.entity.MessageType;
/*    */ import com.portal.extrafunc.service.MessageTypeService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class MessageTypeServiceImpl
/*    */   implements MessageTypeService
/*    */ {
/*    */   private MessageTypeDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<MessageType> getPage(int pageNo, int pageSize)
/*    */   {
/* 20 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<MessageType> getList(Integer siteId, String sortname, String sortorder)
/*    */   {
/* 26 */     return this.dao.getList(siteId, sortname, sortorder);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public MessageType getUniqueType(Integer siteId) {
/* 31 */     return this.dao.getUniqueType(siteId);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public MessageType findById(Integer id) {
/* 36 */     MessageType entity = (MessageType)this.dao.findById(id);
/* 37 */     return entity;
/*    */   }
/*    */ 
/*    */   public MessageType save(MessageType bean, Site site) {
/* 41 */     bean.setSite(site);
/* 42 */     bean.init();
/* 43 */     this.dao.save(bean);
/* 44 */     return bean;
/*    */   }
/*    */ 
/*    */   public MessageType update(MessageType bean) {
/* 48 */     bean = (MessageType)this.dao.update(bean);
/* 49 */     return bean;
/*    */   }
/*    */ 
/*    */   public MessageType deleteById(Integer id) {
/* 53 */     MessageType bean = (MessageType)this.dao.deleteById(id);
/* 54 */     return bean;
/*    */   }
/*    */ 
/*    */   public MessageType[] deleteByIds(Integer[] ids) {
/* 58 */     MessageType[] beans = new MessageType[ids.length];
/* 59 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 60 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 62 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(MessageTypeDao dao)
/*    */   {
/* 69 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.MessageTypeServiceImpl
 * JD-Core Version:    0.6.1
 */
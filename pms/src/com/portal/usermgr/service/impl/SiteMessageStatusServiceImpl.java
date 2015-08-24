/*    */ package com.portal.usermgr.service.impl;
/*    */ 
/*    */ import com.portal.usermgr.dao.SiteMessageStatusDao;
/*    */ import com.portal.usermgr.entity.SiteMessage;
/*    */ import com.portal.usermgr.entity.SiteMessageStatus;
/*    */ import com.portal.usermgr.service.SiteMessageStatusService;
/*    */ import com.portal.usermgr.service.UserService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class SiteMessageStatusServiceImpl
/*    */   implements SiteMessageStatusService
/*    */ {
/*    */   private SiteMessageStatusDao dao;
/*    */   private UserService userService;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<SiteMessageStatus> getPage(int pageNo, int pageSize)
/*    */   {
/* 19 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public SiteMessageStatus findByRecive(Integer receiveId, Integer messageId) {
/* 24 */     return this.dao.findByRecive(receiveId, messageId);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public SiteMessageStatus findById(Integer id) {
/* 29 */     SiteMessageStatus entity = (SiteMessageStatus)this.dao.findById(id);
/* 30 */     return entity;
/*    */   }
/*    */ 
/*    */   public void save(SiteMessage message, Integer[] receiveIds) {
/* 34 */     for (Integer receiveId : receiveIds) {
/* 35 */       SiteMessageStatus bean = new SiteMessageStatus();
/* 36 */       bean.setMessage(message);
/* 37 */       bean.setReceive(this.userService.findById(receiveId));
/* 38 */       this.dao.save(bean);
/*    */     }
/*    */   }
/*    */ 
/*    */   public SiteMessageStatus update(SiteMessageStatus bean) {
/* 43 */     bean = (SiteMessageStatus)this.dao.update(bean);
/* 44 */     return bean;
/*    */   }
/*    */ 
/*    */   public SiteMessageStatus deleteByReceive(Integer messageId, Integer receiveId)
/*    */   {
/* 49 */     SiteMessageStatus bean = this.dao.findByRecive(receiveId, messageId);
/* 50 */     if (bean != null) {
/* 51 */       if (bean.getStatus().equals(SiteMessageStatus.DEL))
/* 52 */         this.dao.deleteById(bean.getId());
/*    */       else {
/* 54 */         bean.setStatus(SiteMessageStatus.DEL);
/*    */       }
/*    */     }
/* 57 */     return bean;
/*    */   }
/*    */ 
/*    */   public SiteMessageStatus deleteById(Integer id) {
/* 61 */     SiteMessageStatus bean = (SiteMessageStatus)this.dao.deleteById(id);
/* 62 */     return bean;
/*    */   }
/*    */ 
/*    */   public SiteMessageStatus[] deleteByIds(Integer[] ids) {
/* 66 */     SiteMessageStatus[] beans = new SiteMessageStatus[ids.length];
/* 67 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 68 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 70 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(SiteMessageStatusDao dao)
/*    */   {
/* 78 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setUserService(UserService userService) {
/* 83 */     this.userService = userService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.service.impl.SiteMessageStatusServiceImpl
 * JD-Core Version:    0.6.1
 */
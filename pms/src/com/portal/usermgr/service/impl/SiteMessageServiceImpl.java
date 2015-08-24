/*     */ package com.portal.usermgr.service.impl;
/*     */ 
/*     */ import com.portal.usermgr.dao.SiteMessageDao;
/*     */ import com.portal.usermgr.entity.SiteMessage;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.MessageReceiveService;
/*     */ import com.portal.usermgr.service.SiteMessageService;
/*     */ import com.portal.usermgr.service.SiteMessageStatusService;
/*     */ import com.portal.usermgr.service.UserService;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class SiteMessageServiceImpl
/*     */   implements SiteMessageService
/*     */ {
/*     */   private SiteMessageDao dao;
/*     */   private UserService userService;
/*     */   private MessageReceiveService receiveService;
/*     */   private SiteMessageStatusService statusService;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Page<SiteMessage> getPage(int pageNo, int pageSize)
/*     */   {
/*  21 */     return this.dao.getPage(pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Page<SiteMessage> getPageByTag(Integer sendId, Integer status, int pageNo, int pageSize)
/*     */   {
/*  27 */     return this.dao.getPageByTag(sendId, status, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public SiteMessage findById(Integer id) {
/*  32 */     SiteMessage entity = (SiteMessage)this.dao.findById(id);
/*  33 */     return entity;
/*     */   }
/*     */ 
/*     */   public SiteMessage save(SiteMessage bean, Integer sendId, Integer[] receiveIds)
/*     */   {
/*  38 */     bean.setSend(this.userService.findById(sendId));
/*  39 */     bean.init();
/*  40 */     this.dao.save(bean);
/*  41 */     if ((receiveIds != null) && (receiveIds.length > 3))
/*  42 */       this.receiveService.save(bean, receiveIds);
/*     */     else {
/*  44 */       this.statusService.save(bean, receiveIds);
/*     */     }
/*  46 */     return bean;
/*     */   }
/*     */ 
/*     */   public SiteMessage update(SiteMessage bean) {
/*  50 */     bean = (SiteMessage)this.dao.update(bean);
/*  51 */     return bean;
/*     */   }
/*     */ 
/*     */   public SiteMessage deleteById(Integer id, User user) {
/*  55 */     SiteMessage message = (SiteMessage)this.dao.findById(id);
/*  56 */     if (message.getSend().equals(user)) {
/*  57 */       if (message.getStatus().equals(SiteMessage.NORMAL))
/*  58 */         message.setStatus(SiteMessage.RECYCLE);
/*     */       else
/*  60 */         message.setStatus(SiteMessage.DEL);
/*     */     }
/*     */     else {
/*  63 */       this.statusService.deleteByReceive(id, user.getId());
/*     */     }
/*  65 */     return message;
/*     */   }
/*     */ 
/*     */   public SiteMessage deleteById(Integer id) {
/*  69 */     SiteMessage bean = (SiteMessage)this.dao.deleteById(id);
/*  70 */     return bean;
/*     */   }
/*     */ 
/*     */   public SiteMessage[] deleteByIds(Integer[] ids) {
/*  74 */     SiteMessage[] beans = new SiteMessage[ids.length];
/*  75 */     int i = 0; for (int len = ids.length; i < len; i++) {
/*  76 */       beans[i] = deleteById(ids[i]);
/*     */     }
/*  78 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(SiteMessageDao dao)
/*     */   {
/*  88 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setUserService(UserService userService) {
/*  93 */     this.userService = userService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setReceiveService(MessageReceiveService receiveService) {
/*  98 */     this.receiveService = receiveService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setStatusService(SiteMessageStatusService statusService) {
/* 103 */     this.statusService = statusService;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.service.impl.SiteMessageServiceImpl
 * JD-Core Version:    0.6.1
 */
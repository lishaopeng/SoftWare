/*    */ package com.portal.govcenter.service.impl;
/*    */ 
/*    */ import com.portal.govcenter.dao.MailboxExtDao;
/*    */ import com.portal.govcenter.entity.Mailbox;
/*    */ import com.portal.govcenter.entity.MailboxExt;
/*    */ import com.portal.govcenter.service.MailboxExtService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class MailboxExtServiceImpl
/*    */   implements MailboxExtService
/*    */ {
/*    */   private MailboxExtDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<MailboxExt> getPage(int pageNo, int pageSize)
/*    */   {
/* 18 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public MailboxExt findById(Integer id) {
/* 23 */     MailboxExt entity = (MailboxExt)this.dao.findById(id);
/* 24 */     return entity;
/*    */   }
/*    */ 
/*    */   public MailboxExt save(Mailbox mailbox, MailboxExt bean) {
/* 28 */     bean.setMailbox(mailbox);
/* 29 */     this.dao.save(bean);
/* 30 */     mailbox.setExt(bean);
/* 31 */     return bean;
/*    */   }
/*    */ 
/*    */   public MailboxExt update(Mailbox mailbox, MailboxExt bean) {
/* 35 */     MailboxExt ext = findById(mailbox.getId());
/* 36 */     if (ext == null) {
/* 37 */       ext = save(mailbox, bean);
/* 38 */       return ext;
/*    */     }
/* 40 */     bean = (MailboxExt)this.dao.update(bean);
/* 41 */     return bean;
/*    */   }
/*    */ 
/*    */   public MailboxExt deleteById(Integer id)
/*    */   {
/* 46 */     MailboxExt bean = (MailboxExt)this.dao.deleteById(id);
/* 47 */     return bean;
/*    */   }
/*    */ 
/*    */   public MailboxExt[] deleteByIds(Integer[] ids) {
/* 51 */     MailboxExt[] beans = new MailboxExt[ids.length];
/* 52 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 53 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 55 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(MailboxExtDao dao)
/*    */   {
/* 62 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.govcenter.service.impl.MailboxExtServiceImpl
 * JD-Core Version:    0.6.1
 */
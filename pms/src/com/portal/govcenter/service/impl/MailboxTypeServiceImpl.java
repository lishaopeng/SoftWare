/*    */ package com.portal.govcenter.service.impl;
/*    */ 
/*    */ import com.portal.govcenter.dao.MailboxTypeDao;
/*    */ import com.portal.govcenter.entity.MailboxType;
/*    */ import com.portal.govcenter.service.MailboxTypeService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class MailboxTypeServiceImpl
/*    */   implements MailboxTypeService
/*    */ {
/*    */   private MailboxTypeDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<MailboxType> getPage(int pageNo, int pageSize)
/*    */   {
/* 20 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<MailboxType> getList(Integer siteId) {
/* 25 */     return this.dao.getList(siteId);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public MailboxType findById(Integer id) {
/* 30 */     MailboxType entity = (MailboxType)this.dao.findById(id);
/* 31 */     return entity;
/*    */   }
/*    */ 
/*    */   public MailboxType save(MailboxType bean, Site site) {
/* 35 */     bean.setSite(site);
/* 36 */     bean.init();
/* 37 */     this.dao.save(bean);
/* 38 */     return bean;
/*    */   }
/*    */ 
/*    */   public MailboxType update(MailboxType bean) {
/* 42 */     bean = (MailboxType)this.dao.update(bean);
/* 43 */     return bean;
/*    */   }
/*    */ 
/*    */   public MailboxType deleteById(Integer id) {
/* 47 */     MailboxType bean = (MailboxType)this.dao.deleteById(id);
/* 48 */     return bean;
/*    */   }
/*    */ 
/*    */   public MailboxType[] deleteByIds(Integer[] ids) {
/* 52 */     MailboxType[] beans = new MailboxType[ids.length];
/* 53 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 54 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 56 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(MailboxTypeDao dao)
/*    */   {
/* 63 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.govcenter.service.impl.MailboxTypeServiceImpl
 * JD-Core Version:    0.6.1
 */
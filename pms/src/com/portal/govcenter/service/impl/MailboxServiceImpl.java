/*     */ package com.portal.govcenter.service.impl;
/*     */ 
/*     */ import com.portal.govcenter.dao.MailboxDao;
/*     */ import com.portal.govcenter.entity.Mailbox;
/*     */ import com.portal.govcenter.entity.MailboxExt;
/*     */ import com.portal.govcenter.service.MailboxExtService;
/*     */ import com.portal.govcenter.service.MailboxService;
/*     */ import com.portal.govcenter.service.MailboxTypeService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.usermgr.entity.Admin;
/*     */ import com.portal.usermgr.entity.Depart;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.DepartService;
/*     */ import java.sql.Timestamp;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class MailboxServiceImpl
/*     */   implements MailboxService
/*     */ {
/*     */   private MailboxDao dao;
/*     */   private MailboxTypeService typeService;
/*     */   private MailboxExtService extService;
/*     */   private DepartService departService;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Page<Mailbox> getPage(String name, Integer siteId, User user, Integer typeId, int pageNo, int pageSize)
/*     */   {
/*  28 */     Integer departId = null;
/*  29 */     if (!user.getAdmin().haveAllManage(siteId)) {
/*  30 */       Depart depart = user.getAdmin().getDepart(siteId);
/*  31 */       if (depart != null) {
/*  32 */         departId = depart.getId();
/*     */       }
/*     */     }
/*  35 */     return this.dao.getPage(name, siteId, departId, typeId, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public Page<Mailbox> getPageByTag(String name, Integer siteId, Integer departId, Integer typeId, int pageNo, int pageSize)
/*     */   {
/*  40 */     return this.dao.getPageByTag(name, siteId, departId, typeId, pageNo, 
/*  41 */       pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Mailbox findById(Integer id) {
/*  46 */     Mailbox entity = (Mailbox)this.dao.findById(id);
/*  47 */     return entity;
/*     */   }
/*     */ 
/*     */   public Mailbox save(Mailbox bean, MailboxExt ext, Site site, Integer departId, Integer typeId)
/*     */   {
/*  52 */     bean.setType(this.typeService.findById(typeId));
/*  53 */     if (departId != null) {
/*  54 */       bean.setDepart(this.departService.findById(departId));
/*     */     }
/*  56 */     bean.setSite(site);
/*  57 */     bean.init();
/*  58 */     this.dao.save(bean);
/*  59 */     this.extService.save(bean, ext);
/*  60 */     return bean;
/*     */   }
/*     */ 
/*     */   public Mailbox update(Mailbox bean, MailboxExt ext, User user, Integer siteId)
/*     */   {
/*  65 */     bean = (Mailbox)this.dao.update(bean);
/*  66 */     if (!StringUtils.isBlank(ext.getReply())) {
/*  67 */       bean.setReplyTime(new Timestamp(System.currentTimeMillis()));
/*  68 */       bean.setDepart(user.getAdmin().getDepart(siteId));
/*  69 */       bean.setStatus(Mailbox.DEALED);
/*     */     }
/*  71 */     this.extService.update(bean, ext);
/*  72 */     return bean;
/*     */   }
/*     */ 
/*     */   public Mailbox showMailbox(Integer id) {
/*  76 */     Mailbox bean = (Mailbox)this.dao.findById(id);
/*  77 */     if (bean.getShow().booleanValue())
/*  78 */       bean.setShow(Boolean.valueOf(false));
/*     */     else {
/*  80 */       bean.setShow(Boolean.valueOf(true));
/*     */     }
/*  82 */     return bean;
/*     */   }
/*     */ 
/*     */   public Mailbox forwardMailbox(Integer id, Integer departId) {
/*  86 */     Mailbox bean = (Mailbox)this.dao.findById(id);
/*  87 */     bean.setDepart(this.departService.findById(departId));
/*  88 */     bean.setStatus(Mailbox.FORWARD);
/*  89 */     return bean;
/*     */   }
/*     */ 
/*     */   public Mailbox backMailbox(Integer id) {
/*  93 */     Mailbox bean = (Mailbox)this.dao.findById(id);
/*  94 */     bean.setDepart(null);
/*  95 */     bean.setStatus(Mailbox.BACK);
/*  96 */     return bean;
/*     */   }
/*     */ 
/*     */   public Mailbox deleteById(Integer id) {
/* 100 */     Mailbox bean = (Mailbox)this.dao.deleteById(id);
/* 101 */     return bean;
/*     */   }
/*     */ 
/*     */   public Mailbox[] deleteByIds(Integer[] ids) {
/* 105 */     Mailbox[] beans = new Mailbox[ids.length];
/* 106 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 107 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 109 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(MailboxDao dao)
/*     */   {
/* 119 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setTypeService(MailboxTypeService typeService) {
/* 124 */     this.typeService = typeService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setExtService(MailboxExtService extService) {
/* 129 */     this.extService = extService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDepartService(DepartService departService) {
/* 134 */     this.departService = departService;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.govcenter.service.impl.MailboxServiceImpl
 * JD-Core Version:    0.6.1
 */
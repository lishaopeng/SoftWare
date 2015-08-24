/*     */ package com.portal.usermgr.service.impl;
/*     */ 
/*     */ /*     */ import java.util.Set;

/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;

import com.portal.doccenter.service.ChannelService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.usermgr.dao.AdminCheckDao;
/*     */ import com.portal.usermgr.entity.Admin;
/*     */ import com.portal.usermgr.entity.AdminCheck;
/*     */ import com.portal.usermgr.service.AdminCheckService;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class AdminCheckServiceImpl
/*     */   implements AdminCheckService
/*     */ {
/*     */   private AdminCheckDao dao;
/*     */   private ChannelService channelService;
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public AdminCheck findById(Integer id)
/*     */   {
/*  21 */     AdminCheck entity = this.dao.findById(id);
/*  22 */     return entity;
/*     */   }
/*     */ 
/*     */   @Override
public AdminCheck save(Site site, Admin admin, Byte manageStatus, Boolean takeDepart, Integer[] channelIds)
/*     */   {
/*  27 */     AdminCheck bean = new AdminCheck();
/*  28 */     bean.setSite(site);
/*  29 */     bean.setAdmin(admin);
/*  30 */     bean.setManageStatus(manageStatus);
/*  31 */     if ((!takeDepart.booleanValue()) && 
/*  32 */       (channelIds != null)) {
/*  33 */       for (Integer cid : channelIds) {
/*  34 */         bean.addToChannels(this.channelService.findById(cid));
/*     */       }
/*     */     }
/*     */ 
/*  38 */     bean.setTakeDepart(takeDepart);
/*  39 */     this.dao.save(bean);
/*  40 */     admin.addToAdminChecks(bean);
/*  41 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public AdminCheck update(AdminCheck bean) {
/*  45 */     bean = this.dao.update(bean);
/*  46 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public void updateByAdmin(Admin admin, Site site, Byte manageStatus, Boolean takeDepart, Integer[] channelIds)
/*     */   {
		/* 51 */Set<AdminCheck> ass = admin.getAdminChecks();
/*  52 */     if ((site == null) || (manageStatus == null)) {
/*  53 */       return;
/*     */     }
/*  55 */     if (ass == null)
/*  56 */       save(site, admin, manageStatus, takeDepart, channelIds);
/*     */     else
/*  58 */       for (AdminCheck as : ass)
/*  59 */         if (site.getId().equals(as.getSite().getId())) {
/*  60 */           as.setManageStatus(manageStatus);
/*  61 */           as.setTakeDepart(takeDepart);
/*  62 */           as.getChannels().clear();
/*  63 */           if ((!takeDepart.booleanValue()) && 
/*  64 */             (channelIds != null))
/*  65 */             for (Integer cid : channelIds)
/*  66 */               as.addToChannels(this.channelService.findById(cid));
/*     */         }
/*     */   }
/*     */ 
/*     */   @Override
public int deleteBySiteId(Integer siteId)
/*     */   {
/*  76 */     return this.dao.deleteBySiteId(siteId);
/*     */   }
/*     */ 
/*     */   @Override
public AdminCheck deleteById(Integer id) {
/*  80 */     AdminCheck bean = this.dao.deleteById(id);
/*  81 */     bean.getChannels().clear();
/*  82 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public AdminCheck[] deleteByIds(Integer[] ids) {
/*  86 */     AdminCheck[] beans = new AdminCheck[ids.length];
/*  87 */     int i = 0; for (int len = ids.length; i < len; i++) {
/*  88 */       beans[i] = deleteById(ids[i]);
/*     */     }
/*  90 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(AdminCheckDao dao)
/*     */   {
/*  98 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setChannelService(ChannelService channelService) {
/* 103 */     this.channelService = channelService;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.service.impl.AdminCheckServiceImpl
 * JD-Core Version:    0.6.1
 */
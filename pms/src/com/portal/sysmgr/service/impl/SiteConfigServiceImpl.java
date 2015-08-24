/*    */ package com.portal.sysmgr.service.impl;
/*    */ 
/*    */ import com.portal.sysmgr.dao.SiteConfigDao;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.entity.SiteConfig;
/*    */ import com.portal.sysmgr.service.SiteConfigService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class SiteConfigServiceImpl
/*    */   implements SiteConfigService
/*    */ {
/*    */   private SiteConfigDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<SiteConfig> getPage(int pageNo, int pageSize)
/*    */   {
/* 18 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public SiteConfig findById(Integer id) {
/* 23 */     SiteConfig entity = (SiteConfig)this.dao.findById(id);
/* 24 */     return entity;
/*    */   }
/*    */ 
/*    */   public SiteConfig save(SiteConfig bean) {
/* 28 */     this.dao.save(bean);
/* 29 */     return bean;
/*    */   }
/*    */ 
/*    */   public SiteConfig update(SiteConfig bean, Site site) {
/* 33 */     if (bean.getId() != null) {
/* 34 */       SiteConfig sc = findById(bean.getId());
/* 35 */       bean = (SiteConfig)this.dao.update(bean);
/* 36 */       if (bean.getRegMin() == null) {
/* 37 */         sc.setRegMin(null);
/*    */       }
/* 39 */       if (bean.getRegMax() == null) {
/* 40 */         sc.setRegMax(null);
/*    */       }
/* 42 */       if (bean.getLoginCount() == null)
/* 43 */         sc.setLoginCount(null);
/*    */     }
/*    */     else {
/* 46 */       bean.setSite(site);
/* 47 */       bean.init();
/* 48 */       save(bean);
/* 49 */       site.setConfig(bean);
/*    */     }
/* 51 */     return bean;
/*    */   }
/*    */ 
/*    */   public SiteConfig deleteById(Integer id) {
/* 55 */     SiteConfig bean = (SiteConfig)this.dao.deleteById(id);
/* 56 */     return bean;
/*    */   }
/*    */ 
/*    */   public SiteConfig[] deleteByIds(Integer[] ids) {
/* 60 */     SiteConfig[] beans = new SiteConfig[ids.length];
/* 61 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 62 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 64 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(SiteConfigDao dao)
/*    */   {
/* 71 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.service.impl.SiteConfigServiceImpl
 * JD-Core Version:    0.6.1
 */
/*    */ package com.portal.sysmgr.service.impl;
/*    */ 
/*    */ import com.portal.sysmgr.dao.ThirdpartyConfigDao;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.entity.ThirdpartyConfig;
/*    */ import com.portal.sysmgr.service.ThirdpartyConfigService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ThirdpartyConfigServiceImpl
/*    */   implements ThirdpartyConfigService
/*    */ {
/*    */   private ThirdpartyConfigDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<ThirdpartyConfig> getPage(int pageNo, int pageSize)
/*    */   {
/* 18 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public ThirdpartyConfig findById(Integer id) {
/* 23 */     ThirdpartyConfig entity = (ThirdpartyConfig)this.dao.findById(id);
/* 24 */     return entity;
/*    */   }
/*    */ 
/*    */   public ThirdpartyConfig save(ThirdpartyConfig bean) {
/* 28 */     this.dao.save(bean);
/* 29 */     return bean;
/*    */   }
/*    */ 
/*    */   public ThirdpartyConfig update(ThirdpartyConfig bean, Site site) {
/* 33 */     if (bean.getId() != null) {
/* 34 */       bean = (ThirdpartyConfig)this.dao.update(bean);
/*    */     } else {
/* 36 */       bean.setSite(site);
/* 37 */       save(bean);
/*    */     }
/* 39 */     return bean;
/*    */   }
/*    */ 
/*    */   public ThirdpartyConfig deleteById(Integer id) {
/* 43 */     ThirdpartyConfig bean = (ThirdpartyConfig)this.dao.deleteById(id);
/* 44 */     return bean;
/*    */   }
/*    */ 
/*    */   public ThirdpartyConfig[] deleteByIds(Integer[] ids) {
/* 48 */     ThirdpartyConfig[] beans = new ThirdpartyConfig[ids.length];
/* 49 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 50 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 52 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ThirdpartyConfigDao dao)
/*    */   {
/* 59 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.service.impl.ThirdpartyConfigServiceImpl
 * JD-Core Version:    0.6.1
 */
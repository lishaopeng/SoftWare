/*    */ package com.portal.sysmgr.service.impl;
/*    */ 
/*    */ import com.portal.sysmgr.dao.DatabaseConfigDao;
/*    */ import com.portal.sysmgr.entity.DatabaseConfig;
/*    */ import com.portal.sysmgr.service.DatabaseConfigService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class DatabaseConfigServiceImpl
/*    */   implements DatabaseConfigService
/*    */ {
/*    */   private DatabaseConfigDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<DatabaseConfig> getPage(int pageNo, int pageSize)
/*    */   {
/* 17 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public DatabaseConfig findUnique() {
/* 22 */     return this.dao.findUnique();
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public DatabaseConfig findById(Integer id) {
/* 27 */     DatabaseConfig entity = (DatabaseConfig)this.dao.findById(id);
/* 28 */     return entity;
/*    */   }
/*    */ 
/*    */   public DatabaseConfig save(DatabaseConfig bean) {
/* 32 */     this.dao.save(bean);
/* 33 */     return bean;
/*    */   }
/*    */ 
/*    */   public DatabaseConfig update(DatabaseConfig bean) {
/* 37 */     if (bean.getId() != null) {
/* 38 */       bean = (DatabaseConfig)this.dao.update(bean);
/*    */     } else {
/* 40 */       bean.init();
/* 41 */       save(bean);
/*    */     }
/* 43 */     return bean;
/*    */   }
/*    */ 
/*    */   public DatabaseConfig deleteById(Integer id) {
/* 47 */     DatabaseConfig bean = (DatabaseConfig)this.dao.deleteById(id);
/* 48 */     return bean;
/*    */   }
/*    */ 
/*    */   public DatabaseConfig[] deleteByIds(Integer[] ids) {
/* 52 */     DatabaseConfig[] beans = new DatabaseConfig[ids.length];
/* 53 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 54 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 56 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(DatabaseConfigDao dao)
/*    */   {
/* 63 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.service.impl.DatabaseConfigServiceImpl
 * JD-Core Version:    0.6.1
 */
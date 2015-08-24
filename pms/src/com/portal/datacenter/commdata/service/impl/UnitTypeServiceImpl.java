/*    */ package com.portal.datacenter.commdata.service.impl;
/*    */ 
/*    */ import com.portal.datacenter.commdata.dao.UnitTypeDao;
/*    */ import com.portal.datacenter.commdata.entity.UnitType;
/*    */ import com.portal.datacenter.commdata.service.UnitTypeService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class UnitTypeServiceImpl
/*    */   implements UnitTypeService
/*    */ {
/*    */   private UnitTypeDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<UnitType> getPage(int pageNo, int pageSize)
/*    */   {
/* 17 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public UnitType findById(Integer id) {
/* 22 */     UnitType entity = (UnitType)this.dao.findById(id);
/* 23 */     return entity;
/*    */   }
/*    */ 
/*    */   public UnitType save(UnitType bean) {
/* 27 */     this.dao.save(bean);
/* 28 */     return bean;
/*    */   }
/*    */ 
/*    */   public UnitType update(UnitType bean) {
/* 32 */     bean = (UnitType)this.dao.update(bean);
/* 33 */     return bean;
/*    */   }
/*    */ 
/*    */   public UnitType deleteById(Integer id) {
/* 37 */     UnitType bean = (UnitType)this.dao.deleteById(id);
/* 38 */     return bean;
/*    */   }
/*    */ 
/*    */   public UnitType[] deleteByIds(Integer[] ids) {
/* 42 */     UnitType[] beans = new UnitType[ids.length];
/* 43 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 44 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 46 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(UnitTypeDao dao)
/*    */   {
/* 53 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.service.impl.UnitTypeServiceImpl
 * JD-Core Version:    0.6.1
 */
/*    */ package com.portal.datacenter.commdata.service.impl;
/*    */ 
/*    */ import com.portal.datacenter.commdata.dao.EconomyTypeDao;
/*    */ import com.portal.datacenter.commdata.entity.EconomyType;
/*    */ import com.portal.datacenter.commdata.service.EconomyTypeService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class EconomyTypeServiceImpl
/*    */   implements EconomyTypeService
/*    */ {
/*    */   private EconomyTypeDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<EconomyType> getPage(int pageNo, int pageSize)
/*    */   {
/* 19 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<EconomyType> getEconomyTypeList() {
/* 24 */     return this.dao.getEconomyTypeList();
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public EconomyType findByCode(String code) {
/* 29 */     return this.dao.findByCode(code);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public EconomyType findById(Integer id) {
/* 34 */     EconomyType entity = (EconomyType)this.dao.findById(id);
/* 35 */     return entity;
/*    */   }
/*    */ 
/*    */   public EconomyType save(EconomyType bean) {
/* 39 */     this.dao.save(bean);
/* 40 */     return bean;
/*    */   }
/*    */ 
/*    */   public EconomyType update(EconomyType bean) {
/* 44 */     bean = (EconomyType)this.dao.update(bean);
/* 45 */     return bean;
/*    */   }
/*    */ 
/*    */   public EconomyType deleteById(Integer id) {
/* 49 */     EconomyType bean = (EconomyType)this.dao.deleteById(id);
/* 50 */     return bean;
/*    */   }
/*    */ 
/*    */   public EconomyType[] deleteByIds(Integer[] ids) {
/* 54 */     EconomyType[] beans = new EconomyType[ids.length];
/* 55 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 56 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 58 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(EconomyTypeDao dao)
/*    */   {
/* 65 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.service.impl.EconomyTypeServiceImpl
 * JD-Core Version:    0.6.1
 */
/*    */ package com.portal.datacenter.commdata.service.impl;
/*    */ 
/*    */ import com.portal.datacenter.commdata.dao.SpecialtyDao;
/*    */ import com.portal.datacenter.commdata.entity.Specialty;
/*    */ import com.portal.datacenter.commdata.service.SpecialtyService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class SpecialtyServiceImpl
/*    */   implements SpecialtyService
/*    */ {
/*    */   private SpecialtyDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<Specialty> getPage(String key, int pageNo, int pageSize)
/*    */   {
/* 19 */     return this.dao.getPage(key, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<Specialty> getSpecialtyList(Integer id) {
/* 24 */     return this.dao.getSpecialtyList(id);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<Specialty> getSpecialtyChild(Integer id) {
/* 29 */     return this.dao.getSpecialtyChild(id);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Specialty findByCode(String code) {
/* 34 */     return this.dao.findByCode(code);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Specialty findById(Integer id) {
/* 39 */     Specialty entity = (Specialty)this.dao.findById(id);
/* 40 */     return entity;
/*    */   }
/*    */ 
/*    */   public Specialty save(Specialty bean, Integer parentId) {
/* 44 */     if (parentId != null) {
/* 45 */       bean.setParent(findById(parentId));
/*    */     }
/* 47 */     this.dao.save(bean);
/* 48 */     return bean;
/*    */   }
/*    */ 
/*    */   public Specialty update(Specialty bean, Integer parentId) {
/* 52 */     bean = (Specialty)this.dao.update(bean);
/* 53 */     if (parentId != null)
/* 54 */       bean.setParent(findById(parentId));
/*    */     else {
/* 56 */       bean.setParent(null);
/*    */     }
/* 58 */     return bean;
/*    */   }
/*    */ 
/*    */   public Specialty deleteById(Integer id) {
/* 62 */     Specialty bean = (Specialty)this.dao.deleteById(id);
/* 63 */     return bean;
/*    */   }
/*    */ 
/*    */   public Specialty[] deleteByIds(Integer[] ids) {
/* 67 */     Specialty[] beans = new Specialty[ids.length];
/* 68 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 69 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 71 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(SpecialtyDao dao)
/*    */   {
/* 78 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.service.impl.SpecialtyServiceImpl
 * JD-Core Version:    0.6.1
 */
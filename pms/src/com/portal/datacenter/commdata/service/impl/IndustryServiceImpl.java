/*    */ package com.portal.datacenter.commdata.service.impl;
/*    */ 
/*    */ import com.portal.datacenter.commdata.dao.IndustryDao;
/*    */ import com.portal.datacenter.commdata.entity.Industry;
/*    */ import com.portal.datacenter.commdata.service.IndustryService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class IndustryServiceImpl
/*    */   implements IndustryService
/*    */ {
/*    */   private IndustryDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<Industry> getPage(String key, int pageNo, int pageSize)
/*    */   {
/* 19 */     return this.dao.getPage(key, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<Industry> getIndustryList(Integer id) {
/* 24 */     return this.dao.getIndustryList(id);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<Industry> getIndustryChild(Integer id) {
/* 29 */     return this.dao.getIndustryChild(id);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Industry findByCode(String code) {
/* 34 */     return this.dao.findByCode(code);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Industry findById(Integer id) {
/* 39 */     Industry entity = (Industry)this.dao.findById(id);
/* 40 */     return entity;
/*    */   }
/*    */ 
/*    */   public Industry save(Industry bean, Integer parentId) {
/* 44 */     if (parentId != null) {
/* 45 */       bean.setParent(findById(parentId));
/*    */     }
/* 47 */     this.dao.save(bean);
/* 48 */     return bean;
/*    */   }
/*    */ 
/*    */   public Industry update(Industry bean, Integer parentId) {
/* 52 */     bean = (Industry)this.dao.update(bean);
/* 53 */     if (parentId != null)
/* 54 */       bean.setParent(findById(parentId));
/*    */     else {
/* 56 */       bean.setParent(null);
/*    */     }
/* 58 */     return bean;
/*    */   }
/*    */ 
/*    */   public Industry deleteById(Integer id) {
/* 62 */     Industry bean = (Industry)this.dao.deleteById(id);
/* 63 */     return bean;
/*    */   }
/*    */ 
/*    */   public Industry[] deleteByIds(Integer[] ids) {
/* 67 */     Industry[] beans = new Industry[ids.length];
/* 68 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 69 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 71 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(IndustryDao dao)
/*    */   {
/* 78 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.service.impl.IndustryServiceImpl
 * JD-Core Version:    0.6.1
 */
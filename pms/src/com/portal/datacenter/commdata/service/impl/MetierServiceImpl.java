/*    */ package com.portal.datacenter.commdata.service.impl;
/*    */ 
/*    */ import com.portal.datacenter.commdata.dao.MetierDao;
/*    */ import com.portal.datacenter.commdata.entity.Metier;
/*    */ import com.portal.datacenter.commdata.service.MetierService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class MetierServiceImpl
/*    */   implements MetierService
/*    */ {
/*    */   private MetierDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<Metier> getPage(String key, int pageNo, int pageSize)
/*    */   {
/* 19 */     return this.dao.getPage(key, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<Metier> getMetierList(Integer id) {
/* 24 */     return this.dao.getMetierList(id);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<Metier> getMetierChild(Integer id) {
/* 29 */     return this.dao.getMetierChild(id);
/*    */   }
/*    */ 
/*    */   public Metier findByCode(String code) {
/* 33 */     return this.dao.findByCode(code);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Metier findById(Integer id) {
/* 38 */     Metier entity = (Metier)this.dao.findById(id);
/* 39 */     return entity;
/*    */   }
/*    */ 
/*    */   public Metier save(Metier bean, Integer parentId) {
/* 43 */     if (parentId != null) {
/* 44 */       bean.setParent(findById(parentId));
/*    */     }
/* 46 */     this.dao.save(bean);
/* 47 */     return bean;
/*    */   }
/*    */ 
/*    */   public Metier update(Metier bean, Integer parentId) {
/* 51 */     bean = (Metier)this.dao.update(bean);
/* 52 */     if (parentId != null)
/* 53 */       bean.setParent(findById(parentId));
/*    */     else {
/* 55 */       bean.setParent(null);
/*    */     }
/* 57 */     return bean;
/*    */   }
/*    */ 
/*    */   public Metier deleteById(Integer id) {
/* 61 */     Metier bean = (Metier)this.dao.deleteById(id);
/* 62 */     return bean;
/*    */   }
/*    */ 
/*    */   public Metier[] deleteByIds(Integer[] ids) {
/* 66 */     Metier[] beans = new Metier[ids.length];
/* 67 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 68 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 70 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(MetierDao dao)
/*    */   {
/* 77 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.service.impl.MetierServiceImpl
 * JD-Core Version:    0.6.1
 */
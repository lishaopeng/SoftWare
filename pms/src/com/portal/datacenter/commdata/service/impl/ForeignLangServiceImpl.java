/*    */ package com.portal.datacenter.commdata.service.impl;
/*    */ 
/*    */ import com.portal.datacenter.commdata.dao.ForeignLangDao;
/*    */ import com.portal.datacenter.commdata.entity.ForeignLang;
/*    */ import com.portal.datacenter.commdata.service.ForeignLangService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ForeignLangServiceImpl
/*    */   implements ForeignLangService
/*    */ {
/*    */   private ForeignLangDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<ForeignLang> getPage(int pageNo, int pageSize)
/*    */   {
/* 19 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<ForeignLang> getForeignLangList() {
/* 24 */     return this.dao.getForeignLangList();
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public ForeignLang findById(Integer id) {
/* 29 */     ForeignLang entity = (ForeignLang)this.dao.findById(id);
/* 30 */     return entity;
/*    */   }
/*    */ 
/*    */   public ForeignLang save(ForeignLang bean) {
/* 34 */     this.dao.save(bean);
/* 35 */     return bean;
/*    */   }
/*    */ 
/*    */   public ForeignLang update(ForeignLang bean) {
/* 39 */     bean = (ForeignLang)this.dao.update(bean);
/* 40 */     return bean;
/*    */   }
/*    */ 
/*    */   public ForeignLang deleteById(Integer id) {
/* 44 */     ForeignLang bean = (ForeignLang)this.dao.deleteById(id);
/* 45 */     return bean;
/*    */   }
/*    */ 
/*    */   public ForeignLang[] deleteByIds(Integer[] ids) {
/* 49 */     ForeignLang[] beans = new ForeignLang[ids.length];
/* 50 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 51 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 53 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ForeignLangDao dao)
/*    */   {
/* 60 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.service.impl.ForeignLangServiceImpl
 * JD-Core Version:    0.6.1
 */
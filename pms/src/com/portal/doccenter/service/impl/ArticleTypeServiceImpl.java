/*    */ package com.portal.doccenter.service.impl;
/*    */ 
/*    */ import com.portal.doccenter.dao.ArticleTypeDao;
/*    */ import com.portal.doccenter.entity.ArticleType;
/*    */ import com.portal.doccenter.service.ArticleTypeService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ArticleTypeServiceImpl
/*    */   implements ArticleTypeService
/*    */ {
/*    */   private ArticleTypeDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<ArticleType> getList(boolean containDisabled, String sortname, String sortorder)
/*    */   {
/* 19 */     return this.dao.getList(containDisabled, sortname, sortorder);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public ArticleType getDef() {
/* 24 */     return this.dao.getDef();
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public ArticleType findById(Integer id) {
/* 29 */     ArticleType entity = (ArticleType)this.dao.findById(id);
/* 30 */     return entity;
/*    */   }
/*    */ 
/*    */   public ArticleType save(ArticleType bean) {
/* 34 */     this.dao.save(bean);
/* 35 */     return bean;
/*    */   }
/*    */ 
/*    */   public ArticleType update(ArticleType bean) {
/* 39 */     ArticleType entity = (ArticleType)this.dao.update(bean);
/* 40 */     return entity;
/*    */   }
/*    */ 
/*    */   public ArticleType deleteById(Integer id) {
/* 44 */     ArticleType bean = (ArticleType)this.dao.deleteById(id);
/* 45 */     return bean;
/*    */   }
/*    */ 
/*    */   public ArticleType[] deleteByIds(Integer[] ids) {
/* 49 */     ArticleType[] beans = new ArticleType[ids.length];
/* 50 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 51 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 53 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ArticleTypeDao dao)
/*    */   {
/* 60 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.service.impl.ArticleTypeServiceImpl
 * JD-Core Version:    0.6.1
 */
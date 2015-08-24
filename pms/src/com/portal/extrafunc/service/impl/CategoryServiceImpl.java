/*    */ package com.portal.extrafunc.service.impl;
/*    */ 
/*    */ import com.portal.extrafunc.dao.CategoryDao;
/*    */ import com.portal.extrafunc.entity.Category;
/*    */ import com.portal.extrafunc.service.CategoryService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.event.DelCategoryEvent;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class CategoryServiceImpl
/*    */   implements CategoryService
/*    */ {
/*    */   private CategoryDao dao;
/*    */   private ApplicationContext applicationContext;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<Category> getPage(String sortname, String sortorder, int pageNo, int pageSize)
/*    */   {
/* 23 */     return this.dao.getPage(sortname, sortorder, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<Category> getList(Integer siteId, String sortname, String sortorder)
/*    */   {
/* 29 */     return this.dao.getList(siteId, sortname, sortorder);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Category findById(Integer id) {
/* 34 */     Category entity = (Category)this.dao.findById(id);
/* 35 */     return entity;
/*    */   }
/*    */ 
/*    */   public Category save(Category bean, Site site) {
/* 39 */     bean.setSite(site);
/* 40 */     this.dao.save(bean);
/* 41 */     return bean;
/*    */   }
/*    */ 
/*    */   public Category update(Category bean) {
/* 45 */     bean = (Category)this.dao.update(bean);
/* 46 */     return bean;
/*    */   }
/*    */ 
/*    */   public int deleteBySiteId(Integer siteId) {
/* 50 */     return this.dao.deleteBySiteId(siteId);
/*    */   }
/*    */ 
/*    */   public Category deleteById(Integer id) {
/* 54 */     Category bean = (Category)this.dao.deleteById(id);
/* 55 */     this.applicationContext.publishEvent(new DelCategoryEvent(bean));
/* 56 */     return bean;
/*    */   }
/*    */ 
/*    */   public Category[] deleteByIds(Integer[] ids) {
/* 60 */     Category[] beans = new Category[ids.length];
/* 61 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 62 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 64 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(CategoryDao dao)
/*    */   {
/* 72 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setApplicationContext(ApplicationContext applicationContext) {
/* 77 */     this.applicationContext = applicationContext;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.CategoryServiceImpl
 * JD-Core Version:    0.6.1
 */
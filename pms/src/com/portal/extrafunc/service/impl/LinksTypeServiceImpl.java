/*    */ package com.portal.extrafunc.service.impl;
/*    */ 
/*    */ import com.portal.extrafunc.dao.LinksTypeDao;
/*    */ import com.portal.extrafunc.entity.LinksType;
/*    */ import com.portal.extrafunc.service.LinksTypeService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class LinksTypeServiceImpl
/*    */   implements LinksTypeService
/*    */ {
/*    */   private LinksTypeDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<LinksType> getPage(int pageNo, int pageSize)
/*    */   {
/* 20 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public LinksType findById(Integer id) {
/* 25 */     LinksType entity = (LinksType)this.dao.findById(id);
/* 26 */     return entity;
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<LinksType> getList(Integer siteId, String sortname, String sortorder)
/*    */   {
/* 32 */     return this.dao.getList(siteId, sortname, sortorder);
/*    */   }
/*    */ 
/*    */   public LinksType save(LinksType bean, Site site) {
/* 36 */     bean.setSite(site);
/* 37 */     bean.init();
/* 38 */     this.dao.save(bean);
/* 39 */     return bean;
/*    */   }
/*    */ 
/*    */   public LinksType update(LinksType bean) {
/* 43 */     bean = (LinksType)this.dao.update(bean);
/* 44 */     return bean;
/*    */   }
/*    */ 
/*    */   public LinksType deleteById(Integer id) {
/* 48 */     LinksType bean = (LinksType)this.dao.deleteById(id);
/* 49 */     return bean;
/*    */   }
/*    */ 
/*    */   public LinksType[] deleteByIds(Integer[] ids) {
/* 53 */     LinksType[] beans = new LinksType[ids.length];
/* 54 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 55 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 57 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(LinksTypeDao dao)
/*    */   {
/* 64 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.LinksTypeServiceImpl
 * JD-Core Version:    0.6.1
 */
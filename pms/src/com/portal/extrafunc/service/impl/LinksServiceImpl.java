/*    */ package com.portal.extrafunc.service.impl;
/*    */ 
/*    */ import com.portal.extrafunc.dao.LinksDao;
/*    */ import com.portal.extrafunc.entity.Links;
/*    */ import com.portal.extrafunc.service.LinksService;
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
/*    */ public class LinksServiceImpl
/*    */   implements LinksService
/*    */ {
/*    */   private LinksDao dao;
/*    */   private LinksTypeService typeService;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<Links> getPage(int pageNo, int pageSize)
/*    */   {
/* 21 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Links findById(Integer id) {
/* 26 */     Links entity = (Links)this.dao.findById(id);
/* 27 */     return entity;
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<Links> getPage(Integer siteId, Integer typeId, String sortname, String sortorder, int pageNo, int pageSize)
/*    */   {
/* 33 */     return this.dao.getPage(siteId, typeId, sortname, sortorder, pageNo, 
/* 34 */       pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<Links> getListByTag(Integer siteId, Integer typeId, Integer count)
/*    */   {
/* 40 */     return this.dao.getListByTag(siteId, typeId, count);
/*    */   }
/*    */ 
/*    */   public Links save(Links bean, Integer typeId, Site site) {
/* 44 */     if (typeId != null) {
/* 45 */       bean.setType(this.typeService.findById(typeId));
/*    */     }
/* 47 */     bean.setSite(site);
/* 48 */     bean.init();
/* 49 */     this.dao.save(bean);
/* 50 */     return bean;
/*    */   }
/*    */ 
/*    */   public Links update(Links bean, Integer typeId) {
/* 54 */     bean = (Links)this.dao.update(bean);
/* 55 */     if (typeId != null) {
/* 56 */       bean.setType(this.typeService.findById(typeId));
/*    */     }
/* 58 */     return bean;
/*    */   }
/*    */ 
/*    */   public Links deleteById(Integer id) {
/* 62 */     Links bean = (Links)this.dao.deleteById(id);
/* 63 */     return bean;
/*    */   }
/*    */ 
/*    */   public Links[] deleteByIds(Integer[] ids) {
/* 67 */     Links[] beans = new Links[ids.length];
/* 68 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 69 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 71 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(LinksDao dao)
/*    */   {
/* 79 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setTypeService(LinksTypeService typeService) {
/* 84 */     this.typeService = typeService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.LinksServiceImpl
 * JD-Core Version:    0.6.1
 */
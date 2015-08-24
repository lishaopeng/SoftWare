/*    */ package com.portal.extrafunc.service.impl;
/*    */ 
/*    */ import com.portal.extrafunc.dao.AdvertSlotDao;
/*    */ import com.portal.extrafunc.entity.AdvertSlot;
/*    */ import com.portal.extrafunc.service.AdvertSlotService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.event.DelAdvertSlotEvent;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class AdvertSlotServiceImpl
/*    */   implements AdvertSlotService
/*    */ {
/*    */   private AdvertSlotDao dao;
/*    */   private ApplicationContext applicationContext;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<AdvertSlot> getPage(Integer siteId, String sortname, String sortorder, int pageNo, int pageSize)
/*    */   {
/* 23 */     return this.dao.getPage(siteId, sortname, sortorder, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<AdvertSlot> getList(Integer siteId) {
/* 28 */     return this.dao.getList(siteId);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public AdvertSlot findById(Integer id) {
/* 33 */     AdvertSlot entity = (AdvertSlot)this.dao.findById(id);
/* 34 */     return entity;
/*    */   }
/*    */ 
/*    */   public AdvertSlot save(AdvertSlot bean, Site site) {
/* 38 */     bean.setSite(site);
/* 39 */     bean.init();
/* 40 */     this.dao.save(bean);
/* 41 */     return bean;
/*    */   }
/*    */ 
/*    */   public AdvertSlot update(AdvertSlot bean) {
/* 45 */     bean.init();
/* 46 */     bean = (AdvertSlot)this.dao.update(bean);
/* 47 */     return bean;
/*    */   }
/*    */ 
/*    */   public int deleteBySiteId(Integer siteId) {
/* 51 */     return this.dao.deleteBySiteId(siteId);
/*    */   }
/*    */ 
/*    */   public AdvertSlot deleteById(Integer id) {
/* 55 */     AdvertSlot bean = (AdvertSlot)this.dao.deleteById(id);
/* 56 */     this.applicationContext.publishEvent(new DelAdvertSlotEvent(bean));
/* 57 */     return bean;
/*    */   }
/*    */ 
/*    */   public AdvertSlot[] deleteByIds(Integer[] ids) {
/* 61 */     AdvertSlot[] beans = new AdvertSlot[ids.length];
/* 62 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 63 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 65 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(AdvertSlotDao dao)
/*    */   {
/* 73 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setApplicationContext(ApplicationContext applicationContext) {
/* 78 */     this.applicationContext = applicationContext;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.AdvertSlotServiceImpl
 * JD-Core Version:    0.6.1
 */
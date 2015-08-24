/*     */ package com.portal.extrafunc.service.impl;
/*     */ 
/*     */ import com.portal.extrafunc.dao.AdvertDao;
/*     */ import com.portal.extrafunc.entity.Advert;
/*     */ import com.portal.extrafunc.service.AdvertService;
/*     */ import com.portal.extrafunc.service.AdvertSlotService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import java.util.List;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class AdvertServiceImpl
/*     */   implements AdvertService
/*     */ {
/*     */   private AdvertDao dao;
/*     */   private AdvertSlotService slotService;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Page<Advert> getPage(Integer siteId, Integer slotId, String sortname, String sortorder, int pageNo, int pageSize)
/*     */   {
/*  23 */     return this.dao.getPage(siteId, slotId, sortname, sortorder, pageNo, 
/*  24 */       pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Advert> getListByTag(Integer siteId, Integer slotId) {
/*  29 */     return this.dao.getListByTag(siteId, slotId);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Advert findById(Integer id) {
/*  34 */     Advert entity = (Advert)this.dao.findById(id);
/*  35 */     return entity;
/*     */   }
/*     */ 
/*     */   public Advert save(Advert bean, Integer slotId, boolean advtype, Site site) {
/*  39 */     if (slotId != null) {
/*  40 */       bean.setSlot(this.slotService.findById(slotId));
/*     */     }
/*  42 */     if (advtype) {
/*  43 */       bean.setAdvType("js");
/*     */     }
/*  45 */     else if ((!StringUtils.isBlank(bean.getAttrUrl())) && 
/*  46 */       (bean.getAttrUrl().indexOf("swf") > -1))
/*  47 */       bean.setAdvType("flash");
/*     */     else {
/*  49 */       bean.setAdvType("img");
/*     */     }
/*     */ 
/*  52 */     bean.setSite(site);
/*  53 */     bean.init();
/*  54 */     this.dao.save(bean);
/*  55 */     return bean;
/*     */   }
/*     */ 
/*     */   public Advert update(Advert bean, Integer slotId, boolean advtype) {
/*  59 */     bean = (Advert)this.dao.update(bean);
/*  60 */     if (slotId != null) {
/*  61 */       bean.setSlot(this.slotService.findById(slotId));
/*     */     }
/*  63 */     if (advtype) {
/*  64 */       bean.setAdvType("js");
/*     */     }
/*  66 */     else if ((!StringUtils.isBlank(bean.getAttrUrl())) && 
/*  67 */       (bean.getAttrUrl().indexOf("swf") > -1))
/*  68 */       bean.setAdvType("flash");
/*     */     else {
/*  70 */       bean.setAdvType("img");
/*     */     }
/*     */ 
/*  73 */     return bean;
/*     */   }
/*     */ 
/*     */   public int deleteBySlotId(Integer slotId) {
/*  77 */     return this.dao.deleteBySlotId(slotId);
/*     */   }
/*     */ 
/*     */   public int deleteBySiteId(Integer siteId) {
/*  81 */     return this.dao.deleteBySiteId(siteId);
/*     */   }
/*     */ 
/*     */   public Advert deleteById(Integer id) {
/*  85 */     Advert bean = (Advert)this.dao.deleteById(id);
/*  86 */     return bean;
/*     */   }
/*     */ 
/*     */   public Advert[] deleteByIds(Integer[] ids) {
/*  90 */     Advert[] beans = new Advert[ids.length];
/*  91 */     int i = 0; for (int len = ids.length; i < len; i++) {
/*  92 */       beans[i] = deleteById(ids[i]);
/*     */     }
/*  94 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(AdvertDao dao)
/*     */   {
/* 102 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setSlotService(AdvertSlotService slotService) {
/* 107 */     this.slotService = slotService;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.AdvertServiceImpl
 * JD-Core Version:    0.6.1
 */
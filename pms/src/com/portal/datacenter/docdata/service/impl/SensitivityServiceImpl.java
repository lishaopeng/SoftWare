/*    */ package com.portal.datacenter.docdata.service.impl;
/*    */ 
/*    */ /*    */ import java.util.List;

/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;

import com.portal.datacenter.docdata.dao.SensitivityDao;
/*    */ import com.portal.datacenter.docdata.entity.Sensitivity;
/*    */ import com.portal.datacenter.docdata.service.SensitivityService;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class SensitivityServiceImpl
/*    */   implements SensitivityService
/*    */ {
/*    */   private SensitivityDao dao;
/*    */ 
/*    */   @Override
@Transactional(readOnly=true)
/*    */   public String replaceSensitivity(String s)
/*    */   {
/* 19 */     if (StringUtils.isBlank(s)) {
/* 20 */       return s;
/*    */     }
		/* 22 */List<Sensitivity> list = getList(true, null, null);
/* 23 */     for (Sensitivity sen : list) {
/* 24 */       s = StringUtils.replace(s, sen.getSearch(), sen.getReplacement());
/*    */     }
/* 26 */     return s;
/*    */   }
/*    */ 
/*    */   @Override
@Transactional(readOnly=true)
/*    */   public List<Sensitivity> getList(boolean cacheable, String sortname, String sortorder)
/*    */   {
/* 32 */     return this.dao.getList(cacheable, sortname, sortorder);
/*    */   }
/*    */ 
/*    */   @Override
@Transactional(readOnly=true)
/*    */   public Sensitivity findById(Integer id) {
/* 37 */     Sensitivity entity = this.dao.findById(id);
/* 38 */     return entity;
/*    */   }
/*    */ 
/*    */   @Override
public Sensitivity save(Sensitivity bean) {
/* 42 */     this.dao.save(bean);
/* 43 */     return bean;
/*    */   }
/*    */ 
/*    */   @Override
public Sensitivity update(Sensitivity bean) {
/* 47 */     bean = this.dao.update(bean);
/* 48 */     return bean;
/*    */   }
/*    */ 
/*    */   @Override
public Sensitivity deleteById(Integer id) {
/* 52 */     Sensitivity bean = this.dao.deleteById(id);
/* 53 */     return bean;
/*    */   }
/*    */ 
/*    */   @Override
public Sensitivity[] deleteByIds(Integer[] ids) {
/* 57 */     Sensitivity[] beans = new Sensitivity[ids.length];
/* 58 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 59 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 61 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(SensitivityDao dao)
/*    */   {
/* 68 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.docdata.service.impl.SensitivityServiceImpl
 * JD-Core Version:    0.6.1
 */
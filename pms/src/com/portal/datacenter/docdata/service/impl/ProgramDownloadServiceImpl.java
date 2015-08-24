/*    */ package com.portal.datacenter.docdata.service.impl;
/*    */ 
/*    */ import com.portal.datacenter.docdata.dao.ProgramDownloadDao;
/*    */ import com.portal.datacenter.docdata.entity.ProgramDownload;
/*    */ import com.portal.datacenter.docdata.service.ProgramDownloadService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ProgramDownloadServiceImpl
/*    */   implements ProgramDownloadService
/*    */ {
/*    */   private ProgramDownloadDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<ProgramDownload> getPage(int pageNo, int pageSize)
/*    */   {
/* 17 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public ProgramDownload findUnique() {
/* 22 */     return this.dao.findUnique();
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public ProgramDownload findById(Integer id) {
/* 27 */     ProgramDownload entity = (ProgramDownload)this.dao.findById(id);
/* 28 */     return entity;
/*    */   }
/*    */ 
/*    */   public ProgramDownload save() {
/* 32 */     ProgramDownload bean = new ProgramDownload();
/* 33 */     bean.setCount(Integer.valueOf(1));
/* 34 */     this.dao.save(bean);
/* 35 */     return bean;
/*    */   }
/*    */ 
/*    */   public ProgramDownload updateCount() {
/* 39 */     ProgramDownload pd = findUnique();
/* 40 */     if (pd != null)
/* 41 */       pd.setCount(Integer.valueOf(pd.getCount().intValue() + 1));
/*    */     else {
/* 43 */       pd = save();
/*    */     }
/* 45 */     return pd;
/*    */   }
/*    */ 
/*    */   public ProgramDownload update(ProgramDownload bean) {
/* 49 */     bean = (ProgramDownload)this.dao.update(bean);
/* 50 */     return bean;
/*    */   }
/*    */ 
/*    */   public ProgramDownload deleteById(Integer id) {
/* 54 */     ProgramDownload bean = (ProgramDownload)this.dao.deleteById(id);
/* 55 */     return bean;
/*    */   }
/*    */ 
/*    */   public ProgramDownload[] deleteByIds(Integer[] ids) {
/* 59 */     ProgramDownload[] beans = new ProgramDownload[ids.length];
/* 60 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 61 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 63 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ProgramDownloadDao dao)
/*    */   {
/* 70 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.docdata.service.impl.ProgramDownloadServiceImpl
 * JD-Core Version:    0.6.1
 */
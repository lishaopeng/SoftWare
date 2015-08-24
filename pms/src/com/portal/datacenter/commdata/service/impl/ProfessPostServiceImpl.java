/*    */ package com.portal.datacenter.commdata.service.impl;
/*    */ 
/*    */ import com.portal.datacenter.commdata.dao.ProfessPostDao;
/*    */ import com.portal.datacenter.commdata.entity.ProfessPost;
/*    */ import com.portal.datacenter.commdata.service.ProfessPostService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ProfessPostServiceImpl
/*    */   implements ProfessPostService
/*    */ {
/*    */   private ProfessPostDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<ProfessPost> getPage(int pageNo, int pageSize)
/*    */   {
/* 19 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<ProfessPost> getProfessPostList(Integer id) {
/* 24 */     return this.dao.getProfessPostList(id);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public ProfessPost findById(Integer id) {
/* 29 */     ProfessPost entity = (ProfessPost)this.dao.findById(id);
/* 30 */     return entity;
/*    */   }
/*    */ 
/*    */   public ProfessPost save(ProfessPost bean) {
/* 34 */     this.dao.save(bean);
/* 35 */     return bean;
/*    */   }
/*    */ 
/*    */   public ProfessPost update(ProfessPost bean) {
/* 39 */     bean = (ProfessPost)this.dao.update(bean);
/* 40 */     return bean;
/*    */   }
/*    */ 
/*    */   public ProfessPost deleteById(Integer id) {
/* 44 */     ProfessPost bean = (ProfessPost)this.dao.deleteById(id);
/* 45 */     return bean;
/*    */   }
/*    */ 
/*    */   public ProfessPost[] deleteByIds(Integer[] ids) {
/* 49 */     ProfessPost[] beans = new ProfessPost[ids.length];
/* 50 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 51 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 53 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ProfessPostDao dao)
/*    */   {
/* 60 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.service.impl.ProfessPostServiceImpl
 * JD-Core Version:    0.6.1
 */
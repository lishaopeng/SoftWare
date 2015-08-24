/*    */ package com.portal.extrafunc.service.impl;
/*    */ 
/*    */ import com.portal.extrafunc.dao.QuestionnaireDao;
/*    */ import com.portal.extrafunc.entity.Questionnaire;
/*    */ import com.portal.extrafunc.service.QuestionnaireService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class QuestionnaireServiceImpl
/*    */   implements QuestionnaireService
/*    */ {
/*    */   private QuestionnaireDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<Questionnaire> getPage(Integer siteId, boolean enable, int pageNo, int pageSize)
/*    */   {
/* 19 */     return this.dao.getPage(siteId, enable, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Questionnaire findById(Integer id) {
/* 24 */     Questionnaire entity = (Questionnaire)this.dao.findById(id);
/* 25 */     return entity;
/*    */   }
/*    */ 
/*    */   public Questionnaire save(Questionnaire bean, Site site) {
/* 29 */     bean.setSite(site);
/* 30 */     bean.init();
/* 31 */     this.dao.save(bean);
/* 32 */     return bean;
/*    */   }
/*    */ 
/*    */   public Questionnaire update(Questionnaire bean) {
/* 36 */     bean.updateinit();
/* 37 */     bean = (Questionnaire)this.dao.update(bean);
/* 38 */     return bean;
/*    */   }
/*    */ 
/*    */   public Questionnaire deleteById(Integer id) {
/* 42 */     Questionnaire bean = (Questionnaire)this.dao.deleteById(id);
/* 43 */     return bean;
/*    */   }
/*    */ 
/*    */   public Questionnaire[] deleteByIds(Integer[] ids) {
/* 47 */     Questionnaire[] beans = new Questionnaire[ids.length];
/* 48 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 49 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 51 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(QuestionnaireDao dao)
/*    */   {
/* 58 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.QuestionnaireServiceImpl
 * JD-Core Version:    0.6.1
 */
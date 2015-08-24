/*    */ package com.portal.extrafunc.service.impl;
/*    */ 
/*    */ import com.portal.extrafunc.dao.SurveyDetailDao;
/*    */ import com.portal.extrafunc.entity.SurveyDetail;
/*    */ import com.portal.extrafunc.entity.SurveyTheme;
/*    */ import com.portal.extrafunc.service.SurveyDetailService;
/*    */ import com.portal.usermgr.entity.User;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class SurveyDetailServiceImpl
/*    */   implements SurveyDetailService
/*    */ {
/*    */   private SurveyDetailDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<SurveyDetail> getPage(Integer surveyId, int pageNo, int pageSize)
/*    */   {
/* 19 */     return this.dao.getPage(surveyId, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public SurveyDetail findById(Integer id) {
/* 24 */     SurveyDetail entity = (SurveyDetail)this.dao.findById(id);
/* 25 */     return entity;
/*    */   }
/*    */ 
/*    */   public SurveyDetail save(String content, SurveyTheme st, User user) {
/* 29 */     SurveyDetail bean = new SurveyDetail();
/* 30 */     bean.setSurvey(st);
/* 31 */     bean.setUser(user);
/* 32 */     bean.setContent(content);
/* 33 */     bean.init();
/* 34 */     this.dao.save(bean);
/* 35 */     return bean;
/*    */   }
/*    */ 
/*    */   public SurveyDetail update(SurveyDetail bean) {
/* 39 */     bean = (SurveyDetail)this.dao.update(bean);
/* 40 */     return bean;
/*    */   }
/*    */ 
/*    */   public SurveyDetail deleteById(Integer id) {
/* 44 */     SurveyDetail bean = (SurveyDetail)this.dao.deleteById(id);
/* 45 */     return bean;
/*    */   }
/*    */ 
/*    */   public SurveyDetail[] deleteByIds(Integer[] ids) {
/* 49 */     SurveyDetail[] beans = new SurveyDetail[ids.length];
/* 50 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 51 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 53 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(SurveyDetailDao dao)
/*    */   {
/* 60 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.SurveyDetailServiceImpl
 * JD-Core Version:    0.6.1
 */
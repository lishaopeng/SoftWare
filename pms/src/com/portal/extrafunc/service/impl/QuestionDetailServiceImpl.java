/*    */ package com.portal.extrafunc.service.impl;
/*    */ 
/*    */ import com.portal.extrafunc.dao.QuestionDetailDao;
/*    */ import com.portal.extrafunc.entity.QuestionDetail;
/*    */ import com.portal.extrafunc.entity.Questionnaire;
/*    */ import com.portal.extrafunc.service.QuestionDetailService;
/*    */ import com.portal.usermgr.entity.User;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class QuestionDetailServiceImpl
/*    */   implements QuestionDetailService
/*    */ {
/*    */   private QuestionDetailDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<QuestionDetail> getPage(Integer questionId, int pageNo, int pageSize)
/*    */   {
/* 19 */     return this.dao.getPage(questionId, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public QuestionDetail getDetail(Integer questionId, Integer userId, String ip)
/*    */   {
/* 25 */     return this.dao.getDetail(questionId, userId, ip);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public long getCountDetail(Integer questionId) {
/* 30 */     return this.dao.getCountDetail(questionId);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public QuestionDetail findById(Integer id) {
/* 35 */     QuestionDetail entity = (QuestionDetail)this.dao.findById(id);
/* 36 */     return entity;
/*    */   }
/*    */ 
/*    */   public QuestionDetail save(Questionnaire question, User user, String ip) {
/* 40 */     QuestionDetail bean = new QuestionDetail();
/* 41 */     bean.setQuestion(question);
/* 42 */     bean.setUser(user);
/* 43 */     bean.setIp(ip);
/* 44 */     bean.init();
/* 45 */     this.dao.save(bean);
/* 46 */     return bean;
/*    */   }
/*    */ 
/*    */   public QuestionDetail update(QuestionDetail bean) {
/* 50 */     bean = (QuestionDetail)this.dao.update(bean);
/* 51 */     return bean;
/*    */   }
/*    */ 
/*    */   public QuestionDetail deleteById(Integer id) {
/* 55 */     QuestionDetail bean = (QuestionDetail)this.dao.deleteById(id);
/* 56 */     return bean;
/*    */   }
/*    */ 
/*    */   public QuestionDetail[] deleteByIds(Integer[] ids) {
/* 60 */     QuestionDetail[] beans = new QuestionDetail[ids.length];
/* 61 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 62 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 64 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(QuestionDetailDao dao)
/*    */   {
/* 71 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.QuestionDetailServiceImpl
 * JD-Core Version:    0.6.1
 */
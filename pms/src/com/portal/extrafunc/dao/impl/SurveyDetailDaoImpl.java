/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.SurveyDetailDao;
/*    */ import com.portal.extrafunc.entity.SurveyDetail;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class SurveyDetailDaoImpl extends QueryDaoImpl<SurveyDetail, Integer>
/*    */   implements SurveyDetailDao
/*    */ {
/*    */   public Page<SurveyDetail> getPage(Integer surveyId, int pageNo, int pageSize)
/*    */   {
/* 17 */     Criteria crit = createCriteria();
/* 18 */     if (surveyId != null) {
/* 19 */       crit.add(Restrictions.eq("survey.id", surveyId));
/*    */     }
/* 21 */     crit.addOrder(Order.desc("createTime"));
/* 22 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public SurveyDetail getDetailByUser(Integer surveyId, Integer userId) {
/* 26 */     Criteria crit = createCriteria();
/* 27 */     if (surveyId != null) {
/* 28 */       crit.add(Restrictions.eq("survey.id", surveyId));
/*    */     }
/* 30 */     if (userId != null) {
/* 31 */       crit.add(Restrictions.eq("user.id", surveyId));
/*    */     }
/* 33 */     return (SurveyDetail)findUnique(crit);
/*    */   }
/*    */ 
/*    */   protected Class<SurveyDetail> getEntityClass()
/*    */   {
/* 38 */     return SurveyDetail.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.SurveyDetailDaoImpl
 * JD-Core Version:    0.6.1
 */
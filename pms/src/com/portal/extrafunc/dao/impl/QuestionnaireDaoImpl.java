/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.QuestionnaireDao;
/*    */ import com.portal.extrafunc.entity.Questionnaire;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class QuestionnaireDaoImpl extends QueryDaoImpl<Questionnaire, Integer>
/*    */   implements QuestionnaireDao
/*    */ {
/*    */   public Page<Questionnaire> getPage(Integer siteId, boolean enable, int pageNo, int pageSize)
/*    */   {
/* 17 */     Criteria crit = createCriteria();
/* 18 */     crit.add(Restrictions.eq("site.id", siteId));
/* 19 */     if (enable) {
/* 20 */       crit.add(Restrictions.eq("enable", Boolean.valueOf(true)));
/*    */     }
/* 22 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   protected Class<Questionnaire> getEntityClass()
/*    */   {
/* 27 */     return Questionnaire.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.QuestionnaireDaoImpl
 * JD-Core Version:    0.6.1
 */
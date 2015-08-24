/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.SurveyThemeDao;
/*    */ import com.portal.extrafunc.entity.SurveyTheme;
/*    */ import java.util.List;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class SurveyThemeDaoImpl extends QueryDaoImpl<SurveyTheme, Integer>
/*    */   implements SurveyThemeDao
/*    */ {
/*    */   public Page<SurveyTheme> getPage(Integer naireId, int pageNo, int pageSize)
/*    */   {
/* 19 */     Criteria crit = createCriteria();
/* 20 */     crit.add(Restrictions.eq("naire.id", naireId));
/* 21 */     crit.addOrder(Order.asc("priority"));
/* 22 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public List<SurveyTheme> getList(Integer naireId) {
/* 26 */     Criteria crit = createCriteria();
/* 27 */     crit.add(Restrictions.eq("naire.id", naireId));
/* 28 */     crit.addOrder(Order.asc("priority"));
/* 29 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   protected Class<SurveyTheme> getEntityClass()
/*    */   {
/* 34 */     return SurveyTheme.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.SurveyThemeDaoImpl
 * JD-Core Version:    0.6.1
 */
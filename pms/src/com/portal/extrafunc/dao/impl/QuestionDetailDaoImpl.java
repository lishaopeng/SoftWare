/*    */ package com.portal.extrafunc.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.extrafunc.dao.QuestionDetailDao;
/*    */ import com.portal.extrafunc.entity.QuestionDetail;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Projections;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class QuestionDetailDaoImpl extends QueryDaoImpl<QuestionDetail, Integer>
/*    */   implements QuestionDetailDao
/*    */ {
/*    */   public Page<QuestionDetail> getPage(Integer questionId, int pageNo, int pageSize)
/*    */   {
/* 19 */     Criteria crit = createCriteria();
/* 20 */     if (questionId != null) {
/* 21 */       crit.add(Restrictions.eq("question.id", questionId));
/*    */     }
/* 23 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public QuestionDetail getDetail(Integer questionId, Integer userId, String ip)
/*    */   {
/* 28 */     Criteria crit = createCriteria();
/* 29 */     if (questionId != null) {
/* 30 */       crit.add(Restrictions.eq("question.id", questionId));
/*    */     }
/* 32 */     if (userId != null) {
/* 33 */       crit.add(Restrictions.eq("user.id", userId));
/*    */     }
/* 35 */     if (!StringUtils.isBlank(ip)) {
/* 36 */       crit.add(Restrictions.eq("ip", ip));
/*    */     }
/* 38 */     crit.addOrder(Order.desc("createTime"));
/* 39 */     return (QuestionDetail)findUnique(crit);
/*    */   }
/*    */ 
/*    */   public long getCountDetail(Integer questionId) {
/* 43 */     Criteria crit = createCriteria();
/* 44 */     if (questionId != null) {
/* 45 */       crit.add(Restrictions.eq("question.id", questionId));
/*    */     }
/* 47 */     crit.setProjection(Projections.count("id"));
/* 48 */     return ((Long)crit.uniqueResult()).longValue();
/*    */   }
/*    */ 
/*    */   protected Class<QuestionDetail> getEntityClass()
/*    */   {
/* 53 */     return QuestionDetail.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.QuestionDetailDaoImpl
 * JD-Core Version:    0.6.1
 */
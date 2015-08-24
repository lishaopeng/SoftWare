/*    */ package com.portal.doccenter.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.doccenter.dao.FlowStepDao;
/*    */ import com.portal.doccenter.entity.FlowStep;
/*    */ import org.hibernate.Criteria;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class FlowStepDaoImpl extends QueryDaoImpl<FlowStep, Integer>
/*    */   implements FlowStepDao
/*    */ {
/*    */   public Page<FlowStep> getPage(int pageNo, int pageSize)
/*    */   {
/* 15 */     Criteria crit = createCriteria();
/* 16 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   protected Class<FlowStep> getEntityClass()
/*    */   {
/* 21 */     return FlowStep.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.dao.impl.FlowStepDaoImpl
 * JD-Core Version:    0.6.1
 */
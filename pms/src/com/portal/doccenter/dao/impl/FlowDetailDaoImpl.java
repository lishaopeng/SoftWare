/*    */ package com.portal.doccenter.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.doccenter.dao.FlowDetailDao;
/*    */ import com.portal.doccenter.entity.FlowDetail;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class FlowDetailDaoImpl extends QueryDaoImpl<FlowDetail, Integer>
/*    */   implements FlowDetailDao
/*    */ {
/*    */   public Page<FlowDetail> getPage(int pageNo, int pageSize)
/*    */   {
/* 17 */     Criteria crit = createCriteria();
/* 18 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public FlowDetail getLastFlowDetail(Integer docId) {
/* 22 */     Criteria crit = createCriteria();
/* 23 */     crit.add(Restrictions.eq("doc.id", docId));
/* 24 */     crit.addOrder(Order.desc("priority"));
/* 25 */     return (FlowDetail)findUnique(crit);
/*    */   }
/*    */ 
/*    */   public FlowDetail getLastFlowDetail(Integer docId, Integer userId) {
/* 29 */     Criteria crit = createCriteria();
/* 30 */     crit.add(Restrictions.eq("doc.id", docId));
/* 31 */     crit.add(Restrictions.eq("user.id", userId));
/* 32 */     crit.add(Restrictions.eq("checked", Boolean.valueOf(true)));
/* 33 */     crit.addOrder(Order.desc("priority"));
/* 34 */     return (FlowDetail)findUnique(crit);
/*    */   }
/*    */ 
/*    */   public FlowDetail getFlowDetailByPri(Integer docId, Integer priority) {
/* 38 */     Criteria crit = createCriteria();
/* 39 */     crit.add(Restrictions.eq("doc.id", docId));
/* 40 */     crit.add(Restrictions.lt("priority", priority));
/* 41 */     crit.add(Restrictions.eq("checked", Boolean.valueOf(true)));
/* 42 */     crit.addOrder(Order.desc("priority"));
/* 43 */     return (FlowDetail)findUnique(crit);
/*    */   }
/*    */ 
/*    */   protected Class<FlowDetail> getEntityClass()
/*    */   {
/* 48 */     return FlowDetail.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.dao.impl.FlowDetailDaoImpl
 * JD-Core Version:    0.6.1
 */
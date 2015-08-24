/*    */ package com.portal.doccenter.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.doccenter.dao.WorkFlowDao;
/*    */ import com.portal.doccenter.entity.WorkFlow;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class WorkFlowDaoImpl extends QueryDaoImpl<WorkFlow, Integer>
/*    */   implements WorkFlowDao
/*    */ {
/*    */   public Page<WorkFlow> getPage(Integer siteId, String sortname, String sortorder, int pageNo, int pageSize)
/*    */   {
/* 21 */     Criteria crit = createCriteria();
/* 22 */     crit.add(Restrictions.eq("site.id", siteId));
/* 23 */     if (!StringUtils.isBlank(sortname)) {
/* 24 */       if ("asc".equals(sortorder))
/* 25 */         crit.addOrder(Order.asc(sortname));
/*    */       else
/* 27 */         crit.addOrder(Order.desc(sortname));
/*    */     }
/*    */     else {
/* 30 */       crit.addOrder(Order.asc("id"));
/*    */     }
/* 32 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public List<WorkFlow> findByList(Integer siteId) {
/* 36 */     Criteria crit = createCriteria();
/* 37 */     crit.add(Restrictions.eq("site.id", siteId));
/* 38 */     crit.addOrder(Order.desc("createTime"));
/* 39 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   protected Class<WorkFlow> getEntityClass()
/*    */   {
/* 44 */     return WorkFlow.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.dao.impl.WorkFlowDaoImpl
 * JD-Core Version:    0.6.1
 */
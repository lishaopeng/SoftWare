/*    */ package com.portal.datacenter.docdata.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.datacenter.docdata.dao.SensitivityDao;
/*    */ import com.portal.datacenter.docdata.entity.Sensitivity;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class SensitivityDaoImpl extends QueryDaoImpl<Sensitivity, Integer>
/*    */   implements SensitivityDao
/*    */ {
/*    */   public List<Sensitivity> getList(boolean cacheable, String sortname, String sortorder)
/*    */   {
/* 20 */     Criteria crit = createCriteria();
/* 21 */     if (!StringUtils.isBlank(sortname)) {
/* 22 */       if ("asc".equals(sortorder))
/* 23 */         crit.addOrder(Order.asc(sortname));
/*    */       else
/* 25 */         crit.addOrder(Order.desc(sortname));
/*    */     }
/*    */     else {
/* 28 */       crit.addOrder(Order.desc("id"));
/*    */     }
/* 30 */     crit.setCacheable(cacheable);
/* 31 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   protected Class<Sensitivity> getEntityClass()
/*    */   {
/* 36 */     return Sensitivity.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.docdata.dao.impl.SensitivityDaoImpl
 * JD-Core Version:    0.6.1
 */
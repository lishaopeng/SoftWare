/*    */ package com.portal.datacenter.commdata.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.datacenter.commdata.dao.UnitTypeDao;
/*    */ import com.portal.datacenter.commdata.entity.UnitType;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class UnitTypeDaoImpl extends QueryDaoImpl<UnitType, Integer>
/*    */   implements UnitTypeDao
/*    */ {
/*    */   public Page<UnitType> getPage(int pageNo, int pageSize)
/*    */   {
/* 15 */     Criteria crit = createCriteria();
/* 16 */     crit.addOrder(Order.asc("code"));
/* 17 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   protected Class<UnitType> getEntityClass()
/*    */   {
/* 22 */     return UnitType.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.dao.impl.UnitTypeDaoImpl
 * JD-Core Version:    0.6.1
 */
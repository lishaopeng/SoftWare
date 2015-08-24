/*    */ package com.portal.datacenter.commdata.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.datacenter.commdata.dao.EconomyTypeDao;
/*    */ import com.portal.datacenter.commdata.entity.EconomyType;
/*    */ import java.util.List;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class EconomyTypeDaoImpl extends QueryDaoImpl<EconomyType, Integer>
/*    */   implements EconomyTypeDao
/*    */ {
/*    */   public Page<EconomyType> getPage(int pageNo, int pageSize)
/*    */   {
/* 19 */     Criteria crit = createCriteria();
/* 20 */     crit.addOrder(Order.asc("code"));
/* 21 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public List<EconomyType> getEconomyTypeList() {
/* 25 */     Criteria crit = createCriteria();
/* 26 */     crit.addOrder(Order.asc("code"));
/* 27 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   public EconomyType findByCode(String code) {
/* 31 */     Criteria crit = createCriteria();
/* 32 */     crit.add(Restrictions.eq("code", code));
/* 33 */     return (EconomyType)findUnique(crit);
/*    */   }
/*    */ 
/*    */   protected Class<EconomyType> getEntityClass()
/*    */   {
/* 38 */     return EconomyType.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.dao.impl.EconomyTypeDaoImpl
 * JD-Core Version:    0.6.1
 */
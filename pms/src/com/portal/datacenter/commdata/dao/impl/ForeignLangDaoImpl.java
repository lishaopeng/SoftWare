/*    */ package com.portal.datacenter.commdata.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.datacenter.commdata.dao.ForeignLangDao;
/*    */ import com.portal.datacenter.commdata.entity.ForeignLang;
/*    */ import java.util.List;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class ForeignLangDaoImpl extends QueryDaoImpl<ForeignLang, Integer>
/*    */   implements ForeignLangDao
/*    */ {
/*    */   public Page<ForeignLang> getPage(int pageNo, int pageSize)
/*    */   {
/* 18 */     Criteria crit = createCriteria();
/* 19 */     crit.addOrder(Order.asc("code"));
/* 20 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public List<ForeignLang> getForeignLangList() {
/* 24 */     Criteria crit = createCriteria();
/* 25 */     crit.addOrder(Order.asc("code"));
/* 26 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   protected Class<ForeignLang> getEntityClass()
/*    */   {
/* 31 */     return ForeignLang.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.commdata.dao.impl.ForeignLangDaoImpl
 * JD-Core Version:    0.6.1
 */
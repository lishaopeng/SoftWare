/*    */ package com.portal.govcenter.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.govcenter.dao.MailboxTypeDao;
/*    */ import com.portal.govcenter.entity.MailboxType;
/*    */ import java.util.List;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class MailboxTypeDaoImpl extends QueryDaoImpl<MailboxType, Integer>
/*    */   implements MailboxTypeDao
/*    */ {
/*    */   public List<MailboxType> getList(Integer siteId)
/*    */   {
/* 19 */     Criteria crit = createCriteria();
/* 20 */     crit.add(Restrictions.eq("site.id", siteId));
/* 21 */     crit.addOrder(Order.asc("priority"));
/* 22 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   protected Class<MailboxType> getEntityClass()
/*    */   {
/* 27 */     return MailboxType.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.govcenter.dao.impl.MailboxTypeDaoImpl
 * JD-Core Version:    0.6.1
 */
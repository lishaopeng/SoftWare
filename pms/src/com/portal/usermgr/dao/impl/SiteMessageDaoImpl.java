/*    */ package com.portal.usermgr.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.usermgr.dao.SiteMessageDao;
/*    */ import com.portal.usermgr.entity.SiteMessage;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class SiteMessageDaoImpl extends QueryDaoImpl<SiteMessage, Integer>
/*    */   implements SiteMessageDao
/*    */ {
/*    */   public Page<SiteMessage> getPage(int pageNo, int pageSize)
/*    */   {
/* 17 */     Criteria crit = createCriteria();
/* 18 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public Page<SiteMessage> getPageByTag(Integer sendId, Integer status, int pageNo, int pageSize)
/*    */   {
/* 23 */     Criteria crit = createCriteria();
/* 24 */     if (sendId != null) {
/* 25 */       crit.add(Restrictions.eq("send.id", sendId));
/*    */     }
/* 27 */     if (status != null)
/* 28 */       crit.add(Restrictions.eq("status", status));
/*    */     else {
/* 30 */       crit.add(Restrictions.eq("status", SiteMessage.NORMAL));
/*    */     }
/* 32 */     crit.addOrder(Order.desc("createTime"));
/* 33 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   protected Class<SiteMessage> getEntityClass()
/*    */   {
/* 38 */     return SiteMessage.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.impl.SiteMessageDaoImpl
 * JD-Core Version:    0.6.1
 */
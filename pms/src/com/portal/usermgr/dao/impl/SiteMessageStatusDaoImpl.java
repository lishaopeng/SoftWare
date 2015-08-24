/*    */ package com.portal.usermgr.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.usermgr.dao.SiteMessageStatusDao;
/*    */ import com.portal.usermgr.entity.SiteMessageStatus;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class SiteMessageStatusDaoImpl extends QueryDaoImpl<SiteMessageStatus, Integer>
/*    */   implements SiteMessageStatusDao
/*    */ {
/*    */   public Page<SiteMessageStatus> getPage(int pageNo, int pageSize)
/*    */   {
/* 17 */     Criteria crit = createCriteria();
/* 18 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public SiteMessageStatus findByRecive(Integer receiveId, Integer messageId) {
/* 22 */     Criteria crit = createCriteria();
/* 23 */     crit.add(Restrictions.eq("receive.id", receiveId));
/* 24 */     crit.add(Restrictions.eq("message.id", receiveId));
/* 25 */     return (SiteMessageStatus)findUnique(crit);
/*    */   }
/*    */ 
/*    */   protected Class<SiteMessageStatus> getEntityClass()
/*    */   {
/* 30 */     return SiteMessageStatus.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.impl.SiteMessageStatusDaoImpl
 * JD-Core Version:    0.6.1
 */
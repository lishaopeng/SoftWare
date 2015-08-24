/*    */ package com.portal.govcenter.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.govcenter.dao.MailboxDao;
/*    */ import com.portal.govcenter.entity.Mailbox;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class MailboxDaoImpl extends QueryDaoImpl<Mailbox, Integer>
/*    */   implements MailboxDao
/*    */ {
/*    */   public Page<Mailbox> getPage(String name, Integer siteId, Integer departId, Integer typeId, int pageNo, int pageSize)
/*    */   {
/* 18 */     Criteria crit = createCriteria();
/* 19 */     if (!StringUtils.isBlank(name)) {
/* 20 */       crit.add(Restrictions.like("title", "%" + name + "%"));
/*    */     }
/* 22 */     crit.add(Restrictions.eq("site.id", siteId));
/* 23 */     if (typeId != null) {
/* 24 */       crit.add(Restrictions.eq("type.id", typeId));
/*    */     }
/* 26 */     if (departId != null) {
/* 27 */       crit.add(Restrictions.eq("depart.id", departId));
/* 28 */       crit.add(Restrictions.ne("status", Mailbox.DELETED));
/*    */     } else {
/* 30 */       crit.add(Restrictions.ne("status", Mailbox.DELETED));
/* 31 */       crit.add(Restrictions.ne("status", Mailbox.FORWARD));
/*    */     }
/* 33 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public Page<Mailbox> getPageByTag(String name, Integer siteId, Integer departId, Integer typeId, int pageNo, int pageSize)
/*    */   {
/* 38 */     Criteria crit = createCriteria();
/* 39 */     if (!StringUtils.isBlank(name)) {
/* 40 */       crit.add(Restrictions.like("title", "%" + name + "%"));
/*    */     }
/* 42 */     crit.add(Restrictions.eq("site.id", siteId));
/* 43 */     if (typeId != null) {
/* 44 */       crit.add(Restrictions.eq("type.id", typeId));
/*    */     }
/* 46 */     if (departId != null) {
/* 47 */       crit.add(Restrictions.eq("depart.id", departId));
/*    */     }
/* 49 */     crit.add(Restrictions.ne("status", Mailbox.DELETED));
/* 50 */     crit.add(Restrictions.eq("show", Boolean.valueOf(true)));
/* 51 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   protected Class<Mailbox> getEntityClass()
/*    */   {
/* 56 */     return Mailbox.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.govcenter.dao.impl.MailboxDaoImpl
 * JD-Core Version:    0.6.1
 */
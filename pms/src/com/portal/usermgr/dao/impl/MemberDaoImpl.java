/*    */ package com.portal.usermgr.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.usermgr.dao.MemberDao;
/*    */ import com.portal.usermgr.entity.Member;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Projections;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class MemberDaoImpl extends QueryDaoImpl<Member, Integer>
/*    */   implements MemberDao
/*    */ {
/*    */   public Page<Member> getPage(int pageNo, int pageSize)
/*    */   {
/* 20 */     Criteria crit = createCriteria();
/* 21 */     Page page = findByCriteria(crit, pageNo, pageSize);
/* 22 */     return page;
/*    */   }
/*    */ 
/*    */   public Page<Member> getPage(String key, Integer siteId, Integer groupId, String sortname, String sortorder, int pageNo, int pageSize)
/*    */   {
/* 27 */     Criteria crit = createCriteria();
/* 28 */     if (groupId != null) {
/* 29 */       crit.createAlias("groups", "g");
/* 30 */       crit.add(Restrictions.eq("g.id", groupId));
/* 31 */     } else if (siteId != null) {
/* 32 */       crit.createAlias("groups", "g");
/* 33 */       crit.add(Restrictions.eq("g.site.id", siteId));
/*    */     }
/* 35 */     if (!StringUtils.isBlank(key)) {
/* 36 */       crit.add(Restrictions.or(
/* 37 */         Restrictions.like("user.username", "%" + key + "%"), 
/* 38 */         Restrictions.like("user.realName", "%" + key + "%")));
/*    */     }
/* 40 */     if (!StringUtils.isBlank(sortname)) {
/* 41 */       crit.createAlias("user", "u");
/* 42 */       if ("asc".equals(sortorder))
/* 43 */         crit.addOrder(Order.asc(sortname));
/*    */       else
/* 45 */         crit.addOrder(Order.desc(sortname));
/*    */     }
/*    */     else {
/* 48 */       crit.addOrder(Order.desc("registerTime"));
/*    */     }
/* 50 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public int getNoCheckMemberCount() {
/* 54 */     Criteria crit = createCriteria();
/* 55 */     crit.add(Restrictions.eq("status", Byte.valueOf((byte)-2)));
/* 56 */     crit.setProjection(Projections.count("id"));
/* 57 */     return ((Number)crit.uniqueResult()).intValue();
/*    */   }
/*    */ 
/*    */   protected Class<Member> getEntityClass()
/*    */   {
/* 62 */     return Member.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.impl.MemberDaoImpl
 * JD-Core Version:    0.6.1
 */
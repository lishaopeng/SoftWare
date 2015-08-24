/*    */ package com.portal.usermgr.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.usermgr.dao.AdminDao;
/*    */ import com.portal.usermgr.entity.Admin;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class AdminDaoImpl extends QueryDaoImpl<Admin, Integer>
/*    */   implements AdminDao
/*    */ {
/*    */   public Page<Admin> getPage(String key, Integer siteId, Integer departId, Integer roleId, String sortname, String sortorder, int pageNo, int pageSize)
/*    */   {
/* 22 */     Criteria crit = createCriteria();
/* 23 */     crit.createAlias("user", "u");
/* 24 */     if (roleId != null) {
/* 25 */       crit.createAlias("roles", "role");
/* 26 */       crit.add(Restrictions.eq("role.id", roleId));
/* 27 */     } else if (departId != null) {
/* 28 */       crit.createAlias("departs", "depart");
/* 29 */       crit.add(Restrictions.eq("depart.id", departId));
/* 30 */     } else if (siteId != null) {
/* 31 */       crit.createAlias("roles", "role");
/* 32 */       crit.add(Restrictions.eq("role.site.id", siteId));
/*    */     }
/* 34 */     if (!StringUtils.isBlank(key)) {
/* 35 */       crit.add(Restrictions.or(
/* 36 */         Restrictions.like("u.username", "%" + key + "%"), 
/* 37 */         Restrictions.like("u.realName", "%" + key + "%")));
/*    */     }
/* 39 */     if (!StringUtils.isBlank(sortname)) {
/* 40 */       if ("asc".equals(sortorder))
/* 41 */         crit.addOrder(Order.asc(sortname));
/*    */       else
/* 43 */         crit.addOrder(Order.desc(sortname));
/*    */     }
/*    */     else {
/* 46 */       crit.addOrder(Order.desc("registerTime"));
/*    */     }
/* 48 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public List<Admin> getListByDepart(Integer departId) {
/* 52 */     Criteria crit = createCriteria();
/* 53 */     crit.createAlias("departs", "depart");
/* 54 */     crit.add(Restrictions.eq("depart.id", departId));
/* 55 */     crit.addOrder(Order.desc("registerTime"));
/* 56 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   public List<Admin> getListByRole(Integer roleId) {
/* 60 */     Criteria crit = createCriteria();
/* 61 */     crit.createAlias("roles", "role");
/* 62 */     crit.add(Restrictions.eq("role.id", roleId));
/* 63 */     crit.addOrder(Order.desc("registerTime"));
/* 64 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   protected Class<Admin> getEntityClass()
/*    */   {
/* 69 */     return Admin.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.dao.impl.AdminDaoImpl
 * JD-Core Version:    0.6.1
 */
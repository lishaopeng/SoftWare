/*    */ package com.portal.sysmgr.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.portal.sysmgr.dao.SiteDao;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import java.util.List;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class SiteDaoImpl extends QueryDaoImpl<Site, Integer>
/*    */   implements SiteDao
/*    */ {
/*    */   public List<Site> getList(boolean cacheable)
/*    */   {
/* 18 */     Criteria crit = createCriteria();
/* 19 */     crit.addOrder(Order.asc("id"));
/* 20 */     crit.setCacheable(cacheable);
/* 21 */     return findByCriteria(crit);
/*    */   }
/*    */ 
/*    */   public Site findByDomain(String domain, boolean cacheable) {
/* 25 */     Criteria crit = createCriteria();
/* 26 */     crit.add(Restrictions.eq("domain", domain));
/* 27 */     crit.setCacheable(cacheable);
/* 28 */     return (Site)findUnique(crit);
/*    */   }
/*    */ 
/*    */   protected Class<Site> getEntityClass()
/*    */   {
/* 33 */     return Site.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.dao.impl.SiteDaoImpl
 * JD-Core Version:    0.6.1
 */
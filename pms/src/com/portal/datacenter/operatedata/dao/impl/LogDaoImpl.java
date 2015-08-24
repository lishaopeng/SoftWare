/*    */ package com.portal.datacenter.operatedata.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.javapms.basic.utils.ExecuteQueryUtils;
/*    */ import com.portal.datacenter.operatedata.dao.LogDao;
/*    */ import com.portal.datacenter.operatedata.entity.Log;
/*    */ import java.util.Date;
/*    */ import org.apache.commons.lang.StringUtils;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Order;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class LogDaoImpl extends QueryDaoImpl<Log, Integer>
/*    */   implements LogDao
/*    */ {
/*    */   public Page<Log> getPage(Integer category, Integer siteId, Integer userId, String title, String ip, int pageNo, int pageSize)
/*    */   {
/* 22 */     Criteria crit = createCriteria();
/* 23 */     if (category != null) {
/* 24 */       crit.add(Restrictions.eq("category", category));
/*    */     }
/* 26 */     if (siteId != null) {
/* 27 */       crit.add(Restrictions.eq("site.id", siteId));
/*    */     }
/* 29 */     if (userId != null) {
/* 30 */       crit.add(Restrictions.eq("user.id", userId));
/*    */     }
/* 32 */     if (StringUtils.isNotBlank(title)) {
/* 33 */       crit.add(Restrictions.like("title", "%" + title + "%"));
/*    */     }
/* 35 */     if (StringUtils.isNotBlank(ip)) {
/* 36 */       crit.add(Restrictions.like("ip", ip + "%"));
/*    */     }
/* 38 */     crit.addOrder(Order.desc("id"));
/* 39 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public int deleteBatch(Integer category, Integer siteId, Date before) {
/* 43 */     ExecuteQueryUtils eq = ExecuteQueryUtils.create("delete Log bean where 1=1");
/* 44 */     if (category != null) {
/* 45 */       eq.append(" and bean.category=:category");
/* 46 */       eq.setParameter("category", category);
/*    */     }
/* 48 */     if (siteId != null) {
/* 49 */       eq.append(" and bean.site.id=:siteId");
/* 50 */       eq.setParameter("siteId", siteId);
/*    */     }
/* 52 */     if (before != null) {
/* 53 */       eq.append(" and bean.time<=:before");
/* 54 */       eq.setParameter("before", before);
/*    */     }
/* 56 */     return executeQuery(eq);
/*    */   }
/*    */ 
/*    */   protected Class<Log> getEntityClass()
/*    */   {
/* 61 */     return Log.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.operatedata.dao.impl.LogDaoImpl
 * JD-Core Version:    0.6.1
 */
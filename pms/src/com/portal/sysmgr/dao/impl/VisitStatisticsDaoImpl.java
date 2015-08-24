/*    */ package com.portal.sysmgr.dao.impl;
/*    */ 
/*    */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*    */ import com.javapms.basic.utils.DateUtils;
/*    */ import com.portal.sysmgr.dao.VisitStatisticsDao;
/*    */ import com.portal.sysmgr.entity.VisitStatistics;
/*    */ import java.util.Date;
/*    */ import org.hibernate.Criteria;
/*    */ import org.hibernate.criterion.Projections;
/*    */ import org.hibernate.criterion.Restrictions;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Repository;
/*    */ 
/*    */ @Repository
/*    */ public class VisitStatisticsDaoImpl extends QueryDaoImpl<VisitStatistics, Integer>
/*    */   implements VisitStatisticsDao
/*    */ {
/*    */   public Page<VisitStatistics> getStatisticsByDate(Integer siteId, Date start, Date end, int pageNo, int pageSize)
/*    */   {
/* 22 */     Criteria crit = createCriteria();
/* 23 */     crit.add(Restrictions.eq("site.id", siteId));
/* 24 */     crit.add(Restrictions.between("visitTime", start, end));
/* 25 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public Page<VisitStatistics> getStatisticsByToday(Integer siteId, int pageNo, int pageSize)
/*    */   {
/* 30 */     Criteria crit = createCriteria();
/* 31 */     crit.add(Restrictions.eq("site.id", siteId));
/* 32 */     crit.add(Restrictions.between("visitTime", DateUtils.getToday(), 
/* 33 */       DateUtils.getEndOfDay()));
/* 34 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public Page<VisitStatistics> getStatisticsByHour(Integer siteId, Integer hour, int pageNo, int pageSize)
/*    */   {
/* 39 */     Criteria crit = createCriteria();
/* 40 */     crit.add(Restrictions.eq("site.id", siteId));
/* 41 */     crit.add(Restrictions.between("visitTime", DateUtils.getToday(), 
/* 42 */       DateUtils.getEndOfDay()));
/* 43 */     return findByCriteria(crit, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   public long getStatisticsByDate(Integer siteId, Date start, Date end) {
/* 47 */     Criteria crit = createCriteria();
/* 48 */     crit.add(Restrictions.eq("site.id", siteId));
/* 49 */     crit.add(Restrictions.between("visitTime", start, end));
/* 50 */     crit.setProjection(Projections.count("id"));
/* 51 */     return ((Long)crit.uniqueResult()).longValue();
/*    */   }
/*    */ 
/*    */   public long getStatisticsByToday(Integer siteId) {
/* 55 */     Criteria crit = createCriteria();
/* 56 */     crit.add(Restrictions.eq("site.id", siteId));
/* 57 */     crit.add(Restrictions.between("visitTime", DateUtils.getToday(), 
/* 58 */       DateUtils.getEndOfDay()));
/* 59 */     crit.setProjection(Projections.count("id"));
/* 60 */     return ((Long)crit.uniqueResult()).longValue();
/*    */   }
/*    */ 
/*    */   public long getStatisticsByHour(Integer siteId, Integer hour) {
/* 64 */     Criteria crit = createCriteria();
/* 65 */     crit.add(Restrictions.eq("site.id", siteId));
/* 66 */     crit.add(Restrictions.between("visitTime", DateUtils.getToday(), 
/* 67 */       DateUtils.getEndOfDay()));
/* 68 */     crit.add(Restrictions.eq("visitHour", hour));
/* 69 */     crit.setProjection(Projections.count("id"));
/* 70 */     return ((Long)crit.uniqueResult()).longValue();
/*    */   }
/*    */ 
/*    */   public long getStatisticsByMin(Integer siteId, Integer hour, Integer min) {
/* 74 */     Criteria crit = createCriteria();
/* 75 */     crit.add(Restrictions.eq("site.id", siteId));
/* 76 */     crit.add(Restrictions.between("visitTime", DateUtils.getToday(), 
/* 77 */       DateUtils.getEndOfDay()));
/* 78 */     crit.add(Restrictions.eq("visitHour", hour));
/* 79 */     crit.add(Restrictions.eq("visitMin", min));
/* 80 */     crit.setProjection(Projections.count("id"));
/* 81 */     return ((Long)crit.uniqueResult()).longValue();
/*    */   }
/*    */ 
/*    */   protected Class<VisitStatistics> getEntityClass()
/*    */   {
/* 86 */     return VisitStatistics.class;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.dao.impl.VisitStatisticsDaoImpl
 * JD-Core Version:    0.6.1
 */
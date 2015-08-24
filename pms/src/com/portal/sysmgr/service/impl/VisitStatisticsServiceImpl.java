/*     */ package com.portal.sysmgr.service.impl;
/*     */ 
/*     */ import com.portal.sysmgr.dao.VisitStatisticsDao;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.entity.VisitStatistics;
/*     */ import com.portal.sysmgr.service.VisitStatisticsService;
/*     */ import java.util.Date;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class VisitStatisticsServiceImpl
/*     */   implements VisitStatisticsService
/*     */ {
/*     */   private VisitStatisticsDao dao;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Page<VisitStatistics> getPage(int pageNo, int pageSize)
/*     */   {
/*  20 */     return this.dao.getPage(pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Page<VisitStatistics> getStatisticsByDate(Integer siteId, Date start, Date end, int pageNo, int pageSize)
/*     */   {
/*  26 */     return this.dao.getStatisticsByDate(siteId, start, end, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Page<VisitStatistics> getStatisticsByToday(Integer siteId, int pageNo, int pageSize)
/*     */   {
/*  32 */     return this.dao.getStatisticsByToday(siteId, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Page<VisitStatistics> getStatisticsByHour(Integer siteId, Integer hour, int pageNo, int pageSize)
/*     */   {
/*  38 */     return this.dao.getStatisticsByHour(siteId, hour, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public long getStatisticsByDate(Integer siteId, Date start, Date end) {
/*  43 */     return this.dao.getStatisticsByDate(siteId, start, end);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public long getStatisticsByToday(Integer siteId) {
/*  48 */     return this.dao.getStatisticsByToday(siteId);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public long getStatisticsByHour(Integer siteId, Integer hour) {
/*  53 */     return this.dao.getStatisticsByHour(siteId, hour);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public long getStatisticsByMin(Integer siteId, Integer hour, Integer min) {
/*  58 */     return this.dao.getStatisticsByMin(siteId, hour, min);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public VisitStatistics findById(Integer id) {
/*  63 */     VisitStatistics entity = (VisitStatistics)this.dao.findById(id);
/*  64 */     return entity;
/*     */   }
/*     */ 
/*     */   public VisitStatistics save(VisitStatistics bean) {
/*  68 */     this.dao.save(bean);
/*  69 */     return bean;
/*     */   }
/*     */ 
/*     */   public VisitStatistics save(Site site, String url, String ip, String cookie) {
/*  73 */     VisitStatistics bean = new VisitStatistics();
/*  74 */     bean.setIp(ip);
/*  75 */     bean.setUrl(url);
/*  76 */     bean.setCookie(cookie);
/*  77 */     bean.setSite(site);
/*  78 */     bean.init();
/*  79 */     this.dao.save(bean);
/*  80 */     return bean;
/*     */   }
/*     */ 
/*     */   public VisitStatistics update(VisitStatistics bean) {
/*  84 */     bean = (VisitStatistics)this.dao.update(bean);
/*  85 */     return bean;
/*     */   }
/*     */ 
/*     */   public VisitStatistics deleteById(Integer id) {
/*  89 */     VisitStatistics bean = (VisitStatistics)this.dao.deleteById(id);
/*  90 */     return bean;
/*     */   }
/*     */ 
/*     */   public VisitStatistics[] deleteByIds(Integer[] ids) {
/*  94 */     VisitStatistics[] beans = new VisitStatistics[ids.length];
/*  95 */     int i = 0; for (int len = ids.length; i < len; i++) {
/*  96 */       beans[i] = deleteById(ids[i]);
/*     */     }
/*  98 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(VisitStatisticsDao dao)
/*     */   {
/* 105 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.service.impl.VisitStatisticsServiceImpl
 * JD-Core Version:    0.6.1
 */
/*    */ package com.portal.extrafunc.service.impl;
/*    */ 
/*    */ import com.portal.extrafunc.dao.ForumStatisDao;
/*    */ import com.portal.extrafunc.entity.ForumStatis;
/*    */ import com.portal.extrafunc.service.ForumStatisService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ForumStatisServiceImpl
/*    */   implements ForumStatisService
/*    */ {
/*    */   private ForumStatisDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<ForumStatis> getPage(int pageNo, int pageSize)
/*    */   {
/* 18 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public ForumStatis findById(Integer id) {
/* 23 */     ForumStatis entity = (ForumStatis)this.dao.findById(id);
/* 24 */     return entity;
/*    */   }
/*    */ 
/*    */   public ForumStatis save(Site site) {
/* 28 */     ForumStatis bean = new ForumStatis();
/* 29 */     bean.setPostsToday(Integer.valueOf(0));
/* 30 */     bean.setPostsYestoday(Integer.valueOf(0));
/* 31 */     bean.setPostsTotal(Integer.valueOf(0));
/* 32 */     bean.setHighestDay(Integer.valueOf(0));
/* 33 */     bean.setSite(site);
/* 34 */     this.dao.save(bean);
/* 35 */     site.setForumStatis(bean);
/* 36 */     return bean;
/*    */   }
/*    */ 
/*    */   public ForumStatis update(Integer siteId, Integer postsToday, Integer postsTotal)
/*    */   {
/* 41 */     ForumStatis bean = findById(siteId);
/* 42 */     bean.setPostsToday(postsToday);
/* 43 */     bean.setPostsTotal(postsTotal);
/* 44 */     return bean;
/*    */   }
/*    */ 
/*    */   public ForumStatis updateOnday(Integer siteId, Integer postsToday, Integer postsTotal)
/*    */   {
/* 49 */     ForumStatis bean = findById(siteId);
/* 50 */     if (postsToday.intValue() > bean.getHighestDay().intValue()) {
/* 51 */       bean.setHighestDay(postsToday);
/*    */     }
/* 53 */     bean.setPostsToday(Integer.valueOf(0));
/* 54 */     bean.setPostsYestoday(postsToday);
/* 55 */     bean.setPostsTotal(postsTotal);
/* 56 */     return bean;
/*    */   }
/*    */ 
/*    */   public ForumStatis update(ForumStatis bean) {
/* 60 */     bean = (ForumStatis)this.dao.update(bean);
/* 61 */     return bean;
/*    */   }
/*    */ 
/*    */   public ForumStatis deleteById(Integer id) {
/* 65 */     ForumStatis bean = (ForumStatis)this.dao.deleteById(id);
/* 66 */     return bean;
/*    */   }
/*    */ 
/*    */   public ForumStatis[] deleteByIds(Integer[] ids) {
/* 70 */     ForumStatis[] beans = new ForumStatis[ids.length];
/* 71 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 72 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 74 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ForumStatisDao dao)
/*    */   {
/* 81 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.ForumStatisServiceImpl
 * JD-Core Version:    0.6.1
 */
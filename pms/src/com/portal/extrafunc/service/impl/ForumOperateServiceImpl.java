/*    */ package com.portal.extrafunc.service.impl;
/*    */ 
/*    */ import com.portal.extrafunc.dao.ForumOperateDao;
/*    */ import com.portal.extrafunc.entity.ForumOperate;
/*    */ import com.portal.extrafunc.service.ForumOperateService;
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.usermgr.entity.User;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ForumOperateServiceImpl
/*    */   implements ForumOperateService
/*    */ {
/*    */   private ForumOperateDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<ForumOperate> getPage(int pageNo, int pageSize)
/*    */   {
/* 19 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public ForumOperate findById(Integer id) {
/* 24 */     ForumOperate entity = (ForumOperate)this.dao.findById(id);
/* 25 */     return entity;
/*    */   }
/*    */ 
/*    */   public ForumOperate save(Integer targetId, String targetType, String name, String reason, Site site, User user)
/*    */   {
/* 30 */     ForumOperate bean = new ForumOperate();
/* 31 */     bean.setAdmin(user);
/* 32 */     bean.setSite(site);
/* 33 */     bean.setName(name);
/* 34 */     bean.setTarget(targetId);
/* 35 */     bean.setTargetType(targetType);
/* 36 */     bean.setReason(reason);
/* 37 */     bean.init();
/* 38 */     this.dao.save(bean);
/* 39 */     return bean;
/*    */   }
/*    */ 
/*    */   public ForumOperate update(ForumOperate bean) {
/* 43 */     bean = (ForumOperate)this.dao.update(bean);
/* 44 */     return bean;
/*    */   }
/*    */ 
/*    */   public ForumOperate deleteById(Integer id) {
/* 48 */     ForumOperate bean = (ForumOperate)this.dao.deleteById(id);
/* 49 */     return bean;
/*    */   }
/*    */ 
/*    */   public ForumOperate[] deleteByIds(Integer[] ids) {
/* 53 */     ForumOperate[] beans = new ForumOperate[ids.length];
/* 54 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 55 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 57 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ForumOperateDao dao)
/*    */   {
/* 64 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.ForumOperateServiceImpl
 * JD-Core Version:    0.6.1
 */
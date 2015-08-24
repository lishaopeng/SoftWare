/*    */ package com.portal.extrafunc.service.impl;
/*    */ 
/*    */ import com.portal.extrafunc.dao.ForumExtDao;
/*    */ import com.portal.extrafunc.entity.Forum;
/*    */ import com.portal.extrafunc.entity.ForumExt;
/*    */ import com.portal.extrafunc.service.ForumExtService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ForumExtServiceImpl
/*    */   implements ForumExtService
/*    */ {
/*    */   private ForumExtDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<ForumExt> getPage(int pageNo, int pageSize)
/*    */   {
/* 18 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public ForumExt findById(Integer id) {
/* 23 */     ForumExt entity = (ForumExt)this.dao.findById(id);
/* 24 */     return entity;
/*    */   }
/*    */ 
/*    */   public ForumExt save(ForumExt bean, Forum forum) {
/* 28 */     bean.setForum(forum);
/* 29 */     this.dao.save(bean);
/* 30 */     forum.setExt(bean);
/* 31 */     return bean;
/*    */   }
/*    */ 
/*    */   public ForumExt update(ForumExt bean, Forum forum) {
/* 35 */     ForumExt ext = findById(forum.getId());
/* 36 */     if (ext != null)
/* 37 */       bean = (ForumExt)this.dao.update(bean);
/*    */     else {
/* 39 */       bean = save(bean, forum);
/*    */     }
/* 41 */     return bean;
/*    */   }
/*    */ 
/*    */   public int deleteByCategoryId(Integer categoryId) {
/* 45 */     return this.dao.deleteByCategoryId(categoryId);
/*    */   }
/*    */ 
/*    */   public int deleteBySiteId(Integer siteId) {
/* 49 */     return this.dao.deleteBySiteId(siteId);
/*    */   }
/*    */ 
/*    */   public ForumExt deleteById(Integer id) {
/* 53 */     ForumExt bean = (ForumExt)this.dao.deleteById(id);
/* 54 */     return bean;
/*    */   }
/*    */ 
/*    */   public ForumExt[] deleteByIds(Integer[] ids) {
/* 58 */     ForumExt[] beans = new ForumExt[ids.length];
/* 59 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 60 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 62 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ForumExtDao dao)
/*    */   {
/* 69 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.ForumExtServiceImpl
 * JD-Core Version:    0.6.1
 */
/*    */ package com.portal.extrafunc.service.impl;
/*    */ 
/*    */ import com.portal.extrafunc.dao.PostsTxtDao;
/*    */ import com.portal.extrafunc.entity.Posts;
/*    */ import com.portal.extrafunc.entity.PostsTxt;
/*    */ import com.portal.extrafunc.service.PostsTxtService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class PostsTxtServiceImpl
/*    */   implements PostsTxtService
/*    */ {
/*    */   private PostsTxtDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<PostsTxt> getPage(int pageNo, int pageSize)
/*    */   {
/* 18 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public PostsTxt findById(Integer id) {
/* 23 */     PostsTxt entity = (PostsTxt)this.dao.findById(id);
/* 24 */     return entity;
/*    */   }
/*    */ 
/*    */   public PostsTxt save(String content, Posts posts) {
/* 28 */     PostsTxt txt = new PostsTxt();
/* 29 */     txt.setContent(content);
/* 30 */     txt.setPosts(posts);
/* 31 */     this.dao.save(txt);
/* 32 */     posts.setTxt(txt);
/* 33 */     return txt;
/*    */   }
/*    */ 
/*    */   public PostsTxt update(Integer txtId, String content) {
/* 37 */     PostsTxt txt = findById(txtId);
/* 38 */     txt.setContent(content);
/* 39 */     return txt;
/*    */   }
/*    */ 
/*    */   public PostsTxt deleteById(Integer id) {
/* 43 */     PostsTxt bean = (PostsTxt)this.dao.deleteById(id);
/* 44 */     return bean;
/*    */   }
/*    */ 
/*    */   public PostsTxt[] deleteByIds(Integer[] ids) {
/* 48 */     PostsTxt[] beans = new PostsTxt[ids.length];
/* 49 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 50 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 52 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(PostsTxtDao dao)
/*    */   {
/* 59 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.PostsTxtServiceImpl
 * JD-Core Version:    0.6.1
 */
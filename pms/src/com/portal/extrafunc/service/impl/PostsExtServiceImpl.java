/*    */ package com.portal.extrafunc.service.impl;
/*    */ 
/*    */ import com.portal.extrafunc.dao.PostsExtDao;
/*    */ import com.portal.extrafunc.entity.Posts;
/*    */ import com.portal.extrafunc.entity.PostsExt;
/*    */ import com.portal.extrafunc.service.PostsExtService;
/*    */ import com.portal.usermgr.service.UserService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class PostsExtServiceImpl
/*    */   implements PostsExtService
/*    */ {
/*    */   private PostsExtDao dao;
/*    */   private UserService userService;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<PostsExt> getPage(int pageNo, int pageSize)
/*    */   {
/* 19 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public PostsExt findById(Integer id) {
/* 24 */     PostsExt entity = (PostsExt)this.dao.findById(id);
/* 25 */     return entity;
/*    */   }
/*    */ 
/*    */   public PostsExt save(String ip, Posts posts) {
/* 29 */     PostsExt ext = new PostsExt();
/* 30 */     ext.setCreateIp(ip);
/* 31 */     ext.setPosts(posts);
/* 32 */     ext.init();
/* 33 */     this.dao.save(ext);
/* 34 */     posts.setExt(ext);
/* 35 */     return ext;
/*    */   }
/*    */ 
/*    */   public PostsExt update(Integer extId, Integer userId, String ip) {
/* 39 */     PostsExt ext = findById(extId);
/* 40 */     ext.setEditer(this.userService.findById(userId));
/* 41 */     ext.setCreateIp(ip);
/* 42 */     ext.setEditCount(Integer.valueOf(ext.getEditCount().intValue() + 1));
/* 43 */     return ext;
/*    */   }
/*    */ 
/*    */   public PostsExt deleteById(Integer id) {
/* 47 */     PostsExt bean = (PostsExt)this.dao.deleteById(id);
/* 48 */     return bean;
/*    */   }
/*    */ 
/*    */   public PostsExt[] deleteByIds(Integer[] ids) {
/* 52 */     PostsExt[] beans = new PostsExt[ids.length];
/* 53 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 54 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 56 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(PostsExtDao dao)
/*    */   {
/* 64 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setUserService(UserService userService) {
/* 69 */     this.userService = userService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.PostsExtServiceImpl
 * JD-Core Version:    0.6.1
 */
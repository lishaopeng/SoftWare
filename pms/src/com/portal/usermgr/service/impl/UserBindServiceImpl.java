/*    */ package com.portal.usermgr.service.impl;
/*    */ 
/*    */ import com.portal.usermgr.dao.UserBindDao;
/*    */ import com.portal.usermgr.entity.User;
/*    */ import com.portal.usermgr.entity.UserBind;
/*    */ import com.portal.usermgr.service.UserBindService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class UserBindServiceImpl
/*    */   implements UserBindService
/*    */ {
/*    */   private UserBindDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<UserBind> getPage(int pageNo, int pageSize)
/*    */   {
/* 18 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public UserBind getBindByUser(Integer userId, Integer status) {
/* 23 */     return this.dao.getBindByUser(userId, status);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public UserBind findById(Integer id) {
/* 28 */     UserBind entity = (UserBind)this.dao.findById(id);
/* 29 */     return entity;
/*    */   }
/*    */ 
/*    */   public UserBind save(User user, String username, String pass, Integer status) {
/* 33 */     UserBind bean = this.dao.getBindByUser(user.getId(), status);
/* 34 */     if (bean == null) {
/* 35 */       bean = new UserBind();
/* 36 */       bean.setUser(user);
/* 37 */       bean.setUsername(username);
/* 38 */       bean.setPassword(pass);
/* 39 */       bean.setStatus(status);
/* 40 */       this.dao.save(bean);
/*    */     } else {
/* 42 */       bean.setUsername(username);
/* 43 */       bean.setPassword(pass);
/*    */     }
/* 45 */     return bean;
/*    */   }
/*    */ 
/*    */   public UserBind update(UserBind bean) {
/* 49 */     bean = (UserBind)this.dao.update(bean);
/* 50 */     return bean;
/*    */   }
/*    */ 
/*    */   public UserBind deleteById(Integer id) {
/* 54 */     UserBind bean = (UserBind)this.dao.deleteById(id);
/* 55 */     return bean;
/*    */   }
/*    */ 
/*    */   public UserBind[] deleteByIds(Integer[] ids) {
/* 59 */     UserBind[] beans = new UserBind[ids.length];
/* 60 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 61 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 63 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(UserBindDao dao)
/*    */   {
/* 70 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.service.impl.UserBindServiceImpl
 * JD-Core Version:    0.6.1
 */
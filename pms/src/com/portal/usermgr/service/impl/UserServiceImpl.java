/*    */ package com.portal.usermgr.service.impl;
/*    */ 
/*    */ import com.javapms.basic.security.encoder.PwdEncoder;
/*    */ import com.portal.sysmgr.event.DelUserEvent;
/*    */ import com.portal.usermgr.dao.UserDao;
/*    */ import com.portal.usermgr.entity.User;
/*    */ import com.portal.usermgr.service.UserService;
/*    */ import java.sql.Timestamp;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class UserServiceImpl
/*    */   implements UserService
/*    */ {
/*    */   private UserDao dao;
/*    */   private PwdEncoder pwdEncoder;
/*    */   private ApplicationContext applicationContext;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public User findById(Integer id)
/*    */   {
/* 22 */     User entity = (User)this.dao.findById(id);
/* 23 */     return entity;
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public User findByUsername(String username) {
/* 28 */     User entity = this.dao.findByUsername(username);
/* 29 */     return entity;
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public int getAllUserCount() {
/* 34 */     return this.dao.getAllUserCount();
/*    */   }
/*    */ 
/*    */   public User save(User user) {
/* 38 */     user.init();
/* 39 */     user.setPassword(this.pwdEncoder.encodePassword(user.getPassword()));
/* 40 */     this.dao.save(user);
/* 41 */     return user;
/*    */   }
/*    */ 
/*    */   public User update(User user) {
/* 45 */     User bean = (User)this.dao.update(user);
/* 46 */     return bean;
/*    */   }
/*    */ 
/*    */   public User updatePass(Integer userId, String password) {
/* 50 */     User user = findById(userId);
/* 51 */     user.setPassword(this.pwdEncoder.encodePassword(password));
/* 52 */     return user;
/*    */   }
/*    */ 
/*    */   public User updateFailTime(User user) {
/* 56 */     User bean = (User)this.dao.update(user);
/* 57 */     bean.setLastFailTime(new Timestamp(System.currentTimeMillis()));
/* 58 */     if (bean.getFailCount() != null)
/* 59 */       bean.setFailCount(Integer.valueOf(user.getFailCount().intValue() + 1));
/*    */     else {
/* 61 */       bean.setFailCount(Integer.valueOf(1));
/*    */     }
/* 63 */     return bean;
/*    */   }
/*    */ 
/*    */   public User deleteById(Integer id) {
/* 67 */     User bean = (User)this.dao.deleteById(id);
/* 68 */     this.applicationContext.publishEvent(new DelUserEvent(bean));
/* 69 */     return bean;
/*    */   }
/*    */ 
/*    */   public User[] deleteByIds(Integer[] ids) {
/* 73 */     User[] beans = new User[ids.length];
/* 74 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 75 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 77 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(UserDao dao)
/*    */   {
/* 86 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setPwdEncoder(PwdEncoder pwdEncoder) {
/* 91 */     this.pwdEncoder = pwdEncoder;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setApplicationContext(ApplicationContext applicationContext) {
/* 96 */     this.applicationContext = applicationContext;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.service.impl.UserServiceImpl
 * JD-Core Version:    0.6.1
 */
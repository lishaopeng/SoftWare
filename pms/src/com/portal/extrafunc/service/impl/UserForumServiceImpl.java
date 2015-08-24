/*    */ package com.portal.extrafunc.service.impl;
/*    */ 
/*    */ import com.portal.extrafunc.dao.UserForumDao;
/*    */ import com.portal.extrafunc.entity.UserForum;
/*    */ import com.portal.extrafunc.service.UserForumService;
/*    */ import com.portal.usermgr.entity.User;
/*    */ import com.portal.usermgr.service.UserService;
/*    */ import java.util.Date;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class UserForumServiceImpl
/*    */   implements UserForumService
/*    */ {
/*    */   private UserForumDao dao;
/*    */   private UserService userService;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<UserForum> getPage(int pageNo, int pageSize)
/*    */   {
/* 21 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public UserForum findById(Integer id) {
/* 26 */     UserForum entity = (UserForum)this.dao.findById(id);
/* 27 */     return entity;
/*    */   }
/*    */ 
/*    */   public UserForum save(User user) {
/* 31 */     UserForum bean = new UserForum();
/* 32 */     bean.setUser(user);
/* 33 */     bean.init();
/* 34 */     this.dao.save(bean);
/* 35 */     user.setUserForum(bean);
/* 36 */     return bean;
/*    */   }
/*    */ 
/*    */   public UserForum update(Integer userId, Integer themeCount, Integer replyCount, Integer essenaCount, Integer point)
/*    */   {
/* 41 */     UserForum bean = findById(userId);
/* 42 */     if (bean != null) {
/* 43 */       bean.setThemeCount(Integer.valueOf(bean.getThemeCount().intValue() + themeCount.intValue()));
/* 44 */       bean.setEssenaCount(Integer.valueOf(bean.getEssenaCount().intValue() + essenaCount.intValue()));
/* 45 */       bean.setReplyCount(Integer.valueOf(bean.getReplyCount().intValue() + replyCount.intValue()));
/* 46 */       bean.setPoint(Integer.valueOf(bean.getPoint().intValue() + point.intValue()));
/*    */     } else {
/* 48 */       bean = new UserForum();
/* 49 */       User user = this.userService.findById(userId);
/* 50 */       bean.setThemeCount(themeCount);
/* 51 */       bean.setEssenaCount(essenaCount);
/* 52 */       bean.setReplyCount(replyCount);
/* 53 */       bean.setPoint(point);
/* 54 */       bean.setUser(user);
/* 55 */       bean.init();
/* 56 */       this.dao.save(bean);
/* 57 */       user.setUserForum(bean);
/*    */     }
/* 59 */     return bean;
/*    */   }
/*    */ 
/*    */   public UserForum shieldUserForum(Integer userId, Date shieldTime) {
/* 63 */     UserForum bean = findById(userId);
/* 64 */     bean.setStatus(Integer.valueOf(-2));
/* 65 */     bean.setStatusTime(shieldTime);
/* 66 */     return bean;
/*    */   }
/*    */ 
/*    */   public UserForum update(UserForum bean) {
/* 70 */     bean = (UserForum)this.dao.update(bean);
/* 71 */     return bean;
/*    */   }
/*    */ 
/*    */   public UserForum deleteById(Integer id) {
/* 75 */     UserForum bean = (UserForum)this.dao.deleteById(id);
/* 76 */     return bean;
/*    */   }
/*    */ 
/*    */   public UserForum[] deleteByIds(Integer[] ids) {
/* 80 */     UserForum[] beans = new UserForum[ids.length];
/* 81 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 82 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 84 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(UserForumDao dao)
/*    */   {
/* 92 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setUserService(UserService userService) {
/* 97 */     this.userService = userService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.UserForumServiceImpl
 * JD-Core Version:    0.6.1
 */
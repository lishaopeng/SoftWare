/*    */ package com.portal.extrafunc.service.impl;
/*    */ 
/*    */ import com.portal.extrafunc.dao.ThemeTxtDao;
/*    */ import com.portal.extrafunc.entity.Theme;
/*    */ import com.portal.extrafunc.entity.ThemeTxt;
/*    */ import com.portal.extrafunc.service.ThemeTxtService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ThemeTxtServiceImpl
/*    */   implements ThemeTxtService
/*    */ {
/*    */   private ThemeTxtDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<ThemeTxt> getPage(int pageNo, int pageSize)
/*    */   {
/* 18 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public ThemeTxt findById(Integer id) {
/* 23 */     ThemeTxt entity = (ThemeTxt)this.dao.findById(id);
/* 24 */     return entity;
/*    */   }
/*    */ 
/*    */   public ThemeTxt save(Theme theme) {
/* 28 */     ThemeTxt txt = new ThemeTxt();
/* 29 */     txt.setContent(",");
/* 30 */     txt.setTheme(theme);
/* 31 */     this.dao.save(txt);
/* 32 */     theme.setTxt(txt);
/* 33 */     return txt;
/*    */   }
/*    */ 
/*    */   public ThemeTxt update(Integer txtId, Integer userId) {
/* 37 */     ThemeTxt txt = findById(txtId);
/* 38 */     if (txt.getContent().indexOf("," + userId + ",") == -1) {
/* 39 */       txt.setContent(txt.getContent() + userId + ",");
/*    */     }
/* 41 */     return txt;
/*    */   }
/*    */ 
/*    */   public int deleteByForumId(Integer forumId) {
/* 45 */     return this.dao.deleteByForumId(forumId);
/*    */   }
/*    */ 
/*    */   public int deleteByUserId(Integer userId) {
/* 49 */     return this.dao.deleteByUserId(userId);
/*    */   }
/*    */ 
/*    */   public int deleteBySiteId(Integer siteId) {
/* 53 */     return this.dao.deleteBySiteId(siteId);
/*    */   }
/*    */ 
/*    */   public ThemeTxt deleteById(Integer id) {
/* 57 */     ThemeTxt bean = (ThemeTxt)this.dao.deleteById(id);
/* 58 */     return bean;
/*    */   }
/*    */ 
/*    */   public ThemeTxt[] deleteByIds(Integer[] ids) {
/* 62 */     ThemeTxt[] beans = new ThemeTxt[ids.length];
/* 63 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 64 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 66 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ThemeTxtDao dao)
/*    */   {
/* 73 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.ThemeTxtServiceImpl
 * JD-Core Version:    0.6.1
 */
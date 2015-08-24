/*    */ package com.portal.usermgr.service.impl;
/*    */ 
/*    */ import com.portal.usermgr.dao.ThirdpartyBindDao;
/*    */ import com.portal.usermgr.entity.ThirdpartyBind;
/*    */ import com.portal.usermgr.service.ThirdpartyBindService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class ThirdpartyBindServiceImpl
/*    */   implements ThirdpartyBindService
/*    */ {
/*    */   private ThirdpartyBindDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<ThirdpartyBind> getPage(int pageNo, int pageSize)
/*    */   {
/* 17 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public ThirdpartyBind findByOpenId(String openid, String bindType) {
/* 22 */     return this.dao.findByOpenId(openid, bindType);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public ThirdpartyBind findById(Integer id) {
/* 27 */     ThirdpartyBind entity = (ThirdpartyBind)this.dao.findById(id);
/* 28 */     return entity;
/*    */   }
/*    */ 
/*    */   public ThirdpartyBind save(String username, String openid, String openkey, String bindType)
/*    */   {
/* 33 */     ThirdpartyBind bean = new ThirdpartyBind();
/* 34 */     bean.setUsername(username);
/* 35 */     bean.setOpenid(openid);
/* 36 */     bean.setOpenkey(openkey);
/* 37 */     bean.setBindType(bindType);
/* 38 */     bean.init();
/* 39 */     this.dao.save(bean);
/* 40 */     return bean;
/*    */   }
/*    */ 
/*    */   public ThirdpartyBind update(ThirdpartyBind bean) {
/* 44 */     bean = (ThirdpartyBind)this.dao.update(bean);
/* 45 */     return bean;
/*    */   }
/*    */ 
/*    */   public ThirdpartyBind deleteById(Integer id) {
/* 49 */     ThirdpartyBind bean = (ThirdpartyBind)this.dao.deleteById(id);
/* 50 */     return bean;
/*    */   }
/*    */ 
/*    */   public ThirdpartyBind[] deleteByIds(Integer[] ids) {
/* 54 */     ThirdpartyBind[] beans = new ThirdpartyBind[ids.length];
/* 55 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 56 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 58 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(ThirdpartyBindDao dao)
/*    */   {
/* 65 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.service.impl.ThirdpartyBindServiceImpl
 * JD-Core Version:    0.6.1
 */
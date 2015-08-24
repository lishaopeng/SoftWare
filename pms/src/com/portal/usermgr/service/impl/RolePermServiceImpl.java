/*    */ package com.portal.usermgr.service.impl;
/*    */ 
/*    */ import com.portal.usermgr.dao.RolePermDao;
/*    */ import com.portal.usermgr.entity.Role;
/*    */ import com.portal.usermgr.entity.RolePerm;
/*    */ import com.portal.usermgr.service.RolePermService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class RolePermServiceImpl
/*    */   implements RolePermService
/*    */ {
/*    */   private RolePermDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<RolePerm> getPage(int pageNo, int pageSize)
/*    */   {
/* 18 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public RolePerm findById(Integer id) {
/* 23 */     RolePerm entity = (RolePerm)this.dao.findById(id);
/* 24 */     return entity;
/*    */   }
/*    */ 
/*    */   public RolePerm save(Role role, RolePerm perm) {
/* 28 */     perm.setRole(role);
/* 29 */     save(perm);
/* 30 */     role.setRolePerm(perm);
/* 31 */     return perm;
/*    */   }
/*    */ 
/*    */   public RolePerm save(RolePerm bean) {
/* 35 */     this.dao.save(bean);
/* 36 */     return bean;
/*    */   }
/*    */ 
/*    */   public RolePerm update(RolePerm bean) {
/* 40 */     bean = (RolePerm)this.dao.update(bean);
/* 41 */     return bean;
/*    */   }
/*    */ 
/*    */   public int deleteBySiteId(Integer siteId) {
/* 45 */     return this.dao.deleteBySiteId(siteId);
/*    */   }
/*    */ 
/*    */   public RolePerm deleteById(Integer id) {
/* 49 */     RolePerm bean = (RolePerm)this.dao.deleteById(id);
/* 50 */     return bean;
/*    */   }
/*    */ 
/*    */   public RolePerm[] deleteByIds(Integer[] ids) {
/* 54 */     RolePerm[] beans = new RolePerm[ids.length];
/* 55 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 56 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 58 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(RolePermDao dao)
/*    */   {
/* 65 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.service.impl.RolePermServiceImpl
 * JD-Core Version:    0.6.1
 */
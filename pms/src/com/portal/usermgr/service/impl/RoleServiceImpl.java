/*    */ package com.portal.usermgr.service.impl;
/*    */ 
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.sysmgr.event.DelRoleEvent;
/*    */ import com.portal.usermgr.dao.RoleDao;
/*    */ import com.portal.usermgr.entity.Role;
/*    */ import com.portal.usermgr.entity.RolePerm;
/*    */ import com.portal.usermgr.service.RolePermService;
/*    */ import com.portal.usermgr.service.RoleService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.context.ApplicationContext;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class RoleServiceImpl
/*    */   implements RoleService
/*    */ {
/*    */   private RoleDao dao;
/*    */   private RolePermService rolePermService;
/*    */   private ApplicationContext applicationContext;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<Role> getPage(String name, Integer siteId, String sortname, String sortorder, int pageNo, int pageSize)
/*    */   {
/* 26 */     return this.dao.getPage(name, siteId, sortname, sortorder, pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<Role> getListBySite(Integer siteId) {
/* 31 */     return this.dao.getListBySite(siteId);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Role findById(Integer id) {
/* 36 */     Role entity = (Role)this.dao.findById(id);
/* 37 */     return entity;
/*    */   }
/*    */ 
/*    */   public Role saveRole(Role role, RolePerm rolePerm, Site site) {
/* 41 */     role.setSite(site);
/* 42 */     save(role);
/* 43 */     this.rolePermService.save(role, rolePerm);
/* 44 */     return role;
/*    */   }
/*    */ 
/*    */   public Role updateRole(Role role, RolePerm rolePerm) {
/* 48 */     role = update(role);
/* 49 */     this.rolePermService.update(rolePerm);
/* 50 */     return role;
/*    */   }
/*    */ 
/*    */   public Role save(Role bean) {
/* 54 */     bean.init();
/* 55 */     this.dao.save(bean);
/* 56 */     return bean;
/*    */   }
/*    */ 
/*    */   public Role update(Role bean) {
/* 60 */     bean = (Role)this.dao.update(bean);
/* 61 */     return bean;
/*    */   }
/*    */ 
/*    */   public int deleteBySiteId(Integer siteId) {
/* 65 */     this.rolePermService.deleteBySiteId(siteId);
/* 66 */     return this.dao.deleteBySiteId(siteId);
/*    */   }
/*    */ 
/*    */   public Role deleteById(Integer id) {
/* 70 */     Role bean = (Role)this.dao.deleteById(id);
/* 71 */     this.applicationContext.publishEvent(new DelRoleEvent(bean));
/* 72 */     return bean;
/*    */   }
/*    */ 
/*    */   public Role[] deleteByIds(Integer[] ids) {
/* 76 */     Role[] beans = new Role[ids.length];
/* 77 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 78 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 80 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(RoleDao dao)
/*    */   {
/* 89 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setRolePermService(RolePermService rolePermService) {
/* 94 */     this.rolePermService = rolePermService;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setApplicationContext(ApplicationContext applicationContext) {
/* 99 */     this.applicationContext = applicationContext;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.service.impl.RoleServiceImpl
 * JD-Core Version:    0.6.1
 */
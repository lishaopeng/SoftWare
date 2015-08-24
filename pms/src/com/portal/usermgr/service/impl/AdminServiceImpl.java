/*     */ package com.portal.usermgr.service.impl;
/*     */ 
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.usermgr.dao.AdminDao;
/*     */ import com.portal.usermgr.entity.Admin;
/*     */ import com.portal.usermgr.entity.Depart;
/*     */ import com.portal.usermgr.entity.Role;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.AdminCheckService;
/*     */ import com.portal.usermgr.service.AdminService;
/*     */ import com.portal.usermgr.service.DepartService;
/*     */ import com.portal.usermgr.service.RoleService;
/*     */ import com.portal.usermgr.service.UserService;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class AdminServiceImpl
/*     */   implements AdminService
/*     */ {
/*     */   private AdminDao dao;
/*     */   private UserService userService;
/*     */   private AdminCheckService adminCheckService;
/*     */   private RoleService roleService;
/*     */   private DepartService departService;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Page<Admin> getPage(int pageNo, int pageSize)
/*     */   {
/*  26 */     return this.dao.getPage(pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Page<Admin> getPage(String key, Integer siteId, Integer departId, Integer roleId, String sortname, String sortorder, int pageNo, int pageSize)
/*     */   {
/*  33 */     return this.dao.getPage(key, siteId, departId, roleId, sortname, sortorder, 
/*  34 */       pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Admin> getListByDepart(Integer departId) {
/*  39 */     return this.dao.getListByDepart(departId);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Admin> getListByRole(Integer roleId) {
/*  44 */     return this.dao.getListByRole(roleId);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Admin findById(Integer id) {
/*  49 */     Admin entity = (Admin)this.dao.findById(id);
/*  50 */     return entity;
/*     */   }
/*     */ 
/*     */   public Admin saveAdmin(User user, Admin admin, String ip, Integer roleId, Integer departId, Site site, Byte manageStatus, Boolean takeDepart, Integer[] channelIds)
/*     */   {
/*  56 */     admin.setRegisterIp(ip);
/*  57 */     if (roleId != null) {
/*  58 */       admin.addToRoles(this.roleService.findById(roleId));
/*     */     }
/*  60 */     if (departId != null) {
/*  61 */       admin.addToDeparts(this.departService.findById(departId));
/*     */     }
/*  63 */     this.userService.save(user);
/*  64 */     admin.setUser(user);
/*  65 */     admin = save(admin);
/*  66 */     user.setAdmin(admin);
/*  67 */     this.adminCheckService.save(site, admin, manageStatus, takeDepart, 
/*  68 */       channelIds);
/*  69 */     return admin;
/*     */   }
/*     */ 
/*     */   public Admin updateAdmin(User user, Admin admin, Integer roleId, Integer departId, Site site, Byte manageStatus, Boolean takeDepart, Integer[] channelIds)
/*     */   {
/*  75 */     admin = update(admin);
/*  76 */     this.adminCheckService.updateByAdmin(admin, site, manageStatus, takeDepart, 
/*  77 */       channelIds);
/*  78 */     this.userService.update(user);
/*  79 */     if ((roleId != null) && 
/*  80 */       (!roleId.equals(admin.getRole(site.getId()).getId()))) {
/*  81 */       admin.getRoles().remove(admin.getRole(site.getId()));
/*  82 */       admin.addToRoles(this.roleService.findById(roleId));
/*     */     }
/*     */ 
/*  85 */     if (departId != null) {
/*  86 */       if (admin.getDepart(site.getId()) != null) {
/*  87 */         if (!departId.equals(admin.getDepart(site.getId()).getId())) {
/*  88 */           admin.getDeparts().remove(admin.getDepart(site.getId()));
/*  89 */           admin.addToDeparts(this.departService.findById(departId));
/*     */         }
/*     */       }
/*  92 */       else admin.addToDeparts(this.departService.findById(departId));
/*     */     }
/*     */ 
/*  95 */     return admin;
/*     */   }
/*     */ 
/*     */   public void updateLoginInfo(User user, String lastLoginIp) {
/*  99 */     Admin admin = findById(user.getId());
/* 100 */     if (admin != null) {
/* 101 */       admin.setLastLoginIp(lastLoginIp);
/* 102 */       admin.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
/* 103 */       if (admin.getRegisterIp() == null) {
/* 104 */         admin.setRegisterIp(lastLoginIp);
/*     */       }
/* 106 */       admin.setLoginCount(Integer.valueOf(admin.getLoginCount().intValue() + 1));
/*     */     }
/*     */   }
/*     */ 
/*     */   public Admin save(Admin bean) {
/* 111 */     bean.init();
/* 112 */     this.dao.save(bean);
/* 113 */     return bean;
/*     */   }
/*     */ 
/*     */   public Admin update(Admin bean) {
/* 117 */     bean = (Admin)this.dao.update(bean);
/* 118 */     return bean;
/*     */   }
/*     */ 
/*     */   public Admin updatePass(Integer adminId, String password) {
/* 122 */     User user = this.userService.updatePass(adminId, password);
/* 123 */     return user.getAdmin();
/*     */   }
/*     */ 
/*     */   public Admin deleteById(Integer id) {
/* 127 */     Admin bean = (Admin)this.dao.deleteById(id);
/* 128 */     bean.getAdminChecks().clear();
/* 129 */     this.userService.deleteById(id);
/* 130 */     return bean;
/*     */   }
/*     */ 
/*     */   public Admin[] deleteByIds(Integer[] ids) {
/* 134 */     Admin[] beans = new Admin[ids.length];
/* 135 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 136 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 138 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(AdminDao dao)
/*     */   {
/* 149 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setUserService(UserService userService) {
/* 154 */     this.userService = userService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setAdminCheckService(AdminCheckService adminCheckService) {
/* 159 */     this.adminCheckService = adminCheckService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setRoleService(RoleService roleService) {
/* 164 */     this.roleService = roleService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDepartService(DepartService departService) {
/* 169 */     this.departService = departService;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.service.impl.AdminServiceImpl
 * JD-Core Version:    0.6.1
 */
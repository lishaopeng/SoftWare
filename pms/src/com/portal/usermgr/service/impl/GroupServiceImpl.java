/*    */ package com.portal.usermgr.service.impl;
/*    */ 
/*    */ import com.portal.sysmgr.entity.Site;
/*    */ import com.portal.usermgr.dao.GroupDao;
/*    */ import com.portal.usermgr.entity.Group;
/*    */ import com.portal.usermgr.entity.GroupPerm;
/*    */ import com.portal.usermgr.service.GroupPermService;
/*    */ import com.portal.usermgr.service.GroupService;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class GroupServiceImpl
/*    */   implements GroupService
/*    */ {
/*    */   private GroupDao dao;
/*    */   private GroupPermService groupPermService;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public List<Group> getList(Integer siteId, String sortname, String sortorder)
/*    */   {
/* 21 */     return this.dao.getList(siteId, sortname, sortorder);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Group findById(Integer id) {
/* 26 */     Group entity = (Group)this.dao.findById(id);
/* 27 */     return entity;
/*    */   }
/*    */ 
/*    */   public Group saveGroup(Group group, GroupPerm groupPerm, Site site) {
/* 31 */     group.setSite(site);
/* 32 */     save(group);
/* 33 */     this.groupPermService.save(group, groupPerm);
/* 34 */     return group;
/*    */   }
/*    */ 
/*    */   public Group updateGroup(Group group, GroupPerm groupPerm) {
/* 38 */     update(group);
/* 39 */     this.groupPermService.update(groupPerm);
/* 40 */     return group;
/*    */   }
/*    */ 
/*    */   public Group save(Group bean) {
/* 44 */     bean.init();
/* 45 */     this.dao.save(bean);
/* 46 */     return bean;
/*    */   }
/*    */ 
/*    */   public Group update(Group bean) {
/* 50 */     bean = (Group)this.dao.update(bean);
/* 51 */     return bean;
/*    */   }
/*    */ 
/*    */   public Group deleteById(Integer id) {
/* 55 */     Group bean = (Group)this.dao.deleteById(id);
/* 56 */     return bean;
/*    */   }
/*    */ 
/*    */   public Group[] deleteByIds(Integer[] ids) {
/* 60 */     Group[] beans = new Group[ids.length];
/* 61 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 62 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 64 */     return beans;
/*    */   }
/*    */ 
/*    */   public Group[] updatePriority(Integer[] ids, Integer[] priority) {
/* 68 */     int len = ids.length;
/* 69 */     Group[] beans = new Group[len];
/* 70 */     for (int i = 0; i < len; i++) {
/* 71 */       beans[i] = findById(ids[i]);
/* 72 */       beans[i].setPriority(priority[i]);
/*    */     }
/* 74 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(GroupDao dao)
/*    */   {
/* 82 */     this.dao = dao;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setGroupPermService(GroupPermService groupPermService) {
/* 87 */     this.groupPermService = groupPermService;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.service.impl.GroupServiceImpl
 * JD-Core Version:    0.6.1
 */
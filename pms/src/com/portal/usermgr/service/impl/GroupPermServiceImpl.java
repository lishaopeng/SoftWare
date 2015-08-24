/*    */ package com.portal.usermgr.service.impl;
/*    */ 
/*    */ import com.portal.usermgr.dao.GroupPermDao;
/*    */ import com.portal.usermgr.entity.Group;
/*    */ import com.portal.usermgr.entity.GroupPerm;
/*    */ import com.portal.usermgr.service.GroupPermService;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.data.domain.Page;
/*    */ import org.springframework.stereotype.Service;
/*    */ import org.springframework.transaction.annotation.Transactional;
/*    */ 
/*    */ @Service
/*    */ @Transactional
/*    */ public class GroupPermServiceImpl
/*    */   implements GroupPermService
/*    */ {
/*    */   private GroupPermDao dao;
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public Page<GroupPerm> getPage(int pageNo, int pageSize)
/*    */   {
/* 18 */     return this.dao.getPage(pageNo, pageSize);
/*    */   }
/*    */ 
/*    */   @Transactional(readOnly=true)
/*    */   public GroupPerm findById(Integer id) {
/* 23 */     GroupPerm entity = (GroupPerm)this.dao.findById(id);
/* 24 */     return entity;
/*    */   }
/*    */ 
/*    */   public GroupPerm save(Group group, GroupPerm perm) {
/* 28 */     perm.setGroup(group);
/* 29 */     save(perm);
/* 30 */     group.setGroupPerm(perm);
/* 31 */     return perm;
/*    */   }
/*    */ 
/*    */   public GroupPerm save(GroupPerm bean) {
/* 35 */     this.dao.save(bean);
/* 36 */     return bean;
/*    */   }
/*    */ 
/*    */   public GroupPerm update(GroupPerm bean) {
/* 40 */     bean = (GroupPerm)this.dao.update(bean);
/* 41 */     return bean;
/*    */   }
/*    */ 
/*    */   public GroupPerm deleteById(Integer id) {
/* 45 */     GroupPerm bean = (GroupPerm)this.dao.deleteById(id);
/* 46 */     return bean;
/*    */   }
/*    */ 
/*    */   public GroupPerm[] deleteByIds(Integer[] ids) {
/* 50 */     GroupPerm[] beans = new GroupPerm[ids.length];
/* 51 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 52 */       beans[i] = deleteById(ids[i]);
/*    */     }
/* 54 */     return beans;
/*    */   }
/*    */ 
/*    */   @Autowired
/*    */   public void setDao(GroupPermDao dao)
/*    */   {
/* 61 */     this.dao = dao;
/*    */   }
/*    */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.service.impl.GroupPermServiceImpl
 * JD-Core Version:    0.6.1
 */
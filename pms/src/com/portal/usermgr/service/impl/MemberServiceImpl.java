/*     */ package com.portal.usermgr.service.impl;
/*     */ 
/*     */ import com.portal.usermgr.dao.MemberDao;
/*     */ import com.portal.usermgr.entity.Group;
/*     */ import com.portal.usermgr.entity.Member;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.GroupService;
/*     */ import com.portal.usermgr.service.MemberService;
/*     */ import com.portal.usermgr.service.UserService;
/*     */ import java.sql.Timestamp;
/*     */ import java.util.Set;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class MemberServiceImpl
/*     */   implements MemberService
/*     */ {
/*     */   private MemberDao dao;
/*     */   private UserService userService;
/*     */   private GroupService groupService;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Page<Member> getPage(int pageNo, int pageSize)
/*     */   {
/*  22 */     Page page = this.dao.getPage(pageNo, pageSize);
/*  23 */     return page;
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Page<Member> getPage(String key, Integer siteId, Integer groupId, String sortname, String sortorder, int pageNo, int pageSize)
/*     */   {
/*  29 */     return this.dao.getPage(key, siteId, groupId, sortname, sortorder, pageNo, 
/*  30 */       pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public int getNoCheckMemberCount() {
/*  35 */     return this.dao.getNoCheckMemberCount();
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Member findById(Integer id) {
/*  40 */     Member entity = (Member)this.dao.findById(id);
/*  41 */     return entity;
/*     */   }
/*     */ 
/*     */   public Member registerMember(User user, Member member, String ip, Integer groupId)
/*     */   {
/*  46 */     member.setRegisterIp(ip);
/*  47 */     member.setLastLoginIp(ip);
/*  48 */     if (groupId != null) {
/*  49 */       member.addToGroups(this.groupService.findById(groupId));
/*     */     }
/*  51 */     this.userService.save(user);
/*  52 */     member.setUser(user);
/*  53 */     save(member);
/*  54 */     user.setMember(member);
/*  55 */     return member;
/*     */   }
/*     */ 
/*     */   public Member updateMember(User user, Member member, Integer groupId, Integer siteId)
/*     */   {
/*  60 */     if (findById(user.getId()) != null) {
/*  61 */       member = update(member);
/*     */     } else {
/*  63 */       member.setUser(user);
/*  64 */       member = save(member);
/*  65 */       user.setMember(member);
/*     */     }
/*  67 */     this.userService.update(user);
/*  68 */     if ((groupId != null) && 
/*  69 */       (!groupId.equals(member.getGroup(siteId).getId()))) {
/*  70 */       member.getGroups().remove(member.getGroup(siteId));
/*  71 */       member.addToGroups(this.groupService.findById(groupId));
/*     */     }
/*     */ 
/*  74 */     return member;
/*     */   }
/*     */ 
/*     */   public void updateLoginInfo(User user, String lastLoginIp) {
/*  78 */     Member member = findById(user.getId());
/*  79 */     if (member != null) {
/*  80 */       member.setLastLoginIp(lastLoginIp);
/*  81 */       member.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
/*  82 */       if (member.getRegisterIp() == null) {
/*  83 */         member.setRegisterIp(lastLoginIp);
/*     */       }
/*  85 */       member.setLoginCount(Integer.valueOf(member.getLoginCount().intValue() + 1));
/*     */     }
/*     */   }
/*     */ 
/*     */   public Member save(Member bean) {
/*  90 */     bean.init();
/*  91 */     this.dao.save(bean);
/*  92 */     return bean;
/*     */   }
/*     */ 
/*     */   public Member update(Member bean) {
/*  96 */     bean = (Member)this.dao.update(bean);
/*  97 */     return bean;
/*     */   }
/*     */ 
/*     */   public Member updatePass(Integer memberId, String password) {
/* 101 */     User user = this.userService.updatePass(memberId, password);
/* 102 */     return user.getMember();
/*     */   }
/*     */ 
/*     */   public Member deleteById(Integer id) {
/* 106 */     Member bean = (Member)this.dao.deleteById(id);
/* 107 */     this.userService.deleteById(id);
/* 108 */     return bean;
/*     */   }
/*     */ 
/*     */   public Member[] deleteByIds(Integer[] ids) {
/* 112 */     Member[] beans = new Member[ids.length];
/* 113 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 114 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 116 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(MemberDao dao)
/*     */   {
/* 125 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setUserService(UserService userService) {
/* 130 */     this.userService = userService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setGroupService(GroupService groupService) {
/* 135 */     this.groupService = groupService;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.service.impl.MemberServiceImpl
 * JD-Core Version:    0.6.1
 */
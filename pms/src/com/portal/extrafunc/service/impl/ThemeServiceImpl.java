/*     */ package com.portal.extrafunc.service.impl;
/*     */ 
/*     */ /*     */ import java.sql.Timestamp;
/*     */ import java.util.Date;
/*     */ import java.util.List;

/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;

import com.portal.extrafunc.action.cache.ForumCache;
/*     */ import com.portal.extrafunc.action.cache.ForumStatisCache;
/*     */ import com.portal.extrafunc.dao.ThemeDao;
/*     */ import com.portal.extrafunc.entity.Theme;
/*     */ import com.portal.extrafunc.service.ForumOperateService;
/*     */ import com.portal.extrafunc.service.ForumService;
/*     */ import com.portal.extrafunc.service.ThemeService;
/*     */ import com.portal.extrafunc.service.ThemeTxtService;
/*     */ import com.portal.sysmgr.event.DelThemeEvent;
/*     */ import com.portal.sysmgr.service.SiteService;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.UserService;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class ThemeServiceImpl
/*     */   implements ThemeService
/*     */ {
/*     */   private ThemeDao dao;
/*     */   private UserService userService;
/*     */   private ForumService forumService;
/*     */   private SiteService siteService;
/*     */   private ThemeTxtService txtService;
/*     */   private ForumCache forumCache;
/*     */   private ForumStatisCache statisCache;
/*     */   private ForumOperateService operateService;
/*     */   private ApplicationContext applicationContext;
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public Page<Theme> getPage(int pageNo, int pageSize)
/*     */   {
/*  32 */     return this.dao.getPage(pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public Page<Theme> getThemePageForTag(Integer forumId, Integer status, Integer createrId, Integer replyId, int orderBy, int pageNo, int pageSize)
/*     */   {
/*  39 */     return this.dao.getThemePageForTag(forumId, status, createrId, replyId, 
/*  40 */       orderBy, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public List<Theme> getThemeAll() {
/*  45 */     return this.dao.getThemeAll();
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public Theme findById(Integer id) {
/*  50 */     Theme entity = this.dao.findById(id);
/*  51 */     return entity;
/*     */   }
/*     */ 
/*     */   @Override
public Theme save(Integer siteId, Integer userId, Integer forumId, String title, boolean img, boolean affix)
/*     */   {
/*  56 */     Theme theme = new Theme();
/*  57 */     theme.setCreater(this.userService.findById(userId));
/*  58 */     theme.setLastReplyer(this.userService.findById(userId));
/*  59 */     theme.setSite(this.siteService.findById(siteId));
/*  60 */     theme.setForum(this.forumService.findById(forumId));
/*  61 */     theme.setTitle(title);
/*  62 */     theme.setAffix(Boolean.valueOf(affix));
/*  63 */     theme.setImg(Boolean.valueOf(img));
/*  64 */     theme.init();
/*  65 */     this.dao.save(theme);
/*  66 */     this.txtService.save(theme);
/*  67 */     this.forumCache.updateForum(theme, Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(0));
/*  68 */     this.statisCache.updateStatis(this.siteService.findById(siteId), Integer.valueOf(1), Integer.valueOf(1));
/*  69 */     return theme;
/*     */   }
/*     */ 
/*     */   @Override
public Theme updateReply(Theme theme, User user, boolean affix) {
/*  73 */     if (!theme.getAffix().booleanValue()) {
/*  74 */       theme.setAffix(Boolean.valueOf(affix));
/*     */     }
/*  76 */     if (user.getAdmin() != null) {
/*  77 */       theme.setModerReply(Boolean.valueOf(true));
/*     */     }
/*  79 */     theme.setLastReplyer(user);
/*  80 */     theme.setLastReplyTime(new Timestamp(System.currentTimeMillis()));
/*  81 */     theme.setReplyCount(Integer.valueOf(theme.getReplyCount().intValue() + 1));
/*  82 */     this.txtService.update(theme.getId(), user.getId());
/*  83 */     this.forumCache.updateForum(theme, Integer.valueOf(0), Integer.valueOf(1), Integer.valueOf(0), Integer.valueOf(1));
/*  84 */     this.statisCache.updateStatis(theme.getSite(), Integer.valueOf(1), Integer.valueOf(1));
/*  85 */     return theme;
/*     */   }
/*     */ 
/*     */   @Override
public Theme topTheme(Integer themeId, Integer userId, Integer topLevel, Date topTime, String reason)
/*     */   {
/*  90 */     Theme theme = findById(themeId);
/*  91 */     theme.setStatus(topLevel);
/*  92 */     theme.setTopTime(topTime);
/*  93 */     this.operateService.save(theme.getId(), "THEME", "置顶", reason, 
/*  94 */       theme.getSite(), this.userService.findById(userId));
/*  95 */     return theme;
/*     */   }
/*     */ 
/*     */   @Override
public Theme essenaTheme(Integer themeId, Integer userId, Date essenaTime, String reason)
/*     */   {
/* 100 */     Theme theme = findById(themeId);
/* 101 */     theme.setEssena(Boolean.valueOf(true));
/* 102 */     theme.setEssenaTime(essenaTime);
/* 103 */     this.operateService.save(theme.getId(), "THEME", "精华", reason, 
/* 104 */       theme.getSite(), this.userService.findById(userId));
/* 105 */     return theme;
/*     */   }
/*     */ 
/*     */   @Override
public Theme lightTheme(Integer themeId, Integer userId, String color, Boolean bold, Boolean italic, Date lightTime, String reason)
/*     */   {
/* 110 */     Theme theme = findById(themeId);
/* 111 */     theme.setBold(bold);
/* 112 */     theme.setColor(color);
/* 113 */     theme.setItalic(italic);
/* 114 */     theme.setLightTime(lightTime);
/* 115 */     this.operateService.save(theme.getId(), "THEME", "高亮", reason, 
/* 116 */       theme.getSite(), this.userService.findById(userId));
/* 117 */     return theme;
/*     */   }
/*     */ 
/*     */   @Override
public Theme lockTheme(Integer themeId, Integer userId, Date lockTime, String reason)
/*     */   {
/* 122 */     Theme theme = findById(themeId);
/* 123 */     theme.setLock(Boolean.valueOf(true));
/* 124 */     theme.setLockTime(lockTime);
/* 125 */     this.operateService.save(theme.getId(), "THEME", "锁定", reason, 
/* 126 */       theme.getSite(), this.userService.findById(userId));
/* 127 */     return theme;
/*     */   }
/*     */ 
/*     */   @Override
public Theme moveTheme(Integer themeId, Integer userId, Integer forumId, String reason)
/*     */   {
/* 132 */     Theme theme = findById(themeId);
/* 133 */     theme.setForum(this.forumService.findById(forumId));
/* 134 */     this.operateService.save(theme.getId(), "THEME", "移动", reason, 
/* 135 */       theme.getSite(), this.userService.findById(userId));
/* 136 */     return theme;
/*     */   }
/*     */ 
/*     */   @Override
public void themeTopCheck() {
		/* 140 */List<Theme> list = this.dao.getThemeByTop();
/* 141 */     for (Theme theme : list)
/* 142 */       if (theme.getCheckTopTime()) {
/* 143 */         theme.setStatus(Integer.valueOf(0));
/* 144 */         theme.setTopTime(null);
/*     */       }
/*     */   }
/*     */ 
/*     */   @Override
public void themeLightCheck()
/*     */   {
		/* 150 */List<Theme> list = this.dao.getThemeByLight();
/* 151 */     for (Theme theme : list)
/* 152 */       if (theme.getCheckLightTime()) {
/* 153 */         theme.setColor(null);
/* 154 */         theme.setItalic(Boolean.valueOf(false));
/* 155 */         theme.setLightTime(null);
/*     */       }
/*     */   }
/*     */ 
/*     */   @Override
public void themeLockCheck()
/*     */   {
		/* 161 */List<Theme> list = this.dao.getThemeByLock();
/* 162 */     for (Theme theme : list)
/* 163 */       if (theme.getCheckLockTime()) {
/* 164 */         theme.setLock(Boolean.valueOf(false));
/* 165 */         theme.setLockTime(null);
/*     */       }
/*     */   }
/*     */ 
/*     */   @Override
public Theme update(Theme bean)
/*     */   {
/* 171 */     bean = this.dao.update(bean);
/* 172 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public Theme updateViewCount(Integer themeId, Integer viewCount) {
/* 176 */     Theme theme = findById(themeId);
/* 177 */     theme.setViewsCount(viewCount);
/* 178 */     return theme;
/*     */   }
/*     */ 
/*     */   @Override
public int deleteByForumId(Integer forumId) {
/* 182 */     this.txtService.deleteByForumId(forumId);
/* 183 */     return this.dao.deleteByForumId(forumId);
/*     */   }
/*     */ 
/*     */   @Override
public int deleteByUserId(Integer userId) {
/* 187 */     return this.dao.deleteByUserId(userId);
/*     */   }
/*     */ 
/*     */   @Override
public int deleteBySiteId(Integer siteId) {
/* 191 */     this.txtService.deleteBySiteId(siteId);
/* 192 */     return this.dao.deleteBySiteId(siteId);
/*     */   }
/*     */ 
/*     */   @Override
public int deleteByCategoryId(Integer categoryId) {
/* 196 */     return this.dao.deleteByCategoryId(categoryId);
/*     */   }
/*     */ 
/*     */   @Override
public Theme deleteById(Integer id) {
/* 200 */     Theme bean = this.dao.deleteById(id);
/* 201 */     this.applicationContext.publishEvent(new DelThemeEvent(bean));
/* 202 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public Theme[] deleteByIds(Integer[] ids) {
/* 206 */     Theme[] beans = new Theme[ids.length];
/* 207 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 208 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 210 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(ThemeDao dao)
/*     */   {
/* 225 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setUserService(UserService userService) {
/* 230 */     this.userService = userService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setForumService(ForumService forumService) {
/* 235 */     this.forumService = forumService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setSiteService(SiteService siteService) {
/* 240 */     this.siteService = siteService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setTxtService(ThemeTxtService txtService) {
/* 245 */     this.txtService = txtService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setForumCache(ForumCache forumCache) {
/* 250 */     this.forumCache = forumCache;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setStatisCache(ForumStatisCache statisCache) {
/* 255 */     this.statisCache = statisCache;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setOperateService(ForumOperateService operateService) {
/* 260 */     this.operateService = operateService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setApplicationContext(ApplicationContext applicationContext) {
/* 265 */     this.applicationContext = applicationContext;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.service.impl.ThemeServiceImpl
 * JD-Core Version:    0.6.1
 */
/*     */ package com.portal.datacenter.operatedata.service.impl;
/*     */ 
/*     */ /*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;

/*     */ import javax.servlet.http.HttpServletRequest;

/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.data.domain.PageImpl;
/*     */ import org.springframework.data.domain.PageRequest;
/*     */ import org.springframework.data.domain.Pageable;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ import org.springframework.web.util.UrlPathHelper;

import com.javapms.basic.plugin.springmvc.MessageResolver;
/*     */ import com.javapms.basic.utils.ServicesUtils;
/*     */ import com.portal.datacenter.operatedata.dao.LogDao;
/*     */ import com.portal.datacenter.operatedata.entity.Log;
/*     */ import com.portal.datacenter.operatedata.service.LogService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.utils.ContextTools;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.UserService;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class LogServiceImpl
/*     */   implements LogService
/*     */ {
/*     */   private UserService userService;
/*     */   private LogDao dao;
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public Page<Log> getPage(Integer category, Integer siteId, String username, String title, String ip, int pageNo, int pageSize)
/*     */   {
/*     */     Page page;
/*  37 */     if (StringUtils.isBlank(username)) {
/*  38 */       page = this.dao.getPage(category, siteId, null, title, ip, pageNo, 
/*  39 */         pageSize);
/*     */     } else {
/*  41 */       User user = this.userService.findByUsername(username);
/*  42 */       if (user != null) {
/*  43 */         page = this.dao.getPage(category, siteId, user.getId(), title, ip, 
/*  44 */           pageNo, pageSize);
/*     */       } else {
/*  46 */         Pageable p = new PageRequest(pageNo - 1, pageSize);
/*  47 */         page = new PageImpl(new ArrayList(0), p, 0L);
/*     */       }
/*     */     }
/*  50 */     return page;
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public Log findById(Integer id) {
/*  55 */     Log entity = this.dao.findById(id);
/*  56 */     return entity;
/*     */   }
/*     */ 
/*     */   public Log save(Integer category, Site site, User user, String url, String ip, Date date, String title, String content)
/*     */   {
/*  61 */     Log log = new Log();
/*  62 */     log.setSite(site);
/*  63 */     log.setUser(user);
/*  64 */     log.setCategory(category);
/*  65 */     log.setIp(ip);
/*  66 */     log.setTime(date);
/*  67 */     log.setUrl(url);
/*  68 */     log.setTitle(title);
/*  69 */     log.setContent(content);
/*  70 */     save(log);
/*  71 */     return log;
/*     */   }
/*     */ 
/*     */   @Override
public Log loginSuccess(HttpServletRequest request, User user, String title) {
/*  75 */     String ip = ServicesUtils.getIpAddr(request);
/*  76 */     UrlPathHelper helper = new UrlPathHelper();
/*  77 */     String uri = helper.getOriginatingRequestUri(request);
/*  78 */     Date date = new Date();
/*  79 */     Log log = save(Integer.valueOf(1), null, user, uri, ip, date, 
/*  80 */       MessageResolver.getMessage(request, title, new Object[0]), null);
/*  81 */     return log;
/*     */   }
/*     */ 
/*     */   @Override
public Log loginFailure(HttpServletRequest request, String title, String content)
/*     */   {
/*  86 */     String ip = ServicesUtils.getIpAddr(request);
/*  87 */     UrlPathHelper helper = new UrlPathHelper();
/*  88 */     String uri = helper.getOriginatingRequestUri(request);
/*  89 */     Date date = new Date();
/*  90 */     Log log = save(Integer.valueOf(2), null, null, uri, ip, date, 
/*  91 */       MessageResolver.getMessage(request, title, new Object[0]), content);
/*  92 */     return log;
/*     */   }
/*     */ 
/*     */   @Override
public Log operating(HttpServletRequest request, String title, String content)
/*     */   {
/*  97 */     Site site = ContextTools.getSite(request);
/*  98 */     User user = ContextTools.getUser(request);
/*  99 */     String ip = ServicesUtils.getIpAddr(request);
/* 100 */     UrlPathHelper helper = new UrlPathHelper();
/* 101 */     String uri = helper.getOriginatingRequestUri(request);
/* 102 */     Date date = new Date();
/* 103 */     Log log = save(Integer.valueOf(3), site, user, uri, ip, date, title, content);
/* 104 */     return log;
/*     */   }
/*     */ 
/*     */   @Override
public Log save(Log bean) {
/* 108 */     this.dao.save(bean);
/* 109 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public int deleteBatch(Integer category, Integer siteId, Integer days) {
/* 113 */     Date date = null;
/* 114 */     if ((days != null) && (days.intValue() > 0)) {
/* 115 */       Calendar cal = Calendar.getInstance();
/* 116 */       cal.add(5, -days.intValue());
/* 117 */       date = cal.getTime();
/*     */     }
/* 119 */     return this.dao.deleteBatch(category, siteId, date);
/*     */   }
/*     */ 
/*     */   @Override
public Log deleteById(Integer id) {
/* 123 */     Log bean = this.dao.deleteById(id);
/* 124 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public Log[] deleteByIds(Integer[] ids) {
/* 128 */     Log[] beans = new Log[ids.length];
/* 129 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 130 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 132 */     return beans;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setUserService(UserService userService)
/*     */   {
/* 140 */     this.userService = userService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(LogDao dao) {
/* 145 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.datacenter.operatedata.service.impl.LogServiceImpl
 * JD-Core Version:    0.6.1
 */
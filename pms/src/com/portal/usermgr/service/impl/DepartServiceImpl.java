/*     */ package com.portal.usermgr.service.impl;
/*     */ 
/*     */ /*     */ import java.util.List;
/*     */ import java.util.Set;

/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;

import com.portal.doccenter.entity.Channel;
/*     */ import com.portal.doccenter.service.ChannelService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.event.DelDepartEvent;
/*     */ import com.portal.usermgr.dao.DepartDao;
/*     */ import com.portal.usermgr.entity.Depart;
/*     */ import com.portal.usermgr.service.DepartService;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class DepartServiceImpl
/*     */   implements DepartService
/*     */ {
/*     */   private ChannelService channelService;
/*     */   private DepartDao dao;
/*     */   private ApplicationContext applicationContext;
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public Page<Depart> getPage(String key, Integer siteId, String sortname, String sortorder, int pageNo, int pageSize)
/*     */   {
/*  26 */     return this.dao.getPage(key, siteId, sortname, sortorder, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public List<Depart> getListByTag(Integer siteId, Integer parentId, Integer orderBy)
/*     */   {
/*  32 */     return this.dao.getListByTag(siteId, parentId, orderBy);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public List<Depart> getAllList(Integer siteId) {
/*  37 */     return this.dao.getAllList(siteId);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public List<Depart> getListNoParent(Integer siteId) {
/*  42 */     return this.dao.getListNoParent(siteId);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public List<Depart> getListByParent(Integer parentId) {
/*  47 */     return this.dao.getListByParent(parentId);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public Depart getDepartByPath(String path, Integer siteId) {
/*  52 */     return this.dao.getDepartByPath(path, siteId);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public Depart getDepartByName(String name, Integer siteId) {
/*  57 */     return this.dao.getDepartByName(name, siteId);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public Depart findById(Integer id) {
/*  62 */     Depart entity = this.dao.findById(id);
/*  63 */     return entity;
/*     */   }
/*     */ 
/*     */   @Override
public Depart save(Depart bean, Site site, Integer parentId, Integer[] channelIds)
/*     */   {
/*  68 */     if (parentId != null) {
/*  69 */       Depart parent = findById(parentId);
/*  70 */       bean.setParent(parent);
/*  71 */       parent.addToChilds(bean);
/*     */     }
/*  73 */     bean.setSite(site);
/*  74 */     if ((!bean.getAllChannel().booleanValue()) && 
/*  75 */       (channelIds != null)) {
/*  76 */       for (Integer cid : channelIds) {
/*  77 */         bean.addToChannels(this.channelService.findById(cid));
/*     */       }
/*     */     }
/*     */ 
/*  81 */     bean.init();
/*  82 */     this.dao.save(bean);
/*  83 */     bean.initTreeNumber();
/*  84 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public Depart update(Depart bean, Integer parentId, Integer[] channelIds) {
/*  88 */     bean = this.dao.update(bean);
/*  89 */     if (parentId != null) {
/*  90 */       Depart parent = findById(parentId);
/*  91 */       parent.addToChilds(bean);
/*  92 */       if ((bean.getParent() != null) && (bean.getParent().getChild() != null)) {
/*  93 */         bean.getParent().getChild().remove(bean);
/*     */       }
/*  95 */       bean.setParent(parent);
/*     */     } else {
/*  97 */       bean.setParent(null);
/*     */     }
		/* 99 */Set<Channel> channels = bean.getChannels();
/* 100 */     for (Channel channel : channels) {
/* 101 */       channel.getDeparts().remove(bean);
/*     */     }
/* 103 */     bean.getChannels().clear();
/* 104 */     if ((!bean.getAllChannel().booleanValue()) && 
/* 105 */       (channelIds != null)) {
/* 106 */       for (Integer cid : channelIds) {
/* 107 */         bean.addToChannels(this.channelService.findById(cid));
/*     */       }
/*     */     }
/*     */ 
/* 111 */     bean.initTreeNumber();
/* 112 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public Depart updatePrio(Integer id, Integer priority) {
/* 116 */     Depart d = findById(id);
/* 117 */     d.setPriority(priority);
/* 118 */     return d;
/*     */   }
/*     */ 
/*     */   @Override
public void deleteBySiteId(Integer siteId) {
		/* 122 */List<Depart> list = getAllList(siteId);
/* 123 */     for (Depart d : list)
/* 124 */       deleteById(d.getId());
/*     */   }
/*     */ 
/*     */   @Override
public Depart deleteById(Integer id)
/*     */   {
/* 129 */     Depart bean = this.dao.deleteById(id);
/* 130 */     this.applicationContext.publishEvent(new DelDepartEvent(bean));
		/* 131 */Set<Channel> channels = bean.getChannels();
/* 132 */     for (Channel channel : channels) {
/* 133 */       channel.getDeparts().remove(bean);
/*     */     }
/* 135 */     bean.getChannels().clear();
/* 136 */     moveChild(bean.getChild());
/* 137 */     Depart parent = bean.getParent();
/* 138 */     if (parent != null) {
/* 139 */       parent.getChild().remove(bean);
/*     */     }
/* 141 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public Depart[] deleteByIds(Integer[] ids) {
/* 145 */     Depart[] beans = new Depart[ids.length];
/* 146 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 147 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 149 */     return beans;
/*     */   }
/*     */ 
/*     */   private void moveChild(Set<Depart> childs)
/*     */   {
/* 162 */     for (Depart d : childs)
/* 163 */       d.setParent(null);
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(DepartDao dao)
/*     */   {
/* 188 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setChannelService(ChannelService channelService) {
/* 193 */     this.channelService = channelService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setApplicationContext(ApplicationContext applicationContext) {
/* 198 */     this.applicationContext = applicationContext;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.usermgr.service.impl.DepartServiceImpl
 * JD-Core Version:    0.6.1
 */
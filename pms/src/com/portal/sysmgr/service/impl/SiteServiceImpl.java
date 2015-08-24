/*     */ package com.portal.sysmgr.service.impl;
/*     */ 
/*     */ /*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;

/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.slf4j.Logger;
/*     */ import org.slf4j.LoggerFactory;
/*     */ import org.springframework.beans.BeanUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;

import com.portal.doccenter.entity.Channel;
/*     */ import com.portal.doccenter.entity.ChannelExt;
/*     */ import com.portal.doccenter.entity.ChannelTxt;
/*     */ import com.portal.doccenter.entity.ChnlTplSelection;
/*     */ import com.portal.doccenter.service.ChannelService;
/*     */ import com.portal.sysmgr.dao.SiteDao;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.service.ResourceService;
/*     */ import com.portal.sysmgr.service.SiteService;
/*     */ import com.portal.usermgr.entity.Admin;
/*     */ import com.portal.usermgr.entity.Depart;
/*     */ import com.portal.usermgr.entity.Role;
/*     */ import com.portal.usermgr.entity.RolePerm;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.AdminCheckService;
/*     */ import com.portal.usermgr.service.DepartService;
/*     */ import com.portal.usermgr.service.RoleService;
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class SiteServiceImpl
/*     */   implements SiteService
/*     */ {
/*  39 */   private static final Logger log = LoggerFactory.getLogger(SiteServiceImpl.class);
/*     */ 
/* 211 */   private Map<Integer, Channel> haveSaveIds = new HashMap();
/*     */   private AdminCheckService adminCheckService;
/*     */   private ResourceService resourceService;
/*     */   private RoleService roleService;
/*     */   private DepartService departService;
/*     */   private ChannelService channelService;
/*     */   private SiteDao dao;
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public List<Site> getList()
/*     */   {
/*  43 */     return this.dao.getList(false);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public List<Site> getListFromCache() {
/*  48 */     return this.dao.getList(true);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public Site findByDomain(String domain, boolean cacheable) {
/*  53 */     return this.dao.findByDomain(domain, cacheable);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public Site findById(Integer id) {
/*  58 */     Site entity = this.dao.findById(id);
/*  59 */     return entity;
/*     */   }
/*     */ 
/*     */   @Override
public Site save(Site bean, User user, Integer siteId, Integer[] channelIds) {
/*  63 */     bean.init();
/*  64 */     Admin admin = user.getAdmin();
/*  65 */     bean = this.dao.save(bean);
/*     */     try
/*     */     {
/*  68 */       this.resourceService.copyTplAndRes(findById(siteId), bean);
/*     */     } catch (IOException e) {
/*  70 */       System.out.println("模板拷贝失败");
/*     */     }
/*     */ 
/*  73 */     Role r = copyRole(bean, user, siteId);
/*     */ 
/*  75 */     Depart d = copyDepart(bean, user, siteId);
/*  76 */     admin.addToRoles(r);
/*  77 */     admin.addToDeparts(d);
/*     */ 
/*  79 */     copyChannel(bean, channelIds);
/*  80 */     this.adminCheckService.save(bean, admin, Byte.valueOf((byte)4), Boolean.valueOf(true), null);
/*  81 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public Site update(Site bean) {
/*  85 */     Site entity = findById(bean.getId());
/*  86 */     bean = this.dao.update(bean);
/*  87 */     if (bean.getPort() == null) {
/*  88 */       entity.setPort(null);
/*     */     }
/*  90 */     if (StringUtils.isBlank(bean.getContextPath())) {
/*  91 */       entity.setContextPath(null);
/*     */     }
/*  93 */     return entity;
/*     */   }
/*     */ 
/*     */   @Override
public void updateTplStyle(Integer siteId, String style) {
/*  97 */     Site site = findById(siteId);
/*  98 */     site.setTplStyle(style);
/*     */   }
/*     */ 
/*     */   @Override
public Site deleteById(Integer id) {
/* 102 */     this.adminCheckService.deleteBySiteId(id);
/* 103 */     Site bean = this.dao.deleteById(id);
/*     */     try {
/* 105 */       this.resourceService.delTplAndRes(bean);
/*     */     } catch (IOException e) {
/* 107 */       log.error("删除模板失败!", e);
/*     */     }
/* 109 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public Site[] deleteByIds(Integer[] ids) {
/* 113 */     Site[] beans = new Site[ids.length];
/* 114 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 115 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 117 */     return beans;
/*     */   }
/*     */ 
/*     */   private Role copyRole(Site bean, User user, Integer siteId)
/*     */   {
/* 122 */     Role r = new Role();
/* 123 */     RolePerm rp = new RolePerm();
/* 124 */     BeanUtils.copyProperties(user.getRole(siteId), r);
/* 125 */     BeanUtils.copyProperties(user.getRole(siteId).getRolePerm(), rp);
/* 126 */     r.setId(null);
/* 127 */     rp.setRole(null);
/* 128 */     r = this.roleService.saveRole(r, rp, bean);
/* 129 */     return r;
/*     */   }
/*     */ 
/*     */   private Depart copyDepart(Site bean, User user, Integer siteId)
/*     */   {
/* 134 */     Depart d = new Depart();
/* 135 */     BeanUtils.copyProperties(user.getDepart(siteId), d);
/* 136 */     d.setId(null);
/* 137 */     d.setChannels(null);
/* 138 */     d.setParent(null);
/* 139 */     d.setChild(null);
/* 140 */     d = this.departService.save(d, bean, null, null);
/* 141 */     return d;
/*     */   }
/*     */ 
/*     */   private void copyChannel(Site bean, Integer[] channelIds)
/*     */   {
/* 147 */     this.haveSaveIds.clear();
/* 148 */     if (channelIds != null)
/* 149 */       for (Integer channelId : channelIds) {
/* 150 */         Channel channel = this.channelService.findById(channelId);
/* 151 */         if ((channel.getChild() == null) || 
/* 152 */           (channel.getChild().size() == 0))
/* 153 */           saveChannel(channel, bean);
/*     */       }
/*     */   }
/*     */ 
/*     */   private Channel saveChannel(Channel channel, Site bean)
/*     */   {
/* 160 */     if (channel.getParent() != null)
/*     */     {
/*     */       Channel p;
/* 162 */       if (!this.haveSaveIds.containsKey(channel.getParent().getId()))
/*     */       {
/* 164 */         p = saveChannel(channel.getParent(), bean);
/*     */       }
/* 166 */       else p = this.haveSaveIds.get(channel.getParent().getId());
/*     */ 
/* 168 */       return saveChannelFinal(channel, p, bean);
/*     */     }
/* 170 */     return saveChannelFinal(channel, null, bean);
/*     */   }
/*     */ 
/*     */   private Channel saveChannelFinal(Channel channel, Channel parent, Site bean)
/*     */   {
/* 175 */     Channel c = new Channel();
/* 176 */     ChannelExt ext = new ChannelExt();
/* 177 */     ChannelTxt txt = new ChannelTxt();
/* 178 */     BeanUtils.copyProperties(channel, c);
/* 179 */     BeanUtils.copyProperties(channel.getExt(), ext);
/* 180 */     if (channel.getTxt() != null) {
/* 181 */       BeanUtils.copyProperties(channel.getTxt(), txt);
/* 182 */       txt.setId(null);
/*     */     }
/* 184 */     c.setId(null);
/*     */ 
/* 186 */     c.setChild(null);
/* 187 */     c.setViewGroups(null);
/* 188 */     c.setContriGroups(null);
/* 189 */     c.setDeparts(null);
/* 190 */     c.setTpls(null);
/* 191 */     ext.setId(null);
/* 192 */     if (channel.getTpls() != null) {
/* 193 */       for (ChnlTplSelection tpl : channel.getTpls()) {
/* 194 */         ChnlTplSelection tpls = new ChnlTplSelection();
/* 195 */         tpls.setModel(tpl.getModel());
/* 196 */         tpls.setTplDoc(tpl.getTplDoc());
/* 197 */         c.addToTpls(tpls);
/*     */       }
/*     */     }
/* 200 */     Channel cl = this.channelService.save(c, ext, txt, bean, null, null, null, 
/* 201 */       null, null, null);
/* 202 */     if (parent != null) {
/* 203 */       cl.setParent(parent);
/* 204 */       parent.addToChilds(cl);
/*     */     }
/*     */ 
/* 207 */     this.haveSaveIds.put(channel.getId(), cl);
/* 208 */     return cl;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setAdminCheckService(AdminCheckService adminCheckService)
/*     */   {
/* 222 */     this.adminCheckService = adminCheckService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setResourceService(ResourceService resourceService) {
/* 227 */     this.resourceService = resourceService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setRoleService(RoleService roleService) {
/* 232 */     this.roleService = roleService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDepartService(DepartService departService) {
/* 237 */     this.departService = departService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setChannelService(ChannelService channelService) {
/* 242 */     this.channelService = channelService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(SiteDao dao) {
/* 247 */     this.dao = dao;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.sysmgr.service.impl.SiteServiceImpl
 * JD-Core Version:    0.6.1
 */
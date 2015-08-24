/*     */ package com.portal.doccenter.service.impl;
/*     */ 
/*     */ import com.portal.doccenter.dao.ChannelDao;
/*     */ import com.portal.doccenter.entity.Channel;
/*     */ import com.portal.doccenter.entity.ChannelExt;
/*     */ import com.portal.doccenter.entity.ChannelTxt;
/*     */ import com.portal.doccenter.entity.ChnlTplSelection;
/*     */ import com.portal.doccenter.entity.Model;
/*     */ import com.portal.doccenter.service.ChannelExtService;
/*     */ import com.portal.doccenter.service.ChannelService;
/*     */ import com.portal.doccenter.service.ChannelTxtService;
/*     */ import com.portal.doccenter.service.ModelService;
/*     */ import com.portal.doccenter.service.WorkFlowService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.event.AddOrUpdateChannelEvent;
/*     */ import com.portal.sysmgr.event.DelChannelEvent;
/*     */ import com.portal.usermgr.entity.Admin;
/*     */ import com.portal.usermgr.entity.Depart;
/*     */ import com.portal.usermgr.entity.Group;
/*     */ import com.portal.usermgr.entity.Member;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.GroupService;
/*     */ import com.portal.usermgr.service.UserService;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Map.Entry;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.ArrayUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class ChannelServiceImpl
/*     */   implements ChannelService
/*     */ {
/*     */   private ChannelExtService channelExtService;
/*     */   private ChannelTxtService channelTxtService;
/*     */   private WorkFlowService workFlowService;
/*     */   private UserService userService;
/*     */   private GroupService groupService;
/*     */   private ModelService modelService;
/*     */   private ChannelDao dao;
/*     */   private ApplicationContext applicationContext;
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Channel> getChannelListByTag(Integer siteId, Integer parentId, Boolean alone, boolean show, int count)
/*     */   {
/*  43 */     return this.dao.getChannelListByTag(siteId, parentId, alone, show, count);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Page<Channel> getChannelPageByTag(Integer siteId, Integer parentId, Boolean alone, boolean show, int pageNo, int pageSize)
/*     */   {
/*  49 */     return this.dao.getChannelPageByTag(siteId, parentId, alone, show, pageNo, 
/*  50 */       pageSize);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Channel> getChannelBySite(Integer siteId, Integer parentId, String key, String sortname, String sortorder, Boolean alone)
/*     */   {
/*  56 */     return this.dao.getChannelBySite(siteId, parentId, key, sortname, sortorder, 
/*  57 */       alone);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Channel> getChannelByAdmin(Integer userId, Integer siteId, Integer parentId, String key, String sortname, String sortorder, Boolean alone)
/*     */   {
/*  64 */     User user = this.userService.findById(userId);
/*  65 */     Depart depart = user.getAdmin().getDepart(siteId);
/*  66 */     if (user.getTakeDepart(siteId).booleanValue()) {
/*  67 */       Integer departId = null;
/*  68 */       if ((!user.getAllChannel(siteId)) && (depart != null)) {
/*  69 */         departId = depart.getId();
/*     */       }
/*  71 */       return this.dao.getChannelByAdmin(siteId, departId, parentId, key, 
/*  72 */         sortname, sortorder, alone);
/*     */     }
/*  74 */     return this.dao.getChannelByAdminAndTake(siteId, 
/*  75 */       user.getAdmin().getId(), parentId, key, sortname, 
/*  76 */       sortorder, alone);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public List<Channel> getChannelByModel(Integer parentId, Integer modelId, User user, Integer siteId)
/*     */   {
/*  83 */     Depart depart = user.getDepart(siteId);
/*  84 */     if (user.getAllChannel(siteId)) {
/*  85 */       return this.dao.getChannelByModel(parentId, modelId, null, siteId);
/*     */     }
/*  87 */     if (user.getTakeDepart(siteId).booleanValue()) {
/*  88 */       return this.dao.getChannelByModel(parentId, modelId, depart.getId(), 
/*  89 */         siteId);
/*     */     }
/*  91 */     return this.dao.getChannelByModelAndTake(parentId, modelId, user
/*  92 */       .getAdmin().getId(), siteId);
/*     */   }
/*     */ 
/*     */   public List<Channel> getChannelByModelAndMember(Integer parentId, Integer modelId, User user, Integer siteId)
/*     */   {
/*  99 */     if (user.getAdmin() != null) {
/* 100 */       return this.dao.getChannelByModelAndMember(parentId, modelId, null, 
/* 101 */         siteId);
/*     */     }
/* 103 */     if (user.getMember() == null) {
/* 104 */       return new ArrayList();
/*     */     }
/* 106 */     if (user.getMember().getGroup(siteId) == null) {
/* 107 */       return new ArrayList();
/*     */     }
/* 109 */     return this.dao.getChannelByModelAndMember(parentId, modelId, user
/* 110 */       .getMember().getGroupId(siteId), siteId);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Channel findByNumber(String number)
/*     */   {
/* 116 */     return this.dao.findByNumber(number);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Channel findByName(String name) {
/* 121 */     return this.dao.findByName(name);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Channel findById(Integer id) {
/* 126 */     Channel entity = (Channel)this.dao.findById(id);
/* 127 */     return entity;
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Channel findByPath(String path, Integer siteId) {
/* 132 */     return this.dao.findByPath(path, siteId, false);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public Channel findByPathForTag(String path, Integer siteId) {
/* 137 */     return this.dao.findByPath(path, siteId, true);
/*     */   }
/*     */ 
/*     */   @Transactional(readOnly=true)
/*     */   public int getAllChannelCount(Integer siteId) {
/* 142 */     return this.dao.getAllChannelCount(siteId);
/*     */   }
/*     */ 
/*     */   public Channel save(Channel bean, ChannelExt ext, ChannelTxt txt, Site site, User user, Integer flowId, Integer[] viewGroupIds, Integer[] contriGroupIds, Integer parentId, Map<String, String> tpls)
/*     */   {
/* 148 */     if (parentId != null) {
/* 149 */       Channel parent = findById(parentId);
/* 150 */       bean.setParent(parent);
/* 151 */       parent.addToChilds(bean);
/*     */     }
/* 153 */     if (tpls != null) {
/* 154 */       addTpl(tpls, bean);
/*     */     }
/* 156 */     bean.setSite(site);
/* 157 */     if (flowId != null) {
/* 158 */       bean.setFlow(this.workFlowService.findById(flowId));
/*     */     }
/* 160 */     bean.setInputDepart(user.getDepart(site.getId()));
/* 161 */     bean.init();
/* 162 */     this.dao.save(bean);
/* 163 */     this.channelExtService.save(ext, bean);
/* 164 */     this.channelTxtService.save(txt, bean);
/* 165 */     if (!ArrayUtils.isEmpty(viewGroupIds)) {
/* 166 */       for (Integer gid : viewGroupIds) {
/* 167 */         bean.addToViewGroups(this.groupService.findById(gid));
/*     */       }
/*     */     }
/* 170 */     if (!ArrayUtils.isEmpty(contriGroupIds)) {
/* 171 */       for (Integer gid : contriGroupIds) {
/* 172 */         bean.addToContriGroups(this.groupService.findById(gid));
/*     */       }
/*     */     }
/* 175 */     if ((user != null) && (!user.getAllChannel(site.getId()))) {
/* 176 */       user.getDepart(site.getId()).addToChannels(bean);
/*     */     }
/* 178 */     bean.initTreeNumber();
/* 179 */     this.applicationContext.publishEvent(new AddOrUpdateChannelEvent(bean));
/* 180 */     return bean;
/*     */   }
/*     */ 
/*     */   public Channel update(Channel bean, ChannelExt ext, ChannelTxt txt, Integer flowId, Integer[] viewGroupIds, Integer[] contriGroupIds, Integer parentId, Map<String, String> tpls)
/*     */   {
/* 186 */     bean = (Channel)this.dao.update(bean);
/* 187 */     Channel c = bean.getParent();
/* 188 */     if (c != null) {
/* 189 */       c.getChild().remove(bean);
/*     */     }
/* 191 */     if (parentId != null) {
/* 192 */       Channel parent = findById(parentId);
/* 193 */       parent.addToChilds(bean);
/* 194 */       if (bean.getParent() != null) {
/* 195 */         bean.getParent().getChild().remove(bean);
/*     */       }
/* 197 */       bean.setParent(parent);
/*     */     } else {
/* 199 */       bean.setParent(null);
/*     */     }
/* 201 */     if (flowId != null)
/* 202 */       bean.setFlow(this.workFlowService.findById(flowId));
/*     */     else {
/* 204 */       bean.setFlow(null);
/*     */     }
/* 206 */     this.channelExtService.update(ext);
/* 207 */     this.channelTxtService.update(txt, bean);
/* 208 */     for (Group g : bean.getViewGroups()) {
/* 209 */       g.getViewChannels().remove(bean);
/*     */     }
/* 211 */     bean.getViewGroups().clear();
/* 212 */     if (!ArrayUtils.isEmpty(viewGroupIds)) {
/* 213 */       for (Integer gid : viewGroupIds) {
/* 214 */         bean.addToViewGroups(this.groupService.findById(gid));
/*     */       }
/*     */     }
/* 217 */     for (Group g : bean.getContriGroups()) {
/* 218 */       g.getContriChannels().remove(bean);
/*     */     }
/* 220 */     bean.getContriGroups().clear();
/* 221 */     if (!ArrayUtils.isEmpty(contriGroupIds)) {
/* 222 */       for (Integer gid : contriGroupIds) {
/* 223 */         bean.addToContriGroups(this.groupService.findById(gid));
/*     */       }
/*     */     }
/* 226 */     bean.getTpls().clear();
/* 227 */     if (tpls != null) {
/* 228 */       addTpl(tpls, bean);
/*     */     }
/* 230 */     bean.initTreeNumber();
/* 231 */     this.applicationContext.publishEvent(new AddOrUpdateChannelEvent(bean));
/* 232 */     return bean;
/*     */   }
/*     */ 
/*     */   public Channel updatePrio(Integer id, Integer priority) {
/* 236 */     Channel c = findById(id);
/* 237 */     c.setPriority(priority);
/* 238 */     return c;
/*     */   }
/*     */ 
/*     */   public int updateChannelByInputDepart(Integer departId) {
/* 242 */     return this.dao.updateChannelByInputDepart(departId);
/*     */   }
/*     */ 
/*     */   public int delChannelByInputDepart(Integer departId) {
/* 246 */     return this.dao.delChannelByInputDepart(departId);
/*     */   }
/*     */ 
/*     */   public int updateChannelByWorkFlow(Integer flowId) {
/* 250 */     return this.dao.updateChannelByWorkFlow(flowId);
/*     */   }
/*     */ 
/*     */   public Channel delChannel(Integer id, Boolean del, Integer cid) {
/* 254 */     Channel c = (Channel)this.dao.findById(id);
/* 255 */     if (del != null) {
/* 256 */       if (del.booleanValue()) {
/* 257 */         if ((c.getChild() != null) && (c.getChild().size() > 0)) {
/* 258 */           delChild(c.getChild());
/*     */         }
/*     */       }
/* 261 */       else if ((c.getChild() != null) && (c.getChild().size() > 0)) {
/* 262 */         moveChild(c.getChild(), cid);
/*     */       }
/*     */     }
/*     */ 
/* 266 */     deleteById(c.getId());
/* 267 */     return null;
/*     */   }
/*     */ 
/*     */   private void delChild(Set<Channel> childs) {
/* 271 */     for (Channel c : childs) {
/* 272 */       if ((c.getChild() != null) && (c.getChild().size() > 0)) {
/* 273 */         delChild(c.getChild());
/*     */       }
/* 275 */       deleteById(c.getId());
/*     */     }
/*     */   }
/*     */ 
/*     */   private void moveChild(Set<Channel> childs, Integer cid) {
/* 280 */     Channel parent = findById(cid);
/* 281 */     for (Channel c : childs) {
/* 282 */       parent.addToChilds(c);
/* 283 */       c.getParent().getChild().remove(c);
/* 284 */       c.setParent(parent);
/* 285 */       c.setNumber(parent.getNumber() + c.getId() + "-");
/*     */     }
/* 287 */     this.applicationContext.publishEvent(new AddOrUpdateChannelEvent(parent));
/*     */   }
/*     */ 
/*     */   public Channel deleteById(Integer id) {
/* 291 */     Channel entity = (Channel)this.dao.findById(id);
/* 292 */     for (Group group : entity.getViewGroups()) {
/* 293 */       group.getViewChannels().remove(entity);
/*     */     }
/* 295 */     for (Group group : entity.getContriGroups()) {
/* 296 */       group.getContriChannels().remove(entity);
/*     */     }
/* 298 */     for (Depart depart : entity.getDeparts()) {
/* 299 */       depart.getChannels().remove(entity);
/*     */     }
/* 301 */     if (entity.getParent() != null) {
/* 302 */       entity.getParent().getChild().remove(entity);
/*     */     }
/* 304 */     entity = (Channel)this.dao.deleteById(id);
/* 305 */     this.applicationContext.publishEvent(new DelChannelEvent(entity));
/* 306 */     return entity;
/*     */   }
/*     */ 
/*     */   public Channel[] deleteByIds(Integer[] ids) {
/* 310 */     Channel[] beans = new Channel[ids.length];
/* 311 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 312 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 314 */     return beans;
/*     */   }
/*     */ 
/*     */   public Channel[] updatePriority(Integer[] ids, Integer[] priority) {
/* 318 */     int len = ids.length;
/* 319 */     Channel[] beans = new Channel[len];
/* 320 */     for (int i = 0; i < len; i++) {
/* 321 */       beans[i] = findById(ids[i]);
/* 322 */       beans[i].setPriority(priority[i]);
/*     */     }
/* 324 */     return beans;
/*     */   }
/*     */ 
/*     */   private void addTpl(Map<String, String> tpls, Channel bean) {
/* 328 */     if (tpls != null)
/* 329 */       for (Map.Entry entry : tpls.entrySet()) {
/* 330 */         ChnlTplSelection cs = new ChnlTplSelection();
/* 331 */         Model m = this.modelService
/* 332 */           .findById(Integer.valueOf(Integer.parseInt((String)entry.getKey())));
/* 333 */         if (!StringUtils.isBlank((String)entry.getValue()))
/* 334 */           cs.setTplDoc((String)entry.getValue());
/*     */         else {
/* 336 */           cs.setTplDoc(m.getTplDoc());
/*     */         }
/* 338 */         cs.setModel(m);
/* 339 */         bean.addToTpls(cs);
/*     */       }
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setChannelExtService(ChannelExtService channelExtService)
/*     */   {
/* 355 */     this.channelExtService = channelExtService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setChannelTxtService(ChannelTxtService channelTxtService) {
/* 360 */     this.channelTxtService = channelTxtService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setWorkFlowService(WorkFlowService workFlowService) {
/* 365 */     this.workFlowService = workFlowService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setModelService(ModelService modelService) {
/* 370 */     this.modelService = modelService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setUserService(UserService userService) {
/* 375 */     this.userService = userService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setGroupService(GroupService groupService) {
/* 380 */     this.groupService = groupService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(ChannelDao dao) {
/* 385 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setApplicationContext(ApplicationContext applicationContext) {
/* 390 */     this.applicationContext = applicationContext;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.service.impl.ChannelServiceImpl
 * JD-Core Version:    0.6.1
 */
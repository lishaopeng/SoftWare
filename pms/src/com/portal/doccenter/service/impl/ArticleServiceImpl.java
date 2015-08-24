/*     */ package com.portal.doccenter.service.impl;
/*     */ 
/*     */ /*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;

/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
/*     */ import org.springframework.context.ApplicationContext;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Service;
/*     */ import org.springframework.transaction.annotation.Transactional;

import com.portal.datacenter.lucene.LuceneDocService;
/*     */ import com.portal.doccenter.dao.ArticleDao;
/*     */ import com.portal.doccenter.dao.ArticleQueryDao;
/*     */ import com.portal.doccenter.entity.Article;
/*     */ import com.portal.doccenter.entity.ArticleExt;
/*     */ import com.portal.doccenter.entity.ArticleTxt;
/*     */ import com.portal.doccenter.entity.Channel;
/*     */ import com.portal.doccenter.entity.FlowDetail;
/*     */ import com.portal.doccenter.service.ArticleExtService;
/*     */ import com.portal.doccenter.service.ArticleService;
/*     */ import com.portal.doccenter.service.ArticleTxtService;
/*     */ import com.portal.doccenter.service.ArticleTypeService;
/*     */ import com.portal.doccenter.service.ChannelService;
/*     */ import com.portal.doccenter.service.DocStatisService;
/*     */ import com.portal.doccenter.service.FlowDetailService;
/*     */ import com.portal.doccenter.service.ModelService;
/*     */ import com.portal.sysmgr.entity.Site;
/*     */ import com.portal.sysmgr.event.AddArticleEvent;
/*     */ import com.portal.sysmgr.event.DelArticleEvent;
/*     */ import com.portal.sysmgr.event.EmptyArticleEvent;
/*     */ import com.portal.sysmgr.event.UpdateArticleEvent;
/*     */ import com.portal.usermgr.entity.Depart;
/*     */ import com.portal.usermgr.entity.Role;
/*     */ import com.portal.usermgr.entity.User;
/*     */ import com.portal.usermgr.service.GroupService;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Service
/*     */ @Transactional
/*     */ public class ArticleServiceImpl
/*     */   implements ArticleService
/*     */ {
/*     */   private ChannelService channelService;
/*     */   private ArticleExtService articleExtService;
/*     */   private ArticleTxtService articleTxtService;
/*     */   private DocStatisService statisService;
/*     */   private ArticleTypeService articleTypeService;
/*     */   private GroupService groupService;
/*     */   private ModelService modelService;
/*     */   private LuceneDocService luceneDocService;
/*     */   private FlowDetailService detailService;
/*     */   private ArticleDao dao;
/*     */   private ArticleQueryDao articleQueryDao;
/*     */   private ApplicationContext applicationContext;
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public Page<Article> getPageArticle(String title, Integer[] typeIds, Integer[] modelIds, Integer inputDepartId, boolean top, boolean recommend, Byte[] statuss, Integer siteId, User user, Integer chnlId, int orderBy, String sortname, String sortorder, int pageNo, int pageSize)
/*     */   {
/*  52 */     return this.dao.getPageArticle(title, typeIds, modelIds, inputDepartId, top, 
/*  53 */       recommend, statuss, siteId, user.getId(), 
/*  54 */       user.getDepartId(siteId), user.getRoleId(siteId), 
/*  55 */       getTreeNumber(chnlId), user.getManageStatus(siteId), 
/*  56 */       Boolean.valueOf(user.getAllChannel(siteId)), user.getTakeDepart(siteId), 
/*  57 */       orderBy, sortname, sortorder, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Override
public Page<Article> getPageDocByMember(String title, Integer[] typeIds, Integer[] modelIds, boolean top, boolean recommend, Integer siteId, Integer userId, Integer chnlId, int pageNo, int pageSize)
/*     */   {
/*  63 */     return this.dao.getPageDocByMember(title, typeIds, modelIds, top, recommend, 
/*  64 */       siteId, userId, getTreeNumber(chnlId), pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public long getCountByChecking(Integer siteId, User user, boolean checking) {
/*  69 */     return this.dao.getCountByChecking(siteId, user.getId(), 
/*  70 */       user.getDepartId(siteId), user.getRoleId(siteId), 
/*  71 */       user.getManageStatus(siteId), Boolean.valueOf(user.getAllChannel(siteId)), 
/*  72 */       checking);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public int getAllArtiCount(Integer siteId, boolean check) {
/*  77 */     return this.dao.getAllArtiCount(siteId, check);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public long getCountDoc(Integer chnlId) {
/*  82 */     return this.dao.getCountDoc(getTreeNumber(chnlId));
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public long getCountByDepartSign(Integer siteId, Integer departId) {
/*  87 */     return this.dao.getCountByDepartSign(siteId, departId);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public Article getSide(Integer id, Integer siteId, Integer channelId, boolean next)
/*     */   {
/*  93 */     return this.dao.getSide(id, siteId, channelId, next);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public List<Article> getListTagByIds(Integer[] ids, int orderBy) {
/*  98 */     if (ids.length == 1) {
/*  99 */       Article article = findById(ids[0]);
/*     */       List list;
/* 101 */       if (article != null) {
				/* 102 */list = new ArrayList(1);
/* 103 */         list.add(article);
/*     */       } else {
/* 105 */         list = new ArrayList(0);
/*     */       }
/* 107 */       return list;
/*     */     }
/* 109 */     return this.dao.getListTagByIds(ids, orderBy);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public Page<Article> getPageTagByChannelIds(Integer[] channelIds, Integer siteId, Integer[] modelIds, Integer[] typeIds, Integer inputDepartId, Integer userId, Boolean recommend, Date date, int orderBy, int callLevel, int pageNo, int pageSize)
/*     */   {
/* 118 */     return this.dao.getPageTagByChannelIds(channelIds, siteId, modelIds, 
/* 119 */       typeIds, inputDepartId, userId, getTreeNumber(channelIds), 
/* 120 */       recommend, date, orderBy, callLevel, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public List<Article> getListTagByChannelIds(Integer[] channelIds, Integer siteId, Integer[] modelIds, Integer[] typeIds, Integer inputDepartId, Integer userId, Boolean recommend, Date date, int orderBy, int callLevel, Integer first, Integer count)
/*     */   {
/* 128 */     return this.dao.getListTagByChannelIds(channelIds, siteId, modelIds, 
/* 129 */       typeIds, inputDepartId, userId, getTreeNumber(channelIds), 
/* 130 */       recommend, date, orderBy, callLevel, first, count);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public Page<Article> getPageTagByModelIds(Integer[] modelIds, Integer[] typeIds, Integer siteId, Boolean recommend, int orderBy, int pageNo, int pageSize)
/*     */   {
/* 137 */     return this.dao.getPageTagByModelIds(modelIds, typeIds, siteId, recommend, 
/* 138 */       orderBy, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public List<Article> getListTagByModelIds(Integer[] modelIds, Integer[] typeIds, Integer siteId, Boolean recommend, int orderBy, Integer first, Integer count)
/*     */   {
/* 145 */     return this.dao.getListTagByModelIds(modelIds, typeIds, siteId, recommend, 
/* 146 */       orderBy, first, count);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public List<Object> getCountByDepart(Integer siteId, Integer cid, Integer dId, Date start, Date end)
/*     */   {
/* 152 */     return this.dao
/* 153 */       .getCountByDepart(siteId, getTreeNumber(cid), dId, start, end);
/*     */   }
/*     */ 
/*     */   @Override
@Transactional(readOnly=true)
/*     */   public Article findById(Integer id) {
/* 158 */     Article entity = this.dao.findById(id);
/* 159 */     return entity;
/*     */   }
/*     */ 
/*     */   @Override
public Article save(Article bean, ArticleExt ext, ArticleTxt txt, Site site, User user, Integer[] viewGroupIds, String[] attPaths, String[] attNames, String[] imgPaths, String[] imgDescs, Boolean[] thumbs, String[] imgStyles, Integer channelId, Integer modelId, boolean isMember)
/*     */   {
/* 167 */     bean.setChannel(this.channelService.findById(channelId));
/* 168 */     if (StringUtils.isBlank(bean.getStyle()))
/* 169 */       bean.setStyle("," + this.articleTypeService.getDef().getId() + ",");
/*     */     else {
/* 171 */       bean.setStyle("," + bean.getStyle() + ",");
/*     */     }
/* 173 */     if (modelId != null) {
/* 174 */       bean.setModel(this.modelService.findById(modelId));
/*     */     }
/* 176 */     bean.setUser(user);
/* 177 */     bean.setSite(site);
/* 178 */     if (bean.getStatus() == null) {
/* 179 */       if (!isMember)
/* 180 */         flowstep(bean, site, user);
/*     */       else {
/* 182 */         bean.setStatus(Byte.valueOf((byte)1));
/*     */       }
/*     */     }
/* 185 */     bean.init();
/* 186 */     this.dao.save(bean);
/* 187 */     this.articleExtService.save(ext, bean);
/* 188 */     this.articleTxtService.save(txt, bean);
/* 189 */     this.statisService.save(bean);
/* 190 */     if ((viewGroupIds != null) && (viewGroupIds.length > 0)) {
/* 191 */       for (Integer gid : viewGroupIds) {
/* 192 */         bean.addToGroups(this.groupService.findById(gid));
/*     */       }
/*     */     }
/* 195 */     if ((attPaths != null) && (attPaths.length > 0)) {
/* 196 */       int i = 0; for (int len = attPaths.length; i < len; i++) {
/* 197 */         if (!StringUtils.isBlank(attPaths[i])) {
/* 198 */           bean.addToAttachmemts(attPaths[i], attNames[i]);
/*     */         }
/*     */       }
/*     */     }
/* 202 */     if ((imgPaths != null) && (imgPaths.length > 0)) {
/* 203 */       for (int i = 0; i < imgPaths.length; i++) {
/* 204 */         if (!StringUtils.isBlank(imgPaths[i])) {
/* 205 */           bean.addToPictures(imgPaths[i], imgDescs[i], thumbs[i], 
/* 206 */             imgStyles[i]);
/*     */         }
/*     */       }
/*     */     }
/* 210 */     this.luceneDocService.addDoc(bean);
/* 211 */     this.applicationContext.publishEvent(new AddArticleEvent(bean));
/* 212 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public Article update(Article bean, ArticleExt ext, ArticleTxt txt, Integer[] channelIds, Integer[] viewGroupIds, String[] attPaths, String[] attNames, String[] imgPaths, String[] imgDescs, Boolean[] thumbs, String[] imgStyles, Map<String, String> attr, Integer channelId, User user, boolean isMember)
/*     */   {
/* 220 */     bean = this.dao.update(bean);
/* 221 */     if (StringUtils.isBlank(bean.getStyle()))
/* 222 */       bean.setStyle("," + this.articleTypeService.getDef().getId() + ",");
/*     */     else {
/* 224 */       bean.setStyle("," + bean.getStyle() + ",");
/*     */     }
/* 226 */     if (channelId != null) {
/* 227 */       bean.setChannel(this.channelService.findById(channelId));
/*     */     }
/* 229 */     if (isMember) {
/* 230 */       bean.setStatus(Byte.valueOf((byte)1));
/*     */     }
/* 232 */     this.articleExtService.update(ext);
/* 233 */     this.articleTxtService.update(txt, bean);
/* 234 */     if (attr != null) {
/* 235 */       bean.getAttr().clear();
/* 236 */       bean.getAttr().putAll(attr);
/*     */     }
/* 238 */     Set groups = bean.getViewGroups();
/* 239 */     groups.clear();
/* 240 */     if ((viewGroupIds != null) && (viewGroupIds.length > 0)) {
/* 241 */       for (Integer gid : viewGroupIds) {
/* 242 */         groups.add(this.groupService.findById(gid));
/*     */       }
/*     */     }
/* 245 */     bean.getAtts().clear();
/* 246 */     if ((attPaths != null) && (attPaths.length > 0)) {
/* 247 */       int i = 0; for (int len = attPaths.length; i < len; i++) {
/* 248 */         if (!StringUtils.isBlank(attPaths[i])) {
/* 249 */           bean.addToAttachmemts(attPaths[i], attNames[i]);
/*     */         }
/*     */       }
/*     */     }
/* 253 */     bean.getPics().clear();
/* 254 */     if ((imgPaths != null) && (imgPaths.length > 0)) {
/* 255 */       for (int i = 0; i < imgPaths.length; i++) {
/* 256 */         if (!StringUtils.isBlank(imgPaths[i])) {
/* 257 */           bean.addToPictures(imgPaths[i], imgDescs[i], thumbs[i], 
/* 258 */             imgStyles[i]);
/*     */         }
/*     */       }
/*     */     }
/* 262 */     this.luceneDocService.updateDoc(bean);
/* 263 */     this.applicationContext.publishEvent(new UpdateArticleEvent(bean));
/* 264 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public int moveDoc(Integer chnlId, Map<String, String> channels) {
/* 268 */     int count = 0;
/* 269 */     Channel c = this.channelService.findById(chnlId);
/* 270 */     for (Map.Entry entry : channels.entrySet()) {
/* 271 */       if (StringUtils.isNotBlank((String)entry.getValue())) {
/* 272 */         this.dao.updateMoveDoc(c.getNumber(), Integer.valueOf(Integer.parseInt(
/* 273 */           (String)entry
/* 273 */           .getKey())), this.channelService.findById(
/* 274 */           Integer.valueOf(Integer.parseInt((String)entry.getValue()))));
/* 275 */         this.luceneDocService.updateChannel(c.getSite().getId(), 
/* 276 */           Integer.valueOf(Integer.parseInt((String)entry.getValue())));
/* 277 */         count++;
/*     */       }
/*     */     }
/* 280 */     this.luceneDocService.updateChannel(c.getSite().getId(), chnlId);
/* 281 */     c.getSite().initTime();
/* 282 */     return count;
/*     */   }
/*     */ 
/*     */   @Override
public int emptyDoc(Integer chnlId) {
/* 286 */     Channel c = this.channelService.findById(chnlId);
/* 287 */     this.applicationContext.publishEvent(new EmptyArticleEvent(c));
/* 288 */     int count = this.articleQueryDao.emptyArticlePage(c.getNumber());
/* 289 */     this.luceneDocService.deleteSearchIndex(c.getSite().getId(), chnlId);
/* 290 */     return count;
/*     */   }
/*     */ 
/*     */   public Article check(Integer id, User user) {
/* 294 */     Article article = findById(id);
/* 295 */     Integer siteId = article.getSite().getId();
/* 296 */     Role role = user.getRole(siteId);
/* 297 */     Integer roleId = role.getId();
/* 298 */     if (!article.isChecked()) {
/* 299 */       if (article.getChannel().getFlow() != null) {
/* 300 */         if (article.getChannel().isDocChecked(user.getAdmin())) {
/* 301 */           article.setStatus(Byte.valueOf((byte)2));
/* 302 */           article.setCheckUser(user);
/* 303 */           article.setRole(role);
/* 304 */           this.luceneDocService.addDoc(article);
/* 305 */           this.applicationContext.publishEvent(new UpdateArticleEvent(
/* 306 */             article));
/*     */         } else {
/* 308 */           article.setStatus(Byte.valueOf((byte)1));
/* 309 */           article.setCheckUser(user);
/* 310 */           article.setRole(article.getChannel().getNextRole(roleId));
/*     */         }
/* 312 */         return article;
/*     */       }
/* 314 */       article.setStatus(Byte.valueOf((byte)2));
/* 315 */       article.setCheckUser(user);
/* 316 */       this.luceneDocService.addDoc(article);
/* 317 */       this.applicationContext.publishEvent(new UpdateArticleEvent(article));
/*     */     } else {
/* 319 */       article.setStatus(Byte.valueOf((byte)1));
/* 320 */       this.luceneDocService.deleteDoc(article.getId());
/* 321 */       this.applicationContext.publishEvent(new DelArticleEvent(article, Integer.valueOf(1)));
/*     */     }
/* 323 */     return article;
/*     */   }
/*     */ 
/*     */   public Article back(Integer id, User user, String reason, boolean initial) {
/* 327 */     Article article = findById(id);
/* 328 */     Integer siteId = article.getSite().getId();
/* 329 */     Role role = user.getAdmin().getRole(siteId);
/* 330 */     if ((article.getStatus().equals(Byte.valueOf((byte)1))) || 
/* 331 */       (article.getStatus().equals(Byte.valueOf((byte)-1)))) {
/* 332 */       if (article.getChannel().getFlow() != null) {
/* 333 */         if (initial) {
/* 334 */           article.setRole(null);
/*     */         } else {
/* 336 */           FlowDetail fd = this.detailService.getLastFlowDetail(id);
/* 337 */           if (fd != null)
/* 338 */             article.setRole(role);
/*     */           else
/* 340 */             article.setRole(null);
/*     */         }
/*     */       }
/*     */       else {
/* 344 */         article.setRole(null);
/*     */       }
/* 346 */       article.setStatus(Byte.valueOf((byte)-1));
/* 347 */       this.detailService.saveReturn(article, user, role, reason, initial);
/* 348 */       return article;
/*     */     }
/* 350 */     return article;
/*     */   }
/*     */ 
/*     */   @Override
public Article[] check(Integer[] ids, User user) {
/* 354 */     Article[] beans = new Article[ids.length];
/* 355 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 356 */       beans[i] = check(ids[i], user);
/*     */     }
/* 358 */     return beans;
/*     */   }
/*     */ 
/*     */   @Override
public Article cycle(Integer id) {
/* 362 */     Article article = findById(id);
/* 363 */     article.setStatus(Byte.valueOf((byte)3));
/* 364 */     this.luceneDocService.deleteDoc(id);
/* 365 */     this.applicationContext.publishEvent(new DelArticleEvent(article, Integer.valueOf(1)));
/* 366 */     return article;
/*     */   }
/*     */ 
/*     */   @Override
public Article[] cycle(Integer[] ids) {
/* 370 */     Article[] beans = new Article[ids.length];
/* 371 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 372 */       beans[i] = cycle(ids[i]);
/*     */     }
/* 374 */     return beans;
/*     */   }
/*     */ 
/*     */   @Override
public Article reduct(Integer id) {
/* 378 */     Article article = findById(id);
/* 379 */     article.setStatus(Byte.valueOf((byte)2));
/* 380 */     this.luceneDocService.addDoc(article);
/* 381 */     this.applicationContext.publishEvent(new AddArticleEvent(article));
/* 382 */     return article;
/*     */   }
/*     */ 
/*     */   @Override
public Article[] reduct(Integer[] ids) {
/* 386 */     Article[] beans = new Article[ids.length];
/* 387 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 388 */       beans[i] = reduct(ids[i]);
/*     */     }
/* 390 */     return beans;
/*     */   }
/*     */ 
/*     */   @Override
public int updateDocByInputUser(Integer userId) {
/* 394 */     return this.dao.updateDocByInputUser(userId);
/*     */   }
/*     */ 
/*     */   @Override
public int updateDocByCheckUser(Integer userId) {
/* 398 */     return this.dao.updateDocByCheckUser(userId);
/*     */   }
/*     */ 
/*     */   @Override
public int updateDocByInputDepart(Integer departId) {
/* 402 */     return this.dao.updateDocByInputDepart(departId);
/*     */   }
/*     */ 
/*     */   @Override
public int updateDocByRole(Integer roleId) {
/* 406 */     return this.dao.updateDocByRole(roleId);
/*     */   }
/*     */ 
/*     */   @Override
public int delDocByInputUser(Integer userId) {
/* 410 */     return this.dao.delDocByInputUser(userId);
/*     */   }
/*     */ 
/*     */   @Override
public int delDocBySite(Integer siteId) {
/* 414 */     return this.dao.delDocBySite(siteId);
/*     */   }
/*     */ 
/*     */   @Override
public Article deleteById(Integer id) {
/* 418 */     Article bean = this.dao.deleteById(id);
/* 419 */     this.applicationContext.publishEvent(new DelArticleEvent(bean, Integer.valueOf(0)));
/* 420 */     this.luceneDocService.deleteDoc(id);
/* 421 */     return bean;
/*     */   }
/*     */ 
/*     */   @Override
public Article[] deleteByIds(Integer[] ids) {
/* 425 */     Article[] beans = new Article[ids.length];
/* 426 */     int i = 0; for (int len = ids.length; i < len; i++) {
/* 427 */       beans[i] = deleteById(ids[i]);
/*     */     }
/* 429 */     return beans;
/*     */   }
/*     */ 
/*     */   private void flowstep(Article bean, Site site, User user) {
/* 433 */     if (bean.getStatus() == null) {
/* 434 */       Depart depart = user.getDepart(site.getId());
/* 435 */       Role role = user.getRole(site.getId());
/* 436 */       bean.setCheckUser(user);
/* 437 */       bean.setInputDepart(depart);
/* 438 */       if (bean.getChannel().getFlow() != null) {
/* 439 */         if (bean.getChannel().getFlowStep(role.getId()) != null) {
/* 440 */           if (bean.getChannel().isLastStep(role)) {
/* 441 */             bean.setStatus(Byte.valueOf((byte)2));
/* 442 */             bean.setRole(role);
/*     */           } else {
/* 444 */             bean.setStatus(Byte.valueOf((byte)1));
/* 445 */             bean.setRole(bean.getChannel()
/* 446 */               .getNextRole(role.getId()));
/*     */           }
/*     */         }
/* 449 */         else bean.setStatus(Byte.valueOf((byte)2));
/*     */       }
/*     */       else
/* 452 */         bean.setStatus(Byte.valueOf((byte)2));
/*     */     }
/*     */   }
/*     */ 
/*     */   private String getTreeNumber(Integer cId)
/*     */   {
/* 458 */     if (cId == null) {
/* 459 */       return null;
/*     */     }
/* 461 */     Channel c = this.channelService.findById(cId);
/* 462 */     if (c != null) {
/* 463 */       return c.getNumber();
/*     */     }
/* 465 */     return null;
/*     */   }
/*     */ 
/*     */   private String getTreeNumber(Integer[] cIds) {
/* 469 */     if ((cIds == null) || (cIds.length == 0)) {
/* 470 */       return null;
/*     */     }
/* 472 */     Channel c = this.channelService.findById(cIds[0]);
/* 473 */     if (c != null) {
/* 474 */       return c.getNumber();
/*     */     }
/* 476 */     return null;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setChannelService(ChannelService channelService)
/*     */   {
/* 494 */     this.channelService = channelService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setArticleExtService(ArticleExtService articleExtService) {
/* 499 */     this.articleExtService = articleExtService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setArticleTxtService(ArticleTxtService articleTxtService) {
/* 504 */     this.articleTxtService = articleTxtService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setStatisService(DocStatisService statisService) {
/* 509 */     this.statisService = statisService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setArticleTypeService(ArticleTypeService articleTypeService) {
/* 514 */     this.articleTypeService = articleTypeService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setGroupService(GroupService groupService) {
/* 519 */     this.groupService = groupService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setModelService(ModelService modelService) {
/* 524 */     this.modelService = modelService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setLuceneDocService(LuceneDocService luceneDocService) {
/* 529 */     this.luceneDocService = luceneDocService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDetailService(FlowDetailService detailService) {
/* 534 */     this.detailService = detailService;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setDao(ArticleDao dao) {
/* 539 */     this.dao = dao;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setArticleQueryDao(ArticleQueryDao articleQueryDao) {
/* 544 */     this.articleQueryDao = articleQueryDao;
/*     */   }
/*     */ 
/*     */   @Autowired
/*     */   public void setApplicationContext(ApplicationContext applicationContext) {
/* 549 */     this.applicationContext = applicationContext;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.service.impl.ArticleServiceImpl
 * JD-Core Version:    0.6.1
 */
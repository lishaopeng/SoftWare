/*     */ package com.portal.doccenter.dao.impl;
/*     */ 
/*     */ /*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.List;

/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.hibernate.CacheMode;
/*     */ import org.hibernate.Criteria;
/*     */ import org.hibernate.ScrollMode;
/*     */ import org.hibernate.ScrollableResults;
/*     */ import org.hibernate.Session;
/*     */ import org.hibernate.criterion.Disjunction;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.ProjectionList;
/*     */ import org.hibernate.criterion.Projections;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Repository;

import com.javapms.basic.hibernate4.QueryDaoImpl;
/*     */ import com.portal.doccenter.dao.ArticleDao;
/*     */ import com.portal.doccenter.entity.Article;
/*     */ import com.portal.doccenter.entity.Channel;
/*     */ 
/*     */ @Repository
/*     */ public class ArticleDaoImpl extends QueryDaoImpl<Article, Integer>
/*     */   implements ArticleDao
/*     */ {
/*     */   @Override
public Page<Article> getPageArticle(String title, Integer[] typeIds, Integer[] modelIds, Integer inputDepartId, boolean top, boolean recommend, Byte[] statuss, Integer siteId, Integer userId, Integer departId, Integer roleId, String number, Byte manageStatus, Boolean allChannel, Boolean takeDepart, int orderBy, String sortname, String sortorder, int pageNo, int pageSize)
/*     */   {
/*  37 */     Criteria crit = byPageArticle(title, typeIds, modelIds, inputDepartId, 
/*  38 */       top, recommend, statuss, siteId, userId, departId, roleId, 
/*  39 */       number, manageStatus, allChannel, takeDepart);
/*  40 */     if (!StringUtils.isBlank(sortname)) {
/*  41 */       if ("asc".equals(sortorder))
/*  42 */         crit.addOrder(Order.asc(sortname));
/*     */       else
/*  44 */         crit.addOrder(Order.desc(sortname));
/*     */     }
/*     */     else {
/*  47 */       crit.addOrder(Order.desc("releaseDate"));
/*     */     }
/*  49 */     return findByCriteria(crit, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Override
public Page<Article> getPageDocByMember(String title, Integer[] typeIds, Integer[] modelIds, boolean top, boolean recommend, Integer siteId, Integer userId, String number, int pageNo, int pageSize)
/*     */   {
/*  55 */     Criteria crit = createCriteria();
/*  56 */     crit.createAlias("channel", "c");
/*  57 */     if (!StringUtils.isBlank(number))
/*  58 */       crit.add(Restrictions.like("c.number", number + "%"));
/*  59 */     else if (siteId != null) {
/*  60 */       crit.add(Restrictions.eq("site.id", siteId));
/*     */     }
/*  62 */     crit.add(Restrictions.eq("user.id", userId));
/*  63 */     if (!StringUtils.isBlank(title)) {
/*  64 */       crit.add(Restrictions.like("title", "%" + title + "%"));
/*     */     }
/*  66 */     if (typeIds != null) {
/*  67 */       appendTypeIds(crit, typeIds);
/*     */     }
/*  69 */     if (modelIds != null) {
/*  70 */       appendModelIds(crit, modelIds);
/*     */     }
/*  72 */     if (top) {
/*  73 */       crit.add(Restrictions.eq("top", Boolean.valueOf(true)));
/*     */     }
/*  75 */     if (recommend) {
/*  76 */       crit.add(Restrictions.eq("recommend", Boolean.valueOf(true)));
/*     */     }
/*  78 */     crit.add(Restrictions.ne("status", Byte.valueOf((byte)3)));
/*  79 */     crit.addOrder(Order.desc("releaseDate"));
/*  80 */     return findByCriteria(crit, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Override
public long getCountDoc(String number) {
/*  84 */     Criteria crit = createCriteria();
/*  85 */     crit.createAlias("channel", "c");
/*  86 */     crit.add(Restrictions.like("c.number", number + "%"));
/*  87 */     crit.setProjection(Projections.count("id"));
/*  88 */     return ((Long)crit.uniqueResult()).longValue();
/*     */   }
/*     */ 
/*     */   @Override
public long getCountByChecking(Integer siteId, Integer userId, Integer departId, Integer roleId, Byte manageStatus, Boolean allChannel, boolean checking)
/*     */   {
/*  95 */     Byte[] status = null;
/*  96 */     if (checking) {
			/* 97 */status[0] = (byte) 1;
/*     */     }
/*  99 */     Criteria crit = byPageArticle(null, null, null, null, false, false, 
/* 100 */       status, siteId, userId, departId, roleId, null, manageStatus, 
/* 101 */       allChannel, Boolean.valueOf(false));
/* 102 */     crit.setProjection(Projections.count("id"));
/* 103 */     return ((Long)crit.uniqueResult()).longValue();
/*     */   }
/*     */ 
/*     */   @Override
public long getCountByDepartSign(Integer siteId, Integer departId) {
/* 107 */     Criteria crit = createCriteria();
/* 108 */     crit.createAlias("admins", "a");
/* 109 */     crit.createAlias("a.departs", "d");
/* 110 */     crit.add(Restrictions.eq("d.id", departId));
/* 111 */     crit.setProjection(Projections.count("id"));
/* 112 */     return ((Long)crit.uniqueResult()).longValue();
/*     */   }
/*     */ 
/*     */   @Override
public Article getSide(Integer id, Integer siteId, Integer channelId, boolean next)
/*     */   {
/* 117 */     Criteria crit = createCriteria();
/* 118 */     if (channelId != null)
/* 119 */       crit.add(Restrictions.eq("channel.id", channelId));
/* 120 */     else if (siteId != null) {
/* 121 */       crit.add(Restrictions.eq("site.id", siteId));
/*     */     }
/* 123 */     crit.add(Restrictions.eq("status", Byte.valueOf((byte)2)));
/* 124 */     if (next) {
/* 125 */       crit.add(Restrictions.gt("id", id));
/* 126 */       crit.addOrder(Order.asc("id"));
/*     */     } else {
/* 128 */       crit.add(Restrictions.lt("id", id));
/* 129 */       crit.addOrder(Order.desc("id"));
/*     */     }
/* 131 */     return findUnique(crit);
/*     */   }
/*     */ 
/*     */   @Override
public List<Article> getListTagByIds(Integer[] ids, int orderBy) {
/* 135 */     Criteria crit = createCriteria();
/* 136 */     crit.add(Restrictions.in("id", ids));
/* 137 */     appendOrder(crit, orderBy);
/* 138 */     return findByCriteria(crit);
/*     */   }
/*     */ 
/*     */   @Override
public Page<Article> getPageTagByChannelIds(Integer[] channelIds, Integer siteId, Integer[] modelIds, Integer[] typeIds, Integer inputDepartId, Integer userId, String chnlNumber, Boolean recommend, Date date, int orderBy, int callLevel, int pageNo, int pageSize)
/*     */   {
/* 146 */     Criteria crit = byChannelIds(channelIds, siteId, modelIds, typeIds, 
/* 147 */       inputDepartId, userId, chnlNumber, recommend, date, orderBy, 
/* 148 */       callLevel);
/* 149 */     return findByCriteria(crit, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Override
public List<Article> getListTagByChannelIds(Integer[] channelIds, Integer siteId, Integer[] modelIds, Integer[] typeIds, Integer inputDepartId, Integer userId, String chnlNumber, Boolean recommend, Date date, int orderBy, int callLevel, Integer first, Integer count)
/*     */   {
/* 157 */     Criteria crit = byChannelIds(channelIds, siteId, modelIds, typeIds, 
/* 158 */       inputDepartId, userId, chnlNumber, recommend, date, orderBy, 
/* 159 */       callLevel);
/* 160 */     if (first != null) {
/* 161 */       crit.setFirstResult(first.intValue());
/*     */     }
/* 163 */     if (count != null) {
/* 164 */       crit.setMaxResults(count.intValue());
/*     */     }
/* 166 */     return findByCriteria(crit);
/*     */   }
/*     */ 
/*     */   @Override
public Page<Article> getPageTagByModelIds(Integer[] modelIds, Integer[] typeIds, Integer siteId, Boolean recommend, int orderBy, int pageNo, int pageSize)
/*     */   {
/* 172 */     Criteria crit = byModelIds(modelIds, typeIds, siteId, recommend, 
/* 173 */       orderBy);
/* 174 */     return findByCriteria(crit, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   @Override
public List<Article> getListTagByModelIds(Integer[] modelIds, Integer[] typeIds, Integer siteId, Boolean recommend, int orderBy, Integer first, Integer count)
/*     */   {
/* 180 */     Criteria crit = byModelIds(modelIds, typeIds, siteId, recommend, 
/* 181 */       orderBy);
/* 182 */     if (first != null) {
/* 183 */       crit.setFirstResult(first.intValue());
/*     */     }
/* 185 */     if (count != null) {
/* 186 */       crit.setMaxResults(count.intValue());
/*     */     }
/* 188 */     return findByCriteria(crit);
/*     */   }
/*     */ 
/*     */   @Override
public List<Article> getListByUp(Integer siteId, Integer cid, Integer start, Integer end)
/*     */   {
/* 193 */     Criteria crit = createCriteria();
/* 194 */     crit.add(Restrictions.eq("site.id", siteId));
/* 195 */     if (cid != null) {
/* 196 */       crit.add(Restrictions.eq("channel.id", cid));
/*     */     }
/* 198 */     crit.add(Restrictions.ge("id", start));
/* 199 */     crit.add(Restrictions.le("id", end));
/* 200 */     return findByCriteria(crit);
/*     */   }
/*     */ 
/*     */   @Override
public List<Object> getCountByDepart(Integer siteId, String treeNumber, Integer dId, Date start, Date end)
/*     */   {
/* 206 */     Criteria crit = createCriteria();
/* 207 */     crit.createAlias("inputDepart", "d");
/* 208 */     crit.createAlias("channel", "c");
/* 209 */     crit.add(Restrictions.eq("site.id", siteId));
/* 210 */     if (!StringUtils.isBlank(treeNumber)) {
/* 211 */       crit.add(Restrictions.like("c.number", treeNumber + "%"));
/*     */     }
/* 213 */     if (dId != null) {
/* 214 */       crit.add(Restrictions.eq("d.id", dId));
/*     */     }
/* 216 */     if (start != null) {
/* 217 */       crit.add(Restrictions.gt("releaseDate", start));
/*     */     }
/* 219 */     if (end != null) {
/* 220 */       crit.add(Restrictions.gt("releaseDate", end));
/*     */     }
/* 222 */     crit.add(Restrictions.eq("d.show", Boolean.valueOf(true)));
/* 223 */     crit.add(Restrictions.eq("status", Byte.valueOf((byte)2)));
/* 224 */     ProjectionList projList = Projections.projectionList();
/* 225 */     projList.add(Projections.groupProperty("d.name"));
/* 226 */     projList.add(Projections.alias(Projections.count("inputDepart"), "c"));
/* 227 */     crit.setProjection(projList);
/* 228 */     crit.addOrder(Order.desc("c"));
/* 229 */     return crit.list();
/*     */   }
/*     */ 
/*     */   @Override
public int getAllArtiCount(Integer siteId, boolean check) {
/* 233 */     Criteria crit = createCriteria();
/* 234 */     crit.add(Restrictions.eq("site.id", siteId));
/* 235 */     if (check)
/* 236 */       crit.add(Restrictions.eq("status", Byte.valueOf((byte)1)));
/*     */     else {
/* 238 */       crit.add(Restrictions.ne("status", Byte.valueOf((byte)3)));
/*     */     }
/* 240 */     crit.setProjection(Projections.count("id"));
/* 241 */     return ((Number)crit.uniqueResult()).intValue();
/*     */   }
/*     */ 
/*     */   @Override
public int updateMoveDoc(String treeNumber, Integer modelId, Channel c) {
/* 245 */     String hql = "update Article bean set bean.channel=? where bean.model.id=? and bean.channel.id in (select c.id from Channel c where c.number like ?)";
/*     */ 
/* 247 */     return executeQuery(hql, new Object[] { c, modelId, treeNumber + "%" });
/*     */   }
/*     */ 
/*     */   @Override
public int updateDocByInputUser(Integer userId) {
/* 251 */     String hql = "update Article bean set bean.user=null where bean.user.id=?";
/* 252 */     return executeQuery(hql, new Object[] { userId });
/*     */   }
/*     */ 
/*     */   @Override
public int updateDocByCheckUser(Integer userId) {
/* 256 */     String hql = "update Article bean set bean.checkUser=null where bean.checkUser.id=?";
/* 257 */     return executeQuery(hql, new Object[] { userId });
/*     */   }
/*     */ 
/*     */   @Override
public int updateDocByInputDepart(Integer departId) {
/* 261 */     String hql = "update Article bean set bean.inputDepart=null where bean.inputDepart.id=?";
/* 262 */     return executeQuery(hql, new Object[] { departId });
/*     */   }
/*     */ 
/*     */   @Override
public int updateDocByRole(Integer roleId) {
/* 266 */     String hql = "update Article bean set bean.role=null where bean.role.id=?";
/* 267 */     return executeQuery(hql, new Object[] { roleId });
/*     */   }
/*     */ 
/*     */   @Override
public int delDocByInputUser(Integer userId) {
/* 271 */     Criteria crit = createCriteria();
/* 272 */     crit.add(Restrictions.eq("user.id", userId));
/* 273 */     Session session = getSession();
/* 274 */     ScrollableResults articles = crit.setCacheMode(CacheMode.IGNORE)
/* 275 */       .scroll(ScrollMode.FORWARD_ONLY);
/*     */ 
/* 277 */     int count = 0;
/* 278 */     while (articles.next()) {
/* 279 */       Article article = (Article)articles.get(0);
/* 280 */       session.delete(article);
/*     */ 
/* 282 */       count++; if (count % 20 == 0) {
/* 283 */         session.clear();
/*     */       }
/*     */     }
/* 286 */     return count;
/*     */   }
/*     */ 
/*     */   @Override
public int delDocBySite(Integer siteId) {
/* 290 */     Criteria crit = createCriteria();
/* 291 */     crit.add(Restrictions.eq("site.id", siteId));
/* 292 */     Session session = getSession();
/* 293 */     ScrollableResults articles = crit.setCacheMode(CacheMode.IGNORE)
/* 294 */       .scroll(ScrollMode.FORWARD_ONLY);
/*     */ 
/* 296 */     int count = 0;
/* 297 */     while (articles.next()) {
/* 298 */       Article article = (Article)articles.get(0);
/* 299 */       session.delete(article);
/*     */ 
/* 301 */       count++; if (count % 20 == 0) {
/* 302 */         session.clear();
/*     */       }
/*     */     }
/* 305 */     return count;
/*     */   }
/*     */ 
/*     */   private Criteria byChannelIds(Integer[] channelIds, Integer siteId, Integer[] modelIds, Integer[] typeIds, Integer departId, Integer userId, String number, Boolean recommend, Date date, int orderBy, int callLevel)
/*     */   {
/* 312 */     Criteria crit = createCriteria();
/* 313 */     if (channelIds != null) {
/* 314 */       if (callLevel == 1) {
/* 315 */         crit.createAlias("channel", "c");
/* 316 */         if (!StringUtils.isBlank(number))
/* 317 */           crit.add(Restrictions.like("c.number", number + "%"));
/*     */       }
/* 319 */       else if (callLevel == 2) {
				/* 320 */crit.createAlias("channel", "c");
/* 321 */         crit.add(Restrictions.eq("c.id", channelIds[0]));
/*     */       } else {
/* 323 */         appendChannelIds(crit, channelIds);
/*     */       }
/*     */     }
/* 326 */     else crit.add(Restrictions.eq("site.id", siteId));
/*     */ 
/* 328 */     if (departId != null) {
/* 329 */       crit.add(Restrictions.eq("inputDepart.id", departId));
/*     */     }
/* 331 */     if (userId != null) {
/* 332 */       crit.add(Restrictions.eq("user.id", userId));
/*     */     }
/* 334 */     if (recommend != null) {
/* 335 */       crit.add(Restrictions.eq("recommend", recommend));
/*     */     }
/* 337 */     if (date != null) {
/* 338 */       Calendar c = Calendar.getInstance();
/* 339 */       c.setTime(date);
/* 340 */       c.add(5, 1);
/* 341 */       crit.add(Restrictions.between("releaseDate", date, c.getTime()));
/*     */     }
/* 343 */     appendModelIds(crit, modelIds);
/* 344 */     appendTypeIds(crit, typeIds);
/* 345 */     crit.add(Restrictions.eq("status", Byte.valueOf((byte)2)));
/* 346 */     appendOrder(crit, orderBy);
/* 347 */     return crit;
/*     */   }
/*     */ 
/*     */   private Criteria byPageArticle(String title, Integer[] typeIds, Integer[] modelIds, Integer inputDepartId, boolean top, boolean recommend, Byte[] statuss, Integer siteId, Integer userId, Integer departId, Integer roleId, String number, Byte manageStatus, Boolean allChannel, Boolean takeDepart)
/*     */   {
/* 355 */     Criteria crit = createCriteria();
/* 356 */     crit.createAlias("channel", "c");
/* 357 */     if (!StringUtils.isBlank(number))
/* 358 */       crit.add(Restrictions.like("c.number", number + "%"));
/*     */     else {
/* 360 */       crit.add(Restrictions.eq("site.id", siteId));
/*     */     }
/* 362 */     if (!allChannel.booleanValue()) {
/* 363 */       if (!takeDepart.booleanValue()) {
/* 364 */         crit.createAlias("c.checks", "check");
/* 365 */         crit.add(Restrictions.eq("check.admin.id", userId));
/*     */       } else {
/* 367 */         crit.createAlias("c.departs", "depart");
/* 368 */         crit.add(Restrictions.eq("depart.id", departId));
/*     */       }
/*     */     }
/* 371 */     if (inputDepartId != null) {
/* 372 */       crit.add(Restrictions.eq("inputDepart.id", inputDepartId));
/*     */     }
/* 374 */     if (manageStatus.equals(Byte.valueOf((byte)1))) {
/* 375 */       crit.add(Restrictions.eq("user.id", userId));
/* 376 */     } else if (manageStatus.equals(Byte.valueOf((byte)2))) {
/* 377 */       crit.add(Restrictions.eq("inputDepart.id", departId));
/* 378 */       crit.add(Restrictions.or(Restrictions.eq("user.id", userId), 
/* 379 */         Restrictions.eq("role.id", roleId)));
/* 380 */     } else if (manageStatus.equals(Byte.valueOf((byte)3))) {
/* 381 */       crit.add(Restrictions.eq("inputDepart.id", departId));
/*     */     }
/* 383 */     if (!StringUtils.isBlank(title)) {
/* 384 */       crit.add(Restrictions.like("title", "%" + title + "%"));
/*     */     }
/* 386 */     if (typeIds != null) {
/* 387 */       appendTypeIds(crit, typeIds);
/*     */     }
/* 389 */     if (modelIds != null) {
/* 390 */       appendModelIds(crit, modelIds);
/*     */     }
/* 392 */     if (top) {
/* 393 */       crit.add(Restrictions.eq("top", Boolean.valueOf(true)));
/*     */     }
/* 395 */     if (recommend) {
/* 396 */       crit.add(Restrictions.eq("recommend", Boolean.valueOf(true)));
/*     */     }
/* 398 */     if (statuss != null)
/* 399 */       appendStatuss(crit, statuss);
/*     */     else {
/* 401 */       crit.add(Restrictions.ne("status", Byte.valueOf((byte)3)));
/*     */     }
/* 403 */     return crit;
/*     */   }
/*     */ 
/*     */   private Criteria byModelIds(Integer[] modelIds, Integer[] typeIds, Integer siteId, Boolean recommend, int orderBy)
/*     */   {
/* 408 */     Criteria crit = createCriteria();
/* 409 */     crit.add(Restrictions.eq("site.id", siteId));
/* 410 */     appendModelIds(crit, modelIds);
/* 411 */     if (recommend != null) {
/* 412 */       crit.add(Restrictions.eq("recommend", recommend));
/*     */     }
/* 414 */     appendTypeIds(crit, typeIds);
/* 415 */     crit.add(Restrictions.eq("status", Byte.valueOf((byte)2)));
/* 416 */     appendOrder(crit, orderBy);
/* 417 */     return crit;
/*     */   }
/*     */ 
/*     */   private void appendChannelIds(Criteria crit, Integer[] channelIds) {
/* 421 */     if (channelIds != null) {
/* 422 */       int len = channelIds.length;
/* 423 */       if (len == 1)
/* 424 */         crit.add(Restrictions.eq("channel.id", channelIds[0]));
/*     */       else
/* 426 */         crit.add(Restrictions.in("channel.id", channelIds));
/*     */     }
/*     */   }
/*     */ 
/*     */   private void appendModelIds(Criteria crit, Integer[] modelIds)
/*     */   {
/* 432 */     if (modelIds != null) {
/* 433 */       int len = modelIds.length;
/* 434 */       if (len == 1)
/* 435 */         crit.add(Restrictions.eq("model.id", modelIds[0]));
/*     */       else
/* 437 */         crit.add(Restrictions.in("model.id", modelIds));
/*     */     }
/*     */   }
/*     */ 
/*     */   private void appendStatuss(Criteria crit, Byte[] statuss)
/*     */   {
/* 444 */     if (statuss != null) {
/* 445 */       int len = statuss.length;
/* 446 */       if (len == 1) {
/* 447 */         crit.add(Restrictions.eq("status", statuss[0]));
/* 448 */       } else if (len > 1) {
/* 449 */         Disjunction dis = Restrictions.disjunction();
/* 450 */         for (Byte status : statuss) {
/* 451 */           dis.add(Restrictions.eq("status", status));
/*     */         }
/* 453 */         crit.add(dis);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void appendTypeIds(Criteria crit, Integer[] typeIds)
/*     */   {
/* 460 */     if (typeIds != null) {
/* 461 */       int len = typeIds.length;
/* 462 */       if (len == 1) {
/* 463 */         crit.add(Restrictions.like("style", "%," + typeIds[0] + ",%"));
/* 464 */       } else if (len > 1) {
/* 465 */         Disjunction dis = Restrictions.disjunction();
/* 466 */         for (Integer typeId : typeIds) {
/* 467 */           dis.add(Restrictions.like("style", "%," + typeId + ",%"));
/*     */         }
/* 469 */         crit.add(dis);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private void appendOrder(Criteria crit, int orderBy) {
/* 475 */     switch (orderBy) {
/*     */     case 1:
/* 477 */       crit.addOrder(Order.desc("releaseDate"));
/* 478 */       break;
/*     */     case 2:
/* 480 */       crit.addOrder(Order.desc("top"));
/* 481 */       crit.addOrder(Order.desc("releaseDate"));
/* 482 */       break;
/*     */     case 3:
/* 484 */       crit.addOrder(Order.desc("recommend"));
/* 485 */       crit.addOrder(Order.desc("releaseDate"));
/* 486 */       break;
/*     */     case 4:
/* 488 */       crit.createAlias("docStatis", "docStatis");
/* 489 */       crit.addOrder(Order.desc("docStatis.viewsCount"));
/* 490 */       crit.addOrder(Order.desc("releaseDate"));
/* 491 */       break;
/*     */     case 5:
/* 493 */       crit.createAlias("docStatis", "docStatis");
/* 494 */       crit.addOrder(Order.desc("docStatis.commentCount"));
/* 495 */       crit.addOrder(Order.desc("releaseDate"));
/* 496 */       break;
/*     */     case 6:
/* 498 */       crit.addOrder(Order.desc("top"));
/* 499 */       crit.addOrder(Order.desc("recommend"));
/* 500 */       crit.addOrder(Order.desc("releaseDate"));
/* 501 */       break;
/*     */     case 7:
/* 503 */       crit.addOrder(Order.asc("releaseDate"));
/* 504 */       break;
/*     */     default:
/* 506 */       crit.addOrder(Order.desc("releaseDate"));
/*     */     }
/*     */   }
/*     */ 
/*     */   @Override
protected Class<Article> getEntityClass()
/*     */   {
/* 512 */     return Article.class;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.doccenter.dao.impl.ArticleDaoImpl
 * JD-Core Version:    0.6.1
 */
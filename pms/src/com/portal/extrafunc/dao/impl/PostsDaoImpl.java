/*     */ package com.portal.extrafunc.dao.impl;
/*     */ 
/*     */ import com.javapms.basic.hibernate4.QueryDaoImpl;
/*     */ import com.portal.extrafunc.dao.PostsDao;
/*     */ import com.portal.extrafunc.entity.Posts;
/*     */ import org.hibernate.CacheMode;
/*     */ import org.hibernate.Criteria;
/*     */ import org.hibernate.ScrollMode;
/*     */ import org.hibernate.ScrollableResults;
/*     */ import org.hibernate.Session;
/*     */ import org.hibernate.criterion.Order;
/*     */ import org.hibernate.criterion.Projections;
/*     */ import org.hibernate.criterion.Restrictions;
/*     */ import org.springframework.data.domain.Page;
/*     */ import org.springframework.stereotype.Repository;
/*     */ 
/*     */ @Repository
/*     */ public class PostsDaoImpl extends QueryDaoImpl<Posts, Integer>
/*     */   implements PostsDao
/*     */ {
/*     */   public Page<Posts> getPage(int pageNo, int pageSize)
/*     */   {
/*  22 */     Criteria crit = createCriteria();
/*  23 */     return findByCriteria(crit, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public Page<Posts> getPostsPageForTag(Integer themeId, int pageNo, int pageSize)
/*     */   {
/*  28 */     Criteria crit = createCriteria();
/*  29 */     crit.add(Restrictions.eq("theme.id", themeId));
/*  30 */     crit.addOrder(Order.asc("floor"));
/*  31 */     return findByCriteria(crit, pageNo, pageSize);
/*     */   }
/*     */ 
/*     */   public Posts getPostsOneFloor(Integer themeId) {
/*  35 */     Criteria crit = createCriteria();
/*  36 */     crit.add(Restrictions.eq("theme.id", themeId));
/*  37 */     crit.add(Restrictions.eq("floor", Integer.valueOf(1)));
/*  38 */     return (Posts)findUnique(crit);
/*     */   }
/*     */ 
/*     */   public Posts getLastPostsByUserAndForum(Integer forumId, Integer userId) {
/*  42 */     Criteria crit = createCriteria();
/*  43 */     crit.createAlias("theme", "t");
/*  44 */     crit.add(Restrictions.eq("t.forum.id", forumId));
/*  45 */     crit.add(Restrictions.ne("creater.id", userId));
/*  46 */     crit.addOrder(Order.desc("createTime"));
/*  47 */     return (Posts)findUnique(crit);
/*     */   }
/*     */ 
/*     */   public Posts getLastPostsByUserAndTheme(Integer themeId, Integer userId) {
/*  51 */     Criteria crit = createCriteria();
/*  52 */     crit.add(Restrictions.eq("theme.id", themeId));
/*  53 */     crit.add(Restrictions.ne("creater.id", userId));
/*  54 */     crit.addOrder(Order.desc("createTime"));
/*  55 */     return (Posts)findUnique(crit);
/*     */   }
/*     */ 
/*     */   public Integer getAllPostCount(Integer siteId) {
/*  59 */     Criteria crit = createCriteria();
/*  60 */     crit.add(Restrictions.eq("site.id", siteId));
/*  61 */     crit.setProjection(Projections.count("id"));
/*  62 */     return Integer.valueOf(((Number)crit.uniqueResult()).intValue());
/*     */   }
/*     */ 
/*     */   public Integer getFloorByTheme(Integer themeId) {
/*  66 */     Criteria crit = createCriteria();
/*  67 */     crit.add(Restrictions.eq("theme.id", themeId));
/*  68 */     crit.setProjection(Projections.max("floor"));
/*  69 */     return Integer.valueOf(((Number)crit.uniqueResult()).intValue() + 1);
/*     */   }
/*     */ 
/*     */   public int updatePostsBySite(Integer siteId) {
/*  73 */     String hql = "update Posts bean set bean.quote=null where bean.site.id=?";
/*  74 */     return executeQuery(hql, new Object[] { siteId });
/*     */   }
/*     */ 
/*     */   public int updatePostsByCategory(Integer categoryId) {
/*  78 */     String hql = "update Posts bean set bean.quote=null where bean.theme.id in (select t.id from Theme t inner join t.forum f where f.id in (select f.id from Forum c inner join f.category c where c.id=?))";
/*     */ 
/*  81 */     return executeQuery(hql, new Object[] { categoryId });
/*     */   }
/*     */ 
/*     */   public int updatePostsByForum(Integer forumId) {
/*  85 */     String hql = "update Posts bean set bean.quote=null where bean.theme.id in (select t.id from Theme t inner join t.forum f where f.id=?)";
/*     */ 
/*  87 */     return executeQuery(hql, new Object[] { forumId });
/*     */   }
/*     */ 
/*     */   public int updatePostsByTheme(Integer themeId) {
/*  91 */     String hql = "update Posts bean set bean.quote=null where bean.theme.id=?";
/*  92 */     return executeQuery(hql, new Object[] { themeId });
/*     */   }
/*     */ 
/*     */   public int updatePostsByUser(Integer userId) {
/*  96 */     String hql = "update Posts bean set bean.quote=null where bean.quote.id in (select p.id from Posts p inner join p.creater c where c.id=?)";
/*     */ 
/*  98 */     return executeQuery(hql, new Object[] { userId });
/*     */   }
/*     */ 
/*     */   public int deletePosts(Integer siteId, Integer categoryId, Integer forumId, Integer themeId, Integer userId)
/*     */   {
/* 103 */     Criteria crit = createCriteria();
/* 104 */     if (siteId != null) {
/* 105 */       crit.createAlias("site", "s");
/* 106 */       crit.add(Restrictions.eq("s.id", siteId));
/*     */     }
/* 108 */     if (categoryId != null) {
/* 109 */       crit.createAlias("theme", "t");
/* 110 */       crit.createAlias("t.forum", "f");
/* 111 */       crit.createAlias("f.category", "c");
/* 112 */       crit.add(Restrictions.eq("c.id", categoryId));
/*     */     }
/* 114 */     if (forumId != null) {
/* 115 */       crit.createAlias("theme", "t");
/* 116 */       crit.createAlias("t.forum", "f");
/* 117 */       crit.add(Restrictions.eq("f.id", forumId));
/*     */     }
/* 119 */     if (themeId != null) {
/* 120 */       crit.add(Restrictions.eq("theme.id", themeId));
/*     */     }
/* 122 */     Session session = getSession();
/* 123 */     ScrollableResults posts = crit.setCacheMode(CacheMode.IGNORE).scroll(
/* 124 */       ScrollMode.FORWARD_ONLY);
/*     */ 
/* 126 */     int count = 0;
/* 127 */     while (posts.next()) {
/* 128 */       Posts p = (Posts)posts.get(0);
/* 129 */       session.delete(p);
/* 130 */       count++; if (count % 20 == 0) {
/* 131 */         session.clear();
/*     */       }
/*     */     }
/* 134 */     return count;
/*     */   }
/*     */ 
/*     */   protected Class<Posts> getEntityClass()
/*     */   {
/* 139 */     return Posts.class;
/*     */   }
/*     */ }

/* Location:           C:\Users\anli\Desktop\classes\
 * Qualified Name:     com.portal.extrafunc.dao.impl.PostsDaoImpl
 * JD-Core Version:    0.6.1
 */